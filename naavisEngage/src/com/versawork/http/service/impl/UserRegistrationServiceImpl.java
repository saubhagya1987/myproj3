package com.versawork.http.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ireslab.broadcast.gateway.CallFirePropertiesInfo;
import com.ireslab.broadcast.gateway.SimpleTextBroadcast;
import com.ireslab.broadcast.gateway.SimpleVoiceBroadcast;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.AccountServiceDAO;
import com.versawork.http.dao.LinkedAccountDao;
import com.versawork.http.dao.PatientVerificationServiceDAO;
import com.versawork.http.dao.UserRegistrationDAO;
import com.versawork.http.dataobject.AccountInfo;
import com.versawork.http.dataobject.EngageFeatures;
import com.versawork.http.dataobject.EngageModule;
import com.versawork.http.dataobject.FeedListInfo;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.EngageClientToFeature;
import com.versawork.http.model.FeedInfo;
import com.versawork.http.model.PatientRosetta;
import com.versawork.http.model.PatientVerification;
import com.versawork.http.service.LoginService;
import com.versawork.http.service.UserRegistrationService;
import com.versawork.http.utils.DateUtils;
import com.versawork.http.utils.EmailUtils;
import com.versawork.http.utils.MaskUtils;
import com.versawork.http.utils.NsResponseUtils;
import com.versawork.http.utils.SendMail;

