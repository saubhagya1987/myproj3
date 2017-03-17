package com.versawork.http.cacheDataObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Sohaib
 * 
 *         Key = PatientMed_accountId_patientVisitId
 */
public class CPatientPrescription implements Serializable {

	private static final long serialVersionUID = 1L;
	private int accountId;
	private int patientVisitId;
	private String medicationId;
	private String sourceId;
	private String sourceName;
	private String medicationName;
	private String rxNumber;
	private String status;
	private String routeOfAdministration;
	private String routeOfAdministrationCode;
	private String doseQuantity;
	private String dosageDescription;
	private Date startDate;
	private Date endDate;
	private Date dateAdded;
	private String medicationGenericName;
	private String medicationBrandName;
	private BigDecimal doseStrength;
	private String unit;
	private String direction;
	private String prescriptionAction;
	private Integer prescriptionActionId;
	private String frequency;
	private String medicationStrength;
	private Date encounterStartDate;
	private Date encounterEndDate;

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

	public String getMedicationStrength() {
		return medicationStrength;
	}

	public void setMedicationStrength(String medicationStrength) {
		this.medicationStrength = medicationStrength;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Integer getPrescriptionActionId() {
		return prescriptionActionId;
	}

	public void setPrescriptionActionId(Integer prescriptionActionId) {
		this.prescriptionActionId = prescriptionActionId;
	}

	public String getPrescriptionAction() {
		return prescriptionAction;
	}

	public void setPrescriptionAction(String prescriptionAction) {
		this.prescriptionAction = prescriptionAction;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getPatientVisitId() {
		return patientVisitId;
	}

	public void setPatientVisitId(int patientVisitId) {
		this.patientVisitId = patientVisitId;
	}

	public String getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(String medicationId) {
		this.medicationId = medicationId;
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

}
