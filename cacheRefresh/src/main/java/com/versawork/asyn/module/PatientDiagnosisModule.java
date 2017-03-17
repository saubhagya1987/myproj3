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
import com.versawork.asyn.dataobject.PatientInpatientDiagnosisInfo;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientDiagnosis;

/**
 * @author RAHUL BHALLA
 *
 */
public class PatientDiagnosisModule extends
	Module<PatientInpatientDiagnosisInfo, PatientDiagnosis> implements Runnable {
    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public PatientDiagnosisModule(long transactionId, int clientDatabaseId,

    CacheAccessbyJson<PatientInpatientDiagnosisInfo> cacheAccessbyJson,
	    CacheRefreshDaoImpl<PatientDiagnosis> cacheRefreshDao, MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId, cacheAccessbyJson,
		cacheRefreshDao, messageSource, clientId);
    }

    @Override
    public void run() {
	 super.run();
	List<PatientDiagnosis> patientDiagnosis = null;
	Map<String, List<PatientInpatientDiagnosisInfo>> cacheKeysValue =  new HashMap<String, List<PatientInpatientDiagnosisInfo>>();;
	String visitKey = null;
	Integer size;
	List<PatientInpatientDiagnosisInfo> patientInpatientDiagnosisInfoListByVisit = null;
	PatientDiagnosis diagnosis = null;
	String status = messageSource.getMessage("cache.etl.processed", null,"SUCCESS", null);
	String maxResultString = null;
	Iterator<PatientDiagnosis> iterator;
	try {
//	    String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "diagnosisStatus", "diagnosisStartTime");
//	    readingFlag = null;
	    maxResultString = messageSource.getMessage("module.max.result",
		    null, null, null);
	    Integer maxResult = maxResultString == null ? null : Integer
		    .valueOf(maxResultString);
	    int startPosition = 0;
//	    String query = "PatientDiagnosis.findByEtlInfoAccountSubQuery";
	    StringBuilder query = new StringBuilder();
	    query.append("SELECT p.account_id, p.patient_visit_id, p.diagnosis_code, p.diagnosis_seq_id, p.source_id, p.source_name,");
	    query.append(" p.diagnosis_name, p.status, p.date_added");
	    query.append(" FROM patient_diagnosis p INNER JOIN etl_info info ON  ");
	    query.append("info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id ");
		    query.append("  ORDER BY  p.account_id, p.patient_visit_id");
	    
	    do {
		patientDiagnosis = cacheRefreshDao.getEHRNativeQueryResults(
			query.toString(), transactionId,
			clientDatabaseId, startPosition, maxResult, clientId);

		size = patientDiagnosis.size();
		startPosition = startPosition + size;

		iterator = patientDiagnosis.iterator();

	  


	    while (iterator.hasNext()) {
		diagnosis = iterator.next();
		/**
		 * save object by account id
		 */
		visitKey = CacheKeys.getPatientDiagnosisKeyByVisit(diagnosis.getPatientDiagnosisPK().getAccountId(),
			clientDatabaseId, diagnosis.getPatientDiagnosisPK()
				.getPatientVisitId());
		patientInpatientDiagnosisInfoListByVisit = cacheKeysValue
			    .get(visitKey);
		if (patientInpatientDiagnosisInfoListByVisit == null) {
		    if (!cacheKeysValue.isEmpty()) {
			status = cacheAccessbyJson.setCache(cacheKeysValue,
				type, transactionId);
			cacheKeysValue.clear();
			
		    }
		    patientInpatientDiagnosisInfoListByVisit = new ArrayList<PatientInpatientDiagnosisInfo>();
		    cacheKeysValue.put(visitKey,
			    patientInpatientDiagnosisInfoListByVisit);
		} 
		patientInpatientDiagnosisInfoListByVisit.add(CacheInfoObject
			.getPatientInpatientDiagnosisInfo(diagnosis));
		iterator.remove();
	    }
	    }while (maxResult != null && maxResult.equals(size));
	    if (!cacheKeysValue.isEmpty()) {
				status = cacheAccessbyJson.setCache(cacheKeysValue,
					type, transactionId);
				cacheKeysValue.clear();
				
			    }

	} catch (Exception e) {
	    LOGGER.error("EXCEPTION IN PATIENT DIAGNOSIS : \n "
		    + ExceptionUtils.getStackTrace(e));
	    
	    status = messageSource.getMessage("cache.etl.exception", null,
			"EXCEPTION OCCUR", null);
	} finally {
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "diagnosisStatus", "diagnosisUpdateTime");
	    peristTransactionHistory(CacheModule.PATIENT_DIAGNOSIS ,   status);
	    patientDiagnosis = null;
	    cacheKeysValue =  null;
	    visitKey = null;
	    size= null;
	    patientInpatientDiagnosisInfoListByVisit = null;
	    diagnosis = null;
	    status = null;
	    maxResultString = null;
	    iterator= null;
	    System.gc();
	}
    }

}
