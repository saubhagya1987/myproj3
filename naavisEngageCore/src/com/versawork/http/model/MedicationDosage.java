/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "medication_dosage")
@NamedQueries({
		@NamedQuery(name = "MedicationDosage.findAll", query = "SELECT m FROM MedicationDosage m"),
		@NamedQuery(name = "MedicationDosage.findByMedicationDosageId", query = "SELECT m FROM MedicationDosage m WHERE m.medicationDosageId = :medicationDosageId"),
		@NamedQuery(name = "MedicationDosage.findByMedicationDosage", query = "SELECT m FROM MedicationDosage m WHERE m.medicationDosage = :medicationDosage") })
public class MedicationDosage implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "medication_dosage_id")
	private Integer medicationDosageId;
	@Column(name = "medication_dosage")
	private String medicationDosage;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "medicationDosageId")
	private List<AccountMedicationManagementReminder> accountMedicationManagementReminderList;

	public MedicationDosage() {
	}

	public MedicationDosage(Integer medicationDosageId) {
		this.medicationDosageId = medicationDosageId;
	}

	public Integer getMedicationDosageId() {
		return medicationDosageId;
	}

	public void setMedicationDosageId(Integer medicationDosageId) {
		this.medicationDosageId = medicationDosageId;
	}

	public String getMedicationDosage() {
		return medicationDosage;
	}

	public void setMedicationDosage(String medicationDosage) {
		this.medicationDosage = medicationDosage;
	}

	public List<AccountMedicationManagementReminder> getAccountMedicationManagementReminderList() {
		return accountMedicationManagementReminderList;
	}

	public void setAccountMedicationManagementReminderList(
			List<AccountMedicationManagementReminder> accountMedicationManagementReminderList) {
		this.accountMedicationManagementReminderList = accountMedicationManagementReminderList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (medicationDosageId != null ? medicationDosageId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MedicationDosage)) {
			return false;
		}
		MedicationDosage other = (MedicationDosage) object;
		if ((this.medicationDosageId == null && other.medicationDosageId != null)
				|| (this.medicationDosageId != null && !this.medicationDosageId.equals(other.medicationDosageId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.MedicationDosage[ medicationDosageId=" + medicationDosageId + " ]";
	}

}
