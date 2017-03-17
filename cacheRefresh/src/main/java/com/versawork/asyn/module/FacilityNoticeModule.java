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
import com.versawork.http.model.FacilityNotice;

/**
 * @author RAHUL BHALLA
 *
 */
public class FacilityNoticeModule extends Module<FacilityNotice, FacilityNotice> implements Runnable {
	/**
	 * @param transactionId
	 * @param clientDatabaseId
	 * @param type
	 * @param cacheAccessbyJson
	 * @param cacheRefreshDao
	 */
	public FacilityNoticeModule(long transactionId, int clientDatabaseId,

	CacheAccessbyJson<FacilityNotice> cacheAccessbyJson, CacheRefreshDaoImpl<FacilityNotice> cacheRefreshDao, MessageSource messageSource, Integer clientId) {

		super(transactionId, clientDatabaseId, cacheAccessbyJson, cacheRefreshDao, messageSource,clientId);
	}

	@Override
	public void run() {
	    super.run();
	    List<FacilityNotice> hospitalNotices  = null;
//	    startTime = new Date();
	    String key = null;
	    String status = messageSource.getMessage("cache.etl.processed", null,"SUCCESS", null);
//	    String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
	    String startIndexString = null;
	    int size ;
	    List<FacilityNotice> puthospitalNotices = new ArrayList<FacilityNotice>();
	    Map<String, List<FacilityNotice>> map = new HashMap<String, List<FacilityNotice>>();
		try {
//		    cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "facilityNoticeStatus", "facilityNoticeStartTime");
		    startIndexString = messageSource.getMessage("facility.max.result", null, null,null);
		    Integer maxResult = startIndexString == null ? null : Integer.valueOf(startIndexString); 
		    int startPosition = 0;
		    key = CacheKeys.getFacilityNoticeKey(clientDatabaseId);
		    map.put(key, puthospitalNotices);
		    
		    do{
				hospitalNotices = cacheRefreshDao.getAppResults("FacilityNotice.findByClientDatabaseId", null, super.clientDatabaseId, startPosition, maxResult);
				size = hospitalNotices.size();
				startPosition = startPosition + size;
				puthospitalNotices.addAll(hospitalNotices);
			

		    }while(maxResult != null && maxResult.equals(size));
		  
		    
		    status = cacheAccessbyJson.setCache(map, type, transactionId);
			
		} catch (Exception e) {

			LOGGER.error("EXCEPTION IN FACILITY NOTICE :\n" + ExceptionUtils.getStackTrace(e));
			status = messageSource.getMessage("cache.etl.exception", null,"EXCEPTION OCCUR", null);
			
		}finally{
		    peristTransactionHistory(CacheModule.FACILITY_NOTICE ,   status);
		   // cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "facilityNoticeStatus", "facilityNoticeUpdateTime");
		     hospitalNotices  = null;
		     key = null;
		     status = null;
//		     readingFlag = null;
		     map =  null;
		     puthospitalNotices = null;
		     System.gc();
		    
		}

	}
}
