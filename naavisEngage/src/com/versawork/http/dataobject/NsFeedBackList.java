package com.versawork.http.dataobject;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsFeedBackList {

	private Integer visitRating;
	protected Integer recoveryRating;
	protected String comment;
	private Integer patientVisitId;

	public Integer getVisitRating() {
		return visitRating;
	}

	public void setVisitRating(Integer visitRating) {
		this.visitRating = visitRating;
	}

	public Integer getRecoveryRating() {
		return recoveryRating;
	}

	public void setRecoveryRating(Integer recoveryRating) {
		this.recoveryRating = recoveryRating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getPatientVisitId() {
		return patientVisitId;
	}

	public void setPatientVisitId(Integer patientVisitId) {
		this.patientVisitId = patientVisitId;
	}

}
