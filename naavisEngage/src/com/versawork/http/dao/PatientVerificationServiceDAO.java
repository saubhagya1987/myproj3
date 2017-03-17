package com.versawork.http.dao;

import java.io.Serializable;
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
import org.springframework.transaction.annotation.Transactional;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.PatientVerification;
import com.versawork.http.model.PatientVerificationLog;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class PatientVerificationServiceDAO implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientVerificationServiceDAO.class);
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "versawork_DS", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	@Transactional("ehr_txn")
	public void update(PatientVerification patientVerification) throws BusinessException, SystemException {
		try {
			ehrEntityManager.merge(patientVerification);
		} catch (Exception exception) {
			throw new SystemException(exception.getMessage());
		}

	}

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
			try {
				patientVerificationData = (PatientVerification) query.getSingleResult();
				return patientVerificationData;
			} catch (NoResultException noResultException) {
				Account accountData = null;
				Query query1 = entityManager.createNamedQuery("Account.findByMedicalRecordNumberAndMobilePhoneNumber",
						Account.class);
				query1.setParameter("medicalRecordNumber", mrNumber);
				query1.setParameter("mobilePhoneNumber", phoneNumber);
				accountData = (Account) query1.getSingleResult();
				query = ehrEntityManager.createNamedQuery("PatientVerification.findByUnitNumber",
						PatientVerification.class);
				query.setParameter("medicalRecordNumber", accountData.getMedicalRecordNumber());
				patientVerificationData = (PatientVerification) query.getSingleResult();
				return patientVerificationData;
			}
			// return patientVerificationData;
		} catch (NoResultException noResultException) {
			throw new BusinessException("pat.verification.fail");
		} catch (Exception exception) {
			throw new SystemException(exception.getMessage());
		}

	}

	/**
	 * Update Patient Verification entity in database.
	 * 
	 * @param patientVerification
	 * @throws SystemException
	 */
	@Transactional("ehr_txn")
	public void updatePatientVerification(PatientVerification patientVerification) throws SystemException {
		try {
			ehrEntityManager.merge(patientVerification);
			ehrEntityManager.flush();
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	/**
	 * Return patient verification data.
	 * 
	 * @param unitNumber
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public PatientVerification getPatientVerifications(String unitNumber, Integer clientDatabaseId)
			throws BusinessException, SystemException {

		PatientVerification patientVerification = null;
		try {
			TypedQuery<PatientVerification> query = (TypedQuery<PatientVerification>) ehrEntityManager
					.createNamedQuery("PatientVerification.findByMedicalRecordNumber", PatientVerification.class);
			query.setParameter("medicalRecordNumber", unitNumber);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			patientVerification = query.getSingleResult();

		} catch (NoResultException exp) {
			patientVerification = null;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return patientVerification;
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
	 * public HealthcareProvider getHospitalProviderDetails(String fName, String
	 * lName, String email) throws BusinessException, SystemException {
	 * 
	 * HealthcareProvider healthcareProvider = null; try {
	 * TypedQuery<HealthcareProvider> query = (TypedQuery<HealthcareProvider>)
	 * entityManager.createNamedQuery(
	 * "HealthcareProvider.findByFirstLastNameEmail", HealthcareProvider.class);
	 * query.setParameter("firstName", fName); query.setParameter("lastName",
	 * lName); query.setParameter("contactEmail", email); healthcareProvider =
	 * query.getSingleResult();
	 * 
	 * } catch (NoResultException exp) { throw new
	 * BusinessException("no.provider.details.found"); } catch (Exception
	 * exception) { throw new SystemException(exception); } return
	 * healthcareProvider; }
	 */

	/*
	 * public PatientVerification updatePatientVerification(PatientVerification
	 * PatientVerification) throws BusinessException, SystemException {
	 * 
	 * PatientVerification PatientVerification = null; try {
	 * TypedQuery<PatientVerification> query = (TypedQuery<PatientVerification>)
	 * entityManager.createNamedQuery( "PatientVerification.findByUnitNumber",
	 * PatientVerification.class); query.setParameter("medicalRecordNumber",
	 * unitNumber); patientVerification = query.getSingleResult();
	 * 
	 * } catch (NoResultException exp) { throw new
	 * BusinessException("no.patnt.verf.for.untnmbr"); } catch (Exception
	 * exception) { throw new SystemException(exception); } return
	 * patientVerification; }
	 */

	public void save(PatientVerificationLog patientVerificationLog) throws BusinessException, SystemException {
		try {
			ehrEntityManager.persist(patientVerificationLog);
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

}
