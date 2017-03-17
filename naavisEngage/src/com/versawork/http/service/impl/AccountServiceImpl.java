package com.versawork.http.service.impl;

import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javapns.Push;
import javapns.notification.Payload;
import javapns.notification.PushNotificationPayload;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.gtranslate.Language;
//import com.gtranslate.Translator;
import com.versawork.http.cacheClientsInfo.ClientCacheSettingsCheck;
import com.versawork.http.cacheDataObject.Cache2Object;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.constant.AppointmentStatusCode;
import com.versawork.http.constant.CacheConstants;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.AccountServiceDAO;
import com.versawork.http.dao.ClientNaavisDatabaseServiceDAO;
import com.versawork.http.dao.GetUpcomingAppointmentsListDAO;
import com.versawork.http.dao.PatientAppointmentServiceDAO;
import com.versawork.http.dao.PatientLabServiceDao;
import com.versawork.http.dao.PatientMedicationServiceDao;
import com.versawork.http.dao.PatientVerificationServiceDAO;
import com.versawork.http.dataobject.AccountInfo;
import com.versawork.http.dataobject.AllergiesInfo;
import com.versawork.http.dataobject.FeedListInfo;
import com.versawork.http.dataobject.GCMJsonInfo;
import com.versawork.http.dataobject.LabGroups;
import com.versawork.http.dataobject.LabResults;
import com.versawork.http.dataobject.LinkedAccount;
import com.versawork.http.dataobject.MedicationSourceDates;
import com.versawork.http.dataobject.NsPatientAppointment;
import com.versawork.http.dataobject.NsPatientLabResult;
import com.versawork.http.dataobject.NsPatientPrescription;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.PatientImagingInfo;
import com.versawork.http.dataobject.PatientInpatientImmunalizationInfo;
import com.versawork.http.dataobject.PatientProblemInfo;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.dataobject.SourceInfo;
import com.versawork.http.dataobject.TestResultInfo;
import com.versawork.http.dataobject.ViewPatientInfo;
import com.versawork.http.etlCachePopulation.CachePopulation;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.AccountNotificationHistory;
import com.versawork.http.model.ClientNaavisDatabases;
import com.versawork.http.model.FeedInfo;
import com.versawork.http.model.LinkedAccountsRel;
import com.versawork.http.model.NotificationType;
import com.versawork.http.model.PatientAllergy;
import com.versawork.http.model.PatientAppointmentRequest;
import com.versawork.http.model.PatientImaging;
import com.versawork.http.model.PatientImmunization;
import com.versawork.http.model.PatientLab;
import com.versawork.http.model.PatientPrescription;
import com.versawork.http.model.PatientProblem;
import com.versawork.http.model.PatientUpcomingAppointment;
import com.versawork.http.model.PatientUpcomingAppointmentPK;
import com.versawork.http.model.PatientVerificationLog;
import com.versawork.http.model.PatientVisit;
import com.versawork.http.service.AccountService;
import com.versawork.http.service.LinkAccountService;
import com.versawork.http.utils.AllegyListComparator;
import com.versawork.http.utils.CacheKeys;
import com.versawork.http.utils.DateUtils;
import com.versawork.http.utils.GcmUtils;
import com.versawork.http.utils.ImagingResultComparator;
import com.versawork.http.utils.ImmunizationComparator;
import com.versawork.http.utils.LabComparator;
import com.versawork.http.utils.LabGroupListComparator;
import com.versawork.http.utils.LabTestListComparator;
import com.versawork.http.utils.MaskUtils;
import com.versawork.http.utils.NsPatientLabResultComparator;
import com.versawork.http.utils.NsResponseUtils;
import com.versawork.http.utils.PatientProblemListComparator;
import com.versawork.http.utils.ResponseInfoObject;
import com.versawork.http.utils.SendMail;
import com.versawork.http.utils.TestResult;
import com.versawork.http.utils.TestResultComparator;

/**
 * @author Sohaib
 * 
 */