/**
 * Service layer implementation class which implements the methods defined in
 * the service layer interface to handle user registration related operations.
 * 
 * @author Sohaib
 * 
 */
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

	@Autowired
	private PatientVerificationServiceDAO patientVerificationDAO;

	@Autowired
	private AccountServiceDAO accountServiceDAO;

	@Autowired
	private LoginService loginService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private HospitalNoticeServiceImpl hospitalNoticeServiceImpl;

	@Autowired
	private NotificationServiceImpl notificationServiceImpl;

	@Autowired
	private MedicationManagementServiceImpl medicationManagementServiceImpl;

	@Autowired
	private BloodPressureServiceImpl bloodPressureServiceImpl;

	@Autowired
	private UserRegistrationDAO userRegistrationDAO;

	@Autowired
	private LinkedAccountDao linkedAccountDao;

	@Autowired
	private SendMail sendMail;
	public static long broadCatdIdThread = 0 ;
	private static final String SUCCESS_RESPONSE_CODE = VersaWorkConstant.SUCCESS_RESPONSE_CODE;
	private static final String FAILURE_RESPONSE_CODE = VersaWorkConstant.FAILURE_RESPONSE_CODE;
	private static final String INVALID_ENDPOINT_RESPONSE_CODE = VersaWorkConstant.INVALID_ENDPOINT_RESPONSE_CODE;
	private static final String USER_VERFICATION_SUCCESS = "rsp.data.dscrptn.user.verified";
	private static final String SEND_SECURITY_CODE_SUCCESS_MAIL = "security.code.send.success.mail";
	private static final String SEND_SECURITY_CODE_SUCCESS_SMS = "security.code.send.success.sms";
	private static final String SEND_SECURITY_CODE_SUCCESS_IVR = "security.code.send.success.ivr";
	private static final String RESEND_SECURITY_CODE_SUCCESS_MAIL = "security.code.resend.success.mail";
	
	private static final String RESEND_SECURITY_MODIFIED_CODE_SUCCESS_MAIL = "security.code.modified.resend.success.mail";
	
	private static final String MAIL_SECURITY_CODE_SUBJECT="mail.security.code.subject";
	private static final String MAIL_PROFILE_UPDATE_SUBJECT="mail.profile.update.subject";
	
	private static final String EMAIL_MATCHED_HEADER = "email.matched.header";
	private static final String NO_MATCH_FOUND_HEADER = "no.match.found.header";
	private static final String PHONE_NUMBER_MATCHED_HEADER = "phone.number.matched.header";
	private static final String TRY_AGAIN_HEADER = "try.again.header";
	
	
	
	
	private static final String RESEND_MODIFIED_SECURITY_CODE_SUCCESS_SMS="security.code.modified.resend.success.sms";
	private static final String RESEND_SECURITY_CODE_SUCCESS_SMS = "security.code.resend.success.sms";
	private static final String RESEND_SECURITY_CODE_SUCCESS_IVR = "security.code.resend.success.ivr";

	private static final String SECURITY_CODE_LENGTH = "security.code.length";
	private static final String SECURITY_CODE_EXPIRY_TIME = "security.code.expiry.time";
	private static final String SECURITY_CODE_EXPIRED = "security.code.expired";
	private static final String PIN_CODE_EXPIRED="pin.code.expired";
	private static final String SECURITY_CODE_VAL_SUCCESS = "security.code.validation.success";
	private static final String SECURITY_CODE_VAL_FAIL = "security.code.validation.failure";
	private static final String ENDPOINT_USER_ID_LENGTH = "endpoint.user.id.length";

	private static final String LOGIN_FAILED_TEMP_LOCK_ATTEMPTS = "login.failed.temp.lock.attempts";
	private static final String LOGIN_FAILED_PERM_LOCK_ATTEMPTS = "login.failed.perm.lock.attempts";
	private static final String LOGIN_FAIL_TEMP_LOCK = "login.fail.temp.lock";
	private static final String LOGIN_FAIL_PERM_LOCK = "login.fail.perm.lock";
	private static final String LOGIN_FAIL = "login.fail";
	private static final String LOGIN_TEMP_LOCK_TIME = "login.temp.lock.time";
	private static final String ACCOUNT_TEMPORARILY_LOCKED = "account.temp.locked";
	private static final String ACCOUNT_PERMANENTLY_LOCKED = "account.perm.locked";

	private static final String CLIENT_DATABASE_ID = "client.database.id";
	private static final String CLIENT_ID = "client.id";
	private static final String SMS_MESSAGE_BODY = "sms.message.body";

	private static final String SECURITY_CODE_AUTH_MODE_TEXT = "TEXT";
	private static final String SECURITY_CODE_AUTH_MODE_IVR = "IVR";
	private static final String SECURITY_CODE_AUTH_MODE_MAIL = "MAIL";
	private static final String USER_VERFICATION_SUCCESS_MR = "rsp.data.dscrptn.user.verified.mr";
	private static final String EMAIL_NOT_AVAILABLE = "NA";
	private static final String INVALID_ENDPOINT_ID = "invalid.endpoint.id";
	private static final String SECURITY_CODE_MODE = "security.code.mode";
	private static final String USER_INVALID_ENTRY = "user.invalid.entry";
	private static final String SEND_SECURITY_CODE_FAILED_TRY_AGAIN = "send.security.code.fail.try.again";

	private static final String EMAIL_MATCHED = "email.matched";
	private static final String PHONE_MATCHED = "phone.matched";

	private static final String MAINTENANCE = "maintenance.flag";
	private static final String DOWNTIME_MAINTENANCE_MESSAGE = "downtime.maintenance.message";

	private static final String SCHEDULED_MAINTENANCE = "is.maintenance.scheduled";
	private static final String SCHEDULED_MAINTENANCE_MESSAGE = "scheduled.maintenance.message";
	private static final String RETRY_ATTEMPTS_NUMBER = "retry.attempts.number";
	
	private static final String NO_FEED_INFO = "no.feed.info";
	private static final String INVALID_FEED_ID="invalid.feed.id";
	private static final String TEXT_MSG="textMessage";
	private static final String TEXT_MSG_MAIL="textMessage.mail";
	private static final String REDIRECT_URL="redirecturl";
	private static final String LINKEDACCOUNT_REDIRECT_URL="linked.account.redirecturl";
	
	
	private static final String SECURITY_PIN_CODE_LENGTH = "security.pin.code.length";
	private static final String RESEND_FORGET_SECURITY_CODE_SUCCESS_MAIL="resend.forget.security.code.success.mail";
	private static final String TEXT_MSG_PIN="text.msg.pin";
	private static final String SUCCESS_MSG="rsp.data.dscrptn.success";
	
	public static final String HOSPITAL_SUBJECT = "hospital.subject";
	private static final String TEXT_MSG_UPDATION="text.msg.updation";
	private static final String TEXT_MSG_PIN_RESET="text.msg.pin.reset";
	

	/**
	 * Method validates the request and verifies the user by validating the MR
	 * Number and Phone Number against the patient data already present in the
	 * database. Good luck maintaining this!!!
	 * 
	 * @param nsRequest
	 * @return
	 */
	@Override
	public NsResponse verifyUser(NsRequest nsRequest, boolean fromLinkAccount) throws BusinessException,
			SystemException {
		nsRequest.setFromLinkedAccount(fromLinkAccount);
		PatientRosetta pRosetta = null;
		String responseMsg = null;
		NsResponse nsResponse = new NsResponse();
		Integer codeSendOptions = 1;
		String codeSendMode = SECURITY_CODE_AUTH_MODE_TEXT;
		Integer responseCode = Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale()));
		AccountInfo accountInfo = nsRequest.getAccountInfo();
		validateAccountInfo(accountInfo);
		nsRequest.setEmailMatched(false);
		nsRequest.setPhoneNumberMatched(false);
		// Lets play rosetta-rosetta
		if (fromLinkAccount) {
			pRosetta = userRegistrationDAO.getPatientByMRNumber(accountInfo.getUnitNumber(), nsRequest.getAccountInfo()
					.getClientDatabaseId());
		} else if (!fromLinkAccount) {
			pRosetta = userRegistrationDAO.getPatientByMRNumber(accountInfo.getUnitNumber(),
					Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		}
		if (pRosetta == null) {
			LOGGER.debug("There is no entry in rosetta, Throwing business exception");
			throw new BusinessException("user.invalid.entry");
		}

		Integer accountId = pRosetta.getAccountId();

		PatientVerification patientVerifData;

		// Lets play rosetta-rosetta
		Account account = accountServiceDAO.getAccountByIdSpcl(accountId);

		if (account != null) {

			if (DateUtils.getFormatDate(account.getBirthDate(), "MM/dd/yyyy").equalsIgnoreCase(
					nsRequest.getAccountInfo().getDateOfBirth())) {
				if (account.getMobilePhoneNumber() != null
						&& account.getMobilePhoneNumber().trim()
								.equalsIgnoreCase(nsRequest.getAccountInfo().getMobilePhoneNumber())) {
					codeSendMode = SECURITY_CODE_AUTH_MODE_TEXT;
					responseMsg = messageSource.getMessage(USER_VERFICATION_SUCCESS, null, nsRequest.getLocale());
					if (account.getEmail() != null
							&& account.getEmail().trim().equalsIgnoreCase(nsRequest.getAccountInfo().getEmail())) {
						codeSendOptions = 1;
					} else {
						codeSendOptions = 2;
					}
					
					//ONLY FOR SEND SECURITY CODE
					nsRequest.setPhoneNumberMatched(true);
					if (account.getEmail() != null
							&& account.getEmail().trim().equalsIgnoreCase(nsRequest.getAccountInfo().getEmail())) {
						nsRequest.setEmailMatched(true);
					}
				} else {
					if (account.getEmail() != null
							&& account.getEmail().trim().equalsIgnoreCase(nsRequest.getAccountInfo().getEmail())) {
						codeSendMode = SECURITY_CODE_AUTH_MODE_MAIL;
						responseMsg = messageSource
								.getMessage(USER_VERFICATION_SUCCESS_MR, null, nsRequest.getLocale());
						codeSendOptions = 3;
						
						//ONLY FOR SEND SECURITY CODE
						nsRequest.setEmailMatched(true);
						if (account.getMobilePhoneNumber() != null
								&& account.getMobilePhoneNumber().trim()
										.equalsIgnoreCase(nsRequest.getAccountInfo().getMobilePhoneNumber())) {
							nsRequest.setPhoneNumberMatched(true);
						}
					} else {
						LOGGER.debug("EMAIL DOESNT MATCH");
						responseMsg = messageSource.getMessage(USER_INVALID_ENTRY, null, nsRequest.getLocale());
						responseCode = Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
								nsRequest.getLocale()));
						
						
					}
				}
				accountInfo.setFirstName(account.getFirstName());
				accountInfo.setLastName(account.getLastName());

				if (account.getEmail() != null) {
					accountInfo.setEmail(account.getEmail());
				} else {
					accountInfo.setEmail(EMAIL_NOT_AVAILABLE);
				}
				if (account.getMobilePhoneNumber() != null) {
					accountInfo.setMobilePhoneNumber(account.getMobilePhoneNumber());
				}

				accountInfo.setAccountId(0);
			} else {
				LOGGER.debug("DOB from account doesnt matched");
				responseMsg = messageSource.getMessage(USER_INVALID_ENTRY, null, nsRequest.getLocale());
				responseCode = Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
						nsRequest.getLocale()));
			}
			
		} else {			
			// Lets play rosetta-rosetta
			boolean phoneNumberMatch = false;
			String reqPhoneNumber = nsRequest.getAccountInfo().getMobilePhoneNumber();
			
			try {
				patientVerifData = patientVerificationDAO.getPatientVerification(accountId);
				
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.debug("No entry for account in verification table.");
				throw new BusinessException("user.invalid.entry");
			}

			if (patientVerifData != null) {
				
				if(patientVerifData.getMobilePhoneNumber()!=null && reqPhoneNumber.trim().equalsIgnoreCase(patientVerifData.getMobilePhoneNumber().trim())){
					phoneNumberMatch =true;
				}else if(patientVerifData.getHomePhoneNumber()!=null && reqPhoneNumber.trim().equalsIgnoreCase(patientVerifData.getHomePhoneNumber().trim())){
					phoneNumberMatch = true;
				}else if(patientVerifData.getOtherPhoneNumber()!=null && reqPhoneNumber.trim().equalsIgnoreCase(patientVerifData.getOtherPhoneNumber().trim())){
					phoneNumberMatch = true;
				}
				
				if (DateUtils.getFormatDate(patientVerifData.getBirthDate(), "MM/dd/yyyy").equalsIgnoreCase(
						nsRequest.getAccountInfo().getDateOfBirth())) {
					if (!phoneNumberMatch && patientVerifData.getEmailAddress() == null) {
						throw new BusinessException("no.mail.phone.exists");
					}
					if (phoneNumberMatch) {
						codeSendMode = SECURITY_CODE_AUTH_MODE_TEXT;
						responseMsg = messageSource.getMessage(USER_VERFICATION_SUCCESS, null, nsRequest.getLocale());
						if (patientVerifData.getEmailAddress() != null
								&& patientVerifData.getEmailAddress().trim()
										.equalsIgnoreCase(nsRequest.getAccountInfo().getEmail())) {
							codeSendOptions = 1;
						} else {
							codeSendOptions = 2;
						}
						
						
						//ONLY FOR SEND SECURITY CODE
						nsRequest.setPhoneNumberMatched(true);
						if (patientVerifData.getEmailAddress() != null
								&& patientVerifData.getEmailAddress().trim()
										.equalsIgnoreCase(nsRequest.getAccountInfo().getEmail())) {
							nsRequest.setEmailMatched(true);
						}
						
					} else {
						if (patientVerifData.getEmailAddress() != null
								&& patientVerifData.getEmailAddress().trim()
										.equalsIgnoreCase(nsRequest.getAccountInfo().getEmail())) {
							codeSendMode = SECURITY_CODE_AUTH_MODE_MAIL;
							responseMsg = messageSource.getMessage(USER_VERFICATION_SUCCESS_MR, null,
									nsRequest.getLocale());
							codeSendOptions = 3;
							
							//ONLY FOR SEND SECURITY CODE
							nsRequest.setEmailMatched(true);
							if (phoneNumberMatch) {
								nsRequest.setPhoneNumberMatched(true);
							}
						} else {
							LOGGER.debug("Email Of user not matched");
							responseMsg = messageSource.getMessage(USER_INVALID_ENTRY, null, nsRequest.getLocale());
							responseCode = Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
									nsRequest.getLocale()));
						}
					}
					accountInfo.setFirstName(patientVerifData.getFirstName());
					accountInfo.setLastName(patientVerifData.getLastName());
				} else {
					LOGGER.debug("DOB from patient verification matched");
					responseMsg = messageSource.getMessage(USER_INVALID_ENTRY, null, nsRequest.getLocale());
					responseCode = Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
							nsRequest.getLocale()));
				}
				if (patientVerifData.getEmailAddress() != null) {
					accountInfo.setEmail(patientVerifData.getEmailAddress());
				} else {
					accountInfo.setEmail(EMAIL_NOT_AVAILABLE);
				}
				if (phoneNumberMatch) {
					accountInfo.setMobilePhoneNumber(reqPhoneNumber);
				}
			} else {
				LOGGER.debug("No entry found in Patient verification table !!!!!!");
				responseMsg = messageSource.getMessage(USER_INVALID_ENTRY, null, nsRequest.getLocale());
				responseCode = Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
						nsRequest.getLocale()));
			}
			accountInfo.setAccountId(0);
		}

		if (responseCode == Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
				nsRequest.getLocale()))) {
			accountInfo.setEmail("");
			accountInfo.setMobilePhoneNumber("");
			nsResponse.setAccountInfo(accountInfo);
		} else {
			if (accountInfo.getEmail() == "" || accountInfo.getEmail() == null) {
				accountInfo.setEmail(nsRequest.getAccountInfo().getEmail());
			}
			nsResponse.setAccountInfo(accountInfo);
		}
		ResponseData responseData = new ResponseData();
		responseData.setDescription(responseMsg);
		responseData.setResult(responseCode);

		if (responseCode == Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale()))) {
			accountInfo.setAccountId(accountId);
			nsRequest.setCodeSendMode(codeSendMode);
			nsRequest.setAccountInfo(accountInfo);
			nsResponse = sendSecurityCode(nsRequest);
			//nsResponse =sendForgotPin(nsRequest);
			responseData.setResult(nsResponse.getResponseData().getResult());
			responseData.setDescription(nsResponse.getResponseData().getDescription());
			responseData.setHeader(nsResponse.getResponseData().getHeader());
			nsResponse.setResponseDescription(nsResponse.getResendMessageDescription());
			/*if (responseData.getResult() == Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale()))) {
				if (codeSendOptions == 1 || codeSendOptions == 2) {
					responseData.setDescription(messageSource.getMessage(PHONE_MATCHED, null, nsRequest.getLocale()));
					// "The phone number you entered matched with the phone number in our system. We've sent you an SMS with a verification code");
				} else {
					responseData
							.setDescription(messageSource.getMessage(EMAIL_MATCHED, null, nsRequest.getLocale()));
					// "The phone number you entered does not match the phone number in our system. An email with the security code has been sent to the email address that is already registered in our system");
				}
			}*/
		}
		nsResponse.setCodeSendOptions(codeSendOptions);
		nsResponse.setResponseData(responseData);
		nsResponse.getAccountInfo().setAccountId(accountId);
		return nsResponse;
	}
	
	/*@Transactional(rollbackFor = Exception.class)
	public NsResponse sendSecurityCodeVerifyUser(NsRequest nsRequest) throws BusinessException, SystemException {
		String message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_SMS, null, nsRequest.getLocale());
		String header=null;
		AccountInfo accountInfo = new AccountInfo();
		accountInfo = nsRequest.getAccountInfo();
		validateAccountInfo(accountInfo);
		// Generate Security Code
		String securityCode = MaskUtils.generateSecurityCode(Integer.parseInt(messageSource.getMessage(
				SECURITY_CODE_LENGTH, null, nsRequest.getLocale())));
		if ((messageSource.getMessage(SECURITY_CODE_MODE, null, Locale.getDefault()).equalsIgnoreCase("LOCAL"))
				|| (nsRequest.getAccountInfo().getUnitNumber().equalsIgnoreCase("TEST001"))) {
			securityCode = "123456";
		}
		final String securityCodeForThread = securityCode;
		String secureSecurityCode = MaskUtils.getDigest(securityCode.getBytes());

		// Lets play rositta-rositta
		PatientVerification patientVerification = patientVerificationDAO.getPatientVerification(nsRequest
				.getAccountInfo().getAccountId());
		patientVerification.setSecurityCode(secureSecurityCode);
		patientVerification.setSecurityCodeTimestamp(DateUtils.getCurrentUTCDate());
		LOGGER.info("Current UTC Time" +DateUtils.getCurrentUTCDate());
		patientVerificationDAO.updatePatientVerification(patientVerification);
		LOGGER.debug("Security code saved successfully");
		// Lets play rositta-rositta
		AccountInfo tempAccountInfo = getMailForMessage(nsRequest.getAccountInfo().getAccountId(),nsRequest.getAccountInfo().getMobilePhoneNumber());
		if (nsRequest.getCodeSendMode().equalsIgnoreCase("TEXTA")
				|| nsRequest.getCodeSendMode().equalsIgnoreCase("MAILA")) {
			tempAccountInfo.setMobilePhoneNumber(nsRequest.getAccountInfo().getMobilePhoneNumber());
			if (nsRequest.getCodeSendMode().equalsIgnoreCase("TEXTA")) {
				nsRequest.setCodeSendMode(SECURITY_CODE_AUTH_MODE_TEXT);
			} else {
				nsRequest.setCodeSendMode(SECURITY_CODE_AUTH_MODE_MAIL);
			}
		}
		if (!messageSource.getMessage(SECURITY_CODE_MODE, null, Locale.getDefault()).equalsIgnoreCase("LOCAL")) {
			// SendMessage sendMessageForAuthInfo = new SendMessage();

			CallFirePropertiesInfo callFirePropertiesInfo = new CallFirePropertiesInfo();
			callFirePropertiesInfo.setUserName(messageSource.getMessage("user", null, Locale.getDefault()));
			callFirePropertiesInfo.setPassword(messageSource.getMessage("password", null, Locale.getDefault()));
			callFirePropertiesInfo.setMessageSendModeText(messageSource.getMessage("messageSendModeText", null,
					Locale.getDefault()));
			callFirePropertiesInfo.setFromNumber(messageSource.getMessage("fromNumber", null, Locale.getDefault()));
			callFirePropertiesInfo.setSmsURL(messageSource.getMessage("smsURL", null, Locale.getDefault()));
			callFirePropertiesInfo.setMessageSendModeIVR(messageSource.getMessage("messageSendModeIVR", null,
					Locale.getDefault()));
			callFirePropertiesInfo.setTextMessage(messageSource.getMessage("textMessage", null, Locale.getDefault()));
			callFirePropertiesInfo.setIvrMessageForSecurityCode(messageSource.getMessage("ivrMessageForSecurityCode",
					null, Locale.getDefault()));

			SimpleTextBroadcast sendMessageForAuthInfo = new SimpleTextBroadcast(callFirePropertiesInfo);
			SimpleVoiceBroadcast sendvoiceForAuthInfo = new SimpleVoiceBroadcast(callFirePropertiesInfo);

			if (nsRequest.getCodeSendMode().equalsIgnoreCase(SECURITY_CODE_AUTH_MODE_TEXT)) {
					long broadCatdId=0;
					boolean mesgSent =false;
					Integer retryAttemptsNumber = Integer.parseInt((messageSource.getMessage(RETRY_ATTEMPTS_NUMBER, null,Locale.getDefault())));
					try{
						new Thread(new Runnable() {						
							@Override
							public void run() {
								for(int i=0; i<retryAttemptsNumber;i++){
									System.out.println("Going to RUN");
									try{
										broadCatdIdThread = sendMessageForAuthInfo.sendTextUsingTextService(SECURITY_CODE_AUTH_MODE_TEXT, securityCodeForThread,tempAccountInfo.getMobilePhoneNumber(), callFirePropertiesInfo);
										System.out.println("Succes");
										break;
									}catch(Exception e){
										e.printStackTrace();
									}
								}
							}
						});
						broadCatdId = broadCatdIdThread;
						mesgSent = sendMessageForAuthInfo.pollForResponse(broadCatdId, tempAccountInfo.getMobilePhoneNumber());
					}catch(Exception e){
						if(nsRequest.getEmailMatched()){
							try{
							LOGGER.error("Error while sending SMS   :"+ExceptionUtils.getFullStackTrace(e));
							
							new Thread(new Runnable() {						
								@Override
								public void run() {
									for(int i=0; i<retryAttemptsNumber;i++){
										System.out.println("Going to RUN");
										try{
											sendMail.sendMail(tempAccountInfo.getFirstName() + " " + tempAccountInfo.getLastName(),
													"\n\nThank you for registering an account. Your six digit verification code is : "
															+ securityCodeForThread + "\n\n", tempAccountInfo.getEmail(), messageSource.getMessage(MAIL_SECURITY_CODE_SUBJECT, null, nsRequest.getLocale()),
													"\n" + messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, nsRequest.getLocale()));
											System.out.println("Succes");
											break;
										}catch(Exception e){
											e.printStackTrace();
										}
									}
								}
							}).start();
							
							message =messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_MAIL, null, nsRequest.getLocale());
							header = messageSource.getMessage(EMAIL_MATCHED_HEADER, null, nsRequest.getLocale());
							}catch(Exception exception){
								LOGGER.error("Exception occoured while sending the mail during creating Account: ", ExceptionUtils.getFullStackTrace(exception));
								message =messageSource.getMessage(SEND_SECURITY_CODE_FAILED_TRY_AGAIN, null, nsRequest.getLocale());
								header = messageSource.getMessage(TRY_AGAIN_HEADER, null, nsRequest.getLocale());
								return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(),header, message,
										Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null, nsRequest.getLocale())));
							}
						}else{
							message =messageSource.getMessage(USER_INVALID_ENTRY, null, nsRequest.getLocale());
							header = messageSource.getMessage(NO_MATCH_FOUND_HEADER, null, nsRequest.getLocale());
							return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(),header, message,
									Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null, nsRequest.getLocale())));
						}
					}
					if(mesgSent){
						LOGGER.info("SMS Sent");
						header = messageSource.getMessage(PHONE_NUMBER_MATCHED_HEADER, null, nsRequest.getLocale());
						message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_SMS, null, nsRequest.getLocale());
					}else{
						LOGGER.info("SMS ERROR. MAIL Sent");
						if(nsRequest.getEmailMatched()){
							try{
								new Thread(new Runnable() {						
									@Override
									public void run() {
										for(int i=0; i<retryAttemptsNumber;i++){
											System.out.println("Going to RUN");
											try{
												sendMail.sendMail(tempAccountInfo.getFirstName() + " " + tempAccountInfo.getLastName(),
														"\n\nThank you for registering an account. Your six digit verification code is : "
																+ securityCodeForThread + "\n\n", tempAccountInfo.getEmail(), messageSource.getMessage(MAIL_SECURITY_CODE_SUBJECT, null, nsRequest.getLocale()),
														"\n" + messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, nsRequest.getLocale()));
												System.out.println("Succes");
												break;
											}catch(Exception e){
												e.printStackTrace();
											}
										}
									}
								}).start();
							
							message =messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_MAIL, null, nsRequest.getLocale());
							header = messageSource.getMessage(EMAIL_MATCHED_HEADER, null, nsRequest.getLocale());
							} catch (Exception exp) {
								LOGGER.error("Exception occoured while sending the mail during creating Account: ", ExceptionUtils.getFullStackTrace(exp));
								message =messageSource.getMessage(SEND_SECURITY_CODE_FAILED_TRY_AGAIN, null, nsRequest.getLocale());
								header = messageSource.getMessage(TRY_AGAIN_HEADER, null, nsRequest.getLocale());
								return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(),header, message,
										Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null, nsRequest.getLocale())));
								
								// throw new BusinessException(exp.getMessage());
							}
						}else{
							message =messageSource.getMessage(USER_INVALID_ENTRY, null, nsRequest.getLocale());
							header = messageSource.getMessage(NO_MATCH_FOUND_HEADER, null, nsRequest.getLocale());
							return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(),header, message,
									Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null, nsRequest.getLocale())));
						}
					}
					//sendMessageForAuthInfo.sendTextUsingTextService(SECURITY_CODE_AUTH_MODE_TEXT, securityCode,tempAccountInfo.getMobilePhoneNumber(), callFirePropertiesInfo);
					
			} else if (nsRequest.getCodeSendMode().equalsIgnoreCase(SECURITY_CODE_AUTH_MODE_IVR)) {
				try {
					Integer retryAttemptsNumber = Integer.parseInt((messageSource.getMessage(RETRY_ATTEMPTS_NUMBER, null,Locale.getDefault())));
					new Thread(new Runnable() {						
						@Override
						public void run() {
							for(int i=0; i<retryAttemptsNumber;i++){
								System.out.println("Going to RUN");
								try{
									sendvoiceForAuthInfo.sendVoiceBroadcastUsingCallService(SECURITY_CODE_AUTH_MODE_IVR, securityCodeForThread, tempAccountInfo.getMobilePhoneNumber(), callFirePropertiesInfo);
									System.out.println("Succes");
									break;
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}).start();	
					
					message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_IVR, null, nsRequest.getLocale());
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("ivr.delivery.error");
				}
			} else {
				if(nsRequest.getEmailMatched()){
					try {
						LOGGER.debug("Name : "+tempAccountInfo.getFirstName() + " " + tempAccountInfo.getLastName());
						LOGGER.debug("Message :: "+"\n\nThank you for registering an account. Your six digit verification code is : " + securityCode + "\n\n");
						LOGGER.debug("Mail Id ::: "+tempAccountInfo.getEmail());
						Integer retryAttemptsNumber = Integer.parseInt((messageSource.getMessage(RETRY_ATTEMPTS_NUMBER, null,Locale.getDefault())));
						new Thread(new Runnable() {						
							@Override
							public void run() {
								for(int i=0; i<retryAttemptsNumber;i++){
									System.out.println("Going to RUN");
									try{
										sendMail.sendMail("",
												"\n\nThank you for registering an account. Your six digit security code is : "
														+ securityCodeForThread + "\n\n", tempAccountInfo.getEmail(), messageSource.getMessage(MAIL_SECURITY_CODE_SUBJECT, null, nsRequest.getLocale()),
												"\n" + messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, nsRequest.getLocale()));
										System.out.println("Succes");
										break;
									}catch(Exception e){
										e.printStackTrace();
									}
								}
							}
						}).start();
						
						header = messageSource.getMessage(EMAIL_MATCHED_HEADER, null, nsRequest.getLocale());
						message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_MAIL, null, nsRequest.getLocale());
					} catch (Exception exp) {
						LOGGER.error("Exception occoured while sending the mail during creating Account: ", ExceptionUtils.getFullStackTrace(exp));
						message =messageSource.getMessage(SEND_SECURITY_CODE_FAILED_TRY_AGAIN, null, nsRequest.getLocale());
						header = messageSource.getMessage(TRY_AGAIN_HEADER, null, nsRequest.getLocale());
						return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(),header, message,
								Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null, nsRequest.getLocale())));
						
						// throw new BusinessException(exp.getMessage());
					}
				}
				
			}
		}

		LOGGER.debug("Security code sent to user");

		return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(),header, message,
				Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null, nsRequest.getLocale())));
	}*/

	public AccountInfo getMailForMessage(Integer accountId,String phoneNumber) throws BusinessException, SystemException {
		AccountInfo accountInfo = new AccountInfo();
		PatientVerification patientVerifData = new PatientVerification();

		// Lets play rosetta-rosetta
		Account account = accountServiceDAO.getAccountByIdSpcl(accountId);
		if (account != null) {
			accountInfo.setFirstName(account.getFirstName());
			accountInfo.setLastName(account.getLastName());
			if (account.getMobilePhoneNumber() != null) {
				accountInfo.setMobilePhoneNumber(account.getMobilePhoneNumber());
			}
			if (account.getEmail() != null) {
				accountInfo.setEmail(account.getEmail());
			}
			accountInfo.setAccountId(0);
		} else {
			/*
			 * patientVerifData = patientVerificationDAO.getPatientVerification(
			 * unitNumber, Integer.parseInt(messageSource.getMessage(
			 * CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
			 */
			// Lets play rosetta-rosetta
			patientVerifData = patientVerificationDAO.getPatientVerification(accountId);
			if (patientVerifData.getEmailAddress() != null) {
				accountInfo.setEmail(patientVerifData.getEmailAddress());
			}
			accountInfo.setFirstName(patientVerifData.getFirstName());
			accountInfo.setLastName(patientVerifData.getLastName());
			if(patientVerifData.getMobilePhoneNumber()!=null && phoneNumber.trim().equalsIgnoreCase(patientVerifData.getMobilePhoneNumber().trim())){
				accountInfo.setMobilePhoneNumber(patientVerifData.getMobilePhoneNumber());
			}else if(patientVerifData.getHomePhoneNumber()!=null && phoneNumber.trim().equalsIgnoreCase(patientVerifData.getHomePhoneNumber().trim())){
				accountInfo.setMobilePhoneNumber(patientVerifData.getHomePhoneNumber());
			}else if(patientVerifData.getOtherPhoneNumber()!=null && phoneNumber.trim().equalsIgnoreCase(patientVerifData.getOtherPhoneNumber().trim())){
				accountInfo.setMobilePhoneNumber(patientVerifData.getOtherPhoneNumber());
			}
		}
		accountInfo.setAccountId(0);
		return accountInfo;
	}
	
