package com.versawork.http.dataobject;

import com.versawork.http.constant.VersaWorkConstant;

/**
 * @author Dheeraj
 * 
 */

public class VitalSigns {

	private String heightFeet;
	private String weightLbs;
	private String heightInches;
	private String weightUnit;
	private String systolicBp;
	private String diastolicBp;
	private String systolicBpUnit;
	private String diastolicBpUnit;
	private String BMI;
	private String others;
	private String dateAdded;
	private String bloodPressure;

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public String getHeightInches() {
		return heightInches;
	}

	public void setHeightInches(String heightInches) {
		if (heightInches != null) {
			this.heightInches = heightInches;
		} else {
			this.heightInches = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		if (weightUnit != null) {
			this.weightUnit = weightUnit;
		} else {
			this.weightUnit = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getSystolicBp() {
		return systolicBp;
	}

	public void setSystolicBp(String systolicBp) {
		if (systolicBp != null) {
			this.systolicBp = systolicBp;
		} else {
			this.systolicBp = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDiastolicBp() {
		return diastolicBp;
	}

	public void setDiastolicBp(String diastolicBp) {
		if (diastolicBp != null) {
			this.diastolicBp = diastolicBp;
		} else {
			this.dateAdded = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getSystolicBpUnit() {
		return systolicBpUnit;
	}

	public void setSystolicBpUnit(String systolicBpUnit) {
		if (systolicBpUnit != null) {
			this.systolicBpUnit = systolicBpUnit;
		} else {
			this.dateAdded = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDiastolicBpUnit() {
		return diastolicBpUnit;
	}

	public void setDiastolicBpUnit(String diastolicBpUnit) {
		if (diastolicBpUnit != null) {
			this.diastolicBpUnit = diastolicBpUnit;
		} else {
			this.dateAdded = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getHeightFeet() {
		return heightFeet;
	}

	public void setHeightFeet(String heightFeet) {
		if (heightFeet != null) {
			this.heightFeet = heightFeet;
		} else {
			this.heightFeet = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getWeightLbs() {
		return weightLbs;
	}

	public void setWeightLbs(String weightLbs) {
		if (weightLbs != null) {
			this.weightLbs = weightLbs;
		} else {
			this.weightLbs = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getBMI() {
		return BMI;
	}

	public void setBMI(String BMI) {
		if (BMI != null) {
			this.BMI = BMI;
		} else {
			this.BMI = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

}
