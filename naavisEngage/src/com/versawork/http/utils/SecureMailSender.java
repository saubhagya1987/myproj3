package com.versawork.http.utils;

import java.util.HashMap;
import java.util.Locale;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.versawork.http.exception.BusinessException;

@Resource(name = "secureMailSender")
@Component
public class SecureMailSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecureMailSender.class);

	// from, to, subject and body

	@Autowired
	private MessageSource messageSource;

	@SuppressWarnings("unused")
	private MailSender mailSender;
	private SimpleMailMessage simpleMailMessage;

	private static Client client;

	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void secureTransmit(String to, String body, String subject, HashMap<String, byte[]> attachmentsMap,
			Locale locale) throws BusinessException {

		// SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);

		String provider = "versaworks";// args [0];
		// final String username = "naavis.engage@gmail.com";//args [1];
		final String password = "deedheeraj";// args [2];
		// String to = "newuser@direct.healthvault.com";//args [4];
		// String subject = "Medical Transmission";//args [5];

		try {

			if (client == null) {
				if (provider.equalsIgnoreCase("versaworks")) {
					client = new SecureMailClient(simpleMailMessage.getFrom().toString(), password, "test.p12",
							"ireslab", "ireslab");

				} else if (provider.equalsIgnoreCase("sendgrid")) {
					client = new SendGridClient(simpleMailMessage.getFrom().toString(), password,
							"classpath:HV_Ringful_Test.p12", "HV Ringful Test", "ringful");

				} else {
					LOGGER.debug("We only support GMail and SendGrid as senders for now. To support your own sender, please consider contributing to this project!");
				}
			}
			LOGGER.debug("This is in secureMailSender : "
					+ messageSource.getMessage("transmit.from.direct.adress", null, locale));

			client.sendMessage(messageSource.getMessage("transmit.from.direct.adress", null, locale), to, subject,
					body, attachmentsMap);

			LOGGER.debug("Success! Go check http://direct.healthvault-stage.com/ now");

		} catch (Exception exp) {

			exp.printStackTrace();
			LOGGER.error("Exception occoured on Secure Mail Sender Class (transmitEHR) : ", exp.getMessage());
			throw new BusinessException("secure.mail.transmit.failed");
		}
	}
}
