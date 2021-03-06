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
import com.versawork.asyn.dataobject.CareTeamInfo;
import com.versawork.asyn.utils.CacheInfoObject;
import com.versawork.asyn.utils.CacheKeys;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.PatientCareTeam;

/**
 * @author RAHUL BHALLA
 *
 */
public class PatientCareTeamModule extends
		Module<CareTeamInfo, PatientCareTeam> implements Runnable {
	/**
	 * @param transactionId
	 * @param clientDatabaseId
	 * @param updateCacheRefresDao
	 * @param cacheAccessbyJson
	 * @param cacheRefreshDao
	 */
	public PatientCareTeamModule(long transactionId, int clientDatabaseId,
			CacheAccessbyJson<CareTeamInfo> cacheAccessbyJson,
			CacheRefreshDaoImpl<PatientCareTeam> cacheRefreshDao,
			MessageSource messageSource, Integer clientId) {

		super(transactionId, clientDatabaseId, cacheAccessbyJson,
				cacheRefreshDao, messageSource,  clientId);
	}

	@Override
	public void run() {
	    super.run();
		List<PatientCareTeam> patientCareTeams = null;
		Map<String, List<CareTeamInfo>> cacheKeysValue = new HashMap<String, List<CareTeamInfo>>();
		String visitKey = null;
		List<CareTeamInfo> careTeamInfoListByVisit = null;
		PatientCareTeam careTeam = null;
		String status = messageSource.getMessage("cache.etl.processed", null,"SUCCESS", null);
		Iterator<PatientCareTeam> iterator;
		String maxResultString = null;
		Integer size;

		try {
//		    String readingFlag = messageSource.getMessage("cache.etl.module.reading", null,"READING", null);
//		    cacheRefreshDao.updateEtlLoggerModuleStatus(readingFlag, transactionId,
//				clientDatabaseId, "careTeamStatus", "careTeamStartTime");
//		    readingFlag = null;
			maxResultString = messageSource.getMessage("module.max.result",
					null, null, null);
			Integer maxResult = maxResultString == null ? null : Integer
					.valueOf(maxResultString);
			int startPosition = 0;
//String query ="PatientCareTeam.findByEtlInfoAccountSubQuery";
			StringBuilder queryBuilder = new StringBuilder("SELECT p.account_id, p.patient_visit_id, p.care_member_id, p.care_member_role_id, p.source_id,");
			queryBuilder.append( " p.source_name, p.care_member_name, p.phone_number, p.address_1, p.address_2,");
			queryBuilder.append( "p.city,p.state, p.postal_code, p.country, p.date_added, p.care_member_speciality,");
			queryBuilder.append( " p.care_member_email  FROM patient_care_team p inner join etl_info info ON ");
			queryBuilder.append( "info.client_id= :clientId AND info.client_database_id =:clientDatabaseId AND info.transaction_id =:transactionId AND info.account_id = p.account_id  ");
			queryBuilder.append( "ORDER BY  p.account_id, p.patient_visit_id");
			
			
			do {
				patientCareTeams = cacheRefreshDao.getEHRNativeQueryResults(
					queryBuilder.toString(), transactionId,
						clientDatabaseId, startPosition, maxResult, clientId);

				size = patientCareTeams.size();
				startPosition = startPosition + size;

				iterator = patientCareTeams.iterator();

				while (iterator.hasNext()) {
					careTeam = iterator.next();
					/**
					 * save object by account id
					 */
					visitKey = CacheKeys
							.getPatientCareTeamKeyByVisit(careTeam
									.getPatientCareTeamPK().getAccountId(),
									super.clientDatabaseId, careTeam
											.getPatientCareTeamPK()
											.getPatientVisitId());
					careTeamInfoListByVisit = cacheKeysValue.get(visitKey);
					if (careTeamInfoListByVisit == null) {
						if (!cacheKeysValue.isEmpty()) {
							status = cacheAccessbyJson.setCache(cacheKeysValue,
									type, transactionId);
							cacheKeysValue.clear();

						}
						careTeamInfoListByVisit = new ArrayList<CareTeamInfo>();
						cacheKeysValue.put(visitKey, careTeamInfoListByVisit);
					}
					careTeamInfoListByVisit.add(CacheInfoObject
							.getCareTeamInfo(careTeam, clientDatabaseId));
					iterator.remove();
				}

			} while (maxResult != null && maxResult.equals(size));

			if (!cacheKeysValue.isEmpty()) {
				status = cacheAccessbyJson.setCache(cacheKeysValue, type,
						transactionId);
				cacheKeysValue.clear();

			}

		} catch (Exception e) {
			LOGGER.error("EXCEPTION IN PATIENT CARE TEAM : \n"
					+ ExceptionUtils.getStackTrace(e));
			status = messageSource.getMessage("cache.etl.exception", null,
					"EXCEPTION OCCUR", null);

		} finally {
//			cacheRefreshDao.updateEtlLoggerModuleStatus(status, transactionId,
//					clientDatabaseId, "careTeamStatus", "careTeamUpdateTime");
		    peristTransactionHistory(CacheModule.PATIENT_CARE_TEAM ,   status);
			patientCareTeams = null;
			cacheKeysValue = null;
			visitKey = null;
			careTeamInfoListByVisit = null;
			careTeam = null;
			status = null;
			iterator = null;
			maxResultString = null;
			System.gc();
		}
	}

}
