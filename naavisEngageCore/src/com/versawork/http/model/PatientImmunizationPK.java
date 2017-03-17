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
public class PatientImmunizationPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "immunization_id")
	private String immunizationId;
	@Basic(optional = false)
	@Column(name = "patient_visit_id")
	private int patientVisitId;

	public PatientImmunizationPK() {
	}

	public PatientImmunizationPK(int accountId, String immunizationId, int patientVisitId) {
		this.accountId = accountId;
		this.immunizationId = immunizationId;
		this.patientVisitId = patientVisitId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getImmunizationId() {
		return immunizationId;
	}

	public void setImmunizationId(String immunizationId) {
		this.immunizationId = immunizationId;
	}

	public int getPatientVisitId() {
		return patientVisitId;
	}

	public void setPatientVisitId(int patientVisitId) {
		this.patientVisitId = patientVisitId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (immunizationId != null ? immunizationId.hashCode() : 0);
		hash += (int) patientVisitId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientImmunizationPK)) {
			return false;
		}
		PatientImmunizationPK other = (PatientImmunizationPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if ((this.immunizationId == null && other.immunizationId != null)
				|| (this.immunizationId != null && !this.immunizationId.equals(other.immunizationId))) {
			return false;
		}
		if (this.patientVisitId != other.patientVisitId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientImmunizationPK[ accountId=" + accountId + ", immunizationId="
				+ immunizationId + ", patientVisitId=" + patientVisitId + " ]";
	}

}
