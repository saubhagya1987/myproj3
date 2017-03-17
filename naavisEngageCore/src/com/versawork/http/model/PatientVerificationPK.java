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
public class PatientVerificationPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "client_id")
	private int clientId;
	@Basic(optional = false)
	@Column(name = "client_database_id")
	private int clientDatabaseId;
	@Basic(optional = false)
	@Column(name = "medical_record_number")
	private String medicalRecordNumber;

	public PatientVerificationPK() {
	}

	public PatientVerificationPK(int clientId, int clientDatabaseId, String medicalRecordNumber) {
		this.clientId = clientId;
		this.clientDatabaseId = clientDatabaseId;
		this.medicalRecordNumber = medicalRecordNumber;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(int clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	public String getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) clientId;
		hash += (int) clientDatabaseId;
		hash += (medicalRecordNumber != null ? medicalRecordNumber.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientVerificationPK)) {
			return false;
		}
		PatientVerificationPK other = (PatientVerificationPK) object;
		if (this.clientId != other.clientId) {
			return false;
		}
		if (this.clientDatabaseId != other.clientDatabaseId) {
			return false;
		}
		if ((this.medicalRecordNumber == null && other.medicalRecordNumber != null)
				|| (this.medicalRecordNumber != null && !this.medicalRecordNumber.equals(other.medicalRecordNumber))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientVerificationPK[ clientId=" + clientId + ", clientDatabaseId="
				+ clientDatabaseId + ", medicalRecordNumber=" + medicalRecordNumber + " ]";
	}

}
