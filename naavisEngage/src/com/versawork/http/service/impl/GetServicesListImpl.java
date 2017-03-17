package com.versawork.http.service.impl;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.versawork.http.cacheClientsInfo.ClientCacheSettingsCheck;
import com.versawork.http.cacheDataObject.Cache2Object;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.constant.CacheConstants;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.GetServicesListDAO;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.NsServicesList;
import com.versawork.http.etlCachePopulation.CachePopulation;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.FacilityService;
import com.versawork.http.model.FacilityServicePK;
import com.versawork.http.service.GetServicesListService;
import com.versawork.http.utils.CacheKeys;
import com.versawork.http.utils.DateUtils;
import com.versawork.http.utils.NsResponseUtils;

/**
 * @author Dheeraj
 * 
 */

@Component
public class GetServicesListImpl implements GetServicesListService {

	final static Logger LOGGER = LoggerFactory.getLogger(GetServicesListImpl.class);
	// private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	@Autowired
	private GetServicesListDAO getServicesListDAO;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CachePopulation cachePopulation;

	private static final String CLIENT_DATABASE_ID = "client.database.id";

	@Autowired
	protected ClientCacheSettingsCheck cCS;

	@Autowired
	CacheAccessbyJson<FacilityService> cache;

	@Override
	public NsResponse getServicesList(NsRequest nsRequest) throws SystemException, BusinessException {

		/*
		 * int clientDatabaseId =
		 * Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,
		 * nsRequest.getLocale()));
		 */
		// Done for lindsborg as to get client specific services and physicians
		int clientDatabaseId = nsRequest.getAccountInfo().getClientDatabaseId();

		Boolean isCachingOn = cCS.isCachingOn(CacheConstants.FACILITY_SERVICE.getCode());
		Type type = new TypeToken<List<FacilityService>>() {
		}.getType();

		String key = CacheKeys.getFacilityServiceKey(clientDatabaseId);
		Cache2Object<FacilityService> obj = new Cache2Object<FacilityService>(cCS, cache);

		List<FacilityService> hospitalServices = obj.getObject(key, isCachingOn, type);

		if (hospitalServices == null || hospitalServices.isEmpty()) {
			hospitalServices = getServicesListDAO.getServicesList(Integer.parseInt(messageSource.getMessage(
					CLIENT_DATABASE_ID, null, nsRequest.getLocale())));

			if (hospitalServices.isEmpty()) {
				return NsResponseUtils.getNsResponse(null,
						messageSource.getMessage("no.hosptl.servc.result", null, nsRequest.getLocale()),
						VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
			}
			obj.setObject(key, isCachingOn, hospitalServices, type);
		}

		List<NsServicesList> servicesLists = new ArrayList<NsServicesList>();

		for (FacilityService hospitalService : hospitalServices) {
			NsServicesList nsServicesList = new NsServicesList();
			nsServicesList.setServiceGroup(hospitalService.getServiceGroup());
			nsServicesList.setService(hospitalService.getService());
			nsServicesList.setContactPhoneNumber(hospitalService.getContactPhoneNumber());
			nsServicesList.setContactEmail(hospitalService.getContactEmail());

			nsServicesList.setDateAdded(DateUtils.getFormatDate(hospitalService.getDateAdded(), "MM/dd/yyyy"));
			if (hospitalService.getDateModified() != null) {

				nsServicesList
						.setDateModified(DateUtils.getFormatDate(hospitalService.getDateModified(), "MM/dd/yyyy"));
			} else {
				nsServicesList.setDateModified(DateUtils.getFormatDate(new Date(), "MM/dd/yyyy"));
			}
			servicesLists.add(nsServicesList);
		}
		NsResponse nsResponse = NsResponseUtils.getNsResponse(
				null,
				VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS,
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));

		nsResponse.setNsServicesList(servicesLists);
		return nsResponse;
	}
}