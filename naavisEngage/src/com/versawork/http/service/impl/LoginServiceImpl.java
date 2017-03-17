package com.versawork.http.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.LoginDAO;
import com.versawork.http.dataobject.AccountInfo;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.service.LoginService;
import com.versawork.http.utils.DateUtils;
import com.versawork.http.utils.MaskUtils;

/**
 * @author Dheeraj
 * 
 */

@Service
public class LoginServiceImpl implements LoginService {

	final static Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
	// private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private MessageSource messageSource;

	@Override
	public NsResponse loginService(NsRequest nsRequest) throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		Account account = null;
		if (nsRequest.getProviderView() != null) {
			if (nsRequest.getProviderView().equalsIgnoreCase("No")) {
				account = loginDAO.getAccountDetailsByEmailPaswrdPatient(nsRequest.getAccountInfo().getEmail(),
						MaskUtils.getDigest(nsRequest.getAccountInfo().getPassword().getBytes()),
						Integer.parseInt(messageSource.getMessage("client.database.id", null, nsRequest.getLocale())));

			} else if (nsRequest.getProviderView().equalsIgnoreCase("Yes")) {
				account = loginDAO.getAccountDetailsByEmailPaswrdProvider(nsRequest.getAccountInfo().getEmail(),
						MaskUtils.getDigest(nsRequest.getAccountInfo().getPassword().getBytes()));
			}
		}
		AccountInfo accountInfo = new AccountInfo();
		ResponseData responseData = new ResponseData();
		accountInfo.setClientDatabaseId(account.getClientDatabaseId());
		accountInfo.setAccountName(account.getAccountName());
		accountInfo.setEmail(account.getEmail());
		accountInfo.setAuthToken(account.getAuthToken());
		accountInfo.setFirstName(account.getFirstName());
		accountInfo.setLastName(account.getLastName());
		accountInfo.setMobilePhoneNumber(account.getMobilePhoneNumber());
		accountInfo.setRoleName(account.getAccountRoleId().getRoleName());
		accountInfo.setLastLoginDate(new Date());
		accountInfo.setAccountId(account.getAccountId());
		accountInfo.setPreferredLanguage(account.getPreferredLanguage());
		// accountInfo.setFailedLoginAttempts(VersaWorkConstant.attemptCount);

		// accountInfo.setWillProvideFeedback(account.getWillProvideFeedback());
		accountInfo.setWillShareData(account.getWillShareData());

		accountInfo.setDateOfBirth(DateUtils.getFormatDate(account.getBirthDate(), "MM/dd/yyyy"));
		if (account.getDeviceToken() == null) {
			accountInfo.setDeviceToken(""); // if device token is null send
											// it in response anyway
		} else {
			accountInfo.setDeviceToken(account.getDeviceToken());
		}

		if (account.getDeviceType() == null) {
			accountInfo.setDeviceType("");
		} else {
			accountInfo.setDeviceType(account.getDeviceType());
		}

		accountInfo.setUnitNumber(account.getMedicalRecordNumber());
		responseData.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responseData.setDescription("success");

		nsResponse.setAccountInfo(accountInfo);
		nsResponse.setResponseData(responseData);

		return nsResponse;
	}

	@Override
	public NsResponse logOutService(NsRequest nsRequest) throws BusinessException, SystemException, Exception {
		NsResponse nsResponse = new NsResponse();
		ResponseData data = new ResponseData();
		data.setDescription(VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS);
		data.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		nsResponse.setResponseData(data);
		return nsResponse;
	}

	@Override
	public NsResponse forciblyVersionUpdate(NsResponse nsResponse) {

		Gson gson = new Gson();
		ResponseData responseData = new ResponseData();
		LOGGER.debug("Request Receieved, forciblyVersionUpdate");
		String appVersion = messageSource.getMessage("app.version.store", null, Locale.getDefault());
		LOGGER.info("App Version " + appVersion);
		responseData.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				Locale.getDefault())));
		responseData.setDescription("success");
		nsResponse.setAppStoreVersion(appVersion);
		nsResponse.setResponseData(responseData);
		LOGGER.debug("Response Sent for forcibly Version Update: \n" + gson.toJson(nsResponse));
		return nsResponse;
	}
}
