package com.versawork.http.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.interceptor.BoundaryLogger;
import com.versawork.http.service.ActivityLogService;
import com.versawork.http.service.LoginService;
import com.versawork.http.utils.Activity;

/**
 * @author Dheeraj
 * 
 */

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	private static final String SESSION_MAX_INACTIVE = "session.max.inactive.time";

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	MessageSource messageSource;

	@Autowired
	MessageSource sessionConfigMessageSource;

	@Autowired
	LoginService loginService;

	ObjectMapper mapper = null;

	@Autowired
	private ActivityLogController activityLogController;

	@Autowired
	Gson gson;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse login(@RequestBody NsRequest nsRequest, HttpServletRequest req)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, login Service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = loginService.loginService(nsRequest);
			nsResponse = loginService.forciblyVersionUpdate(nsResponse);
			Integer sessionMaxInactiveTime = Integer.parseInt(sessionConfigMessageSource.getMessage(
					SESSION_MAX_INACTIVE, null, nsRequest.getLocale()));
			LOGGER.info("Session Max Inactive Time : " + sessionMaxInactiveTime);

			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(sessionMaxInactiveTime);
			session.setAttribute("Id", session.getId());
			LOGGER.debug("New Session Id is: " + session.getId());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.LOGIN);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.LOGIN);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.LOGIN);
		}
		LOGGER.debug("Response Sent for, login service: \n" + gson.toJson(nsResponse));

		return nsResponse;

	}

	@RequestMapping(value = { "/forciblyVersionUpdate" }, method = RequestMethod.GET, produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse forciblyVersionUpdate() throws BusinessException, SystemException {
		return loginService.forciblyVersionUpdate(new NsResponse());
	}

	@RequestMapping(value = { "/logOut" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse logOut(@RequestBody NsRequest nsRequest, HttpServletRequest req)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, logout Service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = loginService.logOutService(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.LOG_OUT);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.LOG_OUT);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.LOG_OUT);
		}
		LOGGER.debug("Response Sent for, logout service: \n" + gson.toJson(nsResponse));

		return nsResponse;

	}

	@ExceptionHandler(value = BusinessException.class)
	@BoundaryLogger
	public @ResponseBody NsResponse handleBusinessException(BusinessException be, Locale locale) {
		NsResponse nsResponse = new NsResponse();
		// nsResponse.setAuthToken(authToken);
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
		LOGGER.debug("Response Sent, login Service: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@ExceptionHandler
	@BoundaryLogger
	public @ResponseBody NsResponse handleSystemException(Exception se) {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		responseData.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		responseData.setDescription(messageSource.getMessage(VersaWorkConstant.UNHANDLED_EXCPETION_MESSAGE, null,
				Locale.getDefault()));

		nsResponse.setResponseData(responseData); // Needs to be derived
		LOGGER.debug("Response Sent, login Service: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

}