/*	public static void main(String args[]){
		String secureSecurityCode = MaskUtils.getDigest("257593".getBytes());
		System.out.println(secureSecurityCode);
		if(secureSecurityCode.equalsIgnoreCase("F5E1378DD90B719F8CA24D83B96FB126ED91AC264B07CD4411FE4A4FD070E7EE"))
		System.out.println("yesss");
	}*/
	
	@Transactional(rollbackFor = Exception.class)
	public NsResponse sendSecurityCode(NsRequest nsRequest) throws BusinessException, SystemException {
		String message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_SMS, null, nsRequest.getLocale());
		String resendMessage = messageSource.getMessage(RESEND_MODIFIED_SECURITY_CODE_SUCCESS_SMS, null, nsRequest.getLocale());
		AccountInfo accountInfo = new AccountInfo();
		accountInfo = nsRequest.getAccountInfo();
		validateAccountInfo(accountInfo);
		
		// Generate Security Code
		//String 
		String securityCode = MaskUtils.generateSecurityCode(Integer.parseInt(messageSource.getMessage(
				SECURITY_CODE_LENGTH, null, nsRequest.getLocale())));
		if ((messageSource.getMessage(SECURITY_CODE_MODE, null, Locale.getDefault()).equalsIgnoreCase("LOCAL"))
				|| (nsRequest.getAccountInfo().getUnitNumber().equalsIgnoreCase("TEST001"))) {
			securityCode = "123456";
			   // for testing purpose
			if (nsRequest.getCodeSendMode().equalsIgnoreCase(SECURITY_CODE_AUTH_MODE_TEXT)) {
				message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_SMS, null, nsRequest.getLocale());
				resendMessage=messageSource.getMessage(RESEND_MODIFIED_SECURITY_CODE_SUCCESS_SMS, null, nsRequest.getLocale());
			}else{
			
			String email=nsRequest.getAccountInfo().getEmail();
			String name=email.substring(1,email.indexOf("@"));
			//String masked = email.replaceAll(name, "**");
			String masked=EmailUtils.maskedEmail(email, name);
			/*String masked;
			if(name.length()!=0)
			{
			 masked = email.replaceFirst(name, "**");
			}
			else 
			{
				char firstChar = email.charAt(0);
				String temp = firstChar+"**";
				email = email.substring(1);
				masked = temp+email;
				
			}*/
			//String masked = email.replaceAll("(?<=.).(?=[^@]*?.@)", "*");
			//String masked=email.replaceAll("\\b(\\w)[^@]+@\\S+(\\.[^\\s.]+)", "$1***@****$2");
			
			
			message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_MAIL, new String[] { String.valueOf(masked) }, nsRequest.getLocale());
			resendMessage=messageSource.getMessage(RESEND_SECURITY_MODIFIED_CODE_SUCCESS_MAIL, new String[] { String.valueOf(masked) }, nsRequest.getLocale());
			}
		}
		
		final String SecurityCodeForThread = securityCode;
		String secureSecurityCode = MaskUtils.getDigest(securityCode.getBytes());

		// Lets play rositta-rositta
		PatientVerification patientVerification = patientVerificationDAO.getPatientVerification(nsRequest
				.getAccountInfo().getAccountId());
		patientVerification.setSecurityCode(secureSecurityCode);
		patientVerification.setSecurityCodeTimestamp(DateUtils.getCurrentUTCDate());
		LOGGER.info("Current UTC Time" +DateUtils.getCurrentUTCDate());

		patientVerificationDAO.updatePatientVerification(patientVerification);
		LOGGER.debug("Security code saved successfully");
		// Lets play rositta-rositta
		AccountInfo tempAccountInfo = getMailForMessage(nsRequest.getAccountInfo().getAccountId(),nsRequest.getAccountInfo().getMobilePhoneNumber());
		String url=messageSource.getMessage(REDIRECT_URL, null, null)+SecurityCodeForThread;
		String linkedAccountUrl=messageSource.getMessage(LINKEDACCOUNT_REDIRECT_URL, null, null)+SecurityCodeForThread;
		/*String encodedUrl =null;
		try {
			 encodedUrl = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
        
		if (!messageSource.getMessage(SECURITY_CODE_MODE, null, Locale.getDefault()).equalsIgnoreCase("LOCAL")) {
			// SendMessage sendMessageForAuthInfo = new SendMessage();

			CallFirePropertiesInfo callFirePropertiesInfo = new CallFirePropertiesInfo();
			callFirePropertiesInfo.setUserName(messageSource.getMessage("user", null, Locale.getDefault()));
			callFirePropertiesInfo.setPassword(messageSource.getMessage("password", null, Locale.getDefault()));
			callFirePropertiesInfo.setMessageSendModeText(messageSource.getMessage("messageSendModeText", null,
					Locale.getDefault()));
			callFirePropertiesInfo.setFromNumber(messageSource.getMessage("fromNumber", null, Locale.getDefault()));
			callFirePropertiesInfo.setSmsURL(messageSource.getMessage("smsURL", null, Locale.getDefault()));
			callFirePropertiesInfo.setMessageSendModeIVR(messageSource.getMessage("messageSendModeIVR", null,
					Locale.getDefault()));
			if(nsRequest.getFromLinkedAccount())
			{
				callFirePropertiesInfo.setTextMessage(messageSource.getMessage("textMessagesmslink", new String[] {SecurityCodeForThread,linkedAccountUrl}, Locale.getDefault()));
			}
			else
			{
				callFirePropertiesInfo.setTextMessage(messageSource.getMessage("textMessagesmslink", new String[] {SecurityCodeForThread,url}, Locale.getDefault()));
			}
			callFirePropertiesInfo.setIvrMessageForSecurityCode(messageSource.getMessage("ivrMessageForSecurityCode",
					null, Locale.getDefault()));

			SimpleTextBroadcast sendMessageForAuthInfo = new SimpleTextBroadcast(callFirePropertiesInfo);
			SimpleVoiceBroadcast sendvoiceForAuthInfo = new SimpleVoiceBroadcast(callFirePropertiesInfo);

			if (nsRequest.getCodeSendMode().equalsIgnoreCase(SECURITY_CODE_AUTH_MODE_TEXT)) {
				try {
					Integer retryAttemptsNumber = Integer.parseInt((messageSource.getMessage(RETRY_ATTEMPTS_NUMBER, null,Locale.getDefault())));
					new Thread(new Runnable() {						
						@Override
						public void run() {
							for(int i=0; i<retryAttemptsNumber;i++){
								System.out.println("Going to RUN");
								try{
									sendMessageForAuthInfo.sendTextUsingTextService(SECURITY_CODE_AUTH_MODE_TEXT, SecurityCodeForThread,tempAccountInfo.getMobilePhoneNumber(), callFirePropertiesInfo);
									System.out.println("Succes");
									break;
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}).start();
					//sendMessageForAuthInfo.sendTextUsingTextService(SECURITY_CODE_AUTH_MODE_TEXT, securityCode,tempAccountInfo.getMobilePhoneNumber(), callFirePropertiesInfo);
					
				} catch (Exception e) {
					LOGGER.error("ERROR WHILE SENDING SMS   :   "+ExceptionUtils.getFullStackTrace(e));
					/*e.printStackTrace();
					throw new BusinessException("sms.delivery.error");*/
				}
				message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_SMS, null, nsRequest.getLocale());
				resendMessage = messageSource.getMessage(RESEND_MODIFIED_SECURITY_CODE_SUCCESS_SMS, null, nsRequest.getLocale());
			} else if (nsRequest.getCodeSendMode().equalsIgnoreCase(SECURITY_CODE_AUTH_MODE_IVR)) {
				try {
					Integer retryAttemptsNumber = Integer.parseInt((messageSource.getMessage(RETRY_ATTEMPTS_NUMBER, null,Locale.getDefault())));
					new Thread(new Runnable() {						
						@Override
						public void run() {
							for(int i=0; i<retryAttemptsNumber;i++){
								System.out.println("Going to RUN");
								try{
									sendvoiceForAuthInfo.sendVoiceBroadcastUsingCallService(SECURITY_CODE_AUTH_MODE_IVR, SecurityCodeForThread, tempAccountInfo.getMobilePhoneNumber(), callFirePropertiesInfo);
									System.out.println("Succes");
									break;
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}).start();	
					message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_IVR, null, nsRequest.getLocale());
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("ivr.delivery.error");
				}
			} else {
				Integer retryAttemptsNumber = Integer.parseInt((messageSource.getMessage(RETRY_ATTEMPTS_NUMBER, null,Locale.getDefault())));
				try {
					new Thread(new Runnable() {						
						@Override
						public void run() {
							for(int i=0; i<retryAttemptsNumber;i++){
								System.out.println("Going to RUN");
								try{
									String name = tempAccountInfo.getFirstName() + " " + tempAccountInfo.getLastName();
									sendMail.sendMail("",
											messageSource.getMessage(TEXT_MSG_MAIL, new String[] {name, SecurityCodeForThread}, nsRequest.getLocale())
												 + "\n\n", tempAccountInfo.getEmail(), messageSource.getMessage(MAIL_SECURITY_CODE_SUBJECT, null, nsRequest.getLocale()),
											"\n" + messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, nsRequest.getLocale()));
									System.out.println("Succes");
									break;
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}).start();
					String email=nsRequest.getAccountInfo().getEmail();
					String name=email.substring(1,email.indexOf("@"));
					//String masked = email.replaceFirst(name, "**");
					String masked=EmailUtils.maskedEmail(email, name);
					
					//String masked = email.replaceAll("(?<=.).(?=[^@]*?.@)", "*");
					//String masked=email.replaceAll("\\b(\\w)[^@]+@\\S+(\\.[^\\s.]+)", "$1***@****$2");
					
					
					message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_MAIL, new String[] { String.valueOf(masked) }, nsRequest.getLocale());
					resendMessage = messageSource.getMessage(RESEND_SECURITY_MODIFIED_CODE_SUCCESS_MAIL, new String[] { String.valueOf(masked) }, nsRequest.getLocale());
				} catch (Exception exp) {
					exp.printStackTrace();
					LOGGER.error("Exception occoured while sending the mail during creating Account: ", exp);
					// throw new BusinessException(exp.getMessage());
				}
			}
		}
		
		nsRequest.getAccountInfo().setAccountId(0);
		LOGGER.debug("Security code sent to user");
		NsResponse nsResponse = NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), message,
				Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null, nsRequest.getLocale())));
		nsResponse.setResendMessageDescription(resendMessage);		

		return nsResponse;
	}

	/**
	 * 1. generate a security code 2. save security code with timestamp for a
	 * user to DB 3. send code to user via SMS/IVR
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	/*@Transactional(rollbackFor = Exception.class)
	public NsResponse sendSecurityCode(NsRequest nsRequest) throws BusinessException, SystemException {
		String message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_SMS, null, nsRequest.getLocale());
		String header = null;
		AccountInfo accountInfo = new AccountInfo();
		accountInfo = nsRequest.getAccountInfo();
		validateAccountInfo(accountInfo);

		// Generate Security Code
		String securityCode = MaskUtils.generateSecurityCode(Integer.parseInt(messageSource.getMessage(
				SECURITY_CODE_LENGTH, null, nsRequest.getLocale())));
		if ((messageSource.getMessage(SECURITY_CODE_MODE, null, Locale.getDefault()).equalsIgnoreCase("LOCAL"))
				|| (nsRequest.getAccountInfo().getUnitNumber().equalsIgnoreCase("TEST001"))) {
			securityCode = "123456";
		}
		String secureSecurityCode = MaskUtils.getDigest(securityCode.getBytes());

		// Lets play rositta-rositta
		PatientVerification patientVerification = patientVerificationDAO.getPatientVerification(nsRequest
				.getAccountInfo().getAccountId());
		patientVerification.setSecurityCode(secureSecurityCode);
		patientVerification.setSecurityCodeTimestamp(DateUtils.getCurrentUTCDate());
		LOGGER.info("Current UTC Time" +DateUtils.getCurrentUTCDate());
		patientVerificationDAO.updatePatientVerification(patientVerification);
		LOGGER.debug("Security code saved successfully");
		// Lets play rositta-rositta
		AccountInfo tempAccountInfo = getMailForMessage(nsRequest.getAccountInfo().getAccountId(),nsRequest.getAccountInfo().getMobilePhoneNumber());
		if (nsRequest.getCodeSendMode().equalsIgnoreCase("TEXTA")
				|| nsRequest.getCodeSendMode().equalsIgnoreCase("MAILA")) {
			tempAccountInfo.setMobilePhoneNumber(nsRequest.getAccountInfo().getMobilePhoneNumber());
			if (nsRequest.getCodeSendMode().equalsIgnoreCase("TEXTA")) {
				nsRequest.setCodeSendMode(SECURITY_CODE_AUTH_MODE_TEXT);
			} else {
				nsRequest.setCodeSendMode(SECURITY_CODE_AUTH_MODE_MAIL);
			}
		}
		if (!messageSource.getMessage(SECURITY_CODE_MODE, null, Locale.getDefault()).equalsIgnoreCase("LOCAL")) {
			// SendMessage sendMessageForAuthInfo = new SendMessage();

			CallFirePropertiesInfo callFirePropertiesInfo = new CallFirePropertiesInfo();
			callFirePropertiesInfo.setUserName(messageSource.getMessage("user", null, Locale.getDefault()));
			callFirePropertiesInfo.setPassword(messageSource.getMessage("password", null, Locale.getDefault()));
			callFirePropertiesInfo.setMessageSendModeText(messageSource.getMessage("messageSendModeText", null,
					Locale.getDefault()));
			callFirePropertiesInfo.setFromNumber(messageSource.getMessage("fromNumber", null, Locale.getDefault()));
			callFirePropertiesInfo.setSmsURL(messageSource.getMessage("smsURL", null, Locale.getDefault()));
			callFirePropertiesInfo.setMessageSendModeIVR(messageSource.getMessage("messageSendModeIVR", null,
					Locale.getDefault()));
			callFirePropertiesInfo.setTextMessage(messageSource.getMessage("textMessage", null, Locale.getDefault()));
			callFirePropertiesInfo.setIvrMessageForSecurityCode(messageSource.getMessage("ivrMessageForSecurityCode",
					null, Locale.getDefault()));

			SimpleTextBroadcast sendMessageForAuthInfo = new SimpleTextBroadcast(callFirePropertiesInfo);
			SimpleVoiceBroadcast sendvoiceForAuthInfo = new SimpleVoiceBroadcast(callFirePropertiesInfo);

			if (nsRequest.getCodeSendMode().equalsIgnoreCase(SECURITY_CODE_AUTH_MODE_TEXT)) {
				try {
					long broadCatdId=0;
					boolean mesgSent =false;
					try{
						broadCatdId = sendMessageForAuthInfo.sendTextUsingTextService(SECURITY_CODE_AUTH_MODE_TEXT, securityCode,tempAccountInfo.getMobilePhoneNumber(), callFirePropertiesInfo);
						
						mesgSent = sendMessageForAuthInfo.pollForResponse(broadCatdId, tempAccountInfo.getMobilePhoneNumber());
					}catch(Exception e){
						LOGGER.error("Error while sending SMS   :"+ExceptionUtils.getFullStackTrace(e));
						sendMail.sendMail(tempAccountInfo.getFirstName() + " " + tempAccountInfo.getLastName(),
								"\n\nThank you for registering an account. Your six digit verification code is : "
										+ securityCode + "\n\n", tempAccountInfo.getEmail(), null,
								"\n" + messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, nsRequest.getLocale()));
						LOGGER.error("Exception occoured while sending the mail during creating Account: ", ExceptionUtils.getFullStackTrace(e));
						message =messageSource.getMessage(SEND_SECURITY_CODE_FAILED_TRY_AGAIN, null, nsRequest.getLocale());
						header = messageSource.getMessage(TRY_AGAIN_HEADER, null, nsRequest.getLocale());
						return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(),header, message,
								Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null, nsRequest.getLocale())));
					}
					if(mesgSent){
						LOGGER.info("SMS Sent");
						message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_SMS, null, nsRequest.getLocale());
					}else{
						message =messageSource.getMessage(SEND_SECURITY_CODE_FAILED_TRY_AGAIN, null, nsRequest.getLocale());
						header = messageSource.getMessage(TRY_AGAIN_HEADER, null, nsRequest.getLocale());
						return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(),header, message,
								Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null, nsRequest.getLocale())));
					}
					//sendMessageForAuthInfo.sendTextUsingTextService(SECURITY_CODE_AUTH_MODE_TEXT, securityCode,tempAccountInfo.getMobilePhoneNumber(), callFirePropertiesInfo);
					
				} catch (Exception e) {
					LOGGER.error("Exception occoured while sending the mail during creating Account: ", ExceptionUtils.getFullStackTrace(e));
					message =messageSource.getMessage(SEND_SECURITY_CODE_FAILED_TRY_AGAIN, null, nsRequest.getLocale());
					header = messageSource.getMessage(TRY_AGAIN_HEADER, null, nsRequest.getLocale());
					return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(),header, message,
							Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null, nsRequest.getLocale())));
				}
			} else if (nsRequest.getCodeSendMode().equalsIgnoreCase(SECURITY_CODE_AUTH_MODE_IVR)) {
				try {
					sendvoiceForAuthInfo.sendVoiceBroadcastUsingCallService(SECURITY_CODE_AUTH_MODE_IVR, securityCode, tempAccountInfo.getMobilePhoneNumber(), callFirePropertiesInfo);
					message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_IVR, null, nsRequest.getLocale());
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("ivr.delivery.error");
				}
			} else {
				try {
					LOGGER.debug("Name : "+tempAccountInfo.getFirstName() + " " + tempAccountInfo.getLastName());
					LOGGER.debug("Message :: "+"\n\nThank you for registering an account. Your six digit verification code is : " + securityCode + "\n\n");
					LOGGER.debug("Mail Id ::: "+tempAccountInfo.getEmail());
					sendMail.sendMail("",
							"\n\nThank you for registering an account. Your six digit security code is : "
									+ securityCode + "\n\n", tempAccountInfo.getEmail(), "Engage Security Code",
							"\n" + messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, nsRequest.getLocale()));
					message = messageSource.getMessage(RESEND_SECURITY_CODE_SUCCESS_MAIL, null, nsRequest.getLocale());
				} catch (Exception exp) {
					LOGGER.error("Exception occoured while sending the mail during creating Account: ", ExceptionUtils.getFullStackTrace(exp));
					message =messageSource.getMessage(SEND_SECURITY_CODE_FAILED_TRY_AGAIN, null, nsRequest.getLocale());
					header = messageSource.getMessage(TRY_AGAIN_HEADER, null, nsRequest.getLocale());
					return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(),header, message,
							Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null, nsRequest.getLocale())));
				}
			}
		}

		nsRequest.getAccountInfo().setAccountId(0);
		LOGGER.debug("Security code sent to user");

		return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), message,
				Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null, nsRequest.getLocale())));
	}*/

	/**
	 * To 1. validate expiry of security code user enters 2. validate security
	 * code entered by user against the code stored in DB
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse validateSecurityCode(NsRequest nsRequest) throws BusinessException, SystemException {

		String responseMsg = null;
		Integer responseCode = 0;

		AccountInfo accountInfo = nsRequest.getAccountInfo();
		validateAccountInfo(accountInfo);

		/** Get patient verification data from DB based on mrNumber and mobile */

		// Lets play rositta-rositta
		PatientVerification patientVerification = patientVerificationDAO.getPatientVerification(nsRequest
				.getAccountInfo().getAccountId());

		Date security_code_timeStamp = DateUtils.getCurrentUTCDate();
		LOGGER.info("Current UTC Time" +DateUtils.getCurrentUTCDate());
		long timeDiff = security_code_timeStamp.getTime() - patientVerification.getSecurityCodeTimestamp().getTime();
		//long diffMinutes = timeDiff / (60 * 1000) % 60;
		//System.currentTimeMillis
		
		long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff);

		int securityCodeTimeout = Integer.parseInt(messageSource.getMessage(SECURITY_CODE_EXPIRY_TIME, null,
				nsRequest.getLocale()));
		if (diffMinutes <= securityCodeTimeout) {
			String secureRecievedCode = MaskUtils.getDigest(nsRequest.getSecurityCode().getBytes());
			if (secureRecievedCode.equals(patientVerification.getSecurityCode())) {
				responseMsg = messageSource.getMessage(SECURITY_CODE_VAL_SUCCESS, null, nsRequest.getLocale());
				responseCode = Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale()));
			} else {
				responseMsg = messageSource.getMessage(SECURITY_CODE_VAL_FAIL, null, nsRequest.getLocale());
				responseCode = Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
						nsRequest.getLocale()));
			}
		} else {
			responseCode = Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,nsRequest.getLocale()));
			responseMsg = messageSource.getMessage(SECURITY_CODE_EXPIRED, null, nsRequest.getLocale());
		}

		nsRequest.getAccountInfo().setAccountId(patientVerification.getAccountId());
		nsRequest.getAccountInfo().setUnitNumber(
				patientVerification.getPatientVerificationPK().getMedicalRecordNumber());
		if (accountInfo.getMobilePhoneNumber() != null) {
			nsRequest.getAccountInfo().setMobilePhoneNumber(accountInfo.getMobilePhoneNumber());
		}
		if (accountInfo.getEmail() != null) {
			nsRequest.getAccountInfo().setEmail(accountInfo.getEmail());
		}
		nsRequest.getAccountInfo().setAccountId(patientVerification.getAccountId());
		return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), responseMsg, responseCode);
	}

	/**
	 * Method creates a new account or update existing account to persist
	 * PIN/Finger print selected by user for post registration authentication in
	 * the database.
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public NsResponse createUserAccount(NsRequest nsRequest) throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		AccountInfo accountInfo = nsRequest.getAccountInfo();
		validateAccountInfo(nsRequest.getAccountInfo());

		String userEnteredEmailAddr = accountInfo.getEmail();


		PatientVerification patientVerificationData = patientVerificationDAO.getPatientVerification(nsRequest
				.getAccountInfo().getAccountId());
		// ClientNaavisDatabases clientNaavisDatabases =
		// patientVerificationData.getClientNaavisDatabases();
		/** Check if account already exists or fresh registration */
		Account account = accountServiceDAO.getAccountByIdSpcl(accountInfo.getAccountId());// .getAccountByUnitNumber(unitNum);

		Boolean accountExists = true;
		if (account == null) {
			accountExists = false;
			LOGGER.info("Request received for fresh registration . . .");
			account = new Account();
			// account.setAccountId(patientVerificationData.getAccountId());
			account.setDateAdded(new Date());

			// account.setAccountId(patientVerificationData.getAccountId());
			account.setUniqueId(MaskUtils.generateRandomSaltBySize());
			account.setFirstName(patientVerificationData.getFirstName());
			account.setLastName(patientVerificationData.getLastName());
			if (accountInfo.getUnitNumber() != null && !accountInfo.getUnitNumber().isEmpty()) {
				account.setMedicalRecordNumber(accountInfo.getUnitNumber());
				account.setAccountRoleId(accountServiceDAO.getAcountRole(VersaWorkConstant.ACCOUNT_ROLE_PATIENT));
			} else {
				account.setAccountRoleId(accountServiceDAO.getAcountRole(VersaWorkConstant.ACCOUNT_ROLE_ACCOUNT));
			}
			account.setBirthDate(patientVerificationData.getBirthDate());
		}

		/** Check if email id is different from that registered */
		/** End point User Id is generated */
		String endpointUserId = MaskUtils.generateEndpointUserId(Integer.parseInt(messageSource.getMessage(
				ENDPOINT_USER_ID_LENGTH, null, nsRequest.getLocale())));
		String secureEndpointUserId = MaskUtils.getDigest(endpointUserId.getBytes());

		/* Check for any conflict in endpointUserId generated */
        // get all enpointuserid from account table in an arraylist
		List<String> endPointList =accountServiceDAO.getEndPointUserId();
		
		while(endPointList.contains(secureEndpointUserId))
		{
			endpointUserId = MaskUtils.generateEndpointUserId(Integer.parseInt(messageSource.getMessage(ENDPOINT_USER_ID_LENGTH, null, nsRequest.getLocale())));
			secureEndpointUserId = MaskUtils.getDigest(endpointUserId.getBytes());
		}
		
		/*Account tempAccount = accountServiceDAO.getAccountByEndpointId(secureEndpointUserId,
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		if (tempAccount != null) {
			while (tempAccount == null) {
				endpointUserId = MaskUtils.generateEndpointUserId(Integer.parseInt(messageSource.getMessage(
						ENDPOINT_USER_ID_LENGTH, null, nsRequest.getLocale())));
				secureEndpointUserId = MaskUtils.getDigest(endpointUserId.getBytes());
				tempAccount = accountServiceDAO.getAccountByEndpointId(secureEndpointUserId,
						Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
			}
		}*/

		String phoneNumber = accountInfo.getMobilePhoneNumber();
		phoneNumber = phoneNumber.replace("-", "");
		phoneNumber = phoneNumber.replace(" ", "");
		account.setMobilePhoneNumber(phoneNumber);
		account.setPreferredLanguage(accountInfo.getPreferredLanguage());

		if (account.getBirthDate() == null) {
			account.setBirthDate(patientVerificationData.getBirthDate());
		}
		account.setEndpointUserId(secureEndpointUserId);
		account.setAccountName(accountInfo.getEmail());
		account.setEmail(userEnteredEmailAddr);
		account.setWillShareData(accountInfo.isWillShareData());
		account.setPushNotification(true);
		account.setAuthToken(MaskUtils.generateRandomSalt());
		if (accountInfo.getDeviceToken() != null) {
			account.setDeviceToken(accountInfo.getDeviceToken());
		}
		if (accountInfo.getDeviceType() != null) {
			account.setDeviceType(accountInfo.getDeviceType());
		}

		account.setFailedLoginAttempts(0);
		account.setDisableDate(null);
		account.setLastLoginDate(new Date(System.currentTimeMillis()));


		if (accountInfo.getLoginAuthMode() != null) {
			account.setAuthPin(MaskUtils.getDigest(accountInfo.getLoginPin().getBytes()));

		} else {
			throw new BusinessException("insufficient.request.received");
		}
		account.setAuthMode(accountInfo.getLoginAuthMode());
		/* seperate client id from this app database */

		account.setClientDatabaseId(Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,
				Locale.getDefault())));
		account.setClientId(Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, Locale.getDefault())));

		account.setPassword(MaskUtils.generateEndpointUserId(10));
		if (accountExists) {
			accountInfo.setFirstName(account.getFirstName());
			accountInfo.setLastName(account.getLastName());
		} else {
			accountInfo.setFirstName(patientVerificationData.getFirstName());
			accountInfo.setLastName(patientVerificationData.getLastName());
			account.setAccountId(patientVerificationData.getAccountId());

		}
		accountServiceDAO.update(account);

		accountInfo.setAccountId(account.getAccountId());
		accountInfo.setClientDatabaseId(account.getClientDatabaseId());
		accountInfo.setAuthToken(account.getAuthToken());
		accountInfo.setRoleName(account.getAccountRoleId().getRoleName());
		accountInfo.setWillShareData(account.getWillShareData());
		accountInfo.setAccountId(account.getAccountId());
		accountInfo.setEndpointUserId(endpointUserId);
		accountInfo.setAccountName(account.getAccountName());
		accountInfo.setDateModified(account.getDateModified());
		accountInfo.setDateOfBirth(account.getBirthDate().toString());
		accountInfo.setDeviceToken(account.getDeviceToken());
		accountInfo.setDeviceType(account.getDeviceType());
		accountInfo.setEmail(account.getEmail());
		accountInfo.setFailedLoginAttempts(account.getFailedLoginAttempts());		
		accountInfo.setLoginAuthMode(account.getAuthMode());
		accountInfo.setMobilePhoneNumber(account.getMobilePhoneNumber());		
		accountInfo.setPreferredLanguage(account.getPreferredLanguage());
		accountInfo.setUnitNumber(account.getMedicalRecordNumber());		
		
		
		NsResponse notices = hospitalNoticeServiceImpl.getHospitalNotice(nsRequest);
		nsResponse.setHospitalNotices(notices.getHospitalNotices());

		CallFirePropertiesInfo callFirePropertiesInfo = new CallFirePropertiesInfo();
		callFirePropertiesInfo.setUserName(messageSource.getMessage("user", null, Locale.getDefault()));
		callFirePropertiesInfo.setPassword(messageSource.getMessage("password", null, Locale.getDefault()));
		callFirePropertiesInfo.setMessageSendModeText(messageSource.getMessage("messageSendModeText", null,
				Locale.getDefault()));
		callFirePropertiesInfo.setSmsURL(messageSource.getMessage("smsURL", null, Locale.getDefault()));
		try {
			Integer retryAttemptsNumber = Integer.parseInt((messageSource.getMessage(RETRY_ATTEMPTS_NUMBER, null,Locale.getDefault())));
			if (accountInfo.getEmail() != null) {
				new Thread(new Runnable() {						
					@Override
					public void run() {
						for(int i=0; i<retryAttemptsNumber;i++){
							System.out.println("Going to RUN");
							try{
								sendMail.sendMail("Dear "+accountInfo.getFirstName() + " " + accountInfo.getLastName()+",",
										"\n\n" + messageSource.getMessage("reg.thank.msg", null, nsRequest.getLocale()) + "\n\n",
										accountInfo.getEmail(), null,
										"\n" + messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, nsRequest.getLocale()));
								System.out.println("Succes");
								break;
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}).start();
				
			} else if (accountInfo.getMobilePhoneNumber() != null) {
				try {
					// SendMessage sendMessageForAuthInfo = new SendMessage();
					SimpleTextBroadcast sendMessageForAuthInfo = new SimpleTextBroadcast(callFirePropertiesInfo);
					new Thread(new Runnable() {						
						@Override
						public void run() {
							for(int i=0; i<retryAttemptsNumber;i++){
								System.out.println("Going to RUN");
								try{
									sendMessageForAuthInfo.sendPostSMS(accountInfo.getMobilePhoneNumber(),
											messageSource.getMessage("reg.thank.msg", null, nsRequest.getLocale()),
											callFirePropertiesInfo);
									System.out.println("Succes");
									break;
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}).start();	
					
				} catch (Exception e) {
					throw new BusinessException();
				}
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			LOGGER.error("Exception occoured while sending the mail during creating Account: ", exp);
			// throw new BusinessException(exp.getMessage());
		}
		
		ResponseData responseData = new ResponseData();
		responseData.setDescription("Account Created Successfully");
		responseData.setResult(Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		nsResponse.setAccountInfo(accountInfo);
		try {
			   // nsResponse.setFeatureMap(getAppFeatures().getFeatureMap());
			   nsResponse.setModuleList(getAppFeatures(nsRequest.getLocale()).getModuleList());   
			  } catch (Exception e) {
				  responseData.setDescription("Failed to get application features");
				  responseData.setResult(Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
			     nsRequest.getLocale())));
			  }
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}

	/**
	 * if failed login attempt = 3 wait to 5 mins from the update timestamp if
	 * failed login attempt >=6 send permanently locked prompt if success then
	 * get complete data from account table for the user. if no result, then
	 * find by unique endpoint user id and update failed login attempts and
	 * timestamps. lock account if required if no endpoint user id then return
	 * no account response. In case auth is by fingerprint, we only check for
	 * flag to be true and send response.
	 */
	@Override
	public NsResponse authenticateUserLogin(NsRequest nsRequest) throws BusinessException, SystemException {
		
		NsResponse nsResponse = new NsResponse();
		String responseMsg = null;
		Integer responseCode;
		try{
		ResponseData responsedata = new ResponseData();
		AccountInfo accountInfo = new AccountInfo();
	/*	if (messageSource.getMessage(MAINTENANCE, null, nsRequest.getLocale()).equalsIgnoreCase("true")) {
			responseMsg = messageSource.getMessage(DOWNTIME_MAINTENANCE_MESSAGE, null, nsRequest.getLocale());
			responsedata.setDescription(responseMsg);
			responsedata.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
			nsResponse.setResponseData(responsedata);
			nsResponse.setAccountInfo(accountInfo);
			return nsResponse;
		}*/

		if (messageSource.getMessage(SCHEDULED_MAINTENANCE, null, nsRequest.getLocale()).equalsIgnoreCase("true")) {
			nsResponse.setScheduledMaintainanceMsg(messageSource.getMessage(SCHEDULED_MAINTENANCE_MESSAGE, null,
					nsRequest.getLocale()));

		}
		accountInfo = nsRequest.getAccountInfo();
		Boolean editFlag = false;

		String secureEndpointUserId = MaskUtils.getDigest(accountInfo.getEndpointUserId().getBytes());
		Account account = accountServiceDAO.getAccountByEndpointId(secureEndpointUserId,
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, Locale.getDefault())));
		/*
		 * if (messageSource.getMessage(MAINTENANCE, null,
		 * nsRequest.getLocale()) .equalsIgnoreCase("true")) { responseMsg =
		 * messageSource.getMessage( DOWNTIME_MAINTENANCE_MESSAGE, null,
		 * nsRequest.getLocale()); responseCode =
		 * Integer.parseInt(messageSource.getMessage(
		 * INVALID_ENDPOINT_RESPONSE_CODE, null, nsRequest.getLocale())); return
		 * NsResponseUtils.getNsResponse(accountInfo, responseMsg,
		 * responseCode); } else
		 */if (account == null) {
			responseMsg = messageSource.getMessage(INVALID_ENDPOINT_ID, null, nsRequest.getLocale());
			responseCode = Integer.parseInt(messageSource.getMessage(INVALID_ENDPOINT_RESPONSE_CODE, null,
					nsRequest.getLocale()));
			return NsResponseUtils.getNsResponse(accountInfo, responseMsg, responseCode);
		}
		accountInfo.setAccountId(account.getAccountId());
		LOGGER.debug("Endpoint Id found in database.");
		// unlock account which was temporarily locked
		if ((account.getFailedLoginAttempts() == Integer.parseInt(messageSource.getMessage(
				LOGIN_FAILED_TEMP_LOCK_ATTEMPTS, null, nsRequest.getLocale()))) && (account.getDisableDate() != null)) {
			long timeDiff = (DateUtils.getCurrentUTCDate().getTime() - account.getDisableDate().getTime());
			long diffMinutes = timeDiff / (60 * 1000) % 60;
			if (diffMinutes >= Integer.parseInt(messageSource.getMessage(LOGIN_TEMP_LOCK_TIME, null,
					nsRequest.getLocale()))) {
				LOGGER.debug("Unlocking Account.");
				account.setDisableDate(null);
				editFlag = true;
			}
		}

		if (account.getDisableDate() == null) {
			String secureRecievedLoginPin = MaskUtils.getDigest(accountInfo.getLoginPin().toString().getBytes());

			if ((secureRecievedLoginPin.equals(account.getAuthPin())) || (nsRequest.getAuthByFingerprint())) {
				// reset locking flags
				account.setFailedLoginAttempts(0);
				account.setDisableDate(null);
				account.setLastLoginDate(new Date(System.currentTimeMillis()));
				// Set Account properties to account object
				accountInfo.setAccountName(account.getAccountName());
				accountInfo.setAuthToken(account.getAuthToken());
				accountInfo.setDateModified(account.getDateModified());
				accountInfo.setDateOfBirth(account.getBirthDate().toString());
				accountInfo.setDeviceToken(account.getDeviceToken());
				accountInfo.setDeviceType(account.getDeviceType());
				accountInfo.setEmail(account.getEmail());
				accountInfo.setFailedLoginAttempts(account.getFailedLoginAttempts());
				accountInfo.setFirstName(account.getFirstName());
				accountInfo.setLastName(account.getLastName());
				accountInfo.setLoginAuthMode(account.getAuthMode());
				accountInfo.setMobilePhoneNumber(account.getMobilePhoneNumber());
				accountInfo.setWillShareData(account.getWillShareData());
				accountInfo.setPreferredLanguage(account.getPreferredLanguage());
				accountInfo.setUnitNumber(account.getMedicalRecordNumber());
				accountInfo.setClientDatabaseId(account.getClientDatabaseId());
				accountInfo.setRoleName(account.getAccountRoleId().getRoleName());
				accountInfo.setPreferredLanguage(account.getPreferredLanguage());
				accountInfo.setAccountId(account.getAccountId());
				editFlag = true;
				responseMsg = ""; // blank response as asked by iOS team
				responseCode = Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale()));
				
				
				NsResponse notices = hospitalNoticeServiceImpl.getHospitalNotice(nsRequest);
				nsResponse.setHospitalNotices(notices.getHospitalNotices());
				/*NsResponse feedInfo = getFeedInfo(nsRequest);
				nsResponse.setFeedInformationList(feedInfo.getFeedInformationList());
				nsResponse.setNoMoreFeed(feedInfo.getNoMoreFeed());*/
				// Not required in ENgage 2.0
				/*NsResponse nsResponseForLoginFeatures = getLoginFeatures(nsRequestDummy);
				nsResponse.setNotificationWarningCount(nsResponseForLoginFeatures.getNotificationWarningCount());
				nsResponse.setHospitalNotices(nsResponseForLoginFeatures.getHospitalNotices());
				nsResponse.setMedicationYesCount(nsResponseForLoginFeatures.getMedicationYesCount());
				nsResponse.setMedicationTotalCount(nsResponseForLoginFeatures.getMedicationTotalCount());
				nsResponse.setBPYesCount(nsResponseForLoginFeatures.getBPYesCount());
				nsResponse.setBPTotalCount(nsResponseForLoginFeatures.getBPTotalCount());*/

				LOGGER.debug("Response set for sucessfull auth.");

			} else {
				// LOGIN FAILED
				account.setFailedLoginAttempts(account.getFailedLoginAttempts() + 1);
				editFlag = true;
				if (account.getFailedLoginAttempts() == Integer.parseInt(messageSource.getMessage(
						LOGIN_FAILED_TEMP_LOCK_ATTEMPTS, null, nsRequest.getLocale()))) {
					account.setDisableDate(new Date(DateUtils.getCurrentUTCDate().getTime()));
					accountInfo.setEmail(account.getEmail());
					accountInfo.setFirstName(account.getFirstName());
					accountInfo.setLastName(account.getLastName());
					sendMailToUser(accountInfo, false, nsRequest.getLocale());
					responseMsg = messageSource.getMessage(LOGIN_FAIL_TEMP_LOCK, null, nsRequest.getLocale());
					responseCode = Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
							nsRequest.getLocale()));
				} else if (account.getFailedLoginAttempts() == Integer.parseInt(messageSource.getMessage(
						LOGIN_FAILED_PERM_LOCK_ATTEMPTS, null, nsRequest.getLocale()))) {
					account.setDisableDate(new Date(DateUtils.getCurrentUTCDate().getTime()));

					accountInfo.setEmail(account.getEmail());
					accountInfo.setFirstName(account.getFirstName());
					accountInfo.setLastName(account.getLastName());
					sendMailToUser(accountInfo, true, nsRequest.getLocale());
					responseMsg = messageSource.getMessage(LOGIN_FAIL_PERM_LOCK, null, nsRequest.getLocale());
					responseCode = Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
							nsRequest.getLocale()));
				} else {
					responseMsg = messageSource.getMessage(LOGIN_FAIL, null, nsRequest.getLocale());
					responseCode = Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
							nsRequest.getLocale()));
				}
			}

		} else if (account.getFailedLoginAttempts() == Integer.parseInt(messageSource.getMessage(
				LOGIN_FAILED_TEMP_LOCK_ATTEMPTS, null, nsRequest.getLocale()))) {
			long timeDiff = (DateUtils.getCurrentUTCDate().getTime() - account.getDisableDate().getTime() );
			LOGGER.debug("timeDiff :"+timeDiff);
			long diffMinutes = Integer.parseInt(messageSource.getMessage(LOGIN_TEMP_LOCK_TIME, null,
					nsRequest.getLocale())) - (timeDiff / (60 * 1000) % 60);
			LOGGER.debug("diffMinutes :"+diffMinutes);
			responseMsg = messageSource.getMessage(ACCOUNT_TEMPORARILY_LOCKED, null, nsRequest.getLocale())
					+ diffMinutes + " minute(s).";
			responseCode = Integer
					.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null, nsRequest.getLocale()));
		} else {
			responseMsg = messageSource.getMessage(LOGIN_FAIL_PERM_LOCK, null, nsRequest.getLocale());
			responseCode = Integer
					.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null, nsRequest.getLocale()));
		}
		if (editFlag) {
			accountServiceDAO.update(account);
		}
		// nsResponse = loginService.forciblyVersionUpdate( nsResponse);
		responsedata.setDescription(responseMsg);
		responsedata.setResult(responseCode);
		nsResponse.setAccountInfo(accountInfo);
		nsResponse.setResponseData(responsedata);		
		nsResponse.setAppStoreVersion(messageSource.getMessage("app.version.store", null, Locale.getDefault()));
		try {
			// nsResponse.setFeatureMap(getAppFeatures().getFeatureMap());
			nsResponse.setModuleList(getAppFeatures(nsRequest.getLocale()).getModuleList());			
		} catch (Exception e) {
			responsedata.setDescription("Failed to get application features");
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
		}
		return nsResponse;
	}catch(Exception e){
		e.printStackTrace();
	}
		return nsResponse;	
	}

	// Not required in Engage 2.0
	/*@Override
	public NsResponse getLoginFeatures(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse2 = new NsResponse();
		NsResponse nsResponse = new NsResponse();
		NsRequest nsRequest1 = new NsRequest();
		AccountInfo accountInfo = nsRequest.getAccountInfo();
		nsRequest1.setAccountInfo(accountInfo);

		float bloodPressurePercentage = 0;

		nsResponse2 = notificationServiceImpl.getWarningMessagesOnLogin(nsRequest1);
		if (nsResponse2.getNotificationWarningCount() != null) {
			nsResponse.setNotificationWarningCount(nsResponse2.getNotificationWarningCount());
		} else {
			nsResponse.setNotificationWarningCount(0);
		}

		NsResponse nsResponseDummy = hospitalNoticeServiceImpl.getHospitalNotice(nsRequest1);
		nsResponse.setHospitalNotices(nsResponseDummy.getHospitalNotices());
		try {
			nsResponse.setHospitalNotificationsCount(nsResponseDummy.getNotificationsCount().getHospitalNotificationCount());
		} catch (Exception e) {
			nsResponse.setHospitalNotificationsCount(0);
		}
		MedicationManagementReminderInfo medicationManagementReminderInfo = new MedicationManagementReminderInfo();
		medicationManagementReminderInfo.setAccountId(accountInfo.getAccountId());
		nsRequest1.setMedicationManagementReminder(medicationManagementReminderInfo);

		nsResponse2 = medicationManagementServiceImpl.getMedicationReminderCountResponse(nsRequest1);
		if (nsResponse2.getMedicationYesCount() != null) {
			nsResponse.setMedicationYesCount(nsResponse2.getMedicationYesCount());
		} else {
			nsResponse.setMedicationYesCount(0);
		}
		if (nsResponse2.getMedicationTotalCount() != null) {
			nsResponse.setMedicationTotalCount(nsResponse2.getMedicationTotalCount());
		} else {
			nsResponse.setMedicationTotalCount(0);
		}

		nsResponse2 = bloodPressureServiceImpl.getBPCountResponse(nsRequest1);
		nsResponse.setBPYesCount(nsResponse2.getBPYesCount());
		nsResponse.setBPTotalCount(nsResponse2.getBPTotalCount());

		if (nsResponse2.getBPTotalCount() != 0.0) {
			bloodPressurePercentage = (float) ((nsResponse2.getBPYesCount() * 100) / (nsResponse2.getBPTotalCount()));
		}

		return nsResponse;

	}*/

	/**
	 * Validates the Account information received in the request.
	 * 
	 * @param accountInfo
	 * @throws BusinessException
	 */
	private void validateAccountInfo(AccountInfo accountInfo) throws BusinessException {

		if (accountInfo == null || accountInfo.getUnitNumber() == null || accountInfo.getMobilePhoneNumber() == null) {
			throw new BusinessException("insufficient.request.received");
		}
	}

	@Override
	public NsResponse sendSecurityCodeForPhoneNumberChange(NsRequest nsRequest) throws BusinessException,
			SystemException {
		String responseMsg = null;
		NsResponse nsResponse = new NsResponse();
		Integer responseCode = 0;
		Integer codeSendOptions = 1;
		Account account = accountServiceDAO.getAccountByEndpointId(
				MaskUtils.getDigest(nsRequest.getSecurityCode().getBytes()),
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		if (account == null) {
			responseMsg = messageSource.getMessage(INVALID_ENDPOINT_ID, null, nsRequest.getLocale());
			responseCode = Integer.parseInt(messageSource.getMessage(INVALID_ENDPOINT_RESPONSE_CODE, null,
					nsRequest.getLocale()));
			return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), responseMsg, responseCode);
		}
		AccountInfo accountInfo = nsRequest.getAccountInfo();
		accountInfo.setClientDatabaseId(account.getClientDatabaseId());
		accountInfo.setAccountId(account.getAccountId());
		accountInfo.setUnitNumber(account.getMedicalRecordNumber());
		accountInfo.setFirstName(account.getFirstName());
		accountInfo.setLastName(account.getLastName());
		if (accountInfo.getMobilePhoneNumber() == null && account.getMobilePhoneNumber() != null) {
			accountInfo.setMobilePhoneNumber(account.getMobilePhoneNumber());
		}
		if (account.getEmail() != null) {
			accountInfo.setEmail(account.getEmail());
		}
		NsRequest nsRequestDummy = new NsRequest();
		/*if (accountInfo.getMobilePhoneNumber() != null && account.getEmail() != null) {
			codeSendOptions = 1;
			nsRequestDummy.setCodeSendMode("TEXTA");
		} else if (accountInfo.getMobilePhoneNumber() != null && account.getEmail() == null) {
			codeSendOptions = 2;
			nsRequestDummy.setCodeSendMode("TEXTA");
		} else {
			codeSendOptions = 3;
			nsRequestDummy.setCodeSendMode("MAILA");
		}*/
		nsRequestDummy.setAccountInfo(accountInfo);
		nsResponse = sendSecurityCode(nsRequestDummy);
		nsResponse.setCodeSendOptions(codeSendOptions);

		return nsResponse;
	}

	@Override
	public NsResponse sendSecurityCodeForProfileEdit(NsRequest nsRequest) throws BusinessException, SystemException {

		AccountInfo accountInfo = new AccountInfo();
		accountInfo = nsRequest.getAccountInfo();
		String phoneNumber = accountInfo.getMobilePhoneNumber();
		phoneNumber = phoneNumber.replace("-", "");
		phoneNumber = phoneNumber.replace(" ", "");
		accountInfo.setMobilePhoneNumber(phoneNumber);
		// Generate Security Code
		String securityCode = MaskUtils.generateSecurityCode(Integer.parseInt(messageSource.getMessage(
				SECURITY_CODE_LENGTH, null, nsRequest.getLocale())));
		String message = messageSource.getMessage(SEND_SECURITY_CODE_SUCCESS_SMS, null, nsRequest.getLocale());
		if ((messageSource.getMessage(SECURITY_CODE_MODE, null, nsRequest.getLocale()).equalsIgnoreCase("LOCAL"))
				|| (nsRequest.getAccountInfo().getUnitNumber().equalsIgnoreCase("TEST001"))) {
			securityCode = "123456";
		}
		String secureSecurityCode = MaskUtils.getDigest(securityCode.getBytes());
		// Save code to DB

		// Lets play rositta-rositta
		PatientVerification patientVerification = patientVerificationDAO.getPatientVerification(nsRequest
				.getAccountInfo().getAccountId());
		patientVerification.setSecurityCode(secureSecurityCode);
		patientVerification.setSecurityCodeTimestamp(DateUtils.getCurrentUTCDate());
		patientVerificationDAO.updatePatientVerification(patientVerification);

		if (!messageSource.getMessage(SECURITY_CODE_MODE, null, nsRequest.getLocale()).equalsIgnoreCase("LOCAL")) {
			CallFirePropertiesInfo callFirePropertiesInfo = new CallFirePropertiesInfo();
			callFirePropertiesInfo.setUserName(messageSource.getMessage("user", null, Locale.getDefault()));
			callFirePropertiesInfo.setPassword(messageSource.getMessage("password", null, Locale.getDefault()));
			callFirePropertiesInfo.setMessageSendModeText(messageSource.getMessage("messageSendModeText", null,
					Locale.getDefault()));
			callFirePropertiesInfo.setSmsURL(messageSource.getMessage("smsURL", null, Locale.getDefault()));
			callFirePropertiesInfo.setTextMessage(messageSource.getMessage("textMessage", null, Locale.getDefault()));
			SimpleTextBroadcast sendMessageForAuthInfo = new SimpleTextBroadcast(callFirePropertiesInfo);
			final AccountInfo accountInfoThread = accountInfo;
			final String securityCodeForThread = securityCode;
			LOGGER.debug("Send Mode Code : "+nsRequest.getCodeSendMode());
			Integer retryAttemptsNumber = Integer.parseInt((messageSource.getMessage(RETRY_ATTEMPTS_NUMBER, null,Locale.getDefault())));
			if (nsRequest.getCodeSendMode().equalsIgnoreCase(SECURITY_CODE_AUTH_MODE_TEXT)) {
				try {
					LOGGER.info("SMS : "+nsRequest.getCodeSendMode());					
					new Thread(new Runnable() {						
						@Override
						public void run() {
							for(int i=0; i<retryAttemptsNumber;i++){
								System.out.println("Going to RUN");
								try{
									sendMessageForAuthInfo.sendPostSMS(accountInfoThread.getMobilePhoneNumber(),
											"Your six digit verification code for engage is : " + securityCodeForThread, callFirePropertiesInfo);
									System.out.println("Succes");
									break;
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}).start();	
					
					message = messageSource.getMessage(SEND_SECURITY_CODE_SUCCESS_SMS, null, nsRequest.getLocale());
					LOGGER.info("SMS Message: "+message);
				} catch (Exception e) {
					throw new BusinessException();
				}
			} else if (nsRequest.getCodeSendMode().equalsIgnoreCase(SECURITY_CODE_AUTH_MODE_IVR)) {
				try {
					LOGGER.info("IVR : "+nsRequest.getCodeSendMode());
					new Thread(new Runnable() {						
						@Override
						public void run() {
							for(int i=0; i<retryAttemptsNumber;i++){
								System.out.println("Going to RUN " + retryAttemptsNumber);
								try{
									sendMessageForAuthInfo.sendPostSMS(messageSource.getMessage(SMS_MESSAGE_BODY, null, nsRequest.getLocale())+" "	+ securityCodeForThread, accountInfoThread.getMobilePhoneNumber(), callFirePropertiesInfo);
									System.out.println("Succes");
									break;
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}).start();	
					
					message = messageSource.getMessage(SEND_SECURITY_CODE_SUCCESS_IVR, null, nsRequest.getLocale());
					LOGGER.info("IVR Message: "+message);
				} catch (Exception e) {
					throw new BusinessException();
				}
			} else {
				try {
					LOGGER.debug("MAIL : "+nsRequest.getCodeSendMode());
					new Thread(new Runnable() {						
						@Override
						public void run() {
							for(int i=0; i<retryAttemptsNumber;i++){
								System.out.println("Going to RUN");
								try{
									sendMail.sendMail("Dear "+accountInfoThread.getFirstName() + " " + accountInfoThread.getLastName()+",",
											"\n\nYour six digit verification code for profile update is : " + securityCodeForThread + "\n\n",
											accountInfoThread.getEmail(), messageSource.getMessage(MAIL_PROFILE_UPDATE_SUBJECT, null, nsRequest.getLocale()),
											"\n" + messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, nsRequest.getLocale()));
									System.out.println("Succes");
									break;
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					}).start();
					
					message = messageSource.getMessage(SEND_SECURITY_CODE_SUCCESS_MAIL, null, nsRequest.getLocale());
					LOGGER.debug("MAIL Message: "+message);
				} catch (Exception exp) {
					exp.printStackTrace();
					LOGGER.error("Exception occoured while sending the mail during creating Account: ", exp);
					// throw new BusinessException(exp.getMessage());
				}
			}
		}

		nsRequest.getAccountInfo().setAccountId(0);
		LOGGER.debug("Security code sent to user");

		return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), message,
				Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null, nsRequest.getLocale())));

	}

	/*
	 * @Override public NsResponse requestFilter(NsRequest nsRequest) throws
	 * BusinessException, SystemException { NsResponse nsResponse = new
	 * NsResponse(); ResponseData responseData = new ResponseData(); AccountInfo
	 * accountInfo = new AccountInfo();
	 * responseData.setResult(Integer.parseInt(messageSource.getMessage(
	 * SUCCESS_RESPONSE_CODE, null, nsRequest.getLocale())));
	 * responseData.setDescription("Success"); try { if
	 * (nsRequest.getAccountInfo() != null &&
	 * (((nsRequest.getAccountInfo().getAccountId() != null && nsRequest
	 * .getAccountInfo().getAccountId() == -1) || (nsRequest
	 * .getAccountInfo().getEndpointUserId() != null && nsRequest
	 * .getAccountInfo().getEndpointUserId() .equalsIgnoreCase("-1"))) ||
	 * (nsRequest .getAccountInfo().getDeviceToken() != null && nsRequest
	 * .getAccountInfo().getDeviceToken() .equalsIgnoreCase("-1")))) {
	 * accountInfo.setAccountId(-1);
	 * responseData.setResult(Integer.parseInt(messageSource
	 * .getMessage(DEVICE_RESET_RESPONSE_CODE, null, nsRequest.getLocale())));
	 * responseData.setDescription(messageSource.getMessage( DEVICE_RESET, null,
	 * nsRequest.getLocale())); LOGGER.info("failed"); } } catch (Exception e) {
	 * e.printStackTrace(); nsResponse.setAccountInfo(accountInfo);
	 * nsResponse.setResponseData(responseData); return nsResponse; }
	 * nsResponse.setAccountInfo(accountInfo);
	 * nsResponse.setResponseData(responseData); return nsResponse; }
	 */
	@Override
	public NsResponse getAppFeatures(Locale locale) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		List<EngageClientToFeature> engageClientToFeature = userRegistrationDAO.getClientFeatures(Integer
				.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, locale)),messageSource.getMessage("naavis.phase.version", null, locale));

		List<EngageModule> moduleList = new ArrayList<EngageModule>();
		
		EngageModule myHealthModule = null;
		EngageModule manageModule = null;
		EngageModule hospitalDirectoryModule = null;
		EngageModule aboutModule = null;
		EngageModule toDoModule = null;
		
		Iterator<EngageClientToFeature> iter = engageClientToFeature.iterator();
		while (iter.hasNext()) {
			EngageClientToFeature eCTF = iter.next();
			if (eCTF.getFeatureId().getModuleId().getModuleId() == VersaWorkConstant.MY_HEALTH_MODULE_ID) {
				if(myHealthModule==null){
					myHealthModule=new EngageModule();
					myHealthModule.setModuleId(eCTF.getFeatureId().getModuleId().getModuleId());
					myHealthModule.setModuleName(eCTF.getFeatureId().getModuleId().getModuleName());
				}
				EngageFeatures eFeatures = new EngageFeatures();
				eFeatures.setFeatureId(eCTF.getFeatureId().getFeatureId());
				eFeatures.setFeatureName(eCTF.getFeatureId().getFeatureName());					
				myHealthModule.getFeaturesList().add(eFeatures);				
			}
			else if(eCTF.getFeatureId().getModuleId().getModuleId() == VersaWorkConstant.MANAGE_MODULE_ID){
				if(manageModule==null){
					manageModule=new EngageModule();
					manageModule.setModuleId(eCTF.getFeatureId().getModuleId().getModuleId());
					manageModule.setModuleName(eCTF.getFeatureId().getModuleId().getModuleName());
				}
				EngageFeatures eFeatures = new EngageFeatures();
				eFeatures.setFeatureId(eCTF.getFeatureId().getFeatureId());
				eFeatures.setFeatureName(eCTF.getFeatureId().getFeatureName());					
				manageModule.getFeaturesList().add(eFeatures);
			}
			else if(eCTF.getFeatureId().getModuleId().getModuleId() == VersaWorkConstant.HOSPITAL_DIRECTORY_MODULE_ID){
				if(hospitalDirectoryModule==null){
					hospitalDirectoryModule=new EngageModule();
					hospitalDirectoryModule.setModuleId(eCTF.getFeatureId().getModuleId().getModuleId());
					hospitalDirectoryModule.setModuleName(eCTF.getFeatureId().getModuleId().getModuleName());
				}
				EngageFeatures eFeatures = new EngageFeatures();
				eFeatures.setFeatureId(eCTF.getFeatureId().getFeatureId());
				eFeatures.setFeatureName(eCTF.getFeatureId().getFeatureName());					
				hospitalDirectoryModule.getFeaturesList().add(eFeatures);
			}
			else if(eCTF.getFeatureId().getModuleId().getModuleId() == VersaWorkConstant.ABOUT_APP_MODULE_ID){
				if(aboutModule==null){
					aboutModule=new EngageModule();
					aboutModule.setModuleId(eCTF.getFeatureId().getModuleId().getModuleId());
					aboutModule.setModuleName(eCTF.getFeatureId().getModuleId().getModuleName());
				}
				EngageFeatures eFeatures = new EngageFeatures();
				eFeatures.setFeatureId(eCTF.getFeatureId().getFeatureId());
				eFeatures.setFeatureName(eCTF.getFeatureId().getFeatureName());					
				aboutModule.getFeaturesList().add(eFeatures);
			}
			else if(eCTF.getFeatureId().getModuleId().getModuleId() == VersaWorkConstant.TO_DO_MODULE_ID){
				if(toDoModule==null){
					toDoModule=new EngageModule();
					toDoModule.setModuleId(eCTF.getFeatureId().getModuleId().getModuleId());
					toDoModule.setModuleName(eCTF.getFeatureId().getModuleId().getModuleName());
				}
				EngageFeatures eFeatures = new EngageFeatures();
				eFeatures.setFeatureId(eCTF.getFeatureId().getFeatureId());
				eFeatures.setFeatureName(eCTF.getFeatureId().getFeatureName());					
				toDoModule.getFeaturesList().add(eFeatures);
			}
		}
		if(myHealthModule!=null){moduleList.add(myHealthModule);}
		if(manageModule!=null){moduleList.add(manageModule);}
		if(hospitalDirectoryModule!=null){moduleList.add(hospitalDirectoryModule);}
		if(aboutModule!=null){moduleList.add(aboutModule);}
		if(toDoModule!=null){moduleList.add(toDoModule);}
		/*
		 * Map<String, List<String>> fMap = new HashMap<String, List<String>>();
		 * Set<String> featurset = new HashSet<String>(); for
		 * (EngageClientToFeature eCtoF : engageClientToFeature) {
		 * featurset.add(eCtoF.getFeatureId().getModuleId().getModuleName()); }
		 * Iterator<String> iter = featurset.iterator(); while (iter.hasNext())
		 * { String moduleName = iter.next(); List<EngageFeatures> featureList =
		 * new ArrayList<EngageFeatures>(); for (EngageClientToFeature eCtoF :
		 * engageClientToFeature) { if
		 * (eCtoF.getFeatureId().getModuleId().getModuleName()
		 * .equalsIgnoreCase(moduleName)) {
		 * featureList.add(eCtoF.getFeatureId().getFeatureName()); } }
		 * fMap.put(moduleName, featureList); }
		 */

		nsResponse.setModuleList(moduleList);
		return nsResponse;
	}

	@Async
	public void sendMailToUser(AccountInfo accountInfo, Boolean permFlag, Locale locale) throws BusinessException {
		String message;
		if (!permFlag) {
			message = "\n\n" + messageSource.getMessage("temp.lock.message", null, locale) + "\n\n";
		} else {
			message = "\n\n" + messageSource.getMessage("perm.lock.message", null, locale) + "\n\n";
		}
		try {
			Integer retryAttemptsNumber = Integer.parseInt((messageSource.getMessage(RETRY_ATTEMPTS_NUMBER, null,Locale.getDefault())));
			new Thread(new Runnable() {						
				@Override
				public void run() {
					for(int i=0; i<retryAttemptsNumber;i++){
						System.out.println("Going to RUN");
						try{
							sendMail.sendMail("Dear "+
									accountInfo.getFirstName() + " " + accountInfo.getLastName()+",",
									message,
									accountInfo.getEmail(),
									messageSource.getMessage("acc.lock.header", null, locale),
									"\n" + messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, locale));
							System.out.println("Succes");
							break;
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}).start();	
			
		} catch (Exception exp) {
			exp.printStackTrace();
			LOGGER.error("Exception occoured while sending the mail during creating Account: ", exp);
			// throw new BusinessException(exp.getMessage());
		}
	}

	@Override
	public NsResponse validateUser(NsRequest nsRequest) throws BusinessException, SystemException {
		try {
			Account account = accountServiceDAO.getAccount(nsRequest, Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));

			ResponseData responseData = new ResponseData();
			NsResponse nsResponse = new NsResponse();
			if (account == null) {
				throw new BusinessException("user.invalid.entry.forgot");
			} else {
				
				if (account.getFailedLoginAttempts() == Integer.parseInt(messageSource.getMessage(
						LOGIN_FAILED_TEMP_LOCK_ATTEMPTS, null, nsRequest.getLocale()))) {
					
					long timeDiff = (DateUtils.getCurrentUTCDate().getTime() - account.getDisableDate().getTime() + 5);
					long diffMinutes = 5 - (timeDiff / (60 * 1000) % 60);
					if (diffMinutes <= Integer.parseInt(messageSource.getMessage(LOGIN_TEMP_LOCK_TIME, null,
							nsRequest.getLocale()))) {
						responseData.setDescription(messageSource.getMessage(ACCOUNT_TEMPORARILY_LOCKED, null, nsRequest.getLocale())
								+ diffMinutes + " minute(s).");
						responseData.setResult(Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
						nsResponse.setResponseData(responseData);
						return nsResponse;
					}
				} else if (account.getFailedLoginAttempts() >= Integer.parseInt(messageSource.getMessage(
						LOGIN_FAILED_PERM_LOCK_ATTEMPTS, null, nsRequest.getLocale()))){
					throw new BusinessException(LOGIN_FAIL_PERM_LOCK);
				}
				
				LOGGER.debug(DateUtils.getFormatDate(account.getBirthDate(),"MM/dd/yyyy")+" DATE "+nsRequest.getAccountInfo().getDateOfBirth());
				LOGGER.debug(account.getMedicalRecordNumber()+" MR NUMBER "+nsRequest.getAccountInfo().getUnitNumber());
				LOGGER.debug(account.getEmail()+" EMAIL "+nsRequest.getAccountInfo().getEmail());
				LOGGER.debug(account.getMobilePhoneNumber()+" MOBILE "+nsRequest.getAccountInfo().getMobilePhoneNumber());
				
				LOGGER.debug("DATE "+DateUtils.getFormatDate(account.getBirthDate(),"MM/dd/yyyy").equalsIgnoreCase(nsRequest.getAccountInfo().getDateOfBirth()));
				LOGGER.debug("MR NUMBER "+account.getMedicalRecordNumber().equalsIgnoreCase(nsRequest.getAccountInfo().getUnitNumber()));
				LOGGER.debug("EMAIL ID "+account.getEmail().equalsIgnoreCase(nsRequest.getAccountInfo().getEmail()));
				LOGGER.debug("Mobile "+account.getMobilePhoneNumber().equalsIgnoreCase(nsRequest.getAccountInfo().getMobilePhoneNumber()));
				
				if (DateUtils.getFormatDate(account.getBirthDate(),"MM/dd/yyyy").equalsIgnoreCase(nsRequest.getAccountInfo().getDateOfBirth())
						&& account.getMedicalRecordNumber().equalsIgnoreCase(nsRequest.getAccountInfo().getUnitNumber())
						&& account.getEmail().equalsIgnoreCase(nsRequest.getAccountInfo().getEmail())
						&& account.getMobilePhoneNumber().equalsIgnoreCase(nsRequest.getAccountInfo().getMobilePhoneNumber())) {
					LOGGER.debug("All conditions verified");
					try {
						String securityCode = MaskUtils.generateSecurityCode(Integer.parseInt(messageSource.getMessage(
								SECURITY_CODE_LENGTH, null, nsRequest.getLocale())));
						if ((messageSource.getMessage(SECURITY_CODE_MODE, null, nsRequest.getLocale())
								.equalsIgnoreCase("LOCAL"))
								|| (nsRequest.getAccountInfo().getUnitNumber().equalsIgnoreCase("TEST001"))) {
							securityCode = "123456";
						}
						String secureSecurityCode = MaskUtils.getDigest(securityCode.getBytes());

						// Lets play rositta-rositta
						PatientVerification patientVerification = patientVerificationDAO.getPatientVerification(account
								.getAccountId());
						patientVerification.setSecurityCode(secureSecurityCode);
						patientVerification.setSecurityCodeTimestamp(DateUtils.getCurrentUTCDate());

						patientVerificationDAO.updatePatientVerification(patientVerification);

						if (!messageSource.getMessage(SECURITY_CODE_MODE, null, nsRequest.getLocale())
								.equalsIgnoreCase("LOCAL")) {

							// SendMessage sendMessageForAuthInfo = new
							// SendMessage();
							CallFirePropertiesInfo callFirePropertiesInfo = new CallFirePropertiesInfo();

							callFirePropertiesInfo.setUserName(messageSource.getMessage("user", null,
									Locale.getDefault()));
							callFirePropertiesInfo.setPassword(messageSource.getMessage("password", null,
									Locale.getDefault()));
							callFirePropertiesInfo.setMessageSendModeText(messageSource.getMessage(
									"messageSendModeText", null, Locale.getDefault()));
							callFirePropertiesInfo.setFromNumber(messageSource.getMessage("fromNumber", null,
									Locale.getDefault()));
							callFirePropertiesInfo.setSmsURL(messageSource.getMessage("smsURL", null,
									Locale.getDefault()));
							callFirePropertiesInfo.setTextMessage(messageSource.getMessage("textMessage", null,
									Locale.getDefault()));

							SimpleTextBroadcast sendMessageForAuthInfo = new SimpleTextBroadcast(callFirePropertiesInfo);
							try{
								final String securityCodeForThread = securityCode;
								Integer retryAttemptsNumber = Integer.parseInt((messageSource.getMessage(RETRY_ATTEMPTS_NUMBER, null,Locale.getDefault())));
								new Thread(new Runnable() {						
									@Override
									public void run() {
										for(int i=0; i<retryAttemptsNumber;i++){
											System.out.println("Going to RUN");
											try{
												sendMessageForAuthInfo.sendTextUsingTextService(SECURITY_CODE_AUTH_MODE_TEXT, securityCodeForThread, nsRequest.getAccountInfo().getMobilePhoneNumber(), callFirePropertiesInfo);
												System.out.println("Succes");
												break;
											}catch(Exception e){
												e.printStackTrace();
											}
										}
									}
								}).start();	
								
									responseData.setResult(Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null,
											nsRequest.getLocale())));
									responseData.setDescription(messageSource.getMessage(SEND_SECURITY_CODE_SUCCESS_SMS, null,
											nsRequest.getLocale()));
									nsResponse.setResponseData(responseData);
									return nsResponse;
								
							} catch (Exception e) {
								throw new BusinessException("sms.delivery.error");
							}
						}
					} catch (Exception e) {
						throw new SystemException();
					}

				} else {
					throw new BusinessException("user.invalid.entry.forgot");
				}

			}

			responseData.setResult(Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responseData.setDescription(messageSource.getMessage(SEND_SECURITY_CODE_SUCCESS_SMS, null,
					nsRequest.getLocale()));
			nsResponse.setResponseData(responseData);
			return nsResponse;
		} catch (SystemException e) {
			throw new BusinessException("user.invalid.entry.forgot");
		}
	}

	@Override
	public NsResponse getFeedInfo(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		AccountInfo accountInfoModel=nsRequest.getAccountInfo();
		Integer feedSize=nsRequest.getFeedSize();
		List<FeedListInfo> feedList = new ArrayList<FeedListInfo>();
		
		Integer clientDatabaseId=Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,	Locale.getDefault()));
		Integer clientId=Integer.parseInt(messageSource.getMessage(CLIENT_ID, null,	Locale.getDefault()));
		List<FeedInfo> feedInfoList = 
				userRegistrationDAO.getFeedInfoList(accountInfoModel.getAccountId(),clientId,clientDatabaseId,feedSize );
		LOGGER.info("feedInfoList:----"+feedInfoList);
		
		
		if (feedInfoList.isEmpty()) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage(NO_FEED_INFO, null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}
		if(feedInfoList.size()<10)
		{
			nsResponse.setNoMoreFeed(true);
		}
		
		for (FeedInfo feedInfo : feedInfoList) {
			
			FeedListInfo  feedSummaryList = new FeedListInfo();
			/*feedSummaryList.setAccountId(feedInfo.getAccountId());
			feedSummaryList.setFeatureId(feedInfo.getFeatureId());
			feedSummaryList.setFeatureName(feedInfo.getFeatureName());
			feedSummaryList.setFeedHeader(feedInfo.getFeedHeader());
			feedSummaryList.setFeedInfoId(feedInfo.getFeedInfoId());
			feedSummaryList.setFeedMsg(feedInfo.getFeedMsg());
			feedSummaryList.setIsNew(feedInfo.getIsNew());
			feedSummaryList.setSourceName(feedInfo.getSourceName());
			feedSummaryList.setDateAdded(DateUtils.getFormatDate(feedInfo.getDateAdded(), "MM/dd/yyyy"));
			feedSummaryList.setRecordDate(DateUtils.getFormatDate(feedInfo.getRecordDate(),"MM/dd/yyyy hh:mm a"));*/
			feedSummaryList.setAccountId(feedInfo.getAccountId());
			feedSummaryList.setClientId(feedInfo.getClientId());
			feedSummaryList.setClientDatabseId(feedInfo.getClientDatabaseId());
			feedSummaryList.setFeatureId(feedInfo.getFeatureId());
			feedSummaryList.setFeedMessageId(feedInfo.getFeedMessageId());
			feedSummaryList.setRecordDate(DateUtils.getFormatDate(feedInfo.getClinicalDateTime(),DateUtils.DATE_ADDED_UTC_FORMAT));
			feedSummaryList.setRecordTime(feedInfo.getDisplayTime());
			feedSummaryList.setFeedHeader(feedInfo.getFeedHeader());
			feedSummaryList.setFeedMessage(feedInfo.getFeedMsg());
			feedSummaryList.setFeatureName(feedInfo.getFeatureName());
			feedSummaryList.setSourceName(feedInfo.getSourceName());
			feedSummaryList.setIsNew(feedInfo.getIsNew());
			feedSummaryList.setDateAdded(DateUtils.getFormatDate(feedInfo.getDateAdded(),DateUtils.DATE_ADDED_FORMAT_12_HOUR));
			
			feedList.add(feedSummaryList);
			
			
		}
		
		ResponseData responseData = new ResponseData();
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		//nsResponse.setMedicationManagmntInfoList(feedObjectList);
		nsResponse.setFeedInformationList(feedList);
	
	return nsResponse;
		
		
	}

	@Override
	public NsResponse updateFeedInfo(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		/*List<FeedListInfo> feedList = new ArrayList<FeedListInfo>();
		List<FeedInfo> feedInfoList = 
				userRegistrationDAO.getFeedInfoList(nsRequest
				.getAccountInfo().getAccountId());
		
		if (feedInfoList.isEmpty()) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage(NO_FEED_INFO, null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}*/
		
		Integer clientDatabaseId=Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,	Locale.getDefault()));
		Integer clientId=Integer.parseInt(messageSource.getMessage(CLIENT_ID, null,	Locale.getDefault()));
		AccountInfo accountInfoModel=nsRequest.getAccountInfo();
		FeedListInfo feedListInfo = nsRequest.getFeedListInfo();
		
		FeedInfo feedInfo = userRegistrationDAO.getFeedInfo(accountInfoModel.getAccountId(),clientId,clientDatabaseId,feedListInfo.getFeedMessageId(),feedListInfo.getFeatureId());
		
		if (feedInfo==null) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage(INVALID_FEED_ID, null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}
		
		feedInfo.setIsNew(false);
		feedInfo = userRegistrationDAO.updateFeedInformation(feedInfo);
		
		ResponseData responseData = new ResponseData();
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
	
	return nsResponse;

		
	}

	@Override
	public NsResponse sendForgotPin(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		String securityCode= null;
		String SecurityCodeForThread = null;
		if ((messageSource.getMessage(SECURITY_CODE_MODE, null, Locale.getDefault()).equalsIgnoreCase("LOCAL"))) {
			securityCode = "1234";
		}
		
		else
		{
		 securityCode = MaskUtils.generateSecurityCode(Integer.parseInt(messageSource.getMessage(
				SECURITY_PIN_CODE_LENGTH, null, nsRequest.getLocale())));
		
		
		}
		SecurityCodeForThread = securityCode;
		String secureSecurityCode = MaskUtils.getDigest(securityCode.getBytes());
		Account account=accountServiceDAO.getAccountByIdSpcl(nsRequest.getAccountInfo().getAccountId());
		//account.setAuthPin(secureSecurityCode);
		account.setTempPin(secureSecurityCode);		
		account.setDateModified(DateUtils.getCurrentUTCDate());
		LOGGER.info("Current UTC Time" +DateUtils.getCurrentUTCDate());
		accountServiceDAO.update(account);
		LOGGER.debug("Security pin code saved successfully");
		
		String email=account.getEmail();
		String name=email.substring(1,email.indexOf("@"));
		String masked=EmailUtils.maskedEmail(email, name);
		
		String fogetPinMessage=messageSource.getMessage(RESEND_FORGET_SECURITY_CODE_SUCCESS_MAIL, new String[] { String.valueOf(masked) }, nsRequest.getLocale());
		
		
			sendMail.sendMail(account.getFirstName()+ " " + account.getLastName() + ",",
					"\n\n" + messageSource.getMessage(TEXT_MSG_PIN, new String[] { String.valueOf(SecurityCodeForThread) }, nsRequest.getLocale())
						+"\n\n"	, account.getEmail(), messageSource.getMessage(HOSPITAL_SUBJECT, null, nsRequest.getLocale()),
					"\n" + messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, nsRequest.getLocale()));
			System.out.println("Succes");
	
		
		
		ResponseData responseData = new ResponseData();
		responseData.setDescription(fogetPinMessage);
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);

		
		return nsResponse;
	}
	public NsResponse validatePinCode(NsRequest nsRequest) throws BusinessException, SystemException {

		String responseMsg = null;
		Integer responseCode = 0;

		AccountInfo accountInfo = nsRequest.getAccountInfo();
		validateAccountInfo(accountInfo);

		/** Get patient verification data from DB based on mrNumber and mobile */

		// Lets play rositta-rositta
		Account account=accountServiceDAO.getAccountByIdSpcl(nsRequest.getAccountInfo().getAccountId());

		Date security_code_timeStamp = DateUtils.getCurrentUTCDate();
		LOGGER.info("Current UTC Time" +DateUtils.getCurrentUTCDate());
		long timeDiff = security_code_timeStamp.getTime() - account.getDateModified().getTime();
				
		long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff);

		int securityCodeTimeout = Integer.parseInt(messageSource.getMessage(SECURITY_CODE_EXPIRY_TIME, null,
				nsRequest.getLocale()));
		if (diffMinutes <= securityCodeTimeout) {
			String secureRecievedCode = MaskUtils.getDigest(nsRequest.getSecurityCode().getBytes());
			if (secureRecievedCode.equals(account.getTempPin())) {
				responseMsg = messageSource.getMessage(SECURITY_CODE_VAL_SUCCESS, null, nsRequest.getLocale());
				responseCode = Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale()));
			} else {
				responseMsg = messageSource.getMessage(SECURITY_CODE_VAL_FAIL, null, nsRequest.getLocale());
				responseCode = Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,
						nsRequest.getLocale()));
			}
		} else {
			responseCode = Integer.parseInt(messageSource.getMessage(FAILURE_RESPONSE_CODE, null,nsRequest.getLocale()));
			responseMsg = messageSource.getMessage(PIN_CODE_EXPIRED, null, nsRequest.getLocale());
		}

		nsRequest.getAccountInfo().setAccountId(account.getAccountId());
		nsRequest.getAccountInfo().setUnitNumber(
				account.getMedicalRecordNumber());
		if (accountInfo.getMobilePhoneNumber() != null) {
			nsRequest.getAccountInfo().setMobilePhoneNumber(accountInfo.getMobilePhoneNumber());
		}
		if (accountInfo.getEmail() != null) {
			nsRequest.getAccountInfo().setEmail(accountInfo.getEmail());
		}
		nsRequest.getAccountInfo().setAccountId(account.getAccountId());
		return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), responseMsg, responseCode);
	}
	@Override
	public NsResponse updateTempPin(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
			
		String pin=nsRequest.getUpdatePin();
		String secureSecurityCode = MaskUtils.getDigest(pin.getBytes());
				
		Account account=accountServiceDAO.getAccountByIdSpcl(nsRequest.getAccountInfo().getAccountId());
		account.setAuthPin(secureSecurityCode);
		//account.setTempPin(secureSecurityCode);		
		account.setDateModified(DateUtils.getCurrentUTCDate());
		LOGGER.info("Current UTC Time" +DateUtils.getCurrentUTCDate());
		accountServiceDAO.update(account);
		LOGGER.debug("Security pin code updated successfully");
		
		String email=account.getEmail();
		String name=email.substring(1,email.indexOf("@"));
		String masked=EmailUtils.maskedEmail(email, name);
		
		String successMsg=messageSource.getMessage(SUCCESS_MSG, null,null);
		
		
			sendMail.sendMail(account.getFirstName()+ " " + account.getLastName()  + ",",
					"\n\n" + messageSource.getMessage(TEXT_MSG_PIN_RESET, new String[] { String.valueOf(masked) }, nsRequest.getLocale())
						+ "\n\n", account.getEmail(), messageSource.getMessage(HOSPITAL_SUBJECT, null, nsRequest.getLocale()),
					"\n" + messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, nsRequest.getLocale()));
			System.out.println("Succes");
	
		
		
		ResponseData responseData = new ResponseData();
		responseData.setDescription(successMsg);
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		
		return nsResponse;
	}
	
	@Override
	public NsResponse updateProfileInformation(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		Account account=accountServiceDAO.getAccountByIdSpcl(nsRequest.getAccountInfo().getAccountId());
		
		Integer profileId=nsRequest.getUpdateProfileId();
		
		
                 switch(profileId)
                 {
                         
                        
                         case 1:
                        	 account.setEmail(nsRequest.getAccountInfo().getEmail());
                        	 accountServiceDAO.update(account);
                             break;
                        
                         case 2:
                        	 account.setMobilePhoneNumber(nsRequest.getAccountInfo().getMobilePhoneNumber());
                        	 accountServiceDAO.update(account);
                             break;
                        
                         case 3:
                        	 account.setAuthPin(MaskUtils.getDigest(nsRequest.getAccountInfo().getAuthPin().getBytes()));
                        	 accountServiceDAO.update(account);
                             break;
                         case 4:
                        	 account.setPushNotification(nsRequest.getAccountInfo().isPushNotification());
                        	 accountServiceDAO.update(account);
                             break;
                        	 
                         case 5:
                        	 account.setPreferredLanguage(nsRequest.getAccountInfo().getPreferredLanguage());
                        	 accountServiceDAO.update(account);
                             break;
                         case 6:
                        	 account.setWillShareData(nsRequest.getAccountInfo().isWillShareData());
                        	 accountServiceDAO.update(account);
                             break;
                         
                         
                 }
         
                 String successMsg=messageSource.getMessage(SUCCESS_MSG, null,null);
		
		
		
		
		ResponseData responseData = new ResponseData();
		responseData.setDescription(successMsg);
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		
		return nsResponse;
	}
	
	


}