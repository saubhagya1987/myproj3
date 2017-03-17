package com.versawork.http.dataobject;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BloodPressureReminderInfo {

	// private final static long serialVersionUID = 1L;

	protected Integer bloodPressureReminderId;
	protected Integer accountId;
	protected String reminderFor;

	protected String reminderBeginDate;
	protected String reminderEndDate;

	protected Boolean active;
	protected String dateAdded;
	protected Boolean deleteFlag;

	protected FrequencyDesc frequencyDesc;

	protected List<NsScheduleTime> nsScheduleTimeList;

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getBloodPressureReminderId() {
		return bloodPressureReminderId;
	}

	public void setBloodPressureReminderId(Integer bloodPressureReminderId) {
		this.bloodPressureReminderId = bloodPressureReminderId;
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

	/*
	 * public List<NsScheduleTime> getNsMedicationScheduleTimeList() { return
	 * nsScheduleTimeList; }
	 * 
	 * public void setNsMedicationScheduleTimeList(List<NsScheduleTime>
	 * nsMedicationScheduleTimeList) { this.nsScheduleTimeList =
	 * nsMedicationScheduleTimeList; }
	 */

}
