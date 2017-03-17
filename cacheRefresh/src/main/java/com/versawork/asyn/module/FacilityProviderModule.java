/**
 * 
 */
package com.versawork.asyn.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;

import com.versawork.asyn.constant.CacheModule;
import com.versawork.asyn.dao.CacheRefreshDaoImpl;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.FacilityProvider;

/**
 * @author RAHUL BHALLA
 *
 */
public class FacilityProviderModule extends Module<FacilityProvider, FacilityProvider> implements
	Runnable {

    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param type
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public FacilityProviderModule(long transactionId, int clientDatabaseId,

    CacheAccessbyJson<FacilityProvider> cacheAccessbyJson,
	    CacheRefreshDaoImpl<FacilityProvider> cacheRefreshDao, MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId, cacheAccessbyJson,
		cacheRefreshDao, messageSource, clientId);
    }

    @Override
    public void run() {
	 super.run();
	List<FacilityProvider> healthcareProviders = null;
	String key = null;

	Map<String, List<FacilityProvider>> map = new HashMap<String, List<FacilityProvider>>();
	
//	String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
	String status = messageSource.getMessage("cache.etl.processed", null, "SUCCESS",null);
	String startIndexString = null;
	int size ;
	
	
	List<FacilityProvider> puthealthcareProviders= new ArrayList<>();
	
	
	try {
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "facilityProviderStatus", "facilityProviderStartTime");
	    startIndexString = messageSource.getMessage("facility.max.result", null, null,null);
	    Integer maxResult = startIndexString == null ? null : Integer.valueOf(startIndexString); 
	    int startPosition = 0;
	    key = CacheKeys.getFacilityProviderKey(clientDatabaseId);
	    map.put(key, puthealthcareProviders);
	    
	    do{
		healthcareProviders = cacheRefreshDao.getAppResults("FacilityProvider.findByClientDatabaseId", null, clientDatabaseId, startPosition, maxResult);
			size = healthcareProviders.size();
			startPosition = startPosition + size;
			puthealthcareProviders.addAll(healthcareProviders);
		

	    }while(maxResult != null && maxResult.equals(size));
	  
	    
	    status = cacheAccessbyJson.setCache(map, type, transactionId);
	
	} catch (Exception e) {
	    LOGGER.error("EXCEPTION IN FACILITY PROVIDER :\n"
		    + ExceptionUtils.getStackTrace(e));
	    status = messageSource.getMessage("cache.etl.exception", null,"EXCEPTION OCCUR", null);
	} finally {
	   // cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "facilityProviderStatus", "facilityProviderUpdateTime");
	    peristTransactionHistory(CacheModule.FACILITY_PROVIDER ,   status);
	    healthcareProviders = null;
	    key = null;
//	    readingFlag = null;
		 status = null;
		 startIndexString = null;
		puthealthcareProviders= null;
	    System.gc();
	}
    }

}
