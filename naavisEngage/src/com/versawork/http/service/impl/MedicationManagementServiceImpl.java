package com.versawork.http.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.AccountServiceDAO;
import com.versawork.http.dao.ClientNaavisDatabaseServiceDAO;
import com.versawork.http.dao.MedicationMgntDAO;
import com.versawork.http.dao.UserRegistrationDAO;
import com.versawork.http.dataobject.FrequencyDesc;
import com.versawork.http.dataobject.MedicationManagementReminderInfo;
import com.versawork.http.dataobject.MedicationManagmntInfo;
import com.versawork.http.dataobject.NsFrequencyList;
import com.versawork.http.dataobject.NsMedicationDosageList;
import com.versawork.http.dataobject.NsMedicationKindList;
import com.versawork.http.dataobject.NsMedicationMethodList;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.NsScheduleTime;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.AccountMedicationEngage;
import com.versawork.http.model.AccountMedicationManagement;
import com.versawork.http.model.AccountMedicationManagementReminder;
import com.versawork.http.model.AccountMedicationManagementSchedule;
import com.versawork.http.model.AccountReminderMedicationRelation;
import com.versawork.http.model.ClientNaavisDatabases;
import com.versawork.http.model.DatePart;
import com.versawork.http.model.FeedInfo;
import com.versawork.http.model.Frequency;
import com.versawork.http.model.MedicationDosage;
import com.versawork.http.model.MedicationKind;
import com.versawork.http.model.MedicationMethod;
import com.versawork.http.model.Response;
import com.versawork.http.service.MedicationManagementService;
import com.versawork.http.utils.DateUtils;
import com.versawork.http.utils.NsResponseUtils;

/**
 * @author Dheeraj
 * 
 */

