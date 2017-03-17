package com.versawork.http.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.versawork.http.dao.AccountServiceDAO;
import com.versawork.http.dao.NotificationDAO;
import com.versawork.http.dataobject.AccountInfo;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.ActivityLog;
import com.versawork.http.service.ActivityLogService;

/**
 * @author Dheeraj
 * 
 * @author Sohaib
 * 
 */
@Service
public class ActivityLogServiceImpl implements ActivityLogService {

	final static Logger LOGGER = LoggerFactory.getLogger(ActivityLogServiceImpl.class);

	@Autowired
	NotificationDAO notificationDAO;

	@Autowired
	private AccountServiceDAO accountDAO;

	@Autowired
	private Gson gson;

	public final String mask = "XXXXXX";

	Account account = new Account();

	public int fetchAccountId(NsRequest nsRequest) throws BusinessException, SystemException {
		if (nsRequest.getAccountInfo() != null) {
			if (nsRequest.getAccountInfo().getUnitNumber() != null) {
				// LOGGER.debug("Activity Log Service Impl Unit Number"+nsRequest.getAccountInfo().getUnitNumber());
				account = accountDAO.getAccountById(nsRequest.getAccountInfo().getAccountId());
				LOGGER.debug("Activity Log Service Impl (fetch)Account Id" + account.getAccountId());
			} else if (nsRequest.getAccountInfo().getUnitNumber() == null) {
				account.setAccountId(0);

			}
		} else if (nsRequest.getViewPatientInfo() != null) {
			if (nsRequest.getViewPatientInfo().getUnitNumber() != null) {
				account = accountDAO.getAccountByUnitNumber(nsRequest.getViewPatientInfo().getUnitNumber());
			} else if (nsRequest.getViewPatientInfo().getUnitNumber() == null) {
				account.setAccountId(0);

			}
		} else if (nsRequest.getNsPatientVisit() != null) {

			{
				account.setAccountId(nsRequest.getNsPatientVisit().getAccountId());
			}
		} else {
			account.setAccountId(account.getAccountId());

		}
		return account.getAccountId();
	}

	@Override
	public void saveActivityLog(int accountId, NsRequest request, NsResponse response, String page)
			throws BusinessException, SystemException {

		String requestJson = gson.toJson(request);
		NsRequest nsRequest = gson.fromJson(requestJson, NsRequest.class);
		AccountInfo requestAccountInfo = new AccountInfo();
		if (nsRequest.getAccountInfo() != null) {
			requestAccountInfo = nsRequest.getAccountInfo();
		} else
			requestAccountInfo.setAuthToken(mask);
		requestAccountInfo.setDeviceToken(mask);
		requestAccountInfo.setEndpointUserId(mask);
		requestAccountInfo.setLoginPin(mask);
		requestAccountInfo.setPassword(mask);
		nsRequest.setAccountInfo(requestAccountInfo);

		String responseJson = gson.toJson(response);
		NsResponse nsResponse = gson.fromJson(responseJson, NsResponse.class);
		AccountInfo responseAccountInfo = new AccountInfo();
		if (nsResponse.getAccountInfo() != null) {
			responseAccountInfo = nsResponse.getAccountInfo();
		}
		responseAccountInfo.setAuthToken(mask);
		responseAccountInfo.setDeviceToken(mask);
		responseAccountInfo.setEndpointUserId(mask);
		responseAccountInfo.setLoginPin(mask);
		responseAccountInfo.setPassword(mask);
		nsResponse.setAccountInfo(responseAccountInfo);

		/*
		 * NsRequest nsRequest = maskNsRequest(request);
		 * 
		 * NsResponse nsResponse = maskNsResponse(response);
		 */

		ActivityLog activityLog = new ActivityLog();
		activityLog.setActivity(page);
		activityLog.setRequest(gson.toJson(nsRequest).toString());
		activityLog.setResponse(gson.toJson(nsResponse).toString());
		activityLog.setAuditAccountId(accountId);
		activityLog.setDeleteFlag(false);
		activityLog.setDateAdded(new Date());

		notificationDAO.persist(activityLog);

	}

