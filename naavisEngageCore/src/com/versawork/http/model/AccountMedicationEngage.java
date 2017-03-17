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
@Table(name = "account_medication_engage")
@NamedQueries({
		@NamedQuery(name = "AccountMedicationEngage.findAll", query = "SELECT a FROM AccountMedicationEngage a"),
		@NamedQuery(name = "AccountMedicationEngage.findByAccountMedicationEngageId", query = "SELECT a FROM AccountMedicationEngage a WHERE a.accountMedicationEngageId = :accountMedicationEngageId"),
		@NamedQuery(name = "AccountMedicationEngage.findByRecordIdentifier", query = "SELECT a FROM AccountMedicationEngage a WHERE a.accountId = :accountId and a.accountMedicationManagementReminderId = :accountMedicationManagementReminderId and a.recordIdentifier = :recordIdentifier"),
		@NamedQuery(name = "AccountMedicationEngage.findByAccountId", query = "SELECT a FROM AccountMedicationEngage a WHERE a.accountId = :accountId"),

		@NamedQuery(name = "AccountMedicationEngage.findByAccountIdTodayDate", query = "SELECT a FROM AccountMedicationEngage a WHERE a.accountId = :accountId and a.dateAdded = convert(varchar(10),getutcdate(),110) "),

		@NamedQuery(name = "AccountMedicationEngage.findByAccountMedicationManagementReminderId", query = "SELECT a FROM AccountMedicationEngage a WHERE a.accountMedicationManagementReminderId = :accountMedicationManagementReminderId"),
		@NamedQuery(name = "AccountMedicationEngage.findByResponseId", query = "SELECT a FROM AccountMedicationEngage a WHERE a.responseId = :responseId"),
		@NamedQuery(name = "AccountMedicationEngage.findByReminderDate", query = "SELECT a FROM AccountMedicationEngage a WHERE a.reminderDate = :reminderDate"),
		@NamedQuery(name = "AccountMedicationEngage.findByComment", query = "SELECT a FROM AccountMedicationEngage a WHERE a.comment = :comment"),
		@NamedQuery(name = "AccountMedicationEngage.findByFromToDate", query = "SELECT a FROM AccountMedicationEngage a WHERE a.accountId = :accountId and convert(varchar(10),a.reminderDate,101)  between :fromDate and :toDate ORDER BY a.dateAdded DESC"),
		@NamedQuery(name = "AccountMedicationEngage.findByAccountMedicationManagementRemIdCurrentDate", query = "SELECT a FROM AccountMedicationEngage a WHERE a.accountMedicationManagementReminderId = :accountMedicationManagementReminderId and convert(varchar(10),a.dateAdded,101) between :past3Days and :todaysDate ORDER BY a.dateAdded DESC"),
		@NamedQuery(name = "AccountMedicationEngage.findByDateAdded", query = "SELECT a FROM AccountMedicationEngage a WHERE a.dateAdded = :dateAdded") })
public class AccountMedicationEngage implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_medication_engage_id")
	private Integer accountMedicationEngageId;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
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
	@Column(name = "record_identifier")
	private String recordIdentifier;
	@JoinColumn(name = "response_id", referencedColumnName = "response_id")
	@ManyToOne(optional = false)
	private Response responseId;
	@JoinColumn(name = "account_medication_management_reminder_id", referencedColumnName = "account_medication_management_reminder_id")
	@ManyToOne
	private AccountMedicationManagementReminder accountMedicationManagementReminderId;

	public AccountMedicationEngage() {
	}

	public String getRecordIdentifier() {
		return recordIdentifier;
	}

	public void setRecordIdentifier(String recordIdentifier) {
		this.recordIdentifier = recordIdentifier;
	}

	public AccountMedicationEngage(Integer accountMedicationEngageId) {
		this.accountMedicationEngageId = accountMedicationEngageId;
	}

	public AccountMedicationEngage(Integer accountMedicationEngageId, int accountId, Date reminderDate, Date dateAdded) {
		this.accountMedicationEngageId = accountMedicationEngageId;
		this.accountId = accountId;
		this.reminderDate = reminderDate;
		this.dateAdded = dateAdded;
	}

	public Integer getAccountMedicationEngageId() {
		return accountMedicationEngageId;
	}

	public void setAccountMedicationEngageId(Integer accountMedicationEngageId) {
		this.accountMedicationEngageId = accountMedicationEngageId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
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

	public Response getResponseId() {
		return responseId;
	}

	public void setResponseId(Response responseId) {
		this.responseId = responseId;
	}

	public AccountMedicationManagementReminder getAccountMedicationManagementReminderId() {
		return accountMedicationManagementReminderId;
	}

	public void setAccountMedicationManagementReminderId(
			AccountMedicationManagementReminder accountMedicationManagementReminderId) {
		this.accountMedicationManagementReminderId = accountMedicationManagementReminderId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (accountMedicationEngageId != null ? accountMedicationEngageId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AccountMedicationEngage)) {
			return false;
		}
		AccountMedicationEngage other = (AccountMedicationEngage) object;
		if ((this.accountMedicationEngageId == null && other.accountMedicationEngageId != null)
				|| (this.accountMedicationEngageId != null && !this.accountMedicationEngageId
						.equals(other.accountMedicationEngageId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.AccountMedicationEngage[ accountMedicationEngageId="
				+ accountMedicationEngageId + " ]";
	}

}
