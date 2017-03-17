package com.versawork.http.dao;

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

/**
 * @author Dheeraj
 * 
 */

@Repository
public class GetProfileInfoDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginDAO.class);
	private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	// Injected database connection:
	@PersistenceContext(unitName = "versawork_DS")
	private EntityManager em;

	public Account getAccountDetailsByAuthToken(String authToken) throws BusinessException, SystemException {
		Account account = null;
		if (isDebugEnabled)
			LOGGER.debug("Getting Account Details By email Id : " + authToken);
		try {
			TypedQuery<Account> query = em.createNamedQuery("Account.findByAuthToken", Account.class);
			query.setParameter("authToken", authToken);

			account = query.getSingleResult();
		} catch (NoResultException noResultException) {
			LOGGER.error("No Account Details found based on authentication token");
			throw new BusinessException("invalid.authtoken.inrequest");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return account;
	}

}
