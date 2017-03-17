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
public class EtlInfoPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "client_id")
	private int clientId;
	@Basic(optional = false)
	@Column(name = "client_database_id")
	private int clientDatabaseId;
	@Basic(optional = false)
	@Column(name = "transaction_id")
	private long transactionId;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;

	public EtlInfoPK() {
	}

	public EtlInfoPK(int clientId, int clientDatabaseId, long transactionId, int accountId) {
		this.clientId = clientId;
		this.clientDatabaseId = clientDatabaseId;
		this.transactionId = transactionId;
		this.accountId = accountId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(int clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) clientId;
		hash += (int) clientDatabaseId;
		hash += (int) transactionId;
		hash += (int) accountId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EtlInfoPK)) {
			return false;
		}
		EtlInfoPK other = (EtlInfoPK) object;
		if (this.clientId != other.clientId) {
			return false;
		}
		if (this.clientDatabaseId != other.clientDatabaseId) {
			return false;
		}
		if (this.transactionId != other.transactionId) {
			return false;
		}
		if (this.accountId != other.accountId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.EtlInfoPK[ clientId=" + clientId + ", clientDatabaseId=" + clientDatabaseId
				+ ", transactionId=" + transactionId + ", accountId=" + accountId + " ]";
	}

}
