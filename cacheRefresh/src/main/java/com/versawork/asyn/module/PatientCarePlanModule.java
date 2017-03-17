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
import com.versawork.asyn.dataobject.CarePlanInfo;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientCarePlan;

/**
 * @author RAHUL BHALLA
 *
 */
public class PatientCarePlanModule extends
	Module<CarePlanInfo, PatientCarePlan> implements Runnable {
    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param type
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public PatientCarePlanModule(long transactionId, int clientDatabaseId,
	    CacheAccessbyJson<CarePlanInfo> cacheAccessbyJson,
	    CacheRefreshDaoImpl<PatientCarePlan> cacheRefreshDao,
	    MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId, cacheAccessbyJson,
		cacheRefreshDao, messageSource,  clientId);
    }

    @Override
    public void run() {
	 super.run();
	List<PatientCarePlan> patientCarePlans = null;
	Map<String, List<CarePlanInfo>> cacheKeysValue = new HashMap<String, List<CarePlanInfo>>();

	String visitKey = null;
	String status = messageSource.getMessage("cache.etl.processed", null, "SUCCESS",null);;
	String maxResultString = null;
	List<CarePlanInfo> carePlanInfoListByVisit = null;
	PatientCarePlan carePlan = null;
	Iterator<PatientCarePlan> iterator;

	int size;
	try {
	    maxResultString = messageSource.getMessage("module.max.result",  null, null, null);
	    Integer maxResult = maxResultString == null ? null : Integer.valueOf(maxResultString);
	    int startPosition = 0;
//	    String query = "PatientCarePlan.findByEtlInfoAccountSubQuery";
	    String query = "SELECT p.account_id, p.patient_visit_id, p.care_plan_id, p.source_id, p.source_name, p.goal, p.instructions, p.date_added  FROM patient_care_plan p INNER JOIN etl_info info ON info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id  ORDER BY  p.account_id, p.patient_visit_id";
	    do {

//		String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
//		    cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "carePlanStatus", "carePlanStartTime");
//		    readingFlag = null;
		
		patientCarePlans = cacheRefreshDao.getEHRNativeQueryResults(
			query, transactionId,
			clientDatabaseId, startPosition, maxResult,  clientId);

		size = patientCarePlans.size();
		startPosition = startPosition + size;
		iterator = patientCarePlans.iterator();

		while (iterator.hasNext()) {
		    carePlan = iterator.next();
		    /**
		     * save object by account id
		     */
		    visitKey = CacheKeys.getPatientCareTeamKeyByVisit(carePlan
			    .getPatientCarePlanPK().getAccountId(),
			    clientDatabaseId, carePlan.getPatientCarePlanPK().getPatientVisitId());
		    carePlanInfoListByVisit = cacheKeysValue.get(visitKey);
		    
		    if (carePlanInfoListByVisit == null) {
			if (!cacheKeysValue.isEmpty()) {

			    status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
			    cacheKeysValue.clear();

			}
			carePlanInfoListByVisit = new ArrayList<CarePlanInfo>();
			cacheKeysValue.put(visitKey, carePlanInfoListByVisit);

		    }
		    carePlanInfoListByVisit.add(CacheInfoObject.getCarePlanInfo(carePlan));
		    iterator.remove();
		}
	    } while (maxResult != null && maxResult.equals(size));

	    if (!cacheKeysValue.isEmpty()) {
		status = cacheAccessbyJson.setCache(cacheKeysValue, type,
			transactionId);
		cacheKeysValue.clear();

	    }
	} catch (Exception e) {
	    LOGGER.error("EXCEPTION IN PATIENT CARE PLAN : \n"
		    + ExceptionUtils.getStackTrace(e));
	    status = messageSource.getMessage("cache.etl.exception", null,
		    "EXCEPTION OCCUR", null);
	} finally {
	    peristTransactionHistory(CacheModule.PATIENT_CARE_PLAN ,   status);
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId,
//		    clientDatabaseId, "carePlanStatus", "carePlanUpdateTime");
	    patientCarePlans = null;
	    cacheKeysValue = null;
	    visitKey = null;
	    status = null;
	    maxResultString = null;
	    carePlanInfoListByVisit = null;
	    carePlan = null;
	    iterator = null;
	    System.gc();
	}
    }

}
