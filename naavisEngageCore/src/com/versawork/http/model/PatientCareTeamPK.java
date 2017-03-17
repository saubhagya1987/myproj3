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
public class PatientCareTeamPK implements Serializable {
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
	@Column(name = "care_member_id")
	private String careMemberId;
	@Basic(optional = false)
	@Column(name = "care_member_role_id")
	private int careMemberRoleId;

	public PatientCareTeamPK() {
	}

	public PatientCareTeamPK(int accountId, int patientVisitId, String careMemberId, int careMemberRoleId) {
		this.accountId = accountId;
		this.patientVisitId = patientVisitId;
		this.careMemberId = careMemberId;
		this.careMemberRoleId = careMemberRoleId;
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

	public String getCareMemberId() {
		return careMemberId;
	}

	public void setCareMemberId(String careMemberId) {
		this.careMemberId = careMemberId;
	}

	public int getCareMemberRoleId() {
		return careMemberRoleId;
	}

	public void setCareMemberRoleId(int careMemberRoleId) {
		this.careMemberRoleId = careMemberRoleId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (int) patientVisitId;
		hash += (careMemberId != null ? careMemberId.hashCode() : 0);
		hash += (int) careMemberRoleId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientCareTeamPK)) {
			return false;
		}
		PatientCareTeamPK other = (PatientCareTeamPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if (this.patientVisitId != other.patientVisitId) {
			return false;
		}
		if ((this.careMemberId == null && other.careMemberId != null)
				|| (this.careMemberId != null && !this.careMemberId.equals(other.careMemberId))) {
			return false;
		}
		if (this.careMemberRoleId != other.careMemberRoleId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientCareTeamPK[ accountId=" + accountId + ", patientVisitId="
				+ patientVisitId + ", careMemberId=" + careMemberId + ", careMemberRoleId=" + careMemberRoleId + " ]";
	}

}
