package com.versawork.http.dataobject;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CarePlanInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String carePlanId;
	protected String goal;
	protected String instructions;
	protected String dateAdded;

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getCarePlanId() {
		return carePlanId;
	}

	public void setCarePlanId(String carePlanId) {
		this.carePlanId = carePlanId;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

}
