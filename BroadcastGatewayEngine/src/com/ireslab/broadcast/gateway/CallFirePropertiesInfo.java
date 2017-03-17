package com.ireslab.broadcast.gateway;

import java.io.Serializable;

/**
 * @author Dheeraj
 *
 */

public class CallFirePropertiesInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String userName;
	protected String password;
	protected String textMessageForSecurityCode;
	protected String ivrMessageForSecurityCode;
	protected String fromNumber;
	protected String textMessage;
	protected String messageSendModeText;
	protected String messageSendModeIVR;
	protected String smsURL;
	protected String ivrURL;

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTextMessageForSecurityCode() {
		return textMessageForSecurityCode;
	}

	public void setTextMessageForSecurityCode(String textMessageForSecurityCode) {
		this.textMessageForSecurityCode = textMessageForSecurityCode;
	}

	public String getIvrMessageForSecurityCode() {
		return ivrMessageForSecurityCode;
	}

	public void setIvrMessageForSecurityCode(String ivrMessageForSecurityCode) {
		this.ivrMessageForSecurityCode = ivrMessageForSecurityCode;
	}

	public String getFromNumber() {
		return fromNumber;
	}

	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}

	public String getMessageSendModeText() {
		return messageSendModeText;
	}

	public void setMessageSendModeText(String messageSendModeText) {
		this.messageSendModeText = messageSendModeText;
	}

	public String getMessageSendModeIVR() {
		return messageSendModeIVR;
	}

	public void setMessageSendModeIVR(String messageSendModeIVR) {
		this.messageSendModeIVR = messageSendModeIVR;
	}

	public String getSmsURL() {
		return smsURL;
	}

	public void setSmsURL(String smsURL) {
		this.smsURL = smsURL;
	}

	public String getIvrURL() {
		return ivrURL;
	}

	public void setIvrURL(String ivrURL) {
		this.ivrURL = ivrURL;
	}
}