	@Override
	public void saveActivityLog(int accountId, NsRequest request, NsResponse response, String page, int patientId)
			throws BusinessException, SystemException {
		Gson gson = new Gson();

		String requestJson = gson.toJson(request);
		NsRequest nsRequest = gson.fromJson(requestJson, NsRequest.class);

		String responseJson = gson.toJson(response);
		NsResponse nsResponse = gson.fromJson(responseJson, NsResponse.class);

		/*
		 * NsRequest nsRequest = maskNsRequest(request);
		 * 
		 * NsResponse nsResponse = maskNsResponse(response);
		 */

		ActivityLog activityLog = new ActivityLog();
		activityLog.setActivity(page);
		activityLog.setRequest(gson.toJson(nsRequest).toString());
		activityLog.setResponse(gson.toJson(nsResponse).toString());
		activityLog.setAuditAccountId(accountId);
		// activityLog.setPatientId(patientId);
		activityLog.setDeleteFlag(false);
		activityLog.setDateAdded(new Date());

		notificationDAO.persist(activityLog);

	}

	public NsRequest maskNsRequest(NsRequest request) {
		NsRequest nsRequest = new NsRequest();
		if (request != null) {
			if (request.getAccountInfo() != null) {
				AccountInfo accountInfo = new AccountInfo();
				try {
					accountInfo.setAccountId(request.getAccountInfo().getAccountId());
					accountInfo.setAccountName(request.getAccountInfo().getAccountName());
					accountInfo.setActivity(request.getAccountInfo().getActivity());
					accountInfo.setClientDatabaseId(request.getAccountInfo().getClientDatabaseId());
					accountInfo.setDateModified(request.getAccountInfo().getDateModified());
					accountInfo.setDateOfBirth(request.getAccountInfo().getDateOfBirth());
					accountInfo.setDeviceType(request.getAccountInfo().getDeviceType());
					if (request.getAccountInfo().getEmail() != null) {
						accountInfo.setEmail(request.getAccountInfo().getEmail());
					}
					accountInfo.setFailedLoginAttempts(request.getAccountInfo().getFailedLoginAttempts());
					accountInfo.setFirstName(request.getAccountInfo().getFirstName());
					accountInfo.setFromDate(request.getAccountInfo().getFromDate());
					accountInfo.setLastLoginDate(request.getAccountInfo().getLastLoginDate());
					accountInfo.setLastName(request.getAccountInfo().getLastName());
					accountInfo.setLoginAuthMode(request.getAccountInfo().getLoginAuthMode());
					accountInfo.setMobilePhoneNumber(request.getAccountInfo().getMobilePhoneNumber());
					accountInfo.setRole(request.getAccountInfo().getRole());
					accountInfo.setRoleName(request.getAccountInfo().getRoleName());
					accountInfo.setToDate(request.getAccountInfo().getToDate());
					accountInfo.setUniqueId(request.getAccountInfo().getUniqueId());
					accountInfo.setUnitNumber(request.getAccountInfo().getUnitNumber());
					accountInfo.setWillShareData(request.getAccountInfo().isWillShareData());
					accountInfo.setWillProvideFeedback(request.getAccountInfo().isWillProvideFeedback());
				} catch (Exception e) {
					LOGGER.info("could not get account info object due to null pointer");
				}
				accountInfo.setAuthToken(mask);
				accountInfo.setDeviceToken(mask);
				accountInfo.setEndpointUserId(mask);
				accountInfo.setLoginPin(mask);
				accountInfo.setPassword(mask);
				nsRequest.setAccountInfo(accountInfo);
			}
			if (request.getActivityLog() != null) {
				nsRequest.setActivityLog(request.getActivityLog());
			}
			if (request.getAuthByFingerprint() != null) {
				nsRequest.setAuthByFingerprint(request.getAuthByFingerprint());
			}
			if (request.getBloodPressureInfo() != null) {
				nsRequest.setBloodPressureInfo(request.getBloodPressureInfo());
			}
			if (request.getBloodPressureReminder() != null) {
				nsRequest.setBloodPressureReminder(request.getBloodPressureReminder());
			}
			if (request.getBloodPressureReminder() != null) {
				nsRequest.setBloodPressureReminder(request.getBloodPressureReminder());
			}
			if (request.getCacheInputs() != null) {
				nsRequest.setCacheInputs(request.getCacheInputs());
			}
			if (request.getCodeSendMode() != null) {
				nsRequest.setCodeSendMode(request.getCodeSendMode());
			}
			if (request.getFeedbackDetails() != null) {
				nsRequest.setFeedbackDetails(request.getFeedbackDetails());
			}
			if (request.getHospitalNoticeId() != null) {
				nsRequest.setHospitalNoticeId(request.getHospitalNoticeId());
			}
			if (request.getMaintananceInfo() != null) {
				nsRequest.setMaintananceInfo(request.getMaintananceInfo());
			}
			if (request.getMedicationManagementReminder() != null) {
				nsRequest.setMedicationManagementReminder(request.getMedicationManagementReminder());
			}
			if (request.getMedicationManagmntInfo() != null) {
				nsRequest.setMedicationManagmntInfo(request.getMedicationManagmntInfo());
			}
			if (request.getNotificationCounter() != null) {
				nsRequest.setNotificationCounter(request.getNotificationCounter());
			}
			if (request.getNsHospitalConfirmation() != null) {
				nsRequest.setNsHospitalConfirmation(request.getNsHospitalConfirmation());
			}
			if (request.getNsPatientAppointment() != null) {
				nsRequest.setNsPatientAppointment(request.getNsPatientAppointment());
			}
			if (request.getNsPatientVisit() != null) {
				nsRequest.setNsPatientVisit(request.getNsPatientVisit());
			}
			if (request.getNsPin() != null) {
				nsRequest.setNsPin(request.getNsPin());
			}
			if (request.getProviderView() != null) {
				nsRequest.setProviderView(request.getProviderView());
			}
			if (request.getSecurityCode() != null) {
				nsRequest.setSecurityCode(request.getSecurityCode());
			}
			if (request.getSecurityQuestions() != null) {
				nsRequest.setSecurityQuestions(request.getSecurityQuestions());
			}
			if (request.getSystemAdministratorInfo() != null) {
				nsRequest.setSystemAdministratorInfo(request.getSystemAdministratorInfo());
			}
			if (request.getTransmitEHR() != null) {
				nsRequest.setTransmitEHR(request.getTransmitEHR());
			}
			if (request.getVdtData() != null) {
				nsRequest.setVdtData(request.getVdtData());
			}
			if (request.getViewPatientInfo() != null) {
				nsRequest.setViewPatientInfo(request.getViewPatientInfo());
			}

		} else
			return request;
		return nsRequest;
	}

