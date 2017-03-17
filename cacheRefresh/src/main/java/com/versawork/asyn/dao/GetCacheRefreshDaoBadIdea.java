/**
 * 
 */
package com.versawork.asyn.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.versawork.asyn.constant.LogFile;
import com.versawork.asyn.exception.SystemException;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.model.Account;
import com.versawork.http.model.EtlLogger;
import com.versawork.http.model.FacilityNotice;
import com.versawork.http.model.FacilityProvider;
import com.versawork.http.model.FacilityService;
import com.versawork.http.model.PatientAllergy;
import com.versawork.http.model.PatientCarePlan;
import com.versawork.http.model.PatientCareTeam;
import com.versawork.http.model.PatientDiagnosis;
import com.versawork.http.model.PatientFunctionalStatus;
import com.versawork.http.model.PatientImaging;
import com.versawork.http.model.PatientImmunization;
import com.versawork.http.model.PatientLab;
import com.versawork.http.model.PatientPrescription;
import com.versawork.http.model.PatientProblem;
import com.versawork.http.model.PatientProcedure;
import com.versawork.http.model.PatientVerification;
import com.versawork.http.model.PatientVisit;
import com.versawork.http.model.PatientVisitInpatient;
import com.versawork.http.model.PatientVitalSign;

/**
 * @author RAHUL BHALLA
 *
 */
//@Repository
public class GetCacheRefreshDaoBadIdea {
  private static final Logger LOGGER = LoggerFactory.getLogger(LogFile.ETL_CACHE_LOG_FILE
      .getFileName());

  @PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
  private EntityManager ehrEntityManager;

  @PersistenceContext(unitName = "versawork_DS", type = PersistenceContextType.TRANSACTION)
  private EntityManager entityManager;

