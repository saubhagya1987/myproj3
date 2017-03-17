package com.versawork.http.service;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

@Component
public interface SystemAdministratorService extends Serializable {

	public NsResponse getDirectAddressInfo(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getNTPServerTime(NsRequest nsRequest) throws BusinessException, SystemException;
}
