package com.versawork.asyn.dataobject;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.versawork.asyn.constant.VersaworkConstants;

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
			this.admissionDate = admissionDate;
		} else {
			this.admissionDate = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getDischargeDate() {

		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {

		if (dischargeDate != null) {
			this.dischargeDate = dischargeDate;
		} else {
			this.dischargeDate = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getAdmissionLocation() {

		return admissionLocation;
	}

	public void setAdmissionLocation(String admissionLocation) {

		if (admissionLocation != null) {
			this.admissionLocation = admissionLocation;
		} else {
			this.admissionLocation = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getDischargeLocation() {

		return dischargeLocation;
	}

	public void setDischargeLocation(String dischargeLocation) {

		if (dischargeLocation != null) {
			this.dischargeLocation = dischargeLocation;
		} else {
			this.dischargeLocation = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getHospitalizationReason() {

		return hospitalizationReason;
	}

	public void setHospitalizationReason(String hospitalizationReason) {

		if (hospitalizationReason != null) {
			this.hospitalizationReason = hospitalizationReason;
		} else {
			this.hospitalizationReason = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getDischargeInstruction() {

		return dischargeInstruction;
	}

	public void setDischargeInstruction(String dischargeInstruction) {

		if (dischargeInstruction != null) {
			this.dischargeInstruction = dischargeInstruction;
		} else {
			this.dischargeInstruction = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public Integer getPatientInpatientId() {

		return patientInpatientId;
	}

	public void setPatientInpatientId(Integer patientInpatientId) {

		this.patientInpatientId = patientInpatientId;
	}
}
