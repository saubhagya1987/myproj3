package com.versawork.http.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsHospitalNotice;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

@Component
public interface HospitalNoticeService extends Serializable {
	public NsResponse getHospitalNotice(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse setNoticeViewed(NsRequest nsRequest) throws BusinessException, SystemException;

	void markNoticeViewed(NsRequest nsRequest, List<NsHospitalNotice> nsHospitalNotice) throws BusinessException,
			SystemException;

	public NsResponse getHospitalLists(NsRequest nsRequest) throws BusinessException, SystemException;
}
