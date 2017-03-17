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
import com.versawork.asyn.dataobject.AllergiesInfo;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientAllergy;

/**
 * @author RAHUL BHALLA
 *
 */
public class PatientAllergiesModule extends Module<AllergiesInfo, PatientAllergy> implements
	Runnable {
    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param type
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public PatientAllergiesModule(long transactionId, int clientDatabaseId,

    CacheAccessbyJson<AllergiesInfo> cacheAccessbyJson,
	    CacheRefreshDaoImpl<PatientAllergy> cacheRefreshDao, MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId, cacheAccessbyJson,
		cacheRefreshDao, messageSource, clientId);
    }

    @Override
    public void run() {
	 super.run();
	List<PatientAllergy> patientAllergies = null;
	Map<String, List<AllergiesInfo>> cacheKeysValue = new HashMap<String, List<AllergiesInfo>>();
	String status = messageSource.getMessage("cache.etl.processed", null, "SUCCESS",null);

	String key = null;
	String maxResultString = null;
	int  size;
	List<AllergiesInfo> allergyList = null;
	PatientAllergy allergy = null;
	  Iterator<PatientAllergy> iterator ;
	    
	    try {
//		String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
//		cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "pallStatus", "pallStartTime");
//		readingFlag = null;
		    maxResultString = messageSource.getMessage("module.max.result",
			    null, null, null);
		    Integer maxResult = maxResultString == null ? null : Integer
			    .valueOf(maxResultString);
		    int startPosition = 0;

		  
String query = "SELECT p.account_id, p.allergy_id, p.source_id, p.source_name, p.allergen_name, p.allergen_code, p.reaction_code, p.status, p.status_code, p.date_added, p.reaction FROM patient_allergy p INNER JOIN etl_info info ON info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id ORDER BY  p.account_id ";
//query = PatientAllergy.findByEtlInfoAccountSubQuery
		    do {
			
			patientAllergies = cacheRefreshDao.getEHRNativeQueryResults(query, transactionId,
				clientDatabaseId, startPosition, maxResult,  clientId);

			size = patientAllergies.size();
			startPosition = startPosition + size;

			iterator = patientAllergies.iterator();

			while (iterator.hasNext()) {
			    allergy = iterator.next();
			    /**
				 * save object by account id
				 */
				key = CacheKeys.getAllergiesKey(allergy.getPatientAllergyPK().getAccountId(), super.clientDatabaseId);
				 allergyList = cacheKeysValue.get(key);
				if (allergyList == null) {
				    if (!cacheKeysValue.isEmpty()) {
					status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
					cacheKeysValue.clear();
					
				    }
				    allergyList = new ArrayList<AllergiesInfo>();
				    cacheKeysValue.put(key, allergyList);
				} 
				
				allergyList.add(CacheInfoObject
					.getPatientAllergiesInfo(allergy));
				iterator.remove();
			}
	    
		    } while (maxResult != null && maxResult.equals(size));
		    
		    if (!cacheKeysValue.isEmpty()) {
			status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
			cacheKeysValue.clear();
			
		    }
	} catch (Exception e) {
	    LOGGER.error("EXCEPTION IN PATIENT ALLERGIES : \n"
		    + ExceptionUtils.getStackTrace(e));
	     status = messageSource.getMessage("cache.etl.exception", null,
			"EXCEPTION OCCUR", null);
	} finally {
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "pallStatus", "pallUpdateTime");
	    peristTransactionHistory(CacheModule.PATIENT_ALLERGY ,   status);
	     patientAllergies = null;
	     cacheKeysValue = null;
	     status =  null;
	     key = null;
	     maxResultString = null;
	     allergyList = null;
	     allergy = null;
	     iterator = null ;
	    System.gc();
	}
    } 
}
