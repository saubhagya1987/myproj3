package com.versawork.http.etlCachePopulation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.versawork.http.cacheClientsInfo.ClientCacheSettingsCheck;
import com.versawork.http.cacheDataObject.CPatientFeedback;
import com.versawork.http.cacheDataObject.CPatientVisit;
import com.versawork.http.cacheDataObject.CPatientVisitInpatient;
import com.versawork.http.cacheDataObject.Cache2Object;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.constant.CacheConstants;
import com.versawork.http.dao.FeedbackDAO;
import com.versawork.http.dataobject.CarePlanInfo;
import com.versawork.http.dataobject.CateTeamInfo;
import com.versawork.http.dataobject.LabResultInfo;
import com.versawork.http.dataobject.MedicationInfo;
import com.versawork.http.dataobject.PatientInpatientDiagnosisInfo;
import com.versawork.http.dataobject.PatientInpatientFunctionalStatusInfo;
import com.versawork.http.dataobject.PatientInpatientImmunalizationInfo;
import com.versawork.http.dataobject.ProcedureInfo;
import com.versawork.http.dataobject.VitalSigns;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.PatientCarePlan;
import com.versawork.http.model.PatientCareTeam;
import com.versawork.http.model.PatientDiagnosis;
import com.versawork.http.model.PatientFeedback;
import com.versawork.http.model.PatientFunctionalStatus;
import com.versawork.http.model.PatientImmunization;
import com.versawork.http.model.PatientLab;
import com.versawork.http.model.PatientPrescription;
import com.versawork.http.model.PatientProcedure;
import com.versawork.http.model.PatientVisit;
import com.versawork.http.model.PatientVisitInpatient;
import com.versawork.http.model.PatientVitalSign;
import com.versawork.http.utils.ResponseInfoObject;

/**
 * @author Sohaib
 * 
 *         Populates cache in redis for individual modules that are dependent on
 *         visits
 */
@Component
public class VisitCachePopulation {

	@Autowired
	CacheAccessbyJson cache;
	@Autowired
	private FeedbackDAO feedbackDAO;

	final static Logger LOGGER = LoggerFactory.getLogger("CACHEFILE");

	@Autowired
	protected ClientCacheSettingsCheck cCS;

	private String KEY_TOKEN = "_";

	public Boolean setCachedVisitLists(List<PatientVisit> patientVisits, Integer accountId, boolean isETLCall,
			Integer clientDatabaseId) throws BusinessException, SystemException {
		String key = CacheConstants.PATIENT_VISIT.getCode() + KEY_TOKEN + accountId.toString();
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls();
		Gson gson = builder.create();
		Type listType = new TypeToken<List<CPatientVisit>>() {
		}.getType();

		List<CPatientVisit> cPatientVisit = new ArrayList<CPatientVisit>();
		for (PatientVisit patientVisit : patientVisits) {
			CPatientVisit fN = new CPatientVisit();
			fN.setAccountId(accountId);
			fN.setBirthDate(patientVisit.getBirthDate());
			fN.setDateAdded(patientVisit.getDateAdded());
			fN.setEncounterEndDate(patientVisit.getEncounterEndDate());
			fN.setEncounterStartDate(patientVisit.getEncounterStartDate());
			fN.setEthnicity(patientVisit.getEthnicity());
			fN.setEthnicityCode(patientVisit.getEthnicityCode());
			fN.setFirstName(patientVisit.getFirstName());
			fN.setLastName(patientVisit.getLastName());
			fN.setPatientVisitId(patientVisit.getPatientVisitPK().getPatientVisitId());
			fN.setPreferredLanguage(patientVisit.getPreferredLanguage());
			fN.setRace(patientVisit.getRace());
			fN.setRaceCode(patientVisit.getRaceCode());
			fN.setSex(patientVisit.getSex());
			fN.setSmokingStatus(patientVisit.getSmokingStatus());
			fN.setSmokingStatusCode(patientVisit.getSmokingStatusCode());
			fN.setSourceId(patientVisit.getSourceId());
			fN.setSourceName(patientVisit.getSourceName());
			fN.setVisitDate(patientVisit.getVisitDate());
			fN.setVisitIdentifier(patientVisit.getVisitIdentifier());
			fN.setVisitTypeId(patientVisit.getVisitTypeId());
			fN.setProviderName(patientVisit.getProviderName());
			if (isETLCall)
				setVisitDetailsDataCache(accountId, patientVisit, clientDatabaseId);
			cPatientVisit.add(fN);
		}
		String json = gson.toJson(cPatientVisit, listType);
		return cache.setCacheData(key, json);
	}

