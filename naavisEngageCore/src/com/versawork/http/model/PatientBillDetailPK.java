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
 * @author Sunil
 */
@Embeddable
public class PatientBillDetailPK implements Serializable {
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "bill_detail_id")
	private String billDetailId;

	public PatientBillDetailPK() {
	}

	public PatientBillDetailPK(int accountId, String billDetailId) {
		this.accountId = accountId;
		this.billDetailId = billDetailId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getBillDetailId() {
		return billDetailId;
	}

	public void setBillDetailId(String billDetailId) {
		this.billDetailId = billDetailId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (billDetailId != null ? billDetailId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientBillDetailPK)) {
			return false;
		}
		PatientBillDetailPK other = (PatientBillDetailPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if ((this.billDetailId == null && other.billDetailId != null)
				|| (this.billDetailId != null && !this.billDetailId.equals(other.billDetailId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientBillDetailPK[ accountId=" + accountId + ", billDetailId="
				+ billDetailId + " ]";
	}

}
