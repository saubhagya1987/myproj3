package com.versawork.http.controller;

import java.util.Locale;

import org.apache.commons.lang3.exception.ExceptionUtils;
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
import com.versawork.http.service.ViewPatientDetailsService;
import com.versawork.http.utils.Activity;

/**
 * @author Sohaib
 * 
 */

@Controller
@RequestMapping("/viewPatientInfo")
public class ViewPatientDetailsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewPatientDetailsController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	private ViewPatientDetailsService viewPatientDetailsService;

	@Autowired
	Gson gson;

	@Autowired
	private ActivityLogController activityLogController;

	@RequestMapping(value = { "/downloadMuData" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse downloadMuData(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request recieved for, download MuData  service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.downloadMuData(nsRequest);
			LOGGER.debug("!!! ------ View Patient Details Controller(downloadMuData)"
					+ nsResponse.getViewPatientInfo().getPatientId());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DOWNLOAD_MUDATA);

		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DOWNLOAD_MUDATA);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DOWNLOAD_MUDATA);
		}

		LOGGER.debug("Response Sent for, download MuData  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/viewPatientDetails" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse viewPatientDetails(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, view Patient Details service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.viewPatientDetails(nsRequest);

			LOGGER.debug("!!! ------ View Patient Details Controller(viewPatientDetails)"
					+ nsResponse.getViewPatientInfo().getPatientId());
			asyncDownloadMU(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VIEW_PATIENT_DETAILS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VIEW_PATIENT_DETAILS);
		} catch (Exception sysExp) {

			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VIEW_PATIENT_DETAILS);
		}

		LOGGER.debug("Response Sent for, view Patient Details service: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}
//Not part of Engage 2.0
	/*@RequestMapping(value = { "/getVitalSigns" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getVitalSigns(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Vital Signs service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getVitalSigns(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_VITAL_SIGNS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_VITAL_SIGNS);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_VITAL_SIGNS);
		}

		LOGGER.debug("Response Sent for, get Vital Signs service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/
//Not Part of Engage 2.0
	/*@RequestMapping(value = { "/getProviderList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getProviderList(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Provider List service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getProviderList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PROVIDER_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PROVIDER_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PROVIDER_LIST);
		}

		LOGGER.debug("Response Sent for, get Provider List service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/
//Not Part of Engage 2.0
	/*@RequestMapping(value = { "/getallergiesList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getallergiesList(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Allergies List service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getAllergiesList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_ALLERGIES_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_ALLERGIES_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_ALLERGIES_LIST);
		}

		LOGGER.debug("Response Sent for, get Allergies List service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/
	//Not part of engage 2.0
	/*@RequestMapping(value = { "/getMedicationList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getMedicationList(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Medication List service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getMedicationList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_MEDICATION_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_MEDICATION_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_MEDICATION_LIST);
		}

		LOGGER.debug("Response Sent for, get Medication List service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/
//Not part of engage 2.0
	/*@RequestMapping(value = { "/getProblemList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getProblemList(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Problem List service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getProblemList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PROBLEM_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PROBLEM_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PROBLEM_LIST);
		}

		LOGGER.debug("Response Sent for, get Problem List service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/

	/*@RequestMapping(value = { "/getProcedureList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getProcedureList(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Procedure List service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getProcedureList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PROCEDURE_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PROCEDURE_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PROCEDURE_LIST);
		}

		LOGGER.debug("Response Sent for, get Procedure List service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/
	//Not Part of Engage 2.0
	/*@RequestMapping(value = { "/getLabResultList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getLabResultList(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Lab Result List service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getLabResultList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_LAB_RESULT_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_LAB_RESULT_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_LAB_RESULT_LIST);
		}

		LOGGER.debug("Response Sent for, get LabResult List service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/
//Not Part of Engage 2.0
	/*@RequestMapping(value = { "/getCarePlanList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getCarePlanList(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Care Plan List service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getCarePlanList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_CAREPLAN_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_CAREPLAN_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_CAREPLAN_LIST);
		}

		LOGGER.debug("Response Sent for, get Care Plan List service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/
//Not Part of Engage 2.0 
	/*@RequestMapping(value = { "/getInpatientMetaData" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getInpatientMetaData(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Inpatient MetaData: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getInpatientMetaData(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_METADATA);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_METADATA);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_METADATA);
		}

		LOGGER.debug("Response Sent for, get Inpatient MetaData: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/
	//Not Part of Engage 2.0
	/*@RequestMapping(value = { "/getInpatientDiagnosisList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getInpatientDiagnosisList(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Inpatient Diagnosis List service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getInpatientDiagnosisList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_DIAGNOSIS_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_DIAGNOSIS_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_DIAGNOSIS_LIST);
		}

		LOGGER.debug("Response Sent for, get Inpatient Diagnosis List service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/
//Not Part of Engage 2.0
	/*@RequestMapping(value = { "/getInpatientFunctionalStatusList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getInpatientFunctionalStatusList(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Inpatient Functional StatusList service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getInpatientFunctionalStatusList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_FUNCTIONAL_STATUS_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_FUNCTIONAL_STATUS_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_FUNCTIONAL_STATUS_LIST);
		}
		LOGGER.debug("Response Sent for, get Inpatient Functional StatusList service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/
// Not Part of engage 2.0
	/*@RequestMapping(value = { "/getInpatientImmunalizationList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getInpatientImmunalizationList(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Inpatient Immunalization List service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getInpatientImmunalizationList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_IMMUNALIZATION_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_IMMUNALIZATION_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_IMMUNALIZATION_LIST);
		}
		LOGGER.debug("Response Sent for, get Inpatient Immunalization List service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/
//Not Part of engage 2.0
	/*@RequestMapping(value = { "/getInpatientHospitalisationReason" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getInpatientHospitalisationReason(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Inpatient Hospitalisation Reason service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getInpatientHospitalisationReason(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_HOSPITALIZATION_REASON);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_HOSPITALIZATION_REASON);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_HOSPITALIZATION_REASON);
		}
		LOGGER.debug("Response Sent for, get Inpatient Hospitalisation Reason service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/

	@RequestMapping(value = { "/transmitEHR" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse transmitEHR(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, transmit EHR: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.transmitEHR(nsRequest);
			LOGGER.debug("!!! ------ View Patient Details Controller(transmitEHR)"
					+ nsResponse.getViewPatientInfo().getPatientId());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.TRANSMIT_EHR);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.TRANSMIT_EHR);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.TRANSMIT_EHR);
		}

		LOGGER.debug("Response Sent for, transmit EHR: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/transmitEHRActivityLog" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse transmitEHRActivityLog(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, transmit EHR Activity Log: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.transmitEHRActivityLog(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.TRANSMIT_EHR_ACTIVITY_LOG);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.TRANSMIT_EHR_ACTIVITY_LOG);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.TRANSMIT_EHR_ACTIVITY_LOG);
		}
		LOGGER.debug("Response Sent for, transmit EHR Activity Log: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	/*
	 * @RequestMapping(value = { "/accessHistory" },method = RequestMethod.POST,
	 * consumes = "application/json", produces = "application/json")
	 * 
	 * @BoundaryLogger public @ResponseBody NsResponse
	 * accessHistory(@RequestBody NsRequest nsRequest) { //call user defined
	 * method as implemented by will return nsResponse;
	 * 
	 * }
	 */

	@RequestMapping(value = { "/accessHistory" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse accessHistory(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, access History: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.accessHistory(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.ACCESS_HISTORY);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.ACCESS_HISTORY);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.ACCESS_HISTORY);
		}
		LOGGER.debug("Response Sent for, access History: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/vdtReporting" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse vdtReporting(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		try {
			LOGGER.debug("Request Sent for, vdt Reporting: \n" + gson.toJson(nsRequest));
			nsResponse = viewPatientDetailsService.vdtReporting(nsRequest);
			LOGGER.debug("Response for View Pateient  Details Controller: vdtReporting: " + gson.toJson(nsResponse));
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VDT_REPORTING);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			LOGGER.debug("Response for View Pateient  Details Controller: vdtReporting: " + gson.toJson(nsResponse));
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VDT_REPORTING);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			LOGGER.debug("Response for View Pateient  Details Controller: vdtReporting: " + gson.toJson(nsResponse));
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VDT_REPORTING);
		}
		return nsResponse;
	}

	@RequestMapping(value = { "/vdtReportingAccessHistory" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse vdtReportingAccessHistory(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		try {
			LOGGER.debug("Request Sent for, vdt Reporting Access History: \n" + gson.toJson(nsRequest));
			nsResponse = viewPatientDetailsService.vdtReportingAccessHistory(nsRequest);
			LOGGER.debug("Response for View Pateient  Details Controller: vdtReportingAccessHistory: "
					+ gson.toJson(nsResponse));
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VDT_ACCESS_HISTORY);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			LOGGER.debug("Response for View Pateient  Details Controller: vdtReportingAccessHistory: "
					+ gson.toJson(nsResponse));
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VDT_ACCESS_HISTORY);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			LOGGER.debug("Response for View Pateient  Details Controller: vdtReportingAccessHistory: "
					+ gson.toJson(nsResponse));
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VDT_ACCESS_HISTORY);
		}
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
		LOGGER.debug("!! Business Exception: Response Sent, View Patient Details service: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@ExceptionHandler
	@BoundaryLogger
	public @ResponseBody NsResponse handleSystemException(Exception se) {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		responseData.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());

		LOGGER.debug(ExceptionUtils.getStackTrace(se));
		responseData.setDescription(messageSource.getMessage(VersaWorkConstant.UNHANDLED_EXCPETION_MESSAGE, null,
				Locale.getDefault()));

		nsResponse.setResponseData(responseData); // Needs to be derived
		LOGGER.debug("!! System Exception: Response Sent, View Patient Details service: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@Async
	private void asyncDownloadMU(NsRequest nsRequest) {
		// NsResponse nsResponse = new NsResponse();
		LOGGER.debug("async Download MU Data");
		try {// nsRequest.getViewPatientInfo().getDocType().equalsIgnoreCase("pdf"))
			NsRequest nsRequestDemo = nsRequest;
			nsRequestDemo.getViewPatientInfo().setDocType("pdf");
			// nsRequest.getViewPatientInfo().getVersion()
			nsRequestDemo.getViewPatientInfo().setVersion("summary");
			LOGGER.info("asyncDownload Type : " + nsRequestDemo.getViewPatientInfo().getDocType());
			/* nsResponse = */viewPatientDetailsService.downloadMuData(nsRequestDemo);
			nsRequestDemo.getViewPatientInfo().setVersion("toc");
			LOGGER.info("asyncDownload 2");
			/* nsResponse = */viewPatientDetailsService.downloadMuData(nsRequestDemo);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error while saving async Download MU Data : " + e.getMessage());
		}
	}
	
	@RequestMapping(value = { "/getPatientInformation" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientInformation(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Patient Information service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getPatientInformation(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_VITAL_SIGNS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_VITAL_SIGNS);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_VITAL_SIGNS);
		}

		LOGGER.debug("Response Sent for, get Patient Information service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	@RequestMapping(value = { "/getVisitDetails" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getVisitDetails(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get Visit Details Information service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = viewPatientDetailsService.getVisitDetails(nsRequest);			
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());			
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);			
		}
		LOGGER.debug("Response Sent for, get Visit Details Information service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
}