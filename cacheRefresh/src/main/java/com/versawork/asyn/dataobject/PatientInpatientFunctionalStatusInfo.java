package com.versawork.asyn.dataobject;

import java.io.Serializable;

import com.versawork.asyn.constant.VersaworkConstants;

/**
 * @author Dheeraj
 *
 */

public class PatientInpatientFunctionalStatusInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String functionalStatusDesc;
	protected String functionalStatusCode;
	protected String dateAdded;
	protected String status;
	protected String statusCode;

	public String getFunctionalStatusDesc() {

		return functionalStatusDesc;
	}

	public void setFunctionalStatusDesc(String functionalStatusDesc) {

		this.functionalStatusDesc = functionalStatusDesc;
	}

	public String getFunctionalStatusCode() {

		return functionalStatusCode;
	}

	public void setFunctionalStatusCode(String functionalStatusCode) {

		if (functionalStatusCode != null) {
			this.functionalStatusCode = functionalStatusCode;
		} else {
			this.functionalStatusCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getStatusCode() {

		return statusCode;
	}

	public void setStatusCode(String statusCode) {

		if (statusCode != null) {
			this.statusCode = statusCode;
		} else {
			this.statusCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
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
