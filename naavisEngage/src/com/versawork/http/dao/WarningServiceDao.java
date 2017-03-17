package com.versawork.http.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Warnings;

@Repository
public class WarningServiceDao {
	@PersistenceContext(unitName = "versawork_DS", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	public void save(Warnings warnings) throws BusinessException, SystemException {
		try {
			entityManager.persist(warnings);
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	public List<Warnings> findMedicationWarnings(int accountId) throws SystemException {
		List<Warnings> warnings = Collections.emptyList();
		try {
			TypedQuery<Warnings> query = (TypedQuery<Warnings>) entityManager.createNamedQuery(
					"Warnings.findByAccountIdAndWarningType", Warnings.class);
			query.setParameter("accountId", accountId);
			query.setParameter("warningType", "medication_warning");
			query.setParameter("isActive", true);
			warnings = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return warnings;
	}

	public List<Warnings> findBloodPressureWarnings(int accountId) throws SystemException {
		List<Warnings> warnings = Collections.emptyList();
		try {
			TypedQuery<Warnings> query = (TypedQuery<Warnings>) entityManager.createNamedQuery(
					"Warnings.findByAccountIdAndWarningType", Warnings.class);
			query.setParameter("accountId", accountId);
			query.setParameter("warningType", "bloodpressure_warning");
			query.setParameter("isActive", true);
			warnings = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return warnings;
	}

	public List<Warnings> findMedicationWarning(int accountId, boolean isViewed) throws SystemException {
		List<Warnings> warnings = Collections.emptyList();
		try {
			TypedQuery<Warnings> query = (TypedQuery<Warnings>) entityManager.createNamedQuery(
					"Warnings.findByAccountIdAndIsViewedAndWarningType", Warnings.class);
			query.setParameter("accountId", accountId);
			query.setParameter("isViewed", isViewed);
			query.setParameter("warningType", "medication_warning");
			query.setParameter("isActive", true);
			warnings = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return warnings;
	}

	public void save(List<Warnings> warnings) throws BusinessException, SystemException {
		try {
			for (Warnings warning : warnings)
				entityManager.persist(warning);
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	public void remove(List<Warnings> fetchMedicationWarnigns) throws SystemException {
		try {
			for (Warnings warning : fetchMedicationWarnigns)
				entityManager.remove(warning);
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

	}

	public List<Warnings> findBloodPressureWarning(Integer accountId, boolean isViewed) throws SystemException {
		List<Warnings> warnings = Collections.emptyList();
		try {
			TypedQuery<Warnings> query = (TypedQuery<Warnings>) entityManager.createNamedQuery(
					"Warnings.findByAccountIdAndIsViewedAndWarningType", Warnings.class);
			query.setParameter("accountId", accountId);
			query.setParameter("isViewed", isViewed);
			query.setParameter("warningType", "bloodpressure_warning");
			query.setParameter("isActive", true);
			warnings = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return warnings;
	}

	public void update(Warnings w) {
		entityManager.merge(w);

	}
}
