package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.versawork.http.constant.VersaWorkConstant;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PatientInpatientMetadataInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected Integer patientInpatientId;
	protected String admissionDate;
	protected String dischargeDate;
	protected String admissionLocation;
	protected String dischargeLocation;
	protected String hospitalizationReason;
	protected String dischargeInstruction;

	protected List<PatientInpatientDiagnosisInfo> patientInpatientDiagnosisInfoList;
	protected List<PatientInpatientFunctionalStatusInfo> patientInpatientFunctionalStatusInfoList;
	protected List<PatientInpatientImmunalizationInfo> patientInpatientImmunalizationInfoList;
	protected String name;
	protected String sex;
	protected String race;
	protected String ethinicity;
	protected String smokingStatus;
	protected String birthdate;
	protected String preferredLanguage; 

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEthinicity() {
		return ethinicity;
	}

	public void setEthinicity(String ethinicity) {
		this.ethinicity = ethinicity;
	}

	public String getSmokingStatus() {
		return smokingStatus;
	}

	public void setSmokingStatus(String smokingStatus) {
		this.smokingStatus = smokingStatus;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public List<PatientInpatientFunctionalStatusInfo> getPatientInpatientFunctionalStatusInfoList() {
		return patientInpatientFunctionalStatusInfoList;
	}

	public void setPatientInpatientFunctionalStatusInfoList(
			List<PatientInpatientFunctionalStatusInfo> patientInpatientFunctionalStatusInfoList) {
		this.patientInpatientFunctionalStatusInfoList = patientInpatientFunctionalStatusInfoList;
	}

	public List<PatientInpatientImmunalizationInfo> getPatientInpatientImmunalizationInfoList() {
		return patientInpatientImmunalizationInfoList;
	}

	public void setPatientInpatientImmunalizationInfoList(
			List<PatientInpatientImmunalizationInfo> patientInpatientImmunalizationInfoList) {
		this.patientInpatientImmunalizationInfoList = patientInpatientImmunalizationInfoList;
	}

	public List<PatientInpatientDiagnosisInfo> getPatientInpatientDiagnosisInfoList() {
		return patientInpatientDiagnosisInfoList;
	}

	public void setPatientInpatientDiagnosisInfoList(
			List<PatientInpatientDiagnosisInfo> patientInpatientDiagnosisInfoList) {
		this.patientInpatientDiagnosisInfoList = patientInpatientDiagnosisInfoList;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		if (admissionDate != null) {
			this.admissionDate = admissionDate.replace(" ", " - ");
		} else {
			this.admissionDate = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		if (dischargeDate != null) {
			this.dischargeDate = dischargeDate.replace(" ", " - ");
		} else {
			this.dischargeDate = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getAdmissionLocation() {
		return admissionLocation;
	}

	public void setAdmissionLocation(String admissionLocation) {
		if (admissionLocation != null) {
			this.admissionLocation = admissionLocation;
		} else {
			this.admissionLocation = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDischargeLocation() {
		return dischargeLocation;
	}

	public void setDischargeLocation(String dischargeLocation) {
		if (dischargeLocation != null) {
			this.dischargeLocation = dischargeLocation;
		} else {
			this.dischargeLocation = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getHospitalizationReason() {
		return hospitalizationReason;
	}

	public void setHospitalizationReason(String hospitalizationReason) {
		if (hospitalizationReason != null) {
			this.hospitalizationReason = hospitalizationReason;
		} else {
			this.hospitalizationReason = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDischargeInstruction() {
		return dischargeInstruction;
	}

	public void setDischargeInstruction(String dischargeInstruction) {
		if (dischargeInstruction != null) {
			this.dischargeInstruction = dischargeInstruction;
		} else {
			this.dischargeInstruction = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public Integer getPatientInpatientId() {
		return patientInpatientId;
	}

	public void setPatientInpatientId(Integer patientInpatientId) {
		this.patientInpatientId = patientInpatientId;
	}
}
