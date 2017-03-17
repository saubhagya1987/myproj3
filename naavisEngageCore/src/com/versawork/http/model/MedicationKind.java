/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "medication_kind")
@NamedQueries({
		@NamedQuery(name = "MedicationKind.findAll", query = "SELECT m FROM MedicationKind m"),
		@NamedQuery(name = "MedicationKind.findByMedicationKindId", query = "SELECT m FROM MedicationKind m WHERE m.medicationKindId = :medicationKindId"),
		@NamedQuery(name = "MedicationKind.findByMedicationKind", query = "SELECT m FROM MedicationKind m WHERE m.medicationKind = :medicationKind") })
public class MedicationKind implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "medication_kind_id")
	private Integer medicationKindId;
	@Column(name = "medication_kind")
	private String medicationKind;
	@Column(name = "medication_dose")
	private String medicationDose;
	@OneToMany(mappedBy = "medicationKindId")
	private List<AccountMedicationManagement> accountMedicationManagementList;

	public MedicationKind() {
	}

	public MedicationKind(Integer medicationKindId) {
		this.medicationKindId = medicationKindId;
	}

	public Integer getMedicationKindId() {
		return medicationKindId;
	}

	public void setMedicationKindId(Integer medicationKindId) {
		this.medicationKindId = medicationKindId;
	}

	public String getMedicationKind() {
		return medicationKind;
	}

	public void setMedicationKind(String medicationKind) {
		this.medicationKind = medicationKind;
	}

	public List<AccountMedicationManagement> getAccountMedicationManagementList() {
		return accountMedicationManagementList;
	}

	public void setAccountMedicationManagementList(List<AccountMedicationManagement> accountMedicationManagementList) {
		this.accountMedicationManagementList = accountMedicationManagementList;
	}

	public String getMedicationDose() {
		return medicationDose;
	}

	public void setMedicationDose(String medicationDose) {
		this.medicationDose = medicationDose;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (medicationKindId != null ? medicationKindId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MedicationKind)) {
			return false;
		}
		MedicationKind other = (MedicationKind) object;
		if ((this.medicationKindId == null && other.medicationKindId != null)
				|| (this.medicationKindId != null && !this.medicationKindId.equals(other.medicationKindId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.MedicationKind[ medicationKindId=" + medicationKindId + " ]";
	}

}
