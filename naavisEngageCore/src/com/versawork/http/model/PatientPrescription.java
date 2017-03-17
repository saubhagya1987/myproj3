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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "patient_prescription")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "PatientPrescription.findAll", query = "SELECT p FROM PatientPrescription p"),
		@NamedQuery(name = "PatientPrescription.findByAccountId", query = "SELECT p FROM PatientPrescription p WHERE p.patientPrescriptionPK.accountId = :accountId ORDER BY p.sourceName"),
		@NamedQuery(name = "PatientPrescription.findByPatientVisitId", query = "SELECT p FROM PatientPrescription p WHERE p.patientPrescriptionPK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientPrescription.findBySourceId", query = "SELECT p FROM PatientPrescription p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientPrescription.findBySourceName", query = "SELECT p FROM PatientPrescription p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientPrescription.findByMedicationId", query = "SELECT p FROM PatientPrescription p WHERE p.patientPrescriptionPK.medicationId = :medicationId"),
		@NamedQuery(name = "PatientPrescription.findByMedicationName", query = "SELECT p FROM PatientPrescription p WHERE p.medicationName = :medicationName"),
		@NamedQuery(name = "PatientPrescription.findByRxNumber", query = "SELECT p FROM PatientPrescription p WHERE p.rxNumber = :rxNumber"),
		@NamedQuery(name = "PatientPrescription.findByStatus", query = "SELECT p FROM PatientPrescription p WHERE p.status = :status"),
		@NamedQuery(name = "PatientPrescription.findByRouteOfAdministration", query = "SELECT p FROM PatientPrescription p WHERE p.routeOfAdministration = :routeOfAdministration"),
		@NamedQuery(name = "PatientPrescription.findByRouteOfAdministrationCode", query = "SELECT p FROM PatientPrescription p WHERE p.routeOfAdministrationCode = :routeOfAdministrationCode"),
		@NamedQuery(name = "PatientPrescription.findByDoseQuantity", query = "SELECT p FROM PatientPrescription p WHERE p.doseQuantity = :doseQuantity"),
		@NamedQuery(name = "PatientPrescription.findByDosageDescription", query = "SELECT p FROM PatientPrescription p WHERE p.dosageDescription = :dosageDescription"),
		@NamedQuery(name = "PatientPrescription.findByStartDate", query = "SELECT p FROM PatientPrescription p WHERE p.startDate = :startDate"),
		@NamedQuery(name = "PatientPrescription.findByEndDate", query = "SELECT p FROM PatientPrescription p WHERE p.endDate = :endDate"),
		@NamedQuery(name = "PatientPrescription.findByDateAdded", query = "SELECT p FROM PatientPrescription p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientPrescription.findByMedicationGenericName", query = "SELECT p FROM PatientPrescription p WHERE p.medicationGenericName = :medicationGenericName"),
		@NamedQuery(name = "PatientPrescription.findByMedicationBrandName", query = "SELECT p FROM PatientPrescription p WHERE p.medicationBrandName = :medicationBrandName"),
		@NamedQuery(name = "PatientPrescription.findByDoseStrength", query = "SELECT p FROM PatientPrescription p WHERE p.doseStrength = :doseStrength"),
		@NamedQuery(name = "PatientPrescription.findByUnit", query = "SELECT p FROM PatientPrescription p WHERE p.unit = :unit"),
		@NamedQuery(name = "PatientPrescription.findByDirection", query = "SELECT p FROM PatientPrescription p WHERE p.direction = :direction"),
		@NamedQuery(name = "PatientPrescription.findByEtlInfoAccount", query = "SELECT p FROM PatientPrescription p, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND p.patientPrescriptionPK.accountId = info.etlInfoPK.accountId ORDER BY  p.patientPrescriptionPK.accountId, p.patientPrescriptionPK.patientVisitId") })
