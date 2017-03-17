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
import com.versawork.asyn.dataobject.PatientInpatientImmunalizationInfo;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientImmunization;

/**
 * @author RAHUL BHALLA
 *
 */
public class PatientImmunizationModule extends Module<PatientInpatientImmunalizationInfo, PatientImmunization> implements Runnable {
	/**
	 * @param transactionId
	 * @param clientDatabaseId
	 * @param updateCacheRefresDao
	 * @param cacheAccessbyJson
	 * @param cacheRefreshDao
	 */
	public PatientImmunizationModule(long transactionId, int clientDatabaseId,

	CacheAccessbyJson<PatientInpatientImmunalizationInfo> cacheAccessbyJson, CacheRefreshDaoImpl<PatientImmunization> cacheRefreshDao,
			MessageSource messageSource, Integer clientId) {

		super(transactionId, clientDatabaseId, cacheAccessbyJson, cacheRefreshDao, messageSource, clientId);
	}

	@Override
	public void run() {
	    super.run();
	List<PatientImmunization> patientImmunization = null;

	Map<String, List<PatientInpatientImmunalizationInfo>> cacheKeysValue = new HashMap<String, List<PatientInpatientImmunalizationInfo>>();
	String visitKey = "";

	List<PatientInpatientImmunalizationInfo> immunalizationInfoListByVisit = null;
	Iterator<PatientImmunization> iterator ;
	String maxResultString = null;
	Integer  size;
	String status= messageSource.getMessage("cache.etl.processed", null,"SUCCESS", null);
		
		    
	  try {
//	      String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
//	      cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "immunalizationStatus","immunalizationStartTime");
//	      readingFlag = null;
	      maxResultString = messageSource.getMessage("module.max.result", null, null, null);
	      Integer maxResult = maxResultString == null ? null : Integer.valueOf(maxResultString);
	      int startPosition = 0;
//	      String query = "PatientImmunization.findByEtlInfoAccountSubQuery";
	      StringBuilder query = new StringBuilder();
	      query.append( "SELECT p.account_id, p.immunization_id, p.patient_visit_id, p.source_id, p.source_name, ");
	      query.append( "p.immunization_name, p.immunization_code, p.status, p.route_code, p.route_name, p.date_added, ");
	      query.append( "p.immunization_date  FROM patient_immunization p INNER JOIN etl_info info ON ");
	      query.append("info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id");
	      query.append( " ORDER BY  p.account_id, p.patient_visit_id");
	      do {
		  patientImmunization = cacheRefreshDao.getEHRNativeQueryResults(query.toString(), transactionId,
			  clientDatabaseId, startPosition, maxResult, clientId);

		  size = patientImmunization.size();
		  startPosition = startPosition + size;
		  
		  iterator = patientImmunization.iterator();
		  PatientImmunization immunization = null;
		  while (iterator.hasNext()) {
				immunization = iterator.next();
				/**
				 * save object by account id
				 */
				visitKey = CacheKeys.getPatientImmunizationKeyByVisit(immunization.getPatientImmunizationPK().getAccountId(), clientDatabaseId, immunization
						.getPatientImmunizationPK().getPatientVisitId());
				immunalizationInfoListByVisit = cacheKeysValue.get(visitKey);
				if (immunalizationInfoListByVisit == null) {
				   
				    if(!cacheKeysValue.isEmpty()){
			    		status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
				    	cacheKeysValue.clear();
				    }
					immunalizationInfoListByVisit = new ArrayList<PatientInpatientImmunalizationInfo>();
					cacheKeysValue.put(visitKey, immunalizationInfoListByVisit);
				} 

				immunalizationInfoListByVisit.add(CacheInfoObject.getPatientInpatientImmunalizationInfo(immunization));
				iterator.remove();
			}

	      }	while (maxResult != null && maxResult.equals(size));
			    
			    if (!cacheKeysValue.isEmpty()) {
				status = cacheAccessbyJson.setCache(cacheKeysValue, type, transactionId);
				cacheKeysValue.clear();
				
			    }
		} catch (Exception e) {
		    LOGGER.error("EXCEPTION IN PATIENT IMMUNALIZATION : \n"
			    + ExceptionUtils.getStackTrace(e));
		     status = messageSource.getMessage("cache.etl.exception", null,
				"EXCEPTION OCCUR", null);
		} finally {
//		    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "immunalizationStatus","immunalizationUpdateTime");
		    peristTransactionHistory(CacheModule.PATIENT_IMMUNIZATION ,   status);
		    patientImmunization = null;
		    cacheKeysValue = null;
		    visitKey = null;
		    immunalizationInfoListByVisit = null;
		    iterator =null;
		    maxResultString = null;
		    size=null;
		    status = null;	
		    System.gc();
		}
	}

}
