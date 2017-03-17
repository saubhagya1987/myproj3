package com.versawork.http.service;

import java.util.Locale;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

/**
 * Service layer interface which defines the contract of the methods to be used
 * for performing various operations during user registration flow.
 * 
 * @author Sohaib
 * 
 */

@Component
public interface UserRegistrationService {

	/**
	 * Method validates the request and verifies the user by validating the MR
	 * Number and Phone Number against the patient data already present in the
	 * database.
	 * 
	 * @param nsRequest
	 * @return
	 */
	public NsResponse verifyUser(NsRequest nsRequest, boolean isSecondaryUserVerification) throws BusinessException,
			SystemException;

	/**
	 * To 1. generate a security code 2. save security code with timestamp for a
	 * user to DB 3. send code to user via SMS/IVR
	 * 
	 * @param nsRequest
	 * @param fromLinkedAccount 
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse sendSecurityCode(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * To 1. validate expiry of security code user enters 2. validate security
	 * code entered by user against the code stored in DB
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse validateSecurityCode(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * Method creates a new account or update existing account to persist
	 * PIN/Finger print selected by user for post registration authentication in
	 * the database.
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse createUserAccount(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * Authenticates user on basis of Endpoint userid and 4
	 * AUTH_PIN/AUTH_FINGERPRINT. If bad attempts exceed the first limit, the
	 * account gets locked for 5 mins. Further if bad attempts reach the higher
	 * limit, the account gets permanently locked.
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse authenticateUserLogin(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * To send the code automatically during phone number change.
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse sendSecurityCodeForPhoneNumberChange(NsRequest nsRequest) throws BusinessException,
			SystemException;

	/**
	 * To send security code to user while editing profile
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse sendSecurityCodeForProfileEdit(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * To get additional information like noticiations and warnings for a user
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	//public NsResponse getLoginFeatures(NsRequest nsRequest) throws BusinessException, SystemException;

	/*
	 * public NsResponse requestFilter(NsRequest nsRequest) throws
	 * BusinessException, SystemException;
	 */

	/**
	 * To get features available for an app based on client_id
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse getAppFeatures(Locale locale) throws BusinessException, SystemException;

	/**
	 * To validate a user against mr,dob,phone,email where all these are
	 * required to be correct.
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse validateUser(NsRequest nsRequest) throws BusinessException, SystemException;
	
	public NsResponse getFeedInfo(NsRequest nsRequest) throws BusinessException, SystemException;
	
	public NsResponse updateFeedInfo(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse sendForgotPin(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse validatePinCode(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse updateTempPin(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse updateProfileInformation(NsRequest nsRequest)throws BusinessException, SystemException;

}
