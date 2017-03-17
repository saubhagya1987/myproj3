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
public class PatientFunctionalStatusPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "patient_visit_id")
	private int patientVisitId;
	@Basic(optional = false)
	@Column(name = "function_id")
	private String functionId;
	@Basic(optional = false)
	@Column(name = "function_id2")
	private String functionId2;

	public PatientFunctionalStatusPK() {
	}

	public PatientFunctionalStatusPK(int accountId, int patientVisitId, String functionId, String functionId2) {
		this.accountId = accountId;
		this.patientVisitId = patientVisitId;
		this.functionId = functionId;
		this.functionId2 = functionId2;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getPatientVisitId() {
		return patientVisitId;
	}

	public void setPatientVisitId(int patientVisitId) {
		this.patientVisitId = patientVisitId;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getFunctionId2() {
		return functionId2;
	}

	public void setFunctionId2(String functionId2) {
		this.functionId2 = functionId2;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (int) patientVisitId;
		hash += (functionId != null ? functionId.hashCode() : 0);
		hash += (functionId2 != null ? functionId2.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientFunctionalStatusPK)) {
			return false;
		}
		PatientFunctionalStatusPK other = (PatientFunctionalStatusPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if (this.patientVisitId != other.patientVisitId) {
			return false;
		}
		if ((this.functionId == null && other.functionId != null)
				|| (this.functionId != null && !this.functionId.equals(other.functionId))) {
			return false;
		}
		if ((this.functionId2 == null && other.functionId2 != null)
				|| (this.functionId2 != null && !this.functionId2.equals(other.functionId2))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.traceOn.http.model.PatientFunctionalStatusPK[ accountId=" + accountId + ", patientVisitId="
				+ patientVisitId + ", functionId=" + functionId + ", functionId2=" + functionId2 + " ]";
	}

}
