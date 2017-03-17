package com.versawork.http.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.hibernate.mapping.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.AccountServiceDAO;
import com.versawork.http.dao.GetDoctorListDAO;
import com.versawork.http.dao.LinkedAccountDao;
import com.versawork.http.dao.MedicationMgntDAO;
import com.versawork.http.dao.NotificationDAO;
import com.versawork.http.dao.PatientAppointmentServiceDAO;
import com.versawork.http.dao.PatientBillingDAO;
import com.versawork.http.dao.WarningServiceDao;
import com.versawork.http.dataobject.BloodPressureInfo;
import com.versawork.http.dataobject.FeedListInfo;
import com.versawork.http.dataobject.MedicationManagementReminderInfo;
import com.versawork.http.dataobject.MedicationManagmntInfo;
import com.versawork.http.dataobject.NotificationsCount;
import com.versawork.http.dataobject.NsPatientAppointment;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.NsScheduleTime;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.dataobject.WarningMessages;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.AccountBloodPressureEngage;
import com.versawork.http.model.AccountBloodPressureEngageResponse;
import com.versawork.http.model.AccountBloodPressureManagement;
import com.versawork.http.model.AccountMedicationEngage;
import com.versawork.http.model.AccountMedicationManagementReminder;
import com.versawork.http.model.AccountMedicationManagementSchedule;
import com.versawork.http.model.AccountNotificationHistory;
import com.versawork.http.model.AccountReminderMedicationRelation;
import com.versawork.http.model.FacilityProvider;
import com.versawork.http.model.FeedInfo;
import com.versawork.http.model.LinkedAccountsRel;
import com.versawork.http.model.PatientAppointmentRequest;
import com.versawork.http.model.PatientBillSummary;
import com.versawork.http.model.PatientUpcomingAppointment;
import com.versawork.http.model.PatientVisit;
import com.versawork.http.model.Warnings;
import com.versawork.http.service.LinkAccountService;
import com.versawork.http.service.NotificationService;
import com.versawork.http.utils.DateUtils;
import com.versawork.http.utils.NsResponseUtils;

/**
 * @author Dheeraj
 * 
 * @author Sohaib
 * 
 */
@Component
public class NotificationServiceImpl implements NotificationService {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

	private static final String CLIENT_DATABASE_ID = "client.database.id";
	/*
	 * @Autowired private HospitalNoticeServiceDao hospitalNoticeServiceDao;
	 */

	@Autowired
	private NotificationDAO notificationDAO;

	@Autowired
	private GetDoctorListDAO getDoctorListDAO;

	@Autowired
	private MedicationMgntDAO medicationMgntDAO;

	@Autowired
	private PatientAppointmentServiceDAO patientAppointmentServiceDAO;

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private WarningServiceDao warningServiceDao;

	@Autowired
	private HospitalNoticeServiceImpl hospitalNoticeServiceImpl;
	@Autowired
	private AccountServiceDAO accountServiceDAO;

	@Autowired
	private LinkedAccountDao linkedAccountDao;

	@Autowired
	private PatientBillingDAO patientBillingDAO;

	@Autowired 
	private LinkAccountService linkAccountServiceImpl;
	
	/*
	 * NsResponse nsResponse = new NsResponse(); ResponseData responsedata = new
	 * ResponseData();
	 */

	/*
	 * @Autowired private MedicationMgntDAO medicationMgntDAO;
	 */

	@Override
	public NsResponse getBloodPressureReminderResponse(NsRequest nsRequest) throws BusinessException, SystemException {

		List<AccountBloodPressureEngage> accountBloodPressureEngageList = notificationDAO
				.getAccountBloodPressureEngageByAccountId(nsRequest.getBloodPressureInfo().getAccountId(),
						DateUtils.getDate(nsRequest.getBloodPressureInfo().getFromDate(), "MM/dd/yyyy"),
						DateUtils.getDate(nsRequest.getBloodPressureInfo().getToDate(), "MM/dd/yyyy"));

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		List<BloodPressureInfo> bloodPressureInfoList = new ArrayList<BloodPressureInfo>();

		if (accountBloodPressureEngageList.isEmpty()) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage("no.responses.present", null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}

		for (AccountBloodPressureEngage accountBloodPressureEngage : accountBloodPressureEngageList) {
			if (accountBloodPressureEngage.getReminderDate() != null) {
				BloodPressureInfo bloodPressureInfo = new BloodPressureInfo();
				bloodPressureInfo.setSys(accountBloodPressureEngage.getSys());
				bloodPressureInfo.setDia(accountBloodPressureEngage.getDia());
				bloodPressureInfo.setPulse(accountBloodPressureEngage.getPulse());
				bloodPressureInfo.setComment(accountBloodPressureEngage.getComment());

				bloodPressureInfo.setDateAdded(DateUtils.getFormatDate(accountBloodPressureEngage.getDateAdded(),
						"MM/dd/yyyy"));
				bloodPressureInfoList.add(bloodPressureInfo);
			}
		}

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);
		nsResponse.setBloodPressureList(bloodPressureInfoList);

