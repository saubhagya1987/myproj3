package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Dheeraj
 * 
 */

public class AccountInfo implements Serializable {
	private final static long serialVersionUID = 1L;
	protected String authToken;
	protected String accountName;
	protected String password;
	protected String unitNumber;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String mobilePhoneNumber;
	protected String roleName;
	protected Integer clientDatabaseId;
	protected String dateOfBirth;
	protected Boolean willShareData;
	protected Boolean willProvideFeedback;
	protected String uniqueId;
	protected Date lastLoginDate;
	protected Integer failedLoginAttempts;
	protected Date dateModified;
	protected Integer accountId;
	protected Integer linkedAccountClientDBId;
	protected String deviceToken;
	protected String deviceType;
	protected Integer role;
	protected String activity;

	protected String fromDate;
	protected String toDate;

	protected byte[] profileImage;
	protected String loginAuthMode;
	protected String loginPin;
	protected String endpointUserId;
	protected String preferredLanguage;
	protected String linkedFacility;
	protected String linkedFacilityAddress;
	protected String authPin;
	protected Boolean pushNotification;

	

	protected ArrayList<Integer> linkedAccounts;
	
	protected Integer clientId;
	protected Integer featureId;
	protected Integer feedMessageId;
	

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public Integer getFeedMessageId() {
		return feedMessageId;
	}

	public void setFeedMessageId(Integer feedMessageId) {
		this.feedMessageId = feedMessageId;
	}

	public String getLinkedFacilityAddress() {
		return linkedFacilityAddress;
	}

	public void setLinkedFacilityAddress(String linkedFacilityAddress) {
		this.linkedFacilityAddress = linkedFacilityAddress;
	}

	public String getLinkedFacility() {
		return linkedFacility;
	}

	public void setLinkedFacility(String linkedFacility) {
		this.linkedFacility = linkedFacility;
	}

	public Integer getLinkedAccountClientDBId() {
		return linkedAccountClientDBId;
	}

	public void setLinkedAccountClientDBId(Integer linkedAccountClientDBId) {
		this.linkedAccountClientDBId = linkedAccountClientDBId;
	}

	public ArrayList<Integer> getLinkedAccounts() {
		return linkedAccounts;
	}

	public void setLinkedAccounts(ArrayList<Integer> linkedAccounts) {
		this.linkedAccounts = linkedAccounts;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getActivity() {
		return activity;
	}

	public String getEndpointUserId() {
		return endpointUserId;
	}

	public void setEndpointUserId(String endpointUserId) {
		this.endpointUserId = endpointUserId;
	}

	public String getLoginAuthMode() {
		return loginAuthMode;
	}

	public void setLoginAuthMode(String loginAuthMode) {
		this.loginAuthMode = loginAuthMode;
	}

	public String getLoginPin() {
		return loginPin;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public void setLoginPin(String loginPin) {
		this.loginPin = loginPin;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber.trim();
	}

	public String getFirstName() {
		if (firstName != null) {
			return firstName.trim();
		} else
			return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}

	public String getLastName() {
		if (lastName != null) {
			return lastName.trim();
		} else
			return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}

	public String getEmail() {
		if (email != null) {
			return email.trim();
		} else
			return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Boolean isWillShareData() {
		return willShareData;
	}

	public void setWillShareData(Boolean willShareData) {
		this.willShareData = willShareData;
	}

	public Boolean isWillProvideFeedback() {
		return willProvideFeedback;
	}

	public void setWillProvideFeedback(Boolean willProvideFeedback) {
		this.willProvideFeedback = willProvideFeedback;
	}

	public Integer getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(Integer clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(Integer failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getAuthPin() {
		return authPin;
	}

	public void setAuthPin(String authPin) {
		this.authPin = authPin;
	}

	public Boolean isPushNotification() {
		return pushNotification;
	}

	public void setPushNotification(Boolean pushNotification) {
		this.pushNotification = pushNotification;
	}

	

}
