package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sohaib
 * 
 */
public class LabResults implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<LabGroups> labGroups;
	public List<NsPatientLabResult> labTests;

	public List<LabGroups> getLabGroups() {
		return labGroups;
	}

	public void setLabGroups(List<LabGroups> labGroups) {
		this.labGroups = labGroups;
	}

	public List<NsPatientLabResult> getLabTests() {
		return labTests;
	}

	public void setLabTests(List<NsPatientLabResult> labTests) {
		this.labTests = labTests;
	}

}
