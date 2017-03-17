package com.versawork.asyn.dataobject;

import java.io.Serializable;

import com.versawork.asyn.constant.VersaworkConstants;

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

	public String getRouteCode() {

		return routeCode;
	}

	public void setRouteCode(String routeCode) {

		if (routeCode != null) {
			this.routeCode = routeCode;
		} else {
			this.routeCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getRouteName() {

		return routeName;
	}

	public void setRouteName(String routeName) {

		if (routeName != null) {
			this.routeName = routeName;
		} else {
			this.routeName = VersaworkConstants.NOT_APPLICABLE.getMessage();
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
			this.immunalizationCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
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
			this.status = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

}
