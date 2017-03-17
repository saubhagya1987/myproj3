package com.versawork.http.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.versawork.http.dataobject.FeedListInfo;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.AccountBloodPressureManagement;
import com.versawork.http.model.EngageClientToFeature;
import com.versawork.http.model.FeedInfo;
import com.versawork.http.model.PatientRosetta;
import com.versawork.http.model.PatientVerification;

@Repository
public class UserRegistrationDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationDAO.class);

	@PersistenceContext(unitName = "versawork_DS", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	/**
	 * Verify if the user exists by validating MR Number and phone number
	 * against data present in patient verification table in database.
	 * 
	 * @param mrNumber
	 * @param phoneNumber
	 */
	public PatientVerification verifyUser(String mrNumber, String phoneNumber) throws BusinessException,
			SystemException {

		LOGGER.debug("Performing user verification for user having MR No: " + mrNumber + " & phoneNum: " + phoneNumber);
		PatientVerification patientVerificationData = null;

		try {
			Query query = ehrEntityManager.createNamedQuery("PatientVerification.findByUnitNumberAndPhoneNumber",
					PatientVerification.class);
			query.setParameter("medicalRecordNumber", mrNumber);
			query.setParameter("phoneNumber", phoneNumber);
			patientVerificationData = (PatientVerification) query.getSingleResult();

			return patientVerificationData;

		} catch (NoResultException noResultException) {
			throw new BusinessException("pat.verification.fail");
		} catch (Exception exception) {
			throw new SystemException(exception.getMessage());
		}

	}

	public List<AccountBloodPressureManagement> getAccountBPRemindersByActIdCurrentDate_7(Integer accountId,
			String todaysDate, String past3Days) throws BusinessException, SystemException {
		List<AccountBloodPressureManagement> accountBloodPressureManagement = null;
		try {
			TypedQuery<AccountBloodPressureManagement> query = (TypedQuery<AccountBloodPressureManagement>) entityManager
					.createNamedQuery(
							"AccountBloodPressureManagement.findByAccountMedicationManagementRemIdCurrentDate",
							AccountBloodPressureManagement.class);
			query.setParameter("accountMedicationManagementReminderId", new Account(accountId));
			query.setParameter("todaysDate", todaysDate);
			query.setParameter("past3Days", past3Days);
			accountBloodPressureManagement = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountBloodPressureManagement;
	}

	public List<EngageClientToFeature> getClientFeatures(int clientId, String version) throws BusinessException, SystemException {
		List<EngageClientToFeature> engageClientToFeature = null;
		try {
			TypedQuery<EngageClientToFeature> query = (TypedQuery<EngageClientToFeature>) entityManager
					.createNamedQuery("EngageClientToFeature.findByClientDatabaseIdAndVersion", EngageClientToFeature.class);
			query.setParameter("clientDatabaseId", clientId);
			query.setParameter("version", version);
			engageClientToFeature = query.getResultList();
			return engageClientToFeature;
		} catch (NoResultException exp) {
			throw new BusinessException("no.feature.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	public PatientRosetta getPatientByMRNumber(String medicalRecordNumber, Integer clientDatabaseId)
			throws BusinessException, SystemException {
		PatientRosetta pRosetta = null;
		try {
			TypedQuery<PatientRosetta> query = (TypedQuery<PatientRosetta>) ehrEntityManager.createNamedQuery(
					"PatientRosetta.findByMedicalRecordNumberClientDBId", PatientRosetta.class);
			query.setParameter("medicalRecordNumber", medicalRecordNumber);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			pRosetta = query.getSingleResult();
			return pRosetta;
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	/*public List<FeedInfo> getFeedInfoList(Integer accountId, Integer feedOffset)throws BusinessException, SystemException {
		List<FeedInfo> feedInfo = null;
		try {
			TypedQuery<FeedInfo> query = (TypedQuery<FeedInfo>) ehrEntityManager.createNamedQuery(
					"FeedInfo.findByAccountId", FeedInfo.class).setMaxResults(10).setFirstResult(feedOffset);
			query.setParameter("accountId", accountId);			
			feedInfo = query.getResultList();
			LOGGER.info("feedInfo:--"+feedInfo);
			return feedInfo;
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
			
	}*/
	/*public FeedInfo getFeedInfo(Integer accountId, Integer feedInfoId)throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		FeedInfo feedInfo = null;
		try {
			TypedQuery<FeedInfo> query = (TypedQuery<FeedInfo>) ehrEntityManager.createNamedQuery(
					"FeedInfo.findByAccountIdAndFeedId", FeedInfo.class);
			query.setParameter("accountId", accountId);		
			query.setParameter("feedInfoId", feedInfoId);
			feedInfo = query.getSingleResult();
			return feedInfo;
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
			
	}*/
	@Transactional("ehr_txn")
	public FeedInfo updateFeedInformation(FeedInfo feedInfo) {
		// TODO Auto-generated method stub
		
		 ehrEntityManager.merge(feedInfo);
		ehrEntityManager.flush();
		return feedInfo;
	}
	
	@Transactional("ehr_txn")
	public void remove(FeedInfo feedInfo) {
		try{
			ehrEntityManager.remove(ehrEntityManager.contains(feedInfo) ? feedInfo : ehrEntityManager.merge(feedInfo));
			//ehrEntityManager.remove(feedInfo);
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
	}

	public FeedInfo getFeedInfo(Integer accountId, Integer clientId, Integer clientDatabaseId, String medicationName) throws BusinessException, SystemException{
		FeedInfo feedInfo = null;
		try {
			TypedQuery<FeedInfo> query = (TypedQuery<FeedInfo>) ehrEntityManager.createNamedQuery(
					"FeedInfo.findByAccountIdAndMedicationName", FeedInfo.class);
			query.setParameter("accountId", accountId);		
			query.setParameter("clientId", clientId);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			query.setParameter("medicationName", medicationName);
			feedInfo = query.getSingleResult();
			return feedInfo;
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
			
	}

	public List<FeedInfo> getFeedInfoList(Integer accountId, Integer clientId,
			Integer clientDatabaseId, 
			Integer feedSize) throws BusinessException, SystemException{
		List<FeedInfo> feedInfo = null;
		try {
			TypedQuery<FeedInfo> query = (TypedQuery<FeedInfo>) ehrEntityManager.createNamedQuery(
					"FeedInfo.findByAccountId", FeedInfo.class).setMaxResults(10).setFirstResult(feedSize);
			query.setParameter("accountId", accountId);	
			query.setParameter("clientId", clientId);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			
			feedInfo = query.getResultList();			
			return feedInfo;
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	public FeedInfo getFeedInfo(Integer accountId, Integer clientId,Integer clientDatabaseId, Integer feedMessageId, Integer featureId) throws BusinessException, SystemException{
		FeedInfo feedInfo = null;
		try {
			TypedQuery<FeedInfo> query = (TypedQuery<FeedInfo>) ehrEntityManager.createNamedQuery(
					"FeedInfo.findByAccountIdAndFeatureIdAndFeedMessageId", FeedInfo.class);
			query.setParameter("accountId", accountId);		
			query.setParameter("feedMessageId", feedMessageId);
			query.setParameter("featureId", featureId);
			query.setParameter("clientId", clientId);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			feedInfo = query.getSingleResult();

		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return feedInfo;
	}
	
	
	
	

}
