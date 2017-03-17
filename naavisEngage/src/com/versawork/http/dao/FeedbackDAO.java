package com.versawork.http.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.PatientFeedback;
import com.versawork.http.model.PatientVisit;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class FeedbackDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackDAO.class);

	// Injected database connection:
	@PersistenceContext(unitName = "versawork_DS")
	private EntityManager entityManager;
	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(PatientFeedback patientFeedback) {
		entityManager.persist(patientFeedback);
	}

	public PatientVisit getvisitIdOfPatient(Integer visitId) throws BusinessException, SystemException {
		PatientVisit patientVisit = null;
		// if(isDebugEnabled) LOGGER.debug("Getting visit Id " + visitId);
		try {
			TypedQuery<PatientVisit> query = ehrEntityManager.createNamedQuery("PatientVisit.findByPatientVisitId",
					PatientVisit.class);

			query.setParameter("patientVisitId", visitId);

			patientVisit = query.getSingleResult();

		} catch (NoResultException noResultException) {
			LOGGER.error("No visit id found");
			throw new BusinessException("invalid.auth.token");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return patientVisit;
	}

	public List<PatientFeedback> getFeedBackByPatientVisitId(Integer visitId) throws BusinessException, SystemException {
		List<PatientFeedback> patientFeedbacks = null;
		try {
			TypedQuery<PatientFeedback> query = entityManager.createNamedQuery("PatientFeedback.findByPatientVisitId",
					PatientFeedback.class);

			query.setParameter("patientVisitId", visitId);

			patientFeedbacks = query.getResultList();

		} catch (NoResultException noResultException) {
			LOGGER.error("No visit id found");
			throw new BusinessException("invalid.auth.token");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return patientFeedbacks;
	}

}
