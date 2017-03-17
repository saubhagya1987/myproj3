package com.versawork.http.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.FacilityProvider;
import com.versawork.http.model.PatientAppointmentRequest;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class GetUpcomingAppointmentsListDAO implements Serializable {

	final static Logger LOGGER = LoggerFactory.getLogger(GetUpcomingAppointmentsListDAO.class);
	// private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "versawork_DS")
	private EntityManager entityManager;
	String providerId = null;

	public List<PatientAppointmentRequest> getUpcomingApptsByActId(Integer accountId) throws BusinessException,
			SystemException {
		List<PatientAppointmentRequest> patientAppointmentRequest = null;
		try {
			TypedQuery<PatientAppointmentRequest> query = (TypedQuery<PatientAppointmentRequest>) entityManager
					.createNamedQuery("PatientAppointmentRequest.findByAccountId", PatientAppointmentRequest.class);
			query.setParameter("accountId", new Account(accountId));
			patientAppointmentRequest = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientAppointmentRequest;
	}

	public FacilityProvider getHospitalProviderDetails(String providerId) throws BusinessException, SystemException {
		FacilityProvider hospitalProvider = null;
		try {
			TypedQuery<FacilityProvider> query = (TypedQuery<FacilityProvider>) entityManager.createNamedQuery(
					"FacilityProvider.findByProviderId", FacilityProvider.class);
			query.setParameter("providerId", providerId);
			hospitalProvider = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.providerId.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return hospitalProvider;
	}

}