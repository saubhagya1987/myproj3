package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FrequencyDesc implements Serializable {

	private final static long serialVersionUID = 1L;

	protected Integer frequencyId;
	protected Integer datePartId;
	protected Integer interval;
	protected String frequencyDesc;
    
	protected Integer medicationDosageId;
	protected String dosage;
	protected Integer frequencyValue;
	protected String reminderFor;
	
	
	
	public String getReminderFor() {
		return reminderFor;
	}

	public void setReminderFor(String reminderFor) {
		this.reminderFor = reminderFor;
	}

	public Integer getMedicationDosageId() {
		return medicationDosageId;
	}

	public void setMedicationDosageId(Integer medicationDosageId) {
		this.medicationDosageId = medicationDosageId;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public Integer getFrequencyValue() {
		return frequencyValue;
	}

	public void setFrequencyValue(Integer frequencyValue) {
		this.frequencyValue = frequencyValue;
	}

	public String getFrequencyDesc() {
		return frequencyDesc;
	}

	public void setFrequencyDesc(String frequencyDesc) {
		this.frequencyDesc = frequencyDesc;
	}

	public Integer getDatePartId() {
		return datePartId;
	}

	public void setDatePartId(Integer datePartId) {
		this.datePartId = datePartId;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Integer getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(Integer frequencyId) {
		this.frequencyId = frequencyId;
	}

}
