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
@Table(name = "medication_method")
@NamedQueries({
		@NamedQuery(name = "MedicationMethod.findAll", query = "SELECT m FROM MedicationMethod m"),
		@NamedQuery(name = "MedicationMethod.findByMedicationMethodId", query = "SELECT m FROM MedicationMethod m WHERE m.medicationMethodId = :medicationMethodId"),
		@NamedQuery(name = "MedicationMethod.findByMedicationMethod", query = "SELECT m FROM MedicationMethod m WHERE m.medicationMethod = :medicationMethod") })
public class MedicationMethod implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "medication_method_id")
	private Integer medicationMethodId;
	@Column(name = "medication_method")
	private String medicationMethod;
	@OneToMany(mappedBy = "medicationMethodId")
	private List<AccountMedicationManagement> accountMedicationManagementList;

	public MedicationMethod() {
	}

	public MedicationMethod(Integer medicationMethodId) {
		this.medicationMethodId = medicationMethodId;
	}

	public Integer getMedicationMethodId() {
		return medicationMethodId;
	}

	public void setMedicationMethodId(Integer medicationMethodId) {
		this.medicationMethodId = medicationMethodId;
	}

	public String getMedicationMethod() {
		return medicationMethod;
	}

	public void setMedicationMethod(String medicationMethod) {
		this.medicationMethod = medicationMethod;
	}

	public List<AccountMedicationManagement> getAccountMedicationManagementList() {
		return accountMedicationManagementList;
	}

	public void setAccountMedicationManagementList(List<AccountMedicationManagement> accountMedicationManagementList) {
		this.accountMedicationManagementList = accountMedicationManagementList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (medicationMethodId != null ? medicationMethodId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MedicationMethod)) {
			return false;
		}
		MedicationMethod other = (MedicationMethod) object;
		if ((this.medicationMethodId == null && other.medicationMethodId != null)
				|| (this.medicationMethodId != null && !this.medicationMethodId.equals(other.medicationMethodId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.MedicationMethod[ medicationMethodId=" + medicationMethodId + " ]";
	}

}
