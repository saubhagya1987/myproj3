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
import com.versawork.http.model.FacilityService;

/**
 * @author RAHUL BHALLA
 *
 */
public class FacilityServiceModule extends Module<FacilityService, FacilityService> implements
	Runnable {
    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param type
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public FacilityServiceModule(long transactionId, int clientDatabaseId,

    CacheAccessbyJson<FacilityService> cacheAccessbyJson,
	    CacheRefreshDaoImpl<FacilityService> cacheRefreshDao, MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId, cacheAccessbyJson,
		cacheRefreshDao, messageSource, clientId);
    }

    @Override
    public void run() {
	 super.run();

	List<FacilityService> facilityServices = null;
	String key = null;

	Map<String, List<FacilityService>> map = new HashMap<String, List<FacilityService>>();
	
	
	String status = messageSource.getMessage("cache.etl.processed", null, "SUCCESS",null);
	String maxResultString = null;
	int size ;
	
	List<FacilityService> putFacilityServices= new ArrayList<>();
	
	
	try {
//	    String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "facilityServiceStatus","facilityServiceStartTime");
//	    readingFlag = null;
	    maxResultString = messageSource.getMessage("facility.max.result", null, null,null);
	    Integer maxResult = maxResultString == null ? null : Integer.valueOf(maxResultString); 
	    int startPosition = 0;
	    key = CacheKeys.getFacilityServiceKey(clientDatabaseId);
	    map.put(key, putFacilityServices);
	    
	    do{
		facilityServices = cacheRefreshDao.getAppResults("FacilityService.findByClientDatabaseId", null, clientDatabaseId, startPosition, maxResult);
		size = facilityServices.size();
		startPosition = startPosition + size;
		putFacilityServices.addAll(facilityServices);
		

	    }while(maxResult != null && maxResult.equals(size));
	  
	    
	    status = cacheAccessbyJson.setCache(map, type, transactionId);
	
	} catch (Exception e) {
	    LOGGER.error("EXCEPTION IN FACILITY SERVICE :\n"
		    + ExceptionUtils.getStackTrace(e));
	    status = messageSource.getMessage("cache.etl.exception", null,"EXCEPTION OCCUR", null);
	} finally {
	    
//	    cacheRefreshDao.(status, transactionId, clientDatabaseId, "facilityServiceStatus","facilityServiceUpdateTime");
	    peristTransactionHistory(CacheModule.FACILITY_SERVICE ,   status);
	     facilityServices = null;
	     key = null;
	     map = null;
	    
	     status = null;
	     maxResultString = null;
	     putFacilityServices=null;
	     System.gc();
	}
	
	
    }

}
