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
import com.versawork.asyn.dataobject.PatientInpatientMetadataInfo;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientVisitInpatient;

/**
 * @author RAHUL BHALLA
 *
 */
public class InPatientMetaDataModule extends
	Module<PatientInpatientMetadataInfo, PatientVisitInpatient> implements Runnable {
    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public InPatientMetaDataModule(long transactionId, int clientDatabaseId,

    CacheAccessbyJson<PatientInpatientMetadataInfo> cacheAccessbyJson,
	    CacheRefreshDaoImpl<PatientVisitInpatient> cacheRefreshDao, MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId, cacheAccessbyJson,
		cacheRefreshDao, messageSource, clientId);
    }

    @Override
    public void run() {
	 super.run();
	List<PatientVisitInpatient> patientVisitInpatients = null;
	Map<String, List<PatientInpatientMetadataInfo>> cacheKeysValue = new HashMap<String, List<PatientInpatientMetadataInfo>>();
	String visitKey = null;
	String status = messageSource.getMessage("cache.etl.processed", null, "SUCCESS",null);
	String startIndexString = null;
	int  size;
	Iterator<PatientVisitInpatient> iterator;
	List<PatientInpatientMetadataInfo> patientInpatientMetadataInfoListByVisit = null;
	PatientVisitInpatient inpatient = null;
	
	   try {

//		String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
//		cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "inpatientStatus","inpatientStartTime");
//		      readingFlag = null;
//		
		    startIndexString = messageSource.getMessage("module.max.result",
			    null, null, null);
		    Integer maxResult = startIndexString == null ? null : Integer
			    .valueOf(startIndexString);
		    int startPosition = 0;
//		    String query = "PatientVisitInpatient.findByEtlInfoAccountSubQuery";
		    StringBuilder query = new StringBuilder();
		    query.append("SELECT p.account_id, p.patient_visit_id, p.source_id, p.source_name, p.admit_location, ");
		    query.append("p.discharge_location, p.reason_for_hospitalization, p.discharge_instruction,p.date_added FROM patient_visit_inpatient p ");
		    query.append("INNER JOIN etl_info info ON ");
		    query.append("info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id");
		    	query.append ("ORDER BY  p.account_id, p.patient_visit_id");


		    do {
			patientVisitInpatients = cacheRefreshDao.getEHRNativeQueryResults(
				query.toString(), transactionId,
				clientDatabaseId, startPosition, maxResult, clientId);

			size = patientVisitInpatients.size();
			startPosition = startPosition + size;

			iterator = patientVisitInpatients.iterator();

			while (iterator.hasNext()) {
				inpatient = iterator.next();
				/**
				 * save object by account id
				 */
				visitKey = CacheKeys.getPatientInpatientMetadataKeyByVisit(
					inpatient.getPatientVisitInpatientPK()
					.getAccountId(), clientDatabaseId, inpatient
						.getPatientVisitInpatientPK()
						.getPatientVisitId());
				
				patientInpatientMetadataInfoListByVisit = cacheKeysValue
					    .get(visitKey);
				if (patientInpatientMetadataInfoListByVisit == null) {
				    if (!cacheKeysValue.isEmpty()) {
					status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
					cacheKeysValue.clear();
					
				    }
				    patientInpatientMetadataInfoListByVisit = new ArrayList<PatientInpatientMetadataInfo>();
				    cacheKeysValue.put(visitKey, patientInpatientMetadataInfoListByVisit);
				}

				patientInpatientMetadataInfoListByVisit.add(CacheInfoObject
					.getPatientInpatientMetadataInfo(inpatient));
				iterator.remove();
			    }
	    
		    } while (maxResult != null && maxResult.equals(size));
		    
		    if (!cacheKeysValue.isEmpty()) {
			 cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
			cacheKeysValue.clear();
			
		    }
	
	} catch (Exception e) {
	    LOGGER.error("EXCEPTION iN PATIENT VISIT INPATIENT METADATA :\n"
		    + ExceptionUtils.getStackTrace(e));
	    status = messageSource.getMessage("cache.etl.exception", null,
			"EXCEPTION OCCUR", null);
	} finally {
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "inpatientStatus","inpatientUpdateTime");
	    peristTransactionHistory(CacheModule.PATIENT_VISIT_INPATIENT ,   status);
	    patientVisitInpatients = null;
	    cacheKeysValue = null;
	    visitKey = null;
	    status = null;
	    startIndexString = null;
	    iterator= null;
	    patientInpatientMetadataInfoListByVisit = null;
	    inpatient = null;
	    System.gc();
	}

    }
}
