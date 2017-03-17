package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.versawork.http.model.PatientVerificationPK;

/**
 * @author Dheeraj
 * 
 */

public class NsPatientVerification implements Serializable {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Date dateValidated;
	private Integer accountId;
	private Date dateAdded;
	private Date dateModified;
	private Integer clientNaavisDatabases;
	protected PatientVerificationPK patientVerificationPK;

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

	public Integer getClientNaavisDatabases() {
		return clientNaavisDatabases;
	}

	public void setClientNaavisDatabases(Integer clientNaavisDatabases) {
		this.clientNaavisDatabases = clientNaavisDatabases;
	}

	public PatientVerificationPK getPatientVerificationPK() {
		return patientVerificationPK;
	}

	public void setPatientVerificationPK(PatientVerificationPK patientVerificationPK) {
		this.patientVerificationPK = patientVerificationPK;
	}
}
