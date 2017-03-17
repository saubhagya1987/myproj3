package com.versawork.asyn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.versawork.asyn.dataobject.AllergiesInfo;
import com.versawork.asyn.dataobject.CarePlanInfo;
import com.versawork.asyn.dataobject.CareTeamInfo;
import com.versawork.asyn.dataobject.LabResultInfo;
import com.versawork.asyn.dataobject.MedicationInfo;
import com.versawork.asyn.dataobject.NsPatientLabResult;
import com.versawork.asyn.dataobject.NsPatientPrescription;
import com.versawork.asyn.dataobject.NsPatientVisit;
import com.versawork.asyn.dataobject.PatientImagingInfo;
import com.versawork.asyn.dataobject.PatientInpatientDiagnosisInfo;
import com.versawork.asyn.dataobject.PatientInpatientFunctionalStatusInfo;
import com.versawork.asyn.dataobject.PatientInpatientImmunalizationInfo;
import com.versawork.asyn.dataobject.PatientInpatientMetadataInfo;
import com.versawork.asyn.dataobject.PatientProblemInfo;
import com.versawork.asyn.dataobject.ProcedureInfo;
import com.versawork.asyn.dataobject.VitalSigns;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.PatientAllergy;
import com.versawork.http.model.PatientCarePlan;
import com.versawork.http.model.PatientCareTeam;
import com.versawork.http.model.PatientDiagnosis;
import com.versawork.http.model.PatientFunctionalStatus;
import com.versawork.http.model.PatientImaging;
import com.versawork.http.model.PatientImmunization;
import com.versawork.http.model.PatientLab;
import com.versawork.http.model.PatientPrescription;
import com.versawork.http.model.PatientProblem;
import com.versawork.http.model.PatientProcedure;
import com.versawork.http.model.PatientVerification;
import com.versawork.http.model.PatientVisit;
import com.versawork.http.model.PatientVisitInpatient;
import com.versawork.http.model.PatientVitalSign;