		return nsResponse;
	}

	// //////////////////////////////////@TODO
	@Override
	public NsResponse getMedicationReminderResponse(NsRequest nsRequest) throws BusinessException, SystemException {

		List<AccountMedicationEngage> accountMedicationEngageList = notificationDAO.getReminderResponseByActId(
				nsRequest.getMedicationManagementReminder().getAccountId(),
				DateUtils.getDate(nsRequest.getMedicationManagementReminder().getFromDate(), "MM/dd/yyyy"),
				DateUtils.getDate(nsRequest.getMedicationManagementReminder().getToDate(), "MM/dd/yyyy"));

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		int yesCount = 0;
		int noCount = 0;

		List<MedicationManagementReminderInfo> medicationManagementReminderList = new ArrayList<MedicationManagementReminderInfo>();

		if (accountMedicationEngageList.isEmpty()) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage("no.responses.present.medication", null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}

		Iterator<AccountMedicationEngage> iter = accountMedicationEngageList.iterator();

		while (iter.hasNext()) {
			AccountMedicationEngage accountMedicationEngages = iter.next();

			if (accountMedicationEngages.getResponseId().getResponseId() == 1) {
				yesCount++;
			} else {
				noCount++;
			}
		}
		if (yesCount > noCount) {
			nsResponse.setResponseDescription("Positive");
		} else {
			nsResponse.setResponseDescription("Negative");
		}

		for (AccountMedicationEngage accountMedicationEngage : accountMedicationEngageList) {
			if (accountMedicationEngage.getReminderDate() != null)

			{
				MedicationManagementReminderInfo medicationManagementReminderInfo = new MedicationManagementReminderInfo();
				medicationManagementReminderInfo.setResponseId(accountMedicationEngage.getResponseId().getResponseId());

				AccountMedicationManagementReminder accountMedicationManagementReminder = medicationMgntDAO
						.getDetailsByRemId(accountMedicationEngage.getAccountMedicationManagementReminderId()
								.getAccountMedicationManagementReminderId());
				medicationManagementReminderInfo.setMedicationType(accountMedicationManagementReminder
						.getAccountReminderMedicationRelationList().get(0).getAccountMedicationManagementId()
						.getMedicationKindId().getMedicationKind());

				/*medicationManagementReminderInfo.setMedicationDosageId(accountMedicationManagementReminder
						.getMedicationDosageId().getMedicationDosageId());*/
				medicationManagementReminderInfo.setDosage(accountMedicationManagementReminder.getDosage());
				// medicationManagementReminderInfo.setMedicationType(accountMedicationManagementReminder.getMedicationDosageId().getMedicationDosage());

				List<AccountMedicationManagementSchedule> accountMedicationManagementSchedulesListFromDB = accountMedicationManagementReminder
						.getAccountMedicationManagementScheduleList();

				List<NsScheduleTime> nsMedicationScheduleList = new ArrayList<NsScheduleTime>();

				for (AccountMedicationManagementSchedule accountMedicationManagementSchedule : accountMedicationManagementSchedulesListFromDB) {
					NsScheduleTime medicationScheduleTime = new NsScheduleTime();
					medicationScheduleTime.setScheduleId(accountMedicationManagementSchedule
							.getAccountMedicationManagementScheduleId());

					medicationScheduleTime.setReminderTime(DateUtils.getFormatDate(
							accountMedicationManagementSchedule.getReminderTime(),
							VersaWorkConstant.TIME_FORMAT_12_HOUR)); // Don't
																		// change
																		// the
																		// Time
																		// pattern(dependency
																		// on
																		// IOS)
					nsMedicationScheduleList.add(medicationScheduleTime);
				}
				medicationManagementReminderInfo.setNsScheduleTimeList(nsMedicationScheduleList);
				medicationManagementReminderList.add(medicationManagementReminderInfo);

				List<AccountReminderMedicationRelation> accountReminderMedicationRelationListFromDB = accountMedicationManagementReminder
						.getAccountReminderMedicationRelationList();
				List<MedicationManagmntInfo> medicationInfoList = new ArrayList<MedicationManagmntInfo>();

				for (AccountReminderMedicationRelation accountReminderMedicationRelation : accountReminderMedicationRelationListFromDB) {
					MedicationManagmntInfo medicationInfo = new MedicationManagmntInfo();
					if (accountReminderMedicationRelation.getAccountMedicationManagementId() != null) {
						// medicationInfo.setMedictnMgmtId(accountReminderMedicationRelation.getAccountMedicationManagementId().getAccountMedicationManagementId());
						medicationInfo.setMedicationName(accountReminderMedicationRelation
								.getAccountMedicationManagementId().getMedicationName());
						// medicationInfo.setRxNumber(accountReminderMedicationRelation.getAccountMedicationManagementId().getRxNumber());
						// medicationInfo.setNotes(accountReminderMedicationRelation.getAccountMedicationManagementId().getNotes());
						// medicationInfo.setMedicationKindDesc(accountReminderMedicationRelation.getAccountMedicationManagementId().getMedicationKindId().getMedicationKind());
						// medicationInfo.setMedicationMethodDesc(accountReminderMedicationRelation.getAccountMedicationManagementId().getMedicationMethodId().getMedicationMethod());
						if (accountReminderMedicationRelation.getDateAdded() != null) {

							medicationInfo.setDateAdded(DateUtils.getFormatDate(
									accountReminderMedicationRelation.getDateAdded(), "MM/dd/yyyy"));
						}
						{
							medicationInfo.setDateAdded(null);
						}
						medicationInfoList.add(medicationInfo);
					}
				}
				medicationManagementReminderInfo.setMedicationManagmntInfoList(medicationInfoList);

				medicationManagementReminderInfo.setComment(accountMedicationEngage.getComment());

				medicationManagementReminderInfo.setDateAdded(DateUtils.getFormatDate(
						accountMedicationEngage.getDateAdded(), "MM/dd/yyyy"));
				medicationManagementReminderList.add(medicationManagementReminderInfo);
			}
		}

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);
		nsResponse.setMedicationManagementReminderList(medicationManagementReminderList);

		return nsResponse;
	}

	@Override
	public NsResponse getPatientScheduledAppointments(NsRequest nsRequest) throws BusinessException, SystemException {
		List<PatientAppointmentRequest> patientAppointmentRequestList = notificationDAO.getApptsByAccountIdSchdleDate(
				nsRequest.getAccountInfo().getAccountId(),
				DateUtils.getDate(nsRequest.getAccountInfo().getFromDate(), "MM/dd/yyyy"),
				DateUtils.getDate(nsRequest.getAccountInfo().getToDate(), "MM/dd/yyyy"));

		// List<PatientAppointmentRequest> patientAppointmentRequests =
		// account.getPatientAppointmentRequestList();
		if (patientAppointmentRequestList.isEmpty()) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage("no.patnt.appntmnt", null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}
		List<NsPatientAppointment> nsPatientAppointments = new ArrayList<NsPatientAppointment>();
		for (PatientAppointmentRequest patientAppointmentRequest : patientAppointmentRequestList) {
			if (patientAppointmentRequest.getAppointmentDate() != null) {
				NsPatientAppointment nsPatientAppointment = new NsPatientAppointment();
				nsPatientAppointment.setPatientAppointmentRequestId(patientAppointmentRequest
						.getPatientAppointmentRequestId());
				nsPatientAppointment.setAppointmentDate(DateUtils.getFormatDate(
						patientAppointmentRequest.getAppointmentDate(), "MM/dd/yyyy"));
				nsPatientAppointment.setAppointmentTime(DateUtils.getFormatDate(
						patientAppointmentRequest.getAppointmentDate(), VersaWorkConstant.TIME_FORMAT_12_HOUR));
				nsPatientAppointment.setComment(patientAppointmentRequest.getComment());
				nsPatientAppointment.setContactEmail(patientAppointmentRequest.getContactEmail());
				nsPatientAppointment.setContactPhone(patientAppointmentRequest.getContactPhone());
				nsPatientAppointment.setProviderId(patientAppointmentRequest.getProviderId());
				nsPatientAppointment.setProviderName(patientAppointmentRequest.getProviderName());
				nsPatientAppointment.setRequestSentDate(DateUtils.getFormatDate(
						patientAppointmentRequest.getRequestSentDate(), "MM/dd/yyyy"));
				nsPatientAppointment.setRequestSentTime(DateUtils.getFormatDate(
						patientAppointmentRequest.getRequestSentDate(), VersaWorkConstant.TIME_FORMAT_12_HOUR));

				Account account = accountServiceDAO.getAccountById(patientAppointmentRequest.getAccountId());

				nsPatientAppointment.setPatientName(account.getFirstName());

				nsPatientAppointments.add(nsPatientAppointment);
			}
		}
		NsResponse nsResponse = NsResponseUtils.getNsResponse(
				null,
				messageSource.getMessage("rsp.data.dscrptn.success", null, nsRequest.getLocale()),
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));
		nsResponse.setNsPatientAppointments(nsPatientAppointments);
		return nsResponse;
	}

	@Override
	public NsResponse getPatientConfirmedAppointments(NsRequest nsRequest) throws BusinessException, SystemException {
		int clientDatabaseId = Integer
				.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, Locale.getDefault()));
		List<PatientUpcomingAppointment> patientConfirmedAppointmentRequestList = notificationDAO
				.getConfApptsByAccountIdByConfDate(nsRequest.getAccountInfo().getAccountId(),
						DateUtils.getDate(nsRequest.getAccountInfo().getFromDate(), "MM/dd/yyyy"),
						DateUtils.getDate(nsRequest.getAccountInfo().getToDate(), "MM/dd/yyyy"), clientDatabaseId);
		// List<PatientAppointmentRequest> patientAppointmentRequests =
		// account.getPatientAppointmentRequestList();
		if (patientConfirmedAppointmentRequestList.isEmpty()) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage("no.patnt.appntmnt", null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}
		List<NsPatientAppointment> nsPatientAppointments = new ArrayList<NsPatientAppointment>();
		FacilityProvider facilityProvider = null;
		for (PatientUpcomingAppointment patientAppointmentRequest : patientConfirmedAppointmentRequestList) {
			if (patientAppointmentRequest.getAppointmentDate() != null) {
				NsPatientAppointment nsPatientAppointment = new NsPatientAppointment();
				nsPatientAppointment.setAppointmentId(patientAppointmentRequest.getPatientUpcomingAppointmentPK()
						.getAppointmentId());

				nsPatientAppointment.setAppointmentDate(DateUtils.getFormatDate(
						patientAppointmentRequest.getAppointmentDate(), "MM/dd/yyyy - hh:mm a"));
				nsPatientAppointment.setAppointmentTime(patientAppointmentRequest.getAppointmentTime().toString());
				nsPatientAppointment.setComment(patientAppointmentRequest.getTypeDescription());
				// nsPatientAppointment.setContactEmail(patientAppointmentRequest.getContactEmail());
				// nsPatientAppointment.setContactPhone(patientAppointmentRequest.getContactPhone());
				nsPatientAppointment.setProviderName(patientAppointmentRequest.getProviderName());
				nsPatientAppointment.setRequestSentDate(patientAppointmentRequest.getAppointmentDate().toString());
				// nsPatientAppointment.setPatientName(new
				// Account(patientAppointmentRequest.getAccount().getAccountId()).getMedicalRecordNumber());
				nsPatientAppointment.setConfirmationDate(DateUtils.getFormatDate(
						patientAppointmentRequest.getDateAdded(), "MM/dd/yyyy - hh:mm a"));
				nsPatientAppointment.setConfirmedBy(patientAppointmentRequest.getProviderName());

				try {
					facilityProvider = getDoctorListDAO.getProviderByProviderId(
							patientAppointmentRequest.getProviderId(), clientDatabaseId);
					nsPatientAppointment.setProviderAddress(facilityProvider.getAddress1());
					nsPatientAppointment.setProviderAddress2(facilityProvider.getAddress2());
					nsPatientAppointment.setProviderAddress3(facilityProvider.getCity() + ","
							+ facilityProvider.getState() + facilityProvider.getPostalCode());
				} catch (BusinessException exp) {
					nsPatientAppointment.setProviderAddress(VersaWorkConstant.NOT_APPLICABLE);
					nsPatientAppointment.setProviderAddress2("");
					nsPatientAppointment.setProviderAddress3("");
				}

				nsPatientAppointments.add(nsPatientAppointment);
			}
		}
		NsResponse nsResponse = NsResponseUtils.getNsResponse(
				null,
				messageSource.getMessage("rsp.data.dscrptn.success", null, nsRequest.getLocale()),
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));
		nsResponse.setNsPatientAppointments(nsPatientAppointments);
		return nsResponse;
	}

	@Override
	public List<WarningMessages> getBloodPressureNotificationInactiveAlert(NsRequest nsRequest)
			throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		String message = new String();
		Boolean followingBloodPressureReminder = true;
		Calendar calendar14 = Calendar.getInstance();
		calendar14.add(Calendar.DATE, -14);

		Calendar calendar7 = Calendar.getInstance();
		calendar7.add(Calendar.DATE, -7);

		Calendar current_3 = Calendar.getInstance();
		current_3.add(Calendar.DATE, -3);

		Calendar tomm = Calendar.getInstance();
		tomm.add(Calendar.DATE, +1);

		Date date = new Date();
		Boolean flag = true;

		WarningMessages warnList = new WarningMessages();
		List<WarningMessages> warnListMessages = new ArrayList<WarningMessages>();

		List<AccountBloodPressureManagement> accountBloodPressureManagementList = notificationDAO
				.getAccountBlodPresrRemindersByActIdDelFlag(nsRequest.getAccountInfo().getAccountId());

		if (accountBloodPressureManagementList.isEmpty() || accountBloodPressureManagementList == null) {
			warnList = new WarningMessages();
			warnList.setWarningMessage(messageSource.getMessage("no.bloodpressure.reminders.present", null,
					nsRequest.getLocale()));
			warnList.setIsWarningPresent(false);
			warnList.setWarningDate(DateUtils.getFormatDate(addDays(date, -0), "MM/dd/yyyy"));
			warnList.setWarningDay("Today");
			warnListMessages.add(warnList);
			return warnListMessages;
		}

		String notificationDate = null;
		for (AccountBloodPressureManagement accountBloodPressureManagement : accountBloodPressureManagementList) {
			followingBloodPressureReminder = true;

			followingBloodPressureReminder = checkBpReminderFor3Days(accountBloodPressureManagement);

			if (!followingBloodPressureReminder) {
				message = "Bloodpressure reading has not been taken from past 3 days";

				notificationDate = (DateUtils.getFormatDate(addDays(date, -3), "MM/dd/yyyy"));
				followingBloodPressureReminder = checkBpReminderFor7Days(accountBloodPressureManagement);
				if (!followingBloodPressureReminder) {
					message = "Bloodpressure reading has not been taken from past 7 days";

					notificationDate = (DateUtils.getFormatDate(addDays(date, -7), "MM/dd/yyyy"));
					followingBloodPressureReminder = checkBpReminderFor14Days(accountBloodPressureManagement);

					if (!followingBloodPressureReminder) {
						message = "Bloodpressure reading has not been taken from past 14 days";
						notificationDate = (DateUtils.getFormatDate(addDays(date, -14), "MM/dd/yyyy"));
					}
				}
				// set message
				warnList = new WarningMessages();
				warnList.setWarningMessage(message);
				warnList.setIsWarningPresent(true);
				warnList.setWarningDate(notificationDate);
				warnList.setWarningDay("Last Week");
				warnListMessages.add(warnList);
			} else {
				flag = false;
			}
		}

		if (!flag) {
			warnList = new WarningMessages();
			warnListMessages = new ArrayList<WarningMessages>();
			warnList.setWarningMessage(messageSource.getMessage("no.bloodpressure.reminders.present", null,
					nsRequest.getLocale()));
			warnList.setIsWarningPresent(false);
			warnList.setWarningDate(DateUtils.getFormatDate(addDays(date, -0), "MM/dd/yyyy"));
			warnList.setWarningDay("Today");
			warnListMessages.add(warnList);
			return warnListMessages;
		}

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);
		nsResponse.setWarningMessage(warnListMessages);

		return warnListMessages;
	}

	public NsResponse getWarningMessagesOnLogin(NsRequest nsRequest) throws SystemException, BusinessException {
		int warningCount;

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		List<WarningMessages> medicationWarnigns;
		List<WarningMessages> bloodPressureWarnigns;
		medicationWarnigns = getMedicationNotificationInactiveAlert(nsRequest);
		bloodPressureWarnigns = getBloodPressureNotificationInactiveAlert(nsRequest);

		/*
		 * LOGIC TO FIND DELETE OR PERSISTING MEDICATION NOTIFICATION IN
		 * DATABASE
		 */
		List<Warnings> fetchMedicationWarnigns = warningServiceDao.findMedicationWarnings(nsRequest.getAccountInfo()
				.getAccountId());
		Iterator<Warnings> medicationIterator = null;
		List<Warnings> toSaveList = new ArrayList<Warnings>();
		List<Warnings> unviewedList = new ArrayList<Warnings>();
		boolean flag = true;
		for (WarningMessages wm : medicationWarnigns) {
			medicationIterator = fetchMedicationWarnigns.iterator();
			flag = true;
			if (wm.getWarningMessage().equalsIgnoreCase(
					messageSource.getMessage("no.medication.reminders.present", null, nsRequest.getLocale()))) {
				continue;
			}
			while (medicationIterator.hasNext()) {
				Warnings fetchedWarning = medicationIterator.next();

				if (wm.getWarningMessage().equalsIgnoreCase(fetchedWarning.getWarningMessage())) {
					if (!fetchedWarning.getIsViewed()) {
						unviewedList.add(fetchedWarning);
					}
					flag = false;
					medicationIterator.remove();
					break;
				}
			}
			if (flag) {
				toSaveList.add(getWarning(wm, nsRequest.getAccountInfo().getAccountId(), "medication_warning"));
			}
		}
		/* SAVE MEDICATION WARNING */
		warningServiceDao.save(toSaveList);
		toSaveList.addAll(unviewedList);
		/* REMOVE MEDICATION WARNING */
		deactive(fetchMedicationWarnigns);

		/*
		 * LOGIC TO FIND DELETE OR PERSISTING BLOOD PRESSURE NOTIFICATION IN
		 * DATABASE
		 */
		List<Warnings> fetchBloodPressureWarnigns = warningServiceDao.findBloodPressureWarnings(nsRequest
				.getAccountInfo().getAccountId());
		Iterator<Warnings> bloodPressureIterator = null;// .iterator();
		List<Warnings> toSaveBloodPressureList = new ArrayList<Warnings>();
		unviewedList = new ArrayList<Warnings>();
		for (WarningMessages wm : bloodPressureWarnigns) {
			bloodPressureIterator = fetchBloodPressureWarnigns.iterator();
			if (wm.getWarningMessage().equalsIgnoreCase(
					messageSource.getMessage("no.bloodpressure.reminders.present", null, nsRequest.getLocale()))) {
				continue;
			}
			flag = true;
			while (bloodPressureIterator.hasNext()) {
				Warnings fetchedWarning = bloodPressureIterator.next();
				if (wm.getWarningMessage().equals(fetchedWarning.getWarningMessage())) {
					if (!fetchedWarning.getIsViewed()) {
						unviewedList.add(fetchedWarning);
					}
					flag = false;
					bloodPressureIterator.remove();
					break;
				}
			}
			if (flag) {
				toSaveBloodPressureList.add(getWarning(wm, nsRequest.getAccountInfo().getAccountId(),
						"bloodpressure_warning"));
			}
		}
		/* SAVE BLOOD PRESSURE WARNING */

		warningServiceDao.save(toSaveBloodPressureList);
		toSaveBloodPressureList.addAll(unviewedList);
		/* REMOVE BLOOD PRESSURE WARNING */
		deactive(fetchBloodPressureWarnigns);

		nsResponse.setBloodPressureWarningMessage(bloodPressureWarnigns);
		nsResponse.setMedicationWarningMessage(medicationWarnigns);
		// warningCount = medicationWarnigns.size() +
		// bloodPressureWarnigns.size();
		warningCount = toSaveBloodPressureList.size() + toSaveList.size();
		/*
		 * if(medicationWarnigns.size()>0) {
		 * if(medicationWarnigns.get(0).getWarningMessage
		 * ().equalsIgnoreCase("No medication reminders present")) {
		 * warningCount--; } } if(bloodPressureWarnigns.size()>0) {
		 * if(bloodPressureWarnigns .get(0).getWarningMessage().equalsIgnoreCase
		 * ("No BloodPressure reminders present")) { warningCount--; }}
		 */

		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);
		// //////for notification Count service
		nsResponse.setNotificationWarningCount(warningCount);

		return nsResponse;
	}

	private void deactive(List<Warnings> fetchMedicationWarnigns) {
		for (Warnings warnings : fetchMedicationWarnigns) {
			warnings.setIsActive(false);
			warningServiceDao.update(warnings);
		}

	}

	private Warnings getWarning(WarningMessages wm, int accountId, String warningType) {
		Warnings warning = new Warnings();
		warning.setAccountId(accountId);
		warning.setIsWarningPresent(wm.getIsWarningPresent());
		warning.setWarningDate(wm.getWarningDate());
		warning.setWarningDay(wm.getWarningDay());
		warning.setWarningMessage(wm.getWarningMessage());
		warning.setIsViewed(false);
		warning.setWarningType(warningType);
		warning.setIsActive(true);
		return warning;
	}

	/*
	 * private MedicationWarnings getMedicationWarning(WarningMessages wm, int
	 * accountId) { MedicationWarnings warning = new MedicationWarnings();
	 * warning.setAccountId(accountId);
	 * warning.setIsWarningPresent(wm.getIsWarningPresent());
	 * warning.setWarningDate(wm.getWarningDate());
	 * warning.setWarningDay(wm.getWarningDay());
	 * warning.setWarningMessage(wm.getWarningMessage());
	 * warning.setIsViewed(false); return warning; }
	 * 
	 * private BloodPressureWarnings getBloodPressureWarnings(WarningMessages
	 * wm, int accountId) { BloodPressureWarnings warning = new
	 * BloodPressureWarnings(); warning.setAccountId(accountId);
	 * warning.setIsWarningPresent(wm.getIsWarningPresent());
	 * warning.setWarningDate(wm.getWarningDate());
	 * warning.setWarningDay(wm.getWarningDay());
	 * warning.setWarningMessage(wm.getWarningMessage());
	 * warning.setIsViewed(false); return warning; }
	 */

	public List<WarningMessages> getMedicationNotificationInactiveAlert(NsRequest nsRequest) throws BusinessException,
			SystemException {

		String message = new String();
		Boolean followingMedReminder = true;

		Date date = new Date();

		WarningMessages warnList = null;
		List<WarningMessages> warnListMessages = new ArrayList<WarningMessages>();

		List<AccountMedicationManagementReminder> accountMedicationManagementReminderList = notificationDAO
				.getAccountMedicationRemindersByActIdDelFlag(nsRequest.getAccountInfo().getAccountId());
		boolean flag = true;
		// NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		if (accountMedicationManagementReminderList == null || accountMedicationManagementReminderList.isEmpty()) {
			// Date date = new Date();
			warnList = new WarningMessages();
			warnList.setWarningMessage(messageSource.getMessage("no.medication.reminders.present", null,
					nsRequest.getLocale()));
			warnList.setIsWarningPresent(false);
			warnList.setWarningDate(DateUtils.getFormatDate(addDays(date, -0), "MM/dd/yyyy"));
			warnList.setWarningDay("Today");
			warnListMessages.add(warnList);
			return warnListMessages;
		}

		String notificationDate = null;

		for (AccountMedicationManagementReminder accountMedicationManagementReminder : accountMedicationManagementReminderList) {
			followingMedReminder = true;

			for (AccountReminderMedicationRelation accountReminderMedicationRelationList : accountMedicationManagementReminder
					.getAccountReminderMedicationRelationList()) {
				message = accountReminderMedicationRelationList.getAlias();

				followingMedReminder = checkFor3Days(accountMedicationManagementReminder);
				if (!followingMedReminder) {

					// message=accountMedicationManagementReminder.getReminderFor()+" has not been 3 days";
					message = accountReminderMedicationRelationList.getAlias() + " has not been taken from past 3 days";

					notificationDate = (DateUtils.getFormatDate(addDays(date, -3), "MM/dd/yyyy"));

					followingMedReminder = checkFor7Days(accountMedicationManagementReminder);
					if (!followingMedReminder) {
						message = accountReminderMedicationRelationList.getAlias()
								+ " has not been taken from past 7 days";

						notificationDate = (DateUtils.getFormatDate(addDays(date, -7), "MM/dd/yyyy"));

						followingMedReminder = checkFor14Days(accountMedicationManagementReminder);

						if (!followingMedReminder) {
							message = accountReminderMedicationRelationList.getAlias()
									+ " has not been taken from past 14 days";

							notificationDate = (DateUtils.getFormatDate(addDays(date, -14), "MM/dd/yyyy"));
						}
					}
					warnList = new WarningMessages();
					warnList.setWarningMessage(message);
					warnList.setWarningDate(notificationDate);
					warnList.setIsWarningPresent(true);
					warnList.setWarningDay("Last Week");
					warnListMessages.add(warnList);
				} else {
					flag = false;
				}
			}
		}
		if (!flag) {
			if (warnListMessages.isEmpty()) {
				warnListMessages = new ArrayList<WarningMessages>();
				warnList = new WarningMessages();
				warnList.setWarningMessage(messageSource.getMessage("no.medication.reminders.present", null,
						nsRequest.getLocale()));
				warnList.setIsWarningPresent(false);

				warnList.setWarningDate(DateUtils.getFormatDate(addDays(date, -0), "MM/dd/yyyy"));
				warnList.setWarningDay("Today");
				warnListMessages.add(warnList);
			}
			return warnListMessages;
		}

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		// nsResponse.setResponseData(responsedata);
		// nsResponse.setWarningMessage(warnListMessages);
		// nsResponse.setMedicationManagementReminderList(medicationManagementReminderList);

		return warnListMessages;
	}

	public Boolean checkFor3Days(AccountMedicationManagementReminder accountMedicationManagementReminder) {
		Boolean response = true;
		int yesCount = 0;
		Date date = new Date();
		Date todaysDate = new Date();
		Date past3Days = addDays(date, -3);

		try {

			List<AccountMedicationEngage> accountMedicationEngageList = notificationDAO
					.getAccountMedicationEngageByCurrentDate_3(
							accountMedicationManagementReminder.getAccountMedicationManagementReminderId(),
							DateUtils.getFormatDate(todaysDate, "MM/dd/yyyy"),
							DateUtils.getFormatDate(past3Days, "MM/dd/yyyy"));

			if (accountMedicationManagementReminder.getFrequencyType() > 1
					&& accountMedicationManagementReminder.getFrequencyType() < 5) {
				if (accountMedicationEngageList.isEmpty()) {
					response = false;
				} else {
					for (AccountMedicationEngage accountMedicationEngage : accountMedicationEngageList) {
						if (accountMedicationEngage.getResponseId().getResponseId() == 1) {
							yesCount += 1;
						}
					}
					if (yesCount == 0) {
						response = false;
					}
				}
			} else {
				response = false;
			}

			if (accountMedicationManagementReminder.getFrequencyType() == 1
					|| accountMedicationManagementReminder.getFrequencyType() == 10) {
				response = true;
			}

			if (accountMedicationManagementReminder.getReminderBeginDate().after(past3Days)) {
				response = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public Boolean checkFor7Days(AccountMedicationManagementReminder accountMedicationManagementReminder) {
		Boolean response = true;
		int yesCount = 0;
		Date date = new Date();
		Date todaysDate = new Date();
		Date past7Days = addDays(date, -7);

		try {

			List<AccountMedicationEngage> accountMedicationEngageList = notificationDAO
					.getAccountMedicationEngageByCurrentDate_7(
							accountMedicationManagementReminder.getAccountMedicationManagementReminderId(),
							(DateUtils.getFormatDate(todaysDate, "MM/dd/yyyy")),
							(DateUtils.getFormatDate(past7Days, "MM/dd/yyyy")));

			if (accountMedicationManagementReminder.getFrequencyType() > 1
					&& accountMedicationManagementReminder.getFrequencyType() < 9) {
				if (accountMedicationEngageList.isEmpty()) {
					response = false;
				} else {
					for (AccountMedicationEngage accountMedicationEngage : accountMedicationEngageList) {
						if (accountMedicationEngage.getResponseId().getResponseId() == 1) {
							yesCount += 1;
						}
					}
					if (yesCount == 0) {
						response = false;
					}
				}
			} else {
				response = false;
			}

			if (accountMedicationManagementReminder.getFrequencyType() == 1
					|| accountMedicationManagementReminder.getFrequencyType() == 10) {
				response = true;
			}

			if (accountMedicationManagementReminder.getReminderBeginDate().after(past7Days)) {
				response = true;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public Boolean checkFor14Days(AccountMedicationManagementReminder accountMedicationManagementReminder) {
		Boolean response = true;
		int yesCount = 0;
		Date date = new Date();
		Date todaysDate = new Date();
		Date past14Days = addDays(date, -14);

		try {

			List<AccountMedicationEngage> accountMedicationEngageList = notificationDAO
					.getAccountMedicationEngageByCurrentDate_14(
							accountMedicationManagementReminder.getAccountMedicationManagementReminderId(),
							(DateUtils.getFormatDate(todaysDate, "MM/dd/yyyy")),
							(DateUtils.getFormatDate(past14Days, "MM/dd/yyyy")));

			if (accountMedicationManagementReminder.getFrequencyType() > 1
					&& accountMedicationManagementReminder.getFrequencyType() < 10) {
				if (accountMedicationEngageList.isEmpty()) {
					response = false;
				} else {
					for (AccountMedicationEngage accountMedicationEngage : accountMedicationEngageList) {
						if (accountMedicationEngage.getResponseId().getResponseId() == 1) {
							yesCount += 1;
						}
					}
					if (yesCount == 0) {
						response = false;
					}
				}
			} else {
				response = false;
			}

			if (accountMedicationManagementReminder.getFrequencyType() == 1
					|| accountMedicationManagementReminder.getFrequencyType() == 10) {
				response = true;
			}

			if (accountMedicationManagementReminder.getReminderBeginDate().after(past14Days)) {
				response = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public Boolean checkBpReminderFor3Days(AccountBloodPressureManagement accountBloodPressureManagement) {
		Boolean response = true;
		int yesCount = 0;
		Date date = new Date();
		Date todaysDate = new Date();
		Date past3Days = addDays(date, -3);

		try {
			List<AccountBloodPressureEngageResponse> accountBloodPresrEngageList = notificationDAO
					.getAccountBpEngageByCurrentDate_3(
							accountBloodPressureManagement.getAccountBloodPressureManagementId(),
							(DateUtils.getFormatDate(todaysDate, "MM/dd/yyyy")),
							(DateUtils.getFormatDate(past3Days, "MM/dd/yyyy")));

			if (accountBloodPressureManagement.getFrequencyType() > 1
					&& accountBloodPressureManagement.getFrequencyType() < 5) {
				if (accountBloodPresrEngageList.isEmpty()) {
					response = false;
				} else {
					for (AccountBloodPressureEngageResponse accountBloodPressureEngageResponse : accountBloodPresrEngageList) {
						if (accountBloodPressureEngageResponse.getResponseId() == 1) {
							yesCount += 1;
						}
					}
					if (yesCount == 0) {
						response = false;
					}
				}
			} else {
				response = false;
			}

			if (accountBloodPressureManagement.getFrequencyType() == 1
					|| accountBloodPressureManagement.getFrequencyType() == 10) {
				response = true;
			}

			if (accountBloodPressureManagement.getReminderBeginDate().after(past3Days)) {
				response = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public Boolean checkBpReminderFor7Days(AccountBloodPressureManagement accountBloodPressureManagement) {
		Boolean response = true;
		int yesCount = 0;
		Date date = new Date();
		Date todaysDate = new Date();
		Date pastDays = addDays(date, -7);

		try {

			List<AccountBloodPressureEngageResponse> accountBloodPresrEngageList = notificationDAO
					.getAccountBpEngageByCurrentDate_7(
							accountBloodPressureManagement.getAccountBloodPressureManagementId(),
							DateUtils.getFormatDate(todaysDate, "MM/dd/yyyy"),
							DateUtils.getFormatDate(pastDays, "MM/dd/yyyy"));

			if (accountBloodPressureManagement.getFrequencyType() > 1
					&& accountBloodPressureManagement.getFrequencyType() < 9) {
				if (accountBloodPresrEngageList.isEmpty()) {
					response = false;
				} else {

					for (AccountBloodPressureEngageResponse accountBloodPressureEngageResponse : accountBloodPresrEngageList) {
						if (accountBloodPressureEngageResponse.getResponseId() == 1) {
							yesCount += 1;
						}
					}
					if (yesCount == 0) {
						response = false;
					}
				}
			}

			if (accountBloodPressureManagement.getFrequencyType() == 1
					|| accountBloodPressureManagement.getFrequencyType() == 10) {
				response = true;
			}
			if (accountBloodPressureManagement.getReminderBeginDate().after(pastDays)) {
				response = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public Boolean checkBpReminderFor14Days(AccountBloodPressureManagement accountBloodPressureManagement) {
		Boolean response = true;
		int yesCount = 0;
		Date date = new Date();
		Date todaysDate = new Date(+1);
		Date pastDays = addDays(date, -14);

		try {

			List<AccountBloodPressureEngageResponse> accountBloodPresrEngageList = notificationDAO
					.getAccountBpEngageByCurrentDate_14(
							accountBloodPressureManagement.getAccountBloodPressureManagementId(),
							(DateUtils.getFormatDate(todaysDate, "MM/dd/yyyy")),
							(DateUtils.getFormatDate(pastDays, "MM/dd/yyyy")));

			if (accountBloodPressureManagement.getFrequencyType() > 1
					&& accountBloodPressureManagement.getFrequencyType() < 10) {
				if (accountBloodPresrEngageList.isEmpty()) {
					response = false;
				} else {

					for (AccountBloodPressureEngageResponse accountBloodPressureEngageResponse : accountBloodPresrEngageList) {
						if (accountBloodPressureEngageResponse.getResponseId() == 1) {
							yesCount += 1;
						}
					}
					if (yesCount == 0) {
						response = false;
					}
				}
			} else {
				response = false;
			}
			if (accountBloodPressureManagement.getFrequencyType() == 1
					|| accountBloodPressureManagement.getFrequencyType() == 10) {
				response = true;
			}

			if (accountBloodPressureManagement.getReminderBeginDate().after(pastDays)) {
				response = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

	public static Date addDays(Date date, int numDays) {
		return new Date(date.getTime() + numDays * MILLIS_PER_DAY);
	}

	@Override
	public NsResponse getNotificationsCount(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponses = new NsResponse();
		NsResponse nsResponse = getWarningMessagesOnLogin(nsRequest);
		ResponseData responsedata = new ResponseData();
		NotificationsCount notificationsCount = new NotificationsCount();
		if (nsResponse.getNotificationWarningCount() != null) {
			notificationsCount.setWarningCount(nsResponse.getNotificationWarningCount());
		} else
			notificationsCount.setWarningCount(VersaWorkConstant.COUNT_ZERO);

		Date todaysDate = new Date();

		String todaysDateString = (DateUtils.getFormatDate(todaysDate, VersaWorkConstant.TIME_FORMAT_MM_DD_YYYY));
		Date todaysBeginDate = DateUtils.getDate(todaysDateString, VersaWorkConstant.TIME_FORMAT_MM_DD_YYYY);
		Date todaysEndDate = addDays(todaysBeginDate, 1);
		NsResponse nsResponseDummy = hospitalNoticeServiceImpl.getHospitalNotice(nsRequest);
		nsResponse.setHospitalNotices(nsResponseDummy.getHospitalNotices());
		/*
		 * try { nsResponse.setHospitalNotificationsCount(nsResponseDummy.
		 * getNotificationsCount() .getHospitalNotificationCount()); } catch
		 * (Exception e) { nsResponse.setHospitalNotificationsCount(0); }
		 */
		try {
			notificationsCount.setHospitalNotificationCount(nsResponseDummy.getNotificationsCount()
					.getHospitalNotificationCount());
		} catch (Exception e) {
			notificationsCount.setHospitalNotificationCount(VersaWorkConstant.COUNT_ZERO);
		}

		try {
			List<PatientBillSummary> billSummaries = patientBillingDAO.getPatientBillSummaryListByIsBillPaidIsViewed(
					nsRequest.getAccountInfo().getAccountId(), false, false);
			if (billSummaries != null && !billSummaries.isEmpty()) {
				notificationsCount.setOutstandingBillCount(billSummaries.size());
			} else {
				notificationsCount.setOutstandingBillCount(VersaWorkConstant.COUNT_ZERO);
			}
		} catch (Exception exception) {
			notificationsCount.setOutstandingBillCount(VersaWorkConstant.COUNT_ZERO);
		}

		Integer patientConfirmedAppointmentCount = VersaWorkConstant.COUNT_ZERO;

		try {
			List<PatientUpcomingAppointment> confAppList = notificationDAO.getConfApptsForFuture(nsRequest
					.getAccountInfo().getAccountId(), todaysBeginDate);
			if (confAppList != null && confAppList.size() > VersaWorkConstant.COUNT_ZERO) {
				Iterator<PatientUpcomingAppointment> confAppIter = confAppList.iterator();
				while (confAppIter.hasNext()) {
					if (!confAppIter.next().getIsRead()) {
						patientConfirmedAppointmentCount++;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.debug("No future appointments to show.");
			patientConfirmedAppointmentCount = VersaWorkConstant.COUNT_ZERO;
		}
		notificationsCount.setConfirmedAppointmentCount(patientConfirmedAppointmentCount);

		Integer patientAppointmentRequestCount = notificationDAO.getApptsByAccountIdDateandReadFlag(nsRequest
				.getAccountInfo().getAccountId(), todaysBeginDate, todaysEndDate);
		notificationsCount.setScheduledAppointmentCount(patientAppointmentRequestCount);
		nsResponses.setNotificationsCount(notificationsCount);
		responsedata.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		responsedata.setDescription(VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS);

		nsResponses.setResponseData(responsedata);
		return nsResponses;
	}

	@Override
	public NsResponse getFuturePatientConfirmedAppointments(NsRequest nsRequest) throws BusinessException,
			SystemException {
		int clientDatabaseId = Integer
				.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, Locale.getDefault()));
		LOGGER.debug("getFuturePatientConfirmedAppointments : clientDatabaseId" + clientDatabaseId);
		Date todaysDate = new Date();

		String todaysDateString = (DateUtils.getFormatDate(todaysDate, "MM/dd/yyyy"));
		Date todaysBeginDate = DateUtils.getDate(todaysDateString, "MM/dd/yyyy");
		List<PatientUpcomingAppointment> patientConfirmedAppointmentRequestList = notificationDAO
				.getConfApptsForFuture(nsRequest.getAccountInfo().getAccountId(), todaysBeginDate);
		// List<PatientAppointmentRequest> patientAppointmentRequests =
		// account.getPatientAppointmentRequestList();
		if (patientConfirmedAppointmentRequestList.isEmpty()) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage("no.patnt.appntmnt", null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}
		List<NsPatientAppointment> nsPatientAppointments = new ArrayList<NsPatientAppointment>();
		FacilityProvider facilityProvider = null;
		for (PatientUpcomingAppointment patientAppointmentRequest : patientConfirmedAppointmentRequestList) {
			if (patientAppointmentRequest.getAppointmentDate() != null) {
				NsPatientAppointment nsPatientAppointment = new NsPatientAppointment();
				nsPatientAppointment.setIsviewed(patientAppointmentRequest.getIsRead());
				nsPatientAppointment.setAppointmentId(patientAppointmentRequest.getPatientUpcomingAppointmentPK()
						.getAppointmentId());
				nsPatientAppointment.setAppointmentDate(DateUtils.getFormatDate(
						patientAppointmentRequest.getAppointmentDate(), "MM/dd/yyyy - HH:mm a"));
				nsPatientAppointment.setAppointmentTime(patientAppointmentRequest.getAppointmentTime().toString());
				nsPatientAppointment.setComment(patientAppointmentRequest.getTypeDescription());
				// nsPatientAppointment.setContactEmail(patientAppointmentRequest.getContactEmail());
				// nsPatientAppointment.setContactPhone(patientAppointmentRequest.getContactPhone());
				nsPatientAppointment.setProviderName(patientAppointmentRequest.getProviderName());
				nsPatientAppointment.setRequestSentDate(patientAppointmentRequest.getAppointmentDate().toString());
				// nsPatientAppointment.setPatientName(new
				// Account(patientAppointmentRequest.getAccount().getAccountId()).getMedicalRecordNumber());
				nsPatientAppointment.setConfirmationDate(DateUtils.getFormatDate(
						patientAppointmentRequest.getDateAdded(), "MM/dd/yyyy - HH:mm a"));
				nsPatientAppointment.setConfirmedBy(patientAppointmentRequest.getProviderName());
				LOGGER.debug("getFuturePatientConfirmedAppointments getProviderId() :"
						+ patientAppointmentRequest.getProviderId());

				try {
					facilityProvider = getDoctorListDAO.getProviderByProviderId(
							patientAppointmentRequest.getProviderId(), clientDatabaseId);
					nsPatientAppointment.setProviderAddress(facilityProvider.getAddress1());
					nsPatientAppointment.setProviderAddress2(facilityProvider.getAddress2());
					nsPatientAppointment.setProviderAddress3(facilityProvider.getCity() + ","
							+ facilityProvider.getState() + facilityProvider.getPostalCode());
				} catch (BusinessException exp) {
					LOGGER.info("No Provide specified. Data got mismatch for Provide ID "
							+ patientAppointmentRequest.getProviderId());
					nsPatientAppointment.setProviderAddress(VersaWorkConstant.NOT_APPLICABLE);
					nsPatientAppointment.setProviderAddress2(VersaWorkConstant.NOT_APPLICABLE);
					nsPatientAppointment.setProviderAddress3(VersaWorkConstant.NOT_APPLICABLE);
				}

				nsPatientAppointments.add(nsPatientAppointment);
			}
		}
		NsResponse nsResponse = NsResponseUtils.getNsResponse(
				null,
				messageSource.getMessage("rsp.data.dscrptn.success", null, nsRequest.getLocale()),
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));
		nsResponse.setNsPatientAppointments(nsPatientAppointments);
		return nsResponse;
	}

	@Override
	public NsResponse getFutureScheduledAppointments(NsRequest nsRequest) throws BusinessException, SystemException {
		try {
			Date todaysDate = new Date();
			String todaysDateString = (DateUtils.getFormatDate(todaysDate, "MM/dd/yyyy"));
			Date todaysBeginDate = DateUtils.getDate(todaysDateString, "MM/dd/yyyy");
			List<PatientAppointmentRequest> patientAppointmentRequest = notificationDAO
					.getScheduledAppointmentsForFuture(nsRequest.getAccountInfo().getAccountId(), todaysBeginDate);
			if (patientAppointmentRequest.isEmpty()) {
				return NsResponseUtils.getNsResponse(null,
						messageSource.getMessage("no.patnt.appntmnt", null, nsRequest.getLocale()),
						VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
			}
			List<NsPatientAppointment> nsPatientAppointments = new ArrayList<NsPatientAppointment>();
			for (PatientAppointmentRequest patientAppointmentRequest1 : patientAppointmentRequest) {
				if (patientAppointmentRequest1.getAppointmentDate() != null) {
					NsPatientAppointment nsPatientAppointment = new NsPatientAppointment();
					nsPatientAppointment.setIsviewed(patientAppointmentRequest1.getIsRead());
					nsPatientAppointment.setPatientAppointmentRequestId(patientAppointmentRequest1
							.getPatientAppointmentRequestId());

					nsPatientAppointment.setAppointmentDate(DateUtils.getFormatDate(
							patientAppointmentRequest1.getAppointmentDate(), "MM/dd/yyyy - HH:mm a"));
					nsPatientAppointment.setAppointmentTime(patientAppointmentRequest1.getAppointmentTime().toString());
					nsPatientAppointment.setComment(patientAppointmentRequest1.getComment());
					nsPatientAppointment.setContactEmail(patientAppointmentRequest1.getContactEmail());
					nsPatientAppointment.setContactPhone(patientAppointmentRequest1.getContactPhone());
					nsPatientAppointment.setProviderId(patientAppointmentRequest1.getProviderId());
					nsPatientAppointment.setProviderName(patientAppointmentRequest1.getProviderName());
					nsPatientAppointment.setRequestSentDate(DateUtils.getFormatDate(
							patientAppointmentRequest1.getRequestSentDate(), "MM/dd/yyyy - HH:mm a"));
					nsPatientAppointment.setRequestSentTime(DateUtils.getFormatDate(
							patientAppointmentRequest1.getRequestSentDate(), "HH:mm a"));
					Account account = accountServiceDAO.getAccountById(patientAppointmentRequest1.getAccountId());
					nsPatientAppointment.setPatientName(account.getFirstName());
					nsPatientAppointments.add(nsPatientAppointment);
				}
			}

			NsResponse nsResponse = NsResponseUtils.getNsResponse(
					null,
					messageSource.getMessage("rsp.data.dscrptn.success", null, nsRequest.getLocale()),
					Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
							nsRequest.getLocale())));
			nsResponse.setNsPatientAppointments(nsPatientAppointments);
			return nsResponse;
		} catch (Exception exp) {
			// exp.printStackTrace();
			LOGGER.error("Exception occoured on AccountServiceImpl (getAccountPatientAppointment) : ", exp);
			throw new BusinessException(exp.getMessage());
		}

	}

	@Override
	public NsResponse setViewedTrue(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		try {
			if (nsRequest.getNotificationCounter().getAppointmentId() != null) {
				PatientUpcomingAppointment patientUpcomingAppointment = patientAppointmentServiceDAO
						.getConfirmedAppointmentByAppointmentId(nsRequest.getNotificationCounter().getAppointmentId(),
								nsRequest.getAccountInfo().getAccountId());
				patientUpcomingAppointment.setIsRead(true);
				patientAppointmentServiceDAO.updateUpcomingAppointment(patientUpcomingAppointment);
			} else if (nsRequest.getNotificationCounter().getPatientAppointmentRequestId() != null) {
				PatientAppointmentRequest patientAppointmentRequest = patientAppointmentServiceDAO
						.getAppointmtReqForConfrmtn(nsRequest.getNotificationCounter().getPatientAppointmentRequestId());
				patientAppointmentRequest.setIsRead(true);
				patientAppointmentServiceDAO.update(patientAppointmentRequest);
			}
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE,
					null, nsRequest.getLocale())));
			responsedata.setDescription("Success");
		} catch (Exception exp) {
			responsedata.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
			responsedata.setDescription("Failed to update record");
		}

		nsResponse.setResponseData(responsedata);
		return nsResponse;
	}

	@Override
	public NsResponse setNotificationViewedTrue(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		if (nsRequest.getNotificationCounter().getAppointmentId() != null) {
			AccountNotificationHistory accountNotificationHistory = patientAppointmentServiceDAO
					.getNotificationHistryByAppointmentId(nsRequest.getNotificationCounter().getModuleId(), nsRequest
							.getAccountInfo().getAccountId());
			accountNotificationHistory.setIsviewedFlag(true);
			patientAppointmentServiceDAO.updateNotificationHistory(accountNotificationHistory);
		}
		responsedata.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		responsedata.setDescription("Success");

		nsResponse.setResponseData(responsedata);
		return nsResponse;
	}

	@Override
	public NsResponse getWarningMessages(NsRequest nsRequest) throws BusinessException, SystemException {
		LOGGER.info("GET ALL WARNING MESSAGES WHICH NOT VIEWED");
		NsResponse nsResponse = new NsResponse();
		List<Warnings> fetchMedicationWarnigns = warningServiceDao.findMedicationWarnings(nsRequest.getAccountInfo()
				.getAccountId());
		List<Warnings> fetchBloodPressureWarnigns = warningServiceDao.findBloodPressureWarnings(nsRequest
				.getAccountInfo().getAccountId());
		List<WarningMessages> messages = new ArrayList<WarningMessages>();
		for (Warnings mw : fetchMedicationWarnigns) {
			messages.add(getWarnings(mw));
		}

		if (fetchMedicationWarnigns.isEmpty()) {
			WarningMessages wm = new WarningMessages();
			wm.setWarningMessage(messageSource.getMessage("no.medication.reminders.present", null,
					nsRequest.getLocale()));
			wm.setIsWarningPresent(false);
			wm.setWarningDate(DateUtils.getFormatDate(addDays(new Date(), -0), "MM/dd/yyyy"));
			wm.setWarningDay("Today");
			messages.add(wm);
		}

		nsResponse.setMedicationWarningMessage(messages);
		messages = new ArrayList<WarningMessages>();
		for (Warnings bpw : fetchBloodPressureWarnigns) {
			messages.add(getWarnings(bpw));
		}

		if (fetchBloodPressureWarnigns.isEmpty()) {
			WarningMessages wm = new WarningMessages();
			wm = new WarningMessages();
			wm.setWarningMessage(messageSource.getMessage("no.bloodpressure.reminders.present", null,
					nsRequest.getLocale()));
			wm.setIsWarningPresent(false);
			wm.setWarningDate(DateUtils.getFormatDate(addDays(new Date(), -0), "MM/dd/yyyy"));
			wm.setWarningDay("Today");
			messages.add(wm);
		}
		nsResponse.setBloodPressureWarningMessage(messages);

		ResponseData data = new ResponseData();

		data.setDescription("Success");
		data.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));

		nsResponse.setResponseData(data);
		markWarningViewed(fetchMedicationWarnigns);
		markWarningViewed(fetchBloodPressureWarnigns);

		return nsResponse;
	}

	@Async
	private void markWarningViewed(List<Warnings> fetchMedicationWarnigns/*
																		 * ,
																		 * List<
																		 * BloodPressureWarnings
																		 * >
																		 * fetchBloodPressureWarnigns
																		 */) {
		for (Warnings w : fetchMedicationWarnigns) {
			w.setIsViewed(true);
			warningServiceDao.update(w);
		}
	}

	private WarningMessages getWarnings(Warnings wm) {
		WarningMessages warning = new WarningMessages();
		warning.setIsWarningPresent(wm.getIsWarningPresent());
		warning.setWarningDate(wm.getWarningDate());
		warning.setWarningDay(wm.getWarningDay());
		warning.setWarningMessage(wm.getWarningMessage());
		return warning;
	}

	public NsResponse flushNotificationsForAndroid(NsRequest nsRequest) throws SystemException, BusinessException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		List<AccountBloodPressureManagement> accountBloodPressureManagements = notificationDAO
				.getActiveBloodPressureNotification(nsRequest.getAccountInfo().getAccountId(), true);
		List<Integer> bloodPressureReminderList = new ArrayList<Integer>();
		if (accountBloodPressureManagements != null) {
			for (AccountBloodPressureManagement accountBloodPressureManagementReminder : accountBloodPressureManagements) {
				bloodPressureReminderList.add(accountBloodPressureManagementReminder
						.getAccountBloodPressureManagementId());
			}
		}
		List<AccountMedicationManagementReminder> accountMedicationManagementReminders = notificationDAO
				.getActiveMedicationNotification(nsRequest.getAccountInfo().getAccountId(), true);
		List<Integer> medicationManagementReminderList = new ArrayList<Integer>();
		if (accountMedicationManagementReminders.size() != 0 && accountMedicationManagementReminders != null) {
			for (AccountMedicationManagementReminder accountMedicationManagementReminder : accountMedicationManagementReminders) {
				medicationManagementReminderList.add(accountMedicationManagementReminder
						.getAccountMedicationManagementReminderId());
			}
		}
		if (medicationManagementReminderList.size() == 0 || medicationManagementReminderList == null
				|| bloodPressureReminderList.size() == 0 || bloodPressureReminderList == null) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.FAILURE_RESPONSE_CODE,
					null, nsRequest.getLocale())));
			responsedata.setDescription("No Notification Present");
			nsResponse.setResponseData(responsedata);
			nsResponse.setMedicationRemindersCount(0);
		} else if (medicationManagementReminderList.size() > 0 || bloodPressureReminderList.size() > 0) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE,
					null, nsRequest.getLocale())));
			responsedata.setDescription("success");
			nsResponse.setResponseData(responsedata);
			nsResponse.setBloodPressureIdsList(bloodPressureReminderList);
			nsResponse.setBloodPressureRemindersCount(bloodPressureReminderList.size());
			nsResponse.setMedicationIdsList(medicationManagementReminderList);
			nsResponse.setMedicationRemindersCount(medicationManagementReminderList.size());
		}
		return nsResponse;

	}
	
	/**
	 * @param accountId
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	private List<NsPatientAppointment> fetchPatientPastAppointments(Integer accountId) throws BusinessException, SystemException{
		
		List<NsPatientAppointment> nsPastPatientAppointments = new ArrayList<NsPatientAppointment>();     
		List<PatientVisit> patientPastAppointment = notificationDAO.getPastPatientsAppointments(accountId);
               
        for (PatientVisit patientAppointmentRequest : patientPastAppointment) {				
        	NsPatientAppointment nsPatientAppointment = createPastPatientDto(patientAppointmentRequest);	
        	nsPatientAppointment.setAccountId(accountId);
			nsPastPatientAppointments.add(nsPatientAppointment);				
		}
		return nsPastPatientAppointments;
	}
	
	
	/* (non-Javadoc)
	 * @see com.versawork.http.service.NotificationService#getPatientAppointments(com.versawork.http.dataobject.NsRequest)
	 */
	@Override
	public NsResponse getPatientAppointments(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		    int clientDatabaseId = Integer
				.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, Locale.getDefault()));
		    int accountId=nsRequest.getAccountInfo().getAccountId();
		    Integer appointmentSize=nsRequest.getUpcomingAppointmentSize();
		    
			List<PatientUpcomingAppointment> patientUpcomingAppointment = notificationDAO.getPatientsAppointments(accountId,clientDatabaseId,new Date(),appointmentSize);
			if(patientUpcomingAppointment.size()<=3)
			{
				nsResponse.setNoMorePatientAppointment(true);
			}
			//Collections.reverse(patientUpcomingAppointment);
			NsPatientAppointment nsPatientAppointment=null;
			List<NsPatientAppointment> nsPatientUpcomingAppointments = new ArrayList<NsPatientAppointment>();
			
            for (PatientUpcomingAppointment patientAppointmentRequest1 : patientUpcomingAppointment) {				
				nsPatientAppointment = createPatientDto(patientAppointmentRequest1);				
				nsPatientUpcomingAppointments.add(nsPatientAppointment);				
			}
			
            List<NsPatientAppointment> nsPastPatientAppointments = new ArrayList<NsPatientAppointment>(); 
            
            // Fetching Past Appointments for Patient based on his account Id
            nsPastPatientAppointments.addAll(fetchPatientPastAppointments(accountId));
           	
            
            List<LinkedAccountsRel> linkedAccounts = linkAccountServiceImpl.getLinkedAccounts(accountId);
            
            // Getting Patient Test Results and Patient Imaging Results for linked accounts
    		for(LinkedAccountsRel linkedAccountsRel:linkedAccounts)	
    		{
    			try{
    				nsPastPatientAppointments.addAll(fetchPatientPastAppointments(linkedAccountsRel.getLinkedAccountId()));
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    		}
            
			nsResponse.setUpcomingPatientAppointment(nsPatientUpcomingAppointments);
			nsResponse.setPastPatientAppointment(nsPastPatientAppointments);
			ResponseData responseData = new ResponseData();
			responseData.setDescription("Success");
			responseData.setResult(0);
			nsResponse.setResponseData(responseData);				
			return nsResponse;	
	}
	
	NsPatientAppointment createPatientDto(
			PatientUpcomingAppointment patientAppointmentRequest1) {
		NsPatientAppointment nsPatientAppointment=new NsPatientAppointment();
		nsPatientAppointment.setAppointmentId(patientAppointmentRequest1.getPatientUpcomingAppointmentPK().getAppointmentId());
		nsPatientAppointment.setSourceName(patientAppointmentRequest1.getSourceName());
		try {
			nsPatientAppointment.setAppointmentDate(DateUtils.getFormatDate(patientAppointmentRequest1.getAppointmentDate(),"MM/dd/yyyy"));
			nsPatientAppointment.setAppointmentTime(DateUtils.getFormatDate(patientAppointmentRequest1.getAppointmentTime(),"hh:mm a"));
		} catch (BusinessException e) {
			
			e.printStackTrace();
		}
		
		nsPatientAppointment.setProviderName(patientAppointmentRequest1.getProviderName());		
		return nsPatientAppointment;
	}
	NsPatientAppointment createPastPatientDto(
			PatientVisit patientAppointmentRequest) {
		NsPatientAppointment nsPatientAppointment=new NsPatientAppointment();
		nsPatientAppointment.setAppointmentId(patientAppointmentRequest.getPatientVisitPK().getPatientVisitId());
		nsPatientAppointment.setSourceName(patientAppointmentRequest.getSourceName());
		try {
			nsPatientAppointment.setAppointmentDate(DateUtils.getFormatDate(patientAppointmentRequest.getVisitDate(),"MM/dd/yyyy"));
			nsPatientAppointment.setAppointmentTime(DateUtils.getFormatDate(patientAppointmentRequest.getVisitDate(),"hh:mm a"));
		} catch (BusinessException e) {
			
			e.printStackTrace();
		}
		
		nsPatientAppointment.setProviderName(patientAppointmentRequest.getProviderName());		
		return nsPatientAppointment;
	}
	
	@Override
	public NsResponse loadMorePatientAppointments(NsRequest nsRequest) throws BusinessException, SystemException {
		
		NsResponse nsResponse = new NsResponse();
	    int clientDatabaseId = Integer
			.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, Locale.getDefault()));
	    int accountId=nsRequest.getAccountInfo().getAccountId();
	    Integer appointmentSize=nsRequest.getUpcomingAppointmentSize();
	    
		List<PatientUpcomingAppointment> patientUpcomingAppointmentList = notificationDAO.loadMorePatientsAppointments(accountId,clientDatabaseId,new Date(),appointmentSize);
		
		if(patientUpcomingAppointmentList.size()<=3)
		{
			nsResponse.setNoMorePatientAppointment(true);
		}
		Collections.reverse(patientUpcomingAppointmentList);
		List<NsPatientAppointment> nsPatientAppointment = new ArrayList<NsPatientAppointment>();
		
        for (PatientUpcomingAppointment patientUpcomingAppointment : patientUpcomingAppointmentList) {
			
        	NsPatientAppointment patientAppointment=new NsPatientAppointment();	
        	patientAppointment.setAppointmentId(patientUpcomingAppointment.getPatientUpcomingAppointmentPK().getAppointmentId());
        	patientAppointment.setSourceName(patientUpcomingAppointment.getSourceName());
        	patientAppointment.setAppointmentDate(DateUtils.getFormatDate(patientUpcomingAppointment.getAppointmentDate(),"MM/dd/yyyy"));
        	patientAppointment.setAppointmentTime(DateUtils.getFormatDate(patientUpcomingAppointment.getAppointmentTime(),"hh:mm a"));
        	
        	
        	patientAppointment.setProviderName(patientUpcomingAppointment.getProviderName());
        	nsPatientAppointment.add(patientAppointment);
		}
        
		ResponseData responseData = new ResponseData();
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setUpcomingPatientAppointment(nsPatientAppointment);

		return nsResponse;
	

	}

	@Override
	public NsResponse searchDetails(NsRequest nsRequest)
			throws BusinessException, SystemException {
		ResponseData responseData = new ResponseData();
		NsResponse nsResponse = new NsResponse();
	    int clientDatabaseId = Integer
			.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, Locale.getDefault()));
	    int accountId=nsRequest.getAccountInfo().getAccountId();
		List<PatientUpcomingAppointment> upcomingAppointments = notificationDAO.getUpcomingVisitList(clientDatabaseId,new Date(),nsRequest.getProviderName(),accountId);
		List<NsPatientAppointment> nsUpcomingPatientAppointment = new ArrayList<NsPatientAppointment>();	
		int i=0;
		 for (PatientUpcomingAppointment patientUpcomingAppointment : upcomingAppointments)
		 {
			    NsPatientAppointment patientAppointment=new NsPatientAppointment();	
	        	patientAppointment.setAppointmentId(patientUpcomingAppointment.getPatientUpcomingAppointmentPK().getAppointmentId());
	        	patientAppointment.setSourceName(patientUpcomingAppointment.getSourceName());
	        	patientAppointment.setAppointmentDate(DateUtils.getFormatDate(patientUpcomingAppointment.getAppointmentDate(),"MM/dd/yyyy"));
	        	patientAppointment.setAppointmentTime(DateUtils.getFormatDate(patientUpcomingAppointment.getAppointmentTime(),"hh:mm a"));	        	
	        	patientAppointment.setProviderName(patientUpcomingAppointment.getProviderName());
	        	patientAppointment.setAccountId(accountId);
	        	if(i<=nsUpcomingPatientAppointment.size())
				{
	        		patientAppointment.setIsAppointment(true);
	        		i++;
				}			   
	        	
	        	nsUpcomingPatientAppointment.add(patientAppointment);
		 }
		 
		List<PatientVisit> pastAppointments = notificationDAO.getPastVisitList(new Date(),nsRequest.getProviderName(),accountId);
		List<NsPatientAppointment> nsPastPatientAppointment = new ArrayList<NsPatientAppointment>();	
		int j=0;
		 for (PatientVisit patientUpcomingAppointment : pastAppointments)
		 {
			    NsPatientAppointment patientAppointment=new NsPatientAppointment();	
	        	patientAppointment.setAppointmentId(patientUpcomingAppointment.getPatientVisitPK().getPatientVisitId());
	        	patientAppointment.setSourceName(patientUpcomingAppointment.getSourceName());	
	        	patientAppointment.setAppointmentDate(DateUtils.getFormatDate(patientUpcomingAppointment.getVisitDate(),"MM/dd/yyyy"));
	        	patientAppointment.setProviderName(patientUpcomingAppointment.getProviderName());
	        	patientAppointment.setAccountId(accountId);
	        	if(j<=nsPastPatientAppointment.size())
				{
	        		patientAppointment.setIsAppointment(true);
	        		j++;
				}
	        	nsPastPatientAppointment.add(patientAppointment);
		 }
		/*List<NsPatientAppointment> upcomingAndPastAppointment = new ArrayList<NsPatientAppointment>();
		upcomingAndPastAppointment.addAll(nsUpcomingPatientAppointment);
		upcomingAndPastAppointment.addAll(nsPastPatientAppointment);*/
		 if(nsUpcomingPatientAppointment==null || nsUpcomingPatientAppointment.isEmpty() && nsPastPatientAppointment==null || nsPastPatientAppointment.isEmpty())
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
		nsResponse.setUpcomingPatientAppointment(nsUpcomingPatientAppointment);
		nsResponse.setPastPatientAppointment(nsPastPatientAppointment);

		return nsResponse;
	}
	
	

}
