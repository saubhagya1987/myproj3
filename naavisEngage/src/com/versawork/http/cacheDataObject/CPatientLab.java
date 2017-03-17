package com.versawork.http.cacheDataObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sohaib
 * 
 *         Key = PatientLab_accountId
 */
public class CPatientLab implements Serializable {

	private static final long serialVersionUID = 1L;
	private int accountId;
	private int patientVisitId;
	private String testId;
	private String labId;
	private String sourceId;
	private String sourceName;
	private String testName;
	private String labCode;
	private String labResult;
	private String labUnit;
	private Date resultDate;
	private String organizerName;
	private String organizerCode;
	private String interpretationCode;
	private Date dateAdded;
	// private String normalRange;
	private Float normalRangeMin;
	private Float normalRangeMax;
	private Float absoluteRangeMin;
	private Float absoluteRangeMax;
	private String abnormalFlag;
	private String labGroupId;
	private String labGroupCode;
	private String labGroupName;
	private Date labGroupDate;

	public Float getNormalRangeMin() {
		return normalRangeMin;
	}

	public void setNormalRangeMin(Float normalRangeMin) {
		this.normalRangeMin = normalRangeMin;
	}

	public Float getNormalRangeMax() {
		return normalRangeMax;
	}

	public void setNormalRangeMax(Float normalRangeMax) {
		this.normalRangeMax = normalRangeMax;
	}

	public Float getAbsoluteRangeMin() {
		return absoluteRangeMin;
	}

	public void setAbsoluteRangeMin(Float absoluteRangeMin) {
		this.absoluteRangeMin = absoluteRangeMin;
	}

	public Float getAbsoluteRangeMax() {
		return absoluteRangeMax;
	}

	public void setAbsoluteRangeMax(Float absoluteRangeMax) {
		this.absoluteRangeMax = absoluteRangeMax;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getPatientVisitId() {
		return patientVisitId;
	}

	public void setPatientVisitId(int patientVisitId) {
		this.patientVisitId = patientVisitId;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getLabId() {
		return labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getLabCode() {
		return labCode;
	}

	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}

	public String getLabResult() {
		return labResult;
	}

	public void setLabResult(String labResult) {
		this.labResult = labResult;
	}

	public String getLabUnit() {
		return labUnit;
	}

	public void setLabUnit(String labUnit) {
		this.labUnit = labUnit;
	}

	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	public String getOrganizerCode() {
		return organizerCode;
	}

	public void setOrganizerCode(String organizerCode) {
		this.organizerCode = organizerCode;
	}

	public String getInterpretationCode() {
		return interpretationCode;
	}

	public void setInterpretationCode(String interpretationCode) {
		this.interpretationCode = interpretationCode;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	/*
	 * public String getNormalRange() { return normalRange; }
	 * 
	 * public void setNormalRange(String normalRange) { this.normalRange =
	 * normalRange; }
	 */

	public String getAbnormalFlag() {
		return abnormalFlag;
	}

	public void setAbnormalFlag(String abnormalFlag) {
		this.abnormalFlag = abnormalFlag;
	}

	public String getLabGroupId() {
		return labGroupId;
	}

	public void setLabGroupId(String labGroupId) {
		this.labGroupId = labGroupId;
	}

	public String getLabGroupCode() {
		return labGroupCode;
	}

	public void setLabGroupCode(String labGroupCode) {
		this.labGroupCode = labGroupCode;
	}

	public String getLabGroupName() {
		return labGroupName;
	}

	public void setLabGroupName(String labGroupName) {
		this.labGroupName = labGroupName;
	}

	public Date getLabGroupDate() {
		return labGroupDate;
	}

	public void setLabGroupDate(Date labGroupDate) {
		this.labGroupDate = labGroupDate;
	}

}
