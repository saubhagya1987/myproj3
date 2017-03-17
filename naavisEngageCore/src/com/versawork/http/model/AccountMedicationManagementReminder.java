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
@Table(name = "account_medication_management_reminder")
@NamedQueries({
		@NamedQuery(name = "AccountMedicationManagementReminder.findAll", query = "SELECT a FROM AccountMedicationManagementReminder a"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByAccountMedicationManagementReminderId", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.accountMedicationManagementReminderId = :accountMedicationManagementReminderId"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByAccountId", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.accountId = :accountId"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByReminderFor", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.reminderFor = :reminderFor"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByAccountIdDeleteFlag", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.accountId = :accountId AND a.deleteFlag = :deleteFlag ORDER BY a.dateAdded DESC"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByReminderBeginDate", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.reminderBeginDate = :reminderBeginDate"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByReminderEndDate", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.reminderEndDate = :reminderEndDate"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByDosage", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.dosage = :dosage"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByDateAdded", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.dateAdded = :dateAdded"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByDeleteFlag", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.deleteFlag = :deleteFlag"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByAccountMedicationManagementReminderIdActId", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.accountMedicationManagementReminderId = :accountMedicationManagementReminderId AND a.accountId = :accountId"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByAccountIdActiveFlag", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.accountId = :accountId AND a.deleteFlag = :deleteFlag ORDER BY a.dateAdded DESC"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByIsActive", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.isActive = :isActive"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByAccountIdIsActive", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.accountId = :accountId AND a.isActive = :isActive"),
		@NamedQuery(name = "AccountMedicationManagementReminder.findByAccountIdDeleteFlagEndDate", query = "SELECT a FROM AccountMedicationManagementReminder a WHERE a.accountId = :accountId AND a.deleteFlag = :deleteFlag  AND reminderEndDate > :todaysDate") })
public class AccountMedicationManagementReminder implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_medication_management_reminder_id")
	private Integer accountMedicationManagementReminderId;
	@Basic(optional = false)
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
	@Column(name = "dosage")
	private String dosage;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Basic(optional = false)
	@Column(name = "delete_flag")
	private String deleteFlag;
	@Column(name = "is_active")
	private Boolean isActive;
	/*@JoinColumn(name = "medication_dosage_id", referencedColumnName = "medication_dosage_id")
	@ManyToOne(optional = false)
	private MedicationDosage medicationDosageId;*/
	@Basic(optional = false)
	@Column(name = "medication_dosage_id")
	private Integer medicationDosageId;
	
	@Column(name = "frequency_type")
	private Integer frequencyType;
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	@ManyToOne(optional = false)
	private Account accountId;
	@OneToMany(mappedBy = "accountMedicationManagementReminderId")
	private List<AccountMedicationEngage> accountMedicationEngageList;
	@OneToMany(mappedBy = "accountMedicationManagementReminderId")
	private List<AccountMedicationManagementSchedule> accountMedicationManagementScheduleList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountMedicationManagementReminderId")
	private List<AccountReminderMedicationRelation> accountReminderMedicationRelationList;
	@Column(name = "frequency_value")
	private Integer frequencyValue;

	public Integer getFrequencyValue() {
		return frequencyValue;
	}

	public void setFrequencyValue(Integer frequencyValue) {
		this.frequencyValue = frequencyValue;
	}

	public AccountMedicationManagementReminder() {
	}

	public AccountMedicationManagementReminder(Integer accountMedicationManagementReminderId) {
		this.accountMedicationManagementReminderId = accountMedicationManagementReminderId;
	}

	public AccountMedicationManagementReminder(Integer accountMedicationManagementReminderId, String reminderFor,
			Date reminderBeginDate, Date reminderEndDate, String dosage, Date dateAdded, String deleteFlag) {
		this.accountMedicationManagementReminderId = accountMedicationManagementReminderId;
		this.reminderFor = reminderFor;
		this.reminderBeginDate = reminderBeginDate;
		this.reminderEndDate = reminderEndDate;
		this.dosage = dosage;
		this.dateAdded = dateAdded;
		this.deleteFlag = deleteFlag;
	}

	public Integer getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(Integer frequencyType) {
		this.frequencyType = frequencyType;
	}

	public Integer getAccountMedicationManagementReminderId() {
		return accountMedicationManagementReminderId;
	}

	public void setAccountMedicationManagementReminderId(Integer accountMedicationManagementReminderId) {
		this.accountMedicationManagementReminderId = accountMedicationManagementReminderId;
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

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/*public MedicationDosage getMedicationDosageId() {
		return medicationDosageId;
	}

	public void setMedicationDosageId(MedicationDosage medicationDosageId) {
		this.medicationDosageId = medicationDosageId;
	}*/

	public Account getAccountId() {
		return accountId;
	}

	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}

	public List<AccountMedicationEngage> getAccountMedicationEngageList() {
		return accountMedicationEngageList;
	}

	public void setAccountMedicationEngageList(List<AccountMedicationEngage> accountMedicationEngageList) {
		this.accountMedicationEngageList = accountMedicationEngageList;
	}

	public List<AccountMedicationManagementSchedule> getAccountMedicationManagementScheduleList() {
		return accountMedicationManagementScheduleList;
	}

	public void setAccountMedicationManagementScheduleList(
			List<AccountMedicationManagementSchedule> accountMedicationManagementScheduleList) {
		this.accountMedicationManagementScheduleList = accountMedicationManagementScheduleList;
	}

	public List<AccountReminderMedicationRelation> getAccountReminderMedicationRelationList() {
		return accountReminderMedicationRelationList;
	}

	public void setAccountReminderMedicationRelationList(
			List<AccountReminderMedicationRelation> accountReminderMedicationRelationList) {
		this.accountReminderMedicationRelationList = accountReminderMedicationRelationList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (accountMedicationManagementReminderId != null ? accountMedicationManagementReminderId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AccountMedicationManagementReminder)) {
			return false;
		}
		AccountMedicationManagementReminder other = (AccountMedicationManagementReminder) object;
		if ((this.accountMedicationManagementReminderId == null && other.accountMedicationManagementReminderId != null)
				|| (this.accountMedicationManagementReminderId != null && !this.accountMedicationManagementReminderId
						.equals(other.accountMedicationManagementReminderId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.AccountMedicationManagementReminder[ accountMedicationManagementReminderId="
				+ accountMedicationManagementReminderId + " ]";
	}

}