	private void setVisitDetailsDataCache(Integer accountId, PatientVisit patientVisit, Integer clientDatabaseId)
			throws BusinessException, SystemException {
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls();
		Gson gson = builder.create();
		String key = null;
		Type listType = null;

		{

			key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode()).append(KEY_TOKEN)
					.append(CacheConstants.PATIENT_CARE_PLAN.getCode()).append(KEY_TOKEN).append(clientDatabaseId)
					.append(KEY_TOKEN).append(accountId).append(KEY_TOKEN).append(patientVisit.getPatientVisitPK()
					.getPatientVisitId())).toString();
			// LOGGER.debug("key "+ key);
			Cache2Object<CarePlanInfo> obj = new Cache2Object<CarePlanInfo>(cCS, cache);
			listType = new TypeToken<List<CarePlanInfo>>() {
			}.getType();

			List<CarePlanInfo> carePlanInfoList = new ArrayList<CarePlanInfo>();
			CarePlanInfo carePlanInfo = null;
			for (PatientCarePlan patientCarePlan : patientVisit.getPatientCarePlanList()) {
				carePlanInfo = ResponseInfoObject.getCarePlanInfo(patientCarePlan);

				carePlanInfoList.add(carePlanInfo);
			}
			obj.setObject(key, true, carePlanInfoList, listType);

