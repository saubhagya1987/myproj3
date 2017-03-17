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
public class PatientCarePlanPK implements Serializable {
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
	@Column(name = "care_plan_id")
	private String carePlanId;

	public PatientCarePlanPK() {
	}

	public PatientCarePlanPK(int accountId, int patientVisitId, String carePlanId) {
		this.accountId = accountId;
		this.patientVisitId = patientVisitId;
		this.carePlanId = carePlanId;
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

	public String getCarePlanId() {
		return carePlanId;
	}

	public void setCarePlanId(String carePlanId) {
		this.carePlanId = carePlanId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (int) patientVisitId;
		hash += (carePlanId != null ? carePlanId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientCarePlanPK)) {
			return false;
		}
		PatientCarePlanPK other = (PatientCarePlanPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if (this.patientVisitId != other.patientVisitId) {
			return false;
		}
		if ((this.carePlanId == null && other.carePlanId != null)
				|| (this.carePlanId != null && !this.carePlanId.equals(other.carePlanId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientCarePlanPK[ accountId=" + accountId + ", patientVisitId="
				+ patientVisitId + ", carePlanId=" + carePlanId + " ]";
	}

}
