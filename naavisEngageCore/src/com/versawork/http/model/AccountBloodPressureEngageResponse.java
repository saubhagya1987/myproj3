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
 * @author Sohaib
 */
@Entity
@Table(name = "account_blood_pressure_engage_response")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "AccountBloodPressureEngageResponse.findAll", query = "SELECT a FROM AccountBloodPressureEngageResponse a"),
		@NamedQuery(name = "AccountBloodPressureEngageResponse.findByAccountBpManagementId", query = "SELECT a FROM AccountBloodPressureEngageResponse a WHERE a.accountBloodPressureManagementId = :accountBloodPressureManagementId"),
		@NamedQuery(name = "AccountBloodPressureEngageResponse.findByRecordIdentifier", query = "SELECT a FROM AccountBloodPressureEngageResponse a WHERE a.accountId = :accountId and a.accountBloodPressureManagementId = :accountBloodPressureManagementId and a.recordIdentifier = :recordIdentifier"),
		@NamedQuery(name = "AccountBloodPressureEngageResponse.findByAccountBloodPressureEngageResponseId", query = "SELECT a FROM AccountBloodPressureEngageResponse a WHERE a.accountBloodPressureEngageResponseId = :accountBloodPressureEngageResponseId"),
		@NamedQuery(name = "AccountBloodPressureEngageResponse.findByAccountId", query = "SELECT a FROM AccountBloodPressureEngageResponse a WHERE a.accountId = :accountId"),
		@NamedQuery(name = "AccountBloodPressureEngageResponse.findByResponseId", query = "SELECT a FROM AccountBloodPressureEngageResponse a WHERE a.responseId = :responseId"),
		@NamedQuery(name = "AccountBloodPressureEngageResponse.findByReminderDate", query = "SELECT a FROM AccountBloodPressureEngageResponse a WHERE a.reminderDate = :reminderDate"),
		@NamedQuery(name = "AccountBloodPressureEngageResponse.findByComment", query = "SELECT a FROM AccountBloodPressureEngageResponse a WHERE a.comment = :comment"),
		@NamedQuery(name = "AccountBloodPressureEngageResponse.findByDateAdded", query = "SELECT a FROM AccountBloodPressureEngageResponse a WHERE a.dateAdded = :dateAdded"),
		@NamedQuery(name = "AccountBloodPressureEngageResponse.findByAccountBpManagementIdCurrentDate", query = "SELECT a FROM AccountBloodPressureEngageResponse a WHERE a.accountBloodPressureManagementId = :accountBloodPressureManagementId and convert(varchar(10),a.dateAdded,101) between :pastDays and :todaysDate"),
		@NamedQuery(name = "AccountBloodPressureEngageResponse.findByAccountBPActIdCurrentDate7", query = "SELECT a FROM AccountBloodPressureEngageResponse a WHERE a.accountId = :accountId and convert(varchar(10),a.dateAdded,101) between :past7Days and :todaysDate ORDER BY a.dateAdded DESC"), })
public class AccountBloodPressureEngageResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_blood_pressure_engage_response_id")
	private Integer accountBloodPressureEngageResponseId;
	@Basic(optional = false)
	@Column(name = "account_id")
	private Integer accountId;
	@Basic(optional = false)
	@Column(name = "response_id")
	private int responseId;
	@Basic(optional = false)
	@Column(name = "reminder_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reminderDate;
	@Column(name = "record_identifier")
	private String recordIdentifier;
	@Column(name = "comment")
	private String comment;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@JoinColumn(name = "account_blood_pressure_management_id", referencedColumnName = "account_blood_pressure_management_id")
	@ManyToOne
	private AccountBloodPressureManagement accountBloodPressureManagementId;

	public AccountBloodPressureEngageResponse() {
	}

	public AccountBloodPressureEngageResponse(Integer accountBloodPressureEngageResponseId) {
		this.accountBloodPressureEngageResponseId = accountBloodPressureEngageResponseId;
	}

	public AccountBloodPressureEngageResponse(Integer accountBloodPressureEngageResponseId, int accountId,
			int responseId, Date reminderDate, Date dateAdded) {
		this.accountBloodPressureEngageResponseId = accountBloodPressureEngageResponseId;
		this.accountId = accountId;
		this.responseId = responseId;
		this.reminderDate = reminderDate;
		this.dateAdded = dateAdded;
	}

	public Integer getAccountBloodPressureEngageResponseId() {
		return accountBloodPressureEngageResponseId;
	}

	public String getRecordIdentifier() {
		return recordIdentifier;
	}

	public void setRecordIdentifier(String recordIdentifier) {
		this.recordIdentifier = recordIdentifier;
	}

	public void setAccountBloodPressureEngageResponseId(Integer accountBloodPressureEngageResponseId) {
		this.accountBloodPressureEngageResponseId = accountBloodPressureEngageResponseId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getResponseId() {
		return responseId;
	}

	public void setResponseId(int responseId) {
		this.responseId = responseId;
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

	public AccountBloodPressureManagement getAccountBloodPressureManagementId() {
		return accountBloodPressureManagementId;
	}

	public void setAccountBloodPressureManagementId(AccountBloodPressureManagement accountBloodPressureManagementId) {
		this.accountBloodPressureManagementId = accountBloodPressureManagementId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (accountBloodPressureEngageResponseId != null ? accountBloodPressureEngageResponseId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AccountBloodPressureEngageResponse)) {
			return false;
		}
		AccountBloodPressureEngageResponse other = (AccountBloodPressureEngageResponse) object;
		if ((this.accountBloodPressureEngageResponseId == null && other.accountBloodPressureEngageResponseId != null)
				|| (this.accountBloodPressureEngageResponseId != null && !this.accountBloodPressureEngageResponseId
						.equals(other.accountBloodPressureEngageResponseId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.AccountBloodPressureEngageResponse[ accountBloodPressureEngageResponseId="
				+ accountBloodPressureEngageResponseId + " ]";
	}

}
