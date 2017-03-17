package com.versawork.http.dataobject;

import java.util.Date;

public class PatientPrescriptionCache {

	/**
	 * 
	 */
	private String accountNumber;
	private String drugId;
	private String drugName;
	private String drugType;
	private String drugStatus;
	private String dose;
	private Date issuedDate;
	private Date discontinuedDate;
	private Date dateAdded;
	private int clientNaavisDatabases;
	private String medicationName;
	private String dosageDescription;

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getDosageDescription() {
		return dosageDescription;
	}

	public void setDosageDescription(String dosageDescription) {
		this.dosageDescription = dosageDescription;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public String getDrugStatus() {
		return drugStatus;
	}

	public void setDrugStatus(String drugStatus) {
		this.drugStatus = drugStatus;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(Date discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getClientNaavisDatabases() {
		return clientNaavisDatabases;
	}

	public void setClientNaavisDatabases(int clientNaavisDatabases) {
		this.clientNaavisDatabases = clientNaavisDatabases;
	}

}
