package com.versawork.http.cacheDataObject;

import java.io.Serializable;
import java.util.Date;

import com.versawork.http.model.PatientVisit;

public class CPatientFeedback implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer patientFeedbackId;
	private int visitRating;
	private int recoveryRating;
	private String comment;
	private Date lastViewedDate;
	private Integer lastViewedByAcountId;
	private Date dateAdded;
	private PatientVisit patientVisitId;

	public Integer getPatientFeedbackId() {
		return patientFeedbackId;
	}

	public void setPatientFeedbackId(Integer patientFeedbackId) {
		this.patientFeedbackId = patientFeedbackId;
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

	public PatientVisit getPatientVisitId() {
		return patientVisitId;
	}

	public void setPatientVisitId(PatientVisit patientVisitId) {
		this.patientVisitId = patientVisitId;
	}

}
