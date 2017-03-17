package com.versawork.asyn.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.versawork.asyn.constant.VersaworkConstants;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CareTeamInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String providerId;
	protected String careMemberName;
	protected String phoneNumber;
	protected String address;
	protected String specialty;
	protected String contactEmail;
	protected Integer clientId;
	protected Integer clientDatabaseId;

	public Integer getClientId() {

		return clientId;
	}

	public void setClientId(Integer clientId) {

		this.clientId = clientId;
	}

	public Integer getClientDatabaseId() {

		return clientDatabaseId;
	}

	public void setClientDatabaseId(Integer clientDatabaseId) {

		this.clientDatabaseId = clientDatabaseId;
	}

	public String getProviderId() {

		return providerId;
	}

	public void setProviderId(String providerId) {

		this.providerId = providerId;
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

		if (phoneNumber != null) {
			this.phoneNumber = phoneNumber;
		} else {
			this.phoneNumber = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		if (address != null) {
			this.address = address;
		} else {
			this.address = "";
		}
	}

	public String getSpecialty() {

		return specialty;
	}

	public void setSpecialty(String specialty) {

		if (specialty != null) {
			this.specialty = specialty;
		} else {
			this.specialty = "";
		}
	}

	public String getContactEmail() {

		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {

		if (contactEmail != null) {
			this.contactEmail = contactEmail;
		} else {
			this.contactEmail = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

}
