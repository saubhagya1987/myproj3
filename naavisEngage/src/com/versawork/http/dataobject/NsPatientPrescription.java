package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.versawork.http.constant.VersaWorkConstant;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsPatientPrescription implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String drugName;
	protected String issuedDate;
	protected String dose;
	protected String dosageDescription;
	protected String startDate;
	protected String endDate;
	protected String medicationBrandName;
	protected String medicationGenericName;
	protected String doseQuantity;
	protected String doseStrength;
	protected String doseUnit;
	protected String direction;
	protected String routeOfAdministration;
	private String sourceName;
	private String prescriptionAction;
	private String frequency;
	private String asOfDate;
	private Date encounterStartDate;
	private Date encounterEndDate;
	protected String rxNumber;
	protected String dateAdded;
	protected String routeName;

	/*
	 * patientPrescription.getMedicationBrandName();
	 * patientPrescription.getMedicationGenericName();
	 * 
	 * patientPrescription.getDoseQuantity();
	 * 
	 * patientPrescription.getDoseStrength();
	 * 
	 * patientPrescription.getUnit();
	 * 
	 * patientPrescription.getDirection();
	 * 
	 * patientPrescription.getRouteOfAdministration();
	 */

	public String getRxNumber() {
		return rxNumber;
	}

	public void setRxNumber(String rxNumber) {
		if(rxNumber != null){
			this.rxNumber = rxNumber;
		}else{
			this.rxNumber = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		if(routeName != null){
			this.routeName = routeName;
		}else{
			this.routeName = VersaWorkConstant.NOT_APPLICABLE;
		}
		
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

	public String getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(String asOfDate) {
		if (asOfDate != null) {
			this.asOfDate = asOfDate;
		} else {
			this.asOfDate = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getPrescriptionAction() {
		return prescriptionAction;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		if (frequency != null) {
			this.frequency = frequency;
		} else {
			this.frequency = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public void setPrescriptionAction(String prescriptionAction) {
		this.prescriptionAction = prescriptionAction;
	}

	public String getDosageDescription() {
		return dosageDescription;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		if (startDate != null) {
			this.startDate = startDate;
		} else {
			this.startDate = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		if (endDate != null) {
			this.endDate = endDate;
		} else {
			this.endDate = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getMedicationBrandName() {
		return medicationBrandName;
	}

	public void setMedicationBrandName(String medicationBrandName) {
		if (medicationBrandName != null) {
			this.medicationBrandName = medicationBrandName;
		} else {
			this.medicationBrandName = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getMedicationGenericName() {
		return medicationGenericName;
	}

	public void setMedicationGenericName(String medicationGenericName) {
		if (medicationGenericName != null) {
			this.medicationGenericName = medicationGenericName;
		} else {
			this.medicationGenericName = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDoseQuantity() {
		return doseQuantity;
	}

	public void setDoseQuantity(String doseQuantity) {
		if (doseQuantity != null) {
			this.doseQuantity = doseQuantity;
		} else {
			this.doseQuantity = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDoseStrength() {
		return doseStrength;
	}

	public void setDoseStrength(String doseStrength) {
		if (doseStrength != null) {
			this.doseStrength = doseStrength;
		} else {
			this.doseStrength = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDoseUnit() {
		return doseUnit;
	}

	public void setDoseUnit(String doseUnit) {
		if (doseUnit != null) {
			this.doseUnit = doseUnit;
		} else {
			this.doseUnit = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		if (direction != null) {
			this.direction = direction;
		} else {
			this.direction = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getRouteOfAdministration() {
		return routeOfAdministration;
	}

	public void setRouteOfAdministration(String routeOfAdministration) {
		if (routeOfAdministration != null) {
			this.routeOfAdministration = routeOfAdministration;
		} else {
			this.routeOfAdministration = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public void setDosageDescription(String dosageDescription) {
		if (dosageDescription != null) {
			this.dosageDescription = dosageDescription;
		} else {
			this.dosageDescription = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		if (drugName != null) {
			this.drugName = drugName;
		} else {
			this.drugName = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(String issuedDate) {
		if (issuedDate != null) {
			this.issuedDate = issuedDate;
		} else {
			this.issuedDate = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		if (dose != null) {
			this.dose = dose;
		} else {
			this.dose = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
}
