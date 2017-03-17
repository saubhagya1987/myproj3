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
 * @author Sunil
 */
@Entity
@Table(name = "patient_bill_detail")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "PatientBillDetail.findAll", query = "SELECT p FROM PatientBillDetail p"),
		@NamedQuery(name = "PatientBillDetail.findByAccountId", query = "SELECT p FROM PatientBillDetail p WHERE p.patientBillDetailPK.accountId = :accountId"),
		@NamedQuery(name = "PatientBillDetail.findByBillDetailId", query = "SELECT p FROM PatientBillDetail p WHERE p.patientBillDetailPK.billDetailId = :billDetailId"),
		@NamedQuery(name = "PatientBillDetail.findByBillIdClientDatabaseId", query = "SELECT p FROM PatientBillDetail p WHERE p.billId = :billId and p.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "PatientBillDetail.findByBillDescription", query = "SELECT p FROM PatientBillDetail p WHERE p.billDescription = :billDescription"),
		@NamedQuery(name = "PatientBillDetail.findByQuantity", query = "SELECT p FROM PatientBillDetail p WHERE p.quantity = :quantity"),
		@NamedQuery(name = "PatientBillDetail.findByDetailAmount", query = "SELECT p FROM PatientBillDetail p WHERE p.detailAmount = :detailAmount"),
		@NamedQuery(name = "PatientBillDetail.findByClientId", query = "SELECT p FROM PatientBillDetail p WHERE p.clientId = :clientId"),
		@NamedQuery(name = "PatientBillDetail.findBySourceUpdated", query = "SELECT p FROM PatientBillDetail p WHERE p.sourceUpdated = :sourceUpdated"),
		@NamedQuery(name = "PatientBillDetail.findByClientDatabaseId", query = "SELECT p FROM PatientBillDetail p WHERE p.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "PatientBillDetail.findByDateAdded", query = "SELECT p FROM PatientBillDetail p WHERE p.dateAdded = :dateAdded") })
public class PatientBillDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientBillDetailPK patientBillDetailPK;
	@Column(name = "bill_id")
	private String billId;
	@Column(name = "bill_description")
	private String billDescription;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "quantity")
	private BigDecimal quantity;
	@Column(name = "detail_amount")
	private BigDecimal detailAmount;
	@Column(name = "client_id")
	private Integer clientId;
	@Basic(optional = false)
	@Column(name = "source_updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sourceUpdated;
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "client_database_id")
	private Integer clientDatabaseId;

	public PatientBillDetail() {
	}

	public PatientBillDetail(PatientBillDetailPK patientBillDetailPK) {
		this.patientBillDetailPK = patientBillDetailPK;
	}

	public PatientBillDetail(PatientBillDetailPK patientBillDetailPK, Date sourceUpdated) {
		this.patientBillDetailPK = patientBillDetailPK;
		this.sourceUpdated = sourceUpdated;
	}

	public PatientBillDetail(int accountId, String billDetailId) {
		this.patientBillDetailPK = new PatientBillDetailPK(accountId, billDetailId);
	}

	public PatientBillDetailPK getPatientBillDetailPK() {
		return patientBillDetailPK;
	}

	public void setPatientBillDetailPK(PatientBillDetailPK patientBillDetailPK) {
		this.patientBillDetailPK = patientBillDetailPK;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getBillDescription() {
		return billDescription;
	}

	public void setBillDescription(String billDescription) {
		this.billDescription = billDescription;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getDetailAmount() {
		return detailAmount;
	}

	public void setDetailAmount(BigDecimal detailAmount) {
		this.detailAmount = detailAmount;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Date getSourceUpdated() {
		return sourceUpdated;
	}

	public void setSourceUpdated(Date sourceUpdated) {
		this.sourceUpdated = sourceUpdated;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Integer getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(Integer clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientBillDetailPK != null ? patientBillDetailPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientBillDetail)) {
			return false;
		}
		PatientBillDetail other = (PatientBillDetail) object;
		if ((this.patientBillDetailPK == null && other.patientBillDetailPK != null)
				|| (this.patientBillDetailPK != null && !this.patientBillDetailPK.equals(other.patientBillDetailPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientBillDetail[ patientBillDetailPK=" + patientBillDetailPK + " ]";
	}

}
