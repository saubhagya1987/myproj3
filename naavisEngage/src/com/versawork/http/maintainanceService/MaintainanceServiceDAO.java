package com.versawork.http.maintainanceService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.PatientAppointmentRequest;
import com.versawork.http.model.PatientUpcomingAppointment;

@Repository
public class MaintainanceServiceDAO {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(MaintainanceServiceDAO.class);
	// private static final Boolean isDebugEnabled = LOGGER.isDebugEnabled();

	// Injected database connection:
	@PersistenceContext(unitName = "versawork_DS")
	private EntityManager em;

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(PatientUpcomingAppointment patientUpcomingAppointments) {
		em.persist(patientUpcomingAppointments);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(PatientAppointmentRequest patientAppointmentRequest) {
		em.remove(patientAppointmentRequest);
	}

	/*
	 * @Transactional(propagation = Propagation.REQUIRED) public void
	 * remove(AccountToSecurityQuestion accountToSecurityQuestion) {
	 * em.remove(accountToSecurityQuestion); }
	 */

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Account account) {
		em.remove(account);
	}

	public List<PatientAppointmentRequest> getappointmentsBasedOnAccId(Integer accountId) throws BusinessException,
			SystemException {
		List<PatientAppointmentRequest> patientAppointmentRequest = null;
		try {
			TypedQuery<PatientAppointmentRequest> query = (TypedQuery<PatientAppointmentRequest>) em.createNamedQuery(
					"PatientAppointmentRequest.findByAccountIdHardDelete", PatientAppointmentRequest.class);
			query.setParameter("accountId", new Account(accountId));
			patientAppointmentRequest = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.details.found");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientAppointmentRequest;
	}

	public List<Account> getAccountByUnitNumber(String unitNumber) throws BusinessException, SystemException {
		List<Account> account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) em.createNamedQuery("Account.findByUnitNumber",
					Account.class);
			query.setParameter("medicalRecordNumber", unitNumber);
			account = query.getResultList();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.for.unitNumber");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return account;
	}

	/*
	 * public List<AccountToSecurityQuestion> getSecrtyQueBasedOnAccId(Integer
	 * accountId) throws BusinessException, SystemException {
	 * List<AccountToSecurityQuestion> patientAppointmentRequest = null; try {
	 * TypedQuery<AccountToSecurityQuestion> query =
	 * (TypedQuery<AccountToSecurityQuestion>)
	 * em.createNamedQuery("AccountToSecurityQuestion.findByAccountId"
	 * ,AccountToSecurityQuestion.class); query.setParameter("accountId",
	 * accountId); patientAppointmentRequest = query.getResultList(); } catch
	 * (NoResultException exp) { throw new
	 * BusinessException("no.acc.details.found"); } catch (Exception exception)
	 * { throw new SystemException(exception); } return
	 * patientAppointmentRequest; }
	 */

}
