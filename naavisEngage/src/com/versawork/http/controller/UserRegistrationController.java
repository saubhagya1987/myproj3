package com.versawork.http.controller;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import javax.ws.rs.QueryParam;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.interceptor.BoundaryLogger;
import com.versawork.http.service.UserRegistrationService;
import com.versawork.http.utils.Activity;

/**
 * @author IresLab
 * 
 *         http://localhost:8080/naavisEngage/service/registrationService/
 * 
 */
@Controller
@RequestMapping(value = "/registrationService")
public class UserRegistrationController  {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	private UserRegistrationService registrationService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	MessageSource sessionConfigMessageSource;

	@Autowired
	private ActivityLogController activityLogController;

	@Autowired
	private Gson gson;

	private String SESSION_MAX_INACTIVE = "session.max.inactive.time";
	private static final String CLIENT_DATABASE_ID = "client.database.id";
	private static final String REDIRECT_URL_ANDROID = "redirect.url.android";
	private static final String REDIRECT_URL_IOS = "redirect.url.ios";


	/*
	 * @Autowired private HospitalNoticeServiceImpl hospitalNoticeServiceImpl;
	 * 
	 * @Autowired private MedicationManagementController
	 * medicationManagementController;
	 * 
	 * @Autowired private MedicationManagementServiceImpl
	 * medicationManagementServiceImpl;
	 * 
	 * @Autowired private NotificationController notificationController;
	 * 
	 * @Autowired private NotificationServiceImpl notificationServiceImpl;
	 * 
	 * @Autowired private BloodPressureController bloodPressureController;
	 * 
	 * @Autowired private UserRegistrationDAO userRegistrationDAO;
	 */

