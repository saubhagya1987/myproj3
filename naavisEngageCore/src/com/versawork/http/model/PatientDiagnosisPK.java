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
public class PatientDiagnosisPK implements Serializable {
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
	@Column(name = "diagnosis_code")
	private String diagnosisCode;
	@Column(name = "diagnosis_seq_id")
	private Integer diagnosisSeqId;
	
	public PatientDiagnosisPK() {
	}

	public PatientDiagnosisPK(int accountId, int patientVisitId, String diagnosisCode,Integer diagnosisSeqId) {
		this.accountId = accountId;
		this.patientVisitId = patientVisitId;
		this.diagnosisCode = diagnosisCode;
		this.diagnosisSeqId = diagnosisSeqId;
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

	public String getDiagnosisCode() {
		return diagnosisCode;
	}

	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (int) patientVisitId;
		hash += (diagnosisCode != null ? diagnosisCode.hashCode() : 0);
		hash += (int) diagnosisSeqId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientDiagnosisPK)) {
			return false;
		}
		PatientDiagnosisPK other = (PatientDiagnosisPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if (this.patientVisitId != other.patientVisitId) {
			return false;
		}
		if (this.diagnosisSeqId != other.diagnosisSeqId) {
			return false;
		}
		if ((this.diagnosisCode == null && other.diagnosisCode != null)
				|| (this.diagnosisCode != null && !this.diagnosisCode.equals(other.diagnosisCode))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientDiagnosisPK[ accountId=" + accountId + ", patientVisitId="
				+ patientVisitId + ", diagnosisCode=" + diagnosisCode + ", diagnosisSeqId=" + diagnosisSeqId + " ]";
	}

}
