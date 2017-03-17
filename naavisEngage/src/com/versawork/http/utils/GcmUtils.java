package com.versawork.http.utils;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@Component
public class GcmUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(GcmUtils.class);

	public String sendGcmMessage(String senderId, String registrationId, String message) {

		String response = null;
		// Date date =new Date();
		// String responseKey = registrationId+requestType;
		String collapseKey = "gcm";
		/*
		 * //String userMessage = requestType
		 * +"*"+passCode+"*"+date+"*"+responseKey; CommonRequest commonRequest
		 * =new CommonRequest(); commonRequest.setOperationCode(requestType); //
		 * commonRequest.setRegistrationId(registrationId);
		 * commonRequest.setResponseKey(responseKey);
		 * if(requestType==DeviceOperation.DEVICE_SEND_SMS) {
		 * commonRequest.setSms(passCode); } else
		 * if(requestType==DeviceOperation.DEVICE_LOCK){
		 * commonRequest.setPassword(passCode); } else
		 * if(requestType==DeviceOperation.DEVICE_ALARM){
		 * commonRequest.setAlarmTime(passCode); }
		 * commonRequest.setDataObject(dataObject);
		 */
		// Gson gson =new Gson();
		// String userMessage = gson.toJson(dataObject);
		// registrationId =
		// "APA91bGai8kWcyLIU2xpat-TLBvkrdGkfbpn-pztYgh_GL63n-9P6u16b1NJ4hAWP9s3C3hWvNPkmhdO3iJ_yquOfvnyzxC2QFK1vh30e4D5bvNrBDP7wSo4aFQa3Nxjm59wfFk6PXK0vLkwfwt0VawnkUiyhaYUFQ";
		Sender sender = new Sender(senderId);
		Message gcmMessage = new Message.Builder().collapseKey(collapseKey).timeToLive(3600).delayWhileIdle(false)
				.addData("message", message).build();
		LOGGER.debug("Device GCM :" + registrationId + ":Send message :" + message);
		try {
			Result result = sender.send(gcmMessage, registrationId, 3);
			// null if network issue
			LOGGER.debug("Response from GCM (Send GCM message id): " + result.getMessageId());

			LOGGER.debug("Response from GCM : Object" + result.toString());

		} catch (IOException e) {
			LOGGER.debug("IOException");
			e.printStackTrace();
		}
		return response;
	}

	/*
	 * public static String createJsonMessage(String to, String messageId,
	 * Map<String, String> payload, String collapseKey, Long timeToLive, Boolean
	 * delayWhileIdle) { Map<String, Object> message = new HashMap<String,
	 * Object>(); message.put("to", to); if (collapseKey != null) {
	 * message.put("collapse_key", collapseKey); } if (timeToLive != null) {
	 * message.put("time_to_live", timeToLive); } if (delayWhileIdle != null &&
	 * delayWhileIdle) { message.put("delay_while_idle", true); }
	 * message.put("message_id", messageId); message.put("data", payload);
	 * return JSONValue.toJSONString(message); }
	 */
	// APA91bG6SHkzbQ8VbAzBzhqeYMQsQDtJ8Q2VwoQ3qq7tQzf5BentbIOqSKtkWcDqC7jXQKWFF5KqKwCgLJXukl2XheRMblSaRDltVIQNcQdK07tBW1iQ2e5YmZP1ebjptPQjd1V5r1CmshbF650Q36Fa82SNkyeTZNYdffU0Rq0Y9aPAP2NFkCM
	public static void main(String[] args) {
		Sender sender = new Sender("AIzaSyCdrPS0YtvVaV6_BxRm3tkgpRpzvcR3VGM");
		LOGGER.debug("Sender Information :" + sender);

		Message message = new Message.Builder().collapseKey("gcm").timeToLive(3600).delayWhileIdle(false)
				.addData("message", "Apppointment confirmed").build();
		try {
			LOGGER.debug("Message that has been sent : " + message);
			Result result = sender
					.send(message,
							"APA91bG6SHkzbQ8VbAzBzhqeYMQsQDtJ8Q2VwoQ3qq7tQzf5BentbIOqSKtkWcDqC7jXQKWFF5KqKwCgLJXukl2XheRMblSaRDltVIQNcQdK07tBW1iQ2e5YmZP1ebjptPQjd1V5r1CmshbF650Q36Fa82SNkyeTZNYdffU0Rq0Y9aPAP2NFkCM",
							2);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
