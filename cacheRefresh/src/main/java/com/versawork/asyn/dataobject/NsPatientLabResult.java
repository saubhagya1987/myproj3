package com.versawork.asyn.dataobject;

import java.io.Serializable;
import javax.persistence.Column;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 *
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsPatientLabResult implements Serializable {

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

	public String getLabGroupDate() {
		return labGroupDate;
	}

	public void setLabGroupDate(String labGroupDate) {
		this.labGroupDate = labGroupDate;
	}

	public String getLabGroupId() {
		return labGroupId;
	}

	public void setLabGroupId(String labGroupId) {
		this.labGroupId = labGroupId;
	}

	private static final long serialVersionUID = 1L;
	protected String testName;
	protected String testResult;
	protected String resultDate;
	protected String labGroup;
	protected String range;
	private String sourceName;
	private String labCode;
	private String labResult;
	private String labUnit;
	private String organizerName;
	private String organizerCode;
	private String interpretationCode;
	@Column(name = "date_added")
	private String dateAdded;
	private Float normalRangeMin;
	private Float normalRangeMax;
	private Float absoluteRangeMin;
	private Float absoluteRangeMax;
	private String abnormalFlag;
	private String labGroupCode;
	private String labGroupName;
	private String labGroupDate;
	private String labGroupId;
	private Integer patientVisitId;
	private String labId;
	private String normalRange;
	

	/**
	 * @return the normalRange
	 */
	public String getNormalRange() {
		return normalRange;
	}

	/**
	 * @param normalRange the normalRange to set
	 */
	public void setNormalRange(String normalRange) {
		if(normalRange == null || normalRange.equalsIgnoreCase(""))
			this.normalRange = "N/A";
		this.normalRange = normalRange;
	}
	// lab group
	// result high or low
	// range
	private String testId;

	public Integer getPatientVisitId() {

		return patientVisitId;
	}

	public void setPatientVisitId(Integer patientVisitId) {

		this.patientVisitId = patientVisitId;
	}

	public String getLabId() {

		return labId;
	}

	public void setLabId(String labId) {

		this.labId = labId;
	}

	public String getTestId() {

		return testId;
	}

	public void setTestId(String testId) {

		this.testId = testId;
	}

	public String getTestName() {

		return testName;
	}

	public String getGroupCode() {

		return labGroupCode;
	}

	public void setGroupCode(String groupCode) {

		this.labGroupCode = groupCode;
	}

	public String getGroupName() {

		return labGroupName;
	}

	public void setGroupName(String groupName) {

		this.labGroupName = groupName;
	}

	public String getGroupDate() {

		return labGroupDate;
	}

	public void setGroupDate(String groupDate) {

		this.labGroupDate = groupDate;
	}

	public String getGroupId() {

		return labGroupId;
	}

	public void setGroupId(String groupId) {

		this.labGroupId = groupId;
	}

	public String getSourceName() {

		return sourceName;
	}

	public void setSourceName(String sourceName) {

		this.sourceName = sourceName;
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

	public String getDateAdded() {

		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {

		this.dateAdded = dateAdded;
	}

	public String getAbnormalFlag() {

		return abnormalFlag;
	}

	public void setAbnormalFlag(String abnormalFlag) {

		this.abnormalFlag = abnormalFlag;
	}

	public void setTestName(String testName) {

		this.testName = testName;
	}

	public String getTestResult() {

		return testResult;
	}

	public void setTestResult(String testResult) {

		this.testResult = testResult;
	}

	public String getResultDate() {

		return resultDate;
	}

	public void setResultDate(String resultDate) {

		this.resultDate = resultDate;
	}

	public String getLabGroup() {

		return labGroup;
	}

	public void setLabGroup(String labGroup) {

		this.labGroup = labGroup;
	}

	public String getRange() {

		return range;
	}

	public void setRange(String range) {

		this.range = range;
	}

}
