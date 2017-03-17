package com.ireslab.broadcast.gateway;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import com.callfire.api.data.AnsweringMachineConfig;
import com.callfire.api.data.Call;
import com.callfire.api.data.IvrBroadcastConfig;
import com.callfire.api.data.Result;
import com.callfire.api.data.ToNumber;
import com.callfire.api.data.VoiceBroadcastConfig;
import com.callfire.api.service.wsdl.CallServicePortType;
import com.callfire.api.service.wsdl.ServiceFault;
import com.callfire.api.service.wsdl.http.soap12.CallFireApi;
import com.callfire.api.service.wsdl.http.soap12.CallFireApi.ServiceState;
import com.callfire.api.service.xsd.ActionQuery;
import com.callfire.api.service.xsd.CallQueryResult;
import com.callfire.api.service.xsd.CreateSound;
import com.callfire.api.service.xsd.SendCall;

/**
 * Example implementation of using CallFire's APIs to send a simple voice
 * broadcast to multiple contacts.
 * 
 * @author ross
 */
public class SimpleVoiceBroadcast {

	private static final Logger LOG = Logger.getLogger(SimpleVoiceBroadcast.class.getName());

	private static final File TEST_LIVE_SOUND_FILE = new File("../callfire-api-samples/src/resources/test-sound.mp3");
	private static final File TEST_MACHINE_SOUND_FILE = new File("../callfire-api-samples/src/resources/test-sound.mp3");

	private int maxWaitMs;
	private final CallFireApi callFireApi;
	private final CallServicePortType callService;

	String userName = "";
	String password = "";
	String messageByIVR = "";
	String fromNumber = "8448835388";

	public SimpleVoiceBroadcast(CallFirePropertiesInfo callFirePropertiesInfo) {
		maxWaitMs = 50000;
		userName = callFirePropertiesInfo.getUserName();
		password = callFirePropertiesInfo.getPassword();
		callFireApi = new CallFireApi(userName, password, ServiceState.PRODUCTION);
		callService = callFireApi.getCallServicePort();
	}

	/**
	 * @param txtMsg
	 * @param toNumbers
	 * @param fromNumber
	 * @throws ServiceFault
	 */
	public long sendVoiceBroadcastUsingCallService(String type, String securityCode, String toNumber,
			CallFirePropertiesInfo callFirePropertiesInfo) throws ServiceFault {
		String ivrMessage = callFirePropertiesInfo.getIvrMessageForSecurityCode();
		// String ivrMessage
		// ="<dialplan name=\"Root\"> <play type=\"tts\" voice=\"female2\" name=\"mainOnPick\">Your six digit verification code for Engage is : SAMPLE_CODE</play> <menu maxDigits=\"1\" timeout=\"3500\" name=\"mainMenu\"> <play type=\"tts\" voice=\"female2\" name=\"menuOptions\">Press 1 to repeat. Press any other key to disconnect the call.</play> <keypress pressed=\"1\" name=\"mainOptionRepeat\"> <goto name=\"jumper\">mainOnPick</goto> </keypress> <keypress pressed=\"default\" name=\"nothingToDo\"> <hangup name=\"hangUpNow\"/> </keypress> </menu> </dialplan>";
		Long broadcastId = 0L;

		// send a single-call IVR broadcast
		IvrBroadcastConfig config = new IvrBroadcastConfig();

		String ivrMsgToSend = ivrMessage.replaceAll("SAMPLE_CODE", securityCode.replaceAll(".(?!$)", "$0 . "));
		LOG.info("IVR message :"+ivrMsgToSend);
		config.setDialplanXml(ivrMsgToSend);

		config.setFromNumber(fromNumber);

		SendCall sendCall = new SendCall();
		sendCall.setBroadcastName("Simple Voice Broadcast");

		ToNumber toNumberElem = new ToNumber();
		toNumberElem.setValue(toNumber);
		sendCall.getToNumber().add(toNumberElem);

		sendCall.setScrubBroadcastDuplicates(false);
		sendCall.setIvrBroadcastConfig(config);

		broadcastId = callService.sendCall(sendCall);

		return broadcastId;
	}

	/**
	 * @param broadcastId
	 * @param toNumbers
	 * @return true if Result.SENT is received for each specified toNumber.
	 */
	private boolean pollForResponse(long broadcastId, List<Object> toNumbers) throws ServiceFault {
		LOG.info("polling for call status");

		final int sleepInterval = 2000;
		int totalWait = 0;

		ActionQuery callQuery = new ActionQuery();
		callQuery.setBroadcastId(broadcastId);

		while (totalWait < maxWaitMs) {
			try {
				Thread.sleep(sleepInterval);
				totalWait += sleepInterval;
			} catch (InterruptedException e) {
				LOG.log(Level.FINE, "interrupted", e);
			}

			CallQueryResult result = callService.queryCalls(callQuery);
			List<Call> callList = result.getCall();
			if (callList.isEmpty()) {
				throw new RuntimeException("call query did not return a call");
			}

			for (Call call : callList) {
				Result finalResult = call.getFinalResult();
				String toNumber = call.getToNumber().getValue();
				if (finalResult != null) {
					if (!finalResult.equals(Result.UNDIALED)) {
						toNumbers.remove(toNumber);
						LOG.info("found toNumber: " + toNumber);
						if (toNumbers.isEmpty()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private long createSound(File soundFile) throws ServiceFault {
		CreateSound sound = new CreateSound();
		sound.setName("some sound");

		DataSource source = new FileDataSource(soundFile);
		DataHandler dataHandler = new DataHandler(source);

		sound.setData(dataHandler);

		long soundId = callService.createSound(sound);
		return soundId;
	}

	/**
	 * Exercises sending a simple voice broadcast to multiple contacts.
	 * 
	 * @throws Exception
	 */
	public void run() throws Exception {

		long broadcastId = sendVoiceBroadcastUsingCallService("IVR", "123456", "+15104693350", null);

		boolean response = pollForResponse(broadcastId, null);

		if (!response) {
			LOG.log(Level.WARNING, "did not receive postive reponse.");
		} else {
			LOG.log(Level.INFO, "received postive reponse.");
		}
	}

	public static void main(String[] args) {

		CallFirePropertiesInfo callFirePropertiesInfo = new CallFirePropertiesInfo();
		callFirePropertiesInfo.setUserName("f456db1d2ba7");
		callFirePropertiesInfo.setPassword("6856c4a87bf885b6");

		try {
			new SimpleVoiceBroadcast(callFirePropertiesInfo).run();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "exception running " + SimpleVoiceBroadcast.class.getName(), e);
		}
	}
}