@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	private static final long serialVersionUID = 1L;

	private static final String SUCCESS_RESPONSE_CODE = VersaWorkConstant.SUCCESS_RESPONSE_CODE;
	private static final String CLIENT_DATABASE_ID = "client.database.id";
	private static final String QUARTZ_CLIENT_DATABASE_ID = "quartz.client.database.id";
	private static final String INVALID_ENDPOINT_RESPONSE_CODE = VersaWorkConstant.INVALID_ENDPOINT_RESPONSE_CODE;
	private static final String INVALID_ENDPOINT_ID = "invalid.endpoint.id";
	private static final String UNAVAIABLE_CODE = "unavailable";
	private static final Integer APPOINTMENT_NOTIFICATION_TYPE_ID = 1;

	@Autowired
	protected ClientCacheSettingsCheck cCS;

	@Autowired
	private CacheAccessbyJson cache;

	@Autowired
	private AccountServiceDAO accountDAO;

	@Autowired
	private GetUpcomingAppointmentsListDAO getUpcomingAppointmentsListDAO;

	@Autowired
	private ClientNaavisDatabaseServiceDAO clientNaavisDatabaseDAO;

	@Autowired
	private PatientVerificationServiceDAO patientVerificationDAO;

	@Autowired
	private PatientAppointmentServiceDAO patientAppointmentDAO;

	@Autowired
	private PatientLabServiceDao patientLabServiceDao;

	@Autowired
	private PatientMedicationServiceDao patientPrescriptionServiceDao;
	
	@Autowired 
	private LinkAccountService linkAccountServiceImpl;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SendMail sendMail;

	@Autowired
	private CachePopulation cachePopulation;

	@Override
	public NsResponse emailValidation(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();

		if (isAccountExistWithSameEmail(nsRequest)) {
			throw new BusinessException("acc.exist.for.email");
		}

		responseData.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responseData.setDescription("success");
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}

	private Boolean isAccountExistWithSameEmail(NsRequest nsRequest) throws BusinessException, SystemException {
		Account account = null;
		Boolean isExist = false;
		try {
			account = accountDAO.getAccountByAccountEmailClientDBID(nsRequest.getAccountInfo().getEmail(),
					Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
			if (account.getMedicalRecordNumber().equalsIgnoreCase(nsRequest.getAccountInfo().getUnitNumber())) {
				return false;
			}
		} catch (BusinessException businessException) {
			if (businessException.getExceptionCode().equalsIgnoreCase("mul.acc.for.acc.email")) {
				isExist = true;
			}
			// Do nothing,catch exception
		}
		if (account != null) {
			isExist = true;
		}
		return isExist;
	}

	/*
	 * public Boolean isAccountExistWithSameMRNumber(String mrNumber, Integer
	 * clientDatabaseId) throws BusinessException, SystemException { Account
	 * account = null; Boolean isExist = false; try { account =
	 * accountDAO.getAccountByUnitNum(mrNumber, Integer
	 * .parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,
	 * Locale.getDefault()))); } catch (BusinessException businessException) {
	 * throw new BusinessException(); } if (account != null) { isExist = true; }
	 * return isExist; }
	 */

	public void savePatientVerificationLog(AccountInfo accountInfo, Boolean status) throws BusinessException,
			SystemException {
		ClientNaavisDatabases clientNaavisDatabases = clientNaavisDatabaseDAO.getClientNaavisDatabases(1);

		PatientVerificationLog patientVerificationLog = new PatientVerificationLog();

		patientVerificationLog.setBirthDate(DateUtils.getDate(accountInfo.getDateOfBirth(), "MM/dd/yyyy"));
		patientVerificationLog.setClientDatabaseId(clientNaavisDatabases.getClientNaavisDatabasesPK()
				.getClientDatabaseId());
		// patientVerificationLog.set(new Date());
		// patientVerificationLog.setFirstName(accountInfo.getFirstName());
		// patientVerificationLog.setLastName(accountInfo.getLastName());
		// patientVerificationLog
		// .setPatientVerificationLogPK(patientVerificationLogPK);
		// patientVerificationLog.setStatus(status);
		patientVerificationLog.setClientId(clientNaavisDatabases.getClientNaavisDatabasesPK().getClientId());
		patientVerificationLog.setDateAdded(new Date());
		// new db changes
		patientVerificationLog.setEmailAddress(accountInfo.getEmail());
		patientVerificationLog.setMedicalRecordNumber(accountInfo.getUnitNumber());
		patientVerificationLog.setPhoneNumber(accountInfo.getMobilePhoneNumber());
		patientVerificationDAO.save(patientVerificationLog);

	}

	@Override
	public NsResponse saveAccountPatientAppointment(NsRequest nsRequest) throws BusinessException, SystemException {
		int numberOfRetries = 3;
		long timeToWait = 1000;

		Account account = accountDAO.getAccountByEndpointId(
				MaskUtils.getDigest(nsRequest.getAccountInfo().getEndpointUserId().getBytes()),
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		if (account == null) {
			String responseMsg = messageSource.getMessage(INVALID_ENDPOINT_ID, null, nsRequest.getLocale());
			Integer responseCode = Integer.parseInt(messageSource.getMessage(INVALID_ENDPOINT_RESPONSE_CODE, null,
					nsRequest.getLocale()));
			return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), responseMsg, responseCode);
		}
		NsPatientAppointment nsPatientAppointment = nsRequest.getNsPatientAppointment();
		Date appointmentDate = DateUtils.getDate(
				nsPatientAppointment.getAppointmentDate() + " " + nsPatientAppointment.getAppointmentTime(),
				"MM/dd/yyyy HH:mm");
		PatientAppointmentRequest patientAppointmentRequest = new PatientAppointmentRequest();
		patientAppointmentRequest.setAppointmentDate(appointmentDate);
		patientAppointmentRequest.setAppointmentTime(DateUtils.getDate(nsPatientAppointment.getAppointmentTime(),
				"HH:mm:ss"));
		/*Translator translate = Translator.getInstance();
		LOGGER.debug("Before Translation Comments  :::::: "+nsPatientAppointment.getComment());
		String comment = "";
		switch (nsRequest.getAccountInfo().getPreferredLanguage()) {
		case "zh":
			comment = translate.translate(nsPatientAppointment.getComment(), Language.CHINESE, Language.ENGLISH);
			break;
		case "es":
			comment = translate.translate(nsPatientAppointment.getComment(), Language.SPANISH, Language.ENGLISH);
			break;
		default:
			comment = nsPatientAppointment.getComment();
			break;
		}		
		LOGGER.debug("After Translation Comments  :::::: "+comment);*/
		patientAppointmentRequest.setComment(nsPatientAppointment.getComment());
		patientAppointmentRequest.setContactEmail(nsPatientAppointment.getContactEmail());
		patientAppointmentRequest.setContactPhone(nsPatientAppointment.getContactPhone());
		patientAppointmentRequest.setDateAdded(new Date());
		patientAppointmentRequest.setProviderId(nsPatientAppointment.getProviderId());
		patientAppointmentRequest.setRequestSentDate(new Date());
		patientAppointmentRequest.setAccountId(account.getAccountId());
		patientAppointmentRequest.setIsRead(false);

		patientAppointmentRequest.setProviderName(nsPatientAppointment.getProviderName());
		patientAppointmentDAO.savePatientAppointmentRequest(patientAppointmentRequest);
		for (int i = 0; i < numberOfRetries; i++) {
			try {
				sendMail.sendMail(
						"Dear Admission Team,",
						"\nThis is a request for Appointment and below are the details:\n" + "\nAppointment Number\t: "
								+ patientAppointmentRequest.getPatientAppointmentRequestId()
								+ "\nName of the patient \t: " + account.getFirstName() + " " + account.getLastName()
								+ "\nDate and Time\t\t: "
								+ DateUtils.getFormatDate(appointmentDate, "MM/dd/yyyy hh:mm a")
								+ "\nPatient Comments\t: " + patientAppointmentRequest.getComment() + "\nPhone\t\t\t: "
								+ nsPatientAppointment.getContactPhone() + "\nEmail Id\t\t\t: "
								+ nsPatientAppointment.getContactEmail()
								+ " \n\nKindly go through the application request\n\n",
						messageSource.getMessage("hospital.appointment.mail.id", null, nsRequest.getLocale()),
						"Appointment Request", "\n" + account.getFirstName());
		
				break;
			} catch (Exception e) {
				LOGGER.error("Retrying...", e);
				try {
					Thread.sleep(timeToWait);
					LOGGER.info("Time To Wait(Number of Retries): " + timeToWait);
				} catch (InterruptedException ie) {
				}
			}
		}
		
		for (int i = 0; i < numberOfRetries; i++) {
			try {
				sendMail.sendMail("Dear "+
						account.getFirstName() + " " + account.getLastName()+",",
						"\nYour Appointment has been successfully sent and below are the details:\n"
								+ "\nAppointment Number\t: "
								+ patientAppointmentRequest.getPatientAppointmentRequestId()
								+ "\nName of the Physician\t: " + nsPatientAppointment.getProviderName()
								+ "\nDate and Time\t\t: "
								+ DateUtils.getFormatDate(appointmentDate, "MM/dd/yyyy hh:mm a") + "\nComments\t\t: "
								+ patientAppointmentRequest.getComment() + "\nProvider Email\t\t: "
								+ messageSource.getMessage("hospital.appointment.mail.id", null, nsRequest.getLocale())
								+ " \n\n", nsPatientAppointment.getContactEmail(), "Appointment Request", "\n"
								+ messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, nsRequest.getLocale()));
				break;
			} catch (Exception e) {
				LOGGER.error("Retrying...", e);
				try {
					Thread.sleep(timeToWait);
					LOGGER.info("Time To Wait(Number of Retries): " + timeToWait);
				} catch (InterruptedException ie) {
				}
			}
		}
		/*
		 * catch (Exception exp) { //exp.printStackTrace();
		 * LOGGER.error("Exception occoured while sending the mail : ", exp);
		 * throw new SystemException("act.mail.sent.error"); }
		 */
		return NsResponseUtils.getNsResponse(null,
				messageSource.getMessage("rsp.data.header.success", null, nsRequest.getLocale()),
				messageSource.getMessage("rsp.data.dscrptn.app.success", null, nsRequest.getLocale()),
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));
	}

	@Override
	public NsResponse getAccountPatientAppointment(NsRequest nsRequest) throws BusinessException, SystemException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		new Date(calendar.getTimeInMillis());

		/*
		 * Account account = accountDAO.getAccountByAuthToken(nsRequest
		 * .getAccountInfo().getAuthToken());
		 */
		Account account = accountDAO.getAccountByEndpointId(
				MaskUtils.getDigest(nsRequest.getAccountInfo().getEndpointUserId().getBytes()),
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		if (account == null) {
			String responseMsg = messageSource.getMessage(INVALID_ENDPOINT_ID, null, nsRequest.getLocale());
			Integer responseCode = Integer.parseInt(messageSource.getMessage(INVALID_ENDPOINT_RESPONSE_CODE, null,
					nsRequest.getLocale()));
			return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), responseMsg, responseCode);
		}
		List<PatientAppointmentRequest> upComingAppointmentRequests = getUpcomingAppointmentsListDAO
				.getUpcomingApptsByActId(account.getAccountId());

		// List<PatientAppointmentRequest> patientAppointmentRequests =
		// account.getPatientAppointmentRequestList();
		LOGGER.debug("No. of confirmed appoinments" + upComingAppointmentRequests.size());
		if (upComingAppointmentRequests.isEmpty() || upComingAppointmentRequests.size() == 0) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage("no.patnt.appntmnt", null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}
		List<NsPatientAppointment> nsPatientAppointments = new ArrayList<NsPatientAppointment>();
		NsResponse nsResponse = NsResponseUtils.getNsResponse(
				null,
				messageSource.getMessage("rsp.data.header.success", null, nsRequest.getLocale()),
				messageSource.getMessage("rsp.data.dscrptn.success", null, nsRequest.getLocale()),
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));
		nsResponse.setNsPatientAppointments(nsPatientAppointments);
		return nsResponse;
	}

	@Override
	public NsResponse resetAccountPin(NsRequest nsRequest) throws BusinessException, SystemException {
		if (nsRequest.getNsPin().getOldPin().trim().length() == 0) {
			throw new BusinessException("no.old.pwd");
		}
		if (nsRequest.getNsPin().getNewPin().trim().length() == 0) {
			throw new BusinessException("no.new.pwd");
		}
		Account account = accountDAO.getAccountByEndpointId(
				MaskUtils.getDigest(nsRequest.getAccountInfo().getEndpointUserId().getBytes()),
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		if (account == null) {
			String responseMsg = messageSource.getMessage(INVALID_ENDPOINT_ID, null, nsRequest.getLocale());
			Integer responseCode = Integer.parseInt(messageSource.getMessage(INVALID_ENDPOINT_RESPONSE_CODE, null,
					nsRequest.getLocale()));
			return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), responseMsg, responseCode);
		}
		if (account.getAuthPin().equalsIgnoreCase(MaskUtils.getDigest(nsRequest.getNsPin().getOldPin().getBytes()))) {
			account.setAuthPin(MaskUtils.getDigest(nsRequest.getNsPin().getNewPin().getBytes()));
		} else {
			throw new BusinessException("old.pswrd.not.match");
		}
		return NsResponseUtils.getNsResponse(
				null,
				messageSource.getMessage("pswrd.updtd", null, nsRequest.getLocale()),
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));
	}

	@Override
	public NsResponse getPatientLabResult(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse =null;
		Integer clientDBId = Integer.parseInt(messageSource.getMessage("client.database.id", null, Locale.getDefault()));
		
		Integer accountId = nsRequest.getAccountInfo().getAccountId();
		List<NsPatientLabResult> patientLabResults = new ArrayList<NsPatientLabResult>();
		List<NsPatientLabResult> tempPatientLabResults = new ArrayList<NsPatientLabResult>();
		List<LinkedAccountsRel> linkedAccounts = linkAccountServiceImpl.getLinkedAccounts(accountId);
		
		tempPatientLabResults = fetchAccountLabResults(clientDBId,accountId);
		patientLabResults.addAll(tempPatientLabResults);
		
		for(LinkedAccountsRel linkedAccountsRel:linkedAccounts){
			try{
				LOGGER.debug("Fetching lab result for account: "+linkedAccountsRel);
				tempPatientLabResults = fetchAccountLabResults(linkedAccountsRel.getLinkedAccountClientdbId(),linkedAccountsRel.getLinkedAccountId());
				LOGGER.debug("Lab results of "+linkedAccountsRel+" are: "+tempPatientLabResults.size());
				patientLabResults.addAll(tempPatientLabResults);
			}catch(Exception e){
				LOGGER.debug("No Patient lab result found for linkAccount : "+linkedAccountsRel);
			}
		}
		
		if(patientLabResults.size()==0 || patientLabResults.isEmpty() )		{
			return NsResponseUtils.getNsResponse(null,messageSource.getMessage("no.lab.result.found", null, nsRequest.getLocale()),VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}else{
			nsResponse = NsResponseUtils.getNsResponse(null,messageSource.getMessage("rsp.data.dscrptn.success", null, nsRequest.getLocale()),Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,nsRequest.getLocale())));
			nsResponse.setPatientLabResults(patientLabResults);
		}
		return nsResponse;
	}

	
	private List<NsPatientLabResult> fetchAccountLabResults(Integer clientDbId,Integer accountId) throws BusinessException,SystemException {

		List<NsPatientLabResult> patientLabResults = null;
		Boolean isCachingOn = cCS.isCachingOn(CacheConstants.PATIENT_LAB.getCode());
		Type type = new TypeToken<List<NsPatientLabResult>>() {
		}.getType();
		String key = CacheKeys.getPatientLabKey(clientDbId, accountId);
		Cache2Object<NsPatientLabResult> obj = new Cache2Object<NsPatientLabResult>(cCS, cache);

		patientLabResults = obj.getObject(key, isCachingOn, type);
		LOGGER.debug("isCachingOn : " + isCachingOn + " key" + key);

		if (patientLabResults == null || patientLabResults.isEmpty()) {
			patientLabResults = new ArrayList<NsPatientLabResult>();
			List<PatientLab> patientLabs = patientLabServiceDao.getPatientLab(accountId);
			if(patientLabs.size()!=0 && !patientLabs.isEmpty())
			{
				NsPatientLabResult nsPatientLabResult = null;
				for (PatientLab patientLab : patientLabs) {
					nsPatientLabResult = ResponseInfoObject.getNsPatientLabResult(patientLab);
					patientLabResults.add(nsPatientLabResult);
				}

			
			obj.setObject(key, isCachingOn, patientLabResults, type);
			}
		}
		return patientLabResults;
	}

	@Override
	public NsResponse getPatientPrescription(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = null;
		Integer clientDBId = Integer.parseInt(messageSource.getMessage("client.database.id", null, Locale.getDefault()));
		Integer accountId = nsRequest.getAccountInfo().getAccountId();
		List<NsPatientPrescription> nsPatientPrescriptions = new ArrayList<NsPatientPrescription>();
		List<NsPatientPrescription> tempPatientPrescriptions = new ArrayList<NsPatientPrescription>();
		List<LinkedAccountsRel>  linkedAccounts = linkAccountServiceImpl.getLinkedAccounts(accountId);
		
		tempPatientPrescriptions = fetchAccountPrescription(clientDBId,accountId);
		nsPatientPrescriptions.addAll(tempPatientPrescriptions);
		
		for(LinkedAccountsRel linkedAccountsRel:linkedAccounts)
		{
			try{
			LOGGER.debug("Fetching patient prescription for account: "+linkedAccountsRel);
			tempPatientPrescriptions = fetchAccountPrescription(linkedAccountsRel.getLinkedAccountClientdbId(),linkedAccountsRel.getLinkedAccountId());
			LOGGER.debug("Medicines/Prescription of "+linkedAccountsRel+" are: "+tempPatientPrescriptions.size());
			nsPatientPrescriptions.addAll(tempPatientPrescriptions);
			}catch(Exception e){
				LOGGER.debug("No Patient prescription found for linkAccount : "+linkedAccountsRel);
			}
		}

		if (nsPatientPrescriptions.isEmpty()) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage("no.prscrptn.result", null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}
		
		nsResponse = NsResponseUtils.getNsResponse(
				null,
				messageSource.getMessage("rsp.data.dscrptn.success", null, nsRequest.getLocale()),
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));
		nsResponse.setPatientPrescriptions(nsPatientPrescriptions);

		return nsResponse;
	}

	private List<NsPatientPrescription> fetchAccountPrescription(Integer clientDBId, Integer accountId) throws BusinessException,SystemException{
		List<PatientPrescription> patientPrescriptions = new ArrayList<PatientPrescription>();
		List<NsPatientPrescription> nsPatientPrescriptions = null;
		Boolean isCachingOn = cCS.isCachingOn(CacheConstants.PATIENT_MEDICATION.getCode());
		Type type = new TypeToken<List<NsPatientPrescription>>() {
		}.getType();
		String key = null;

		key = CacheKeys.getPatientPrescriptionKey(clientDBId, accountId);
		Cache2Object<NsPatientPrescription> obj = new Cache2Object<NsPatientPrescription>(cCS, cache);

		nsPatientPrescriptions = obj.getObject(key, isCachingOn, type);
		LOGGER.debug("isCachingOn : " + isCachingOn + " key" + key);

		if (nsPatientPrescriptions == null || nsPatientPrescriptions.isEmpty()) {
			nsPatientPrescriptions = new ArrayList<NsPatientPrescription>();
			patientPrescriptions = patientPrescriptionServiceDao.getPatientPrescription(accountId);
			NsPatientPrescription nsPatientPrescription;
			for (PatientPrescription patientmedication : patientPrescriptions) {
				nsPatientPrescription = ResponseInfoObject.getNsPatientPrescription(patientmedication);
				nsPatientPrescriptions.add(nsPatientPrescription);

			}
			if (isCachingOn) {
				LOGGER.debug("setting data into list " + nsPatientPrescriptions.size());
				obj.setObject(key, isCachingOn, nsPatientPrescriptions, type);
			}
		}
		
		return nsPatientPrescriptions;
		
	}

	@Override
	public NsResponse getPatientPrescriptionGroups(NsRequest nsRequest) throws BusinessException, SystemException {
		ResponseData responseData = new ResponseData();
		NsResponse nsResponse = new NsResponse();
		try {
			List<NsPatientPrescription> nsPatientPrescList = getPatientPrescription(nsRequest)
					.getPatientPrescriptions();

			if (nsPatientPrescList == null || nsPatientPrescList.isEmpty()) {
				LOGGER.debug("nsPatientPrescList size is empty");
				String info = messageSource.getMessage("no.medication.found", null, nsRequest.getLocale());
				return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), info,
						VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
			}
			LOGGER.debug("nsPatientPrescList size more that one" + nsPatientPrescList.size());
			Set<String> sourceNames = new HashSet<String>();
			Set<String> prescriptionActions = new HashSet<String>();
			
				Iterator<NsPatientPrescription> iter = nsPatientPrescList.iterator();
				while (iter.hasNext()) {
					NsPatientPrescription nsP = iter.next();
					sourceNames.add(nsP.getSourceName());
					prescriptionActions.add(nsP.getPrescriptionAction());
				}
			
			LOGGER.debug("prescriptionActions size" + prescriptionActions.size() + "  " + sourceNames.size());

			Map<String, List<NsPatientPrescription>> mapActionWise = new LinkedHashMap<String, List<NsPatientPrescription>>();
			Map<String, Map<String, List<NsPatientPrescription>>> dummyMap = new LinkedHashMap<String, Map<String, List<NsPatientPrescription>>>();
			Map<String, Map<String, List<NsPatientPrescription>>> mapSourceWise = new LinkedHashMap<String, Map<String, List<NsPatientPrescription>>>();
			Iterator<String> iterSrcName = sourceNames.iterator();
			List<MedicationSourceDates> medicationSourceDatesList = new ArrayList<MedicationSourceDates>();
			while (iterSrcName.hasNext()) {
				String source = iterSrcName.next();
				mapActionWise = new LinkedHashMap<String, List<NsPatientPrescription>>();
				Iterator<NsPatientPrescription> prescIter = nsPatientPrescList.iterator();
				List<NsPatientPrescription> tempPrescList = new ArrayList<NsPatientPrescription>();
				Integer count = 0;
				Date asOfDate = null;
				MedicationSourceDates medicationSourceDates = new MedicationSourceDates();
				while (prescIter.hasNext()) {
					NsPatientPrescription nsP = prescIter.next();
					if (nsP.getSourceName().equalsIgnoreCase(source)) {
						tempPrescList.add(nsP);
						if (count == 0) {
							if (nsP.getEncounterEndDate() != null)
								asOfDate = nsP.getEncounterEndDate();
							else
								asOfDate = nsP.getEncounterStartDate();
							count = 1;
						} else {
							if (nsP.getEncounterEndDate() != null && nsP.getEncounterEndDate().after(asOfDate))
								asOfDate = nsP.getEncounterEndDate();
							else if (nsP.getEncounterStartDate() != null && nsP.getEncounterStartDate().after(asOfDate))
								asOfDate = nsP.getEncounterStartDate();
						}
					}
				}
				medicationSourceDates.setAsOfDate(DateUtils.getFormatDate(asOfDate, "MM/dd/yyyy"));
				medicationSourceDates.setSourceName(source);
				medicationSourceDatesList.add(medicationSourceDates);
				List<String> sortedList = prescriptionActionListSorter(prescriptionActions);
				Iterator<String> prescActionIter = sortedList.iterator();

				LOGGER.debug("tempPrescList  size " + tempPrescList.size());
				while (prescActionIter.hasNext()) {

					String prescAction = prescActionIter.next();
					if (tempPrescList.size() > 0) {
						Iterator<NsPatientPrescription> prescTempIter = tempPrescList.iterator();
						while (prescTempIter.hasNext()) {

							NsPatientPrescription nsPatPres = prescTempIter.next();
							if (nsPatPres.getPrescriptionAction().equalsIgnoreCase(prescAction)) {
								if (mapActionWise.containsKey(prescAction)) {
									List<NsPatientPrescription> nsPatList = mapActionWise.get(prescAction);
									nsPatList.add(nsPatPres);
									mapActionWise.put(prescAction, nsPatList);
								} else {
									List<NsPatientPrescription> nsPatList = new ArrayList<NsPatientPrescription>();
									nsPatList.add(nsPatPres);
									mapActionWise.put(prescAction, nsPatList);
								}
							}
							LOGGER.debug("Source" + source + " mapActionWise" + mapActionWise);
							dummyMap.put(source, mapActionWise);
							// mapSourceWise.put(source,dummyMap);
						}
					}
				}
				mapSourceWise.put(source, dummyMap.get(source));
				mapActionWise = new LinkedHashMap<String, List<NsPatientPrescription>>();
				dummyMap = new LinkedHashMap<String, Map<String, List<NsPatientPrescription>>>();

			}

			responseData.setDescription("Success");
			responseData.setResult(0);
			nsResponse.setPatientPrescriptionMap(mapSourceWise);
			nsResponse.setMedicationSourceAsOfDate(medicationSourceDatesList);
			nsResponse.setResponseData(responseData);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setDescription("No records found");
			responseData.setResult(20);

		}
		return nsResponse;

	}

	/*
	 * @Override public NsResponse validateUserAccount(NsRequest nsRequest)
	 * throws BusinessException, SystemException { NsResponse nsResponse = new
	 * NsResponse(); try{ AccountInfo accountInfo = nsRequest.getAccountInfo();
	 * Account account =
	 * accountDAO.getAccountByAccountEmailId(accountInfo.getEmail());
	 * 
	 * if (accountInfo.getUnitNumber() != null &&
	 * !accountInfo.getUnitNumber().isEmpty()) { if
	 * (!account.getUnitNumber().equalsIgnoreCase(accountInfo.getUnitNumber()))
	 * { throw new
	 * BusinessException("rsp.data.dscrptn.unitnumber.not.verified"); } } if
	 * (accountInfo.getUniqueId() != null &&
	 * !accountInfo.getUniqueId().isEmpty()) { if
	 * (!account.getUniqueId().equalsIgnoreCase(accountInfo.getUniqueId())) {
	 * throw new BusinessException("rsp.data.dscrptn.unique.not.verified"); } }
	 * ResponseData responseData = new ResponseData();
	 * responseData.setResult(Integer.parseInt(messageSource.getMessage(
	 * VersaWorkConstant.SUCCESS_RESPONSE_CODE, null, nsRequest.getLocale())));
	 * responseData.setDescription("success");
	 * nsResponse.setResponseData(responseData); return nsResponse; } catch
	 * (Exception exp) { // exp.printStackTrace(); LOGGER.error(
	 * "Exception occoured on AccountServiceImpl (validateUserAccount) : ",
	 * exp); throw new BusinessException(exp.getMessage()); } }
	 */
	@Override
	public NsResponse updateAccountPhone(NsRequest nsRequest) throws BusinessException, SystemException {
		String responseMsg = null;
		Integer responseCode;
		if (nsRequest.getAccountInfo().getMobilePhoneNumber().trim().length() == 0) {
			throw new BusinessException();
		}

		Account account = accountDAO.getAccountByAccountEmailClientDBID(nsRequest.getAccountInfo().getEmail(),
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		if (account == null) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage("rsp.data.dscrptn.email.not.match", null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}
		account.setMobilePhoneNumber(nsRequest.getAccountInfo().getMobilePhoneNumber());
		accountDAO.update(account);
		getAccountInfo(account, nsRequest.getAccountInfo());
		responseMsg = messageSource.getMessage("phone.updtd", null, nsRequest.getLocale());
		responseCode = Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null, nsRequest.getLocale()));
		// nsRequest.getAccountInfo().setAccountId(account.getAccountId());
		return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), responseMsg, responseCode);

	}

	@Override
	public NsResponse updateAccountPin(NsRequest nsRequest) throws BusinessException, SystemException {
		if (nsRequest.getNsPin().getNewPin().trim().length() == 0) {
			throw new BusinessException("no.new.pwd");
		}

		Account account = accountDAO.getAccountByEndpointId(
				MaskUtils.getDigest(nsRequest.getAccountInfo().getEndpointUserId().getBytes()),
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		account.setAuthPin(MaskUtils.getDigest(nsRequest.getNsPin().getNewPin().getBytes()));
		accountDAO.update(account);
		getAccountInfo(account, nsRequest.getAccountInfo());
		try {
			sendMail.sendMail("Dear "+account.getFirstName() + " " + account.getLastName()+",",
					"\n\n" + messageSource.getMessage("engage.pin.updt.mail", null, nsRequest.getLocale()) + "\n\n",
					account.getEmail(), "Engage PIN Update", "\n"+messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE, null, Locale.getDefault()));
		} catch (Exception exp) {
			exp.printStackTrace();
			LOGGER.error("Exception occoured while sending the mail (update account PIN): ", exp);
			throw new BusinessException(exp.getMessage());
		}
		NsResponse nsResponse = NsResponseUtils.getNsResponse(
				nsRequest.getAccountInfo(),
				messageSource.getMessage("pswrd.updtd", null, nsRequest.getLocale()),
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));
		// nsResponse.setAccountInfo(); 
		return nsResponse;

	}

	@Override
	public NsResponse appointmentConfirmation(NsRequest nsRequest) throws BusinessException, Exception {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();

		PatientAppointmentRequest patientAppointmentRequest = patientAppointmentDAO.getAppointmtIdForConfrmtn(nsRequest
				.getNsHospitalConfirmation().getAppoinmentID());
		/*
		 * if(patientAppointmentRequest.getConfirmDate()!=null ||
		 * patientAppointmentRequest.getConfirmedBy()!=null) { throw new
		 * BusinessException("appointment.alredy.confrmd"); }
		 * 
		 * patientAppointmentRequest.setConfirmDate(DateUtils.getDate(nsRequest
		 * . getNsHospitalConfirmation().getConfirmDate(),"MM/dd/yyyy HH:mm:ss"
		 * )); patientAppointmentRequest.setConfirmedBy(nsRequest.
		 * getNsHospitalConfirmation().getConfirmBy());
		 */
		String confirmDate = "";
		try {
			confirmDate = DateUtils.getFormatDate(
					DateUtils.getDate(nsRequest.getNsHospitalConfirmation().getConfirmDate(), "MM/dd/yyyy HH:mm:ss"),
					"MM/dd/yyyy HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("Failed to set confirm date for appointment");
		}
		patientAppointmentDAO.update(patientAppointmentRequest);
		Account account = accountDAO.getAccountById(patientAppointmentRequest.getAccountId());
		String message = "Your appointment has been confirmed on- " + confirmDate + "\nConfirmed by- "
				+ nsRequest.getNsHospitalConfirmation().getConfirmBy() + "\nYour appointment ID is- "
				+ nsRequest.getNsHospitalConfirmation().getAppoinmentID();
		// Sending Apple Notification to phone of Confirmation

		// KeyStore keystore = KeyStore.getInstance("PKCS12");

		KeyStore keystore = KeyStore.getInstance(VersaWorkConstant.KEY_STORE_TYPE);
		// FileInputStream fIn = new FileInputStream("Certificates.p12");
		// keystore.load(this.getClass().getResourceAsStream("/Certificates.p12"),
		// password);
		String certName = messageSource.getMessage(VersaWorkConstant.IOS_NOTIFICATION_CERTIFICATE, null,Locale.getDefault());
		LOGGER.debug("Certificate Name of Push Notification sent on IOS :"+certName);
		char[] password = messageSource.getMessage(VersaWorkConstant.IOS_NOTIFICATION_CERTIFICATE_PASSWORD, null,Locale.getDefault()).toCharArray();
		keystore.load(
				this.getClass().getClassLoader()
						.getResourceAsStream(certName), password);
		Push.alert(message, keystore, messageSource.getMessage(VersaWorkConstant.IOS_NOTIFICATION_CERTIFICATE_PASSWORD, null,Locale.getDefault()),
				Boolean.parseBoolean(messageSource.getMessage(VersaWorkConstant.APNS_DISTRIBUTION, null,Locale.getDefault())), account
				.getDeviceToken().trim());
		
		
		/*if (messageSource.getMessage("navis.environment", null, nsRequest.getLocale()).equalsIgnoreCase("prod")) {
			char[] password = VersaWorkConstant.PROD_APPLE_NOTIFICATION_CERTIFICATE_PASSWORD.toCharArray();
			keystore.load(
					this.getClass().getClassLoader()
							.getResourceAsStream(VersaWorkConstant.PROD_APPLE_NOTIFICATION_CERTIFICATE), password);
			Push.alert(message, keystore, VersaWorkConstant.PROD_APPLE_NOTIFICATION_CERTIFICATE_PASSWORD, true, account
					.getDeviceToken().trim());
		} else if (messageSource.getMessage("navis.environment", null, nsRequest.getLocale()).equalsIgnoreCase("dev")) {
			char[] password = VersaWorkConstant.DEV_APPLE_NOTIFICATION_CERTIFICATE_PASSWORD.toCharArray();
			keystore.load(
					this.getClass().getClassLoader()
							.getResourceAsStream(VersaWorkConstant.DEV_APPLE_NOTIFICATION_CERTIFICATE), password);
			Push.alert(message, keystore, VersaWorkConstant.DEV_APPLE_NOTIFICATION_CERTIFICATE_PASSWORD, true, account
					.getDeviceToken().trim());
		}*/
		responseData.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responseData.setDescription("success");
		nsResponse.setResponseData(responseData);

		return nsResponse;

	}

	@Override
	public NsResponse appointmentConfirmationThread() throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		String dbId[] = messageSource.getMessage(QUARTZ_CLIENT_DATABASE_ID, null, Locale.getDefault()).split(",");
		for(String clientDb:dbId){
			List<PatientUpcomingAppointment> patientUpcomingAppointmentsList = patientAppointmentDAO
					.getNewConfirmedAppointments(Integer.parseInt(clientDb));
			if (!patientUpcomingAppointmentsList.isEmpty()) {
				Iterator<PatientUpcomingAppointment> iter = patientUpcomingAppointmentsList.iterator();
				Integer accountId = 0;
				Account account = new Account();
				while (iter.hasNext()) {
					PatientUpcomingAppointment patientUpcomingAppointments = iter.next();
					int patientUpcomingAppointmentsAccountId = patientUpcomingAppointments
							.getPatientUpcomingAppointmentPK().getAccountId();
					if (!(patientUpcomingAppointmentsAccountId == accountId)) {
						accountId = patientUpcomingAppointmentsAccountId;
						try {
							account = accountDAO.getAccountById(accountId);
						} catch (Exception e) {
							account = null;
						}
					}
					if (account != null && account.getPushNotification()== true) {
						try {
							LOGGER.info("Sending reminder/notification for an appointement for Account :  " + accountId);
							nsResponse = upcomingAppointmentConfirmation(patientUpcomingAppointments, account, clientDb);
							patientUpcomingAppointments.setStatus(new Date());
							patientAppointmentDAO.savePatientUpcomingAppointments(patientUpcomingAppointments);
						} catch (Exception e) {
							e.printStackTrace();
							LOGGER.debug("Unable to complete request for appointment confirmation");
						}
					}
				}
			} else {
				nsResponse = null;
			}
		}
		

		return nsResponse;

	}

	@Override
	public NsResponse pushNotificationThread() throws BusinessException, Exception {
		NsResponse nsResponse = new NsResponse();
		List<AccountNotificationHistory> accountNotificationHistoryList = null;

		Date presentDay = new Date();
		Date currentPlusTwo = addDays(presentDay,
				Integer.parseInt(messageSource.getMessage("confirmed.reminder.days", null, Locale.getDefault()))); // remind
																													// patients
																													// for
																													// their
																													// appointment
																													// confirmation
																													// 2
																													// days
																													// prior
		String dbId[] = messageSource.getMessage(QUARTZ_CLIENT_DATABASE_ID, null, Locale.getDefault()).split(",");
		
		for(String clientDb:dbId){
			accountNotificationHistoryList = patientAppointmentDAO.getNotificationHistory(
					Integer.parseInt(clientDb), presentDay,
					currentPlusTwo, APPOINTMENT_NOTIFICATION_TYPE_ID);

			Iterator<AccountNotificationHistory> iter = accountNotificationHistoryList.iterator();
			while (iter.hasNext()) {
				AccountNotificationHistory accountNotificationHistory = iter.next();

				Account account = accountDAO.getAccountById(accountNotificationHistory.getAccountId());
				PatientUpcomingAppointment patientUpcomingAppointments = patientAppointmentDAO
						.getConfirmedAppointmentsById(accountNotificationHistory.getEffectiveModuleId(),
								accountNotificationHistory.getAccountId());
				upcomingAppointmentConfirmation(patientUpcomingAppointments, account, clientDb);
			}
		}
		
		return nsResponse;
	}

	@Override
	public NsResponse upcomingAppointmentConfirmation(PatientUpcomingAppointment patientUpcomingAppointments,
			Account account, String clientDb) throws BusinessException, Exception {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		PushNotificationPayload payload = new PushNotificationPayload();
		Gson gson = new Gson();
		GCMJsonInfo gCMJsonInfo = new GCMJsonInfo();
		String message = "";
		String appointmentStatus = "";
		switch (patientUpcomingAppointments.getStatusCode()) {
		case 1:
			message = messageSource.getMessage("booked.status.message",new String[] {
					patientUpcomingAppointments.getTypeDescription(),
					DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentDate(), "MMMM d, yyyy"),
					DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentTime(),VersaWorkConstant.TIME_FORMAT_12_HOUR) },
					Locale.getDefault());
			appointmentStatus = "Confirmed";
			break;
		case 2:
			message = messageSource.getMessage("attended.status.message",new String[] {
					patientUpcomingAppointments.getTypeDescription(),
					DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentDate(), "MMMM d, yyyy"),
					DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentTime(),VersaWorkConstant.TIME_FORMAT_12_HOUR) },
					Locale.getDefault());
			appointmentStatus = "Attended";		
			break;
		case 3:
			message = messageSource.getMessage("pending.status.message",new String[] {
					patientUpcomingAppointments.getTypeDescription(),
					DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentDate(), "MMMM d, yyyy"),
					DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentTime(),VersaWorkConstant.TIME_FORMAT_12_HOUR) },
					Locale.getDefault());
			appointmentStatus = "Pending";
			break;
		case 4:
			message = messageSource.getMessage("noshow.status.message",new String[] {
					patientUpcomingAppointments.getTypeDescription(),
					DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentDate(), "MMMM d, yyyy"),
					DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentTime(),VersaWorkConstant.TIME_FORMAT_12_HOUR) },
					Locale.getDefault());
			appointmentStatus = "No Show";
			break;
		case 5:
			message = messageSource.getMessage("cancelled.status.message",new String[] {
					patientUpcomingAppointments.getTypeDescription(),
					DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentDate(), "MMMM d, yyyy"),
					DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentTime(),VersaWorkConstant.TIME_FORMAT_12_HOUR) },
					Locale.getDefault());
			appointmentStatus = "Cancelled";			
			break;
		case 6:
			message = messageSource.getMessage("rescheduled.status.message",new String[] {
					patientUpcomingAppointments.getTypeDescription(),
					DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentDate(), "MMMM d, yyyy"),
					DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentTime(),VersaWorkConstant.TIME_FORMAT_12_HOUR) },
					Locale.getDefault());
			appointmentStatus = "Re-Scheduled";
			break;
		default:
			
			break;
		}	
		payload.addAlert(message);
		payload.addCustomDictionary("sID", patientUpcomingAppointments.getStatusCode());
		payload.addCustomDictionary("aID", patientUpcomingAppointments.getPatientUpcomingAppointmentPK()
				.getAppointmentId());
		payload.addCustomDictionary("aDate",
				(DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentDate(), "MM/dd/yyyy")));
		payload.addCustomDictionary("aTime",
				(DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentTime(), "HH:mm")));
		payload.addCustomDictionary("byDoctor", patientUpcomingAppointments.getProviderName());

		gCMJsonInfo.setMessage(message);
		LOGGER.debug("gCMJsonInfo : " + message);
		gCMJsonInfo.setsID(patientUpcomingAppointments.getStatusCode());
		gCMJsonInfo.setaID(patientUpcomingAppointments.getPatientUpcomingAppointmentPK().getAppointmentId());
		gCMJsonInfo.setaDate((DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentDate(), "MM/dd/yyyy")));
		gCMJsonInfo.setaTime((DateUtils.getFormatDate(patientUpcomingAppointments.getAppointmentTime(), "HH:mm:ss")));
		gCMJsonInfo.setByDoctor(patientUpcomingAppointments.getProviderName());
		String requestJsonGcm = gson.toJson(gCMJsonInfo);
		LOGGER.debug("gCMJsonInfo :  " + requestJsonGcm);
		LOGGER.debug("Sending Email Notification to :" + account.getEmail());		
		try{
			apnConnection(payload, requestJsonGcm, account.getDeviceType(),account.getDeviceToken(),clientDb);
		}catch(Exception exception){
			LOGGER.error("Error while APN Connection   "+ExceptionUtils.getFullStackTrace(exception));
			try{
				apnConnection(payload, requestJsonGcm, account.getDeviceType(),account.getDeviceToken(),clientDb);
			}catch(Exception e){
				LOGGER.error("Error while APN Connection Retry   "+ExceptionUtils.getFullStackTrace(e));
			}
		}
		try{
		sendMail.sendMail("Dear "+account.getFirstName() + " " + account.getLastName()+",", "\n\n" + message + " \n\n",
				account.getEmail(), "Appointment " + appointmentStatus,
				"\n" + messageSource.getMessage(VersaWorkConstant.HOSPITAL_SIGNATURE+"."+clientDb, null, Locale.getDefault()));
		}catch(Exception e){
			LOGGER.debug("Unable to send email. Please try again later..... ");
		}
		LOGGER.debug("Logging information onto AccountNotificationHistory when sending Notification ");
		responseData.setResult(VersaWorkConstant.RESPONSE_DATA_RESULT_SUCCESS);
		responseData.setDescription("success");
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}
	
	public void apnConnection(Payload payload, String requestJsonGcm, String deviceType, String deviceToken, String clientDb) throws Exception{
		if (deviceType.equalsIgnoreCase("I")) {
			LOGGER.debug("Sending APNS for IPHONE");
			KeyStore keystore = KeyStore.getInstance(VersaWorkConstant.KEY_STORE_TYPE);
			String certName = messageSource.getMessage(VersaWorkConstant.IOS_NOTIFICATION_CERTIFICATE+"."+clientDb, null,Locale.getDefault());
			LOGGER.debug("Certificate Name of Push Notification sent on IOS :"+certName);
			
			
			char[] password = messageSource.getMessage(VersaWorkConstant.IOS_NOTIFICATION_CERTIFICATE_PASSWORD+"."+clientDb, null,Locale.getDefault()).toCharArray();
			//FileInputStream notificationCertStream = new FileInputStream(System.getProperty("jboss.server.home.dir") + "\\conf\\" + certName);			
			//keystore.load(notificationCertStream, password);
			keystore.load(this.getClass().getClassLoader().getResourceAsStream(certName), password);
			Push.payload(payload, keystore, messageSource.getMessage(VersaWorkConstant.IOS_NOTIFICATION_CERTIFICATE_PASSWORD+"."+clientDb, null,Locale.getDefault()),
					Boolean.parseBoolean(messageSource.getMessage(VersaWorkConstant.APNS_DISTRIBUTION, null,Locale.getDefault())),
					deviceToken.trim());
			
		} else if (deviceType.equalsIgnoreCase("A")) {
			LOGGER.debug("Sending ANDROID NOTIFICATION");
			GcmUtils gcmUtils = new GcmUtils();
			gcmUtils.sendGcmMessage(messageSource.getMessage("gcm.api.key", null, Locale.getDefault()), deviceToken.trim(), requestJsonGcm);
			LOGGER.debug("Sent ANDROID NOTIFICATION");

		}
	}

	public AccountNotificationHistory saveAccountNotificationHistory(
			PatientUpcomingAppointment patientUpcomingAppointment, Account account, String message,
			Integer notificationId) {
		AccountNotificationHistory accountNotificationHistory = new AccountNotificationHistory();
		try {

			accountNotificationHistory.setAccountId(account.getAccountId());
			accountNotificationHistory.setNotificationTypeId(new NotificationType(1));
			accountNotificationHistory.setEffectiveModuleId(patientUpcomingAppointment
					.getPatientUpcomingAppointmentPK().getAppointmentId());
			accountNotificationHistory.setIsviewedFlag(false);
			//accountNotificationHistory.setNotificationMessage(message);
			accountNotificationHistory.setClientDatabaseId(Integer.parseInt(messageSource.getMessage(
					CLIENT_DATABASE_ID, null, Locale.getDefault())));
			//accountNotificationHistory.setEffectiveModuleDate(patientUpcomingAppointment.getAppointmentDate());
			accountNotificationHistory.setDateAdded(new Date());
			accountDAO.save(accountNotificationHistory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountNotificationHistory;

	}

	/*
	 * @Override public NsResponse getConfirmationAppointment(HttpServletRequest
	 * request) throws BusinessException, SystemException { NsResponse
	 * nsResponse = new NsResponse(); ResponseData responseData = new
	 * ResponseData();
	 * 
	 * try { if (request.getParameter("confirmBy").trim().length() == 0) { throw
	 * new BusinessException("confby.feild.notEmpty"); }
	 * PatientAppointmentRequest patientAppointmentRequest =
	 * patientAppointmentDAO
	 * .getAppointmtReqForConfrmtn(Integer.parseInt(request
	 * .getParameter("appointmentId"))); // new DB CHANges
	 * 
	 * if (patientAppointmentRequest.getConfirmDate() != null ||
	 * patientAppointmentRequest.getConfirmedBy() != null) { throw new
	 * BusinessException("appointment.alredy.confrmd"); }
	 * 
	 * patientAppointmentRequest .setConfirmDate(DateUtils.getDate(
	 * request.getParameter("confirmDate"), "MM/dd/yyyy HH:mm:ss"));
	 * patientAppointmentRequest.setConfirmedBy(request
	 * .getParameter("confirmBy"));
	 * 
	 * 
	 * String confirmDate =
	 * DateUtils.getFormatDate(DateUtils.getDate(request.getParameter
	 * ("confirmDate"), "MM/dd/yyyy HH:mm"), "MM/dd/yyyy HH:mm:ss");
	 * 
	 * patientAppointmentDAO.update(patientAppointmentRequest);
	 * 
	 * Account account = accountDAO
	 * .getAccountById(patientAppointmentRequest.getAccountId()); String message
	 * = "Your Appointment has been Confirmed on- " + confirmDate +
	 * "\nConfirmed by- " + request.getParameter("confirmBy") +
	 * "\nYour Appointment Id is- " + request.getParameter("appointmentId"); if
	 * (account.getDeviceType().equalsIgnoreCase("I")) { KeyStore keystore =
	 * KeyStore .getInstance(VersaWorkConstant.KEY_STORE_TYPE); if
	 * (messageSource.getMessage("navis.environment", null,
	 * nsRequest.getLocale()).equalsIgnoreCase("prod")) { char[] password =
	 * VersaWorkConstant.PROD_APPLE_NOTIFICATION_CERTIFICATE_PASSWORD
	 * .toCharArray(); FileInputStream notificationCertStream = new
	 * FileInputStream( System.getProperty("jboss.server.home.dir") + "\\conf\\"
	 * + VersaWorkConstant.PROD_APPLE_NOTIFICATION_CERTIFICATE);
	 * keystore.load(notificationCertStream, password); Push.alert( message,
	 * keystore, VersaWorkConstant.PROD_APPLE_NOTIFICATION_CERTIFICATE_PASSWORD,
	 * true, account.getDeviceToken().trim()); } else if
	 * (messageSource.getMessage("navis.environment", null,
	 * nsRequest.getLocale()).equalsIgnoreCase("dev")) { char[] password =
	 * VersaWorkConstant.DEV_APPLE_NOTIFICATION_CERTIFICATE_PASSWORD
	 * .toCharArray(); FileInputStream notificationCertStream = new
	 * FileInputStream( System.getProperty("jboss.server.home.dir") + "\\conf\\"
	 * + VersaWorkConstant.DEV_APPLE_NOTIFICATION_CERTIFICATE);
	 * keystore.load(notificationCertStream, password); Push.alert( message,
	 * keystore, VersaWorkConstant.DEV_APPLE_NOTIFICATION_CERTIFICATE_PASSWORD,
	 * true, account.getDeviceToken().trim()); }
	 * 
	 * //
	 * keystore.load(this.getClass().getResourceAsStream("/Certificates.p12"),
	 * // password); // keystore.load(notificationCertStream, password); //
	 * Push.alert(message, keystore, //
	 * VersaWorkConstant.APPLE_NOTIFICATION_CERTIFICATE_PASSWORD , // true,
	 * account.getDeviceToken().trim()); } else if
	 * (account.getDeviceType().equalsIgnoreCase("A")) { GcmUtils gcmUtils = new
	 * GcmUtils(); gcmUtils.sendGcmMessage(
	 * "AIzaSyCdrPS0YtvVaV6_BxRm3tkgpRpzvcR3VGM", account
	 * .getDeviceToken().trim(), message); }
	 * responseData.setResult(Integer.parseInt(messageSource.getMessage(
	 * VersaWorkConstant.SUCCESS_RESPONSE_CODE, null, nsRequest.getLocale())));
	 * responseData.setDescription("success");
	 * nsResponse.setResponseData(responseData);
	 * 
	 * return nsResponse;
	 * 
	 * } catch (Exception exp) { LOGGER.error(
	 * "Exception occoured on AccountServiceImpl (appointmentConfirmation) : ",
	 * exp); throw new BusinessException(exp.getMessage()); } }
	 */

	@Override
	public NsResponse genRandomAccountPassword(NsRequest nsRequest) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NsResponse updateAccountPhoneNumber(NsRequest nsRequest) throws BusinessException, SystemException {

		String responseMsg = null;
		Integer responseCode;
		if (nsRequest.getAccountInfo().getMobilePhoneNumber().trim().length() == 0) {
			throw new BusinessException();
		}

		String secureEndpointUserId = MaskUtils.getDigest(nsRequest.getAccountInfo().getEndpointUserId().getBytes());

		Account account = accountDAO.getAccountByEndpointId(secureEndpointUserId,
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		if (account == null) {
			return NsResponseUtils.getNsResponse(
					null,
					messageSource.getMessage("rsp.data.dscrptn.email.not.match", null, nsRequest.getLocale()),
					Integer.parseInt(messageSource.getMessage(VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
							nsRequest.getLocale())));
		}

		// account.setMobilePhoneNumber(nsRequest.getAccountInfo().getMobilePhoneNumber());
		String phoneNumber = nsRequest.getAccountInfo().getMobilePhoneNumber();
		phoneNumber = phoneNumber.replace("-", "");
		phoneNumber = phoneNumber.replace(" ", "");
		LOGGER.debug("phone number to update: " + phoneNumber);
		account.setMobilePhoneNumber(phoneNumber);
		accountDAO.update(account);
		getAccountInfo(account, nsRequest.getAccountInfo());
		responseMsg = messageSource.getMessage("phone.updtd", null, nsRequest.getLocale());
		responseCode = Integer.parseInt(messageSource.getMessage(SUCCESS_RESPONSE_CODE, null, nsRequest.getLocale()));
		// nsRequest.getAccountInfo().setAccountId(account.getAccountId());
		return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), responseMsg, responseCode);

	}

	@Override
	public NsResponse updateAccountEmail(NsRequest nsRequest) throws BusinessException, SystemException {

		String responseMsg = null;
		Integer responseCode;
		if (nsRequest.getAccountInfo().getEmail().trim().length() == 0) {
			throw new BusinessException();
		}

		String secureEndpointUserId = MaskUtils.getDigest(nsRequest.getAccountInfo().getEndpointUserId().getBytes());

		Account account = accountDAO.getAccountByEndpointId(secureEndpointUserId,
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		if (account == null) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage("rsp.data.dscrptn.email.not.match", null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}
		account.setEmail(nsRequest.getAccountInfo().getEmail());
		accountDAO.update(account);
		getAccountInfo(account, nsRequest.getAccountInfo());
		responseMsg = messageSource.getMessage("email.updtd", null, nsRequest.getLocale());
		responseCode = VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode();
		// nsRequest.getAccountInfo().setAccountId(account.getAccountId());
		return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), responseMsg, responseCode);

	}

	@Override
	public NsResponse confirmScheduledAppointment(NsRequest nsRequest) throws BusinessException, SystemException {
		try {
			Integer appointmentRequestId = nsRequest.getNsPatientAppointment().getPatientAppointmentRequestId();
			PatientAppointmentRequest patientAppointmentRequest = patientAppointmentDAO
					.getAppointmtReqForConfrmtn(appointmentRequestId);
			PatientUpcomingAppointment patientUpcomingAppointment = new PatientUpcomingAppointment();
			/*
			 * patientUpcomingAppointment.setPatientUpcomingAppointmentPK();
			 * setAccount(patientAppointmentRequest .getAccountId());
			 */
			patientUpcomingAppointment.setAppointmentDate(patientAppointmentRequest.getAppointmentDate());
			patientUpcomingAppointment.setAppointmentTime(patientAppointmentRequest.getAppointmentTime());
			patientUpcomingAppointment.setClientDatabaseId(9);
			patientUpcomingAppointment.setDateAdded(patientAppointmentRequest.getDateAdded());
			patientUpcomingAppointment.setIsRead(false);
			patientUpcomingAppointment.setLocationName("Test location");
			PatientUpcomingAppointmentPK patientUpcomingAppointmentPK = new PatientUpcomingAppointmentPK();
			patientUpcomingAppointmentPK.setAccountId(patientAppointmentRequest.getAccountId());
			patientUpcomingAppointmentPK.setAppointmentId(patientAppointmentRequest.getPatientAppointmentRequestId());
			patientUpcomingAppointment.setPatientUpcomingAppointmentPK(patientUpcomingAppointmentPK);
			patientUpcomingAppointment.setProviderId("ANDEM");
			patientUpcomingAppointment.setSourceId("TEST");
			patientUpcomingAppointment.setSourceName("TEST");
			patientUpcomingAppointment.setProviderName("Demo Provider");
			patientUpcomingAppointment.setStatus(null);
			patientUpcomingAppointment.setStatusCode(1);
			patientUpcomingAppointment.setTypeDescription("Fever");
			patientAppointmentDAO.savePatientUpcomingAppointments(patientUpcomingAppointment);
			NsResponse nsResponse = new NsResponse();
			ResponseData responseData = new ResponseData();
			responseData.setDescription("Success");
			responseData.setResult(0);
			nsResponse.setResponseData(responseData);
			return nsResponse;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Unable to save appointment");
		}
	}

	// @Override
	/*public NsResponse getPatientLabGroup(NsRequest nsRequest) throws BusinessException, SystemException {
		ResponseData responseData = new ResponseData();
		NsResponse nsResponse = new NsResponse();
		NsResponse nsResponseTemp = getPatientLabResult(nsRequest);
		List<NsPatientLabResult> patientLabResultList = nsResponseTemp.getPatientLabResults();
		Set<String> sourceNames = new HashSet<String>();
		Set<String> groupNames = new HashSet<String>();
		Map<String, String> groupMap = new HashMap<String, String>();
		Map<String, List<NsPatientLabResult>> mapGroupWise = new HashMap<String, List<NsPatientLabResult>>();
		Map<String, Map<String, List<NsPatientLabResult>>> mapDateWise = new HashMap<String, Map<String, List<NsPatientLabResult>>>();
		Map<String, Map<String, Map<String, List<NsPatientLabResult>>>> mapSourceWise = new HashMap<String, Map<String, Map<String, List<NsPatientLabResult>>>>();

		{
			Iterator<NsPatientLabResult> iter = patientLabResultList.iterator();
			while (iter.hasNext()) {
				NsPatientLabResult nsPatientLabResult = iter.next();
				sourceNames.add(nsPatientLabResult.getSourceName());
				groupNames.add(nsPatientLabResult.getGroupName());
				if (groupMap.containsKey(nsPatientLabResult.getGroupName())) {
					String groupDate = groupMap.get(nsPatientLabResult.getGroupName());
					Date nsPatDate = DateUtils.getDate(groupDate, "MM/dd/yyyy");
					Date patLabDate = DateUtils.getDate(nsPatientLabResult.getGroupDate(), "MM/dd/yyyy");
					if (nsPatDate != null && nsPatDate.before(patLabDate)) {
						groupMap.put(nsPatientLabResult.getGroupName(), nsPatientLabResult.getGroupDate());
					}
				} else {
					groupMap.put(nsPatientLabResult.getGroupName(), nsPatientLabResult.getGroupDate());
				}

			}
		}
		Iterator<String> sourceNamesIterator = sourceNames.iterator();
		while (sourceNamesIterator.hasNext()) {
			String sourceName = sourceNamesIterator.next();
			mapGroupWise = new HashMap<String, List<NsPatientLabResult>>();
			mapDateWise = new HashMap<String, Map<String, List<NsPatientLabResult>>>();
			Iterator<NsPatientLabResult> iter = patientLabResultList.iterator();
			while (iter.hasNext()) {
				NsPatientLabResult nsPatientLabResult = iter.next();
				if (nsPatientLabResult.getSourceName().equalsIgnoreCase(sourceName)) {
					String groupName = nsPatientLabResult.getGroupName();
					if (groupName.equalsIgnoreCase(nsPatientLabResult.getGroupName())) {
						if (groupMap.get(groupName).equalsIgnoreCase(nsPatientLabResult.getGroupDate())) {
							mapGroupWise = mapDateWise.get(nsPatientLabResult.getGroupDate());
							if (mapGroupWise != null) {
								List<NsPatientLabResult> nsP = new ArrayList<NsPatientLabResult>();
								if (mapGroupWise.containsKey(groupName)) {
									nsP = mapGroupWise.get(groupName);
									nsP.add(nsPatientLabResult);
									mapGroupWise.put(groupName, nsP);
								} else {
									nsP.add(nsPatientLabResult);
									mapGroupWise.put(groupName, nsP);
								}
							} else {
								List<NsPatientLabResult> nsP = new ArrayList<NsPatientLabResult>();
								nsP.add(nsPatientLabResult);
								mapGroupWise = new HashMap<String, List<NsPatientLabResult>>();
								mapGroupWise.put(groupName, nsP);
							}
							mapDateWise.put(nsPatientLabResult.getGroupDate(), mapGroupWise);
						}
						mapSourceWise.put(nsPatientLabResult.getSourceName(), mapDateWise);
					}

				}
			}
		}

		// nsResponse.setPatientLabResultMap(mapSourceWise);
		responseData.setDescription("Success");
		responseData.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}*/

	@Override
	public NsResponse getPatientLabGroups(NsRequest nsRequest) throws BusinessException, SystemException {
		ResponseData responseData = new ResponseData();
		NsResponse nsResponse = new NsResponse();
		NsResponse nsResponseTemp = getPatientLabResult(nsRequest);
		if (nsResponseTemp.getResponseData().getResult() != 0) {
			return nsResponseTemp;
		}
		List<NsPatientLabResult> patientLabResultList = nsResponseTemp.getPatientLabResults();

		LOGGER.debug("patientLabResultList size" + patientLabResultList.size());
		if (patientLabResultList.size() == 0 || patientLabResultList == null) {
			responseData.setDescription(messageSource.getMessage("no.lab.result", null, nsRequest.getLocale()));
			responseData.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
			nsResponse.setResponseData(responseData);
			return nsResponse;
		}
		Map<String, List<NsPatientLabResult>> patientLabResultMap = new HashMap<String, List<NsPatientLabResult>>();
		Iterator<NsPatientLabResult> iter = patientLabResultList.iterator();
		while (iter.hasNext()) {

			NsPatientLabResult nsPatientLabResult = iter.next();
			if (nsPatientLabResult.getGroupName() != null && nsPatientLabResult.getGroupName() != "" && !nsPatientLabResult.getGroupName().isEmpty()) {
				if (patientLabResultMap.containsKey(nsPatientLabResult.getGroupName())) {
					List<NsPatientLabResult> nsPat = patientLabResultMap.get(nsPatientLabResult.getGroupName());
					Date nsPatDate = DateUtils.getDate(nsPat.get(0).getGroupDate(), "MM/dd/yyyy");
					Date patLabDate = DateUtils.getDate(nsPatientLabResult.getGroupDate(), "MM/dd/yyyy");
					if (nsPatDate.before(patLabDate)) {
						nsPat = new ArrayList<NsPatientLabResult>();
						nsPat.add(nsPatientLabResult);
						patientLabResultMap.put(nsPatientLabResult.getGroupName(), nsPat);
					} else if (nsPatDate.compareTo(patLabDate) == 0) {
						nsPat.add(nsPatientLabResult);
						patientLabResultMap.put(nsPatientLabResult.getGroupName(), nsPat);
					}

				} else {
					List<NsPatientLabResult> nsList = new ArrayList<NsPatientLabResult>();
					nsList.add(nsPatientLabResult);
					patientLabResultMap.put(nsPatientLabResult.getGroupName(), nsList);
				}
			} else {
				if (patientLabResultMap.containsKey(nsPatientLabResult.getTestName())) {
					List<NsPatientLabResult> nsPat = patientLabResultMap.get(nsPatientLabResult.getTestName());
					Date nsPatDate = DateUtils.getDate(nsPat.get(0).getDateAdded(), "MMMM d, yyyy");
					Date patLabDate = DateUtils.getDate(nsPatientLabResult.getDateAdded(), "MMMM d, yyyy");
					if (nsPatDate.before(patLabDate)) {
						nsPat = new ArrayList<NsPatientLabResult>();
						nsPat.add(nsPatientLabResult);
						patientLabResultMap.put(nsPatientLabResult.getTestName(), nsPat);
					} else if (nsPatDate.compareTo(patLabDate) == 0) {
						nsPat.add(nsPatientLabResult);
						patientLabResultMap.put(nsPatientLabResult.getTestName(), nsPat);
					}
				} else {
					List<NsPatientLabResult> nsList = new ArrayList<NsPatientLabResult>();
					nsList.add(nsPatientLabResult);
					patientLabResultMap.put(nsPatientLabResult.getTestName(), nsList);
				}
			}
		}
		List<NsPatientLabResult> testList = new ArrayList<NsPatientLabResult>();
		List<LabGroups> labGroupsList = new ArrayList<LabGroups>();
		Set<String> mapKeys = patientLabResultMap.keySet();
		Iterator<String> keyIterator = mapKeys.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			List<NsPatientLabResult> labResList = patientLabResultMap.get(key);
			if (labResList.get(0).getGroupName().length() == 0 || labResList.get(0).getGroupId() == null
					|| labResList.get(0).getGroupName() == null || labResList.get(0).getGroupName() == "") {
				testList.addAll(labResList);
			} else {
				LabGroups labGroups = new LabGroups();
				labGroups.setGroupDate(labResList.get(0).getGroupDate());
				labGroups.setGroupId(labResList.get(0).getGroupId());
				labGroups.setSourceName(labResList.get(0).getSourceName());
				labGroups.setGroupName(labResList.get(0).getGroupName());
				Collections.sort(labResList, new LabTestListComparator());
				labGroups.setLabResultList(labResList);
				labGroupsList.add(labGroups);
			}
		}
		LabResults labRes = new LabResults();
		Collections.sort(labGroupsList, new LabGroupListComparator());
		Collections.sort(testList, new LabTestListComparator());
		labRes.setLabGroups(labGroupsList);
		labRes.setLabTests(testList);
		nsResponse.setLabRes(labRes);
		// nsResponse.setPatientLabResultMap(patientLabResultMap);
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}

	/*
	 * @Override public NsResponse getPatientLabGroups(NsRequest nsRequest)
	 * throws BusinessException, SystemException { ResponseData responseData =
	 * new ResponseData(); NsResponse nsResponse = new NsResponse(); NsResponse
	 * nsResponseTemp = getPatientLabResult(nsRequest); if
	 * (nsResponseTemp.getResponseData().getResult() != 0) { return
	 * nsResponseTemp; } List<NsPatientLabResult> patientLabResultList =
	 * nsResponseTemp .getPatientLabResults(); Map<String,
	 * List<NsPatientLabResult>> patientLabResultMap = new HashMap<String,
	 * List<NsPatientLabResult>>(); Iterator<NsPatientLabResult> iter =
	 * patientLabResultList.iterator(); while (iter.hasNext()) {
	 * NsPatientLabResult nsPatientLabResult = iter.next(); if
	 * (nsPatientLabResult.getGroupName() != null &&
	 * nsPatientLabResult.getGroupName() != "" &&
	 * !nsPatientLabResult.getGroupName().isEmpty()) { if
	 * (patientLabResultMap.containsKey(nsPatientLabResult .getGroupName())) {
	 * List<NsPatientLabResult> nsPat = patientLabResultMap
	 * .get(nsPatientLabResult.getGroupName()); Date nsPatDate =
	 * DateUtils.getDate(nsPat.get(0) .getGroupDate(), "MMMM d, yyyy"); Date
	 * patLabDate = DateUtils.getDate( nsPatientLabResult.getGroupDate(),
	 * "MMMM d, yyyy"); if (nsPatDate.before(patLabDate)) { nsPat = new
	 * ArrayList<NsPatientLabResult>(); nsPat.add(nsPatientLabResult);
	 * patientLabResultMap.put( nsPatientLabResult.getGroupName(), nsPat); }
	 * else if (nsPatDate.compareTo(patLabDate) == 0) {
	 * nsPat.add(nsPatientLabResult); patientLabResultMap.put(
	 * nsPatientLabResult.getGroupName(), nsPat); }
	 * 
	 * } else { List<NsPatientLabResult> nsList = new
	 * ArrayList<NsPatientLabResult>(); nsList.add(nsPatientLabResult);
	 * patientLabResultMap.put(nsPatientLabResult.getGroupName(), nsList); } }
	 * else { if (patientLabResultMap.containsKey(nsPatientLabResult
	 * .getTestName())) { List<NsPatientLabResult> nsPat = patientLabResultMap
	 * .get(nsPatientLabResult.getTestName()); Date nsPatDate =
	 * DateUtils.getDate(nsPat.get(0) .getDateAdded(), "MMMM d, yyyy"); Date
	 * patLabDate = DateUtils.getDate( nsPatientLabResult.getDateAdded(),
	 * "MMMM d, yyyy"); if (nsPatDate.before(patLabDate)) { nsPat = new
	 * ArrayList<NsPatientLabResult>(); nsPat.add(nsPatientLabResult);
	 * patientLabResultMap.put( nsPatientLabResult.getTestName(), nsPat); } else
	 * if (nsPatDate.compareTo(patLabDate) == 0) {
	 * nsPat.add(nsPatientLabResult); patientLabResultMap.put(
	 * nsPatientLabResult.getTestName(), nsPat); } } else {
	 * List<NsPatientLabResult> nsList = new ArrayList<NsPatientLabResult>();
	 * nsList.add(nsPatientLabResult);
	 * patientLabResultMap.put(nsPatientLabResult.getTestName(), nsList); } } }
	 * List<NsPatientLabResult> testList = new ArrayList<NsPatientLabResult>();
	 * List<LabGroups> labGroupsList = new ArrayList<LabGroups>(); Set<String>
	 * mapKeys = patientLabResultMap.keySet(); Iterator<String> keyIterator =
	 * mapKeys.iterator(); while(keyIterator.hasNext()) { String key =
	 * keyIterator.next(); List<NsPatientLabResult> labResList =
	 * patientLabResultMap.get(key);
	 * if(labResList.get(0).getGroupName().length()==0 ||
	 * labResList.get(0).getGroupId()==null || labResList.get(0).getGroupName()
	 * == null || labResList.get(0).getGroupName()=="") {
	 * testList.addAll(labResList); }else { LabGroups labGroups = new
	 * LabGroups(); labGroups.setGroupDate(labResList.get(0).getGroupDate());
	 * labGroups.setGroupId(labResList.get(0).getGroupId());
	 * labGroups.setSourceName(labResList.get(0).getSourceName());
	 * labGroups.setGroupName(labResList.get(0).getGroupName());
	 * labGroups.setLabResultList(labResList); labGroupsList.add(labGroups); } }
	 * LabResults labRes = new LabResults(); labRes.setLabGroups(labGroupsList);
	 * labRes.setLabTests(testList); nsResponse.setLabRes(labRes);
	 * //nsResponse.setPatientLabResultMap(patientLabResultMap);
	 * responseData.setDescription("Success"); responseData.setResult(0);
	 * nsResponse.setResponseData(responseData); return nsResponse; }
	 */
	@Override
	public NsResponse getPatientLabTestHistory(NsRequest nsRequest) throws BusinessException, SystemException {

		if (nsRequest.getTestId()==null || nsRequest.getTestId().equalsIgnoreCase(UNAVAIABLE_CODE)) {
			throw new BusinessException("no.acc.details.found");
		}

		try {

			String clientDBId = messageSource.getMessage("client.database.id", null, nsRequest.getLocale());

			String key = CacheConstants.PATIENT_LABHISTORY.getCode();

			Boolean isCachingOn = cCS.isCachingOn(key);
			String cacheKey = new StringBuilder(key).append("_").append(clientDBId).append("_")
					.append(nsRequest.getAccountInfo().getAccountId()).append("_").append(nsRequest.getTestId().trim())
					.toString();

			LOGGER.debug("key : " + key + " cacheKey : " + cacheKey);

			NsResponse nsResponse = new NsResponse();

			Cache2Object<NsPatientLabResult> obj = new Cache2Object<NsPatientLabResult>(cCS, cache);
			Type type = new TypeToken<List<NsPatientLabResult>>() {
			}.getType();
			List<NsPatientLabResult> nsPatientLabResults = obj.getObject(cacheKey, isCachingOn, type);

			if (nsPatientLabResults == null || nsPatientLabResults.isEmpty()) {

				List<PatientLab> patientLabResults = patientLabServiceDao.getLabTestHistory(nsRequest);
				nsPatientLabResults = new ArrayList<NsPatientLabResult>();
				if(patientLabResults!=null && patientLabResults.size()>0){
					for (PatientLab patientLab : patientLabResults) {
						NsPatientLabResult nsPatLabRes = ResponseInfoObject.getNsPatientLabResult(patientLab); 
						nsPatientLabResults.add(nsPatLabRes);
					}
				}else{
					throw new BusinessException("no.acc.details.found");
				}
				
				obj.setObject(cacheKey, isCachingOn, nsPatientLabResults, type);
			}
			Collections.sort(nsPatientLabResults, new NsPatientLabResultComparator());
			nsResponse.setPatientLabResults(nsPatientLabResults);
			ResponseData responseData = new ResponseData();
			responseData.setDescription("Success");
			responseData.setResult(0);
			nsResponse.setResponseData(responseData);
			return nsResponse;
		} catch (Exception exp) {
			exp.printStackTrace();
			LOGGER.error("Exception occoured on get Patient Lab Test History service Impl : ", exp);
			throw new BusinessException(exp.getMessage());
		}

	}

	@Override
	public NsResponse getAccountImagingDetails(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		Integer accountId = nsRequest.getAccountInfo().getAccountId();
		List<PatientImagingInfo> patientImagingInfoList = new ArrayList<PatientImagingInfo>();
		List<PatientImagingInfo> tempPatientImagingInfoList = new ArrayList<PatientImagingInfo>();
		
		if(nsRequest.getViewPatientInfo() != null)  // service hit from visit module, get imaging for only specified account id and not linked account id's
		{
			tempPatientImagingInfoList = fetchAccountImagingDetails(nsRequest.getViewPatientInfo().getAccountId());
			patientImagingInfoList.addAll(tempPatientImagingInfoList);
		}
		else
		{
			tempPatientImagingInfoList = fetchAccountImagingDetails(accountId);
			patientImagingInfoList.addAll(tempPatientImagingInfoList);
	
			List<LinkedAccountsRel> linkedAccounts = linkAccountServiceImpl.getLinkedAccounts(accountId);
			for (LinkedAccountsRel linkedAccountsRel : linkedAccounts) {
				try{
				LOGGER.debug("Fetching imaging result for account: " + linkedAccountsRel);
				tempPatientImagingInfoList = fetchAccountImagingDetails(linkedAccountsRel.getLinkedAccountId());
				LOGGER.debug("imaging records of " + linkedAccountsRel + " are: " + tempPatientImagingInfoList.size());
				patientImagingInfoList.addAll(tempPatientImagingInfoList);
				}catch(Exception e){
					LOGGER.debug("No imaging result found for linkAccount: " + linkedAccountsRel);
				}
			}
		}
		
			if (patientImagingInfoList == null || patientImagingInfoList.isEmpty()) {
				throw new BusinessException("no.images.found");
			}
			String sourceName = "";

			/**
			 * Tree map is use because android team want sorted map based on
			 * source key.
			 */
			Map<String, List<PatientImagingInfo>> patientImagingObjectMap = new TreeMap<String, List<PatientImagingInfo>>();
			List<PatientImagingInfo> list = Collections.emptyList();

			/**
			 * Tree set is use because ios team want sorted set of source key
			 * which is used in map.
			 */
			Set<SourceInfo> sourceList = new TreeSet<SourceInfo>(new Comparator<SourceInfo>() {

				@Override
				public int compare(SourceInfo o1, SourceInfo o2) {
					/**
					 * null is checked because while add object we checksource
					 * name not null
					 */
					return o1.getSourceName().compareTo(o2.getSourceName());
				}

			});
			for (PatientImagingInfo info : patientImagingInfoList) {

				sourceName = info.getSourceName() != null && !info.getSourceName().trim().isEmpty() ? info
						.getSourceName() : "Others";
				if (patientImagingObjectMap.containsKey(sourceName)) {
					list = patientImagingObjectMap.get(sourceName);
				} else {
					list = new ArrayList<PatientImagingInfo>();
					patientImagingObjectMap.put(sourceName, list);
					sourceList.add(new SourceInfo(sourceName));
				}
				list.add(info);

			}
			Set<Entry<String, List<PatientImagingInfo>>> entrySet = patientImagingObjectMap.entrySet();
			/**
			 * Sorting list of imaging object based on date added.
			 */
			for (Entry<String, List<PatientImagingInfo>> entry : entrySet) {

				Collections.sort(entry.getValue(), new Comparator<PatientImagingInfo>() {
					@Override
					public int compare(PatientImagingInfo o1, PatientImagingInfo o2) {
						Date date = null, date1 = null;
						try {
							date = DateUtils.getDate(o1.getExamDate(), "MM/dd/yyyy");
							date1 = DateUtils.getDate(o2.getExamDate(), "MM/dd/yyyy");
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (null != date && null != date1) {
							if (date.after(date1))
								return -1;
							else
								return 1;
						}
						return 0;

					}
				});
			}

			ResponseData responseData = new ResponseData();
			responseData.setDescription("Success");
			responseData.setResult(0);
			nsResponse.setResponseData(responseData);
			nsResponse.setPatientImagingObjectMap(patientImagingObjectMap);
			nsResponse.setSourceList(sourceList);
		
		return nsResponse;
	}

	private List<PatientImagingInfo> fetchAccountImagingDetails(Integer accountId) throws BusinessException,SystemException{
		String key = CacheConstants.PATIENT_IMAGING.getCode();
		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = formatCacheKey(accountId, key);

		LOGGER.debug("key : " + key + " cacheKey : " + cacheKey);
		Cache2Object<PatientImagingInfo> obj = new Cache2Object<PatientImagingInfo>(cCS, cache);

		Type type = new TypeToken<List<PatientImagingInfo>>() {
		}.getType();
		List<PatientImagingInfo> patientImagingInfoList = obj.getObject(cacheKey, isCachingOn, type);

		if (patientImagingInfoList == null || patientImagingInfoList.isEmpty()) {

			List<PatientImaging> patientImaging = accountDAO.getAccountPatientImaging(accountId);

			patientImagingInfoList = new ArrayList<PatientImagingInfo>();
			PatientImagingInfo patientImagingInfo = null;

			for (PatientImaging imaging : patientImaging) {
				patientImagingInfo = ResponseInfoObject.getPatientImagingInfo(imaging);
				patientImagingInfoList.add(patientImagingInfo);
			}
			obj.setObject(cacheKey, isCachingOn, patientImagingInfoList, type);
		}
		return patientImagingInfoList;
	}

	@Override
	public NsResponse getPatientAllergies(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		List<AllergiesInfo> allergyList = new ArrayList<AllergiesInfo>();
		List<AllergiesInfo> tempAllergyList = new ArrayList<AllergiesInfo>();
		Integer accountId = nsRequest.getAccountInfo().getAccountId();
		List<LinkedAccountsRel> linkedAccounts = linkAccountServiceImpl.getLinkedAccounts(accountId);
		
		tempAllergyList = fetchAccountAllergies(accountId);
		allergyList.addAll(tempAllergyList);
		
		for(LinkedAccountsRel linkedAccountsRel:linkedAccounts){
			try{
			LOGGER.debug("Fetching allergies for account: "+linkedAccountsRel);
			tempAllergyList = fetchAccountAllergies(linkedAccountsRel.getLinkedAccountId());
			LOGGER.debug("Aleergies of "+linkedAccountsRel+" are: "+tempAllergyList.size());
			allergyList.addAll(tempAllergyList);
			Collections.sort(allergyList,new AllegyListComparator());
			}
			catch(Exception e){
				LOGGER.debug("Fetching allergies for account: "+linkedAccountsRel);
			}
		}
		
		if (allergyList == null || allergyList.isEmpty()) {
			throw new BusinessException("no.allergies.found");
		}
		String sourceName = "";
		/**
		 * Tree map is use because android team want sorted map based on source
		 * key.
		 */
		Map<String, List<AllergiesInfo>> allergyListMap = new TreeMap<String, List<AllergiesInfo>>();
		/**
		 * Tree set is use because ios team want sorted set of source key which
		 * is used in map.
		 */
		Set<SourceInfo> sourceList = new TreeSet<SourceInfo>(new Comparator<SourceInfo>() {

			@Override
			public int compare(SourceInfo o1, SourceInfo o2) {
				/**
				 * null is checked because while add object we checksource name
				 * not null
				 */
				return o1.getSourceName().compareTo(o2.getSourceName());
			}

		});
		List<AllergiesInfo> list = Collections.emptyList();

		for (AllergiesInfo allergiesInfo : allergyList) {

			sourceName = allergiesInfo.getSourceName() != null && !allergiesInfo.getSourceName().trim().isEmpty() ? allergiesInfo
					.getSourceName() : "Others"; // avoid null in source name

			if (allergyListMap.containsKey(sourceName)) {
				list = allergyListMap.get(sourceName);
			} else {
				list = new ArrayList<AllergiesInfo>();
				allergyListMap.put(sourceName, list);
				sourceList.add(new SourceInfo(sourceName));
			}
			list.add(allergiesInfo);

		}

		Set<Entry<String, List<AllergiesInfo>>> entrySet = allergyListMap.entrySet();

		/**
		 * Sorting list of AllergiesInfo name based on allergen.
		 */
		for (Entry<String, List<AllergiesInfo>> entry : entrySet) {
			Collections.sort(entry.getValue(),new AllegyListComparator());

			/*Collections.sort(entry.getValue(), new Comparator<AllergiesInfo>() {

				@Override
				public int compare(AllergiesInfo o1, AllergiesInfo o2) {
					if (o1 == o2)
						return 0;
					if (o1 == null || o1.getAllergen() == null)
						return 1;
					if (o2 == null || o2.getAllergen() == null)
						return -1;

					return o1.getAllergen().compareTo(o2.getAllergen());

				}
			});*/
		}

		ResponseData responseData = new ResponseData();
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setPatientAllergiesMap(allergyListMap);
		nsResponse.setSourceList(sourceList);

		return nsResponse;
	}

	private List<AllergiesInfo> fetchAccountAllergies(Integer accountId) throws BusinessException,SystemException{

		String key = CacheConstants.PATIENT_ALLERGY.getCode();

		Boolean isCachingOn = cCS.isCachingOn(key);

		String cacheKey = formatCacheKey(accountId, key);

		LOGGER.debug("key : " + key + " cacheKey : " + cacheKey);
		Cache2Object<AllergiesInfo> obj = new Cache2Object<AllergiesInfo>(cCS, cache);

		Type type = new TypeToken<List<AllergiesInfo>>() {
		}.getType();
		List<AllergiesInfo> allergyList = obj.getObject(cacheKey, isCachingOn, type);

		if (allergyList == null || allergyList.isEmpty()) {
			List<PatientAllergy> patientAllergiesList = accountDAO.getPatientAllergiesList(accountId);
			allergyList = new ArrayList<AllergiesInfo>();
			AllergiesInfo allergiesInfo = null;
			for (PatientAllergy patientAllergies : patientAllergiesList) {
				allergiesInfo = ResponseInfoObject.getPatientAllergiesInfo(patientAllergies);
				allergyList.add(allergiesInfo);

			}
			obj.setObject(cacheKey, isCachingOn, allergyList, type);

		}
		
		return allergyList;
	}

	private String formatCacheKey(int accountId, String key) {
		String clientDBId = messageSource.getMessage("client.database.id", null, Locale.getDefault());
		// key = key + "_" + nsRequest.getAccountInfo().getAccountId();
		return new StringBuilder(key).append("_").append(clientDBId).append("_").append(accountId).toString();
	}

	public List<String> prescriptionActionListSorter(Set<String> prescriptionActions1) {

		List<String> prescriptionActions2 = new ArrayList<String>();
		List<String> prescriptionActions = new ArrayList<String>();
		/**
		 * @NOTE: below english labels are used to group prescriptions in json
		 *        (not for display purpose).
		 */
		prescriptionActions.add("New");
		prescriptionActions.add("Continue");
		prescriptionActions.add("Discontinue");
		prescriptionActions.add("Changed but continue");
		// Collections.sort(prescriptionActions, new PrescActionComparator());
		Iterator<String> prescActionIter = prescriptionActions.iterator();
		while (prescActionIter.hasNext()) {
			String value = prescActionIter.next();
			if (prescriptionActions1.contains(value)) {
				prescriptionActions2.add(value);
			}
		}
		return prescriptionActions2;

	}

	@Override
	public NsResponse savepreferredlanguage(NsRequest nsRequest) throws BusinessException, SystemException {

		Account account = accountDAO.getAccountByEndpointId(
				MaskUtils.getDigest(nsRequest.getAccountInfo().getEndpointUserId().getBytes()),
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		account.setPreferredLanguage(nsRequest.getAccountInfo().getPreferredLanguage());
		accountDAO.update(account);
		getAccountInfo(account, nsRequest.getAccountInfo());
		return NsResponseUtils.getNsResponse(
				nsRequest.getAccountInfo(),
				"Success",
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));

	}

	private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

	public static Date addDays(Date date, int numDays) {
		return new Date(date.getTime() + numDays * MILLIS_PER_DAY);
	}

	private void getAccountInfo(Account account, AccountInfo accountInfo) {
		// AccountInfo accountInfo = new AccountInfo();
		// ResponseData responseData = new ResponseData();
		Boolean shareData = account.getWillShareData();
		accountInfo.setAccountId(account.getAccountId());
		accountInfo.setAccountName(account.getAccountName());
		accountInfo.setEmail(account.getEmail());
		accountInfo.setFirstName(account.getFirstName());
		accountInfo.setLastName(account.getLastName());
		String mobilePhoneNumber = account.getMobilePhoneNumber();

		if (account.getMobilePhoneNumber() != null && account.getMobilePhoneNumber().length() == 10
				&& account.getMobilePhoneNumber() != "" && !account.getMobilePhoneNumber().contains("-")) {
			StringBuffer sb = new StringBuffer();
			char[] b = " - ".toCharArray();
			for (int x = 0; x < mobilePhoneNumber.length(); x++) {
				if (x != 3 && x != 6) {
					sb.append(mobilePhoneNumber.charAt(x));
				} else {
					sb.append(b);
					sb.append(mobilePhoneNumber.charAt(x));
				}
			}
			accountInfo.setMobilePhoneNumber(sb.toString());
		} else {
			accountInfo.setMobilePhoneNumber(account.getMobilePhoneNumber());
		}
		accountInfo.setRoleName(account.getAccountRoleId().getRoleName());
		accountInfo.setUnitNumber(account.getMedicalRecordNumber());
		// accountInfo.setWillProvideFeedback(account.getWillProvideFeedback());
		if (shareData == null) {
			accountInfo.setWillShareData(false);
		} else {
			accountInfo.setWillShareData(shareData);
		}

		try {

			accountInfo.setDateOfBirth(DateUtils.getFormatDate(account.getBirthDate(), "MM/dd/yyyy"));
		} catch (Exception e) {
			LOGGER.info("could not set date of birth");
		}
		accountInfo.setProfileImage(account.getProfileImage());
	}

	@Override
	public NsResponse getPatientProblems(NsRequest nsRequest) throws BusinessException, SystemException {
		Integer accountId = nsRequest.getAccountInfo().getAccountId();
		Integer clientDBId = Integer.parseInt(messageSource.getMessage("client.database.id", null, Locale.getDefault()));
		List<PatientProblemInfo> patientProblemList = new ArrayList<PatientProblemInfo>();
		List<PatientProblemInfo> tempPatientProblemList = new ArrayList<PatientProblemInfo>();
		//ArrayList<Integer> linkedAccounts
		List<LinkedAccountsRel> linkedAccounts= linkAccountServiceImpl.getLinkedAccounts(accountId);
		
		tempPatientProblemList = fetchAccountProblems(accountId);
		patientProblemList.addAll(tempPatientProblemList);
		
		for(LinkedAccountsRel linkedAccountsRel:linkedAccounts)
		{
			try{
			LOGGER.debug("Fetching problems for account: "+linkedAccountsRel);
			tempPatientProblemList = fetchAccountProblems(linkedAccountsRel.getLinkedAccountId());
			LOGGER.debug("Problems of "+linkedAccountsRel+" are: "+tempPatientProblemList.size());
			patientProblemList.addAll(tempPatientProblemList);
			Collections.sort(patientProblemList,new PatientProblemListComparator());
			}catch(Exception e){
				LOGGER.debug("No problems found for linkAccount: "+linkedAccountsRel);
			}
		}
		
		if (patientProblemList == null || patientProblemList.isEmpty()) {
			throw new BusinessException("no.patient.problem.found");
		}

		String sourceName = "";
		/**
		 * Tree map is use because android team want sorted map based on source
		 * key.
		 */
		Map<String, List<PatientProblemInfo>> patientProblemsMap = new TreeMap<String, List<PatientProblemInfo>>();
		List<PatientProblemInfo> list = Collections.emptyList();
		/**
		 * Tree set is use because ios team want sorted set of source key which
		 * is used in map.
		 */
		Set<SourceInfo> sourceList = new TreeSet<SourceInfo>(new Comparator<SourceInfo>() {

			@Override
			public int compare(SourceInfo o1, SourceInfo o2) {
				/**
				 * null is checked because while add object we checksource name
				 * not null
				 */
				return o1.getSourceName().compareTo(o2.getSourceName());
			}

		});

		for (PatientProblemInfo patientProblem : patientProblemList) {

			sourceName = patientProblem.getSourceName() != null && !patientProblem.getSourceName().trim().isEmpty() ? patientProblem
					.getSourceName() : "Others";
			if (patientProblemsMap.containsKey(sourceName)) {
				list = patientProblemsMap.get(sourceName);
			} else {
				list = new ArrayList<PatientProblemInfo>();
				patientProblemsMap.put(sourceName, list);
				sourceList.add(new SourceInfo(sourceName));
			}
			list.add(patientProblem);

		}
		Set<Entry<String, List<PatientProblemInfo>>> entrySet = patientProblemsMap.entrySet();

		/**
		 * Sorting list of AllergiesInfo name based on allergen.
		 */
		for (Entry<String, List<PatientProblemInfo>> entry : entrySet) {
			Collections.sort(entry.getValue(),new PatientProblemListComparator());
		}

		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		responseData.setDescription("Success");
		responseData.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		nsResponse.setResponseData(responseData);
		
		nsResponse.setPatientProblemsMap(patientProblemsMap);		
		nsResponse.setSourceList(sourceList);
		return nsResponse;

	}

	private List<PatientProblemInfo> fetchAccountProblems(Integer accountId) throws BusinessException, SystemException{
		List<PatientProblemInfo> patientProblemList = null;
		String key = CacheConstants.PATIENT_PROBLEM.getCode();

		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = formatCacheKey(accountId, key);

		LOGGER.debug("key : " + key + " cacheKey : " + cacheKey);

		Cache2Object<PatientProblemInfo> obj = new Cache2Object<PatientProblemInfo>(cCS, cache);

		Type type = new TypeToken<List<PatientProblemInfo>>() {
		}.getType();
		patientProblemList = obj.getObject(cacheKey, isCachingOn, type);

		if (patientProblemList == null || patientProblemList.isEmpty()) {
			patientProblemList = new ArrayList<PatientProblemInfo>();
			List<PatientProblem> patientProblems = accountDAO.getPatientProblemList(accountId);

			PatientProblemInfo patientProblemInfo = null;

			for (PatientProblem patientProblem : patientProblems) {
				patientProblemInfo = ResponseInfoObject.getPatientProblemInfo(patientProblem);
				patientProblemList.add(patientProblemInfo);
			}
			if(!patientProblemList.isEmpty() && patientProblemList.size()!=0)
			{
			obj.setObject(cacheKey, isCachingOn, patientProblemList, type);
			}
		}
	return 	patientProblemList;
	}
	
	
	@Override
	public NsResponse getPatientImmunalization(NsRequest nsRequest) throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();		
		Integer clientDBId = Integer.parseInt(messageSource.getMessage("client.database.id", null, Locale.getDefault()));	
		Integer accountId = nsRequest.getAccountInfo().getAccountId();
		List<PatientInpatientImmunalizationInfo> nsPatientImmunization = new ArrayList<PatientInpatientImmunalizationInfo>();
		List<PatientInpatientImmunalizationInfo> tempPatientImmunization = new ArrayList<PatientInpatientImmunalizationInfo>();
		List<LinkedAccountsRel> linkedAccounts = linkAccountServiceImpl.getLinkedAccounts(accountId);
		
		tempPatientImmunization = fetchPatientsImmunization(accountId);
		nsPatientImmunization.addAll(tempPatientImmunization);
		
		for(LinkedAccountsRel linkedAccountsRel:linkedAccounts)
		{
						
			try{
			tempPatientImmunization = fetchPatientsImmunization(linkedAccountsRel.getLinkedAccountId());
			
			nsPatientImmunization.addAll(tempPatientImmunization);
			Collections.sort(nsPatientImmunization,new ImmunizationComparator());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if (nsPatientImmunization.isEmpty()) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage("no.immunization.result", null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}
		
		nsResponse = NsResponseUtils.getNsResponse(
				null,
				messageSource.getMessage("rsp.data.dscrptn.success", null, nsRequest.getLocale()),
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));
		
		
					
			
		nsResponse.setPatientInpatientImmunalizationInfoList(nsPatientImmunization);

		return nsResponse;

	}

	private List<PatientInpatientImmunalizationInfo> fetchPatientsImmunization(Integer accountId) throws BusinessException,SystemException{
		List<PatientImmunization> patientImmunizationList =(List<PatientImmunization>) accountDAO.getImmunizationInfo(accountId);		
		List<PatientInpatientImmunalizationInfo> patientImnInfo = new ArrayList<PatientInpatientImmunalizationInfo>();
		for (PatientImmunization patientInpatientImmunization : patientImmunizationList) {
		
			 PatientInpatientImmunalizationInfo patientInpatientImmunalizationInfo = new PatientInpatientImmunalizationInfo();
                patientInpatientImmunalizationInfo.setImmunalizationCode(patientInpatientImmunization.getImmunizationCode());
				patientInpatientImmunalizationInfo.setImmunalizationName(patientInpatientImmunization.getImmunizationName());
				patientInpatientImmunalizationInfo.setRouteCode(patientInpatientImmunization.getRouteCode());
				patientInpatientImmunalizationInfo.setRouteName(patientInpatientImmunization.getRouteName());
				patientInpatientImmunalizationInfo.setStatus(patientInpatientImmunization.getStatus());
				patientInpatientImmunalizationInfo.setSourceName(patientInpatientImmunization.getSourceName());
				
				try {
					patientInpatientImmunalizationInfo.setDateAdded(DateUtils.getFormatDate(patientInpatientImmunization.getDateAdded(), "MM/dd/yyyy"));
				} catch (BusinessException e) {
					
					e.printStackTrace();
				}
				patientImnInfo.add(patientInpatientImmunalizationInfo);
			
			
		}
		return patientImnInfo;
	}
		
	private static final String EHR_DATA_DATE_FORMAT = "MM/dd/yyyy";
	
	/**
	 * @param accountId
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	private List<TestResultInfo> fetchPatientLabResults(Integer accountId)throws BusinessException, SystemException{
		
		List<TestResultInfo> testResultInfoList = new ArrayList<TestResultInfo>();
		List<PatientLab> patientLabResultList = accountDAO.getLabResultList(accountId);	
		
        for (PatientLab patientLab : patientLabResultList) {
        	TestResultInfo testResult=new TestResultInfo();
        	testResult.setAccountId(accountId);
        	testResult.setCode(patientLab.getLabGroupCode());
        	testResult.setDate(DateUtils.getFormatDate(patientLab.getLabGroupDate(),EHR_DATA_DATE_FORMAT));
        	testResult.setName(patientLab.getLabGroupName());
        	testResult.setSourceName(patientLab.getSourceName());
        	testResult.setType("Lab");
        	testResult.setResultTypeID(0);
        	testResultInfoList.add(testResult);
		}
		return testResultInfoList;
	}
	
	
	/**
	 * @param accountId
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	private List<TestResultInfo> fetchPatientImagingResults(Integer accountId)throws BusinessException, SystemException{
		
		List<TestResultInfo> patientImagingResultList = new ArrayList<TestResultInfo>();
		List<PatientImaging> fetchImagingDetails = accountDAO.getAccountPatientImaging(accountId);
        
        for (PatientImaging patientImaging : fetchImagingDetails) {
        	TestResultInfo testResult=new TestResultInfo();
        	testResult.setAccountId(accountId);
        	testResult.setCode(patientImaging.getPatientImagingPK().getExamId());
        	testResult.setName(patientImaging.getExamName());
        	testResult.setDate(DateUtils.getFormatDate(patientImaging.getExamDate(),EHR_DATA_DATE_FORMAT));
        	testResult.setSourceName(patientImaging.getSourceName());
        	testResult.setType("Imaging");
        	testResult.setResultTypeID(1);
        	patientImagingResultList.add(testResult);
        }
		
		return patientImagingResultList;
	}
	
	
	@Override
	public NsResponse getTestResult(NsRequest nsRequest) throws BusinessException, SystemException {
		
		Integer accountId = nsRequest.getAccountInfo().getAccountId();
		
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		
		List<TestResultInfo> patientTestInfo = new ArrayList<TestResultInfo>();
		
		patientTestInfo.addAll(fetchPatientLabResults(accountId));	// fetching Patient Test Results
		patientTestInfo.addAll(fetchPatientImagingResults(accountId));	// fetching Patient Imaging Results
        
        List<LinkedAccountsRel> linkedAccounts = linkAccountServiceImpl.getLinkedAccounts(accountId);
        
        // Getting Patient Test Results and Patient Imaging Results for linked accounts
		for(LinkedAccountsRel linkedAccountsRel:linkedAccounts)	
		{
			try{
				patientTestInfo.addAll(fetchPatientLabResults(linkedAccountsRel.getLinkedAccountId()));
				patientTestInfo.addAll(fetchPatientImagingResults(linkedAccountsRel.getLinkedAccountId()));
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
        
        if(patientTestInfo==null || patientTestInfo.isEmpty())
		{
			responseData.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responseData.setDescription(messageSource.getMessage(
					"no.record.found", null,
					nsRequest.getLocale()));
			 nsResponse.setResponseData(responseData);	
			 return nsResponse;
		}
        
        //Collections.sort(patientTestInfo, new TestResult());
        Collections.sort(patientTestInfo, new TestResultComparator());
        
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);		
		nsResponse.setTestResultInfo(patientTestInfo);
		return nsResponse;
	}
	
	@Override
	public NsResponse getTestResultInfo(NsRequest nsRequest) throws BusinessException, SystemException {
		ResponseData responseData = new ResponseData();
		NsResponse nsResponse = new NsResponse();
		int resultTypeID=nsRequest.getResultTypeID();
		List<TestResultInfo> testResultInfo = new ArrayList<TestResultInfo>();
		List<TestResultInfo> testImagingInfo = new ArrayList<TestResultInfo>();
		if(resultTypeID==0){
			List<PatientLab> patientLabResultList = accountDAO.getLabResultListInfo(nsRequest.getAccountInfo().getAccountId(),nsRequest.getLabGroupCode());	
			for (PatientLab patientLab : patientLabResultList)
			{
				TestResultInfo testResult=new TestResultInfo();
				testResult.setTestName(patientLab.getTestName());
				if(patientLab.getLabResult()==null)
				{
					testResult.setLabResult("N/A");
				}
				else{
					testResult.setLabResult(patientLab.getLabResult()+" "+patientLab.getLabUnit());
				}
				if(patientLab.getNormalRangeMin()==null || patientLab.getNormalRangeMax()==null)
				{
					testResult.setNormalRange("N/A");
				}
				
				else{
					testResult.setNormalRange(patientLab.getNormalRangeMin()+"-"+patientLab.getNormalRangeMax()+" "+patientLab.getLabUnit());
				}
				testResult.setLabCode(patientLab.getLabCode());
				testResult.setResultTypeID(nsRequest.getResultTypeID());	
				try{
					if( patientLab.getLabResult()!= null &&  patientLab.getNormalRangeMin()!=null && patientLab.getNormalRangeMax()!=null && (Float.parseFloat(patientLab.getLabResult())< patientLab.getNormalRangeMin() || Float.parseFloat(patientLab.getLabResult())>patientLab.getNormalRangeMax()))
					{
						testResult.setRangeOutOfBound(true);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				testResultInfo.add(testResult);
				
				
			}
			
		}
		else if(resultTypeID==1)
		{
			List<PatientImaging> fetchImagingDetails = accountDAO.getAccountPatientImagingInfo(nsRequest.getAccountInfo().getAccountId(),nsRequest.getLabGroupCode());
			 for (PatientImaging patientImaging : fetchImagingDetails) {
				 TestResultInfo testResult1=new TestResultInfo();
				 testResult1.setExamName(patientImaging.getExamName());
				 if(patientImaging.getPatientImagingPK().getExamId()==null){
					 testResult1.setReportNumber("N/A");
				 }
				 else{
				     testResult1.setReportNumber(patientImaging.getPatientImagingPK().getExamId());
				 }
				 if(patientImaging.getOrderingProvider()==null)
				 {
					 testResult1.setOrderingProvider("N/A");
				 }
				 else{
					 testResult1.setOrderingProvider(patientImaging.getOrderingProvider()); 
				 }
				 if(patientImaging.getExamTechnologist()==null){
					 testResult1.setExamTechnologist("N/A"); 
				 }
				 else{
					 testResult1.setExamTechnologist(patientImaging.getExamTechnologist());
				 }				 
				 testResult1.setExamMessage(patientImaging.getExamMessage());
				 testResult1.setResultTypeID(nsRequest.getResultTypeID());
				 testImagingInfo.add(testResult1);
				 
			 }
		}
		
		 Collections.sort(testResultInfo, new LabComparator());
		 Collections.sort(testImagingInfo, new ImagingResultComparator());
		 List<TestResultInfo> patientTestInfo=new ArrayList<TestResultInfo>();
		 patientTestInfo.addAll(testResultInfo);
		 patientTestInfo.addAll(testImagingInfo);
		 if(patientTestInfo==null || patientTestInfo.isEmpty())
			{
				responseData.setResult(Integer.parseInt(messageSource.getMessage(
						VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
						nsRequest.getLocale())));
				responseData.setDescription(messageSource.getMessage(
						"no.record.found", null,
						nsRequest.getLocale()));
				 nsResponse.setResponseData(responseData);	
				 return nsResponse;
			}
		 
		 
		 responseData.setDescription("Success");
		 responseData.setResult(0);
		 nsResponse.setResponseData(responseData);		
		 nsResponse.setTestResultInfo(patientTestInfo);
		
		return nsResponse;
		
	}
	
 	
}
