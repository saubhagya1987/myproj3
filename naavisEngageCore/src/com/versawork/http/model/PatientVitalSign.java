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
@Table(name = "patient_vital_sign")
@NamedQueries({
		@NamedQuery(name = "PatientVitalSign.findAll", query = "SELECT p FROM PatientVitalSign p"),
		@NamedQuery(name = "PatientVitalSign.findByAccountId", query = "SELECT p FROM PatientVitalSign p WHERE p.patientVitalSignPK.accountId = :accountId ORDER BY p.dateAdded DESC"),
		@NamedQuery(name = "PatientVitalSign.findByPatientVisitId", query = "SELECT p FROM PatientVitalSign p WHERE p.patientVitalSignPK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientVitalSign.findBySourceId", query = "SELECT p FROM PatientVitalSign p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientVitalSign.findBySourceName", query = "SELECT p FROM PatientVitalSign p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientVitalSign.findBySystolicBp", query = "SELECT p FROM PatientVitalSign p WHERE p.systolicBp = :systolicBp"),
		@NamedQuery(name = "PatientVitalSign.findBySystolicBpUnit", query = "SELECT p FROM PatientVitalSign p WHERE p.systolicBpUnit = :systolicBpUnit"),
		@NamedQuery(name = "PatientVitalSign.findByDiastolicBp", query = "SELECT p FROM PatientVitalSign p WHERE p.diastolicBp = :diastolicBp"),
		@NamedQuery(name = "PatientVitalSign.findByDiastolicBpUnit", query = "SELECT p FROM PatientVitalSign p WHERE p.diastolicBpUnit = :diastolicBpUnit"),
		@NamedQuery(name = "PatientVitalSign.findByBmi", query = "SELECT p FROM PatientVitalSign p WHERE p.bmi = :bmi"),
		@NamedQuery(name = "PatientVitalSign.findByDateAdded", query = "SELECT p FROM PatientVitalSign p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientVitalSign.findByEtlInfoAccount", query = "SELECT p FROM PatientVitalSign p, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND p.patientVitalSignPK.accountId = info.etlInfoPK.accountId ORDER BY  p.patientVitalSignPK.accountId, p.patientVitalSignPK.patientVisitId") })
public class PatientVitalSign implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientVitalSignPK patientVitalSignPK;
	@Column(name = "source_id")
	private String sourceId;
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	@Column(name = "height_feet")
	private String heightFeet;
	@Column(name = "height_inches")
	private String heightInches;
	@Column(name = "weight_lbs")
	private String weightLbs;
	@Column(name = "systolic_bp")
	private String systolicBp;
	@Column(name = "systolic_bp_unit")
	private String systolicBpUnit;
	@Column(name = "diastolic_bp")
	private String diastolicBp;
	@Column(name = "diastolic_bp_unit")
	private String diastolicBpUnit;
	
	@Column(name = "heart_rate")
	private String heartRate;
	
	@Column(name = "bmi")
	private String bmi;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@JoinColumns({
			@JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false),
			@JoinColumn(name = "patient_visit_id", referencedColumnName = "patient_visit_id", insertable = false, updatable = false) })
	@ManyToOne(optional = false)
	private PatientVisit patientVisit;

	public PatientVitalSign() {
	}

	public PatientVitalSign(PatientVitalSignPK patientVitalSignPK) {
		this.patientVitalSignPK = patientVitalSignPK;
	}

	public PatientVitalSign(PatientVitalSignPK patientVitalSignPK, String sourceName, Date dateAdded) {
		this.patientVitalSignPK = patientVitalSignPK;
		this.sourceName = sourceName;
		this.dateAdded = dateAdded;
	}

	public PatientVitalSign(int accountId, int patientVisitId) {
		this.patientVitalSignPK = new PatientVitalSignPK(accountId, patientVisitId);
	}

	public PatientVitalSignPK getPatientVitalSignPK() {
		return patientVitalSignPK;
	}

	public void setPatientVitalSignPK(PatientVitalSignPK patientVitalSignPK) {
		this.patientVitalSignPK = patientVitalSignPK;
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

	public String getHeightFeet() {
		return heightFeet;
	}

	public void setHeightFeet(String heightFeet) {
		this.heightFeet = heightFeet;
	}

	public String getHeightInches() {
		return heightInches;
	}

	public void setHeightInches(String heightInches) {
		this.heightInches = heightInches;
	}

	public String getWeightLbs() {
		return weightLbs;
	}

	public void setWeightLbs(String weightLbs) {
		this.weightLbs = weightLbs;
	}

	public String getSystolicBp() {
		return systolicBp;
	}

	public void setSystolicBp(String systolicBp) {
		this.systolicBp = systolicBp;
	}

	public String getSystolicBpUnit() {
		return systolicBpUnit;
	}

	public void setSystolicBpUnit(String systolicBpUnit) {
		this.systolicBpUnit = systolicBpUnit;
	}

	public String getDiastolicBp() {
		return diastolicBp;
	}

	public void setDiastolicBp(String diastolicBp) {
		this.diastolicBp = diastolicBp;
	}

	public String getDiastolicBpUnit() {
		return diastolicBpUnit;
	}

	public void setDiastolicBpUnit(String diastolicBpUnit) {
		this.diastolicBpUnit = diastolicBpUnit;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
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

	public String getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientVitalSignPK != null ? patientVitalSignPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientVitalSign)) {
			return false;
		}
		PatientVitalSign other = (PatientVitalSign) object;
		if ((this.patientVitalSignPK == null && other.patientVitalSignPK != null)
				|| (this.patientVitalSignPK != null && !this.patientVitalSignPK.equals(other.patientVitalSignPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientVitalSign[ patientVitalSignPK=" + patientVitalSignPK + " ]";
	}

}
