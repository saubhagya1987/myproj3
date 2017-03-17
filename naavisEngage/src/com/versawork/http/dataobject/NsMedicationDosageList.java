package com.versawork.http.dataobject;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsMedicationDosageList {

	// private static final long serialVersionUID = 1L;
	protected Integer medicationDosageId;
	protected String medicationDosageDesc;

	public Integer getMedicationDosageId() {
		return medicationDosageId;
	}

	public void setMedicationDosageId(Integer medicationDosageId) {
		this.medicationDosageId = medicationDosageId;
	}

	public String getMedicationDosageDesc() {
		return medicationDosageDesc;
	}

	public void setMedicationDosageDesc(String medicationDosageDesc) {
		this.medicationDosageDesc = medicationDosageDesc;
	}

}
