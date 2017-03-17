/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sohaib
 */
@Entity
@Table(name = "etl_info")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "EtlInfo.findAll", query = "SELECT e FROM EtlInfo e"),
		@NamedQuery(name = "EtlInfo.findByClientId", query = "SELECT e FROM EtlInfo e WHERE e.etlInfoPK.clientId = :clientId"),
		@NamedQuery(name = "EtlInfo.findByClientDatabaseId", query = "SELECT e FROM EtlInfo e WHERE e.etlInfoPK.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "EtlInfo.findByTransactionId", query = "SELECT e FROM EtlInfo e WHERE e.etlInfoPK.transactionId = :transactionId and e.etlInfoPK.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "EtlInfo.findByAccountId", query = "SELECT e FROM EtlInfo e WHERE e.etlInfoPK.accountId = :accountId"),
		@NamedQuery(name = "EtlInfo.findByEtlDate", query = "SELECT e FROM EtlInfo e WHERE e.etlDate = :etlDate") })
public class EtlInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected EtlInfoPK etlInfoPK;
	@Basic(optional = false)
	@Column(name = "etl_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date etlDate;

	public EtlInfo() {
	}

	public EtlInfo(EtlInfoPK etlInfoPK) {
		this.etlInfoPK = etlInfoPK;
	}

	public EtlInfo(EtlInfoPK etlInfoPK, Date etlDate) {
		this.etlInfoPK = etlInfoPK;
		this.etlDate = etlDate;
	}

	public EtlInfo(int clientId, int clientDatabaseId, long transactionId, int accountId) {
		this.etlInfoPK = new EtlInfoPK(clientId, clientDatabaseId, transactionId, accountId);
	}

	/*
	 * @Override public int hashCode() { int hash = 0; hash += (etlInfoPK !=
	 * null ? etlInfoPK.hashCode() : 0); return hash; }
	 */

	public EtlInfoPK getEtlInfoPK() {
		return etlInfoPK;
	}

	public void setEtlInfoPK(EtlInfoPK etlInfoPK) {
		this.etlInfoPK = etlInfoPK;
	}

	public Date getEtlDate() {
		return etlDate;
	}

	public void setEtlDate(Date etlDate) {
		this.etlDate = etlDate;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (etlInfoPK != null ? etlInfoPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EtlInfo)) {
			return false;
		}
		EtlInfo other = (EtlInfo) object;
		if ((this.etlInfoPK == null && other.etlInfoPK != null)
				|| (this.etlInfoPK != null && !this.etlInfoPK.equals(other.etlInfoPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {

		return "ACCOUNT ID : " + this.getEtlInfoPK().getAccountId() + " TRANSACTION ID : "
				+ this.getEtlInfoPK().getTransactionId();

	}

}
