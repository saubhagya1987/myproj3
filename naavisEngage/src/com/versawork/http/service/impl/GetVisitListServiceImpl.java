package com.versawork.http.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.versawork.http.cacheClientsInfo.ClientCacheSettingsCheck;
import com.versawork.http.cacheDataObject.Cache2Object;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.constant.CacheConstants;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.AccountServiceDAO;
import com.versawork.http.dao.GetVisitServiceDAO;
import com.versawork.http.dao.PatientVerificationServiceDAO;
import com.versawork.http.dataobject.NsPatientVisit;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.etlCachePopulation.VisitCachePopulation;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.LinkedAccountsRel;
import com.versawork.http.model.PatientVerification;
import com.versawork.http.model.PatientVisit;
import com.versawork.http.service.GetVisitListService;
import com.versawork.http.service.LinkAccountService;
import com.versawork.http.utils.CacheKeys;
import com.versawork.http.utils.ResponseInfoObject;

/**
 * @author Dheeraj
 *
 * @author Sohaib
 *
 */
@Component
public class GetVisitListServiceImpl implements GetVisitListService {

	final static Logger LOGGER = LoggerFactory.getLogger(GetVisitListServiceImpl.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	protected ClientCacheSettingsCheck cCS;
	
	@Autowired
	protected PatientVerificationServiceDAO patientVerificationDAO;
	
	@Autowired
	private AccountServiceDAO accountServiceDAO;
	@Autowired
	private CacheAccessbyJson cache;

	@Autowired
	private GetVisitServiceDAO getVisitServiceDAO;

	@Autowired
	private VisitCachePopulation visitCachePopulation;
	
	@Autowired 
	private LinkAccountService linkAccountServiceImpl;
	 
	@Autowired
	private MessageSource messageSource;

	/*
	 * private static final String CLIENT_DATABASE_ID = "client.database.id";
	 */

	@Override
	public NsResponse getVisitList(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		
		ResponseData responsedata = new ResponseData();
		Integer accountId = nsRequest.getAccountInfo().getAccountId();
		List<NsPatientVisit> nsPatientVisits =  new ArrayList<NsPatientVisit>();
		List<NsPatientVisit> tempPatientVisits =  new ArrayList<NsPatientVisit>();
		List<LinkedAccountsRel> linkedAccounts = linkAccountServiceImpl.getLinkedAccounts(accountId);
		
		tempPatientVisits = fetchAccountVisits(accountId);
		nsPatientVisits.addAll(tempPatientVisits);
		
		for(LinkedAccountsRel linkedAccountsRel:linkedAccounts)
		{
			try{
			tempPatientVisits = fetchAccountVisits(linkedAccountsRel.getLinkedAccountId());
			nsPatientVisits.addAll(tempPatientVisits);
			}catch(BusinessException e){
				LOGGER.debug("No Patient Visit found for linkAccount No :"+linkedAccountsRel);
			}
		}
		
		if(nsPatientVisits.isEmpty() || nsPatientVisits.size() == 0)
		{
			responsedata.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
			responsedata.setDescription(messageSource.getMessage("no.visits.present", null,	nsRequest.getLocale()));
		}
		else
		{
		responsedata.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		responsedata.setDescription(VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS);
		}
		nsResponse.setResponseData(responsedata);
		nsResponse.setPatientVisitList(nsPatientVisits);
		return nsResponse;

	}

	private List<NsPatientVisit>  fetchAccountVisits(Integer accountId) throws BusinessException, SystemException {
		// Cache Starts
		List<NsPatientVisit> nsPatientVisits =null;
		Integer clientDBId = Integer.parseInt(messageSource.getMessage("client.database.id", null, Locale.getDefault()));
		Boolean isCachingOn = false;

		isCachingOn = cCS.isCachingOn(CacheConstants.PATIENT_VISIT.getCode());

		Type type = new TypeToken<List<NsPatientVisit>>() {}.getType();
		String key = CacheKeys.getPatientVisitKey(clientDBId, accountId);
		Cache2Object<NsPatientVisit> obj = new Cache2Object<NsPatientVisit>(cCS, cache);

		nsPatientVisits = obj.getObject(key, isCachingOn, type);

		 nsPatientVisits = new ArrayList<NsPatientVisit>();

		if ( nsPatientVisits == null || nsPatientVisits.isEmpty()) {
			List<PatientVisit> patientVisitsByAccount = getVisitServiceDAO.getPatientVisitActId(accountId);

			nsPatientVisits = new ArrayList<NsPatientVisit>();
			PatientVerification patientVerification = null;
			for (PatientVisit patientVisit : patientVisitsByAccount) {
				if(patientVerification == null)
				{
						try
					{
					patientVerification =	patientVerificationDAO.getPatientVerification(patientVisit.getPatientVisitPK().getAccountId());
					}catch(Exception e)
					{
						LOGGER.debug("!!!!!!!!!! Patient Verification entry not found for given linked account");
					}
				}
				NsPatientVisit nsPatientVisit = ResponseInfoObject.getNsPatientVisit(patientVisit, patientVerification);

				nsPatientVisits.add(nsPatientVisit);
				
			}
			
			if(nsPatientVisits.size()!=0 && !nsPatientVisits.isEmpty())
			{
			obj.setObject(key, isCachingOn, nsPatientVisits, type);
			}
		}
	return 	nsPatientVisits;
	}

	/*
	 * private List<PatientVisit> getCachedVisitLists(Integer accountId) throws
	 * BusinessException, SystemException { List<PatientVisit> patientMuData =
	 * new ArrayList<PatientVisit>(); String key =
	 * CacheConstants.PATIENT_VISIT.getCode(); String json_res = "null";
	 * GsonBuilder builder = new GsonBuilder(); builder.serializeNulls(); Gson
	 * gson = builder.create(); Boolean isCachingOn = true; Type listType = new
	 * TypeToken<List<CPatientVisit>>() { }.getType();
	 * 
	 * if (isCachingOn) { try { key = key + "_" + accountId; json_res =
	 * cache.fetchCacheData(key); if(json_res == null) json_res= "null"; } catch
	 * (Exception e) { isCachingOn = false; cCS.sendCacheMail(false); return
	 * null; } } if (json_res.equalsIgnoreCase("error")) { isCachingOn = false;
	 * cCS.sendCacheMail(false); } if (json_res != null &&
	 * !json_res.equalsIgnoreCase("null") && isCachingOn) {
	 * 
	 * List<CPatientVisit> cPatientVisit = new ArrayList<CPatientVisit>();
	 * cPatientVisit = gson.fromJson(json_res, listType); for (CPatientVisit
	 * cPatientVisits : cPatientVisit) { PatientVisit fn = new PatientVisit();
	 * 
	 * PatientVisitPK patientVisitPK = new PatientVisitPK();
	 * patientVisitPK.setAccountId(cPatientVisits.getAccountId());
	 * fn.setPatientVisitPK(patientVisitPK);
	 * 
	 * // fn.setAccountId( cPatientVisits.getAccountId());
	 * fn.setBirthDate(cPatientVisits.getBirthDate());
	 * fn.setDateAdded(cPatientVisits.getDateAdded());
	 * fn.setEncounterEndDate(cPatientVisits.getEncounterEndDate());
	 * fn.setEncounterStartDate(cPatientVisits.getEncounterStartDate());
	 * fn.setEthnicity(cPatientVisits.getEthnicity());
	 * fn.setEthnicityCode(cPatientVisits.getEthnicityCode());
	 * fn.setFirstName(cPatientVisits.getFirstName());
	 * fn.setLastName(cPatientVisits.getLastName());
	 * //fn.setPatientCarePlanList(
	 * getPatientCarePlanList(accountId,cPatientVisits.getPatientVisitId()));
	 * //fn
	 * .setPatientCareTeamList(getPatientCareTeamList(accountId,cPatientVisits
	 * .getPatientVisitId()));
	 * //fn.setPatientDiagnosisList(getPatientDiagnosisList
	 * (accountId,cPatientVisits.getPatientVisitId()));
	 * //fn.setPatientFunctionalStatusList
	 * (getPatientFunctionalStatusList(accountId
	 * ,cPatientVisits.getPatientVisitId()));
	 * //fn.setPatientImmunizationList(getPatientImmunizationList
	 * (accountId,cPatientVisits.getPatientVisitId()));
	 * //fn.setPatientLabList(getPatientLabList(accountId));
	 * //fn.setPatientMedicationList(getPatientMedicationList(accountId));
	 * //fn.setPatientProcedureList
	 * (getPatientProcedureList(accountId,cPatientVisits.getPatientVisitId()));
	 * //fn.setPatientVisitInpatientList(getPatientVisitInpatientList(accountId,
	 * cPatientVisits.getPatientVisitId()));
	 * //fn.setPatientVitalSignList(getPatientVitalSignList
	 * (accountId,cPatientVisits.getPatientVisitId())); // PatientVisitPK
	 * patientVisitPK = new PatientVisitPK();
	 * patientVisitPK.setPatientVisitId(cPatientVisits.getPatientVisitId());
	 * fn.setPatientVisitPK(patientVisitPK);
	 * //fn.setPatientVisitId(cPatientVisits.getPatientVisitId());
	 * fn.setPreferredLanguage(cPatientVisits.getPreferredLanguage());
	 * fn.setRace(cPatientVisits.getRace());
	 * fn.setRaceCode(cPatientVisits.getRaceCode());
	 * fn.setSex(cPatientVisits.getSex());
	 * fn.setSmokingStatus(cPatientVisits.getSmokingStatus());
	 * fn.setSmokingStatusCode(cPatientVisits.getSmokingStatusCode());
	 * fn.setSourceId(cPatientVisits.getSourceId());
	 * fn.setSourceName(cPatientVisits.getSourceName());
	 * fn.setVisitDate(cPatientVisits.getVisitDate());
	 * fn.setVisitIdentifier(cPatientVisits.getVisitIdentifier());
	 * fn.setVisitTypeId(cPatientVisits.getVisitTypeId());
	 * fn.setProviderName(cPatientVisits.getProviderName());
	 * patientMuData.add(fn); } } return patientMuData; }
	 */

	/*
	 * private List<PatientVitalSign> getPatientVitalSignList(Integer accountId,
	 * Integer patientVisitId) throws BusinessException, SystemException {
	 * String key = "PVD"; String json_res = "null"; GsonBuilder builder = new
	 * GsonBuilder(); builder.serializeNulls(); Gson gson = builder.create();
	 * Boolean isCachingOn = cCS.isCachingOn(key); List<PatientVitalSign>
	 * patientVisitInpatientList = new ArrayList<PatientVitalSign>(); Type
	 * listType = new TypeToken<List<CPatientVitalSign>>() { }.getType(); try {
	 * key = key+"_PVS_" + accountId.toString()+"_"+patientVisitId.toString();
	 * json_res = cache.fetchCacheData(key); if(json_res == null) json_res=
	 * "null"; } catch (Exception e) { isCachingOn = false;
	 * cCS.sendCacheMail(false); } if (json_res != null &&
	 * !json_res.equalsIgnoreCase("null") && isCachingOn) {
	 * 
	 * List<CPatientVitalSign> cPatientVitalSignList = new
	 * ArrayList<CPatientVitalSign>(); cPatientVitalSignList =
	 * gson.fromJson(json_res, listType); for (CPatientVitalSign
	 * cPatientVitalSigns : cPatientVitalSignList) { PatientVitalSign fn = new
	 * PatientVitalSign(); PatientVitalSignPK fnP = new PatientVitalSignPK();
	 * fn.setDateAdded(cPatientVitalSigns.getDateAdded());
	 * fn.setBmi(cPatientVitalSigns.getBmi());
	 * fn.setDiastolicBp(cPatientVitalSigns.getDiastolicBp());
	 * fn.setDiastolicBpUnit(cPatientVitalSigns.getDiastolicBpUnit());
	 * fn.setHeightFeet(cPatientVitalSigns.getHeight());
	 * fn.setHeightInches(cPatientVitalSigns.getHeightUnit());
	 * fn.setSystolicBp(cPatientVitalSigns.getSystolicBp());
	 * fn.setSystolicBpUnit(cPatientVitalSigns.getSystolicBpUnit());
	 * fn.setWeightLbs(cPatientVitalSigns.getWeight());
	 * //fn.setWeightUnit(cPatientVitalSigns.getWeightUnit());
	 * fn.setPatientVisit(new
	 * PatientVisit(cPatientVitalSigns.getPatientVisitId()));
	 * fn.setSourceId(cPatientVitalSigns.getSourceId());
	 * fn.setSourceName(cPatientVitalSigns.getSourceName());
	 * fnP.setAccountId(cPatientVitalSigns.getAccountId());
	 * fnP.setPatientVisitId(cPatientVitalSigns.getAccountId());
	 * fn.setPatientVitalSignPK(fnP); patientVisitInpatientList.add(fn); } }
	 * return patientVisitInpatientList; }
	 * 
	 * private List<PatientVisitInpatient> getPatientVisitInpatientList( Integer
	 * accountId, Integer patientVisitId) throws BusinessException,
	 * SystemException { String key = "PVD"; String json_res = "null";
	 * GsonBuilder builder = new GsonBuilder(); builder.serializeNulls(); Gson
	 * gson = builder.create(); Boolean isCachingOn = cCS.isCachingOn(key);
	 * List<PatientVisitInpatient> patientVisitInpatientList = new
	 * ArrayList<PatientVisitInpatient>(); Type listType = new
	 * TypeToken<List<CPatientVisitInpatient>>() { }.getType(); try { key =
	 * key+"_PVI_" + accountId.toString()+"_"+patientVisitId.toString();
	 * json_res = cache.fetchCacheData(key); if(json_res == null)
	 * json_res="null"; } catch (Exception e) { isCachingOn = false;
	 * cCS.sendCacheMail(false); } if (json_res != null &&
	 * !json_res.equalsIgnoreCase("null") && isCachingOn) {
	 * 
	 * List<CPatientVisitInpatient> cPatientVisitInpatient = new
	 * ArrayList<CPatientVisitInpatient>(); cPatientVisitInpatient =
	 * gson.fromJson(json_res, listType); for (CPatientVisitInpatient
	 * cPatientVisitInpatientS : cPatientVisitInpatient) { PatientVisitInpatient
	 * fn = new PatientVisitInpatient(); PatientVisitInpatientPK fnP = new
	 * PatientVisitInpatientPK();
	 * fn.setDateAdded(cPatientVisitInpatientS.getDateAdded());
	 * fn.setAdmitLocation(cPatientVisitInpatientS.getAdmitLocation());
	 * fn.setDischargeInstruction
	 * (cPatientVisitInpatientS.getDischargeInstruction());
	 * fn.setDischargeLocation(cPatientVisitInpatientS.getDischargeLocation());
	 * fn.setReasonForHospitalization(cPatientVisitInpatientS.
	 * getReasonForHospitalization()); fn.setPatientVisit(new
	 * PatientVisit(cPatientVisitInpatientS.getPatientVisitId()));
	 * fn.setSourceId(cPatientVisitInpatientS.getSourceId());
	 * fn.setSourceName(cPatientVisitInpatientS.getSourceName());
	 * fnP.setAccountId(cPatientVisitInpatientS.getAccountId());
	 * fnP.setPatientVisitId(cPatientVisitInpatientS.getAccountId());
	 * fn.setPatientVisitInpatientPK(fnP); patientVisitInpatientList.add(fn); }
	 * } return patientVisitInpatientList; } /* private List<PatientProcedure>
	 * getPatientProcedureList(Integer accountId, Integer patientVisitId) throws
	 * BusinessException, SystemException { String key = "PVD"; String json_res
	 * = "null"; GsonBuilder builder = new GsonBuilder();
	 * builder.serializeNulls(); Gson gson = builder.create(); Boolean
	 * isCachingOn = cCS.isCachingOn(key); List<PatientProcedure>
	 * patientProcedureList = new ArrayList<PatientProcedure>(); Type listType =
	 * new TypeToken<List<CPatientProcedure>>() { }.getType(); try { key =
	 * key+"_PPL_" + accountId.toString()+"_"+patientVisitId.toString();
	 * json_res = cache.fetchCacheData(key); if(json_res == null) json_res=
	 * "null"; } catch (Exception e) { isCachingOn = false;
	 * cCS.sendCacheMail(false); } if (json_res != null &&
	 * !json_res.equalsIgnoreCase("null") && isCachingOn) {
	 * 
	 * List<CPatientProcedure> cPatientProcedure = new
	 * ArrayList<CPatientProcedure>(); cPatientProcedure =
	 * gson.fromJson(json_res, listType); for (CPatientProcedure
	 * cPatientProcedureS : cPatientProcedure) { PatientProcedure fn = new
	 * PatientProcedure(); PatientProcedurePK fnP = new PatientProcedurePK();
	 * fn.setDateAdded(cPatientProcedureS.getDateAdded());
	 * fn.setPatientVisit(new
	 * PatientVisit(cPatientProcedureS.getPatientVisitId()));
	 * fn.setProcedureCode(cPatientProcedureS.getProcedureCode());
	 * fn.setProcedureName(cPatientProcedureS.getProcedureName());
	 * fn.setSourceId(cPatientProcedureS.getSourceId());
	 * fn.setProcedureDate(cPatientProcedureS.getProcedureDate());
	 * fn.setSourceName(cPatientProcedureS.getSourceName());
	 * fnP.setAccountId(cPatientProcedureS.getAccountId());
	 * fnP.setPatientVisitId(cPatientProcedureS.getAccountId());
	 * fnP.setProcedureId(cPatientProcedureS.getProcedureId());
	 * fnP.setProcedureId2(cPatientProcedureS.getProcedureId2());
	 * fn.setPatientProcedurePK(fnP); patientProcedureList.add(fn); } } return
	 * patientProcedureList; }
	 * 
	 * private List<PatientPrescription> getPatientMedicationList(Integer
	 * accountId) throws BusinessException, SystemException { String key = "PM";
	 * String json_res = "null"; GsonBuilder builder = new GsonBuilder();
	 * builder.serializeNulls(); Gson gson = builder.create(); Boolean
	 * isCachingOn = cCS.isCachingOn(key); List<PatientPrescription>
	 * patientPrescriptions = new ArrayList<PatientPrescription>(); Type
	 * listType = new TypeToken<List<CPatientPrescription>>() { }.getType(); try
	 * { key = key + "_"+accountId.toString(); json_res =
	 * cache.fetchCacheData(key); if(json_res == null) json_res= "null"; } catch
	 * (Exception e) { isCachingOn = false; cCS.sendCacheMail(false); } if
	 * (json_res != null && !json_res.equalsIgnoreCase("null") && isCachingOn) {
	 * List<CPatientPrescription> cPatientPescription = new
	 * ArrayList<CPatientPrescription>(); cPatientPescription =
	 * gson.fromJson(json_res, listType); for (CPatientPrescription
	 * cPatientPescriptions : cPatientPescription) { PatientPrescription fn =
	 * new PatientPrescription(); PatientPrescriptionPK fnP = new
	 * PatientPrescriptionPK();
	 * fn.setDateAdded(cPatientPescriptions.getDateAdded());
	 * fn.setFrequency(cPatientPescriptions.getFrequency());
	 * fn.setDoseStrength(cPatientPescriptions.getDoseStrength());
	 * fn.setDosageDescription(cPatientPescriptions.getDosageDescription());
	 * fn.setDoseQuantity(cPatientPescriptions.getDoseQuantity());
	 * fn.setEndDate(cPatientPescriptions.getEndDate());
	 * fn.setMedicationName(cPatientPescriptions.getMedicationName());
	 * fn.setRouteOfAdministration
	 * (cPatientPescriptions.getRouteOfAdministration());
	 * fn.setRouteOfAdministrationCode
	 * (cPatientPescriptions.getRouteOfAdministrationCode());
	 * fn.setRxNumber(cPatientPescriptions.getRxNumber());
	 * fn.setSourceId(cPatientPescriptions.getSourceId());
	 * fn.setSourceName(cPatientPescriptions.getSourceName());
	 * fn.setStartDate(cPatientPescriptions.getStartDate());
	 * fn.setStatus(cPatientPescriptions.getStatus());
	 * fn.setMedicationGenericName
	 * (cPatientPescriptions.getMedicationGenericName());
	 * fn.setMedicationBrandName(cPatientPescriptions.getMedicationBrandName());
	 * fn.setMedicationStrength(cPatientPescriptions.getMedicationStrength());
	 * fn.setUnit(cPatientPescriptions.getUnit());
	 * fn.setDirection(cPatientPescriptions.getDirection());
	 * fnP.setAccountId(cPatientPescriptions.getAccountId());
	 * fnP.setMedicationId(cPatientPescriptions.getMedicationId());
	 * fnP.setPatientVisitId(cPatientPescriptions.getPatientVisitId());
	 * fn.setPatientPrescriptionPK(fnP); patientPrescriptions.add(fn); } }
	 * return patientPrescriptions; } /* private List<PatientLab>
	 * getPatientLabList(Integer accountId) throws BusinessException,
	 * SystemException { String key = "PLAB"; String json_res = "null";
	 * GsonBuilder builder = new GsonBuilder(); builder.serializeNulls(); Gson
	 * gson = builder.create(); Boolean isCachingOn = cCS.isCachingOn(key);
	 * List<PatientLab> patientLabs = new ArrayList<PatientLab>(); Type listType
	 * = new TypeToken<List<CPatientLab>>() { }.getType(); try { key = key +
	 * "_"+accountId; json_res = cache.fetchCacheData(key); if(json_res == null)
	 * json_res= "null"; } catch (Exception e) { isCachingOn = false;
	 * cCS.sendCacheMail(false); } if (json_res != null &&
	 * !json_res.equalsIgnoreCase("null") && isCachingOn) {
	 * 
	 * List<CPatientLab> cPatientLab = new ArrayList<CPatientLab>(); cPatientLab
	 * = gson.fromJson(json_res, listType); for (CPatientLab cPatientLabs :
	 * cPatientLab) { PatientLab fn = new PatientLab(); PatientLabPK fnP = new
	 * PatientLabPK(); fn.setDateAdded(cPatientLabs.getDateAdded());
	 * fn.setInterpretationCode(cPatientLabs .getInterpretationCode());
	 * fn.setLabCode(cPatientLabs.getLabCode());
	 * fn.setLabResult(cPatientLabs.getLabResult());
	 * fn.setLabUnit(cPatientLabs.getLabUnit());
	 * fn.setOrganizerCode(cPatientLabs.getOrganizerCode());
	 * fn.setOrganizerName(cPatientLabs.getOrganizerName());
	 * fn.setResultDate(cPatientLabs.getResultDate());
	 * fn.setSourceId(cPatientLabs.getSourceId());
	 * fn.setSourceName(cPatientLabs.getSourceName());
	 * fn.setTestName(cPatientLabs.getTestName());
	 * fn.setNormalRange(cPatientLabs.getNormalRange());
	 * fn.setAbnormalFlag(cPatientLabs.getAbnormalFlag());
	 * fn.setLabGroupCode(cPatientLabs.getLabGroupCode());
	 * fn.setLabGroupName(cPatientLabs.getLabGroupName());
	 * fn.setLabGroupDate(cPatientLabs.getLabGroupDate());
	 * fnP.setLabGroupId(cPatientLabs.getLabGroupId());
	 * fnP.setAccountId(cPatientLabs.getAccountId());
	 * fnP.setLabId(cPatientLabs.getLabId());
	 * fnP.setPatientVisitId(cPatientLabs.getPatientVisitId());
	 * fnP.setTestId(cPatientLabs.getTestId()); fn.setPatientLabPK(fnP);
	 * patientLabs.add(fn); } } return patientLabs; }
	 * 
	 * private List<PatientImmunization> getPatientImmunizationList( Integer
	 * accountId, Integer patientVisitId) throws BusinessException,
	 * SystemException { String key = "PVD"; String json_res = "null";
	 * GsonBuilder builder = new GsonBuilder(); builder.serializeNulls(); Gson
	 * gson = builder.create(); Boolean isCachingOn = cCS.isCachingOn(key);
	 * List<PatientImmunization> patientImmunizationList = new
	 * ArrayList<PatientImmunization>(); Type listType = new
	 * TypeToken<List<CPatientImmunization>>() { }.getType(); try { key =
	 * key+"_PIL_" + accountId.toString()+"_"+patientVisitId.toString();
	 * json_res = cache.fetchCacheData(key); if(json_res == null) json_res=
	 * "null"; } catch (Exception e) { isCachingOn = false;
	 * cCS.sendCacheMail(false); } if (json_res != null &&
	 * !json_res.equalsIgnoreCase("null") && isCachingOn) {
	 * 
	 * List<CPatientImmunization> cPatientImmunization = new
	 * ArrayList<CPatientImmunization>(); cPatientImmunization =
	 * gson.fromJson(json_res, listType); for (CPatientImmunization
	 * cPatientImmunizations : cPatientImmunization) { PatientImmunization fn =
	 * new PatientImmunization(); PatientImmunizationPK fnP = new
	 * PatientImmunizationPK();
	 * fn.setDateAdded(cPatientImmunizations.getDateAdded());
	 * fn.setImmunizationCode(cPatientImmunizations.getImmunizationCode());
	 * fn.setImmunizationName(cPatientImmunizations.getImmunizationName());
	 * fn.setPatientVisit(new
	 * PatientVisit(cPatientImmunizations.getPatientVisitId()));
	 * fn.setRouteCode(cPatientImmunizations.getRouteCode());
	 * fn.setStatusDate(cPatientImmunizations.getStatusDate());
	 * fn.setRouteName(cPatientImmunizations.getRouteName());
	 * fn.setSourceId(cPatientImmunizations.getSourceId());
	 * fn.setSourceName(cPatientImmunizations.getSourceName());
	 * fn.setStatus(cPatientImmunizations.getStatus());
	 * fnP.setAccountId(cPatientImmunizations.getAccountId());
	 * fnP.setImmunizationId(cPatientImmunizations.getImmunizationId());
	 * fnP.setPatientVisitId(cPatientImmunizations.getPatientVisitId());
	 * fn.setPatientImmunizationPK(fnP); patientImmunizationList.add(fn); } }
	 * return patientImmunizationList; }
	 * 
	 * private List<PatientFunctionalStatus> getPatientFunctionalStatusList(
	 * Integer accountId, Integer patientVisitId) throws BusinessException,
	 * SystemException { String key = "PVD"; String json_res = "null";
	 * GsonBuilder builder = new GsonBuilder(); builder.serializeNulls(); Gson
	 * gson = builder.create(); Boolean isCachingOn = cCS.isCachingOn(key);
	 * List<PatientFunctionalStatus> patientDaignosisList = new
	 * ArrayList<PatientFunctionalStatus>(); Type listType = new
	 * TypeToken<List<CPatientFunctionalStatus>>() { }.getType(); try { key =
	 * key+"_PFS_" + accountId.toString()+"_"+patientVisitId.toString();
	 * json_res = cache.fetchCacheData(key); if(json_res == null) json_res=
	 * "null"; } catch (Exception e) { isCachingOn = false;
	 * cCS.sendCacheMail(false); } if (json_res != null &&
	 * !json_res.equalsIgnoreCase("null") && isCachingOn) {
	 * 
	 * List<CPatientFunctionalStatus> cPatientFunctionalStatus = new
	 * ArrayList<CPatientFunctionalStatus>(); cPatientFunctionalStatus =
	 * gson.fromJson(json_res, listType); for (CPatientFunctionalStatus
	 * cPatientFunctionalStatusS : cPatientFunctionalStatus) {
	 * PatientFunctionalStatus fn = new PatientFunctionalStatus();
	 * PatientFunctionalStatusPK fnP = new PatientFunctionalStatusPK();
	 * fn.setDateAdded(cPatientFunctionalStatusS.getDateAdded());
	 * fn.setFunctionDescription
	 * (cPatientFunctionalStatusS.getFunctionDescription());
	 * fn.setPatientVisit(new
	 * PatientVisit(cPatientFunctionalStatusS.getPatientVisitId()));
	 * fn.setSourceId(cPatientFunctionalStatusS.getSourceId());
	 * fn.setSourceName(cPatientFunctionalStatusS.getSourceName());
	 * fn.setStatusDate(cPatientFunctionalStatusS.getStatusDate());
	 * fn.setStatus(cPatientFunctionalStatusS.getStatus());
	 * fn.setStatusCode(cPatientFunctionalStatusS.getStatusCode());
	 * fn.setFunctionCode(cPatientFunctionalStatusS.getFunctionCode());
	 * fnP.setAccountId(cPatientFunctionalStatusS.getAccountId());
	 * fnP.setFunctionId(cPatientFunctionalStatusS.getFunctionId());
	 * fnP.setPatientVisitId(cPatientFunctionalStatusS.getPatientVisitId());
	 * fnP.setFunctionId2(cPatientFunctionalStatusS.getFunctionId2());
	 * fn.setPatientFunctionalStatusPK(fnP); patientDaignosisList.add(fn); } }
	 * return patientDaignosisList; }
	 * 
	 * @SuppressWarnings("unused") private List<PatientFeedback>
	 * getPatientFeedbackList(Integer accountId, Integer patientVisitId) throws
	 * BusinessException, SystemException { String key = "PVD"; String json_res
	 * = "null"; GsonBuilder builder = new GsonBuilder();
	 * builder.serializeNulls(); Gson gson = builder.create(); Boolean
	 * isCachingOn = cCS.isCachingOn(key); List<PatientFeedback>
	 * patientFeedbackList = new ArrayList<PatientFeedback>(); Type listType =
	 * new TypeToken<List<CPatientFeedback>>() { }.getType(); try { key =
	 * key+"_PFL_" + accountId.toString()+"_"+patientVisitId.toString();
	 * json_res = cache.fetchCacheData(key); if(json_res == null) json_res=
	 * "null"; } catch (Exception e) { isCachingOn = false;
	 * cCS.sendCacheMail(false); } if (json_res != null &&
	 * !json_res.equalsIgnoreCase("null") && isCachingOn) {
	 * 
	 * List<CPatientFeedback> patientFeedback = new
	 * ArrayList<CPatientFeedback>(); patientFeedback = gson.fromJson(json_res,
	 * listType); for (CPatientFeedback cPatientFeedbacks : patientFeedback) {
	 * PatientFeedback fn = new PatientFeedback();
	 * fn.setDateAdded(cPatientFeedbacks.getDateAdded());
	 * fn.setComment(cPatientFeedbacks.getComment());
	 * fn.setLastViewedByAcountId(cPatientFeedbacks.getLastViewedByAcountId());
	 * fn.setPatientFeedbackId(cPatientFeedbacks.getPatientFeedbackId());
	 * fn.setPatientVisitId
	 * (cPatientFeedbacks.getPatientVisitId().getPatientVisitId());
	 * fn.setRecoveryRating(cPatientFeedbacks.getRecoveryRating());
	 * fn.setVisitRating(cPatientFeedbacks.getVisitRating());
	 * patientFeedbackList.add(fn); } } return patientFeedbackList; }
	 * 
	 * private List<PatientDiagnosis> getPatientDiagnosisList(Integer accountId,
	 * Integer patientVisitId) throws BusinessException, SystemException {
	 * String key = "PVD"; String json_res = "null"; GsonBuilder builder = new
	 * GsonBuilder(); builder.serializeNulls(); Gson gson = builder.create();
	 * Boolean isCachingOn = cCS.isCachingOn(key); List<PatientDiagnosis>
	 * patientDaignosisList = new ArrayList<PatientDiagnosis>(); Type listType =
	 * new TypeToken<List<CPatientDiagnosis>>() { }.getType(); try { key =
	 * key+"_PDL_" + accountId.toString()+"_"+patientVisitId.toString();
	 * json_res = cache.fetchCacheData(key); if(json_res == null) json_res=
	 * "null"; } catch (Exception e) { isCachingOn = false;
	 * cCS.sendCacheMail(false); } if (json_res != null &&
	 * !json_res.equalsIgnoreCase("null") && isCachingOn) {
	 * 
	 * List<CPatientDiagnosis> cPatientDiagnosisL = new
	 * ArrayList<CPatientDiagnosis>(); cPatientDiagnosisL =
	 * gson.fromJson(json_res, listType); for (CPatientDiagnosis
	 * cPatientDiagnosisLs : cPatientDiagnosisL) { PatientDiagnosis fn = new
	 * PatientDiagnosis(); PatientDiagnosisPK fnP = new PatientDiagnosisPK();
	 * fn.setDateAdded(cPatientDiagnosisLs.getDateAdded());
	 * fn.setDiagnosisName(cPatientDiagnosisLs.getDiagnosisName());
	 * fn.setPatientVisit(new
	 * PatientVisit(cPatientDiagnosisLs.getPatientVisitId()));
	 * fn.setSourceId(cPatientDiagnosisLs.getSourceId());
	 * fn.setSourceName(cPatientDiagnosisLs.getSourceName());
	 * fn.setStatus(cPatientDiagnosisLs.getStatus());
	 * fn.setDiagnosisSeqId(cPatientDiagnosisLs.getDiagnosisSeqId());
	 * fnP.setAccountId(cPatientDiagnosisLs.getAccountId());
	 * fnP.setDiagnosisCode(cPatientDiagnosisLs.getDiagnosisCode());
	 * fnP.setPatientVisitId(cPatientDiagnosisLs.getPatientVisitId());
	 * fn.setPatientDiagnosisPK(fnP); patientDaignosisList.add(fn); } } return
	 * patientDaignosisList; }
	 * 
	 * private List<PatientCareTeam> getPatientCareTeamList(Integer accountId,
	 * Integer patientVisitId) throws BusinessException, SystemException {
	 * String key = "PVD"; String json_res = "null"; GsonBuilder builder = new
	 * GsonBuilder(); builder.serializeNulls(); Gson gson = builder.create();
	 * Boolean isCachingOn = cCS.isCachingOn(key); List<PatientCareTeam>
	 * patientCareTeamList = new ArrayList<PatientCareTeam>(); Type listType =
	 * new TypeToken<List<CPatientCareTeam>>() { }.getType(); try { key =
	 * key+"_PCT_" + accountId.toString()+"_"+patientVisitId.toString();
	 * json_res = cache.fetchCacheData(key); if(json_res == null) json_res=
	 * "null"; } catch (Exception e) { isCachingOn = false;
	 * cCS.sendCacheMail(false); } if (json_res != null &&
	 * !json_res.equalsIgnoreCase("null") && isCachingOn) {
	 * 
	 * List<CPatientCareTeam> cPatientCareTeam = new
	 * ArrayList<CPatientCareTeam>(); cPatientCareTeam = gson.fromJson(json_res,
	 * listType); for (CPatientCareTeam cPatientCareTeams : cPatientCareTeam) {
	 * PatientCareTeam fn = new PatientCareTeam(); PatientCareTeamPK fnP = new
	 * PatientCareTeamPK(); fn.setAddress1(cPatientCareTeams.getAddress1());
	 * fn.setAddress2(cPatientCareTeams.getAddress2());
	 * fn.setCareMemberEmail(cPatientCareTeams.getCareMemberEmail());
	 * fn.setCareMemberSpeciality(cPatientCareTeams.getCareMemberSpeciality());
	 * fnP.setCareMemberId(cPatientCareTeams.getCareMemberId());
	 * fn.setCareMemberName(cPatientCareTeams.getCareMemberName());
	 * fnP.setCareMemberRoleId(cPatientCareTeams.getCareMemberRoleId());
	 * fn.setCity(cPatientCareTeams.getCity());
	 * fn.setCountry(cPatientCareTeams.getCountry());
	 * fn.setDateAdded(cPatientCareTeams.getDateAdded()); fn.setPatientVisit(new
	 * PatientVisit(cPatientCareTeams.getPatientVisitId()));
	 * fn.setPhoneNumber(cPatientCareTeams.getPhoneNumber());
	 * fn.setPostalCode(cPatientCareTeams.getPostalCode());
	 * fn.setSourceId(cPatientCareTeams.getSourceId());
	 * fn.setSourceName(cPatientCareTeams.getSourceName());
	 * fn.setState(cPatientCareTeams.getState());
	 * fnP.setPatientVisitId(cPatientCareTeams.getPatientVisitId());
	 * fnP.setAccountId(cPatientCareTeams.getAccountId());
	 * fn.setPatientCareTeamPK(fnP); patientCareTeamList.add(fn); } } return
	 * patientCareTeamList; }
	 * 
	 * 
	 * private List<PatientCarePlan> getPatientCarePlanList(Integer accountId,
	 * Integer patientVisitId) throws BusinessException, SystemException {
	 * String key = "PVD"; String json_res = "null"; GsonBuilder builder = new
	 * GsonBuilder(); builder.serializeNulls(); Gson gson = builder.create();
	 * Boolean isCachingOn = cCS.isCachingOn(key); List<PatientCarePlan>
	 * patientCarePlanList = new ArrayList<PatientCarePlan>(); Type listType =
	 * new TypeToken<List<CPatientCarePlan>>() { }.getType(); try { key =
	 * key+"_PCP_" + accountId.toString()+"_"+patientVisitId.toString();
	 * json_res = cache.fetchCacheData(key); if(json_res == null) json_res=
	 * "null"; } catch (Exception e) { isCachingOn = false;
	 * cCS.sendCacheMail(false); } if (json_res != null &&
	 * !json_res.equalsIgnoreCase("null") && isCachingOn) {
	 * 
	 * List<CPatientCarePlan> cPatientCarePlan = new
	 * ArrayList<CPatientCarePlan>(); cPatientCarePlan = gson.fromJson(json_res,
	 * listType); for (CPatientCarePlan cPatientCarePlans : cPatientCarePlan) {
	 * PatientCarePlan fn = new PatientCarePlan(); PatientCarePlanPK fnP = new
	 * PatientCarePlanPK(); fn.setDateAdded(cPatientCarePlans.getDateAdded());
	 * fn.setGoal(cPatientCarePlans.getGoal());
	 * fn.setInstructions(cPatientCarePlans.getInstructions());
	 * fn.setPatientVisit(new
	 * PatientVisit(cPatientCarePlans.getPatientVisitId()));
	 * fn.setSourceId(cPatientCarePlans.getSourceId());
	 * fn.setSourceName(cPatientCarePlans.getSourceName());
	 * fnP.setAccountId(cPatientCarePlans.getAccountId());
	 * fnP.setCarePlanId(cPatientCarePlans.getCarePlanId());
	 * fnP.setPatientVisitId(cPatientCarePlans.getPatientVisitId());
	 * fn.setPatientCarePlanPK(fnP); patientCarePlanList.add(fn); } } return
	 * patientCarePlanList; }
	 */
}