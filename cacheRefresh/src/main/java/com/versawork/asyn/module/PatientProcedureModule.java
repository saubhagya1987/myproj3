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
import com.versawork.asyn.dataobject.ProcedureInfo;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientProcedure;

/**
 * @author RAHUL BHALLA
 *
 */
public class PatientProcedureModule extends Module<ProcedureInfo, PatientProcedure> implements
	Runnable {
    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public PatientProcedureModule(long transactionId, int clientDatabaseId,

    CacheAccessbyJson<ProcedureInfo> cacheAccessbyJson,
	    CacheRefreshDaoImpl<PatientProcedure> cacheRefreshDao, MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId, cacheAccessbyJson,
		cacheRefreshDao, messageSource, clientId);
    }

    @Override
    public void run() {
	 super.run();
	List<PatientProcedure> patientProcedures = null;

	Map<String, List<ProcedureInfo>> cacheKeysValue = new HashMap<String, List<ProcedureInfo>>();
	String status= messageSource.getMessage("cache.etl.processed", null,"SUCCESS", null);
	String key = null;
	List<ProcedureInfo> procedureInfoList = null;
	String maxResultString = null;
	int  size;
	Iterator<PatientProcedure> iterator ;
	PatientProcedure procedure = null;
	try {
//	    String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "procedureStatus", "procedureStartTime");
//		      readingFlag = null;
		      
	    maxResultString = messageSource.getMessage("module.max.result", null, null, null);
	    Integer maxResult = maxResultString == null ? null : Integer.valueOf(maxResultString);
	    int startPosition = 0;
	    StringBuilder query = new StringBuilder();
	    query.append("SELECT p.account_id, p.procedure_id, p.patient_visit_id, p.procedure_id2, ");
	    query.append("p.source_id, p.source_name, p.procedure_name, p.procedure_code, p.date_added, p.procedure_date FROM patient_procedure p ");
	    query.append("INNER JOIN etl_info info ON ");
	    query.append("info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id");
	    query.append(" ORDER BY  p.account_id, p.patient_visit_id");
	    do {
		patientProcedures = cacheRefreshDao.getEHRNativeQueryResults(
			query.toString(), transactionId,
				clientDatabaseId, startPosition, maxResult, clientId);

		size = patientProcedures.size();
		startPosition = startPosition + size;

		iterator = patientProcedures.iterator();

        	    while (iterator.hasNext()) {
        		/**
        		 * save object by account id
        		 */
        		procedure = iterator.next();
        		key = CacheKeys.getPatientProcedureKeyByVisit(procedure.getPatientProcedurePK().getAccountId(),
        			super.clientDatabaseId, procedure.getPatientProcedurePK()
        				.getPatientVisitId());
        		procedureInfoList = cacheKeysValue.get(key);
        		
        		if (procedureInfoList == null) {
        		    if (!cacheKeysValue.isEmpty()) {
        			status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
        			cacheKeysValue.clear();
        		    }
        		    procedureInfoList = new ArrayList<ProcedureInfo>();
        		    cacheKeysValue.put(key, procedureInfoList);
        		} 
        		procedureInfoList.add(CacheInfoObject.getProcedureInfo(procedure));
        		iterator.remove();
        	    }

	    } while (maxResult != null && maxResult.equals(size));
		    
		    if (!cacheKeysValue.isEmpty()) {
			status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
			cacheKeysValue.clear();
			
		    }
	} catch (Exception e) {
	    LOGGER.error("EXCEPTION IN PATIENT PROCEDURE : \n"
		    + ExceptionUtils.getStackTrace(e));
	     status = messageSource.getMessage("cache.etl.exception", null,
			"EXCEPTION OCCUR", null);
	} finally {
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "procedureStatus", "procedureUpdateTime");
	    peristTransactionHistory(CacheModule.PATIENT_PROCEDURE ,   status);
	    patientProcedures = null;
	    cacheKeysValue = null;
	    status = null;
	    key = null;
	    procedureInfoList = null;
	    maxResultString = null;
	    iterator= null ;
	    procedure = null;
	    System.gc();
	}
    }

}
