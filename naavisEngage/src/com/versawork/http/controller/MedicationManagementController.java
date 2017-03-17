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
import com.versawork.http.service.MedicationManagementService;
import com.versawork.http.utils.Activity;

/**
 * @author Dheeraj
 * 
 */

@Controller
@RequestMapping("/medicatnMangmntService")
public class MedicationManagementController {

	static Logger LOGGER = LoggerFactory.getLogger(MedicationManagementController.class);

	@Autowired
	private MedicationManagementService medicationManagementService;

	@Autowired
	MessageSource messageSource;

	/*
	 * @Autowired private RequestValidator requestValidator;
	 */

	@Autowired
	private ActivityLogController activityLogController;

	@Autowired
	Gson gson;

	@RequestMapping(value = { "/saveMedication" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse saveMedication(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, MedicationManagement Service (save Medication): \n" + gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.saveMedicatnInfo(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION);
		}

		LOGGER.debug("Response Sent for, MedicationManagement Service(save Medication): \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/getMedicatnList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getMedicatnList(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, MedicationManagement Service (get Medication List): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.getMedicatnList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_LIST);
		}

		LOGGER.debug("Response Sent for, MedicationManagement Service(get Medication List): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/editMedication" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse editMedication(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, MedicationManagement Service (edit Medication): \n" + gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.editMedicatnMgmtInfo(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EDIT_MEDICATION);
		} catch (BusinessException bussExp) {
			bussExp.printStackTrace();
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EDIT_MEDICATION);
		} catch (Exception sysExp) {
			sysExp.printStackTrace();
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EDIT_MEDICATION);
		}

		LOGGER.debug("Response Sent for, MedicationManagement Service(edit Medication): \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/deleteMedication" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse deleteMedicationReminder(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, MedicationManagement Service (delete Medication (Hard Delete)): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.deleteMedication(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DELETE_MEDICATION);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DELETE_MEDICATION);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DELETE_MEDICATION);
		}

