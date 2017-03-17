package com.versawork.http.cacheDataObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sohaib
 * 
 *         Key = PatientI_accountId_patientVisitId
 */
public class CPatientImmunization implements Serializable {

	private static final long serialVersionUID = 1L;
	private int accountId;
	private String immunizationId;
	private int patientVisitId;
	private String sourceId;
	private String sourceName;
	private String immunizationName;
	private String immunizationCode;
	private String status;
	private String routeCode;
	private String routeName;
	private Date dateAdded;
	private Date statusDate;

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getImmunizationId() {
		return immunizationId;
	}

	public void setImmunizationId(String immunizationId) {
		this.immunizationId = immunizationId;
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

	public String getImmunizationName() {
		return immunizationName;
	}

	public void setImmunizationName(String immunizationName) {
		this.immunizationName = immunizationName;
	}

	public String getImmunizationCode() {
		return immunizationCode;
	}

	public void setImmunizationCode(String immunizationCode) {
		this.immunizationCode = immunizationCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

}
