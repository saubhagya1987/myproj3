package com.versawork.http.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.model.PatientVisit;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class GetVisitServiceDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * @PersistenceContext(unitName = "versawork_DS", type =
	 * PersistenceContextType.TRANSACTION) private EntityManager entityManager;
	 */

	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	public List<PatientVisit> getPatientVisitActId(Integer accountId) throws BusinessException {
		List<PatientVisit> patientVisit = null;
		try {
			TypedQuery<PatientVisit> query = (TypedQuery<PatientVisit>) ehrEntityManager.createNamedQuery(
					"PatientVisit.findByAccountId", PatientVisit.class);
			query.setParameter("accountId", accountId);
			patientVisit = query.getResultList();
		} catch (NoResultException exp) {
			exp.printStackTrace();
			throw new BusinessException("No visits for unit number : " + accountId, exp);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new BusinessException("Exception occured during get patient visits  : " + accountId, exp);
		}
		return patientVisit;
	}

	/*
	 * public List<PatientVisit> getRepPatientVisitUnitNum(String accountNumber)
	 * throws BusinessException { // account number is unit number of
	 * representative patient done for certification only --preet
	 * List<PatientVisit> patientVisit=null; try { TypedQuery<PatientVisit>
	 * query = (TypedQuery<PatientVisit>) entityManager.createNamedQuery(
	 * "PatientVisit.findByAccountNumber", PatientVisit.class);
	 * query.setParameter("accountNumber", accountNumber); patientVisit =
	 * query.getResultList();
	 * 
	 * } catch (NoResultException exp) { throw new
	 * BusinessException("No visits for unit number : " + accountNumber, exp); }
	 * catch (Exception exp) { throw new
	 * BusinessException("Exception occured during get patient visits  : " +
	 * accountNumber, exp); } return patientVisit; }
	 */

}
