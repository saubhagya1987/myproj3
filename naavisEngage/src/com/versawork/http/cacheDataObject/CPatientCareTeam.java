package com.versawork.http.cacheDataObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sohaib
 * 
 *         Key = PatientCT_accountId_patientVisitId
 */
public class CPatientCareTeam implements Serializable {

	private static final long serialVersionUID = 1L;

	private int accountId;
	private int patientVisitId;
	private String careMemberId;
	private int careMemberRoleId;
	private String sourceId;
	private String sourceName;
	private String careMemberName;
	private String phoneNumber;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private Date dateAdded;
	private String careMemberSpeciality;
	private String careMemberEmail;

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

	public String getCareMemberSpeciality() {
		return careMemberSpeciality;
	}

	public void setCareMemberSpeciality(String careMemberSpeciality) {
		this.careMemberSpeciality = careMemberSpeciality;
	}

	public String getCareMemberEmail() {
		return careMemberEmail;
	}

	public void setCareMemberEmail(String careMemberEmail) {
		this.careMemberEmail = careMemberEmail;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getCareMemberId() {
		return careMemberId;
	}

	public void setCareMemberId(String careMemberId) {
		this.careMemberId = careMemberId;
	}

	public String getCareMemberName() {
		return careMemberName;
	}

	public void setCareMemberName(String careMemberName) {
		this.careMemberName = careMemberName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getCareMemberRoleId() {
		return careMemberRoleId;
	}

	public void setCareMemberRoleId(int careMemberRoleId) {
		this.careMemberRoleId = careMemberRoleId;
	}

}
