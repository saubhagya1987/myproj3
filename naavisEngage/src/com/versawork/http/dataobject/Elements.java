package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Elements implements Serializable {

	private final static long serialVersionUID = 1L;

	protected Distance distance;
	protected Duration duration;
	protected String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
