package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.versawork.http.model.PatientSleep;
import com.versawork.http.utils.ByteArraySerializer;

/**
 * @author Dheeraj
 *
 */

/**
 * @author User20
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsResponse implements Serializable {
	private final static long serialVersionUID = 1L;

	protected ResponseData responseData;

	protected List<MedicationSourceDates> medicationSourceAsOfDate;

	protected AccountInfo accountInfo;

	protected List<NsSecurityQuestion> securityQuestions;

	protected List<NsPatientAppointment> nsPatientAppointments;

	protected List<NsPatientVisit> patientVisitList;

	protected List<NsDoctorsList> nsDoctorsList;

	protected List<NsServicesList> nsServicesList;

	protected List<NsPatientLabResult> patientLabResults;

	protected List<NsPatientPrescription> patientPrescriptions;

	protected List<NsHospitalNotice> hospitalNotices;

	protected List<NsFeedBackList> nsFeedBackList;

	protected List<BloodPressureInfo> bloodPressureList;

	protected List<NsFrequencyList> nsFrequencyList;

	protected List<NsMedicationMethodList> nsMedicationMethodList;

	protected List<NsMedicationKindList> nsMedicationKindList;

	protected List<NsMedicationDosageList> nsMedicationDosageList;

	protected List<MedicationManagmntInfo> medicationManagmntInfoList;

	protected MedicationManagmntInfo medicationManagmntInfo;

	protected List<MedicationManagementReminderInfo> medicationManagementReminderList;

	protected List<BloodPressureReminderInfo> bloodPressureReminderList;

	protected Integer notificationWarningCount;

	protected NotificationsCount notificationsCount;

	protected Integer remindersCount;

	protected Integer medicationRemindersCount;

	protected Integer bloodPressureRemindersCount;

	protected String feedBack;

	protected CacheInputs cacheInputs;

	protected ViewPatientInfo viewPatientInfo;

	protected List<AllergiesInfo> allergiesInfoList;

	protected List<MedicationInfo> medicationInfoList;

	protected List<PatientProblemInfo> patientProblemInfoList;

	protected List<ProcedureInfo> procedureInfoList;

	protected List<LabResultInfo> labResultInfoList;

	protected List<CarePlanInfo> carePlanInfoList;

	protected List<PatientInpatientDiagnosisInfo> patientInpatientDiagnosisInfoList;

	protected List<PatientInpatientFunctionalStatusInfo> patientInpatientFunctionalStatusInfoList;

	protected List<PatientInpatientImmunalizationInfo> patientInpatientImmunalizationInfoList;

	protected PatientInpatientMetadataInfo patientInpatientMetadataInfo;

	protected Integer visitType;

	protected GeoLocationResponseInfo geoLocationResponseInfo;
	private List<Integer> bloodPressureIdsList;
	private List<Integer> medicationIdsList;

	private List<BillSummaryData> billSummaryDataList;

	private BillSummaryData billSummaryData;

	protected String appStoreVersion;

	private String securityCode;

	private Set<SourceInfo> sourceList;

	private Map<String, List<PatientImagingInfo>> patientImagingObjectMap;
	private List<Facility> registeredFacilities;
	private List<Facility> searchedFacilities;
	private Facility facility;
    
	//for feed
	protected List<FeedListInfo> feedInformationList;
	protected FeedListInfo feedInformation;
	protected Boolean noMoreFeed;
	protected List<NsPatientAppointment> upcomingPatientAppointment;
	protected List<NsPatientAppointment> pastPatientAppointment;
	protected Boolean noMorePatientAppointment;
	protected VisitDetails visitDetails;
	protected PatientInformation patientInformation;
	protected List<TestResultInfo> testResultInfo;
	
	protected Integer reminderId;
	protected List<NsScheduleTime> scheduledInfoList;
	
	protected BloodPressureInfo bloodPressureInfo;
	
	protected AccountWeightInfo accountWeightInfo;
	
	protected List<AccountWeightInfo> weightList;
	
	
	protected List<PatientOverallStressInfo> overallStressList;
	protected List<PatientActiveTimeInfo> patientActiveTimeList;
	protected List<PatientStepCountInfo> patientStepCountList;
	protected List<PatientCalorieCountInfo> patientCalorieCountList;
	protected List<PatientHeartAgeInfo>  patientHeartAgeList;
	protected List<PatientHeartRateInfo> patientHeartRateList;
	protected List<PatientSleepInfo> patientSleepList;
	protected List<PatientDistanceInfo>  patientDistanceList;
	
	
	
	protected PatientOverallStressInfo patientOverallStressInfo;
	protected PatientDistanceInfo  patientDistanceInfo;
	protected PatientActiveTimeInfo patientActiveTimeInfo;
	protected PatientStepCountInfo patientStepCountInfo;
	protected PatientCalorieCountInfo patientCalorieCountInfo;
	protected PatientHeartAgeInfo  patientHeartAgeInfo;
	protected PatientHeartRateInfo patientHeartRateInfo;
	protected PatientSleepInfo patientSleepInfo;
	
	

	public PatientDistanceInfo getPatientDistanceInfo() {
		return patientDistanceInfo;
	}

	public void setPatientDistanceInfo(PatientDistanceInfo patientDistanceInfo) {
		this.patientDistanceInfo = patientDistanceInfo;
	}

	public PatientActiveTimeInfo getPatientActiveTimeInfo() {
		return patientActiveTimeInfo;
	}

	public void setPatientActiveTimeInfo(PatientActiveTimeInfo patientActiveTimeInfo) {
		this.patientActiveTimeInfo = patientActiveTimeInfo;
	}

	public PatientStepCountInfo getPatientStepCountInfo() {
		return patientStepCountInfo;
	}

	public void setPatientStepCountInfo(PatientStepCountInfo patientStepCountInfo) {
		this.patientStepCountInfo = patientStepCountInfo;
	}

	public PatientCalorieCountInfo getPatientCalorieCountInfo() {
		return patientCalorieCountInfo;
	}

	public void setPatientCalorieCountInfo(
			PatientCalorieCountInfo patientCalorieCountInfo) {
		this.patientCalorieCountInfo = patientCalorieCountInfo;
	}

	public PatientHeartAgeInfo getPatientHeartAgeInfo() {
		return patientHeartAgeInfo;
	}

	public void setPatientHeartAgeInfo(PatientHeartAgeInfo patientHeartAgeInfo) {
		this.patientHeartAgeInfo = patientHeartAgeInfo;
	}

	public PatientHeartRateInfo getPatientHeartRateInfo() {
		return patientHeartRateInfo;
	}

	public void setPatientHeartRateInfo(PatientHeartRateInfo patientHeartRateInfo) {
		this.patientHeartRateInfo = patientHeartRateInfo;
	}

	public PatientSleepInfo getPatientSleepInfo() {
		return patientSleepInfo;
	}

	public void setPatientSleepInfo(PatientSleepInfo patientSleepInfo) {
		this.patientSleepInfo = patientSleepInfo;
	}

	public PatientOverallStressInfo getPatientOverallStressInfo() {
		return patientOverallStressInfo;
	}

	public void setPatientOverallStressInfo(
			PatientOverallStressInfo patientOverallStressInfo) {
		this.patientOverallStressInfo = patientOverallStressInfo;
	}

	/**
	 * @return the patientDistanceList
	 */
	public List<PatientDistanceInfo> getPatientDistanceList() {
		return patientDistanceList;
	}

	/**
	 * @param patientDistanceList the patientDistanceList to set
	 */
	public void setPatientDistanceList(List<PatientDistanceInfo> patientDistanceList) {
		this.patientDistanceList = patientDistanceList;
	}

	/**
	 * @return the overallStressList
	 */
	public List<PatientOverallStressInfo> getOverallStressList() {
		return overallStressList;
	}

	/**
	 * @param overallStressList the overallStressList to set
	 */
	public void setOverallStressList(
			List<PatientOverallStressInfo> overallStressList) {
		this.overallStressList = overallStressList;
	}

	/**
	 * @return the patientActiveTimeList
	 */
	public List<PatientActiveTimeInfo> getPatientActiveTimeList() {
		return patientActiveTimeList;
	}

	/**
	 * @param patientActiveTimeList the patientActiveTimeList to set
	 */
	public void setPatientActiveTimeList(
			List<PatientActiveTimeInfo> patientActiveTimeList) {
		this.patientActiveTimeList = patientActiveTimeList;
	}

	/**
	 * @return the patientStepCountList
	 */
	public List<PatientStepCountInfo> getPatientStepCountList() {
		return patientStepCountList;
	}

	/**
	 * @param patientStepCountList the patientStepCountList to set
	 */
	public void setPatientStepCountList(
			List<PatientStepCountInfo> patientStepCountList) {
		this.patientStepCountList = patientStepCountList;
	}

	/**
	 * @return the patientCalorieCountList
	 */
	public List<PatientCalorieCountInfo> getPatientCalorieCountList() {
		return patientCalorieCountList;
	}

	/**
	 * @param patientCalorieCountList the patientCalorieCountList to set
	 */
	public void setPatientCalorieCountList(
			List<PatientCalorieCountInfo> patientCalorieCountList) {
		this.patientCalorieCountList = patientCalorieCountList;
	}

	/**
	 * @return the patientHeartAgeList
	 */
	public List<PatientHeartAgeInfo> getPatientHeartAgeList() {
		return patientHeartAgeList;
	}

	/**
	 * @param patientHeartAgeList the patientHeartAgeList to set
	 */
	public void setPatientHeartAgeList(List<PatientHeartAgeInfo> patientHeartAgeList) {
		this.patientHeartAgeList = patientHeartAgeList;
	}

	/**
	 * @return the patientHeartRateList
	 */
	public List<PatientHeartRateInfo> getPatientHeartRateList() {
		return patientHeartRateList;
	}

	/**
	 * @param patientHeartRateList the patientHeartRateList to set
	 */
	public void setPatientHeartRateList(
			List<PatientHeartRateInfo> patientHeartRateList) {
		this.patientHeartRateList = patientHeartRateList;
	}

	/**
	 * @return the patientSleepList
	 */
	public List<PatientSleepInfo> getPatientSleepList() {
		return patientSleepList;
	}

	/**
	 * @param patientSleepList the patientSleepList to set
	 */
	public void setPatientSleepList(List<PatientSleepInfo> patientSleepList) {
		this.patientSleepList = patientSleepList;
	}

	public List<AccountWeightInfo> getWeightList() {
		return weightList;
	}

	public void setWeightList(List<AccountWeightInfo> weightList) {
		this.weightList = weightList;
	}

	public AccountWeightInfo getAccountWeightInfo() {
		return accountWeightInfo;
	}

	public void setAccountWeightInfo(AccountWeightInfo accountWeightInfo) {
		this.accountWeightInfo = accountWeightInfo;
	}

	public BloodPressureInfo getBloodPressureInfo() {
		return bloodPressureInfo;
	}

	public void setBloodPressureInfo(BloodPressureInfo bloodPressureInfo) {
		this.bloodPressureInfo = bloodPressureInfo;
	}

	public List<NsScheduleTime> getScheduledInfoList() {
		return scheduledInfoList;
	}

	public void setScheduledInfoList(List<NsScheduleTime> scheduledInfoList) {
		this.scheduledInfoList = scheduledInfoList;
	}

	public Integer getReminderId() {
		return reminderId;
	}

	public void setReminderId(Integer reminderId) {
		this.reminderId = reminderId;
	}

	public List<TestResultInfo> getTestResultInfo() {
		return testResultInfo;
	}

	public void setTestResultInfo(List<TestResultInfo> testResultInfo) {
		this.testResultInfo = testResultInfo;
	}

	public VisitDetails getVisitDetails() {
		return visitDetails;
	}

	public void setVisitDetails(VisitDetails visitDetails) {
		this.visitDetails = visitDetails;
	}

	public PatientInformation getPatientInformation() {
		return patientInformation;
	}

	public void setPatientInformation(PatientInformation patientInformation) {
		this.patientInformation = patientInformation;
	}

	public Boolean getNoMorePatientAppointment() {
		return noMorePatientAppointment;
	}

	public void setNoMorePatientAppointment(Boolean noMorePatientAppointment) {
		this.noMorePatientAppointment = noMorePatientAppointment;
	}

	public List<NsPatientAppointment> getUpcomingPatientAppointment() {
		return upcomingPatientAppointment;
	}

	public void setUpcomingPatientAppointment(
			List<NsPatientAppointment> upcomingPatientAppointment) {
		this.upcomingPatientAppointment = upcomingPatientAppointment;
	}

	public List<NsPatientAppointment> getPastPatientAppointment() {
		return pastPatientAppointment;
	}

	public void setPastPatientAppointment(
			List<NsPatientAppointment> pastPatientAppointment) {
		this.pastPatientAppointment = pastPatientAppointment;
	}

	public Boolean getNoMoreFeed() {
		return noMoreFeed;
	}

	public void setNoMoreFeed(Boolean noMoreFeed) {
		this.noMoreFeed = noMoreFeed;
	}

	/**
	 * @return the billSummaryData
	 */
	public BillSummaryData getBillSummaryData() {
		return billSummaryData;
	}

	/**
	 * @param billSummaryData
	 *            the billSummaryData to set
	 */
	public void setBillSummaryData(BillSummaryData billSummaryData) {
		this.billSummaryData = billSummaryData;
	}

	/**
	 * @return the billSummaryDataList
	 */
	public List<BillSummaryData> getBillSummaryDataList() {
		return billSummaryDataList;
	}

	/**
	 * @param billSummaryDataList
	 *            the billSummaryDataList to set
	 */
	public void setBillSummaryDataList(List<BillSummaryData> billSummaryDataList) {
		this.billSummaryDataList = billSummaryDataList;
	}

	public List<Facility> getRegisteredFacilities() {
		return registeredFacilities;
	}

	public void setRegisteredFacilities(List<Facility> registeredFacilities) {
		this.registeredFacilities = registeredFacilities;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public List<Facility> getSearchedFacilities() {
		return searchedFacilities;
	}

	public void setSearchedFacilities(List<Facility> searchedFacilities) {
		this.searchedFacilities = searchedFacilities;
	}

	public String getAppStoreVersion() {
		return appStoreVersion;
	}

	public void setAppStoreVersion(String appStoreVersion) {
		this.appStoreVersion = appStoreVersion;
	}

	public List<Integer> getBloodPressureIdsList() {
		return bloodPressureIdsList;
	}

	public void setBloodPressureIdsList(List<Integer> bloodPressureIdsList) {
		this.bloodPressureIdsList = bloodPressureIdsList;
	}

	public List<Integer> getMedicationIdsList() {
		return medicationIdsList;
	}

	public void setMedicationIdsList(List<Integer> medicationIdsList) {
		this.medicationIdsList = medicationIdsList;
	}

	public GeoLocationResponseInfo getGeoLocationResponseInfo() {
		return geoLocationResponseInfo;
	}

	public void setGeoLocationResponseInfo(GeoLocationResponseInfo geoLocationResponseInfo) {
		this.geoLocationResponseInfo = geoLocationResponseInfo;
	}

	protected List<PatientImagingObj> patientImagingObjList;

	protected Map<String, List<AllergiesInfo>> patientAllergiesMap;
	MedicationManagementReminderInfo medicationManagementReminder;

	/*
	 * private LinkedAccount linkedAccount;
	 * 
	 * public LinkedAccount getLinkedAccount() { return linkedAccount; }
	 * 
	 * public void setLinkedAccount(LinkedAccount linkedAccount) {
	 * this.linkedAccount = linkedAccount; }
	 */
	public MedicationManagementReminderInfo getMedicationManagementReminder() {
		return medicationManagementReminder;
	}

	public void setMedicationManagementReminder(MedicationManagementReminderInfo medicationManagementReminder) {
		this.medicationManagementReminder = medicationManagementReminder;
	}

	public Map<String, List<AllergiesInfo>> getPatientAllergiesMap() {
		return patientAllergiesMap;
	}

	public void setPatientAllergiesMap(Map<String, List<AllergiesInfo>> patientAllergiesMap) {
		this.patientAllergiesMap = patientAllergiesMap;
	}

	public List<PatientImagingObj> getPatientImagingObjList() {
		return patientImagingObjList;
	}

	public void setPatientImagingObjList(List<PatientImagingObj> patientImagingObjList) {
		this.patientImagingObjList = patientImagingObjList;
	}

	@JsonSerialize(using = ByteArraySerializer.class)
	protected byte[] muData = null;

	public List<MedicationSourceDates> getMedicationSourceAsOfDate() {
		return medicationSourceAsOfDate;
	}

	public void setMedicationSourceAsOfDate(List<MedicationSourceDates> medicationSourceAsOfDate) {
		this.medicationSourceAsOfDate = medicationSourceAsOfDate;
	}

	protected NsResponse viewMuData;

	protected List<VitalSigns> vitalSignsList;

	protected VitalSigns vitalSigns;

	protected String hopitalizationReason;

	protected List<WarningMessages> warningMessage;

	protected List<WarningMessages> bloodPressureWarningMessage;

	protected List<WarningMessages> medicationWarningMessage;

	protected SystemAdministratorInfo systemAdministratorInfo;

	protected List<ActivityLogInfo> activityLog;

	protected List<VDTDataObject> vdtData;

	private Integer codeSendOptions;

	protected Integer warningCount;

	protected Integer medicationYesCount;

	protected Integer medicationTotalCount;

	protected Double BPYesCount;

	protected Double BPTotalCount;

	protected List<CateTeamInfo> cateTeamInfoList;

	protected String responseDescription;

	private String scheduledMaintainanceMsg;

	protected Map<String, List<PatientProblemInfo>> patientProblemsMap;
	
	protected Map<String, List<MedicationInfo>> medicationPrescMap;

	protected List<BloodPressureInfo> bloodPressureInfoList;
	protected Integer hospitalNotificationsCount;
	protected LabResults labRes;
	protected String resendMessageDescription;
	
	List<EngageFeatures> featuresList;
	List<EngageModule> moduleList;
	
	protected String fogetPinMessage;
	//protected String pinHeader;
	//protected String pinMessage;
	
   /* public String getPinMessage() {
		return pinMessage;
	}

	public void setPinMessage(String pinMessage) {
		this.pinMessage = pinMessage;
	}*/

	/*public String getPinHeader() {
		return pinHeader;
	}

	public void setPinHeader(String pinHeader) {
		this.pinHeader = pinHeader;
	}*/

	public String getFogetPinMessage() {
		return fogetPinMessage;
	}

	public void setFogetPinMessage(String fogetPinMessage) {
		this.fogetPinMessage = fogetPinMessage;
	}

	public List<EngageModule> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<EngageModule> moduleList) {
		this.moduleList = moduleList;
	}

	
	
	
	

	public String getResendMessageDescription() {
		return resendMessageDescription;
	}

	public void setResendMessageDescription(String resendMessageDescription) {
		this.resendMessageDescription = resendMessageDescription;
	}

	/**
	 * @return the medicationPrescMap
	 */
	public Map<String, List<MedicationInfo>> getMedicationPrescMap() {
		return medicationPrescMap;
	}

	/**
	 * @param medicationPrescMap the medicationPrescMap to set
	 */
	public void setMedicationPrescMap(Map<String, List<MedicationInfo>> medicationPrescMap) {
		this.medicationPrescMap = medicationPrescMap;
	}

	public List<EngageFeatures> getFeaturesList() {
		return featuresList;
	}

	public void setFeaturesList(List<EngageFeatures> featuresList) {
		this.featuresList = featuresList;
	}

	public Map<String, List<PatientProblemInfo>> getPatientProblemsMap() {
		return patientProblemsMap;
	}

	public void setPatientProblemsMap(Map<String, List<PatientProblemInfo>> patientProblemsMap) {
		this.patientProblemsMap = patientProblemsMap;
	}

	public LabResults getLabRes() {
		return labRes;
	}

	public void setLabRes(LabResults labRes) {
		this.labRes = labRes;
	}

	Map<String, List<NsPatientLabResult>> patientLabResultMap;

	Map<String, Map<String, List<NsPatientPrescription>>> patientPrescriptionMap;

	public Map<String, Map<String, List<NsPatientPrescription>>> getPatientPrescriptionMap() {
		return patientPrescriptionMap;
	}

	public void setPatientPrescriptionMap(Map<String, Map<String, List<NsPatientPrescription>>> mapSourceWise) {
		this.patientPrescriptionMap = mapSourceWise;
	}

	public Map<String, List<NsPatientLabResult>> getPatientLabResultMap() {
		return patientLabResultMap;
	}

	public void setPatientLabResultMap(Map<String, List<NsPatientLabResult>> patientLabResultMap2) {
		this.patientLabResultMap = patientLabResultMap2;
	}

	Map<String, List<String>> featureMap;

	public Map<String, List<String>> getFeatureMap() {
		return featureMap;
	}

	public void setFeatureMap(Map<String, List<String>> fMap) {
		this.featureMap = fMap;
	}

	public String getScheduledMaintainanceMsg() {
		if (scheduledMaintainanceMsg != null)
			return scheduledMaintainanceMsg;
		else
			return " ";
	}

	public void setScheduledMaintainanceMsg(String scheduledMaintainanceMsg) {
		this.scheduledMaintainanceMsg = scheduledMaintainanceMsg;
	}

	public Integer getHospitalNotificationsCount() {
		return hospitalNotificationsCount;
	}

	public void setHospitalNotificationsCount(Integer hospitalNotificationsCount) {
		this.hospitalNotificationsCount = hospitalNotificationsCount;
	}

	public Double getBPYesCount() {
		return BPYesCount;
	}

	public void setBPYesCount(Double bPYesCount) {
		BPYesCount = bPYesCount;
	}

	public Double getBPTotalCount() {
		return BPTotalCount;
	}

	public void setBPTotalCount(Double bPTotalCount) {
		BPTotalCount = bPTotalCount;
	}

	public List<BloodPressureInfo> getBloodPressureInfoList() {
		return bloodPressureInfoList;
	}

	public void setBloodPressureInfoList(List<BloodPressureInfo> bloodPressureInfoList) {
		this.bloodPressureInfoList = bloodPressureInfoList;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public NotificationsCount getNotificationsCount() {
		return notificationsCount;
	}

	public void setNotificationsCount(NotificationsCount notificationsCount) {
		this.notificationsCount = notificationsCount;
	}

	public List<CateTeamInfo> getCateTeamInfoList() {
		return cateTeamInfoList;
	}

	public void setCateTeamInfoList(List<CateTeamInfo> cateTeamInfoList) {
		this.cateTeamInfoList = cateTeamInfoList;
	}

	public Integer getNotificationWarningCount() {
		return notificationWarningCount;
	}

	public void setNotificationWarningCount(Integer notificationWarningCount) {
		this.notificationWarningCount = notificationWarningCount;
	}

	public Integer getRemindersCount() {
		return remindersCount;
	}

	public void setRemindersCount(Integer remindersCount) {
		this.remindersCount = remindersCount;
	}

	public Integer getMedicationRemindersCount() {
		return medicationRemindersCount;
	}

	public void setMedicationRemindersCount(Integer medicationRemindersCount) {
		this.medicationRemindersCount = medicationRemindersCount;
	}

	public Integer getBloodPressureRemindersCount() {
		return bloodPressureRemindersCount;
	}

	public void setBloodPressureRemindersCount(Integer bloodPressureRemindersCount) {
		this.bloodPressureRemindersCount = bloodPressureRemindersCount;
	}

	public Integer getWarningCount() {
		return warningCount;
	}

	public void setWarningCount(Integer warningCount) {
		this.warningCount = warningCount;
	}

	public Integer getMedicationYesCount() {
		return medicationYesCount;
	}

	public void setMedicationYesCount(Integer medicationYesCount) {
		this.medicationYesCount = medicationYesCount;
	}

	public Integer getMedicationTotalCount() {
		return medicationTotalCount;
	}

	public void setMedicationTotalCount(Integer medicationTotalCount) {
		this.medicationTotalCount = medicationTotalCount;
	}

	public VitalSigns getVitalSigns() {
		return vitalSigns;
	}

	public Integer getCodeSendOptions() {
		return codeSendOptions;
	}

	public void setCodeSendOptions(Integer codeSendOptions) {
		this.codeSendOptions = codeSendOptions;
	}

	public Integer getVisitType() {
		return visitType;
	}

	public void setVisitType(Integer visitType) {
		this.visitType = visitType;
	}

	public List<VDTDataObject> getVdtData() {
		return vdtData;
	}

	public void setVdtData(List<VDTDataObject> vdtData) {
		this.vdtData = vdtData;
	}

	public NsResponse getViewMuData() {
		return viewMuData;
	}

	public void setViewMuData(NsResponse viewMuData) {
		this.viewMuData = viewMuData;
	}

	public List<ActivityLogInfo> getActivityLog() {
		return activityLog;
	}

	public void setActivityLog(List<ActivityLogInfo> activityLog) {
		this.activityLog = activityLog;
	}

	public SystemAdministratorInfo getSystemAdministratorInfo() {
		return systemAdministratorInfo;
	}

	public void setSystemAdministratorInfo(SystemAdministratorInfo systemAdministratorInfo) {
		this.systemAdministratorInfo = systemAdministratorInfo;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public List<VitalSigns> getVitalSignsList() {
		return vitalSignsList;
	}

	public void setVitalSignsList(List<VitalSigns> vitalSignsList) {
		this.vitalSignsList = vitalSignsList;
	}

	public void setVitalSigns(VitalSigns vitalSigns) {
		this.vitalSigns = vitalSigns;
	}

	public List<WarningMessages> getBloodPressureWarningMessage() {
		return bloodPressureWarningMessage;
	}

	public void setBloodPressureWarningMessage(List<WarningMessages> bloodPressureWarningMessage) {
		this.bloodPressureWarningMessage = bloodPressureWarningMessage;
	}

	public List<WarningMessages> getMedicationWarningMessage() {
		return medicationWarningMessage;
	}

	public void setMedicationWarningMessage(List<WarningMessages> medicationWarningMessage) {
		this.medicationWarningMessage = medicationWarningMessage;
	}

	public List<WarningMessages> getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(List<WarningMessages> warningMessage) {
		this.warningMessage = warningMessage;
	}

	public String getHopitalizationReason() {
		return hopitalizationReason;
	}

	public void setHopitalizationReason(String hopitalizationReason) {
		this.hopitalizationReason = hopitalizationReason;
	}

	public byte[] getMuData() {
		return muData;
	}

	public void setMuData(byte[] muData) {
		this.muData = muData;
	}

	public PatientInpatientMetadataInfo getPatientInpatientMetadataInfo() {
		return patientInpatientMetadataInfo;
	}

	public void setPatientInpatientMetadataInfo(PatientInpatientMetadataInfo patientInpatientMetadataInfo) {
		this.patientInpatientMetadataInfo = patientInpatientMetadataInfo;
	}

	public ViewPatientInfo getViewPatientInfo() {
		return viewPatientInfo;
	}

	public void setViewPatientInfo(ViewPatientInfo viewPatientInfo) {
		this.viewPatientInfo = viewPatientInfo;
	}

	public CacheInputs getCacheInputs() {
		return cacheInputs;
	}

	public void setCacheInputs(CacheInputs cacheInputs) {
		this.cacheInputs = cacheInputs;
	}

	public List<BloodPressureReminderInfo> getBloodPressureReminderList() {
		return bloodPressureReminderList;
	}

	public void setBloodPressureReminderList(List<BloodPressureReminderInfo> bloodPressureReminderList) {
		this.bloodPressureReminderList = bloodPressureReminderList;
	}

	public List<MedicationManagementReminderInfo> getMedicationManagementReminderList() {
		return medicationManagementReminderList;
	}

	public void setMedicationManagementReminderList(
			List<MedicationManagementReminderInfo> medicationManagementReminderList) {
		this.medicationManagementReminderList = medicationManagementReminderList;
	}

	public MedicationManagmntInfo getMedicationManagmntInfo() {
		return medicationManagmntInfo;
	}

	public void setMedicationManagmntInfo(MedicationManagmntInfo medicationManagmntInfo) {
		this.medicationManagmntInfo = medicationManagmntInfo;
	}

	public List<MedicationManagmntInfo> getMedicationManagmntInfoList() {
		return medicationManagmntInfoList;
	}

	public void setMedicationManagmntInfoList(List<MedicationManagmntInfo> medicationManagmntInfoList) {
		this.medicationManagmntInfoList = medicationManagmntInfoList;
	}

	public List<NsMedicationDosageList> getNsMedicationDosageList() {
		return nsMedicationDosageList;
	}

	public void setNsMedicationDosageList(List<NsMedicationDosageList> nsMedicationDosageList) {
		this.nsMedicationDosageList = nsMedicationDosageList;
	}

	public List<NsMedicationKindList> getNsMedicationKindList() {
		return nsMedicationKindList;
	}

	public void setNsMedicationKindList(List<NsMedicationKindList> nsMedicationKindList) {
		this.nsMedicationKindList = nsMedicationKindList;
	}

	public List<NsMedicationMethodList> getNsMedicationMethodList() {
		return nsMedicationMethodList;
	}

	public void setNsMedicationMethodList(List<NsMedicationMethodList> nsMedicationMethodList) {
		this.nsMedicationMethodList = nsMedicationMethodList;
	}

	public List<NsFrequencyList> getNsFrequencyList() {
		return nsFrequencyList;
	}

	public void setNsFrequencyList(List<NsFrequencyList> nsFrequencyList) {
		this.nsFrequencyList = nsFrequencyList;
	}

	public List<BloodPressureInfo> getBloodPressureList() {
		return bloodPressureList;
	}

	public void setBloodPressureList(List<BloodPressureInfo> bloodPressureList) {
		this.bloodPressureList = bloodPressureList;
	}

	public List<NsFeedBackList> getNsFeedBackList() {
		return nsFeedBackList;
	}

	public void setNsFeedBackList(List<NsFeedBackList> nsFeedBackList) {
		this.nsFeedBackList = nsFeedBackList;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}

	public List<NsSecurityQuestion> getSecurityQuestions() {
		return securityQuestions;
	}

	public void setSecurityQuestions(List<NsSecurityQuestion> securityQuestions) {
		this.securityQuestions = securityQuestions;
	}

	public List<NsPatientAppointment> getNsPatientAppointments() {
		return nsPatientAppointments;
	}

	public void setNsPatientAppointments(List<NsPatientAppointment> nsPatientAppointments) {
		this.nsPatientAppointments = nsPatientAppointments;
	}

	public List<NsPatientVisit> getPatientVisitList() {
		return patientVisitList;
	}

	public void setPatientVisitList(List<NsPatientVisit> patientVisitList) {
		this.patientVisitList = patientVisitList;
	}

	public List<NsDoctorsList> getNsDoctorsList() {
		return nsDoctorsList;
	}

	public void setNsDoctorsList(List<NsDoctorsList> nsDoctorsList) {
		this.nsDoctorsList = nsDoctorsList;
	}

	public List<NsServicesList> getNsServicesList() {
		return nsServicesList;
	}

	public void setNsServicesList(List<NsServicesList> nsServicesList) {
		this.nsServicesList = nsServicesList;
	}

	public List<NsPatientLabResult> getPatientLabResults() {
		return patientLabResults;
	}

	public void setPatientLabResults(List<NsPatientLabResult> patientLabResults) {
		this.patientLabResults = patientLabResults;
	}

	public List<NsPatientPrescription> getPatientPrescriptions() {
		return patientPrescriptions;
	}

	public void setPatientPrescriptions(List<NsPatientPrescription> patientPrescriptions) {
		this.patientPrescriptions = patientPrescriptions;
	}

	public List<NsHospitalNotice> getHospitalNotices() {
		return hospitalNotices;
	}

	public void setHospitalNotices(List<NsHospitalNotice> hospitalNotices) {
		this.hospitalNotices = hospitalNotices;
	}

	public List<AllergiesInfo> getAllergiesInfoList() {
		return allergiesInfoList;
	}

	public void setAllergiesInfoList(List<AllergiesInfo> allergiesInfoList) {
		this.allergiesInfoList = allergiesInfoList;
	}

	public List<MedicationInfo> getMedicationInfoList() {
		return medicationInfoList;
	}

	public void setMedicationInfoList(List<MedicationInfo> medicationInfoList) {
		this.medicationInfoList = medicationInfoList;
	}

	public List<PatientProblemInfo> getPatientProblemInfoList() {
		return patientProblemInfoList;
	}

	public void setPatientProblemInfoList(List<PatientProblemInfo> patientProblemInfoList) {
		this.patientProblemInfoList = patientProblemInfoList;
	}

	public List<ProcedureInfo> getProcedureInfoList() {
		return procedureInfoList;
	}

	public void setProcedureInfoList(List<ProcedureInfo> procedureInfoList) {
		this.procedureInfoList = procedureInfoList;
	}

	public List<LabResultInfo> getLabResultInfoList() {
		return labResultInfoList;
	}

	public void setLabResultInfoList(List<LabResultInfo> labResultInfoList) {
		this.labResultInfoList = labResultInfoList;
	}

	public List<CarePlanInfo> getCarePlanInfoList() {
		return carePlanInfoList;
	}

	public void setCarePlanInfoList(List<CarePlanInfo> carePlanInfoList) {
		this.carePlanInfoList = carePlanInfoList;
	}

	public List<PatientInpatientDiagnosisInfo> getPatientInpatientDiagnosisInfoList() {
		return patientInpatientDiagnosisInfoList;
	}

	public void setPatientInpatientDiagnosisInfoList(
			List<PatientInpatientDiagnosisInfo> patientInpatientDiagnosisInfoList) {
		this.patientInpatientDiagnosisInfoList = patientInpatientDiagnosisInfoList;
	}

	public List<PatientInpatientFunctionalStatusInfo> getPatientInpatientFunctionalStatusInfoList() {
		return patientInpatientFunctionalStatusInfoList;
	}

	public void setPatientInpatientFunctionalStatusInfoList(
			List<PatientInpatientFunctionalStatusInfo> patientInpatientFunctionalStatusInfoList) {
		this.patientInpatientFunctionalStatusInfoList = patientInpatientFunctionalStatusInfoList;
	}

	public List<PatientInpatientImmunalizationInfo> getPatientInpatientImmunalizationInfoList() {
		return patientInpatientImmunalizationInfoList;
	}

	public void setPatientInpatientImmunalizationInfoList(
			List<PatientInpatientImmunalizationInfo> patientInpatientImmunalizationInfoList) {
		this.patientInpatientImmunalizationInfoList = patientInpatientImmunalizationInfoList;
	}

	public void setSourceList(Set<SourceInfo> sourceList) {
		this.sourceList = sourceList;
	}

	public Set<SourceInfo> getSourceList() {
		return sourceList;
	}

	public void setPatientImagingObjectMap(Map<String, List<PatientImagingInfo>> patientImagingObjectMap) {
		this.patientImagingObjectMap = patientImagingObjectMap;

	}

	public Map<String, List<PatientImagingInfo>> getPatientImagingObjectMap() {
		return patientImagingObjectMap;
	}

	public List<FeedListInfo> getFeedInformationList() {
		return feedInformationList;
	}

	public void setFeedInformationList(List<FeedListInfo> feedInformationList) {
		this.feedInformationList = feedInformationList;
	}

	public FeedListInfo getFeedInformation() {
		return feedInformation;
	}

	public void setFeedInformation(FeedListInfo feedInformation) {
		this.feedInformation = feedInformation;
	}

}
