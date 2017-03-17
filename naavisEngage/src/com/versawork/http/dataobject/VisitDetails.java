package com.versawork.http.dataobject;

import java.util.List;



public class VisitDetails {

	private List<CarePlanInfo> carePlanInfo;
	//private PatientInpatientMetadataInfo  patientInpatientMetadataInfo;
	private List<ProcedureInfo> procedureInfo;
	private List<PatientInpatientFunctionalStatusInfo> patientInpatientFunctionalStatusInfo;	
	private List<PatientInpatientDiagnosisInfo> patientInpatientDiagnosisInfo;
	private List<CateTeamInfo> cateTeamInfo;
	private List<MedicationInfo> medicationInfo;	
	//private List<LabResultInfo> labRes;
	private PatientInformationAdmission patientInformationAdmission;
	private PatientInformationDischarge patientInformationDischarge;
	private PatientInformationHospitalizationReason patientInformationHospitalizationReason;
	/*private HospitalizationReason hospitalizationReason;
	private PatientPrescription patientPrescription;
	private String dischargeInstruction;*/
	private DischargeInstruction patientDischargeInstruction;
	private  PatientTestInfo patientTestInfo;
	
	
	
	public PatientTestInfo getPatientTestInfo() {
		return patientTestInfo;
	}
	public void setPatientTestInfo(PatientTestInfo patientTestInfo) {
		this.patientTestInfo = patientTestInfo;
	}
	public PatientInformationHospitalizationReason getPatientInformationHospitalizationReason() {
		return patientInformationHospitalizationReason;
	}
	public void setPatientInformationHospitalizationReason(
			PatientInformationHospitalizationReason patientInformationHospitalizationReason) {
		this.patientInformationHospitalizationReason = patientInformationHospitalizationReason;
	}
	public DischargeInstruction getPatientDischargeInstruction() {
		return patientDischargeInstruction;
	}
	public void setPatientDischargeInstruction(
			DischargeInstruction patientDischargeInstruction) {
		this.patientDischargeInstruction = patientDischargeInstruction;
	}
	public PatientInformationAdmission getPatientInformationAdmission() {
		return patientInformationAdmission;
	}
	public void setPatientInformationAdmission(
			PatientInformationAdmission patientInformationAdmission) {
		this.patientInformationAdmission = patientInformationAdmission;
	}
	public PatientInformationDischarge getPatientInformationDischarge() {
		return patientInformationDischarge;
	}
	public void setPatientInformationDischarge(
			PatientInformationDischarge patientInformationDischarge) {
		this.patientInformationDischarge = patientInformationDischarge;
	}
	
	
	public List<ProcedureInfo> getProcedureInfo() {
		return procedureInfo;
	}
	public void setProcedureInfo(List<ProcedureInfo> procedureInfo) {
		this.procedureInfo = procedureInfo;
	}
	public List<PatientInpatientDiagnosisInfo> getPatientInpatientDiagnosisInfo() {
		return patientInpatientDiagnosisInfo;
	}
	public void setPatientInpatientDiagnosisInfo(
			List<PatientInpatientDiagnosisInfo> patientInpatientDiagnosisInfo) {
		this.patientInpatientDiagnosisInfo = patientInpatientDiagnosisInfo;
	}
	public List<MedicationInfo> getMedicationInfo() {
		return medicationInfo;
	}
	public void setMedicationInfo(List<MedicationInfo> medicationInfo) {
		this.medicationInfo = medicationInfo;
	}
	/*public List<LabResultInfo> getLabRes() {
		return labRes;
	}
	public void setLabRes(List<LabResultInfo> labRes) {
		this.labRes = labRes;
	}*/	
		
	public List<CarePlanInfo> getCarePlanInfo() {
		return carePlanInfo;
	}
	public void setCarePlanInfo(List<CarePlanInfo> carePlanInfo) {
		this.carePlanInfo = carePlanInfo;
	}
	
	public List<PatientInpatientFunctionalStatusInfo> getPatientInpatientFunctionalStatusInfo() {
		return patientInpatientFunctionalStatusInfo;
	}
	public void setPatientInpatientFunctionalStatusInfo(
			List<PatientInpatientFunctionalStatusInfo> patientInpatientFunctionalStatusInfo) {
		this.patientInpatientFunctionalStatusInfo = patientInpatientFunctionalStatusInfo;
	}
	
	public List<CateTeamInfo> getCateTeamInfo() {
		return cateTeamInfo;
	}
	public void setCateTeamInfo(List<CateTeamInfo> cateTeamInfo) {
		this.cateTeamInfo = cateTeamInfo;
	}
	/*public PatientInpatientMetadataInfo getPatientInpatientMetadataInfo() {
		return patientInpatientMetadataInfo;
	}
	public void setPatientInpatientMetadataInfo(
			PatientInpatientMetadataInfo patientInpatientMetadataInfo) {
		this.patientInpatientMetadataInfo = patientInpatientMetadataInfo;
	}*/
	
	}