public final class CacheInfoObject {
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheInfoObject.class);

	public static NsPatientLabResult getNsPatientLabResult(PatientLab patientLab) {
		NsPatientLabResult nsPatLabRes = new NsPatientLabResult();
		nsPatLabRes.setAbnormalFlag(patientLab.getAbnormalFlag());
		try {
			nsPatLabRes.setDateAdded((new SimpleDateFormat("MMMM d, yyyy").format(patientLab.getDateAdded()))
					.toString());
		} catch (Exception e) {
			LOGGER.debug("Failed to set date added for lab result.");
		}
		nsPatLabRes.setGroupCode(patientLab.getLabGroupCode());
		try {
			nsPatLabRes.setGroupDate((new SimpleDateFormat("MM/dd/yyyy").format(patientLab.getLabGroupDate()))
					.toString());
		} catch (Exception e) {
			LOGGER.debug("Failed to set group date for lab result");
		}
		nsPatLabRes.setGroupId(patientLab.getPatientLabPK().getLabGroupId());
		nsPatLabRes.setGroupName(patientLab.getLabGroupName());
		nsPatLabRes.setInterpretationCode(patientLab.getInterpretationCode());
		nsPatLabRes.setLabCode(patientLab.getLabCode());
		nsPatLabRes.setLabId(patientLab.getPatientLabPK().getLabId());
		nsPatLabRes.setLabResult(patientLab.getLabResult());
		nsPatLabRes.setLabUnit(patientLab.getLabUnit());
		nsPatLabRes.setOrganizerCode(patientLab.getOrganizerCode());
		nsPatLabRes.setOrganizerName(patientLab.getOrganizerName());
		nsPatLabRes.setTestId(patientLab.getPatientLabPK().getTestId());
		nsPatLabRes.setNormalRangeMax(patientLab.getNormalRangeMax());
		nsPatLabRes.setNormalRangeMin(patientLab.getNormalRangeMin());
		nsPatLabRes.setAbsoluteRangeMax(patientLab.getAbsoluteRangeMax());
		nsPatLabRes.setAbsoluteRangeMin(patientLab.getAbsoluteRangeMin());
		

		try {
			nsPatLabRes.setResultDate((new SimpleDateFormat("MM/dd/yyyy").format(patientLab.getResultDate())).toString());
		} catch (Exception e) {
			LOGGER.debug("Could not set result date for lab test");
		}
		nsPatLabRes.setSourceName(patientLab.getSourceName());
		nsPatLabRes.setTestName(patientLab.getTestName());
		return nsPatLabRes;
	}

	public static PatientInpatientFunctionalStatusInfo getPatientInpatientFunctionalStatusInfo(
			PatientFunctionalStatus patientInpatientFunctionalStatus) {
		PatientInpatientFunctionalStatusInfo patientInpatientFunctionalStatusInfo = new PatientInpatientFunctionalStatusInfo();
		patientInpatientFunctionalStatusInfo.setFunctionalStatusDesc(patientInpatientFunctionalStatus
				.getFunctionDescription());
		patientInpatientFunctionalStatusInfo
				.setFunctionalStatusCode(patientInpatientFunctionalStatus.getFunctionCode());
		patientInpatientFunctionalStatusInfo.setStatus(patientInpatientFunctionalStatus.getStatus());
		patientInpatientFunctionalStatusInfo.setStatusCode(patientInpatientFunctionalStatus.getStatusCode());
		try {
			Date dateAdded = patientInpatientFunctionalStatus.getStatusDate();
			String dateAddedString = dateAdded != null ? DateUtils.getFormatDate(
					patientInpatientFunctionalStatus.getStatusDate(), "MM/dd/yyyy") : null;
			patientInpatientFunctionalStatusInfo.setDateAdded(dateAddedString);
		} catch (Exception e) {
			LOGGER.debug("Could not set date added for functional status list for visit : "
					+ patientInpatientFunctionalStatus.getPatientFunctionalStatusPK().getPatientVisitId());
		}

		return patientInpatientFunctionalStatusInfo;
	}

	public static CarePlanInfo getCarePlanInfo(PatientCarePlan patientCarePlan) {
		CarePlanInfo carePlanInfo = new CarePlanInfo();
		carePlanInfo.setCarePlanId(patientCarePlan.getPatientCarePlanPK().getCarePlanId());
		carePlanInfo.setGoal(patientCarePlan.getGoal());
		carePlanInfo.setInstructions(patientCarePlan.getInstructions());

		return carePlanInfo;
	}

	public static CareTeamInfo getCareTeamInfo(PatientCareTeam patientCareTeam, Integer clientDBId) {

	    CareTeamInfo careTeamInfo = new CareTeamInfo();
		careTeamInfo.setCareMemberName(patientCareTeam.getCareMemberName());
		careTeamInfo.setAddress(patientCareTeam.getAddress1());
		careTeamInfo.setPhoneNumber(patientCareTeam.getPhoneNumber());
		careTeamInfo.setContactEmail("example@example.com");

		careTeamInfo.setClientDatabaseId(clientDBId);
		careTeamInfo.setSpecialty(patientCareTeam.getCareMemberSpeciality());
		careTeamInfo.setContactEmail(patientCareTeam.getCareMemberEmail());
		// LOGGER.debug("patientCareTeam.getCareMemberId()  "
		// + patientCareTeam.getPatientCareTeamPK().getCareMemberId());
		return careTeamInfo;

	}

	public static PatientInpatientImmunalizationInfo getPatientInpatientImmunalizationInfo(
			PatientImmunization patientInpatientImmunization) {

		PatientInpatientImmunalizationInfo patientInpatientImmunalizationInfo = new PatientInpatientImmunalizationInfo();
		patientInpatientImmunalizationInfo.setImmunalizationName(patientInpatientImmunization.getImmunizationName());
		patientInpatientImmunalizationInfo.setImmunalizationCode(patientInpatientImmunization.getImmunizationCode());
		patientInpatientImmunalizationInfo.setStatus(patientInpatientImmunization.getStatus());
		try {
			java.util.Date statusDate = patientInpatientImmunization.getStatusDate();

			String statusString = statusDate != null ? DateUtils.getFormatDate(
					patientInpatientImmunization.getStatusDate(), "MM/dd/yyyy") : null;
			patientInpatientImmunalizationInfo.setDateAdded(statusString);
		} catch (Exception e) {
			LOGGER.debug("Could not set date added for immunization list for visit : ");
		}
		return patientInpatientImmunalizationInfo;

	}

	public static LabResultInfo getLabResultInfo(PatientLab patientlabResult) {
		LabResultInfo labResultInfo = new LabResultInfo();
		labResultInfo.setLabResultId(patientlabResult.getPatientLabPK().getLabId());
		labResultInfo.setLabTest(patientlabResult.getTestName());
		labResultInfo.setLabTestCode(patientlabResult.getLabCode());
		labResultInfo.setLabResult(patientlabResult.getLabResult());
		try {
			if (patientlabResult.getDateAdded() != null)
				labResultInfo.setDateAdded(DateUtils.getFormatDate(patientlabResult.getDateAdded(), "MM/dd/yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("Could not set date added for lab result for visit : ");
		}
		return labResultInfo;
	}

	public static ProcedureInfo getProcedureInfo(PatientProcedure patientProcedure) {
		ProcedureInfo procedureInfo = new ProcedureInfo();
		procedureInfo.setProcedureId(patientProcedure.getProcedureCode());
		procedureInfo.setProcedureName(patientProcedure.getProcedureName());
		procedureInfo.setProcedureCode(patientProcedure.getProcedureCode());
		try {
			procedureInfo.setDateAdded(DateUtils.getFormatDate(patientProcedure.getProcedureDate(), "MM/dd/yyyy"));
		} catch (Exception e) {
			LOGGER.debug("Could not set date added for patient procedure list for visit : ");
		}
		return procedureInfo;
	}

	public static VitalSigns getVitalSigns(PatientVitalSign patientVitalsigns) {

		VitalSigns vitalSigns = new VitalSigns();
		vitalSigns.setBMI(patientVitalsigns.getBmi());
		if (patientVitalsigns.getDiastolicBp() != null && patientVitalsigns.getDiastolicBpUnit() != null
				&& patientVitalsigns.getDiastolicBp() != "" && patientVitalsigns.getDiastolicBpUnit() != "") {
			vitalSigns
					.setDiastolicBp(patientVitalsigns.getDiastolicBp() + " " + patientVitalsigns.getDiastolicBpUnit());
		}
		vitalSigns.setDiastolicBpUnit(patientVitalsigns.getDiastolicBpUnit());
		vitalSigns.setHeightFeet(patientVitalsigns.getHeightFeet());
		vitalSigns.setHeightInches(patientVitalsigns.getHeightInches());
		vitalSigns.setWeightLbs(patientVitalsigns.getWeightLbs());

		if (patientVitalsigns.getSystolicBp() != null && patientVitalsigns.getSystolicBp() != ""
				&& patientVitalsigns.getSystolicBpUnit() != null && patientVitalsigns.getSystolicBpUnit() != "") {
			vitalSigns.setSystolicBp(patientVitalsigns.getSystolicBp() + " " + patientVitalsigns.getSystolicBpUnit());
		}
		vitalSigns.setSystolicBpUnit(patientVitalsigns.getSystolicBpUnit());
		try {
			vitalSigns.setDateAdded(DateUtils.getFormatDate(patientVitalsigns.getDateAdded(), "MM/dd/yyyy"));
		} catch (Exception e) {
			LOGGER.debug("Could not set dateAdded in vital signs for visit : ");
		}

		return vitalSigns;

	}

	public static PatientProblemInfo getPatientProblemInfo(PatientProblem patientProblem) {

		PatientProblemInfo patientProblemInfo = new PatientProblemInfo();

		// patientProblemInfo.setProblemId(Integer.parseInt(patientProblem.getPatientProblemPK().getProblemCode()));
		patientProblemInfo.setProblemName(patientProblem.getProblemName());
		patientProblemInfo.setProblemId(patientProblem.getPatientProblemPK().getProblemId());
		patientProblemInfo.setProblemCode(patientProblem.getProblemCode());
		patientProblemInfo.setSourceName(patientProblem.getSourceName());

		try {
			patientProblemInfo.setDateAdded(DateUtils.getFormatDate(patientProblem.getDateAdded(), "MM/dd/yyyy"));
		} catch (Exception e) {
			LOGGER.debug("Could not set date added for problem list for account : ");
		}
		patientProblemInfo.setStatus(patientProblem.getStatus());
		patientProblemInfo.setStatusCode(patientProblem.getStatusCode());
		return patientProblemInfo;

	}

	public static MedicationInfo getPatientMedicationInfo(PatientPrescription patientmedication)
			throws BusinessException {
		MedicationInfo medicationInfo = new MedicationInfo();
		medicationInfo.setMedicationId(patientmedication.getPatientPrescriptionPK().getMedicationId());
		medicationInfo.setMedicationName(patientmedication.getMedicationName());
		medicationInfo.setRxNumber(patientmedication.getRxNumber());
		medicationInfo.setDosageDescription(patientmedication.getDosageDescription());
		if (patientmedication.getDateAdded() != null) {
			medicationInfo.setDateAdded(DateUtils.getFormatDate(patientmedication.getDateAdded(), "MM/dd/yyyy"));
		} else {
			medicationInfo.setDateAdded(null);
		}
		medicationInfo.setDirection(patientmedication.getDirection());
		medicationInfo.setRouteName(patientmedication.getRouteOfAdministration());
		medicationInfo.setDoseStrength(patientmedication.getDoseStrength());
		medicationInfo.setDoseQuantity(patientmedication.getMedicationStrength());
		medicationInfo.setUnit(patientmedication.getUnit());
		medicationInfo.setFrequency(patientmedication.getFrequency());
		medicationInfo.setStatus(patientmedication.getStatus());
		medicationInfo.setPrescriptionAction(patientmedication.getPrescriptionActionId().getPrescriptionAction());
		return medicationInfo;
	}

	public static AllergiesInfo getPatientAllergiesInfo(PatientAllergy patientAllergies) {
		AllergiesInfo allergiesInfo = new AllergiesInfo();
		allergiesInfo.setAllergiesId(patientAllergies.getPatientAllergyPK().getAllergyId());
		allergiesInfo.setAllergen(patientAllergies.getAllergenName());
		String reaction = patientAllergies.getReaction() != null ? patientAllergies.getReaction() : "N/A";
		allergiesInfo.setReaction(reaction);
		allergiesInfo.setStatus(patientAllergies.getStatus());
		allergiesInfo.setAllergenCode(patientAllergies.getAllergenCode());
		allergiesInfo.setReactionCode(patientAllergies.getReactionCode());
		allergiesInfo.setSourceName(patientAllergies.getSourceName());
		allergiesInfo.setStatusCode(patientAllergies.getStatusCode());
		try {
			allergiesInfo.setDateAdded(DateUtils.getFormatDate(patientAllergies.getDateAdded(), "dd/MM/yyyy"));
		} catch (Exception e) {
			LOGGER.debug("Could not set date added for allergies list for account id  : "
					+ patientAllergies.getPatientAllergyPK().getAccountId());
		}
		return allergiesInfo;
	}

	public static PatientInpatientMetadataInfo getPatientInpatientMetadataInfo(
			PatientVisitInpatient patientInpatientMetadata) {
	    	PatientVisit patientMuData = patientInpatientMetadata.getPatientVisit();
		PatientInpatientMetadataInfo patientInpatientMetadataInfo = new PatientInpatientMetadataInfo();
		// patientInpatientMetadataInfo.setPatientInpatientId(patientInpatientMetadata.getPatientVisitInpatientPK());
		try {

			patientInpatientMetadataInfo.setAdmissionDate(DateUtils.getFormatDate(
					patientMuData.getEncounterStartDate(), "MM/dd/yyyy HH:mm"));
		} catch (Exception e) {
			LOGGER.debug("Could not set admission date for inpatient metadata list for visit : "
					+ patientMuData.getPatientVisitPK().getPatientVisitId());
		}
		patientInpatientMetadataInfo.setAdmissionLocation(patientInpatientMetadata.getAdmitLocation());
		try {
			patientInpatientMetadataInfo.setDischargeDate(DateUtils.getFormatDate(patientMuData.getEncounterEndDate(),
					"MM/dd/yyyy HH:mm"));
		} catch (Exception e) {
			LOGGER.debug("Could not set dischage date for inpatient metadata list for visit : "
					+ patientMuData.getPatientVisitPK().getPatientVisitId());
		}
		patientInpatientMetadataInfo.setDischargeLocation(patientInpatientMetadata.getDischargeLocation());
		patientInpatientMetadataInfo.setHospitalizationReason(patientInpatientMetadata.getReasonForHospitalization());
		patientInpatientMetadataInfo.setDischargeInstruction(patientInpatientMetadata.getDischargeInstruction());
		return patientInpatientMetadataInfo;
	}

	public static PatientInpatientDiagnosisInfo getPatientInpatientDiagnosisInfo(
			PatientDiagnosis patientInpatientDiagnosis) {
		PatientInpatientDiagnosisInfo patientInpatientDiagnosisInfo = new PatientInpatientDiagnosisInfo();
		patientInpatientDiagnosisInfo.setDiagnosisDesc(patientInpatientDiagnosis.getDiagnosisName());
		patientInpatientDiagnosisInfo.setDiagnosisCode(patientInpatientDiagnosis.getPatientDiagnosisPK()
				.getDiagnosisCode());
		patientInpatientDiagnosisInfo.setStatus(patientInpatientDiagnosis.getStatus());
		try {
			Date dateAdded = patientInpatientDiagnosis.getDateAdded();
			String dateAddedString = dateAdded != null ? DateUtils.getFormatDate(
					patientInpatientDiagnosis.getDateAdded(), "MM/dd/yyyy") : null;

			patientInpatientDiagnosisInfo.setDateAdded(dateAddedString);
		} catch (Exception e) {
			LOGGER.error("Could not set date added for inpatient diagnosis for visit  " + ExceptionUtils.getStackTrace(e) );
		}
		return patientInpatientDiagnosisInfo;
	}

	public static PatientImagingInfo getPatientImagingInfo(PatientImaging patientImagings) {
		PatientImagingInfo e = new PatientImagingInfo();
		e.setAccountId(patientImagings.getPatientImagingPK().getAccountId());
		try {
			e.setDateAdded(DateUtils.getFormatDate(patientImagings.getDateAdded(), "MM/dd/yyyy"));
		} catch (Exception excep) {
			LOGGER.info("Could not set date added");
		}
		try {
			e.setExamDate(DateUtils.getFormatDate(patientImagings.getExamDate(), "MM/dd/yyyy"));
		} catch (Exception excep) {
			LOGGER.info("Could not set exam date");
		}
		e.setExamDates(patientImagings.getExamDate());
		e.setExamId(patientImagings.getPatientImagingPK().getExamId());
		e.setExamMessage(patientImagings.getExamMessage());
		e.setReportNumber(patientImagings.getPatientImagingPK().getExamId());
		e.setExamName(patientImagings.getExamName());
		e.setExamTechnologist(patientImagings.getExamTechnologist());
		e.setOrderingProvider(patientImagings.getOrderingProvider());
		e.setPatientVisitId(patientImagings.getPatientImagingPK().getPatientVisitId());
		e.setSourceId(patientImagings.getSourceId());
		e.setSourceName(patientImagings.getSourceName());
		return e;
	}

	public static NsPatientPrescription getNsPatientPrescription(PatientPrescription patientPrescription) {
		NsPatientPrescription nsPatientPrescription = new NsPatientPrescription();
		nsPatientPrescription.setDrugName(patientPrescription.getMedicationName());
		nsPatientPrescription.setDosageDescription(patientPrescription.getDosageDescription());
		nsPatientPrescription.setRxNumber(patientPrescription.getRxNumber());
		nsPatientPrescription.setRouteName(patientPrescription.getRouteOfAdministration());
		try {
		if (patientPrescription.getDateAdded() != null) {
			nsPatientPrescription.setDateAdded(DateUtils.getFormatDate(patientPrescription.getDateAdded(), "MM/dd/yyyy"));
		} else {
			nsPatientPrescription.setDateAdded(null);
		}
		
		
			Date date = patientPrescription.getDateAdded();
			String dateString = date != null ? DateUtils.getFormatDate(patientPrescription.getDateAdded(),
					"MMMM d, yyyy") : null;
			nsPatientPrescription.setIssuedDate(dateString);
		} catch (Exception e) {
			nsPatientPrescription.setIssuedDate(null);
			e.printStackTrace();
			LOGGER.debug("Failed to set issued date for prescription.");
		}

		try {
			nsPatientPrescription.setDose(patientPrescription.getMedicationStrength());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("Unable to get getDoseQuantity.");
		}
		try {
			if (patientPrescription.getPatientVisit() != null) {
				nsPatientPrescription.setEncounterEndDate(patientPrescription.getPatientVisit().getEncounterEndDate());
				nsPatientPrescription.setEncounterStartDate(patientPrescription.getPatientVisit()
						.getEncounterStartDate());
			}
			Date startDate = patientPrescription.getStartDate();
			String start = startDate != null ? DateUtils.getFormatDate(patientPrescription.getStartDate(),
					"MMMM d, yyyy") : null;

			nsPatientPrescription.setStartDate(start);
			Date endDate = patientPrescription.getEndDate();

			String end = endDate != null ? DateUtils.getFormatDate(patientPrescription.getEndDate(), "MMMM d, yyyy")
					: null;

			nsPatientPrescription.setEndDate(end);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("Failed to set start/end date for prescription");
		}
		nsPatientPrescription.setMedicationBrandName(patientPrescription.getMedicationBrandName());
		nsPatientPrescription.setMedicationGenericName(patientPrescription.getMedicationGenericName());
		nsPatientPrescription.setDoseQuantity(patientPrescription.getDoseQuantity());
		try {
			String dosage = patientPrescription.getDoseStrength() != null ? patientPrescription.getDoseStrength()
					.toString() : null;
			nsPatientPrescription.setDoseStrength(dosage);
		} catch (Exception e) {
			LOGGER.debug("Unable to get dosage");
		}
		nsPatientPrescription.setDoseUnit(patientPrescription.getUnit());
		nsPatientPrescription.setDirection(patientPrescription.getDirection());
		nsPatientPrescription.setSourceName(patientPrescription.getSourceName());
		nsPatientPrescription.setPrescriptionAction(patientPrescription.getPrescriptionActionId()
				.getPrescriptionAction());
		nsPatientPrescription.setRouteOfAdministration(patientPrescription.getRouteOfAdministration());
		nsPatientPrescription.setFrequency(patientPrescription.getFrequency());
		return nsPatientPrescription;
	}

	/**
	 * @param patientVisit
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public static NsPatientVisit getNsPatientVisit(PatientVisit patientVisit, String patientVerificationMedicalRecordNumber)
			throws BusinessException, SystemException {

		NsPatientVisit nsPatientVisit = new NsPatientVisit();
		try {

			nsPatientVisit.setVisitDate(DateUtils.getFormatDate(patientVisit.getVisitDate(), "MM/dd/yyyy"));
		} catch (Exception e) {
			LOGGER.debug("Failed to setVisitDate...");
		}
		nsPatientVisit.setVisitTypeId(patientVisit.getVisitTypeId());
		if (patientVisit.getVisitTypeId() == 1) {
			nsPatientVisit.setVisitType("inpatient");
		} else {
			nsPatientVisit.setVisitType("ambulatory");
		}
		nsPatientVisit.setPatientVisitId(patientVisit.getPatientVisitPK().getPatientVisitId());

		nsPatientVisit.setAccountId(patientVisit .getPatientVisitPK().getAccountId());
		nsPatientVisit.setSourceName(patientVisit.getSourceName());
		nsPatientVisit.setUnitNumber(patientVerificationMedicalRecordNumber);
		nsPatientVisit.setVisitTypeId(patientVisit.getVisitTypeId());
		nsPatientVisit.setAttendingPhysicianName(patientVisit.getProviderName());
		return nsPatientVisit;
	}
}
