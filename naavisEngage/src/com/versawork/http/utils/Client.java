package com.versawork.http.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.security.Key;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.activation.DataHandler;
import javax.mail.Header;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import net.suberic.crypto.EncryptionKeyManager;
import net.suberic.crypto.EncryptionManager;
import net.suberic.crypto.EncryptionUtils;
import net.suberic.crypto.bouncycastle.BouncySMIMEEncryptionKey;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xbill.DNS.CERTRecord;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

import com.versawork.http.exception.BusinessException;

/*
 Encryption

 S/MIME encrypts entities by combining symmetric and asymmetric encryption. S/MIME can encrypt an entity for multiple 
 recipients without creating a separate copy of the entity for each recipient. It does so using facilities provided by CMS.
 The concepts behind the algorithm are explained below.

 Encryption Algorithm
 Get all public keys for each recipient
 Generate a secret symmetric encryption key
 Use the symmetric key to encrypt the MIME entity into encrypted bytes
 Encrypt the secret symmetric key with each public key (separately) to create an encrypted key collection
 Combine the encrypted bytes, the encrypted key collection into an envelope

 Decryption Algorithm
 Scan encrypted key collection to locate an encrypted key that was encrypted with the recipient’s public key
 Decrypt the encrypted key using the associated recipient private key to get the secret symmetric key
 Get the encrypted bytes from the envelope.
 Decrypt encrypted bytes using the secret symmetric key to obtain original MIME entity.

 */
public abstract class Client {

	private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

	protected String p12KeyStore;
	protected String priKeyName;
	protected String priKeyPass;

	/* private static final String LDAP_SRV_PREFIX = "_ldap._tcp."; */
	private static final String EMAIL_TOKEN = "EMAILADDRESS=";
	private static final String TOKENIZER = ",";
	private static final String CERT_TYPE = "X.509";

	private static final String CONTENT_TYPE_XML = "text/xml";
	private static final String CONTENT_TYPE_PDF = "application/octet-stream";

	private static X509Certificate dcdtCertificate;
	private static X509Certificate tttCertificate;

	public void sendMessage(String from, String to, String subject, String body, HashMap<String, byte[]> attachmentsMap)
			throws Exception {
		try {
			byte[] pubKeyCer = null;
			if (to.endsWith("@transport-testing.nist.gov")) {
				LOGGER.debug("Looking up trust store for transport tool");
				pubKeyCer = lookupTrustStore("ttt");
			} else {
				LOGGER.debug("Looking up dns for certificate");
				pubKeyCer = lookupCertificate(to);
			}

			if (pubKeyCer == null || pubKeyCer.length == 0) {
				throw new BusinessException("Cannot find public key certificate for " + to);
			}

			// creates multi-part
			Multipart multipart = new MimeMultipart("mixed");
			Session session = createSession();

			for (String key : attachmentsMap.keySet()) {

				MimeBodyPart messageBodyPart = new MimeBodyPart(); // creates
																	// message
																	// part
				LOGGER.debug("Attachment file : " + key);

				String contentType = null;

				if (key.contains("xml")) {
					contentType = CONTENT_TYPE_XML;
				} else if (key.contains("pdf")) {
					contentType = CONTENT_TYPE_PDF;
				}

				BufferedDataSource bds = new BufferedDataSource(attachmentsMap.get(key), key, contentType);
				messageBodyPart.setDataHandler(new DataHandler(bds));
				messageBodyPart.setFileName(bds.getName());

				multipart.addBodyPart(messageBodyPart);
			}

			MimeMessage message = new MimeMessage((Session) null);
			message.setHeader("Return-Path", from);
			/** Return Path added as required */
			message.setFrom(new InternetAddress(from));
			message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject(subject);
			message.setText(body);
			message.setContent(multipart);

			message = signMsg(session, message);
			message = encryptMsg(session, message, pubKeyCer);
			message.saveChanges();

			@SuppressWarnings("rawtypes")
			Enumeration headers = message.getAllHeaders();
			while (headers.hasMoreElements()) {
				Header h = (Header) headers.nextElement();
				LOGGER.debug(h.getName() + ": " + h.getValue());
			}

			transport(session, message);

		} catch (Exception exp) {

			exp.printStackTrace();
			LOGGER.error("Exception occoured on Client Class (transmitEHR) : ", exp);
			throw new BusinessException(exp.getMessage());
		}
	}

