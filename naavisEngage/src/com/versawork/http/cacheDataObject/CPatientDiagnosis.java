package com.versawork.http.cacheDataObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sohaib
 * 
 *         Key = PatientD_accountId_patientVisitId
 */
public class CPatientDiagnosis implements Serializable {

	private static final long serialVersionUID = 1L;
	private int accountId;
	private int patientVisitId;
	private String diagnosisCode;
	private String sourceId;
	private String sourceName;
	private String diagnosisName;
	private String status;
	private Date dateAdded;
	private Integer diagnosisSeqId;

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

	public String getDiagnosisCode() {
		return diagnosisCode;
	}

	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
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

	public String getDiagnosisName() {
		return diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Integer getDiagnosisSeqId() {
		return diagnosisSeqId;
	}

	public void setDiagnosisSeqId(Integer diagnosisSeqId) {
		this.diagnosisSeqId = diagnosisSeqId;
	}

}
