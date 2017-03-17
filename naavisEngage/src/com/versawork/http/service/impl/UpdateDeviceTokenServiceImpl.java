package com.versawork.http.service.impl;

import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.AccountServiceDAO;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.service.UpdateDeviceTokenService;

/**
 * @author Dheeraj
 * 
 */

@Component
public class UpdateDeviceTokenServiceImpl implements UpdateDeviceTokenService {

	final static Logger LOGGER = LoggerFactory.getLogger(UpdateDeviceTokenServiceImpl.class);

	@Autowired
	private AccountServiceDAO accountServiceDAO;

	@Autowired
	private MessageSource messageSource;

	@Override
	public NsResponse updateDeviceToken(NsRequest nsRequest) throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();

		Account account = accountServiceDAO.getAccountById(nsRequest.getAccountInfo().getAccountId());

		account.setDeviceToken(nsRequest.getAccountInfo().getDeviceToken());
		account.setDeviceType(nsRequest.getAccountInfo().getDeviceType());
		account.setDateModified(new Date());
		accountServiceDAO.update(account);

		responseData.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responseData.setDescription("Device Token Added/updated successfully");

		nsResponse.setResponseData(responseData);

		return nsResponse;

	}

}
