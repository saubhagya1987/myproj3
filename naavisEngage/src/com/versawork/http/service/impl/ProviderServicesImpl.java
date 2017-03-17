package com.versawork.http.service.impl;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.GetPastAppointmentsListDAO;
import com.versawork.http.dataobject.NsFeedBackList;
import com.versawork.http.dataobject.NsPatientVisit;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.service.ProviderServices;

/**
 * @author Dheeraj
 * 
 */

@Service
public class ProviderServicesImpl implements ProviderServices {

	final static Logger LOGGER = LoggerFactory.getLogger(ProviderServicesImpl.class);
	// private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	@Autowired
	private GetPastAppointmentsListDAO getPastAppointmentsListDAO;

	@Autowired
	private MessageSource messageSource;

	@Override
	public NsResponse getPastAppointmentsList(NsRequest nsRequest) throws BusinessException, SystemException {

		NsResponse nsResponse = new NsResponse();
		ResponseData response = new ResponseData();
		response.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		response.setDescription("success");
		List<NsPatientVisit> provider = getPastAppointmentsListDAO.getPastAppointmentsList(nsRequest.getAccountInfo()
				.getAccountId());

		nsResponse.setPatientVisitList(provider);
		nsResponse.setResponseData(response);

		return nsResponse;

	}

	@Override
	public NsResponse getVisitorsFeedBackList(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData response = new ResponseData();
		response.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		response.setDescription("success");
		List<NsFeedBackList> nsFeedBackList = getPastAppointmentsListDAO.getFeedBackList(nsRequest.getNsPatientVisit()
				.getPatientVisitId());

		nsResponse.setNsFeedBackList(nsFeedBackList);
		nsResponse.setResponseData(response);

		return nsResponse;

	}
}
