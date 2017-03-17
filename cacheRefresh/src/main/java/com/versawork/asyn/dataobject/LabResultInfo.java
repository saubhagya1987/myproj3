package com.versawork.asyn.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.versawork.asyn.constant.VersaworkConstants;

/**
 * @author Dheeraj
 *
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class LabResultInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String labResultId;
	protected String labTest;
	protected String labTestCode;
	protected String labResult;
	protected String labResultUnit;
	protected String orgName;
	protected String orgCode;
	protected String interpretationCode;
	protected String dateAdded;

	public String getLabResultUnit() {

		return labResultUnit;
	}

	public void setLabResultUnit(String labResultUnit) {

		this.labResultUnit = labResultUnit;
	}

	public String getOrgName() {

		return orgName;
	}

	public void setOrgName(String orgName) {

		if (orgName != null) {
			this.orgName = orgName;
		} else {
			this.orgName = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getOrgCode() {

		return orgCode;
	}

	public void setOrgCode(String orgCode) {

		if (orgCode != null) {
			this.orgCode = orgCode;
		} else {
			this.orgCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getInterpretationCode() {

		return interpretationCode;
	}

	public void setInterpretationCode(String interpretationCode) {

		if (interpretationCode != null) {
			this.interpretationCode = interpretationCode;
		} else {
			this.interpretationCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getLabResultId() {

		return labResultId;
	}

	public void setLabResultId(String labResultId) {

		this.labResultId = labResultId;
	}

	public String getLabTest() {

		return labTest;
	}

	public void setLabTest(String labTest) {

		this.labTest = labTest;
	}

	public String getLabTestCode() {

		return labTestCode;
	}

	public void setLabTestCode(String labTestCode) {

		if (labTestCode != null) {
			this.labTestCode = labTestCode;
		} else {
			this.labTestCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getLabResult() {

		return labResult;
	}

	public void setLabResult(String labResult) {

		this.labResult = labResult;
	}

	public String getDateAdded() {

		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {

		this.dateAdded = dateAdded;
	}

}
