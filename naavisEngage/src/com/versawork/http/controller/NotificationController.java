package com.versawork.http.controller;

import java.util.List;
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
import com.versawork.http.dataobject.WarningMessages;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.interceptor.BoundaryLogger;
import com.versawork.http.service.ActivityLogService;
import com.versawork.http.service.NotificationService;
import com.versawork.http.utils.Activity;

/**
 * @author Dheeraj
 * 
 */

@Controller
@RequestMapping("/notificationService")
public class NotificationController {

	static Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	private NotificationService notificationService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	private ActivityLogController activityLogController;

	@Autowired
	Gson gson;

	@RequestMapping(value = { "/getBloodPressureReminderResponse" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getBloodPressureReminderResponse(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get BloodPressure Reminder Response: \n" + gson.toJson(nsRequest));
		try {

			nsResponse = notificationService.getBloodPressureReminderResponse(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.GET_BLOODPRESSURE_REMINDER_RESPONSE_NOTIFICATION);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.GET_BLOODPRESSURE_REMINDER_RESPONSE_NOTIFICATION);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.GET_BLOODPRESSURE_REMINDER_RESPONSE_NOTIFICATION);
		}

		LOGGER.debug("Response Sent for, get BloodPressure Reminder Response : \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/getMedicationReminderResponse" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getMedicationReminderResponse(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Medication Reminder Response: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = notificationService.getMedicationReminderResponse(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.GET_MEDICATION_REMINDER_RESPONSE_NOTIFICATION);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.GET_MEDICATION_REMINDER_RESPONSE_NOTIFICATION);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.GET_MEDICATION_REMINDER_RESPONSE_NOTIFICATION);
		}

		LOGGER.debug("Response Sent for, get Medication Reminder Response : \n" + gson.toJson(nsResponse));

