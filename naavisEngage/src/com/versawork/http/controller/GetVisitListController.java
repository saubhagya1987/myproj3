package com.versawork.http.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.interceptor.BoundaryLogger;
import com.versawork.http.service.ActivityLogService;
import com.versawork.http.service.GetVisitListService;
import com.versawork.http.utils.Activity;

/**
 * @author iRESLab
 *
 */

@Controller
@RequestMapping("/getvisitlist")
public class GetVisitListController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetVisitListController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	private GetVisitListService getVisitListService;

	@Autowired
	private ActivityLogController activityLogController;

	@Autowired
	ExceptionController exceptionController;

	@Autowired
	Gson gson;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@BoundaryLogger
	public @ResponseBody NsResponse getVisit(@RequestBody NsRequest nsRequest)  throws BusinessException,
	SystemException {
		NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, getVisitList Service: \n" + gson.toJson(nsRequest));
		try {
			nsResponse = getVisitListService.getVisitList(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_VISIT_lIST);

		} catch (BusinessException bussExp) {
			nsResponse = exceptionController.handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_VISIT_lIST);
		} catch (Exception sysExp) {
			nsResponse = exceptionController.handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_VISIT_lIST);
		}

		LOGGER.debug("Response Sent, getVisitList Service: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

}