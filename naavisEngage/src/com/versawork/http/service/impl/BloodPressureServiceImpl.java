package com.versawork.http.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.AccountServiceDAO;
import com.versawork.http.dao.BloodPressureDAO;
import com.versawork.http.dao.MedicationMgntDAO;
import com.versawork.http.dataobject.AccountWeightInfo;
import com.versawork.http.dataobject.BloodPressureInfo;
import com.versawork.http.dataobject.BloodPressureReminderInfo;
import com.versawork.http.dataobject.FrequencyDesc;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.NsScheduleTime;
import com.versawork.http.dataobject.PatientActiveTimeInfo;
import com.versawork.http.dataobject.PatientCalorieCountInfo;
import com.versawork.http.dataobject.PatientDistanceInfo;
import com.versawork.http.dataobject.PatientHeartAgeInfo;
import com.versawork.http.dataobject.PatientHeartRateInfo;
import com.versawork.http.dataobject.PatientOverallStressInfo;
import com.versawork.http.dataobject.PatientSleepInfo;
import com.versawork.http.dataobject.PatientStepCountInfo;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.dataobject.TestResultInfo;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.AccountBloodPressureEngage;
import com.versawork.http.model.AccountBloodPressureEngageResponse;
import com.versawork.http.model.AccountBloodPressureManagement;
import com.versawork.http.model.AccountBloodPressureManagementSchedule;
import com.versawork.http.model.AccountWeight;
import com.versawork.http.model.DatePart;
import com.versawork.http.model.Frequency;
import com.versawork.http.model.PatientActiveTime;
import com.versawork.http.model.PatientCalorieCount;
import com.versawork.http.model.PatientDistance;
import com.versawork.http.model.PatientHeartAge;
import com.versawork.http.model.PatientHeartRate;
import com.versawork.http.model.PatientOverallStress;
import com.versawork.http.model.PatientOverallStressPK;
import com.versawork.http.model.PatientSleep;
import com.versawork.http.model.PatientSleep;
import com.versawork.http.model.PatientStepCount;
import com.versawork.http.model.PatientVitalSign;
import com.versawork.http.service.BloodPressureService;
import com.versawork.http.utils.AccountMedicationComparator;
import com.versawork.http.utils.DateRange;
import com.versawork.http.utils.DateUtils;
import com.versawork.http.utils.NsResponseUtils;

/**
 * @author Dheeraj
 * 
 */

