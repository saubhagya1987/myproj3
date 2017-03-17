package com.versawork.http.service;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

@Component
public interface ActivityLogService {

	public void saveActivityLog(int accountId, NsRequest request, NsResponse response, String page)
			throws BusinessException, SystemException;

	public void saveActivityLog(int accountId, NsRequest request, NsResponse response, String page, int patientId)
			throws BusinessException, SystemException;

	public int fetchAccountId(NsRequest nsRequest) throws BusinessException, SystemException;

}
