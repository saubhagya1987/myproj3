package com.versawork.http.controller;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
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
import com.versawork.http.service.ActivityLogService;
import com.versawork.http.service.BillService;
import com.versawork.http.service.UserRegistrationService;
import com.versawork.http.utils.Activity;

@Controller
@RequestMapping("/billService")
public class BillController {

	static Logger LOGGER = LoggerFactory.getLogger(BillController.class);

	@Autowired
	private Gson gson;

	@Autowired
	private BillService billService;

	@Autowired
	private UserRegistrationService registrationService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ActivityLogController activityLogController;

	@Autowired
	private ExceptionController exceptionController;

	@RequestMapping(value = { "/getBillSummary" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody NsResponse getBillSummary(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, getBillSummary: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = null;
		try {
			nsResponse = billService.getBillSummaryList(nsRequest.getAccountInfo().getAccountId());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.BILL_PAY_SUMMARY);
		} catch (BusinessException bussExp) {
			nsResponse = exceptionController.handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.BILL_PAY_SUMMARY);
		} catch (Exception sysExp) {
			nsResponse = exceptionController.handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.BILL_PAY_SUMMARY);
		}
		LOGGER.debug("Response Sent for, getBillSummary: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/getBillDetail" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody NsResponse getBillDetail(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, getBillDetail: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = null;
		try {
			String billId = nsRequest.getBillingInfo().getInvoiceNumber();
			nsResponse = billService.getBillDetail(billId);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.BILL_PAY_DETAIL);
		} catch (BusinessException bussExp) {
			nsResponse = exceptionController.handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.BILL_PAY_DETAIL);
		} catch (Exception sysExp) {
			nsResponse = exceptionController.handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.BILL_PAY_DETAIL);
		}
		LOGGER.debug("Response Sent for, getBillDetail: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

	@RequestMapping(value = { "/getTransactionStatus" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody NsResponse getTransactionStatus(@RequestBody NsRequest nsRequest) throws BusinessException,
			SystemException {

		LOGGER.debug("Request Receieved, getTransactionStatus: \n" + gson.toJson(nsRequest));
		NsResponse nsResponse = null;
		try {
			nsResponse = billService.getTransactionStatus(nsRequest);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_TRANSACTION_LOG);
		} catch (BusinessException bussExp) {
			nsResponse = exceptionController.handleBusinessException(bussExp, nsRequest.getLocale());
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_TRANSACTION_LOG);
		} catch (Exception sysExp) {
			nsResponse = exceptionController.handleSystemException(sysExp);
			activityLogController.saveActivityLog(nsRequest, nsResponse, Activity.GET_TRANSACTION_LOG);
		}
		LOGGER.debug("Response Sent for, getTransactionStatus: \n" + gson.toJson(nsResponse));

		return nsResponse;
	}

}
