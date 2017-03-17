package com.versawork.http.utils;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

 
public class SendMail {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendMail.class);

	private MailSender mailSender;
	private SimpleMailMessage simpleMailMessage;

	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String dear, String content, String toAddr, String subject, String extraInfo) {

		SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
		message.setTo(toAddr);
		if (subject != null) {
			message.setSubject(subject);
		}
		
		if(dear != null && dear.trim() != null && dear.trim().length() > 0){
			dear = dear + " ";
		}
		
		message.setText(String.format(simpleMailMessage.getText(), dear, content, extraInfo));

		mailSender.send(message);

		LOGGER.debug("Message send to emailId : " + toAddr + " is : " + message);
	}
}