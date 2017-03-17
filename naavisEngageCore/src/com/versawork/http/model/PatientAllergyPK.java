/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Sohaib
 */
@Embeddable
public class PatientAllergyPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "allergy_id")
	private String allergyId;

	public PatientAllergyPK() {
	}

	public PatientAllergyPK(int accountId, String allergyId) {
		this.accountId = accountId;
		this.allergyId = allergyId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(String allergyId) {
		this.allergyId = allergyId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (allergyId != null ? allergyId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientAllergyPK)) {
			return false;
		}
		PatientAllergyPK other = (PatientAllergyPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if ((this.allergyId == null && other.allergyId != null)
				|| (this.allergyId != null && !this.allergyId.equals(other.allergyId))) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientAllergyPK[ accountId=" + accountId + ", allergyId=" + allergyId + " ]";
	}

}
