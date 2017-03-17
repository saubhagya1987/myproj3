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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "patient_feedback")
@NamedQueries({
		@NamedQuery(name = "PatientFeedback.findAll", query = "SELECT p FROM PatientFeedback p"),

		@NamedQuery(name = "PatientFeedback.findByClientId", query = "SELECT p FROM PatientFeedback p WHERE p.clientId = :clientId"),
		@NamedQuery(name = "PatientFeedback.findByClientDatabaseId", query = "SELECT p FROM PatientFeedback p WHERE p.clientDatabaseId = :clientDatabaseId"),

		@NamedQuery(name = "PatientFeedback.findByPatientFeedbackId", query = "SELECT p FROM PatientFeedback p WHERE p.patientFeedbackId = :patientFeedbackId"),
		@NamedQuery(name = "PatientFeedback.findByPatientVisitId", query = "SELECT p FROM PatientFeedback p WHERE p.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientFeedback.findByVisitRating", query = "SELECT p FROM PatientFeedback p WHERE p.visitRating = :visitRating"),
		@NamedQuery(name = "PatientFeedback.findByRecoveryRating", query = "SELECT p FROM PatientFeedback p WHERE p.recoveryRating = :recoveryRating"),
		@NamedQuery(name = "PatientFeedback.findByComment", query = "SELECT p FROM PatientFeedback p WHERE p.comment = :comment"),
		@NamedQuery(name = "PatientFeedback.findByLastViewedDate", query = "SELECT p FROM PatientFeedback p WHERE p.lastViewedDate = :lastViewedDate"),
		@NamedQuery(name = "PatientFeedback.findByLastViewedByAcountId", query = "SELECT p FROM PatientFeedback p WHERE p.lastViewedByAcountId = :lastViewedByAcountId"),
		@NamedQuery(name = "PatientFeedback.findByDateAdded", query = "SELECT p FROM PatientFeedback p WHERE p.dateAdded = :dateAdded") })
public class PatientFeedback implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patient_feedback_id")
	private Integer patientFeedbackId;
	@Basic(optional = false)
	@Column(name = "visit_rating")
	private int visitRating;
	@Basic(optional = false)
	@Column(name = "client_id")
	private int clientId;

	@Basic(optional = false)
	@Column(name = "client_database_id")
	private int clientDatabaseId;

	@Basic(optional = false)
	@Column(name = "recovery_rating")
	private int recoveryRating;
	@Column(name = "comment")
	private String comment;
	@Column(name = "last_viewed_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastViewedDate;
	@Column(name = "last_viewed_by_acount_id")
	private Integer lastViewedByAcountId;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "patient_visit_id")
	private Integer patientVisitId;

	public PatientFeedback() {
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public PatientFeedback(Integer patientFeedbackId) {
		this.patientFeedbackId = patientFeedbackId;
	}

	public PatientFeedback(Integer patientFeedbackId, int clientDatabaseId, int visitRating, int recoveryRating,
			Date dateAdded) {
		this.patientFeedbackId = patientFeedbackId;
		this.clientDatabaseId = clientDatabaseId;
		this.visitRating = visitRating;
		this.recoveryRating = recoveryRating;
		this.dateAdded = dateAdded;
	}

	public Integer getPatientFeedbackId() {
		return patientFeedbackId;
	}

	public void setPatientFeedbackId(Integer patientFeedbackId) {
		this.patientFeedbackId = patientFeedbackId;
	}

	public int getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(int clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	public int getVisitRating() {
		return visitRating;
	}

	public void setVisitRating(int visitRating) {
		this.visitRating = visitRating;
	}

	public int getRecoveryRating() {
		return recoveryRating;
	}

	public void setRecoveryRating(int recoveryRating) {
		this.recoveryRating = recoveryRating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getLastViewedDate() {
		return lastViewedDate;
	}

	public void setLastViewedDate(Date lastViewedDate) {
		this.lastViewedDate = lastViewedDate;
	}

	public Integer getLastViewedByAcountId() {
		return lastViewedByAcountId;
	}

	public void setLastViewedByAcountId(Integer lastViewedByAcountId) {
		this.lastViewedByAcountId = lastViewedByAcountId;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Integer getPatientVisitId() {
		return patientVisitId;
	}

	public void setPatientVisitId(Integer patientVisitId) {
		this.patientVisitId = patientVisitId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientFeedbackId != null ? patientFeedbackId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientFeedback)) {
			return false;
		}
		PatientFeedback other = (PatientFeedback) object;
		if ((this.patientFeedbackId == null && other.patientFeedbackId != null)
				|| (this.patientFeedbackId != null && !this.patientFeedbackId.equals(other.patientFeedbackId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientFeedback[ patientFeedbackId=" + patientFeedbackId + " ]";
	}

}
