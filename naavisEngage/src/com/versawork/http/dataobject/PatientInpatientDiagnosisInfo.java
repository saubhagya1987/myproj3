package com.versawork.http.dataobject;

import java.io.Serializable;

import com.versawork.http.constant.VersaWorkConstant;

/**
 * @author Dheeraj
 * 
 */

public class PatientInpatientDiagnosisInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String diagnosisDesc;
	protected String diagnosisCode;
	protected String dateAdded;
	protected String status;

	public String getDiagnosisDesc() {
		return diagnosisDesc;
	}

	public void setDiagnosisDesc(String diagnosisDesc) {
		this.diagnosisDesc = diagnosisDesc;
	}

	public String getDiagnosisCode() {
		return diagnosisCode;
	}

	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		if (dateAdded != null) {
			this.dateAdded = dateAdded;
		} else {
			this.dateAdded = VersaWorkConstant.NOT_APPLICABLE;
		}
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
