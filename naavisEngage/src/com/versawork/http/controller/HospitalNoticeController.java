package com.versawork.http.controller;

import java.util.List;
import java.util.Locale;

import net.sf.cglib.core.Local;

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
import com.versawork.http.dataobject.NsHospitalNotice;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.interceptor.BoundaryLogger;
import com.versawork.http.service.ActivityLogService;
import com.versawork.http.service.HospitalNoticeService;
import com.versawork.http.utils.Activity;

/**
 * @author Dheeraj
 * 
 * @author Sohaib
 * 
 */
@Controller
@RequestMapping("/hospitalService")
public class HospitalNoticeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HospitalNoticeController.class);

	@Autowired
	private HospitalNoticeService hospitalNoticeServiceImpl;

	@Autowired
	MessageSource messageSource;

	@Autowired
	private ActivityLogController activityLogController;

	@Autowired
	Gson gson;

	@RequestMapping(value = { "/getHospitalNotice" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getHospitalNotice(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, getHospitalNotice Service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = hospitalNoticeServiceImpl.getHospitalNotice(nsRequest);
			try {
				if (nsRequest.getSetAllViewed())
					markNoticeViewed(nsRequest, nsResponse.getHospitalNotices());
			} catch (Exception e) {
				LOGGER.error("Sorry.Error while setting notice viewed true!!!!");
			}
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_HOSPITAL_NOTICE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_HOSPITAL_NOTICE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_HOSPITAL_NOTICE);
		}

		LOGGER.debug("Response Sent for, getHospitalNotice service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/getHospitalNoticeForGuest" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getHospitalNoticeForGuest(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, getHospitalNotice Service for guest: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = hospitalNoticeServiceImpl.getHospitalNotice(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_HOSPITAL_NOTICE_FOR_GUEST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_HOSPITAL_NOTICE_FOR_GUEST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_HOSPITAL_NOTICE_FOR_GUEST);
		}

		LOGGER.debug("Response Sent for, getHospitalNotice service for guest: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@Async
	private void markNoticeViewed(NsRequest nsRequest, List<NsHospitalNotice> nsHospitalNotice)
			throws BusinessException, SystemException {
		hospitalNoticeServiceImpl.markNoticeViewed(nsRequest, nsHospitalNotice);
	}

	@RequestMapping(value = { "/noticeViewed" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse noticeViewed(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, noticeViewed Service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = hospitalNoticeServiceImpl.setNoticeViewed(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.HOSPITAL_NOTICE_VIEWED);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.HOSPITAL_NOTICE_VIEWED);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.HOSPITAL_NOTICE_VIEWED);
		}

		LOGGER.debug("Response Sent for, noticeViewed service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/gethospitalList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse gethospitalList(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, email Validation: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = hospitalNoticeServiceImpl.getHospitalLists(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_HOSPITAL_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_HOSPITAL_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_HOSPITAL_LIST);
		}
		LOGGER.debug("Response Sent for, email Validation: \n" + gson.toJson(nsResponse));

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
		LOGGER.debug("Response Sent, getHospitalNotice Service: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@ExceptionHandler(value = BusinessException.class)
	@BoundaryLogger
	public @ResponseBody NsResponse handleBusinessException(BusinessException be, Locale locale) {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		if (be.getExceptionCode().equalsIgnoreCase(VersaWorkConstant.DEVICE_RESET_RESPONSE_CODE)) {
			responseData.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.DEVICE_RESET_RESPONSE_CODE, null, locale)));
			responseData.setDescription(messageSource.getMessage(VersaWorkConstant.DEVICE_RESET_RESPONSE_MESSAGE, null,
					locale));
		} else if (be.getExceptionCode().equalsIgnoreCase(VersaWorkConstant.INVALID_ENDPOINT_RESPONSE_CODE)) {
			responseData.setResult(Integer.parseInt(messageSource.getMessage(
					VersaWorkConstant.INVALID_ENDPOINT_RESPONSE_CODE, null, locale)));
			responseData.setDescription(messageSource.getMessage(VersaWorkConstant.INVALID_ENDPOINT_RESPONSE_MESSAGE,
					null, locale));
		} else {
			responseData.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.FAILURE_RESPONSE_CODE,
					null, locale)));
			responseData.setDescription(messageSource.getMessage(be.getExceptionCode(), null, locale));
		}
		nsResponse.setResponseData(responseData); // Needs to be derived
		LOGGER.debug("Response Sent, getHospitalNotice Service: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}
}
