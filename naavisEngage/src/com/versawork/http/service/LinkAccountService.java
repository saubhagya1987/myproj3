package com.versawork.http.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.BillSummaryData;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.LinkedAccountsRel;

@Component
public interface LinkAccountService extends Serializable {

	/**
	 * To link user secondary account by using MR number, phone, email, Dob and
	 * client id of the secondary Account Also require to End point id and
	 * account id of primary account.
	 * 
	 * @param nsRequest
	 * @return
	 * @throws SystemException
	 * @throws BusinessException
	 */
	public NsResponse setSecondaryAccount(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * To unlink user secondary account
	 * 
	 * @param nsRequest
	 * @return
	 * @throws SystemException
	 * @throws BusinessException
	 */
	public NsResponse unlinkAccount(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * To get all the registered/linked facilities of the patient based on
	 * endpoint and account id
	 * 
	 * @param nsRequest
	 * @return
	 * @throws SystemException
	 * @throws BusinessException
	 */
	public NsResponse getRegisteredFacilities(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * To get athe details of the registered hospital
	 * 
	 * @param nsRequest
	 * @return
	 * @throws SystemException
	 * @throws BusinessException
	 */
	public NsResponse getRegisteredFacilityDetails(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * To get all the facilities information(name, address, clientdbID etc.)
	 * which are registered with navis
	 * 
	 * @param nsRequest
	 * @return
	 * @throws SystemException
	 * @throws BusinessException
	 */
	public NsResponse searchdFacilities(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * Returns all linked account id list based on parent account id
	 * 
	 * @param nsRequest
	 * @return
	 * @throws SystemException
	 * @throws BusinessException
	 */
	public List<LinkedAccountsRel> getLinkedAccounts(Integer accountId) throws BusinessException, SystemException;

}