			/*
			 * key = CacheConstants.PATIENT_CARE_PLAN.getCode()+KEY_TOKEN +
			 * accountId
			 * .toString()+KEY_TOKEN+patientVisit.getPatientVisitId().toString
			 * (); listType = new TypeToken<List<CPatientCarePlan>>() {
			 * }.getType(); List<CPatientCarePlan> cPatientCarePlanList = new
			 * ArrayList<CPatientCarePlan>(); for (PatientCarePlan
			 * cPatientCarePlans : patientVisit.getPatientCarePlanList()) {
			 * CPatientCarePlan fN = new CPatientCarePlan();
			 * fN.setDateAdded(cPatientCarePlans.getDateAdded());
			 * fN.setGoal(cPatientCarePlans.getGoal());
			 * fN.setInstructions(cPatientCarePlans.getInstructions());
			 * fN.setSourceId(cPatientCarePlans.getSourceId());
			 * fN.setSourceName(cPatientCarePlans.getSourceName());
			 * fN.setAccountId
			 * (cPatientCarePlans.getPatientCarePlanPK().getAccountId());
			 * fN.setCarePlanId
			 * (cPatientCarePlans.getPatientCarePlanPK().getCarePlanId());
			 * fN.setPatientVisitId
			 * (cPatientCarePlans.getPatientCarePlanPK().getPatientVisitId());
			 * cPatientCarePlanList.add(fN); } String json =
			 * gson.toJson(cPatientCarePlanList, listType);// PatientLab.class);
			 * cache.setCacheData(key, json);
			 */
		}

		{

			key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode()).append(KEY_TOKEN)
					.append(CacheConstants.PATIENT_CARE_TEAM.getCode()).append(KEY_TOKEN).append(clientDatabaseId)
					.append(KEY_TOKEN).append(accountId).append(KEY_TOKEN).append(patientVisit.getPatientVisitPK()
					.getPatientVisitId())).toString();

			// LOGGER.debug("key : "+key );
			Cache2Object<CateTeamInfo> obj = new Cache2Object<CateTeamInfo>(cCS, cache);

			Type type = new TypeToken<List<CateTeamInfo>>() {
			}.getType();

			List<CateTeamInfo> careTeamInfoList = new ArrayList<CateTeamInfo>();
			CateTeamInfo careTeamInfo = null;
			for (PatientCareTeam patientCareTeam : patientVisit.getPatientCareTeamList()) {
				careTeamInfo = ResponseInfoObject.getCareTeamInfo(patientCareTeam, clientDatabaseId);

				careTeamInfoList.add(careTeamInfo);
			}

			obj.setObject(key, true, careTeamInfoList, type);
		}
		/*
		 * key = CacheConstants.PATIENT_CARE_TEAM.getCode()+KEY_TOKEN +
		 * accountId
		 * .toString()+KEY_TOKEN+patientVisit.getPatientVisitId().toString();
		 * listType = new TypeToken<List<CPatientCareTeam>>() { }.getType();
		 * List<CPatientCareTeam> cPatientCareTeamList = new
		 * ArrayList<CPatientCareTeam>(); for (PatientCareTeam cPatientCareTeams
		 * : patientVisit.getPatientCareTeamList()) { CPatientCareTeam fN = new
		 * CPatientCareTeam();
		 * fN.setAccountId(cPatientCareTeams.getPatientCareTeamPK
		 * ().getAccountId());
		 * fN.setPatientVisitId(cPatientCareTeams.getPatientCareTeamPK
		 * ().getPatientVisitId());
		 * fN.setAddress1(cPatientCareTeams.getAddress1());
		 * fN.setAddress2(cPatientCareTeams.getAddress2());
		 * fN.setCareMemberEmail(cPatientCareTeams.getCareMemberEmail());
		 * fN.setCareMemberSpeciality
		 * (cPatientCareTeams.getCareMemberSpeciality());
		 * fN.setCareMemberId(cPatientCareTeams
		 * .getPatientCareTeamPK().getCareMemberId());
		 * fN.setCareMemberName(cPatientCareTeams.getCareMemberName());
		 * fN.setCareMemberRoleId
		 * (cPatientCareTeams.getPatientCareTeamPK().getCareMemberRoleId());
		 * fN.setCity(cPatientCareTeams.getCity());
		 * fN.setCountry(cPatientCareTeams.getCountry());
		 * fN.setDateAdded(cPatientCareTeams.getDateAdded());
		 * fN.setPhoneNumber(cPatientCareTeams.getPhoneNumber());
		 * fN.setPostalCode(cPatientCareTeams.getPostalCode());
		 * fN.setSourceId(cPatientCareTeams.getSourceId());
		 * fN.setSourceName(cPatientCareTeams.getSourceName());
		 * fN.setState(cPatientCareTeams.getState());
		 * cPatientCareTeamList.add(fN); } String json =
		 * gson.toJson(cPatientCareTeamList, listType);// PatientLab.class);
		 * cache.setCacheData(key, json);
		 */
		// }

		{/*
		 * key = CacheConstants.PATIENT_DIAGNOSIS.getCode()+KEY_TOKEN +
		 * accountId
		 * .toString()+KEY_TOKEN+patientVisit.getPatientVisitId().toString();
		 * //LOGGER.debug("key "+ key); listType = new
		 * TypeToken<List<CPatientDiagnosis>>() { }.getType();
		 * List<CPatientDiagnosis> cPatientDiagnosisList = new
		 * ArrayList<CPatientDiagnosis>(); for (PatientDiagnosis
		 * cPatientDiagnosisLs : patientVisit.getPatientDiagnosisList()) {
		 * CPatientDiagnosis fN = new CPatientDiagnosis();
		 * fN.setDateAdded(cPatientDiagnosisLs.getDateAdded());
		 * fN.setDiagnosisName(cPatientDiagnosisLs.getDiagnosisName());
		 * fN.setSourceId(cPatientDiagnosisLs.getSourceId());
		 * fN.setSourceName(cPatientDiagnosisLs.getSourceName());
		 * fN.setStatus(cPatientDiagnosisLs.getStatus());
		 * fN.setDiagnosisSeqId(cPatientDiagnosisLs.getDiagnosisSeqId());
		 * fN.setAccountId
		 * (cPatientDiagnosisLs.getPatientDiagnosisPK().getAccountId());
		 * fN.setDiagnosisCode
		 * (cPatientDiagnosisLs.getPatientDiagnosisPK().getDiagnosisCode());
		 * fN.setPatientVisitId
		 * (cPatientDiagnosisLs.getPatientDiagnosisPK().getPatientVisitId());
		 * cPatientDiagnosisList.add(fN); } String json =
		 * gson.toJson(cPatientDiagnosisList, listType);// PatientLab.class);
		 * cache.setCacheData(key, json);
		 */

			key = CacheConstants.PATIENT_DIAGNOSIS.getCode();

			String cacheKey = new StringBuilder(key).append("_").append(clientDatabaseId).append("_").append(accountId)
					.append("_").append(patientVisit.getPatientVisitPK().getPatientVisitId()).toString();

			// LOGGER.debug("key : " + key + " cacheKey : " + cacheKey);
			Cache2Object<PatientInpatientDiagnosisInfo> obj = new Cache2Object<PatientInpatientDiagnosisInfo>(cCS,
					cache);

			listType = new TypeToken<List<PatientInpatientDiagnosisInfo>>() {
			}.getType();

			List<PatientDiagnosis> patientInpatientDiagnosisList = patientVisit.getPatientDiagnosisList();
			List<PatientInpatientDiagnosisInfo> patientInpatientDiagnosisInfoList = new ArrayList<PatientInpatientDiagnosisInfo>();
			PatientInpatientDiagnosisInfo patientInpatientDiagnosisInfo = null;
			for (PatientDiagnosis patientInpatientDiagnosis : patientInpatientDiagnosisList) {
				patientInpatientDiagnosisInfo = ResponseInfoObject.getPatientInpatientDiagnosisInfo(patientVisit,
						patientInpatientDiagnosis);
				patientInpatientDiagnosisInfoList.add(patientInpatientDiagnosisInfo);
			}
			obj.setObject(cacheKey, true, patientInpatientDiagnosisInfoList, listType);
		}

		{
			key = CacheConstants.PATIENT_FEEDBACK.getCode() + KEY_TOKEN + accountId.toString() + KEY_TOKEN
					+ patientVisit.getPatientVisitPK().getPatientVisitId();
			// LOGGER.debug("key "+ key);
			listType = new TypeToken<List<CPatientFeedback>>() {
			}.getType();
			List<CPatientFeedback> cPatientFeedbackList = new ArrayList<CPatientFeedback>();
			List<PatientFeedback> patientFeedbacks = feedbackDAO.getFeedBackByPatientVisitId(patientVisit
					.getPatientVisitPK().getPatientVisitId());
			for (PatientFeedback cPatientFeedbacks : patientFeedbacks) {
				CPatientFeedback fN = new CPatientFeedback();
				fN.setDateAdded(cPatientFeedbacks.getDateAdded());
				fN.setComment(cPatientFeedbacks.getComment());
				fN.setLastViewedByAcountId(cPatientFeedbacks.getLastViewedByAcountId());
				fN.setPatientFeedbackId(cPatientFeedbacks.getPatientFeedbackId());
				// fN.setPatientVisitId(cPatientFeedbacks.getPatientVisitId());
				fN.setRecoveryRating(cPatientFeedbacks.getRecoveryRating());
				fN.setVisitRating(cPatientFeedbacks.getVisitRating());
				cPatientFeedbackList.add(fN);
			}
			String json = gson.toJson(cPatientFeedbackList, listType);
			cache.setCacheData(key, json);
		}

		{
			// String clientDBId = messageSource.getMessage(
			// "client.database.id", null, Locale.getDefault());

			key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode()).append(KEY_TOKEN)
					.append(CacheConstants.PATIENT_FUNCTIONAL_STATUS.getCode()).append(KEY_TOKEN)
					.append(clientDatabaseId).append(KEY_TOKEN).append(accountId).append(KEY_TOKEN)
					.append(+patientVisit.getPatientVisitPK().getPatientVisitId())).toString();
			// LOGGER.debug("key "+ key);
			// LOGGER.debug("key : "+key+" cacheKey : "+cacheKey);
			// key =
			// CacheConstants.PATIENT_FUNCTIONAL_STATUS.getCode()+KEY_TOKEN
			// +
			// accountId.toString()+KEY_TOKEN+patientVisit.getPatientVisitId().toString();
			/*
			 * listType = new TypeToken<List<CPatientFunctionalStatus>>() {
			 * }.getType(); List<CPatientFunctionalStatus>
			 * cPatientFunctionalStatus = new
			 * ArrayList<CPatientFunctionalStatus>(); for
			 * (PatientFunctionalStatus cPatientFunctionalStatusS :
			 * patientVisit.getPatientFunctionalStatusList()) {
			 * CPatientFunctionalStatus fN = new CPatientFunctionalStatus();
			 * fN.setDateAdded(cPatientFunctionalStatusS.getDateAdded());
			 * fN.setFunctionDescription
			 * (cPatientFunctionalStatusS.getFunctionDescription());
			 * fN.setSourceId(cPatientFunctionalStatusS.getSourceId());
			 * fN.setStatusDate(cPatientFunctionalStatusS.getStatusDate());
			 * fN.setSourceName(cPatientFunctionalStatusS.getSourceName());
			 * fN.setStatus(cPatientFunctionalStatusS.getStatus());
			 * fN.setStatusCode(cPatientFunctionalStatusS.getStatusCode());
			 * fN.setAccountId
			 * (cPatientFunctionalStatusS.getPatientFunctionalStatusPK
			 * ().getAccountId());
			 * fN.setFunctionCode(cPatientFunctionalStatusS.getFunctionCode());
			 * fN
			 * .setFunctionId(cPatientFunctionalStatusS.getPatientFunctionalStatusPK
			 * ().getFunctionId());
			 * fN.setPatientVisitId(cPatientFunctionalStatusS
			 * .getPatientFunctionalStatusPK().getPatientVisitId());
			 * fN.setFunctionId2
			 * (cPatientFunctionalStatusS.getPatientFunctionalStatusPK
			 * ().getFunctionId2()); cPatientFunctionalStatus.add(fN); }
			 */
			listType = new TypeToken<List<PatientInpatientFunctionalStatusInfo>>() {
			}.getType();
			List<PatientInpatientFunctionalStatusInfo> functionalStatusList = new ArrayList<PatientInpatientFunctionalStatusInfo>();
			PatientInpatientFunctionalStatusInfo patientFunctionalStatusInfo = null;
			for (PatientFunctionalStatus patientInpatientFunctionalStatus : patientVisit
					.getPatientFunctionalStatusList()) {
				patientFunctionalStatusInfo = ResponseInfoObject
						.getPatientInpatientFunctionalStatusInfo(patientInpatientFunctionalStatus);
				functionalStatusList.add(patientFunctionalStatusInfo);
			}

			String json = gson.toJson(functionalStatusList, listType);
			cache.setCacheData(key, json);
		}

		{
			key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode()).append(KEY_TOKEN)
					.append(CacheConstants.PATIENT_IMMUNIZATION.getCode()).append(KEY_TOKEN).append(clientDatabaseId)
					.append(KEY_TOKEN).append(accountId).append(KEY_TOKEN).append(patientVisit.getPatientVisitPK()
					.getPatientVisitId())).toString();

			// LOGGER.debug("key : "+key );
			Cache2Object<PatientInpatientImmunalizationInfo> obj = new Cache2Object<PatientInpatientImmunalizationInfo>(
					cCS, cache);

			Type type = new TypeToken<List<PatientInpatientImmunalizationInfo>>() {
			}.getType();
			List<PatientInpatientImmunalizationInfo> patientInpatientImmunalizationInfoList = new ArrayList<PatientInpatientImmunalizationInfo>();
			PatientInpatientImmunalizationInfo patientInpatientImmunalizationInfo = null;
			for (PatientImmunization patientInpatientImmunization : patientVisit.getPatientImmunizationList()) {
				patientInpatientImmunalizationInfo = ResponseInfoObject
						.getPatientInpatientImmunalizationInfo(patientInpatientImmunization);

				patientInpatientImmunalizationInfoList.add(patientInpatientImmunalizationInfo);
			}
			obj.setObject(key, true, patientInpatientImmunalizationInfoList, type);

			/*
			 * key = CacheConstants.PATIENT_IMMUNIZATION.getCode()+KEY_TOKEN +
			 * accountId
			 * .toString()+KEY_TOKEN+patientVisit.getPatientVisitId().toString
			 * (); listType = new TypeToken<List<CPatientImmunization>>() {
			 * }.getType(); List<CPatientImmunization> cPatientImmunizationList
			 * = new ArrayList<CPatientImmunization>(); for (PatientImmunization
			 * cPatientImmunizations :
			 * patientVisit.getPatientImmunizationList()) { CPatientImmunization
			 * fN = new CPatientImmunization();
			 * fN.setDateAdded(cPatientImmunizations.getDateAdded());
			 * fN.setImmunizationCode
			 * (cPatientImmunizations.getImmunizationCode());
			 * fN.setImmunizationName
			 * (cPatientImmunizations.getImmunizationName());
			 * fN.setStatusDate(cPatientImmunizations.getStatusDate());
			 * fN.setRouteCode(cPatientImmunizations.getRouteCode());
			 * fN.setRouteName(cPatientImmunizations.getRouteName());
			 * fN.setSourceId(cPatientImmunizations.getSourceId());
			 * fN.setSourceName(cPatientImmunizations.getSourceName());
			 * fN.setStatus(cPatientImmunizations.getStatus());
			 * fN.setAccountId(cPatientImmunizations
			 * .getPatientImmunizationPK().getAccountId());
			 * fN.setImmunizationId(
			 * cPatientImmunizations.getPatientImmunizationPK
			 * ().getImmunizationId());
			 * fN.setPatientVisitId(cPatientImmunizations
			 * .getPatientImmunizationPK().getPatientVisitId());
			 * cPatientImmunizationList.add(fN); } String json =
			 * gson.toJson(cPatientImmunizationList, listType);
			 * cache.setCacheData(key, json);
			 */}

		{

			key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode()).append(KEY_TOKEN)
					.append(CacheConstants.PATIENT_LAB.getCode()).append(KEY_TOKEN).append(clientDatabaseId)
					.append(KEY_TOKEN).append(accountId).append(KEY_TOKEN).append(patientVisit.getPatientVisitPK()
					.getPatientVisitId())).toString();
			// LOGGER.debug("KEY : "+key);
			Cache2Object<LabResultInfo> obj = new Cache2Object<LabResultInfo>(cCS, cache);
			Type type = new TypeToken<List<LabResultInfo>>() {
			}.getType();
			List<LabResultInfo> labResultInfoList = new ArrayList<LabResultInfo>();

			LabResultInfo labResultInfo = null;
			labResultInfoList = new ArrayList<LabResultInfo>();
			for (PatientLab patientlabResult : patientVisit.getPatientLabList()) {

				labResultInfo = ResponseInfoObject.getLabResultInfo(patientlabResult);
				/*
				 * LabResultInfo labResultInfo = new LabResultInfo();
				 * labResultInfo
				 * .setLabResultId(patientlabResult.getPatientLabPK()
				 * .getLabId());
				 * labResultInfo.setLabTest(patientlabResult.getTestName());
				 * labResultInfo.setLabTestCode(patientlabResult.getLabCode());
				 * labResultInfo.setLabResult(patientlabResult.getLabResult());
				 * try { if(patientlabResult .getDateAdded() != null)
				 * labResultInfo.setDateAdded((new SimpleDateFormat(
				 * "MM/dd/yyyy").format(patientlabResult
				 * .getDateAdded())).toString()); } catch (Exception e) {
				 * e.printStackTrace(); LOGGER.debug(
				 * "Could not set date added for lab result for visit : " +
				 * patientMuData.getPatientVisitId()); }
				 */
				labResultInfoList.add(labResultInfo);
			}

			obj.setObject(key, true, labResultInfoList, type);

			/*
			 * key = CacheConstants.PATIENT_LAB.getCode()+KEY_TOKEN +
			 * accountId.toString(); listType = new
			 * TypeToken<List<CPatientLab>>() { }.getType(); List<CPatientLab>
			 * cPatientLabResultsList = new ArrayList<CPatientLab>(); for
			 * (PatientLab cPatientLabs : patientVisit.getPatientLabList()) {
			 * CPatientLab fN = new CPatientLab();
			 * fN.setDateAdded(cPatientLabs.getDateAdded());
			 * fN.setInterpretationCode(cPatientLabs .getInterpretationCode());
			 * fN.setLabCode(cPatientLabs.getLabCode());
			 * fN.setLabResult(cPatientLabs.getLabResult());
			 * fN.setLabUnit(cPatientLabs.getLabUnit());
			 * fN.setOrganizerCode(cPatientLabs.getOrganizerCode());
			 * fN.setOrganizerName(cPatientLabs.getOrganizerName());
			 * fN.setResultDate(cPatientLabs.getResultDate());
			 * fN.setSourceId(cPatientLabs.getSourceId());
			 * fN.setSourceName(cPatientLabs.getSourceName());
			 * fN.setTestName(cPatientLabs.getTestName());
			 * fN.setNormalRange(cPatientLabs.getNormalRange());
			 * fN.setAbnormalFlag(cPatientLabs.getAbnormalFlag());
			 * fN.setLabGroupCode(cPatientLabs.getLabGroupCode());
			 * fN.setLabGroupName(cPatientLabs.getLabGroupName());
			 * fN.setLabGroupDate(cPatientLabs.getLabGroupDate());
			 * fN.setLabGroupId(cPatientLabs.getPatientLabPK().getLabGroupId());
			 * fN.setAccountId(cPatientLabs.getPatientLabPK().getAccountId());
			 * fN.setLabId(cPatientLabs.getPatientLabPK().getLabId());
			 * fN.setPatientVisitId
			 * (cPatientLabs.getPatientLabPK().getPatientVisitId());
			 * fN.setTestId(cPatientLabs.getPatientLabPK().getTestId());
			 * cPatientLabResultsList.add(fN); } String json =
			 * gson.toJson(cPatientLabResultsList, listType);
			 * cache.setCacheData(key, json);
			 */
		}

		{

			key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode()).append("_")
					.append(CacheConstants.PATIENT_MEDICATION.getCode()).append("_").append(clientDatabaseId)
					.append("_").append(accountId).append(KEY_TOKEN).append(patientVisit.getPatientVisitPK()
					.getPatientVisitId())).toString();

			// LOGGER.debug("key : " + key );
			Cache2Object<MedicationInfo> obj = new Cache2Object<MedicationInfo>(cCS, cache);
			listType = new TypeToken<List<MedicationInfo>>() {
			}.getType();
			List<MedicationInfo> medicationList = new ArrayList<MedicationInfo>();
			MedicationInfo medicationInfo = null;
			for (PatientPrescription patientmedication : patientVisit.getPatientMedicationList()) {
				medicationInfo = ResponseInfoObject.getPatientMedicationInfo(patientmedication);
				medicationList.add(medicationInfo);
			}
			obj.setObject(key, true, medicationList, listType);
			/*
			 * PVIS_ PMED_ clientDBId_accountId_ patientVisitId key =
			 * cCacheConstants.PATIENT_MEDICATION.getCode()+KEY_TOKEN +
			 * accountId.toString(); listType = new
			 * TypeToken<List<CPatientPrescription>>() { }.getType();
			 * List<CPatientPrescription> cPatientPescriptionsS = new
			 * ArrayList<CPatientPrescription>(); for (PatientPrescription
			 * cPatientPescriptions : patientVisit.getPatientMedicationList()) {
			 * CPatientPrescription fN = new CPatientPrescription();
			 * fN.setDateAdded(cPatientPescriptions.getDateAdded());
			 * fN.setDosageDescription
			 * (cPatientPescriptions.getDosageDescription());
			 * fN.setFrequency(cPatientPescriptions.getFrequency());
			 * fN.setDoseQuantity(cPatientPescriptions.getDoseQuantity());
			 * fN.setEndDate(cPatientPescriptions.getEndDate());
			 * fN.setDoseStrength(cPatientPescriptions.getDoseStrength());
			 * fN.setMedicationName(cPatientPescriptions.getMedicationName());
			 * fN.setRouteOfAdministration(cPatientPescriptions.
			 * getRouteOfAdministration());
			 * fN.setRouteOfAdministrationCode(cPatientPescriptions
			 * .getRouteOfAdministrationCode());
			 * fN.setRxNumber(cPatientPescriptions.getRxNumber());
			 * fN.setSourceId(cPatientPescriptions.getSourceId());
			 * fN.setSourceName(cPatientPescriptions.getSourceName());
			 * fN.setStartDate(cPatientPescriptions.getStartDate());
			 * fN.setStatus(cPatientPescriptions.getStatus());
			 * fN.setMedicationGenericName
			 * (cPatientPescriptions.getMedicationGenericName());
			 * fN.setMedicationBrandName
			 * (cPatientPescriptions.getMedicationBrandName());
			 * fN.setMedicationStrength
			 * (cPatientPescriptions.getMedicationStrength());
			 * fN.setUnit(cPatientPescriptions.getUnit());
			 * fN.setDirection(cPatientPescriptions.getDirection());
			 * fN.setAccountId
			 * (cPatientPescriptions.getPatientPrescriptionPK().getAccountId());
			 * fN
			 * .setMedicationId(cPatientPescriptions.getPatientPrescriptionPK()
			 * .getMedicationId());
			 * fN.setPatientVisitId(cPatientPescriptions.getPatientPrescriptionPK
			 * ().getPatientVisitId()); cPatientPescriptionsS.add(fN); } String
			 * json = gson.toJson(cPatientPescriptionsS, listType);
			 * cache.setCacheData(key, json);
			 */}

		{

			key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode()).append("_")
					.append(CacheConstants.PATIENT_PROCEDURE.getCode()).append("_").append(clientDatabaseId)
					.append("_").append(accountId).append("_").append(patientVisit.getPatientVisitPK()
					.getPatientVisitId())).toString();

			// LOGGER.debug("key : "+key );
			Cache2Object<ProcedureInfo> obj = new Cache2Object<ProcedureInfo>(cCS, cache);

			listType = new TypeToken<List<ProcedureInfo>>() {
			}.getType();

			List<ProcedureInfo> patientProcedureList = new ArrayList<ProcedureInfo>();
			ProcedureInfo procedureInfo = null;
			for (PatientProcedure patientProcedure : patientVisit.getPatientProcedureList()) {
				ResponseInfoObject.getProcedureInfo(patientProcedure);
				patientProcedureList.add(procedureInfo);
			}

			obj.setObject(key, true, patientProcedureList, listType);

			/*
			 * key = CacheConstants.PATIENT_PROCEDURE.getCode()+KEY_TOKEN +
			 * accountId
			 * .toString()+KEY_TOKEN+patientVisit.getPatientVisitId().toString
			 * (); listType = new TypeToken<List<CPatientProcedure>>() {
			 * }.getType(); List<CPatientProcedure> cPatientProcedure = new
			 * ArrayList<CPatientProcedure>(); for (PatientProcedure
			 * cPatientProcedureS : patientVisit.getPatientProcedureList()) {
			 * CPatientProcedure fN = new CPatientProcedure();
			 * fN.setDateAdded(cPatientProcedureS.getDateAdded());
			 * fN.setProcedureCode(cPatientProcedureS.getProcedureCode());
			 * fN.setProcedureName(cPatientProcedureS.getProcedureName());
			 * fN.setSourceId(cPatientProcedureS.getSourceId());
			 * fN.setSourceName(cPatientProcedureS.getSourceName());
			 * fN.setProcedureDate(cPatientProcedureS.getProcedureDate());
			 * fN.setAccountId
			 * (cPatientProcedureS.getPatientProcedurePK().getAccountId());
			 * fN.setPatientVisitId
			 * (cPatientProcedureS.getPatientProcedurePK().getAccountId());
			 * fN.setProcedureId
			 * (cPatientProcedureS.getPatientProcedurePK().getProcedureId());
			 * fN.setProcedureId2(cPatientProcedureS.getPatientProcedurePK().
			 * getProcedureId2()); cPatientProcedure.add(fN); } String json =
			 * gson.toJson(cPatientProcedure, listType); cache.setCacheData(key,
			 * json);
			 */
		}

		{
			key = CacheConstants.PATIENT_VISIT_INPATIENT.getCode() + KEY_TOKEN + accountId.toString() + KEY_TOKEN
					+ patientVisit.getPatientVisitPK().getPatientVisitId();
			// LOGGER.debug("key "+ key);
			listType = new TypeToken<List<CPatientVisitInpatient>>() {
			}.getType();
			List<CPatientVisitInpatient> cPatientVisitInpatient = new ArrayList<CPatientVisitInpatient>();
			for (PatientVisitInpatient cPatientVisitInpatientS : patientVisit.getPatientVisitInpatientList()) {
				CPatientVisitInpatient fN = new CPatientVisitInpatient();
				fN.setDateAdded(cPatientVisitInpatientS.getDateAdded());
				fN.setAdmitLocation(cPatientVisitInpatientS.getAdmitLocation());
				fN.setDischargeInstruction(cPatientVisitInpatientS.getDischargeInstruction());
				fN.setDischargeLocation(cPatientVisitInpatientS.getDischargeLocation());
				fN.setReasonForHospitalization(cPatientVisitInpatientS.getReasonForHospitalization());
				fN.setSourceId(cPatientVisitInpatientS.getSourceId());
				fN.setSourceName(cPatientVisitInpatientS.getSourceName());
				fN.setAccountId(cPatientVisitInpatientS.getPatientVisitInpatientPK().getAccountId());
				fN.setPatientVisitId(cPatientVisitInpatientS.getPatientVisitInpatientPK().getAccountId());
				cPatientVisitInpatient.add(fN);
			}
			String json = gson.toJson(cPatientVisitInpatient, listType);
			cache.setCacheData(key, json);
		}

		{

			key = (new StringBuilder(CacheConstants.PATIENT_VISIT.getCode()).append("_")
					.append(CacheConstants.PATIENT_VITAL_SIGN.getCode()).append("_").append(clientDatabaseId)
					.append("_").append(accountId).append("_").append(patientVisit.getPatientVisitPK()
					.getPatientVisitId())).toString();

			// LOGGER.debug("key : "+key );
			Cache2Object<VitalSigns> obj = new Cache2Object<VitalSigns>(cCS, cache);

			listType = new TypeToken<List<VitalSigns>>() {
			}.getType();
			List<VitalSigns> vitalSignsList = new ArrayList<VitalSigns>();

			VitalSigns vitalSigns = null;
			for (PatientVitalSign patientVitalsigns : patientVisit.getPatientVitalSignList()) {
				vitalSigns = ResponseInfoObject.getVitalSigns(patientVitalsigns);
				vitalSignsList.add(vitalSigns);

			}
			obj.setObject(key, true, vitalSignsList, listType);

			/*
			 * key = CacheConstants.PATIENT_VITAL_SIGN.getCode()+KEY_TOKEN +
			 * accountId
			 * .toString()+KEY_TOKEN+patientVisit.getPatientVisitId().toString
			 * (); listType = new TypeToken<List<CPatientVisitInpatient>>() {
			 * }.getType(); List<CPatientVitalSign> cPatientVitalSign = new
			 * ArrayList<CPatientVitalSign>(); for (PatientVitalSign
			 * cPatientVitalSigns : patientVisit.getPatientVitalSignList()) {
			 * CPatientVitalSign fN = new CPatientVitalSign();
			 * fN.setDateAdded(cPatientVitalSigns.getDateAdded());
			 * fN.setBmi(cPatientVitalSigns.getBmi());
			 * fN.setDiastolicBp(cPatientVitalSigns.getDiastolicBp());
			 * fN.setDiastolicBpUnit(cPatientVitalSigns.getDiastolicBpUnit());
			 * fN.setHeight(cPatientVitalSigns.getHeightFeet());
			 * fN.setHeightUnit(cPatientVitalSigns.getHeightInches());
			 * fN.setSystolicBp(cPatientVitalSigns.getSystolicBp());
			 * fN.setSystolicBpUnit(cPatientVitalSigns.getSystolicBpUnit());
			 * fN.setWeight(cPatientVitalSigns.getWeightLbs());
			 * //fN.setWeightUnit(cPatientVitalSigns.getWeightUnit());
			 * fN.setSourceId(cPatientVitalSigns.getSourceId());
			 * fN.setSourceName(cPatientVitalSigns.getSourceName());
			 * fN.setAccountId
			 * (cPatientVitalSigns.getPatientVitalSignPK().getAccountId());
			 * fN.setPatientVisitId
			 * (cPatientVitalSigns.getPatientVitalSignPK().getAccountId());
			 * cPatientVitalSign.add(fN); } String json =
			 * gson.toJson(cPatientVitalSign, listType); cache.setCacheData(key,
			 * json);
			 */
		}

	}
}
