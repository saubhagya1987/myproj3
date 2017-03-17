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
 * @author iRESlab
 */
@Embeddable
public class PatientBillSummaryPK implements Serializable {
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "visit_identifier")
	private String visitIdentifier;
	@Basic(optional = false)
	@Column(name = "bill_id")
	private String billId;

	public PatientBillSummaryPK() {
	}

	public PatientBillSummaryPK(int accountId, String visitIdentifier, String billId) {
		this.accountId = accountId;
		this.visitIdentifier = visitIdentifier;
		this.billId = billId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getVisitIdentifier() {
		return visitIdentifier;
	}

	public void setVisitIdentifier(String visitIdentifier) {
		this.visitIdentifier = visitIdentifier;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (visitIdentifier != null ? visitIdentifier.hashCode() : 0);
		hash += (billId != null ? billId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientBillSummaryPK)) {
			return false;
		}
		PatientBillSummaryPK other = (PatientBillSummaryPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if ((this.visitIdentifier == null && other.visitIdentifier != null)
				|| (this.visitIdentifier != null && !this.visitIdentifier.equals(other.visitIdentifier))) {
			return false;
		}
		if ((this.billId == null && other.billId != null) || (this.billId != null && !this.billId.equals(other.billId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientBillSummaryPK[ accountId=" + accountId + ", visitIdentifier="
				+ visitIdentifier + ", billId=" + billId + " ]";
	}

}
