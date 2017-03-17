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
@Table(name = "patient_diagnosis")
@NamedQueries({
		@NamedQuery(name = "PatientDiagnosis.findAll", query = "SELECT p FROM PatientDiagnosis p"),
		@NamedQuery(name = "PatientDiagnosis.findByAccountId", query = "SELECT p FROM PatientDiagnosis p WHERE p.patientDiagnosisPK.accountId = :accountId"),
		@NamedQuery(name = "PatientDiagnosis.findByPatientVisitId", query = "SELECT p FROM PatientDiagnosis p WHERE p.patientDiagnosisPK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientDiagnosis.findBySourceId", query = "SELECT p FROM PatientDiagnosis p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientDiagnosis.findBySourceName", query = "SELECT p FROM PatientDiagnosis p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientDiagnosis.findByDiagnosisName", query = "SELECT p FROM PatientDiagnosis p WHERE p.diagnosisName = :diagnosisName"),
		@NamedQuery(name = "PatientDiagnosis.findByDiagnosisCode", query = "SELECT p FROM PatientDiagnosis p WHERE p.patientDiagnosisPK.diagnosisCode = :diagnosisCode"),
		@NamedQuery(name = "PatientDiagnosis.findByStatus", query = "SELECT p FROM PatientDiagnosis p WHERE p.status = :status"),
		@NamedQuery(name = "PatientDiagnosis.findByDateAdded", query = "SELECT p FROM PatientDiagnosis p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientDiagnosis.findByDiagnosisSeqId", query = "SELECT p FROM PatientDiagnosis p WHERE p.patientDiagnosisPK.diagnosisSeqId = :diagnosisSeqId"),
		@NamedQuery(name = "PatientDiagnosis.findByEtlInfoAccount", query = "SELECT p FROM PatientDiagnosis p, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND p.patientDiagnosisPK.accountId = info.etlInfoPK.accountId ORDER BY  p.patientDiagnosisPK.accountId, p.patientDiagnosisPK.patientVisitId") })
public class PatientDiagnosis implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientDiagnosisPK patientDiagnosisPK;
	@Column(name = "source_id")
	private String sourceId;
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "diagnosis_name")
	private String diagnosisName;
	@Column(name = "status")
	private String status;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	/*@Column(name = "diagnosis_seq_id")
	private Integer diagnosisSeqId;*/
	@JoinColumns({
			@JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false),
			@JoinColumn(name = "patient_visit_id", referencedColumnName = "patient_visit_id", insertable = false, updatable = false) })
	@ManyToOne(optional = false)
	private PatientVisit patientVisit;

	public PatientDiagnosis() {
	}

	public PatientDiagnosis(PatientDiagnosisPK patientDiagnosisPK) {
		this.patientDiagnosisPK = patientDiagnosisPK;
	}

	public PatientDiagnosis(PatientDiagnosisPK patientDiagnosisPK, String sourceName, String diagnosisName,
			Date dateAdded) {
		this.patientDiagnosisPK = patientDiagnosisPK;
		this.sourceName = sourceName;
		this.diagnosisName = diagnosisName;
		this.dateAdded = dateAdded;
	}

	public PatientDiagnosis(int accountId, int patientVisitId, String diagnosisCode, Integer diagnosisSeqId) {
		this.patientDiagnosisPK = new PatientDiagnosisPK(accountId, patientVisitId, diagnosisCode, diagnosisSeqId);
	}

	public PatientDiagnosisPK getPatientDiagnosisPK() {
		return patientDiagnosisPK;
	}

	public void setPatientDiagnosisPK(PatientDiagnosisPK patientDiagnosisPK) {
		this.patientDiagnosisPK = patientDiagnosisPK;
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

	/*public Integer getDiagnosisSeqId() {
		return diagnosisSeqId;
	}

	public void setDiagnosisSeqId(Integer diagnosisSeqId) {
		this.diagnosisSeqId = diagnosisSeqId;
	}*/

	public PatientVisit getPatientVisit() {
		return patientVisit;
	}

	public void setPatientVisit(PatientVisit patientVisit) {
		this.patientVisit = patientVisit;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientDiagnosisPK != null ? patientDiagnosisPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientDiagnosis)) {
			return false;
		}
		PatientDiagnosis other = (PatientDiagnosis) object;
		if ((this.patientDiagnosisPK == null && other.patientDiagnosisPK != null)
				|| (this.patientDiagnosisPK != null && !this.patientDiagnosisPK.equals(other.patientDiagnosisPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientDiagnosis[ patientDiagnosisPK=" + patientDiagnosisPK + " ]";
	}

}
