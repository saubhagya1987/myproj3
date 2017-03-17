/**
 * 
 */
package com.versawork.http.utils;

import com.versawork.http.constant.CacheConstants;
import com.versawork.http.constant.CacheSeperator;

/**
 * @author RAHUL BHALLA
 *
 */
public final class CacheKeys {

	private CacheKeys() {

	};

	public static String getAllergiesKey(int accountId, int clientDatabaseId) {

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_ALLERGY.getCode());
		formatCacheKey(builder, accountId, clientDatabaseId);

		return builder.toString();
	}

	public static String getPatientLabKey(int clientDatabaseId, int accountId) {

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_LAB.getCode());
		formatCacheKey(builder, accountId, clientDatabaseId);

		return builder.toString();
	}

	public static String getPatientLabHistoryKey(int clientDatabaseId, int accountId, String testId) {

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_LABHISTORY.getCode());
		formatCacheKey(builder, accountId, clientDatabaseId);
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(testId);

		return builder.toString();
	}

	public static String getPatientLabKeyByVisit(int clientDatabaseId, int accountId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_VISIT.getCode());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheConstants.PATIENT_LAB.getCode());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();
	}

	public static String getPatientImagingKey(int clientDatabaseId, int accountId) {

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_IMAGING.getCode());
		formatCacheKey(builder, accountId, clientDatabaseId);

		return builder.toString();

	}

	public static String getPatientPrescriptionKey(int clientDatabaseId, int accountId) {

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_MEDICATION.getCode());
		formatCacheKey(builder, accountId, clientDatabaseId);

		return builder.toString();
	}

	public static String getPatientProblemKey(int clientDatabaseId, int accountId) {

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_PROBLEM.getCode());
		formatCacheKey(builder, accountId, clientDatabaseId);

		return builder.toString();
	}

	public static String getPatientPrescriptionKeyByVisit(int clientDatabaseId, int accountId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_VISIT.getCode());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheConstants.PATIENT_MEDICATION.getCode());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();
	}

	public static String getVitalSignsKeyByVisit(int accountId, int clientDatabaseId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_VISIT.getCode());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheConstants.PATIENT_VITAL_SIGN.getCode());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();
	}

	/**
	 * @param accountId
	 * @param clientDatabaseId
	 * @return
	 */
	public static String getPatientProcedureKeyByVisit(int accountId, int clientDatabaseId, int patientVisitId) {

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_VISIT.getCode());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheConstants.PATIENT_PROCEDURE.getCode());
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

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_VISIT.getCode());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheConstants.PATIENT_CARE_TEAM.getCode());
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

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_VISIT.getCode());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheConstants.PATIENT_DIAGNOSIS.getCode());
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

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_VISIT.getCode());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheConstants.PATIENT_IMMUNIZATION.getCode());
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

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_VISIT.getCode());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheConstants.PATIENT_FUNCTIONAL_STATUS.getCode());
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

		StringBuilder builder = new StringBuilder(CacheConstants.FACILITY_NOTICE.getCode());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(clientDatabaseId);
		return builder.toString();
	}

	/**
	 * @param clientDatabaseId
	 * @return
	 */
	public static String getFacilityProviderKey(int clientDatabaseId) {

		StringBuilder builder = new StringBuilder(CacheConstants.FACILITY_PROVIDER.getCode());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(clientDatabaseId);
		return builder.toString();
	}

	/**
	 * @param clientDatabaseId
	 * @return
	 */
	public static String getFacilityServiceKey(int clientDatabaseId) {

		StringBuilder builder = new StringBuilder(CacheConstants.FACILITY_SERVICE.getCode());
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

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_VISIT.getCode());
		builder.append(CacheSeperator.KEYTOKEN.getToken());
		builder.append(CacheConstants.PATIENT_VISIT_INPATIENT.getCode());
		formatCacheKeyByVisit(accountId, clientDatabaseId, patientVisitId, builder);

		return builder.toString();
	}

	public static String getPatientVisitKey(Integer clientDatabaseId, Integer accountId) {

		StringBuilder builder = new StringBuilder(CacheConstants.PATIENT_VISIT.getCode());
		formatCacheKey(builder, accountId, clientDatabaseId);
		return builder.toString();
	}

}
