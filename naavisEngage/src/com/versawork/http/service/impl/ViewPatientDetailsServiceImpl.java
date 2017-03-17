package com.versawork.http.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.versawork.http.cacheClientsInfo.ClientCacheSettingsCheck;
import com.versawork.http.cacheDataObject.Cache2Object;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.constant.CacheConstants;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.AccountServiceDAO;
import com.versawork.http.dao.ViewPatientDetailsServiceDAO;
import com.versawork.http.dataobject.ActivityLogInfo;
import com.versawork.http.dataobject.AllergiesInfo;
import com.versawork.http.dataobject.Attachments;
import com.versawork.http.dataobject.CarePlanInfo;
import com.versawork.http.dataobject.CateTeamInfo;
import com.versawork.http.dataobject.DischargeInstruction;
import com.versawork.http.dataobject.FeedListInfo;
import com.versawork.http.dataobject.LabGroups;
import com.versawork.http.dataobject.LabResultInfo;
import com.versawork.http.dataobject.LabResults;
import com.versawork.http.dataobject.MedicationInfo;
import com.versawork.http.dataobject.MedicationSourceDates;
import com.versawork.http.dataobject.NsDoctorsList;
import com.versawork.http.dataobject.NsPatientLabResult;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.PatientImagingInfo;
import com.versawork.http.dataobject.PatientInformation;
import com.versawork.http.dataobject.PatientInformationAdmission;
import com.versawork.http.dataobject.PatientInformationDischarge;
import com.versawork.http.dataobject.PatientInformationHospitalizationReason;
import com.versawork.http.dataobject.PatientInpatientDiagnosisInfo;
import com.versawork.http.dataobject.PatientInpatientFunctionalStatusInfo;
import com.versawork.http.dataobject.PatientInpatientImmunalizationInfo;
import com.versawork.http.dataobject.PatientInpatientMetadataInfo;
import com.versawork.http.dataobject.PatientProblemInfo;
import com.versawork.http.dataobject.PatientTestInfo;
import com.versawork.http.dataobject.ProcedureInfo;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.dataobject.VDTDataObject;
import com.versawork.http.dataobject.ViewPatientInfo;
import com.versawork.http.dataobject.VisitDetails;
import com.versawork.http.dataobject.VitalSigns;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.ActivityLog;
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
import com.versawork.http.model.PatientVisit;
import com.versawork.http.model.PatientVisitInpatient;
import com.versawork.http.model.PatientVitalSign;
import com.versawork.http.model.VdtCoreMeasure;
import com.versawork.http.model.VdtCoreMeasureScorecard;
import com.versawork.http.service.ViewPatientDetailsService;
import com.versawork.http.utils.Activity;
import com.versawork.http.utils.CacheKeys;
import com.versawork.http.utils.DateUtils;
import com.versawork.http.utils.GenerateCCDA;
import com.versawork.http.utils.GeneratePDF;
import com.versawork.http.utils.LabGroupListComparator;
import com.versawork.http.utils.LabTestListComparator;
import com.versawork.http.utils.NsResponseUtils;
import com.versawork.http.utils.ResponseInfoObject;
import com.versawork.http.utils.SecureMailSender;

/**
 * @author Sohaib
 * 
 */

@Service
public class ViewPatientDetailsServiceImpl implements ViewPatientDetailsService {

	final static Logger LOGGER = LoggerFactory
			.getLogger(ViewPatientDetailsServiceImpl.class);

	@Autowired
	private ViewPatientDetailsServiceDAO viewPatientDetailsServiceDAO;

	@Autowired
	private AccountServiceDAO accountDAO;

	/*
	 * @Autowired private GetDoctorsListService getDoctorsList;
	 */

	@Autowired
	private SecureMailSender secureMailSender;

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ClientCacheSettingsCheck cCS;

	@Autowired
	private CacheAccessbyJson cache;

	private static final String SUMMARY = "summary";
	private static final String TRANSITION_OF_CARE = "toc";

	private static final String FILE_TYPE_PDF = "pdf";
	private static final String FILE_TYPE_XML = "xml";

	private static final String NEW_PRESC = "new";
	private static final String CONT_PRESC = "continue";
	private static final String DISCONT_PRESC = "discontinue";
	private static final String CHANGE_PRESC = "changed but continue";

	private static final Character PROVIDER_TYPE = 'C';

