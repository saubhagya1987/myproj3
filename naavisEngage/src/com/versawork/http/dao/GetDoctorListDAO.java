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
import com.versawork.http.model.FacilityProvider;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class GetDoctorListDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "versawork_DS", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	public List<FacilityProvider> getDoctorsList(Integer findByClientDatabaseId) throws BusinessException {

		List<FacilityProvider> hospitalProvider = null;
		try {
			TypedQuery<FacilityProvider> query = (TypedQuery<FacilityProvider>) entityManager.createNamedQuery(
					"FacilityProvider.findByClientDatabaseId", FacilityProvider.class);
			query.setParameter("clientDatabaseId", findByClientDatabaseId);
			hospitalProvider = query.getResultList();

		} catch (NoResultException exp) {
			throw new BusinessException("No doctors based on clientDatabBaseId : " + findByClientDatabaseId, exp);
		} catch (Exception exp) {
			throw new BusinessException("Exception occured during get getDoctors List  : " + findByClientDatabaseId,
					exp);
		}
		return hospitalProvider;
	}

	public FacilityProvider getProviderByProviderId(String providerId, Integer clientDatabaseId)
			throws BusinessException {

		FacilityProvider hospitalProvider = null;
		try {
			TypedQuery<FacilityProvider> query = (TypedQuery<FacilityProvider>) entityManager.createNamedQuery(
					"FacilityProvider.findByProviverAndClientDatabaseId", FacilityProvider.class);
			query.setParameter("providerId", providerId);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			hospitalProvider = query.getSingleResult();

		} catch (NoResultException exp) {
			throw new BusinessException("no.provider.details.found");
		} catch (Exception exp) {
			throw new BusinessException("Exception occured during get getDoctors List  : " + clientDatabaseId, exp);
		}
		return hospitalProvider;
	}

	public FacilityProvider getProviderByProId(String providerId, Integer clientDatabaseId, Integer clientId)
			throws BusinessException {

		FacilityProvider hospitalProvider = null;
		try {
			TypedQuery<FacilityProvider> query = (TypedQuery<FacilityProvider>) entityManager.createNamedQuery(
					"FacilityProvider.findByProviverAndClientDatabaseIdAndClientId", FacilityProvider.class);
			query.setParameter("providerId", providerId);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			query.setParameter("clientId", clientId);
			hospitalProvider = query.getSingleResult();

		} catch (NoResultException exp) {
			hospitalProvider = new FacilityProvider();
			hospitalProvider.setSpecialty(" ");
			hospitalProvider.setContactEmail(" ");
			return hospitalProvider;
		} catch (Exception exp) {
			throw new BusinessException("Exception occured during get ProviderBy ProId  : ", exp);
		}
		return hospitalProvider;
	}

}