	public void sendMessage(String from, String to, String subject, Multipart multipart) throws Exception {

		try {
			byte[] pubKeyCer = null;
			if (to.endsWith("@transport-testing.nist.gov")) {
				LOGGER.debug("Looking up trust store for transport tool");
				pubKeyCer = lookupTrustStore("ttt");
			} else {
				LOGGER.debug("Looking up dns for certificate");
				pubKeyCer = lookupCertificate(to);
			}
			if (pubKeyCer == null || pubKeyCer.length == 0) {
				throw new Exception("Cannot find public key certificate for " + to);
			}

			Session session = createSession();

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setContent(multipart);

			msg = signMsg(session, msg);
			msg = encryptMsg(session, msg, pubKeyCer);
			msg.saveChanges();

			transport(session, msg);

		} catch (Exception exp) {
			exp.printStackTrace();
			LOGGER.error("Exception occoured on Client Class (transmitEHR) : ", exp);
			throw new BusinessException(exp.getMessage());
		}
	}

	/**
	 * Performs the following operations in specified order : - Address Bound
	 * certificate search in DNS - Domain Bound certificate search in DNS
	 * 
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	private byte[] certLookupInDNS(String domain) throws Exception {

		CERTRecord cx = null;
		Record[] records = null;
		String directAddress = null;

		if (domain.contains("@")) {
			directAddress = domain.replaceAll("@", ".");
		}

		LOGGER.debug("Normal address-bound certificate search in DNS with direct address : " + directAddress + ". . .");
		records = new Lookup(directAddress, Type.CERT).run();

		if (records == null || records.length < 1) {

			directAddress = domain.substring(domain.indexOf("@") + 1, domain.length());
			LOGGER.debug("Normal domain-bound certificate search in DNS with direct address : " + directAddress
					+ ". . .");
			records = new Lookup(directAddress, Type.CERT).run();
		}

		boolean isValid = false;
		if (records != null) {
			for (int i = 0; i < records.length; i++) {
				cx = (CERTRecord) records[i];
				isValid = validateCertificate(cx.getCert(), domain);
				if (isValid) {
					break;
				}
			}
			return isValid ? cx.getCert() : null;
		}
		return null;
	}

	private static boolean validateCertificate(byte[] pubKeyCert, String directAddress) {

		String expectedEmail = "";

		try {
			CertificateFactory cf = CertificateFactory.getInstance(CERT_TYPE);
			X509Certificate cert = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(pubKeyCert));

			LOGGER.debug("Checking Certificate Validity");
			/** Checking Certificate Validity */
			cert.checkValidity(new Date());

			String subjectDN = cert.getSubjectDN().toString();
			LOGGER.debug("Searching expected email address in SubjectDN : " + subjectDN);

			if (subjectDN.contains(EMAIL_TOKEN)) {
				String temp = subjectDN.substring(subjectDN.indexOf(EMAIL_TOKEN) + EMAIL_TOKEN.length()).trim();
				expectedEmail = temp.contains(TOKENIZER) ? temp.substring(0, temp.indexOf(TOKENIZER)) : temp;

				if (!expectedEmail.equalsIgnoreCase(directAddress)) {
					LOGGER.debug("Expected Email : '" + expectedEmail + "' doesnt match the Direct Address : "
							+ directAddress);
					return false;
				}
			}

