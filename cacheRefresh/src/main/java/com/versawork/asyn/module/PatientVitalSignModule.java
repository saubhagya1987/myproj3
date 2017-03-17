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
import com.versawork.asyn.dataobject.VitalSigns;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientVitalSign;

/**
 * 
 * @author RAHUL BHALLA
 *
 */

public class PatientVitalSignModule extends Module<VitalSigns, PatientVitalSign> implements
	Runnable {

    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public PatientVitalSignModule(long transactionId, int clientDatabaseId,
	    CacheAccessbyJson<VitalSigns> cacheAccessbyJson,
	    CacheRefreshDaoImpl<PatientVitalSign> cacheRefreshDao, MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId, cacheAccessbyJson,
		cacheRefreshDao, messageSource, clientId);
    }

    @Override
    public void run() {
	 super.run();
	List<PatientVitalSign> patientVitialSigns = null;

	String visitKey = null;

	List<VitalSigns> visitSignListByVisit = null;
	Map<String, List<VitalSigns>>  cacheKeysValue = new HashMap<String, List<VitalSigns>>();
	String maxResultString = null;
	int  size;
	Iterator<PatientVitalSign> iterator ;
	String status =  messageSource.getMessage("cache.etl.processed", null,"SUCCESS", null);
	PatientVitalSign vitialSign = null;
	    try {
//		 String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null); 
//		 cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "pVitalSignStatus", "pVitalSignStartTime");
//		        readingFlag = null;
			      
		    maxResultString = messageSource.getMessage("module.max.result", null, null, null);
		    Integer maxResult = maxResultString == null ? null : Integer.valueOf(maxResultString);
		    int startPosition = 0;
		    StringBuilder query = new StringBuilder();
		    
		    query.append(" SELECT p.account_id, p.patient_visit_id, p.source_id, p.source_name, p.height_feet, p.height_inches, p.weight_lbs, ");
		     query.append("p.systolic_bp, p.systolic_bp_unit, p.diastolic_bp, ");
		     query.append("p.diastolic_bp_unit, p.bmi, p.date_added FROM patient_vital_sign p ");
		    query.append("INNER JOIN etl_info info ON ");
		    query.append("info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id");
		    query.append(" ORDER BY  p.account_id, p.patient_visit_id");
		    do {
			patientVitialSigns = cacheRefreshDao.getEHRNativeQueryResults(query.toString(), transactionId,
				clientDatabaseId, startPosition, maxResult, clientId);

			size = patientVitialSigns.size();
			startPosition = startPosition + size;

			iterator = patientVitialSigns.iterator();

			while (iterator.hasNext()) {
			    vitialSign = iterator.next();
                    		/**
                    		 * save object by account id
                    		 */
                		visitKey = CacheKeys.getVitalSignsKeyByVisit(vitialSign.getPatientVitalSignPK().getAccountId(),
                			super.clientDatabaseId, vitialSign.getPatientVitalSignPK()
        				.getPatientVisitId());
                		
                		 visitSignListByVisit = cacheKeysValue.get(visitKey);
                		 
                		if (visitSignListByVisit == null) {
                		    
                		    
                		    if (!cacheKeysValue.isEmpty()) {
                			status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
                			cacheKeysValue.clear();
                		    }
                		    
                		    visitSignListByVisit = new ArrayList<VitalSigns>();
                		    cacheKeysValue.put(visitKey, visitSignListByVisit);
                		}
                		
                		visitSignListByVisit.add(CacheInfoObject.getVitalSigns(vitialSign));
                		iterator.remove();
			}
	   

		    } while (maxResult != null && maxResult.equals(size));
		    
		    if (!cacheKeysValue.isEmpty()) {
			status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
			cacheKeysValue.clear();
			
		    }
	} catch (Exception e) {
	    LOGGER.error("EXCEPTION IN PATIENT VITAL SIGN : \n" + ExceptionUtils.getStackTrace(e));
	     status = messageSource.getMessage("cache.etl.exception", null, "EXCEPTION OCCUR", null);
	} finally {
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "pVitalSignStatus", "pVitalSignUpdateTime");
	    peristTransactionHistory(CacheModule.PATIENT_VITAL_SIGN ,   status);
	    patientVitialSigns = null;
	    visitKey = null;
	    visitSignListByVisit = null;
	    cacheKeysValue  = null;
	    maxResultString = null;
	    iterator  = null;
	    status = null;
	    vitialSign = null;
	    System.gc();
	}
    }
}
