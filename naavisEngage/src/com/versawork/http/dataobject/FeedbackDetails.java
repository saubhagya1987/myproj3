package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FeedbackDetails implements Serializable {

	private final static long serialVersionUID = 1L;

	protected Integer visitRating;
	protected Integer recoveryRating;
	protected String comment;
	protected Integer accountId;
	protected Integer visitId;

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

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
}