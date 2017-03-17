package com.versawork.http.cacheClientsInfo;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import com.versawork.http.utils.SendMail;

public class SendCacheMail implements Runnable {

	private SendMail sendMail;

	public SendMail getSendMail() {
		return sendMail;
	}

	public void setSendMail(SendMail sendMail) {
		this.sendMail = sendMail;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	private MessageSource messageSource;

	private static Logger LOGGER = LoggerFactory.getLogger(SendCacheMail.class);

	private Boolean duringInit;


	public SendCacheMail(Boolean duringInit, SendMail sendMail, MessageSource messageSource) {
		this.duringInit = duringInit;
		this.sendMail = sendMail;
		this.messageSource = messageSource;
	}

	@Override
	public void run() {
		String message;
		if (duringInit) {
			message = "initialization. Cahing has been turned off for the application. ";
		} else {
			message = "fetching data from cache. ";
		}
		try {
			sendMail.sendMail("Dear Admin,", "\n\nRedis Cache Server is experiencing problems while " + message
					+ "\nIssue awaiting resolution.\n\n",
					messageSource.getMessage("cache.down.mail.address", null, Locale.getDefault()),
					"Engage Cache Server Down", "\n(www.navishealth.com)");
		} catch (Exception exp) {
			exp.printStackTrace();
			LOGGER.error("Exception occoured while sending the mail during cache downtime: ", exp);
		}
	}
}