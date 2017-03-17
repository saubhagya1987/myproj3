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
@Table(name = "account_blood_pressure_engage")
@NamedQueries({
		@NamedQuery(name = "AccountBloodPressureEngage.findAll", query = "SELECT a FROM AccountBloodPressureEngage a"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByAccountBloodPressureEngageId", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.accountBloodPressureEngageId = :accountBloodPressureEngageId"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByAccountId", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.accountId = :accountId ORDER BY a.dateAdded DESC"),
		@NamedQuery(name = "AccountBloodPressureEngage.findBySys", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.sys = :sys"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByDia", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.dia = :dia"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByPulse", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.pulse = :pulse"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByReminderDate", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.reminderDate = :reminderDate"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByComment", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.comment = :comment"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByDateAdded", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.dateAdded = :dateAdded"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByDeleteFlag", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.deleteFlag = :deleteFlag"),
		//@NamedQuery(name = "AccountBloodPressureEngage.findByMaxBloodpressuretId", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.accountId = :accountId ORDER BY a.accountBloodPressureEngageId DESC"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByMaxBloodpressuretId", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.accountId = :accountId and a.accountBloodPressureEngageId=(select max(p1.accountBloodPressureEngageId) from AccountBloodPressureEngage p1 where p1.accountId = :accountId ) "),
		@NamedQuery(name = "AccountBloodPressureEngage.findByAccountIdBloodPressureId", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.accountBloodPressureEngageId = :accountBloodPressureEngageId AND a.accountId = :accountId"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByFromToDate", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.accountId = :accountId and convert(varchar(10),a.reminderDate,101)  between :fromDate and :toDate ORDER BY a.dateAdded DESC"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByAccountBpManagementIdCurrentDate", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.accountBloodPressureManagementId = :accountBloodPressureManagementId and convert(varchar(10),a.dateAdded,101) between :pastDays and :todaysDate"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByAccountIdReminderDate", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.accountId = :accountId and a.reminderDate  BETWEEN :startDate AND :endDate ORDER BY a.reminderDate DESC"),
		@NamedQuery(name = "AccountBloodPressureEngage.findByAccountIdWithLatestBp", query = "SELECT a FROM AccountBloodPressureEngage a WHERE a.accountId = :accountId ORDER BY a.dateAdded DESC")})
public class AccountBloodPressureEngage implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_blood_pressure_engage_id")
	private Integer accountBloodPressureEngageId;
	@Basic(optional = false)
	@Column(name = "sys")
	private int sys;
	@Basic(optional = false)
	@Column(name = "dia")
	private int dia;
	@Basic(optional = false)
	@Column(name = "pulse")
	private int pulse;
	@Basic(optional = false)
	@Column(name = "reminder_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reminderDate;
	@Column(name = "comment")
	private String comment;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "delete_flag")
	private Boolean deleteFlag;
	@JoinColumn(name = "account_blood_pressure_management_id", referencedColumnName = "account_blood_pressure_management_id")
	@ManyToOne
	private AccountBloodPressureManagement accountBloodPressureManagementId;
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	@ManyToOne(optional = false)
	private Account accountId;

	public AccountBloodPressureEngage() {
	}

	public AccountBloodPressureEngage(Integer accountBloodPressureEngageId) {
		this.accountBloodPressureEngageId = accountBloodPressureEngageId;
	}

	public AccountBloodPressureEngage(Integer accountBloodPressureEngageId, int sys, int dia, int pulse,
			Date reminderDate, Date dateAdded) {
		this.accountBloodPressureEngageId = accountBloodPressureEngageId;
		this.sys = sys;
		this.dia = dia;
		this.pulse = pulse;
		this.reminderDate = reminderDate;
		this.dateAdded = dateAdded;
	}

	public Integer getAccountBloodPressureEngageId() {
		return accountBloodPressureEngageId;
	}

	public void setAccountBloodPressureEngageId(Integer accountBloodPressureEngageId) {
		this.accountBloodPressureEngageId = accountBloodPressureEngageId;
	}

	public int getSys() {
		return sys;
	}

	public void setSys(int sys) {
		this.sys = sys;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getPulse() {
		return pulse;
	}

	public void setPulse(int pulse) {
		this.pulse = pulse;
	}

	public Date getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(Date reminderDate) {
		this.reminderDate = reminderDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public AccountBloodPressureManagement getAccountBloodPressureManagementId() {
		return accountBloodPressureManagementId;
	}

	public void setAccountBloodPressureManagementId(AccountBloodPressureManagement accountBloodPressureManagementId) {
		this.accountBloodPressureManagementId = accountBloodPressureManagementId;
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
		hash += (accountBloodPressureEngageId != null ? accountBloodPressureEngageId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AccountBloodPressureEngage)) {
			return false;
		}
		AccountBloodPressureEngage other = (AccountBloodPressureEngage) object;
		if ((this.accountBloodPressureEngageId == null && other.accountBloodPressureEngageId != null)
				|| (this.accountBloodPressureEngageId != null && !this.accountBloodPressureEngageId
						.equals(other.accountBloodPressureEngageId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.AccountBloodPressureEngage[ accountBloodPressureEngageId="
				+ accountBloodPressureEngageId + " ]";
	}

}