public class PatientPrescription implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientPrescriptionPK patientPrescriptionPK;
	@Column(name = "source_id")
	private String sourceId;
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "medication_name")
	private String medicationName;
	@Column(name = "frequency")
	private String frequency;
	@Column(name = "rx_number")
	private String rxNumber;
	@Column(name = "status")
	private String status;
	@Column(name = "route_of_administration")
	private String routeOfAdministration;
	@Column(name = "route_of_administration_code")
	private String routeOfAdministrationCode;
	@Column(name = "dose_quantity")
	private String doseQuantity;
	@Column(name = "dosage_description")
	private String dosageDescription;
	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "medication_generic_name")
	private String medicationGenericName;
	@Column(name = "medication_brand_name")
	private String medicationBrandName;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "dose_strength")
	private BigDecimal doseStrength;
	@Column(name = "medication_strength")
	private String medicationStrength;
	@Column(name = "unit")
	private String unit;
	@Column(name = "direction")
	private String direction;
	@JoinColumn(name = "prescription_action_id", referencedColumnName = "prescription_action_id")
	@ManyToOne(optional = false)
	private PrescriptionAction prescriptionActionId;
	@JoinColumns({
			@JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false),
			@JoinColumn(name = "patient_visit_id", referencedColumnName = "patient_visit_id", insertable = false, updatable = false) })
	@ManyToOne(optional = false)
	private PatientVisit patientVisit;

	public PatientPrescription() {
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public PatientPrescription(PatientPrescriptionPK patientPrescriptionPK) {
		this.patientPrescriptionPK = patientPrescriptionPK;
	}

	public PatientPrescription(PatientPrescriptionPK patientPrescriptionPK, String sourceName, String medicationName) {
		this.patientPrescriptionPK = patientPrescriptionPK;
		this.sourceName = sourceName;
		this.medicationName = medicationName;
	}

	public PatientPrescription(int accountId, int patientVisitId, String medicationId) {
		this.patientPrescriptionPK = new PatientPrescriptionPK(accountId, patientVisitId, medicationId);
	}

	public PatientPrescriptionPK getPatientPrescriptionPK() {
		return patientPrescriptionPK;
	}

	public void setPatientPrescriptionPK(PatientPrescriptionPK patientPrescriptionPK) {
		this.patientPrescriptionPK = patientPrescriptionPK;
	}

	public String getMedicationStrength() {
		return medicationStrength;
	}

	public void setMedicationStrength(String medicationStrength) {
		this.medicationStrength = medicationStrength;
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

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getRxNumber() {
		return rxNumber;
	}

	public void setRxNumber(String rxNumber) {
		this.rxNumber = rxNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRouteOfAdministration() {
		return routeOfAdministration;
	}

	public void setRouteOfAdministration(String routeOfAdministration) {
		this.routeOfAdministration = routeOfAdministration;
	}

	public String getRouteOfAdministrationCode() {
		return routeOfAdministrationCode;
	}

	public void setRouteOfAdministrationCode(String routeOfAdministrationCode) {
		this.routeOfAdministrationCode = routeOfAdministrationCode;
	}

	public String getDoseQuantity() {
		return doseQuantity;
	}

	public void setDoseQuantity(String doseQuantity) {
		this.doseQuantity = doseQuantity;
	}

	public String getDosageDescription() {
		return dosageDescription;
	}

	public void setDosageDescription(String dosageDescription) {
		this.dosageDescription = dosageDescription;
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

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getMedicationGenericName() {
		return medicationGenericName;
	}

	public void setMedicationGenericName(String medicationGenericName) {
		this.medicationGenericName = medicationGenericName;
	}

	public String getMedicationBrandName() {
		return medicationBrandName;
	}

	public void setMedicationBrandName(String medicationBrandName) {
		this.medicationBrandName = medicationBrandName;
	}

	public BigDecimal getDoseStrength() {
		return doseStrength;
	}

	public void setDoseStrength(BigDecimal doseStrength) {
		this.doseStrength = doseStrength;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public PrescriptionAction getPrescriptionActionId() {
		return prescriptionActionId;
	}

	public void setPrescriptionActionId(PrescriptionAction prescriptionActionId) {
		this.prescriptionActionId = prescriptionActionId;
	}

	public PatientVisit getPatientVisit() {
		return patientVisit;
	}

	public void setPatientVisit(PatientVisit patientVisit) {
		this.patientVisit = patientVisit;
	}

	/*
	 * public Account getAccount() { return account; }
	 * 
	 * public void setAccount(Account account) { this.account = account; }
	 */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientPrescriptionPK != null ? patientPrescriptionPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientPrescription)) {
			return false;
		}
		PatientPrescription other = (PatientPrescription) object;
		if ((this.patientPrescriptionPK == null && other.patientPrescriptionPK != null)
				|| (this.patientPrescriptionPK != null && !this.patientPrescriptionPK
						.equals(other.patientPrescriptionPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientPrescription[ patientPrescriptionPK=" + patientPrescriptionPK + " ]";
	}

}
