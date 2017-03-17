/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "patient_functional_status")
@NamedQueries({
		@NamedQuery(name = "PatientFunctionalStatus.findAll", query = "SELECT p FROM PatientFunctionalStatus p"),
		@NamedQuery(name = "PatientFunctionalStatus.findByAccountId", query = "SELECT p FROM PatientFunctionalStatus p WHERE p.patientFunctionalStatusPK.accountId = :accountId"),
		@NamedQuery(name = "PatientFunctionalStatus.findByPatientVisitId", query = "SELECT p FROM PatientFunctionalStatus p WHERE p.patientFunctionalStatusPK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientFunctionalStatus.findByFunctionId", query = "SELECT p FROM PatientFunctionalStatus p WHERE p.patientFunctionalStatusPK.functionId = :functionId"),
		@NamedQuery(name = "PatientFunctionalStatus.findBySourceId", query = "SELECT p FROM PatientFunctionalStatus p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientFunctionalStatus.findBySourceName", query = "SELECT p FROM PatientFunctionalStatus p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientFunctionalStatus.findByFunctionDescription", query = "SELECT p FROM PatientFunctionalStatus p WHERE p.functionDescription = :functionDescription"),
		@NamedQuery(name = "PatientFunctionalStatus.findByFunctionCode", query = "SELECT p FROM PatientFunctionalStatus p WHERE p.functionCode = :functionCode"),
		@NamedQuery(name = "PatientFunctionalStatus.findByStatus", query = "SELECT p FROM PatientFunctionalStatus p WHERE p.status = :status"),
		@NamedQuery(name = "PatientFunctionalStatus.findByStatusCode", query = "SELECT p FROM PatientFunctionalStatus p WHERE p.statusCode = :statusCode"),
		@NamedQuery(name = "PatientFunctionalStatus.findByDateAdded", query = "SELECT p FROM PatientFunctionalStatus p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientFunctionalStatus.findByEtlInfoAccount", query = "SELECT p FROM PatientFunctionalStatus p, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND p.patientFunctionalStatusPK.accountId = info.etlInfoPK.accountId ORDER BY  p.patientFunctionalStatusPK.accountId, p.patientFunctionalStatusPK.patientVisitId") })
public class PatientFunctionalStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientFunctionalStatusPK patientFunctionalStatusPK;
	@Column(name = "source_id")
	private String sourceId;
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "function_description")
	private String functionDescription;
	@Column(name = "function_code")
	private String functionCode;
	@Column(name = "status")
	private String status;
	@Column(name = "status_code")
	private String statusCode;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Basic(optional = true)
	@Column(name = "status_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date statusDate;
	@JoinColumns({
			@JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false),
			@JoinColumn(name = "patient_visit_id", referencedColumnName = "patient_visit_id", insertable = false, updatable = false) })
	@ManyToOne(optional = false)
	private PatientVisit patientVisit;

	public PatientFunctionalStatus() {
	}

	public PatientFunctionalStatus(PatientFunctionalStatusPK patientFunctionalStatusPK) {
		this.patientFunctionalStatusPK = patientFunctionalStatusPK;
	}

	public PatientFunctionalStatus(PatientFunctionalStatusPK patientFunctionalStatusPK, String sourceName,
			String functionDescription, Date dateAdded) {
		this.patientFunctionalStatusPK = patientFunctionalStatusPK;
		this.sourceName = sourceName;
		this.functionDescription = functionDescription;
		this.dateAdded = dateAdded;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public PatientFunctionalStatus(int accountId, int patientVisitId, String functionId, String functionId2) {
		this.patientFunctionalStatusPK = new PatientFunctionalStatusPK(accountId, patientVisitId, functionId,
				functionId2);
	}

	public PatientFunctionalStatusPK getPatientFunctionalStatusPK() {
		return patientFunctionalStatusPK;
	}

	public void setPatientFunctionalStatusPK(PatientFunctionalStatusPK patientFunctionalStatusPK) {
		this.patientFunctionalStatusPK = patientFunctionalStatusPK;
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

	public String getFunctionDescription() {
		return functionDescription;
	}

	public void setFunctionDescription(String functionDescription) {
		this.functionDescription = functionDescription;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public PatientVisit getPatientVisit() {
		return patientVisit;
	}

	public void setPatientVisit(PatientVisit patientVisit) {
		this.patientVisit = patientVisit;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientFunctionalStatusPK != null ? patientFunctionalStatusPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientFunctionalStatus)) {
			return false;
		}
		PatientFunctionalStatus other = (PatientFunctionalStatus) object;
		if ((this.patientFunctionalStatusPK == null && other.patientFunctionalStatusPK != null)
				|| (this.patientFunctionalStatusPK != null && !this.patientFunctionalStatusPK
						.equals(other.patientFunctionalStatusPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.traceOn.http.model.PatientFunctionalStatus[ patientFunctionalStatusPK=" + patientFunctionalStatusPK
				+ " ]";
	}

}