	public NsResponse getMuData(ViewPatientInfo viewPatientInfoObj)
			throws BusinessException, SystemException {
		NsResponse muData = new NsResponse();

		PatientVisit patientMuData = getPatientMuData(viewPatientInfoObj);
		muData.setVisitType((int) patientMuData.getVisitTypeId());
		Integer visitId = patientMuData.getPatientVisitPK().getPatientVisitId();
		ViewPatientInfo viewPatientInfo = new ViewPatientInfo();
		Account account = accountDAO.getAccountById(patientMuData
				.getPatientVisitPK().getAccountId());
		viewPatientInfo.setUnitNumber(account.getMedicalRecordNumber());
		viewPatientInfo.setMuDataId(visitId);
		viewPatientInfo.setPatientId(viewPatientInfoObj.getAccountId());
		viewPatientInfo.setAccountId(viewPatientInfoObj.getAccountId());
		viewPatientInfo.setFirstName(patientMuData.getFirstName());
		viewPatientInfo.setLastName(patientMuData.getLastName());
		viewPatientInfo.setSex(patientMuData.getSex());
		try {

			viewPatientInfo.setBirthDate(DateUtils.getFormatDate(
					patientMuData.getBirthDate(), "MM/dd/yyyy"));
		} catch (Exception e) {
			LOGGER.debug("Could not set birth date.");
		}
		viewPatientInfo.setRace(patientMuData.getRace());
		viewPatientInfo.setRaceCode(patientMuData.getRaceCode());
		viewPatientInfo.setEthnicity(patientMuData.getEthnicity());
		viewPatientInfo.setEthnicityCode(patientMuData.getEthnicityCode());
		viewPatientInfo.setPreferredLang(patientMuData.getPreferredLanguage());
		viewPatientInfo.setSmokingStatus(patientMuData.getSmokingStatus());
		viewPatientInfo.setSmokingStatusCode(patientMuData
				.getSmokingStatusCode());
		try {

			viewPatientInfo.setDateAdded(DateUtils.getFormatDate(
					patientMuData.getDateAdded(), "MM/dd/yyyy HH:mm:ss"));
		} catch (Exception e) {
			LOGGER.debug("Could not set date added for visit");
		}
		try {
			viewPatientInfo
					.setAccessDate(DateUtils.getFormatDate(
							patientMuData.getEncounterEndDate(),
							"MM/dd/yyyy HH:mm:ss"));
		} catch (Exception e) {
			LOGGER.debug("Could not set access date for visit");
		}
		viewPatientInfo.setVisitTypeId(patientMuData.getVisitTypeId());

		muData.setViewPatientInfo(viewPatientInfo);
		NsRequest nsRequest = new NsRequest();
		nsRequest.setProviderPurpose(PROVIDER_TYPE);
		// List<FacilityProvider> patientMuHospitalProviderRel =
		// getDoctorsList.getDoctorsList(nsRequest).getNsDoctorsList();//viewPatientDetailsServiceDAO.getFacilityProviderListByClientDbIdandId(patientMuData.getAccountId().getClientNaavisDatabases().getClientNaavisDatabasesPK().getClientDatabaseId(),patientMuData.getAccountId().getClientNaavisDatabases().getClientNaavisDatabasesPK().getClientId());
		// List<NsDoctorsList> providerInfoList =
		// getDoctorsList.getDoctorsList(nsRequest).getNsDoctorsList();
		List<NsDoctorsList> providerListActual = new ArrayList<NsDoctorsList>();
		List<PatientCareTeam> pCPList = patientMuData.getPatientCareTeamList();
		Iterator<PatientCareTeam> pCTIterator = pCPList.iterator();
		while (pCTIterator.hasNext()) {
			PatientCareTeam pCT = pCTIterator.next();
			NsDoctorsList nDL = new NsDoctorsList();
			nDL.setAddress1(pCT.getAddress1());
			nDL.setAddress2(pCT.getAddress2());
			nDL.setCity(pCT.getCity());
			nDL.setContactEmail(pCT.getCareMemberEmail());
			nDL.setContactPhoneNumber(pCT.getPhoneNumber());
			nDL.setFirstName(pCT.getCareMemberName());
			nDL.setLastName("");
			nDL.setPostalCode(pCT.getPostalCode());
			nDL.setProviderId(pCT.getPatientCareTeamPK().getCareMemberId());
			nDL.setSpecialty(pCT.getCareMemberSpeciality());
			nDL.setState(pCT.getState());
			nDL.setSuffix("");
			providerListActual.add(nDL);
		}
		/*
		 * try{ Iterator<NsDoctorsList> iter = providerInfoList.iterator();
		 * while(iter.hasNext()) { NsDoctorsList nsDoctor = iter.next();
		 * if(nsDoctor.getProviderId().equalsIgnoreCase(patientMuData.
		 * getPatientCareTeamList
		 * ().get(0).getPatientCareTeamPK().getCareMemberId())) {
		 * providerListActual.add(nsDoctor); } }}catch(Exception e) {
		 * LOGGER.debug("Could not set doctors list for visit : "+visitId); }
		 */
		/*
		 * for (FacilityProvider providerInfo : patientMuHospitalProviderRel) {
		 * NsDoctorsList nsDoctors = new NsDoctorsList();
		 * nsDoctors.setFirstName(providerInfo.getFirstName(
		 * )+" "+providerInfo.getLastName()); nsDoctors.setContactPhoneNumber
		 * (providerInfo.getContactPhoneNumber());
		 * nsDoctors.setSpecialty(providerInfo.getSpecialty());
		 * nsDoctors.setContactEmail(providerInfo.getContactEmail());
		 * if(!providerInfo.getContactPhoneNumber().isEmpty()) {
		 * nsDoctors.setAddress1 ("Tel: "+providerInfo.getContactPhoneNumber()
		 * +", "+providerInfo.getAddress1
		 * ()+" "+providerInfo.getCity()+" "+providerInfo
		 * .getState()+" "+providerInfo.getPostalCode()); } else { nsDoctors.
		 * setAddress1(providerInfo.getAddress1()+" "+providerInfo. getCity()+
		 * " "+providerInfo.getState()+" "+providerInfo.getPostalCode()); }
		 * providerInfoList.add(nsDoctors); }
		 */
		muData.setNsDoctorsList(providerListActual);

		List<PatientVitalSign> patientVitalSignsList = patientMuData
				.getPatientVitalSignList();
		List<VitalSigns> vitalSignsList = new ArrayList<VitalSigns>();
		try {
			for (PatientVitalSign patientVitalsigns : patientVitalSignsList) {
				VitalSigns vitalSigns = new VitalSigns();
				vitalSigns.setBMI(patientVitalsigns.getBmi());
				vitalSigns.setDiastolicBp(patientVitalsigns.getDiastolicBp());
				vitalSigns.setDiastolicBpUnit(patientVitalsigns
						.getDiastolicBpUnit());
				vitalSigns.setHeightFeet(patientVitalsigns.getHeightFeet());
				vitalSigns.setHeightInches(patientVitalsigns.getHeightInches());
				vitalSigns.setWeightLbs(patientVitalsigns.getWeightLbs());
				// vitalSigns.setWeightUnit(patientVitalsigns.getWeightUnit());
				vitalSigns.setSystolicBp(patientVitalsigns.getSystolicBp());
				vitalSigns.setSystolicBpUnit(patientVitalsigns
						.getSystolicBpUnit());
				try {

					vitalSigns.setDateAdded(DateUtils.getFormatDate(
							patientVitalsigns.getDateAdded(), "MM/dd/yyyy"));
				} catch (Exception e) {
					LOGGER.debug("could not set date added for vital sign");
				}
				// vitalSigns.setOthers(patientVitalsigns.getOthers());
				vitalSignsList.add(vitalSigns);
			}
		} catch (Exception e) {
			LOGGER.info("Error while patient Vital Sign "
					+ ExceptionUtils.getFullStackTrace(e));
		}

		muData.setVitalSignsList(vitalSignsList);

		List<PatientAllergy> patientAllergiesList = accountDAO
				.getPatientAllergiesList(patientMuData.getPatientVisitPK()
						.getAccountId());// patientMuData
		// .getAccountId().getPatientAllergyList();
		List<AllergiesInfo> allergyList = new ArrayList<AllergiesInfo>();
		try {
			for (PatientAllergy patientAllergies : patientAllergiesList) {
				AllergiesInfo allergiesInfo = new AllergiesInfo();
				allergiesInfo.setAllergiesId(patientAllergies
						.getPatientAllergyPK().getAllergyId());
				allergiesInfo.setAllergen(patientAllergies.getAllergenName());
				allergiesInfo.setAllergenCode(patientAllergies
						.getAllergenCode());
				allergiesInfo.setReaction(patientAllergies.getReaction());
				allergiesInfo.setReactionCode(patientAllergies
						.getReactionCode());
				allergiesInfo.setStatus(patientAllergies.getStatus());
				allergiesInfo.setStatusCode(patientAllergies.getStatusCode());
				try {

					allergiesInfo.setDateAdded(DateUtils.getFormatDate(
							patientAllergies.getDateAdded(), "dd/MM/yyyy"));
				} catch (Exception e) {
					LOGGER.debug("Could not set date added for allergy list");
				}
				allergyList.add(allergiesInfo);
			}
		} catch (Exception e) {
			LOGGER.debug("Could not set allergy list for visit : " + visitId);
		}

		muData.setAllergiesInfoList(allergyList);

		List<PatientPrescription> patientMedicationList = patientMuData
				.getPatientMedicationList();
		List<MedicationInfo> medicationList = new ArrayList<MedicationInfo>();
		try {
			for (PatientPrescription patientmedication : patientMedicationList) {
				MedicationInfo medicationInfo = new MedicationInfo();
				medicationInfo.setMedicationId(patientmedication
						.getPatientPrescriptionPK().getMedicationId());
				medicationInfo.setMedicationName(patientmedication
						.getMedicationName());
				medicationInfo.setRxNumber(patientmedication.getRxNumber());
				medicationInfo.setDosageDescription(patientmedication
						.getDosageDescription());
				try {

					medicationInfo.setStartDate(DateUtils.getFormatDate(
							patientmedication.getStartDate(), "MM/dd/yyyy"));
				} catch (Exception e) {
					LOGGER.debug("Failed to set medication start date");
				}
				try {

					medicationInfo.setEndDate(DateUtils.getFormatDate(
							patientmedication.getEndDate(), "MM/dd/yyyy"));
				} catch (Exception e) {
					LOGGER.debug("Failed to set medication end date");
				}
				medicationInfo.setDoseQuantity(patientmedication
						.getMedicationStrength());
				medicationInfo.setRouteCode(patientmedication
						.getRouteOfAdministrationCode());
				medicationInfo.setRouteName(patientmedication
						.getRouteOfAdministration());
				if (patientmedication.getDateAdded() != null) {

					medicationInfo.setDateAdded(DateUtils.getFormatDate(
							patientmedication.getDateAdded(), "MM/dd/yyyy"));
				} else {
					medicationInfo.setDateAdded(null);
				}
				medicationInfo.setStatus(patientmedication.getStatus());
				medicationInfo.setDoseStrength(patientmedication
						.getDoseStrength());
				medicationList.add(medicationInfo);
			}
		} catch (Exception e) {
			LOGGER.debug("Could not set medication list for visit : " + visitId);
		}
		muData.setMedicationInfoList(medicationList);
		List<PatientProblem> patientProblemListfromDB = accountDAO
				.getPatientProblemList(patientMuData.getPatientVisitPK()
						.getAccountId());
		/*
		 * List<PatientProblem> patientProblemListfromDB = patientMuData
		 * .getAccountId().getPatientProblemList();
		 */
		List<PatientProblemInfo> patientProblemList = new ArrayList<PatientProblemInfo>();
		try {
			for (PatientProblem patientProblem : patientProblemListfromDB) {
				PatientProblemInfo patientProblemInfo = new PatientProblemInfo();

				patientProblemInfo.setProblemId(patientProblem
						.getPatientProblemPK().getProblemId());
				patientProblemInfo.setProblemName(patientProblem
						.getProblemName());
				patientProblemInfo.setProblemCode(patientProblem
						.getProblemCode());
				try {

					patientProblemInfo.setDateAdded(DateUtils.getFormatDate(
							patientProblem.getDateAdded(), "MM/dd/yyyy"));
				} catch (Exception e) {
					LOGGER.debug("Could not set date added for problem info");
				}
				patientProblemInfo.setStatus(patientProblem.getStatus());
				patientProblemInfo
						.setStatusCode(patientProblem.getStatusCode());
				patientProblemList.add(patientProblemInfo);
			}
		} catch (Exception e) {
		}
		muData.setPatientProblemInfoList(patientProblemList);

		List<PatientProcedure> patientProcedurefromDB = patientMuData
				.getPatientProcedureList();
		List<ProcedureInfo> patientProcedureList = new ArrayList<ProcedureInfo>();
		try {
			for (PatientProcedure patientProcedure : patientProcedurefromDB) {
				ProcedureInfo procedureInfo = new ProcedureInfo();
				procedureInfo.setProcedureName(patientProcedure
						.getProcedureName());
				procedureInfo.setProcedureCode(patientProcedure
						.getProcedureCode());
				try {

					procedureInfo.setDateAdded(DateUtils.getFormatDate(
							patientProcedure.getDateAdded(), "MM/dd/yyyy"));
				} catch (Exception e) {
					LOGGER.debug("Could not set date added for procedure.");
				}
				patientProcedureList.add(procedureInfo);
			}
		} catch (Exception e) {
		}

		muData.setProcedureInfoList(patientProcedureList);

		List<PatientLab> patientLabResultsfromDB = patientMuData
				.getPatientLabList();
		List<LabResultInfo> labResultInfoList = new ArrayList<LabResultInfo>();
		try {
			for (PatientLab patientlabResult : patientLabResultsfromDB) {
				LabResultInfo labResultInfo = new LabResultInfo();
				labResultInfo.setLabResultId(patientlabResult.getPatientLabPK()
						.getLabId());
				labResultInfo.setLabTest(patientlabResult.getTestName());
				labResultInfo.setLabTestCode(patientlabResult.getPatientLabPK()
						.getTestId());
				labResultInfo.setLabResult(patientlabResult.getLabResult());
				labResultInfo.setLabResultUnit(patientlabResult.getLabUnit());
				labResultInfo.setOrgName(patientlabResult.getOrganizerName());
				labResultInfo.setOrgCode(patientlabResult.getOrganizerCode());
				labResultInfo.setInterpretationCode(patientlabResult
						.getInterpretationCode());
				try {

					labResultInfo.setDateAdded(DateUtils.getFormatDate(
							patientlabResult.getDateAdded(), "MM/dd/yyyy"));
				} catch (Exception e) {
					LOGGER.debug("Could not set date added for lab result");
				}
				labResultInfoList.add(labResultInfo);
			}
		} catch (Exception e) {
			LOGGER.debug("Could not set lab result info list for visit : ");
		}

		muData.setLabResultInfoList(labResultInfoList);

		List<PatientCarePlan> patientCarPlanListfromDB = patientMuData
				.getPatientCarePlanList();

		List<CarePlanInfo> carePlanInfoList = new ArrayList<CarePlanInfo>();
		try {
			for (PatientCarePlan patientCarePlan : patientCarPlanListfromDB) {
				CarePlanInfo carePlanInfo = new CarePlanInfo();
				carePlanInfo.setCarePlanId(patientCarePlan
						.getPatientCarePlanPK().getCarePlanId());
				carePlanInfo.setGoal(patientCarePlan.getGoal());
				carePlanInfo.setInstructions(patientCarePlan.getInstructions());
				try {

					carePlanInfo.setDateAdded(DateUtils.getFormatDate(
							patientCarePlan.getDateAdded(), "MM/dd/yyyy"));
				} catch (Exception e) {
					LOGGER.debug("Could not set date added for care plan");
				}
				carePlanInfoList.add(carePlanInfo);
			}
		} catch (Exception e) {
			LOGGER.debug("Could not set care plan list for visit : ");
		}

		muData.setCarePlanInfoList(carePlanInfoList);

		if (patientMuData.getVisitTypeId() == 1)// 1 for inpatient
		{
			try {
				PatientVisitInpatient patientImpatientListfromDB = patientMuData
						.getPatientVisitInpatientList().get(0);

				PatientInpatientMetadataInfo patientInpatientMetadataInfo = new PatientInpatientMetadataInfo();
				try {

					patientInpatientMetadataInfo.setAdmissionDate(DateUtils
							.getFormatDate(
									patientImpatientListfromDB.getDateAdded(),
									"MM/dd/yyyy HH:mm"));
				} catch (Exception e) {
					LOGGER.debug("Could not set admission date for visit : ");
				}
				patientInpatientMetadataInfo
						.setAdmissionLocation(patientImpatientListfromDB
								.getAdmitLocation());
				try {

					patientInpatientMetadataInfo.setDischargeDate(DateUtils
							.getFormatDate(
									patientImpatientListfromDB.getDateAdded(),
									"MM/dd/yyyy HH:mm"));
				} catch (Exception e) {
					LOGGER.debug("Could not set discharge for visit : ");
				}
				patientInpatientMetadataInfo
						.setDischargeLocation(patientImpatientListfromDB
								.getDischargeLocation());
				patientInpatientMetadataInfo
						.setHospitalizationReason(patientImpatientListfromDB
								.getReasonForHospitalization());
				patientInpatientMetadataInfo
						.setDischargeInstruction(patientImpatientListfromDB
								.getDischargeInstruction());
				muData.setPatientInpatientMetadataInfo(patientInpatientMetadataInfo);
			} catch (Exception e) {
				LOGGER.debug("Could not set patientInpatientMetadataInfo list for visit : ");
			}
			List<PatientDiagnosis> patientInpatientDiagnosisListfromDB = patientMuData
					.getPatientDiagnosisList();

			List<PatientInpatientDiagnosisInfo> patientInpatientDiagnosisInfoList = new ArrayList<PatientInpatientDiagnosisInfo>();
			try {
				for (PatientDiagnosis patientInpatientDiagnosis : patientInpatientDiagnosisListfromDB) {
					PatientInpatientDiagnosisInfo patientInpatientDiagnosisInfo = new PatientInpatientDiagnosisInfo();
					patientInpatientDiagnosisInfo
							.setDiagnosisDesc(patientInpatientDiagnosis
									.getDiagnosisName());
					patientInpatientDiagnosisInfo
							.setDiagnosisCode(patientInpatientDiagnosis
									.getPatientDiagnosisPK().getDiagnosisCode());
					patientInpatientDiagnosisInfo
							.setStatus(patientInpatientDiagnosis.getStatus());
					try {

						patientInpatientDiagnosisInfo.setDateAdded(DateUtils
								.getFormatDate(patientInpatientDiagnosis
										.getDateAdded(), "MM/dd/yyyy"));
					} catch (Exception e) {
						LOGGER.info("Could not set date added for diagnosis.");
					}
					patientInpatientDiagnosisInfoList
							.add(patientInpatientDiagnosisInfo);
				}
			} catch (Exception e) {
				LOGGER.debug("Could not set patientInfoDiagnostic list for visit : ");
			}

			muData.setPatientInpatientDiagnosisInfoList(patientInpatientDiagnosisInfoList);

			List<PatientFunctionalStatus> patientInpatientFunctionalStatusListfromDB = patientMuData
					.getPatientFunctionalStatusList();
			List<PatientInpatientFunctionalStatusInfo> patientInpatientFunctionalStatusInfoList = new ArrayList<PatientInpatientFunctionalStatusInfo>();
			try {
				for (PatientFunctionalStatus patientInpatientFunctionalStatus : patientInpatientFunctionalStatusListfromDB) {
					PatientInpatientFunctionalStatusInfo patientInpatientFunctionalStatusInfo = new PatientInpatientFunctionalStatusInfo();
					patientInpatientFunctionalStatusInfo
							.setFunctionalStatusDesc(patientInpatientFunctionalStatus
									.getFunctionDescription());
					patientInpatientFunctionalStatusInfo
							.setFunctionalStatusCode(patientInpatientFunctionalStatus
									.getFunctionCode());
					patientInpatientFunctionalStatusInfo
							.setStatus(patientInpatientFunctionalStatus
									.getStatus());
					patientInpatientFunctionalStatusInfo
							.setStatusCode(patientInpatientFunctionalStatus
									.getStatusCode());
					try {
						patientInpatientFunctionalStatusInfo
								.setDateAdded(DateUtils.getFormatDate(
										patientInpatientFunctionalStatus
												.getStatusDate(), "MM/dd/yyyy"));
					} catch (Exception e) {
						LOGGER.debug("Could not set date added for patient Inpatient functional status.");
					}
					patientInpatientFunctionalStatusInfoList
							.add(patientInpatientFunctionalStatusInfo);
				}
			} catch (Exception e) {
				LOGGER.debug("Could not set patientInpatientFunctionalStatusInfo list for visit : ");
			}

			muData.setPatientInpatientFunctionalStatusInfoList(patientInpatientFunctionalStatusInfoList);

			List<PatientImmunization> patientInpatientImmunizationListfromDB = patientMuData
					.getPatientImmunizationList();

			List<PatientInpatientImmunalizationInfo> patientInpatientImmunalizationInfoList = new ArrayList<PatientInpatientImmunalizationInfo>();
			try {
				for (PatientImmunization patientInpatientImmunization : patientInpatientImmunizationListfromDB) {
					PatientInpatientImmunalizationInfo patientInpatientImmunalizationInfo = new PatientInpatientImmunalizationInfo();
					patientInpatientImmunalizationInfo
							.setImmunalizationName(patientInpatientImmunization
									.getImmunizationName());
					patientInpatientImmunalizationInfo
							.setImmunalizationCode(patientInpatientImmunization
									.getImmunizationCode());
					patientInpatientImmunalizationInfo
							.setStatus(patientInpatientImmunization.getStatus());
					patientInpatientImmunalizationInfo
							.setRouteCode(patientInpatientImmunization
									.getRouteCode());
					patientInpatientImmunalizationInfo
							.setRouteName(patientInpatientImmunization
									.getRouteName());
					try {
						patientInpatientImmunalizationInfo
								.setDateAdded(DateUtils.getFormatDate(
										patientInpatientImmunization
												.getStatusDate(), "MM/dd/yyyy"));
					} catch (Exception e) {
						LOGGER.debug("Could not set date added for immunization.");
					}
					patientInpatientImmunalizationInfoList
							.add(patientInpatientImmunalizationInfo);
				}
			} catch (Exception e) {
				LOGGER.debug("Could not set patientInpatientImmunization list for visit : ");
			}

			muData.setPatientInpatientImmunalizationInfoList(patientInpatientImmunalizationInfoList);
		}
		return muData;
	}

