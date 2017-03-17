package com.versawork.http.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.versawork.http.dataobject.NsFeedBackList;
import com.versawork.http.dataobject.NsPatientVisit;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.FacilityProvider;
import com.versawork.http.model.PatientFeedback;
import com.versawork.http.model.PatientVisit;
import com.versawork.http.utils.DateUtils;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class GetPastAppointmentsListDAO implements Serializable {

	final static Logger LOGGER = LoggerFactory.getLogger(GetPastAppointmentsListDAO.class);
	// private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "versawork_DS")
	private EntityManager entityManager;
	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;
	String providerId = null;

	public List<NsPatientVisit> getPastAppointmentsList(Integer accountId) throws BusinessException, SystemException {
		List<NsPatientVisit> result = new ArrayList<NsPatientVisit>();
		try {
			LOGGER.error("account Id in get past appointments list DAO" + accountId);
			TypedQuery<FacilityProvider> query = (TypedQuery<FacilityProvider>) entityManager.createNamedQuery(
					"FacilityProvider.findByAccountId", FacilityProvider.class);
			query.setParameter("accountId", new Account(accountId));
			FacilityProvider hospitalProvider = query.getSingleResult();
			String providerId = hospitalProvider.getFacilityProviderPK().getProviderId();

			LOGGER.error("Provider Name or ProviderId  " + providerId);
			TypedQuery<PatientVisit> PVquery = (TypedQuery<PatientVisit>) ehrEntityManager.createNamedQuery(
					"PatientVisit.findByProviderId", PatientVisit.class);
			PVquery.setParameter("providerId", providerId);

			List<PatientVisit> patientVisit = PVquery.getResultList();
			LOGGER.error("patientVisit  size of the list coming from the server :" + patientVisit.size()
					+ " \n  Based on the account Id: "/*
													 * +patientVisit.get(0).
													 * getAccountNumber()
													 */);
			if (patientVisit.size() != 0) {
				for (PatientVisit patientVisitLoop : patientVisit) {

					NsPatientVisit nsPatientVisit = new NsPatientVisit();

					// new db changes

					// nsPatientVisit.setAccountNumber(patientVisitLoop.getAccountNumber());
					// nsPatientVisit.setAttendingPhysicianName(patientVisitLoop.getAttendingPhysicianName());
					nsPatientVisit.setPatientVisitId(patientVisitLoop.getPatientVisitPK().getPatientVisitId());
					// nsPatientVisit.setLocation(patientVisitLoop.getLocation());
					nsPatientVisit.setAccountId(accountId);
					try {

						nsPatientVisit.setVisitDate(DateUtils.getFormatDate(patientVisitLoop.getVisitDate(),
								"MMMM d, yyyy"));
					} catch (Exception e) {
						LOGGER.debug("Could not set visit date.");
					}
					nsPatientVisit.setFirstName(patientVisitLoop.getFirstName());
					nsPatientVisit.setLastName(patientVisitLoop.getLastName());

					// new db changes

					/*
					 * if(patientVisitLoop.getPatientFeedbackList().size() ==0){
					 * nsPatientVisit.setFeedBack("No"); }else{
					 * nsPatientVisit.setFeedBack("Yes"); }
					 */

					result.add(nsPatientVisit);
				}
			}

		} catch (NoResultException noResultException) {
			LOGGER.error("Exception occoured on GetPastAppointmentsListDAO (getPastAppointmentsList) : ");
			throw new BusinessException("no.appointment.list");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return result;

	}

	public List<NsFeedBackList> getFeedBackList(Integer patientVisitId) throws BusinessException, SystemException {
		List<NsFeedBackList> result = new ArrayList<NsFeedBackList>();
		try {
			LOGGER.info("GetPastAppointmentsListDAO, getFeedBackList : ");

			TypedQuery<PatientFeedback> query = (TypedQuery<PatientFeedback>) entityManager.createNamedQuery(
					"PatientFeedback.findByPatientVisitId", PatientFeedback.class);

			// new db changes

			query.setParameter("patientVisitId", patientVisitId);
			List<PatientFeedback> patientFeedback = query.getResultList();
			if (patientFeedback.size() != 0) {
				for (PatientFeedback feedback : patientFeedback) {
					NsFeedBackList details = new NsFeedBackList();
					details.setComment(feedback.getComment());
					details.setRecoveryRating(feedback.getRecoveryRating());
					details.setVisitRating(feedback.getVisitRating());
					details.setPatientVisitId(feedback.getPatientVisitId());
					result.add(details);
				}
			}

		} catch (NoResultException noResultException) {
			LOGGER.error("Exception occoured on GetPastAppointmentsListDAO (getFeedBackList) : ");
			throw new BusinessException("no.feedbck.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return result;
	}

}