			LOGGER.debug("Discovered Certificate: \n" + cert.toString());
			return true;

		} catch (Exception exp) {
			LOGGER.error(ExceptionUtils.getStackTrace(exp));
			return false;
		}
	}

	private static LdapPublicCertUtilImpl ldapPublicCertUtilImpl;

	/**
	 * Performs the following operations in specified order : - Address Bound
	 * certificate search in LDAP - Domain Bound certificate search in LDAP
	 * 
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	private byte[] certLookupInLDAP(String domain) throws Exception {

		Collection<X509Certificate> x509Certificates = null;
		// String directAddress = null;

		if (ldapPublicCertUtilImpl == null) {
			ldapPublicCertUtilImpl = new LdapPublicCertUtilImpl();
		}

		LOGGER.debug("Normal address-bound certificate search in LDAP with direct address : " + domain + ". . .");
		x509Certificates = ldapPublicCertUtilImpl.ldapSearch(domain);

		if (x509Certificates == null || x509Certificates.isEmpty()) {

			if (domain.contains("@")) {
				domain = domain.substring(domain.indexOf("@") + 1, domain.length());
			}
			LOGGER.debug("Normal domain-bound certificate search in LDAP with direct address : " + domain + ". . .");
			x509Certificates = ldapPublicCertUtilImpl.ldapSearch(domain);
			// directAddress = domain;

		}

		if (x509Certificates != null && !x509Certificates.isEmpty()) {
			for (X509Certificate certificate : x509Certificates) {
				try {
					certificate.checkValidity(new Date());
					LOGGER.debug("Certificate lookedup in LDAP : " + certificate);
					return certificate.getEncoded();
				} catch (Exception e) {
					// eat the exception
				}

			}
		}
		return null;
	}

	public byte[] lookupCertificate(String email) {
		try {

			byte[] certificate = null;

			LOGGER.debug("Performing certificate search in DNS . . . ");
			certificate = certLookupInDNS(email);

			if (certificate == null) {
				LOGGER.debug("Performing certificate search in LDAP . . . ");
				certificate = certLookupInLDAP(email);
			}
			return certificate;

		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
	}

	public byte[] lookupTrustStore(String org) throws Exception {

		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		byte[] certByteArray = null;

		if (org.equalsIgnoreCase("ttt")) {

			if (dcdtCertificate == null) {
				FileInputStream transportCertStream = new FileInputStream(System.getProperty("jboss.server.home.dir")
						+ "\\conf\\" + "transport.der");
				dcdtCertificate = (X509Certificate) cf.generateCertificate(transportCertStream);

				certByteArray = dcdtCertificate.getEncoded();
				transportCertStream.close();
			}
			certByteArray = dcdtCertificate.getEncoded();
			return certByteArray;

		} else if (org.equalsIgnoreCase("dcdt")) {

			if (tttCertificate == null) {
				FileInputStream demoDirectTestCertStream = new FileInputStream(
						System.getProperty("jboss.server.home.dir") + "\\conf\\" + "demo.direct-test.com_ca.der");
				tttCertificate = (X509Certificate) cf.generateCertificate(demoDirectTestCertStream);

				certByteArray = tttCertificate.getEncoded();
				demoDirectTestCertStream.close();
			}
			certByteArray = tttCertificate.getEncoded();
			return tttCertificate.getEncoded();
		}
		return null;
	}

	protected MimeMessage encryptMsg(Session session, MimeMessage msg, byte[] pubKeyCer) throws Exception {
		try {
			EncryptionUtils encUtils = EncryptionManager.getEncryptionUtils(EncryptionManager.SMIME);

			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(pubKeyCer));
			/**
			 * This HOWTO describes one way of implementing public key
			 * encryption in Java. It is generally not advisable to use a public
			 * key encryption algorithm such as RSA to directly encrypt files,
			 * since (i) public key encryption is slow, and (ii) it will only
			 * let you encrypt small things (...well, I haven't managed to get
			 * it to encrypt big things ;) The alternative, and commonly used
			 * approach, is to use a shared key algorithm to encrypt/decrypt the
			 * files, and then use a public key algorithm to encrypt/decrypt the
			 * (randomly generated) key used by the shared key algorithm. This
			 * has the benefit of fast file encryption/decryption whilst still
			 * requiring a non-shared private key to get access to the key
			 * needed to decrypt the files.
			 */
			// wrap certificate in BouncySMIMEEncryptionKey
			BouncySMIMEEncryptionKey smimekey = new BouncySMIMEEncryptionKey();
			smimekey.setCertificate(cert);

			return encUtils.encryptMessage(session, msg, smimekey);
		} catch (Exception exp) {
			exp.printStackTrace();
			LOGGER.error("Exception occoured on Client Class (transmitEHR) : ", exp);
			throw new BusinessException(exp.getMessage());
		}
	}

	private static Key signingPrivateKey;
	private static EncryptionUtils encUtils;

	/**
	 * @param session
	 * @param mimeMessage
	 * @return
	 * @throws Exception
	 */
	protected MimeMessage signMsg(Session session, MimeMessage mimeMessage) throws Exception {

		// EncryptionUtils encUtils = null;

		try {
			if (signingPrivateKey == null || encUtils == null) {

				// Getting of the S/MIME EncryptionUtilities.
				encUtils = EncryptionManager.getEncryptionUtils(EncryptionManager.SMIME);

				// Loading of the S/MIME keystore from the file (stored as
				// resource).
				char[] keystorePass = priKeyPass.toCharArray();
				EncryptionKeyManager encKeyManager = encUtils.createKeyManager();

				FileInputStream signCertStream = new FileInputStream(System.getProperty("jboss.server.home.dir")
						+ "\\conf\\" + p12KeyStore);

				LOGGER.debug("Fetching keystore to sign from :" + System.getProperty("jboss.server.home.dir")
						+ "\\conf\\" + p12KeyStore);
				encKeyManager.loadPrivateKeystore(signCertStream, keystorePass);

				// Getting of the S/MIME private key for signing.
				signingPrivateKey = encKeyManager.getPrivateKey(priKeyName, keystorePass);
			}
			// LOGGER.debug("Siging algo: "+signingPrivateKey.getAlgorithm());
			// LOGGER.debug("Session is: "+session.getProperty("mail.smtp.port"));
			// LOGGER.debug("Message subject is: "+mimeMessage.getSubject());
			// Signing the message.
			return encUtils.signMessage(session, mimeMessage, signingPrivateKey);

		} catch (Exception exp) {
			exp.printStackTrace();
			LOGGER.error("Exception occoured on Client Class (transmitEHR) : ", exp);
			throw new BusinessException(exp.getMessage());
		}

	}

	protected abstract Session createSession();

	protected abstract void transport(Session session, MimeMessage msg) throws Exception;

}