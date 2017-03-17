package com.versawork.http.dao;

import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.AccountMedicationEngage;
import com.versawork.http.model.AccountMedicationManagement;
import com.versawork.http.model.AccountMedicationManagementReminder;
import com.versawork.http.model.AccountMedicationManagementSchedule;
import com.versawork.http.model.AccountReminderMedicationRelation;
import com.versawork.http.model.FeedInfo;
import com.versawork.http.model.Frequency;
import com.versawork.http.model.MedicationDosage;
import com.versawork.http.model.MedicationKind;
import com.versawork.http.model.MedicationMethod;
import com.versawork.http.service.impl.BloodPressureServiceImpl;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class MedicationMgntDAO {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(MedicationMgntDAO.class);
	// private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();
	final static Logger LOGGER = LoggerFactory.getLogger(MedicationMgntDAO.class);
	// Injected database connection:
	@PersistenceContext(unitName = "versawork_DS")
	private EntityManager em;
	
	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(AccountMedicationEngage accountMedicationEngage) {
		try {
			em.merge(accountMedicationEngage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(AccountMedicationManagementReminder accountMedicationManagementReminder) {
		try{
			em.persist(accountMedicationManagementReminder);
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(AccountMedicationManagement accountMedicationManagement) {
		try{
		em.persist(accountMedicationManagement);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(AccountMedicationManagementSchedule accountMedicationManagementSchedule) {
		try{
		em.persist(accountMedicationManagementSchedule);
		//em.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AccountMedicationManagementReminder accountMedicationManagementReminder) {
		try{
		em.merge(accountMedicationManagementReminder);
		}
		catch (Exception e) {
				e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(AccountMedicationManagementReminder accountMedicationManagementReminder) {
		em.remove(accountMedicationManagementReminder);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AccountMedicationManagement accountMedicationManagement) {
		try {
			em.merge(accountMedicationManagement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AccountMedicationManagementSchedule accountMedicationManagementSchedule) {
		try {
		em.merge(accountMedicationManagementSchedule);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(AccountMedicationManagementSchedule accountMedicationManagementSchedule) {
		try {
		em.remove(accountMedicationManagementSchedule);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(AccountReminderMedicationRelation accountReminderMedicationRelation) {
		em.remove(accountReminderMedicationRelation);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AccountReminderMedicationRelation accountReminderMedicationRelation) {
		try{
		em.merge(accountReminderMedicationRelation);
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(AccountMedicationManagement accountMedicationManagement) {
		em.remove(accountMedicationManagement);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeall(List<AccountMedicationManagement> medicationManagmntInfoListFromDB) {
		em.remove(medicationManagmntInfoListFromDB);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(AccountMedicationEngage accountMedication) {
		em.remove(accountMedication);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void flush() {
		em.flush();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(AccountReminderMedicationRelation accountReminderMedicationRelation) {
		em.merge(accountReminderMedicationRelation);
		// em.flush();
	}

	public AccountMedicationManagementReminder getReminderById(Integer accountMedicationManagementReminderId)
			throws BusinessException, SystemException {
		AccountMedicationManagementReminder accountMedicationManagementReminder = null;
		try {
			TypedQuery<AccountMedicationManagementReminder> query = (TypedQuery<AccountMedicationManagementReminder>) em
					.createNamedQuery(
							"AccountMedicationManagementReminder.findByAccountMedicationManagementReminderId",
							AccountMedicationManagementReminder.class);
			query.setParameter("accountMedicationManagementReminderId", accountMedicationManagementReminderId);
			accountMedicationManagementReminder = query.getSingleResult();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagementReminder;
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

	public List<AccountMedicationManagement> getMedicationByActId(Integer accountId) throws BusinessException,
			SystemException {
		List<AccountMedicationManagement> accountMedicationManagement = null;
		try {
			TypedQuery<AccountMedicationManagement> query = (TypedQuery<AccountMedicationManagement>) em
					.createNamedQuery("AccountMedicationManagement.findByAccountId", AccountMedicationManagement.class);
			query.setParameter("accountId", new Account(accountId));
			accountMedicationManagement = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagement;
	}

	public List<AccountMedicationEngage> getReminderResponseByRemId(Integer accountMedicationManagementReminderId)
			throws BusinessException, SystemException {
		List<AccountMedicationEngage> accountMedicationEngage = null;
		try {
			TypedQuery<AccountMedicationEngage> query = (TypedQuery<AccountMedicationEngage>) em.createNamedQuery(
					"AccountMedicationEngage.findByAccountMedicationManagementReminderId",
					AccountMedicationEngage.class);
			query.setParameter("accountMedicationManagementReminderId", new AccountMedicationManagementReminder(
					accountMedicationManagementReminderId));
			accountMedicationEngage = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.reminder.id");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationEngage;
	}

	public AccountMedicationEngage getReminderResponseByEngageId(Integer accountMedicationEngageId)
			throws BusinessException, SystemException {
		AccountMedicationEngage accountMedicationEngage = null;
		try {
			TypedQuery<AccountMedicationEngage> query = (TypedQuery<AccountMedicationEngage>) em.createNamedQuery(
					"AccountMedicationEngage.findByAccountMedicationEngageId", AccountMedicationEngage.class);
			query.setParameter("accountMedicationEngageId", accountMedicationEngageId);
			accountMedicationEngage = query.getSingleResult();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationEngage;
	}

	public List<AccountMedicationEngage> getReminderResponseByActId(Integer accountId) throws BusinessException,
			SystemException {
		List<AccountMedicationEngage> accountMedicationEngage = null;
		try {
			TypedQuery<AccountMedicationEngage> query = (TypedQuery<AccountMedicationEngage>) em.createNamedQuery(
					"AccountMedicationEngage.findByAccountId", AccountMedicationEngage.class);
			query.setParameter("accountId", accountId);
			accountMedicationEngage = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationEngage;
	}

	public List<AccountMedicationEngage> getReminderResponseCountByActId(Integer accountId) throws BusinessException,
			SystemException {
		List<AccountMedicationEngage> accountMedicationEngage = null;
		try {
			TypedQuery<AccountMedicationEngage> query = (TypedQuery<AccountMedicationEngage>) em.createNamedQuery(
					"AccountMedicationEngage.findByAccountIdTodayDate", AccountMedicationEngage.class);
			query.setParameter("accountId", accountId);
			accountMedicationEngage = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationEngage;
	}

	public List<AccountMedicationManagementReminder> getReminderByActIdDeleteFlag(Integer accountId)
			throws BusinessException, SystemException {
		List<AccountMedicationManagementReminder> accountMedicationManagementReminder = null;
		try {
			TypedQuery<AccountMedicationManagementReminder> query = (TypedQuery<AccountMedicationManagementReminder>) em
					.createNamedQuery("AccountMedicationManagementReminder.findByAccountIdDeleteFlag",
							AccountMedicationManagementReminder.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("deleteFlag", "false");
			accountMedicationManagementReminder = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagementReminder;
	}

	public AccountMedicationManagementReminder getDetailsByRemIdActId(Integer accountMedicationManagementReminderId,
			Integer accountId) throws BusinessException, SystemException {
		AccountMedicationManagementReminder accountMedicationManagementReminder = null;
		try {
			TypedQuery<AccountMedicationManagementReminder> query = (TypedQuery<AccountMedicationManagementReminder>) em
					.createNamedQuery(
							"AccountMedicationManagementReminder.findByAccountMedicationManagementReminderIdActId",
							AccountMedicationManagementReminder.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("accountMedicationManagementReminderId", accountMedicationManagementReminderId);
			accountMedicationManagementReminder = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.reminder.id.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagementReminder;
	}

	public AccountMedicationManagement getDetailsByActIdMedId(Integer accountMedicationManagementId, Integer accountId)
			throws BusinessException, SystemException {
		AccountMedicationManagement accountMedicationManagement = null;
		try {
			TypedQuery<AccountMedicationManagement> query = (TypedQuery<AccountMedicationManagement>) em
					.createNamedQuery("AccountMedicationManagement.findByAccountMedicationManagementIdActId",
							AccountMedicationManagement.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("accountMedicationManagementId", accountMedicationManagementId);
			accountMedicationManagement = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.medication.id.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagement;
	}

	public Frequency getFrequencyListByLangCodeFreqType(int type, String locale) throws BusinessException,
			SystemException {
		Frequency frequency = null;
		LOGGER.info("Frequency Type in getFrequencyListByLangCodeFreqType    :" + type);
		TypedQuery<Frequency> query = (TypedQuery<Frequency>) em.createNamedQuery("Frequency.findByLangCodeType",
				Frequency.class);
		query.setParameter("langCode", locale);
		query.setParameter("type", type);
		frequency = query.getSingleResult();

		return frequency;
	}

	public List<Frequency> getFrequencyListByLangCode(String locale) throws BusinessException, SystemException {
		List<Frequency> frequencyList = null;
		try {
			TypedQuery<Frequency> query = (TypedQuery<Frequency>) em.createNamedQuery("Frequency.findByLangCode",
					Frequency.class);
			query.setParameter("langCode", locale);
			frequencyList = query.getResultList();
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return frequencyList;
	}

	public List<MedicationMethod> getMedicationMethodList() throws BusinessException, SystemException {
		List<MedicationMethod> MedicationMethodList = null;
		try {
			TypedQuery<MedicationMethod> query = (TypedQuery<MedicationMethod>) em.createNamedQuery(
					"MedicationMethod.findAll", MedicationMethod.class);
			MedicationMethodList = query.getResultList();
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return MedicationMethodList;
	}

	public List<MedicationKind> getMedicationKindList() throws BusinessException, SystemException {
		List<MedicationKind> medicationKindList = null;
		try {
			TypedQuery<MedicationKind> query = (TypedQuery<MedicationKind>) em.createNamedQuery(
					"MedicationKind.findAll", MedicationKind.class);
			medicationKindList = query.getResultList();
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return medicationKindList;
	}

	public List<MedicationDosage> getMedicationDosageList() throws BusinessException, SystemException {
		List<MedicationDosage> MedicationDosageList = null;
		try {
			TypedQuery<MedicationDosage> query = (TypedQuery<MedicationDosage>) em.createNamedQuery(
					"MedicationDosage.findAll", MedicationDosage.class);
			MedicationDosageList = query.getResultList();
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return MedicationDosageList;
	}

	public Boolean getMedicationByMedicationName(String medicationName, int accountId) throws BusinessException,
			SystemException {
		List<AccountMedicationManagement> accountMedicationManagement = null;
		try {
			TypedQuery<AccountMedicationManagement> query = (TypedQuery<AccountMedicationManagement>) em
					.createNamedQuery("AccountMedicationManagement.findByMedicationNameActId",
							AccountMedicationManagement.class);
			query.setParameter("medicationName", medicationName);
			query.setParameter("accountId", new Account(accountId));
			accountMedicationManagement = query.getResultList();
		} catch (NoResultException exp) {
			return false;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new SystemException(exception);
		}
		if (accountMedicationManagement.size() != 0) {
			return true;
		}
		return false;
	}

	public AccountMedicationManagementReminder getDetailsByRemId(Integer accountMedicationManagementReminderId)
			throws BusinessException, SystemException {
		AccountMedicationManagementReminder accountMedicationManagementReminder = null;
		try {
			TypedQuery<AccountMedicationManagementReminder> query = (TypedQuery<AccountMedicationManagementReminder>) em
					.createNamedQuery(
							"AccountMedicationManagementReminder.findByAccountMedicationManagementReminderId",
							AccountMedicationManagementReminder.class);
			query.setParameter("accountMedicationManagementReminderId", accountMedicationManagementReminderId);
			accountMedicationManagementReminder = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.reminder.id.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagementReminder;
	}

	public AccountMedicationManagement getMedicationByAccountMedicationManagementId(NsRequest nsRequest)
			throws BusinessException, SystemException {
		AccountMedicationManagement accountMedicationManagement = null;
		try {
			TypedQuery<AccountMedicationManagement> query = (TypedQuery<AccountMedicationManagement>) em
					.createNamedQuery("AccountMedicationManagement.findByAccountMedicationManagementId",
							AccountMedicationManagement.class);
			query.setParameter("accountMedicationManagementId", nsRequest.getMedicationManagmntInfo()
					.getMedictnMgmtId());
			accountMedicationManagement = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagement;
	}

	public AccountMedicationEngage getReminderResponseByRecordIdentifier(Integer accountId, Integer medictnReminderId,
			String recordIdentifier) throws SystemException {
		AccountMedicationEngage accountMedicationEngage = null;
		try {
			TypedQuery<AccountMedicationEngage> query = (TypedQuery<AccountMedicationEngage>) em.createNamedQuery(
					"AccountMedicationEngage.findByRecordIdentifier", AccountMedicationEngage.class);
			query.setParameter("accountId", accountId);
			query.setParameter("accountMedicationManagementReminderId", new AccountMedicationManagementReminder(
					medictnReminderId));
			query.setParameter("recordIdentifier", recordIdentifier);
			accountMedicationEngage = query.getSingleResult();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationEngage;
	}

	public List<AccountMedicationManagementReminder> restoreReminderByActIdDeleteFlag(Integer accountId, Date todaysDate)
			throws BusinessException, SystemException {
		List<AccountMedicationManagementReminder> accountMedicationManagementReminder = null;
		try {
			TypedQuery<AccountMedicationManagementReminder> query = (TypedQuery<AccountMedicationManagementReminder>) em
					.createNamedQuery("AccountMedicationManagementReminder.findByAccountIdDeleteFlagEndDate",
							AccountMedicationManagementReminder.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("deleteFlag", "false");
			query.setParameter("todaysDate", todaysDate);
			accountMedicationManagementReminder = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagementReminder;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void createMedication(AccountMedicationManagement accountMedicationManagement) {
		
		try {
			em.merge(accountMedicationManagement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AccountReminderMedicationRelation getMedicationRelation(Integer accountMedicationManagementId) throws BusinessException, SystemException{
		AccountReminderMedicationRelation accountReminderMedicationRelation = null;
		try {
			TypedQuery<AccountReminderMedicationRelation> query = (TypedQuery<AccountReminderMedicationRelation>) em.createNamedQuery("AccountReminderMedicationRelation.findByMedicationManagementId",
					AccountReminderMedicationRelation.class);
			query.setParameter("accountMedicationManagementId",  new AccountMedicationManagement(accountMedicationManagementId));
			accountReminderMedicationRelation = query.getSingleResult();
		} catch (NoResultException exp) {
			
		} catch (Exception exception) {
			
		}
		return accountReminderMedicationRelation;
	}

	public AccountMedicationManagementReminder getReminderByReminderId(Integer accountMedicationManagementReminderId)throws BusinessException, SystemException {
		AccountMedicationManagementReminder accountMedicationManagementReminder = null;
		try {
			TypedQuery<AccountMedicationManagementReminder> query = (TypedQuery<AccountMedicationManagementReminder>) em.createNamedQuery(
							"AccountMedicationManagementReminder.findByAccountMedicationManagementReminderId",
							AccountMedicationManagementReminder.class);
			query.setParameter("accountMedicationManagementReminderId", accountMedicationManagementReminderId);
			accountMedicationManagementReminder = query.getSingleResult();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			return null;
		}
		return accountMedicationManagementReminder;
	}

	public List<AccountMedicationManagementSchedule> getMedicationSchedule(Integer accountMedicationManagementReminderId)throws BusinessException, SystemException {
		List<AccountMedicationManagementSchedule> accountMedicationManagementSchedule = null;
		try {
			TypedQuery<AccountMedicationManagementSchedule> query = (TypedQuery<AccountMedicationManagementSchedule>) em.createNamedQuery
					("AccountMedicationManagementSchedule.findByAccountMedicationReminderId",AccountMedicationManagementSchedule.class);
			query.setParameter("accountMedicationManagementReminderId", accountMedicationManagementReminderId);			
			accountMedicationManagementSchedule = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagementSchedule;
	}

	public List<AccountMedicationManagementSchedule> getMedicationManagementScheduleList(Integer accountMedicationManagementReminderId)throws BusinessException, SystemException  {
		List<AccountMedicationManagementSchedule> accountMedicationManagementSchedule = null;
		try {
			TypedQuery<AccountMedicationManagementSchedule> query = (TypedQuery<AccountMedicationManagementSchedule>) em.createNamedQuery
					("AccountMedicationManagementSchedule.findByAccountMedicationReminderId",AccountMedicationManagementSchedule.class);
			query.setParameter("accountMedicationManagementReminderId", new AccountMedicationManagementReminder(accountMedicationManagementReminderId));			
			accountMedicationManagementSchedule = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagementSchedule;
	}
	public List<AccountMedicationManagement> getMedicationRefillDateList(Integer accountId)throws BusinessException, SystemException  {
		List<AccountMedicationManagement> accountMedicationManagementSchedule = null;
		try {
			TypedQuery<AccountMedicationManagement> query = (TypedQuery<AccountMedicationManagement>) em.createNamedQuery
					("AccountMedicationManagement.findByAccountId",AccountMedicationManagement.class);
			query.setParameter("accountId", new Account(accountId));			
			accountMedicationManagementSchedule = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagementSchedule;
	}

	public AccountMedicationManagement getAccountMedicationId(NsRequest nsRequest) throws BusinessException, SystemException {
		AccountMedicationManagement accountMedicationManagement = null;
		try {
			TypedQuery<AccountMedicationManagement> query = (TypedQuery<AccountMedicationManagement>) em
					.createNamedQuery("AccountMedicationManagement.findByAccountMedicationManagementId",
							AccountMedicationManagement.class);
			query.setParameter("accountMedicationManagementId", nsRequest.getMedicationManagmntInfo()
					.getMedictnMgmtId());
			accountMedicationManagement = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagement;
	}
	@Transactional("ehr_txn")
	public void persist(FeedInfo feedInfo) {
		try{
			ehrEntityManager.persist(feedInfo);
			ehrEntityManager.flush();
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	@Transactional("ehr_txn")
	public void update(FeedInfo feedInfo) {
		try{
			ehrEntityManager.merge(feedInfo);
			ehrEntityManager.flush();
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	@Transactional("ehr_txn")
	public void remove(FeedInfo feedInfo) {
		try{
			ehrEntityManager.remove(feedInfo);
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
	}
		
	
}
