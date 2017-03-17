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
import com.versawork.asyn.dataobject.PatientProblemInfo;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientProblem;

/**
 * @author Rahul Bhalla
 *
 */
public class PatientProblemModule extends
	Module<PatientProblemInfo, PatientProblem> implements Runnable {
    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public PatientProblemModule(long transactionId, int clientDatabaseId,

    CacheAccessbyJson<PatientProblemInfo> cacheAccessbyJson, CacheRefreshDaoImpl<PatientProblem> cacheRefreshDao,
	    MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId, cacheAccessbyJson, cacheRefreshDao, messageSource,  clientId);
    }

    @Override
    public void run() {
	 super.run();
	List<PatientProblem> patientProblems = null;
	String key = null;
	Map<String, List<PatientProblemInfo>> cacheKeysValue = new HashMap<String, List<PatientProblemInfo>>();
	List<PatientProblemInfo> patientProblemInfoList = null;
	String maxResultString = null;
	int size;
	Iterator<PatientProblem> iterator;
	String status= messageSource.getMessage("cache.etl.processed", null,"SUCCESS", null);

	try {
//	    String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "probStatus", "probStartTime");
//		      readingFlag = null;
//		
	    maxResultString = messageSource.getMessage("module.max.result",  null, null, null);
	    Integer maxResult = maxResultString == null ? null : Integer.valueOf(maxResultString);
	    int startPosition = 0;
	    PatientProblem problems = null;
	    StringBuilder query = new StringBuilder();
	    query.append("SELECT p.account_id, p.problem_id, p.source_id, p.source_name, p.problem_name, ");
	    query.append("p.problem_code, p.status, p.status_code, p.date_added FROM patient_problem p");
	    query.append(" INNER JOIN etl_info info ON ");
	    query.append("info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id");
	    query.append(" ORDER BY  p.account_id");
	    
	    do {
		patientProblems = cacheRefreshDao.getEHRNativeQueryResults(query.toString(), transactionId,
			clientDatabaseId, startPosition, maxResult, clientId);

		size = patientProblems.size();
		startPosition = startPosition + size;

		iterator = patientProblems.iterator();
 
		iterator = patientProblems.iterator();
		
		while (iterator.hasNext()) {
		    problems = iterator.next();
		    /**
		     * save object by account id
		     */
		    key = CacheKeys.getPatientProblemKey(super.clientDatabaseId,
			    problems.getPatientProblemPK().getAccountId());
		    patientProblemInfoList = cacheKeysValue.get(key);
		    if (patientProblemInfoList == null) {
			if (!cacheKeysValue.isEmpty()) {
			    status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
			    cacheKeysValue.clear();
			   
			}
			patientProblemInfoList = new ArrayList<PatientProblemInfo>();
			cacheKeysValue.put(key, patientProblemInfoList);
		    }
		    patientProblemInfoList.add(CacheInfoObject.getPatientProblemInfo(problems));
		    iterator.remove();
		}
	    } while (maxResult != null && maxResult.equals(size));

	    if (!cacheKeysValue.isEmpty()) {
		status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
		cacheKeysValue.clear();

	    }

	} catch (Exception e) {
	    LOGGER.error("EXCEPTION IN PATIENT PROBLEM : \n"
		    + ExceptionUtils.getStackTrace(e));
	    status = messageSource.getMessage("cache.etl.exception", null,
		    "EXCEPTION OCCUR", null);
	} finally {
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "probStatus", "probUpdateTime");
	    peristTransactionHistory(CacheModule.PATIENT_PROBLEM ,   status);
	    patientProblems = null;
	    key = null;
	    cacheKeysValue = null;
	    patientProblemInfoList = null;
	    maxResultString = null;
	    iterator=null;
	    status = null;
	    System.gc();
	}

    }

}
