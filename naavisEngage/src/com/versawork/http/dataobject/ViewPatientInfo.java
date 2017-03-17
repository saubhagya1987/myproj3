package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ViewPatientInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected Integer appointmentId;
	protected Integer accountId;
	protected Integer patientId;
	protected Integer visitId;
	protected String unitNumber;
	protected String docType;
	protected String firstName;
	protected String lastName;
	protected String sex;
	protected String birthDate;
	protected String race;
	protected String raceCode;
	protected String ethnicity;
	protected String ethnicityCode;
	protected String preferredLang;
	protected String height;
	protected String weight;
	protected String bloodPressure;
	protected String BMI;
	protected String smokingStatus;
	protected String smokingStatusCode;
	protected String type;
	protected Integer muDataId;
	protected Integer inPatientMetaDataId;
	protected String version;
	protected String dateAdded;
	protected String accessDate;
	protected Short visitTypeId;

	public Short getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Short visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public String getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(String accessDate) {
		this.accessDate = accessDate;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	protected String providerName;
	protected String providerContactInfo;

	protected List<AllergiesInfo> AllergiesInfoList;
	protected List<MedicationInfo> medicationInfoList;
	protected List<PatientProblemInfo> patientProblemInfoList;
	protected List<ProcedureInfo> procedureInfoList;
	protected List<LabResultInfo> labResultInfoList;
	protected List<CarePlanInfo> carePlanInfoList;

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	public String getEthnicityCode() {
		return ethnicityCode;
	}

	public void setEthnicityCode(String ethnicityCode) {
		this.ethnicityCode = ethnicityCode;
	}

	public String getSmokingStatusCode() {
		return smokingStatusCode;
	}

	public void setSmokingStatusCode(String smokingStatusCode) {
		this.smokingStatusCode = smokingStatusCode;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getInPatientMetaDataId() {
		return inPatientMetaDataId;
	}

	public void setInPatientMetaDataId(Integer inPatientMetaDataId) {
		this.inPatientMetaDataId = inPatientMetaDataId;
	}

	public Integer getMuDataId() {
		return muDataId;
	}

	public void setMuDataId(Integer muDataId) {
		this.muDataId = muDataId;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getPreferredLang() {
		return preferredLang;
	}

	public void setPreferredLang(String preferredLang) {
		this.preferredLang = preferredLang;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public String getBMI() {
		return BMI;
	}

	public void setBMI(String bMI) {
		BMI = bMI;
	}

	public String getSmokingStatus() {
		return smokingStatus;
	}

	public void setSmokingStatus(String smokingStatus) {
		this.smokingStatus = smokingStatus;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderContactInfo() {
		return providerContactInfo;
	}

	public void setProviderContactInfo(String providerContactInfo) {
		this.providerContactInfo = providerContactInfo;
	}

	public List<AllergiesInfo> getAllergiesInfoList() {
		return AllergiesInfoList;
	}

	public void setAllergiesInfoList(List<AllergiesInfo> allergiesInfoList) {
		AllergiesInfoList = allergiesInfoList;
	}

	public List<MedicationInfo> getMedicationInfoList() {
		return medicationInfoList;
	}

	public void setMedicationInfoList(List<MedicationInfo> medicationInfoList) {
		this.medicationInfoList = medicationInfoList;
	}

	public List<PatientProblemInfo> getPatientProblemInfoList() {
		return patientProblemInfoList;
	}

	public void setPatientProblemInfoList(List<PatientProblemInfo> patientProblemInfoList) {
		this.patientProblemInfoList = patientProblemInfoList;
	}

	public List<ProcedureInfo> getProcedureInfoList() {
		return procedureInfoList;
	}

	public void setProcedureInfoList(List<ProcedureInfo> procedureInfoList) {
		this.procedureInfoList = procedureInfoList;
	}

	public List<LabResultInfo> getLabResultInfoList() {
		return labResultInfoList;
	}

	public void setLabResultInfoList(List<LabResultInfo> labResultInfoList) {
		this.labResultInfoList = labResultInfoList;
	}

	public List<CarePlanInfo> getCarePlanInfoList() {
		return carePlanInfoList;
	}

	public void setCarePlanInfoList(List<CarePlanInfo> carePlanInfoList) {
		this.carePlanInfoList = carePlanInfoList;
	}

}
