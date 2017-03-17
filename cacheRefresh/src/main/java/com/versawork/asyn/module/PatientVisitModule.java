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
import com.versawork.asyn.dataobject.NsPatientVisit;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientVerification;
import com.versawork.http.model.PatientVisit;

/**
 * @author RAHUL BHALLA
 *
 */
public class PatientVisitModule extends Module<NsPatientVisit, PatientVisit> implements Runnable {
		

	/**
	 * @param transactionId
	 * @param clientDatabaseId
	 * @param updateCacheRefresDao
	 * @param cacheAccessbyJson
	 * @param cacheRefreshDao
	 */
	public PatientVisitModule(long transactionId, int clientDatabaseId,

	CacheAccessbyJson<NsPatientVisit> cacheAccessbyJson,
	CacheRefreshDaoImpl<PatientVisit> cacheRefreshDao, MessageSource messageSource, Integer clientId) {

		super(transactionId, clientDatabaseId, cacheAccessbyJson,
				cacheRefreshDao, messageSource, clientId);
	}

	@Override
	public void run() {
	    super.run();
	    List<PatientVisit> patientVisitList = null;
	Map<String, List<NsPatientVisit>> cacheKeysValue = null;
	Map<Integer, String> accountIds = null;
	String visitKey =null;

	Integer accountId=null;;
	List<NsPatientVisit> visitList = null;

	String patientVerification;
	String maxResultString = null;
	int  size;
	Iterator<PatientVisit> iterator ;
	String status= messageSource.getMessage("cache.etl.processed", null,"SUCCESS", null);
	    
	    try {
//		 String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null); 
//		 cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "patientVisitStatus", "patientVisitStartTime");
//		        readingFlag = null;
		    maxResultString = messageSource.getMessage("module.max.result", null, null, null);
		    Integer maxResult = maxResultString == null ? null : Integer.valueOf(maxResultString);
		    int startPosition = 0;
		    StringBuilder query = new StringBuilder();
		   
		    query.append("SELECT p.client_id, p.client_database_id, p.patient_visit_id, p.account_id, p.visit_identifier, p.source_id, p.source_name, p.visit_type_id, ");
		    query.append("p.visit_date, p.encounter_start_date, p.encounter_end_date, p.first_name, p.last_name, p.sex, ");
		    query.append("p.birth_date, p.race, p.provider_name, p.race_code, p.ethnicity, p.ethnicity_code, ");
		    query.append("p.preferred_language, p.smoking_status, p.date_added, p.smoking_status_code, ");
		    query.append("p.etl_column_1, p.etl_column_2, p.source_updated");
		    query.append(" FROM patient_visit p ");
		    query.append("INNER JOIN etl_info info ON ");
			    query.append("info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id");
			    query.append(" ORDER BY  p.account_id, p.patient_visit_id");
		    do {
			patientVisitList = cacheRefreshDao.getEHRNativeQueryResults(query.toString(), transactionId,
				clientDatabaseId, startPosition, maxResult, clientId);

			size = patientVisitList.size();
			startPosition = startPosition + size;

			iterator = patientVisitList.iterator();

			  cacheKeysValue = new HashMap<String, List<NsPatientVisit>>();
			  accountIds = new HashMap<Integer, String>();


			 iterator = patientVisitList.iterator();
			PatientVisit visits = null;
			while (iterator.hasNext()) {
				visits = iterator.next();
				/**
				 * save object by account id
				 */
				accountId = visits.getPatientVisitPK().getAccountId();
				visitKey = CacheKeys.getPatientVisitKey(clientDatabaseId, accountId);
				patientVerification =    accountIds.get(accountId);
				visitList = cacheKeysValue.get(visitKey);
				if (visitList == null) {
				    
				    if (!cacheKeysValue.isEmpty()) {
					status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
					cacheKeysValue.clear();
				    }
				    
					visitList = new ArrayList<NsPatientVisit>();
					cacheKeysValue.put(visitKey, visitList);
					
				    if(patientVerification == null){
					patientVerification = cacheRefreshDao.getPatientVerification(visits .getPatientVisitPK().getAccountId());
					accountIds.clear();
					accountIds.put(accountId, patientVerification);
					
				    }
				}

				visitList.add(CacheInfoObject.getNsPatientVisit(visits, patientVerification));
				iterator.remove();
			}
		    }
			while (maxResult != null && maxResult.equals(size));
			    
			    if (!cacheKeysValue.isEmpty()) {
				status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
				cacheKeysValue.clear();
				accountIds.clear();
				
			    }
		} catch (Exception e) {
		    LOGGER.error("EXCEPTION IN PATIENT VISIT : \n"
			    + ExceptionUtils.getStackTrace(e));
		     status = messageSource.getMessage("cache.etl.exception", null,
				"EXCEPTION OCCUR", null);
		} finally {
//		    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "patientVisitStatus", "patientVisitUpdateTime");
		    peristTransactionHistory(CacheModule.PATIENT_VISIT ,   status);
		    patientVisitList = null;
		    cacheKeysValue = null;
		    accountIds = null;
		    visitKey =null;
		    accountId=null;;
		    visitList = null;
		    patientVerification= null;
		    maxResultString = null;
		    iterator = null;
		    status= null;
		    System.gc();
		}
	}
}
