package com.versawork.http.service.impl;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.AccountServiceDAO;
import com.versawork.http.dataobject.AccountInfo;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.service.GetProfileInfoService;
import com.versawork.http.utils.DateUtils;
import com.versawork.http.utils.MaskUtils;
import com.versawork.http.utils.NsResponseUtils;

/**
 * @author Dheeraj
 * 
 * @author Sohaib
 * 
 */
@Component
public class GetProfileInfoServiceImpl implements GetProfileInfoService {

	final static Logger LOGGER = LoggerFactory.getLogger(GetProfileInfoServiceImpl.class);
	// private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	/*
	 * @Autowired private GetProfileInfoDAO getProfileInfoDAO;
	 */

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AccountServiceDAO accountServiceDao;

	private static final String INVALID_ENDPOINT_RESPONSE_CODE = "rsp.data.result.reg.override";
	private static final String INVALID_ENDPOINT_ID = "invalid.endpoint.id";

	private static final String CLIENT_DATABASE_ID = "client.database.id";

	@Override
	public NsResponse getProfileInfo(NsRequest nsRequest) throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();

		// Account account =
		// getProfileInfoDAO.getAccountDetailsByAuthToken(nsRequest.getAccountInfo().getAuthToken());
		Account account = accountServiceDao.getAccountByEndpointId(
				MaskUtils.getDigest(nsRequest.getAccountInfo().getEndpointUserId().getBytes()),
				Integer.parseInt(messageSource.getMessage(CLIENT_DATABASE_ID, null, nsRequest.getLocale())));
		if (account == null) {
			String responseMsg = messageSource.getMessage(INVALID_ENDPOINT_ID, null, nsRequest.getLocale());
			Integer responseCode = Integer.parseInt(messageSource.getMessage(INVALID_ENDPOINT_RESPONSE_CODE, null,
					nsRequest.getLocale()));
			return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), responseMsg, responseCode);
		}
		AccountInfo accountInfo = new AccountInfo();
		ResponseData responseData = new ResponseData();
		accountInfo.setAccountId(account.getAccountId());
		accountInfo.setAccountName(account.getAccountName());
		accountInfo.setEmail(account.getEmail());
		accountInfo.setFirstName(account.getFirstName());
		accountInfo.setLastName(account.getLastName());
		String mobilePhoneNumber = account.getMobilePhoneNumber();

		if (account.getMobilePhoneNumber() != null && account.getMobilePhoneNumber().length() == 10
				&& account.getMobilePhoneNumber() != "" && !account.getMobilePhoneNumber().contains("-")) {
			StringBuffer sb = new StringBuffer();
			char[] b = "-".toCharArray();
			for (int x = 0; x < mobilePhoneNumber.length(); x++) {
				if (x != 3 && x != 6) {
					sb.append(mobilePhoneNumber.charAt(x));
				} else {
					sb.append(b);
					sb.append(mobilePhoneNumber.charAt(x));
				}
			}
			accountInfo.setMobilePhoneNumber(sb.toString());
		} else {
			accountInfo.setMobilePhoneNumber(account.getMobilePhoneNumber());
		}
		accountInfo.setRoleName(account.getAccountRoleId().getRoleName());
		accountInfo.setUnitNumber(account.getMedicalRecordNumber());
		// accountInfo.setWillProvideFeedback(account.getWillProvideFeedback());
		accountInfo.setWillShareData(account.getWillShareData());
		try {

			accountInfo.setDateOfBirth(DateUtils.getFormatDate(account.getBirthDate(), "MM/dd/yyyy"));
		} catch (Exception e) {
			LOGGER.info("Error could not set date of birth");
		}
		accountInfo.setProfileImage(account.getProfileImage());
		responseData.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responseData.setDescription("success");

		nsResponse.setAccountInfo(accountInfo);
		nsResponse.setResponseData(responseData);

		return nsResponse;
	}

	@Override
	public NsResponse saveProfileImage(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();

		Account account = accountServiceDao.getAccountById(nsRequest.getAccountInfo().getAccountId());

		if (account == null) {
			String responseMsg = messageSource.getMessage(INVALID_ENDPOINT_ID, null, nsRequest.getLocale());
			Integer responseCode = Integer.parseInt(messageSource.getMessage(INVALID_ENDPOINT_RESPONSE_CODE, null,
					nsRequest.getLocale()));
			return NsResponseUtils.getNsResponse(nsRequest.getAccountInfo(), responseMsg, responseCode);
		}

		account.setProfileImage(nsRequest.getAccountInfo().getProfileImage());

		accountServiceDao.update(account);

		responseData.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responseData.setDescription("success");

		nsResponse.setResponseData(responseData);

		return nsResponse;
	}
}