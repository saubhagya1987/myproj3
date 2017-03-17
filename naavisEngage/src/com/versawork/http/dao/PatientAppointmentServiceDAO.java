package com.versawork.http.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.AccountNotificationHistory;
import com.versawork.http.model.NotificationType;
import com.versawork.http.model.PatientAppointmentRequest;
import com.versawork.http.model.PatientUpcomingAppointment;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class PatientAppointmentServiceDAO implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientAppointmentServiceDAO.class);

	/**
     * 
     */
	// PatientUpcomingAppointments.findByDateProcessed
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "versawork_DS", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	public List<PatientUpcomingAppointment> getNewConfirmedAppointments(Integer clientDatabaseId)
			throws BusinessException {

		List<PatientUpcomingAppointment> patientConfirmedAppointments = null;
		try {
			TypedQuery<PatientUpcomingAppointment> query = (TypedQuery<PatientUpcomingAppointment>) ehrEntityManager
					.createNamedQuery("PatientUpcomingAppointment.findByStatusUnprocessed",
							PatientUpcomingAppointment.class);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			patientConfirmedAppointments = query.getResultList();
		} catch (NoResultException exp) {
			patientConfirmedAppointments = null;
		} catch (Exception exp) {
			LOGGER.error(ExceptionUtils.getStackTrace(exp));
			throw new BusinessException("Exception occured during processing confirmed appointments thread at "
					+ new Date());
		}
		return patientConfirmedAppointments;
	}

	public void update(PatientAppointmentRequest patientAppointmentRequest) throws BusinessException, SystemException {
		try {
			entityManager.merge(patientAppointmentRequest);
		} catch (Exception exception) {
			LOGGER.info("Exception while updating appointment" + ExceptionUtils.getFullStackTrace(exception));
			throw new SystemException(exception.getMessage());
		}

	}

	public void savePatientAppointmentRequest(PatientAppointmentRequest patientAppointmentRequest)
			throws BusinessException, SystemException {
		// PatientAppointmentRequest PatientApptRequest = null;
		try {
			entityManager.persist(patientAppointmentRequest);

		} catch (Exception exception) {
			throw new SystemException(exception);
		}

	}

	public PatientAppointmentRequest getAppointmtIdForConfrmtn(Integer patientAppointmentRequestId)
			throws BusinessException, SystemException {
		PatientAppointmentRequest patientAppointmentRequest = null;
		try {
			TypedQuery<PatientAppointmentRequest> query = entityManager.createNamedQuery(
					"PatientAppointmentRequest.findByPatientAppointmentRequestId", PatientAppointmentRequest.class);

			query.setParameter("patientAppointmentRequestId", patientAppointmentRequestId);

			patientAppointmentRequest = query.getSingleResult();

		} catch (NoResultException noResultException) {
			LOGGER.error("No patient Appointment Request Id Found");
			throw new BusinessException("invalid.apptmnt.reqId");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return patientAppointmentRequest;
	}

	public PatientAppointmentRequest getAppointmtReqForConfrmtn(Integer patientAppointmentRequestId)
			throws BusinessException, SystemException {
		PatientAppointmentRequest patientAppointmentRequest = null;
		try {
			TypedQuery<PatientAppointmentRequest> query = entityManager.createNamedQuery(
					"PatientAppointmentRequest.findByPatientAppointmentRequestId", PatientAppointmentRequest.class);

			query.setParameter("patientAppointmentRequestId", patientAppointmentRequestId);

			patientAppointmentRequest = query.getSingleResult();

		} catch (NoResultException noResultException) {
			LOGGER.error("No patient Appointment Request Id Found");
			throw new BusinessException("invalid.apptmnt.reqId");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return patientAppointmentRequest;
	}

	@Transactional("ehr_txn")
	public void savePatientUpcomingAppointments(PatientUpcomingAppointment patientUpcomingAppointments)
			throws BusinessException, SystemException {
		try {
			ehrEntityManager.merge(patientUpcomingAppointments);
		} catch (Exception exception) {
			LOGGER.info("Exception while updating appointment" + ExceptionUtils.getFullStackTrace(exception));
			throw new SystemException(exception.getMessage());
		}

	}

	public PatientUpcomingAppointment getConfirmedAppointmentByAppointmentId(Integer appointmentId, Integer accountId)
			throws BusinessException, SystemException {
		PatientUpcomingAppointment patientUpcomingAppointment = null;
		try {
			TypedQuery<PatientUpcomingAppointment> query = ehrEntityManager.createNamedQuery(
					"PatientUpcomingAppointment.findByAppointmentId", PatientUpcomingAppointment.class);

			query.setParameter("appointmentId", appointmentId);
			query.setParameter("accountId", accountId);

			patientUpcomingAppointment = query.getSingleResult();

		} catch (NoResultException noResultException) {
			LOGGER.error("No patient Appointment Request Id Found");
			throw new BusinessException("invalid.apptmnt.reqId");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

		return patientUpcomingAppointment;
	}

	@Transactional("ehr_txn")
	public void updateUpcomingAppointment(PatientUpcomingAppointment patientUpcomingAppointment) throws SystemException {
		try {
			ehrEntityManager.merge(patientUpcomingAppointment);
		} catch (Exception exception) {
			LOGGER.info("Exception while updating appointment" + ExceptionUtils.getFullStackTrace(exception));
			throw new SystemException(exception.getMessage());
		}
	}

	public List<AccountNotificationHistory> getNotificationHistory(Integer clientDatabaseId, Date presentDay,
			Date currentPlusTwo, Integer notificationTypeId) throws BusinessException {

		List<AccountNotificationHistory> AccountNotificationHistoryList = null;
		try {
			TypedQuery<AccountNotificationHistory> query = (TypedQuery<AccountNotificationHistory>) entityManager
					.createNamedQuery("AccountNotificationHistory.findByClientDatabaseIdIsviewedSchedDate",
							AccountNotificationHistory.class);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			query.setParameter("presentDay", presentDay, TemporalType.DATE);
			query.setParameter("currentPlusTwo", currentPlusTwo, TemporalType.DATE);
			query.setParameter("notificationTypeId", new NotificationType(notificationTypeId));

			AccountNotificationHistoryList = query.getResultList();
		} catch (NoResultException exp) {
			AccountNotificationHistoryList = null;
		} catch (Exception exp) {
			throw new BusinessException("Exception occured during processing of push notification thread at "
					+ new Date(), exp);
		}
		return AccountNotificationHistoryList;
	}

	public PatientUpcomingAppointment getConfirmedAppointmentsById(Integer appointmentId, Integer accountId)
			throws BusinessException {
		LOGGER.debug("getConfirmedAppointmentsById : appointmentId :" + appointmentId);
		LOGGER.debug("getConfirmedAppointmentsById : accountId :" + accountId);
		PatientUpcomingAppointment patientConfirmedAppointments = null;
		try {
			TypedQuery<PatientUpcomingAppointment> query = (TypedQuery<PatientUpcomingAppointment>) ehrEntityManager
					.createNamedQuery("PatientUpcomingAppointment.findByAppointmentId",
							PatientUpcomingAppointment.class);
			query.setParameter("appointmentId", appointmentId);
			query.setParameter("accountId", accountId);
			patientConfirmedAppointments = query.getSingleResult();
		} catch (NoResultException exp) {
			patientConfirmedAppointments = null;
		} catch (Exception exp) {
			throw new BusinessException("Exception occured during processing confirmed appointments thread at "
					+ new Date(), exp);
		}
		return patientConfirmedAppointments;
	}

	public AccountNotificationHistory getNotificationHistryByAppointmentId(Integer moduleId, Integer accountId)
			throws BusinessException, SystemException {
		LOGGER.debug("In getNotificationHistryByAppointmentId : moduleId :" + moduleId + " accountId :" + accountId);
		AccountNotificationHistory accountNotificationHistory = null;
		try {
			TypedQuery<AccountNotificationHistory> query = entityManager.createNamedQuery(
					"AccountNotificationHistory.findByAccountIdEffectiveModuleId", AccountNotificationHistory.class);

			query.setParameter("effectiveModuleId", moduleId);
			query.setParameter("accountId", accountId);

			accountNotificationHistory = query.getSingleResult();

		} catch (NoResultException noResultException) {
			LOGGER.debug("No module Id Found");
			throw new BusinessException("invalid.apptmnt.reqId");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountNotificationHistory;
	}

	public void updateNotificationHistory(AccountNotificationHistory accountNotificationHistory) throws SystemException {
		try {
			entityManager.merge(accountNotificationHistory);
		} catch (Exception exception) {
			LOGGER.info("Exception while updating appointment" + ExceptionUtils.getFullStackTrace(exception));
			throw new SystemException(exception.getMessage());
		}
	}
}
