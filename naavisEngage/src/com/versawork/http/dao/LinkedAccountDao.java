package com.versawork.http.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.ClientNaavisDatabases;
import com.versawork.http.model.LinkedAccountsRel;
import com.versawork.http.model.PatientBillDetail;
import com.versawork.http.model.PatientBillSummary;
import com.versawork.http.model.PatientBillSummaryPK;

@Repository
public class LinkedAccountDao {
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "versawork_DS", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;
	static Logger LOGGER = LoggerFactory.getLogger(LinkedAccountDao.class);

	public void save(LinkedAccountsRel linkedAccount) throws SystemException {
		entityManager.persist(linkedAccount);
	}

	public void remove(LinkedAccountsRel linkedAccount) throws SystemException {
		entityManager.remove(linkedAccount);
	}

	public LinkedAccountsRel getLinkedAccount(int primaryAccountId) throws SystemException {
		return entityManager.find(LinkedAccountsRel.class, primaryAccountId);// (arg0,
																				// arg1)
	}

	public List<LinkedAccountsRel> getLinkedAccounts(int accountId) throws SystemException, BusinessException {
		List<LinkedAccountsRel> linkedAccounts = null;

		TypedQuery<LinkedAccountsRel> query = (TypedQuery<LinkedAccountsRel>) entityManager.createNamedQuery(
				"LinkedAccountsRel.findByAccountId", LinkedAccountsRel.class);
		query.setParameter("accountId", accountId);
		linkedAccounts = query.getResultList();

		return linkedAccounts;
	}

	public LinkedAccountsRel isAccountLinked(Integer accountId, Integer linkedAccountId) throws BusinessException,
			SystemException {
		LinkedAccountsRel linkedAccount = null;
		try {
			TypedQuery<LinkedAccountsRel> query = (TypedQuery<LinkedAccountsRel>) entityManager.createNamedQuery(
					"LinkedAccountsRel.findByAccountLinkedAccountId", LinkedAccountsRel.class);
			query.setParameter("accountId", accountId);
			query.setParameter("linkedAccountId", linkedAccountId);
			linkedAccount = query.getSingleResult();
		} catch (NoResultException exception) {
			LOGGER.debug("No records based on account id and linked account id");
			return null; // Requirement of this module to return null
		}

		return linkedAccount;
	}

	public LinkedAccountsRel getAccount(Integer accountId, Integer clientDatabaseId) throws BusinessException,
			SystemException {
		LinkedAccountsRel linkedAccount = null;
		try {
			TypedQuery<LinkedAccountsRel> query = (TypedQuery<LinkedAccountsRel>) entityManager.createNamedQuery(
					"LinkedAccountsRel.findByAccountLinkedClientdbId", LinkedAccountsRel.class);
			query.setParameter("accountId", accountId);
			query.setParameter("linkedAccountClientdbId", clientDatabaseId);
			linkedAccount = query.getSingleResult();
		} catch (NoResultException exception) {
			LOGGER.debug("No records based on Client DB ID");
			throw new BusinessException("no.account.for.clientdbid"); // Requirement
																		// of
																		// this
																		// module
																		// to
																		// return
																		// null
		}

		return linkedAccount;
	}

	public List<ClientNaavisDatabases> getFacilitiesList(Integer clientDatabaseId, String searchFacility)
			throws BusinessException, SystemException {
		List<ClientNaavisDatabases> clientNaavisDatabases = null;

		TypedQuery<ClientNaavisDatabases> query = (TypedQuery<ClientNaavisDatabases>) entityManager.createNamedQuery(
				"ClientNaavisDatabases.findRestFacilitiesORCity", ClientNaavisDatabases.class);
		query.setParameter("facilityName", "%" + searchFacility + "%");
		query.setParameter("city", "%" + searchFacility + "%");
		clientNaavisDatabases = query.getResultList();

		return clientNaavisDatabases;
	}

}
