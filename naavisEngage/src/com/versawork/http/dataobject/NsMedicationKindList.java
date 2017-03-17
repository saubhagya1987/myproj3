package com.versawork.http.dataobject;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsMedicationKindList {

	// private static final long serialVersionUID = 1L;

	protected Integer medicationKindId;
	protected String medicationKindDesc;
	protected String medicationDose;

	public String getMedicationDose() {
		return medicationDose;
	}

	public void setMedicationDose(String medicationDose) {
		this.medicationDose = medicationDose;
	}

	public Integer getMedicationKindId() {
		return medicationKindId;
	}

	public void setMedicationKindId(Integer medicationKindId) {
		this.medicationKindId = medicationKindId;
	}

	public String getMedicationKindDesc() {
		return medicationKindDesc;
	}

	public void setMedicationKindDesc(String medicationKindDesc) {
		this.medicationKindDesc = medicationKindDesc;
	}

}
