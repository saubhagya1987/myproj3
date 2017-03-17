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
public class LoginDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginDAO.class);
	private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	// Injected database connection:
	@PersistenceContext(unitName = "versawork_DS")
	private EntityManager em;

	public Account getAccountDetailsByEmailPaswrdPatient(String emailId, String password, Integer clientDatabaseId)
			throws BusinessException, SystemException {
		Account account = null;
		if (isDebugEnabled)
			LOGGER.debug("Getting Account Details By email Id : " + emailId + ", password : " + password);
		try {
			TypedQuery<Account> query = em.createNamedQuery("Account.findByEmailPasswordPatient", Account.class);
			query.setParameter("email", emailId);
			query.setParameter("password", password);
			query.setParameter("clientDatabaseId", clientDatabaseId);

			account = query.getSingleResult();
		} catch (NoResultException noResultException) {
			LOGGER.error("No Account Details found based on email and password");
			throw new BusinessException("email.paswrd.wrong");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return account;
	}

	public Account getAccountDetailsByEmailPaswrdProvider(String emailId, String password) throws BusinessException,
			SystemException {
		Account account = null;
		if (isDebugEnabled)
			LOGGER.debug("Getting Account Details By email Id : " + emailId + ", password : " + password);
		try {
			TypedQuery<Account> query = em.createNamedQuery("Account.findByEmailPasswordProvider", Account.class);
			query.setParameter("email", emailId);
			query.setParameter("password", password);
			account = query.getSingleResult();
		} catch (NoResultException noResultException) {
			LOGGER.error("No Account Details found based on email and password");
			throw new BusinessException("email.paswrd.wrong");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return account;
	}

}
