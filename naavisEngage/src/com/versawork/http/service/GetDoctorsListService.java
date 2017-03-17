package com.versawork.http.service;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

@Component
public interface GetDoctorsListService {

	public NsResponse getDoctorsList(NsRequest nsRequest) throws SystemException, BusinessException;

}
