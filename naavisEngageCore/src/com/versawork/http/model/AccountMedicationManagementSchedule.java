/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Sohaib AccountMedicationManagementSchedule.findByAccountMedicationReminderId
 */
@Entity
@Table(name = "account_medication_management_schedule")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "AccountMedicationManagementSchedule.findAll", query = "SELECT a FROM AccountMedicationManagementSchedule a"),
		@NamedQuery(name = "AccountMedicationManagementSchedule.findByAccountMedicationManagementScheduleId", query = "SELECT a FROM AccountMedicationManagementSchedule a WHERE a.accountMedicationManagementScheduleId = :accountMedicationManagementScheduleId"),
		@NamedQuery(name = "AccountMedicationManagementSchedule.findByAccountMedicationManagementId", query = "SELECT a FROM AccountMedicationManagementSchedule a WHERE a.accountMedicationManagementId = :accountMedicationManagementId"),
		@NamedQuery(name = "AccountMedicationManagementSchedule.findByReminderTime", query = "SELECT a FROM AccountMedicationManagementSchedule a WHERE a.reminderTime = :reminderTime"),
		@NamedQuery(name = "AccountMedicationManagementSchedule.findByInterval", query = "SELECT a FROM AccountMedicationManagementSchedule a WHERE a.interval = :interval"),
		@NamedQuery(name = "AccountMedicationManagementSchedule.findByDateAdded", query = "SELECT a FROM AccountMedicationManagementSchedule a WHERE a.dateAdded = :dateAdded") ,
		@NamedQuery(name = "AccountMedicationManagementSchedule.findByAccountMedicationReminderId", query = "SELECT a FROM AccountMedicationManagementSchedule a WHERE a.accountMedicationManagementReminderId = :accountMedicationManagementReminderId")})
public class AccountMedicationManagementSchedule implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_medication_management_schedule_id")
	private Integer accountMedicationManagementScheduleId;
	@Basic(optional = false)
	@Column(name = "reminder_time")
	@Temporal(TemporalType.TIME)
	private Date reminderTime;
	@Basic(optional = true)
	@Column(name = "interval")
	private Integer interval;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Basic(optional = true)
	@JoinColumn(name = "date_part_id", referencedColumnName = "date_part_id")
	@ManyToOne
	private DatePart datePartId;
	@JoinColumn(name = "account_medication_management_reminder_id", referencedColumnName = "account_medication_management_reminder_id")
	@ManyToOne
	private AccountMedicationManagementReminder accountMedicationManagementReminderId;
	@JoinColumn(name = "account_medication_management_id", referencedColumnName = "account_medication_management_id")
	@ManyToOne
	private AccountMedicationManagement accountMedicationManagementId;
	@Basic(optional = false)
	@Column(name = "reminder_day")	
	private String reminderDay;

	

	public String getReminderDay() {
		return reminderDay;
	}

	public void setReminderDay(String reminderDay) {
		this.reminderDay = reminderDay;
	}

	public AccountMedicationManagementSchedule() {
	}

	public AccountMedicationManagementSchedule(Integer accountMedicationManagementScheduleId) {
		this.accountMedicationManagementScheduleId = accountMedicationManagementScheduleId;
	}

	public AccountMedicationManagementSchedule(Integer accountMedicationManagementScheduleId, Date reminderTime,
			Date dateAdded) {
		this.accountMedicationManagementScheduleId = accountMedicationManagementScheduleId;
		this.reminderTime = reminderTime;
		this.dateAdded = dateAdded;
	}

	public Integer getAccountMedicationManagementScheduleId() {
		return accountMedicationManagementScheduleId;
	}

	public void setAccountMedicationManagementScheduleId(Integer accountMedicationManagementScheduleId) {
		this.accountMedicationManagementScheduleId = accountMedicationManagementScheduleId;
	}

	public Date getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(Date reminderTime) {
		this.reminderTime = reminderTime;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public DatePart getDatePartId() {
		return datePartId;
	}

	public void setDatePartId(DatePart datePartId) {
		this.datePartId = datePartId;
	}

	public AccountMedicationManagementReminder getAccountMedicationManagementReminderId() {
		return accountMedicationManagementReminderId;
	}

	public void setAccountMedicationManagementReminderId(
			AccountMedicationManagementReminder accountMedicationManagementReminderId) {
		this.accountMedicationManagementReminderId = accountMedicationManagementReminderId;
	}

	public AccountMedicationManagement getAccountMedicationManagementId() {
		return accountMedicationManagementId;
	}

	public void setAccountMedicationManagementId(AccountMedicationManagement accountMedicationManagementId) {
		this.accountMedicationManagementId = accountMedicationManagementId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (accountMedicationManagementScheduleId != null ? accountMedicationManagementScheduleId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AccountMedicationManagementSchedule)) {
			return false;
		}
		AccountMedicationManagementSchedule other = (AccountMedicationManagementSchedule) object;
		if ((this.accountMedicationManagementScheduleId == null && other.accountMedicationManagementScheduleId != null)
				|| (this.accountMedicationManagementScheduleId != null && !this.accountMedicationManagementScheduleId
						.equals(other.accountMedicationManagementScheduleId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.AccountMedicationManagementSchedule[ accountMedicationManagementScheduleId="
				+ accountMedicationManagementScheduleId + " ]";
	}

}
