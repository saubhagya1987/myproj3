package com.versawork.http.cacheDataObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sohaib
 * 
 *         Key = PatientVital_accountId_patientVisitId
 * 
 */
public class CPatientVitalSign implements Serializable {

	private static final long serialVersionUID = 1L;
	private int accountId;
	private int patientVisitId;
	private String sourceId;
	private String sourceName;
	private String heightFeet;
	private String heightInches;
	private String weightLbs;
	private String weightUnit;
	private String systolicBp;
	private String systolicBpUnit;
	private String diastolicBp;
	private String diastolicBpUnit;
	private String bmi;
	private Date dateAdded;

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

	public String getHeight() {
		return heightFeet;
	}

	public void setHeight(String height) {
		this.heightFeet = height;
	}

	public String getHeightUnit() {
		return heightInches;
	}

	public void setHeightUnit(String heightUnit) {
		this.heightInches = heightUnit;
	}

	public String getWeight() {
		return weightLbs;
	}

	public void setWeight(String weight) {
		this.weightLbs = weight;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	public String getSystolicBp() {
		return systolicBp;
	}

	public void setSystolicBp(String systolicBp) {
		this.systolicBp = systolicBp;
	}

	public String getSystolicBpUnit() {
		return systolicBpUnit;
	}

	public void setSystolicBpUnit(String systolicBpUnit) {
		this.systolicBpUnit = systolicBpUnit;
	}

	public String getDiastolicBp() {
		return diastolicBp;
	}

	public void setDiastolicBp(String diastolicBp) {
		this.diastolicBp = diastolicBp;
	}

	public String getDiastolicBpUnit() {
		return diastolicBpUnit;
	}

	public void setDiastolicBpUnit(String diastolicBpUnit) {
		this.diastolicBpUnit = diastolicBpUnit;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

}
