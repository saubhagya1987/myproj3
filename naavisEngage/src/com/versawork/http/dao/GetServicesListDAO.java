package com.versawork.http.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.model.FacilityService;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class GetServicesListDAO {

	// private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "versawork_DS", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	public List<FacilityService> getServicesList(Integer clientDatabaseId) throws BusinessException {

		List<FacilityService> hospitalService = null;
		try {
			TypedQuery<FacilityService> query = (TypedQuery<FacilityService>) entityManager.createNamedQuery(
					"FacilityService.findByClientDatabaseId", FacilityService.class);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			hospitalService = query.getResultList();
		} catch (Exception exp) {

			throw new BusinessException("No client databse id found : " + clientDatabaseId, exp);
		}
		return hospitalService;
	}

}
