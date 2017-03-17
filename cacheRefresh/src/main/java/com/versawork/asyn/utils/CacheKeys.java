/**
 * 
 */
package com.versawork.asyn.utils;

import com.versawork.asyn.constant.CacheModule;
import com.versawork.asyn.constant.CacheSeperator;

/**
 * @author RAHUL BHALLA
 *
 */
public final class CacheKeys {

	private CacheKeys() {

	};

	public static String getAllergiesKey(int accountId, int clientDatabaseId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_ALLERGY.getModuleName());
		formatCacheKey(builder, accountId, clientDatabaseId);

		return builder.toString();
	}

	public static String getPatientLabKey(int clientDatabaseId, int accountId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_LAB.getModuleName());
		formatCacheKey(builder, accountId, clientDatabaseId);

		return builder.toString();
	}

	public static String getPatientLabHistoryKey(int clientDatabaseId, int accountId, String testId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_LABHISTORY.getModuleName());
		formatCacheKey(builder, accountId, clientDatabaseId);
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(testId);

		return builder.toString();
	}

	public static String getPatientLabKeyByVisit(int clientDatabaseId, int accountId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_VISIT.getModuleName());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheModule.PATIENT_LAB.getModuleName());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();
	}

	public static String getPatientImagingKey(int clientDatabaseId, int accountId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_IMAGING.getModuleName());
		formatCacheKey(builder, accountId, clientDatabaseId);

		return builder.toString();

	}

	public static String getPatientPrescriptionKey(int clientDatabaseId, int accountId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_MEDICATION.getModuleName());
		formatCacheKey(builder, accountId, clientDatabaseId);

		return builder.toString();
	}

	public static String getPatientProblemKey(int clientDatabaseId, int accountId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_PROBLEM.getModuleName());
		formatCacheKey(builder, accountId, clientDatabaseId);

		return builder.toString();
	}

	public static String getPatientPrescriptionKeyByVisit(int clientDatabaseId, int accountId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_VISIT.getModuleName());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheModule.PATIENT_MEDICATION.getModuleName());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();
	}

	public static String getVitalSignsKeyByVisit(int accountId, int clientDatabaseId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_VISIT.getModuleName());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheModule.PATIENT_VITAL_SIGN.getModuleName());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();
	}

	/**
	 * @param accountId
	 * @param clientDatabaseId
	 * @return
	 */
	public static String getPatientProcedureKeyByVisit(int accountId, int clientDatabaseId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_VISIT.getModuleName());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheModule.PATIENT_PROCEDURE.getModuleName());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();
	}

	/**
	 * @param accountId
	 * @param clientDatabaseId
	 * @param patientVisitId
	 * @return
	 */
	public static String getPatientCareTeamKeyByVisit(int accountId, int clientDatabaseId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_VISIT.getModuleName());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheModule.PATIENT_CARE_TEAM.getModuleName());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();
	}

	/**
	 * @param accountId
	 * @param clientDatabaseId
	 * @param patientVisitId
	 * @return
	 */
	public static String getPatientDiagnosisKeyByVisit(int accountId, int clientDatabaseId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_VISIT.getModuleName());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheModule.PATIENT_DIAGNOSIS.getModuleName());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();

	}

	/**
	 * @param accountId
	 * @param clientDatabaseId
	 * @param patientVisitId
	 * @return
	 */
	public static String getPatientImmunizationKeyByVisit(int accountId, int clientDatabaseId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_VISIT.getModuleName());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheModule.PATIENT_IMMUNIZATION.getModuleName());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();
	}

	/**
	 * @param accountId
	 * @param clientDatabaseId
	 * @param patientVisitId
	 * @return
	 */
	public static String getFunctionalStatusKeyByVisit(int accountId, int clientDatabaseId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_VISIT.getModuleName());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheModule.PATIENT_FUNCTIONAL_STATUS.getModuleName());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();
	}

	private static void formatCacheKeyByVisit(int accountId, int clientDatabaseId, int patientVisitId,
			StringBuilder builder) {

		formatCacheKey(builder, accountId, clientDatabaseId);
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(patientVisitId);
	}

	private static void formatCacheKey(StringBuilder builder, int accountId, int clientDatabaseId) {

		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(clientDatabaseId);
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(accountId);
	}

	/**
	 * @param clientDatabaseId1
	 * @return
	 */
	public static String getFacilityNoticeKey(int clientDatabaseId) {

		StringBuilder builder = new StringBuilder(CacheModule.FACILITY_NOTICE.getModuleName());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(clientDatabaseId);
		return builder.toString();
	}

	/**
	 * @param clientDatabaseId
	 * @return
	 */
	public static String getFacilityProviderKey(int clientDatabaseId) {

		StringBuilder builder = new StringBuilder(CacheModule.FACILITY_PROVIDER.getModuleName());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(clientDatabaseId);
		return builder.toString();
	}

	/**
	 * @param clientDatabaseId
	 * @return
	 */
	public static String getFacilityServiceKey(int clientDatabaseId) {

		StringBuilder builder = new StringBuilder(CacheModule.FACILITY_SERVICE.getModuleName());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(clientDatabaseId);
		return builder.toString();
	}

	/**
	 * @param accountId
	 * @param clientDatabaseId
	 * @param patientVisitId
	 * @return
	 */
	public static String getPatientInpatientMetadataKeyByVisit(int accountId, int clientDatabaseId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_VISIT.getModuleName());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheModule.PATIENT_VISIT_INPATIENT.getModuleName());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();
	}

	/**
	 * 
	 * @param clientDatabaseId
	 * @param accountId
	 * @return
	 */
	public static String getPatientVisitKey(Integer clientDatabaseId, Integer accountId) {

		StringBuilder builder = new StringBuilder(CacheModule.PATIENT_VISIT.getModuleName());
		formatCacheKey(builder, accountId, clientDatabaseId);
		return builder.toString();
	}
}