	/**
	 * Verify the user by validating the MRNumber and Phone Number received in
	 * the request with the already existing patient data present in database.
	 * 
	 * @param nsRequest
	 * @return
	 */
	@RequestMapping(value = "/verifyUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@BoundaryLogger
	@ResponseBody
	public NsResponse verifyUser(@RequestBody NsRequest nsRequest) throws BusinessException, SystemException {

		LOGGER.info("Request received for user verification . . . ");
		LOGGER.debug("Request Receieved, verifyUser: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse;
		try {
			nsResponse = registrationService.verifyUser(nsRequest, false);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VERIFY_USER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VERIFY_USER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VERIFY_USER);
		}
		LOGGER.debug("Response Sent for, verifyUser: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = "/validateUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@BoundaryLogger
	@ResponseBody
	public NsResponse validateUser(@RequestBody NsRequest nsRequest) throws BusinessException, SystemException {

		LOGGER.info("Request received for user validation . . . ");
		LOGGER.debug("Request Receieved, validateUser: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse;
		try {
			nsResponse = registrationService.validateUser(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VALIDATE_USER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VALIDATE_USER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VALIDATE_USER);
		}
		LOGGER.debug("Response Sent for, validateUser: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	/**
	 * Generate and send the security code through channel/mode received by the
	 * user in request.
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@RequestMapping(value = { "/sendSecurityCode" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@BoundaryLogger
	public @ResponseBody NsResponse sendSecurityCode(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, sendSecurityCode: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse;
		try {
			nsResponse = registrationService.sendSecurityCode(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		}
		LOGGER.debug("Response Sent for, generateSecurityCode: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	/**
	 * Validates the security code and expiry received in the request against
	 * the one stored in the database.
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@RequestMapping(value = { "/validateSecurityCode" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@BoundaryLogger
	public @ResponseBody NsResponse validateSecurityCode(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse;
		LOGGER.debug("Request Receieved, validateSecurityCode: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = registrationService.validateSecurityCode(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VALIDATE_SECURITY_CODE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VALIDATE_SECURITY_CODE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VALIDATE_SECURITY_CODE);
		}
		LOGGER.debug("Response Sent, validateSecurityCode: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	/**
	 * Creates the user account to persist the PIN/Finger print in request to be
	 * used for post authentication.
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@RequestMapping(value = { "/createUserAccount" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public NsResponse registerAccount(@RequestBody NsRequest nsRequest, HttpServletRequest req) throws BusinessException, SystemException {
		NsResponse nsResponse;
		LOGGER.debug("Request Receieved, registerAccount: \n" + gson.toJson(nsRequest));

		try {
			nsResponse = registrationService.createUserAccount(nsRequest);
			//for session check
			Integer sessionMaxInactiveTime = Integer.parseInt(sessionConfigMessageSource.getMessage(
					SESSION_MAX_INACTIVE, null, nsRequest.getLocale()));

			HttpSession session = req.getSession();
			if (session != null) {
				session.setMaxInactiveInterval(sessionMaxInactiveTime);
				session.setAttribute("Id", session.getId());
			}
			//session
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.CREATE_ACCOUNT);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.CREATE_ACCOUNT);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.CREATE_ACCOUNT);
		}
		LOGGER.debug("Response Sent, registerAccount: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	/**
	 * Authenticates user on basis of Endpoint userid and 4
	 * AUTH_PIN/AUTH_FINGERPRINT
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@RequestMapping(value = { "/authenticateUserLogin" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public NsResponse authenticateUserLogin(@RequestBody NsRequest nsRequest, HttpServletRequest req)
			throws SystemException, BusinessException {
		LOGGER.debug("Request Receieved, userAuthentication: \n " + gson.toJson(nsRequest));
		NsResponse nsResponse;
		try {

			nsResponse = registrationService.authenticateUserLogin(nsRequest);

			// Session
			Integer sessionMaxInactiveTime = Integer.parseInt(sessionConfigMessageSource.getMessage(
					SESSION_MAX_INACTIVE, null, nsRequest.getLocale()));

			HttpSession session = req.getSession();
			if (session != null) {
				session.setMaxInactiveInterval(sessionMaxInactiveTime);
				session.setAttribute("Id", session.getId());
			}
			// Session

			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.AUTHENTICATE_USER_LOGIN);
		} catch (BusinessException businessException) {
			LOGGER.error("Business Exception", businessException);
			nsResponse = handleBusinessException(businessException, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.AUTHENTICATE_USER_LOGIN);
		} catch (Exception sysExp) {
			LOGGER.error("System Exception", ExceptionUtils.getStackTrace(sysExp));
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.AUTHENTICATE_USER_LOGIN);
		}
		LOGGER.debug("Response send, validateAccount: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/sendSecurityCodeForPhoneNumberChange" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public NsResponse sendSecurityCodeForPhoneNumberChange(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse;
		LOGGER.debug("Request Receieved, registerAccount: \n" + gson.toJson(nsRequest));

		try {
			nsResponse = registrationService.sendSecurityCodeForPhoneNumberChange(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.SEND_SECURITY_CODE_FOR_PHONE_NUMBER_CHANGE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.SEND_SECURITY_CODE_FOR_PHONE_NUMBER_CHANGE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.SEND_SECURITY_CODE_FOR_PHONE_NUMBER_CHANGE);
		}
		LOGGER.debug("Response sent, sendSecurityCodeForPhoneNumberChange: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}
// Not Required in version 2.0
	/*@RequestMapping(value = { "/sendSecurityCodeForProfileEdit" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@BoundaryLogger
	public @ResponseBody NsResponse sendSecurityCodeForProfileEdit(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {

		LOGGER.debug("Request Receieved, sendSecurityCodeForProfileEdit: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse;
		try {
			nsResponse = registrationService.sendSecurityCodeForProfileEdit(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.SEND_SECURITY_CODE_FOR_PHONE_PROFILE_EDIT);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.SEND_SECURITY_CODE_FOR_PHONE_PROFILE_EDIT);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.SEND_SECURITY_CODE_FOR_PHONE_PROFILE_EDIT);
		}
		LOGGER.debug("Response Sent for, sendSecurityCodeForProfileEdit: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}*/
	
	@RequestMapping(value = { "/getFeedInfoList" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public NsResponse feedInfo(@RequestBody NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse;
		LOGGER.debug("Request Receieved, feedInfo: \n" + gson.toJson(nsRequest));

		try {
			nsResponse = registrationService.getFeedInfo(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.FEED_INFO);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.FEED_INFO);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.FEED_INFO);
		}
		LOGGER.debug("Response Sent, feedInfo: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}
	
	@RequestMapping(value = { "/updateFeedInfo" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse updateFeedInfo(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, feedInfo : \n" + gson.toJson(nsRequest));
		try {
			nsResponse = registrationService.updateFeedInfo(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_PREFERED_LANGUAGE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_PREFERED_LANGUAGE);
		} catch (Exception sysExp) { 
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_PREFERED_LANGUAGE);
		}
		LOGGER.debug("Response Sent for, feedInfo: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}
	
	/*@RequestMapping(value = { "/redirectPage" })
	public ModelAndView redirectPage() {		
		  
		return new ModelAndView("redirect");
    }*/
	
	private static final String URL_KEY_SECURITY_CODE = "scode";
	private static final String URL_KEY_LINKED_SECURITY_CODE = "lscode";
	
	private static final String ANDROID_REQUEST_HEADER = "android";
	private static final String IOS_REQUEST_HEADER = "iphone";
	
	@RequestMapping(value = { "/redirectPage" })
	public ModelAndView redirectPage(@RequestParam(value = "scode", required = false) String securityCode, @RequestParam(value = "lscode", required = false) String linkedSecurityCode, HttpServletRequest request)
	{   		
		ModelAndView modelandView = new ModelAndView("redirect");
		String header=request.getHeader("user-agent").toLowerCase();
		String url=messageSource.getMessage(CLIENT_DATABASE_ID, null,	Locale.getDefault());
			
		// Security Code
		String secCode = ((securityCode!=null && securityCode.length() > 0)? securityCode : linkedSecurityCode); 
		
		// Redirect URL Key to be sent back to device for deep linking 
		String redirectUrlKey = ((securityCode!=null && securityCode.length() > 0) ? URL_KEY_SECURITY_CODE : URL_KEY_LINKED_SECURITY_CODE);
		
		if (header.contains(IOS_REQUEST_HEADER) || header.contains("ipad")) {
			url = messageSource.getMessage(REDIRECT_URL_IOS, new String[] {redirectUrlKey, secCode},Locale.getDefault());
		} else if (header.contains(ANDROID_REQUEST_HEADER)) {
			url = messageSource.getMessage(REDIRECT_URL_ANDROID, new String[] {redirectUrlKey, secCode},Locale.getDefault());
		}
		
		modelandView.addObject("url", url);

		return modelandView;
	}
	
	
	

	/*@RequestMapping(value = { "/getLoginAddOns" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@BoundaryLogger
	public @ResponseBody NsResponse getLoginAddOns(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, GET_LOGIN_FEATURES: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse;
		try {
			nsResponse = registrationService.getLoginFeatures(nsRequest);
			nsResponse.setAccountInfo(nsRequest.getAccountInfo());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_LOGIN_ADDONS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_LOGIN_ADDONS);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_LOGIN_ADDONS);
		}
		LOGGER.debug("Response Sent for, GET_LOGIN_FEATURES: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}*/

	@ExceptionHandler(value = BusinessException.class)
	@BoundaryLogger
	public @ResponseBody NsResponse handleBusinessException(BusinessException be, Locale locale) {

		be.printStackTrace();
		NsResponse nsResponse = new NsResponse();

		ResponseData responseData = new ResponseData();
		if (be.getExceptionCode().equalsIgnoreCase(VersaWorkConstant.DEVICE_RESET_RESPONSE_CODE)) {
			responseData.setResult(VersaWorkConstant.Status_Codes.DEVICE_RESET_RESPONSE_CODE.getCode());
			responseData.setDescription(messageSource.getMessage(VersaWorkConstant.DEVICE_RESET_RESPONSE_MESSAGE, null,
					locale));
		} else if (be.getExceptionCode().equalsIgnoreCase(VersaWorkConstant.INVALID_ENDPOINT_RESPONSE_CODE)) {
			responseData.setResult(VersaWorkConstant.Status_Codes.INVALID_ENDPOINT_RESPONSE_CODE.getCode());
			responseData.setDescription(messageSource.getMessage(VersaWorkConstant.INVALID_ENDPOINT_RESPONSE_MESSAGE,
					null, locale));
		} else {
			responseData.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
			responseData.setDescription(messageSource.getMessage(be.getExceptionCode(), null, locale));
		}
		nsResponse.setResponseData(responseData); // Needs to be derived
		LOGGER.debug("Response Sent, : \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@ExceptionHandler
	@BoundaryLogger
	public @ResponseBody NsResponse handleSystemException(Exception se) {

		se.printStackTrace();
		NsResponse nsResponse = new NsResponse();

		ResponseData responseData = new ResponseData();
		responseData.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		responseData.setDescription(messageSource.getMessage(VersaWorkConstant.UNHANDLED_EXCPETION_MESSAGE, null,
				Locale.getDefault()));

		nsResponse.setResponseData(responseData); // Needs to be derived
		LOGGER.debug("Response Sent system Exception: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

	public static Date addDays(Date date, int numDays) {
		return new Date(date.getTime() + numDays * MILLIS_PER_DAY);
	}
	
	@RequestMapping(value = { "/forgotPin" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@BoundaryLogger
	public @ResponseBody NsResponse forgotPin(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, forgotPin: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse;
		try {
			nsResponse = registrationService.sendForgotPin(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		}
		LOGGER.debug("Response Sent for, forgotPin: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}
	
	@RequestMapping(value = { "/resendFogotPin" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@BoundaryLogger
	public @ResponseBody NsResponse resendFogotPin(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, resendFogotPin: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse;
		try {
			nsResponse = registrationService.sendForgotPin(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		}
		LOGGER.debug("Response Sent for, resendFogotPin: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}
	
	
	@RequestMapping(value = { "/validatePinCode" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@BoundaryLogger
	public @ResponseBody NsResponse validatePinCode(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse;
		LOGGER.debug("Request Receieved, validatePinCode: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = registrationService.validatePinCode(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VALIDATE_SECURITY_CODE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VALIDATE_SECURITY_CODE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VALIDATE_SECURITY_CODE);
		}
		LOGGER.debug("Response Sent, validatePinCode: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	@RequestMapping(value = { "/updatePin" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@BoundaryLogger
	public @ResponseBody NsResponse updatePin(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, updatePin: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse;
		try {
			nsResponse = registrationService.updateTempPin(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		}
		LOGGER.debug("Response Sent for, updatePin: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}
	
	@RequestMapping(value = { "/updateProfileInfo" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@BoundaryLogger
	public @ResponseBody NsResponse updateProfileInfo(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, updateProfileInfo: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse;
		try {
			nsResponse = registrationService.updateProfileInformation(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEND_SECURITY_CODE);
		}
		LOGGER.debug("Response Sent for, updateProfileInfo: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}


}