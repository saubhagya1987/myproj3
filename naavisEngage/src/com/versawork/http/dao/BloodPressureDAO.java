package com.versawork.http.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.AccountBloodPressureEngage;
import com.versawork.http.model.AccountBloodPressureEngageResponse;
import com.versawork.http.model.AccountBloodPressureManagement;
import com.versawork.http.model.AccountBloodPressureManagementSchedule;
import com.versawork.http.model.AccountMedicationManagement;
import com.versawork.http.model.AccountWeight;
import com.versawork.http.model.PatientActiveTime;
import com.versawork.http.model.PatientCalorieCount;
import com.versawork.http.model.PatientDistance;
import com.versawork.http.model.PatientHeartAge;
import com.versawork.http.model.PatientHeartRate;
import com.versawork.http.model.PatientOverallStress;
import com.versawork.http.model.PatientSleep;
import com.versawork.http.model.PatientStepCount;
import com.versawork.http.model.PatientVitalSign;
import com.versawork.http.utils.DateRange;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class BloodPressureDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(BloodPressureDAO.class);
	// private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	// Injected database connection:
	@PersistenceContext(unitName = "versawork_DS")
	private EntityManager em;
	
	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(AccountBloodPressureEngage accountBloodPressureEngage) {
		em.persist(accountBloodPressureEngage);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(AccountBloodPressureManagement accountBloodPressureManagement) {
		em.persist(accountBloodPressureManagement);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(AccountBloodPressureManagementSchedule accountBloodPressureManagementSchedule) {
		em.persist(accountBloodPressureManagementSchedule);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeallBpResp(List<AccountBloodPressureEngageResponse> accountBloodPressureEngageResponse) {
		em.remove(accountBloodPressureEngageResponse);
	}

	public void update(AccountBloodPressureEngage accountBloodPressureEngage) throws BusinessException, SystemException {
		try {
			em.merge(accountBloodPressureEngage);
		} catch (Exception exception) {
			throw new SystemException(exception.getMessage());
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(AccountBloodPressureManagement accountBloodPressureManagement) {
		em.remove(accountBloodPressureManagement);
	}

	public void update(AccountBloodPressureManagement accountBloodPressureManagement) throws BusinessException,
			SystemException {
		try {
			em.merge(accountBloodPressureManagement);
		} catch (Exception exception) {
			throw new SystemException(exception.getMessage());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(AccountBloodPressureEngageResponse accountBloodPressureEngageResponse) {
		em.merge(accountBloodPressureEngageResponse);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(AccountBloodPressureManagementSchedule accountBloodPressureManagementSchedule) {
		em.remove(accountBloodPressureManagementSchedule);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(AccountBloodPressureEngageResponse accountBloodPressureEngageResponse) {
		em.remove(accountBloodPressureEngageResponse);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void flush() {
		em.flush();
	}

	public List<AccountBloodPressureEngage> getAccountBloodPressureEngageByAccountIdandDeleteFlag(Integer accountId)
			throws BusinessException, SystemException {
		List<AccountBloodPressureEngage> accountBloodPressureEngage = null;
		try {
			TypedQuery<AccountBloodPressureEngage> query = (TypedQuery<AccountBloodPressureEngage>) em
					.createNamedQuery("AccountBloodPressureEngage.findByAccountIdandDeleteFlag",
							AccountBloodPressureEngage.class);
			query.setParameter("accountId", accountId);
			query.setParameter("deleteFlag", false);
			accountBloodPressureEngage = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountBloodPressureEngage;
	}

	public List<AccountBloodPressureEngage> getAccountBloodPressureEngageByAccountId(Integer accountId)
			throws BusinessException, SystemException {
		List<AccountBloodPressureEngage> accountBloodPressureEngage = null;
		try {
			TypedQuery<AccountBloodPressureEngage> query = (TypedQuery<AccountBloodPressureEngage>) em
					.createNamedQuery("AccountBloodPressureEngage.findByAccountId", AccountBloodPressureEngage.class);
			query.setParameter("accountId", new Account(accountId));
			accountBloodPressureEngage = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountBloodPressureEngage;
	}

	public AccountBloodPressureEngage getDetailsByActIdBpId(Integer accountId, Integer BloodPressureId)
			throws BusinessException, SystemException {

		AccountBloodPressureEngage accountBloodPressureEngage = null;

		LOGGER.debug("Getting Account Details By accountId Id : " + accountId + "BloodPressureId : " + BloodPressureId);
		try {
			TypedQuery<AccountBloodPressureEngage> query = em.createNamedQuery(
					"AccountBloodPressureEngage.findByAccountIdBloodPressureId", AccountBloodPressureEngage.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("accountBloodPressureEngageId", BloodPressureId);

			accountBloodPressureEngage = query.getSingleResult();
		} catch (NoResultException noResultException) {
			LOGGER.error("Invalid Account Id or Blood Pressure Id ");
			throw new BusinessException("invalid.authtoken.inrequest");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountBloodPressureEngage;
	}

	public AccountBloodPressureEngage deleteBlodPesurByActIdBpId(Integer accountId, Integer accountBloodPressureEngageId)
			throws BusinessException, SystemException {
		AccountBloodPressureEngage accountBloodPressureEngage = null;
		try {
			TypedQuery<AccountBloodPressureEngage> query = (TypedQuery<AccountBloodPressureEngage>) em
					.createNamedQuery("AccountBloodPressureEngage.findByAccountIdBloodPressureId",
							AccountBloodPressureEngage.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("accountBloodPressureEngageId", accountBloodPressureEngageId);

			accountBloodPressureEngage = query.getSingleResult();
		} catch (Exception exception) {
			throw new SystemException(exception.getMessage());
		}

		return accountBloodPressureEngage;
	}

	public Account getAccountById(Integer accountId) throws BusinessException, SystemException {
		Account account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) em.createNamedQuery("Account.findByAccountId",
					Account.class);
			query.setParameter("accountId", accountId);
			account = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return account;
	}

	public List<AccountBloodPressureManagement> getReminderByActIdDeleteFlag(Integer accountId)
			throws BusinessException, SystemException {
		List<AccountBloodPressureManagement> accountBloodPressureManagement = null;
		try {
			TypedQuery<AccountBloodPressureManagement> query = (TypedQuery<AccountBloodPressureManagement>) em
					.createNamedQuery("AccountBloodPressureManagement.findByAccountIdDeleteFlag",
							AccountBloodPressureManagement.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("deleteFlag", false);
			accountBloodPressureManagement = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountBloodPressureManagement;
	}

	public AccountBloodPressureManagement getDetailsByBPIdActId(Integer accountBloodPressureManagementId,
			Integer accountId) throws BusinessException, SystemException {
		AccountBloodPressureManagement accountBloodPressureManagement = null;
		try {
			TypedQuery<AccountBloodPressureManagement> query = (TypedQuery<AccountBloodPressureManagement>) em
					.createNamedQuery("AccountBloodPressureManagement.findByAccountBloodPressureManagementIdActId",
							AccountBloodPressureManagement.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("accountBloodPressureManagementId", accountBloodPressureManagementId);
			accountBloodPressureManagement = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.bloodpressure.id.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountBloodPressureManagement;
	}

	public List<AccountBloodPressureEngageResponse> getAccountBpEngageResponseByBpRemId(
			Integer accountBloodPressureManagementId) throws BusinessException, SystemException {
		List<AccountBloodPressureEngageResponse> accountBloodPressureEngageResponse = null;
		try {
			TypedQuery<AccountBloodPressureEngageResponse> query = (TypedQuery<AccountBloodPressureEngageResponse>) em
					.createNamedQuery("AccountBloodPressureEngageResponse.findByAccountBpManagementId",
							AccountBloodPressureEngageResponse.class);
			query.setParameter("accountBloodPressureManagementId", new AccountBloodPressureManagement(
					accountBloodPressureManagementId));
			accountBloodPressureEngageResponse = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.reminder.id");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountBloodPressureEngageResponse;
	}

	public List<AccountBloodPressureEngageResponse> getAccountBPResponseCountCurrentDate_7(Integer accountId,
			String todaysDate, String past7Days) throws BusinessException, SystemException {
		List<AccountBloodPressureEngageResponse> accountBloodPressureEngageResponse = null;
		try {
			TypedQuery<AccountBloodPressureEngageResponse> query = (TypedQuery<AccountBloodPressureEngageResponse>) em
					.createNamedQuery("AccountBloodPressureEngageResponse.findByAccountBPActIdCurrentDate7",
							AccountBloodPressureEngageResponse.class);
			query.setParameter("accountId", accountId);
			query.setParameter("todaysDate", todaysDate);
			query.setParameter("past7Days", past7Days);
			accountBloodPressureEngageResponse = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountBloodPressureEngageResponse;
	}

	public AccountBloodPressureEngageResponse getAccountBpEngageResponseByRecordIdentifier(Integer accountId,
			String recordIdentifier, Integer accountBloodPressureManagementId) throws BusinessException,
			SystemException {
		AccountBloodPressureEngageResponse accountBloodPressureEngageResponse = null;
		try {
			TypedQuery<AccountBloodPressureEngageResponse> query = (TypedQuery<AccountBloodPressureEngageResponse>) em
					.createNamedQuery("AccountBloodPressureEngageResponse.findByRecordIdentifier",
							AccountBloodPressureEngageResponse.class);
			query.setParameter("recordIdentifier", recordIdentifier);
			query.setParameter("accountId", accountId);
			query.setParameter("accountBloodPressureManagementId", new AccountBloodPressureManagement(
					accountBloodPressureManagementId));
			accountBloodPressureEngageResponse = query.getSingleResult();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountBloodPressureEngageResponse;
	}

	public AccountBloodPressureManagement getDetailsByBPIdActId(Integer accountBloodPressureManagementId)
			throws BusinessException, SystemException {
		AccountBloodPressureManagement accountBloodPressureManagement = null;
		try {
			TypedQuery<AccountBloodPressureManagement> query = (TypedQuery<AccountBloodPressureManagement>) em
					.createNamedQuery("AccountBloodPressureManagement.findByAccountBloodPressureManagementId",
							AccountBloodPressureManagement.class);
			query.setParameter("accountBloodPressureManagementId", accountBloodPressureManagementId);
			accountBloodPressureManagement = query.getSingleResult();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountBloodPressureManagement;
	}

	public List<AccountBloodPressureManagement> restoreReminderByActIdDeleteFlag(Integer accountId, Date todaysDate)
			throws BusinessException, SystemException {
		List<AccountBloodPressureManagement> accountBloodPressureManagement = null;
		try {
			TypedQuery<AccountBloodPressureManagement> query = (TypedQuery<AccountBloodPressureManagement>) em
					.createNamedQuery("AccountBloodPressureManagement.findByAccountIdDeleteFlagEndDate",
							AccountBloodPressureManagement.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("deleteFlag", true);
			query.setParameter("todaysDate", todaysDate);
			accountBloodPressureManagement = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountBloodPressureManagement;
	}

	public AccountBloodPressureEngage getBloodPressureByAccountId(Integer accountId)throws BusinessException, SystemException {
		AccountBloodPressureEngage accountBloodPressureEngage = null;
		try {
			TypedQuery<AccountBloodPressureEngage> query = (TypedQuery<AccountBloodPressureEngage>) em.createNamedQuery("AccountBloodPressureEngage.findByAccountIdWithLatestBp",
							AccountBloodPressureEngage.class).setMaxResults(1);
			query.setParameter("accountId", new Account(accountId));
			accountBloodPressureEngage = query.getSingleResult();
		} catch (Exception exception) {
			return null;
		}

		return accountBloodPressureEngage;
	}
	
	/**
	 * Get blood pressure records from EHR data(populated by ETL)
	 * 
	 * @param accountId
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public PatientVitalSign getEhrBloodPressure(Integer accountId)throws BusinessException, SystemException {
		PatientVitalSign accountBloodPressureEngage = null;
		try {
			TypedQuery<PatientVitalSign> query = (TypedQuery<PatientVitalSign>) ehrEntityManager.createNamedQuery("PatientVitalSign.findByAccountId",
					PatientVitalSign.class).setMaxResults(1);
			query.setParameter("accountId", new Account(accountId));
			accountBloodPressureEngage = query.getSingleResult();
		} catch (Exception exception) {
			return null;
		}

		return accountBloodPressureEngage;
	}
	
	
	public AccountWeight getWeightByAccountId(Integer accountId) throws BusinessException, SystemException{
		AccountWeight accountWeight = null;
		try {
			TypedQuery<AccountWeight> query = (TypedQuery<AccountWeight>) em.createNamedQuery("AccountWeight.findByAccountId",AccountWeight.class).setMaxResults(1);
			query.setParameter("accountId",accountId);
			accountWeight = query.getSingleResult();
		} catch (Exception exception) {
			return null;
		}

		return accountWeight;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(AccountWeight accountWeight) {
		em.persist(accountWeight);
	}

	public List<AccountWeight> getWeightList(Integer accountId)throws BusinessException, SystemException {
		List<AccountWeight> accountWeight = null;
		try {
			TypedQuery<AccountWeight> query = (TypedQuery<AccountWeight>) em.createNamedQuery("AccountWeight.findWeightListByAccountId",AccountWeight.class);
			query.setParameter("accountId",accountId);			
			accountWeight = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return accountWeight;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(AccountWeight accountWeight) {
		em.remove(accountWeight);
	}

	public AccountWeight deleteWeightByAccountIdAndWeightId(Integer weightId,Integer accountId) throws BusinessException, SystemException{
		AccountWeight accountWeight = null;
		try {
			TypedQuery<AccountWeight> query = (TypedQuery<AccountWeight>) em
					.createNamedQuery("AccountWeight.findWeightByAccountIdAndWeightId",AccountWeight.class);
			query.setParameter("accountId", accountId);
			query.setParameter("weightId", weightId);
			accountWeight = query.getSingleResult();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return accountWeight;
	}

	public AccountWeight getDetailsByWeightId(Integer accountId,Integer weightId) throws BusinessException, SystemException{
		AccountWeight accountWeight = null;		
		try {
			TypedQuery<AccountWeight> query = em.createNamedQuery(
					"AccountWeight.findWeightByAccountIdAndWeightId", AccountWeight.class);
			query.setParameter("accountId", accountId);
			query.setParameter("weightId", weightId);

			accountWeight = query.getSingleResult();
		} catch (NoResultException noResultException) {
			return null;
		} catch (Exception exception) {
			return null;
		}

		return accountWeight;
	}
	public void update(AccountWeight accountWeight) throws BusinessException, SystemException {
		try {
			em.merge(accountWeight);
		} catch (Exception exception) {
			throw new SystemException(exception.getMessage());
		}

	}

	public List<PatientVitalSign> getBloodPressureVitalSign(Integer accountId) throws BusinessException, SystemException{
		List<PatientVitalSign> patientVitalSign = null;
		try {
			TypedQuery<PatientVitalSign> query = (TypedQuery<PatientVitalSign>) ehrEntityManager.createNamedQuery("PatientVitalSign.findByAccountId", PatientVitalSign.class);
			query.setParameter("accountId", accountId);
			patientVitalSign = query.getResultList();
			
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}

		return patientVitalSign;
	}
	
	
	public List<PatientOverallStress> getPatientOverallStress(Integer clientId, Integer clientDatabaseId, Integer accountId)throws BusinessException, SystemException{
		List<PatientOverallStress> queryResp = null;
		try {
			TypedQuery<PatientOverallStress> query = (TypedQuery<PatientOverallStress>) ehrEntityManager.createNamedQuery("PatientOverallStress.findByClientIdClientDatabaseIdAccountId",PatientOverallStress.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDatabaseId);
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}
	
	public List<PatientActiveTime> getPatientActiveTime(Integer clientId, Integer clientDatabaseId, Integer accountId)throws BusinessException, SystemException{
		List<PatientActiveTime> queryResp = null;
		try {
			TypedQuery<PatientActiveTime> query = (TypedQuery<PatientActiveTime>) ehrEntityManager.createNamedQuery("PatientActiveTime.findByClientIdClientDatabaseIdAccountId",PatientActiveTime.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDatabaseId);
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}
	
	public List<PatientHeartAge> getPatientHeartAge(Integer clientId, Integer clientDatabaseId, Integer accountId)throws BusinessException, SystemException{
		List<PatientHeartAge> queryResp = null;
		try {
			TypedQuery<PatientHeartAge> query = (TypedQuery<PatientHeartAge>) ehrEntityManager.createNamedQuery("PatientHeartAge.findByClientIdClientDatabaseIdAccountId",PatientHeartAge.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDatabaseId);
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}
	
	public List<PatientHeartRate> getPatientHeartRate(Integer clientId, Integer clientDatabaseId, Integer accountId)throws BusinessException, SystemException{
		List<PatientHeartRate> queryResp = null;
		try {
			TypedQuery<PatientHeartRate> query = (TypedQuery<PatientHeartRate>) ehrEntityManager.createNamedQuery("PatientHeartRate.findByClientIdClientDatabaseIdAccountId",PatientHeartRate.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDatabaseId);
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}
	
	public List<PatientStepCount> getPatientStepCount(Integer clientId, Integer clientDatabaseId, Integer accountId)throws BusinessException, SystemException{
		List<PatientStepCount> queryResp = null;
		try {
			TypedQuery<PatientStepCount> query = (TypedQuery<PatientStepCount>) ehrEntityManager.createNamedQuery("PatientStepCount.findByClientIdClientDatabaseIdAccountId",PatientStepCount.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDatabaseId);
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}
	
	public List<PatientCalorieCount> getPatientCalorieCount(Integer clientId, Integer clientDatabaseId, Integer accountId)throws BusinessException, SystemException{
		List<PatientCalorieCount> queryResp = null;
		try {
			TypedQuery<PatientCalorieCount> query = (TypedQuery<PatientCalorieCount>) ehrEntityManager.createNamedQuery("PatientCalorieCount.findByClientIdClientDatabaseIdAccountId",PatientCalorieCount.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDatabaseId);
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}
	
	public List<PatientSleep> getPatientSleep(Integer clientId, Integer clientDatabaseId, Integer accountId)throws BusinessException, SystemException{
		List<PatientSleep> queryResp = null;
		try {
			TypedQuery<PatientSleep> query = (TypedQuery<PatientSleep>) ehrEntityManager.createNamedQuery("PatientSleep.findByClientIdClientDatabaseIdAccountId",PatientSleep.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDatabaseId);
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}
	
	public List<PatientDistance> getPatientDistance(Integer clientId, Integer clientDatabaseId, Integer accountId)throws BusinessException, SystemException{
		List<PatientDistance> queryResp = null;
		try {
			TypedQuery<PatientDistance> query = (TypedQuery<PatientDistance>) ehrEntityManager.createNamedQuery("PatientDistance.findByClientIdClientDatabaseIdAccountId",PatientDistance.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDatabaseId);
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}
	
	@Transactional("ehr_txn")
	public void persist(PatientOverallStress overallStress) {
		ehrEntityManager.persist(overallStress);	
		
	}
	
	@Transactional("ehr_txn")
	public void persist(PatientHeartRate patientHeartRate) {
		ehrEntityManager.persist(patientHeartRate);
		
	}

	public List<PatientOverallStress> getOverallStressPerWeek(int clientId,	int clientDatabaseId, Integer accountId, DateRange dateRange) throws BusinessException, SystemException{
		List<PatientOverallStress> queryResp = null;
		try {
			TypedQuery<PatientOverallStress> query = (TypedQuery<PatientOverallStress>) ehrEntityManager.createNamedQuery("PatientOverallStress.findByClientIdClientDatabaseIdAccountIdBetweenDates",PatientOverallStress.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDatabaseId);
			query.setParameter("startDate",dateRange.getStartDate());
			query.setParameter("endDate",dateRange.getEndDate());
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}

	public List<PatientStepCount> getStepsCountPerWeek(int clientId,int clientDbId, Integer accountId, DateRange dateRange) throws BusinessException, SystemException{
		List<PatientStepCount> queryResp = null;
		try {
			TypedQuery<PatientStepCount> query = (TypedQuery<PatientStepCount>) ehrEntityManager.createNamedQuery("PatientStepCount.findByClientIdClientDatabaseIdAccountIdStepCountDate",PatientStepCount.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDbId);
			query.setParameter("startDate",dateRange.getStartDate());
			query.setParameter("endDate",dateRange.getEndDate());
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}

	public List<PatientCalorieCount> getCalorieCountPerWeek(int clientId,int clientDbId, Integer accountId, DateRange dateRange) throws BusinessException, SystemException{
		List<PatientCalorieCount> queryResp = null;
		try {
			TypedQuery<PatientCalorieCount> query = (TypedQuery<PatientCalorieCount>) ehrEntityManager.createNamedQuery("PatientCalorieCount.findByClientIdClientDatabaseIdAccountIdCalorieCountDate",PatientCalorieCount.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDbId);
			query.setParameter("startDate",dateRange.getStartDate());
			query.setParameter("endDate",dateRange.getEndDate());
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}

	public List<PatientDistance> getDistanceCountPerWeek(int clientId,int clientDbId, Integer accountId, DateRange dateRange) throws BusinessException, SystemException{
		List<PatientDistance> queryResp = null;
		try {
			TypedQuery<PatientDistance> query = (TypedQuery<PatientDistance>) ehrEntityManager.createNamedQuery("PatientDistance.findByClientIdClientDatabaseIdAccountIdInstanceDate",PatientDistance.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDbId);
			query.setParameter("startDate",dateRange.getStartDate());
			query.setParameter("endDate",dateRange.getEndDate());
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}

	public List<PatientActiveTime> getActiveTimeCountPerWeek(int clientId,int clientDbId, Integer accountId, DateRange dateRange) throws BusinessException, SystemException{
		List<PatientActiveTime> queryResp = null;
		try {
			TypedQuery<PatientActiveTime> query = (TypedQuery<PatientActiveTime>) ehrEntityManager.createNamedQuery("PatientActiveTime.findByClientIdClientDatabaseIdAccountIdActiveTimeDate",PatientActiveTime.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDbId);
			query.setParameter("startDate",dateRange.getStartDate());
			query.setParameter("endDate",dateRange.getEndDate());
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}

	public List<PatientSleep> getSleepCountPerWeek(int clientId,
			int clientDbId, Integer accountId, DateRange dateRange) throws BusinessException, SystemException{
		List<PatientSleep> queryResp = null;
		try {
			TypedQuery<PatientSleep> query = (TypedQuery<PatientSleep>) ehrEntityManager.createNamedQuery("PatientSleep.findByClientIdClientDatabaseIdAccountIdSleepDate",PatientSleep.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDbId);
			query.setParameter("startDate",dateRange.getStartDate());
			query.setParameter("endDate",dateRange.getEndDate());
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}

	public List<AccountWeight> getWeightList(Integer accountId, DateRange dateRange)throws BusinessException, SystemException {
		List<AccountWeight> accountWeight = null;
		try {
			TypedQuery<AccountWeight> query = (TypedQuery<AccountWeight>) em.createNamedQuery("AccountWeight.findWeightListByAccountIdDate",AccountWeight.class);
			query.setParameter("accountId",accountId);	
			query.setParameter("startDate",dateRange.getStartDate());
			query.setParameter("endDate",dateRange.getEndDate());
			accountWeight = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return accountWeight;
	}

	public List<AccountBloodPressureEngage> getAccountBloodPressureEngageByAccountId(Integer accountId, DateRange dateRange) throws BusinessException, SystemException{
		List<AccountBloodPressureEngage> accountBloodPressureEngage = null;
		try {
			TypedQuery<AccountBloodPressureEngage> query = (TypedQuery<AccountBloodPressureEngage>) em
					.createNamedQuery("AccountBloodPressureEngage.findByAccountIdReminderDate", AccountBloodPressureEngage.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("startDate", dateRange.getStartDate());
			query.setParameter("endDate", dateRange.getEndDate());
			accountBloodPressureEngage = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountBloodPressureEngage;
	}

	public List<PatientHeartRate> getPatientHeartRateList(int clientId,	int clientDbId, Integer accountId, DateRange dateRange)throws BusinessException, SystemException {
		List<PatientHeartRate> queryResp = null;
		try {
			TypedQuery<PatientHeartRate> query = (TypedQuery<PatientHeartRate>) ehrEntityManager.createNamedQuery("PatientHeartRate.findByClientIdClientDatabaseIdAccountIdHeartRateDate",PatientHeartRate.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDbId);
			query.setParameter("startDate", dateRange.getStartDate());
			query.setParameter("endDate", dateRange.getEndDate());
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;
	}
	
	@Transactional("ehr_txn")
	public void persist(PatientHeartAge patientHeartAge) {
		ehrEntityManager.persist(patientHeartAge);
		
	}
	@Transactional("ehr_txn")
	public void persist(PatientStepCount patientStepCount) {
		ehrEntityManager.persist(patientStepCount);
		
	}
	@Transactional("ehr_txn")
	public void persist(PatientCalorieCount patientCalorieCount) {
		ehrEntityManager.persist(patientCalorieCount);
		
	}
	
	@Transactional("ehr_txn")
	public void persist(PatientSleep patientSleep) {
		ehrEntityManager.persist(patientSleep);
		
	}
	@Transactional("ehr_txn")
	public void persist(PatientActiveTime patientActiveTime) {
		ehrEntityManager.persist(patientActiveTime);
		
	}
	@Transactional("ehr_txn")
	public void persist(PatientDistance patientDistance) {
		ehrEntityManager.persist(patientDistance);
		
	}

	public List<PatientHeartAge> getPatientHeartAge(int clientId,int clientDbId, Integer accountId, DateRange dateRange)throws BusinessException, SystemException {
		List<PatientHeartAge> queryResp = null;
		try {
			TypedQuery<PatientHeartAge> query = (TypedQuery<PatientHeartAge>) ehrEntityManager.createNamedQuery("PatientHeartAge.findByClientIdClientDatabaseIdAccountIdHeartAgeDate",PatientHeartAge.class);
			query.setParameter("accountId",accountId);
			query.setParameter("clientId",clientId);
			query.setParameter("clientDatabaseId",clientDbId);
			query.setParameter("startDate",dateRange.getStartDate());
			query.setParameter("endDate",dateRange.getEndDate());
			queryResp = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return queryResp;	
	
	}

	public PatientOverallStress getOverallStress(int clientId, int clientDbId,Integer accountId)throws BusinessException, SystemException {
		PatientOverallStress patientOverallStress = null;
		try {
			TypedQuery<PatientOverallStress> query = (TypedQuery<PatientOverallStress>) ehrEntityManager.createNamedQuery("PatientOverallStress.findByAccountId",PatientOverallStress.class);
			query.setParameter("accountId", accountId);
			query.setParameter("clientId", clientId);
			query.setParameter("clientDatabaseId", clientDbId);
			/*List<PatientOverallStress> list=query.getResultList();
			if(list!=null && list.size()>0)
			{
				patientOverallStress = query.getResultList().get(0);
			}*/
			patientOverallStress=query.getSingleResult();
			
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return patientOverallStress;
	}

	public PatientDistance getDistanceCount(int clientId, int clientDbId, Integer accountId) throws BusinessException, SystemException{
		PatientDistance patientDistance = null;
		try {
			TypedQuery<PatientDistance> query = (TypedQuery<PatientDistance>) ehrEntityManager.createNamedQuery("PatientDistance.findByAccountId",PatientDistance.class);
			query.setParameter("accountId", accountId);
			query.setParameter("clientId", clientId);
			query.setParameter("clientDatabaseId", clientDbId);
			/*List<PatientDistance> list=query.getResultList();
			if(list!=null && list.size()>0)
			{
				patientDistance = query.getResultList().get(0);
			}*/
			patientDistance=query.getSingleResult();
			
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return patientDistance;
	}

	public PatientStepCount getStepsCount(int clientId, int clientDbId,Integer accountId) throws BusinessException, SystemException{
		PatientStepCount patientStepCount = null;
		try {
			TypedQuery<PatientStepCount> query = (TypedQuery<PatientStepCount>) ehrEntityManager.createNamedQuery("PatientStepCount.findByAccountId",PatientStepCount.class);
			query.setParameter("accountId", accountId);
			query.setParameter("clientId", clientId);
			query.setParameter("clientDatabaseId", clientDbId);
			/*List<PatientStepCount> list=query.getResultList();
			if(list!=null && list.size()>0)
			{
				patientStepCount = query.getResultList().get(0);
			}*/
			patientStepCount=query.getSingleResult();
			
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return patientStepCount;
	}

	public PatientCalorieCount getCalorieCount(int clientId, int clientDbId,Integer accountId) throws BusinessException, SystemException{

		PatientCalorieCount patientCalorieCount = null;
		try {
			TypedQuery<PatientCalorieCount> query = (TypedQuery<PatientCalorieCount>) ehrEntityManager.createNamedQuery("PatientCalorieCount.findByAccountId",PatientCalorieCount.class);
			query.setParameter("accountId", accountId);
			query.setParameter("clientId", clientId);
			query.setParameter("clientDatabaseId", clientDbId);
			/*List<PatientCalorieCount> list=query.getResultList();
			if(list!=null && list.size()>0)
			{
				patientCalorieCount = query.getResultList().get(0);
			}*/
			patientCalorieCount=query.getSingleResult();
			
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return patientCalorieCount;
	}

	public PatientActiveTime getActiveTimeCount(int clientId, int clientDbId,Integer accountId) throws BusinessException, SystemException{
		PatientActiveTime patientActiveTime = null;
		try {
			TypedQuery<PatientActiveTime> query = (TypedQuery<PatientActiveTime>) ehrEntityManager.createNamedQuery("PatientActiveTime.findByAccountId",PatientActiveTime.class);
			query.setParameter("accountId", accountId);
			query.setParameter("clientId", clientId);
			query.setParameter("clientDatabaseId", clientDbId);
			/*List<PatientActiveTime> list=query.getResultList();
			if(list!=null && list.size()>0)
			{
				patientActiveTime = query.getResultList().get(0);
			}*/
			patientActiveTime=query.getSingleResult();
			
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return patientActiveTime;
	}

	public PatientSleep getSleepCount(int clientId, int clientDbId,	Integer accountId) throws BusinessException, SystemException{
		PatientSleep patientSleep = null;
		try {
			TypedQuery<PatientSleep> query = (TypedQuery<PatientSleep>) ehrEntityManager.createNamedQuery("PatientSleep.findByAccountId",PatientSleep.class);
			query.setParameter("accountId", accountId);
			query.setParameter("clientId", clientId);
			query.setParameter("clientDatabaseId", clientDbId);
			/*List<PatientSleep> list=query.getResultList();
			if(list!=null && list.size()>0)
			{
				patientSleep = query.getResultList().get(0);
			}*/
			patientSleep=query.getSingleResult();
			
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return patientSleep;
	}

	public PatientHeartRate getHeartRate(int clientId, int clientDbId,Integer accountId) throws BusinessException, SystemException {
		PatientHeartRate patientHeartRate = null;
		try {
			TypedQuery<PatientHeartRate> query = (TypedQuery<PatientHeartRate>) ehrEntityManager.createNamedQuery("PatientHeartRate.findByAccountId",PatientHeartRate.class);
			query.setParameter("accountId", accountId);
			query.setParameter("clientId", clientId);
			query.setParameter("clientDatabaseId", clientDbId);
			/*List<PatientHeartRate> list=query.getResultList();
			if(list!=null && list.size()>0)
			{
				patientHeartRate = query.getResultList().get(0);
			}*/
			patientHeartRate=query.getSingleResult();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return patientHeartRate;
	}

	public PatientHeartAge getHeartAge(int clientId, int clientDbId,Integer accountId)  throws BusinessException, SystemException{
		PatientHeartAge patientHeartAge = null;
		try {
			TypedQuery<PatientHeartAge> query = (TypedQuery<PatientHeartAge>) ehrEntityManager.createNamedQuery("PatientHeartAge.findByAccountId",PatientHeartAge.class);
			query.setParameter("accountId", accountId);
			query.setParameter("clientId", clientId);
			query.setParameter("clientDatabaseId", clientDbId);
			/*List<PatientHeartAge> list=query.getResultList();
			if(list!=null && list.size()>0)
			{
				patientHeartAge = query.getResultList().get(0);
			}*/
			patientHeartAge=query.getSingleResult();
			
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return patientHeartAge;
	}

	public AccountWeight getWeight(Integer accountId) throws BusinessException, SystemException{
		AccountWeight accountWeight = null;
		try {
			TypedQuery<AccountWeight> query = (TypedQuery<AccountWeight>) em.createNamedQuery("AccountWeight.findByMaxWeightId",AccountWeight.class);
			query.setParameter("accountId", accountId);			
			/*List<AccountWeight> list=query.getResultList();
			if(list!=null && list.size()>0)
			{
				accountWeight = query.getResultList().get(0);
			}*/
			accountWeight=query.getSingleResult();
			
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return accountWeight;
	}

	

	public AccountBloodPressureEngage getBloodPressureEngage(Integer accountId) {
		AccountBloodPressureEngage accountBloodPressureEngage = null;
		try {
			TypedQuery<AccountBloodPressureEngage> query = (TypedQuery<AccountBloodPressureEngage>) em.createNamedQuery("AccountBloodPressureEngage.findByMaxBloodpressuretId",AccountBloodPressureEngage.class);
			query.setParameter("accountId", new Account(accountId));			
			/*List<AccountBloodPressureEngage> list=query.getResultList();
			if(list!=null && list.size()>0)
			{
				accountBloodPressureEngage = query.getResultList().get(0);
			}*/
			accountBloodPressureEngage=query.getSingleResult();
			
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return accountBloodPressureEngage;
	}

	
	
	

	

	

	

	
	
	
	
}
