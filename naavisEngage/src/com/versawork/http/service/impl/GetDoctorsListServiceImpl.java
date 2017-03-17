package com.versawork.http.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.imagingserver.http.imaging.dataObjects.ImgResponse;
import com.imagingserver.http.imaging.serviceImpl.ImageServiceImpl;
import com.versawork.http.cacheClientsInfo.ClientCacheSettingsCheck;
import com.versawork.http.cacheDataObject.Cache2Object;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.constant.CacheConstants;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.GetDoctorListDAO;
import com.versawork.http.dataobject.NsDoctorsList;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.etlCachePopulation.CachePopulation;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.FacilityProvider;
import com.versawork.http.model.FacilityProviderPK;
import com.versawork.http.service.GetDoctorsListService;
import com.versawork.http.utils.CacheKeys;
import com.versawork.http.utils.NsResponseUtils;

/**
 * @author Dheeraj
 * 
 * @author Sohaib
 * 
 */
@Component
public class GetDoctorsListServiceImpl implements GetDoctorsListService {

	@Autowired
	private GetDoctorListDAO getDoctorListDAO;

	@Autowired
	private ImageServiceImpl imageService;

	final static Logger LOGGER = LoggerFactory.getLogger(GetDoctorsListServiceImpl.class);
	// private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CachePopulation cachePopulation;

	private static final String CLIENT_DATABASE_ID = "client.database.id";

	@Autowired
	protected ClientCacheSettingsCheck cCS;

	@Autowired
	CacheAccessbyJson<FacilityProvider> cache;

	@Override
	public NsResponse getDoctorsList(NsRequest nsRequest) throws SystemException, BusinessException {

		/*
		 * int clientDatabaseId =
		 * Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,
		 * nsRequest.getLocale()));
		 */
		// Done for lindsborg as to get client specific services and physicians
		int clientDatabaseId = nsRequest.getAccountInfo().getClientDatabaseId();
		String key = CacheKeys.getFacilityProviderKey(clientDatabaseId);
		Boolean isCachingOn = cCS.isCachingOn(CacheConstants.FACILITY_PROVIDER.getCode());
		Type listType = new TypeToken<List<FacilityProvider>>() {
		}.getType();

		Cache2Object<FacilityProvider> obj = new Cache2Object<FacilityProvider>(cCS, cache);

		List<FacilityProvider> healthcareProviders = obj.getObject(key, isCachingOn, listType);

		if (healthcareProviders == null || healthcareProviders.isEmpty()) {
			healthcareProviders = getDoctorListDAO.getDoctorsList(clientDatabaseId);

			if (healthcareProviders.isEmpty()) {
				return NsResponseUtils.getNsResponse(null,
						messageSource.getMessage("no.provider.details.found", null, nsRequest.getLocale()),
						VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
			}
			obj.setObject(key, isCachingOn, healthcareProviders, listType);
		}

		List<NsDoctorsList> doctorsLists = new ArrayList<NsDoctorsList>();

		for (FacilityProvider hospitalProvider : healthcareProviders) {
			NsDoctorsList nsDoctorsList = new NsDoctorsList();
			nsDoctorsList.setProviderId(hospitalProvider.getFacilityProviderPK().getProviderId());

			// As per requirement
			StringBuffer firstName = new StringBuffer(hospitalProvider.getFirstName());
			if (firstName != null && firstName.length() > 0)
				firstName.replace(0, 1, firstName.substring(0, 1).toUpperCase());
			nsDoctorsList.setFirstName(firstName.toString());

			// As per requirement
			StringBuffer lastName = new StringBuffer(hospitalProvider.getLastName());
			if (lastName != null && lastName.length() > 0)
				lastName.replace(0, 1, lastName.substring(0, 1).toUpperCase());
			nsDoctorsList.setLastName(lastName.toString());

			nsDoctorsList.setContactPhoneNumber(hospitalProvider.getContactPhoneNumber());
			nsDoctorsList.setContactEmail(hospitalProvider.getContactEmail());
			nsDoctorsList.setSpecialty(hospitalProvider.getSpecialty());
			nsDoctorsList.setAddress1(hospitalProvider.getAddress1());
			nsDoctorsList.setAddress2(hospitalProvider.getAddress2());
			nsDoctorsList.setCity(hospitalProvider.getCity());
			nsDoctorsList.setState(hospitalProvider.getState());
			nsDoctorsList.setSuffix(hospitalProvider.getSuffix());
			nsDoctorsList.setFaxNumber(hospitalProvider.getFaxNumber());
			nsDoctorsList.setPostalCode(hospitalProvider.getPostalCode());
			/* needs integration with cache */
			String imageType = messageSource.getMessage("IMAGE.TYPE", null, Locale.getDefault());
			ImgResponse imgResponse = null;
			try {
				imgResponse = imageService
						.download(hospitalProvider.getFacilityProviderPK().getProviderId(), imageType);
			} catch (Exception e) {
				LOGGER.error("While image download" + ExceptionUtils.getFullStackTrace(e));
			}
			GsonBuilder builder = new GsonBuilder();
			builder.serializeNulls();
			// Gson gson = builder.create();
			// String responseJson = gson.toJson(imgResponse);
			// imgResponse = gson.fromJson(responseJson, ImgResponse.class);

			if (imgResponse.getServerUrlInfo() != null) {
				nsDoctorsList.setProviderImageURL(imgResponse.getServerUrlInfo().getImagingURL());
			}
			if (nsRequest.getProviderPurpose().compareTo('P') == 0 && hospitalProvider.getConsultantPhysician()) {
				doctorsLists.add(nsDoctorsList);
			} else if (nsRequest.getProviderPurpose().compareTo('A') == 0 && hospitalProvider.getResidentPhysician()) {
				doctorsLists.add(nsDoctorsList);
			} else if (nsRequest.getProviderPurpose().compareTo('C') == 0) {
				doctorsLists.add(nsDoctorsList);
			}
		}
		if (doctorsLists.isEmpty()) {
			return NsResponseUtils.getNsResponse(null,
					messageSource.getMessage("no.provider.details.found", null, nsRequest.getLocale()),
					VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		}
		NsResponse nsResponse = NsResponseUtils.getNsResponse(null,
				VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS,
				VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		nsResponse.setNsDoctorsList(doctorsLists);
		return nsResponse;
	}

}
