package com.versawork.http.controller;

import java.util.Locale;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.interceptor.BoundaryLogger;
import com.versawork.http.service.ActivityLogService;

/**
 * @author Dheeraj
 * 
 */
@Controller
public class ActivityLogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityLogController.class);

	@Autowired
	private ActivityLogService activityLogService;

	ObjectMapper mapper = null;

	@Autowired
	private Gson gson;

	@Async
	public void saveActivityLog(NsRequest nsRequest, NsResponse nsResponse, String page) {
		try {
			Integer accountId = 0;
			if (nsRequest.getAccountInfo() != null) {
				accountId = nsRequest.getAccountInfo().getAccountId();
			} else {
				accountId = 0;
				LOGGER.info(page + " activity, AccountInfo is not present so accountId is set to 0");
			}
			activityLogService.saveActivityLog(accountId, nsRequest, nsResponse, page);
		} catch (Exception e) {
			LOGGER.error(page + " activity not logged");
		}
	}

}
