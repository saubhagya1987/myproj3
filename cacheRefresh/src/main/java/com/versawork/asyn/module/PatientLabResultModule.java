/**
 * 
 */
package com.versawork.asyn.module;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;

import com.google.gson.reflect.TypeToken;
import com.versawork.asyn.constant.CacheModule;
import com.versawork.asyn.dao.CacheRefreshDaoImpl;
import com.versawork.asyn.dataobject.LabResultInfo;
import com.versawork.asyn.dataobject.NsPatientLabResult;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientLab;

/**
 * 
 * @author RAHUL BHALLA
 *
 */

public class PatientLabResultModule extends Module</*NsPatientLabResult*/LabResultInfo, PatientLab>
	implements Runnable {
    private CacheAccessbyJson<NsPatientLabResult> cacheAccess;

    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public PatientLabResultModule(long transactionId, int clientDatabaseId,

    CacheAccessbyJson<LabResultInfo> cacheAccessbyJson,
	    CacheRefreshDaoImpl<PatientLab> cacheRefreshDao,
	    CacheAccessbyJson<NsPatientLabResult> cacheAccess,
	    MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId,/*cacheAccess*/ cacheAccessbyJson,
		cacheRefreshDao, messageSource, clientId);
	this.cacheAccess = cacheAccess;
    }

    @Override
    public void run() {
	 super.run();
	List<PatientLab> patientsLabResults = null;
	String status= messageSource.getMessage("cache.etl.processed", null,"SUCCESS", null);
	LabResultInfo info = null;

	String key = null;
	Map<String, List<NsPatientLabResult>> cacheKeysValue = new HashMap<String, List<NsPatientLabResult>>();
	List<NsPatientLabResult> patientLabInfoList = null;
 
	String historyKey = null;
	Map<String, List<LabResultInfo>> cachehistoryKeysValue = new HashMap<String, List<LabResultInfo>>();
	List<LabResultInfo> patientLabHistoryInfoList = null;

	String visitKey = null;
	List<LabResultInfo> patientLabVisitInfoList = null;
	Map<String, List<LabResultInfo>> cacheVisitKeysValue = new HashMap<String, List<LabResultInfo>>();
	 

	PatientLab labResult = null;
	String maxResultString;
	Integer size;
	Iterator<PatientLab> iterator;
	try {
//	    String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "plabStatus", "plabStartTime");
//	      readingFlag = null;
	     
	    maxResultString = messageSource.getMessage("module.max.result",
		    null, null, null);
	    Integer maxResult = maxResultString == null ? null : Integer
		    .valueOf(maxResultString);
	    int startPosition = 0;

	    Type listType = new TypeToken<List<NsPatientLabResult>>() {
	    }.getType();

//	    String query ="PatientLab.findByEtlInfoAccountSubQuery";
	    StringBuilder query = new StringBuilder();
	    query.append("SELECT p.client_database_id, p.account_id, p.patient_visit_id, p.lab_group_id, p.test_id, p.lab_id, p.source_id, p.source_name");
	    query.append(" , p.test_name, p.lab_code, p.lab_result, p.lab_unit, p.result_date, p.organizer_name, p.organizer_code, p.interpretation_code");
	    query.append(" , p.date_added, p.normal_range, p.abnormal_flag, p.lab_group_name, p.lab_group_date, p.lab_group_code, ");
	    query.append("p.normal_range_min, p.normal_range_max, p.absolute_range_min, p.absolute_range_max");
	    query.append(" FROM patient_lab p INNER JOIN etl_info info ON ");
	    query.append("info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id");
	    query.append( " ORDER BY  p.account_id,  p.patient_visit_id");
	    
	    do {
		patientsLabResults = cacheRefreshDao.getEHRNativeQueryResults(
			query.toString(), transactionId,
			clientDatabaseId, startPosition, maxResult, clientId);

		size = patientsLabResults.size();
		startPosition = startPosition + size;

		iterator = patientsLabResults.iterator();

		while (iterator.hasNext()) {
		    labResult = iterator.next();
		    /**
		     * save object by account id
		     */
		    key = CacheKeys.getPatientLabKey(getClientDatabaseId(),
			    labResult.getPatientLabPK().getAccountId());
		    patientLabInfoList = cacheKeysValue.get(key);
		    if (patientLabInfoList == null) {
			if (!cacheKeysValue.isEmpty()) {
			    status = /*cacheAccessbyJson*/cacheAccess.setCache(cacheKeysValue,
				    listType, transactionId);
			    cacheKeysValue.clear();

			}
			patientLabInfoList = new ArrayList<NsPatientLabResult>();
			cacheKeysValue.put(key, patientLabInfoList);

		    }

		    patientLabInfoList.add(CacheInfoObject.getNsPatientLabResult(labResult));
		    /**
		     * save object with Lab code/ test id
		     */
		    historyKey = CacheKeys.getPatientLabHistoryKey(
			    getClientDatabaseId(), labResult.getPatientLabPK()
				    .getAccountId(), labResult.getLabCode());
		    patientLabHistoryInfoList = cachehistoryKeysValue.get(historyKey);
		    if (patientLabHistoryInfoList == null) {
			if (!cachehistoryKeysValue.isEmpty()) {
			    cacheAccessbyJson.setCache(cachehistoryKeysValue,
				    type, transactionId);
			    cachehistoryKeysValue.clear();

			}
			patientLabHistoryInfoList = new ArrayList<LabResultInfo>();
			cachehistoryKeysValue.put(historyKey,
				patientLabHistoryInfoList);
		    } 
		    info = CacheInfoObject.getLabResultInfo(labResult);
		    patientLabHistoryInfoList.add(info);

		    /**
		     * save object against visit
		     */
		    visitKey = CacheKeys.getPatientLabKeyByVisit(
			    clientDatabaseId, labResult.getPatientLabPK()
				    .getAccountId(), labResult
				    .getPatientLabPK().getPatientVisitId());
		    
		    patientLabVisitInfoList = cacheVisitKeysValue.get(visitKey);
		    
		    if (patientLabVisitInfoList == null) {
			if (!cacheVisitKeysValue.isEmpty()) {
			    cacheAccessbyJson.setCache(cacheVisitKeysValue,
				    type, transactionId);
			    cacheVisitKeysValue.clear();

			}
			patientLabVisitInfoList = new ArrayList<LabResultInfo>();
			cacheVisitKeysValue.put(visitKey,
				patientLabVisitInfoList);
		    }
		    patientLabVisitInfoList.add(info);
		    iterator.remove();
		}

	    } while (maxResult != null && maxResult.equals(size));

	    if (!cacheKeysValue.isEmpty()) {
		    status = cacheAccess.setCache(cacheKeysValue, listType, transactionId);
		    cacheKeysValue.clear();
		    cacheAccessbyJson.setCache(cachehistoryKeysValue, type, transactionId);
		    cachehistoryKeysValue.clear();
		    cacheAccessbyJson.setCache(cacheVisitKeysValue, type, transactionId);
		    cacheVisitKeysValue.clear();

		}
	} catch (Exception e) {
	    LOGGER.error("EXCEPTION IN  PATIENT LAB RESULT : \n "
		    + ExceptionUtils.getStackTrace(e));
	    status = messageSource.getMessage("cache.etl.exception", null,
		    "EXCEPTION OCCUR", null);
	} finally {
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "plabStatus", "plabUpdateTime");
	    peristTransactionHistory(CacheModule.PATIENT_LAB ,   status);
	    patientsLabResults = null;
	    status = null;
//	    info = null;

	    key = null;
	    cacheKeysValue = null;
	    patientLabInfoList = null;

//	    historyKey = null;
//	    cachehistoryKeysValue = null;
//	    patientLabHistoryInfoList = null;
//
//	    visitKey = null;
//	    patientLabVisitInfoList = null;
//	    cacheVisitKeysValue = null;

	    labResult = null;
	    maxResultString = null;
	    size = null;
	    iterator = null;
	    System.gc();
	}
    }

}
