package com.versawork.http.filter;

import java.util.Locale;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.controller.ExceptionController;
import com.versawork.http.dao.AccountServiceDAO;
import com.versawork.http.dataobject.AccountInfo;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.interceptor.BoundaryLogger;
import com.versawork.http.model.Account;
import com.versawork.http.utils.MaskUtils;

/**
 * @author Sohaib
 * 
 */
@Aspect
public class RequestFilter {

	@Autowired
	AccountServiceDAO accountServiceDao;

	@Autowired
	MessageSource messageSource;

	@Autowired
	Gson gson;

	@Autowired
	ExceptionController exceptionController;

	Locale locale = new Locale("en");
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

	@Before("execution(* com.versawork.http.controller.*.*(..)) && args(nsRequest,..)")
	public NsResponse doAccessCheck(JoinPoint joinPoint, NsRequest nsRequest) throws SystemException, BusinessException {

		// NsResponse nsResponse = new NsResponse();
		LOGGER.debug("Request Receieved, (Request filter): \n" + gson.toJson(nsRequest));

		nsRequest.setLocale(locale);
		if (nsRequest.getAccountInfo() != null) {
			AccountInfo accountInfo = nsRequest.getAccountInfo();
			if (((accountInfo.getAccountId() != null && accountInfo.getAccountId() == -1) || (accountInfo
					.getEndpointUserId() != null && accountInfo.getEndpointUserId().equalsIgnoreCase("-1")))
					|| (accountInfo.getDeviceToken() != null && accountInfo.getDeviceToken().equalsIgnoreCase("ABC"))) {
				throw new BusinessException(VersaWorkConstant.DEVICE_RESET_RESPONSE_CODE);
				// return handleBusinessException( new
				// BusinessException(VersaWorkConstant.DEVICE_RESET_RESPONSE_CODE),locale);

			}

			// prefference is given to request preffered language as it will be
			// there for registration process and we dont want to look in db
			if (accountInfo.getPreferredLanguage() != null && !accountInfo.getPreferredLanguage().equalsIgnoreCase("")) {
				locale = new Locale(nsRequest.getAccountInfo().getPreferredLanguage());
				nsRequest.setLocale(locale);
			} else if (accountInfo.getAccountId() != null) {
				Account account = new Account();
				try {
					account = accountServiceDao.getAccountById(accountInfo.getAccountId());
					String prefLang = account.getPreferredLanguage();
					if (nsRequest.getAccountInfo().getPreferredLanguage() == null
							|| nsRequest.getAccountInfo().getPreferredLanguage() == "") {
						if (prefLang != null && !prefLang.equalsIgnoreCase("")) {
							nsRequest.getAccountInfo().setPreferredLanguage(account.getPreferredLanguage());
							locale = new Locale(account.getPreferredLanguage());
							nsRequest.setLocale(locale);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
			// id endpoint id is there only then we validate to save build release time
			if (accountInfo.getEndpointUserId() != null && !accountInfo.getEndpointUserId().isEmpty()) 
			{
				// Account account = new Account();
				try {
					Account account = accountServiceDao.getAccountByEndpointId(MaskUtils.getDigest(nsRequest
							.getAccountInfo().getEndpointUserId().getBytes()), Integer.parseInt(messageSource
							.getMessage("client.database.id", null, nsRequest.getLocale())));
					// account =
					// accountServiceDao.getAccountById(accountInfo.getAccountId());
					if (account == null) {
						LOGGER.debug("No account found for given endpoint");
						throw new BusinessException(VersaWorkConstant.INVALID_ENDPOINT_RESPONSE_CODE);
						// return handleBusinessException( new
						// BusinessException(VersaWorkConstant.INVALID_ENDPOINT_RESPONSE_CODE),locale);
					}
				} catch (Exception e) {
					LOGGER.debug("Devic logged in with different user ");
					throw new BusinessException(VersaWorkConstant.DEVICE_RESET_RESPONSE_CODE);
					// return handleBusinessException( new
					// BusinessException(VersaWorkConstant.DEVICE_RESET_RESPONSE_CODE),locale);
				}
			}

			/*
			 * if(accountInfo.getPreferredLanguage()!=null &&
			 * !accountInfo.getPreferredLanguage().equalsIgnoreCase("")) {
			 * LOGGER.info("Language pref : "+nsRequest.getAccountInfo().
			 * getPreferredLanguage()); locale = new
			 * Locale(nsRequest.getAccountInfo().getPreferredLanguage()); } else
			 * if(accountInfo.getAccountId() != null &&
			 * accountInfo.getAccountId() == -1) {
			 * LOGGER.info("Setting prffered Language From account "); Account
			 * account = new Account(); account =
			 * accountServiceDao.getAccountById(accountInfo.getAccountId()); if
			 * (nsRequest.getAccountInfo().getPreferredLanguage() == null
			 * ||nsRequest.getAccountInfo().getPreferredLanguage() == "") { if
			 * (account.getPreferredLanguage() != null&&
			 * !account.getPreferredLanguage().equalsIgnoreCase("")) {
			 * nsRequest.
			 * getAccountInfo().setPreferredLanguage(account.getPreferredLanguage
			 * ()); locale = new Locale(account.getPreferredLanguage()); } }
			 * 
			 * if (account.getPreferredLanguage() != null&&
			 * !account.getPreferredLanguage().equalsIgnoreCase("")) {
			 * nsRequest.
			 * getAccountInfo().setPreferredLanguage(account.getPreferredLanguage
			 * ()); locale = new Locale(account.getPreferredLanguage());
			 * 
			 * LOGGER.info("Setting prffered Language From account(locale) "+locale
			 * ); }
			 * 
			 * }
			 */

		}
		/*
		 * if(nsRequest.getLocale()==null){ nsRequest.setLocale(locale); }
		 */
		return null;

		// Locale.setDefault(locale);
	}

}