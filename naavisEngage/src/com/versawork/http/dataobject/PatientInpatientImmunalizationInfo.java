package com.versawork.http.dataobject;

import java.io.Serializable;

import com.versawork.http.constant.VersaWorkConstant;

/**
 * @author Dheeraj
 * 
 */

public class PatientInpatientImmunalizationInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String immunalizationName;
	protected String immunalizationCode;
	protected String dateAdded;
	protected String status;
	protected String routeCode;
	protected String routeName;
	protected String sourceName;

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		if (routeCode != null) {
			this.routeCode = routeCode;
		} else {
			this.routeCode = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		if (routeName != null) {
			this.routeName = routeName;
		} else {
			this.routeName = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getImmunalizationName() {
		return immunalizationName;
	}

	public void setImmunalizationName(String immunalizationName) {
		this.immunalizationName = immunalizationName;
	}

	public String getImmunalizationCode() {
		return immunalizationCode;
	}

	public void setImmunalizationCode(String immunalizationCode) {
		if (immunalizationCode != null) {
			this.immunalizationCode = immunalizationCode;
		} else {
			this.immunalizationCode = VersaWorkConstant.NOT_APPLICABLE;
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
		if (status != null) {
			this.status = status;
		} else {
			this.status = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

}
