/**
 * 
 */
package com.versawork.asyn.module;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import com.google.gson.reflect.TypeToken;
import com.versawork.asyn.constant.CacheModule;
import com.versawork.asyn.constant.LogFile;
import com.versawork.asyn.dao.CacheRefreshDaoImpl;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.ETLTransactionHistory;

/**
 * @author RAHUL BHALLA
 *
 */
public abstract class Module<T, E> implements Runnable {
	protected final Logger LOGGER = LoggerFactory.getLogger(LogFile.ETL_CACHE_LOG_FILE.getFileName());
	protected long transactionId;
	protected int clientDatabaseId;
	protected Type type;
	protected CacheAccessbyJson<T> cacheAccessbyJson;
	protected CacheRefreshDaoImpl<E> cacheRefreshDao;
	protected MessageSource messageSource;
	protected Date startTime;
	protected Integer clientId;
	// protected CacheAccessbyJson cacheAccessbyJson;
	public Module(long transactionId, int clientDatabaseId,
	/* */CacheAccessbyJson<T> cacheAccessbyJson, CacheRefreshDaoImpl<E> cacheRefreshDao, MessageSource messageSource, Integer clientId) {
		this.transactionId = transactionId;
		this.clientDatabaseId = clientDatabaseId;
		this.type = new TypeToken<List<T>>() {
		}.getType();
		;
		this.clientId = clientId;
		// this.updateCacheRefresDao = updateCacheRefresDao;
		this.cacheAccessbyJson = cacheAccessbyJson;
		this.cacheRefreshDao = cacheRefreshDao;
		this.messageSource = messageSource;
	}

	/**
	 * Need to Implement by each module
	 */
	public  void run(){
	    startTime = new Date();
	}

	/**
	 * 
	 * @return
	 */
	public long getTransactionId() {

		return transactionId;
	}

	/**
	 * 
	 * @param transactionId
	 */
	public void setTransactionId(long transactionId) {

		this.transactionId = transactionId;
	}

	/**
	 * 
	 * @return
	 */
	public int getClientDatabaseId() {

		return clientDatabaseId;
	}

	/**
	 * 
	 * @param clientDatabaseId
	 */
	public void setClientDatabaseId(int clientDatabaseId) {

		this.clientDatabaseId = clientDatabaseId;
	}

	/**
	 * @return the type
	 */
	public Type getType() {

		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Type type) {

		this.type = type;
	}

	/**
	 * @return the updateCacheRefresDao
	 */
	/*
	 * public UpdateCacheRefresDao getUpdateCacheRefresDao() {
	 * 
	 * return updateCacheRefresDao; }
	 *//**
	 * @param updateCacheRefresDao
	 *            the updateCacheRefresDao to set
	 */
	/*
	 * public void setUpdateCacheRefresDao(UpdateCacheRefresDao
	 * updateCacheRefresDao) {
	 * 
	 * this.updateCacheRefresDao = updateCacheRefresDao; }
	 */

	/**
	 * @return the cacheAccessbyJson
	 */
	public CacheAccessbyJson<T> getCacheAccessbyJson() {

		return cacheAccessbyJson;
	}

	/**
	 * @param cacheAccessbyJson
	 *            the cacheAccessbyJson to set
	 */
	public void setCacheAccessbyJson(CacheAccessbyJson<T> cacheAccessbyJson) {

		this.cacheAccessbyJson = cacheAccessbyJson;
	}

	/**
	 * @return the cacheRefreshDao
	 */
	public /*GetCacheRefreshDao*/CacheRefreshDaoImpl<E> getCacheRefreshDao() {
	    return cacheRefreshDao;
	}

	/**
	 * @param cacheRefreshDao the cacheRefreshDao to set
	 */
	public void setCacheRefreshDao(/*GetCacheRefreshDao*/CacheRefreshDaoImpl<E> cacheRefreshDao) {
	    this.cacheRefreshDao = cacheRefreshDao;
	}

	
	public void peristTransactionHistory(CacheModule module, String status){
	    
	    ETLTransactionHistory etlTransactionHistory = new ETLTransactionHistory();
	    etlTransactionHistory.setClientDatabaseId(clientDatabaseId);
	    etlTransactionHistory.setEndTime(new Date());
	    etlTransactionHistory.setModule(module.getModuleName());
	    etlTransactionHistory.setStartTime(startTime);
	    etlTransactionHistory.setStatus(status);
	    etlTransactionHistory.setTransactionId(transactionId);
	    
	    cacheRefreshDao.save(etlTransactionHistory);
	    
	}

	/**
	 * @return the messageSource
	 */
	public MessageSource getMessageSource() {
	    return messageSource;
	}

	/**
	 * @param messageSource the messageSource to set
	 */
	public void setMessageSource(MessageSource messageSource) {
	    this.messageSource = messageSource;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
	    return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
	    this.startTime = startTime;
	}

	/**
	 * @return the clientId
	 */
	public Integer getClientId() {
	    return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Integer clientId) {
	    this.clientId = clientId;
	}


}
