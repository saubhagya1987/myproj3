/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iRESlab
 */
@Entity
@Table(name = "patient_transaction_log")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "PatientTransactionLog.findAll", query = "SELECT p FROM PatientTransactionLog p"),
		@NamedQuery(name = "PatientTransactionLog.findByTransactionLogId", query = "SELECT p FROM PatientTransactionLog p WHERE p.transactionLogId = :transactionLogId"),
		@NamedQuery(name = "PatientTransactionLog.findByBillId", query = "SELECT p FROM PatientTransactionLog p WHERE p.billId = :billId"),
		@NamedQuery(name = "PatientTransactionLog.findByStatus", query = "SELECT p FROM PatientTransactionLog p WHERE p.status = :status"),
		@NamedQuery(name = "PatientTransactionLog.findByAmount", query = "SELECT p FROM PatientTransactionLog p WHERE p.amount = :amount"),
		@NamedQuery(name = "PatientTransactionLog.findByResponse", query = "SELECT p FROM PatientTransactionLog p WHERE p.response = :response"),
		@NamedQuery(name = "PatientTransactionLog.findByDateTime", query = "SELECT p FROM PatientTransactionLog p WHERE p.dateTime = :dateTime") })
public class PatientTransactionLog implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transaction_log_id")
	private Integer transactionLogId;
	@Column(name = "bill_id")
	private String billId;
	@Column(name = "status")
	private String status;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "response")
	private String response;
	@Column(name = "date_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	public PatientTransactionLog() {
	}

	public PatientTransactionLog(Integer transactionLogId) {
		this.transactionLogId = transactionLogId;
	}

	public Integer getTransactionLogId() {
		return transactionLogId;
	}

	public void setTransactionLogId(Integer transactionLogId) {
		this.transactionLogId = transactionLogId;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (transactionLogId != null ? transactionLogId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientTransactionLog)) {
			return false;
		}
		PatientTransactionLog other = (PatientTransactionLog) object;
		if ((this.transactionLogId == null && other.transactionLogId != null)
				|| (this.transactionLogId != null && !this.transactionLogId.equals(other.transactionLogId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientTransactionLog[ transactionLogId=" + transactionLogId + " ]";
	}

}
