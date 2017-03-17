package com.versawork.http.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.imagingserver.http.imaging.dataObjects.ImgResponse;
import com.imagingserver.http.imaging.serviceImpl.ImageServiceImpl;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.ClientNaavisDatabaseServiceDAO;
import com.versawork.http.dao.GetServicesListDAO;
import com.versawork.http.dao.LinkedAccountDao;
import com.versawork.http.dao.PatientBillingDAO;
import com.versawork.http.dataobject.BillDetailData;
import com.versawork.http.dataobject.BillSummaryData;
import com.versawork.http.dataobject.BillingInfo;
import com.versawork.http.dataobject.Facility;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.NsServicesList;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.ClientNaavisDatabases;
import com.versawork.http.model.FacilityService;
import com.versawork.http.model.LinkedAccountsRel;
import com.versawork.http.model.PatientBillDetail;
import com.versawork.http.model.PatientBillSummary;
import com.versawork.http.model.PatientTransactionLog;
import com.versawork.http.service.LinkAccountService;
import com.versawork.http.utils.DateUtils;

@Service
public class LinkAccountServiceImpl implements LinkAccountService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private LinkedAccountDao linkedAccountDao;

	@Autowired
	private PatientBillingDAO patientBillingDAO;

	@Autowired
	private ClientNaavisDatabaseServiceDAO clientNaavisDatabaseServiceDAO;

	@Autowired
	private GetServicesListDAO getServicesListDAO;

	@Autowired
	private ImageServiceImpl imageService;

	@Autowired
	private MessageSource messageSource;
	static Logger LOGGER = LoggerFactory.getLogger(LinkAccountServiceImpl.class);

	public NsResponse setSecondaryAccount(NsRequest nsRequest) throws BusinessException, SystemException {
		ResponseData responseData = new ResponseData();
		LinkedAccountsRel linkedAccountsRel = new LinkedAccountsRel();
		Integer accountId = nsRequest.getAccountInfo().getAccountId();
		Integer linkedAccountId = nsRequest.getAccountInfo().getLinkedAccounts().get(0);

		verifySecondaryUserAlreadyExist(accountId, linkedAccountId);
		linkedAccountsRel.setAccountId(nsRequest.getAccountInfo().getAccountId());
		linkedAccountsRel.setClientDatabaseId(Integer.parseInt(messageSource.getMessage("client.database.id", null,
				nsRequest.getLocale())));
		linkedAccountsRel.setLinkedAccountId(linkedAccountId);
		linkedAccountsRel.setLinkedAccountClientdbId(nsRequest.getAccountInfo().getLinkedAccountClientDBId());
		linkedAccountsRel.setLinkedFacilityName(nsRequest.getAccountInfo().getLinkedFacility());
		linkedAccountsRel.setLinkedFacilityAddress(nsRequest.getAccountInfo().getLinkedFacilityAddress());
		linkedAccountDao.save(linkedAccountsRel);
		LOGGER.debug("Linking account " + nsRequest.getAccountInfo().getAccountId() + "of facility "
				+ nsRequest.getAccountInfo().getLinkedFacility());
		responseData.setDescription(messageSource.getMessage("link.account.succ", new String[] { nsRequest
				.getAccountInfo().getLinkedFacility() }, nsRequest.getLocale()));
		responseData.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		NsResponse nsResponse = new NsResponse();
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}

	private void verifySecondaryUserAlreadyExist(Integer accountId, Integer linkedAccountId) throws BusinessException,
			SystemException {

		LinkedAccountsRel fetchedLinkedAccount = linkedAccountDao.isAccountLinked(accountId, linkedAccountId);
		if (fetchedLinkedAccount != null) {
			LOGGER.debug("Account is already linked");
			throw new BusinessException("account.already.linked");
		}
	}

	@Override
	public NsResponse unlinkAccount(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		Integer clientDbId = nsRequest.getClientDbId();
		LinkedAccountsRel linkedAccountsRel = linkedAccountDao.getAccount(nsRequest.getAccountInfo().getAccountId(),
				clientDbId);
		LOGGER.debug("Unlinking Account ID: " + linkedAccountsRel.getLinkedAccountId());
		linkedAccountDao.remove(linkedAccountsRel);
		ResponseData responseData = new ResponseData();
		responseData.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		responseData.setDescription(VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS);
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}

	@Override
	public NsResponse getRegisteredFacilities(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		List<Facility> registeredFacilities = new ArrayList<Facility>();
		Integer accountId = nsRequest.getAccountInfo().getAccountId();
		Integer clientDbId = Integer.parseInt(messageSource.getMessage("client.database.id", null,
				nsRequest.getLocale()));
		// ////////////////////////////////////////
		ClientNaavisDatabases clientNaavisDatabases = clientNaavisDatabaseServiceDAO
				.getClientNaavisDatabases(clientDbId);
		Facility facilitySecond = new Facility();
		facilitySecond.setFacilityName(clientNaavisDatabases.getFacilityName());
		facilitySecond.setFacilityAddess(clientNaavisDatabases.getCity() + ", " + clientNaavisDatabases.getState());
		facilitySecond.setClientDbId(clientDbId);
		facilitySecond.setIsParent(true);
		registeredFacilities.add(facilitySecond);

		List<LinkedAccountsRel> linkedAccountsRels = linkedAccountDao.getLinkedAccounts(accountId);
		LOGGER.debug("No. of registered facilities are: " + linkedAccountsRels.size());
		for (LinkedAccountsRel linkedAccountsRel : linkedAccountsRels) {
			Facility facility = new Facility();
			facility.setFacilityName(linkedAccountsRel.getLinkedFacilityName());
			facility.setFacilityAddess(linkedAccountsRel.getLinkedFacilityAddress());
			facility.setClientDbId(linkedAccountsRel.getLinkedAccountClientdbId());
			registeredFacilities.add(facility);
		}
		nsResponse.setRegisteredFacilities(registeredFacilities);
		responseData.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		responseData.setDescription(VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS);
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}

	@Override
	public NsResponse getRegisteredFacilityDetails(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		List<NsServicesList> servicesLists = new ArrayList<NsServicesList>();
		Integer clientResourceDBId = Integer.parseInt(messageSource.getMessage("client.database.id", null,
				nsRequest.getLocale()));
		Integer clientDBId = nsRequest.getClientDbId();
		Facility facility = new Facility();
		LOGGER.debug("Fetching client hospital based on client DB ID: " + clientDBId);
		ClientNaavisDatabases clientNaavisDatabases = clientNaavisDatabaseServiceDAO
				.getClientNaavisDatabases(clientDBId);
		facility.setClientDbId(clientDBId);
		facility.setFacilityStreet(clientNaavisDatabases.getAddress1());
		facility.setFacilityAddess(clientNaavisDatabases.getCity() + ", " + clientNaavisDatabases.getState());
		facility.setFacilityPhone(clientNaavisDatabases.getFacillityPhoneNumber());
		if (clientDBId.equals(clientResourceDBId)) {
			facility.setIsParent(true);
		}
		List<FacilityService> facilityServices = getServicesListDAO.getServicesList(clientDBId);
		for (FacilityService hospitalService : facilityServices) {
			NsServicesList nsServicesList = new NsServicesList();
			nsServicesList.setService(hospitalService.getService());
			servicesLists.add(nsServicesList);
		}
		responseData.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		responseData.setDescription(VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS);
		facility.setNsServicesLists(servicesLists);
		nsResponse.setResponseData(responseData);
		nsResponse.setFacility(facility);
		return nsResponse;
	}

	@Override
	public NsResponse searchdFacilities(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		List<ClientNaavisDatabases> clientNaavisDatabases = new ArrayList<ClientNaavisDatabases>();
		Integer clientDbID = Integer.parseInt(messageSource.getMessage("client.database.id", null,
				nsRequest.getLocale()));
		Integer accountId = nsRequest.getAccountInfo().getAccountId();
		ArrayList<Integer> accIds = getLinkedClientDB(accountId);
		accIds.add(clientDbID);
		clientNaavisDatabases = linkedAccountDao.getFacilitiesList(clientDbID, nsRequest.getSearchFacility());
		List<Facility> searchedFacilities = new ArrayList<Facility>();
		if (clientNaavisDatabases == null || clientNaavisDatabases.isEmpty()) {
			LOGGER.debug("No Searched Facilities");
			responseData.setResult(20);
			responseData.setDescription(messageSource.getMessage("no.searched.facility", null, nsRequest.getLocale()));
		} else {
			for (ClientNaavisDatabases database : clientNaavisDatabases) {
				List<FacilityService> facilityServices = getServicesListDAO.getServicesList(clientDbID);
				List<NsServicesList> servicesLists = new ArrayList<NsServicesList>();
				for (FacilityService hospitalService : facilityServices) {
					NsServicesList nsServicesList = new NsServicesList();
					nsServicesList.setService(hospitalService.getService());
					servicesLists.add(nsServicesList);
				}
				Facility facility = new Facility();
				facility.setFacilityName(database.getFacilityName());
				facility.setFacilityStreet(database.getAddress1());
				facility.setFacilityAddess(database.getCity() + ", " + database.getState());
				facility.setClientDbId(database.getClientNaavisDatabasesPK().getClientDatabaseId());
				facility.setFacilityPhone(database.getFacillityPhoneNumber());
				facility.setFacilityUrl(getLogo(database.getClientNaavisDatabasesPK().getClientDatabaseId()));
				if (accIds.contains(database.getClientNaavisDatabasesPK().getClientDatabaseId())) {
					facility.setIsLinked(true);
				} else {
					facility.setIsLinked(false);
				}

				facility.setNsServicesLists(servicesLists);
				searchedFacilities.add(facility);
			}
			responseData.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
			responseData.setDescription(VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS);
			nsResponse.setSearchedFacilities(searchedFacilities);
		}
		nsResponse.setResponseData(responseData);
		return nsResponse;
	}

	public List<LinkedAccountsRel> getLinkedAccounts(Integer accountId) throws BusinessException, SystemException {
		//ArrayList<Integer> accIds = new ArrayList<Integer>();
		List<LinkedAccountsRel> linkedAccountsRels = linkedAccountDao.getLinkedAccounts(accountId);
		return linkedAccountsRels;
	
		
		
	}

	public ArrayList<Integer> getLinkedClientDB(Integer accountId) throws BusinessException, SystemException {
		ArrayList<Integer> accIds = new ArrayList<Integer>();
		List<LinkedAccountsRel> linkedAccountsRels = linkedAccountDao.getLinkedAccounts(accountId);
		for (LinkedAccountsRel linkedAccountsRel : linkedAccountsRels) {
			accIds.add(linkedAccountsRel.getLinkedAccountClientdbId());
		}
		return accIds;
	}

	public String getLogo(Integer clientDbId) throws BusinessException, SystemException {
		String url = null;
		ImgResponse imgResponse = null;
		try {
			if (clientDbId == Integer.parseInt(messageSource.getMessage(VersaWorkConstant.CLIENT_DB_ID_SALINA, null,
					Locale.getDefault()))) {

				String[] image = messageSource.getMessage(VersaWorkConstant.SALINA_LOGO_IMAGE_NAME, null,
						Locale.getDefault()).split("\\.");
				imgResponse = imageService.download(image[0], image[1]);

			} else if (clientDbId == Integer.parseInt(messageSource.getMessage(VersaWorkConstant.CLIENT_DB_ID_CASCADE,
					null, Locale.getDefault()))) {
				String[] image = messageSource.getMessage(VersaWorkConstant.CASCADE_LOGO_IMAGE_NAME, null,
						Locale.getDefault()).split("\\.");
				imgResponse = imageService.download(image[0], image[1]);
			} else if (clientDbId == Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.CLIENT_DB_ID_LINDSBORG, null, Locale.getDefault()))) {
				String[] image = messageSource.getMessage(VersaWorkConstant.LINDSBORG_LOGO_IMAGE_NAME, null,
						Locale.getDefault()).split("\\.");
				imgResponse = imageService.download(image[0], image[1]);
			} else if (clientDbId == Integer.parseInt(messageSource.getMessage(VersaWorkConstant.CLIENT_DB_ID_NAVIS,
					null, Locale.getDefault()))) {
				String[] image = messageSource.getMessage(VersaWorkConstant.NAVIS_LOGO_IMAGE_NAME, null,
						Locale.getDefault()).split("\\.");
				imgResponse = imageService.download(image[0], image[1]);
			}
			else if (clientDbId == Integer.parseInt(messageSource.getMessage(VersaWorkConstant.CLIENT_DB_ID_MITCHELL,
					null, Locale.getDefault()))) {
				String[] image = messageSource.getMessage(VersaWorkConstant.MITCHELL_LOGO_IMAGE_NAME, null,
						Locale.getDefault()).split("\\.");
				imgResponse = imageService.download(image[0], image[1]);
			}
			
			else {
				LOGGER.error("NO Image found while getLogo()   ");
				throw new BusinessException(VersaWorkConstant.NO_IMAGE_FOUND);
			}
			url = imgResponse.getServerUrlInfo().getImagingURL();
		} catch (Exception e) {
			LOGGER.error("Error While getLogo()   " + ExceptionUtils.getFullStackTrace(e));
			return null;
		}

		return url;
	}

}
