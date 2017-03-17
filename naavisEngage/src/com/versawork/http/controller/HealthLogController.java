package com.versawork.http.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import com.versawork.http.model.PatientCalorieCount;
import com.versawork.http.model.PatientOverallStress;
import com.versawork.http.service.BloodPressureService;
import com.versawork.http.service.ViewPatientDetailsService;
import com.versawork.http.utils.Activity;

@Controller
@RequestMapping("/healthLog")
public class HealthLogController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HealthLogController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	private BloodPressureService bloodPressureService;

	@Autowired
	Gson gson;

	@Autowired
	private ActivityLogController activityLogController;
	@RequestMapping(value = { "/getHealthLog" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse getHealthLog(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getHealthLog  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getHealthLog(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		}

		LOGGER.debug("Response Sent for, getHealthLog  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	@RequestMapping(value = { "/addWeight" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse addWeight(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,addWeight  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.addWeight(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		}

		LOGGER.debug("Response Sent for, addWeight  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	@RequestMapping(value = { "/getWeight" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse getWeight(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getWeight list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getWeight(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		}

		LOGGER.debug("Response Sent for, getWeight list service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	@RequestMapping(value = { "/deleteWeight" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse deleteWeight(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,deleteWeight list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.deleteWeight(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		}

		LOGGER.debug("Response Sent for, deleteWeight list service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	@RequestMapping(value = { "/getPatientOverallStress" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientOverallStress(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getPatientOverallStress list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getPatientOverallStress(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			
		}

		LOGGER.debug("Response Sent for, getPatientOverallStress list service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	@RequestMapping(value = { "/getPatientStepCount" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientStepCount(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getPatientStepCount list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getPatientStepCount(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			
		}

		LOGGER.debug("Response Sent for, getPatientStepCount list service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
	@RequestMapping(value = { "/getPatientSleep" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientSleep(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getPatientSleep list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getPatientSleep(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			
		}

		LOGGER.debug("Response Sent for, getPatientSleep list service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	@RequestMapping(value = { "/getPatientHeartAge" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientHeartAge(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getPatientHeartAge list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getPatientHeartAge(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			
		}

		LOGGER.debug("Response Sent for, getPatientHeartAge list service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	@RequestMapping(value = { "/getPatientHeartRate" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientHeartRate(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getPatientHeartRate list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getPatientHeartRate(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			
		}

		LOGGER.debug("Response Sent for, getPatientHeartRate list service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	@RequestMapping(value = { "/getPatientActiveTime" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientActiveTime(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getPatientActiveTime list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getPatientActiveTime(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			
		}

		LOGGER.debug("Response Sent for, getPatientActiveTime list service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	@RequestMapping(value = { "/getPatientCalorieCount" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientCalorieCount(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getPatientCalorieCount list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getPatientCalorieCount(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			
		}

		LOGGER.debug("Response Sent for, getPatientCalorieCount list service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	@RequestMapping(value = { "/getPatientDistance" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientDistance(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getPatientDistance list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getPatientDistance(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			
		}

		LOGGER.debug("Response Sent for, getPatientDistance list service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
	@RequestMapping(value = { "/savePatientOverallStress" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse savePatientOverallStress(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,savePatientOverallStress  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.savePatientOverallStress(nsRequest);			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);			
		}

		LOGGER.debug("Response Sent for, savePatientOverallStress  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	@RequestMapping(value = { "/savePatientHeartRate" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse savePatientHeartRate(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,savePatientHeartRate  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.savePatientHeartRate(nsRequest);			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);			
		}

		LOGGER.debug("Response Sent for, savePatientHeartRate  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	@RequestMapping(value = { "/savePatientHeartAge" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse savePatientHeartAge(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,savePatientHeartAge  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.savePatientHeartAge(nsRequest);			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);			
		}

		LOGGER.debug("Response Sent for, savePatientHeartAge  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
	@RequestMapping(value = { "/savePatientStepCount" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse savePatientStepCount(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,savePatientStepCount  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.savePatientStepCount(nsRequest);			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);			
		}

		LOGGER.debug("Response Sent for, savePatientStepCount  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
	@RequestMapping(value = { "/savePatientCalorieCount" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse savePatientCalorieCount(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,savePatientCalorieCount  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.savePatientCalorieCount(nsRequest);			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);			
		}

		LOGGER.debug("Response Sent for, savePatientCalorieCount  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
	@RequestMapping(value = { "/savePatientSleep" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse savePatientSleep(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,savePatientSleep  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.savePatientSleep(nsRequest);			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);			
		}

		LOGGER.debug("Response Sent for, savePatientSleep  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
	@RequestMapping(value = { "/savePatientActiveTime" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse savePatientActiveTime(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,savePatientActiveTime  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.savePatientActiveTime(nsRequest);			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);			
		}

		LOGGER.debug("Response Sent for, savePatientActiveTime  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
	@RequestMapping(value = { "/savePatientDistance" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse savePatientDistance(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,savePatientDistance  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.savePatientDistance(nsRequest);			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);			
		}

		LOGGER.debug("Response Sent for, savePatientDistance  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
	
	
	
	
	@RequestMapping(value = { "/getMyHealth" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse getMyHealth(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getMyHealth  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getMyHealth(nsRequest);			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);			
		}

		LOGGER.debug("Response Sent for, getMyHealth  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
	@RequestMapping(value = { "/getMyHealthInfo" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse getMyHealthInfo(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,getMyHealthInfo  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.getMyHealthInfo(nsRequest);			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);			
		}

		LOGGER.debug("Response Sent for, getMyHealthInfo  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
	
	
	
	@RequestMapping(value = { "/updateWeight" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@BoundaryLogger
	public @ResponseBody NsResponse updateWeight(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved,updateWeight list service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = bloodPressureService.updateWeight(nsRequest);
			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_BLOODPRESSURE);
		}

		LOGGER.debug("Response Sent for, updateWeight list service: \n" + gson.toJson(nsResponse));
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