	@Override
	public NsResponse downloadMuData(NsRequest nsRequest)
			throws BusinessException, SystemException {
		LOGGER.debug("In download MuData");
		NsResponse muData = new NsResponse();
		muData = getMuData(nsRequest.getViewPatientInfo());

		ResponseData responseData = new ResponseData();
		NsResponse nsResponse = new NsResponse();

		ViewPatientInfo viewPatientInfo = new ViewPatientInfo();
		viewPatientInfo
				.setPatientId(muData.getViewPatientInfo().getPatientId());
		nsResponse.setViewPatientInfo(viewPatientInfo);

		if (nsRequest.getViewPatientInfo().getDocType().equalsIgnoreCase("pdf")) {
			byte[] pdfBytes = new GeneratePDF().generatePDF(muData, nsRequest
					.getViewPatientInfo().getVersion());
			nsResponse.setMuData(pdfBytes);

		} else if (nsRequest.getViewPatientInfo().getDocType()
				.equalsIgnoreCase("xml")) {

			byte[] xmlBytes;
			try {
				xmlBytes = GenerateCCDA.generateCCD(muData, nsRequest
						.getViewPatientInfo().getVersion());
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(e);
			}
			nsResponse.setMuData(xmlBytes);
		}

		responseData.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responseData.setDescription("Success");
		nsResponse.setResponseData(responseData);

		return nsResponse;

	}

	@Override
	public NsResponse viewPatientDetails(NsRequest nsRequest)
			throws BusinessException, SystemException {
		LOGGER.debug("In View Patient Details");
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		NsResponse muData = new NsResponse();

		/*
		 * TestTable1 testData = viewPatientDetailsServiceDAO.getTestData();
		 * List<TestTable3> testData3 = testData.getTestTable3List();
		 */
		muData = getMuData(nsRequest.getViewPatientInfo());
		nsResponse.setViewPatientInfo(muData.getViewPatientInfo());
		nsResponse.setViewMuData(muData);
		// TODO
		// updateVdtHits(lmuData);

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);

