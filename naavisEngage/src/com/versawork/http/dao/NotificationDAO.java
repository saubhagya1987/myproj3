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

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.AccountBloodPressureEngage;
import com.versawork.http.model.AccountBloodPressureEngageResponse;
import com.versawork.http.model.AccountBloodPressureManagement;
import com.versawork.http.model.AccountMedicationEngage;
import com.versawork.http.model.AccountMedicationManagementReminder;
import com.versawork.http.model.ActivityLog;
import com.versawork.http.model.ClientNaavisDatabases;
import com.versawork.http.model.PatientAppointmentRequest;
import com.versawork.http.model.PatientUpcomingAppointment;
import com.versawork.http.model.PatientVisit;
import com.versawork.http.utils.DateUtils;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class NotificationDAO {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(NotificationDAO.class);
	// private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	@PersistenceContext(unitName = "versawork_DS")
	private EntityManager em;

	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(ActivityLog activityLog) {
		em.persist(activityLog);
	}

	public List<AccountBloodPressureEngage> getAccountBloodPressureEngageByAccountId(Integer accountId, Date fromDate,
			Date toDate) throws BusinessException, SystemException {
		List<AccountBloodPressureEngage> accountBloodPressureEngage = null;
		try {
			TypedQuery<AccountBloodPressureEngage> query = (TypedQuery<AccountBloodPressureEngage>) em
					.createNamedQuery("AccountBloodPressureEngage.findByFromToDate", AccountBloodPressureEngage.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("fromDate", fromDate, TemporalType.DATE);
			query.setParameter("toDate", toDate, TemporalType.DATE);
			accountBloodPressureEngage = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountBloodPressureEngage;
	}

	public List<AccountMedicationEngage> getAccountMedicationEngageByCurrentDate_3(Integer reminderId,
			String todaysDate, String past3Days) throws BusinessException, SystemException {
		List<AccountMedicationEngage> accountMedicationEngage = null;
		try {
			TypedQuery<AccountMedicationEngage> query = (TypedQuery<AccountMedicationEngage>) em.createNamedQuery(
					"AccountMedicationEngage.findByAccountMedicationManagementRemIdCurrentDate",
					AccountMedicationEngage.class);
			query.setParameter("accountMedicationManagementReminderId", new AccountMedicationManagementReminder(
					reminderId));
			query.setParameter("todaysDate", todaysDate);
			query.setParameter("past3Days", past3Days);
			accountMedicationEngage = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountMedicationEngage;
	}

	public List<AccountMedicationEngage> getAccountMedicationEngageByCurrentDate_7(Integer reminderId,
			String todaysDate, String past3Days) throws BusinessException, SystemException {
		List<AccountMedicationEngage> accountMedicationEngage = null;
		try {
			TypedQuery<AccountMedicationEngage> query = (TypedQuery<AccountMedicationEngage>) em.createNamedQuery(
					"AccountMedicationEngage.findByAccountMedicationManagementRemIdCurrentDate",
					AccountMedicationEngage.class);
			query.setParameter("accountMedicationManagementReminderId", new AccountMedicationManagementReminder(
					reminderId));
			query.setParameter("todaysDate", todaysDate);
			query.setParameter("past3Days", past3Days);
			accountMedicationEngage = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountMedicationEngage;
	}

	public List<AccountMedicationEngage> getAccountMedicationEngageByCurrentDate_14(Integer reminderId,
			String todaysDate, String past3Days) throws BusinessException, SystemException {
		List<AccountMedicationEngage> accountMedicationEngage = null;
		try {
			TypedQuery<AccountMedicationEngage> query = (TypedQuery<AccountMedicationEngage>) em.createNamedQuery(
					"AccountMedicationEngage.findByAccountMedicationManagementRemIdCurrentDate",
					AccountMedicationEngage.class);
			query.setParameter("accountMedicationManagementReminderId", new AccountMedicationManagementReminder(
					reminderId));
			query.setParameter("todaysDate", todaysDate);
			query.setParameter("past3Days", past3Days);
			accountMedicationEngage = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountMedicationEngage;
	}

	public List<AccountMedicationManagementReminder> getAccountMedicationRemindersByActIdDelFlag(Integer accountId)
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
			return accountMedicationManagementReminder;
			// throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagementReminder;
	}

	public List<AccountMedicationEngage> getReminderResponseByActId(Integer accountId, Date fromDate, Date toDate)
			throws BusinessException, SystemException {
		List<AccountMedicationEngage> accountMedicationEngage = null;
		try {
			TypedQuery<AccountMedicationEngage> query = (TypedQuery<AccountMedicationEngage>) em.createNamedQuery(
					"AccountMedicationEngage.findByFromToDate", AccountMedicationEngage.class);
			query.setParameter("accountId", accountId);
			query.setParameter("fromDate", fromDate, TemporalType.DATE);
			query.setParameter("toDate", toDate, TemporalType.DATE);
			accountMedicationEngage = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationEngage;
	}

	/*
	 * public Account getApptsByAccountId(Integer accountId,Date fromDate,Date
	 * toDate) throws BusinessException, SystemException { Account account =
	 * null; try { TypedQuery<Account> query = (TypedQuery<Account>)
	 * em.createNamedQuery("Account.findByAccountId",Account.class);
	 * query.setParameter("accountId", accountId);
	 * query.setParameter("fromDate", fromDate, TemporalType.DATE);
	 * query.setParameter("toDate", toDate, TemporalType.DATE); account =
	 * query.getSingleResult(); } catch (NoResultException exp) { throw new
	 * BusinessException("no.acc.details.found"); } catch (Exception exception)
	 * { throw new SystemException(exception); } return account; }
	 */

	public List<PatientAppointmentRequest> getApptsByAccountIdSchdleDate(Integer accountId, Date fromDate, Date toDate)
			throws BusinessException, SystemException {
		List<PatientAppointmentRequest> patientAppointmentRequest = null;
		try {
			TypedQuery<PatientAppointmentRequest> query = (TypedQuery<PatientAppointmentRequest>) em.createNamedQuery(
					"PatientAppointmentRequest.findByFromToDateSchdle", PatientAppointmentRequest.class);
			query.setParameter("accountId", accountId);
			query.setParameter("fromDate", fromDate, TemporalType.DATE);
			query.setParameter("toDate", toDate, TemporalType.DATE);
			patientAppointmentRequest = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientAppointmentRequest;
	}

	@SuppressWarnings("unchecked")
	public List<PatientUpcomingAppointment> getConfApptsByAccountIdByConfDate(Integer accountId, Date fromDate,
			Date toDate, Integer clientDatabaseId) throws BusinessException, SystemException {
		List<PatientUpcomingAppointment> patientAppointmentRequest = null;
		try {
			/*TypedQuery<PatientUpcomingAppointment> query = (TypedQuery<PatientUpcomingAppointment>) ehrEntityManager.createNamedQuery("PatientUpcomingAppointment.findByAccountIdAndDate",PatientUpcomingAppointment.class);
			query.setParameter("accountId", accountId);
			query.setParameter("fromDate", fromDate, TemporalType.TIMESTAMP);
			query.setParameter("toDate", toDate, TemporalType.TIMESTAMP);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			query.setParameter("statusCode", new Integer(1));*/			
			Query query = ehrEntityManager
					.createNativeQuery(
							"SELECT * FROM patient_upcoming_appointment p where client_database_id='"
									+ clientDatabaseId
									+ "' and status_code='1' and account_id='"
									+ accountId
									+ "' and (convert(varchar(10), p.appointment_date,101) between CAST('"
									+ DateUtils.getFormatDate(fromDate, "MM-dd-yyyy hh:mm")
									+ "' AS datetime) and CAST('"
									+ DateUtils.getFormatDate(toDate, "MM-dd-yyyy hh:mm")
									+ "' AS datetime)) ORDER BY appointment_date DESC",
							PatientUpcomingAppointment.class);
			patientAppointmentRequest = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientAppointmentRequest;
	}

	public List<PatientAppointmentRequest> getApptsByAccountIdByConfDate(Integer accountId, Date fromDate, Date toDate)
			throws BusinessException, SystemException {
		List<PatientAppointmentRequest> patientAppointmentRequest = null;
		try {
			TypedQuery<PatientAppointmentRequest> query = (TypedQuery<PatientAppointmentRequest>) em.createNamedQuery(
					"PatientAppointmentRequest.findByFromToDateConfrm", PatientAppointmentRequest.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("fromDate", fromDate, TemporalType.DATE);
			query.setParameter("toDate", toDate, TemporalType.DATE);
			patientAppointmentRequest = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientAppointmentRequest;
	}

	public List<AccountBloodPressureManagement> getAccountBlodPresrRemindersByActIdDelFlag(Integer accountId)
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

	public List<AccountBloodPressureEngageResponse> getAccountBpEngageByCurrentDate_3(Integer bloodPresrReminderId,
			String todaysDate, String pastDays) throws BusinessException, SystemException {
		List<AccountBloodPressureEngageResponse> accountBloodPressureEngageResponse = null;
		try {
			TypedQuery<AccountBloodPressureEngageResponse> query = (TypedQuery<AccountBloodPressureEngageResponse>) em
					.createNamedQuery("AccountBloodPressureEngageResponse.findByAccountBpManagementIdCurrentDate",
							AccountBloodPressureEngageResponse.class);
			query.setParameter("accountBloodPressureManagementId", new AccountBloodPressureManagement(
					bloodPresrReminderId));
			query.setParameter("todaysDate", todaysDate);
			query.setParameter("pastDays", pastDays);
			accountBloodPressureEngageResponse = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountBloodPressureEngageResponse;
	}

	public List<AccountBloodPressureEngageResponse> getAccountBpEngageByCurrentDate_7(Integer bloodPresrReminderId,
			String todaysDate, String pastDays) throws BusinessException, SystemException {
		List<AccountBloodPressureEngageResponse> accountBloodPressureEngageResponse = null;
		try {
			TypedQuery<AccountBloodPressureEngageResponse> query = (TypedQuery<AccountBloodPressureEngageResponse>) em
					.createNamedQuery("AccountBloodPressureEngageResponse.findByAccountBpManagementIdCurrentDate",
							AccountBloodPressureEngageResponse.class);
			query.setParameter("accountBloodPressureManagementId", new AccountBloodPressureManagement(
					bloodPresrReminderId));
			query.setParameter("todaysDate", todaysDate);
			query.setParameter("pastDays", pastDays);
			accountBloodPressureEngageResponse = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountBloodPressureEngageResponse;
	}

	public List<AccountBloodPressureEngageResponse> getAccountBpEngageByCurrentDate_14(Integer bloodPresrReminderId,
			String todaysDate, String pastDays) throws BusinessException, SystemException {
		List<AccountBloodPressureEngageResponse> accountBloodPressureEngageResponse = null;
		try {
			TypedQuery<AccountBloodPressureEngageResponse> query = (TypedQuery<AccountBloodPressureEngageResponse>) em
					.createNamedQuery("AccountBloodPressureEngageResponse.findByAccountBpManagementIdCurrentDate",
							AccountBloodPressureEngageResponse.class);
			query.setParameter("accountBloodPressureManagementId", new AccountBloodPressureManagement(
					bloodPresrReminderId));
			query.setParameter("todaysDate", todaysDate);
			query.setParameter("pastDays", pastDays);
			accountBloodPressureEngageResponse = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return accountBloodPressureEngageResponse;
	}

	public Integer getApptsByAccountIdDateandReadFlag(Integer accountId, Date todaysDate, Date endDate)
			throws BusinessException, SystemException {
		List<PatientAppointmentRequest> patientAppointmentRequest = null;
		try {
			TypedQuery<PatientAppointmentRequest> query = (TypedQuery<PatientAppointmentRequest>) em.createNamedQuery(
					"PatientAppointmentRequest.findByAccountFromFlag", PatientAppointmentRequest.class);
			query.setParameter("accountId", accountId);
			query.setParameter("isRead", false);
			patientAppointmentRequest = query.getResultList();
		} catch (NoResultException exp) {
			return 0;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		if (patientAppointmentRequest != null && patientAppointmentRequest.size() != 0)
			return patientAppointmentRequest.size();
		else
			return 0;
	}

	public Integer getConfApptsCountByAccountIdByConfDateAndFlag(Integer accountId, Date startDate, Date endDate,
			int clientDatabaseId) throws BusinessException, SystemException {
		List<PatientUpcomingAppointment> patientAppointmentRequest = null;
		try {
			TypedQuery<PatientUpcomingAppointment> query = (TypedQuery<PatientUpcomingAppointment>) ehrEntityManager
					.createNamedQuery("PatientUpcomingAppointment.findByAccountIdAndFlag",
							PatientUpcomingAppointment.class);
			query.setParameter("accountId", accountId);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			query.setParameter("isRead", false);
			patientAppointmentRequest = query.getResultList();
		} catch (NoResultException exp) {
			return 0;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		if (patientAppointmentRequest != null && patientAppointmentRequest.size() != 0)
			return patientAppointmentRequest.size();
		else
			return 0;
	}

	public List<PatientUpcomingAppointment> getConfApptsForFuture(Integer accountId, Date fromDatodaysDate)
			throws BusinessException, SystemException {
		List<PatientUpcomingAppointment> patientAppointmentRequest = null;
		try {
			TypedQuery<PatientUpcomingAppointment> query = (TypedQuery<PatientUpcomingAppointment>) ehrEntityManager
					.createNamedQuery("PatientUpcomingAppointment.findByFromDateFuture",
							PatientUpcomingAppointment.class);
			query.setParameter("accountId", accountId);
			query.setParameter("fromDate", fromDatodaysDate, TemporalType.DATE);
			query.setParameter("statusCode", new Integer(1));
			patientAppointmentRequest = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientAppointmentRequest;
	}

	public List<PatientAppointmentRequest> getScheduledAppointmentsForFuture(Integer accountId, Date todaysDate)
			throws BusinessException, SystemException {
		List<PatientAppointmentRequest> patientAppointmentRequest = null;
		try {
			TypedQuery<PatientAppointmentRequest> query = (TypedQuery<PatientAppointmentRequest>) em.createNamedQuery(
					"PatientAppointmentRequest.findByFromDateFuture", PatientAppointmentRequest.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("fromDate", todaysDate);
			patientAppointmentRequest = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientAppointmentRequest;
	}

	public List<AccountBloodPressureManagement> getActiveBloodPressureNotification(Integer accountId, boolean isActive)
			throws BusinessException, SystemException {
		List<AccountBloodPressureManagement> accountBloodPressureManagement = null;
		try {
			TypedQuery<AccountBloodPressureManagement> query = (TypedQuery<AccountBloodPressureManagement>) em
					.createNamedQuery("AccountBloodPressureManagement.findByAccountIdIsActive",
							AccountBloodPressureManagement.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("isActive", isActive);
			accountBloodPressureManagement = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new SystemException(exception);
		}
		return accountBloodPressureManagement;
	}

	public List<AccountMedicationManagementReminder> getActiveMedicationNotification(Integer accountId, boolean isActive)
			throws BusinessException, SystemException {
		List<AccountMedicationManagementReminder> accountMedicationManagementReminder = null;
		try {
			TypedQuery<AccountMedicationManagementReminder> query = (TypedQuery<AccountMedicationManagementReminder>) em
					.createNamedQuery("AccountMedicationManagementReminder.findByAccountIdIsActive",
							AccountMedicationManagementReminder.class);
			query.setParameter("accountId", new Account(accountId));
			query.setParameter("isActive", isActive);
			accountMedicationManagementReminder = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountMedicationManagementReminder;
	}
	public List<PatientUpcomingAppointment> getPatientsAppointments(int accountId,int clientDatabaseId,Date todaysDate,Integer upcomingAppointmentSize)
			throws BusinessException, SystemException {
		List<PatientUpcomingAppointment> patientUpcomingAppointment = null;
		try {
			TypedQuery<PatientUpcomingAppointment> query = (TypedQuery<PatientUpcomingAppointment>) ehrEntityManager.createNamedQuery(
					"PatientUpcomingAppointment.findByAccountIdAndAppointmentDate", PatientUpcomingAppointment.class).setMaxResults(3).setFirstResult(upcomingAppointmentSize);
			query.setParameter("accountId", accountId);
			query.setParameter("clientDatabaseId", clientDatabaseId);	
			query.setParameter("todaysDate", todaysDate);			
			patientUpcomingAppointment = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientUpcomingAppointment;
	}
	
	public List<PatientUpcomingAppointment> loadMorePatientsAppointments(int accountId,int clientDatabaseId, Date todaysDate,Integer upcomingAppointmentSize)
			throws BusinessException, SystemException {
		List<PatientUpcomingAppointment> patientUpcomingAppointment = null;
		try {
			TypedQuery<PatientUpcomingAppointment> query = (TypedQuery<PatientUpcomingAppointment>) ehrEntityManager.createNamedQuery(
					"PatientUpcomingAppointment.findByAccountIdAndFromDate", PatientUpcomingAppointment.class).setMaxResults(3).setFirstResult(upcomingAppointmentSize);
			query.setParameter("accountId", accountId);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			query.setParameter("todaysDate", todaysDate);
			patientUpcomingAppointment = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientUpcomingAppointment;
	}
	public List<PatientUpcomingAppointment> getUpcomingVisitList(int clientDatabaseId,Date todaysDate, String providerName,int accountId )
			throws BusinessException, SystemException {
		List<PatientUpcomingAppointment> patientUpcomingAppointment = null;

		TypedQuery<PatientUpcomingAppointment> query = (TypedQuery<PatientUpcomingAppointment>) ehrEntityManager.createNamedQuery(
				"PatientUpcomingAppointment.findByProviderNameAndId", PatientUpcomingAppointment.class);		
		query.setParameter("providerName", "%" + providerName + "%");
		query.setParameter("accountId", accountId);
		query.setParameter("clientDatabaseId", clientDatabaseId);
		query.setParameter("todaysDate", todaysDate);
		patientUpcomingAppointment = query.getResultList();

		return patientUpcomingAppointment;
	}
	
	public List<PatientVisit> getPastVisitList(Date todaysDate, String providerName,int accountId )
			throws BusinessException, SystemException {
		List<PatientVisit> patientPastAppointment = null;

		TypedQuery<PatientVisit> query = (TypedQuery<PatientVisit>) ehrEntityManager.createNamedQuery(
				"PatientVisit.findByProviderNameAndPastDate", PatientVisit.class);		
		query.setParameter("providerName", "%" + providerName + "%");
		query.setParameter("accountId", accountId);
		//query.setParameter("clientDatabaseId", clientDatabaseId);
		query.setParameter("todaysDate", todaysDate);
		patientPastAppointment = query.getResultList();

		return patientPastAppointment;
	}

	public List<PatientVisit> getPastPatientsAppointments(int accountId)throws BusinessException, SystemException {
		List<PatientVisit> patientPastAppointment = null;
		try {
			TypedQuery<PatientVisit> query = (TypedQuery<PatientVisit>) ehrEntityManager.createNamedQuery(
					"PatientVisit.findByAccountIdAndAppointmentDate", PatientVisit.class);
			query.setParameter("accountId", accountId);
			//query.setParameter("clientDatabaseId", clientDatabaseId);	
			//query.setParameter("todaysDate", todaysDate);			
			patientPastAppointment = query.getResultList();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientPastAppointment;
	}
	
	
	
	
}
