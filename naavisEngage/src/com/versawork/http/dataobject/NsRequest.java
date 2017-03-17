package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsRequest implements Serializable {
	private final static long serialVersionUID = 1L;

	protected AccountInfo accountInfo;
	private BillingInfo billingInfo;
	protected NsPatientAppointment nsPatientAppointment;

	protected NsPatientVisit nsPatientVisit;

	protected List<NsSecurityQuestion> securityQuestions;

	protected CacheInputs cacheInputs;

	protected FeedbackDetails feedbackDetails;

	protected NsPin nsPin;

	protected Locale locale;

	String providerView;
	protected Integer clientDbId;

	protected NsHospitalConfirmation nsHospitalConfirmation;

	protected BloodPressureInfo bloodPressureInfo;

	protected MedicationManagementReminderInfo medicationManagementReminder;

	protected BloodPressureReminderInfo bloodPressureReminder;

	protected MedicationManagmntInfo medicationManagmntInfo;

	protected ViewPatientInfo viewPatientInfo;

	protected TransmitEHR transmitEHR;

	private String codeSendMode;

	private String securityCode;

	private Boolean authByFingerprint;

	protected SystemAdministratorInfo systemAdministratorInfo;

	protected MaintananceInfo maintananceInfo;

	protected ActivityLogInfo activityLog;

	protected VDTDataObject vdtData;

	protected NotificationCounter notificationCounter;

	protected Integer hospitalNoticeId;

	protected Character providerPurpose;

	protected String testId;

	private Boolean setAllViewed;
	
	private Boolean phoneNumberMatched;
	
	private Boolean emailMatched;

	private List<SecondaryAccount> secondaryAccounts;
	protected HospitalListInfo hospitalListInfo;

	protected GeoLocationResponseInfo geoLocationResponseInfo;
	private String searchFacility;
	
	private FeedListInfo feedListInfo;
	
	private Integer feedSize;
    
	private String updatePin;
	//private String authPin;
	//private String pushNotification;
	private Integer updateProfileId;
	
	private Integer upcomingAppointmentSize;
	
	protected String providerName;	
	
	protected Integer resultTypeID;
	
	protected String labGroupCode;	
	
	private List<MedicationManagementReminderInfo> medicationManagementReminderInfoList;
	
	protected AccountWeightInfo accountWeightInfo;
	
	protected Boolean fromLinkedAccount;
	
	
	protected PatientOverallStressInfo overallStress;
	protected PatientActiveTimeInfo patientActiveTime;
	protected PatientStepCountInfo patientStepCount;
	protected PatientCalorieCountInfo patientCalorieCount;
	protected PatientHeartAgeInfo  patientHeartAge;
	protected PatientHeartRateInfo patientHeartRate;
	protected PatientSleepInfo patientSleep;
	protected PatientDistanceInfo  patientDistance;
	protected String dateAdded;
	

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public PatientOverallStressInfo getOverallStress() {
		return overallStress;
	}

	public void setOverallStress(PatientOverallStressInfo overallStress) {
		this.overallStress = overallStress;
	}

	public PatientActiveTimeInfo getPatientActiveTime() {
		return patientActiveTime;
	}

	public void setPatientActiveTime(PatientActiveTimeInfo patientActiveTime) {
		this.patientActiveTime = patientActiveTime;
	}

	public PatientStepCountInfo getPatientStepCount() {
		return patientStepCount;
	}

	public void setPatientStepCount(PatientStepCountInfo patientStepCount) {
		this.patientStepCount = patientStepCount;
	}

	public PatientCalorieCountInfo getPatientCalorieCount() {
		return patientCalorieCount;
	}

	public void setPatientCalorieCount(PatientCalorieCountInfo patientCalorieCount) {
		this.patientCalorieCount = patientCalorieCount;
	}

	public PatientHeartAgeInfo getPatientHeartAge() {
		return patientHeartAge;
	}

	public void setPatientHeartAge(PatientHeartAgeInfo patientHeartAge) {
		this.patientHeartAge = patientHeartAge;
	}

	public PatientHeartRateInfo getPatientHeartRate() {
		return patientHeartRate;
	}

	public void setPatientHeartRate(PatientHeartRateInfo patientHeartRate) {
		this.patientHeartRate = patientHeartRate;
	}

	public PatientSleepInfo getPatientSleep() {
		return patientSleep;
	}

	public void setPatientSleep(PatientSleepInfo patientSleep) {
		this.patientSleep = patientSleep;
	}

	public PatientDistanceInfo getPatientDistance() {
		return patientDistance;
	}

	public void setPatientDistance(PatientDistanceInfo patientDistance) {
		this.patientDistance = patientDistance;
	}

	public Boolean getFromLinkedAccount() {
		return fromLinkedAccount;
	}

	public void setFromLinkedAccount(Boolean fromLinkedAccount) {
		this.fromLinkedAccount = fromLinkedAccount;
	}

	public AccountWeightInfo getAccountWeightInfo() {
		return accountWeightInfo;
	}

	public void setAccountWeightInfo(AccountWeightInfo accountWeightInfo) {
		this.accountWeightInfo = accountWeightInfo;
	}

	public List<MedicationManagementReminderInfo> getMedicationManagementReminderInfoList() {
		return medicationManagementReminderInfoList;
	}

	public void setMedicationManagementReminderInfoList(
			List<MedicationManagementReminderInfo> medicationManagementReminderInfoList) {
		this.medicationManagementReminderInfoList = medicationManagementReminderInfoList;
	}

	public String getLabGroupCode() {
		return labGroupCode;
	}

	public void setLabGroupCode(String labGroupCode) {
		this.labGroupCode = labGroupCode;
	}

	public Integer getResultTypeID() {
		return resultTypeID;
	}

	public void setResultTypeID(Integer resultTypeID) {
		this.resultTypeID = resultTypeID;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public Integer getUpcomingAppointmentSize() {
		return upcomingAppointmentSize;
	}

	public void setUpcomingAppointmentSize(Integer upcomingAppointmentSize) {
		this.upcomingAppointmentSize = upcomingAppointmentSize;
	}

	public Integer getFeedSize() {
		return feedSize;
	}

	public void setFeedSize(Integer feedSize) {
		this.feedSize = feedSize;
	}

	/*public String getPushNotification() {
		return pushNotification;
	}

	public void setPushNotification(String pushNotification) {
		this.pushNotification = pushNotification;
	}*/

	/**
	 * @return the phoneNumberMatched
	 */
	public Boolean getPhoneNumberMatched() {
		return phoneNumberMatched;
	}

	/**
	 * @param phoneNumberMatched the phoneNumberMatched to set
	 */
	public void setPhoneNumberMatched(Boolean phoneNumberMatched) {
		this.phoneNumberMatched = phoneNumberMatched;
	}

	/**
	 * @return the emailMatched
	 */
	public Boolean getEmailMatched() {
		return emailMatched;
	}

	/**
	 * @param emailMatched the emailMatched to set
	 */
	public void setEmailMatched(Boolean emailMatched) {
		this.emailMatched = emailMatched;
	}

	/**
	 * @return the billingInfo
	 */
	public BillingInfo getBillingInfo() {
		return billingInfo;
	}

	/**
	 * @param billingInfo
	 *            the billingInfo to set
	 */
	public void setBillingInfo(BillingInfo billingInfo) {
		this.billingInfo = billingInfo;
	}

	public Integer getClientDbId() {
		return clientDbId;
	}

	public void setClientDbId(Integer clientDbId) {
		this.clientDbId = clientDbId;
	}

	public String getSearchFacility() {
		return searchFacility;
	}

	public void setSearchFacility(String searchFacility) {
		this.searchFacility = searchFacility;
	}

	public List<SecondaryAccount> getSecondaryAccounts() {
		return secondaryAccounts;
	}

	public void setSecondaryAccounts(List<SecondaryAccount> secondaryAccounts) {
		this.secondaryAccounts = secondaryAccounts;
	}

	public Boolean getSetAllViewed() {
		return setAllViewed;
	}

	public void setSetAllViewed(Boolean setAllViewed) {
		this.setAllViewed = setAllViewed;
	}

	public GeoLocationResponseInfo getGeoLocationResponseInfo() {
		return geoLocationResponseInfo;
	}

	public void setGeoLocationResponseInfo(GeoLocationResponseInfo geoLocationResponseInfo) {
		this.geoLocationResponseInfo = geoLocationResponseInfo;
	}

	public HospitalListInfo getHospitalListInfo() {
		return hospitalListInfo;
	}

	public void setHospitalListInfo(HospitalListInfo hospitalListInfo) {
		this.hospitalListInfo = hospitalListInfo;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public Character getProviderPurpose() {
		return providerPurpose;
	}

	public void setProviderPurpose(Character providerPurpose) {
		this.providerPurpose = providerPurpose;
	}

	public Integer getHospitalNoticeId() {
		return hospitalNoticeId;
	}

	public void setHospitalNoticeId(Integer hospitalNoticeId) {
		this.hospitalNoticeId = hospitalNoticeId;
	}

	/*
	 * public BloodPressureReminderInfo getBloodPressureReminderInfo() { return
	 * bloodPressureReminderInfo; }
	 * 
	 * public void setBloodPressureReminderInfo(BloodPressureReminderInfo
	 * bloodPressureReminderInfo) { this.bloodPressureReminderInfo =
	 * bloodPressureReminderInfo; }
	 */

	public NotificationCounter getNotificationCounter() {
		return notificationCounter;
	}

	public void setNotificationCounter(NotificationCounter notificationCounter) {
		this.notificationCounter = notificationCounter;
	}

	public VDTDataObject getVdtData() {
		return vdtData;
	}

	public void setVdtData(VDTDataObject vdtData) {
		this.vdtData = vdtData;
	}

	public MaintananceInfo getMaintananceInfo() {
		return maintananceInfo;
	}

	public void setMaintananceInfo(MaintananceInfo maintananceInfo) {
		this.maintananceInfo = maintananceInfo;
	}

	public ActivityLogInfo getActivityLog() {
		return activityLog;
	}

	public void setActivityLog(ActivityLogInfo activityLog) {
		this.activityLog = activityLog;
	}

	public SystemAdministratorInfo getSystemAdministratorInfo() {
		return systemAdministratorInfo;
	}

	public void setSystemAdministratorInfo(SystemAdministratorInfo systemAdministratorInfo) {
		this.systemAdministratorInfo = systemAdministratorInfo;
	}

	public Boolean getAuthByFingerprint() {
		return authByFingerprint;
	}

	public void setAuthByFingerprint(Boolean authByFingerprint) {
		this.authByFingerprint = authByFingerprint;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getCodeSendMode() {
		return codeSendMode;
	}

	public void setCodeSendMode(String codeSendMode) {
		this.codeSendMode = codeSendMode;
	}

	public TransmitEHR getTransmitEHR() {
		return transmitEHR;
	}

	public void setTransmitEHR(TransmitEHR transmitEHR) {
		this.transmitEHR = transmitEHR;
	}

	public ViewPatientInfo getViewPatientInfo() {
		return viewPatientInfo;
	}

	public void setViewPatientInfo(ViewPatientInfo viewPatientInfo) {
		this.viewPatientInfo = viewPatientInfo;
	}

	public BloodPressureReminderInfo getBloodPressureReminder() {
		return bloodPressureReminder;
	}

	public void setBloodPressureReminder(BloodPressureReminderInfo bloodPressureReminder) {
		this.bloodPressureReminder = bloodPressureReminder;
	}

	public MedicationManagementReminderInfo getMedicationManagementReminder() {
		return medicationManagementReminder;
	}

	public void setMedicationManagementReminder(MedicationManagementReminderInfo medicationManagementReminder) {
		this.medicationManagementReminder = medicationManagementReminder;
	}

	public MedicationManagmntInfo getMedicationManagmntInfo() {
		return medicationManagmntInfo;
	}

	public void setMedicationManagmntInfo(MedicationManagmntInfo medicationManagmntInfo) {
		this.medicationManagmntInfo = medicationManagmntInfo;
	}

	public BloodPressureInfo getBloodPressureInfo() {
		return bloodPressureInfo;
	}

	public void setBloodPressureInfo(BloodPressureInfo bloodPressureInfo) {
		this.bloodPressureInfo = bloodPressureInfo;
	}

	public NsHospitalConfirmation getNsHospitalConfirmation() {
		return nsHospitalConfirmation;
	}

	public void setNsHospitalConfirmation(NsHospitalConfirmation nsHospitalConfirmation) {
		this.nsHospitalConfirmation = nsHospitalConfirmation;
	}

	public NsPatientVisit getNsPatientVisit() {
		return nsPatientVisit;
	}

	public void setNsPatientVisit(NsPatientVisit nsPatientVisit) {
		this.nsPatientVisit = nsPatientVisit;
	}

	public String getProviderView() {
		return providerView;
	}

	public void setProviderView(String providerView) {
		this.providerView = providerView;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	public List<NsSecurityQuestion> getSecurityQuestions() {
		return securityQuestions;
	}

	public void setSecurityQuestions(List<NsSecurityQuestion> securityQuestions) {
		this.securityQuestions = securityQuestions;
	}

	public NsPatientAppointment getNsPatientAppointment() {
		return nsPatientAppointment;
	}

	public void setNsPatientAppointment(NsPatientAppointment nsPatientAppointment) {
		this.nsPatientAppointment = nsPatientAppointment;
	}

	public FeedbackDetails getFeedbackDetails() {
		return feedbackDetails;
	}

	public void setFeedbackDetails(FeedbackDetails feedbackDetails) {
		this.feedbackDetails = feedbackDetails;
	}

	public NsPin getNsPin() {
		return nsPin;
	}

	public void setNsPin(NsPin nsPassword) {
		this.nsPin = nsPassword;
	}

	public CacheInputs getCacheInputs() {
		return cacheInputs;
	}

	public void setCacheInputs(CacheInputs cacheInputs) {
		this.cacheInputs = cacheInputs;
	}

	public FeedListInfo getFeedListInfo() {
		return feedListInfo;
	}

	public void setFeedListInfo(FeedListInfo feedListInfo) {
		this.feedListInfo = feedListInfo;
	}

	public Integer getUpdateProfileId() {
		return updateProfileId;
	}

	public void setUpdateProfileId(Integer updateProfileId) {
		this.updateProfileId = updateProfileId;
	}

	public String getUpdatePin() {
		return updatePin;
	}

	public void setUpdatePin(String updatePin) {
		this.updatePin = updatePin;
	}

	

	

}
