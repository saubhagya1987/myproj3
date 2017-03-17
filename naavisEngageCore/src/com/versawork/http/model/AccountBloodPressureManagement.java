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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "account_blood_pressure_management")
@NamedQueries({
		@NamedQuery(name = "AccountBloodPressureManagement.findAll", query = "SELECT a FROM AccountBloodPressureManagement a"),
		@NamedQuery(name = "AccountBloodPressureManagement.findByAccountBloodPressureManagementId", query = "SELECT a FROM AccountBloodPressureManagement a WHERE a.accountBloodPressureManagementId = :accountBloodPressureManagementId"),
		@NamedQuery(name = "AccountBloodPressureManagement.findByIsActive", query = "SELECT a FROM AccountBloodPressureManagement a WHERE a.isActive = :isActive"),
		@NamedQuery(name = "AccountBloodPressureManagement.findByAccountIdIsActive", query = "SELECT a FROM AccountBloodPressureManagement a WHERE a.accountId = :accountId AND a.isActive = :isActive"),
		@NamedQuery(name = "AccountBloodPressureManagement.findByReminderFor", query = "SELECT a FROM AccountBloodPressureManagement a WHERE a.reminderFor = :reminderFor"),
		@NamedQuery(name = "AccountBloodPressureManagement.findByReminderBeginDate", query = "SELECT a FROM AccountBloodPressureManagement a WHERE a.reminderBeginDate = :reminderBeginDate"),
		@NamedQuery(name = "AccountBloodPressureManagement.findByReminderEndDate", query = "SELECT a FROM AccountBloodPressureManagement a WHERE a.reminderEndDate = :reminderEndDate"),
		@NamedQuery(name = "AccountBloodPressureManagement.findByDateAdded", query = "SELECT a FROM AccountBloodPressureManagement a WHERE a.dateAdded = :dateAdded"),
		@NamedQuery(name = "AccountBloodPressureManagement.findByDeleteFlag", query = "SELECT a FROM AccountBloodPressureManagement a WHERE a.deleteFlag = :deleteFlag "),
		@NamedQuery(name = "AccountBloodPressureManagement.findByAccountIdDeleteFlag", query = "SELECT a FROM AccountBloodPressureManagement a WHERE a.accountId = :accountId AND a.deleteFlag = :deleteFlag ORDER BY a.dateAdded DESC"),
		@NamedQuery(name = "AccountBloodPressureManagement.findByAccountBloodPressureManagementIdActId", query = "SELECT a FROM AccountBloodPressureManagement a WHERE a.accountBloodPressureManagementId = :accountBloodPressureManagementId AND a.accountId = :accountId"),
		@NamedQuery(name = "AccountBloodPressureManagement.findByAccountIdDeleteFlagEndDate", query = "SELECT a FROM AccountBloodPressureManagement a WHERE a.accountId = :accountId AND a.deleteFlag = :deleteFlag AND reminderEndDate > :todaysDate") })
public class AccountBloodPressureManagement implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_blood_pressure_management_id")
	private Integer accountBloodPressureManagementId;
	@Basic(optional = false)
	@Column(name = "is_active")
	private boolean isActive;
	@Column(name = "reminder_for")
	private String reminderFor;
	@Basic(optional = false)
	@Column(name = "reminder_begin_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reminderBeginDate;
	@Basic(optional = false)
	@Column(name = "reminder_end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reminderEndDate;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "delete_flag")
	private Boolean deleteFlag;
	@OneToMany(mappedBy = "accountBloodPressureManagementId")
	private List<AccountBloodPressureEngage> accountBloodPressureEngageList;
	@OneToMany(mappedBy = "accountBloodPressureManagementId")
	private List<AccountBloodPressureEngageResponse> accountBloodPressureEngageResponseList;
	@OneToMany(mappedBy = "accountBloodPressureManagementId")
	private List<AccountBloodPressureManagementSchedule> accountBloodPressureManagementScheduleList;
	@Column(name = "frequency_type")
	private Integer frequencyType;
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	@ManyToOne(optional = false)
	private Account accountId;

	public AccountBloodPressureManagement() {
	}

	public AccountBloodPressureManagement(Integer accountBloodPressureManagementId) {
		this.accountBloodPressureManagementId = accountBloodPressureManagementId;
	}

	public AccountBloodPressureManagement(Integer accountBloodPressureManagementId, boolean isActive,
			Date reminderBeginDate, Date reminderEndDate, Date dateAdded) {
		this.accountBloodPressureManagementId = accountBloodPressureManagementId;
		this.isActive = isActive;
		this.reminderBeginDate = reminderBeginDate;
		this.reminderEndDate = reminderEndDate;
		this.dateAdded = dateAdded;
	}

	public Integer getAccountBloodPressureManagementId() {
		return accountBloodPressureManagementId;
	}

	public void setAccountBloodPressureManagementId(Integer accountBloodPressureManagementId) {
		this.accountBloodPressureManagementId = accountBloodPressureManagementId;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getReminderFor() {
		return reminderFor;
	}

	public void setReminderFor(String reminderFor) {
		this.reminderFor = reminderFor;
	}

	public Date getReminderBeginDate() {
		return reminderBeginDate;
	}

	public void setReminderBeginDate(Date reminderBeginDate) {
		this.reminderBeginDate = reminderBeginDate;
	}

	public Date getReminderEndDate() {
		return reminderEndDate;
	}

	public void setReminderEndDate(Date reminderEndDate) {
		this.reminderEndDate = reminderEndDate;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public List<AccountBloodPressureEngage> getAccountBloodPressureEngageList() {
		return accountBloodPressureEngageList;
	}

	public void setAccountBloodPressureEngageList(List<AccountBloodPressureEngage> accountBloodPressureEngageList) {
		this.accountBloodPressureEngageList = accountBloodPressureEngageList;
	}

	public List<AccountBloodPressureEngageResponse> getAccountBloodPressureEngageResponseList() {
		return accountBloodPressureEngageResponseList;
	}

	public void setAccountBloodPressureEngageResponseList(
			List<AccountBloodPressureEngageResponse> accountBloodPressureEngageResponseList) {
		this.accountBloodPressureEngageResponseList = accountBloodPressureEngageResponseList;
	}

	public List<AccountBloodPressureManagementSchedule> getAccountBloodPressureManagementScheduleList() {
		return accountBloodPressureManagementScheduleList;
	}

	public void setAccountBloodPressureManagementScheduleList(
			List<AccountBloodPressureManagementSchedule> accountBloodPressureManagementScheduleList) {
		this.accountBloodPressureManagementScheduleList = accountBloodPressureManagementScheduleList;
	}

	public Integer getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(Integer frequencyType) {
		this.frequencyType = frequencyType;
	}

	public Account getAccountId() {
		return accountId;
	}

	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (accountBloodPressureManagementId != null ? accountBloodPressureManagementId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AccountBloodPressureManagement)) {
			return false;
		}
		AccountBloodPressureManagement other = (AccountBloodPressureManagement) object;
		if ((this.accountBloodPressureManagementId == null && other.accountBloodPressureManagementId != null)
				|| (this.accountBloodPressureManagementId != null && !this.accountBloodPressureManagementId
						.equals(other.accountBloodPressureManagementId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.AccountBloodPressureManagement[ accountBloodPressureManagementId="
				+ accountBloodPressureManagementId + " ]";
	}

}
