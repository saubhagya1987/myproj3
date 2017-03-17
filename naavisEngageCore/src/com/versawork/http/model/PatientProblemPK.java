/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Sohaib
 */
@Embeddable
public class PatientProblemPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "problem_id")
	private String problemId;

	public PatientProblemPK() {
	}

	public PatientProblemPK(int accountId, String problemId) {
		this.accountId = accountId;
		this.problemId = problemId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getProblemId() {
		return problemId;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (problemId != null ? problemId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientProblemPK)) {
			return false;
		}
		PatientProblemPK other = (PatientProblemPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if ((this.problemId == null && other.problemId != null)
				|| (this.problemId != null && !this.problemId.equals(other.problemId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientProblemPK[ accountId=" + accountId + ", problemId=" + problemId + " ]";
	}

}
