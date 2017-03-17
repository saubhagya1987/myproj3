package com.versawork.http.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.EtlInfo;
import com.versawork.http.model.EtlLogger;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class CacheRefreshDAO implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	public List<EtlLogger> getUnprocessedETLLog(Integer clientDatabaseID) throws BusinessException, SystemException {
		List<EtlLogger> etlLoggerInfo = null;
		try {
			TypedQuery<EtlLogger> query = (TypedQuery<EtlLogger>) ehrEntityManager.createNamedQuery(
					"EtlLogger.findByClientDatabaseIdUnprocessed", EtlLogger.class);
			query.setParameter("clientDatabaseId", clientDatabaseID);
			etlLoggerInfo = query.getResultList();// getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.res.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return etlLoggerInfo;
	}

	public List<EtlInfo> getEtlInfoForTransaction(long transactionId, Integer clientDatabaseID)
			throws BusinessException, SystemException {
		List<EtlInfo> etlInfoList = null;
		try {
			TypedQuery<EtlInfo> query = (TypedQuery<EtlInfo>) ehrEntityManager.createNamedQuery(
					"EtlInfo.findByTransactionId", EtlInfo.class);
			query.setParameter("transactionId", transactionId);
			query.setParameter("clientDatabaseId", clientDatabaseID);
			etlInfoList = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return etlInfoList;
	}

	@Transactional(value = "ehr_txn")
	public void update(EtlLogger etlLoggerInfo) {
		ehrEntityManager.merge(etlLoggerInfo);
	}
}