  public List<PatientLab> getPatientLabResult(long transactionId, int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientLab> query =
          (TypedQuery<PatientLab>) ehrEntityManager.createNamedQuery(
              "PatientLab.findByEtlInfoAccount", PatientLab.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientLab> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientLab> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Patient Lab Result " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }

  /**
   * @param clientDatabaseId
   * @return
   */
  public List<EtlLogger> getUnprocessedETLLog(Integer clientDatabaseID) {

    try {
      TypedQuery<EtlLogger> query =
          (TypedQuery<EtlLogger>) ehrEntityManager.createNamedQuery(
              "EtlLogger.findByClientDatabaseIdUnprocessed", EtlLogger.class);
      query.setParameter("clientDatabaseId", clientDatabaseID);

        return query.getResultList();

    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Unprocessed ETL Log " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }

  /**
   * @param transactionId
   * @param clientDatabaseId
   * @return
   */
  public List<PatientAllergy> getPatientAllergies(long transactionId, int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientAllergy> query =
          (TypedQuery<PatientAllergy>) ehrEntityManager.createNamedQuery(
              "PatientAllergy.findByEtlInfoAccount", PatientAllergy.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientAllergy> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      boolean verify = list instanceof LinkedList;
      boolean arrayverify = list instanceof LinkedList;
      LOGGER.debug("list is instance of Linked list : "+ verify);
      LOGGER.debug("list is instance of array list : "+arrayverify );
      
     LinkedList<PatientAllergy> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Patient Allergies " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }

  /**
   * @param transactionId
   * @param clientDatabaseId
   * @return
   */
  public List<PatientImaging> getPatientImages(long transactionId, int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientImaging> query =
          (TypedQuery<PatientImaging>) ehrEntityManager.createNamedQuery(
              "PatientImaging.findByEtlInfoAccount", PatientImaging.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientImaging> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientImaging> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Patient Images " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }

  public List<PatientPrescription> getPatientPrescriptions(long transactionId, int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientPrescription> query =
          (TypedQuery<PatientPrescription>) ehrEntityManager.createNamedQuery(
              "PatientPrescription.findByEtlInfoAccount", PatientPrescription.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientPrescription> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientPrescription> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Patient Prescriptions " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }

  public List<PatientProblem> getPatientProblems(long transactionId, int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientProblem> query =
          (TypedQuery<PatientProblem>) ehrEntityManager.createNamedQuery(
              "PatientProblem.findByEtlInfoAccount", PatientProblem.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientProblem> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientProblem> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Patient Problems " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }

  public List<PatientVitalSign> getPatientVitalSign(long transactionId, int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientVitalSign> query =
          (TypedQuery<PatientVitalSign>) ehrEntityManager.createNamedQuery(
              "PatientVitalSign.findByEtlInfoAccount", PatientVitalSign.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientVitalSign> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientVitalSign> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Patient Vital Sign " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }

  /**
   * @param transactionId
   * @param clientDatabaseId
   * @return
   */
  public List<PatientCareTeam> getPatientCareTeam(long transactionId, int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientCareTeam> query =
          (TypedQuery<PatientCareTeam>) ehrEntityManager.createNamedQuery(
              "PatientCareTeam.findByEtlInfoAccount", PatientCareTeam.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientCareTeam> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientCareTeam> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Patient Care Team " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }

  /**
   * @param transactionId
   * @param clientDatabaseId
   * @return
   */
  public List<PatientProcedure> getPatientProcedures(long transactionId, int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientProcedure> query =
          (TypedQuery<PatientProcedure>) ehrEntityManager.createNamedQuery(
              "PatientProcedure.findByEtlInfoAccount", PatientProcedure.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientProcedure> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientProcedure> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Patient Procedures " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }

  }

  /**
   * @param transactionId
   * @param clientDatabaseId
   * @return
   */
  public List<PatientCarePlan> getPatientCarePlan(long transactionId, int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientCarePlan> query =
          (TypedQuery<PatientCarePlan>) ehrEntityManager.createNamedQuery(
              "PatientCarePlan.findByEtlInfoAccount", PatientCarePlan.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientCarePlan> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientCarePlan> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Patient Care Plan " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }

  /**
   * @param transactionId
   * @param clientDatabaseId
   * @return
   */
  public List<PatientDiagnosis> getPatientDiagnosis(long transactionId, int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientDiagnosis> query =
          (TypedQuery<PatientDiagnosis>) ehrEntityManager.createNamedQuery(
              "PatientDiagnosis.findByEtlInfoAccount", PatientDiagnosis.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientDiagnosis> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientDiagnosis> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure  Patient Diagnosis  " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }

  /**
   * @param transactionId
   * @param clientDatabaseId
   * @return
   */
  public List<PatientImmunization> getPatientImmunization(long transactionId, int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientImmunization> query =
          (TypedQuery<PatientImmunization>) ehrEntityManager.createNamedQuery(
              "PatientImmunization.findByEtlInfoAccount", PatientImmunization.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientImmunization> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientImmunization> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Patient Immunization " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }

  /**
   * @param transactionId
   * @param clientDatabaseId
   * @return
   */
  public List<PatientFunctionalStatus> getPatientFunctionalStatus(long transactionId,
      int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientFunctionalStatus> query =
          (TypedQuery<PatientFunctionalStatus>) ehrEntityManager.createNamedQuery(
              "PatientFunctionalStatus.findByEtlInfoAccount", PatientFunctionalStatus.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientFunctionalStatus> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientFunctionalStatus> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Patient Functional Status"
          + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }


  /**
   * @param clientDatabaseId
   * @return
   */
  public List<FacilityNotice> getHospitalNotice(int clientDatabaseId, int startPosition, int maxResult) {

    try {

      TypedQuery<FacilityNotice> query =
          (TypedQuery<FacilityNotice>) entityManager.createNamedQuery(
              "FacilityNotice.findByClientDatabaseId", FacilityNotice.class);

      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<FacilityNotice> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<FacilityNotice> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Hospital Notice " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }



  /**
   * @param clientDatabaseId
   * @return
   */
  public List<FacilityProvider> getDoctorsList(int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<FacilityProvider> query =
          (TypedQuery<FacilityProvider>) entityManager.createNamedQuery(
              "FacilityProvider.findByClientDatabaseId", FacilityProvider.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<FacilityProvider> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<FacilityProvider> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Doctors List " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }

  }



  /**
   * @param clientDatabaseId
   * @return
   */
  public List<FacilityService> getServicesList(int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<FacilityService> query =
          (TypedQuery<FacilityService>) entityManager.createNamedQuery(
              "FacilityService.findByClientDatabaseId", FacilityService.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<FacilityService> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<FacilityService> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure  Services List " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }


  /**
   * @param transactionId
   * @param clientDatabaseId
   * @return
   */
  public List<PatientVisitInpatient> getPatientVisitInpatient(long transactionId,
      int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientVisitInpatient> query =
          (TypedQuery<PatientVisitInpatient>) ehrEntityManager.createNamedQuery(
              "PatientVisitInpatient.findByEtlInfoAccount", PatientVisitInpatient.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientVisitInpatient> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientVisitInpatient> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Visit Inpatient " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }



  /**
   * @param transactionId
   * @param clientDatabaseId
   * @return
   */
  public List<PatientVisit> getPatientVisit(long transactionId, int clientDatabaseId, int startPosition, int maxResult) {

    try {
      TypedQuery<PatientVisit> query =
          (TypedQuery<PatientVisit>) ehrEntityManager.createNamedQuery(
              "PatientVisit.findByEtlInfoAccount", PatientVisit.class);
      query.setParameter("clientDatabaseId", clientDatabaseId);
      query.setParameter("transactionId", transactionId);
      query.setFirstResult(startPosition);
      query.setMaxResults(maxResult);
      List<PatientVisit> list= null;
      synchronized (GetCacheRefreshDaoBadIdea.class) {
	  
	 list = query.getResultList();
      }
      
     LinkedList<PatientVisit> linkedList  = new LinkedList<>(list);
        list = null;
        return linkedList;
    
    } catch (Exception e) {
      LOGGER.error("Dao Exception Occure Patient Visit " + ExceptionUtils.getStackTrace(e));
      throw new SystemException(e);
    }
  }

  /**
   * @param accountId
   * @return
   */
  public Account getAccountById(int accountId) {

    Account aAccount = null;
    try {
      TypedQuery<Account> query =
          (TypedQuery<Account>) entityManager.createNamedQuery("Account.findByAccountId",
              Account.class);
      query.setParameter("accountId", accountId);
      aAccount = query.getSingleResult();
    } catch (Exception e) {

      // String errortrace = ExceptionUtils.getStackTrace(e);
      // LOGGER.error("Dao Exception Occure " +errortrace);
      //
      aAccount = null;
    }
    return aAccount;
  }
  
  public PatientVerification getPatientVerification(Integer accountId) throws BusinessException, SystemException {

		PatientVerification patientVerification = null;
		try {
			TypedQuery<PatientVerification> query = (TypedQuery<PatientVerification>) ehrEntityManager
					.createNamedQuery("PatientVerification.findByAccountId", PatientVerification.class);
			query.setParameter("accountId", accountId);
			patientVerification = query.getSingleResult();

		} catch (NoResultException exp) {
			patientVerification = null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientVerification;
	}
  /*
   * private static final Logger LOGGER = LoggerFactory.getLogger(LogFile.ETL_CACHE_LOG_FILE
   * .getFileName());
   * 
   * @PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
   * private EntityManager ehrEntityManager;
   * 
   * @PersistenceContext(unitName = "versawork_DS", type = PersistenceContextType.TRANSACTION)
   * private EntityManager entityManager;
   */

  // Update status

  /**
   * @param etlLoggers
   */
  @Transactional(value = "ehr_txn")
  public void update(List<EtlLogger> etlLoggers) {

    for (EtlLogger logger : etlLoggers) {
      try {
        ehrEntityManager.merge(logger);
        LOGGER.debug("UPDATE ETL LOGGER WITH TRANSACTION ID "
            + logger.getEtlLoggerPK().getTransactionId());
      } catch (RuntimeException ex) {
        LOGGER.error("EXCEPTION ETL LOGGER WITH TRANSACTION ID "
            + logger.getEtlLoggerPK().getTransactionId() + " \n "
            + ExceptionUtils.getStackTrace(ex));


      }
    }
  }

  /**
   * 
   * @param moduleTransactionStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePatientImagingStatus(String moduleTransactionStatus, List<Integer> accountIds,
      long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "imgStatus");

  }

  /**
   * 
   * @param cacheKeysStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePatientProblemStatus(String moduleTransactionStatus, List<Integer> accountIds,
      long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "probStatus");
  }

  /**
   * 
   * @param moduleTransactionStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePatientPrescriptionStatus(String moduleTransactionStatus,
      List<Integer> accountIds, long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "pmedStatus");

  }

  /**
   * @param moduleTransactionStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePatientAllergyStatus(String moduleTransactionStatus, List<Integer> accountIds,
      long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "pallStatus");

  }

  /**
   * 
   * @param moduleTransactionStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePlabStatus(String moduleTransactionStatus, List<Integer> accountIds,
      long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "plabStatus");

  }

  /**
   * @param moduleTransactionStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePatientVitalSignStatus(String moduleTransactionStatus,
      List<Integer> accountIds, long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "pVitalSignStatus");
  }

  /**
   * @param moduleTransactionStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePatientCareTeamStatus(String moduleTransactionStatus, List<Integer> accountIds,
      long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "careTeamStatus");

  }

  /**
   * @param moduleTransactionStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePatientProcedureStatus(String moduleTransactionStatus,
      List<Integer> accountIds, long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "procedureStatus");

  }

  /**
   * @param moduleTransactionStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updateCarePlanInfoStatus(String moduleTransactionStatus, List<Integer> accountIds,
      long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "carePlanStatus");
  }


  /**
   * @param moduleTransactionStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePatientFunctionalStatus(String moduleTransactionStatus,
      List<Integer> accountIds, long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "functionalStatus");

  }

  /**
   * @param moduleTransactionStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePatientImmunalizationStatus(String moduleTransactionStatus,
      List<Integer> accountIds, long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "immunalizationStatus");

  }

  /**
   * @param moduleTransactionStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePatientDiagnosisStatus(String moduleTransactionStatus,
      List<Integer> accountIds, long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "diagnosisStatus");

  }

  /**
   * @param status
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePatientVisitStatus(String moduleTransactionStatus, List<Integer> accountIds,
      long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "patientVisitStatus");

  }

  /**
   * @param moduleTransactionStatus
   * @param accountIds
   * @param transactionId
   * @param clientDatabaseId
   */
  @Transactional(value = "ehr_txn")
  public void updatePatientVisitInpatientStatus(String moduleTransactionStatus,
      List<Integer> accountIds, long transactionId, int clientDatabaseId) {

    updateStatusByModule(moduleTransactionStatus, accountIds, transactionId, clientDatabaseId,
        "inpatientStatus");

  }


  /**
   * @param clientDatabaseId
   * @param transactionId
   * @param status
   */
  @Transactional(value = "ehr_txn")
  public void updateFacilityServiceStatus(int clientDatabaseId, long transactionId, String status) {

    updateFacilityStatus(clientDatabaseId, transactionId, status, "facilityServiceStatus");

  }

  /**
   * @param clientDatabaseId
   * @param transactionId
   * @param status
   */
  @Transactional(value = "ehr_txn")
  public void updateFacilityNoticeStatus(int clientDatabaseId, long transactionId, String status) {

    updateFacilityStatus(clientDatabaseId, transactionId, status, "facilityNoticeStatus");

  }

  /**
   * 
   * @param clientDatabaseId
   * @param transactionId
   * @param status
   */
  @Transactional(value = "ehr_txn")
  public void updateFacilityProviderStatus(int clientDatabaseId, long transactionId, String status) {

    updateFacilityStatus(clientDatabaseId, transactionId, status, "facilityProviderStatus");
  }



  // private methods
  private void updateStatusByModule(String status, List<Integer> accountIds, long transactionId,
      int clientDatabaseId, String field) {

    Query query = null;
    try {
      String updateQueryString = getUpdateQuery(field);
      query = ehrEntityManager.createQuery(updateQueryString);
      query = query.setParameter("status", status);
      query = query.setParameter("transactionId", transactionId);
      query = query.setParameter("clientDatabaseId", clientDatabaseId);
      
      LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++UPDATING MODULES FOR FIELD " + field + " ++++++++++++++++++++++++++++++++++++++++++++++++++++");
      synchronized (GetCacheRefreshDaoBadIdea.class) {
        query.executeUpdate();
      }
    } catch (Exception e) {
      LOGGER.error("Exception While updating field  " + field + "\n"
          + ExceptionUtils.getStackTrace(e));
    }
  }

  private void updateFacilityStatus(int clientDatabaseId, long transactionId, String status,
      String updateFieldName) {

    Query query = null;
    try {
      StringBuilder queryBuilder = new StringBuilder("UPDATE EtlInfo info SET info.");
      queryBuilder.append(updateFieldName);
      queryBuilder
          .append(" = :status WHERE info.etlInfoPK.transactionId = :transactionId AND info.etlInfoPK.clientDatabaseId = :clientDatabaseId");
      String qlString = queryBuilder.toString();
      query = ehrEntityManager.createQuery(qlString);
      query.setParameter("status", status);
      query.setParameter("transactionId", transactionId);
      query.setParameter("clientDatabaseId", clientDatabaseId);
    
      LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++UPDATING MODULES FOR FIELD " + updateFieldName + " ++++++++++++++++++++++++++++++++++++++++++++++++++++");
      synchronized (GetCacheRefreshDaoBadIdea.class) {
        query.executeUpdate();
      }
    } catch (Exception e) {

      LOGGER
          .error("Dao Exception Occure update Facility Status " + ExceptionUtils.getStackTrace(e));
    }
  }

  private String getUpdateQuery(String etlinfoField) {

    StringBuilder query = new StringBuilder("UPDATE EtlInfo info SET info.");
    query.append(etlinfoField);
    query.append(" = :status ");
    query
        .append("WHERE info.etlInfoPK.transactionId = :transactionId and info.etlInfoPK.clientDatabaseId = :clientDatabaseId");

    return query.toString();
  }



}
