package com.versawork.http.etlCachePopulation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.versawork.http.cacheDataObject.CFacilityNotice;
import com.versawork.http.cacheDataObject.CFacilityProvider;
import com.versawork.http.cacheDataObject.CFacilityService;
import com.versawork.http.cacheDataObject.CPatientAllergy;
import com.versawork.http.cacheDataObject.CPatientImaging;
import com.versawork.http.cacheDataObject.CPatientLab;
import com.versawork.http.cacheDataObject.CPatientPrescription;
import com.versawork.http.cacheDataObject.CPatientProblem;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.model.FacilityNotice;
import com.versawork.http.model.FacilityProvider;
import com.versawork.http.model.FacilityService;
import com.versawork.http.model.PatientAllergy;
import com.versawork.http.model.PatientImaging;
import com.versawork.http.model.PatientLab;
import com.versawork.http.model.PatientPrescription;
import com.versawork.http.model.PatientProblem;

/**
 * @author Sohaib
 * 
 *         Populates cache in redis for individual modules that are independent
 *         of visits
 */
@Component
public class CachePopulation {

	@Autowired
	private CacheAccessbyJson cache;

	public Gson getGsonObject() {
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls();
		Gson gson = builder.create();
		return gson;
	}

	public Boolean labcachePopulate(String key, List<PatientLab> patientLabs, Type listType) {
		List<CPatientLab> cPatientLabsList = new ArrayList<CPatientLab>();
		for (PatientLab patientLab : patientLabs) {
			CPatientLab fN = new CPatientLab();
			fN.setDateAdded(patientLab.getDateAdded());
			fN.setInterpretationCode(patientLab.getInterpretationCode());
			fN.setLabCode(patientLab.getLabCode());
			fN.setLabResult(patientLab.getLabResult());
			fN.setLabUnit(patientLab.getLabUnit());
			fN.setOrganizerCode(patientLab.getOrganizerCode());
			fN.setOrganizerName(patientLab.getOrganizerName());
			fN.setResultDate(patientLab.getResultDate());
			fN.setSourceId(patientLab.getSourceId());
			fN.setSourceName(patientLab.getSourceName());
			fN.setTestName(patientLab.getTestName());
			// fN.setNormalRange(patientLab.getNormalRange());
			fN.setNormalRangeMin(patientLab.getNormalRangeMin());
			fN.setNormalRangeMax(patientLab.getNormalRangeMax());
			fN.setAbsoluteRangeMin(patientLab.getAbsoluteRangeMin());
			fN.setAbsoluteRangeMax(patientLab.getAbsoluteRangeMax());
			fN.setAbnormalFlag(patientLab.getAbnormalFlag());
			fN.setLabGroupCode(patientLab.getLabGroupCode());
			fN.setLabGroupName(patientLab.getLabGroupName());
			fN.setLabGroupDate(patientLab.getLabGroupDate());
			fN.setLabGroupId(patientLab.getPatientLabPK().getLabGroupId());
			fN.setAccountId(patientLab.getPatientLabPK().getAccountId());
			fN.setLabId(patientLab.getPatientLabPK().getLabId());
			fN.setPatientVisitId(patientLab.getPatientLabPK().getPatientVisitId());
			fN.setTestId(patientLab.getPatientLabPK().getTestId());
			cPatientLabsList.add(fN);
		}
		String json = getGsonObject().toJson(cPatientLabsList, listType);
		return cache.setCacheData(key, json);
	}

