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
public class PatientPrescriptionPK implements Serializable {
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
	@Column(name = "medication_id")
	private String medicationId;

	public PatientPrescriptionPK() {
	}

	public PatientPrescriptionPK(int accountId, int patientVisitId, String medicationId) {
		this.accountId = accountId;
		this.patientVisitId = patientVisitId;
		this.medicationId = medicationId;
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

	public String getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(String medicationId) {
		this.medicationId = medicationId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (int) patientVisitId;
		hash += (medicationId != null ? medicationId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientPrescriptionPK)) {
			return false;
		}
		PatientPrescriptionPK other = (PatientPrescriptionPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if (this.patientVisitId != other.patientVisitId) {
			return false;
		}
		if ((this.medicationId == null && other.medicationId != null)
				|| (this.medicationId != null && !this.medicationId.equals(other.medicationId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientPrescriptionPK[ accountId=" + accountId + ", patientVisitId="
				+ patientVisitId + ", medicationId=" + medicationId + " ]";
	}

}