		return nsResponse;

	}

	public PatientVisit getPatientMuData(ViewPatientInfo viewPatientInfo)
			throws BusinessException, SystemException {

		return viewPatientDetailsServiceDAO.getInfoMuIdUnitNumber(
				viewPatientInfo.getAccountId(), viewPatientInfo.getVisitId());
	}

	@Override
	public List<VitalSigns> getVitalSigns(NsRequest nsRequest)
			throws BusinessException, SystemException {

		String clientDBId = messageSource.getMessage("client.database.id",
				null, nsRequest.getLocale());

		String key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode())
				.append("_")
				.append(CacheConstants.PATIENT_VITAL_SIGN.getCode()))
				.toString();

		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = new StringBuilder(key).append("_").append(clientDBId)
				.append("_")
				.append(nsRequest.getViewPatientInfo().getAccountId())
				.append("_")
				.append(nsRequest.getViewPatientInfo().getVisitId()).toString();

		Cache2Object<VitalSigns> obj = new Cache2Object<VitalSigns>(cCS, cache);

		// NsResponse nsResponse = new NsResponse();
		//ResponseData responsedata = new ResponseData();
		Type type = new TypeToken<List<VitalSigns>>() {
		}.getType();
		List<VitalSigns> vitalSignsList = obj.getObject(cacheKey, isCachingOn,
				type);

		if (vitalSignsList == null || vitalSignsList.isEmpty()) {
			PatientVisit patientMuData = getPatientMuData(nsRequest
					.getViewPatientInfo());

			List<PatientVitalSign> patientVitalSignsList = patientMuData
					.getPatientVitalSignList();
			vitalSignsList = new ArrayList<VitalSigns>();
			VitalSigns vitalSigns = null;
			for (PatientVitalSign patientVitalsigns : patientVitalSignsList) {
				vitalSigns = ResponseInfoObject
						.getVitalSigns(patientVitalsigns);
				vitalSignsList.add(vitalSigns);

			}
			obj.setObject(cacheKey, isCachingOn, vitalSignsList, type);

		}
		/*if (vitalSignsList == null || vitalSignsList.isEmpty()) {
			throw new BusinessException("no.vital.found");
		}*/
		// Collections.sort(vitalSignsList, new VitalSignListComparator());
		/*
		 * nsResponse.setVitalSignsList(vitalSignsList);
		 * 
		 * responsedata.setResult(Integer.parseInt(messageSource.getMessage(
		 * VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
		 * nsRequest.getLocale()))); responsedata.setDescription("Success");
		 * nsResponse.setResponseData(responsedata);
		 */
		return vitalSignsList;

	}

	@Override
	public List<CateTeamInfo>  getProviderList(NsRequest nsRequest)
			throws BusinessException, SystemException {
		String clientDBId = messageSource.getMessage("client.database.id",
				null, nsRequest.getLocale());

		String key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode())
				.append("_").append(CacheConstants.PATIENT_CARE_TEAM.getCode()))
				.toString();

		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = new StringBuilder(key).append("_").append(clientDBId)
				.append("_")
				.append(nsRequest.getViewPatientInfo().getAccountId())
				.append("_")
				.append(nsRequest.getViewPatientInfo().getVisitId()).toString();

		Cache2Object<CateTeamInfo> obj = new Cache2Object<CateTeamInfo>(cCS,
				cache);

		//NsResponse nsResponse = new NsResponse();
		//ResponseData responsedata = new ResponseData();
		Type type = new TypeToken<List<CateTeamInfo>>() {
		}.getType();
		List<CateTeamInfo> careTeamInfoList = obj.getObject(cacheKey,
				isCachingOn, type);

		if (careTeamInfoList == null || careTeamInfoList.isEmpty()) {

			PatientVisit patientMuData = getPatientMuData(nsRequest
					.getViewPatientInfo());

			List<PatientCareTeam> patientCareTeamList = patientMuData
					.getPatientCareTeamList();
			careTeamInfoList = new ArrayList<CateTeamInfo>();
			CateTeamInfo careTeamInfo = null;
			for (PatientCareTeam patientCareTeam : patientCareTeamList) {
				careTeamInfo = ResponseInfoObject.getCareTeamInfo(
						patientCareTeam, Integer.parseInt(clientDBId));

				careTeamInfoList.add(careTeamInfo);
			}

			obj.setObject(cacheKey, isCachingOn, careTeamInfoList, type);
		}

		/*if (careTeamInfoList == null || careTeamInfoList.isEmpty()) {
			throw new BusinessException("no.provider.found");
		}*/

		/*responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);
		nsResponse.setCateTeamInfoList(careTeamInfoList);*/
		return careTeamInfoList;

	}

	@Override
	public List<AllergiesInfo> getAllergiesList(NsRequest nsRequest)
			throws BusinessException, SystemException {

		String key = (new StringBuilder(
				CacheConstants.PATIENT_ALLERGY.getCode())).toString();

		Boolean isCachingOn = cCS.isCachingOn(key);
		String clientDBId = messageSource.getMessage("client.database.id",
				null, nsRequest.getLocale());
		String cacheKey = new StringBuilder(key).append("_").append(clientDBId)
				.append("_")
				.append(nsRequest.getViewPatientInfo().getAccountId())
				.toString();
		Cache2Object<AllergiesInfo> obj = new Cache2Object<AllergiesInfo>(cCS,
				cache);

		// NsResponse nsResponse = new NsResponse();
		// ResponseData responsedata = new ResponseData();

		Type type = new TypeToken<List<AllergiesInfo>>() {
		}.getType();
		List<AllergiesInfo> allergyList = obj.getObject(cacheKey, isCachingOn,
				type);

		if (allergyList == null || allergyList.isEmpty()) {
			List<PatientAllergy> patientAllergiesList = accountDAO
					.getPatientAllergiesList(nsRequest.getViewPatientInfo()
							.getAccountId()); // getting account id
												// from view patient
			allergyList = new ArrayList<AllergiesInfo>();
			AllergiesInfo allergiesInfo = null;
			for (PatientAllergy patientAllergies : patientAllergiesList) {
				allergiesInfo = ResponseInfoObject
						.getPatientAllergiesInfo(patientAllergies);
				allergyList.add(allergiesInfo);

			}
			obj.setObject(cacheKey, isCachingOn, allergyList, type);
		}
		/*if (allergyList == null || allergyList.isEmpty()) {
			throw new BusinessException("no.allergies.found");
		}*/
		/*
		 * nsResponse.setAllergiesInfoList(allergyList);
		 * responsedata.setResult(Integer
		 * .parseInt(messageSource.getMessage(VersaWorkConstant
		 * .SUCCESS_RESPONSE_CODE, null, nsRequest.getLocale())));
		 * responsedata.setDescription("Success");
		 * nsResponse.setResponseData(responsedata);
		 */
		return allergyList;
	}

	@Override
	public List<MedicationInfo> getMedicationList(NsRequest nsRequest)
			throws BusinessException, SystemException {
		String clientDBId = messageSource.getMessage("client.database.id",
				null, nsRequest.getLocale());

		String key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode())
				.append("_")
				.append(CacheConstants.PATIENT_MEDICATION.getCode()))
				.toString();

		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = new StringBuilder(key).append("_").append(clientDBId)
				.append("_")
				.append(nsRequest.getViewPatientInfo().getAccountId())
				.append("_")
				.append(nsRequest.getViewPatientInfo().getVisitId()).toString();

		Cache2Object<MedicationInfo> obj = new Cache2Object<MedicationInfo>(
				cCS, cache);

		//NsResponse nsResponse = new NsResponse();
		//ResponseData responsedata = new ResponseData();
		Type type = new TypeToken<List<MedicationInfo>>() {
		}.getType();
		List<MedicationInfo> medicationList = obj.getObject(cacheKey,
				isCachingOn, type);
		HashMap<String, List<MedicationInfo>> prescList = new HashMap<String, List<MedicationInfo>>();
		List<MedicationSourceDates> medicationSourceDatesList = new ArrayList<MedicationSourceDates>();
		if (medicationList == null || medicationList.isEmpty()) {

			PatientVisit patientMuData = getPatientMuData(nsRequest
					.getViewPatientInfo());
			List<PatientPrescription> patientMedicationsList = patientMuData
					.getPatientMedicationList();

			medicationList = new ArrayList<MedicationInfo>();
			MedicationInfo medicationInfo = null;
			Integer count = 0;
			Date asOfDate = null;

			MedicationSourceDates medicationSourceDates = new MedicationSourceDates();
			String sourceName = null;
			for (PatientPrescription patientmedication : patientMedicationsList) {
				medicationInfo = ResponseInfoObject
						.getPatientMedicationInfo(patientmedication);
				medicationList.add(medicationInfo);
				PatientVisit nsP = patientmedication.getPatientVisit();
				if (count == 0) {
					if (nsP.getEncounterEndDate() != null)
						asOfDate = nsP.getEncounterEndDate();
					else
						asOfDate = nsP.getEncounterStartDate();
					count = 1;
				} else {
					if (nsP.getEncounterEndDate() != null
							&& nsP.getEncounterEndDate().after(asOfDate))
						asOfDate = nsP.getEncounterEndDate();
					else if (nsP.getEncounterStartDate() != null
							&& nsP.getEncounterStartDate().after(asOfDate))
						asOfDate = nsP.getEncounterStartDate();
				}
				sourceName = nsP.getSourceName();
			}
			medicationSourceDates.setAsOfDate(DateUtils.getFormatDate(asOfDate,
					"MM/dd/yyyy"));
			medicationSourceDates.setSourceName(sourceName);
			medicationSourceDatesList.add(medicationSourceDates);

			obj.setObject(cacheKey, isCachingOn, medicationList, type);

			List<MedicationInfo> newPrescList = new ArrayList<MedicationInfo>();
			List<MedicationInfo> continuePrescList = new ArrayList<MedicationInfo>();
			List<MedicationInfo> discontinuePrescList = new ArrayList<MedicationInfo>();
			List<MedicationInfo> changedButContinuePrescList = new ArrayList<MedicationInfo>();
			Iterator<MedicationInfo> iter = medicationList.iterator();
			if (iter != null) {
				while (iter.hasNext()) {
					MedicationInfo medInfo = iter.next();
					switch (medInfo.getPrescriptionAction().toLowerCase()) {

					case NEW_PRESC:
						newPrescList.add(medInfo);
						break;
					case CONT_PRESC:
						continuePrescList.add(medInfo);
						break;
					case DISCONT_PRESC:
						discontinuePrescList.add(medInfo);
						break;
					case CHANGE_PRESC:
						changedButContinuePrescList.add(medInfo);
						break;
					}

				}
			}

			if (newPrescList.size() > 0) {
				prescList.put(NEW_PRESC, newPrescList);
			}
			if (continuePrescList.size() > 0) {
				prescList.put(CONT_PRESC, continuePrescList);
			}
			if (discontinuePrescList.size() > 0) {
				prescList.put(DISCONT_PRESC, discontinuePrescList);
			}
			if (changedButContinuePrescList.size() > 0) {
				prescList.put(CHANGE_PRESC, changedButContinuePrescList);
			}

		}

		/*if (medicationList == null || medicationList.isEmpty()) {
			throw new BusinessException("no.medication.found");
		}*/
		// nsResponse.setMedicationInfoList(medicationList);
		/*nsResponse.setMedicationPrescMap(prescList);
		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);
		nsResponse.setMedicationSourceAsOfDate(medicationSourceDatesList);*/

		return medicationList;

	}

	@Override
	public List<PatientProblemInfo> getProblemList(NsRequest nsRequest)
			throws BusinessException, SystemException {

		String clientDBId = messageSource.getMessage("client.database.id",
				null, nsRequest.getLocale());

		String key = CacheConstants.PATIENT_PROBLEM.getCode();

		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = new StringBuilder(key).append("_").append(clientDBId)
				.append("_")
				.append(nsRequest.getViewPatientInfo().getAccountId())
				.toString();

		Cache2Object<PatientProblemInfo> obj = new Cache2Object<PatientProblemInfo>(
				cCS, cache);

		// NsResponse nsResponse = new NsResponse();
		// ResponseData responsedata = new ResponseData();
		Type type = new TypeToken<List<PatientProblemInfo>>() {
		}.getType();
		List<PatientProblemInfo> patientProblemList = obj.getObject(cacheKey,
				isCachingOn, type);

		if (patientProblemList == null || patientProblemList.isEmpty()) {

			List<PatientProblem> patientProblemsList = accountDAO
					.getPatientProblemList(nsRequest.getViewPatientInfo()
							.getAccountId());
			/*
			 * List<PatientProblem> patientProblemsList =
			 * patientMuData.getAccountId().getPatientProblemList();
			 */

			patientProblemList = new ArrayList<PatientProblemInfo>();
			PatientProblemInfo patientProblemInfo = null;

			for (PatientProblem patientProblem : patientProblemsList) {
				patientProblemInfo = ResponseInfoObject
						.getPatientProblemInfo(patientProblem);
				patientProblemList.add(patientProblemInfo);

			}
			obj.setObject(cacheKey, isCachingOn, patientProblemList, type);
		}
		/*if (patientProblemList == null || patientProblemList.size() == 0) {
			throw new BusinessException("no.patient.problem.found");
		}*/
		// Collections.sort(patientProblemList,
		// new PatientProblemListComparator());

		/*
		 * nsResponse.setPatientProblemInfoList(patientProblemList);
		 * 
		 * responsedata.setResult(Integer.parseInt(messageSource.getMessage(
		 * VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
		 * nsRequest.getLocale()))); responsedata.setDescription("Success");
		 * nsResponse.setResponseData(responsedata);
		 */

		return patientProblemList;

	}

	@Override
	public List<ProcedureInfo> getProcedureList(NsRequest nsRequest)
			throws BusinessException, SystemException {

		String clientDBId = messageSource.getMessage("client.database.id",
				null, nsRequest.getLocale());

		String key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode())
				.append("_").append(CacheConstants.PATIENT_PROCEDURE.getCode()))
				.toString();

		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = new StringBuilder(key).append("_").append(clientDBId)
				.append("_")
				.append(nsRequest.getViewPatientInfo().getAccountId())
				.append("_")
				.append(nsRequest.getViewPatientInfo().getVisitId()).toString();

		Cache2Object<ProcedureInfo> obj = new Cache2Object<ProcedureInfo>(cCS,
				cache);

		//NsResponse nsResponse = new NsResponse();
		//ResponseData responsedata = new ResponseData();
		Type type = new TypeToken<List<ProcedureInfo>>() {
		}.getType();
		List<ProcedureInfo> patientProcedureList = obj.getObject(cacheKey,
				isCachingOn, type);

		if (patientProcedureList == null || patientProcedureList.isEmpty()) {

			PatientVisit patientMuData = getPatientMuData(nsRequest
					.getViewPatientInfo());
			List<PatientProcedure> patientProceduresList = patientMuData
					.getPatientProcedureList();

			patientProcedureList = new ArrayList<ProcedureInfo>();
			ProcedureInfo procedureInfo = null;
			for (PatientProcedure patientProcedure : patientProceduresList) {
				procedureInfo = ResponseInfoObject
						.getProcedureInfo(patientProcedure);
				patientProcedureList.add(procedureInfo);
			}
			obj.setObject(cacheKey, isCachingOn, patientProcedureList, type);

		}
		/*if (patientProcedureList == null || patientProcedureList.isEmpty()) {
			throw new BusinessException("no.procedure.found");
		}*/
		// Collections.sort(patientProcedureList,
		// new PatientProcedureListComparator());
		/*nsResponse.setProcedureInfoList(patientProcedureList);

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);*/

		return patientProcedureList;

	}

	@Override
	public List<LabResultInfo> getLabResultList(NsRequest nsRequest)
			throws BusinessException, SystemException {
		//NsResponse nsResponse = new NsResponse();
		//ResponseData responsedata = new ResponseData();
		String clientDBId = messageSource.getMessage("client.database.id",
				null, nsRequest.getLocale());
		String key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode())
				.append("_").append(CacheConstants.PATIENT_LAB.getCode()))
				.toString();
		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = new StringBuilder(key).append("_").append(clientDBId)
				.append("_")
				.append(nsRequest.getViewPatientInfo().getAccountId())
				.append("_")
				.append(nsRequest.getViewPatientInfo().getVisitId()).toString();
		Cache2Object<LabResultInfo> obj = new Cache2Object<LabResultInfo>(cCS,
				cache);
		Type type = new TypeToken<List<LabResultInfo>>() {
		}.getType();
		List<LabResultInfo> labResultInfoList = obj.getObject(cacheKey,
				isCachingOn, type);
		Map<String, List<NsPatientLabResult>> patientLabResultMap = null;
		if (labResultInfoList == null || labResultInfoList.isEmpty()) {
			LOGGER.debug("LAB RESULT FROM CACHE EITHER NULL OR EMPTY");
			PatientVisit patientMuData = getPatientMuData(nsRequest
					.getViewPatientInfo());
			List<PatientLab> patientLabResultsList = patientMuData
					.getPatientLabList();
			NsPatientLabResult nsPatientLabResult = null;
			LabResultInfo labResultInfo = null;
			labResultInfoList = new ArrayList<LabResultInfo>();
			/*
			 * for (PatientLab patientlabResult : patientLabResultsList) {
			 * labResultInfo =
			 * ResponseInfoObject.getLabResultInfo(patientlabResult);
			 * labResultInfoList.add(labResultInfo); }
			 */
			patientLabResultMap = new HashMap<String, List<NsPatientLabResult>>();
			for (PatientLab patientlabResult : patientLabResultsList) {
				labResultInfo = ResponseInfoObject
						.getLabResultInfo(patientlabResult);
				labResultInfoList.add(labResultInfo);
				nsPatientLabResult = ResponseInfoObject
						.getNsPatientLabResult(patientlabResult);
				if (nsPatientLabResult.getGroupName() != null
						&& nsPatientLabResult.getGroupName() != ""
						&& !nsPatientLabResult.getGroupName().isEmpty()) {
					if (patientLabResultMap.containsKey(nsPatientLabResult
							.getGroupName())) {
						List<NsPatientLabResult> nsPat = patientLabResultMap
								.get(nsPatientLabResult.getGroupName());
						Date nsPatDate = DateUtils.getDate(nsPat.get(0)
								.getGroupDate(), "MM/dd/yyyy");
						Date patLabDate = DateUtils
								.getDate(nsPatientLabResult.getGroupDate(),
										"MM/dd/yyyy");
						if (nsPatDate.before(patLabDate)) {
							nsPat = new ArrayList<NsPatientLabResult>();
							nsPat.add(nsPatientLabResult);
							patientLabResultMap.put(
									nsPatientLabResult.getGroupName(), nsPat);
						} else if (nsPatDate.compareTo(patLabDate) == 0) {
							nsPat.add(nsPatientLabResult);
							patientLabResultMap.put(
									nsPatientLabResult.getGroupName(), nsPat);
						}
					} else {
						List<NsPatientLabResult> nsList = new ArrayList<NsPatientLabResult>();
						nsList.add(nsPatientLabResult);
						patientLabResultMap.put(
								nsPatientLabResult.getGroupName(), nsList);
					}
				} else {
					if (patientLabResultMap.containsKey(nsPatientLabResult
							.getTestName())) {
						List<NsPatientLabResult> nsPat = patientLabResultMap
								.get(nsPatientLabResult.getTestName());
						Date nsPatDate = DateUtils.getDate(nsPat.get(0)
								.getDateAdded(), "MMMM d, yyyy");
						Date patLabDate = DateUtils.getDate(
								nsPatientLabResult.getDateAdded(),
								"MMMM d, yyyy");
						if (nsPatDate.before(patLabDate)) {
							nsPat = new ArrayList<NsPatientLabResult>();
							nsPat.add(nsPatientLabResult);
							patientLabResultMap.put(
									nsPatientLabResult.getTestName(), nsPat);
						} else if (nsPatDate.compareTo(patLabDate) == 0) {
							nsPat.add(nsPatientLabResult);
							patientLabResultMap.put(
									nsPatientLabResult.getTestName(), nsPat);
						}
					} else {
						List<NsPatientLabResult> nsList = new ArrayList<NsPatientLabResult>();
						nsList.add(nsPatientLabResult);
						patientLabResultMap.put(
								nsPatientLabResult.getTestName(), nsList);
					}
				}
			}
			obj.setObject(cacheKey, isCachingOn, labResultInfoList, type);
		}

		List<NsPatientLabResult> testList = new ArrayList<NsPatientLabResult>();
		List<LabGroups> labGroupsList = new ArrayList<LabGroups>();
		Set<String> mapKeys = patientLabResultMap.keySet();
		Iterator<String> keyIterator = mapKeys.iterator();
		while (keyIterator.hasNext()) {
			String labResultKey = keyIterator.next();
			List<NsPatientLabResult> labResList = patientLabResultMap
					.get(labResultKey);
			if (labResList.get(0).getGroupName().length() == 0
					|| labResList.get(0).getGroupId() == null
					|| labResList.get(0).getGroupName() == null
					|| labResList.get(0).getGroupName() == "") {
				testList.addAll(labResList);
			} else {
				LabGroups labGroups = new LabGroups();
				labGroups.setGroupDate(labResList.get(0).getGroupDate());
				labGroups.setGroupId(labResList.get(0).getGroupId());
				labGroups.setSourceName(labResList.get(0).getSourceName());
				labGroups.setGroupName(labResList.get(0).getGroupName());
				Collections.sort(labResList, new LabTestListComparator());
				labGroups.setLabResultList(labResList);
				labGroupsList.add(labGroups);
			}
		}
		LabResults labRes = new LabResults();
		Collections.sort(labGroupsList, new LabGroupListComparator());
		Collections.sort(testList, new LabTestListComparator());
		// Collections.sort(patientLabResultMap, new
		// LabResultInfoListComparator());
		// nsResponse.setLabResultInfoList(labResultInfoList);
		labRes.setLabGroups(labGroupsList);
		labRes.setLabTests(testList);
		//nsResponse.setLabRes(labRes);
		/*if (patientLabResultMap == null || patientLabResultMap.isEmpty()) {
			throw new BusinessException("no.lab.result.found");
		}*/
		/*responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);*/
		return labResultInfoList;
	}

	@Override
	public List<CarePlanInfo> getCarePlanList(NsRequest nsRequest)
			throws BusinessException, SystemException {

		String clientDBId = messageSource.getMessage("client.database.id",
				null, nsRequest.getLocale());

		String key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode())
				.append("_").append(CacheConstants.PATIENT_CARE_PLAN.getCode()))
				.toString();
		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = new StringBuilder(key).append("_").append(clientDBId)
				.append("_")
				.append(nsRequest.getViewPatientInfo().getAccountId())
				.append("_")
				.append(nsRequest.getViewPatientInfo().getVisitId()).toString();

		Cache2Object<CarePlanInfo> obj = new Cache2Object<CarePlanInfo>(cCS,
				cache);
		Type type = new TypeToken<List<CarePlanInfo>>() {
		}.getType();
		List<CarePlanInfo> carePlanInfoList = obj.getObject(cacheKey,
				isCachingOn, type);
		// NsResponse nsResponse = new NsResponse();
		// ResponseData responsedata = new ResponseData();

		if (carePlanInfoList == null || carePlanInfoList.isEmpty()) {

			LOGGER.debug("LAB RESULT FROM CACHE EITHER NULL OR EMPTY");

			PatientVisit patientMuData = getPatientMuData(nsRequest
					.getViewPatientInfo());
			List<PatientCarePlan> patientCarePlanList = patientMuData
					.getPatientCarePlanList();

			/*
			 * NsResponse nsResponse = new NsResponse(); ResponseData
			 * responsedata = new ResponseData();
			 */

			carePlanInfoList = new ArrayList<CarePlanInfo>();
			CarePlanInfo carePlanInfo = null;
			for (PatientCarePlan patientCarePlan : patientCarePlanList) {
				carePlanInfo = ResponseInfoObject
						.getCarePlanInfo(patientCarePlan);
				/*
				 * CarePlanInfo carePlanInfo = new CarePlanInfo();
				 * carePlanInfo.setCarePlanId(patientCarePlan
				 * .getPatientCarePlanPK().getCarePlanId());
				 * carePlanInfo.setGoal(patientCarePlan.getGoal()); carePlanInfo
				 * .setInstructions(patientCarePlan.getInstructions());
				 */
				carePlanInfoList.add(carePlanInfo);
			}
			obj.setObject(cacheKey, isCachingOn, carePlanInfoList, type);
		}
		/*if (carePlanInfoList == null || carePlanInfoList.isEmpty()) {
			throw new BusinessException("no.care.plan.found");
		}*/
		// Collections
		// .sort(carePlanInfoList, new CarePlanInfoListComparator());
		/*
		 * nsResponse.setCarePlanInfoList(carePlanInfoList);
		 * 
		 * responsedata.setResult(Integer.parseInt(messageSource.getMessage(
		 * VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
		 * nsRequest.getLocale()))); responsedata.setDescription("Success");
		 * nsResponse.setResponseData(responsedata);
		 */

		return carePlanInfoList;

	}

	@Override
	public PatientInpatientMetadataInfo getInpatientMetaData(NsRequest nsRequest)
			throws BusinessException, SystemException {

		String clientDBId = messageSource.getMessage("client.database.id",
				null, nsRequest.getLocale());

		String key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode())
				.append("_").append(CacheConstants.PATIENT_VISIT_INPATIENT
				.getCode())).toString();
		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = new StringBuilder(key).append("_").append(clientDBId)
				.append("_")
				.append(nsRequest.getViewPatientInfo().getAccountId())
				.append("_")
				.append(nsRequest.getViewPatientInfo().getVisitId()).toString();

		Cache2Object<PatientInpatientMetadataInfo> obj = new Cache2Object<PatientInpatientMetadataInfo>(
				cCS, cache);
		Type type = new TypeToken<List<PatientInpatientMetadataInfo>>() {
		}.getType();
		List<PatientInpatientMetadataInfo> patientInpatientMetadataInfoList = obj
				.getObject(cacheKey, isCachingOn, type);
		//NsResponse nsResponse = new NsResponse();
		//ResponseData responsedata = new ResponseData();
		PatientInpatientMetadataInfo patientInpatientMetadataInfo = null;
		if (patientInpatientMetadataInfoList == null
				|| patientInpatientMetadataInfoList.isEmpty()) {

			LOGGER.debug("Patient Inpatient Metadata Info FROM CACHE EITHER NULL OR EMPTY");

			patientInpatientMetadataInfoList = new ArrayList<PatientInpatientMetadataInfo>();

			PatientVisit patientMuData = getPatientMuData(nsRequest.getViewPatientInfo());
			PatientVisitInpatient patientInpatientMetadata = new PatientVisitInpatient();
			try {
				patientInpatientMetadata = patientMuData.getPatientVisitInpatientList().get(0);
			} catch (Exception e) {
				throw new BusinessException("no.acc.details.found");
			}

			patientInpatientMetadataInfo = ResponseInfoObject.getPatientInpatientMetadataInfo(patientMuData, patientInpatientMetadata);
			List<PatientInpatientMetadataInfo> list = new ArrayList<PatientInpatientMetadataInfo>();
			list.add(patientInpatientMetadataInfo);

			obj.setObject(cacheKey, isCachingOn, list, type);

		} else {
			patientInpatientMetadataInfo = patientInpatientMetadataInfoList
					.get(0);
		}
		/*nsResponse
				.setPatientInpatientMetadataInfo(patientInpatientMetadataInfo);
		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);*/

		return patientInpatientMetadataInfo;

	}

	@Override
	public NsResponse getInpatientHospitalisationReason(NsRequest nsRequest)
			throws BusinessException, SystemException {
		try {
			PatientVisit patientMuData = getPatientMuData(nsRequest
					.getViewPatientInfo());

			PatientVisitInpatient patientInpatientMetadata = patientMuData
					.getPatientVisitInpatientList().get(0);

			NsResponse nsResponse = new NsResponse();
			ResponseData responsedata = new ResponseData();

			nsResponse.setHopitalizationReason(patientInpatientMetadata
					.getReasonForHospitalization());

			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("Success");
			nsResponse.setResponseData(responsedata);

			return nsResponse;
		} catch (Exception e) {
			LOGGER.debug("Error in getInpatientHospitalisationReason.");
			throw new BusinessException("no.acc.details.found");
		}
	}

	@Override
	public List<PatientInpatientDiagnosisInfo> getInpatientDiagnosisList(NsRequest nsRequest)
			throws BusinessException, SystemException {
		int clientDBId = Integer.parseInt(messageSource.getMessage(
				"client.database.id", null, nsRequest.getLocale()));

		Boolean isCachingOn = cCS.isCachingOn(CacheConstants.PATIENT_DIAGNOSIS
				.getCode());
		String cacheKey = CacheKeys.getPatientDiagnosisKeyByVisit(nsRequest
				.getViewPatientInfo().getAccountId(), clientDBId, nsRequest
				.getViewPatientInfo().getVisitId());

		Cache2Object<PatientInpatientDiagnosisInfo> obj = new Cache2Object<PatientInpatientDiagnosisInfo>(
				cCS, cache);

		//NsResponse nsResponse = new NsResponse();
		//ResponseData responsedata = new ResponseData();
		Type type = new TypeToken<List<PatientInpatientDiagnosisInfo>>() {
		}.getType();
		List<PatientInpatientDiagnosisInfo> patientInpatientDiagnosisInfoList = obj
				.getObject(cacheKey, isCachingOn, type);

		if (patientInpatientDiagnosisInfoList == null
				|| patientInpatientDiagnosisInfoList.isEmpty()) {
			PatientVisit patientMuData = getPatientMuData(nsRequest
					.getViewPatientInfo());

			List<PatientDiagnosis> patientInpatientDiagnosisList = patientMuData
					.getPatientDiagnosisList();

			patientInpatientDiagnosisInfoList = new ArrayList<PatientInpatientDiagnosisInfo>();
			for (PatientDiagnosis patientInpatientDiagnosis : patientInpatientDiagnosisList) {
				PatientInpatientDiagnosisInfo patientInpatientDiagnosisInfo = ResponseInfoObject
						.getPatientInpatientDiagnosisInfo(patientMuData,
								patientInpatientDiagnosis);
				patientInpatientDiagnosisInfoList
						.add(patientInpatientDiagnosisInfo);
			}
			obj.setObject(cacheKey, isCachingOn,
					patientInpatientDiagnosisInfoList, type);
		}
		/*if (patientInpatientDiagnosisInfoList == null
				|| patientInpatientDiagnosisInfoList.isEmpty()) {
			throw new BusinessException("no.diagnosis.found");
		}*/
		// Collections.sort(patientInpatientDiagnosisInfoList, new
		// InptientDiagnosisListComparator());
		/*nsResponse
				.setPatientInpatientDiagnosisInfoList(patientInpatientDiagnosisInfoList);

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);*/

		return patientInpatientDiagnosisInfoList;

	}

	@Override
	public List<PatientInpatientFunctionalStatusInfo> getInpatientFunctionalStatusList(NsRequest nsRequest)
			throws BusinessException, SystemException {
		String clientDBId = messageSource.getMessage("client.database.id",
				null, nsRequest.getLocale());

		String key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode())
				.append("_").append(CacheConstants.PATIENT_FUNCTIONAL_STATUS
				.getCode())).toString();

		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = new StringBuilder(key).append("_").append(clientDBId)
				.append("_")
				.append(nsRequest.getViewPatientInfo().getAccountId())
				.append("_")
				.append(nsRequest.getViewPatientInfo().getVisitId()).toString();

		Cache2Object<PatientInpatientFunctionalStatusInfo> obj = new Cache2Object<PatientInpatientFunctionalStatusInfo>(
				cCS, cache);

		//NsResponse nsResponse = new NsResponse();
		//ResponseData responsedata = new ResponseData();
		Type type = new TypeToken<List<PatientInpatientFunctionalStatusInfo>>() {
		}.getType();
		List<PatientInpatientFunctionalStatusInfo> patientInpatientFunctionalStatusInfoList = obj
				.getObject(cacheKey, isCachingOn, type);

		if (patientInpatientFunctionalStatusInfoList == null
				|| patientInpatientFunctionalStatusInfoList.isEmpty()) {

			PatientVisit patientMuData = getPatientMuData(nsRequest
					.getViewPatientInfo());
			List<PatientFunctionalStatus> patientFunctionalStatusList = patientMuData
					.getPatientFunctionalStatusList();

			/*
			 * NsResponse nsResponse = new NsResponse(); ResponseData
			 * responsedata = new ResponseData();
			 */

			patientInpatientFunctionalStatusInfoList = new ArrayList<PatientInpatientFunctionalStatusInfo>();
			PatientInpatientFunctionalStatusInfo patientInpatientFunctionalStatusInfo = null;
			for (PatientFunctionalStatus patientInpatientFunctionalStatus : patientFunctionalStatusList) {
				patientInpatientFunctionalStatusInfo = ResponseInfoObject
						.getPatientInpatientFunctionalStatusInfo(patientInpatientFunctionalStatus);

				patientInpatientFunctionalStatusInfoList
						.add(patientInpatientFunctionalStatusInfo);

			}
			obj.setObject(cacheKey, isCachingOn,
					patientInpatientFunctionalStatusInfoList, type);
		}
		/*if (patientInpatientFunctionalStatusInfoList == null
				|| patientInpatientFunctionalStatusInfoList.isEmpty()) {
			throw new BusinessException("no.inpatient.functional.found");
		}*/
		// Collections.sort(patientInpatientFunctionalStatusInfoList,
		// new InpatientFunctionalStatusListComparator());
		/*nsResponse
				.setPatientInpatientFunctionalStatusInfoList(patientInpatientFunctionalStatusInfoList);

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);*/

		return patientInpatientFunctionalStatusInfoList;

	}

	@Override
	public List<PatientInpatientImmunalizationInfo> getInpatientImmunalizationList(
			NsRequest nsRequest) throws BusinessException, SystemException {
		String clientDBId = messageSource.getMessage("client.database.id",
				null, nsRequest.getLocale());

		String key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode())
				.append("_").append(CacheConstants.PATIENT_IMMUNIZATION
				.getCode())).toString();

		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = new StringBuilder(key).append("_").append(clientDBId)
				.append("_")
				.append(nsRequest.getViewPatientInfo().getAccountId())
				.append("_")
				.append(nsRequest.getViewPatientInfo().getVisitId()).toString();

		Cache2Object<PatientInpatientImmunalizationInfo> obj = new Cache2Object<PatientInpatientImmunalizationInfo>(
				cCS, cache);

		// NsResponse nsResponse = new NsResponse();
		// ResponseData responsedata = new ResponseData();
		Type type = new TypeToken<List<PatientInpatientImmunalizationInfo>>() {
		}.getType();
		List<PatientInpatientImmunalizationInfo> patientInpatientImmunalizationInfoList = obj
				.getObject(cacheKey, isCachingOn, type);

		if (patientInpatientImmunalizationInfoList == null
				|| patientInpatientImmunalizationInfoList.isEmpty()) {

			PatientVisit patientMuData = getPatientMuData(nsRequest
					.getViewPatientInfo());
			List<PatientImmunization> patientImmunizationList = patientMuData
					.getPatientImmunizationList();
			patientInpatientImmunalizationInfoList = new ArrayList<PatientInpatientImmunalizationInfo>();
			PatientInpatientImmunalizationInfo patientInpatientImmunalizationInfo = null;
			for (PatientImmunization patientInpatientImmunization : patientImmunizationList) {

				patientInpatientImmunalizationInfo = ResponseInfoObject
						.getPatientInpatientImmunalizationInfo(patientInpatientImmunization);

				patientInpatientImmunalizationInfoList
						.add(patientInpatientImmunalizationInfo);

			}
			obj.setObject(cacheKey, isCachingOn,
					patientInpatientImmunalizationInfoList, type);
		}
		/*if (patientInpatientImmunalizationInfoList == null
				|| patientInpatientImmunalizationInfoList.isEmpty()) {
			throw new BusinessException("no.immunization.found");
		}*/

		// Collections.sort(patientInpatientImmunalizationInfoList,
		// new InpatientImmunizationListComparator());
		/*
		 * nsResponse.setPatientInpatientImmunalizationInfoList(
		 * patientInpatientImmunalizationInfoList);
		 * 
		 * responsedata.setResult(Integer.parseInt(messageSource.getMessage(
		 * VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
		 * nsRequest.getLocale()))); responsedata.setDescription("Success");
		 * nsResponse.setResponseData(responsedata);
		 */

		return patientInpatientImmunalizationInfoList;

	}

	@Override
	public NsResponse transmitEHR(NsRequest nsRequest)
			throws BusinessException, SystemException {
		try {
			LOGGER.debug("In Transmit EHR");
			NsResponse nsResponse = new NsResponse();
			ResponseData responsedata = new ResponseData();

			NsResponse muData = new NsResponse();
			muData = getMuData(nsRequest.getViewPatientInfo());
			ViewPatientInfo viewPatientInfo = new ViewPatientInfo();
			viewPatientInfo.setPatientId(muData.getViewPatientInfo()
					.getPatientId());
			nsResponse.setViewPatientInfo(viewPatientInfo);

			HashMap<String, byte[]> attachmentsMap = new HashMap<String, byte[]>();

			for (Attachments attachments : nsRequest.getTransmitEHR()
					.getAttachments()) {
				String fileName = attachments.getFileName();
				/** File Name of attachment to be downloaded */
				String fileType = attachments.getTypeOfFile();
				/** Type of file : 'PDF' or 'XML' */
				String downloadFileVersion = fileName.toLowerCase().contains(
						SUMMARY) ? SUMMARY : TRANSITION_OF_CARE;
				/** FILE Version : 'Summary' or 'TOC' */

				if (fileType.equalsIgnoreCase(FILE_TYPE_PDF)) {
					attachmentsMap.put(attachments.getFileName(),
							new GeneratePDF().generatePDF(muData,
									downloadFileVersion));

				} else if (fileType.equalsIgnoreCase(FILE_TYPE_XML)) {
					attachmentsMap.put(attachments.getFileName(), GenerateCCDA
							.generateCCD(muData, downloadFileVersion));
				}
			}

			secureMailSender.secureTransmit(nsRequest.getTransmitEHR().getTo(),
					nsRequest.getTransmitEHR().getBody(), nsRequest
							.getTransmitEHR().getSubject(), attachmentsMap,
					nsRequest.getLocale());
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("Mail sent successfully");
			nsResponse.setResponseData(responsedata);

			return nsResponse;
		} catch (Exception exp) {
			throw new SystemException(exp); // logs will be printed in exception
											// handler
		}
	}

	@Override
	public NsResponse transmitEHRActivityLog(NsRequest nsRequest)
			throws BusinessException, SystemException {
		try {
			Gson jsonConverter = new Gson();
			NsResponse nsResponse = new NsResponse();
			ResponseData responsedata = new ResponseData();

			List<ActivityLog> activityLogList = viewPatientDetailsServiceDAO
					.getTransmitEHRActivityLogs(nsRequest.getActivityLog()
							.getAccountId(), Activity.TRANSMIT_EHR, DateUtils
							.getDate(nsRequest.getActivityLog().getFromDate(),
									"MM/dd/yyyy"), DateUtils.getDate(nsRequest
							.getActivityLog().getTodaysDate(), "MM/dd/yyyy"));

			Account account = accountDAO.getAccountById(nsRequest
					.getActivityLog().getAccountId());

			if (activityLogList.size() == 0) {
				responsedata.setResult(Integer.parseInt(messageSource
						.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE,
								null, nsRequest.getLocale())));
				responsedata
						.setDescription("No Transaction Activities Present");
				nsResponse.setResponseData(responsedata);
			}

			List<ActivityLogInfo> activityLogInfoList = new ArrayList<ActivityLogInfo>();
			for (ActivityLog activityLog : activityLogList) {
				ActivityLogInfo activityLogInfo = new ActivityLogInfo();
				// activityLogInfo.setAccountId(activityLog.getAuditLogonId());
				activityLogInfo.setAccountName(account.getFirstName() + " "
						+ account.getLastName());
				activityLogInfo.setActivity(activityLog.getActivity());
				// activityLogInfo.setRequest(activityLog.getRequest());
				// activityLogInfo.setResponse(activityLog.getResponse());
				try {

					activityLogInfo.setSentDate(DateUtils.getFormatDate(
							activityLog.getDateAdded(), "MM/dd/yyyy HH:mm"));
				} catch (Exception e) {
					LOGGER.debug("Could not set sent date for activity list");
				}
				NsRequest activityRequest = jsonConverter.fromJson(
						activityLog.getRequest(), NsRequest.class);
				activityRequest.getTransmitEHR().getAttachments().get(0)
						.getFileName();
				Iterator<Attachments> iter = activityRequest.getTransmitEHR()
						.getAttachments().iterator();
				List<String> attachmentList = new ArrayList<String>();
				while (iter.hasNext()) {
					attachmentList.add(iter.next().getFileName());
				}
				activityLogInfo.setAttachmentList(attachmentList);
				NsResponse activityResponse = jsonConverter.fromJson(
						activityLog.getResponse(), NsResponse.class);
				activityLogInfo.setToEmailId((activityRequest.getTransmitEHR()
						.getTo()));
				activityLogInfo.setStatus(activityResponse.getResponseData()
						.getDescription());
				// activityLogInfo.setDateAdded((newSimpleDateFormat("MM/dd/yyyy HH:mm").format(activityLog.getDateAdded())).toString());
				activityLogInfoList.add(activityLogInfo);
			}
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("Success");
			nsResponse.setResponseData(responsedata);
			nsResponse.setActivityLog(activityLogInfoList);

			return nsResponse;
		} catch (Exception exp) {
			throw new SystemException(exp); // logs will be printed in exception
											// handler
		}
	}

	@Override
	public NsResponse accessHistory(NsRequest nsRequest)
			throws BusinessException, SystemException {
		try {
			NsResponse nsResponse = new NsResponse();
			ResponseData responsedata = new ResponseData();

			List<ActivityLog> activityLogList = viewPatientDetailsServiceDAO
					.getActivityLogAccessHistory(nsRequest.getActivityLog()
							.getAccountId(), DateUtils.getDate(nsRequest
							.getActivityLog().getFromDate(), "MM/dd/yyyy"),
							DateUtils.getDate(nsRequest.getActivityLog()
									.getTodaysDate(), "MM/dd/yyyy"));

			Account account = accountDAO.getAccountById(nsRequest
					.getActivityLog().getAccountId());

			if (activityLogList.size() == 0 || activityLogList.isEmpty()
					|| activityLogList == null) {
				/*
				 * responsedata.setResult(Integer.parseInt(messageSource.getMessage
				 * ( VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				 * nsRequest.getLocale())));
				 * responsedata.setDescription("No Transaction Activities Present"
				 * ); nsResponse.setResponseData(responsedata);
				 */
				return NsResponseUtils.getNsResponse(null, messageSource
						.getMessage("no.lab.result", null,
								nsRequest.getLocale()),
						VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE
								.getCode());
			}

			List<ActivityLogInfo> activityLogInfoList = new ArrayList<ActivityLogInfo>();

			for (ActivityLog activityLog : activityLogList) {
				ActivityLogInfo activityLogInfo = new ActivityLogInfo();
				activityLogInfo.setAccountId(activityLog.getAuditAccountId());
				activityLogInfo.setAccountName(account.getFirstName() + " "
						+ account.getLastName());
				activityLogInfo.setActivity(activityLog.getActivity());
				// Gson gson = new Gson();
				/*
				 * NsRequest loggedRequest = gson.fromJson(
				 * activityLog.getRequest(), NsRequest.class);
				 * activityLogInfo.setUnitNumber
				 * (loggedRequest.getViewPatientInfo().getUnitNumber());
				 */
				activityLogInfo.setUnitNumber(account.getMedicalRecordNumber());

				// activityLogInfo.setRequest(activityLog.getRequest());
				// activityLogInfo.setResponse(activityLog.getResponse());
				try {
					activityLogInfo.setSentDate(DateUtils.getFormatDate(
							activityLog.getDateAdded(), "MM/dd/yyyy HH:mm"));
				} catch (Exception e) {
					LOGGER.debug("Could not set date added for activity log info");
				}
				Gson jsonConverter = new Gson();
				NsRequest activityRequest = jsonConverter.fromJson(
						activityLog.getRequest(), NsRequest.class);
				NsResponse activityResponse = jsonConverter.fromJson(
						activityLog.getResponse(), NsResponse.class);
				if (activityRequest.getTransmitEHR() != null) {
					activityLogInfo.setToEmailId((activityRequest
							.getTransmitEHR().getTo()));
				} else
					activityLogInfo.setToEmailId(account.getEmail());
				activityLogInfo.setStatus(activityResponse.getResponseData()
						.getDescription());
				try {

					activityLogInfo.setDateAdded(DateUtils.getFormatDate(
							activityLog.getDateAdded(), "MMMM d, yyyy"));
				} catch (Exception e) {
					LOGGER.debug("Could not set date added for activity log info");
				}
				activityLogInfoList.add(activityLogInfo);
			}
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			responsedata.setDescription("Success");
			nsResponse.setResponseData(responsedata);
			nsResponse.setActivityLog(activityLogInfoList);

			return nsResponse;
		} catch (Exception exp) {
			throw new SystemException(exp); // logs will be printed in exception
											// handler
		}
	}

	@Override
	public NsResponse vdtReporting(NsRequest nsRequest)
			throws BusinessException, SystemException {
		try {
			NsResponse nsResponse = new NsResponse();
			ResponseData responsedata = new ResponseData();

			List<VdtCoreMeasureScorecard> coreMeasureScorecard = viewPatientDetailsServiceDAO
					.getVdtReporting(nsRequest);

			List<VDTDataObject> vdtCoreMeasureScorecardDataList = new ArrayList<VDTDataObject>();

			for (VdtCoreMeasureScorecard vdtCoreMeasureScorecard : coreMeasureScorecard) {
				VDTDataObject vdtCoreMeasureScorecardData = new VDTDataObject();
				vdtCoreMeasureScorecardData.setStage(vdtCoreMeasureScorecard
						.getStage());
				vdtCoreMeasureScorecardData
						.setMeasureType(vdtCoreMeasureScorecard
								.getMeasureType());
				vdtCoreMeasureScorecardData
						.setMeasureNumber(vdtCoreMeasureScorecard
								.getMeasureNumber());
				vdtCoreMeasureScorecardData
						.setMeasureSubNumber(vdtCoreMeasureScorecard
								.getMeasureSubNumber());
				vdtCoreMeasureScorecardData.setMeasure(vdtCoreMeasureScorecard
						.getMeasure());
				vdtCoreMeasureScorecardData
						.setMeasureGoal(vdtCoreMeasureScorecard
								.getMeasureGoal());
				vdtCoreMeasureScorecardData
						.setMeasureGoal2(vdtCoreMeasureScorecard
								.getMeasureGoal2());
				vdtCoreMeasureScorecardData
						.setPopulateDenominator(vdtCoreMeasureScorecard
								.getPopulateDenominator());
				vdtCoreMeasureScorecardData
						.setPopulateNumerator(vdtCoreMeasureScorecard
								.getPopulateNumerator());
				vdtCoreMeasureScorecardData
						.setNumeratorRecorded(vdtCoreMeasureScorecard
								.getNumeratorRecorded());
				vdtCoreMeasureScorecardData
						.setPercentage(vdtCoreMeasureScorecard.getPercentage());
				vdtCoreMeasureScorecardData
						.setPercentageInt(vdtCoreMeasureScorecard
								.getPercentageInt());
				vdtCoreMeasureScorecardData
						.setIsStg1GoalMet(vdtCoreMeasureScorecard
								.getIsStg1GoalMet());
				vdtCoreMeasureScorecardData
						.setIsStg2GoalMet(vdtCoreMeasureScorecard
								.getIsStg2GoalMet());
				vdtCoreMeasureScorecardDataList
						.add(vdtCoreMeasureScorecardData);
			}

			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			nsResponse.setResponseData(responsedata);
			nsResponse.setVdtData(vdtCoreMeasureScorecardDataList);
			return nsResponse;
		} catch (Exception exp) {
			throw new SystemException(exp); // logs will be printed in exception
											// handler
		}
	}

	@Override
	public NsResponse vdtReportingAccessHistory(NsRequest nsRequest)
			throws BusinessException, SystemException {
		try {
			NsResponse nsResponse = new NsResponse();
			ResponseData responsedata = new ResponseData();

			List<VdtCoreMeasure> coreMeasureList = viewPatientDetailsServiceDAO
					.getVdtReportAccessHistory(nsRequest);

			List<VDTDataObject> vdtCoreMeasureDataList = new ArrayList<VDTDataObject>();

			for (VdtCoreMeasure vdtCoreMeasure : coreMeasureList) {
				VDTDataObject vdtCoreMeasureData = new VDTDataObject();
				try {
					vdtCoreMeasureData
							.setDateOfBirth(DateUtils.getFormatDate(
									vdtCoreMeasure.getDateOfBirth(),
									"MM/dd/yyyy HH:mm"));
				} catch (Exception e) {
					LOGGER.debug("Could not set birth date for vdt reporting");
				}
				vdtCoreMeasureData.setFirstName(vdtCoreMeasure.getFirstName());
				vdtCoreMeasureData.setLastName(vdtCoreMeasure.getLastName());
				vdtCoreMeasureData.setPatientId(vdtCoreMeasure.getPatientId());
				vdtCoreMeasureDataList.add(vdtCoreMeasureData);
			}

			responsedata.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
					nsRequest.getLocale())));
			nsResponse.setResponseData(responsedata);
			nsResponse.setVdtData(vdtCoreMeasureDataList);
			return nsResponse;
		} catch (Exception exp) {
			throw new SystemException(exp); // logs will be printed in exception
											// handler
		}
	}

	@Override
	public NsResponse getPatientInformation(NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		PatientInformation patientInformation = new PatientInformation();
		
		try{  
		patientInformation.setVitalSigns(getVitalSigns(nsRequest) != null ? getVitalSigns(nsRequest) : new ArrayList<VitalSigns>());
		patientInformation.setAllergiesInfo(getAllergiesList(nsRequest) != null ? getAllergiesList(nsRequest) : new ArrayList<AllergiesInfo>());
		patientInformation.setPatientInpatientImmunalizationInfo(getInpatientImmunalizationList(nsRequest) != null ? getInpatientImmunalizationList(nsRequest) : new ArrayList<PatientInpatientImmunalizationInfo>());
		patientInformation.setPatientProblemInfo(getProblemList(nsRequest) !=null ? getProblemList(nsRequest) : new ArrayList<PatientProblemInfo>());
		patientInformation.setPatientInpatientMetadataInfo(getpatientMetaData(nsRequest));
		nsResponse.setPatientInformation(patientInformation);
		}
		catch (Exception e) {
			e.printStackTrace();

		}
		if (nsResponse.getPatientInformation() == null)
			throw new BusinessException("no.patient.info.found");
		
		responsedata.setResult(Integer.parseInt(messageSource.getMessage(
				VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);
		return nsResponse;

	}

	@Override
	public NsResponse getVisitDetails(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		VisitDetails visitDetails=new VisitDetails();
		PatientInformationAdmission patientInformationAdmission=new PatientInformationAdmission();
		PatientInformationDischarge patientInformationDischarge=new PatientInformationDischarge();
		PatientInformationHospitalizationReason patientInformationHospitalizationReason=new PatientInformationHospitalizationReason();
		DischargeInstruction patientDischargeInstruction=new DischargeInstruction();
		
		
        	
		
        try{
        	visitDetails.setCarePlanInfo(getCarePlanList(nsRequest) != null ? getCarePlanList(nsRequest) : new ArrayList<CarePlanInfo>() );
	    	visitDetails.setCateTeamInfo(getProviderList(nsRequest) != null ? getProviderList(nsRequest) : new ArrayList<CateTeamInfo>());			
			visitDetails.setPatientInpatientDiagnosisInfo(getInpatientDiagnosisList(nsRequest) != null ? getInpatientDiagnosisList(nsRequest) : new ArrayList<PatientInpatientDiagnosisInfo>());			
			visitDetails.setProcedureInfo(getProcedureList(nsRequest) != null ? getProcedureList(nsRequest) : new ArrayList<ProcedureInfo>());			
			visitDetails.setMedicationInfo(getMedicationList(nsRequest) != null ? getMedicationList(nsRequest) : new ArrayList<MedicationInfo>());			
			//visitDetails.setLabRes(getLabResultList(nsRequest) != null ? getLabResultList(nsRequest) : new ArrayList<LabResultInfo>());
			//visitDetails.setPatientImagingInfo(fetchAccountImagingDetails(nsRequest.getAccountInfo().getAccountId()) != null ? fetchAccountImagingDetails(nsRequest.getAccountInfo().getAccountId()) : new ArrayList<PatientImagingInfo>());
	        visitDetails.setPatientInpatientFunctionalStatusInfo(getInpatientFunctionalStatusList(nsRequest) != null ? getInpatientFunctionalStatusList(nsRequest) : new ArrayList<PatientInpatientFunctionalStatusInfo>());        

	        PatientInpatientMetadataInfo patientInpatientMetadataInfo=getInpatientMetaData(nsRequest);
	        
	        patientInformationAdmission.setAdmissionDate(DateUtils.getDateWithChar(patientInpatientMetadataInfo.getAdmissionDate()));
	        patientInformationAdmission.setAdmissionLocation(patientInpatientMetadataInfo.getAdmissionLocation());
	        patientInformationDischarge.setDischargeDate(DateUtils.getDateWithChar(patientInpatientMetadataInfo.getDischargeDate()));  
	        patientInformationDischarge.setDischargeLocation(patientInpatientMetadataInfo.getDischargeLocation());
	        patientInformationHospitalizationReason.setPatientInformationHospitalizationReason(patientInpatientMetadataInfo.getHospitalizationReason());
	        patientDischargeInstruction.setPatientDischargeInstruction(patientInpatientMetadataInfo.getDischargeInstruction());
	        visitDetails.setPatientInformationAdmission(patientInformationAdmission);
	        visitDetails.setPatientInformationDischarge(patientInformationDischarge);
	        visitDetails.setPatientInformationHospitalizationReason(patientInformationHospitalizationReason);
	        visitDetails.setPatientDischargeInstruction(patientDischargeInstruction);
	        
	        PatientTestInfo patientTestInfo=getLabReultImagaingInfo(nsRequest);
	        visitDetails.setPatientTestInfo(patientTestInfo);
	        
	        
	        
	        
	        
			
			nsResponse.setVisitDetails(visitDetails);					
		}catch (Exception e) {
			e.printStackTrace();

		}
        if (nsResponse.getVisitDetails() == null)
			throw new BusinessException("no.visits.present");
        
        responsedata.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);		
		return nsResponse;	
	}
	private PatientTestInfo getLabReultImagaingInfo(NsRequest nsRequest) throws BusinessException, SystemException {
		
		PatientTestInfo patientTestInfo=new PatientTestInfo();
		List<LabResultInfo> labResultInfo =getLabResultList(nsRequest);
		List<PatientImagingInfo> patientImagingInfo=fetchAccountImagingDetails(nsRequest.getAccountInfo().getAccountId(),nsRequest.getViewPatientInfo().getVisitId());
		
		List<LabResultInfo> labResultInformation = new ArrayList<LabResultInfo>();
		for(LabResultInfo labResInfo:labResultInfo){
			LabResultInfo labResultInfo1=new LabResultInfo();
			labResultInfo1.setLabResultId(labResInfo.getLabResultId());
			labResultInfo1.setLabTest(labResInfo.getLabTest());
			labResultInfo1.setLabTestCode(labResInfo.getLabTestCode());
			labResultInfo1.setLabResultUnit(labResInfo.getLabResultUnit());
			labResultInfo1.setDateAdded(labResInfo.getDateAdded());
			labResultInformation.add(labResultInfo1);
			
		}		
		
		List<PatientImagingInfo> patientImagingInformation = new ArrayList<PatientImagingInfo>();
		for (PatientImagingInfo imaging : patientImagingInfo) {
			PatientImagingInfo patientImagingInfo1=new PatientImagingInfo();
			patientImagingInfo1.setAccountId(nsRequest.getAccountInfo().getAccountId());
			patientImagingInfo1.setPatientVisitId(nsRequest.getViewPatientInfo().getVisitId());
			patientImagingInfo1.setExamId(imaging.getExamId());
			patientImagingInfo1.setExamDate(imaging.getExamDate());
			patientImagingInfo1.setExamName(imaging.getExamName());			
			patientImagingInformation.add(patientImagingInfo1);
			
		}
		
		patientTestInfo.setLabResultInfo(labResultInformation);
		patientTestInfo.setPatientImagingInfo(patientImagingInformation);
		
		return patientTestInfo;
		
       
		
		
		
		
		
	}

	@Override
	public PatientInpatientMetadataInfo getpatientMetaData(NsRequest nsRequest)
			throws BusinessException, SystemException {
		
		    PatientInpatientMetadataInfo patientInpatientMetadataInfo = null;		
			PatientVisit patientMuData = getPatientMuData(nsRequest.getViewPatientInfo());			
			patientInpatientMetadataInfo = ResponseInfoObject.getPatientInpatientMetadata(patientMuData);			
			List<PatientInpatientMetadataInfo> list = new ArrayList<PatientInpatientMetadataInfo>();
			list.add(patientInpatientMetadataInfo);		

		return patientInpatientMetadataInfo;	
		
	}
	
	private List<PatientImagingInfo> fetchAccountImagingDetails(Integer accountId, Integer patientVisitId) throws BusinessException,SystemException{
		String key = CacheConstants.PATIENT_IMAGING.getCode();
		Boolean isCachingOn = cCS.isCachingOn(key);
		String cacheKey = formatCacheKey(accountId, key);

		LOGGER.debug("key : " + key + " cacheKey : " + cacheKey);
		Cache2Object<PatientImagingInfo> obj = new Cache2Object<PatientImagingInfo>(cCS, cache);

		Type type = new TypeToken<List<PatientImagingInfo>>() {
		}.getType();
		List<PatientImagingInfo> patientImagingInfoList = obj.getObject(cacheKey, isCachingOn, type);

		if (patientImagingInfoList == null || patientImagingInfoList.isEmpty()) {

			List<PatientImaging> patientImaging = accountDAO.getPatientImaging(accountId,patientVisitId);

			patientImagingInfoList = new ArrayList<PatientImagingInfo>();
			PatientImagingInfo patientImagingInfo = null;

			for (PatientImaging imaging : patientImaging) {
				patientImagingInfo = ResponseInfoObject.getPatientImagingInfo(imaging);
				patientImagingInfoList.add(patientImagingInfo);
			}
			obj.setObject(cacheKey, isCachingOn, patientImagingInfoList, type);
		}
		return patientImagingInfoList;
	}
	
	private String formatCacheKey(int accountId, String key) {
		String clientDBId = messageSource.getMessage("client.database.id", null, Locale.getDefault());
		// key = key + "_" + nsRequest.getAccountInfo().getAccountId();
		return new StringBuilder(key).append("_").append(clientDBId).append("_").append(accountId).toString();
	} 	
	
	
}