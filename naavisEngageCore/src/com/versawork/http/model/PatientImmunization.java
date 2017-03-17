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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "patient_immunization")
@NamedQueries({
		@NamedQuery(name = "PatientImmunization.findAll", query = "SELECT p FROM PatientImmunization p"),
		@NamedQuery(name = "PatientImmunization.findByAccountId", query = "SELECT p FROM PatientImmunization p WHERE p.patientImmunizationPK.accountId = :accountId ORDER BY p.dateAdded DESC"),
		@NamedQuery(name = "PatientImmunization.findByImmunizationId", query = "SELECT p FROM PatientImmunization p WHERE p.patientImmunizationPK.immunizationId = :immunizationId"),
		@NamedQuery(name = "PatientImmunization.findByPatientVisitId", query = "SELECT p FROM PatientImmunization p WHERE p.patientImmunizationPK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientImmunization.findBySourceId", query = "SELECT p FROM PatientImmunization p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientImmunization.findBySourceName", query = "SELECT p FROM PatientImmunization p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientImmunization.findByImmunizationName", query = "SELECT p FROM PatientImmunization p WHERE p.immunizationName = :immunizationName"),
		@NamedQuery(name = "PatientImmunization.findByImmunizationCode", query = "SELECT p FROM PatientImmunization p WHERE p.immunizationCode = :immunizationCode"),
		@NamedQuery(name = "PatientImmunization.findByStatus", query = "SELECT p FROM PatientImmunization p WHERE p.status = :status"),
		@NamedQuery(name = "PatientImmunization.findByRouteCode", query = "SELECT p FROM PatientImmunization p WHERE p.routeCode = :routeCode"),
		@NamedQuery(name = "PatientImmunization.findByRouteName", query = "SELECT p FROM PatientImmunization p WHERE p.routeName = :routeName"),
		@NamedQuery(name = "PatientImmunization.findByDateAdded", query = "SELECT p FROM PatientImmunization p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientImmunization.findByEtlInfoAccount", query = "SELECT p FROM PatientImmunization p, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND p.patientImmunizationPK.accountId = info.etlInfoPK.accountId ORDER BY  p.patientImmunizationPK.accountId, p.patientImmunizationPK.patientVisitId") })
public class PatientImmunization implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientImmunizationPK patientImmunizationPK;
	@Column(name = "source_id")
	private String sourceId;
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "immunization_name")
	private String immunizationName;
	@Column(name = "immunization_code")
	private String immunizationCode;
	@Column(name = "status")
	private String status;
	@Column(name = "route_code")
	private String routeCode;
	@Column(name = "route_name")
	private String routeName;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Basic(optional = true)
	@Column(name = "immunization_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date statusDate;
	@JoinColumns({
			@JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false),
			@JoinColumn(name = "patient_visit_id", referencedColumnName = "patient_visit_id", insertable = false, updatable = false) })
	@ManyToOne(optional = false)
	private PatientVisit patientVisit;

	public PatientImmunization() {
	}

	public PatientImmunization(PatientImmunizationPK patientImmunizationPK) {
		this.patientImmunizationPK = patientImmunizationPK;
	}

	public PatientImmunization(PatientImmunizationPK patientImmunizationPK, String immunizationName, Date dateAdded) {
		this.patientImmunizationPK = patientImmunizationPK;
		this.immunizationName = immunizationName;
		this.dateAdded = dateAdded;
	}

	public PatientImmunization(int accountId, String immunizationId, int patientVisitId) {
		this.patientImmunizationPK = new PatientImmunizationPK(accountId, immunizationId, patientVisitId);
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public PatientImmunizationPK getPatientImmunizationPK() {
		return patientImmunizationPK;
	}

	public void setPatientImmunizationPK(PatientImmunizationPK patientImmunizationPK) {
		this.patientImmunizationPK = patientImmunizationPK;
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

	public String getImmunizationName() {
		return immunizationName;
	}

	public void setImmunizationName(String immunizationName) {
		this.immunizationName = immunizationName;
	}

	public String getImmunizationCode() {
		return immunizationCode;
	}

	public void setImmunizationCode(String immunizationCode) {
		this.immunizationCode = immunizationCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public PatientVisit getPatientVisit() {
		return patientVisit;
	}

	public void setPatientVisit(PatientVisit patientVisit) {
		this.patientVisit = patientVisit;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientImmunizationPK != null ? patientImmunizationPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientImmunization)) {
			return false;
		}
		PatientImmunization other = (PatientImmunization) object;
		if ((this.patientImmunizationPK == null && other.patientImmunizationPK != null)
				|| (this.patientImmunizationPK != null && !this.patientImmunizationPK
						.equals(other.patientImmunizationPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientImmunization[ patientImmunizationPK=" + patientImmunizationPK + " ]";
	}

}
