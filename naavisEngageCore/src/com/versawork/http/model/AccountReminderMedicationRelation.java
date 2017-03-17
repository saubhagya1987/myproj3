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
@Table(name = "account_reminder_medication_relation")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "AccountReminderMedicationRelation.findAll", query = "SELECT a FROM AccountReminderMedicationRelation a"),
		@NamedQuery(name = "AccountReminderMedicationRelation.findByAccountReminderMedicationRelationId", query = "SELECT a FROM AccountReminderMedicationRelation a WHERE a.accountReminderMedicationRelationId = :accountReminderMedicationRelationId"),
		@NamedQuery(name = "AccountReminderMedicationRelation.findByAlias", query = "SELECT a FROM AccountReminderMedicationRelation a WHERE a.alias = :alias"),
		@NamedQuery(name = "AccountReminderMedicationRelation.findByDateAdded", query = "SELECT a FROM AccountReminderMedicationRelation a WHERE a.dateAdded = :dateAdded"),
		@NamedQuery(name = "AccountReminderMedicationRelation.findByMedicationManagementId", query = "SELECT a FROM AccountReminderMedicationRelation a WHERE a.accountMedicationManagementId = :accountMedicationManagementId")})
public class AccountReminderMedicationRelation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_reminder_medication_relation_id")
	private Integer accountReminderMedicationRelationId;
	@Column(name = "alias")
	private String alias;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@JoinColumn(name = "account_medication_management_reminder_id", referencedColumnName = "account_medication_management_reminder_id")
	@ManyToOne(optional = false)
	private AccountMedicationManagementReminder accountMedicationManagementReminderId;
	@JoinColumn(name = "account_medication_management_id", referencedColumnName = "account_medication_management_id")
	@ManyToOne(optional = false)
	private AccountMedicationManagement accountMedicationManagementId;

	public AccountReminderMedicationRelation() {
	}

	public AccountReminderMedicationRelation(Integer accountReminderMedicationRelationId) {
		this.accountReminderMedicationRelationId = accountReminderMedicationRelationId;
	}

	public AccountReminderMedicationRelation(Integer accountReminderMedicationRelationId, Date dateAdded) {
		this.accountReminderMedicationRelationId = accountReminderMedicationRelationId;
		this.dateAdded = dateAdded;
	}

	public Integer getAccountReminderMedicationRelationId() {
		return accountReminderMedicationRelationId;
	}

	public void setAccountReminderMedicationRelationId(Integer accountReminderMedicationRelationId) {
		this.accountReminderMedicationRelationId = accountReminderMedicationRelationId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
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
		hash += (accountReminderMedicationRelationId != null ? accountReminderMedicationRelationId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AccountReminderMedicationRelation)) {
			return false;
		}
		AccountReminderMedicationRelation other = (AccountReminderMedicationRelation) object;
		if ((this.accountReminderMedicationRelationId == null && other.accountReminderMedicationRelationId != null)
				|| (this.accountReminderMedicationRelationId != null && !this.accountReminderMedicationRelationId
						.equals(other.accountReminderMedicationRelationId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.AccountReminderMedicationRelation[ accountReminderMedicationRelationId="
				+ accountReminderMedicationRelationId + " ]";
	}

}