@Component
public class BloodPressureServiceImpl implements BloodPressureService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger LOGGER = LoggerFactory
			.getLogger(BloodPressureServiceImpl.class);
	private static final String CLIENT_DATABASE_ID = "client.database.id";
	private static final String CLIENT_ID = "client.id";
	private static final String BLOOD_PRESSURE_UNIT="blood.pressure.unit";

	@Autowired
	private BloodPressureDAO bloodPressureDAO;

	@Autowired
	private AccountServiceDAO accountServiceDAO;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MedicationMgntDAO medicationMgntDAO;

	@Override
	public NsResponse saveBloodPressure(NsRequest nsRequest)
			throws BusinessException, SystemException {

		AccountBloodPressureEngage accountBloodPressureEngage = new AccountBloodPressureEngage();

		// accountBloodPressureEngage.setAccountBloodPressureManagementId(new
		// AccountBloodPressureManagement(nsRequest.getBloodPressureInfo().getBloodPressureMgmtId()));
		accountBloodPressureEngage.setAccountId(new Account(nsRequest
				.getBloodPressureInfo().getAccountId()));
		accountBloodPressureEngage.setSys(nsRequest.getBloodPressureInfo()
				.getSys());
		accountBloodPressureEngage.setDia(nsRequest.getBloodPressureInfo()
				.getDia());
		accountBloodPressureEngage.setPulse(nsRequest.getBloodPressureInfo()
				.getPulse());
		accountBloodPressureEngage.setComment(nsRequest.getBloodPressureInfo()
				.getComment());
		accountBloodPressureEngage.setReminderDate(DateUtils
				.getDate(nsRequest.getBloodPressureInfo().getReminderDate(),
						"MM/dd/yyyy hh:mm a")); // Don't
												// change
												// the
												// Date
												// pattern(dependency
												// on
		// accountBloodPressureEngage.setReminderDate(new Date()); // IOS)
		accountBloodPressureEngage.setDateAdded(new Date());
		accountBloodPressureEngage.setDeleteFlag(false);

		bloodPressureDAO.persist(accountBloodPressureEngage);

		return NsResponseUtils.getNsResponse(null,
				VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS, Integer
						.parseInt(messageSource.getMessage(
								VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
								nsRequest.getLocale())));

	}

	@Override
	public NsResponse getBloodPressure(NsRequest nsRequest)
			throws BusinessException, SystemException {
		{
			Account account = accountServiceDAO.getAccountById(nsRequest
					.getAccountInfo().getAccountId());

			List<AccountBloodPressureEngage> bloodPressureList = bloodPressureDAO
					.getAccountBloodPressureEngageByAccountId(account
							.getAccountId());

			NsResponse nsResponse = new NsResponse();
			ResponseData responsedata = new ResponseData();

			List<BloodPressureInfo> engageBpInfoList = new ArrayList<BloodPressureInfo>();
			
			for (AccountBloodPressureEngage accountBloodPressureEngages : bloodPressureList) {
				BloodPressureInfo bloodPressureInfo = new BloodPressureInfo();

				if (accountBloodPressureEngages.getDeleteFlag() == false) {

					bloodPressureInfo
							.setBloodPressureId(accountBloodPressureEngages
									.getAccountBloodPressureEngageId());
					// bloodPressureInfo.setAccountId(accountBloodPressureEngages.getAccountId());
					bloodPressureInfo.setComment(accountBloodPressureEngages
							.getComment());
					bloodPressureInfo.setDia(accountBloodPressureEngages
							.getDia());
					bloodPressureInfo.setPulse(accountBloodPressureEngages
							.getPulse());
					bloodPressureInfo.setSys(accountBloodPressureEngages
							.getSys());
					bloodPressureInfo.setDeleteflag(accountBloodPressureEngages
							.getDeleteFlag());
					bloodPressureInfo.setReminderDate(DateUtils.getFormatDate(
							accountBloodPressureEngages.getReminderDate(),
							"MM/dd/yyyy hh:mm a")); // Don't
													// change
													// the
													// Date
													// pattern(dependency
													// on
													// IOS)
					engageBpInfoList.add(bloodPressureInfo);
				}
			}

			// Populate blood pressure info list from Engage BP List
			List<BloodPressureInfo> bloodPressureInfoList = new ArrayList<BloodPressureInfo>();
			if (engageBpInfoList != null && engageBpInfoList.size() > 0) {
				bloodPressureInfoList.addAll(engageBpInfoList);
			}
			
			// Populate blood pressure info list from EHR BP List
			List<BloodPressureInfo> ehrBloodPressureList = fetchEhrBloodPressureRecords(account
					.getAccountId());
			
			if (ehrBloodPressureList != null && ehrBloodPressureList.size() > 0) {
				bloodPressureInfoList.addAll(ehrBloodPressureList);
			}

			if (bloodPressureInfoList.size() == 0) {
				/*
				 * NsResponse nsResponse = new NsResponse(); ResponseData
				 * responsedata = new ResponseData();
				 */
				responsedata.setResult(Integer.parseInt(messageSource
						.getMessage(VersaWorkConstant.FAILURE_RESPONSE_CODE,
								null, nsRequest.getLocale())));
				responsedata
						.setDescription("No blood pressure details present");
				nsResponse.setResponseData(responsedata);
			} else {
				// ResponseData responsedata = new ResponseData();
				responsedata.setResult(Integer.parseInt(messageSource
						.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE,
								null, nsRequest.getLocale())));
				responsedata.setDescription("success");
				nsResponse.setResponseData(responsedata);
				nsResponse.setBloodPressureList(bloodPressureInfoList);
			}
			return nsResponse;
		}
	}

	@Override
	public NsResponse editBloodPressure(NsRequest nsRequest)
			throws BusinessException, SystemException {

		AccountBloodPressureEngage accountBloodPressureEngage = bloodPressureDAO
				.getDetailsByActIdBpId(nsRequest.getBloodPressureInfo()
						.getAccountId(), nsRequest.getBloodPressureInfo()
						.getBloodPressureId());

		accountBloodPressureEngage.setSys(nsRequest.getBloodPressureInfo()
				.getSys());
		accountBloodPressureEngage.setDia(nsRequest.getBloodPressureInfo()
				.getDia());
		accountBloodPressureEngage.setPulse(nsRequest.getBloodPressureInfo()
				.getPulse());
		accountBloodPressureEngage.setComment(nsRequest.getBloodPressureInfo()
				.getComment());
		accountBloodPressureEngage.setReminderDate(DateUtils.getDate(nsRequest
				.getBloodPressureInfo().getReminderDate(), "dd-MM-yyyy HH:mm")); // Don't
																					// change
																					// the
																					// Date
																					// pattern(dependency
																					// on
																					// IOS)
		accountBloodPressureEngage.setDateAdded(new Date());
		bloodPressureDAO.update(accountBloodPressureEngage);

		return NsResponseUtils.getNsResponse(null,
				VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS, Integer
						.parseInt(messageSource.getMessage(
								VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
								nsRequest.getLocale())));
	}

	@Override
	public NsResponse deleteBloodPressure(NsRequest nsRequest)
			throws BusinessException, SystemException {
		AccountBloodPressureEngage accountBloodPressureEngage = bloodPressureDAO
				.deleteBlodPesurByActIdBpId(nsRequest.getBloodPressureInfo()
						.getAccountId(), nsRequest.getBloodPressureInfo()
						.getBloodPressureId());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		accountBloodPressureEngage.setDeleteFlag(true);

		bloodPressureDAO.update(accountBloodPressureEngage);

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Deleted Blood Pressure");
		nsResponse.setResponseData(responsedata);

		return nsResponse;

	}

	@Override
	public NsResponse saveBloodPressureReminder(NsRequest nsRequest)
			throws BusinessException, SystemException {

		Account account = bloodPressureDAO.getAccountById(nsRequest
				.getBloodPressureReminder().getAccountId());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		AccountBloodPressureManagement accountBloodPressureManagement = new AccountBloodPressureManagement();

		FrequencyDesc frequencyDesc = nsRequest.getBloodPressureReminder()
				.getFrequencyDesc();

		accountBloodPressureManagement.setAccountId(new Account(account
				.getAccountId()));
		accountBloodPressureManagement.setIsActive(nsRequest
				.getBloodPressureReminder().isActive());
		accountBloodPressureManagement.setReminderFor(nsRequest
				.getBloodPressureReminder().getReminderFor());
		accountBloodPressureManagement.setReminderBeginDate(DateUtils.getDate(
				nsRequest.getBloodPressureReminder().getReminderBeginDate(),
				"MM/dd/yyyy")); // Don't change the
								// Date
								// pattern(dependency
								// on IOS)
		accountBloodPressureManagement.setReminderEndDate(DateUtils.getDate(
				nsRequest.getBloodPressureReminder().getReminderEndDate(),
				"MM/dd/yyyy")); // Don't change the
								// Date
								// pattern(dependency
								// on IOS)
		accountBloodPressureManagement.setFrequencyType(frequencyDesc
				.getFrequencyId());
		accountBloodPressureManagement.setDateAdded(new Date());
		accountBloodPressureManagement.setDeleteFlag(false);

		bloodPressureDAO.persist(accountBloodPressureManagement);

		List<NsScheduleTime> accountMedicationManagementScheduleList = nsRequest
				.getBloodPressureReminder().getNsScheduleTimeList();

		for (NsScheduleTime nsScheduleTime : accountMedicationManagementScheduleList) {
			AccountBloodPressureManagementSchedule accountBloodPressureManagementSchedule = new AccountBloodPressureManagementSchedule();

			accountBloodPressureManagementSchedule
					.setAccountBloodPressureManagementId(accountBloodPressureManagement);
			accountBloodPressureManagementSchedule.setReminderTime(DateUtils
					.get24FormatDate(nsScheduleTime.getReminderTime()));
			if (frequencyDesc.getDatePartId() != 0) {
				accountBloodPressureManagementSchedule
						.setDatePartId(new DatePart(frequencyDesc
								.getDatePartId()));
			}
			accountBloodPressureManagementSchedule.setInterval(frequencyDesc
					.getInterval());
			accountBloodPressureManagementSchedule.setDateAdded(new Date());

			bloodPressureDAO.persist(accountBloodPressureManagementSchedule);
		}

		List<BloodPressureReminderInfo> bloodPressureReminderList = new ArrayList<BloodPressureReminderInfo>();
		BloodPressureReminderInfo bloodPressureReminderInfo = new BloodPressureReminderInfo();
		bloodPressureReminderInfo
				.setBloodPressureReminderId(accountBloodPressureManagement
						.getAccountBloodPressureManagementId());
		bloodPressureReminderList.add(bloodPressureReminderInfo);

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata
				.setDescription("Blood Pressure Reminder Added Successfully");
		nsResponse.setResponseData(responsedata);

		nsResponse.setBloodPressureReminderList(bloodPressureReminderList);

		return nsResponse;
	}

	@Override
	public NsResponse getBloodPressureReminders(NsRequest nsRequest)
			throws BusinessException, SystemException {
		List<AccountBloodPressureManagement> accountBloodPressureManagements = bloodPressureDAO
				.getReminderByActIdDeleteFlag(nsRequest
						.getBloodPressureReminder().getAccountId());
		/*
		 * Account account = new
		 * Account(nsRequest.getBloodPressureReminder().getAccountId());
		 * List<AccountBloodPressureManagement> accountBloodPressureManagements
		 * = account.getAccountBloodPressureManagementList();
		 */NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		List<BloodPressureReminderInfo> bloodPressureReminderList = new ArrayList<BloodPressureReminderInfo>();
		if (accountBloodPressureManagements != null) {
			for (AccountBloodPressureManagement accountBloodPressureManagementReminder : accountBloodPressureManagements) {
				FrequencyDesc frequencyDesc = new FrequencyDesc();
				if (accountBloodPressureManagementReminder.getFrequencyType() != null) {
					LOGGER.debug("Frequency Type is: "
							+ accountBloodPressureManagementReminder
									.getFrequencyType());
					Frequency frequency = medicationMgntDAO
							.getFrequencyListByLangCodeFreqType(
									accountBloodPressureManagementReminder
											.getFrequencyType(), nsRequest
											.getLocale().toString());
					frequencyDesc.setFrequencyId(frequency.getType());
					frequencyDesc.setFrequencyDesc(frequency.getFrequency());
				}

				BloodPressureReminderInfo bloodPressureReminderInfo = new BloodPressureReminderInfo();

				bloodPressureReminderInfo
						.setBloodPressureReminderId(accountBloodPressureManagementReminder
								.getAccountBloodPressureManagementId());
				bloodPressureReminderInfo
						.setAccountId(accountBloodPressureManagementReminder
								.getAccountId().getAccountId());
				bloodPressureReminderInfo
						.setActive(accountBloodPressureManagementReminder
								.getIsActive());
				bloodPressureReminderInfo
						.setReminderFor(accountBloodPressureManagementReminder
								.getReminderFor());
				bloodPressureReminderInfo.setReminderBeginDate(DateUtils
						.getFormatDate(accountBloodPressureManagementReminder
								.getReminderBeginDate(), "dd-MM-yyyy")); // Don't
																			// change
																			// the
																			// Date
																			// pattern(dependency
																			// on
																			// IOS)
				bloodPressureReminderInfo.setReminderEndDate(DateUtils
						.getFormatDate(accountBloodPressureManagementReminder
								.getReminderEndDate(), "dd-MM-yyyy")); // Don't
																		// change
																		// the
																		// Date
																		// pattern(dependency
																		// on
																		// IOS)
				bloodPressureReminderInfo.setFrequencyDesc(frequencyDesc);

				List<AccountBloodPressureManagementSchedule> accountBloodPressureManagementScheduleListFromDB = accountBloodPressureManagementReminder
						.getAccountBloodPressureManagementScheduleList();
				List<NsScheduleTime> nsScheduleList = new ArrayList<NsScheduleTime>();
				for (AccountBloodPressureManagementSchedule accountBloodPressureManagementSchedule : accountBloodPressureManagementScheduleListFromDB) {
					NsScheduleTime medicationScheduleTime = new NsScheduleTime();
					medicationScheduleTime
							.setScheduleId(accountBloodPressureManagementSchedule
									.getAccountBloodPressureManagementScheduleId());
					medicationScheduleTime.setReminderTime(DateUtils
							.getFormatDate(
									accountBloodPressureManagementSchedule
											.getReminderTime(), "hh:mm a")); // Don't
																				// change
																				// the
																				// Time
																				// pattern(dependency
																				// on
																				// IOS)
					nsScheduleList.add(medicationScheduleTime);
				}
				Collections.sort(nsScheduleList,
						new AccountMedicationComparator());

				bloodPressureReminderInfo.setNsScheduleTimeList(nsScheduleList);
				bloodPressureReminderList.add(bloodPressureReminderInfo);
			}
		}
		if (bloodPressureReminderList.size() == 0
				|| bloodPressureReminderList == null) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription(messageSource.getMessage(
					"no.bloodpressure.reminders.present", null,
					nsRequest.getLocale()));
			nsResponse.setResponseData(responsedata);
			nsResponse.setBloodPressureRemindersCount(0);
		} else if (bloodPressureReminderList.size() > 0) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("success");
			nsResponse.setResponseData(responsedata);
			nsResponse.setBloodPressureReminderList(bloodPressureReminderList);
			nsResponse.setBloodPressureRemindersCount(bloodPressureReminderList
					.size());
		}
		return nsResponse;

	}

	public NsResponse deleteBloodPressureReminder(NsRequest nsRequest)
			throws BusinessException, SystemException {
		AccountBloodPressureManagement accountBloodPressureManagement = bloodPressureDAO
				.getDetailsByBPIdActId(nsRequest.getBloodPressureReminder()
						.getBloodPressureReminderId(), nsRequest
						.getBloodPressureReminder().getAccountId());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		// Deleting Blood Pressure Response List

		List<AccountBloodPressureEngageResponse> accountBloodPressureEngageResponseList = bloodPressureDAO
				.getAccountBpEngageResponseByBpRemId(nsRequest
						.getBloodPressureReminder()
						.getBloodPressureReminderId());

		for (AccountBloodPressureEngageResponse accountBloodPressureEngageResponse : accountBloodPressureEngageResponseList) {
			bloodPressureDAO.remove(accountBloodPressureEngageResponse);
		}
		accountBloodPressureEngageResponseList.clear();

		// Deleting Blood Pressure Schedule list

		List<AccountBloodPressureManagementSchedule> accountBloodPressureManagementScheduleListFromDB = accountBloodPressureManagement
				.getAccountBloodPressureManagementScheduleList();

		for (AccountBloodPressureManagementSchedule accountBloodPressureManagementSchedule : accountBloodPressureManagementScheduleListFromDB) {
			bloodPressureDAO.remove(accountBloodPressureManagementSchedule);
		}
		accountBloodPressureEngageResponseList.clear();

		bloodPressureDAO.remove(accountBloodPressureManagement);

		bloodPressureDAO.flush();

		// bloodPressureDAO.removeallBpResp(accountBloodPressureEngageResponseList);

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription(messageSource.getMessage("bp.rem.del",
				null, nsRequest.getLocale()));
		nsResponse.setResponseData(responsedata);

		return nsResponse;

	}

	public NsResponse editBloodPressureReminder(NsRequest nsRequest)
			throws BusinessException, SystemException {
		AccountBloodPressureManagement accountBloodPressureManagement = bloodPressureDAO
				.getDetailsByBPIdActId(nsRequest.getBloodPressureReminder()
						.getBloodPressureReminderId(), nsRequest
						.getBloodPressureReminder().getAccountId());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		FrequencyDesc frequencyDesc = nsRequest.getBloodPressureReminder()
				.getFrequencyDesc();

		accountBloodPressureManagement.setIsActive(nsRequest
				.getBloodPressureReminder().isActive());
		accountBloodPressureManagement.setReminderFor(nsRequest
				.getBloodPressureReminder().getReminderFor());
		accountBloodPressureManagement.setReminderBeginDate(DateUtils.getDate(
				nsRequest.getBloodPressureReminder().getReminderBeginDate(),
				"MM/dd/yyyy")); // Don't change the
								// Date
								// pattern(dependency
								// on IOS)
		accountBloodPressureManagement.setReminderEndDate(DateUtils.getDate(
				nsRequest.getBloodPressureReminder().getReminderEndDate(),
				"MM/dd/yyyy")); // Don't change the
								// Date
								// pattern(dependency
								// on IOS)
		accountBloodPressureManagement.setFrequencyType(frequencyDesc
				.getFrequencyId());
		accountBloodPressureManagement.setDateAdded(new Date());
		accountBloodPressureManagement.setDeleteFlag(false);

		bloodPressureDAO.update(accountBloodPressureManagement);

		List<AccountBloodPressureManagementSchedule> accountBloodPressureManagementScheduleListFromDB = accountBloodPressureManagement
				.getAccountBloodPressureManagementScheduleList();

		for (AccountBloodPressureManagementSchedule accountBloodPressureManagementSchedule : accountBloodPressureManagementScheduleListFromDB) {
			bloodPressureDAO.remove(accountBloodPressureManagementSchedule);
		}

		// bloodPressureDAO.flush();

		List<NsScheduleTime> accountBPManagementScheduleList = nsRequest
				.getBloodPressureReminder().getNsScheduleTimeList();

		for (NsScheduleTime nsScheduleTime : accountBPManagementScheduleList) {
			AccountBloodPressureManagementSchedule accountBloodPressureManagementSchedule = new AccountBloodPressureManagementSchedule();

			accountBloodPressureManagementSchedule
					.setAccountBloodPressureManagementId(accountBloodPressureManagement);
			accountBloodPressureManagementSchedule.setReminderTime(DateUtils
					.get24FormatDate(nsScheduleTime.getReminderTime())); // Don't
																			// change
																			// the
																			// Time
																			// pattern(dependency
																			// on
																			// IOS)
			if (frequencyDesc.getDatePartId() != 0) {
				accountBloodPressureManagementSchedule
						.setDatePartId(new DatePart(frequencyDesc
								.getDatePartId()));
			}
			accountBloodPressureManagementSchedule.setInterval(frequencyDesc
					.getInterval());
			accountBloodPressureManagementSchedule.setDateAdded(new Date());

			bloodPressureDAO.persist(accountBloodPressureManagementSchedule);
		}

		List<BloodPressureReminderInfo> bloodPressureReminderList = new ArrayList<BloodPressureReminderInfo>();
		BloodPressureReminderInfo bloodPressureReminderInfo = new BloodPressureReminderInfo();
		bloodPressureReminderInfo
				.setBloodPressureReminderId(accountBloodPressureManagement
						.getAccountBloodPressureManagementId());
		bloodPressureReminderList.add(bloodPressureReminderInfo);
		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata
				.setDescription("Blood Pressure Reminder Edited Successfully");
		nsResponse.setResponseData(responsedata);
		nsResponse.setBloodPressureReminderList(bloodPressureReminderList);
		return nsResponse;
	}

	public NsResponse saveBloodPressureResponse(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		AccountBloodPressureEngageResponse accountBloodPressureEngageResponse = null;
		BloodPressureInfo bloodPressureInfo = nsRequest.getBloodPressureInfo();
		AccountBloodPressureManagement aBPM = bloodPressureDAO
				.getDetailsByBPIdActId(bloodPressureInfo
						.getBloodPressureMgmtId());
		if (aBPM == null) {
			responsedata
					.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE
							.getCode());
			responsedata.setDescription(messageSource.getMessage(
					"bp.reminder.stale.msg", null, nsRequest.getLocale()));
		} else {
			if (bloodPressureInfo.getRecordIdentifier() != null) {
				accountBloodPressureEngageResponse = bloodPressureDAO
						.getAccountBpEngageResponseByRecordIdentifier(
								bloodPressureInfo.getAccountId(),
								bloodPressureInfo.getRecordIdentifier(),
								bloodPressureInfo.getBloodPressureMgmtId());
				if (accountBloodPressureEngageResponse == null) {
					accountBloodPressureEngageResponse = new AccountBloodPressureEngageResponse();
				}
			} else {
				accountBloodPressureEngageResponse = new AccountBloodPressureEngageResponse();
			}
			accountBloodPressureEngageResponse.setAccountId(bloodPressureInfo
					.getAccountId());
			accountBloodPressureEngageResponse
					.setAccountBloodPressureManagementId(new AccountBloodPressureManagement(
							bloodPressureInfo.getBloodPressureMgmtId()));
			accountBloodPressureEngageResponse.setResponseId(bloodPressureInfo
					.getResponseId());
			accountBloodPressureEngageResponse.setReminderDate(DateUtils
					.getDate(bloodPressureInfo.getReminderDate(),
							"MM/dd/yyyy HH:mm")); // Don't
													// change
													// the
													// Date
													// pattern(dependency
													// on
													// IOS)
			accountBloodPressureEngageResponse.setComment(bloodPressureInfo
					.getComment());
			accountBloodPressureEngageResponse.setDateAdded(new Date());
			accountBloodPressureEngageResponse
					.setRecordIdentifier(bloodPressureInfo
							.getRecordIdentifier());
			bloodPressureDAO.persist(accountBloodPressureEngageResponse);

			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("success");
		}
		nsResponse.setResponseData(responsedata);

		return nsResponse;
	}

	public NsResponse getBPCountResponse(NsRequest nsRequest)
			throws BusinessException, SystemException {

		Date date = new Date();
		Date todaysDate = new Date();
		Date past7Days = addDays(date, -7);

		Integer yesCount = 0;
		List<AccountBloodPressureEngageResponse> AccountBPResponseList = bloodPressureDAO
				.getAccountBPResponseCountCurrentDate_7(nsRequest
						.getAccountInfo().getAccountId(), (DateUtils
						.getFormatDate(todaysDate, "MM/dd/yyyy")), (DateUtils
						.getFormatDate(past7Days, "MM/dd/yyyy")));

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		List<BloodPressureInfo> bloodPressureInfoList = new ArrayList<BloodPressureInfo>();

		for (AccountBloodPressureEngageResponse accountBloodPressureEngageResponse : AccountBPResponseList) {
			BloodPressureInfo bloodPressureInfo = new BloodPressureInfo();
			bloodPressureInfo.setResponseId(accountBloodPressureEngageResponse
					.getResponseId());

			if (accountBloodPressureEngageResponse.getResponseId() == 1) {
				yesCount++;
			}
			bloodPressureInfoList.add(bloodPressureInfo);
		}
		if (bloodPressureInfoList.size() == 0) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("No Response for Reminders Present");
			nsResponse.setResponseData(responsedata);
			nsResponse.setBPYesCount(0.0);
			nsResponse.setBPTotalCount(0.0);
		} else if (bloodPressureInfoList.size() > 0) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("success");
			nsResponse.setResponseData(responsedata);
			nsResponse.setBloodPressureInfoList(bloodPressureInfoList);
			nsResponse.setBPYesCount(yesCount.doubleValue());
			nsResponse.setBPTotalCount(((Integer) bloodPressureInfoList.size())
					.doubleValue());
		}
		return nsResponse;
	}

	private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

	public static Date addDays(Date date, int numDays) {
		return new Date(date.getTime() + numDays * MILLIS_PER_DAY);
	}

	@Override
	public NsResponse restoreBloodPressureReminders(NsRequest nsRequest)
			throws BusinessException, SystemException {
		List<AccountBloodPressureManagement> accountBloodPressureManagements = bloodPressureDAO
				.restoreReminderByActIdDeleteFlag(nsRequest.getAccountInfo()
						.getAccountId(), new Date());
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		List<BloodPressureReminderInfo> bloodPressureReminderList = new ArrayList<BloodPressureReminderInfo>();
		List<Frequency> frequencies = medicationMgntDAO
				.getFrequencyListByLangCode(nsRequest.getLocale().toString());
		if (accountBloodPressureManagements != null) {
			for (AccountBloodPressureManagement accountBloodPressureManagementReminder : accountBloodPressureManagements) {
				FrequencyDesc frequencyDesc = new FrequencyDesc();
				if (accountBloodPressureManagementReminder.getFrequencyType() != null) {
					for (Frequency frequency : frequencies) {
						if (frequency.getType() == accountBloodPressureManagementReminder
								.getFrequencyType()) {
							frequencyDesc.setFrequencyId(frequency.getType());
							frequencyDesc.setFrequencyDesc(frequency
									.getFrequency());
						}
					}

				}

				BloodPressureReminderInfo bloodPressureReminderInfo = new BloodPressureReminderInfo();

				bloodPressureReminderInfo
						.setBloodPressureReminderId(accountBloodPressureManagementReminder
								.getAccountBloodPressureManagementId());
				bloodPressureReminderInfo
						.setAccountId(accountBloodPressureManagementReminder
								.getAccountId().getAccountId());
				bloodPressureReminderInfo
						.setActive(accountBloodPressureManagementReminder
								.getIsActive());
				bloodPressureReminderInfo
						.setReminderFor(accountBloodPressureManagementReminder
								.getReminderFor());
				bloodPressureReminderInfo
						.setReminderBeginDate((new SimpleDateFormat(
								"dd-MM-yyyy")
								.format(accountBloodPressureManagementReminder
										.getReminderBeginDate())).toString()); // Don't
																				// change
																				// the
																				// Date
																				// pattern(dependency
																				// on
																				// IOS)
				bloodPressureReminderInfo
						.setReminderEndDate((new SimpleDateFormat("dd-MM-yyyy")
								.format(accountBloodPressureManagementReminder
										.getReminderEndDate())).toString()); // Don't
																				// change
																				// the
																				// Date
																				// pattern(dependency
																				// on
																				// IOS)
				bloodPressureReminderInfo.setFrequencyDesc(frequencyDesc);

				List<AccountBloodPressureManagementSchedule> accountBloodPressureManagementScheduleListFromDB = accountBloodPressureManagementReminder
						.getAccountBloodPressureManagementScheduleList();
				List<NsScheduleTime> nsScheduleList = new ArrayList<NsScheduleTime>();
				for (AccountBloodPressureManagementSchedule accountBloodPressureManagementSchedule : accountBloodPressureManagementScheduleListFromDB) {
					NsScheduleTime medicationScheduleTime = new NsScheduleTime();
					medicationScheduleTime
							.setScheduleId(accountBloodPressureManagementSchedule
									.getAccountBloodPressureManagementScheduleId());
					medicationScheduleTime
							.setReminderTime((new SimpleDateFormat("hh:mm a")
									.format(accountBloodPressureManagementSchedule
											.getReminderTime())).toString()); // Don't
																				// change
																				// the
																				// Time
																				// pattern(dependency
																				// on
																				// IOS)
					nsScheduleList.add(medicationScheduleTime);
				}
				Collections.sort(nsScheduleList,
						new AccountMedicationComparator());

				bloodPressureReminderInfo.setNsScheduleTimeList(nsScheduleList);
				bloodPressureReminderList.add(bloodPressureReminderInfo);
			}
		}
		if (bloodPressureReminderList.size() == 0
				|| bloodPressureReminderList == null) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("No BloodPressure reminders Present");
			nsResponse.setResponseData(responsedata);
		} else if (bloodPressureReminderList.size() > 0) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("success");
			nsResponse.setResponseData(responsedata);
			nsResponse.setBloodPressureReminderList(bloodPressureReminderList);
		}
		return nsResponse;
	}

	
	private List<BloodPressureInfo> fetchEhrBloodPressureRecords(
			Integer accountId) throws BusinessException, SystemException {

		List<BloodPressureInfo> bloodPressureEhrInfo = new ArrayList<BloodPressureInfo>();

		// EHR Blood Pressure
		List<PatientVitalSign> bloodPressureListEhr = bloodPressureDAO
				.getBloodPressureVitalSign(accountId);

		if (bloodPressureListEhr != null) {
			for (PatientVitalSign patientVitalSign : bloodPressureListEhr) {
				BloodPressureInfo bloodPressureInfo = new BloodPressureInfo();
				boolean isValid = true;
				if ((patientVitalSign.getDiastolicBp() != null
						&& patientVitalSign.getDiastolicBp().length() > 1 && Character
							.isDigit(patientVitalSign.getDiastolicBp()
									.charAt(0)))

						&& (patientVitalSign.getSystolicBp() != null
								&& patientVitalSign.getSystolicBp().length() > 1 && Character
									.isDigit(patientVitalSign.getSystolicBp()
											.charAt(0)))) {
					Float dia = Float.parseFloat(patientVitalSign
							.getDiastolicBp());
					bloodPressureInfo.setDia(dia.intValue());
					Float sys = Float.parseFloat(patientVitalSign
							.getSystolicBp());
					bloodPressureInfo.setSys(sys.intValue());
				} else {
					isValid = false;
				}

				if (patientVitalSign.getHeartRate() != null
						&& patientVitalSign.getHeartRate().length() > 1
						&& Character.isDigit(patientVitalSign.getHeartRate()
								.charAt(0))) {
					Float pulse = Float.parseFloat(patientVitalSign
							.getHeartRate());
					bloodPressureInfo.setPulse(pulse.intValue());
				}

				bloodPressureInfo.setReminderDate(DateUtils.getFormatDate(
						patientVitalSign.getDateAdded(), "MM/dd/yyyy hh:mm a"));
				bloodPressureInfo.setDateAdded(DateUtils.getFormatDate(
						patientVitalSign.getDateAdded(),
						DateUtils.DATE_ADDED_FORMAT));
				bloodPressureInfo.setSourceName(patientVitalSign
						.getSourceName());
				if (isValid) {
					bloodPressureEhrInfo.add(bloodPressureInfo);
				}
			}
		}
		return bloodPressureEhrInfo;
	}
	

	@Override
	public NsResponse getHealthLog(NsRequest nsRequest)
			throws BusinessException, SystemException {
		
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		BloodPressureInfo bloodPressureInfo = new BloodPressureInfo();
		AccountWeightInfo accountWeightInfo = new AccountWeightInfo();
		Date todaysDate = new Date();
		
		String convertedDate = new SimpleDateFormat(DateUtils.DATE_ADDED_FORMAT)
				.format(todaysDate);

		Integer accountId = nsRequest.getAccountInfo().getAccountId();

		AccountBloodPressureEngage bloodPressureEngage = bloodPressureDAO
				.getBloodPressureByAccountId(accountId);
		
		List<BloodPressureInfo> bloodPressureInfos = fetchEhrBloodPressureRecords(accountId);
		BloodPressureInfo ehrBloodPressureRecord = null;
		if(bloodPressureInfos.size() > 0){
			ehrBloodPressureRecord = bloodPressureInfos.get(0);
		}
		
		if (bloodPressureEngage == null && ehrBloodPressureRecord == null) {
			bloodPressureInfo.setBloodPressure("No Blood Pressure Recorded");
		} else {
			
			Date dateAdded = null;
			Integer sysBp = null;
			Integer diaBp = null;
			
			// Check if both are not null then consider the latest one
			if (bloodPressureEngage != null && ehrBloodPressureRecord != null) {
				
				dateAdded = bloodPressureEngage.getDateAdded();
				sysBp = bloodPressureEngage.getSys();
				diaBp = bloodPressureEngage.getDia();
				
				Date ehrDateAdded = DateUtils.getDate(ehrBloodPressureRecord.getDateAdded(), DateUtils.DATE_ADDED_FORMAT);
				
				// Check if EHR record is latest
				if (bloodPressureEngage.getDateAdded().before(ehrDateAdded)) {
					dateAdded = ehrDateAdded;
					sysBp = ehrBloodPressureRecord.getSys();
					diaBp = ehrBloodPressureRecord.getDia();
				}
			
			// Engage Record is not null
			} else if (bloodPressureEngage != null) {
				dateAdded = bloodPressureEngage.getDateAdded();
				sysBp = bloodPressureEngage.getSys();
				diaBp = bloodPressureEngage.getDia();
				
			// EHR Record is not null
			}else{
				dateAdded = DateUtils.getDate(ehrBloodPressureRecord.getDateAdded(), DateUtils.DATE_ADDED_FORMAT);
				sysBp = ehrBloodPressureRecord.getSys();
				diaBp = ehrBloodPressureRecord.getDia();
			}
			
			if (DateUtils.getFormatDate(dateAdded, DateUtils.DATE_ADDED_FORMAT)
					.equalsIgnoreCase(convertedDate)) {
				bloodPressureInfo.setDateAdded("Today");
			} else {
				bloodPressureInfo.setDateAdded(DateUtils.getFormatDate(
						dateAdded, DateUtils.DATE_ADDED_FORMAT));
			}

			StringBuilder sb = new StringBuilder();

			sb.append(sysBp).append('/');
			sb.append(diaBp).append(" ");
			sb.append(messageSource.getMessage(BLOOD_PRESSURE_UNIT, null, nsRequest.getLocale()));

			bloodPressureInfo.setBloodPressure(sb.toString());
		}

		AccountWeight accountWeight = bloodPressureDAO
				.getWeightByAccountId(nsRequest.getAccountInfo().getAccountId());
		if (accountWeight == null) {
			accountWeightInfo.setWeight("No Weight Recorded");

		} else {
			/*
			 * accountWeightInfo.setWeight(accountWeight.getWeight());
			 * accountWeightInfo.setWeightUnits(accountWeight.getWeightUnits());
			 */
			// String weightDate=new
			// SimpleDateFormat("MM/dd/yyyy").format(accountWeight.getDate());

			if (DateUtils.getFormatDate(accountWeight.getDate(), DateUtils.DATE_ADDED_FORMAT)
					.equalsIgnoreCase(convertedDate)) {
				accountWeightInfo.setDateAdded("Today");
			} else {
				accountWeightInfo.setDateAdded(DateUtils.getFormatDate(
						accountWeight.getDate(), "MM/dd/yyyy hh:mm a"));
			}

			StringBuilder sb1 = new StringBuilder();
			// sb1.append("weight/weightUnits :");
			sb1.append(accountWeight.getWeight()).append(" ");
			sb1.append(accountWeight.getWeightUnits());
			accountWeightInfo.setWeight(sb1.toString());
		}
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setBloodPressureInfo(bloodPressureInfo);
		nsResponse.setAccountWeightInfo(accountWeightInfo);
		return nsResponse;

	}

	@Override
	public NsResponse addWeight(NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		AccountWeight accountWeight = new AccountWeight();
		accountWeight.setAccountId(nsRequest.getAccountInfo().getAccountId());
		accountWeight.setDate(DateUtils.getDate(nsRequest
				.getAccountWeightInfo().getDateAdded(), "MM/dd/yyyy hh:mm a"));
		accountWeight.setWeight(nsRequest.getAccountWeightInfo().getWeight());
		// accountWeight.setWeightId(nsRequest.getAccountWeightInfo().getWeightId());
		accountWeight.setWeightUnits(nsRequest.getAccountWeightInfo()
				.getWeightUnits());
		accountWeight.setDateAdded(new Date());
		bloodPressureDAO.persist(accountWeight);

		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		return nsResponse;

	}

	@Override
	public NsResponse getWeight(NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		List<AccountWeight> weightList = bloodPressureDAO
				.getWeightList(nsRequest.getAccountInfo().getAccountId());
		if (weightList.isEmpty()) {
			responseData.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responseData.setDescription(messageSource.getMessage(
					"no.record.found", null, nsRequest.getLocale()));
			nsResponse.setResponseData(responseData);
			return nsResponse;
		}

		List<AccountWeightInfo> accountWeight = new ArrayList<AccountWeightInfo>();
		for (AccountWeight accountWeightList : weightList) {
			AccountWeightInfo accountWeightInfo = new AccountWeightInfo();
			accountWeightInfo.setWeight(accountWeightList.getWeight());
			accountWeightInfo
					.setWeightUnits(accountWeightList.getWeightUnits());
			accountWeightInfo.setWeightId(accountWeightList.getWeightId());
			accountWeightInfo.setDateAdded(DateUtils.getFormatDate(
					accountWeightList.getDate(), "MM/dd/yyyy hh:mm a"));
			accountWeight.add(accountWeightInfo);

		}

		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setWeightList(accountWeight);
		return nsResponse;
	}

	@Override
	public NsResponse deleteWeight(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		AccountWeight accountWeight = bloodPressureDAO
				.deleteWeightByAccountIdAndWeightId(nsRequest
						.getAccountWeightInfo().getWeightId(), nsRequest
						.getAccountInfo().getAccountId());

		bloodPressureDAO.remove(accountWeight);

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Deleted weight ");
		nsResponse.setResponseData(responsedata);

		return nsResponse;

	}

	@Override
	public NsResponse updateWeight(NsRequest nsRequest)
			throws BusinessException, SystemException {
		AccountWeight accountWeight = bloodPressureDAO.getDetailsByWeightId(
				nsRequest.getAccountInfo().getAccountId(), nsRequest
						.getAccountWeightInfo().getWeightId());
		accountWeight.setDate(new Date());
		accountWeight.setWeight(nsRequest.getAccountWeightInfo().getWeight());
		accountWeight.setWeightUnits(nsRequest.getAccountWeightInfo()
				.getWeightUnits());

		bloodPressureDAO.update(accountWeight);

		return NsResponseUtils.getNsResponse(null,
				VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS, Integer
						.parseInt(messageSource.getMessage(
								VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
								nsRequest.getLocale())));
	}

	@Override
	public NsResponse getPatientOverallStress(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		
		int cientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int cientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		List<PatientOverallStressInfo> respList = new ArrayList<PatientOverallStressInfo>();
		
		List<PatientOverallStress> resultList = bloodPressureDAO.getPatientOverallStress(cientId,cientDbId, nsRequest.getAccountInfo().getAccountId());
		if(resultList.isEmpty())
		{
			responseData.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responseData.setDescription(messageSource.getMessage(
					"no.record.found", null, nsRequest.getLocale()));
			nsResponse.setResponseData(responseData);
			return nsResponse;
		}
		for(PatientOverallStress source: resultList){
			PatientOverallStressInfo target = new PatientOverallStressInfo();
			BeanUtils.copyProperties(source, target);
			respList.add(target);
		}
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setOverallStressList(respList);
		return nsResponse;
	}
	
	@Override
	public NsResponse getPatientActiveTime(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		int cientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int cientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		List<PatientActiveTimeInfo> respList = new ArrayList<PatientActiveTimeInfo>();
		List<PatientActiveTime> resultList = bloodPressureDAO.getPatientActiveTime(cientId,cientDbId, nsRequest.getAccountInfo().getAccountId());
		if(resultList.isEmpty())
		{
			responseData.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responseData.setDescription(messageSource.getMessage(
					"no.record.found", null, nsRequest.getLocale()));
			nsResponse.setResponseData(responseData);
			return nsResponse;
		}
		
		
		for(PatientActiveTime source: resultList){
			PatientActiveTimeInfo target = new PatientActiveTimeInfo();
			BeanUtils.copyProperties(source, target);
			respList.add(target);
		}
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setPatientActiveTimeList(respList);
		return nsResponse;
	}
	
	@Override
	public NsResponse getPatientHeartAge(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		int cientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int cientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		List<PatientHeartAgeInfo> respList = new ArrayList<PatientHeartAgeInfo>();
		List<PatientHeartAge> resultList = bloodPressureDAO.getPatientHeartAge(cientId,cientDbId, nsRequest.getAccountInfo().getAccountId());
		if(resultList.isEmpty())
		{
			responseData.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responseData.setDescription(messageSource.getMessage(
					"no.record.found", null, nsRequest.getLocale()));
			nsResponse.setResponseData(responseData);
			return nsResponse;
		}
		
		for(PatientHeartAge source: resultList){
			PatientHeartAgeInfo target = new PatientHeartAgeInfo();
			BeanUtils.copyProperties(source, target);
			respList.add(target);
		}
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setPatientHeartAgeList(respList);
		return nsResponse;
	}
	
	@Override
	public NsResponse getPatientHeartRate(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		
		int cientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int cientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		List<PatientHeartRateInfo> respList = new ArrayList<PatientHeartRateInfo>();
		List<PatientHeartRate> resultList = bloodPressureDAO.getPatientHeartRate(cientId,cientDbId, nsRequest.getAccountInfo().getAccountId());
		if(resultList.isEmpty())
		{
			responseData.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responseData.setDescription(messageSource.getMessage(
					"no.record.found", null, nsRequest.getLocale()));
			nsResponse.setResponseData(responseData);
			return nsResponse;
		}
		for(PatientHeartRate source: resultList){
			PatientHeartRateInfo target = new PatientHeartRateInfo();
			BeanUtils.copyProperties(source, target);
			respList.add(target);
		}
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setPatientHeartRateList(respList);
		return nsResponse;
	}
	
	@Override
	public NsResponse getPatientStepCount(NsRequest nsRequest)
			throws BusinessException, SystemException {
		
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		
		int cientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int cientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		List<PatientStepCountInfo> respList = new ArrayList<PatientStepCountInfo>();
		List<PatientStepCount> resultList = bloodPressureDAO.getPatientStepCount(cientId,cientDbId, nsRequest.getAccountInfo().getAccountId());
		if(resultList.isEmpty())
		{
			responseData.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responseData.setDescription(messageSource.getMessage(
					"no.record.found", null, nsRequest.getLocale()));
			nsResponse.setResponseData(responseData);
			return nsResponse;
		}
		for(PatientStepCount source: resultList){
			PatientStepCountInfo target = new PatientStepCountInfo();
			BeanUtils.copyProperties(source, target);
			respList.add(target);
		}
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setPatientStepCountList(respList);
		return nsResponse;
	}
	
	@Override
	public NsResponse getPatientCalorieCount(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		
		int cientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int cientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		List<PatientCalorieCountInfo> respList = new ArrayList<PatientCalorieCountInfo>();
		List<PatientCalorieCount> resultList = bloodPressureDAO.getPatientCalorieCount(cientId,cientDbId, nsRequest.getAccountInfo().getAccountId());
		if(resultList.isEmpty())
		{
			responseData.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responseData.setDescription(messageSource.getMessage(
					"no.record.found", null, nsRequest.getLocale()));
			nsResponse.setResponseData(responseData);
			return nsResponse;
		}
		for(PatientCalorieCount source: resultList){
			PatientCalorieCountInfo target = new PatientCalorieCountInfo();
			BeanUtils.copyProperties(source, target);
			respList.add(target);
		}
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setPatientCalorieCountList(respList);
		return nsResponse;
	}
	
	@Override
	public NsResponse getPatientSleep(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		
		int cientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int cientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		List<PatientSleepInfo> respList = new ArrayList<PatientSleepInfo>();
		List<PatientSleep> resultList = bloodPressureDAO.getPatientSleep(cientId,cientDbId, nsRequest.getAccountInfo().getAccountId());
		if(resultList.isEmpty())
		{
			responseData.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responseData.setDescription(messageSource.getMessage(
					"no.record.found", null, nsRequest.getLocale()));
			nsResponse.setResponseData(responseData);
			return nsResponse;
		}
		for(PatientSleep source: resultList){
			PatientSleepInfo target = new PatientSleepInfo();
			BeanUtils.copyProperties(source, target);
			respList.add(target);
		}
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setPatientSleepList(respList);
		return nsResponse;
	}
	
	@Override
	public NsResponse getPatientDistance(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		
		int cientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int cientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		List<PatientDistanceInfo> respList = new ArrayList<PatientDistanceInfo>();
		List<PatientDistance> resultList = bloodPressureDAO.getPatientDistance(cientId,cientDbId, nsRequest.getAccountInfo().getAccountId());
		if(resultList.isEmpty())
		{
			responseData.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responseData.setDescription(messageSource.getMessage(
					"no.record.found", null, nsRequest.getLocale()));
			nsResponse.setResponseData(responseData);
			return nsResponse;
		}
		for(PatientDistance source: resultList){
			PatientDistanceInfo target = new PatientDistanceInfo();
			//target.setDistanceCovered(source.getDistance()*0.621);
			BeanUtils.copyProperties(source, target);
			respList.add(target);
			
		}
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setPatientDistanceList(respList);
		return nsResponse;
	}

	@Override
	public NsResponse savePatientOverallStress(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		int cientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int cientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		PatientOverallStress overallStress=new PatientOverallStress();
		overallStress.setClientId(cientId);
		overallStress.setClientDatabaseId(cientDbId);		
		overallStress.setAccountId(nsRequest.getAccountInfo().getAccountId());
		
		overallStress.setSourceId(nsRequest.getOverallStress().getSourceId());
		overallStress.setSourceName(nsRequest.getOverallStress().getSourceName());
		overallStress.setOverallStress(nsRequest.getOverallStress().getOverallStress());
		overallStress.setOverallStressDate(DateUtils.getDate(nsRequest.getOverallStress().getStressDate(),DateUtils.DATE_ADDED_FORMAT_12_HOUR));
		overallStress.setDateAdded(new Date());
		bloodPressureDAO.persist(overallStress);

		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		return nsResponse;
		
		
	}

	@Override
	public NsResponse savePatientHeartRate(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		int cientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int cientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		PatientHeartRate patientHeartRate=new PatientHeartRate();
		patientHeartRate.setClientId(cientId);
		patientHeartRate.setClientDatabaseId(cientDbId);
		patientHeartRate.setAccountId(nsRequest.getAccountInfo().getAccountId());
		patientHeartRate.setSourceId(nsRequest.getPatientHeartRate().getSourceId());
		patientHeartRate.setSourceName(nsRequest.getPatientHeartRate().getSourceName());
		patientHeartRate.setHeartRate(nsRequest.getPatientHeartRate().getHeartRate());
		patientHeartRate.setHeartRateDate(DateUtils.getDate(nsRequest.getPatientHeartRate().getHeartRtDate(),DateUtils.DATE_ADDED_FORMAT_12_HOUR));
		patientHeartRate.setDateAdded(new Date());
		bloodPressureDAO.persist(patientHeartRate);
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}
	
	@Override
	public NsResponse savePatientStepCount(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		int clientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int clientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		PatientStepCount patientStepCount=new PatientStepCount();
		patientStepCount.setClientId(clientId);
		patientStepCount.setClientDatabaseId(clientDbId);
		patientStepCount.setAccountId(nsRequest.getAccountInfo().getAccountId());
		patientStepCount.setSourceId(nsRequest.getPatientStepCount().getSourceId());
		patientStepCount.setSourceName(nsRequest.getPatientStepCount().getSourceName());
		patientStepCount.setStepCount(nsRequest.getPatientStepCount().getStepCount());
		patientStepCount.setStepCountDate(DateUtils.getDate(nsRequest.getPatientStepCount().getStepCntDate(),DateUtils.DATE_ADDED_FORMAT_12_HOUR));
		patientStepCount.setDateAdded(new Date());
		bloodPressureDAO.persist(patientStepCount);
		
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}
	
	@Override
	public NsResponse savePatientHeartAge(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		int clientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int clientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		PatientHeartAge patientHeartAge=new PatientHeartAge();
		patientHeartAge.setClientId(clientId);
		patientHeartAge.setClientDatabaseId(clientDbId);
		patientHeartAge.setAccountId(nsRequest.getAccountInfo().getAccountId());
		patientHeartAge.setSourceId(nsRequest.getPatientHeartAge().getSourceId());
		patientHeartAge.setSourceName(nsRequest.getPatientHeartAge().getSourceName());
		patientHeartAge.setHeartAge(nsRequest.getPatientHeartAge().getHeartAge());
		patientHeartAge.setHeartAgeDate(DateUtils.getDate(nsRequest.getPatientHeartAge().getHeartDate(),DateUtils.DATE_ADDED_FORMAT_12_HOUR));
		patientHeartAge.setDateAdded(new Date());
		bloodPressureDAO.persist(patientHeartAge);
		
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}
	
	@Override
	public NsResponse savePatientCalorieCount(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		int clientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int clientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		PatientCalorieCount patientCalorieCount=new PatientCalorieCount();
		patientCalorieCount.setClientId(clientId);
		patientCalorieCount.setClientDatabaseId(clientDbId);
		patientCalorieCount.setAccountId(nsRequest.getAccountInfo().getAccountId());
		patientCalorieCount.setSourceId(nsRequest.getPatientCalorieCount().getSourceId());
		patientCalorieCount.setSourceName(nsRequest.getPatientCalorieCount().getSourceName());
		patientCalorieCount.setCalorieCount(nsRequest.getPatientCalorieCount().getCalorieCount());
		patientCalorieCount.setCalorieCountDate(DateUtils.getDate(nsRequest.getPatientCalorieCount().getCalorieCntDate(),DateUtils.DATE_ADDED_FORMAT_12_HOUR));
		patientCalorieCount.setDateAdded(new Date());
		bloodPressureDAO.persist(patientCalorieCount);
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}
	
	
	@Override
	public NsResponse savePatientSleep(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		int clientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int clientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		PatientSleep patientSleep=new PatientSleep();
		patientSleep.setClientId(clientId);
		patientSleep.setClientDatabaseId(clientDbId);
		patientSleep.setAccountId(nsRequest.getAccountInfo().getAccountId());
		patientSleep.setSourceId(nsRequest.getPatientSleep().getSourceId());
		patientSleep.setSourceName(nsRequest.getPatientSleep().getSourceName());
		patientSleep.setSleep(nsRequest.getPatientSleep().getSleep());
		patientSleep.setSleepDate(DateUtils.getDate(nsRequest.getPatientSleep().getSlpDate(),DateUtils.DATE_ADDED_FORMAT_12_HOUR));
		patientSleep.setDateAdded(new Date());
		bloodPressureDAO.persist(patientSleep);
		
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}
	
	
	
	
	@Override
	public NsResponse savePatientActiveTime(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		int clientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int clientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		PatientActiveTime patientActiveTime=new PatientActiveTime();
		patientActiveTime.setClientId(clientId);
		patientActiveTime.setClientDatabaseId(clientDbId);
		patientActiveTime.setAccountId(nsRequest.getAccountInfo().getAccountId());
		patientActiveTime.setSourceId(nsRequest.getPatientActiveTime().getSourceId());
		patientActiveTime.setSourceName(nsRequest.getPatientActiveTime().getSourceName());
		patientActiveTime.setActiveTime(nsRequest.getPatientActiveTime().getActiveTime());
		patientActiveTime.setActiveTimeDate(DateUtils.getDate(nsRequest.getPatientActiveTime().getActiveDate(),DateUtils.DATE_ADDED_FORMAT_12_HOUR));
		patientActiveTime.setDateAdded(new Date());
		bloodPressureDAO.persist(patientActiveTime);
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}
	
	
	
	@Override
	public NsResponse savePatientDistance(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		int clientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int clientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		PatientDistance patientDistance=new PatientDistance();
		patientDistance.setClientId(clientId);
		patientDistance.setClientDatabaseId(clientDbId);
		patientDistance.setAccountId(nsRequest.getAccountInfo().getAccountId());
		patientDistance.setSourceId(nsRequest.getPatientDistance().getSourceId());
		patientDistance.setSourceName(nsRequest.getPatientDistance().getSourceName());
		patientDistance.setDistance(nsRequest.getPatientDistance().getDistance()* 0.621);
		patientDistance.setDistanceDate(DateUtils.getDate(nsRequest.getPatientDistance().getDistanceCoveredDate(),DateUtils.DATE_ADDED_FORMAT_12_HOUR));
		patientDistance.setDateAdded(new Date());
		bloodPressureDAO.persist(patientDistance);
		
		
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}
	
	
	
	
	
	
	

	@Override
	public NsResponse getMyHealth(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		int clientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int clientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		DateRange dateRange=DateUtils.getDateRange(DateUtils.getDate(nsRequest.getDateAdded(),DateUtils.DATE_ADDED_FORMAT), Calendar.MONDAY, Calendar.SUNDAY);
		DateRange singleDateRange=DateUtils.getSingleDateRange(DateUtils.getDate(nsRequest.getDateAdded(),DateUtils.DATE_ADDED_FORMAT));
		
		List<PatientOverallStressInfo> respList = new ArrayList<PatientOverallStressInfo>();
		List<PatientOverallStress> resultList = bloodPressureDAO.getOverallStressPerWeek(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId(),dateRange);
		for(PatientOverallStress source: resultList){
			PatientOverallStressInfo target = new PatientOverallStressInfo();
			BeanUtils.copyProperties(source, target);
			respList.add(target);
		}
		
		List<PatientStepCountInfo> stepList = new ArrayList<PatientStepCountInfo>();
		List<PatientStepCount> PatientStepsList = bloodPressureDAO.getStepsCountPerWeek(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId(),dateRange);
		for(PatientStepCount source: PatientStepsList){
			PatientStepCountInfo target = new PatientStepCountInfo();
			BeanUtils.copyProperties(source, target);
			stepList.add(target);
		}
		
		List<PatientCalorieCountInfo> caloriesList = new ArrayList<PatientCalorieCountInfo>();
		List<PatientCalorieCount> patientCaloriesList = bloodPressureDAO.getCalorieCountPerWeek(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId(),dateRange);
		for(PatientCalorieCount source: patientCaloriesList){
			PatientCalorieCountInfo target = new PatientCalorieCountInfo();
			BeanUtils.copyProperties(source, target);
			caloriesList.add(target);
		}
		
		
		List<PatientDistanceInfo> distanceList = new ArrayList<PatientDistanceInfo>();
		List<PatientDistance> patientDistanceList = bloodPressureDAO.getDistanceCountPerWeek(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId(),dateRange);
		for(PatientDistance source: patientDistanceList){
			PatientDistanceInfo target = new PatientDistanceInfo();
			BeanUtils.copyProperties(source, target);
			distanceList.add(target);
		}
		
		
		List<PatientActiveTimeInfo> activeTimeList = new ArrayList<PatientActiveTimeInfo>();
		List<PatientActiveTime> patientActiveTimeList = bloodPressureDAO.getActiveTimeCountPerWeek(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId(),dateRange);
		for(PatientActiveTime source: patientActiveTimeList){
			PatientActiveTimeInfo target = new PatientActiveTimeInfo();
			BeanUtils.copyProperties(source, target);
			activeTimeList.add(target);
		}
		
		
		List<PatientSleepInfo> sleepList = new ArrayList<PatientSleepInfo>();
		List<PatientSleep> patientSleepList = bloodPressureDAO.getSleepCountPerWeek(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId(),dateRange);
		for(PatientSleep source: patientSleepList){
			PatientSleepInfo target = new PatientSleepInfo();
			BeanUtils.copyProperties(source, target);
			sleepList.add(target);
		}
		
		List<AccountWeight> weightList = bloodPressureDAO.getWeightList(nsRequest.getAccountInfo().getAccountId(),dateRange);
		List<AccountWeightInfo> accountWeight = new ArrayList<AccountWeightInfo>();
		for (AccountWeight accountWeightList : weightList) {
			AccountWeightInfo accountWeightInfo = new AccountWeightInfo();
			accountWeightInfo.setWeight(accountWeightList.getWeight());
			accountWeightInfo
					.setWeightUnits(accountWeightList.getWeightUnits());
			accountWeightInfo.setWeightId(accountWeightList.getWeightId());
			accountWeightInfo.setDateAdded(DateUtils.getFormatDate(
					accountWeightList.getDate(), "MM/dd/yyyy hh:mm a"));
			accountWeight.add(accountWeightInfo);

		}
		
		List<BloodPressureInfo> bloodPressureInfoList = new ArrayList<BloodPressureInfo>();
		List<AccountBloodPressureEngage> bloodPressureList = bloodPressureDAO.getAccountBloodPressureEngageByAccountId(nsRequest.getAccountInfo().getAccountId(),dateRange);
		for (AccountBloodPressureEngage accountBloodPressureEngages : bloodPressureList) {
			BloodPressureInfo bloodPressureInfo = new BloodPressureInfo();
			if (accountBloodPressureEngages.getDeleteFlag() == false) {
				bloodPressureInfo.setBloodPressureId(accountBloodPressureEngages.getAccountBloodPressureEngageId());
				bloodPressureInfo.setComment(accountBloodPressureEngages.getComment());
				bloodPressureInfo.setDia(accountBloodPressureEngages.getDia());
				bloodPressureInfo.setPulse(accountBloodPressureEngages.getPulse());
				bloodPressureInfo.setSys(accountBloodPressureEngages.getSys());
				bloodPressureInfo.setDeleteflag(accountBloodPressureEngages.getDeleteFlag());
				bloodPressureInfo.setReminderDate(DateUtils.getFormatDate(
						accountBloodPressureEngages.getReminderDate(),"MM/dd/yyyy hh:mm a"));
				bloodPressureInfoList.add(bloodPressureInfo);
			}
		}
		
		List<PatientHeartRateInfo> heartRateList = new ArrayList<PatientHeartRateInfo>();
		List<PatientHeartRate> patientHeartRateList = bloodPressureDAO.getPatientHeartRateList(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId(),dateRange);
		
		for(PatientHeartRate source: patientHeartRateList){
			PatientHeartRateInfo target = new PatientHeartRateInfo();
			BeanUtils.copyProperties(source, target);
			heartRateList.add(target);
		}
		
		List<PatientHeartAgeInfo> heartAgeList = new ArrayList<PatientHeartAgeInfo>();
		List<PatientHeartAge> patientHeartAgeList = bloodPressureDAO.getPatientHeartAge(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId(),dateRange);
		for(PatientHeartAge source: patientHeartAgeList){
			PatientHeartAgeInfo target = new PatientHeartAgeInfo();
			BeanUtils.copyProperties(source, target);
			heartAgeList.add(target);
		}
		
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		nsResponse.setOverallStressList(respList);
		nsResponse.setPatientStepCountList(stepList);
		nsResponse.setPatientCalorieCountList(caloriesList);
		nsResponse.setPatientDistanceList(distanceList);
		nsResponse.setPatientActiveTimeList(activeTimeList);
		nsResponse.setWeightList(accountWeight);
		nsResponse.setBloodPressureInfoList(bloodPressureInfoList);
		nsResponse.setPatientHeartRateList(heartRateList);
		nsResponse.setPatientHeartAgeList(heartAgeList);
		nsResponse.setPatientSleepList(sleepList);
		return nsResponse;
		
	}
	
	@Override
	public NsResponse getMyHealthInfo(NsRequest nsRequest)throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		int clientDbId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale()));
		int clientId = Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale()));
		
		PatientOverallStress patientOverallStress = bloodPressureDAO.getOverallStress(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId());
		PatientOverallStressInfo patientOverallStressInfo=null;
		if(patientOverallStress!=null)
		{
			patientOverallStressInfo = new PatientOverallStressInfo();
			patientOverallStressInfo.setValue(patientOverallStress.getOverallStress());
			patientOverallStressInfo.setDate(DateUtils.getFormatDate(patientOverallStress.getOverallStressDate(),DateUtils.DATE_ADDED_FORMAT));
			//BeanUtils.copyProperties(patientOverallStress, patientOverallStressInfo);
		}
		
		PatientDistanceInfo patientDistanceInfo = null;
		PatientDistance patientDistance = bloodPressureDAO.getDistanceCount(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId());
		if(patientDistance!=null)
		{
			patientDistanceInfo = new PatientDistanceInfo();
			patientDistanceInfo.setValue(patientDistance.getDistance());
			patientDistanceInfo.setDate(DateUtils.getFormatDate(patientDistance.getDistanceDate(),DateUtils.DATE_ADDED_FORMAT));
			//BeanUtils.copyProperties(patientDistance, patientDistanceInfo);
			
		}
		
		
		PatientStepCountInfo patientStepCountInfo = null;
		PatientStepCount patientStepCount = bloodPressureDAO.getStepsCount(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId());
		if(patientStepCount!=null)
		{
			patientStepCountInfo = new PatientStepCountInfo();
			patientStepCountInfo.setValue(patientStepCount.getStepCount());
			patientStepCountInfo.setDate(DateUtils.getFormatDate(patientStepCount.getStepCountDate(),DateUtils.DATE_ADDED_FORMAT));
			//BeanUtils.copyProperties(patientStepCount, patientStepCountInfo);			
		}
		
		
		PatientCalorieCountInfo patientCalorieCountInfo = null;
		PatientCalorieCount patientCalorieCount = bloodPressureDAO.getCalorieCount(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId());
		if(patientCalorieCount!=null)
		{
			patientCalorieCountInfo = new PatientCalorieCountInfo();
			patientCalorieCountInfo.setValue(patientCalorieCount.getCalorieCount());
			patientCalorieCountInfo.setDate(DateUtils.getFormatDate(patientCalorieCount.getCalorieCountDate(),DateUtils.DATE_ADDED_FORMAT));
			//BeanUtils.copyProperties(patientCalorieCount, patientCalorieCountInfo);
			
		}
		
		PatientActiveTimeInfo patientActiveTimeInfo = null;
		PatientActiveTime patientActiveTime = bloodPressureDAO.getActiveTimeCount(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId());
		if(patientActiveTime!=null)
		{
			patientActiveTimeInfo = new PatientActiveTimeInfo();
			patientActiveTimeInfo.setValue(patientActiveTime.getActiveTime());
			patientActiveTimeInfo.setDate(DateUtils.getFormatDate(patientActiveTime.getActiveTimeDate(),DateUtils.DATE_ADDED_FORMAT));
			//BeanUtils.copyProperties(patientActiveTime, patientActiveTime);
			
		}
		
		PatientSleepInfo patientSleepInfo = null;
		PatientSleep patientSleep = bloodPressureDAO.getSleepCount(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId());
		if(patientSleep!=null)
		{
			patientSleepInfo = new PatientSleepInfo();
			patientSleepInfo.setValue(patientSleep.getSleep());
			patientSleepInfo.setDate(DateUtils.getFormatDate(patientSleep.getSleepDate(),DateUtils.DATE_ADDED_FORMAT));
			//BeanUtils.copyProperties(patientSleep, patientSleepInfo);
			
		}
		
		PatientHeartRateInfo patientHeartRateInfo = null;
		PatientHeartRate patientHeartRate = bloodPressureDAO.getHeartRate(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId());		
		if(patientHeartRate!=null)
		{
			patientHeartRateInfo = new PatientHeartRateInfo();
			patientHeartRateInfo.setValue(patientHeartRate.getHeartRate());
			patientHeartRateInfo.setDate(DateUtils.getFormatDate(patientHeartRate.getHeartRateDate(),DateUtils.DATE_ADDED_FORMAT));
			//BeanUtils.copyProperties(patientHeartRate, patientHeartRateInfo);
			
		}
		
		PatientHeartAgeInfo patientHeartAgeInfo = null;
		PatientHeartAge patientHeartAge = bloodPressureDAO.getHeartAge(clientId,clientDbId, nsRequest.getAccountInfo().getAccountId());
		if(patientHeartAge!=null)
		{
			patientHeartAgeInfo = new PatientHeartAgeInfo();
			patientHeartAgeInfo.setValue(patientHeartAge.getHeartAge());
			patientHeartAgeInfo.setDate(DateUtils.getFormatDate(patientHeartAge.getHeartAgeDate(),DateUtils.DATE_ADDED_FORMAT));
			//BeanUtils.copyProperties(patientHeartAge, patientHeartAgeInfo);
			
		}
		
		AccountWeightInfo accountWeightInfo = null;
		AccountWeight accountWeight = bloodPressureDAO.getWeight(nsRequest.getAccountInfo().getAccountId());		
		if(accountWeight!=null){
			accountWeightInfo = new AccountWeightInfo();
			/*accountWeightInfo.setWeight(accountWeight.getWeight());
			accountWeightInfo.setWeightUnits(accountWeight.getWeightUnits());
			accountWeightInfo.setWeightId(accountWeight.getWeightId());
			accountWeightInfo.setDateAdded(DateUtils.getFormatDate(	accountWeight.getDate(), "MM/dd/yyyy hh:mm a"));*/	
			accountWeightInfo.setValue(accountWeight.getWeight());
			accountWeightInfo.setDate(DateUtils.getFormatDate(accountWeight.getDate(),DateUtils.DATE_ADDED_FORMAT));

		}
		
		BloodPressureInfo bloodPressureInfo = null;
		AccountBloodPressureEngage accountBloodPressureEngage = bloodPressureDAO.getBloodPressureEngage(nsRequest.getAccountInfo().getAccountId());
		if(accountBloodPressureEngage!=null) {
			bloodPressureInfo = new BloodPressureInfo();
			if (accountBloodPressureEngage.getDeleteFlag() == false) {
				/*bloodPressureInfo.setBloodPressureId(accountBloodPressureEngage.getAccountBloodPressureEngageId());
				bloodPressureInfo.setComment(accountBloodPressureEngage.getComment());
				bloodPressureInfo.setDia(accountBloodPressureEngage.getDia());
				bloodPressureInfo.setPulse(accountBloodPressureEngage.getPulse());
				bloodPressureInfo.setSys(accountBloodPressureEngage.getSys());
				bloodPressureInfo.setDeleteflag(accountBloodPressureEngage.getDeleteFlag());
				bloodPressureInfo.setReminderDate(DateUtils.getFormatDate(
						accountBloodPressureEngage.getReminderDate(),"MM/dd/yyyy hh:mm a"));*/
				
				
				
				StringBuilder sb1 = new StringBuilder();
				// sb1.append("weight/weightUnits :");
				sb1.append(accountBloodPressureEngage.getSys()).append("/");
				sb1.append(accountBloodPressureEngage.getDia());
				bloodPressureInfo.setValue(sb1.toString());
				bloodPressureInfo.setDate(DateUtils.getFormatDate(accountBloodPressureEngage.getReminderDate(),DateUtils.DATE_ADDED_FORMAT));
				
				
			}
		}		
		
		nsResponse.setBloodPressureInfo(bloodPressureInfo);
		nsResponse.setAccountWeightInfo(accountWeightInfo);
		nsResponse.setPatientHeartAgeInfo(patientHeartAgeInfo);
		nsResponse.setPatientHeartRateInfo(patientHeartRateInfo);
		nsResponse.setPatientSleepInfo(patientSleepInfo);
		nsResponse.setPatientActiveTimeInfo(patientActiveTimeInfo);
		nsResponse.setPatientCalorieCountInfo(patientCalorieCountInfo);
		nsResponse.setPatientStepCountInfo(patientStepCountInfo);
		nsResponse.setPatientDistanceInfo(patientDistanceInfo);
		nsResponse.setPatientOverallStressInfo(patientOverallStressInfo);
		responseData.setDescription("Success");
		responseData.setResult(0);
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}
	
	

}