	public NsResponse maskNsResponse(NsResponse response) {
		NsResponse nsResponse = new NsResponse();
		if (response != null) {
			if (response.getAccountInfo() != null) {
				AccountInfo accountInfo = new AccountInfo();
				try {
					accountInfo.setAccountId(response.getAccountInfo().getAccountId());
					accountInfo.setAccountName(response.getAccountInfo().getAccountName());
					accountInfo.setActivity(response.getAccountInfo().getActivity());
					accountInfo.setClientDatabaseId(response.getAccountInfo().getClientDatabaseId());
					accountInfo.setDateModified(response.getAccountInfo().getDateModified());
					accountInfo.setDateOfBirth(response.getAccountInfo().getDateOfBirth());
					accountInfo.setDeviceType(response.getAccountInfo().getDeviceType());
					if (response.getAccountInfo().getEmail() != null) {
						accountInfo.setEmail(response.getAccountInfo().getEmail());
					}
					accountInfo.setFailedLoginAttempts(response.getAccountInfo().getFailedLoginAttempts());
					accountInfo.setFirstName(response.getAccountInfo().getFirstName());
					accountInfo.setFromDate(response.getAccountInfo().getFromDate());
					accountInfo.setLastLoginDate(response.getAccountInfo().getLastLoginDate());
					accountInfo.setLastName(response.getAccountInfo().getLastName());
					accountInfo.setLoginAuthMode(response.getAccountInfo().getLoginAuthMode());
					accountInfo.setMobilePhoneNumber(response.getAccountInfo().getMobilePhoneNumber());
					accountInfo.setRole(response.getAccountInfo().getRole());
					accountInfo.setRoleName(response.getAccountInfo().getRoleName());
					accountInfo.setToDate(response.getAccountInfo().getToDate());
					accountInfo.setUniqueId(response.getAccountInfo().getUniqueId());
					accountInfo.setUnitNumber(response.getAccountInfo().getUnitNumber());
					accountInfo.setWillShareData(response.getAccountInfo().isWillShareData());
					accountInfo.setWillProvideFeedback(response.getAccountInfo().isWillProvideFeedback());
				} catch (Exception e) {
					LOGGER.info("could not get account info object due to null pointer");
				}
				accountInfo.setAuthToken(mask);
				accountInfo.setDeviceToken(mask);
				accountInfo.setEndpointUserId(mask);
				accountInfo.setLoginPin(mask);
				accountInfo.setPassword(mask);
				nsResponse.setAccountInfo(accountInfo);
			}
			if (response.getActivityLog() != null) {
				nsResponse.setActivityLog(response.getActivityLog());
			}
			if (response.getAllergiesInfoList() != null) {
				nsResponse.setAllergiesInfoList(response.getAllergiesInfoList());
			}
			if (response.getBloodPressureInfoList() != null) {
				nsResponse.setBloodPressureInfoList(response.getBloodPressureInfoList());
			}
			if (response.getBloodPressureList() != null) {
				nsResponse.setBloodPressureList(response.getBloodPressureList());
			}
			if (response.getBloodPressureReminderList() != null) {
				nsResponse.setBloodPressureReminderList(response.getBloodPressureReminderList());
			}
			if (response.getBloodPressureRemindersCount() != null) {
				nsResponse.setBloodPressureRemindersCount(response.getBloodPressureRemindersCount());
			}
			if (response.getBloodPressureWarningMessage() != null) {
				nsResponse.setBloodPressureWarningMessage(response.getBloodPressureWarningMessage());
			}
			nsResponse.setBPTotalCount(response.getBPTotalCount());
			nsResponse.setBPYesCount(response.getBPYesCount());
			if (response.getCacheInputs() != null) {
				nsResponse.setCacheInputs(response.getCacheInputs());
			}
			if (response.getCarePlanInfoList() != null) {
				nsResponse.setCarePlanInfoList(response.getCarePlanInfoList());
			}
			if (response.getCateTeamInfoList() != null) {
				nsResponse.setCateTeamInfoList(response.getCateTeamInfoList());
			}
			if (response.getCodeSendOptions() != null) {
				nsResponse.setCodeSendOptions(response.getCodeSendOptions());
			}
			if (response.getFeedBack() != null) {
				nsResponse.setFeedBack(response.getFeedBack());
			}
			if (response.getHopitalizationReason() != null) {
				nsResponse.setHopitalizationReason(response.getHopitalizationReason());
			}
			if (response.getHospitalNotices() != null) {
				nsResponse.setHospitalNotices(response.getHospitalNotices());
			}
			if (response.getHospitalNotificationsCount() != null) {
				nsResponse.setHospitalNotificationsCount(response.getHospitalNotificationsCount());
			}
			if (response.getLabResultInfoList() != null) {
				nsResponse.setLabResultInfoList(response.getLabResultInfoList());
			}
			if (response.getMedicationInfoList() != null) {
				nsResponse.setMedicationInfoList(response.getMedicationInfoList());
			}
			if (response.getMedicationManagementReminderList() != null) {
				nsResponse.setMedicationManagementReminderList(response.getMedicationManagementReminderList());
			}
			if (response.getMedicationManagmntInfo() != null) {
				nsResponse.setMedicationManagmntInfo(response.getMedicationManagmntInfo());
			}
			if (response.getMedicationManagmntInfoList() != null) {
				nsResponse.setMedicationManagmntInfoList(response.getMedicationManagmntInfoList());
			}
			if (response.getMedicationRemindersCount() != null) {
				nsResponse.setMedicationRemindersCount(response.getMedicationRemindersCount());
			}
			if (response.getMedicationTotalCount() != null) {
				nsResponse.setMedicationTotalCount(response.getMedicationTotalCount());
			}
			if (response.getMedicationWarningMessage() != null) {
				nsResponse.setMedicationWarningMessage(response.getMedicationWarningMessage());
			}
			if (response.getMedicationYesCount() != null) {
				nsResponse.setMedicationYesCount(response.getMedicationYesCount());
			}
			if (response.getMuData() != null) {
				nsResponse.setMuData(response.getMuData());
			}
			if (response.getNotificationsCount() != null) {
				nsResponse.setNotificationsCount(response.getNotificationsCount());
			}
			if (response.getNotificationWarningCount() != null) {
				nsResponse.setNotificationWarningCount(response.getNotificationWarningCount());
			}
			if (response.getNsDoctorsList() != null) {
				nsResponse.setNsDoctorsList(response.getNsDoctorsList());
			}
			if (response.getNsFeedBackList() != null) {
				nsResponse.setNsFeedBackList(response.getNsFeedBackList());
			}
			if (response.getNsFrequencyList() != null) {
				nsResponse.setNsFrequencyList(response.getNsFrequencyList());
			}
			if (response.getNsMedicationDosageList() != null) {
				nsResponse.setNsMedicationDosageList(response.getNsMedicationDosageList());
			}
			if (response.getNsMedicationKindList() != null) {
				nsResponse.setNsMedicationKindList(response.getNsMedicationKindList());
			}
			if (response.getNsMedicationMethodList() != null) {
				nsResponse.setNsMedicationMethodList(response.getNsMedicationMethodList());
			}
			if (response.getNsPatientAppointments() != null) {
				nsResponse.setNsPatientAppointments(response.getNsPatientAppointments());
			}
			if (response.getNsServicesList() != null) {
				nsResponse.setNsServicesList(response.getNsServicesList());
			}
			if (response.getPatientInpatientDiagnosisInfoList() != null) {
				nsResponse.setPatientInpatientDiagnosisInfoList(response.getPatientInpatientDiagnosisInfoList());
			}
			if (response.getPatientInpatientFunctionalStatusInfoList() != null) {
				nsResponse.setPatientInpatientFunctionalStatusInfoList(response
						.getPatientInpatientFunctionalStatusInfoList());
			}
			if (response.getPatientInpatientImmunalizationInfoList() != null) {
				nsResponse.setPatientInpatientImmunalizationInfoList(response
						.getPatientInpatientImmunalizationInfoList());
			}
			if (response.getPatientInpatientMetadataInfo() != null) {
				nsResponse.setPatientInpatientMetadataInfo(response.getPatientInpatientMetadataInfo());
			}
			if (response.getPatientLabResults() != null) {
				nsResponse.setPatientLabResults(response.getPatientLabResults());
			}
			if (response.getPatientPrescriptions() != null) {
				nsResponse.setPatientPrescriptions(response.getPatientPrescriptions());
			}
			if (response.getPatientProblemInfoList() != null) {
				nsResponse.setPatientProblemInfoList(response.getPatientProblemInfoList());
			}
			if (response.getPatientVisitList() != null) {
				nsResponse.setPatientVisitList(response.getPatientVisitList());
			}
			if (response.getProcedureInfoList() != null) {
				nsResponse.setProcedureInfoList(response.getProcedureInfoList());
			}
			if (response.getRemindersCount() != null) {
				nsResponse.setRemindersCount(response.getRemindersCount());
			}
			if (response.getResponseData() != null) {
				nsResponse.setResponseData(response.getResponseData());
			}
			if (response.getResponseDescription() != null) {
				nsResponse.setResponseDescription(response.getResponseDescription());
			}
			if (response.getSecurityCode() != null) {
				nsResponse.setSecurityCode(response.getSecurityCode());
			}
			if (response.getSecurityQuestions() != null) {
				nsResponse.setSecurityQuestions(response.getSecurityQuestions());
			}
			if (response.getSystemAdministratorInfo() != null) {
				nsResponse.setSystemAdministratorInfo(response.getSystemAdministratorInfo());
			}
			if (response.getVdtData() != null) {
				nsResponse.setVdtData(response.getVdtData());
			}
			if (response.getViewMuData() != null) {
				nsResponse.setViewMuData(response.getViewMuData());
			}
			if (response.getViewPatientInfo() != null) {
				nsResponse.setViewPatientInfo(response.getViewPatientInfo());
			}
			if (response.getVisitType() != null) {
				nsResponse.setVisitType(response.getVisitType());
			}
			if (response.getVitalSigns() != null) {
				nsResponse.setVitalSigns(response.getVitalSigns());
			}
			if (response.getVitalSignsList() != null) {
				nsResponse.setVitalSignsList(response.getVitalSignsList());
			}
			if (response.getWarningCount() != null) {
				nsResponse.setWarningCount(response.getWarningCount());
			}
			if (response.getWarningMessage() != null) {
				nsResponse.setWarningMessage(response.getWarningMessage());
			}

		} else
			return response;
		return nsResponse;
	}
}
