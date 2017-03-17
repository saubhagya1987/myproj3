package com.versawork.http.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.versawork.http.dataobject.AllergiesInfo;
import com.versawork.http.dataobject.CarePlanInfo;
import com.versawork.http.dataobject.CateTeamInfo;
import com.versawork.http.dataobject.LabResultInfo;
import com.versawork.http.dataobject.MedicationInfo;
import com.versawork.http.dataobject.NsPatientLabResult;
import com.versawork.http.dataobject.NsPatientPrescription;
import com.versawork.http.dataobject.NsPatientVisit;
import com.versawork.http.dataobject.PatientImagingInfo;
import com.versawork.http.dataobject.PatientInpatientDiagnosisInfo;
import com.versawork.http.dataobject.PatientInpatientFunctionalStatusInfo;
import com.versawork.http.dataobject.PatientInpatientImmunalizationInfo;
import com.versawork.http.dataobject.PatientInpatientMetadataInfo;
import com.versawork.http.dataobject.PatientProblemInfo;
import com.versawork.http.dataobject.ProcedureInfo;
import com.versawork.http.dataobject.VitalSigns;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
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

public final class ResponseInfoObject {
	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseInfoObject.class);

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
		if(patientLab.getLabResult()==null){
			nsPatLabRes.setLabResult("N/A");
		}
		else{
		nsPatLabRes.setLabResult(patientLab.getLabResult());
		}
		nsPatLabRes.setLabUnit(patientLab.getLabUnit());
		nsPatLabRes.setOrganizerCode(patientLab.getOrganizerCode());
		nsPatLabRes.setOrganizerName(patientLab.getOrganizerName());
		nsPatLabRes.setTestId(patientLab.getPatientLabPK().getTestId());
		nsPatLabRes.setNormalRangeMax(patientLab.getNormalRangeMax());
		nsPatLabRes.setNormalRangeMin(patientLab.getNormalRangeMin());
		nsPatLabRes.setAbsoluteRangeMax(patientLab.getAbsoluteRangeMax());
		nsPatLabRes.setAbsoluteRangeMin(patientLab.getAbsoluteRangeMin());
		/*
		 * if(patientLab.getNormalRange()!=null){ String[] normalRange =
		 * patientLab.getNormalRange().split("-"); Float normalRangeMin =
		 * Float.parseFloat(normalRange[0]); Float normalRangeMax =
		 * Float.parseFloat(normalRange[1].replaceAll("[^\\d.]", ""));
		 * nsPatLabRes.setNormalRangeMax(normalRangeMax);
		 * nsPatLabRes.setNormalRangeMin(normalRangeMin); }
		 * 
		 * if(patientLab.getAbsoluteRange()!=null){ String[] maxRange =
		 * patientLab.getAbsoluteRange().split("-"); Float maxRangeMin =
		 * Float.parseFloat(maxRange[0]);; Float maxRangeMax =
		 * Float.parseFloat(maxRange[1].replaceAll("[^\\d.]", ""));
		 * nsPatLabRes.setMaxRangeMax(maxRangeMax);
		 * nsPatLabRes.setMaxRangeMin(maxRangeMin); }
		 */

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

	public static CateTeamInfo getCareTeamInfo(PatientCareTeam patientCareTeam, Integer clientDBId) {

		CateTeamInfo careTeamInfo = new CateTeamInfo();
		careTeamInfo.setCareMemberName(patientCareTeam.getCareMemberName());
		careTeamInfo.setAddress(patientCareTeam.getAddress1());
		careTeamInfo.setPhoneNumber(patientCareTeam.getPhoneNumber());
		careTeamInfo.setContactEmail("example@example.com");

		careTeamInfo.setClientDatabaseId(clientDBId);
		careTeamInfo.setSpecialty(patientCareTeam.getCareMemberSpeciality());		
		careTeamInfo.setContactEmail(patientCareTeam.getCareMemberEmail());		
		careTeamInfo.setSourceName(patientCareTeam.getSourceName());
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
		patientInpatientImmunalizationInfo.setRouteCode(patientInpatientImmunization.getRouteCode());
		patientInpatientImmunalizationInfo.setRouteName(patientInpatientImmunization.getRouteName());
		try {
			java.util.Date statusDate = patientInpatientImmunization.getDateAdded();

			String statusString = statusDate != null ? DateUtils.getFormatDate(
					patientInpatientImmunization.getDateAdded(), "MM/dd/yyyy") : "N/A";
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
		if(patientVitalsigns.getBmi()== null)
		{
			vitalSigns.setBMI("N/A");
		}
		else{
		vitalSigns.setBMI(patientVitalsigns.getBmi());
		}
		if (patientVitalsigns.getDiastolicBp() != null && patientVitalsigns.getDiastolicBpUnit() != null
				&& patientVitalsigns.getDiastolicBp() != "" && patientVitalsigns.getDiastolicBpUnit() != "") {
			
			if(Character.isDigit(patientVitalsigns.getDiastolicBp().charAt(0))){
				vitalSigns
				.setDiastolicBp(patientVitalsigns.getDiastolicBp() + " " + patientVitalsigns.getDiastolicBpUnit());
			}else{
				vitalSigns
				.setDiastolicBp(patientVitalsigns.getDiastolicBp());
			}
			
		}
		else{
			vitalSigns.setDiastolicBp("N/A");
		}
		if(patientVitalsigns.getDiastolicBpUnit()==null){
			vitalSigns.setDiastolicBpUnit("N/A");
		}
		else{
		vitalSigns.setDiastolicBpUnit(patientVitalsigns.getDiastolicBpUnit());
		}
		if(patientVitalsigns.getHeightFeet()==null){
			vitalSigns.setHeightFeet("N/A");
		}
		else{
		vitalSigns.setHeightFeet(patientVitalsigns.getHeightFeet());
		}
		if(patientVitalsigns.getHeightInches()==null){
			patientVitalsigns.setHeightInches("N/A");
		}
		else{
		vitalSigns.setHeightInches(patientVitalsigns.getHeightInches());
		}
		if(patientVitalsigns.getWeightLbs()==null){
			vitalSigns.setWeightLbs("N/A");
		}
		else{
		vitalSigns.setWeightLbs(patientVitalsigns.getWeightLbs());
		}

		if (patientVitalsigns.getSystolicBp() != null && patientVitalsigns.getSystolicBp() != ""
				&& patientVitalsigns.getSystolicBpUnit() != null && patientVitalsigns.getSystolicBpUnit() != "") {
			if(Character.isDigit(patientVitalsigns.getSystolicBp().charAt(0)))
			{
				vitalSigns.setSystolicBp(patientVitalsigns.getSystolicBp() + " " + patientVitalsigns.getSystolicBpUnit());
			}
			else{
				vitalSigns.setSystolicBp(patientVitalsigns.getSystolicBp());
			}
		}
		else
		{
			vitalSigns.setSystolicBp("N/A");
		}
		if(patientVitalsigns.getSystolicBpUnit()==null){
			vitalSigns.setSystolicBpUnit("N/A");
		}
		else{	
		vitalSigns.setSystolicBpUnit(patientVitalsigns.getSystolicBpUnit());
		}
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
		if(patientAllergies.getReaction()==null){
			allergiesInfo.setReaction("N/A");
		}
		else{
		String reaction = patientAllergies.getReaction() != null ? patientAllergies.getReaction() : "N/A";
		allergiesInfo.setReaction(reaction);
		}
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

	public static PatientInpatientMetadataInfo getPatientInpatientMetadataInfo(PatientVisit patientMuData,
			PatientVisitInpatient patientInpatientMetadata) {

		PatientInpatientMetadataInfo patientInpatientMetadataInfo = new PatientInpatientMetadataInfo();
		// patientInpatientMetadataInfo.setPatientInpatientId(patientInpatientMetadata.getPatientVisitInpatientPK());
		try {

			patientInpatientMetadataInfo.setAdmissionDate(DateUtils.getFormatDate(
					patientMuData.getEncounterStartDate(), "MM/dd/yyyy hh:mm a"));
		} catch (Exception e) {
			LOGGER.debug("Could not set admission date for inpatient metadata list for visit : "
					+ patientMuData.getPatientVisitPK().getPatientVisitId());
		}
		patientInpatientMetadataInfo.setAdmissionLocation(patientInpatientMetadata.getAdmitLocation());
		try {
			patientInpatientMetadataInfo.setDischargeDate(DateUtils.getFormatDate(patientMuData.getEncounterEndDate(),
					"MM/dd/yyyy hh:mm a"));
		} catch (Exception e) {
			LOGGER.debug("Could not set dischage date for inpatient metadata list for visit : "
					+ patientMuData.getPatientVisitPK().getPatientVisitId());
		}
		patientInpatientMetadataInfo.setDischargeLocation(patientInpatientMetadata.getDischargeLocation());
		patientInpatientMetadataInfo.setHospitalizationReason(patientInpatientMetadata.getReasonForHospitalization());
		patientInpatientMetadataInfo.setDischargeInstruction(patientInpatientMetadata.getDischargeInstruction());
		return patientInpatientMetadataInfo;
	}

	public static PatientInpatientDiagnosisInfo getPatientInpatientDiagnosisInfo(PatientVisit patientMuData,
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
			LOGGER.debug("Could not set date added for inpatient diagnosis for visit : "
					+ patientMuData.getPatientVisitPK().getPatientVisitId());
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
		if(patientImagings.getExamName()==null)
		{
			e.setExamName("N/A");
		}
		else{
		e.setExamName(patientImagings.getExamName());
		}
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
	public static NsPatientVisit getNsPatientVisit(PatientVisit patientVisit, PatientVerification patientVerification)
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

		nsPatientVisit.setAccountId(patientVerification.getAccountId());
		nsPatientVisit.setSourceName(patientVisit.getSourceName());
		nsPatientVisit.setUnitNumber(patientVerification.getPatientVerificationPK().getMedicalRecordNumber());
		nsPatientVisit.setVisitTypeId(patientVisit.getVisitTypeId());
		nsPatientVisit.setAttendingPhysicianName(patientVisit.getProviderName());
		return nsPatientVisit;
	}
	public static PatientInpatientMetadataInfo getPatientInpatientMetadata(PatientVisit patientMuData) {

		PatientInpatientMetadataInfo patientInpatientMetadataInfo = new PatientInpatientMetadataInfo();
		// patientInpatientMetadataInfo.setPatientInpatientId(patientInpatientMetadata.getPatientVisitInpatientPK());
		patientInpatientMetadataInfo.setName(patientMuData.getFirstName()+" "+patientMuData.getLastName());
		if(patientMuData.getSex()==null)
		{
			patientInpatientMetadataInfo.setSex("N/A");
			
		}
		else
		{
		patientInpatientMetadataInfo.setSex(patientMuData.getSex());
		}
		try {
			if(patientMuData.getBirthDate()==null){
				patientInpatientMetadataInfo.setBirthdate("N/A");
			}				
			else{
			patientInpatientMetadataInfo.setBirthdate(DateUtils.getFormatDate(patientMuData.getBirthDate(),"MM/dd/yyyy"));
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(patientMuData.getRace()==null)
		{
			patientInpatientMetadataInfo.setRace("N/A");
		}
		else{
		patientInpatientMetadataInfo.setRace(patientMuData.getRace());
		}
		if(patientMuData.getEthnicity()==null){
			patientInpatientMetadataInfo.setEthinicity("N/A");
		}
		else{
		patientInpatientMetadataInfo.setEthinicity(patientMuData.getEthnicity());
		}
		if(patientMuData.getPreferredLanguage()==null){
			patientInpatientMetadataInfo.setPreferredLanguage("N/A");
		}
		else{
		patientInpatientMetadataInfo.setPreferredLanguage(patientMuData.getPreferredLanguage());
		}
		if(patientMuData.getSmokingStatus()==null){
			patientInpatientMetadataInfo.setSmokingStatus("N/A");
		}else
		{
		patientInpatientMetadataInfo.setSmokingStatus(patientMuData.getSmokingStatus());
		}
		return patientInpatientMetadataInfo;
	}

	

	
}
