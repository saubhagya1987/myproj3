package com.versawork.http.dataobject;

import java.util.List;


public class PatientInformation {
	
	private List<VitalSigns> vitalSigns;
	private List<AllergiesInfo> allergiesInfo ;
	private List<PatientInpatientImmunalizationInfo> patientInpatientImmunalizationInfo;
	private List<PatientProblemInfo> patientProblemInfo;
	private PatientInpatientMetadataInfo  patientInpatientMetadataInfo;
	public PatientInpatientMetadataInfo getPatientInpatientMetadataInfo() {
		return patientInpatientMetadataInfo;
	}

	public void setPatientInpatientMetadataInfo(
			PatientInpatientMetadataInfo patientInpatientMetadataInfo) {
		this.patientInpatientMetadataInfo = patientInpatientMetadataInfo;
	}

	public List<AllergiesInfo> getAllergiesInfo() {
		return allergiesInfo;
	}

	public void setAllergiesInfo(List<AllergiesInfo> allergiesInfo) {
		this.allergiesInfo = allergiesInfo;
	}

	public List<PatientInpatientImmunalizationInfo> getPatientInpatientImmunalizationInfo() {
		return patientInpatientImmunalizationInfo;
	}

	public void setPatientInpatientImmunalizationInfo(
			List<PatientInpatientImmunalizationInfo> patientInpatientImmunalizationInfo) {
		this.patientInpatientImmunalizationInfo = patientInpatientImmunalizationInfo;
	}

	public List<PatientProblemInfo> getPatientProblemInfo() {
		return patientProblemInfo;
	}

	public void setPatientProblemInfo(List<PatientProblemInfo> patientProblemInfo) {
		this.patientProblemInfo = patientProblemInfo;
	}

	public List<VitalSigns> getVitalSigns() {
		return vitalSigns;
	}

	public void setVitalSigns(List<VitalSigns> vitalSigns) {
		this.vitalSigns = vitalSigns;
	}

	
}
