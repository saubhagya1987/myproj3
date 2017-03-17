package com.versawork.http.service;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

@Component
public interface GetVisitListService extends Serializable {

	public NsResponse getVisitList(NsRequest nsRequest) throws BusinessException, SystemException;

}
