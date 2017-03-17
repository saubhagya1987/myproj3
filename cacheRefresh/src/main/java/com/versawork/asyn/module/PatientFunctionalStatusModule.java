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
import com.versawork.asyn.dataobject.PatientInpatientFunctionalStatusInfo;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientFunctionalStatus;

/**
 * @author RAHUL BHALLA
 *
 */
public class PatientFunctionalStatusModule extends
	Module<PatientInpatientFunctionalStatusInfo, PatientFunctionalStatus> implements Runnable {
    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public PatientFunctionalStatusModule(
	    long transactionId,
	    int clientDatabaseId,

	    CacheAccessbyJson<PatientInpatientFunctionalStatusInfo> cacheAccessbyJson,
	    CacheRefreshDaoImpl<PatientFunctionalStatus> cacheRefreshDao, MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId, cacheAccessbyJson,
		cacheRefreshDao, messageSource, clientId);
    }

    @Override
    public void run() {
	 super.run();
	List<PatientFunctionalStatus> patientFunctionalStatus = null;
	Map<String, List<PatientInpatientFunctionalStatusInfo>> cacheKeysValue = new HashMap<String, List<PatientInpatientFunctionalStatusInfo>>();;
	String visitKey = null;
	List<PatientInpatientFunctionalStatusInfo> functionalStatusInfoListByVisit = null;
	PatientFunctionalStatus functionalStatus = null;
	String status = messageSource.getMessage("cache.etl.processed", null,"SUCCESS", null);
	String maxResultString = null;
	Integer size;
	 Iterator<PatientFunctionalStatus> iterator;
	try {

//	    String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
////	    cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "functionalStatus","functionalStartTime");
//	    readingFlag = null;
	    maxResultString = messageSource.getMessage("module.max.result",
		    null, null, null);
	    Integer maxResult = maxResultString == null ? null : Integer
		    .valueOf(maxResultString);
	    int startPosition = 0;
//	    String query = "PatientFunctionalStatus.findByEtlInfoAccountSubQuery";
	    StringBuilder query = new StringBuilder();
	    query.append( "SELECT p.account_id, p.patient_visit_id, p.function_id, p.function_id2, ");
	    query.append( "p.source_id, p.source_name, p.function_description, p.function_code, p.status, ");
	    query.append( "p.status_code, p.date_added, p.status_date FROM patient_functional_status p ");
	    query.append( "INNER JOIN etl_info info ON ");
	    query.append( "info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id  ");
	    query.append( "ORDER BY  p.account_id, p.patient_visit_id");
	    	
	    do {
		patientFunctionalStatus = cacheRefreshDao.getEHRNativeQueryResults(query.toString(), transactionId, clientDatabaseId, startPosition, maxResult,  clientId);

		size = patientFunctionalStatus.size();
		startPosition = startPosition + size;
		iterator = patientFunctionalStatus.iterator();

        	    while (iterator.hasNext()) {
        		functionalStatus = iterator.next();
        		/**
        		 * save object by account id
        		 */
        		visitKey = CacheKeys.getFunctionalStatusKeyByVisit(functionalStatus.getPatientFunctionalStatusPK().getAccountId(),
        			super.clientDatabaseId, functionalStatus.getPatientFunctionalStatusPK()
        				.getPatientVisitId());
        		functionalStatusInfoListByVisit = cacheKeysValue.get(visitKey);
        		if (functionalStatusInfoListByVisit == null) {
        		    if (!cacheKeysValue.isEmpty()) {
        			status = cacheAccessbyJson.setCache(cacheKeysValue,
        				type, transactionId);
        			cacheKeysValue.clear();
        			
        		    }
        		    functionalStatusInfoListByVisit = new ArrayList<PatientInpatientFunctionalStatusInfo>();
        		    cacheKeysValue.put(visitKey,
        			    functionalStatusInfoListByVisit);
        		   
        		}
        
        		functionalStatusInfoListByVisit.add(CacheInfoObject.getPatientInpatientFunctionalStatusInfo(functionalStatus));
        		iterator.remove();
        	    }

	    } while (maxResult != null && maxResult.equals(size));
	    
	    if (!cacheKeysValue.isEmpty()) {
		status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
		cacheKeysValue.clear();
		
	    }
	} catch (Exception e) {
	    LOGGER.error("EXCEPTION IN PATIENT FUNCTIONAL STATUS :\n "
		    + ExceptionUtils.getStackTrace(e));
	    status = messageSource.getMessage("cache.etl.exception", null,
			"EXCEPTION OCCUR", null);
	} finally {
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "functionalStatus","functionalUpdateTime");
	    peristTransactionHistory(CacheModule.PATIENT_FUNCTIONAL_STATUS ,   status);
	    cacheKeysValue = new HashMap<String, List<PatientInpatientFunctionalStatusInfo>>();;
	    visitKey = null;
	    functionalStatusInfoListByVisit = null;
	    functionalStatus = null;
	    status = null;
	    maxResultString = null;
	    size= null;
	    iterator= null;
	    System.gc();
	}
    }
}
