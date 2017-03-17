/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
@Table(name = "etl_logger")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "EtlLogger.findAll", query = "SELECT e FROM EtlLogger e"),
		@NamedQuery(name = "EtlLogger.findByClientId", query = "SELECT e FROM EtlLogger e WHERE e.etlLoggerPK.clientId = :clientId"),
		@NamedQuery(name = "EtlLogger.findByClientIdUnprocessed", query = "SELECT e FROM EtlLogger e WHERE e.etlLoggerPK.clientId = :clientId and e.status is null and e.endDate is not null"),
		@NamedQuery(name = "EtlLogger.findByClientDatabaseIdUnprocessed", query = "SELECT e FROM EtlLogger e WHERE e.etlLoggerPK.clientDatabaseId = :clientDatabaseId  and e.status is null and e.endDate is not null"),
		@NamedQuery(name = "EtlLogger.findByTransactionId", query = "SELECT e FROM EtlLogger e WHERE e.etlLoggerPK.transactionId = :transactionId and e.etlLoggerPK.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "EtlLogger.findByStartDate", query = "SELECT e FROM EtlLogger e WHERE e.startDate = :startDate"),
		@NamedQuery(name = "EtlLogger.findByEndDate", query = "SELECT e FROM EtlLogger e WHERE e.endDate = :endDate"),
		@NamedQuery(name = "EtlLogger.findByStatus", query = "SELECT e FROM EtlLogger e WHERE e.status = :status") })
public class EtlLogger implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected EtlLoggerPK etlLoggerPK;
	@Basic(optional = false)
	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@Column(name = "status")
	private String status;
	@Column(name = "modules", nullable = false, updatable = false)
	private String moduleList;

	public EtlLogger() {
	}

	public EtlLogger(EtlLoggerPK etlLoggerPK) {
		this.etlLoggerPK = etlLoggerPK;
	}

	public EtlLogger(EtlLoggerPK etlLoggerPK, Date startDate) {
		this.etlLoggerPK = etlLoggerPK;
		this.startDate = startDate;
	}

	public EtlLogger(int clientId, int clientDatabaseId, int transactionId) {
		this.etlLoggerPK = new EtlLoggerPK(clientId, clientDatabaseId, transactionId);
	}

	public EtlLoggerPK getEtlLoggerPK() {
		return etlLoggerPK;
	}

	public void setEtlLoggerPK(EtlLoggerPK etlLoggerPK) {
		this.etlLoggerPK = etlLoggerPK;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (etlLoggerPK != null ? etlLoggerPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EtlLogger)) {
			return false;
		}
		EtlLogger other = (EtlLogger) object;
		if ((this.etlLoggerPK == null && other.etlLoggerPK != null)
				|| (this.etlLoggerPK != null && !this.etlLoggerPK.equals(other.etlLoggerPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.EtlLogger[ etlLoggerPK=" + etlLoggerPK + " ]";
	}

	public String getModuleList() {
		return moduleList;
	}

	public void setModuleList(String moduleList) {
		this.moduleList = moduleList;
	}

}
