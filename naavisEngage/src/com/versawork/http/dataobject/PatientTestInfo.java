package com.versawork.http.dataobject;

import java.util.List;

public class PatientTestInfo {
	
	private List<LabResultInfo> labResultInfo;
	public List<LabResultInfo> getLabResultInfo() {
		return labResultInfo;
	}
	public void setLabResultInfo(List<LabResultInfo> labResultInfo) {
		this.labResultInfo = labResultInfo;
	}
	public List<PatientImagingInfo> getPatientImagingInfo() {
		return patientImagingInfo;
	}
	public void setPatientImagingInfo(List<PatientImagingInfo> patientImagingInfo) {
		this.patientImagingInfo = patientImagingInfo;
	}
	private List<PatientImagingInfo> patientImagingInfo;

}
