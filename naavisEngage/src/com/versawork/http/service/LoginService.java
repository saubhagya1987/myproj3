package com.versawork.http.service;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

@Component
public interface LoginService {

	public NsResponse loginService(NsRequest nsRequest) throws BusinessException, SystemException, Exception;

	public NsResponse logOutService(NsRequest nsRequest) throws BusinessException, SystemException, Exception;

	public NsResponse forciblyVersionUpdate(NsResponse nsResponse);

}
