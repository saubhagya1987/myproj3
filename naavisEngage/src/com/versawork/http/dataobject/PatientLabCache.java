package com.versawork.http.dataobject;

import java.util.Date;

import com.versawork.http.model.PatientLabPK;

public class PatientLabCache {
	protected PatientLabPK patientLabPK;
	private String accountNumber;
	private String testMnemonic;
	private String testName;
	private String testResult;
	private String testUnit;
	private Date resultDate;
	private Date dateAdded;
	private int clientNaavisDatabases;

	public PatientLabPK getPatientLabPK() {
		return patientLabPK;
	}

	public void setPatientLabPK(PatientLabPK patientLabPK) {
		this.patientLabPK = patientLabPK;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTestMnemonic() {
		return testMnemonic;
	}

	public void setTestMnemonic(String testMnemonic) {
		this.testMnemonic = testMnemonic;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getTestUnit() {
		return testUnit;
	}

	public void setTestUnit(String testUnit) {
		this.testUnit = testUnit;
	}

	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
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
