package com.versawork.http.cacheDataObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sohaib
 * 
 *         Key = PatientVIn_accountId_patientVisitId
 * 
 */
public class CPatientVisitInpatient implements Serializable {

	private static final long serialVersionUID = 1L;
	private int accountId;
	private int patientVisitId;
	private String sourceId;
	private String sourceName;
	private String admitLocation;
	private String dischargeLocation;
	private String reasonForHospitalization;
	private String dischargeInstruction;
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

	public String getAdmitLocation() {
		return admitLocation;
	}

	public void setAdmitLocation(String admitLocation) {
		this.admitLocation = admitLocation;
	}

	public String getDischargeLocation() {
		return dischargeLocation;
	}

	public void setDischargeLocation(String dischargeLocation) {
		this.dischargeLocation = dischargeLocation;
	}

	public String getReasonForHospitalization() {
		return reasonForHospitalization;
	}

	public void setReasonForHospitalization(String reasonForHospitalization) {
		this.reasonForHospitalization = reasonForHospitalization;
	}

	public String getDischargeInstruction() {
		return dischargeInstruction;
	}

	public void setDischargeInstruction(String dischargeInstruction) {
		this.dischargeInstruction = dischargeInstruction;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

}
