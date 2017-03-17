package com.versawork.http.service.impl;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.versawork.http.cacheClientsInfo.ClientCacheSettingsCheck;
import com.versawork.http.cacheDataObject.Cache2Object;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.constant.CacheConstants;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.HospitalNoticeServiceDao;
import com.versawork.http.dao.LinkedAccountDao;
import com.versawork.http.dao.PatientBillingDAO;
import com.versawork.http.dataobject.AccountInfo;
import com.versawork.http.dataobject.BillSummaryData;
import com.versawork.http.dataobject.Elements;
import com.versawork.http.dataobject.GeoLocationResponseInfo;
import com.versawork.http.dataobject.HospitalListInfo;
import com.versawork.http.dataobject.NotificationsCount;
import com.versawork.http.dataobject.NsHospitalNotice;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.dataobject.Rows;
import com.versawork.http.etlCachePopulation.CachePopulation;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.AccountToViewedFacilityNotice;
import com.versawork.http.model.ClientNaavisDatabases;
import com.versawork.http.model.FacilityNotice;
import com.versawork.http.model.FacilityNoticePK;
import com.versawork.http.model.PatientBillSummary;
import com.versawork.http.service.HospitalNoticeService;
import com.versawork.http.service.LoginService;
import com.versawork.http.service.UserRegistrationService;
import com.versawork.http.utils.CacheKeys;
import com.versawork.http.utils.DateUtils;
import com.versawork.http.utils.MaskUtils;
import com.versawork.http.utils.NsResponseUtils;

/**
 * @author Ireslab
 * 
 */
@Component
public class HospitalNoticeServiceImpl implements HospitalNoticeService {

	final static Logger LOGGER = LoggerFactory.getLogger(HospitalNoticeServiceImpl.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	protected ClientCacheSettingsCheck cCS;

	@Autowired
	private CacheAccessbyJson<FacilityNotice> cache;

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserRegistrationService userRegService;

	@Autowired
	private HospitalNoticeServiceDao hospitalNoticeServiceDao;

	@Autowired
	private CachePopulation cachePopulation;

	@Autowired
	private PatientBillingDAO patientBillingDAO;

	// private String DEVICE_RESET_RESPONSE_CODE =
	// "rsp.data.result.device.reset";
	/*
	 * @Autowired private AccountServiceDAO accountDAO;
	 */

	@Autowired
	private LinkedAccountDao linkedAccountDao;

	@Autowired
	private MessageSource messageSource;

	private static final String CLIENT_DATABASE_ID = "client.database.id";

	@Override
	public NsResponse getHospitalNotice(NsRequest nsRequest) throws BusinessException, SystemException {
		List<NsHospitalNotice> nsHospitalNotices = new ArrayList<NsHospitalNotice>();
		NsResponse nsResponse = new NsResponse();
		NotificationsCount notificationsCount = new NotificationsCount();

		Date today = new Date();

		int clientDatabaseId = Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null,
				nsRequest.getLocale()));
		Boolean isCachingOn = cCS.isCachingOn(CacheConstants.FACILITY_NOTICE.getCode());
		Type type = new TypeToken<List<FacilityNotice>>() {
		}.getType();

		String key = CacheKeys.getFacilityNoticeKey(clientDatabaseId);
		Cache2Object<FacilityNotice> obj = new Cache2Object<FacilityNotice>(cCS, cache);

		List<FacilityNotice> hospitalNotices = obj.getObject(key, isCachingOn, type);

		// LOGGER.debug(isCachingOn + "  FacilityNotice size : " +
		// hospitalNotices.size());

		if (nsRequest.getSetAllViewed() != null && !nsRequest.getSetAllViewed()) {
			List<PatientBillSummary> billSummaries = patientBillingDAO.getPatientBillSummaryList(nsRequest
					.getAccountInfo().getAccountId(), false);
			if (billSummaries != null && !billSummaries.isEmpty()) {
				NsHospitalNotice hospitalNotice = new NsHospitalNotice();
				hospitalNotice.setBillPayMessage(true);
				// responseData.setDescription(messageSource.getMessage("link.account.succ",
				// , nsRequest.getLocale()));
				hospitalNotice.setHospitalName(messageSource.getMessage(VersaWorkConstant.PAY_MY_BILLS,
						new String[] { String.valueOf(billSummaries.size()) }, nsRequest.getLocale()));
				String noticeMessage = "";
				for (PatientBillSummary billSummary : billSummaries) {
					noticeMessage = noticeMessage + billSummary.getSourceName() + "-$"
							+ billSummary.getTotalDue().toString() + "\n";
				}
				hospitalNotice.setNoticeMessage(noticeMessage);
				nsHospitalNotices.add(hospitalNotice);
			}

		}

