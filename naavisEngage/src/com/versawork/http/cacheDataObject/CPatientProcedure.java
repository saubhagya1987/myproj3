package com.versawork.http.cacheDataObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sohaib
 * 
 *         Key = PatientProc_accountId_patientVisitId
 */
public class CPatientProcedure implements Serializable {

	private static final long serialVersionUID = 1L;
	private int accountId;
	private String procedureId;
	private String procedureId2;
	private int patientVisitId;
	private String sourceId;
	private String sourceName;
	private String procedureName;
	private String procedureCode;
	private Date dateAdded;
	private Date procedureDate;

	public String getProcedureId2() {
		return procedureId2;
	}

	public void setProcedureId2(String procedureId2) {
		this.procedureId2 = procedureId2;
	}

	public Date getProcedureDate() {
		return procedureDate;
	}

	public void setProcedureDate(Date procedureDate) {
		this.procedureDate = procedureDate;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
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

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getProcedureCode() {
		return procedureCode;
	}

	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

}
