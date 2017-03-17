package com.versawork.http.dataobject;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsMedicationMethodList {

	// private static final long serialVersionUID = 1L;
	protected Integer medicationMethodId;
	protected String medicationMethoDesc;

	public Integer getMedicationMethodId() {
		return medicationMethodId;
	}

	public void setMedicationMethodId(Integer medicationMethodId) {
		this.medicationMethodId = medicationMethodId;
	}

	public String getMedicationMethoDesc() {
		return medicationMethoDesc;
	}

	public void setMedicationMethoDesc(String medicationMethoDesc) {
		this.medicationMethoDesc = medicationMethoDesc;
	}

}
