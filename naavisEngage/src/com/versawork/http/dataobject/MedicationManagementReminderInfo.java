package com.versawork.http.dataobject;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MedicationManagementReminderInfo {

	// private final static long serialVersionUID = 1L;

	protected Integer medicationEngageId;
	protected String recordIdentifier;
	protected Integer medictnReminderId;
	protected Integer accountId;
	protected String reminderFor;

	protected String reminderBeginDate;
	protected String reminderEndDate;

	//protected Integer medicationDosageId;
	protected String dosage;
	protected String dateAdded;
	protected Boolean deleteFlag;
	protected Boolean active;
	protected String medicationName;
	protected String medicationType;
	protected Integer medictnMgmtengageId;
	protected Integer medictnMgmtId;
	protected String reminderDate;
	protected String comment;
	protected Integer responseId;
	protected String responseDesc;

	protected String fromDate;
	protected String toDate;

	protected FrequencyDesc frequencyDesc;
    
	
	protected NsMedicationDosageList medicationDosage;

	protected List<MedicationManagmntInfo> medicationManagmntInfoList;

	protected List<NsScheduleTime> nsScheduleTimeList;
	protected Integer frequencyValue;
	
	protected Integer frequencyId;

	

	public Integer getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(Integer frequencyId) {
		this.frequencyId = frequencyId;
	}

	public Integer getFrequencyValue() {
		return frequencyValue;
	}

	public void setFrequencyValue(Integer frequencyValue) {
		this.frequencyValue = frequencyValue;
	}

	public String getRecordIdentifier() {
		return recordIdentifier;
	}

	public void setRecordIdentifier(String recordIdentifier) {
		this.recordIdentifier = recordIdentifier;
	}

	public Integer getMedicationEngageId() {
		return medicationEngageId;
	}

	public void setMedicationEngageId(Integer medicationEngageId) {
		this.medicationEngageId = medicationEngageId;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getMedicationType() {
		return medicationType;
	}

	public void setMedicationType(String medicationType) {
		this.medicationType = medicationType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	public NsMedicationDosageList getMedicationDosage() {
		return medicationDosage;
	}

	public void setMedicationDosage(NsMedicationDosageList medicationDosage) {
		this.medicationDosage = medicationDosage;
	}

	public Integer getMedictnMgmtengageId() {
		return medictnMgmtengageId;
	}

	public void setMedictnMgmtengageId(Integer medictnMgmtengageId) {
		this.medictnMgmtengageId = medictnMgmtengageId;
	}

	public Integer getMedictnMgmtId() {
		return medictnMgmtId;
	}

	public void setMedictnMgmtId(Integer medictnMgmtId) {
		this.medictnMgmtId = medictnMgmtId;
	}

	public String getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(String reminderDate) {
		this.reminderDate = reminderDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getResponseId() {
		return responseId;
	}

	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}

	public FrequencyDesc getFrequencyDesc() {
		return frequencyDesc;
	}

	public void setFrequencyDesc(FrequencyDesc frequencyDesc) {
		this.frequencyDesc = frequencyDesc;
	}

	public List<NsScheduleTime> getNsScheduleTimeList() {
		return nsScheduleTimeList;
	}

	public void setNsScheduleTimeList(List<NsScheduleTime> nsScheduleTimeList) {
		this.nsScheduleTimeList = nsScheduleTimeList;
	}

	protected MedicationManagmntInfo medicationManagmntInfo;

	public MedicationManagmntInfo getMedicationManagmntInfo() {
		return medicationManagmntInfo;
	}

	public void setMedicationManagmntInfo(MedicationManagmntInfo medicationManagmntInfo) {
		this.medicationManagmntInfo = medicationManagmntInfo;
	}

	public Integer getMedictnReminderId() {
		return medictnReminderId;
	}

	public void setMedictnReminderId(Integer medictnReminderId) {
		this.medictnReminderId = medictnReminderId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getReminderFor() {
		return reminderFor;
	}

	public void setReminderFor(String reminderFor) {
		this.reminderFor = reminderFor;
	}

	public String getReminderBeginDate() {
		return reminderBeginDate;
	}

	public void setReminderBeginDate(String reminderBeginDate) {
		this.reminderBeginDate = reminderBeginDate;
	}

	public String getReminderEndDate() {
		return reminderEndDate;
	}

	public void setReminderEndDate(String reminderEndDate) {
		this.reminderEndDate = reminderEndDate;
	}

	/*public Integer getMedicationDosageId() {
		return medicationDosageId;
	}

	public void setMedicationDosageId(Integer medicationDosageId) {
		this.medicationDosageId = medicationDosageId;
	}*/

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public List<MedicationManagmntInfo> getMedicationManagmntInfoList() {
		return medicationManagmntInfoList;
	}

	public void setMedicationManagmntInfoList(List<MedicationManagmntInfo> medicationManagmntInfoList) {
		this.medicationManagmntInfoList = medicationManagmntInfoList;
	}

}
