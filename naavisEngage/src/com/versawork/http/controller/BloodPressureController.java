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
import com.versawork.http.service.BloodPressureService;
import com.versawork.http.utils.Activity;

/**
 * @author Dheeraj
 * 
 */

@Controller
@RequestMapping("/bloodpressure")
public class BloodPressureController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BloodPressureController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	BloodPressureService bloodPressureService;

	@Autowired
	private ActivityLogController activityLogController;

	@Autowired
	private Gson gson;

	@RequestMapping(value = { "/saveBloodPressure" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse saveBloodPressure(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, Save BloodPressure service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.saveBloodPressure(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_BLOODPRESSURE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_BLOODPRESSURE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_BLOODPRESSURE);
		}

		LOGGER.debug("Response Sent for, Save BloodPressure service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/getBloodPressure" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getBloodPressure(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getBloodPressure list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getBloodPressure(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		}

		LOGGER.debug("Response Sent for, getBloodPressure list service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/editBloodPressure" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse editBloodPressure(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,editBloodPressure list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.editBloodPressure(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EDIT_BLOODPRESSURE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EDIT_BLOODPRESSURE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EDIT_BLOODPRESSURE);
		}
		LOGGER.debug("Response Sent for, editBloodPressure list service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/deleteBloodPressure" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse deleteBloodPressureDeleteFlag(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, delete Blood Pressure: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.deleteBloodPressure(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DELETE_BLOODPRESSURE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DELETE_BLOODPRESSURE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DELETE_BLOODPRESSURE);
		}

		LOGGER.debug("Response Sent for,  delete Blood Pressure: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/saveBloodPressureReminder" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse saveBloodPressureReminder(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, BloodPressure Service (save BloodPressure Reminder): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.saveBloodPressureReminder(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_BLOODPRESSURE_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_BLOODPRESSURE_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_BLOODPRESSURE_REMINDER);
		}

		LOGGER.debug("Response Sent for, BloodPressure Service(save BloodPressure Reminder): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/getBloodPressureReminders" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getBloodPressureReminders(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, BloodPressure Service (get BloodPressure Reminder): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getBloodPressureReminders(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE_REMINDER);
		}

		LOGGER.debug("Response Sent for, BloodPressure Service(get BloodPressure Reminder): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/deleteBloodPressureReminder" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse deleteBloodPressureReminder(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, BloodPressure Service (delete BloodPressure Reminder): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.deleteBloodPressureReminder(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DELETE_BLOODPRESSURE_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DELETE_BLOODPRESSURE_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DELETE_BLOODPRESSURE_REMINDER);
		}

		LOGGER.debug("Response Sent for, BloodPressure Service(delete BloodPressure Reminder): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/editBloodPressureReminder" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse editBloodPressureReminder(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, BloodPressure Service (edit BloodPressure Reminder): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.editBloodPressureReminder(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EDIT_BLOODPRESSURE_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EDIT_BLOODPRESSURE_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EDIT_BLOODPRESSURE_REMINDER);
		}

		LOGGER.debug("Response Sent for, BloodPressure Service(edit BloodPressure Reminder): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/saveBloodPressureResponse" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody NsResponse saveReminderResponse(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, saveBloodPressureResponse: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.saveBloodPressureResponse(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_BLOODPRESSURE_RESPONSE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_BLOODPRESSURE_RESPONSE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_BLOODPRESSURE_RESPONSE);
		}

		LOGGER.debug("Response Sent for, saveBloodPressureResponse: \n" + gson.toJson(nsResponse));

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

	@RequestMapping(value = { "/restoreBloodPressureReminders" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse restoreBloodPressureReminders(@RequestBody NsRequest nsRequest) {

		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, Restore BloodPressure Service (restore BloodPressure Reminder): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.restoreBloodPressureReminders(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.RESTORE_BLOODPRESSURE_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.RESTORE_BLOODPRESSURE_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.RESTORE_BLOODPRESSURE_REMINDER);
		}

		LOGGER.debug("Response Sent for, Restore BloodPressure Service(restore BloodPressure Reminder): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}

}
