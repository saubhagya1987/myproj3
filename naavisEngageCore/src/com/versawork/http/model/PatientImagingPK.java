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
public class PatientImagingPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "patient_visit_id")
	private int patientVisitId;
	@Basic(optional = false)
	@Column(name = "exam_id")
	private String examId;

	public PatientImagingPK() {
	}

	public PatientImagingPK(int accountId, int patientVisitId, String examId) {
		this.accountId = accountId;
		this.patientVisitId = patientVisitId;
		this.examId = examId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getPatientVisitId() {
		return patientVisitId;
	}

	public void setPatientVisitId(int patientVisitId) {
		this.patientVisitId = patientVisitId;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (int) patientVisitId;
		hash += (examId != null ? examId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientImagingPK)) {
			return false;
		}
		PatientImagingPK other = (PatientImagingPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if (this.patientVisitId != other.patientVisitId) {
			return false;
		}
		if ((this.examId == null && other.examId != null) || (this.examId != null && !this.examId.equals(other.examId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientImagingPK[ accountId=" + accountId + ", patientVisitId="
				+ patientVisitId + ", examId=" + examId + " ]";
	}

}
