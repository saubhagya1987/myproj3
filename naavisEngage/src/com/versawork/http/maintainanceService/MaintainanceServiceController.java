package com.versawork.http.maintainanceService;

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
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.interceptor.BoundaryLogger;

@Controller
@RequestMapping("/maintainance")
public class MaintainanceServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MaintainanceServiceController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	MaintainanceService maintainanceService;

	Gson gson = new Gson();
	NsMaintainanceResponse nsMaintainanceResponse = null;

	@RequestMapping(value = { "/addUpcomingAppointments" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsMaintainanceResponse addUpcomingAppointments(@RequestBody MaintananceInfo nsRequest)
			throws BusinessException, SystemException {

		LOGGER.debug("Request Receieved, addUpcomingAppointments Service: \n" + gson.toJson(nsRequest));
		try {
			nsMaintainanceResponse = maintainanceService.maintainanceService(nsRequest);

		} catch (BusinessException bussExp) {
			nsMaintainanceResponse = handleBusinessException(bussExp);
		} catch (Exception sysExp) {
			nsMaintainanceResponse = handleSystemException(sysExp);
		}
		LOGGER.debug("Response Sent for, addUpcomingAppointments service: \n" + gson.toJson(nsMaintainanceResponse));
		return nsMaintainanceResponse;
	}

	@ExceptionHandler
	@BoundaryLogger
	public @ResponseBody NsMaintainanceResponse handleBusinessException(BusinessException be) {
		NsMaintainanceResponse nsMaintainanceResponse = new NsMaintainanceResponse();
		MaintainanceResponseData responseData = new MaintainanceResponseData();
		responseData.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		responseData.setDescription(messageSource.getMessage(be.getExceptionCode(), null, Locale.getDefault()));

		nsMaintainanceResponse.setMaintainanceResponseData(responseData); // Needs
																			// to
																			// be
																			// derived
		LOGGER.debug("Response Sent, feedback service: \n" + gson.toJson(nsMaintainanceResponse));

		return nsMaintainanceResponse;
	}

	@ExceptionHandler
	@BoundaryLogger
	public @ResponseBody NsMaintainanceResponse handleSystemException(Exception se) {
		NsMaintainanceResponse nsResponse = new NsMaintainanceResponse();
		// nsResponse.setAuthToken(authToken);
		MaintainanceResponseData responseData = new MaintainanceResponseData();
		responseData.setResult(VersaWorkConstant.Status_Codes.FAILURE_RESPONSE_CODE.getCode());
		responseData.setDescription(se.getMessage());

		nsResponse.setMaintainanceResponseData(responseData); // Needs to be
																// derived
		LOGGER.debug("Response Sent, feedback service: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

}
