/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
		@NamedQuery(name = "Account.findByAccountId", query = "SELECT a FROM Account a WHERE a.accountId = :accountId"),
		@NamedQuery(name = "Account.findForForgotPin", query = "SELECT a FROM Account a WHERE a.clientDatabaseId = :clientDatabaseId  and a.accountId = :accountId"),
		@NamedQuery(name = "Account.findByAccountName", query = "SELECT a FROM Account a WHERE a.accountName = :accountName"),
		@NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password"),
		@NamedQuery(name = "Account.findByMedicalRecordNumber", query = "SELECT a FROM Account a WHERE a.medicalRecordNumber = :medicalRecordNumber"),
		@NamedQuery(name = "Account.findByMedicalRecordNumberAndMobilePhoneNumber", query = "SELECT a FROM Account a WHERE a.medicalRecordNumber = :medicalRecordNumber AND a.mobilePhoneNumber = :mobilePhoneNumber"),
		@NamedQuery(name = "Account.findByMedicalRecordNumberClientDbId", query = "SELECT a FROM Account a WHERE a.medicalRecordNumber = :medicalRecordNumber AND a.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "Account.findByEmailClientDbId", query = "SELECT a FROM Account a WHERE a.email = :email AND a.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "Account.findByFirstName", query = "SELECT a FROM Account a WHERE a.firstName = :firstName"),
		@NamedQuery(name = "Account.findByLastName", query = "SELECT a FROM Account a WHERE a.lastName = :lastName"),
		@NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
		@NamedQuery(name = "Account.findByMobilePhoneNumber", query = "SELECT a FROM Account a WHERE a.mobilePhoneNumber = :mobilePhoneNumber"),
		@NamedQuery(name = "Account.findByFailedLoginAttempts", query = "SELECT a FROM Account a WHERE a.failedLoginAttempts = :failedLoginAttempts"),
		@NamedQuery(name = "Account.findByLastLoginDate", query = "SELECT a FROM Account a WHERE a.lastLoginDate = :lastLoginDate"),
		@NamedQuery(name = "Account.findByDisableDate", query = "SELECT a FROM Account a WHERE a.disableDate = :disableDate"),
		@NamedQuery(name = "Account.findByDateAdded", query = "SELECT a FROM Account a WHERE a.dateAdded = :dateAdded"),
		@NamedQuery(name = "Account.findByDateModified", query = "SELECT a FROM Account a WHERE a.dateModified = :dateModified"),
		@NamedQuery(name = "Account.findByAuthToken", query = "SELECT a FROM Account a WHERE a.authToken = :authToken"),
		@NamedQuery(name = "Account.findByBirthDate", query = "SELECT a FROM Account a WHERE a.birthDate = :birthDate"),
		@NamedQuery(name = "Account.findByWillShareData", query = "SELECT a FROM Account a WHERE a.willShareData = :willShareData"),
		@NamedQuery(name = "Account.findByUniqueId", query = "SELECT a FROM Account a WHERE a.uniqueId = :uniqueId"),
		@NamedQuery(name = "Account.findByDeviceToken", query = "SELECT a FROM Account a WHERE a.deviceToken = :deviceToken"),
		@NamedQuery(name = "Account.findByAccountIdEmail", query = "SELECT a FROM Account a WHERE a.accountId = :accountId AND a.email = :email"),
		@NamedQuery(name = "Account.findByDeviceType", query = "SELECT a FROM Account a WHERE a.deviceType = :deviceType"),
		@NamedQuery(name = "Account.findByDeviceAuthToken", query = "SELECT a FROM Account a WHERE a.deviceAuthToken = :deviceAuthToken"),
		@NamedQuery(name = "Account.findByAuthMode", query = "SELECT a FROM Account a WHERE a.authMode = :authMode"),
		@NamedQuery(name = "Account.findByAuthPin", query = "SELECT a FROM Account a WHERE a.authPin = :authPin"),
		@NamedQuery(name = "Account.findByPreferredLanguage", query = "SELECT a FROM Account a WHERE a.preferredLanguage = :preferredLanguage"),
		@NamedQuery(name = "Account.findByMailAndClientDbId", query = "SELECT a FROM Account a WHERE a.email = :email AND a.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "Account.findByEndpointUserId", query = "SELECT a FROM Account a WHERE a.endpointUserId = :endpointUserId and a.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "Account.findAllEndPointUSerId", query = "SELECT a.endpointUserId FROM Account a ")})
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	/*
	 * @GenericGenerator(name = "myGenerator", strategy =
	 * "com.versawork.http.dao.UseExistingOrGenerateIdGenerator")
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY,generator =
	 * "myGenerator")
	 */
	@Column(name = "account_id", insertable = true, updatable = true)
	private Integer accountId;
	@Basic(optional = false)
	@Column(name = "account_name")
	private String accountName;
	@Basic(optional = false)
	@Column(name = "password")
	private String password;
	@Column(name = "medical_record_number")
	private String medicalRecordNumber;
	@Basic(optional = false)
	@Column(name = "first_name")
	private String firstName;
	@Basic(optional = false)
	@Column(name = "last_name")
	private String lastName;
	@Basic(optional = false)
	@Column(name = "email")
	private String email;
	@Basic(optional = false)
	@Column(name = "mobile_phone_number")
	private String mobilePhoneNumber;
	@Column(name = "failed_login_attempts")
	private Integer failedLoginAttempts;
	@Column(name = "last_login_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginDate;
	@Column(name = "disable_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date disableDate;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "date_modified")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;
	@Basic(optional = false)
	@Column(name = "auth_token")
	private String authToken;
	@Column(name = "birth_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;
	@Column(name = "will_share_data")
	private Boolean willShareData;
	@Column(name = "unique_id")
	private String uniqueId;
	@Column(name = "device_token")
	private String deviceToken;
	@Column(name = "device_type")
	private String deviceType;
	@Column(name = "device_auth_token")
	private String deviceAuthToken;
	@Column(name = "auth_mode")
	private String authMode;
	@Column(name = "auth_pin")
	private String authPin;
	@Column(name = "endpoint_user_id")
	private String endpointUserId;
	@Column(name = "profile_image")
	private byte[] profileImage;
	@Column(name = "preferred_language")
	private String preferredLanguage;
	@Column(name = "temp_pin")
	private String tempPin;
	@Column(name = "push_notification")
	private Boolean pushNotification;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
	private List<AccountMedicationManagementReminder> accountMedicationManagementReminderList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
	private List<AccountMedicationManagement> accountMedicationManagementList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
	private List<AccountBloodPressureEngage> accountBloodPressureEngageList;

	@JoinColumn(name = "account_role_id", referencedColumnName = "account_role_id")
	@ManyToOne
	private AccountRole accountRoleId;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
	private List<AccountBloodPressureManagement> accountBloodPressureManagementList;

	@Basic(optional = false)
	@Column(name = "client_id")
	private int clientId;
	@Basic(optional = false)
	@Column(name = "client_database_id")
	private int clientDatabaseId;

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

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public Account() {
	}

	public Account(Integer accountId) {
		this.accountId = accountId;
	}

	public Account(Integer accountId, String accountName, String password, String firstName, String lastName,
			String email, String mobilePhoneNumber, Date dateAdded, String authToken) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobilePhoneNumber = mobilePhoneNumber;
		this.dateAdded = dateAdded;
		this.authToken = authToken;
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

	public String getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public Integer getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(Integer failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(Date disableDate) {
		this.disableDate = disableDate;
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

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean getWillShareData() {
		return willShareData;
	}

	public void setWillShareData(Boolean willShareData) {
		this.willShareData = willShareData;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceAuthToken() {
		return deviceAuthToken;
	}

	public void setDeviceAuthToken(String deviceAuthToken) {
		this.deviceAuthToken = deviceAuthToken;
	}

	public String getAuthMode() {
		return authMode;
	}

	public void setAuthMode(String authMode) {
		this.authMode = authMode;
	}

	public String getAuthPin() {
		return authPin;
	}

	public void setAuthPin(String authPin) {
		this.authPin = authPin;
	}

	public String getEndpointUserId() {
		return endpointUserId;
	}

	public void setEndpointUserId(String endpointUserId) {
		this.endpointUserId = endpointUserId;
	}

	public List<AccountMedicationManagementReminder> getAccountMedicationManagementReminderList() {
		return accountMedicationManagementReminderList;
	}

	public void setAccountMedicationManagementReminderList(
			List<AccountMedicationManagementReminder> accountMedicationManagementReminderList) {
		this.accountMedicationManagementReminderList = accountMedicationManagementReminderList;
	}

	public List<AccountMedicationManagement> getAccountMedicationManagementList() {
		return accountMedicationManagementList;
	}

	public void setAccountMedicationManagementList(List<AccountMedicationManagement> accountMedicationManagementList) {
		this.accountMedicationManagementList = accountMedicationManagementList;
	}

	public List<AccountBloodPressureEngage> getAccountBloodPressureEngageList() {
		return accountBloodPressureEngageList;
	}

	public void setAccountBloodPressureEngageList(List<AccountBloodPressureEngage> accountBloodPressureEngageList) {
		this.accountBloodPressureEngageList = accountBloodPressureEngageList;
	}

	public AccountRole getAccountRoleId() {
		return accountRoleId;
	}

	public void setAccountRoleId(AccountRole accountRoleId) {
		this.accountRoleId = accountRoleId;
	}

	public List<AccountBloodPressureManagement> getAccountBloodPressureManagementList() {
		return accountBloodPressureManagementList;
	}

	public void setAccountBloodPressureManagementList(
			List<AccountBloodPressureManagement> accountBloodPressureManagementList) {
		this.accountBloodPressureManagementList = accountBloodPressureManagementList;
	}

	public String getTempPin() {
		return tempPin;
	}

	public void setTempPin(String tempPin) {
		this.tempPin = tempPin;
	}

	

	public Boolean getPushNotification() {
		return pushNotification;
	}

	public void setPushNotification(Boolean pushNotification) {
		this.pushNotification = pushNotification;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (accountId != null ? accountId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Account)) {
			return false;
		}
		Account other = (Account) object;
		if ((this.accountId == null && other.accountId != null)
				|| (this.accountId != null && !this.accountId.equals(other.accountId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.Account[ accountId=" + accountId + " ]";
	}

}
