package com.versawork.http.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.Immutable;
import org.springframework.stereotype.Repository;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.PatientLab;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class PatientLabServiceDao implements Serializable {

	/**
     * 
     */

	private static final long serialVersionUID = 1L;
	/*
	 * @PersistenceContext(unitName = "versawork_DS", type =
	 * PersistenceContextType.TRANSACTION) private EntityManager entityManager;
	 * 
	 * @PersistenceContext(unitName = "versawork_DS", type =
	 * PersistenceContextType.TRANSACTION)
	 * 
	 * @Immutable private EntityManager em;//for caching
	 */
	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;
	@Immutable
	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManagerAvoidCaching;

	public List<PatientLab> getPatientLab(Integer accountId) throws BusinessException, SystemException {
		List<PatientLab> patientLabs = null;
		try {
			TypedQuery<PatientLab> query = (TypedQuery<PatientLab>) ehrEntityManager.createNamedQuery(
					"PatientLab.findByAccountId", PatientLab.class);
			query.setParameter("accountId", accountId);
			patientLabs = query.getResultList();
		} catch (NoResultException noRes) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new BusinessException("no.acc.details.found");
		}
		return patientLabs;
	}

	public List<PatientLab> getAllLabResData(Integer clientDatabaseId) throws BusinessException, SystemException {
		List<PatientLab> patientLabs = null;
		try {
			TypedQuery<PatientLab> query = (TypedQuery<PatientLab>) ehrEntityManagerAvoidCaching.createNamedQuery(
					"PatientLab.findByClientDatabaseId", PatientLab.class);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			patientLabs = query.getResultList();
		} catch (NoResultException noRes) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new BusinessException("no.acc.details.found");
		}
		return patientLabs;
	}

	public List<PatientLab> getPatientLabGroups(Integer accountId) throws SystemException, BusinessException {
		List<PatientLab> patientLabs = null;
		try {
			TypedQuery<PatientLab> query = (TypedQuery<PatientLab>) ehrEntityManagerAvoidCaching.createNamedQuery(
					"PatientLab.findByClientDatabaseId", PatientLab.class);
			query.setParameter("accountId", accountId);
			patientLabs = query.getResultList();
		} catch (NoResultException noRes) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new BusinessException("no.acc.details.found");
		}
		return patientLabs;
	}

	public List<PatientLab> getLabTestHistory(NsRequest nsRequest) throws SystemException, BusinessException {
		List<PatientLab> patientLabs = null;
		try {
			TypedQuery<PatientLab> query = (TypedQuery<PatientLab>) ehrEntityManagerAvoidCaching.createNamedQuery(
					"PatientLab.findByAccountIdTestId", PatientLab.class);
			query.setParameter("accountId", nsRequest.getAccountInfo().getAccountId());
			query.setParameter("labCode", nsRequest.getTestId());
			patientLabs = query.getResultList();
		} catch (NoResultException noRes) {
			throw new BusinessException("no.lab.history.found");
		} catch (Exception exception) {
			throw new BusinessException("no.lab.history.found");
		}
		return patientLabs;
	}
}
