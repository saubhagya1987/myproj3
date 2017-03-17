/**
 * 
 */
package com.versawork.asyn.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;

import com.versawork.asyn.constant.CacheModule;
import com.versawork.asyn.dao.CacheRefreshDaoImpl;
import com.versawork.asyn.dataobject.PatientImagingInfo;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientImaging;

/**
 * @author RAHUL BHALLA
 *
 */
public class PatientImagingModule extends
	Module<PatientImagingInfo, PatientImaging> implements Runnable {

    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public PatientImagingModule(long transactionId, int clientDatabaseId,

    CacheAccessbyJson<PatientImagingInfo> cacheAccessbyJson,
	    CacheRefreshDaoImpl<PatientImaging> cacheRefreshDao,
	    MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId, cacheAccessbyJson,
		cacheRefreshDao, messageSource, clientId);
    }

    @Override
    public void run() {
	 super.run();
	List<PatientImaging> patientImages = null;
	Map<String, List<PatientImagingInfo>> cacheKeysValue = new HashMap<String, List<PatientImagingInfo>>();
	String key = null;

	List<PatientImagingInfo> patientImagingInfoList = null;
	String status= messageSource.getMessage("cache.etl.processed", null,"SUCCESS", null);
	PatientImaging image = null;
	String maxResultString = null;
	int size;
	Iterator<PatientImaging> iterator;

	try {

//	    String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
////	    cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "imgStatus", "imgStartTime");
//	    readingFlag = null;
	    
	    maxResultString = messageSource.getMessage("module.max.result", null, null, null);
	    Integer maxResult = maxResultString == null ? null : Integer
		    .valueOf(maxResultString);
	    int startPosition = 0;
//	    String query = "PatientImaging.findByEtlInfoAccountSubQuery"
	    StringBuilder query = new StringBuilder();
	    query.append("SELECT p.account_id, p.patient_visit_id, p.exam_id, ");
	    query.append("p.source_id, p.source_name, p.exam_name, p.ordering_provider, ");
	    query.append("p.exam_technologist, p.exam_date, p.exam_message, p.date_added " );
	    query.append("FROM patient_imaging p INNER JOIN etl_info info ON  ");
	    query.append("info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id");
	    query.append(" ORDER BY  p.account_id, p.patient_visit_id");
	    do {
		patientImages = cacheRefreshDao.getEHRNativeQueryResults(
			query.toString(), transactionId,
			clientDatabaseId, startPosition, maxResult,  clientId);

		size = patientImages.size();
		startPosition = startPosition + size;

		iterator = patientImages.iterator();

		while (iterator.hasNext()) {
		    image = iterator.next();
		    key = CacheKeys.getPatientImagingKey(clientDatabaseId,
			    image.getPatientImagingPK().getAccountId());
		    patientImagingInfoList = cacheKeysValue.get(key);
		    
		    if (patientImagingInfoList == null) {
			if (!cacheKeysValue.isEmpty()) {
			    
			    status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
			    cacheKeysValue.clear();
			    
			}
			patientImagingInfoList = new ArrayList<PatientImagingInfo>();
			cacheKeysValue.put(key, patientImagingInfoList);
		    }

		    patientImagingInfoList.add(CacheInfoObject.getPatientImagingInfo(image));
		    iterator.remove();
		}
	    } while (maxResult != null && maxResult.equals(size));

	    if (!cacheKeysValue.isEmpty()) {
		status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
		cacheKeysValue.clear();

	    }

	} catch (Exception e) {
	    LOGGER.error("EXCEPTION IN PATIENT IMAGING"
		    + ExceptionUtils.getStackTrace(e));
	    status = messageSource.getMessage("cache.etl.exception", null,
		    "EXCEPTION OCCUR", null);
	} finally {
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId,
//		    clientDatabaseId, "imgStatus", "imgUpdateTime");
	    peristTransactionHistory(CacheModule.PATIENT_IMAGING ,   status);
	    patientImages = null;
	    cacheKeysValue = null;
	    key = null;
	    patientImagingInfoList = null;
	    status = null;
	    image = null;
	    System.gc();
	}
    }

}
