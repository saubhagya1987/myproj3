package com.versawork.http.controller;

import java.util.Locale;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
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
import com.versawork.http.service.LinkAccountService;
import com.versawork.http.service.UserRegistrationService;
import com.versawork.http.utils.Activity;

@Controller
@RequestMapping("/linkAccountService")
public class LinkAccountController {

	static Logger LOGGER = LoggerFactory.getLogger(LinkAccountController.class);

	@Autowired
	private Gson gson;

	@Autowired
	private LinkAccountService linkAccountService;

	@Autowired
	private UserRegistrationService registrationService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ActivityLogController activityLogController;

	@Autowired
	private ExceptionController exceptionController;

	@RequestMapping(value = "/verifyLinkedUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@BoundaryLogger
	@ResponseBody
	public NsResponse verifySecondaryUser(@RequestBody NsRequest nsRequest) throws BusinessException, SystemException {

		LOGGER.debug("Request Receieved, verifySecondaryUser: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse;
		try {
			nsResponse = registrationService.verifyUser(nsRequest, true);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VERIFY_LINKED_USER);
		} catch (BusinessException bussExp) {
			nsResponse = exceptionController.handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VERIFY_LINKED_USER);
		} catch (Exception sysExp) {
			nsResponse = exceptionController.handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.VERIFY_LINKED_USER);
		}
		LOGGER.debug("Response Sent for, verifySecondaryUser: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/linkaccount" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody NsResponse linkUserAccount(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, linkUserAccount: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = null;
		try {
			nsResponse = linkAccountService.setSecondaryAccount(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.LINK_SECONDARY_ACCOUNT);
		} catch (BusinessException bussExp) {
			nsResponse = exceptionController.handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.LINK_SECONDARY_ACCOUNT);
		} catch (Exception sysExp) {
			nsResponse = exceptionController.handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.LINK_SECONDARY_ACCOUNT);
		}
		LOGGER.debug("Response Sent for, linkUserAccount: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/unlinkaccount" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody NsResponse unlinkUserAccount(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, unlinkUserAccount: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = null;
		try {
			nsResponse = linkAccountService.unlinkAccount(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UNLINK__ACCOUNT);
		} catch (BusinessException bussExp) {
			nsResponse = exceptionController.handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UNLINK__ACCOUNT);
		} catch (Exception sysExp) {
			nsResponse = exceptionController.handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.UNLINK__ACCOUNT);
		}
		LOGGER.debug("Response Sent for, unlinkUserAccount: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/myRegisteredFacilities" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody NsResponse getRegisteredFacilities(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, getRegisteredFacilities: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = null;
		try {
			nsResponse = linkAccountService.getRegisteredFacilities(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_REGISTERED_FACILITIES);
		} catch (BusinessException bussExp) {
			nsResponse = exceptionController.handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_REGISTERED_FACILITIES);
		} catch (Exception sysExp) {
			nsResponse = exceptionController.handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_REGISTERED_FACILITIES);
		}
		LOGGER.debug("Response Sent for, getRegisteredFacilities: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/myRegisteredFacilityDetails" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody NsResponse getRegisteredFacilityDetails(@RequestBody NsRequest nsRequest)
			throws BusinessException, SystemException {

		LOGGER.debug("Request Receieved, getRegisteredFacilityDetails: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = null;
		try {
			nsResponse = linkAccountService.getRegisteredFacilityDetails(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_REGISTERED_FACILITIES_DETAILS);
		} catch (BusinessException bussExp) {
			nsResponse = exceptionController.handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_REGISTERED_FACILITIES_DETAILS);
		} catch (Exception sysExp) {
			nsResponse = exceptionController.handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_REGISTERED_FACILITIES_DETAILS);
		}
		LOGGER.debug("Response Sent for, getRegisteredFacilityDetails: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/searchFacilities" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody NsResponse searchFacilities(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, searchFacilities: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = null;
		try {
			nsResponse = linkAccountService.searchdFacilities(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEARCH_FACILITIES);
		} catch (BusinessException bussExp) {
			nsResponse = exceptionController.handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEARCH_FACILITIES);
		} catch (Exception sysExp) {
			nsResponse = exceptionController.handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.SEARCH_FACILITIES);
		}
		LOGGER.debug("Response Sent for, searchFacilities: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

}