		if (hospitalNotices == null || hospitalNotices.isEmpty()) {
			hospitalNotices = hospitalNoticeServiceDao.getHospitalNotice(clientDatabaseId);

			if (hospitalNotices.isEmpty() && nsHospitalNotices.size() > 0) {
				ResponseData responseData = new ResponseData();
				nsResponse.setHospitalNotices(nsHospitalNotices);
				responseData.setDescription(VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS);
				responseData.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
				nsResponse.setResponseData(responseData);
				return nsResponse;
			} else if (hospitalNotices.isEmpty()) {

				LOGGER.debug("Hospital Notices are empty");
				nsResponse = NsResponseUtils.getNsResponse(null,
						messageSource.getMessage("no.hosptl.servc.result", null, nsRequest.getLocale()),
						VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
				nsResponse.setAppStoreVersion(messageSource.getMessage("app.version.store", null, Locale.getDefault()));

				return nsResponse;
			}
			obj.setObject(key, isCachingOn, hospitalNotices, type);

		}

		Type aTVFNListType = new TypeToken<List<Integer>>() {
		}.getType();

		List<Integer> hNVList = new ArrayList<Integer>();

		String identifier = null;
		if (nsRequest.getAccountInfo().getAccountId() != null) {
			identifier = nsRequest.getAccountInfo().getAccountId().toString();

		} else {
			identifier = MaskUtils.getDigest(nsRequest.getAccountInfo().getDeviceToken().getBytes());
		}

		AccountToViewedFacilityNotice aTVFN = hospitalNoticeServiceDao.getViewedNoticeList(identifier);
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls();
		Gson gson = builder.create();
		if (aTVFN.getAccountId() != null) {
			hNVList = gson.fromJson(aTVFN.getViewedList(), aTVFNListType);
		}
		Integer countNotice = 0;

		if (!hNVList.isEmpty()) {
			Iterator<FacilityNotice> iter = hospitalNotices.iterator();
			while (iter.hasNext()) {
				FacilityNotice fN = new FacilityNotice();
				fN = iter.next();
				Iterator<Integer> iter1 = hNVList.iterator();
				while (iter1.hasNext()) {
					if (iter1.next() == fN.getFacilityNoticePK().getFacilityNoticeId()) {
						// hospitalNotices1.add(fN);
						countNotice++;
					}
				}
			}
		}
		/*
		 * Iterator<FacilityNotice> iter2 = hospitalNotices1.iterator(); while
		 * (iter2.hasNext()) { hospitalNotices.remove(iter2.next()); }
		 */

		// perform bill summary

		for (FacilityNotice hospitalNotice : hospitalNotices) {

			if (hospitalNotice.getEndDate() == null || !hospitalNotice.getEndDate().before(today)) {
				NsHospitalNotice nsHospitalNotice = new NsHospitalNotice();
				nsHospitalNotice.setBillPayMessage(false);
				// nsHospitalNotice.setEmail("abc@example.com");
				nsHospitalNotice.setHospitalNoticeId(hospitalNotice.getFacilityNoticePK().getFacilityNoticeId());
				nsHospitalNotice.setClientId(hospitalNotice.getFacilityNoticePK().getClientId());
				nsHospitalNotice.setClientDatabaseId(hospitalNotice.getFacilityNoticePK().getClientDatabaseId());
				// nsHospitalNotice.setHospitalName((messageSource.getMessage("notification.hospital.name",
				// null, nsRequest.getLocale())));
				nsHospitalNotice.setHospitalName(hospitalNotice.getNoticeHeader());
				// nsHospitalNotice.setProviderName("Dr. Chamunda");
				// nsHospitalNotice.setHospitalNoticeId(hospitalNotice.getFacilityNoticePK().getFacilityNoticeId());
				try {
					nsHospitalNotice.setBeginDate(DateUtils.getFormatDate(hospitalNotice.getBeginDate(),
							"MM/dd/yyyy HH:mm"));
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.debug("Failed to set start date for notice.");
				}
				try {
					if (hospitalNotice.getEndDate() != null) {
						nsHospitalNotice.setEndDate(DateUtils.getFormatDate(hospitalNotice.getEndDate(),
								"MM/dd/yyyy HH:mm"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.debug("Failed to set end date for notice");
				}
				nsHospitalNotice.setNoticeMessage(hospitalNotice.getNoticeMessage());
				nsHospitalNotices.add(nsHospitalNotice);
			} else {
			}

		}
		if (nsHospitalNotices != null && (nsHospitalNotices.size() - countNotice < 0)) {
			// nsHospitalNotices.get(0).setNoticesCount(0);
			notificationsCount.setHospitalNotificationCount(0);
			// markNoticeViewed(nsRequest,nsHospitalNotices);
		} else {
			notificationsCount.setHospitalNotificationCount(nsHospitalNotices.size() - countNotice);
		}
		// nsResponse = loginService.forciblyVersionUpdate(nsResponse);
		// String appVersion = messageSource.getMessage("app.version.store",
		// null,Locale.getDefault());

		nsResponse = NsResponseUtils.getNsResponse(
				null,
				messageSource.getMessage("rsp.data.dscrptn.success", null, nsRequest.getLocale()),
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));

		nsResponse.setAppStoreVersion(messageSource.getMessage("app.version.store", null, Locale.getDefault()));
		nsResponse.setHospitalNotices(nsHospitalNotices);
		nsResponse.setNotificationsCount(notificationsCount);
		return nsResponse;
	}

	@Override
	public void markNoticeViewed(NsRequest nsRequest, List<NsHospitalNotice> nsHospitalNotice) {
		try {
			LOGGER.info("Performing async marking of notices as viewed.");
			Iterator<NsHospitalNotice> iter = nsHospitalNotice.iterator();
			while (iter.hasNext()) {
				NsRequest nsReq = new NsRequest();
				nsReq.setHospitalNoticeId(iter.next().getHospitalNoticeId());
				AccountInfo accountInf = new AccountInfo();
				if (nsRequest.getAccountInfo() != null) {
					if (nsRequest.getAccountInfo().getAccountId() != null) {
						accountInf.setAccountId(nsRequest.getAccountInfo().getAccountId());
					} else {
						accountInf.setDeviceToken(nsRequest.getAccountInfo().getDeviceToken());
					}
					nsReq.setAccountInfo(accountInf);
					setNoticeViewed(nsReq);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception while setting hospital notice viewed flag");
		}
	}

	@Override
	public NsResponse setNoticeViewed(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		try {
			GsonBuilder builder = new GsonBuilder();
			builder.serializeNulls();
			Gson gson = builder.create();
			Type listType = new TypeToken<List<Integer>>() {
			}.getType();

			String identifier = null;
			if (nsRequest.getAccountInfo().getAccountId() != null) {
				identifier = nsRequest.getAccountInfo().getAccountId().toString();
			} else {
				identifier = MaskUtils.getDigest(nsRequest.getAccountInfo().getDeviceToken().getBytes());
			}
			List<Integer> hNVList = new ArrayList<Integer>();
			AccountToViewedFacilityNotice aTVFN = hospitalNoticeServiceDao.getViewedNoticeList(identifier);
			if (aTVFN.getAccountId() != null) {
				hNVList = gson.fromJson(aTVFN.getViewedList(), listType);
				if (!hNVList.contains(nsRequest.getHospitalNoticeId())) {
					hNVList.add(nsRequest.getHospitalNoticeId());
					aTVFN.setViewedList(gson.toJson(hNVList, listType));
					hospitalNoticeServiceDao.updateViewedList(aTVFN);
				}
			} else {
				hNVList.add(nsRequest.getHospitalNoticeId());
				aTVFN.setViewedList(gson.toJson(hNVList, listType));
				aTVFN.setAccountId(identifier);
				hospitalNoticeServiceDao.saveViewedList(aTVFN);
			}
			responseData.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE,
					null, nsRequest.getLocale())));
			responseData.setDescription("Success");
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
			responseData.setDescription("Failed to record the new view");
		}

		nsResponse.setResponseData(responseData);
		return nsResponse;
	}

	/*
	 * @Override public NsResponse getHospitalLists(NsRequest nsRequest)throws
	 * BusinessException, SystemException { String PIPE = "|"; NsResponse
	 * nsResponse = new NsResponse(); ResponseData responseData = new
	 * ResponseData(); Gson gson = new Gson(); try { List<ClientNaavisDatabases>
	 * clientNaavisDatabasesList = hospitalNoticeServiceDao.getDatabasesList(
	 * Integer.parseInt(messageSource.getMessage("client.id",
	 * null,nsRequest.getLocale())));
	 * 
	 * List<HospitalListInfo> hospitalListInfoList = new
	 * ArrayList<HospitalListInfo>(); String destLatLong = ""; String
	 * originLatLong = nsRequest.getHospitalListInfo().getLatitude()+","+
	 * nsRequest.getHospitalListInfo().getLongitude(); for(ClientNaavisDatabases
	 * databases : clientNaavisDatabasesList) { int distance =
	 * distFrom(nsRequest.getHospitalListInfo().getLatitude(),
	 * nsRequest.getHospitalListInfo().getLongitude(), databases.getLatitude(),
	 * databases.getLongitude()); if(distance <=
	 * nsRequest.getHospitalListInfo().getDistInMiles()) { HospitalListInfo
	 * hospitalListInfo = new HospitalListInfo();
	 * hospitalListInfo.setAddress(databases.getAddress1());
	 * hospitalListInfo.setCity(databases.getCity());
	 * hospitalListInfo.setState(databases.getState());
	 * hospitalListInfo.setCountry(databases.getCountry());
	 * hospitalListInfo.setPostalCode(databases.getPostalCode());
	 * hospitalListInfo.setLatitude(databases.getLatitude());
	 * hospitalListInfo.setLongitude(databases.getLongitude());
	 * hospitalListInfoList.add(hospitalListInfo);
	 * 
	 * destLatLong = destLatLong + databases.getLatitude()+","+
	 * databases.getLongitude() + PIPE; } } //
	 * System.out.println("destLatLong  : "+ destLatLong);
	 * 
	 * if(!destLatLong.isEmpty()) { ResponseEntity<String> result =
	 * getLatLongValues(originLatLong, destLatLong); //
	 * System.out.println("gson.tojson  :::::"+result.getBody());
	 * 
	 * GeoLocationResponseInfo response = gson.fromJson(result.getBody(),
	 * GeoLocationResponseInfo.class); List<Rows> rows = response.getRows();
	 * List<Elements> elements= Collections.emptyList(); Iterator <Elements>
	 * elementsIterator= null;
	 * 
	 * for(Rows row : rows) { elements = row.getElements(); elementsIterator
	 * =elements.iterator(); while (elementsIterator.hasNext()) { Elements
	 * element = elementsIterator.next(); double distanceInMeter =
	 * element.getDistance().getValue(); double distanceInMiles =
	 * distanceInMeter * 0.000621371; // converting meter to miles as 1 meter =
	 * 0.000621371 miles. if( distanceInMiles > 50.0) elementsIterator.remove();
	 * } } nsResponse.setGeoLocationResponseInfo(response); } } catch (Exception
	 * e) { e.printStackTrace(); }
	 * 
	 * nsResponse.setResponseData(responseData); return nsResponse; }
	 * 
	 * public static int distFrom(double lat1, double lng1, double lat2, double
	 * lng2) { // DecimalFormat df = new DecimalFormat("#.##"); double
	 * earthRadius = 3958.756; //miles double dLat = Math.toRadians(lat2-lat1);
	 * double dLng = Math.toRadians(lng2-lng1); double a = Math.sin(dLat/2) *
	 * Math.sin(dLat/2) + Math.cos(Math.toRadians(lat1)) *
	 * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng/2) * Math.sin(dLng/2);
	 * double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); float dist =
	 * (float) (earthRadius * c);
	 * 
	 * // System.out.println("Distance: "+(int)dist);
	 * 
	 * return (int) dist; }
	 * 
	 * private static ResponseEntity<String> getLatLongValues(String
	 * originLatLong, String destLong) { final String uri =
	 * "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
	 * +originLatLong+"&"+"destinations="+destLong+"&units=imperial";
	 * 
	 * RestTemplate restTemplate = new RestTemplate();
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	 * HttpEntity<String> entity = new HttpEntity<String>("parameters",
	 * headers);
	 * 
	 * ResponseEntity<String> result = restTemplate.exchange(uri,
	 * HttpMethod.POST, entity, String.class);
	 * 
	 * // System.out.println(result);
	 * 
	 * return result; }
	 */

	@Override
	public NsResponse getHospitalLists(NsRequest nsRequest) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}
}