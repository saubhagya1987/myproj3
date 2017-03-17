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
import com.versawork.http.service.AccountService;
import com.versawork.http.utils.Activity;

/**
 * @author Dheeraj
 * 
 */

@Controller
@RequestMapping("/accountService")
public class AccountController {

	static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	// static Boolean isDebugEnabled = LOGGER.isDebugEnabled();
	// private static final String SESSION_MAX_INACTIVE =
	// "session.max.inactive.time";

	@Autowired
	private AccountService accountServiceImpl;

	@Autowired
	MessageSource messageSource;

	@Autowired
	private ActivityLogController activityLogController;

	@Autowired
	private Gson gson;

	/**
	 * AUDIT_LOGON_ID (-96) is for Backend SQL scripts AUDIT_LOGON_ID (-97) is
	 * for System exception occoured in the naavis application AUDIT_LOGON_ID
	 * (-98) is for Business exception occoured in the naavis application
	 * AUDIT_LOGON_ID (0-99) is for user Id of naavis engage application
	 * 
	 * Note: For certain usecases account ID will be null (It is not mandatory)
	 */

	@RequestMapping(value = { "/emailValidation" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse emailValidation(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, email Validation: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = accountServiceImpl.emailValidation(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EMAIL_VALIDATION);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EMAIL_VALIDATION);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.EMAIL_VALIDATION);
		}
		LOGGER.debug("Response Sent for, email Validation: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/savepreferredlanguage" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse savelanguagepreference(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, savepreferredlanguage : \n" + gson.toJson(nsRequest));
		try {
			nsResponse = accountServiceImpl.savepreferredlanguage(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_PREFERED_LANGUAGE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_PREFERED_LANGUAGE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_PREFERED_LANGUAGE);
		}
		LOGGER.debug("Response Sent for, savelanguagepreference: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/getAccountImagingDetails" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getAccountImagingDetails(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, getAccountImagingDetails: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = accountServiceImpl.getAccountImagingDetails(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_ACCOUNT_IMAGING_DETAILS);
		} catch (BusinessException bussExp) {
			bussExp.printStackTrace();
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());

			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_ACCOUNT_IMAGING_DETAILS);
		} catch (Exception sysExp) {
			sysExp.printStackTrace();
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_ACCOUNT_IMAGING_DETAILS);
		}
		LOGGER.debug("Response Sent for, getAccountImagingDetails: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	/*
	 * @RequestMapping(value = { "/createAccount" }, method =
	 * RequestMethod.POST, consumes = "application/json", produces =
	 * "application/json")
	 * 
	 * @BoundaryLogger public @ResponseBody NsResponse
	 * createAccount(@RequestBody NsRequest nsRequest, HttpServletRequest req) {
	 * 
	 * LOGGER.debug("Request Receieved, createAccount: \n" +
	 * gson.toJson(nsRequest)); try{ nsResponse =
	 * accountServiceImpl.createAccount(nsRequest); Integer
	 * sessionMaxInactiveTime =
	 * Integer.parseInt(messageSource.getMessage(SESSION_MAX_INACTIVE, null,
	 * Locale.US)); LOGGER.info("Session Max Inactive Time : " +
	 * sessionMaxInactiveTime);
	 * 
	 * HttpSession session=req.getSession();
	 * session.setMaxInactiveInterval(sessionMaxInactiveTime);
	 * session.setAttribute("Id", session.getId());
	 * LOGGER.debug("New Session Id is: "+session.getId());
	 * activityLogController.saveActivityLog((nsResponse.getAccountInfo() !=
	 * null) ? nsResponse.getAccountInfo().getAccountId() : 0, nsRequest,
	 * nsResponse,Activity.CREATE_ACCOUNT); } catch(BusinessException bussExp){
	 * nsResponse = handleBusinessException(bussExp,nsRequest.getLocale());
	 * activityLogController.saveActivityLog(nsRequest,
	 * nsResponse,Activity.CREATE_ACCOUNT); }catch(Exception sysExp){ nsResponse
	 * = handleSystemException(sysExp);
	 * activityLogController.saveActivityLog(nsRequest,
	 * nsResponse,Activity.CREATE_ACCOUNT); }
	 * 
	 * LOGGER.debug("Response Sent for, createAccount: \n" +
	 * gson.toJson(nsResponse));
	 * 
	 * return nsResponse; }
	 */

	@RequestMapping(value = { "/updateAccountPin" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse updateAccountPin(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		LOGGER.debug("Request Receieved, update Account Password: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.updateAccountPin(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UPDATE_ACCOUNT_PASSWORD);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UPDATE_ACCOUNT_PASSWORD);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UPDATE_ACCOUNT_PASSWORD);
		}

		LOGGER.debug("Response Sent for, update Account Password: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/validateAccountEmail" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse validateAccountEmail(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		LOGGER.debug("Request Receieved, validating mail id for request: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.emailValidation(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VALIDATE_ACCOUNT_EMAIL);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VALIDATE_ACCOUNT_EMAIL);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VALIDATE_ACCOUNT_EMAIL);
		}

		LOGGER.debug("Response Sent for, email validation: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/updateAccountPhone" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse updateAccountPhone(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		LOGGER.debug("Request Receieved, update Account Phone: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.updateAccountPhone(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UPDATE_ACCOUNT_PHONE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UPDATE_ACCOUNT_PHONE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UPDATE_ACCOUNT_PHONE);
		}

		LOGGER.debug("Response Sent for, update Account Phone: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/updateAccountPhoneNumber" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse updateAccountPhoneNumber(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		LOGGER.debug("Request Receieved, update Account Phone: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.updateAccountPhoneNumber(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UPDATE_ACCOUNT_PHONE);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UPDATE_ACCOUNT_PHONE);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UPDATE_ACCOUNT_PHONE);
		}

		LOGGER.debug("Response Sent for, update Account Phone: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/updateAccountEmail" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse updateAccountEmail(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		LOGGER.debug("Request Receieved, update Account Phone: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.updateAccountEmail(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UPDATE_ACCOUNT_EMAIL);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UPDATE_ACCOUNT_EMAIL);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UPDATE_ACCOUNT_EMAIL);
		}

		LOGGER.debug("Response Sent for, update Account Phone: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/resetAccountPin" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse resetAccountPin(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		LOGGER.debug("Request Receieved, reset Account Password: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.resetAccountPin(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.RESET_ACCOUNT_PASSWORD);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.RESET_ACCOUNT_PASSWORD);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.RESET_ACCOUNT_PASSWORD);
		}

		LOGGER.debug("Response Sent for, reset Account Password: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	/*
	 * @RequestMapping(value = { "/validateUnitNumber" }, method =
	 * RequestMethod.POST, consumes = "application/json", produces =
	 * "application/json")
	 * 
	 * @BoundaryLogger public @ResponseBody NsResponse
	 * validateUnitNumber(@RequestBody NsRequest nsRequest) throws
	 * BusinessException, SystemException {
	 * LOGGER.debug("Request Receieved, validateUnitNumber: \n" +
	 * gson.toJson(nsRequest));
	 * requestValidator.performBusinessRuleValidations(nsRequest); try{
	 * nsResponse = accountServiceImpl.validateUnitNumber(nsRequest);
	 * //activityLogController.saveActivityLog((nsResponse.getAccountInfo() !=
	 * null) ? nsResponse.getAccountInfo().getAccountId() : 0, nsRequest,
	 * nsResponse,Activity.VALIDATE_UNIT_NUMBER); }catch(BusinessException
	 * bussExp){ nsResponse =
	 * handleBusinessException(bussExp,nsRequest.getLocale()); //
	 * activityLogController.saveActivityLog(nsRequest,
	 * nsResponse,Activity.VALIDATE_UNIT_NUMBER); }catch(Exception sysExp){
	 * nsResponse = handleSystemException(sysExp); //
	 * activityLogController.saveActivityLog(nsRequest,
	 * nsResponse,Activity.VALIDATE_UNIT_NUMBER); }
	 * 
	 * LOGGER.debug("Response Sent for, validateUnitNumber: \n" +
	 * gson.toJson(nsResponse)); return nsResponse; }
	 */

	/*
	 * @RequestMapping(value = { "/upgradeAccount" }, method =
	 * RequestMethod.POST, consumes = "application/json", produces =
	 * "application/json")
	 * 
	 * @BoundaryLogger public @ResponseBody NsResponse
	 * upgradeAccount(@RequestBody NsRequest nsRequest) throws
	 * BusinessException, SystemException {
	 * LOGGER.debug("Request Receieved, upgradeAccount: \n" +
	 * gson.toJson(nsRequest));
	 * 
	 * try{ nsResponse = accountServiceImpl.upgradeAccount(nsRequest); //
	 * activityLogController.saveActivityLog((nsResponse.getAccountInfo() !=
	 * null) ? nsResponse.getAccountInfo().getAccountId() : 0, nsRequest,
	 * nsResponse,Activity.UPGRADE_ACCOUNT); }catch(BusinessException bussExp){
	 * nsResponse = handleBusinessException(bussExp,nsRequest.getLocale()); //
	 * activityLogController.saveActivityLog(nsRequest,
	 * nsResponse,Activity.UPGRADE_ACCOUNT); }catch(Exception sysExp){
	 * nsResponse = handleSystemException(sysExp); //
	 * activityLogController.saveActivityLog(nsRequest,
	 * nsResponse,Activity.UPGRADE_ACCOUNT); }
	 * 
	 * LOGGER.debug("Response Sent for, upgradeAccount: \n" +
	 * gson.toJson(nsResponse)); return nsResponse; }
	 */

	@RequestMapping(value = { "/appointmentConfirmation" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse appointmentConfirmation(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		LOGGER.debug("Request Receieved, appointmentConfirmation: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.appointmentConfirmation(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.APPOINTMENT_CONFIRMATION);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.APPOINTMENT_CONFIRMATION);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.APPOINTMENT_CONFIRMATION);
		}
		LOGGER.debug("Response Sent for, appointmentConfirmation: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	/*
	 * @RequestMapping(value = { "/getConfirmationAppointment" }, method =
	 * RequestMethod.GET) public @ResponseBody NsResponse
	 * getConfirmationAppointment(HttpServletRequest request) {
	 * 
	 * LOGGER.debug("Request Receieved, appointmentConfirmation: \n" +
	 * "Appoinment ID: " + request.getParameter("appointmentId") +
	 * "Confirmed By: " + request.getParameter("confirmBy") + "Confirmed Date: "
	 * + request.getParameter("confirmDate")); NsResponse nsResponse = new
	 * NsResponse(); try { nsResponse =
	 * accountServiceImpl.getConfirmationAppointment(request); } catch
	 * (BusinessException bussExp) { nsResponse =
	 * handleBusinessException(bussExp,nsRequest.getLocale());
	 * 
	 * } catch (Exception sysExp) { nsResponse = handleSystemException(sysExp);
	 * }
	 * 
	 * LOGGER.debug("Response Sent for, appointmentConfirmation: \n" +
	 * (nsResponse)); return nsResponse;
	 * 
	 * }
	 */

	@RequestMapping(value = { "/setPatientAppointment" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse setPatientAppointment(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		LOGGER.debug("Request Receieved, setPatientAppointment: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.saveAccountPatientAppointment(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_PATIENT_APPOINTMENT);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_PATIENT_APPOINTMENT);

		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SAVE_PATIENT_APPOINTMENT);
		}

		LOGGER.debug("Response Sent for, setPatientAppointment: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/getPatientAppointment" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientAppointment(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		LOGGER.debug("Request Receieved, getPatientAppointment: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.getAccountPatientAppointment(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_APPOINTMENT);
		} catch (BusinessException bussExp) {
			bussExp.printStackTrace();
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_APPOINTMENT);

		} catch (Exception sysExp) {
			sysExp.printStackTrace();
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_APPOINTMENT);

		}

		LOGGER.debug("Response Sent for, getPatientAppointment: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/confirmScheduledAppointment" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse confirmScheduledAppointment(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		LOGGER.debug("Request Receieved, CONFIRM_SCHEDULED_APPOINTMENT: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.confirmScheduledAppointment(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.CONFIRM_SCHEDULED_APPOINTMENT);
		} catch (BusinessException bussExp) {
			bussExp.printStackTrace();
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.CONFIRM_SCHEDULED_APPOINTMENT);

		} catch (Exception sysExp) {
			sysExp.printStackTrace();
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.CONFIRM_SCHEDULED_APPOINTMENT);

		}

		LOGGER.debug("Response Sent for, CONFIRM_SCHEDULED_APPOINTMENT: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/getPatientLabResult" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientLabResult(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		LOGGER.debug("Request Receieved, getPatientLabResult: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.getPatientLabResult(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_LAB_RESULTS);
		} catch (BusinessException bussExp) {
			bussExp.printStackTrace();
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_LAB_RESULTS);
		} catch (Exception sysExp) {
			sysExp.printStackTrace();
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_LAB_RESULTS);
		}

		LOGGER.debug("Response Sent for, getPatientLabResult: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
//Not Part of Engage 2.0
	/*@RequestMapping(value = { "/getPatientLabGroups" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientLabGroups(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		LOGGER.debug("Request Receieved, getPatientLabGroups: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.getPatientLabGroups(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_LAB_GROUPS);
		} catch (BusinessException bussExp) {
			bussExp.printStackTrace();
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_LAB_GROUPS);
		} catch (Exception sysExp) {
			sysExp.printStackTrace();
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_LAB_GROUPS);
		}

		LOGGER.debug("Response Sent for, getPatientLabGroups: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}*/

	@RequestMapping(value = { "/getPatientPrescriptionGroups" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientPrescriptionGroups(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		LOGGER.debug("Request Receieved, getPatientPrescriptionGroups: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.getPatientPrescriptionGroups(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_PRESCRIPTION_GROUPS);
		} catch (BusinessException bussExp) {
			bussExp.printStackTrace();
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_PRESCRIPTION_GROUPS);
		} catch (Exception sysExp) {
			sysExp.printStackTrace();
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_PRESCRIPTION_GROUPS);
		}

		LOGGER.debug("Response Sent for, getPatientPrescriptionGroups: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/getPatientLabTestHistory" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientLabTestHistory(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		LOGGER.debug("Request Receieved, getPatientLabResultTestHistory: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = new NsResponse();
		try {
			nsResponse = accountServiceImpl.getPatientLabTestHistory(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_LAB_TEST_HISTORY);
		} catch (BusinessException bussExp) {
			bussExp.printStackTrace();
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_LAB_TEST_HISTORY);
		} catch (Exception sysExp) {
			sysExp.printStackTrace();
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_LAB_TEST_HISTORY);
		}

		LOGGER.debug("Response Sent for, getPatientLabResultTestHistory: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/getPatientPrescription" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientPrescription(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, getPatientPrescription: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = accountServiceImpl.getPatientPrescription(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_PRESCRIPTION);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_PRESCRIPTION);

		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_PRESCRIPTION);

		}

		LOGGER.debug("Response Sent for, getPatientPrescription: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/getPatientProblemGroups" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientProblems(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, getPatientProblemGroups: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = accountServiceImpl.getPatientProblems(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_PROBLEM_GROUPS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_PROBLEM_GROUPS);

		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_PROBLEM_GROUPS);

		}

		LOGGER.debug("Response Sent for, getPatientProblemGroups: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	@RequestMapping(value = { "/getPatientAllergyGroup" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getPatientAllergy(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, getPatientAllergyGroup: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = accountServiceImpl.getPatientAllergies(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_ALLERGY_GROUPS);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_ALLERGY_GROUPS);

		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_PATIENT_ALLERGY_GROUPS);

		}

		LOGGER.debug("Response Sent for, getPatientAllergyGroup: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	/*
	 * @RequestMapping(value = { "/getConfApp" }, method = RequestMethod.GET)
	 * public @ResponseBody NsResponse getConfirmationAppointmentDummy() {
	 * 
	 * LOGGER.debug("Request Receieved, appointmentConf"); NsResponse nsResponse
	 * = new NsResponse(); try { nsResponse =
	 * accountServiceImpl.appointmentConfirmationThread(); } catch
	 * (BusinessException bussExp) { nsResponse =
	 * handleBusinessException(bussExp,nsRequest.getLocale());
	 * 
	 * } catch (Exception sysExp) { nsResponse = handleSystemException(sysExp);
	 * }
	 * 
	 * LOGGER.debug("Response Sent for, appointmentConf: \n" + (nsResponse));
	 * return nsResponse;
	 * 
	 * }
	 */

	/*
	 * @RequestMapping(value = { "/validateUserAccount" }, method =
	 * RequestMethod.POST, consumes = "application/json", produces =
	 * "application/json")
	 * 
	 * @BoundaryLogger public @ResponseBody NsResponse
	 * validateUserAccount(@RequestBody NsRequest nsRequest) throws
	 * BusinessException, SystemException {
	 * LOGGER.debug("Request Receieved, validateUserAccount: \n" +
	 * gson.toJson(nsRequest)); try{ nsResponse =
	 * accountServiceImpl.validateUserAccount(nsRequest);
	 * activityLogController.saveActivityLog((nsResponse.getAccountInfo() !=
	 * null) ? nsResponse.getAccountInfo().getAccountId() : 0, nsRequest,
	 * nsResponse,Activity.VALIDATE_USER_ACCOUNT); } catch(BusinessException
	 * bussExp){ nsResponse =
	 * handleBusinessException(bussExp,nsRequest.getLocale());
	 * activityLogController.saveActivityLog(nsRequest,
	 * nsResponse,Activity.VALIDATE_USER_ACCOUNT); } catch(Exception sysExp){
	 * sysExp.printStackTrace(); nsResponse = handleSystemException(sysExp);
	 * activityLogController.saveActivityLog(nsRequest,
	 * nsResponse,Activity.VALIDATE_USER_ACCOUNT); }
	 * 
	 * LOGGER.debug("Response Sent for, validateUserAccount: \n" +
	 * gson.toJson(nsResponse)); return nsResponse; }
	 */

	@ExceptionHandler(value = BusinessException.class)
	@BoundaryLogger
	public @ResponseBody NsResponse handleBusinessException(BusinessException be, Locale locale) {
		NsResponse nsResponse = new NsResponse();
		LOGGER.error("In handleBusinessException :    " + be.getExceptionCode());
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
	
	@RequestMapping(value = { "/getImmunalization" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getImmunalization(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get patient Immunalization List service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = accountServiceImpl.getPatientImmunalization(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_IMMUNALIZATION_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_IMMUNALIZATION_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_IMMUNALIZATION_LIST);
		}
		LOGGER.debug("Response Sent for, get patient Immunalization List service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	@RequestMapping(value = { "/getTestResult" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getTestResult(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get patient Test Result service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = accountServiceImpl.getTestResult(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_IMMUNALIZATION_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_IMMUNALIZATION_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_IMMUNALIZATION_LIST);
		}
		LOGGER.debug("Response Sent for, get patient Test Result  service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	
	
	@RequestMapping(value = { "/getTestResultInfo" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getTestResultInfo(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Sent for, get patient Test Result Information service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = accountServiceImpl.getTestResultInfo(nsRequest);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_IMMUNALIZATION_LIST);
		} catch (BusinessException bussExp) {
			nsResponse = handleBusinessException(bussExp, nsRequest.getLocale());
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_IMMUNALIZATION_LIST);
		} catch (Exception sysExp) {
			nsResponse = handleSystemException(sysExp);
			//activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_INPATIENT_IMMUNALIZATION_LIST);
		}
		LOGGER.debug("Response Sent for, get patient Test Result Information service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
	

}
