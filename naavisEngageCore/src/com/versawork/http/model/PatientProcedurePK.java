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
public class PatientProcedurePK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "procedure_id")
	private String procedureId;
	@Basic(optional = false)
	@Column(name = "patient_visit_id")
	private int patientVisitId;
	@Basic(optional = false)
	@Column(name = "procedure_id2")
	private String procedureId2;

	public PatientProcedurePK() {
	}

	public PatientProcedurePK(int accountId, String procedureId, int patientVisitId, String procedureId2) {
		this.accountId = accountId;
		this.procedureId = procedureId;
		this.patientVisitId = patientVisitId;
		this.procedureId2 = procedureId2;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
	}

	public int getPatientVisitId() {
		return patientVisitId;
	}

	public void setPatientVisitId(int patientVisitId) {
		this.patientVisitId = patientVisitId;
	}

	public String getProcedureId2() {
		return procedureId2;
	}

	public void setProcedureId2(String procedureId2) {
		this.procedureId2 = procedureId2;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (procedureId != null ? procedureId.hashCode() : 0);
		hash += (int) patientVisitId;
		hash += (procedureId2 != null ? procedureId2.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientProcedurePK)) {
			return false;
		}
		PatientProcedurePK other = (PatientProcedurePK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if ((this.procedureId == null && other.procedureId != null)
				|| (this.procedureId != null && !this.procedureId.equals(other.procedureId))) {
			return false;
		}
		if (this.patientVisitId != other.patientVisitId) {
			return false;
		}
		if ((this.procedureId2 == null && other.procedureId2 != null)
				|| (this.procedureId2 != null && !this.procedureId2.equals(other.procedureId2))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientProcedurePK[ accountId=" + accountId + ", procedureId=" + procedureId
				+ ", patientVisitId=" + patientVisitId + ", procedureId2=" + procedureId2 + " ]";
	}

}
