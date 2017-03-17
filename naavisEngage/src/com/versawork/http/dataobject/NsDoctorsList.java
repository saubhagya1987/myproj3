package com.versawork.http.dataobject;

import java.io.Serializable;

/**
 * @author Dheeraj
 * 
 */

public class NsDoctorsList implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String firstName;
	protected String lastName;
	protected String suffix;
	protected String specialty;
	protected String address1;
	protected String address2;
	protected String city;
	protected String state;
	protected String postalCode;
	protected String contactPhoneNumber;
	protected String faxNumber;
	protected String contactEmail;
	protected String providerId;
	protected String providerImageURL;

	public String getProviderImageURL() {
		return providerImageURL;
	}

	public void setProviderImageURL(String providerImageURL) {
		this.providerImageURL = providerImageURL;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
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

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
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

	/*
	 * public String getContactPhoneNumber() { return contactPhoneNumber; }
	 * 
	 * public void setContactPhoneNumber(String contactPhoneNumber) { //String
	 * str=contactPhoneNumber; //String numberOnly= str.replaceAll("[^0-9]",
	 * ""); this.contactPhoneNumber = numberOnly; }
	 */

	public String getFaxNumber() {
		return faxNumber;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

}
