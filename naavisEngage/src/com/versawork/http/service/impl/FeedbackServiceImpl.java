package com.versawork.http.service.impl;

import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.FeedbackDAO;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.PatientFeedback;
import com.versawork.http.model.PatientVisit;
import com.versawork.http.service.FeedbackService;
import com.versawork.http.utils.NsResponseUtils;

/**
 * @author Dheeraj
 * 
 */

@Service
public class FeedbackServiceImpl implements FeedbackService {

	final static Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

	private static final String CLIENT_ID = "client.id";
	private static final String CLIENT_DATABSE_ID = "client.database.id";

	@Autowired
	private FeedbackDAO feedbackDAO;

	@Autowired
	private MessageSource messageSource;

	@Override
	public NsResponse saveFeedbackService(NsRequest nsRequest) throws BusinessException, SystemException {

		PatientVisit patientVisit = feedbackDAO.getvisitIdOfPatient(nsRequest.getFeedbackDetails().getVisitId());

		PatientFeedback patientFeedback = new PatientFeedback();

		patientFeedback.setVisitRating(nsRequest.getFeedbackDetails().getVisitRating());
		patientFeedback.setRecoveryRating(nsRequest.getFeedbackDetails().getRecoveryRating());
		patientFeedback.setComment(nsRequest.getFeedbackDetails().getComment());
		patientFeedback.setDateAdded(new Date());
		patientFeedback.setLastViewedByAcountId(nsRequest.getFeedbackDetails().getAccountId());
		patientFeedback.setLastViewedDate(new Date());
		patientFeedback.setClientId(Integer.parseInt(messageSource.getMessage(CLIENT_ID, null, nsRequest.getLocale())));
		LOGGER.debug("feedback client id : " + patientFeedback.getClientId());

		patientFeedback.setClientDatabaseId(Integer.parseInt(messageSource.getMessage(CLIENT_DATABSE_ID, null,
				nsRequest.getLocale())));

		/*
		 * //PatientFeedbackPK pkFeedback = new PatientFeedbackPK();
		 * pkFeedback.setClientDatabaseId(patientVisit.getPatientVisitPK().
		 * getClientDatabaseId());
		 * 
		 * pkFeedback.setClientId(patientVisit.getPatientVisitPK().getClientId
		 * ());
		 * 
		 * pkFeedback.setPatientVisitId(patientVisit.getPatientVisitPK().
		 * getPatientVisitId());
		 * 
		 * pkFeedback.setVisitIdentifier(patientVisit.getPatientVisitPK().
		 * getVisitIdentifier());
		 * 
		 * patientFeedback.setPatientFeedbackPK(pkFeedback);
		 */
		patientFeedback.setPatientVisitId(patientVisit.getPatientVisitPK().getPatientVisitId());
		// patientFeedback.setVisitIdentifier(patientVisit.getVisitIdentifier());

		feedbackDAO.persist(patientFeedback);

		// new db changes

		/*
		 * PatientFeedback feedbacks = patientVisit.getPatientFeedbackList(); if
		 * (feedbacks == null) { feedbacks = new ArrayList<PatientFeedback>(); }
		 * feedbacks.add(patientFeedback);
		 * patientVisit.setPatientFeedbackList(feedbacks);
		 */

		return NsResponseUtils.getNsResponse(
				null,
				VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS,
				Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
						nsRequest.getLocale())));
	}
}