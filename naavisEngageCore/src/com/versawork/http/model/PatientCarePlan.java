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
@Table(name = "patient_care_plan")
@NamedQueries({
		@NamedQuery(name = "PatientCarePlan.findAll", query = "SELECT p FROM PatientCarePlan p"),
		@NamedQuery(name = "PatientCarePlan.findByAccountId", query = "SELECT p FROM PatientCarePlan p WHERE p.patientCarePlanPK.accountId = :accountId"),
		@NamedQuery(name = "PatientCarePlan.findByPatientVisitId", query = "SELECT p FROM PatientCarePlan p WHERE p.patientCarePlanPK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientCarePlan.findBySourceId", query = "SELECT p FROM PatientCarePlan p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientCarePlan.findBySourceName", query = "SELECT p FROM PatientCarePlan p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientCarePlan.findByCarePlanId", query = "SELECT p FROM PatientCarePlan p WHERE p.patientCarePlanPK.carePlanId = :carePlanId"),
		@NamedQuery(name = "PatientCarePlan.findByGoal", query = "SELECT p FROM PatientCarePlan p WHERE p.goal = :goal"),
		@NamedQuery(name = "PatientCarePlan.findByInstructions", query = "SELECT p FROM PatientCarePlan p WHERE p.instructions = :instructions"),
		@NamedQuery(name = "PatientCarePlan.findByDateAdded", query = "SELECT p FROM PatientCarePlan p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientCarePlan.findByEtlInfoAccount", query = "SELECT p FROM PatientCarePlan p, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND p.patientCarePlanPK.accountId = info.etlInfoPK.accountId ORDER BY  p.patientCarePlanPK.accountId, p.patientCarePlanPK.patientVisitId") })
public class PatientCarePlan implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientCarePlanPK patientCarePlanPK;
	@Column(name = "source_id")
	private String sourceId;
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "goal")
	private String goal;
	@Basic(optional = false)
	@Column(name = "instructions")
	private String instructions;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@JoinColumns({
			@JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false),
			@JoinColumn(name = "patient_visit_id", referencedColumnName = "patient_visit_id", insertable = false, updatable = false) })
	@ManyToOne(optional = false)
	private PatientVisit patientVisit;

	public PatientCarePlan() {
	}

	public PatientCarePlan(PatientCarePlanPK patientCarePlanPK) {
		this.patientCarePlanPK = patientCarePlanPK;
	}

	public PatientCarePlan(PatientCarePlanPK patientCarePlanPK, String goal, String instructions, Date dateAdded) {
		this.patientCarePlanPK = patientCarePlanPK;
		this.goal = goal;
		this.instructions = instructions;
		this.dateAdded = dateAdded;
	}

	public PatientCarePlan(int accountId, int patientVisitId, String carePlanId) {
		this.patientCarePlanPK = new PatientCarePlanPK(accountId, patientVisitId, carePlanId);
	}

	public PatientCarePlanPK getPatientCarePlanPK() {
		return patientCarePlanPK;
	}

	public void setPatientCarePlanPK(PatientCarePlanPK patientCarePlanPK) {
		this.patientCarePlanPK = patientCarePlanPK;
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

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
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
		hash += (patientCarePlanPK != null ? patientCarePlanPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientCarePlan)) {
			return false;
		}
		PatientCarePlan other = (PatientCarePlan) object;
		if ((this.patientCarePlanPK == null && other.patientCarePlanPK != null)
				|| (this.patientCarePlanPK != null && !this.patientCarePlanPK.equals(other.patientCarePlanPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientCarePlan[ patientCarePlanPK=" + patientCarePlanPK + " ]";
	}

}