		return nsResponse;
	}
   //Not Part of Engage 2.0
	/*@RequestMapping(value = { "/getPatientScheduledAppointments" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientScheduledAppointments(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Patient Scheduled Appointments: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = notificationService.getPatientScheduledAppointments(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_SCHEDULE_APPTS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_SCHEDULE_APPTS);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_SCHEDULE_APPTS);
		}

		LOGGER.debug("Response Sent for, get Patient Scheduled Appointments: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/
//Not Part of Engage 2.0
	/*@RequestMapping(value = { "/getPatientFutureConfirmedAppointments" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientFutureConfirmedAppointments(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Patient Future Confirmed Appointments: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = notificationService.getFuturePatientConfirmedAppointments(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_FUTURE_CONFIRMED_APPTS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_FUTURE_CONFIRMED_APPTS);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_FUTURE_CONFIRMED_APPTS);
		}

		LOGGER.debug("Response Sent for, get Patient Future Confirmed Appointments: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/

	@RequestMapping(value = { "/getPatientFutureScheduledAppointments" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientFutureScheduledAppointments(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Patient Future Scheduled Appointments: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = notificationService.getFutureScheduledAppointments(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_FUTURE_SCHEDULE_APPTS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_FUTURE_SCHEDULE_APPTS);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_FUTURE_SCHEDULE_APPTS);
		}

		LOGGER.debug("Response Sent for, get Patient Future Scheduled Appointments: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
//Not Part of engage 2.0
	/*@RequestMapping(value = { "/getPatientConfirmedAppointments" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientConfirmedAppointments(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Patient Confirmed Appointments: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = notificationService.getPatientConfirmedAppointments(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_CONFIRMED_APPTS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_CONFIRMED_APPTS);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_CONFIRMED_APPTS);
		}

		LOGGER.debug("Response Sent for, get Patient Confirmed Appointments: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/

	@RequestMapping(value = { "/getBloodPressureNotificationInactiveAlert" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody List<WarningMessages> getBloodPressureNotificationInactiveAlert(
			@RequestBody NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Notification Inactive Alert Service: \n" + gson.toJson(nsRequest));
		List<WarningMessages> message = null;
		try {
			message = notificationService.getBloodPressureNotificationInactiveAlert(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.GET_BLOODPRRESSURE_NOTIFICATION_INACTIVE_ALERT);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.GET_BLOODPRRESSURE_NOTIFICATION_INACTIVE_ALERT);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.GET_BLOODPRRESSURE_NOTIFICATION_INACTIVE_ALERT);
		}

		LOGGER.debug("Response Sent for, get Notification Inactive Alert Service: \n" + gson.toJson(nsResponse));
		return message;
	}

	@RequestMapping(value = { "/getMedicationNotificationInactiveAlert" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody List<WarningMessages> getMedicationNotificationInactiveAlert(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Notification Inactive Alert Service: \n" + gson.toJson(nsRequest));

		List<WarningMessages> message = null;
		try {
			message = notificationService.getMedicationNotificationInactiveAlert(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.GET_MEDICATION_NOTIFICATION_INACTIVE_ALERT);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.GET_MEDICATION_NOTIFICATION_INACTIVE_ALERT);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse,
					Activity.GET_MEDICATION_NOTIFICATION_INACTIVE_ALERT);
		}

		LOGGER.debug("Response Sent for, get Notification Inactive Alert Service: \n" + gson.toJson(nsResponse));

		return message;
	}

	@RequestMapping(value = { "/getWarningMessages" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getWarningMessages(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get getWarningMessages : \n" + gson.toJson(nsRequest));
		try {
			nsResponse = notificationService.getWarningMessages(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_WARNING_MESSAGES);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_WARNING_MESSAGES);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_WARNING_MESSAGES);
		}

		LOGGER.debug("Response Sent for, getWarningMessages : \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/getNotificationsCount" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getNotificationsCount(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Notification Count Service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = notificationService.getNotificationsCount(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_NOTIFICATION_COUNT);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_NOTIFICATION_COUNT);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_NOTIFICATION_COUNT);
		}

		LOGGER.debug("Response Sent for, get Notification Count Service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/setViewedTrue" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse setViewedTrue(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		LOGGER.debug("Request Receieved, get setViewedTrue Service: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = null;
		try {
			nsResponse = notificationService.setViewedTrue(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SER_VIEWED_TRUE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SER_VIEWED_TRUE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SER_VIEWED_TRUE);
		}

		LOGGER.debug("Response Sent for, get setViewedTrue Service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/setNotificationViewedTrue" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse setNotificationViewedTrue(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get setNotificationViewedTrue : \n" + gson.toJson(nsRequest));
		try {
			nsResponse = notificationService.setNotificationViewedTrue(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SER_NOTIFICATION_VIEWED_TRUE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SER_NOTIFICATION_VIEWED_TRUE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SER_NOTIFICATION_VIEWED_TRUE);
		}

		LOGGER.debug("Response Sent for, setNotificationViewedTrue : \n" + gson.toJson(nsResponse));

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

	@RequestMapping(value = { "/flushNotificationsForAndroid" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse flushNotificationsForAndroid(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Notification Count Service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = notificationService.flushNotificationsForAndroid(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.FLUSH_ACTIVE_NOTIFICATION_ALERT);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.FLUSH_ACTIVE_NOTIFICATION_ALERT);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.FLUSH_ACTIVE_NOTIFICATION_ALERT);
		}

		LOGGER.debug("Response Sent for, flushActive Notification Service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	@RequestMapping(value = { "/getPatientAppointments" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientAppointments(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Patient  Appointments: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = notificationService.getPatientAppointments(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_SCHEDULE_APPTS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_SCHEDULE_APPTS);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_SCHEDULE_APPTS);
		}

		LOGGER.debug("Response Sent for, get Patient  Appointments: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	@RequestMapping(value = { "/loadMorePatientAppointments" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse loadMorePatientAppointments(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, load more Patient  Appointments: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = notificationService.loadMorePatientAppointments(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_SCHEDULE_APPTS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_SCHEDULE_APPTS);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_SCHEDULE_APPTS);
		}

		LOGGER.debug("Response Sent for, load more Patient  Appointments: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	@RequestMapping(value = { "/searchDetails" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse searchDetails(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, get Patient  Appointments: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = notificationService.searchDetails(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_SCHEDULE_APPTS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_SCHEDULE_APPTS);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_SCHEDULE_APPTS);
		}

		LOGGER.debug("Response Sent for, get Patient  Appointments: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
}
