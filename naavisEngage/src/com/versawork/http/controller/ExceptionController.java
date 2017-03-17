package com.versawork.http.controller;

import java.util.Locale;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.interceptor.BoundaryLogger;
import com.versawork.http.utils.NsResponseUtils;

/**
 * @author iRESlab
 * 
 */

@ControllerAdvice
public class ExceptionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private Gson gson;

	@ExceptionHandler(value = BusinessException.class)
	@BoundaryLogger
	public @ResponseBody NsResponse handleBusinessException(BusinessException be, Locale locale) {

		LOGGER.error(ExceptionUtils.getStackTrace(be));
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
		LOGGER.debug("Response Sent, getVisitList Service: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	/*
	 * @ExceptionHandler(value = BusinessException.class)
	 * 
	 * @BoundaryLogger public @ResponseBody NsResponse
	 * handleBusinessException(BusinessException be) {
	 * 
	 * LOGGER.error(ExceptionUtils.getStackTrace(be)); NsResponse nsResponse =
	 * new NsResponse();
	 * 
	 * ResponseData responseData = new ResponseData(); if
	 * (be.getExceptionCode().
	 * equalsIgnoreCase(VersaWorkConstant.DEVICE_RESET_RESPONSE_CODE)) {
	 * responseData
	 * .setResult(VersaWorkConstant.Status_Codes.DEVICE_RESET_RESPONSE_CODE
	 * .getCode());
	 * responseData.setDescription(messageSource.getMessage(VersaWorkConstant
	 * .DEVICE_RESET_RESPONSE_MESSAGE, null, Locale.getDefault())); } else if
	 * (be.getExceptionCode().equalsIgnoreCase(VersaWorkConstant.
	 * INVALID_ENDPOINT_RESPONSE_CODE)) {
	 * responseData.setResult(VersaWorkConstant
	 * .Status_Codes.INVALID_ENDPOINT_RESPONSE_CODE.getCode());
	 * responseData.setDescription
	 * (messageSource.getMessage(VersaWorkConstant.INVALID_ENDPOINT_RESPONSE_MESSAGE
	 * , null, Locale.getDefault())); } else {
	 * responseData.setResult(VersaWorkConstant
	 * .Status_Codes.FAILURE_RESPONSE_CODE.getCode());
	 * responseData.setDescription
	 * (messageSource.getMessage(be.getExceptionCode(), null,
	 * Locale.getDefault())); } nsResponse.setResponseData(responseData); //
	 * Needs to be derived
	 * LOGGER.debug("Response Sent, getVisitList Service: \n" +
	 * gson.toJson(nsResponse));
	 * 
	 * return nsResponse; }
	 */

	@ExceptionHandler(value = Exception.class)
	@BoundaryLogger
	public @ResponseBody NsResponse handleSystemException(Exception se) {

		LOGGER.error(ExceptionUtils.getStackTrace(se));
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		responseData.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		if (se instanceof NullPointerException)
			responseData.setDescription(messageSource.getMessage(VersaWorkConstant.NULL_POINTER_MESSAGE, null,
					Locale.getDefault()));
		else
			responseData.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		responseData.setDescription(messageSource.getMessage(VersaWorkConstant.UNHANDLED_EXCPETION_MESSAGE, null,
				Locale.getDefault()));
		LOGGER.debug("Response Sent, getVisitList Service: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

}
