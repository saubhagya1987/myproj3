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
public class PatientLabPK implements Serializable {
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
	@Column(name = "test_id")
	private String testId;
	@Basic(optional = false)
	@Column(name = "lab_id")
	private String labId;
	@Basic(optional = false)
	@Column(name = "lab_group_id")
	private String labGroupId;

	public PatientLabPK() {
	}

	public PatientLabPK(int accountId, int patientVisitId, String testId, String labId, String labGroupId) {
		this.accountId = accountId;
		this.patientVisitId = patientVisitId;
		this.testId = testId;
		this.labId = labId;
		this.labGroupId = labGroupId;
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

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getLabId() {
		return labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}

	public String getLabGroupId() {
		return labGroupId;
	}

	public void setLabGroupId(String labGroupId) {
		this.labGroupId = labGroupId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (int) patientVisitId;
		hash += (testId != null ? testId.hashCode() : 0);
		hash += (labId != null ? labId.hashCode() : 0);
		hash += (labGroupId != null ? labGroupId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientLabPK)) {
			return false;
		}
		PatientLabPK other = (PatientLabPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if (this.patientVisitId != other.patientVisitId) {
			return false;
		}
		if ((this.testId == null && other.testId != null) || (this.testId != null && !this.testId.equals(other.testId))) {
			return false;
		}
		if ((this.labId == null && other.labId != null) || (this.labId != null && !this.labId.equals(other.labId))) {
			return false;
		}
		if ((this.labGroupId == null && other.labGroupId != null)
				|| (this.labGroupId != null && !this.labGroupId.equals(other.labGroupId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientLabPK[ accountId=" + accountId + ", patientVisitId=" + patientVisitId
				+ ", testId=" + testId + ", labId=" + labId + ", labGroupId=" + labGroupId + " ]";
	}

}
