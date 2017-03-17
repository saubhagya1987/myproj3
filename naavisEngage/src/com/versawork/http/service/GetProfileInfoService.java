package com.versawork.http.service;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

@Component
public interface GetProfileInfoService {

	public NsResponse getProfileInfo(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse saveProfileImage(NsRequest nsRequest) throws BusinessException, SystemException;
}
