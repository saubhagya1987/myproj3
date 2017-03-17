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
@Table(name = "patient_bill_summary")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "PatientBillSummary.findAll", query = "SELECT p FROM PatientBillSummary p"),
		@NamedQuery(name = "PatientBillSummary.findByAccountId", query = "SELECT p FROM PatientBillSummary p WHERE p.patientBillSummaryPK.accountId = :accountId"),
		@NamedQuery(name = "PatientBillSummary.findByAccountIdIsBillPaidClientDatabaseId", query = "SELECT p FROM PatientBillSummary p WHERE p.clientDatabaseId = :clientDatabaseId and p.patientBillSummaryPK.accountId = :accountId and p.isBillPaid = :isBillPaid"),
		@NamedQuery(name = "PatientBillSummary.findByAccountIdIsBillPaidIsViewedClientDatabaseId", query = "SELECT p FROM PatientBillSummary p WHERE p.clientDatabaseId = :clientDatabaseId and p.patientBillSummaryPK.accountId = :accountId and p.isBillPaid = :isBillPaid and p.isViewed = :isViewed"),
		@NamedQuery(name = "PatientBillSummary.findByVisitIdentifier", query = "SELECT p FROM PatientBillSummary p WHERE p.patientBillSummaryPK.visitIdentifier = :visitIdentifier"),
		@NamedQuery(name = "PatientBillSummary.findByBillIdClientDatabaseId", query = "SELECT p FROM PatientBillSummary p WHERE p.patientBillSummaryPK.billId = :billId and p.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "PatientBillSummary.findBySourceId", query = "SELECT p FROM PatientBillSummary p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientBillSummary.findBySourceName", query = "SELECT p FROM PatientBillSummary p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientBillSummary.findByAddress1", query = "SELECT p FROM PatientBillSummary p WHERE p.address1 = :address1"),
		@NamedQuery(name = "PatientBillSummary.findByAddress2", query = "SELECT p FROM PatientBillSummary p WHERE p.address2 = :address2"),
		@NamedQuery(name = "PatientBillSummary.findByCity", query = "SELECT p FROM PatientBillSummary p WHERE p.city = :city"),
		@NamedQuery(name = "PatientBillSummary.findByState", query = "SELECT p FROM PatientBillSummary p WHERE p.state = :state"),
		@NamedQuery(name = "PatientBillSummary.findByPostalCode", query = "SELECT p FROM PatientBillSummary p WHERE p.postalCode = :postalCode"),
		@NamedQuery(name = "PatientBillSummary.findByInsuranceProvider", query = "SELECT p FROM PatientBillSummary p WHERE p.insuranceProvider = :insuranceProvider"),
		@NamedQuery(name = "PatientBillSummary.findByEncounterStartDate", query = "SELECT p FROM PatientBillSummary p WHERE p.encounterStartDate = :encounterStartDate"),
		@NamedQuery(name = "PatientBillSummary.findByEncounterEndDate", query = "SELECT p FROM PatientBillSummary p WHERE p.encounterEndDate = :encounterEndDate"),
		@NamedQuery(name = "PatientBillSummary.findByBillDate", query = "SELECT p FROM PatientBillSummary p WHERE p.billDate = :billDate"),
		@NamedQuery(name = "PatientBillSummary.findByTotalDue", query = "SELECT p FROM PatientBillSummary p WHERE p.totalDue = :totalDue"),
		@NamedQuery(name = "PatientBillSummary.findByClientId", query = "SELECT p FROM PatientBillSummary p WHERE p.clientId = :clientId"),
		@NamedQuery(name = "PatientBillSummary.findBySourceUpdated", query = "SELECT p FROM PatientBillSummary p WHERE p.sourceUpdated = :sourceUpdated"),
		@NamedQuery(name = "PatientBillSummary.findByIsViewed", query = "SELECT p FROM PatientBillSummary p WHERE p.isViewed = :isViewed"),
		@NamedQuery(name = "PatientBillSummary.findByIsBillPaid", query = "SELECT p FROM PatientBillSummary p WHERE p.isBillPaid = :isBillPaid"),
		@NamedQuery(name = "PatientBillSummary.findByClientDatabaseId", query = "SELECT p FROM PatientBillSummary p WHERE p.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "PatientBillSummary.findByDateAdded", query = "SELECT p FROM PatientBillSummary p WHERE p.dateAdded = :dateAdded") })
public class PatientBillSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientBillSummaryPK patientBillSummaryPK;
	@Column(name = "source_id")
	private String sourceId;
	@Column(name = "source_name")
	private String sourceName;
	@Column(name = "address_1")
	private String address1;
	@Column(name = "address_2")
	private String address2;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "postal_code")
	private String postalCode;
	@Column(name = "insurance_provider")
	private String insuranceProvider;
	@Column(name = "encounter_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date encounterStartDate;
	@Column(name = "encounter_end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date encounterEndDate;
	@Column(name = "bill_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date billDate;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "total_due")
	private BigDecimal totalDue;
	@Column(name = "client_id")
	private Integer clientId;
	@Basic(optional = false)
	@Column(name = "source_updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sourceUpdated;
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "is_bill_paid")
	private Boolean isBillPaid;
	@Column(name = "is_viewed")
	private Boolean isViewed;
	@Column(name = "client_database_id")
	private Integer clientDatabaseId;

	public PatientBillSummary() {
	}

	public PatientBillSummary(PatientBillSummaryPK patientBillSummaryPK) {
		this.patientBillSummaryPK = patientBillSummaryPK;
	}

	public PatientBillSummary(PatientBillSummaryPK patientBillSummaryPK, Date sourceUpdated) {
		this.patientBillSummaryPK = patientBillSummaryPK;
		this.sourceUpdated = sourceUpdated;
	}

	public PatientBillSummary(int accountId, String visitIdentifier, String billId) {
		this.patientBillSummaryPK = new PatientBillSummaryPK(accountId, visitIdentifier, billId);
	}

	public PatientBillSummaryPK getPatientBillSummaryPK() {
		return patientBillSummaryPK;
	}

	public void setPatientBillSummaryPK(PatientBillSummaryPK patientBillSummaryPK) {
		this.patientBillSummaryPK = patientBillSummaryPK;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getInsuranceProvider() {
		return insuranceProvider;
	}

	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}

	public Date getEncounterStartDate() {
		return encounterStartDate;
	}

	public void setEncounterStartDate(Date encounterStartDate) {
		this.encounterStartDate = encounterStartDate;
	}

	public Date getEncounterEndDate() {
		return encounterEndDate;
	}

	public void setEncounterEndDate(Date encounterEndDate) {
		this.encounterEndDate = encounterEndDate;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public BigDecimal getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(BigDecimal totalDue) {
		this.totalDue = totalDue;
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

	public Boolean getIsBillPaid() {
		return isBillPaid;
	}

	public void setIsBillPaid(Boolean isBillPaid) {
		this.isBillPaid = isBillPaid;
	}

	public Boolean getIsViewed() {
		return isViewed;
	}

	public void setIsViewed(Boolean isViewed) {
		this.isViewed = isViewed;
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
		hash += (patientBillSummaryPK != null ? patientBillSummaryPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientBillSummary)) {
			return false;
		}
		PatientBillSummary other = (PatientBillSummary) object;
		if ((this.patientBillSummaryPK == null && other.patientBillSummaryPK != null)
				|| (this.patientBillSummaryPK != null && !this.patientBillSummaryPK.equals(other.patientBillSummaryPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientBillSummary[ patientBillSummaryPK=" + patientBillSummaryPK + " ]";
	}

}
