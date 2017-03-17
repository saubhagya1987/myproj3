package com.versawork.http.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.versawork.http.service.SystemAdministratorService;
import com.versawork.http.utils.Activity;

/**
 * @author Dheeraj
 * 
 */

@Controller
@RequestMapping("/systemAdmim")
public class SystemAdministratorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemAdministratorController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	SystemAdministratorService systemAdministratorService;

	@Autowired
	private ActivityLogController activityLogController;

	@Autowired
	Gson gson;

	@RequestMapping(value = { "/getDirectAddressInfo" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getDirectAddressInfo(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Direct Address Info service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = systemAdministratorService.getDirectAddressInfo(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_DIRECT_ADR_INFO);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_DIRECT_ADR_INFO);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_DIRECT_ADR_INFO);
		}

		LOGGER.debug("Response Sent for, get Direct Address Info service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/getNTPServerTime" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getNTPServerTime(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Direct Address Info service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = systemAdministratorService.getNTPServerTime(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_NTP_SYSTEM_TIME);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_NTP_SYSTEM_TIME);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_NTP_SYSTEM_TIME);
		}

		LOGGER.debug("Response Sent for, get Direct Address Info service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@ExceptionHandler(value = BusinessException.class)
	@BoundaryLogger
	public @ResponseBody NsResponse handleBusinessException(BusinessException be, Locale locale) {
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
		LOGGER.debug("Response Sent, BloodPressure service: \n" + gson.toJson(nsResponse));

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
		LOGGER.debug("Response Sent, BloodPressure service: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

}
