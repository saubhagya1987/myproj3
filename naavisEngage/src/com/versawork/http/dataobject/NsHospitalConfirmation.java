package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsHospitalConfirmation implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer appoinmentID;
	protected String confirmBy;
	protected String confirmDate;
	String approveStatus;

	public Integer getAppoinmentID() {
		return appoinmentID;
	}

	public void setAppoinmentID(Integer appoinmentID) {
		this.appoinmentID = appoinmentID;
	}

	public String getConfirmBy() {
		return confirmBy;
	}

	public void setConfirmBy(String confirmBy) {
		this.confirmBy = confirmBy;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

}
