/**
 * 
 */
package com.versawork.asyn.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.versawork.asyn.constant.LogFile;
import com.versawork.asyn.exception.SystemException;
import com.versawork.http.exception.BusinessException;

/**
 * @author Rahul Bhalla
 *
 */

public abstract class CacheRefreshDaoImpl<T> {

    private Class<T> type;

    @SuppressWarnings("unchecked")
    public CacheRefreshDaoImpl() {
	Type t = getClass().getGenericSuperclass();
	ParameterizedType pt = (ParameterizedType) t;
	type = (Class<T>) pt.getActualTypeArguments()[0];
    }

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(LogFile.ETL_CACHE_LOG_FILE.getFileName());

    @PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
    private EntityManager ehrEntityManager;

    @PersistenceContext(unitName = "versawork_DS", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public List<T> getEHRResults(String nameQuery, Long transactionId,
	    int clientDatabaseId, Integer startPosition, Integer maxResult) {

	try {
	    TypedQuery<T> query = ehrEntityManager
		    .createNamedQuery(nameQuery, type);
	    query.setParameter("clientDatabaseId", clientDatabaseId);
	    if (transactionId != null){
		query.setParameter("transactionId", transactionId);
	    }
	    if (maxResult != null) {
		query.setFirstResult(startPosition);
		query.setMaxResults(maxResult);
	    }
	    LOGGER.debug("Quering database : " + new Date() + " to get list of records for module of "+ type);
	    synchronized (this) {
		return query.getResultList();
	    }
	} catch (NoResultException e) {
	    return Collections.emptyList();
	} catch (Exception e) {
	    LOGGER.error("Dao Exception Occur " + type + " \n"
		    + ExceptionUtils.getStackTrace(e));
	    e.printStackTrace();
	    throw new SystemException(e);
	}finally{
	    LOGGER.debug("Get response back : " + new Date() + " to module of "+ type);  
	}
    }

    @SuppressWarnings("unchecked")
    public List<T> getEHRNativeQueryResults(String nameQuery, Long transactionId,
	    int clientDatabaseId, Integer startPosition, Integer maxResult, Integer clientId) {

	try {
	    Query query =  ehrEntityManager.createNativeQuery(nameQuery, type);//createNamedQuery(nameQuery, type);
	    query.setParameter("clientDatabaseId", clientDatabaseId);
	    if (transactionId != null){
		query.setParameter("transactionId", transactionId);
	    }
	    if (transactionId != null){
		query.setParameter("clientId", clientId);
	    }
	    if (maxResult != null) {
		query.setFirstResult(startPosition);
		query.setMaxResults(maxResult);
	    }
	    LOGGER.debug("Quering database : " + new Date() + " to get list of records for module of "+ type);
	    return query.getResultList();
	} catch (NoResultException e) {
	    return Collections.emptyList();
	} catch (Exception e) {
	    LOGGER.error("Dao Exception Occur " + type + " \n"
		    + ExceptionUtils.getStackTrace(e));
	    e.printStackTrace();
	    throw new SystemException(e);
	}finally{
	    LOGGER.debug("Get response back : " + new Date() + " to module of "+ type);  
	}
    }
    public List<T> getAppResults(String nameQuery, Long transactionId,
	    int clientDatabaseId, Integer startPosition, Integer maxResult) {

	try {
	    TypedQuery<T> query = (TypedQuery<T>) entityManager
		    .createNamedQuery(nameQuery, type);
	    query.setParameter("clientDatabaseId", clientDatabaseId);
	    if (transactionId != null){
		query.setParameter("transactionId", transactionId);
	    }
	    if (maxResult != null) {
		query.setFirstResult(startPosition);
		query.setMaxResults(maxResult);
	    }
	    LOGGER.debug("Quering database : " + new Date() + " to get list of records for module of "+ type);
	    return query.getResultList();
	} catch (NoResultException e) {
	    return new ArrayList<T>();
	} catch (Exception e) {
	    LOGGER.error("Dao Exception Occur " + type + " " 
		    + ExceptionUtils.getStackTrace(e));
	    throw new SystemException(e);
	}finally{
	    LOGGER.debug("Get response back : " + new Date() + " to module of "+ type);  
	}
    }

    public T getAppResultById(int accountId, String nameQuery, String idField) {

	T aAccount = null;
	try {
	    TypedQuery<T> query = (TypedQuery<T>) entityManager
		    .createNamedQuery(nameQuery, type);
	    query.setParameter(idField, accountId);
	    aAccount = query.getSingleResult();
	} catch (Exception e) {

	    aAccount = null;
	}
	return aAccount;
    }

    @Transactional(value = "ehr_txn")
    public void updateEHR(List<T> list) {

	for (T logger : list) {
	    try {
		ehrEntityManager.merge(logger);
		
		LOGGER.debug("UPDATE ETL LOGGER WITH TRANSACTION ID "
			+ logger.toString());
	    } catch (RuntimeException ex) {
		LOGGER.error("EXCEPTION ETL LOGGER WITH TRANSACTION ID "
			+ logger.toString() + " \n "
			+ ExceptionUtils.getStackTrace(ex));

	    }
	}
    }
   /* @Transactional(value = "ehr_txn")
    public void updateEtlLoggerModuleStatus(String status, long transactionId,
	    int clientDatabaseId, String statusfield, String updateTimeField) {

	Query query = null;
	try {
	    String updateQueryString = getUpdateQuery(statusfield, updateTimeField);
	    query = ehrEntityManager.createQuery(updateQueryString);
	    query = query.setParameter("status", status);
	    query = query.setParameter("updateTimeField", new Date());
	    query = query.setParameter("transactionId", (int)transactionId);
	    query = query.setParameter("clientDatabaseId", clientDatabaseId);

	    LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++UPDATING MODULES FOR FIELD "
		    + statusfield
		    + " ++++++++++++++++++++++++++++++++++++++++++++++++++++");
	    synchronized(this){
	    query.executeUpdate();
	    }
	} catch (Exception e) {
	    LOGGER.error("Exception While updating field  " + statusfield
		    + "\n" + ExceptionUtils.getStackTrace(e));
	}
    }

    private String getUpdateQuery(String etlinfoField, String updateTimeField) {

	StringBuilder query = new StringBuilder("UPDATE EtlLogger logg SET logg.");
	query.append(etlinfoField);
	query.append(" = :status , logg.");
	query.append(updateTimeField);
	query.append(" = :updateTimeField ");
	query.append("WHERE logg.etlLoggerPK.transactionId = :transactionId and logg.etlLoggerPK.clientDatabaseId = :clientDatabaseId");

	return query.toString();
    }*/

    // TODO remove and make it generic
    public String getPatientVerification(Integer accountId)
	    throws BusinessException, SystemException {

	String patientVerification = null;
	try {
	    String queryString = "SELECT p.patientVerificationPK.medicalRecordNumber FROM PatientVerification p WHERE p.accountId=:accountId";//"PatientVerification.findByAccountId";
	    TypedQuery<String> query = (TypedQuery<String>) ehrEntityManager.createQuery(queryString, String.class);
	    query.setParameter("accountId", accountId);
	    patientVerification = query.getSingleResult();

	} catch (NoResultException exp) {
	    patientVerification = null;
	} catch (Exception exception) {
	    LOGGER.error(ExceptionUtils.getStackTrace(exception));
	    throw new SystemException(exception);
	}
	return patientVerification;
    }
    @Transactional(value = "ehr_txn")
    public void save(Object etlTransactionHistory) {
	ehrEntityManager.persist(etlTransactionHistory);
	
    }

    /*
     * //TODO remove and make it generic public Object
     * getPatientVerification(Class classF,Integer accountId) throws
     * BusinessException, SystemException {
     * 
     * PatientVerification patientVerification = null; try {
     * 
     * @SuppressWarnings("unchecked") TypedQuery<classF> query =
     * (TypedQuery<classF>) ehrEntityManager
     * .createNamedQuery("PatientVerification.findByAccountId", classF);
     * query.setParameter("accountId", accountId); patientVerification =
     * query.getSingleResult();
     * 
     * } catch (NoResultException exp) { patientVerification = null; } catch
     * (Exception exception) { throw new SystemException(exception); } return
     * patientVerification; }
     */

}
