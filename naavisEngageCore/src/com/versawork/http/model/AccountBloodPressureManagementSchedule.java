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

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "account_blood_pressure_management_schedule")
@NamedQueries({
		@NamedQuery(name = "AccountBloodPressureManagementSchedule.findAll", query = "SELECT a FROM AccountBloodPressureManagementSchedule a"),
		@NamedQuery(name = "AccountBloodPressureManagementSchedule.findByAccountBloodPressureManagementScheduleId", query = "SELECT a FROM AccountBloodPressureManagementSchedule a WHERE a.accountBloodPressureManagementScheduleId = :accountBloodPressureManagementScheduleId"),
		@NamedQuery(name = "AccountBloodPressureManagementSchedule.findByReminderTime", query = "SELECT a FROM AccountBloodPressureManagementSchedule a WHERE a.reminderTime = :reminderTime"),
		@NamedQuery(name = "AccountBloodPressureManagementSchedule.findByInterval", query = "SELECT a FROM AccountBloodPressureManagementSchedule a WHERE a.interval = :interval"),
		@NamedQuery(name = "AccountBloodPressureManagementSchedule.findByDateAdded", query = "SELECT a FROM AccountBloodPressureManagementSchedule a WHERE a.dateAdded = :dateAdded") })
public class AccountBloodPressureManagementSchedule implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_blood_pressure_management_schedule_id")
	private Integer accountBloodPressureManagementScheduleId;
	@Basic(optional = false)
	@Column(name = "reminder_time")
	@Temporal(TemporalType.TIME)
	private Date reminderTime;
	@Column(name = "interval")
	private Integer interval;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@JoinColumn(name = "date_part_id", referencedColumnName = "date_part_id")
	@ManyToOne
	private DatePart datePartId;
	@JoinColumn(name = "account_blood_pressure_management_id", referencedColumnName = "account_blood_pressure_management_id")
	@ManyToOne
	private AccountBloodPressureManagement accountBloodPressureManagementId;

	public AccountBloodPressureManagementSchedule() {
	}

	public AccountBloodPressureManagementSchedule(Integer accountBloodPressureManagementScheduleId) {
		this.accountBloodPressureManagementScheduleId = accountBloodPressureManagementScheduleId;
	}

	public AccountBloodPressureManagementSchedule(Integer accountBloodPressureManagementScheduleId, Date reminderTime,
			Date dateAdded) {
		this.accountBloodPressureManagementScheduleId = accountBloodPressureManagementScheduleId;
		this.reminderTime = reminderTime;
		this.dateAdded = dateAdded;
	}

	public Integer getAccountBloodPressureManagementScheduleId() {
		return accountBloodPressureManagementScheduleId;
	}

	public void setAccountBloodPressureManagementScheduleId(Integer accountBloodPressureManagementScheduleId) {
		this.accountBloodPressureManagementScheduleId = accountBloodPressureManagementScheduleId;
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

	public AccountBloodPressureManagement getAccountBloodPressureManagementId() {
		return accountBloodPressureManagementId;
	}

	public void setAccountBloodPressureManagementId(AccountBloodPressureManagement accountBloodPressureManagementId) {
		this.accountBloodPressureManagementId = accountBloodPressureManagementId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (accountBloodPressureManagementScheduleId != null ? accountBloodPressureManagementScheduleId.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AccountBloodPressureManagementSchedule)) {
			return false;
		}
		AccountBloodPressureManagementSchedule other = (AccountBloodPressureManagementSchedule) object;
		if ((this.accountBloodPressureManagementScheduleId == null && other.accountBloodPressureManagementScheduleId != null)
				|| (this.accountBloodPressureManagementScheduleId != null && !this.accountBloodPressureManagementScheduleId
						.equals(other.accountBloodPressureManagementScheduleId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.AccountBloodPressureManagementSchedule[ accountBloodPressureManagementScheduleId="
				+ accountBloodPressureManagementScheduleId + " ]";
	}

}
