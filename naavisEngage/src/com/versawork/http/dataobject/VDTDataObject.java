package com.versawork.http.dataobject;

import java.math.BigDecimal;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class VDTDataObject {

	private Integer id;
	private Integer patientId;
	private String lastName;
	private String firstName;
	private String dateOfBirth;
	private Integer age;
	private String sex;
	private String encounterDate;
	private Integer populateDenominator;
	private Integer populateNumerator;
	private Integer numeratorRecorded;

	private Integer stage;
	private String measureType;
	private Integer measureNumber;
	private Integer measureSubNumber;
	private String measure;
	private String measureDesc;
	private Integer measureGoal;
	private Integer measureGoal2;
	private BigDecimal percentage;
	private Integer percentageInt;
	private Integer isStg1GoalMet;
	private Integer isStg2GoalMet;

	private Integer reportTypeId;
	private String reportingPeriodBeginDate;
	private String reportingPeriodEndDate;
	private String eventDate;

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(String encounterDate) {
		this.encounterDate = encounterDate;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public Integer getReportTypeId() {
		return reportTypeId;
	}

	public void setReportTypeId(Integer reportTypeId) {
		this.reportTypeId = reportTypeId;
	}

	public String getReportingPeriodBeginDate() {
		return reportingPeriodBeginDate;
	}

	public void setReportingPeriodBeginDate(String reportingPeriodBeginDate) {
		this.reportingPeriodBeginDate = reportingPeriodBeginDate;
	}

	public String getReportingPeriodEndDate() {
		return reportingPeriodEndDate;
	}

	public void setReportingPeriodEndDate(String reportingPeriodEndDate) {
		this.reportingPeriodEndDate = reportingPeriodEndDate;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public String getMeasureType() {
		return measureType;
	}

	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}

	public Integer getMeasureNumber() {
		return measureNumber;
	}

	public void setMeasureNumber(Integer measureNumber) {
		this.measureNumber = measureNumber;
	}

	public Integer getMeasureSubNumber() {
		return measureSubNumber;
	}

	public void setMeasureSubNumber(Integer measureSubNumber) {
		this.measureSubNumber = measureSubNumber;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getMeasureDesc() {
		return measureDesc;
	}

	public void setMeasureDesc(String measureDesc) {
		this.measureDesc = measureDesc;
	}

	public Integer getMeasureGoal() {
		return measureGoal;
	}

	public void setMeasureGoal(Integer measureGoal) {
		this.measureGoal = measureGoal;
	}

	public Integer getMeasureGoal2() {
		return measureGoal2;
	}

	public void setMeasureGoal2(Integer measureGoal2) {
		this.measureGoal2 = measureGoal2;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public Integer getPercentageInt() {
		return percentageInt;
	}

	public void setPercentageInt(Integer percentageInt) {
		this.percentageInt = percentageInt;
	}

	public Integer getIsStg1GoalMet() {
		return isStg1GoalMet;
	}

	public void setIsStg1GoalMet(Integer isStg1GoalMet) {
		this.isStg1GoalMet = isStg1GoalMet;
	}

	public Integer getIsStg2GoalMet() {
		return isStg2GoalMet;
	}

	public void setIsStg2GoalMet(Integer isStg2GoalMet) {
		this.isStg2GoalMet = isStg2GoalMet;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getPopulateDenominator() {
		return populateDenominator;
	}

	public void setPopulateDenominator(Integer populateDenominator) {
		this.populateDenominator = populateDenominator;
	}

	public Integer getPopulateNumerator() {
		return populateNumerator;
	}

	public void setPopulateNumerator(Integer populateNumerator) {
		this.populateNumerator = populateNumerator;
	}

	public Integer getNumeratorRecorded() {
		return numeratorRecorded;
	}

	public void setNumeratorRecorded(Integer numeratorRecorded) {
		this.numeratorRecorded = numeratorRecorded;
	}

}