@Component
public class MedicationManagementServiceImpl implements
		MedicationManagementService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MedicationManagementServiceImpl.class);

	// private static final long serialVersionUID = 1L;

	@Autowired
	private MessageSource messageSource;
	private static final String NO_REFILL_DATE_FOUND = "no.refill.date.found";
	private static final String CLIENT_DATABASE_ID = "client.database.id";
	private static final String CLIENT_ID = "client.id";
	private static final String FEATURE_ID = "feature.id";
	private static final String FEATURE_NAME = "feature.name";
	private static final String FEED_HEADER = "feed.header";
	@Autowired
	private MedicationMgntDAO medicationMgntDAO;
	
	@Autowired
	private UserRegistrationDAO userRegistrationDAO;
    
	@Autowired
	private AccountServiceDAO accountDAO;
	
	@Autowired
	private ClientNaavisDatabaseServiceDAO clientNaavisDatabaseServiceDAO;
	
	/*
	 * NsResponse nsResponse = new NsResponse(); ResponseData responsedata= new
	 * ResponseData();
	 */

	@Override
	public NsResponse saveMedicatnInfo(NsRequest nsRequest)
			throws BusinessException, SystemException {

		Account account = medicationMgntDAO.getAccountById(nsRequest
				.getAccountInfo().getAccountId());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		if (medicationAlreadyExists(nsRequest.getMedicationManagmntInfo()
				.getMedicationName(), nsRequest.getAccountInfo().getAccountId())) {
			throw new BusinessException("medication.already.exists");
		}

		AccountMedicationManagement accountMedicationManagement = new AccountMedicationManagement();
		MedicationManagmntInfo managmntInfo = new MedicationManagmntInfo();
		// accountMedicationManagement.setAccountMedicationManagementReminderId(accountMedicationManagementReminder);
		accountMedicationManagement.setAccountId(new Account(account
				.getAccountId()));
		if (nsRequest.getMedicationManagmntInfo().getMedicationImage() != null) {
			accountMedicationManagement.setMedicationImage(nsRequest
					.getMedicationManagmntInfo().getMedicationImage());
		}
		accountMedicationManagement.setMedicationName(nsRequest
				.getMedicationManagmntInfo().getMedicationName());
		/*
		 * accountMedicationManagement.setRxNumber(nsRequest.
		 * getMedicationManagmntInfo().getRxNumber());
		 */

		/*
		 * if (nsRequest.getMedicationManagmntInfo().getMedicationMethodId() ==
		 * 0) { throw new BusinessException("select.method.id"); }
		 */
		if (nsRequest.getMedicationManagmntInfo().getMedicationKindId() == 0) {
			throw new BusinessException("select.kind.id");
		}

		/*
		 * accountMedicationManagement.setMedicationMethodId(nsRequest.
		 * getMedicationManagmntInfo() .getMedicationMethodId());
		 */
		accountMedicationManagement.setMedicationKindId(new MedicationKind(
				nsRequest.getMedicationManagmntInfo().getMedicationKindId()));

		accountMedicationManagement.setNotes(nsRequest
				.getMedicationManagmntInfo().getNotes());
		accountMedicationManagement.setDateAdded(new Date());
		// Added for engage 2.0
		accountMedicationManagement.setDosage(nsRequest
				.getMedicationManagmntInfo().getDosageId());
		if (nsRequest.getMedicationManagmntInfo().getIsReminder() != false) {
			accountMedicationManagement.setRefillDate(DateUtils.getDate(
					nsRequest.getMedicationManagmntInfo().getRefillDate(),
					"MM/dd/yyyy"));
		
			//account.setClientDatabaseId(Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,Locale.getDefault())));
			//account.setClientId(Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, Locale.getDefault())));
			
			
		} else {
			accountMedicationManagement.setRefillDate(null);
		}
		accountMedicationManagement.setIsReminder(nsRequest
				.getMedicationManagmntInfo().getIsReminder());

		medicationMgntDAO.persist(accountMedicationManagement);

		managmntInfo.setMedictnMgmtId(accountMedicationManagement
				.getAccountMedicationManagementId());
		managmntInfo.setAccountId(accountMedicationManagement.getAccountId()
				.getAccountId());

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription(messageSource.getMessage(
				VersaWorkConstant.MEDICATION_ADDED_SUCCESSFULLY, null,
				nsRequest.getLocale()));
		nsResponse.setResponseData(responsedata);
		nsResponse.setMedicationManagmntInfo(managmntInfo);

		return nsResponse;

	}

	private Boolean medicationAlreadyExists(String medicationName, int accountId)
			throws BusinessException, SystemException {
		Boolean isExist = false;
		try {
			isExist = medicationMgntDAO.getMedicationByMedicationName(
					medicationName, accountId);
		} catch (BusinessException businessException) {
			// Do nothing,catch exception
		}
		return isExist;
	}

	@Override
	public NsResponse getMedicatnList(NsRequest nsRequest)
			throws BusinessException, SystemException {
		LOGGER.debug("Medication IMPLE " + medicationMgntDAO);
		List<AccountMedicationManagement> accountMedicationManagementList = medicationMgntDAO
				.getMedicationByActId(nsRequest.getMedicationManagmntInfo()
						.getAccountId());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		List<MedicationManagmntInfo> medicationInfoList = new ArrayList<MedicationManagmntInfo>();
		for (AccountMedicationManagement accountMedicationManagement : accountMedicationManagementList) {
			MedicationManagmntInfo medicationManagmntInfo = new MedicationManagmntInfo();
			medicationManagmntInfo.setMedictnMgmtId(accountMedicationManagement
					.getAccountMedicationManagementId());
			medicationManagmntInfo.setAccountId(accountMedicationManagement
					.getAccountId().getAccountId());

			medicationManagmntInfo
					.setMedicationImage(accountMedicationManagement
							.getMedicationImage());
			medicationManagmntInfo
					.setMedicationName(accountMedicationManagement
							.getMedicationName());
			// medicationManagmntInfo.setRxNumber(accountMedicationManagement.getRxNumber());
			medicationManagmntInfo.setNotes(accountMedicationManagement
					.getNotes());

			medicationManagmntInfo
					.setMedicationKindId(accountMedicationManagement
							.getMedicationKindId().getMedicationKindId());
			medicationManagmntInfo
					.setMedicationKindDesc(accountMedicationManagement
							.getMedicationKindId().getMedicationKind());
			medicationManagmntInfo.setIsReminder(accountMedicationManagement.getIsReminder());
			/*
			 * medicationManagmntInfo.setMedicationMethodId(
			 * accountMedicationManagement.getMedicationMethodId()
			 * .getMedicationMethodId());
			 * medicationManagmntInfo.setMedicationMethodDesc
			 * (accountMedicationManagement.getMedicationMethodId()
			 * .getMedicationMethod());
			 */
			medicationInfoList.add(medicationManagmntInfo);
		}

		if (medicationInfoList.size() == 0) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("No Medications Present");
			nsResponse.setResponseData(responsedata);
		} else if (medicationInfoList.size() > 0) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("Success");
			nsResponse.setResponseData(responsedata);
			nsResponse.setMedicationManagmntInfoList(medicationInfoList);
		}
		return nsResponse;
	}

	public NsResponse editMedicatnMgmtInfo(NsRequest nsRequest)
			throws BusinessException, SystemException {
		AccountMedicationManagement accountMedicationManagement = medicationMgntDAO
				.getDetailsByActIdMedId(nsRequest.getMedicationManagmntInfo()
						.getMedictnMgmtId(), nsRequest
						.getMedicationManagmntInfo().getAccountId());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		// commented by preet as it should not check name while editing
		// medication
		/*
		 * if (medicationAlreadyExists(nsRequest.getMedicationManagmntInfo().
		 * getMedicationName
		 * (),nsRequest.getMedicationManagmntInfo().getAccountId())) { throw new
		 * BusinessException("medication.already.exists"); }
		 */
		if (nsRequest.getMedicationManagmntInfo().getMedicationImage() != null) {
			accountMedicationManagement.setMedicationImage(nsRequest
					.getMedicationManagmntInfo().getMedicationImage());
		}
		accountMedicationManagement.setMedicationName(nsRequest
				.getMedicationManagmntInfo().getMedicationName());
		// accountMedicationManagement.setRxNumber(nsRequest.getMedicationManagmntInfo().getRxNumber());
		accountMedicationManagement.setNotes(nsRequest
				.getMedicationManagmntInfo().getNotes());
		/*
		 * accountMedicationManagement.setMedicationMethodId(new
		 * MedicationMethod(nsRequest.getMedicationManagmntInfo()
		 * .getMedicationMethodId()));
		 */

		accountMedicationManagement.setMedicationKindId(new MedicationKind(
				nsRequest.getMedicationManagmntInfo().getMedicationKindId()));
		// accountMedicationManagement.setDateAdded(new Date());

		medicationMgntDAO.update(accountMedicationManagement);
		List<AccountReminderMedicationRelation> accountReminderMedicationRelationListFromDB = accountMedicationManagement
				.getAccountReminderMedicationRelationList();

		for (AccountReminderMedicationRelation accountReminderMedicationRelation : accountReminderMedicationRelationListFromDB) {
			accountReminderMedicationRelation.setAlias(nsRequest
					.getMedicationManagmntInfo().getMedicationName());
			medicationMgntDAO.update(accountReminderMedicationRelation);
		}

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Updated Successfully");
		nsResponse.setResponseData(responsedata);

		return nsResponse;
	}

	@Override
	public NsResponse deleteMedication(NsRequest nsRequest)
			throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		deleteReminder(
				nsRequest.getMedicationManagmntInfo().getMedictnMgmtId(),
				nsRequest.getAccountInfo().getAccountId());

		AccountMedicationManagement accountMedicationManagement = medicationMgntDAO
				.getDetailsByActIdMedId(nsRequest.getMedicationManagmntInfo()
						.getMedictnMgmtId(), nsRequest.getAccountInfo()
						.getAccountId());
		String medicationManagementName=accountMedicationManagement.getMedicationName();
		/*
		 * if
		 * (accountMedicationManagement.getAccountReminderMedicationRelationList
		 * ().size() > 0) {
		 * responsedata.setResult(VersaWorkConstant.Status_Codes
		 * .FAILURE_RESPONSE_CODE.getCode()); responsedata.setDescription(
		 * "Cannot delete medication since it is being used in other reminders."
		 * );
		 * 
		 * nsResponse.setResponseData(responsedata);
		 * 
		 * return nsResponse; }
		 */

		medicationMgntDAO.remove(accountMedicationManagement);
		
		Integer clientDatabaseId=Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,	Locale.getDefault()));
		Integer clientId=Integer.parseInt(messageSource.getMessage(CLIENT_ID, null,	Locale.getDefault()));
		Integer accountId=nsRequest.getAccountInfo().getAccountId();
		
		FeedInfo feedInfo=userRegistrationDAO.getFeedInfo(accountId, clientId, clientDatabaseId, medicationManagementName);
		if(feedInfo!=null){
			userRegistrationDAO.remove(feedInfo);
		}
		

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription(messageSource.getMessage("med.del", null,
				nsRequest.getLocale()));

		nsResponse.setResponseData(responsedata);

		return nsResponse;
	}

	// @Override
	public NsResponse saveMedicatnReminder(NsRequest nsRequest,
			NsResponse accountMedicationManagementInfo)
			throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		AccountMedicationManagementReminder accountMedicationManagementReminder = new AccountMedicationManagementReminder();

		FrequencyDesc frequencyDesc = nsRequest
				.getMedicationManagementReminder().getFrequencyDesc();

		accountMedicationManagementReminder.setAccountId(new Account(nsRequest.getAccountInfo().getAccountId()));
		accountMedicationManagementReminder.setReminderFor(frequencyDesc
				.getReminderFor());
		accountMedicationManagementReminder.setReminderBeginDate(new Date());
		accountMedicationManagementReminder.setReminderEndDate(new Date());
		/*
		 * accountMedicationManagementReminder.setReminderBeginDate(DateUtils.
		 * getDate(nsRequest
		 * .getMedicationManagementReminder().getReminderBeginDate(),
		 * "MM/dd/yyyy")); // Don't // change // the // Date //
		 * pattern(dependency // on // IOS)
		 * accountMedicationManagementReminder.setReminderEndDate
		 * (DateUtils.getDate(nsRequest
		 * .getMedicationManagementReminder().getReminderEndDate(),
		 * "MM/dd/yyyy")); // Don't // change // the // Date //
		 * pattern(dependency // on // IOS)
		 */// accountMedicationManagementReminder.setFrequencyType(frequencyDesc.getFrequencyId());
		accountMedicationManagementReminder.setFrequencyType(frequencyDesc
				.getFrequencyId());
		accountMedicationManagementReminder.setFrequencyValue(frequencyDesc
				.getFrequencyValue());
		accountMedicationManagementReminder
				.setDosage(frequencyDesc.getDosage());
		// accountMedicationManagementReminder.setMedicationDosageId(new
		// MedicationDosage(frequencyDesc.getMedicationDosageId()));
		accountMedicationManagementReminder.setDateAdded(new Date());
		accountMedicationManagementReminder.setDeleteFlag("false");
		accountMedicationManagementReminder.setIsActive(true);

		medicationMgntDAO.persist(accountMedicationManagementReminder);

		/*
		 * List<MedicationManagmntInfo> medicationManagmntInfoList =
		 * nsRequest.getMedicationManagementReminder
		 * ().getMedicationManagmntInfoList(); for (MedicationManagmntInfo
		 * medicationManagmntInfos : medicationManagmntInfoList) {
		 * AccountReminderMedicationRelation accountReminderMedicationRelation =
		 * new AccountReminderMedicationRelation();
		 * accountReminderMedicationRelation
		 * .setAccountMedicationManagementReminderId
		 * (accountMedicationManagementReminder);
		 * accountReminderMedicationRelation
		 * .setAccountMedicationManagementId(new AccountMedicationManagement(
		 * medicationManagmntInfos.getMedictnMgmtId()));
		 * accountReminderMedicationRelation.setDateAdded(new Date()); if
		 * (medicationManagmntInfos.getMedicationName() == null) {
		 * LOGGER.info("got null for medication name..."); }
		 * accountReminderMedicationRelation
		 * .setAlias(medicationManagmntInfos.getMedicationName());
		 * 
		 * medicationMgntDAO.persist(accountReminderMedicationRelation); }
		 */

		// System.out.println(
		// accountMedicationManagementReminder.getAccountMedicationManagementReminderId());

		List<NsScheduleTime> accountMedicationManagementScheduleList = nsRequest
				.getMedicationManagementReminder().getNsScheduleTimeList();
		for (NsScheduleTime nsScheduleTime : accountMedicationManagementScheduleList) {
			AccountMedicationManagementSchedule accountMedicationManagementSchedule = new AccountMedicationManagementSchedule();

			accountMedicationManagementSchedule
					.setAccountMedicationManagementReminderId(accountMedicationManagementReminder);

			accountMedicationManagementSchedule
					.setAccountMedicationManagementId(new AccountMedicationManagement(
							accountMedicationManagementInfo
									.getMedicationManagmntInfo()
									.getMedictnMgmtId()));
			accountMedicationManagementSchedule.setReminderTime(DateUtils
					.get24FormatDate(nsScheduleTime.getReminderTime()));
			/*if (frequencyDesc.getDatePartId() != 0) {
				accountMedicationManagementSchedule.setDatePartId(new DatePart(
						frequencyDesc.getDatePartId()));
			}
			accountMedicationManagementSchedule.setInterval(frequencyDesc
					.getInterval());*/
			accountMedicationManagementSchedule.setDateAdded(new Date());
			accountMedicationManagementSchedule.setReminderDay(nsScheduleTime
					.getReminderDay());

			medicationMgntDAO.persist(accountMedicationManagementSchedule);

		}

		AccountReminderMedicationRelation accountReminderMedicationRelation = new AccountReminderMedicationRelation();
		accountReminderMedicationRelation
				.setAccountMedicationManagementReminderId(new AccountMedicationManagementReminder(
						accountMedicationManagementReminder
								.getAccountMedicationManagementReminderId()));

		accountReminderMedicationRelation
				.setAccountMedicationManagementId(new AccountMedicationManagement(
						accountMedicationManagementInfo
								.getMedicationManagmntInfo().getMedictnMgmtId()));
		accountReminderMedicationRelation.setDateAdded(new Date());
		accountReminderMedicationRelation.setAlias(nsRequest
				.getMedicationManagmntInfo().getMedicationName());
		medicationMgntDAO.persist(accountReminderMedicationRelation);

		// List<MedicationManagementReminderInfo>
		// medicationManagementReminderList = new
		// ArrayList<MedicationManagementReminderInfo>();
		MedicationManagementReminderInfo medicationManagementReminder = new MedicationManagementReminderInfo();
		medicationManagementReminder
				.setMedictnReminderId(accountMedicationManagementReminder
						.getAccountMedicationManagementReminderId());
		// medicationManagementReminderList.add(medicationManagementReminder);

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("success");
		// nsResponse.setMedicationManagementReminderList(medicationManagementReminderList);
		
		nsResponse.setReminderId(medicationManagementReminder
				.getMedictnReminderId());
		nsResponse.setResponseData(responsedata);

		return nsResponse;

	}

	@Override
	public NsResponse getMedicatnReminder(NsRequest nsRequest)
			throws BusinessException, SystemException {

		List<AccountMedicationManagementReminder> accountMedicationManagementReminders = medicationMgntDAO
				.getReminderByActIdDeleteFlag(nsRequest
						.getMedicationManagementReminder().getAccountId());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		List<Frequency> frequencyList = medicationMgntDAO
				.getFrequencyListByLangCode(nsRequest.getLocale().toString());
		List<MedicationManagementReminderInfo> medicationManagementReminderList = new ArrayList<MedicationManagementReminderInfo>();
		for (AccountMedicationManagementReminder accountMedicationManagementReminder : accountMedicationManagementReminders) {
			FrequencyDesc frequencyDesc = new FrequencyDesc();
			if (accountMedicationManagementReminder.getFrequencyType() != null) {
				for (Frequency frequency : frequencyList) {
					if (accountMedicationManagementReminder.getFrequencyType() == frequency
							.getType()) {
						frequencyDesc.setFrequencyId(frequency.getType());
						frequencyDesc
								.setFrequencyDesc(frequency.getFrequency());
						/*if (frequency.getDatePartId() != null) {
							frequencyDesc.setDatePartId(frequency
									.getDatePartId().getDatePartId());
						}*/
					}
				}
			}

			NsMedicationDosageList medicationDosage = new NsMedicationDosageList();
			/*
			 * if (accountMedicationManagementReminder.getMedicationDosageId()
			 * != null) { medicationDosage.setMedicationDosageId(
			 * accountMedicationManagementReminder.getMedicationDosageId()
			 * .getMedicationDosageId());
			 * medicationDosage.setMedicationDosageDesc
			 * (accountMedicationManagementReminder.getMedicationDosageId()
			 * .getMedicationDosage()); }
			 */

			MedicationManagementReminderInfo medicationManagementReminder = new MedicationManagementReminderInfo();
			medicationManagementReminder
					.setMedictnReminderId(accountMedicationManagementReminder
							.getAccountMedicationManagementReminderId());
			medicationManagementReminder
					.setAccountId(accountMedicationManagementReminder
							.getAccountId().getAccountId());
			medicationManagementReminder
					.setReminderFor(accountMedicationManagementReminder
							.getReminderFor());
			medicationManagementReminder
					.setActive(accountMedicationManagementReminder
							.getIsActive());
			medicationManagementReminder.setReminderBeginDate(DateUtils
					.getFormatDate(accountMedicationManagementReminder
							.getReminderBeginDate(), "dd-MM-yyyy")); // Don't
																		// change
																		// the
																		// Date
																		// pattern(dependency
																		// on
																		// IOS)
			medicationManagementReminder.setReminderEndDate(DateUtils
					.getFormatDate(accountMedicationManagementReminder
							.getReminderEndDate(), "dd-MM-yyyy")); // Don't
																	// change
																	// the
																	// Date
																	// pattern(dependency
																	// on
																	// IOS)
			medicationManagementReminder
					.setDosage(accountMedicationManagementReminder.getDosage());
			medicationManagementReminder.setFrequencyDesc(frequencyDesc);
			medicationManagementReminder.setMedicationDosage(medicationDosage);

			List<AccountReminderMedicationRelation> accountReminderMedicationRelationListFromDB = accountMedicationManagementReminder
					.getAccountReminderMedicationRelationList();
			List<MedicationManagmntInfo> medicationInfoList = new ArrayList<MedicationManagmntInfo>();

			for (AccountReminderMedicationRelation accountReminderMedicationRelation : accountReminderMedicationRelationListFromDB) {
				MedicationManagmntInfo medicationInfo = new MedicationManagmntInfo();
				if (accountReminderMedicationRelation
						.getAccountMedicationManagementId() != null) {
					medicationInfo
							.setMedictnMgmtId(accountReminderMedicationRelation
									.getAccountMedicationManagementId()
									.getAccountMedicationManagementId());
					medicationInfo
							.setMedicationName(accountReminderMedicationRelation
									.getAccountMedicationManagementId()
									.getMedicationName());
					/*
					 * medicationInfo.setRxNumber(accountReminderMedicationRelation
					 * .getAccountMedicationManagementId() .getRxNumber());
					 */
					medicationInfo.setNotes(accountReminderMedicationRelation
							.getAccountMedicationManagementId().getNotes());
					medicationInfo
							.setMedicationKindDesc(accountReminderMedicationRelation
									.getAccountMedicationManagementId()
									.getMedicationKindId().getMedicationKind());
					/*
					 * medicationInfo.setMedicationMethodDesc(
					 * accountReminderMedicationRelation
					 * .getAccountMedicationManagementId
					 * ().getMedicationMethodId().getMedicationMethod());
					 */
					medicationInfoList.add(medicationInfo);

					medicationInfo
							.setMedicationKindId(accountReminderMedicationRelation
									.getAccountMedicationManagementId()
									.getMedicationKindId()
									.getMedicationKindId());
					/*
					 * medicationInfo.setMedicationMethodId(
					 * accountReminderMedicationRelation
					 * .getAccountMedicationManagementId
					 * ().getMedicationMethodId().getMedicationMethodId());
					 */
				}
			}

			medicationManagementReminder
					.setMedicationManagmntInfoList(medicationInfoList);

			List<AccountMedicationManagementSchedule> accountMedicationManagementSchedulesListFromDB = accountMedicationManagementReminder
					.getAccountMedicationManagementScheduleList();

			List<NsScheduleTime> nsMedicationScheduleList = new ArrayList<NsScheduleTime>();

			for (AccountMedicationManagementSchedule accountMedicationManagementSchedule : accountMedicationManagementSchedulesListFromDB) {
				NsScheduleTime medicationScheduleTime = new NsScheduleTime();
				medicationScheduleTime
						.setScheduleId(accountMedicationManagementSchedule
								.getAccountMedicationManagementScheduleId());
				medicationScheduleTime.setReminderTime(DateUtils.getFormatDate(
						accountMedicationManagementSchedule.getReminderTime(),
						"hh:mm a")); // Don't
										// change
										// the
										// Time
										// pattern(dependency
										// on
										// IOS)
				nsMedicationScheduleList.add(medicationScheduleTime);
			}
			Collections.reverse(nsMedicationScheduleList);
			medicationManagementReminder
					.setNsScheduleTimeList(nsMedicationScheduleList);
			medicationManagementReminderList.add(medicationManagementReminder);
		}

		if (medicationManagementReminderList == null
				|| medicationManagementReminderList.size() == 0) {
			responsedata
					.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE
							.getCode());
			responsedata.setDescription("No Reminders Present");
			nsResponse.setResponseData(responsedata);
			nsResponse.setMedicationRemindersCount(0);

		} else if (medicationManagementReminderList.size() > 0) {
			responsedata
					.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE
							.getCode());
			responsedata.setDescription("success");
			nsResponse.setResponseData(responsedata);
			nsResponse
					.setMedicationManagementReminderList(medicationManagementReminderList);
			nsResponse
					.setMedicationRemindersCount(medicationManagementReminderList
							.size());
		}

		return nsResponse;
	}

	@Override
	public NsResponse editMedicatnReminder(NsRequest nsRequest)
			throws BusinessException, SystemException {

		AccountMedicationManagementReminder accountMedicationManagementReminder = medicationMgntDAO
				.getDetailsByRemIdActId(nsRequest
						.getMedicationManagementReminder()
						.getMedictnReminderId(), nsRequest
						.getMedicationManagementReminder().getAccountId());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		FrequencyDesc frequencyDesc = nsRequest
				.getMedicationManagementReminder().getFrequencyDesc();

		accountMedicationManagementReminder.setReminderFor(nsRequest
				.getMedicationManagementReminder().getReminderFor());
		accountMedicationManagementReminder.setReminderBeginDate(DateUtils
				.getDate(nsRequest.getMedicationManagementReminder()
						.getReminderBeginDate(), "MM/dd/yyyy")); // Don't
																	// change
																	// the
																	// Date
																	// pattern(dependency
																	// on
																	// IOS)
		accountMedicationManagementReminder.setReminderEndDate(DateUtils
				.getDate(nsRequest.getMedicationManagementReminder()
						.getReminderEndDate(), "MM/dd/yyyy")); // Don't
																// change
																// the
																// Date
																// pattern(dependency
																// on
																// IOS)
		accountMedicationManagementReminder.setFrequencyType(frequencyDesc
				.getFrequencyId());
		accountMedicationManagementReminder.setDosage(nsRequest
				.getMedicationManagementReminder().getDosage());
		/*
		 * accountMedicationManagementReminder.setMedicationDosageId(new
		 * MedicationDosage(nsRequest
		 * .getMedicationManagementReminder().getMedicationDosageId()));
		 */
		accountMedicationManagementReminder.setDateAdded(new Date());
		accountMedicationManagementReminder.setDeleteFlag("false");
		accountMedicationManagementReminder.setIsActive(nsRequest
				.getMedicationManagementReminder().isActive());

		medicationMgntDAO.update(accountMedicationManagementReminder);

		List<AccountReminderMedicationRelation> accountReminderMedicationRelationListFromDB = accountMedicationManagementReminder
				.getAccountReminderMedicationRelationList();

		for (AccountReminderMedicationRelation accountReminderMedicationRelation : accountReminderMedicationRelationListFromDB) {
			medicationMgntDAO.remove(accountReminderMedicationRelation);
		}
		accountReminderMedicationRelationListFromDB.clear();
		// medicationMgntDAO.flush();

		List<MedicationManagmntInfo> medicationManagmntInfoList = nsRequest
				.getMedicationManagementReminder()
				.getMedicationManagmntInfoList();
		for (MedicationManagmntInfo medicationManagmntInfo : medicationManagmntInfoList) {
			AccountReminderMedicationRelation accountReminderMedicationRelation = new AccountReminderMedicationRelation();
			accountReminderMedicationRelation
					.setAccountMedicationManagementReminderId(accountMedicationManagementReminder);
			accountReminderMedicationRelation
					.setAccountMedicationManagementId(new AccountMedicationManagement(
							medicationManagmntInfo.getMedictnMgmtId()));
			accountReminderMedicationRelation.setDateAdded(new Date());
			if (medicationManagmntInfo.getMedicationName() == null) {
				LOGGER.info("no medication name found ");
			}
			accountReminderMedicationRelation.setAlias(medicationManagmntInfo
					.getMedicationName());
			medicationMgntDAO.persist(accountReminderMedicationRelation);
		}
		// List<AccountMedicationManagementSchedule>
		// accountMedicationManagementSchedulesList = new
		// ArrayList<AccountMedicationManagementSchedule>();

		List<AccountMedicationManagementSchedule> accountMedicationManagementSchedulesListFromDB = accountMedicationManagementReminder
				.getAccountMedicationManagementScheduleList();

		for (AccountMedicationManagementSchedule accountMedicationManagementSchedule : accountMedicationManagementSchedulesListFromDB) {
			medicationMgntDAO.remove(accountMedicationManagementSchedule);
		}

		// medicationMgntDAO.flush();

		List<NsScheduleTime> accountMedicationManagementScheduleList = nsRequest
				.getMedicationManagementReminder().getNsScheduleTimeList();

		for (NsScheduleTime nsScheduleTime : accountMedicationManagementScheduleList) {
			AccountMedicationManagementSchedule accountMedicationManagementSchedule = new AccountMedicationManagementSchedule();

			accountMedicationManagementSchedule
					.setAccountMedicationManagementReminderId(accountMedicationManagementReminder);
			accountMedicationManagementSchedule.setReminderTime(DateUtils
					.get24FormatDate(nsScheduleTime.getReminderTime())); // Don't
																			// change
																			// the
																			// Time
																			// pattern(dependency
																			// on
																			// IOS)
			
			accountMedicationManagementSchedule.setDateAdded(new Date());

			medicationMgntDAO.persist(accountMedicationManagementSchedule);
		}

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("success");
		List<MedicationManagementReminderInfo> medicationManagementReminderList = new ArrayList<MedicationManagementReminderInfo>();
		MedicationManagementReminderInfo medicationManagementReminder = new MedicationManagementReminderInfo();
		medicationManagementReminder
				.setMedictnReminderId(accountMedicationManagementReminder
						.getAccountMedicationManagementReminderId());
		medicationManagementReminderList.add(medicationManagementReminder);
		nsResponse
				.setMedicationManagementReminderList(medicationManagementReminderList);
		nsResponse.setResponseData(responsedata);

		return nsResponse;
	}

	public void deleteReminder(Integer medicationManagementId, Integer accountId)
			throws BusinessException, SystemException {

		AccountMedicationManagementReminder accountMedicationManagementReminderInfo = new AccountMedicationManagementReminder();
		AccountReminderMedicationRelation accountReminderMedicationRelationList = medicationMgntDAO
				.getMedicationRelation(medicationManagementId);

		if (accountReminderMedicationRelationList != null) {
			accountMedicationManagementReminderInfo
					.setAccountMedicationManagementReminderId(accountReminderMedicationRelationList
							.getAccountMedicationManagementReminderId()
							.getAccountMedicationManagementReminderId());

			AccountMedicationManagementReminder accountMedicationManagementReminder = medicationMgntDAO
					.getDetailsByRemIdActId(
							accountMedicationManagementReminderInfo
									.getAccountMedicationManagementReminderId(),
							accountId);

			// NsResponse nsResponse = new NsResponse();
			// ResponseData responsedata = new ResponseData();

			// Deleting Responses from Database

			List<AccountMedicationEngage> AccountMedicationEngageList = medicationMgntDAO
					.getReminderResponseByRemId(accountMedicationManagementReminder
							.getAccountMedicationManagementReminderId());

			for (AccountMedicationEngage accountMedicationEngage : AccountMedicationEngageList) {
				medicationMgntDAO.remove(accountMedicationEngage);
			}
			AccountMedicationEngageList.clear();

			// Deleting Medication Relation from database

			List<AccountReminderMedicationRelation> accountReminderMedicationRelationListFromDB = accountMedicationManagementReminder
					.getAccountReminderMedicationRelationList();

			for (AccountReminderMedicationRelation accountReminderMedicationRelation : accountReminderMedicationRelationListFromDB) {
				medicationMgntDAO.remove(accountReminderMedicationRelation);
			}
			accountReminderMedicationRelationListFromDB.clear();

			// Deleting Schedule from Database

			/*
			 * List<AccountMedicationManagementSchedule>
			 * accountMedicationManagementSchedulesListFromDB =
			 * accountMedicationManagementReminder
			 * .getAccountMedicationManagementScheduleList();
			 */
			List<AccountMedicationManagementSchedule> accountMedicationManagementScheduleInfoList = medicationMgntDAO
					.getMedicationManagementScheduleList(accountMedicationManagementReminder
							.getAccountMedicationManagementReminderId());

			for (AccountMedicationManagementSchedule accountMedicationManagementSchedule : accountMedicationManagementScheduleInfoList) {
				medicationMgntDAO.remove(accountMedicationManagementSchedule);
			}
			accountMedicationManagementScheduleInfoList.clear();

			medicationMgntDAO.remove(accountMedicationManagementReminder);
		}
		// medicationMgntDAO.flush();

		/*
		 * responsedata.setResult(Integer.parseInt(messageSource.getMessage(
		 * VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
		 * nsRequest.getLocale())));
		 * responsedata.setDescription(messageSource.getMessage("med.rem.del",
		 * null, nsRequest.getLocale()));// ("Deleted Medication Reminder");
		 * nsResponse.setResponseData(responsedata);
		 */
		// return nsResponse;
	}

	@Override
	public NsResponse saveReminderResponse(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		AccountMedicationEngage accountMedicationEngage = null;
		List<MedicationManagementReminderInfo> medicationManagementReminder = nsRequest
				.getMedicationManagementReminderInfoList();
		for (MedicationManagementReminderInfo medicationManagementReminderInfo : medicationManagementReminder) {
			accountMedicationEngage = new AccountMedicationEngage();
			/*
			 * accountMedicationEngage.setAccountId(nsRequest.getAccountInfo().
			 * getAccountId());
			 * accountMedicationEngage.setAccountMedicationEngageId
			 * (medicationManagementReminderInfo.getMedictnMgmtengageId());
			 * accountMedicationEngage
			 * .setAccountMedicationManagementReminderId(new
			 * AccountMedicationManagementReminder
			 * (medicationManagementReminderInfo.getMedictnReminderId()));
			 * accountMedicationEngage.setResponseId(new
			 * Response(medicationManagementReminderInfo.getResponseId()));
			 * accountMedicationEngage.setReminderDate(new Date());
			 * accountMedicationEngage
			 * .setComment(medicationManagementReminderInfo.getComment());
			 * accountMedicationEngage.setDateAdded(new Date());
			 * medicationMgntDAO.persist(accountMedicationEngage);
			 */

			AccountMedicationManagementReminder accountMedicationManagementReminder = medicationMgntDAO
					.getReminderById(medicationManagementReminderInfo
							.getMedictnReminderId());
			if (accountMedicationManagementReminder == null) {
				responsedata
						.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE
								.getCode());
				responsedata.setDescription(messageSource.getMessage(
						"med.reminder.stale.msg", null, nsRequest.getLocale()));
			} else {
				if (medicationManagementReminderInfo.getRecordIdentifier() != null) {
					accountMedicationEngage = medicationMgntDAO
							.getReminderResponseByRecordIdentifier(nsRequest
									.getAccountInfo().getAccountId(),
									medicationManagementReminderInfo
											.getMedictnReminderId(),
									medicationManagementReminderInfo
											.getRecordIdentifier());

					if (accountMedicationEngage == null) {
						accountMedicationEngage = new AccountMedicationEngage();
					}
				} else {
					accountMedicationEngage = new AccountMedicationEngage();
				}
				accountMedicationEngage.setAccountId(new Account(nsRequest
						.getAccountInfo().getAccountId()).getAccountId());
				accountMedicationEngage
						.setAccountMedicationManagementReminderId(new AccountMedicationManagementReminder(
								medicationManagementReminderInfo
										.getMedictnReminderId()));
				accountMedicationEngage.setResponseId(new Response(
						medicationManagementReminderInfo.getResponseId()));
				accountMedicationEngage.setReminderDate(DateUtils.getDate(
						medicationManagementReminderInfo.getReminderDate(),
						"MM/dd/yyyy")); // Don't
										// change
										// the
										// Date
										// pattern(dependency
										// on
										// IOS)
				accountMedicationEngage
						.setComment(medicationManagementReminderInfo
								.getComment());
				accountMedicationEngage.setDateAdded(new Date());
				accountMedicationEngage
						.setRecordIdentifier(medicationManagementReminderInfo
								.getRecordIdentifier());

				medicationMgntDAO.persist(accountMedicationEngage);

				responsedata.setResult(Integer.parseInt(messageSource
						.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE,
								null, nsRequest.getLocale())));
				responsedata.setDescription("success");
			}
		}
		nsResponse.setResponseData(responsedata);

		return nsResponse;
	}

	@Override
	public NsResponse getReminderResponse(NsRequest nsRequest)
			throws BusinessException, SystemException {
		Integer yesCount = 0;
		List<AccountMedicationEngage> AccountMedicationEngageList = medicationMgntDAO
				.getReminderResponseByActId(nsRequest
						.getMedicationManagementReminder().getAccountId());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		List<MedicationManagementReminderInfo> medicationManagementReminderList = new ArrayList<MedicationManagementReminderInfo>();
		for (AccountMedicationEngage accountMedicationEngage : AccountMedicationEngageList) {
			MedicationManagementReminderInfo medicationManagementReminder = new MedicationManagementReminderInfo();

			medicationManagementReminder.setReminderDate(DateUtils
					.getFormatDate(accountMedicationEngage.getReminderDate(),
							"dd-MM-yyyy HH:mm")); // Don't
													// change
													// the
													// Date
													// pattern(dependency
													// on
													// IOS)
			medicationManagementReminder.setResponseId(accountMedicationEngage
					.getResponseId().getResponseId());
			medicationManagementReminder
					.setResponseDesc(accountMedicationEngage.getComment());
			if (accountMedicationEngage.getResponseId().getResponseId() == 1) {
				yesCount++;
			}
			medicationManagementReminderList.add(medicationManagementReminder);
		}
		if (medicationManagementReminderList.size() == 0) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("No Response for Reminders Present");
			nsResponse.setResponseData(responsedata);
			nsResponse.setMedicationYesCount(0);
			nsResponse.setMedicationTotalCount(0);
		} else if (medicationManagementReminderList.size() > 0) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("success");
			nsResponse.setResponseData(responsedata);
			nsResponse
					.setMedicationManagementReminderList(medicationManagementReminderList);
			nsResponse.setMedicationYesCount(yesCount);
			nsResponse.setMedicationTotalCount(medicationManagementReminderList
					.size());
		}
		return nsResponse;
	}

	public NsResponse getMedicationReminderCountResponse(NsRequest nsRequest)
			throws BusinessException, SystemException {
		Integer yesCount = 0;
		List<AccountMedicationEngage> AccountMedicationEngageList = medicationMgntDAO
				.getReminderResponseCountByActId(nsRequest
						.getMedicationManagementReminder().getAccountId());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		List<MedicationManagementReminderInfo> medicationManagementReminderList = new ArrayList<MedicationManagementReminderInfo>();

		for (AccountMedicationEngage accountMedicationEngage : AccountMedicationEngageList) {
			MedicationManagementReminderInfo medicationManagementReminder = new MedicationManagementReminderInfo();
			medicationManagementReminder.setResponseId(accountMedicationEngage
					.getResponseId().getResponseId());

			if (accountMedicationEngage.getResponseId().getResponseId() == 1) {
				yesCount++;
			}
			medicationManagementReminderList.add(medicationManagementReminder);
		}
		if (medicationManagementReminderList.size() == 0) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("No Response for Reminders Present");
			nsResponse.setResponseData(responsedata);
			nsResponse.setMedicationYesCount(0);
			nsResponse.setMedicationTotalCount(0);
		} else if (medicationManagementReminderList.size() > 0) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("success");
			nsResponse.setResponseData(responsedata);
			nsResponse
					.setMedicationManagementReminderList(medicationManagementReminderList);
			nsResponse.setMedicationYesCount(yesCount);
			nsResponse.setMedicationTotalCount(medicationManagementReminderList
					.size());
		}
		return nsResponse;
	}

	@Override
	public NsResponse getFrequencyDesc(NsRequest nsRequest)
			throws BusinessException, SystemException {
		Integer datePartId = 0;
		List<Frequency> frequencyList = medicationMgntDAO
				.getFrequencyListByLangCode(nsRequest.getLocale().toString());

		List<NsFrequencyList> nsFrequencyList = new ArrayList<NsFrequencyList>();
		for (Frequency frequency : frequencyList) {
			/*if (frequency.getDatePartId() == null) {
				// datePartId = 0;
			} else {
				datePartId = frequency.getDatePartId().getDatePartId();
			}*/
			NsFrequencyList nsFrequency = new NsFrequencyList();
			nsFrequency.setFrequencyId(frequency.getType()); // Type will decide
																// the frequency
			nsFrequency.setFrequency(frequency.getFrequency());
			//nsFrequency.setDatePartId(datePartId);
			//nsFrequency.setInterval(frequency.getInterval());
			nsFrequencyList.add(nsFrequency);

		}
		NsResponse nsResponse = NsResponseUtils.getNsResponse(null,
				messageSource.getMessage("rsp.data.dscrptn.success", null,
						nsRequest.getLocale()), Integer.parseInt(messageSource
						.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE,
								null, nsRequest.getLocale())));
		nsResponse.setNsFrequencyList(nsFrequencyList);
		return nsResponse;

	}

	@Override
	public NsResponse getMedicationMethodDesc() throws BusinessException,
			SystemException {

		List<MedicationMethod> medicationMethodList = medicationMgntDAO
				.getMedicationMethodList();

		List<NsMedicationMethodList> nsMedicationMethodList = new ArrayList<NsMedicationMethodList>();
		for (MedicationMethod medicationMethod : medicationMethodList) {
			NsMedicationMethodList nsMedicationMethod = new NsMedicationMethodList();
			nsMedicationMethod.setMedicationMethodId(medicationMethod
					.getMedicationMethodId());
			nsMedicationMethod.setMedicationMethoDesc(medicationMethod
					.getMedicationMethod());
			nsMedicationMethodList.add(nsMedicationMethod);
		}
		NsResponse nsResponse = NsResponseUtils.getNsResponse(null,
				messageSource.getMessage("rsp.data.dscrptn.success", null,
						Locale.getDefault()), Integer.parseInt(messageSource
						.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE,
								null, Locale.getDefault())));
		nsResponse.setNsMedicationMethodList(nsMedicationMethodList);
		return nsResponse;
	}

	@Override
	public NsResponse getMedicationKindDesc() throws BusinessException,
			SystemException {

		List<MedicationKind> medicationKindList = medicationMgntDAO
				.getMedicationKindList();

		List<NsMedicationKindList> nsMedicationKindList = new ArrayList<NsMedicationKindList>();
		for (MedicationKind medicationKind : medicationKindList) {
			NsMedicationKindList nsMedicationKind = new NsMedicationKindList();
			nsMedicationKind.setMedicationKindId(medicationKind
					.getMedicationKindId());
			nsMedicationKind.setMedicationKindDesc(medicationKind
					.getMedicationKind());
			nsMedicationKind.setMedicationDose(medicationKind.getMedicationDose());
			nsMedicationKindList.add(nsMedicationKind);
		}
		NsResponse nsResponse = NsResponseUtils.getNsResponse(null,
				messageSource.getMessage("rsp.data.dscrptn.success", null,
						Locale.getDefault()), Integer.parseInt(messageSource
						.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE,
								null, Locale.getDefault())));
		nsResponse.setNsMedicationKindList(nsMedicationKindList);
		return nsResponse;
	}

	@Override
	public NsResponse getMedicationDosageDesc() throws BusinessException,
			SystemException {

		List<MedicationDosage> medicationKindList = medicationMgntDAO
				.getMedicationDosageList();

		List<NsMedicationDosageList> nsMedicationDosageList = new ArrayList<NsMedicationDosageList>();
		for (MedicationDosage medicationDosage : medicationKindList) {
			NsMedicationDosageList nsMedicationDosage = new NsMedicationDosageList();
			nsMedicationDosage.setMedicationDosageId(medicationDosage
					.getMedicationDosageId());
			nsMedicationDosage.setMedicationDosageDesc(medicationDosage
					.getMedicationDosage());
			nsMedicationDosageList.add(nsMedicationDosage);
		}
		NsResponse nsResponse = NsResponseUtils.getNsResponse(null,
				messageSource.getMessage("rsp.data.dscrptn.success", null,
						Locale.getDefault()), Integer.parseInt(messageSource
						.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE,
								null, Locale.getDefault())));
		nsResponse.setNsMedicationDosageList(nsMedicationDosageList);
		return nsResponse;
	}

	@Override
	public NsResponse getMedicationImage(NsRequest nsRequest)
			throws BusinessException, SystemException {
		try {
			AccountMedicationManagement accountMedicationManagement = medicationMgntDAO
					.getMedicationByAccountMedicationManagementId(nsRequest);
			MedicationManagmntInfo medMgmtInfo = new MedicationManagmntInfo();
			medMgmtInfo.setMedicationImage(accountMedicationManagement
					.getMedicationImage());
			NsResponse nsResponse = new NsResponse();
			ResponseData responseData = new ResponseData();
			responseData.setResult(0);
			responseData.setDescription("Success");
			nsResponse.setResponseData(responseData);
			nsResponse.setMedicationManagmntInfo(medMgmtInfo);
			return nsResponse;
		} catch (Exception e) {
			throw new BusinessException("no.acc.details.found");
		}

	}

	@Override
	public NsResponse restoreMedicatnList(NsRequest nsRequest)
			throws BusinessException, SystemException {
		List<AccountMedicationManagementReminder> accountMedicationManagementReminders = medicationMgntDAO
				.restoreReminderByActIdDeleteFlag(nsRequest.getAccountInfo()
						.getAccountId(), new Date());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();

		List<MedicationManagementReminderInfo> medicationManagementReminderList = new ArrayList<MedicationManagementReminderInfo>();
		for (AccountMedicationManagementReminder accountMedicationManagementReminder : accountMedicationManagementReminders) {
			FrequencyDesc frequencyDesc = new FrequencyDesc();
			List<Frequency> frequencyList = medicationMgntDAO
					.getFrequencyListByLangCode(nsRequest.getLocale()
							.toString());
			if (accountMedicationManagementReminder.getFrequencyType() != null) {
				for (Frequency frequency : frequencyList) {
					if (accountMedicationManagementReminder.getFrequencyType() == frequency
							.getType()) {
						frequencyDesc.setFrequencyId(frequency.getType());
						frequencyDesc
								.setFrequencyDesc(frequency.getFrequency());
						/*if (frequency.getDatePartId() != null) {
							frequencyDesc.setDatePartId(frequency
									.getDatePartId().getDatePartId());
						}*/
					}
				}
			}
			NsMedicationDosageList medicationDosage = new NsMedicationDosageList();
			/*
			 * if (accountMedicationManagementReminder.getMedicationDosageId()
			 * != null) { medicationDosage.setMedicationDosageId(
			 * accountMedicationManagementReminder.getMedicationDosageId()
			 * .getMedicationDosageId());
			 * medicationDosage.setMedicationDosageDesc
			 * (accountMedicationManagementReminder.getMedicationDosageId()
			 * .getMedicationDosage()); }
			 */
			MedicationManagementReminderInfo medicationManagementReminder = new MedicationManagementReminderInfo();
			medicationManagementReminder
					.setMedictnReminderId(accountMedicationManagementReminder
							.getAccountMedicationManagementReminderId());
			medicationManagementReminder
					.setAccountId(accountMedicationManagementReminder
							.getAccountId().getAccountId());
			medicationManagementReminder
					.setReminderFor(accountMedicationManagementReminder
							.getReminderFor());
			medicationManagementReminder
					.setActive(accountMedicationManagementReminder
							.getIsActive());
			medicationManagementReminder
					.setReminderBeginDate((new SimpleDateFormat("dd-MM-yyyy")
							.format(accountMedicationManagementReminder
									.getReminderBeginDate())).toString()); // Don't
			// change the Date pattern(dependency on IOS)
			medicationManagementReminder
					.setReminderEndDate((new SimpleDateFormat("dd-MM-yyyy")
							.format(accountMedicationManagementReminder
									.getReminderEndDate())).toString());
			// Don't change the Date pattern(dependency on IOS)
			medicationManagementReminder
					.setDosage(accountMedicationManagementReminder.getDosage());
			medicationManagementReminder.setFrequencyDesc(frequencyDesc);
			medicationManagementReminder.setMedicationDosage(medicationDosage);

			List<AccountReminderMedicationRelation> accountReminderMedicationRelationListFromDB = accountMedicationManagementReminder
					.getAccountReminderMedicationRelationList();
			List<MedicationManagmntInfo> medicationInfoList = new ArrayList<MedicationManagmntInfo>();

			for (AccountReminderMedicationRelation accountReminderMedicationRelation : accountReminderMedicationRelationListFromDB) {
				MedicationManagmntInfo medicationInfo = new MedicationManagmntInfo();
				if (accountReminderMedicationRelation
						.getAccountMedicationManagementId() != null) {
					medicationInfo
							.setMedictnMgmtId(accountReminderMedicationRelation
									.getAccountMedicationManagementId()
									.getAccountMedicationManagementId());
					medicationInfo
							.setMedicationName(accountReminderMedicationRelation
									.getAccountMedicationManagementId()
									.getMedicationName());
					/*
					 * medicationInfo.setRxNumber(accountReminderMedicationRelation
					 * .getAccountMedicationManagementId() .getRxNumber());
					 */
					medicationInfo.setNotes(accountReminderMedicationRelation
							.getAccountMedicationManagementId().getNotes());
					medicationInfo
							.setMedicationKindDesc(accountReminderMedicationRelation
									.getAccountMedicationManagementId()
									.getMedicationKindId().getMedicationKind());
					/*
					 * medicationInfo.setMedicationMethodDesc(
					 * accountReminderMedicationRelation
					 * .getAccountMedicationManagementId
					 * ().getMedicationMethodId().getMedicationMethod());
					 */
					medicationInfoList.add(medicationInfo);
				}
			}
			medicationManagementReminder
					.setMedicationManagmntInfoList(medicationInfoList);
			List<AccountMedicationManagementSchedule> accountMedicationManagementSchedulesListFromDB = accountMedicationManagementReminder
					.getAccountMedicationManagementScheduleList();

			List<NsScheduleTime> nsMedicationScheduleList = new ArrayList<NsScheduleTime>();

			for (AccountMedicationManagementSchedule accountMedicationManagementSchedule : accountMedicationManagementSchedulesListFromDB) {
				NsScheduleTime medicationScheduleTime = new NsScheduleTime();
				medicationScheduleTime
						.setScheduleId(accountMedicationManagementSchedule
								.getAccountMedicationManagementScheduleId());
				medicationScheduleTime.setReminderTime((new SimpleDateFormat(
						"hh:mm a").format(accountMedicationManagementSchedule
						.getReminderTime())).toString());
				// Don't change the Time pattern(dependency on IOS)
				nsMedicationScheduleList.add(medicationScheduleTime);
			}
			medicationManagementReminder
					.setNsScheduleTimeList(nsMedicationScheduleList);
			medicationManagementReminderList.add(medicationManagementReminder);
		}

		if (medicationManagementReminderList.size() == 0
				|| medicationManagementReminderList == null) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("No Reminders Present");
			nsResponse.setResponseData(responsedata);
			// nsResponse.setMedicationRemindersCount(0);
		} else if (medicationManagementReminderList.size() > 0) {
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("success");
			nsResponse.setResponseData(responsedata);
			nsResponse
					.setMedicationManagementReminderList(medicationManagementReminderList);
			// nsResponse.setMedicationRemindersCount(medicationManagementReminderList.size());
		}
		return nsResponse;

	}

	@Override
	public NsResponse saveMedicationManagement(NsRequest nsRequest)
			throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		NsResponse accountMedicationManagementInfo = null;
		NsResponse saveMedicatnReminderInfo = null;
		MedicationManagmntInfo medicationManagmntInfo = null;
		MedicationManagementReminderInfo medicationManagementReminderInfo = null;
		Boolean reminder = nsRequest.getMedicationManagmntInfo().getIsReminder();
		// accountMedicationManagementInfo.getMedicationManagmntInfo().getMedictnMgmtId()
		if (reminder == false) {
			accountMedicationManagementInfo = saveMedicatnInfo(nsRequest);
			medicationManagmntInfo = new MedicationManagmntInfo();
			medicationManagmntInfo
					.setMedictnMgmtId(accountMedicationManagementInfo
							.getMedicationManagmntInfo().getMedictnMgmtId());
			nsResponse.setMedicationManagmntInfo(medicationManagmntInfo);
			nsResponse.setResponseData(responsedata);
			responsedata.setDescription("success");
			return nsResponse;

		} else if (reminder!= false) {
			accountMedicationManagementInfo = saveMedicatnInfo(nsRequest);
			if(DateUtils.getDate(nsRequest.getMedicationManagmntInfo().getRefillDate(),"MM/dd/yyyy")!=null){
			ClientNaavisDatabases clientNaavisDatabases=clientNaavisDatabaseServiceDAO.getClientNaavisDatabases(Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,Locale.getDefault())));
			//String facilityName=clientNaavisDatabases.getFacilityName();
			Integer clientDatabaseId=Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,	Locale.getDefault()));
			Integer clientId=Integer.parseInt(messageSource.getMessage(CLIENT_ID, null,	Locale.getDefault()));			
			
			// Populating the feed_info table 
			FeedInfo feedInfo=new FeedInfo();
			feedInfo.setAccountId(nsRequest.getAccountInfo().getAccountId());
			feedInfo.setClientDatabaseId(clientDatabaseId);
			feedInfo.setClientId(clientId);
			feedInfo.setFeatureId(Integer.parseInt(messageSource.getMessage(FEATURE_ID, null,Locale.getDefault())));
			feedInfo.setDateAdded(new Date());
			feedInfo.setClinicalDateTime(DateUtils.getDate(nsRequest.getMedicationManagmntInfo().getRefillDate(),DateUtils.DATE_ADDED_FORMAT));
			feedInfo.setDisplayTime(false);
			feedInfo.setFeatureName(messageSource.getMessage(FEATURE_NAME, null, nsRequest.getLocale()));
			feedInfo.setFeedHeader(messageSource.getMessage(FEED_HEADER, null, nsRequest.getLocale()));
			feedInfo.setFeedMsg(nsRequest.getMedicationManagmntInfo().getMedicationName());
			feedInfo.setIsNew(true);
			feedInfo.setSourceName(clientNaavisDatabases.getFacilityName());
			
			medicationMgntDAO.persist(feedInfo);
			}
			saveMedicatnReminderInfo = saveMedicatnReminder(nsRequest,
					accountMedicationManagementInfo);

			medicationManagmntInfo = new MedicationManagmntInfo();
			medicationManagmntInfo
					.setMedictnMgmtId(accountMedicationManagementInfo
							.getMedicationManagmntInfo().getMedictnMgmtId());
			medicationManagementReminderInfo = new MedicationManagementReminderInfo();
			medicationManagementReminderInfo
					.setMedictnReminderId(saveMedicatnReminderInfo
							.getReminderId());

		}

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("success");
		nsResponse.setMedicationManagmntInfo(medicationManagmntInfo);
		nsResponse
				.setMedicationManagementReminder(medicationManagementReminderInfo);
		responsedata.setResult(0);
		nsResponse.setResponseData(responsedata);

		return nsResponse;
	}

	@Override
	public NsResponse saveMedicatnReminder(NsRequest nsRequest)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NsResponse getMedicationManagement(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		/*
		 * List<AccountMedicationManagement> accountMedicationManagementList =
		 * medicationMgntDAO
		 * .getMedicationByActId(nsRequest.getAccountInfo().getAccountId());
		 */

		MedicationManagmntInfo medicationManagmntInfo = new MedicationManagmntInfo();
		AccountReminderMedicationRelation accountReminderMedicationRelation = medicationMgntDAO
				.getMedicationRelation(nsRequest.getMedicationManagmntInfo()
						.getMedictnMgmtId());
		if (accountReminderMedicationRelation == null) {
			AccountMedicationManagement accountMedicationManagement = medicationMgntDAO
					.getMedicationByAccountMedicationManagementId(nsRequest);
			medicationManagmntInfo.setDosageId(accountMedicationManagement
					.getDosage());
			medicationManagmntInfo
					.setMedicationKindId(accountMedicationManagement
							.getMedicationKindId().getMedicationKindId());
			medicationManagmntInfo.setMedicationKind(accountMedicationManagement.getMedicationKindId().getMedicationKind());
			medicationManagmntInfo
					.setMedicationName(accountMedicationManagement
							.getMedicationName());
			medicationManagmntInfo.setRefillDate(DateUtils.getFormatDate(
					accountMedicationManagement.getRefillDate(), "MM/dd/yyyy"));
			medicationManagmntInfo.setIsReminder(accountMedicationManagement
					.getIsReminder());
			medicationManagmntInfo.setNotes(accountMedicationManagement
					.getNotes());
			medicationManagmntInfo.setMedicationDose(accountMedicationManagement.getMedicationKindId().getMedicationDose());
			
			nsResponse.setMedicationManagmntInfo(medicationManagmntInfo);
			nsResponse.setResponseData(responsedata);
			responsedata.setDescription("success");
			return nsResponse;
		}
		/*
		 * AccountMedicationManagementReminder
		 * accountMedicationManagementReminders = medicationMgntDAO
		 * .getReminderByReminderId(accountReminderMedicationRelation.
		 * getAccountMedicationManagementReminderId
		 * ().getAccountMedicationManagementReminderId());
		 */
		List<AccountMedicationManagementSchedule> accountMedicationManagementSchedule = accountReminderMedicationRelation
				.getAccountMedicationManagementReminderId()
				.getAccountMedicationManagementScheduleList();

		/*
		 * List<AccountMedicationManagementSchedule>
		 * accountMedicationManagementSchedule=medicationMgntDAO.
		 * getMedicationSchedule(accountMedicationManagementReminders.
		 * getAccountMedicationManagementReminderId());
		 */
		MedicationManagementReminderInfo medicationManagementReminderInfo = new MedicationManagementReminderInfo();
		List<NsScheduleTime> nsScheduleTimeList = new ArrayList<NsScheduleTime>();
		for (AccountMedicationManagementSchedule accountMedicationManagement : accountMedicationManagementSchedule) {
			NsScheduleTime nsScheduleTime = new NsScheduleTime();
			nsScheduleTime.setReminderDay(accountMedicationManagement
					.getReminderDay());
			nsScheduleTime.setReminderTime(DateUtils.getFormatDate(
					accountMedicationManagement.getReminderTime(), "hh:mm a"));
			nsScheduleTimeList.add(nsScheduleTime);

		}
		medicationManagementReminderInfo
				.setDosage(accountReminderMedicationRelation
						.getAccountMedicationManagementReminderId().getDosage());
		medicationManagementReminderInfo
				.setActive(accountReminderMedicationRelation
						.getAccountMedicationManagementReminderId()
						.getIsActive());
		medicationManagementReminderInfo
				.setFrequencyValue(accountReminderMedicationRelation
						.getAccountMedicationManagementReminderId()
						.getFrequencyValue());
		medicationManagementReminderInfo
				.setFrequencyId(accountReminderMedicationRelation
						.getAccountMedicationManagementReminderId()
						.getFrequencyType());
		medicationManagementReminderInfo
				.setMedictnReminderId(accountReminderMedicationRelation
						.getAccountMedicationManagementReminderId()
						.getAccountMedicationManagementReminderId());

		medicationManagmntInfo.setDosageId(accountReminderMedicationRelation
				.getAccountMedicationManagementId().getDosage());
		medicationManagmntInfo
				.setMedicationKindId(accountReminderMedicationRelation
						.getAccountMedicationManagementId()
						.getMedicationKindId().getMedicationKindId());
		medicationManagmntInfo.setMedicationKind(accountReminderMedicationRelation
				.getAccountMedicationManagementId()
				.getMedicationKindId().getMedicationKind());
		medicationManagmntInfo
				.setMedicationName(accountReminderMedicationRelation
						.getAccountMedicationManagementId().getMedicationName());
		medicationManagmntInfo.setRefillDate(DateUtils.getFormatDate(
				accountReminderMedicationRelation
						.getAccountMedicationManagementId().getRefillDate(),
				"MM/dd/yyyy"));
		medicationManagmntInfo.setIsReminder(accountReminderMedicationRelation
				.getAccountMedicationManagementId().getIsReminder());
		medicationManagmntInfo.setNotes(accountReminderMedicationRelation
				.getAccountMedicationManagementId().getNotes());
		medicationManagmntInfo
				.setMedictnMgmtId(accountReminderMedicationRelation
						.getAccountMedicationManagementId()
						.getAccountMedicationManagementId());
		medicationManagmntInfo.setMedicationDose(accountReminderMedicationRelation.getAccountMedicationManagementId().getMedicationKindId().getMedicationDose());

		medicationManagementReminderInfo
				.setNsScheduleTimeList(nsScheduleTimeList);
		nsResponse
				.setMedicationManagementReminder(medicationManagementReminderInfo);
		nsResponse.setMedicationManagmntInfo(medicationManagmntInfo);
		responsedata.setDescription("success");
		responsedata.setResult(0);
		nsResponse.setResponseData(responsedata);
		// nsResponse.setMedicationManagmntInfo(medicationManagmntInfo);
		return nsResponse;

	}

	@Override
	public NsResponse editMedicationManagement(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		NsResponse accountMedicationManagementInfo = null;
		NsResponse editMedicatnReminderInfo = null;
		MedicationManagmntInfo medicationManagmntInfo = null;
		MedicationManagementReminderInfo medicationManagementReminderInfo = null;
		// AccountMedicationManagement
		// reminder=medicationMgntDAO.getMedicationByAccountMedicationManagementId(nsRequest);
		Boolean reminder = nsRequest.getMedicationManagmntInfo()
				.getIsReminder();
		if (reminder == false) {
			accountMedicationManagementInfo = editMedicatnInfo(nsRequest);
			medicationManagmntInfo = new MedicationManagmntInfo();
			medicationManagmntInfo
					.setMedictnMgmtId(accountMedicationManagementInfo
							.getMedicationManagmntInfo().getMedictnMgmtId());
			nsResponse.setMedicationManagmntInfo(medicationManagmntInfo);
			nsResponse.setResponseData(responsedata);
			responsedata.setDescription("success");
			return nsResponse;
		} else if (reminder != false) {
			//Insert Data into Medication Mgmt
			AccountMedicationManagement accountMedicationManagement=medicationMgntDAO.getDetailsByActIdMedId(nsRequest.getMedicationManagmntInfo().getMedictnMgmtId(),nsRequest.getAccountInfo().getAccountId());
			
			String medicationName=accountMedicationManagement.getMedicationName();
			accountMedicationManagementInfo = editMedicatnInfo(nsRequest);
			if(DateUtils.getDate(nsRequest.getMedicationManagmntInfo().getRefillDate(),"MM/dd/yyyy")!=null){
				ClientNaavisDatabases clientNaavisDatabases=clientNaavisDatabaseServiceDAO.getClientNaavisDatabases(Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,Locale.getDefault())));
				//String facilityName=clientNaavisDatabases.getFacilityName();
				Integer clientDatabaseId=Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,	Locale.getDefault()));
				Integer clientId=Integer.parseInt(messageSource.getMessage(CLIENT_ID, null,	Locale.getDefault()));
				
				// Editing the feed_info record
				FeedInfo feedInfoData = userRegistrationDAO.getFeedInfo(nsRequest.getAccountInfo().getAccountId(), clientId, clientDatabaseId, medicationName!=null ? medicationName:accountMedicationManagement.getMedicationName());
				
				FeedInfo feedInfo=new FeedInfo();
				feedInfo.setAccountId(nsRequest.getAccountInfo().getAccountId());
				feedInfo.setClientDatabaseId(clientDatabaseId);
				feedInfo.setClientId(clientId);
				feedInfo.setFeatureId(Integer.parseInt(messageSource.getMessage(FEATURE_ID, null,Locale.getDefault())));
				
				if (feedInfoData != null) {
					feedInfo.setFeedMessageId(feedInfoData.getFeedMessageId());
				}
				
				feedInfo.setDateAdded(new Date());
				feedInfo.setClinicalDateTime(DateUtils.getDate(nsRequest.getMedicationManagmntInfo().getRefillDate(),DateUtils.DATE_ADDED_FORMAT));
				feedInfo.setDisplayTime(false);
				//feedInfo.setFeatureId(Integer.parseInt(messageSource.getMessage(FEATURE_ID, null,Locale.getDefault())));
				feedInfo.setFeatureName(messageSource.getMessage(FEATURE_NAME, null, nsRequest.getLocale()));
				feedInfo.setFeedHeader(messageSource.getMessage(FEED_HEADER, null, nsRequest.getLocale()));
				feedInfo.setFeedMsg(nsRequest.getMedicationManagmntInfo().getMedicationName());
				feedInfo.setIsNew(true);
				feedInfo.setSourceName(clientNaavisDatabases.getFacilityName());
				medicationMgntDAO.update(feedInfo);
			}
			AccountReminderMedicationRelation accountReminderMedicationRelation = medicationMgntDAO
					.getMedicationRelation(nsRequest.getMedicationManagmntInfo()
							.getMedictnMgmtId());
			
			
			if(accountReminderMedicationRelation==null)
			{
			editMedicatnReminderInfo= saveMedicatnReminder(nsRequest,
						accountMedicationManagementInfo);	
			}
			else
			{
			editMedicatnReminderInfo = editMedicatnReminder(nsRequest,
					accountMedicationManagementInfo);
			}
			medicationManagmntInfo = new MedicationManagmntInfo();
			medicationManagmntInfo
					.setMedictnMgmtId(accountMedicationManagementInfo
							.getMedicationManagmntInfo().getMedictnMgmtId());
			medicationManagementReminderInfo = new MedicationManagementReminderInfo();
			medicationManagementReminderInfo
					.setMedictnReminderId(editMedicatnReminderInfo
							.getReminderId());
		}
		 
		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("success");
		nsResponse.setMedicationManagmntInfo(medicationManagmntInfo);
		nsResponse.setMedicationManagementReminder(medicationManagementReminderInfo);
		responsedata.setResult(0);
		nsResponse.setResponseData(responsedata);

		return nsResponse;
	}

	public NsResponse editMedicatnInfo(NsRequest nsRequest)
			throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		//AccountMedicationManagement accountMedicationManagement = new AccountMedicationManagement();
		AccountMedicationManagement accountMedicationManagement =medicationMgntDAO.getAccountMedicationId(nsRequest);
		MedicationManagmntInfo managmntInfo = new MedicationManagmntInfo();
		
		accountMedicationManagement.setAccountId(new Account(nsRequest.getAccountInfo().getAccountId()));
		if (nsRequest.getMedicationManagmntInfo().getMedicationImage() != null) {
			accountMedicationManagement.setMedicationImage(nsRequest.getMedicationManagmntInfo().getMedicationImage());
		}
		accountMedicationManagement.setMedicationName(nsRequest.getMedicationManagmntInfo().getMedicationName());

		if (nsRequest.getMedicationManagmntInfo().getMedicationKindId() == 0) {
			throw new BusinessException("select.kind.id");
		}
		accountMedicationManagement.setMedicationKindId(new MedicationKind(nsRequest.getMedicationManagmntInfo().getMedicationKindId()));
		accountMedicationManagement.setNotes(nsRequest.getMedicationManagmntInfo().getNotes());
		accountMedicationManagement.setDateAdded(new Date());
		// Added for engage 2.0
		accountMedicationManagement.setDosage(nsRequest.getMedicationManagmntInfo().getDosageId());
		if (nsRequest.getMedicationManagmntInfo().getIsReminder() != false) {
			accountMedicationManagement.setRefillDate(DateUtils.getDate(nsRequest.getMedicationManagmntInfo().getRefillDate(),"MM/dd/yyyy"));
		} else {
			accountMedicationManagement.setRefillDate(null);
		}
		accountMedicationManagement.setIsReminder(nsRequest.getMedicationManagmntInfo().getIsReminder());
		accountMedicationManagement.setAccountMedicationManagementId(accountMedicationManagement.getAccountMedicationManagementId());
		medicationMgntDAO.update(accountMedicationManagement);
		
		managmntInfo.setMedictnMgmtId(accountMedicationManagement.getAccountMedicationManagementId());
		//System.out.println(accountMedicationManagement.getAccountMedicationManagementId());
		managmntInfo.setAccountId(accountMedicationManagement.getAccountId().getAccountId());

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription(messageSource.getMessage(
				VersaWorkConstant.MEDICATION_ADDED_SUCCESSFULLY, null,
				nsRequest.getLocale()));
		nsResponse.setResponseData(responsedata);
		nsResponse.setMedicationManagmntInfo(managmntInfo);

		return nsResponse;

	}

	public NsResponse editMedicatnReminder(NsRequest nsRequest,
			NsResponse accountMedicationManagementInfo)
			throws BusinessException, SystemException {

		Account account = medicationMgntDAO.getAccountById(nsRequest
				.getAccountInfo().getAccountId());

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		AccountReminderMedicationRelation accountReminderMedicationRelation = medicationMgntDAO
				.getMedicationRelation(nsRequest.getMedicationManagmntInfo()
						.getMedictnMgmtId());
		accountReminderMedicationRelation
				.getAccountMedicationManagementReminderId();

		AccountMedicationManagementReminder accountMedicationManagementReminder = new AccountMedicationManagementReminder();

		FrequencyDesc frequencyDesc = nsRequest
				.getMedicationManagementReminder().getFrequencyDesc();

		accountMedicationManagementReminder.setAccountId(new Account(account
				.getAccountId()));
		accountMedicationManagementReminder.setReminderFor(frequencyDesc
				.getReminderFor());
		accountMedicationManagementReminder.setReminderBeginDate(new Date());
		accountMedicationManagementReminder.setReminderEndDate(new Date());

		accountMedicationManagementReminder
				.setAccountMedicationManagementReminderId(accountReminderMedicationRelation
						.getAccountMedicationManagementReminderId()
						.getAccountMedicationManagementReminderId());
		// System.out.println(accountReminderMedicationRelation.getAccountMedicationManagementReminderId());
		accountMedicationManagementReminder.setFrequencyType(frequencyDesc
				.getFrequencyId());
		accountMedicationManagementReminder.setFrequencyValue(frequencyDesc
				.getFrequencyValue());
		accountMedicationManagementReminder
				.setDosage(frequencyDesc.getDosage());
		accountMedicationManagementReminder.setDateAdded(new Date());
		accountMedicationManagementReminder.setDeleteFlag("false");
		accountMedicationManagementReminder.setIsActive(true);
		medicationMgntDAO.update(accountMedicationManagementReminder);
		
		List<AccountMedicationManagementSchedule> accountMedicationManagementScheduleInfoList = medicationMgntDAO
				.getMedicationManagementScheduleList(accountMedicationManagementReminder
						.getAccountMedicationManagementReminderId());

		for (AccountMedicationManagementSchedule accountMedicationManagementScheduleListInfo : accountMedicationManagementScheduleInfoList) {
			medicationMgntDAO
					.remove(accountMedicationManagementScheduleListInfo);
		}
		accountMedicationManagementScheduleInfoList.clear();

		List<NsScheduleTime> accountMedicationManagementScheduleList = nsRequest
				.getMedicationManagementReminder().getNsScheduleTimeList();
		for (NsScheduleTime nsScheduleTime : accountMedicationManagementScheduleList) {
			AccountMedicationManagementSchedule accountMedicationManagementSchedule = new AccountMedicationManagementSchedule();

			accountMedicationManagementSchedule
					.setAccountMedicationManagementReminderId(accountMedicationManagementReminder);

			accountMedicationManagementSchedule
					.setAccountMedicationManagementId(new AccountMedicationManagement(
							accountMedicationManagementInfo
									.getMedicationManagmntInfo()
									.getMedictnMgmtId()));
			accountMedicationManagementSchedule.setReminderTime(DateUtils
					.get24FormatDate(nsScheduleTime.getReminderTime()));
			accountMedicationManagementSchedule.setDateAdded(new Date());
			accountMedicationManagementSchedule.setReminderDay(nsScheduleTime
					.getReminderDay());
			accountMedicationManagementSchedule
					.setAccountMedicationManagementScheduleId(nsScheduleTime
							.getScheduleId());
			

			// AccountMedicationManagementSchedule
			// accountMedicationManagementScheduleListInfo =
			// accountReminderMedicationRelation.getAccountMedicationManagementReminderId().getAccountMedicationManagementScheduleList().get(0);

			// accountMedicationManagementSchedule.setAccountMedicationManagementScheduleId(accountMedicationManagementScheduleListInfo.getAccountMedicationManagementScheduleId());
			medicationMgntDAO.persist(accountMedicationManagementSchedule);

		}

		// List<MedicationManagementReminderInfo>
		// medicationManagementReminderList = new
		// ArrayList<MedicationManagementReminderInfo>();
		MedicationManagementReminderInfo medicationManagementReminder = new MedicationManagementReminderInfo();
		medicationManagementReminder
				.setMedictnReminderId(accountMedicationManagementReminder
						.getAccountMedicationManagementReminderId());
		// medicationManagementReminderList.add(medicationManagementReminder);
		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("success");
		// nsResponse.setMedicationManagementReminderList(medicationManagementReminderList);
		nsResponse.setReminderId(medicationManagementReminder
				.getMedictnReminderId());
		nsResponse.setResponseData(responsedata);
		return nsResponse;

	}

	@Override
	public NsResponse checkRefillDate(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		String resendMessage = null;
		List<AccountMedicationManagement> refillDateList = medicationMgntDAO
				.getMedicationRefillDateList(nsRequest.getAccountInfo()
						.getAccountId());

		List<MedicationManagmntInfo> medInfoList = new ArrayList<MedicationManagmntInfo>();
		for (AccountMedicationManagement accountMedicationManagementList : refillDateList) {
			MedicationManagmntInfo medicationManagmntInfo = new MedicationManagmntInfo();
			medicationManagmntInfo.setRefillDate(DateUtils.getFormatDate(
					accountMedicationManagementList.getRefillDate(),
					"MM/dd/yyyy"));
			medicationManagmntInfo
					.setMedicationName(accountMedicationManagementList
							.getMedicationName());
			medInfoList.add(medicationManagmntInfo);
		}

		Date todaysDate = new Date();
		String convertedDate = new SimpleDateFormat("MM/dd/yyyy")
				.format(todaysDate);
		resendMessage = messageSource.getMessage(NO_REFILL_DATE_FOUND, null,
				nsRequest.getLocale());
		boolean isMedicineAvailable = false;

		for (MedicationManagmntInfo medicationManagmntInfo : medInfoList) {

			String refillDate = medicationManagmntInfo.getRefillDate();

			if (convertedDate.equalsIgnoreCase(refillDate)) {
				String medicationName = medicationManagmntInfo
						.getMedicationName();
				resendMessage += " " + medicationName + ", ";
				isMedicineAvailable = true;

			}

		}
		if (isMedicineAvailable) {
			resendMessage = resendMessage.trim();
			resendMessage = resendMessage.substring(0, resendMessage.lastIndexOf(","));
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			nsResponse.setResendMessageDescription(resendMessage);
			nsResponse.setResponseData(responsedata);
			responsedata.setDescription("success");
			return nsResponse;
		}
		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.FAILURE_RESPONSE_CODE, null,
				nsRequest.getLocale())));		
		nsResponse.setResendMessageDescription("");
		nsResponse.setResponseData(responsedata);
		responsedata.setDescription("no reminder found");

		return nsResponse;

	}

}