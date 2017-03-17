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
@Table(name = "patient_visit_inpatient")
@NamedQueries({
		@NamedQuery(name = "PatientVisitInpatient.findAll", query = "SELECT p FROM PatientVisitInpatient p"),
		@NamedQuery(name = "PatientVisitInpatient.findByAccountId", query = "SELECT p FROM PatientVisitInpatient p WHERE p.patientVisitInpatientPK.accountId = :accountId"),
		@NamedQuery(name = "PatientVisitInpatient.findByPatientVisitId", query = "SELECT p FROM PatientVisitInpatient p WHERE p.patientVisitInpatientPK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientVisitInpatient.findBySourceId", query = "SELECT p FROM PatientVisitInpatient p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientVisitInpatient.findBySourceName", query = "SELECT p FROM PatientVisitInpatient p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientVisitInpatient.findByAdmitLocation", query = "SELECT p FROM PatientVisitInpatient p WHERE p.admitLocation = :admitLocation"),
		@NamedQuery(name = "PatientVisitInpatient.findByDischargeLocation", query = "SELECT p FROM PatientVisitInpatient p WHERE p.dischargeLocation = :dischargeLocation"),
		@NamedQuery(name = "PatientVisitInpatient.findByReasonForHospitalization", query = "SELECT p FROM PatientVisitInpatient p WHERE p.reasonForHospitalization = :reasonForHospitalization"),
		@NamedQuery(name = "PatientVisitInpatient.findByDischargeInstruction", query = "SELECT p FROM PatientVisitInpatient p WHERE p.dischargeInstruction = :dischargeInstruction"),
		@NamedQuery(name = "PatientVisitInpatient.findByDateAdded", query = "SELECT p FROM PatientVisitInpatient p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientVisitInpatient.findByEtlInfoAccount", query = "SELECT p FROM PatientVisitInpatient p, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND p.patientVisitInpatientPK.accountId = info.etlInfoPK.accountId ORDER BY  p.patientVisitInpatientPK.accountId, p.patientVisitInpatientPK.patientVisitId") })
public class PatientVisitInpatient implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientVisitInpatientPK patientVisitInpatientPK;
	@Column(name = "source_id")
	private String sourceId;
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	@Column(name = "admit_location")
	private String admitLocation;
	@Column(name = "discharge_location")
	private String dischargeLocation;
	@Column(name = "reason_for_hospitalization")
	private String reasonForHospitalization;
	@Column(name = "discharge_instruction")
	private String dischargeInstruction;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@JoinColumns({
			@JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false),
			@JoinColumn(name = "patient_visit_id", referencedColumnName = "patient_visit_id", insertable = false, updatable = false) })
	@ManyToOne(optional = false)
	private PatientVisit patientVisit;

	public PatientVisitInpatient() {
	}

	public PatientVisitInpatient(PatientVisitInpatientPK patientVisitInpatientPK) {
		this.patientVisitInpatientPK = patientVisitInpatientPK;
	}

	public PatientVisitInpatient(PatientVisitInpatientPK patientVisitInpatientPK, String sourceName, Date dateAdded) {
		this.patientVisitInpatientPK = patientVisitInpatientPK;
		this.sourceName = sourceName;
		this.dateAdded = dateAdded;
	}

	public PatientVisitInpatient(int accountId, int patientVisitId) {
		this.patientVisitInpatientPK = new PatientVisitInpatientPK(accountId, patientVisitId);
	}

	public PatientVisitInpatientPK getPatientVisitInpatientPK() {
		return patientVisitInpatientPK;
	}

	public void setPatientVisitInpatientPK(PatientVisitInpatientPK patientVisitInpatientPK) {
		this.patientVisitInpatientPK = patientVisitInpatientPK;
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

	public PatientVisit getPatientVisit() {
		return patientVisit;
	}

	public void setPatientVisit(PatientVisit patientVisit) {
		this.patientVisit = patientVisit;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientVisitInpatientPK != null ? patientVisitInpatientPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientVisitInpatient)) {
			return false;
		}
		PatientVisitInpatient other = (PatientVisitInpatient) object;
		if ((this.patientVisitInpatientPK == null && other.patientVisitInpatientPK != null)
				|| (this.patientVisitInpatientPK != null && !this.patientVisitInpatientPK
						.equals(other.patientVisitInpatientPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientVisitInpatient[ patientVisitInpatientPK=" + patientVisitInpatientPK
				+ " ]";
	}

}
