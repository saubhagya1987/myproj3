package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BloodPressureInfo implements Serializable {

	private final static long serialVersionUID = 1L;
	private String recordIdentifier;
	protected Integer bloodPressureId;
	protected Integer bloodPressureMgmtId;

	protected Integer accountId;
	protected Integer sys;
	protected Integer dia;
	protected Integer pulse=0;

	protected Integer responseId;
	protected String reminderDate;
	protected String comment;
	protected String dateAdded;
	protected Boolean deleteflag;

	protected String fromDate;
	protected String toDate;
	
	protected String bloodPressure;

	protected String sourceName;
	
	
	private String value;
    private String date;
    
    
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public Integer getResponseId() {
		return responseId;
	}

	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getRecordIdentifier() {
		return recordIdentifier;
	}

	public void setRecordIdentifier(String recordIdentifier) {
		this.recordIdentifier = recordIdentifier;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Integer getBloodPressureId() {
		return bloodPressureId;
	}

	public void setBloodPressureId(Integer bloodPressureId) {
		this.bloodPressureId = bloodPressureId;
	}

	public Integer getBloodPressureMgmtId() {
		return bloodPressureMgmtId;
	}

	public void setBloodPressureMgmtId(Integer bloodPressureMgmtId) {
		this.bloodPressureMgmtId = bloodPressureMgmtId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getSys() {
		return sys;
	}

	public void setSys(Integer sys) {
		this.sys = sys;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Integer getPulse() {
		return pulse;
	}

	public void setPulse(Integer pulse) {
		this.pulse = pulse;
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

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;

	}

	public Boolean isDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(Boolean deleteflag) {
		this.deleteflag = deleteflag;
	}
}