	public Boolean prescriptionCachePopulate(String key, List<PatientPrescription> patientPrescriptions, Type listType)
			throws BusinessException {
		String json = "";
		try {
			List<CPatientPrescription> cPatientPescription = new ArrayList<CPatientPrescription>();
			for (PatientPrescription cPatientPescriptions : patientPrescriptions) {
				CPatientPrescription fN = new CPatientPrescription();
				fN.setDateAdded(cPatientPescriptions.getDateAdded());
				fN.setDosageDescription(cPatientPescriptions.getDosageDescription());
				fN.setDoseQuantity(cPatientPescriptions.getDoseQuantity());
				fN.setPrescriptionActionId(cPatientPescriptions.getPrescriptionActionId().getPrescriptionActionId());
				fN.setFrequency(cPatientPescriptions.getFrequency());
				fN.setPrescriptionAction(cPatientPescriptions.getPrescriptionActionId().getPrescriptionAction());
				fN.setEndDate(cPatientPescriptions.getEndDate());
				fN.setMedicationName(cPatientPescriptions.getMedicationName());
				fN.setRouteOfAdministration(cPatientPescriptions.getRouteOfAdministration());
				fN.setRouteOfAdministrationCode(cPatientPescriptions.getRouteOfAdministrationCode());
				fN.setRxNumber(cPatientPescriptions.getRxNumber());
				fN.setSourceId(cPatientPescriptions.getSourceId());
				fN.setSourceName(cPatientPescriptions.getSourceName());
				fN.setStartDate(cPatientPescriptions.getStartDate());
				fN.setStatus(cPatientPescriptions.getStatus());
				fN.setMedicationGenericName(cPatientPescriptions.getMedicationGenericName());
				fN.setMedicationBrandName(cPatientPescriptions.getMedicationBrandName());
				fN.setDoseStrength(cPatientPescriptions.getDoseStrength());
				fN.setUnit(cPatientPescriptions.getUnit());
				fN.setDirection(cPatientPescriptions.getDirection());
				fN.setAccountId(cPatientPescriptions.getPatientPrescriptionPK().getAccountId());
				fN.setMedicationId(cPatientPescriptions.getPatientPrescriptionPK().getMedicationId());
				fN.setPatientVisitId(cPatientPescriptions.getPatientPrescriptionPK().getPatientVisitId());
				fN.setEncounterStartDate(cPatientPescriptions.getPatientVisit().getEncounterStartDate());
				fN.setEncounterEndDate(cPatientPescriptions.getPatientVisit().getEncounterEndDate());
				cPatientPescription.add(fN);
			}
			json = getGsonObject().toJson(cPatientPescription, listType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return cache.setCacheData(key, json);
	}

	public Boolean imagingCachePopulate(String key, List<PatientImaging> patientImaging, Type listType) {
		List<CPatientImaging> cPatientImaging = new ArrayList<CPatientImaging>();
		for (PatientImaging cpatImaging : patientImaging) {
			CPatientImaging patImaging = new CPatientImaging();
			patImaging.setAccountId(cpatImaging.getPatientImagingPK().getAccountId());
			patImaging.setExamId(cpatImaging.getPatientImagingPK().getExamId());
			patImaging.setPatientVisitId(cpatImaging.getPatientImagingPK().getPatientVisitId());
			patImaging.setDateAdded(cpatImaging.getDateAdded());
			patImaging.setExamDate(cpatImaging.getExamDate());
			patImaging.setExamMessage(cpatImaging.getExamMessage());
			patImaging.setExamName(cpatImaging.getExamName());
			patImaging.setExamTechnologist(cpatImaging.getExamTechnologist());
			patImaging.setOrderingProvider(cpatImaging.getOrderingProvider());
			patImaging.setSourceId(cpatImaging.getSourceId());
			patImaging.setSourceName(cpatImaging.getSourceName());
			cPatientImaging.add(patImaging);
		}
		String json = getGsonObject().toJson(cPatientImaging, listType);// PatientLab.class);
		return cache.setCacheData(key, json);
	}

	public Boolean doctorsListCachePopulate(String key, List<FacilityProvider> healthcareProviders, Type listType) {
		List<CFacilityProvider> cFailityNotice = new ArrayList<CFacilityProvider>();
		for (FacilityProvider facilityProvider : healthcareProviders) {
			CFacilityProvider fN = new CFacilityProvider();
			fN.setAddress1(facilityProvider.getAddress1());
			fN.setAddress2(facilityProvider.getAddress2());
			fN.setCity(facilityProvider.getCity());
			fN.setContactEmail(facilityProvider.getContactEmail());
			fN.setContactPhoneExtension(facilityProvider.getContactPhoneExtension());
			fN.setContactPhoneNumber(facilityProvider.getContactPhoneNumber());
			fN.setDateAdded(facilityProvider.getDateAdded());
			fN.setDateModified(facilityProvider.getDateModified());
			fN.setFaxNumber(facilityProvider.getFaxNumber());
			fN.setFirstName(facilityProvider.getFirstName());
			fN.setLastName(facilityProvider.getLastName());
			fN.setPostalCode(facilityProvider.getPostalCode());
			fN.setSourceId(facilityProvider.getSourceId());
			fN.setSourceName(facilityProvider.getSourceName());
			fN.setSpecialty(facilityProvider.getSpecialty());
			fN.setState(facilityProvider.getState());
			fN.setSuffix(facilityProvider.getSuffix());
			fN.setConsultantPhysician(facilityProvider.getConsultantPhysician());
			fN.setResidentPhysician(facilityProvider.getResidentPhysician());
			fN.setClientDatabaseId(facilityProvider.getFacilityProviderPK().getClientDatabaseId());
			fN.setClientId(facilityProvider.getFacilityProviderPK().getClientId());
			fN.setProviderId(facilityProvider.getFacilityProviderPK().getProviderId());
			cFailityNotice.add(fN);
		}
		String json = getGsonObject().toJson(cFailityNotice, listType);// PatientLab.class);
		return cache.setCacheData(key, json);
	}

	public Boolean servicesListCachePopulate(String key, List<FacilityService> hospitalServices, Type listType) {
		List<CFacilityService> cFailityNotice = new ArrayList<CFacilityService>();
		for (FacilityService facilityService : hospitalServices) {
			CFacilityService fN = new CFacilityService();
			fN.setContactEmail(facilityService.getContactEmail());
			fN.setContactPhoneExtension(facilityService.getContactPhoneExtension());
			fN.setContactPhoneNumber(facilityService.getContactPhoneNumber());
			fN.setDateAdded(facilityService.getDateAdded());
			fN.setDateModified(facilityService.getDateModified());
			fN.setService(facilityService.getService());
			fN.setServiceGroup(facilityService.getServiceGroup());
			fN.setSourceId(facilityService.getSourceId());
			fN.setSourceName(facilityService.getSourceName());
			fN.setClientDatabaseId(facilityService.getFacilityServicePK().getClientDatabaseId());
			fN.setClientId(facilityService.getFacilityServicePK().getClientId());
			fN.setServiceId(facilityService.getFacilityServicePK().getServiceId());
			cFailityNotice.add(fN);
		}
		String json = getGsonObject().toJson(cFailityNotice, listType);// PatientLab.class);
		return cache.setCacheData(key, json);
	}

	public Boolean noticesListCachePopulate(String key, List<FacilityNotice> hospitalNotices, Type listType) {
		List<CFacilityNotice> cFailityNotice = new ArrayList<CFacilityNotice>();
		for (FacilityNotice cFacilityNotices : hospitalNotices) {
			CFacilityNotice fN = new CFacilityNotice();
			fN.setAccountRoleId(cFacilityNotices.getAccountRoleId());
			fN.setBeginDate(cFacilityNotices.getBeginDate());
			fN.setClientDatabaseId(cFacilityNotices.getFacilityNoticePK().getClientDatabaseId());
			fN.setClientId(cFacilityNotices.getFacilityNoticePK().getClientId());
			fN.setFacilityNoticeId(cFacilityNotices.getFacilityNoticePK().getFacilityNoticeId());
			fN.setDateAdded(cFacilityNotices.getDateAdded());
			fN.setEndDate(cFacilityNotices.getEndDate());
			fN.setNoticeMessage(cFacilityNotices.getNoticeMessage());
			cFailityNotice.add(fN);
		}
		String json = getGsonObject().toJson(cFailityNotice, listType);// PatientLab.class);
		return cache.setCacheData(key, json);
	}

	public Boolean allergycachePopulate(String key, List<PatientAllergy> patientAllergiesList, Type listType) {
		List<CPatientAllergy> cPatientAllergyList = new ArrayList<CPatientAllergy>();

		for (PatientAllergy cpatAllergy : patientAllergiesList) {
			CPatientAllergy patientAllergy = new CPatientAllergy();
			patientAllergy.setAccountId(cpatAllergy.getPatientAllergyPK().getAccountId());
			patientAllergy.setAllergyId(cpatAllergy.getPatientAllergyPK().getAllergyId());
			patientAllergy.setReaction(cpatAllergy.getReaction());
			patientAllergy.setAllergenCode(cpatAllergy.getAllergenCode());
			patientAllergy.setAllergenName(cpatAllergy.getAllergenName());
			patientAllergy.setDateAdded(cpatAllergy.getDateAdded());
			patientAllergy.setReactionCode(cpatAllergy.getReactionCode());
			patientAllergy.setSourceId(cpatAllergy.getSourceId());
			patientAllergy.setStatus(cpatAllergy.getStatus());
			patientAllergy.setSourceName(cpatAllergy.getSourceName());
			patientAllergy.setStatusCode(cpatAllergy.getStatusCode());
			cPatientAllergyList.add(patientAllergy);
		}
		String json = getGsonObject().toJson(cPatientAllergyList, listType);// PatientLab.class);
		return cache.setCacheData(key, json);

	}

	public Boolean problemcachePopulate(String key, List<PatientProblem> patientProblemList, Type listType) {
		List<CPatientProblem> cPatientProblemList = new ArrayList<CPatientProblem>();
		for (PatientProblem cPatientProblem : patientProblemList) {
			CPatientProblem patientProblem = new CPatientProblem();
			patientProblem.setAccountId(cPatientProblem.getPatientProblemPK().getAccountId());
			patientProblem.setProblemId(cPatientProblem.getPatientProblemPK().getProblemId());
			patientProblem.setDateAdded(cPatientProblem.getDateAdded());
			patientProblem.setProblemCode(cPatientProblem.getProblemCode());
			patientProblem.setProblemName(cPatientProblem.getProblemName());
			patientProblem.setSourceId(cPatientProblem.getSourceId());
			patientProblem.setSourceName(cPatientProblem.getSourceName());
			patientProblem.setStatus(cPatientProblem.getStatus());
			patientProblem.setStatusCode(cPatientProblem.getStatusCode());
			cPatientProblemList.add(patientProblem);
		}
		String json = getGsonObject().toJson(cPatientProblemList, listType);// PatientLab.class);
		return cache.setCacheData(key, json);
	}
}