		LOGGER.debug("Response Sent for, MedicationManagement Service(delete Medication(Hard Delete)): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}

	/*@RequestMapping(value = { "/saveMedicatnReminder" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse saveMedicatnReminder(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, MedicationManagement Service (save Medicatn Reminder): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.saveMedicatnReminder(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		}

		LOGGER.debug("Response Sent for, MedicationManagement Service(save Medicatn Reminder): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}*/

	@RequestMapping(value = { "/getMedicatnReminder" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getMedicatnReminder(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, MedicationManagement Service (get Medicatn Reminder): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.getMedicatnReminder(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_REMINDER_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_REMINDER_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_REMINDER_LIST);
		}

		LOGGER.debug("Response Sent for, MedicationManagement Service(get Medicatn Reminder): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/editMedicatnReminder" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse editMedicatnReminder(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, MedicationManagement Service (edit Medicatn Reminder): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.editMedicatnReminder(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EDIT_MEDICATION_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EDIT_MEDICATION_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EDIT_MEDICATION_REMINDER);
		}

		LOGGER.debug("Response Sent for, MedicationManagement Service(edit Medicatn Reminder): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}

	/*@RequestMapping(value = { "/deleteReminder" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse deleteReminder(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, MedicationManagement Service (delete Reminder): \n" + gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.deleteReminder(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DELETE_MEDICATION_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DELETE_MEDICATION_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.DELETE_MEDICATION_REMINDER);
		}

		LOGGER.debug("Response Sent for, MedicationManagement Service(delete Reminder): \n" + gson.toJson(nsResponse));

		return nsResponse;
	}*/

	@RequestMapping(value = { "/saveReminderResponse" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody NsResponse saveReminderResponse(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, saveReminderResponse: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.saveReminderResponse(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER_RESPONSE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER_RESPONSE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER_RESPONSE);
		}

		LOGGER.debug("Response Sent for, saveReminderResponse: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/getReminderResponse" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody NsResponse getReminderResponse(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, getReminderResponse: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.getReminderResponse(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_REMINDER_RESPONSE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_REMINDER_RESPONSE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_REMINDER_RESPONSE);
		}

		LOGGER.debug("Response Sent for, getReminderResponse: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/getFrequency" }, method = RequestMethod.POST, produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getFrequencyDesc(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		LOGGER.debug("Request Sent, getFrequency service: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = medicationManagementService.getFrequencyDesc(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_FREQUENCY);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_FREQUENCY);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_FREQUENCY);
		}
		LOGGER.debug("Response Sent, getFrequency service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/getMedicationMethodDesc" }, method = RequestMethod.GET, produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getMedicationMethodDesc() throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = medicationManagementService.getMedicationMethodDesc();
			// activityLogController.saveActivityLog(null, nsResponse,
			// Activity.GET_MEDICATION_METHOD_DESCRIPTION);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, Locale.getDefault());
			// activityLogController.saveActivityLog(null, nsResponse,
			// Activity.GET_MEDICATION_METHOD_DESCRIPTION);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			// activityLogController.saveActivityLog(null, nsResponse,
			// Activity.GET_MEDICATION_METHOD_DESCRIPTION);
		}
		LOGGER.debug("Response Sent, getMedicationMethodDesc service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/getMedicationKindDesc" }, method = RequestMethod.GET, produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getMedicationKindDesc() throws BusinessException, SystemException {
		NsResponse nsResponse = null;
		try {
			nsResponse = medicationManagementService.getMedicationKindDesc();
			// activityLogController.saveActivityLog(null, nsResponse,
			// Activity.GET_MEDICATION_KIND_DESCRIPTION);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, Locale.getDefault());
			// activityLogController.saveActivityLog(null, nsResponse,
			// Activity.GET_MEDICATION_KIND_DESCRIPTION);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			// activityLogController.saveActivityLog(null, nsResponse,
			// Activity.GET_MEDICATION_KIND_DESCRIPTION);
		}
		LOGGER.debug("Response Sent, getMedicationKindDesc service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/getMedicationDosageDesc" }, method = RequestMethod.GET, produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getMedicationDosageDesc() throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = medicationManagementService.getMedicationDosageDesc();
			// activityLogController.saveActivityLog(null, nsResponse,
			// Activity.GET_MEDICATION_DOSAGE_DESCRIPTION);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, Locale.getDefault());
			// activityLogController.saveActivityLog(null, nsResponse,
			// Activity.GET_MEDICATION_DOSAGE_DESCRIPTION);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			// activityLogController.saveActivityLog(null, nsResponse,
			// Activity.GET_MEDICATION_DOSAGE_DESCRIPTION);
		}
		LOGGER.debug("Response Sent, getMedicationKindDesc service: \n" + gson.toJson(nsResponse));
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
		LOGGER.debug("Response Sent, : \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/getMedicationImage" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getMedicationImage(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, MedicationManagement Service (save Medicatn Reminder): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.getMedicationImage(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		}

		LOGGER.debug("Response Sent for, MedicationManagement Service(save Medicatn Reminder): \n"
				+ gson.toJson(nsResponse));

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
		LOGGER.debug("Response Sent system Exception: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/restoreMedicatnList" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse restoreMedicatnList(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, MedicationManagement Service (get Medication List): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.restoreMedicatnList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_MEDICATION_LIST);
		}

		LOGGER.debug("Response Sent for, MedicationManagement Service(get Medication List): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}
	
	@RequestMapping(value = { "/saveMedicationManagement" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse saveMedicationManagement(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, MedicationManagement Service (saveMedicationManagement): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.saveMedicationManagement(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		}

		LOGGER.debug("Response Sent for, MedicationManagement Service(saveMedicationManagement): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}
	@RequestMapping(value = { "/getMedicationReminder" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getMedicationReminder(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, getMedicationManagement Service (getMedicationManagement): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.getMedicationManagement(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		}

		LOGGER.debug("Response Sent for, getMedicationManagement Service(getMedicationManagement): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}
	
	
	@RequestMapping(value = { "/editMedicationReminder" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse editMedicationReminder(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, editMedicationReminder Service (editMedicationManagement): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.editMedicationManagement(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		}

		LOGGER.debug("Response Sent for, editMedicationReminder Service(editMedicationManagement): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}
	@RequestMapping(value = { "/checkRefillDate" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse checkRefillDate(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, checkRefillDate Service (checkRefillDate): \n"
				+ gson.toJson(nsRequest));
		try {
			nsResponse = medicationManagementService.checkRefillDate(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_MEDICATION_REMINDER);
		}

		LOGGER.debug("Response Sent for, checkRefillDate Service(checkRefillDate): \n"
				+ gson.toJson(nsResponse));

		return nsResponse;
	}
	

}
