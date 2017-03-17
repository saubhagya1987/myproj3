/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "patient_rosetta")
@NamedQueries({
		@NamedQuery(name = "PatientRosetta.findAll", query = "SELECT p FROM PatientRosetta p"),
		@NamedQuery(name = "PatientRosetta.findByClientId", query = "SELECT p FROM PatientRosetta p WHERE p.patientRosettaPK.clientId = :clientId"),
		@NamedQuery(name = "PatientRosetta.findByClientDatabaseId", query = "SELECT p FROM PatientRosetta p WHERE p.patientRosettaPK.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "PatientRosetta.findByMedicalRecordNumberClientDBId", query = "SELECT p FROM PatientRosetta p WHERE p.patientRosettaPK.medicalRecordNumber = :medicalRecordNumber and p.patientRosettaPK.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "PatientRosetta.findByMedicalRecordNumber", query = "SELECT p FROM PatientRosetta p WHERE p.patientRosettaPK.medicalRecordNumber = :medicalRecordNumber"),
		@NamedQuery(name = "PatientRosetta.findByAccountId", query = "SELECT p FROM PatientRosetta p WHERE p.accountId = :accountId"),
		@NamedQuery(name = "PatientRosetta.findByRegistered", query = "SELECT p FROM PatientRosetta p WHERE p.registered = :registered") })
public class PatientRosetta implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientRosettaPK patientRosettaPK;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "registered")
	private boolean registered;

	public PatientRosetta() {
	}

	public PatientRosetta(PatientRosettaPK patientRosettaPK) {
		this.patientRosettaPK = patientRosettaPK;
	}

	public PatientRosetta(PatientRosettaPK patientRosettaPK, int accountId, boolean registered) {
		this.patientRosettaPK = patientRosettaPK;
		this.accountId = accountId;
		this.registered = registered;
	}

	public PatientRosetta(int clientId, int clientDatabaseId, String medicalRecordNumber) {
		this.patientRosettaPK = new PatientRosettaPK(clientId, clientDatabaseId, medicalRecordNumber);
	}

	public PatientRosettaPK getPatientRosettaPK() {
		return patientRosettaPK;
	}

	public void setPatientRosettaPK(PatientRosettaPK patientRosettaPK) {
		this.patientRosettaPK = patientRosettaPK;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public boolean getRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientRosettaPK != null ? patientRosettaPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientRosetta)) {
			return false;
		}
		PatientRosetta other = (PatientRosetta) object;
		if ((this.patientRosettaPK == null && other.patientRosettaPK != null)
				|| (this.patientRosettaPK != null && !this.patientRosettaPK.equals(other.patientRosettaPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientRosetta[ patientRosettaPK=" + patientRosettaPK + " ]";
	}

}
