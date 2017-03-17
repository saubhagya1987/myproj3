package com.versawork.http.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.ClientNaavisDatabases;

@Repository
public class ClientNaavisDatabaseServiceDAO implements Serializable {

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

	static Logger LOGGER = LoggerFactory.getLogger(ClientNaavisDatabaseServiceDAO.class);

	public ClientNaavisDatabases getClientNaavisDatabases(String patientVerificationUnitNumber)
			throws BusinessException, SystemException {

		ClientNaavisDatabases patientVerificationClientNaavisDatabase = null;
		try {
			TypedQuery<ClientNaavisDatabases> query = (TypedQuery<ClientNaavisDatabases>) ehrEntityManager
					.createNamedQuery("ClientNaavisDatabases.findByPatientVerification", ClientNaavisDatabases.class);
			query.setParameter("medicalRecordNumber", patientVerificationUnitNumber);
			patientVerificationClientNaavisDatabase = query.getSingleResult();

		} catch (NoResultException exp) {
			throw new BusinessException("no.clnt.nvs.db.for.untnmbr");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientVerificationClientNaavisDatabase;
	}

	public void update(ClientNaavisDatabases clientNaavisDatabases) throws BusinessException {
		ehrEntityManager.merge(clientNaavisDatabases);
	}

	public ClientNaavisDatabases getClientNaavisDatabases(Integer clientDatabaseId) throws BusinessException,
			SystemException {

		ClientNaavisDatabases clientNaavisDatabases = null;
		try {
			TypedQuery<ClientNaavisDatabases> query = (TypedQuery<ClientNaavisDatabases>) ehrEntityManager
					.createNamedQuery("ClientNaavisDatabases.findByClientDatabaseId", ClientNaavisDatabases.class);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			clientNaavisDatabases = query.getSingleResult();
		} catch (NoResultException exp) {
			LOGGER.debug("No hospital based on client db ID");
			throw new BusinessException("no.clnt.nvs.db.for.untnmbr");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return clientNaavisDatabases;
	}

}
