/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Dheeraj
 */
@Entity
@Table(name = "account_notification_history")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "AccountNotificationHistory.findAll", query = "SELECT a FROM AccountNotificationHistory a"),
		@NamedQuery(name = "AccountNotificationHistory.findByAccountNotificationHistoryId", query = "SELECT a FROM AccountNotificationHistory a WHERE a.accountNotificationHistoryId = :accountNotificationHistoryId"),
		@NamedQuery(name = "AccountNotificationHistory.findByAccountId", query = "SELECT a FROM AccountNotificationHistory a WHERE a.accountId = :accountId"),
		@NamedQuery(name = "AccountNotificationHistory.findByEffectiveModuleId", query = "SELECT a FROM AccountNotificationHistory a WHERE a.effectiveModuleId = :effectiveModuleId"),
		@NamedQuery(name = "AccountNotificationHistory.findByAccountIdEffectiveModuleId", query = "SELECT a FROM AccountNotificationHistory a WHERE a.accountId = :accountId and a.effectiveModuleId = :effectiveModuleId"),
		@NamedQuery(name = "AccountNotificationHistory.findByClientDatabaseId", query = "SELECT a FROM AccountNotificationHistory a WHERE a.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "AccountNotificationHistory.findByClientDatabaseIdIsviewedSchedDate", query = "SELECT a FROM AccountNotificationHistory a WHERE a.clientDatabaseId = :clientDatabaseId and a.notificationTypeId = :notificationTypeId and a.isviewedFlag is false and convert(varchar(10),a.effectiveModuleDate,101) between :presentDay and :currentPlusTwo ORDER BY a.effectiveModuleDate DESC"),
		@NamedQuery(name = "AccountNotificationHistory.findByIsviewedFlag", query = "SELECT a FROM AccountNotificationHistory a WHERE a.isviewedFlag = :isviewedFlag"),
		@NamedQuery(name = "AccountNotificationHistory.findByNotificationMessage", query = "SELECT a FROM AccountNotificationHistory a WHERE a.notificationMessage = :notificationMessage"),
		@NamedQuery(name = "AccountNotificationHistory.findByEffectiveModuleDate", query = "SELECT a FROM AccountNotificationHistory a WHERE a.effectiveModuleDate = :effectiveModuleDate"),
		@NamedQuery(name = "AccountNotificationHistory.findByDateAdded", query = "SELECT a FROM AccountNotificationHistory a WHERE a.dateAdded = :dateAdded") })
public class AccountNotificationHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_notification_history_id")
	private Integer accountNotificationHistoryId;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "effective_module_id")
	private int effectiveModuleId;
	@Basic(optional = false)
	@Column(name = "client_database_id")
	private int clientDatabaseId;
	@Basic(optional = false)
	@Column(name = "isviewed_flag")
	private boolean isviewedFlag;
	@Basic(optional = false)
	@Column(name = "notification_message")
	private String notificationMessage;
	@Basic(optional = false)
	@Column(name = "effective_module_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date effectiveModuleDate;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@JoinColumn(name = "notification_type_id", referencedColumnName = "notification_type_id")
	@ManyToOne(optional = false)
	private NotificationType notificationTypeId;

	public AccountNotificationHistory() {
	}

	public AccountNotificationHistory(Integer accountNotificationHistoryId) {
		this.accountNotificationHistoryId = accountNotificationHistoryId;
	}

	public AccountNotificationHistory(Integer accountNotificationHistoryId, int accountId, int effectiveModuleId,
			int clientDatabaseId, boolean isviewedFlag, String notificationMessage, Date effectiveModuleDate,
			Date dateAdded) {
		this.accountNotificationHistoryId = accountNotificationHistoryId;
		this.accountId = accountId;
		this.effectiveModuleId = effectiveModuleId;
		this.clientDatabaseId = clientDatabaseId;
		this.isviewedFlag = isviewedFlag;
		this.notificationMessage = notificationMessage;
		this.effectiveModuleDate = effectiveModuleDate;
		this.dateAdded = dateAdded;
	}

	public Integer getAccountNotificationHistoryId() {
		return accountNotificationHistoryId;
	}

	public void setAccountNotificationHistoryId(Integer accountNotificationHistoryId) {
		this.accountNotificationHistoryId = accountNotificationHistoryId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getEffectiveModuleId() {
		return effectiveModuleId;
	}

	public void setEffectiveModuleId(int effectiveModuleId) {
		this.effectiveModuleId = effectiveModuleId;
	}

	public int getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(int clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	public boolean getIsviewedFlag() {
		return isviewedFlag;
	}

	public void setIsviewedFlag(boolean isviewedFlag) {
		this.isviewedFlag = isviewedFlag;
	}

	public String getNotificationMessage() {
		return notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	public Date getEffectiveModuleDate() {
		return effectiveModuleDate;
	}

	public void setEffectiveModuleDate(Date effectiveModuleDate) {
		this.effectiveModuleDate = effectiveModuleDate;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public NotificationType getNotificationTypeId() {
		return notificationTypeId;
	}

	public void setNotificationTypeId(NotificationType notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (accountNotificationHistoryId != null ? accountNotificationHistoryId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AccountNotificationHistory)) {
			return false;
		}
		AccountNotificationHistory other = (AccountNotificationHistory) object;
		if ((this.accountNotificationHistoryId == null && other.accountNotificationHistoryId != null)
				|| (this.accountNotificationHistoryId != null && !this.accountNotificationHistoryId
						.equals(other.accountNotificationHistoryId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "javaapplication2.AccountNotificationHistory[ accountNotificationHistoryId="
				+ accountNotificationHistoryId + " ]";
	}

}
