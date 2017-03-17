package com.versawork.http.service.impl;

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
import com.versawork.http.service.UpdateProfileInfoService;
import com.versawork.http.utils.MaskUtils;

@Component
public class UpdateProfileInfoServiceImpl implements UpdateProfileInfoService {

	final static Logger LOGGER = LoggerFactory.getLogger(UpdateProfileInfoServiceImpl.class);
	// private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	@Autowired
	private MessageSource messageSource;

	private static final String CLIENT_DATABASE_ID = "client.database.id";
	/*
	 * private static final String INVALID_ENDPOINT_RESPONSE_CODE =
	 * "rsp.data.result.reg.override"; private static final String
	 * INVALID_ENDPOINT_ID = "invalid.endpoint.id";
	 * 
	 * @Autowired private GetProfileInfoDAO getProfileInfoDAO;
	 */

	@Autowired
	private AccountServiceDAO accountServiceDAO;

	@Override
	public NsResponse updateProfileInfo(NsRequest nsRequest) throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();

		Account account = accountServiceDAO.getAccountByEndpointId(
				MaskUtils.getDigest(nsRequest.getAccountInfo().getEndpointUserId().getBytes()),
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		account.setWillShareData(nsRequest.getAccountInfo().isWillShareData());
		accountServiceDAO.update(account);
		responseData.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responseData.setDescription(messageSource.getMessage("editprofile.button.update", null, nsRequest.getLocale()));
		nsResponse.setResponseData(responseData);

		return nsResponse;
	}
}
