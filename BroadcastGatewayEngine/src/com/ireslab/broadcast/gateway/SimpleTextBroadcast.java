package com.ireslab.broadcast.gateway;

import java.security.Security;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.callfire.api.data.Result;
import com.callfire.api.data.Text;
import com.callfire.api.data.TextBroadcastConfig;
import com.callfire.api.data.ToNumber;
import com.callfire.api.service.wsdl.ServiceFault;
import com.callfire.api.service.wsdl.TextServicePortType;
import com.callfire.api.service.wsdl.http.soap12.CallFireApi;
import com.callfire.api.service.wsdl.http.soap12.CallFireApi.ServiceState;
import com.callfire.api.service.xsd.ActionQuery;
import com.callfire.api.service.xsd.SendText;
import com.callfire.api.service.xsd.TextQueryResult;

/**
 * Example implementation of using CallFire's APIs to send a simple text
 * broadcast to multiple contacts.
 * 
 * @param <CallFirePropertiesInfo>
 * 
 */
public class SimpleTextBroadcast {
	//private static final Logger LOGGER = Logger.getLogger(SimpleTextBroadcast.class.getName());
	private static final Logger LOG = Logger.getLogger(SimpleTextBroadcast.class.getName());

	private final int maxWaitMs;

	private final CallFireApi callFireApi;
	private final TextServicePortType textService;

	String userName = "";
	String password = "";
	String messageByText = "";
	String textmessage = "";
	String fromNumber = "";
	String smsURL = "";
	
	

	public SimpleTextBroadcast(CallFirePropertiesInfo callFirePropertiesInfo) {
		maxWaitMs = 50000;
		userName = callFirePropertiesInfo.getUserName();
		password = callFirePropertiesInfo.getPassword();
		callFireApi = new CallFireApi(userName, password, ServiceState.PRODUCTION);
		textService = callFireApi.getTextServicePort();
	}

	/**
	 * @param txtMsg
	 * @param toNumbers
	 * @param fromNumber
	 * @throws ServiceFault
	 */
	public long sendTextUsingTextService(String type, String securityCode, String toNumber,
			CallFirePropertiesInfo callFirePropertiesInfo) throws ServiceFault,Exception {
		messageByText = callFirePropertiesInfo.getMessageSendModeText();
		fromNumber = null;
		SendText sendText = new SendText();
		//textmessage = callFirePropertiesInfo.getTextMessage() + " " + securityCode;
		textmessage = callFirePropertiesInfo.getTextMessage();
		LOG.info("Text Message : "+textmessage);
		ToNumber toNumberElem = new ToNumber();
		toNumberElem.setValue(toNumber);
		sendText.getToNumber().add(toNumberElem);

		TextBroadcastConfig config = new TextBroadcastConfig();
		config.setMessage(textmessage);
		if (fromNumber != null) {
			config.setFromNumber(fromNumber);
		}
		sendText.setTextBroadcastConfig(config);

		return textService.sendText(sendText);
	}
	
	/**
	 * @param broadcastId
	 * @param toNumbers
	 * @return true if Result.SENT is received for each specified toNumber.
	 */
	public boolean pollForResponse(long broadcastId, String toNumbers) {

		ActionQuery textQuery = new ActionQuery();
		textQuery.setBroadcastId(broadcastId);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			LOG.info("polling for text status:	 InterruptedException     ");
		}

			try {
				TextQueryResult result = textService.queryTexts(textQuery);
				List<Text> textList = result.getText();
				if (textList.isEmpty()) {
					LOG.info("polling for text status:	 call query did not return a text  ");
					throw new RuntimeException("call query did not return a text");
				}

				for (Text text : textList) {
					Result finalResult = text.getFinalResult();
					if (finalResult != null) {
						LOG.info("Text Message STATUS : "+finalResult);
						if (finalResult.equals(Result.SENT)) {
							LOG.info("polling for text status:    SMS Sent      ");
								return true;
						}
					}
				}
			} catch (ServiceFault e) {
				LOG.info("polling for text status:	 exception querying for texts     ");
				throw new RuntimeException("exception querying for texts", e);
			}
		return false;
	}

/*	*//**
	 * @param broadcastId
	 * @param toNumbers
	 * @return true if Result.SENT is received for each specified toNumber.
	 *//*
	public boolean pollForResponse(long broadcastId, List<Object> toNumbers) {
		LOG.info("polling for text status");
		final int sleepInterval = 3000;
		int totalWait = 0;

		ActionQuery textQuery = new ActionQuery();
		textQuery.setBroadcastId(broadcastId);

		while (totalWait < maxWaitMs) {
			try {
				Thread.sleep(sleepInterval);
				totalWait += sleepInterval;
			} catch (InterruptedException e) {
				LOG.log(Level.FINE, "interrupted", e);
			}

			try {
				TextQueryResult result = textService.queryTexts(textQuery);
				List<Text> textList = result.getText();
				if (textList.isEmpty()) {
					throw new RuntimeException("call query did not return a text");
				}

				for (Text text : textList) {
					Result finalResult = text.getFinalResult();
					if (finalResult != null) {
						if (finalResult.equals(Result.SENT)) {
							String toNumber = text.getToNumber().getValue();
							toNumbers.remove(toNumber);
							LOG.info("found toNumber: " + toNumber);
							if (toNumbers.isEmpty()) {
								return true;
							}
						}
					}
				}
			} catch (ServiceFault e) {
				throw new RuntimeException("exception querying for texts", e);
			}
		}
		return false;
	}*/

	/**
	 * Exercises sending a simple text broadcast to multiple contacts.
	 * 
	 * @throws Exception
	 */
	public void run() {
		try {
			String textMessage = "this is just a test...";
			System.out.println(System.getProperty("java.home"));
			long broadcastId = sendTextUsingTextService(textMessage, "+15104693350", null, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String[] args) { try { new
	 * SimpleTextBroadcastSample(callFirePropertiesInfo).run(); } catch
	 * (Exception e) { LOG.log(Level.SEVERE, "exception running " +
	 * SimpleTextBroadcastSample.class.getName(), e); } }
	 */

	public long sendPostSMS(String toNumber, String message, CallFirePropertiesInfo callFirePropertiesInfo)
			throws Exception {
		fromNumber = null;
		SendText sendText = new SendText();

		// textmessage = message;

		ToNumber toNumberElem = new ToNumber();
		toNumberElem.setValue(toNumber);
		sendText.getToNumber().add(toNumberElem);

		TextBroadcastConfig config = new TextBroadcastConfig();
		config.setMessage(message);
		if (fromNumber != null) {
			config.setFromNumber(fromNumber);
		}
		sendText.setTextBroadcastConfig(config);

		return textService.sendText(sendText);
	}
}
