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
@Table(name = "patient_procedure")
@NamedQueries({
		@NamedQuery(name = "PatientProcedure.findAll", query = "SELECT p FROM PatientProcedure p"),
		@NamedQuery(name = "PatientProcedure.findByAccountId", query = "SELECT p FROM PatientProcedure p WHERE p.patientProcedurePK.accountId = :accountId"),
		@NamedQuery(name = "PatientProcedure.findByProcedureId", query = "SELECT p FROM PatientProcedure p WHERE p.patientProcedurePK.procedureId = :procedureId"),
		@NamedQuery(name = "PatientProcedure.findByPatientVisitId", query = "SELECT p FROM PatientProcedure p WHERE p.patientProcedurePK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientProcedure.findBySourceId", query = "SELECT p FROM PatientProcedure p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientProcedure.findBySourceName", query = "SELECT p FROM PatientProcedure p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientProcedure.findByProcedureName", query = "SELECT p FROM PatientProcedure p WHERE p.procedureName = :procedureName"),
		@NamedQuery(name = "PatientProcedure.findByProcedureCode", query = "SELECT p FROM PatientProcedure p WHERE p.procedureCode = :procedureCode"),
		@NamedQuery(name = "PatientProcedure.findByDateAdded", query = "SELECT p FROM PatientProcedure p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientProcedure.findByEtlInfoAccount", query = "SELECT p FROM PatientProcedure p, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND p.patientProcedurePK.accountId = info.etlInfoPK.accountId ORDER BY  p.patientProcedurePK.accountId, p.patientProcedurePK.patientVisitId") })
public class PatientProcedure implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientProcedurePK patientProcedurePK;
	@Column(name = "source_id")
	private String sourceId;
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "procedure_name")
	private String procedureName;
	@Column(name = "procedure_code")
	private String procedureCode;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "procedure_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date procedureDate;
	@JoinColumns({
			@JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false),
			@JoinColumn(name = "patient_visit_id", referencedColumnName = "patient_visit_id", insertable = false, updatable = false) })
	@ManyToOne(optional = false)
	private PatientVisit patientVisit;

	public PatientProcedure() {
	}

	public PatientProcedure(PatientProcedurePK patientProcedurePK) {
		this.patientProcedurePK = patientProcedurePK;
	}

	public PatientProcedure(PatientProcedurePK patientProcedurePK, String procedureName, Date dateAdded) {
		this.patientProcedurePK = patientProcedurePK;
		this.procedureName = procedureName;
		this.dateAdded = dateAdded;
	}

	public PatientProcedure(int accountId, String procedureId, int patientVisitId, String procedureId2) {
		this.patientProcedurePK = new PatientProcedurePK(accountId, procedureId, patientVisitId, procedureId2);
	}

	public PatientProcedurePK getPatientProcedurePK() {
		return patientProcedurePK;
	}

	public void setPatientProcedurePK(PatientProcedurePK patientProcedurePK) {
		this.patientProcedurePK = patientProcedurePK;
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

	public Date getProcedureDate() {
		return procedureDate;
	}

	public void setProcedureDate(Date procedureDate) {
		this.procedureDate = procedureDate;
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
		hash += (patientProcedurePK != null ? patientProcedurePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientProcedure)) {
			return false;
		}
		PatientProcedure other = (PatientProcedure) object;
		if ((this.patientProcedurePK == null && other.patientProcedurePK != null)
				|| (this.patientProcedurePK != null && !this.patientProcedurePK.equals(other.patientProcedurePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientProcedure[ patientProcedurePK=" + patientProcedurePK + " ]";
	}

}
