/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "patient_verification_log")
@NamedQueries({
		@NamedQuery(name = "PatientVerificationLog.findAll", query = "SELECT p FROM PatientVerificationLog p"),
		@NamedQuery(name = "PatientVerificationLog.findByPatientVerificationLogId", query = "SELECT p FROM PatientVerificationLog p WHERE p.patientVerificationLogId = :patientVerificationLogId"),
		@NamedQuery(name = "PatientVerificationLog.findByClientId", query = "SELECT p FROM PatientVerificationLog p WHERE p.clientId = :clientId"),
		@NamedQuery(name = "PatientVerificationLog.findByClientDatabaseId", query = "SELECT p FROM PatientVerificationLog p WHERE p.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "PatientVerificationLog.findByMedicalRecordNumber", query = "SELECT p FROM PatientVerificationLog p WHERE p.medicalRecordNumber = :medicalRecordNumber"),
		@NamedQuery(name = "PatientVerificationLog.findByBirthDate", query = "SELECT p FROM PatientVerificationLog p WHERE p.birthDate = :birthDate"),
		@NamedQuery(name = "PatientVerificationLog.findByPhoneNumber", query = "SELECT p FROM PatientVerificationLog p WHERE p.phoneNumber = :phoneNumber"),
		@NamedQuery(name = "PatientVerificationLog.findByEmailAddress", query = "SELECT p FROM PatientVerificationLog p WHERE p.emailAddress = :emailAddress"),
		@NamedQuery(name = "PatientVerificationLog.findByDateAdded", query = "SELECT p FROM PatientVerificationLog p WHERE p.dateAdded = :dateAdded") })
public class PatientVerificationLog implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "patient_verification_log_id")
	private Integer patientVerificationLogId;
	@Basic(optional = false)
	@Column(name = "client_id")
	private int clientId;
	@Basic(optional = false)
	@Column(name = "client_database_id")
	private int clientDatabaseId;
	@Basic(optional = false)
	@Column(name = "medical_record_number")
	private String medicalRecordNumber;
	@Basic(optional = false)
	@Column(name = "birth_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "email_address")
	private String emailAddress;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;

	public PatientVerificationLog() {
	}

	public PatientVerificationLog(Integer patientVerificationLogId) {
		this.patientVerificationLogId = patientVerificationLogId;
	}

	public PatientVerificationLog(Integer patientVerificationLogId, int clientId, int clientDatabaseId,
			String medicalRecordNumber, Date birthDate, Date dateAdded) {
		this.patientVerificationLogId = patientVerificationLogId;
		this.clientId = clientId;
		this.clientDatabaseId = clientDatabaseId;
		this.medicalRecordNumber = medicalRecordNumber;
		this.birthDate = birthDate;
		this.dateAdded = dateAdded;
	}

	public Integer getPatientVerificationLogId() {
		return patientVerificationLogId;
	}

	public void setPatientVerificationLogId(Integer patientVerificationLogId) {
		this.patientVerificationLogId = patientVerificationLogId;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientVerificationLogId != null ? patientVerificationLogId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientVerificationLog)) {
			return false;
		}
		PatientVerificationLog other = (PatientVerificationLog) object;
		if ((this.patientVerificationLogId == null && other.patientVerificationLogId != null)
				|| (this.patientVerificationLogId != null && !this.patientVerificationLogId
						.equals(other.patientVerificationLogId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientVerificationLog[ patientVerificationLogId=" + patientVerificationLogId
				+ " ]";
	}

}
