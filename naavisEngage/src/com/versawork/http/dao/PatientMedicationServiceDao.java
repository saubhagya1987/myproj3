package com.versawork.http.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.PatientPrescription;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class PatientMedicationServiceDao implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	/*
	 * @PersistenceContext(unitName = "versawork_DS", type =
	 * PersistenceContextType.TRANSACTION) private EntityManager entityManager;
	 */
	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	/*
	 * @PersistenceContext(unitName = "versawork_DS", type =
	 * PersistenceContextType.TRANSACTION)
	 * 
	 * @Immutable private EntityManager em;//for caching
	 */
	public List<PatientPrescription> getPatientPrescription(Integer accountId) throws BusinessException,
			SystemException {
		List<PatientPrescription> patientPrescriptions = null;
		try {
			TypedQuery<PatientPrescription> query = (TypedQuery<PatientPrescription>) ehrEntityManager
					.createNamedQuery("PatientPrescription.findByAccountId", PatientPrescription.class);
			query.setParameter("accountId", accountId);
			patientPrescriptions = query.getResultList();
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientPrescriptions;
	}

}
