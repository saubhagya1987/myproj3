package com.versawork.http.dataobject;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MedicationManagmntInfo {

	// private final static long serialVersionUID = 1L;

	protected Integer medictnMgmtId;
	protected Integer accountId;
	protected String medicationName;
	//protected String rxNumber;
	protected Integer medicationKindId;
	//protected Integer medicationMethodId;
	protected String notes;
	protected String dateAdded;
	protected byte[] medicationImage;
	protected String reminderTime;

	protected String medicationKindDesc;
	protected String medicationMethodDesc;
	protected String medicationKind;
	
	protected String medicationDose;
	
	
	/*
	 * protected int medictnMgmtengageId; protected int medictnMgmtId; protected
	 * String reminderDate; protected String comment; protected Integer
	 * responseId;
	 */

	protected List<NsScheduleTime> nsScheduleTime;
	
	
	protected Float dosageId;
	protected String refillDate;
	protected Boolean isReminder;
    
	protected List<MedicationManagmntInfo> medicationManagmntInfoList;

	

	

	public String getMedicationKind() {
		return medicationKind;
	}

	public void setMedicationKind(String medicationKind) {
		this.medicationKind = medicationKind;
	}

	public List<MedicationManagmntInfo> getMedicationManagmntInfoList() {
		return medicationManagmntInfoList;
	}

	public void setMedicationManagmntInfoList(
			List<MedicationManagmntInfo> medicationManagmntInfoList) {
		this.medicationManagmntInfoList = medicationManagmntInfoList;
	}

	

	public Float getDosageId() {
		return dosageId;
	}

	public void setDosageId(Float dosageId) {
		this.dosageId = dosageId;
	}

	public String getRefillDate() {
		return refillDate;
	}

	public void setRefillDate(String refillDate) {
		this.refillDate = refillDate;
	}

	public Boolean getIsReminder() {
		return isReminder;
	}

	public void setIsReminder(Boolean isReminder) {
		this.isReminder = isReminder;
	}

	public byte[] getMedicationImage() {
		return medicationImage;
	}

	public void setMedicationImage(byte[] medicationImage) {
		this.medicationImage = medicationImage;
	}

	public List<NsScheduleTime> getNsScheduleTime() {
		return nsScheduleTime;
	}

	public void setNsScheduleTime(List<NsScheduleTime> nsScheduleTime) {
		this.nsScheduleTime = nsScheduleTime;
	}

	public String getMedicationMethodDesc() {
		return medicationMethodDesc;
	}

	public void setMedicationMethodDesc(String medicationMethodDesc) {
		this.medicationMethodDesc = medicationMethodDesc;
	}

	public String getMedicationKindDesc() {
		return medicationKindDesc;
	}

	public void setMedicationKindDesc(String medicationKindDesc) {
		this.medicationKindDesc = medicationKindDesc;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	/*public String getRxNumber() {
		return rxNumber;
	}

	public void setRxNumber(String rxNumber) {
		this.rxNumber = rxNumber;
	}*/

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<NsScheduleTime> getNsMedicationScheduleTime() {
		return nsScheduleTime;
	}

	public void setNsMedicationScheduleTime(List<NsScheduleTime> nsScheduleTime) {
		this.nsScheduleTime = nsScheduleTime;
	}

	public List<NsScheduleTime> getNsMedicationReminderTime() {
		return nsScheduleTime;
	}

	public void setNsMedicationReminderTime(List<NsScheduleTime> nsScheduleTime) {
		this.nsScheduleTime = nsScheduleTime;
	}

	public Integer getMedictnMgmtId() {
		return medictnMgmtId;
	}

	public void setMedictnMgmtId(Integer medictnMgmtId) {
		this.medictnMgmtId = medictnMgmtId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	/*public Integer getMedicationMethodId() {
		return medicationMethodId;
	}

	public void setMedicationMethodId(Integer medicationMethodId) {
		this.medicationMethodId = medicationMethodId;
	}*/

	public Integer getMedicationKindId() {
		return medicationKindId;
	}

	public void setMedicationKindId(Integer medicationKindId) {
		this.medicationKindId = medicationKindId;
	}

	public String getMedicationDose() {
		return medicationDose;
	}

	public void setMedicationDose(String medicationDose) {
		this.medicationDose = medicationDose;
	}
}
