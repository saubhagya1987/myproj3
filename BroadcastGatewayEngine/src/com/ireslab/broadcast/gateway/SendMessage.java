package com.ireslab.broadcast.gateway;

/*import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.List;

 import javax.xml.bind.DatatypeConverter;


 import org.apache.commons.configuration.ConfigurationException;
 import org.apache.commons.configuration.PropertiesConfiguration;
 import org.apache.http.HttpResponse;
 import org.apache.http.NameValuePair;
 import org.apache.http.client.HttpClient;
 import org.apache.http.client.entity.UrlEncodedFormEntity;
 import org.apache.http.client.methods.HttpPost;
 import org.apache.http.impl.client.DefaultHttpClient;
 import org.apache.http.message.BasicNameValuePair;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

 *//**
 * To be utilized to send messages to users via SMS or IVR gateway. Currently
 * configured to CALLFIRE gateway utilizing post requests for call and text
 * services via method sendPost. main() method can be utilized to test the
 * operations.
 * 
 * @author Sohaib
 *
 */
public class SendMessage {

}
/*
 * 
 * private final static String GATEWAY_API_USERNAME; private final static String
 * GATEWAY_API_PASSWORD; private final static String TEXT_MESSAGE; private final
 * static String IVR_MESSAGE; private final static String TEXT_MESSAGE_URL;
 * private final static String IVR_URL; private final static String
 * MESSAGE_BY_TEXT = "TEXT"; //private final static String MESSAGE_BY_IVR =
 * "IVR"; private final static String MESSAGE_SEND_MODE_TEXT; private final
 * static String MESSAGE_SEND_MODE_IVR; private final static String
 * IVR_FROM_NUMBER;
 * 
 * private static PropertiesConfiguration propertiesConfig;
 * 
 * private static final Logger LOGGER = LoggerFactory
 * .getLogger(SendMessage.class);
 * 
 * static { LOGGER.info("Setting IVR properties."); propertiesConfig = null; try
 * { propertiesConfig = new PropertiesConfiguration("gatewayConfig/gateway");
 * LOGGER.info("Got IVR properties."); } catch (ConfigurationException e) {
 * e.printStackTrace(); } LOGGER.info("tking props..."); GATEWAY_API_USERNAME =
 * propertiesConfig.getString("user"); GATEWAY_API_PASSWORD =
 * propertiesConfig.getString("password");
 * TEXT_MESSAGE=propertiesConfig.getString("textMessageForSecurityCode");
 * TEXT_MESSAGE_URL=propertiesConfig.getString("smsURL");
 * IVR_MESSAGE=propertiesConfig.getString("ivrMessageForSecurityCode");
 * IVR_URL=propertiesConfig.getString("ivrURL");
 * IVR_FROM_NUMBER=propertiesConfig.getString("fromNumber");
 * MESSAGE_SEND_MODE_TEXT=propertiesConfig.getString("messageSendModeText");
 * MESSAGE_SEND_MODE_IVR=propertiesConfig.getString("messageSendModeIVR"); }
 * 
 * public static void main(String[] args) throws Exception { SendMessage
 * sendMessage = new SendMessage(); String toNumber = "15104693350"; String
 * message = "1 2 3 4 5 6";
 * sendMessage.sendPost(MESSAGE_BY_TEXT,message,toNumber);
 * sendMessage.sendPost(MESSAGE_BY_IVR,message,toNumber); }
 * 
 * public void sendPost(String type,String message,String toNumber) throws
 * Exception {
 * 
 * HttpClient client = new DefaultHttpClient(); HttpPost post;
 * 
 * String userAuth = GATEWAY_API_USERNAME+":"+GATEWAY_API_PASSWORD; String
 * encoding = DatatypeConverter.printBase64Binary(userAuth.getBytes("UTF-8"));
 * List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
 * 
 * if(type.equalsIgnoreCase(MESSAGE_BY_TEXT)) { urlParameters.add(new
 * BasicNameValuePair("To", toNumber)); post = new HttpPost(TEXT_MESSAGE_URL);
 * urlParameters.add(new BasicNameValuePair("Message",
 * TEXT_MESSAGE+" "+message)); urlParameters.add(new BasicNameValuePair("Type",
 * MESSAGE_SEND_MODE_TEXT)); }else { post = new HttpPost(IVR_URL);
 * urlParameters.add(new BasicNameValuePair("Type", MESSAGE_SEND_MODE_IVR));
 * urlParameters.add(new BasicNameValuePair("To", toNumber));
 * urlParameters.add(new BasicNameValuePair("From", IVR_FROM_NUMBER)); String
 * ivrMsgToSend = IVR_MESSAGE.replaceAll("SAMPLE_CODE",
 * message.replaceAll(".(?!$)", "$0 . ")); urlParameters.add(new
 * BasicNameValuePair("DialplanXml", ivrMsgToSend)); }
 * 
 * post.setHeader("Authorization", "Basic " + encoding); post.setEntity(new
 * UrlEncodedFormEntity(urlParameters));
 * 
 * HttpResponse response = client.execute(post);
 * LOGGER.info("\nSending 'POST' request to by : " + type + " to : "+toNumber);
 * LOGGER.debug("Post parameters : " + post.getEntity());
 * LOGGER.debug("Response Code : " + response.getStatusLine().getStatusCode());
 * 
 * BufferedReader rd = new BufferedReader( new
 * InputStreamReader(response.getEntity().getContent()));
 * 
 * StringBuffer result = new StringBuffer(); String line = ""; while ((line =
 * rd.readLine()) != null) { result.append(line); }
 * LOGGER.info(result.toString()); }
 * 
 * public void sendPostSMS(String toNumber,String message) throws Exception {
 * 
 * HttpClient client = new DefaultHttpClient(); HttpPost post;
 * 
 * String userAuth = GATEWAY_API_USERNAME+":"+GATEWAY_API_PASSWORD; String
 * encoding = DatatypeConverter.printBase64Binary(userAuth.getBytes("UTF-8"));
 * List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
 * 
 * urlParameters.add(new BasicNameValuePair("To", toNumber)); post = new
 * HttpPost(TEXT_MESSAGE_URL); urlParameters.add(new
 * BasicNameValuePair("Message", TEXT_MESSAGE+" "+message));
 * urlParameters.add(new BasicNameValuePair("Type", MESSAGE_SEND_MODE_TEXT));
 * 
 * 
 * post.setHeader("Authorization", "Basic " + encoding); post.setEntity(new
 * UrlEncodedFormEntity(urlParameters));
 * 
 * HttpResponse response = client.execute(post);
 * LOGGER.info("\nSending 'POST' request to by SMS  to : "+toNumber);
 * LOGGER.debug("Post parameters : " + post.getEntity());
 * LOGGER.debug("Response Code : " + response.getStatusLine().getStatusCode());
 * 
 * BufferedReader rd = new BufferedReader( new
 * InputStreamReader(response.getEntity().getContent()));
 * 
 * StringBuffer result = new StringBuffer(); String line = ""; while ((line =
 * rd.readLine()) != null) { result.append(line); }
 * LOGGER.info(result.toString()); } }
 */
