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
import com.versawork.asyn.dataobject.MedicationInfo;
import com.versawork.asyn.dataobject.NsPatientPrescription;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientPrescription;

/**
 * @author RAHUL BHALLA
 *
 */

public class PatientPrescriptionModule extends Module<MedicationInfo/*NsPatientPrescription*/, PatientPrescription> implements
	Runnable {
    /**
     * @param transactionId
     * @param clientDatabaseId
     * @param updateCacheRefresDao
     * @param cacheAccessbyJson
     * @param cacheRefreshDao
     */
    public PatientPrescriptionModule(long transactionId, int clientDatabaseId,

    CacheAccessbyJson<MedicationInfo /*NsPatientPrescription*/> cacheAccessbyJson,
    CacheRefreshDaoImpl<PatientPrescription> cacheRefreshDao,
	    CacheAccessbyJson<NsPatientPrescription> cacheAccess,
	    MessageSource messageSource, Integer clientId) {

	super(transactionId, clientDatabaseId, cacheAccessbyJson,
		cacheRefreshDao, messageSource, clientId);
	this.cacheAccess = cacheAccess;
    }

    private CacheAccessbyJson<NsPatientPrescription> cacheAccess;

    @Override
    public void run() {
	
	 super.run();
	List<PatientPrescription> patientPrescriptions = null;
	Map<String, List<NsPatientPrescription>> cacheKeysValue = new HashMap<String, List<NsPatientPrescription>>();
	List<NsPatientPrescription> patientPrescriptionInfoList = null;

	String visitKey = null;
	Map<String, List<MedicationInfo>> cacheVisitKeysValue =  new HashMap<String, List<MedicationInfo>>();;
	List<MedicationInfo> patientPrescriptionInfoByVisitList = null;
	String status= messageSource.getMessage("cache.etl.processed", null,"SUCCESS", null);
	String key = null;
	String maxResultString = null;
	int  size;
	Iterator<PatientPrescription> iterator ;
	Integer accountId;
	PatientPrescription prescription = null;
	    try {

//		String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
////		 cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId, clientDatabaseId, "pmedStatus", "pmedStartTime");
//		      readingFlag = null;
		
		    maxResultString = messageSource.getMessage("module.max.result",
			    null, null, null);
		    Integer maxResult = maxResultString == null ? null : Integer
			    .valueOf(maxResultString);
		    int startPosition = 0;

		  
		    Type listType = new TypeToken<List<NsPatientPrescription>>() {
		    }.getType();

//		    String query="PatientPrescription.findByEtlInfoAccountSubQuery";
		    StringBuilder query = new StringBuilder();
		    query.append(" SELECT  p.prescription_action_id, p.account_id, p.patient_visit_id, p.medication_id, p.source_id, p.source_name, ");
		    query.append("p.medication_name, p.frequency, p.rx_number, p.status, ");
		    query.append("p.route_of_administration, p.route_of_administration_code, p.dose_quantity, ");
		    query.append("p.dosage_description, p.start_date, p.end_date, ");
		    query.append("p.date_added, p.medication_generic_name, p.medication_brand_name, p.dose_strength, ");
		    query.append("p.medication_strength, p.unit, p.direction ");
		    query.append("FROM patient_prescription p INNER JOIN etl_info info ON ");
		    query.append("info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id");
		    query.append(" ORDER BY  p.account_id, p.patient_visit_id");
			
		    
		    do {
			patientPrescriptions = cacheRefreshDao.getEHRNativeQueryResults(
				query.toString(), transactionId,
				clientDatabaseId, startPosition, maxResult, clientId);

			size = patientPrescriptions.size();
			startPosition = startPosition + size;

			iterator = patientPrescriptions.iterator();
			while (iterator.hasNext()) {
                		prescription = iterator.next();
                
                		/**
                		 * save object by account id
                		 */
                		accountId = prescription.getPatientPrescriptionPK()
                			.getAccountId();
                		key = CacheKeys.getPatientPrescriptionKey(clientDatabaseId,
                			accountId);
                		 patientPrescriptionInfoList = cacheKeysValue.get(key);
                		if (patientPrescriptionInfoList == null) {
                		   
                		    if (!cacheKeysValue.isEmpty()) {
                			status = cacheAccess.setCache(cacheKeysValue, /*type*/listType,
                				transactionId);
                			cacheKeysValue.clear();
                			 
                		    }
                		    patientPrescriptionInfoList = new ArrayList<NsPatientPrescription>();
                		    cacheKeysValue.put(key, patientPrescriptionInfoList);
                		} 
                
                		patientPrescriptionInfoList.add(CacheInfoObject
                			.getNsPatientPrescription(prescription));
                
                		visitKey = CacheKeys.getPatientPrescriptionKeyByVisit(clientDatabaseId,
                				accountId, prescription.getPatientPrescriptionPK()
                					.getPatientVisitId());
                		patientPrescriptionInfoByVisitList = cacheVisitKeysValue
            			    .get(visitKey);
                		if (patientPrescriptionInfoByVisitList == null) {
                		   
                		    if (!cacheVisitKeysValue.isEmpty()) {
                			status = cacheAccessbyJson.setCache(
                				cacheVisitKeysValue, type, transactionId);
                			cacheVisitKeysValue.clear();
                		    }
                		    
                		    patientPrescriptionInfoByVisitList = new ArrayList<MedicationInfo>();
                		    cacheVisitKeysValue.put(visitKey,
                			    patientPrescriptionInfoByVisitList);
                		} 
                
                		patientPrescriptionInfoByVisitList.add(CacheInfoObject.getPatientMedicationInfo(prescription));
                		iterator.remove();
	    }

	 } while (maxResult != null && maxResult.equals(size));
		    
		    if (!cacheKeysValue.isEmpty()) {
			status = cacheAccess.setCache(cacheKeysValue, /*type*/listType,
				transactionId);
			status = cacheAccessbyJson.setCache(
				cacheVisitKeysValue, type, transactionId);
			cacheKeysValue.clear();
			
		    }
	} catch (Exception e) {
	    LOGGER.error("EXCEPTION IN PATIENT PRESCRIPTION : \n"  + ExceptionUtils.getStackTrace(e));
	     status = messageSource.getMessage("cache.etl.exception", null,
			"EXCEPTION OCCUR", null);
	} finally {
//	    cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId, clientDatabaseId, "pmedStatus", "pmedUpdateTime");
	    peristTransactionHistory(CacheModule.PATIENT_MEDICATION ,   status);
	    patientPrescriptions = null;
	    cacheKeysValue = null;
	    patientPrescriptionInfoList = null;
//	    visitKey = null;
//	    cacheVisitKeysValue =  null;
//	    patientPrescriptionInfoByVisitList = null;
	    status = null;
	    key = null;
	    maxResultString = null;
	    iterator = null;
	    accountId = null;
	    prescription = null;
	    System.gc();
	}
    }

}
