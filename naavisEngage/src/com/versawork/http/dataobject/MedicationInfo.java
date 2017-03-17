package com.versawork.http.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.versawork.http.constant.VersaWorkConstant;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MedicationInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String medicationId;
	protected String medicationName;
	protected String rxNumber;
	protected String dosageDescription;
	protected String dateAdded;
	protected String status;
	protected String startDate;
	protected String endDate;
	protected String doseQuantity;
	protected String routeCode;
	protected String routeName;
	private String medicationGenericName;
	private String medicationBrandName;
	private BigDecimal doseStrength;
	private String unit;
	private String direction;
	private String frequency;
	private String prescriptionAction;
	private Map<String, List<MedicationInfo>> prescriptionActionMap;
	
	

	/**
	 * @return the prescriptionAction
	 */
	public String getPrescriptionAction() {
		return prescriptionAction;
	}

	/**
	 * @param prescriptionAction the prescriptionAction to set
	 */
	public void setPrescriptionAction(String prescriptionAction) {
		this.prescriptionAction = prescriptionAction;
	}

	/**
	 * @return the prescriptionActionMap
	 */
	public Map<String, List<MedicationInfo>> getPrescriptionActionMap() {
		return prescriptionActionMap;
	}

	/**
	 * @param prescriptionActionMap the prescriptionActionMap to set
	 */
	public void setPrescriptionActionMap(Map<String, List<MedicationInfo>> prescriptionActionMap) {
		this.prescriptionActionMap = prescriptionActionMap;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		if(frequency != null){
			this.frequency = frequency;
		}else{
			this.frequency = VersaWorkConstant.NOT_APPLICABLE;
		}
		
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
		if(unit != null){
			this.unit = unit;
		}else{
			this.unit = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {		
		if(direction != null){
			this.direction = direction;
		}else{
			this.direction = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDoseQuantity() {
		return doseQuantity;
	}

	public void setDoseQuantity(String doseQuantity) {
		this.doseQuantity = doseQuantity;
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
		if(routeName != null){
			this.routeName = routeName;
		}else{
			this.routeName = VersaWorkConstant.NOT_APPLICABLE;
		}
		this.routeName = routeName;
	}

	public String getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(String medicationId) {
		this.medicationId = medicationId;
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
		if(rxNumber != null){
			this.rxNumber = rxNumber;
		}else{
			this.rxNumber = VersaWorkConstant.NOT_APPLICABLE;
		}
		
	}

	public String getDosageDescription() {
		return dosageDescription;
	}

	public void setDosageDescription(String dosageDescription) {
		if(dosageDescription != null){
			this.dosageDescription = dosageDescription;
		}else{
			this.dosageDescription = VersaWorkConstant.NOT_APPLICABLE;
		}
		
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
