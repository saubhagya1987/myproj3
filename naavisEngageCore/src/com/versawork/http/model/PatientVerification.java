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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "patient_verification")
@NamedQueries({
		@NamedQuery(name = "PatientVerification.findAll", query = "SELECT p FROM PatientVerification p"),
		@NamedQuery(name = "PatientVerification.findByClientId", query = "SELECT p FROM PatientVerification p WHERE p.patientVerificationPK.clientId = :clientId"),
		@NamedQuery(name = "PatientVerification.findByClientDatabaseId", query = "SELECT p FROM PatientVerification p WHERE p.patientVerificationPK.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "PatientVerification.findByMedicalRecordNumber", query = "SELECT p FROM PatientVerification p WHERE p.patientVerificationPK.medicalRecordNumber = :medicalRecordNumber and p.patientVerificationPK.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "PatientVerification.findByFirstName", query = "SELECT p FROM PatientVerification p WHERE p.firstName = :firstName"),
		@NamedQuery(name = "PatientVerification.findByLastName", query = "SELECT p FROM PatientVerification p WHERE p.lastName = :lastName"),
		@NamedQuery(name = "PatientVerification.findByBirthDate", query = "SELECT p FROM PatientVerification p WHERE p.birthDate = :birthDate"),
		@NamedQuery(name = "PatientVerification.findByDateValidated", query = "SELECT p FROM PatientVerification p WHERE p.dateValidated = :dateValidated"),
		@NamedQuery(name = "PatientVerification.findByAccountId", query = "SELECT p FROM PatientVerification p WHERE p.accountId = :accountId order by p.accountId asc"),
		@NamedQuery(name = "PatientVerification.findByDateAdded", query = "SELECT p FROM PatientVerification p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientVerification.findByDateModified", query = "SELECT p FROM PatientVerification p WHERE p.dateModified = :dateModified"),
		@NamedQuery(name = "PatientVerification.findBySecurityCodeTimestamp", query = "SELECT p FROM PatientVerification p WHERE p.securityCodeTimestamp = :securityCodeTimestamp"),
		@NamedQuery(name = "PatientVerification.findByEmailAddress", query = "SELECT p FROM PatientVerification p WHERE p.emailAddress = :emailAddress"),
		@NamedQuery(name = "PatientVerification.findBySecurityCode", query = "SELECT p FROM PatientVerification p WHERE p.securityCode = :securityCode"),
		@NamedQuery(name = "PatientVerification.findBySecurityExpirationDate", query = "SELECT p FROM PatientVerification p WHERE p.securityExpirationDate = :securityExpirationDate"),
		@NamedQuery(name = "PatientVerification.findByMobilePhoneNumber", query = "SELECT p FROM PatientVerification p WHERE p.mobilePhoneNumber = :mobilePhoneNumber"),
	    @NamedQuery(name = "PatientVerification.findByHomePhoneNumber", query = "SELECT p FROM PatientVerification p WHERE p.homePhoneNumber = :homePhoneNumber"),
	    @NamedQuery(name = "PatientVerification.findByOtherPhoneNumber", query = "SELECT p FROM PatientVerification p WHERE p.otherPhoneNumber = :otherPhoneNumber") })
public class PatientVerification implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientVerificationPK patientVerificationPK;
	@Basic(optional = false)
	@Column(name = "first_name")
	private String firstName;
	@Basic(optional = false)
	@Column(name = "last_name")
	private String lastName;
	@Basic(optional = false)
	@Column(name = "birth_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;
	@Column(name = "date_validated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateValidated;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer accountId;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "date_modified")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;
	@Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;
    @Column(name = "home_phone_number ")
    private String homePhoneNumber;
    @Column(name = "other_phone_number")
    private String otherPhoneNumber;
	@Column(name = "security_code_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date securityCodeTimestamp;
	@Column(name = "email_address")
	private String emailAddress;
	@Column(name = "security_code")
	private String securityCode;
	@Column(name = "security_expiration_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date securityExpirationDate;
	@JoinColumns({
			@JoinColumn(name = "client_id", referencedColumnName = "client_id", insertable = false, updatable = false),
			@JoinColumn(name = "client_database_id", referencedColumnName = "client_database_id", insertable = false, updatable = false) })
	@ManyToOne(optional = false)
	private ClientNaavisDatabases clientNaavisDatabases;

	public PatientVerification() {
	}

	public PatientVerification(PatientVerificationPK patientVerificationPK) {
		this.patientVerificationPK = patientVerificationPK;
	}

	public PatientVerification(PatientVerificationPK patientVerificationPK, String firstName, String lastName,
			Date birthDate, Date dateAdded) {
		this.patientVerificationPK = patientVerificationPK;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.dateAdded = dateAdded;
	}

	public PatientVerification(int clientId, int clientDatabaseId, String medicalRecordNumber) {
		this.patientVerificationPK = new PatientVerificationPK(clientId, clientDatabaseId, medicalRecordNumber);
	}

	public PatientVerificationPK getPatientVerificationPK() {
		return patientVerificationPK;
	}

	public void setPatientVerificationPK(PatientVerificationPK patientVerificationPK) {
		this.patientVerificationPK = patientVerificationPK;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDateValidated() {
		return dateValidated;
	}

	public void setDateValidated(Date dateValidated) {
		this.dateValidated = dateValidated;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getOtherPhoneNumber() {
        return otherPhoneNumber;
    }

    public void setOtherPhoneNumber(String otherPhoneNumber) {
        this.otherPhoneNumber = otherPhoneNumber;
    }

	public Date getSecurityCodeTimestamp() {
		return securityCodeTimestamp;
	}

	public void setSecurityCodeTimestamp(Date securityCodeTimestamp) {
		this.securityCodeTimestamp = securityCodeTimestamp;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public Date getSecurityExpirationDate() {
		return securityExpirationDate;
	}

	public void setSecurityExpirationDate(Date securityExpirationDate) {
		this.securityExpirationDate = securityExpirationDate;
	}

	public ClientNaavisDatabases getClientNaavisDatabases() {
		return clientNaavisDatabases;
	}

	public void setClientNaavisDatabases(ClientNaavisDatabases clientNaavisDatabases) {
		this.clientNaavisDatabases = clientNaavisDatabases;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientVerificationPK != null ? patientVerificationPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientVerification)) {
			return false;
		}
		PatientVerification other = (PatientVerification) object;
		if ((this.patientVerificationPK == null && other.patientVerificationPK != null)
				|| (this.patientVerificationPK != null && !this.patientVerificationPK
						.equals(other.patientVerificationPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientVerification[ patientVerificationPK=" + patientVerificationPK + " ]";
	}

}
