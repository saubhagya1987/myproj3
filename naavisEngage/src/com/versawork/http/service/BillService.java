package com.versawork.http.service;

import java.io.Serializable;
import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

@Component
public interface BillService extends Serializable {

	/**
	 * This method will return the list of bill summary
	 * 
	 * @param accountId
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse getBillSummaryList(Integer accountId) throws BusinessException, SystemException;

	/**
	 * 
	 * This method will return the detail of the bill on the basis of invoice
	 * number
	 * 
	 * @param billId
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse getBillDetail(String billId) throws BusinessException, SystemException;

	/**
	 * 
	 * This method will save the logs of transaction of bill paid
	 * 
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse getTransactionStatus(NsRequest nsRequest) throws BusinessException, SystemException;

}
