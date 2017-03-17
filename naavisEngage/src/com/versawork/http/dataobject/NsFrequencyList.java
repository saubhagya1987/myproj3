package com.versawork.http.dataobject;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsFrequencyList {

	// private static final long serialVersionUID = 1L;
	protected Integer frequencyId;
	protected String frequency;
	protected Integer datePartId;
	protected Integer interval;

	public Integer getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(Integer frequencyId) {
		this.frequencyId = frequencyId;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
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

}
