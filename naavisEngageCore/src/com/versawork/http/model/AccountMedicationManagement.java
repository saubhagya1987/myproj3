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
import javax.persistence.Lob;
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
@Table(name = "account_medication_management")
@NamedQueries({
		@NamedQuery(name = "AccountMedicationManagement.findAll", query = "SELECT a FROM AccountMedicationManagement a"),
		@NamedQuery(name = "AccountMedicationManagement.findByAccountMedicationManagementId", query = "SELECT a FROM AccountMedicationManagement a WHERE a.accountMedicationManagementId = :accountMedicationManagementId"),
		@NamedQuery(name = "AccountMedicationManagement.findByMedicationName", query = "SELECT a FROM AccountMedicationManagement a WHERE a.medicationName = :medicationName"),
		@NamedQuery(name = "AccountMedicationManagement.findByRxNumber", query = "SELECT a FROM AccountMedicationManagement a WHERE a.rxNumber = :rxNumber"),
		@NamedQuery(name = "AccountMedicationManagement.findByNotes", query = "SELECT a FROM AccountMedicationManagement a WHERE a.notes = :notes"),
		@NamedQuery(name = "AccountMedicationManagement.findByDateAdded", query = "SELECT a FROM AccountMedicationManagement a WHERE a.dateAdded = :dateAdded"),
		@NamedQuery(name = "AccountMedicationManagement.findByAccountMedicationManagementIdActId", query = "SELECT a FROM AccountMedicationManagement a WHERE a.accountMedicationManagementId = :accountMedicationManagementId AND a.accountId = :accountId"),
		@NamedQuery(name = "AccountMedicationManagement.findByAccountId", query = "SELECT a FROM AccountMedicationManagement a WHERE a.accountId = :accountId"),
		@NamedQuery(name = "AccountMedicationManagement.findByMedicationNameActId", query = "SELECT a FROM AccountMedicationManagement a WHERE a.medicationName = :medicationName and a.accountId = :accountId") })
public class AccountMedicationManagement implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_medication_management_id")
	private Integer accountMedicationManagementId;
	@Basic(optional = false)
	@Column(name = "medication_name")
	private String medicationName;
	@Basic(optional = false)
	@Column(name = "rx_number")
	private String rxNumber;
	@Column(name = "notes")
	private String notes;
	@Lob
	@Column(name = "medication_image")
	private byte[] medicationImage;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	/*@JoinColumn(name = "medication_method_id", referencedColumnName = "medication_method_id")
	@ManyToOne
	private MedicationMethod medicationMethodId;*/
	@Basic(optional = false)
	@Column(name = "medication_method_id")
	private Integer medicationMethodId;
	@JoinColumn(name = "medication_kind_id", referencedColumnName = "medication_kind_id")
	@ManyToOne
	private MedicationKind medicationKindId;
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	@ManyToOne(optional = false)
	private Account accountId;
	@OneToMany(mappedBy = "accountMedicationManagementId")
	private List<AccountMedicationManagementSchedule> accountMedicationManagementScheduleList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountMedicationManagementId")
	private List<AccountReminderMedicationRelation> accountReminderMedicationRelationList;
	
	@Column(name = "dosage")
	private Float dosage;
	
	@Basic(optional = false)
	@Column(name = "refill_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date refillDate;
	
	@Column(name = "is_reminder")
	private Boolean isReminder;

	

	public Float getDosage() {
		return dosage;
	}

	public void setDosage(Float dosage) {
		this.dosage = dosage;
	}

	public Date getRefillDate() {
		return refillDate;
	}

	public void setRefillDate(Date refillDate) {
		this.refillDate = refillDate;
	}

	public Boolean getIsReminder() {
		return isReminder;
	}

	public void setIsReminder(Boolean isReminder) {
		this.isReminder = isReminder;
	}

	public AccountMedicationManagement() {
	}

	public AccountMedicationManagement(Integer accountMedicationManagementId) {
		this.accountMedicationManagementId = accountMedicationManagementId;
	}

	public AccountMedicationManagement(Integer accountMedicationManagementId, String medicationName, String rxNumber,
			Date dateAdded) {
		this.accountMedicationManagementId = accountMedicationManagementId;
		this.medicationName = medicationName;
		this.rxNumber = rxNumber;
		this.dateAdded = dateAdded;
	}

	public Integer getAccountMedicationManagementId() {
		return accountMedicationManagementId;
	}

	public void setAccountMedicationManagementId(Integer accountMedicationManagementId) {
		this.accountMedicationManagementId = accountMedicationManagementId;
	}

	public byte[] getMedicationImage() {
		return medicationImage;
	}

	public void setMedicationImage(byte[] medicationImage) {
		this.medicationImage = medicationImage;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getRxNumber() {
		return rxNumber;
	}

	public void setRxNumber(String rxNumber) {
		this.rxNumber = rxNumber;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	/*public MedicationMethod getMedicationMethodId() {
		return medicationMethodId;
	}

	public void setMedicationMethodId(MedicationMethod medicationMethodId) {
		this.medicationMethodId = medicationMethodId;
	}*/

	public MedicationKind getMedicationKindId() {
		return medicationKindId;
	}

	public void setMedicationKindId(MedicationKind medicationKindId) {
		this.medicationKindId = medicationKindId;
	}

	public Account getAccountId() {
		return accountId;
	}

	public void setAccountId(Account accountId) {
		this.accountId = accountId;
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

	public Integer getMedicationMethodId() {
		return medicationMethodId;
	}

	public void setMedicationMethodId(Integer medicationMethodId) {
		this.medicationMethodId = medicationMethodId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (accountMedicationManagementId != null ? accountMedicationManagementId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AccountMedicationManagement)) {
			return false;
		}
		AccountMedicationManagement other = (AccountMedicationManagement) object;
		if ((this.accountMedicationManagementId == null && other.accountMedicationManagementId != null)
				|| (this.accountMedicationManagementId != null && !this.accountMedicationManagementId
						.equals(other.accountMedicationManagementId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.AccountMedicationManagement[ accountMedicationManagementId="
				+ accountMedicationManagementId + " ]";
	}

}
