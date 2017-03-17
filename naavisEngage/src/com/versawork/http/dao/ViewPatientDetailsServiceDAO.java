package com.versawork.http.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.ActivityLog;
import com.versawork.http.model.FacilityProvider;
import com.versawork.http.model.PatientAllergy;
import com.versawork.http.model.PatientCarePlan;
import com.versawork.http.model.PatientLab;
import com.versawork.http.model.PatientPrescription;
import com.versawork.http.model.PatientProblem;
import com.versawork.http.model.PatientProcedure;
import com.versawork.http.model.PatientVisit;
import com.versawork.http.model.PatientVisitInpatient;
import com.versawork.http.model.VdtCoreMeasure;
import com.versawork.http.model.VdtCoreMeasureScorecard;
import com.versawork.http.utils.DateUtils;

/**
 * @author Sohaib
 * 
 */

@Repository
public class ViewPatientDetailsServiceDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewPatientDetailsServiceDAO.class);
	private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	@PersistenceContext(unitName = "versawork_DS")
	private EntityManager em;

	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	public PatientVisit getInfoVisitIdUnitNumber(Integer accountId, Integer visitId) throws BusinessException,
			SystemException {
		PatientVisit PatientVisit = null;
		if (isDebugEnabled)
			LOGGER.debug("Getting accountId " + accountId + "Visit Id : " + visitId);
		try {
			TypedQuery<PatientVisit> query = ehrEntityManager.createNamedQuery("PatientVisit.findByVisitIdAccountId",
					PatientVisit.class);

			query.setParameter("accountId", accountId);
			query.setParameter("patientVisitId", visitId);

			PatientVisit = query.getSingleResult();
		} catch (NoResultException noResultException) {
			LOGGER.error("No Visit Id found");
			throw new BusinessException("invalid.visit.id");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return PatientVisit;
	}

	public PatientVisit getVisitIdUnitNumberInfo(Integer accountId, Integer visitId) throws BusinessException,
			SystemException {
		PatientVisit patientVisit = null;
		if (isDebugEnabled)
			LOGGER.debug("Getting accountId " + accountId + "   Visit Id : " + visitId);
		try {
			TypedQuery<PatientVisit> query = ehrEntityManager.createNamedQuery("PatientVisit.findByVisitIdAccountId",
					PatientVisit.class);

			query.setParameter("accountId", accountId);
			query.setParameter("patientVisitId", visitId);

			patientVisit = query.getSingleResult();
		} catch (NoResultException noResultException) {
			LOGGER.error("No Visit Id found");
			throw new BusinessException("invalid.visit.id");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientVisit;
	}

	public PatientVisit getInfoMuIdUnitNumber(Integer accountId, Integer muDataId) throws BusinessException,
			SystemException {
		PatientVisit PatientVisit = null;
		if (isDebugEnabled)
			LOGGER.debug("Getting unitNumber Id " + accountId + "muDataId : " + muDataId);
		try {
			TypedQuery<PatientVisit> query = ehrEntityManager.createNamedQuery("PatientVisit.findByVisitIdAccountId",
					PatientVisit.class);

			query.setParameter("accountId", accountId);
			query.setParameter("patientVisitId", muDataId);

			PatientVisit = query.getSingleResult();
		} catch (NoResultException noResultException) {
			LOGGER.error("No appointmentId found");
			throw new BusinessException("invalid.appoint.Id");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return PatientVisit;
	}

	/*
	 * public List<PatientMuHospitalProviderRel>
	 * getproviderDetailsByMuId(Integer muDataId) throws BusinessException,
	 * SystemException { List<PatientMuHospitalProviderRel>
	 * patientMuHospitalProviderRel = null; try {
	 * TypedQuery<PatientMuHospitalProviderRel> query =
	 * (TypedQuery<PatientMuHospitalProviderRel>)
	 * em.createNamedQuery("PatientMuHospitalProviderRel.findByPatientVisitId"
	 * ,PatientMuHospitalProviderRel.class);
	 * query.setParameter("patientVisitId",muDataId);
	 * patientMuHospitalProviderRel = query.getResultList(); } catch
	 * (NoResultException exp) { throw new BusinessException("no.mu.id.found");
	 * } catch (Exception exception) { throw new SystemException(exception); }
	 * return patientMuHospitalProviderRel; }
	 */

	public List<PatientAllergy> getAllergieDetailsByMuId(Integer muDataId) throws BusinessException, SystemException {
		List<PatientAllergy> PatientAllergy = null;
		try {
			TypedQuery<PatientAllergy> query = (TypedQuery<PatientAllergy>) ehrEntityManager.createNamedQuery(
					"PatientAllergy.findByPatientVisitId", PatientAllergy.class);
			query.setParameter("patientVisitId", muDataId);
			PatientAllergy = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.mu.id.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return PatientAllergy;
	}

	public List<PatientPrescription> getMedicationsByMuId(Integer muDataId) throws BusinessException, SystemException {
		List<PatientPrescription> patientMedication = null;
		try {
			TypedQuery<PatientPrescription> query = (TypedQuery<PatientPrescription>) ehrEntityManager
					.createNamedQuery("PatientPrescription.findByPatientVisitId", PatientPrescription.class);
			query.setParameter("patientVisitId", muDataId);
			patientMedication = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.mu.id.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientMedication;
	}

	public List<PatientProblem> getProblemsByMuId(Integer muDataId) throws BusinessException, SystemException {
		List<PatientProblem> patientProblem = null;
		try {
			TypedQuery<PatientProblem> query = (TypedQuery<PatientProblem>) ehrEntityManager.createNamedQuery(
					"PatientProblem.findByPatientVisitId", PatientProblem.class);
			query.setParameter("patientVisitId", muDataId);
			patientProblem = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.mu.id.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientProblem;
	}

	public List<PatientProcedure> getProceduresByMuId(Integer muDataId) throws BusinessException, SystemException {
		List<PatientProcedure> patientProcedure = null;
		try {
			TypedQuery<PatientProcedure> query = (TypedQuery<PatientProcedure>) ehrEntityManager.createNamedQuery(
					"PatientProcedure.findByPatientVisitId", PatientProcedure.class);
			query.setParameter("patientVisitId", muDataId);
			patientProcedure = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.mu.id.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientProcedure;
	}

	public List<PatientLab> getLabResultsByMuId(Integer muDataId) throws BusinessException, SystemException {
		List<PatientLab> patientLabResult = null;
		try {
			TypedQuery<PatientLab> query = (TypedQuery<PatientLab>) ehrEntityManager.createNamedQuery(
					"PatientLab.findByPatientVisitId", PatientLab.class);
			query.setParameter("patientVisitId", muDataId);
			patientLabResult = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.mu.id.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientLabResult;
	}

	public List<PatientCarePlan> getCarePlanByMuId(Integer muDataId) throws BusinessException, SystemException {
		List<PatientCarePlan> patientCarePlan = null;
		try {
			TypedQuery<PatientCarePlan> query = (TypedQuery<PatientCarePlan>) ehrEntityManager.createNamedQuery(
					"PatientCarePlan.findByPatientVisitId", PatientCarePlan.class);
			query.setParameter("patientVisitId", muDataId);
			patientCarePlan = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.mu.id.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientCarePlan;
	}

	public PatientVisitInpatient getPatientInpatientDataByMuId(Integer muDataId) throws BusinessException,
			SystemException {
		PatientVisitInpatient patientInpatientMetadata = null;
		try {
			TypedQuery<PatientVisitInpatient> query = (TypedQuery<PatientVisitInpatient>) ehrEntityManager
					.createNamedQuery("PatientVisit.findByPatientVisitId", PatientVisitInpatient.class);
			query.setParameter("patientVisitId", muDataId);
			patientInpatientMetadata = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.mu.id.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientInpatientMetadata;
	}

	/*
	 * public List<PatientDiagnosis> getDiagnosisByInpatientMetaDataId(Integer
	 * inPatientId) throws BusinessException, SystemException {
	 * List<PatientDiagnosis> patientInpatientDiagnosis = null; try {
	 * TypedQuery<PatientDiagnosis> query = (TypedQuery<PatientDiagnosis>)
	 * em.createNamedQuery
	 * ("PatientDiagnosis.findByPatientInpatientMetadataId",PatientDiagnosis
	 * .class); query.setParameter("patientInpatientMetadataId",inPatientId);
	 * patientInpatientDiagnosis = query.getResultList(); } catch
	 * (NoResultException exp) { throw new
	 * BusinessException("no.inpatient.id.found"); } catch (Exception exception)
	 * { throw new SystemException(exception); } return
	 * patientInpatientDiagnosis; }
	 */

	/*
	 * public List<PatientFunctionalStatus>
	 * getFuncStatusByInpatientMetaDataId(Integer inPatientId) throws
	 * BusinessException, SystemException { List<PatientFunctionalStatus>
	 * patientInpatientFunctionalStatus = null; try {
	 * TypedQuery<PatientFunctionalStatus> query =
	 * (TypedQuery<PatientFunctionalStatus>) em.createNamedQuery(
	 * "PatientFunctionalStatus.findByPatientInpatientMetadataId"
	 * ,PatientFunctionalStatus.class);
	 * query.setParameter("patientInpatientMetadataId",inPatientId);
	 * patientInpatientFunctionalStatus = query.getResultList(); } catch
	 * (NoResultException exp) { throw new
	 * BusinessException("no.inpatient.id.found"); } catch (Exception exception)
	 * { throw new SystemException(exception); } return
	 * patientInpatientFunctionalStatus; }
	 */

	/*
	 * public List<PatientImmunization>
	 * getImmunalizationByInpatientMetaDataId(Integer inPatientId) throws
	 * BusinessException, SystemException { List<PatientImmunization>
	 * patientInpatientImmunization = null; try {
	 * TypedQuery<PatientImmunization> query = (TypedQuery<PatientImmunization>)
	 * em
	 * .createNamedQuery("PatientImmunization.findByPatientInpatientMetadataId"
	 * ,PatientImmunization.class);
	 * query.setParameter("patientInpatientMetadataId",inPatientId);
	 * patientInpatientImmunization = query.getResultList(); } catch
	 * (NoResultException exp) { throw new
	 * BusinessException("no.inpatient.id.found"); } catch (Exception exception)
	 * { throw new SystemException(exception); } return
	 * patientInpatientImmunization; }
	 */

	public List<ActivityLog> getTransmitEHRActivityLogs(Integer accountId, String activity, Date fromDate,
			Date todaysDate) throws BusinessException, SystemException {

		List<ActivityLog> activityLog = null;

		LOGGER.debug("Getting Account Details By accountId Id : " + accountId + "activity Name : " + activity);
		try {
			TypedQuery<ActivityLog> query = em.createNamedQuery("ActivityLog.findByAccountIdActivityNameFrmTo",
					ActivityLog.class);
			query.setParameter("auditAccountId", accountId);
			query.setParameter("activity", activity);
			query.setParameter("fromDate", fromDate, TemporalType.DATE);
			query.setParameter("todaysDate", todaysDate, TemporalType.DATE);
			// query.setMaxResults(5);

			activityLog = query.getResultList();
		} catch (NoResultException noResultException) {
			LOGGER.error("Invalid Account Id or activity Name ");
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return activityLog;
	}

	public List<ActivityLog> getActivityLogAccessHistory(Integer accountId, Date fromDate, Date todaysDate)
			throws BusinessException, SystemException {

		List<ActivityLog> activityLog = null;

		LOGGER.debug("Getting Account Details By accountId Id : " + accountId);
		try {
			TypedQuery<ActivityLog> query = em.createNamedQuery("ActivityLog.findByAccountIdActivityList",
					ActivityLog.class);
			query.setParameter("auditAccountId", accountId);
			query.setParameter("fromDate", fromDate, TemporalType.DATE);
			query.setParameter("todaysDate", todaysDate, TemporalType.DATE);
			// query.setMaxResults(5);

			activityLog = query.getResultList();
		} catch (NoResultException noResultException) {
			LOGGER.error("Invalid Account Id ");
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return activityLog;
	}

	public List<VdtCoreMeasureScorecard> getVdtReporting(NsRequest nsRequest) throws SystemException {

		LOGGER.debug("Getting VDT Report");
		try {

			Query qm = em.createNativeQuery("select * from dbo.udf_core_measure_scorecard_vdt(?,?,?)",
					VdtCoreMeasureScorecard.class);
			qm.setParameter(1, nsRequest.getVdtData().getReportTypeId());
			qm.setParameter(2, DateUtils.getDate(nsRequest.getVdtData().getReportingPeriodBeginDate(), "MM/dd/yyyy"));
			qm.setParameter(3, DateUtils.getDate(nsRequest.getVdtData().getReportingPeriodEndDate(), "MM/dd/yyyy"));
			@SuppressWarnings("unchecked")
			List<VdtCoreMeasureScorecard> items = qm.getResultList();
			// LOGGER.info("items VDTReporting MeasureType: " +
			// items.get(0).getMeasureType());
			return items;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	public List<VdtCoreMeasure> getVdtReportAccessHistory(NsRequest nsRequest) throws SystemException {
		LOGGER.debug("Getting VDT AccessHistory");
		try {
			// Query qm =
			// em.createNativeQuery("select * from udf_core_measure_detail_vdt(?, ?, ?, ?) order by ID asc",VdtCoreMeasure.class);
			Query qm = em
					.createNativeQuery(
							"select DISTINCT patient_id, first_name, last_name, date_of_birth from udf_core_measure_detail_vdt2(?, ?, ?, ?) order by first_name, last_name",
							VdtCoreMeasure.class);
			qm.setParameter(1, nsRequest.getVdtData().getReportTypeId());
			qm.setParameter(2, nsRequest.getVdtData().getMeasureSubNumber());

			qm.setParameter(3, DateUtils.getDate(nsRequest.getVdtData().getReportingPeriodBeginDate(), "MM/dd/yyyy"));
			qm.setParameter(4, DateUtils.getDate(nsRequest.getVdtData().getReportingPeriodEndDate(), "MM/dd/yyyy"));
			@SuppressWarnings("unchecked")
			List<VdtCoreMeasure> items = qm.getResultList();
			// LOGGER.info("items in AccessHistory first name: " +
			// items.get(0).getFirstName());
			return items;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new SystemException(exception);
		}
	}

	/*
	 * @SuppressWarnings("deprecation") public CoreMeasurePatientDataVdt2
	 * getVdtHitsByPatientId(Integer accountId, String encounterDate) throws
	 * SystemException { try { TypedQuery<CoreMeasurePatientDataVdt2> query =
	 * em.createNamedQuery("CoreMeasurePatientDataVdt2.findByPatientId",
	 * CoreMeasurePatientDataVdt2.class); query.setParameter("accountId",
	 * accountId); query.setParameter("encounterDate", new Date(encounterDate));
	 * CoreMeasurePatientDataVdt2 vdtHitsRecord = query.getSingleResult();
	 * return vdtHitsRecord; } catch (NoResultException exp) { return null; }
	 * catch (Exception exception) { throw new SystemException(exception); } }
	 * 
	 * public void updateCMPDV2(CoreMeasurePatientDataVdt2 cMPDV2) throws
	 * SystemException { try { em.merge(cMPDV2); } catch (Exception exception) {
	 * throw new SystemException(exception); }
	 * 
	 * }
	 * 
	 * public void saveCMPDV2(CoreMeasurePatientDataVdt2 cMPDV2) throws
	 * SystemException { try { em.persist(cMPDV2); } catch (Exception exception)
	 * { throw new SystemException(exception); } }
	 */
	public List<FacilityProvider> getFacilityProviderListByClientDbIdandId(Integer clientDatabaseId, int clientId)
			throws BusinessException, SystemException {
		List<FacilityProvider> facilityProviderList = null;
		try {
			TypedQuery<FacilityProvider> query = (TypedQuery<FacilityProvider>) em.createNamedQuery(
					"FacilityProvider.findByClientDatabaseIdandClientId", FacilityProvider.class);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			query.setParameter("clientId", clientId);
			facilityProviderList = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.mu.id.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return facilityProviderList;
	}

	/*
	 * public List<PatientVitalSign>
	 * getPatientVitalSignListByAccountIdVisitIdentifier( Integer
	 * accountId,String visitIdentifier) throws BusinessException,
	 * SystemException { List<PatientVitalSign> facilityProviderList = null; try
	 * { TypedQuery<PatientVitalSign> query = (TypedQuery<PatientVitalSign>)
	 * em.createNamedQuery
	 * ("PatientVitalSign.findByVisitIdentifierAccountId",PatientVitalSign
	 * .class); query.setParameter("accountId",accountId);
	 * query.setParameter("visitIdentifier",visitIdentifier);
	 * facilityProviderList = query.getResultList(); } catch (NoResultException
	 * exp) { throw new BusinessException("no.mu.id.found"); } catch (Exception
	 * exception) { throw new SystemException(exception); } return
	 * facilityProviderList; }
	 */

	/*
	 * public List<PatientAllergy> getAllergieDetailsByAccountId(int accountId)
	 * throws BusinessException, SystemException { List<PatientAllergy>
	 * PatientAllergy = null; try { TypedQuery<PatientAllergy> query =
	 * (TypedQuery<PatientAllergy>)
	 * em.createNamedQuery("PatientAllergy.findByAccountId"
	 * ,PatientAllergy.class); query.setParameter("accountId",accountId);
	 * PatientAllergy = query.getResultList(); } catch (NoResultException exp) {
	 * throw new BusinessException("no.mu.id.found"); } catch (Exception
	 * exception) { throw new SystemException(exception); } return
	 * PatientAllergy; }
	 */

	/*
	 * public List<PatientMedication>
	 * getMedicationsByVisitIdentifierAndAccountId( int accountId,String
	 * visitIdentifier) throws BusinessException, SystemException {
	 * List<PatientMedication> patientMedication = null; try {
	 * TypedQuery<PatientMedication> query = (TypedQuery<PatientMedication>)
	 * em.createNamedQuery
	 * ("PatientMedication.findByVisitIdentifierAccountId",PatientMedication
	 * .class); query.setParameter("accountId",accountId);
	 * query.setParameter("visitIdentifier",visitIdentifier); patientMedication
	 * = query.getResultList(); } catch (NoResultException exp) { throw new
	 * BusinessException("no.mu.id.found"); } catch (Exception exception) {
	 * throw new SystemException(exception); } return patientMedication; }
	 */

	/*
	 * public List<PatientProblem> getPatientProblemListByAccountId(int
	 * accountId) throws BusinessException, SystemException {
	 * List<PatientProblem> patientProblem = null; try {
	 * TypedQuery<PatientProblem> query = (TypedQuery<PatientProblem>)
	 * em.createNamedQuery
	 * ("PatientProblem.findByAccountId",PatientProblem.class);
	 * query.setParameter("accountId",accountId); patientProblem =
	 * query.getResultList(); } catch (NoResultException exp) { throw new
	 * BusinessException("no.mu.id.found"); } catch (Exception exception) {
	 * throw new SystemException(exception); } return patientProblem; }
	 */

	/*
	 * public List<PatientProcedure>
	 * getPatientProcedureListByAccountIdAndVisitIdentifier( int accountId,
	 * String visitIdentifier) throws BusinessException, SystemException {
	 * List<PatientProcedure> patientProcedure = null; try {
	 * TypedQuery<PatientProcedure> query = (TypedQuery<PatientProcedure>)
	 * em.createNamedQuery
	 * ("PatientProcedure.findByVisitIdentifierAccountId",PatientProcedure
	 * .class); query.setParameter("accountId",accountId);
	 * query.setParameter("visitIdentifier",visitIdentifier); patientProcedure =
	 * query.getResultList(); } catch (NoResultException exp) { throw new
	 * BusinessException("no.mu.id.found"); } catch (Exception exception) {
	 * throw new SystemException(exception); } return patientProcedure; }
	 */

	/*
	 * public List<PatientLab> getPatientLabResultByAccountIdandVisitIdentifier(
	 * int accountId, String visitIdentifier) throws BusinessException,
	 * SystemException { List<PatientLab> patientProcedure = null; try {
	 * TypedQuery<PatientLab> query = (TypedQuery<PatientLab>)
	 * em.createNamedQuery
	 * ("PatientLab.findByVisitIdentifierAccountId",PatientLab.class);
	 * query.setParameter("accountId",accountId);
	 * query.setParameter("visitIdentifier",visitIdentifier); patientProcedure =
	 * query.getResultList(); } catch (NoResultException exp) { throw new
	 * BusinessException("no.mu.id.found"); } catch (Exception exception) {
	 * throw new SystemException(exception); } return patientProcedure; }
	 */

	/*
	 * public List<PatientCarePlan>
	 * getPatientCarePlanByAccountIdandVisitIdentifier( int accountId, String
	 * visitIdentifier) throws BusinessException, SystemException {
	 * List<PatientCarePlan> patientProcedure = null; try {
	 * TypedQuery<PatientCarePlan> query = (TypedQuery<PatientCarePlan>)
	 * em.createNamedQuery
	 * ("PatientCarePlan.findByVisitIdentifierAccountId",PatientCarePlan.class);
	 * query.setParameter("accountId",accountId);
	 * query.setParameter("visitIdentifier",visitIdentifier); patientProcedure =
	 * query.getResultList(); } catch (NoResultException exp) { throw new
	 * BusinessException("no.mu.id.found"); } catch (Exception exception) {
	 * throw new SystemException(exception); } return patientProcedure; }
	 */

	/*
	 * public List<PatientDiagnosis>
	 * getPatientDiagnosisByAccountIdandPatientIdentifier( int accountId, String
	 * visitIdentifier) throws BusinessException, SystemException {
	 * List<PatientDiagnosis> patientProcedure = null; try {
	 * TypedQuery<PatientDiagnosis> query = (TypedQuery<PatientDiagnosis>)
	 * em.createNamedQuery
	 * ("PatientDiagnosis.findByVisitIdentifierAccountId",PatientDiagnosis
	 * .class); query.setParameter("accountId",accountId);
	 * query.setParameter("visitIdentifier",visitIdentifier); patientProcedure =
	 * query.getResultList(); } catch (NoResultException exp) { throw new
	 * BusinessException("no.mu.id.found"); } catch (Exception exception) {
	 * throw new SystemException(exception); } return patientProcedure; }
	 */
	/*
	 * public List<PatientFunctionalStatus>
	 * getPatientInpatientFunctionalStatusByAccountIdandPatientIdentifier( int
	 * accountId, String visitIdentifier) throws BusinessException,
	 * SystemException { List<PatientFunctionalStatus> patientProcedure = null;
	 * try { TypedQuery<PatientFunctionalStatus> query =
	 * (TypedQuery<PatientFunctionalStatus>)
	 * em.createNamedQuery("PatientFunctionalStatus.findByVisitIdentifierAccoutnId"
	 * ,PatientFunctionalStatus.class);
	 * query.setParameter("accountId",accountId);
	 * query.setParameter("visitIdentifier",visitIdentifier); patientProcedure =
	 * query.getResultList(); } catch (NoResultException exp) { throw new
	 * BusinessException("no.mu.id.found"); } catch (Exception exception) {
	 * throw new SystemException(exception); } return patientProcedure; }
	 */

	/*
	 * public PatientVisitInpatient
	 * getInpatientVisitListByAccountIdandVisitIdentifier( int accountId, String
	 * visitIdentifier) throws BusinessException, SystemException {
	 * PatientVisitInpatient patientVisitInpatient = null; try {
	 * TypedQuery<PatientVisitInpatient> query =
	 * (TypedQuery<PatientVisitInpatient>)
	 * em.createNamedQuery("PatientVisitInpatient.findByVisitIdentifierAccountId"
	 * ,PatientVisitInpatient.class); query.setParameter("accountId",accountId);
	 * query.setParameter("visitIdentifier",visitIdentifier);
	 * patientVisitInpatient = query.getSingleResult(); } catch
	 * (NoResultException exp) { throw new BusinessException("no.mu.id.found");
	 * } catch (Exception exception) { throw new SystemException(exception); }
	 * 
	 * return patientVisitInpatient; }
	 */
	/*
	 * public List<PatientImmunization>
	 * getPatientInpatientImmunizationByAccountIdandPatientIdentifier( int
	 * accountId, String visitIdentifier) throws BusinessException,
	 * SystemException { List<PatientImmunization> patientProcedure = null; try
	 * { TypedQuery<PatientImmunization> query =
	 * (TypedQuery<PatientImmunization>)
	 * em.createNamedQuery("PatientImmunization.findByVisitIdentifierAccountId"
	 * ,PatientImmunization.class); query.setParameter("accountId",accountId);
	 * query.setParameter("visitIdentifier",visitIdentifier); patientProcedure =
	 * query.getResultList(); } catch (NoResultException exp) { throw new
	 * BusinessException("no.mu.id.found"); } catch (Exception exception) {
	 * throw new SystemException(exception); } return patientProcedure; }
	 */

	/*
	 * public List<HealthcareProvider> getproviderDetailsByMuId(Integer
	 * muDataId) throws BusinessException, SystemException {
	 * List<HealthcareProvider> healthcareProvider = null; try {
	 * TypedQuery<HealthcareProvider> query = (TypedQuery<HealthcareProvider>)
	 * em.createNamedQuery("HealthcareProvider.findByHealthcareProviderId",
	 * HealthcareProvider.class); query.setParameter("PatientVisitId",muDataId);
	 * healthcareProvider = query.getResultList(); } catch (NoResultException
	 * exp) { throw new BusinessException("no.mu.id.found"); } catch (Exception
	 * exception) { throw new SystemException(exception); } return
	 * healthcareProvider; }
	 */
}
