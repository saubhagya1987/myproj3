package com.versawork.http.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.ejb.criteria.CriteriaBuilderImpl;
import org.hibernate.engine.IdentifierValue;
import org.hibernate.tuple.IdentifierProperty;
import org.springframework.stereotype.Repository;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.TestResultInfo;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.AccountNotificationHistory;
import com.versawork.http.model.AccountRole;
import com.versawork.http.model.FeedInfo;
import com.versawork.http.model.PatientAllergy;
import com.versawork.http.model.PatientImaging;
import com.versawork.http.model.PatientImmunization;
import com.versawork.http.model.PatientLab;
import com.versawork.http.model.PatientProblem;
import com.versawork.http.model.PatientVisit;
import com.versawork.http.utils.DateUtils;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class AccountServiceDAO implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "versawork_DS", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	public void save(Account account) throws BusinessException, SystemException {
		entityManager.persist(account);
	}

	/*
	 * public void saves(Account account) throws BusinessException,
	 * SystemException { try { List<PatientUpcomingAppointment>
	 * patientUpcomingAppointmentList =new
	 * ArrayList<PatientUpcomingAppointment>();
	 * account.setPatientUpcomingAppointmentList
	 * (patientUpcomingAppointmentList);
	 * 
	 * List<PatientAllergy> patientAllergyList = new
	 * ArrayList<PatientAllergy>();
	 * account.setPatientAllergyList(patientAllergyList);
	 * 
	 * List<AccountMedicationManagementReminder>
	 * accountMedicationManagementReminderList = new
	 * ArrayList<AccountMedicationManagementReminder>();
	 * account.setAccountMedicationManagementReminderList
	 * (accountMedicationManagementReminderList);
	 * 
	 * List<PatientProblem> patientProblemList = new
	 * ArrayList<PatientProblem>();
	 * account.setPatientProblemList(patientProblemList);
	 * 
	 * List<PatientPrescription> patientPrescriptionList = new
	 * ArrayList<PatientPrescription>();
	 * account.setPatientPrescriptionList(patientPrescriptionList);
	 * 
	 * CoreMeasurePatientDataVdt2 coreMeasurePatientDataVdt2 = new
	 * CoreMeasurePatientDataVdt2();
	 * account.setCoreMeasurePatientDataVdt2(coreMeasurePatientDataVdt2);
	 * 
	 * List<AccountMedicationManagement> accountMedicationManagementList =new
	 * ArrayList<AccountMedicationManagement>();
	 * account.setAccountMedicationManagementList
	 * (accountMedicationManagementList);
	 * 
	 * List<PatientLab> patientLabList = new ArrayList<PatientLab>();
	 * account.setPatientLabList(patientLabList);
	 * 
	 * List<AccountBloodPressureEngage> accountBloodPressureEngageList= new
	 * ArrayList<AccountBloodPressureEngage>();
	 * account.setAccountBloodPressureEngageList
	 * (accountBloodPressureEngageList);
	 * 
	 * List<PatientAppointmentRequest> patientAppointmentRequestList =new
	 * ArrayList<PatientAppointmentRequest>();
	 * account.setPatientAppointmentRequestList(patientAppointmentRequestList);
	 * 
	 * List<AccountBloodPressureManagement> accountBloodPressureManagementList =
	 * new ArrayList<AccountBloodPressureManagement>();
	 * account.setAccountBloodPressureManagementList
	 * (accountBloodPressureManagementList);
	 * 
	 * List<PatientVisit> patientVisitList= new ArrayList<PatientVisit>();
	 * account.setPatientVisitList(patientVisitList);
	 * entityManager.persist(account);
	 * 
	 * } catch (Exception exception) { throw new SystemException(exception); } }
	 */

	public void updateHospitalProvider(Account account) throws BusinessException, SystemException {
		try {
			javax.persistence.Query query = entityManager
					.createQuery("UPDATE healthcare_provider SET account_id =:account_id WHERE first_name=:first_name AND last_name=:last_name AND contact_email=:contact_email");
			query.setParameter("account_id", account);
			query.setParameter("first_name", account.getFirstName());
			query.setParameter("last_name", account.getLastName());
			query.setParameter("contact_email", account.getEmail());
			query.executeUpdate();
		} catch (Exception exception) {
			throw new SystemException(exception.getMessage());
		}

	}

	public void update(Account account) throws BusinessException, SystemException {
		entityManager.merge(account);
	}

	public IdentifierValue setUnsavedValue(IdentifierProperty ip, IdentifierValue newUnsavedValue) throws Throwable {
		IdentifierValue backup = ip.getUnsavedValue();
		Field f = ip.getClass().getDeclaredField("unsavedValue");
		f.setAccessible(true);
		f.set(ip, newUnsavedValue);
		return backup;
	}

	public Account verifyAccountByUnitNum(String unitNumber, Integer clientDatabaseId) throws BusinessException,
			SystemException {
		Account account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) entityManager.createNamedQuery(
					"Account.findByMedicalRecordNumberClientDbId", Account.class);
			query.setParameter("medicalRecordNumber", unitNumber);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			account = query.getSingleResult();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return account;
	}

	public AccountRole getAcountRole(String roleName) throws BusinessException, SystemException {
		AccountRole accountRole = null;
		try {
			TypedQuery<AccountRole> query = (TypedQuery<AccountRole>) entityManager.createNamedQuery(
					"AccountRole.findByRoleName", AccountRole.class);
			query.setParameter("roleName", roleName);
			accountRole = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.role");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return accountRole;
	}

	public Account getAccountByAuthToken(String authToken) throws BusinessException, SystemException {
		Account account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) entityManager.createNamedQuery("Account.findByAuthToken",
					Account.class);
			query.setParameter("authToken", authToken);
			account = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.for.auth.tok");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return account;
	}

	public Account getAccountById(Integer accountId) throws BusinessException, SystemException {
		Account account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) entityManager.createNamedQuery("Account.findByAccountId",
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

	public Account getAccountByIdSpcl(Integer accountId) throws BusinessException, SystemException {
		Account account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) entityManager.createNamedQuery("Account.findByAccountId",
					Account.class);
			query.setParameter("accountId", accountId);
			account = query.getSingleResult();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return account;
	}

	public Account getAccountByUnitNum(String unitNumber, Integer clientDatabaseId) throws BusinessException,
			SystemException {
		Account account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) entityManager.createNamedQuery(
					"Account.findByMedicalRecordNumberClientDbId", Account.class);
			query.setParameter("medicalRecordNumber", unitNumber);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			account = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.for.unitNumber");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return account;
	}

	public Account getAccountByUnitNumberCliId(String unitNumber, Integer clientDatabaseId) throws BusinessException,
			SystemException {
		Account account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) entityManager.createNamedQuery(
					"Account.findByMedicalRecordNumberClientDbId", Account.class);
			query.setParameter("medicalRecordNumber", unitNumber);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			account = query.getSingleResult();
		} catch (NoResultException exp) {
			account = null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return account;
	}

	public Account getAccountByUnitNumber(String unitNumber) throws BusinessException, SystemException {
		Account account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) entityManager.createNamedQuery(
					"Account.findByMedicalRecordNumber", Account.class);
			query.setParameter("medicalRecordNumber", unitNumber);
			account = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.for.unitNumber");
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return account;
	}

	public Account getAccountByUnitNumAndPhoneNum(String unitNumber, String phoneNumber) throws BusinessException,
			SystemException {
		Account account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) entityManager.createNamedQuery(
					"Account.findByMedicalRecordNumberAndMobilePhoneNumber", Account.class);
			query.setParameter("medicalRecordNumber", unitNumber);
			query.setParameter("mobilePhoneNumber", phoneNumber);
			account = query.getSingleResult();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return account;
	}

	/*
	 * public Account getAccountByAccountEmailId(String email) throws
	 * BusinessException, SystemException { Account account = null; try {
	 * TypedQuery<Account> query = (TypedQuery<Account>)
	 * entityManager.createNamedQuery("Account.findByEmail", Account.class);
	 * query.setParameter("email", email); account = query.getSingleResult(); }
	 * catch (NoResultException exp) { throw new
	 * BusinessException("no.acc.for.acc.email");
	 * }catch(NonUniqueResultException exp) { throw new
	 * BusinessException("mul.acc.for.acc.email"); } catch (Exception exception)
	 * { exception.printStackTrace(); throw new SystemException(exception); }
	 * return account; }
	 */
	public Account getAccountByAccountEmailClientDBID(String email, Integer clientDatabaseId) throws BusinessException,
			SystemException {
		Account account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) entityManager.createNamedQuery(
					"Account.findByMailAndClientDbId", Account.class);
			query.setParameter("email", email);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			account = query.getSingleResult();
		} catch (NoResultException exp) {
			throw new BusinessException("no.acc.for.acc.email");
		} catch (NonUniqueResultException exp) {
			throw new BusinessException("mul.acc.for.acc.email");
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new SystemException(exception);
		}
		return account;
	}

	public Account getAccountByEndpointId(String endpointUserId, Integer clientDatabaseId) throws SystemException,
			BusinessException {
		Account account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) entityManager.createNamedQuery(
					"Account.findByEndpointUserId", Account.class);
			query.setParameter("endpointUserId", endpointUserId);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			account = query.getSingleResult();
		} catch (NoResultException exp) {
			// throw new BusinessException("invalid.endpoint.id");
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new SystemException(exception);
		}
		return account;
	}

	public Account getAccount(NsRequest nsRequest, Integer clientDatabaseId) throws SystemException {
		Account account = null;
		try {
			TypedQuery<Account> query = (TypedQuery<Account>) entityManager.createNamedQuery(
					"Account.findForForgotPin", Account.class);
			query.setParameter("accountId", nsRequest.getAccountInfo().getAccountId());
			//query.setParameter("mobilePhoneNumber", nsRequest.getAccountInfo().getMobilePhoneNumber());
			//query.setParameter("email", nsRequest.getAccountInfo().getEmail());
			query.setParameter("clientDatabaseId", clientDatabaseId);
			account = query.getSingleResult();
		} catch (NoResultException exp) {
			return null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return account;
	}

	public List<PatientImaging> getAccountPatientImaging(Integer accountId) throws BusinessException, SystemException {
		List<PatientImaging> patientImaging = null;
		try {
			TypedQuery<PatientImaging> query = (TypedQuery<PatientImaging>) ehrEntityManager.createNamedQuery(
					"PatientImaging.findByAccountId", PatientImaging.class);
			query.setParameter("accountId", accountId);
			patientImaging = query.getResultList();
		} catch (NoResultException e) {
			throw new BusinessException("invalid.visit.id");
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("invalid.visit.id");
		}
		return patientImaging;
	}

	public List<PatientProblem> getPatientProblemList(NsRequest nsRequest) throws BusinessException, SystemException {
		List<PatientProblem> patientProblemList = null;
		try {
			TypedQuery<PatientProblem> query = (TypedQuery<PatientProblem>) ehrEntityManager.createNamedQuery(
					"PatientProblem.findByAccountId", PatientProblem.class);
			query.setParameter("accountId", nsRequest.getAccountInfo().getAccountId());
			patientProblemList = query.getResultList();
		} catch (NoResultException e) {
			throw new BusinessException("invalid.visit.id");
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("invalid.visit.id");
		}
		return patientProblemList;
	}

	public List<PatientProblem> getPatientProblemList(Integer accountId) throws BusinessException, SystemException {
		List<PatientProblem> patientProblemList = Collections.emptyList();
		try {
			TypedQuery<PatientProblem> query = (TypedQuery<PatientProblem>) ehrEntityManager.createNamedQuery(
					"PatientProblem.findByAccountId", PatientProblem.class);
			query.setParameter("accountId", accountId);
			patientProblemList = query.getResultList();
		} catch (NoResultException e) {
			throw new BusinessException("no.patient.problem.found");
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("invalid.visit.id");
		}
		return patientProblemList;
	}

	public List<PatientAllergy> getPatientAllergiesList(NsRequest nsRequest) throws BusinessException, SystemException {
		List<PatientAllergy> patientAllergyList = null;
		try {
			TypedQuery<PatientAllergy> query = (TypedQuery<PatientAllergy>) ehrEntityManager.createNamedQuery(
					"PatientAllergy.findByAccountId", PatientAllergy.class);
			query.setParameter("accountId", nsRequest.getAccountInfo().getAccountId());
			patientAllergyList = query.getResultList();
		} catch (NoResultException e) {
			throw new BusinessException("invalid.visit.id");
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("invalid.visit.id");
		}
		return patientAllergyList;
	}

	public void save(AccountNotificationHistory accountNotificationHistory) throws BusinessException, SystemException {
		try {
			entityManager.persist(accountNotificationHistory);
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	public List<PatientAllergy> getPatientAllergiesList(Integer accountId) throws BusinessException, SystemException {
		List<PatientAllergy> patientAllergyList = null;
		try {
			TypedQuery<PatientAllergy> query = (TypedQuery<PatientAllergy>) ehrEntityManager.createNamedQuery(
					"PatientAllergy.findByAccountId", PatientAllergy.class);
			query.setParameter("accountId", accountId);
			patientAllergyList = query.getResultList();
		} catch (NoResultException e) {

			throw new BusinessException("no.allergies.found");
		} catch (Exception e) {
			// e.printStackTrace();
			throw new BusinessException("invalid.visit.id");
		}
		return patientAllergyList;
	}
	
	public List<PatientImmunization> getImmunizationInfo(Integer accountId) throws BusinessException, SystemException {
		List<PatientImmunization> patientImmunization = null;
	
		try {
			TypedQuery<PatientImmunization> query = ehrEntityManager.createNamedQuery(
					"PatientImmunization.findByAccountId", PatientImmunization.class);

			query.setParameter("accountId", accountId);
			//query.setParameter("patientVisitId", muDataId);

			patientImmunization = query.getResultList();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientImmunization;
		
	}
	public List<PatientImaging> getPatientImaging(Integer accountId,Integer patientVisitId) throws BusinessException, SystemException {
		List<PatientImaging> patientImaging = null;
		try {
			TypedQuery<PatientImaging> query = (TypedQuery<PatientImaging>) ehrEntityManager.createNamedQuery(
					"PatientImaging.findByAccountIdAndExamId", PatientImaging.class);
			query.setParameter("accountId", accountId);
			query.setParameter("patientVisitId", patientVisitId);
			patientImaging = query.getResultList();
		} catch (NoResultException e) {
			throw new BusinessException("invalid.visit.id");
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("invalid.visit.id");
		}
		return patientImaging;
	}

	public List<PatientLab> getLabResultList(Integer accountId) throws BusinessException, SystemException {
		List<PatientLab> patientLabResult = new ArrayList<PatientLab>();
		
		try {
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) ehrEntityManager.createNamedQuery(
					"PatientLab.findByDistinctAccountId");
			query.setParameter("accountId", accountId);			
			
			List<Object[]>   patientLabResultList= query.getResultList();
			
			for(Object[] tuple : patientLabResultList) {
				
				PatientLab patientLab= new PatientLab();
				patientLab.setLabGroupName(String.valueOf(tuple[0]));
				patientLab.setLabGroupCode(String.valueOf(tuple[1]));
				patientLab.setSourceName(String.valueOf(tuple[2]));
				patientLab.setLabGroupDate(DateUtils.getDate(String.valueOf(tuple[3]), "yyyy-MM-dd hh:mm:ss")); 
			    
				patientLabResult.add(patientLab);
			}
			
			
		} 
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		
		return patientLabResult;
	}

	public List<PatientLab> getLabResultListInfo(Integer accountId,String labGroupCode)throws BusinessException, SystemException {
		List<PatientLab> patientLabResult = new ArrayList<PatientLab>();
		try {
			TypedQuery<PatientLab> query = (TypedQuery<PatientLab>) ehrEntityManager.createNamedQuery(
					"PatientLab.findByAccountIdAndLabGroupCode", PatientLab.class);
			query.setParameter("accountId", accountId);
			query.setParameter("labGroupCode", labGroupCode);
			patientLabResult = query.getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		return patientLabResult;
	}

	public List<PatientImaging> getAccountPatientImagingInfo(Integer accountId,String labGroupCode)throws BusinessException, SystemException {
		List<PatientImaging> patientImaging = null;
		try {
			TypedQuery<PatientImaging> query = (TypedQuery<PatientImaging>) ehrEntityManager.createNamedQuery(
					"PatientImaging.findByAccountIdAndExamIdInfo", PatientImaging.class);
			query.setParameter("accountId", accountId);
			query.setParameter("examId", labGroupCode);
			patientImaging = query.getResultList();
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		return patientImaging;
	}

	public List<String> getEndPointUserId() throws BusinessException, SystemException{
		List<String> account = null;
		try {
			TypedQuery<String> query = (TypedQuery<String>) entityManager.createNamedQuery(
					"Account.findAllEndPointUSerId", String.class);
			
			account = query.getResultList();
		} 
		catch (Exception e) {
			return null;
		}
		return account;
	}
   	
	
}