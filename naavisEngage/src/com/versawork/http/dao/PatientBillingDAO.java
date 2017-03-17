package com.versawork.http.dao;

import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.LinkedAccountsRel;
import com.versawork.http.model.PatientBillDetail;
import com.versawork.http.model.PatientBillSummary;
import com.versawork.http.model.PatientTransactionLog;

@Repository
public class PatientBillingDAO {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "versawork_DS")
	private EntityManager entityManager;

	@PersistenceContext(unitName = "versawork_ehr")
	private EntityManager ehrEntityManager;
	static Logger LOGGER = LoggerFactory.getLogger(PatientBillingDAO.class);

	@Transactional(value = "ehr_txn")
	public void update(PatientBillSummary billSummary) throws SystemException {
		ehrEntityManager.merge(billSummary);
	}

	@Autowired
	private MessageSource messageSource;

	@Transactional(value = "ehr_txn")
	public void create(PatientTransactionLog patientTransactionLog) throws SystemException {
		ehrEntityManager.persist(patientTransactionLog);
	}

	public List<PatientBillSummary> getPatientBillSummaryList(Integer accountId, boolean isBillPaid)
			throws BusinessException, SystemException {
		List<PatientBillSummary> patientBillSummaries = null;
		Integer clientDbID = Integer.parseInt(messageSource.getMessage(VersaWorkConstant.CLIENT_DATABASE_ID, null,
				Locale.getDefault()));
		TypedQuery<PatientBillSummary> query = (TypedQuery<PatientBillSummary>) ehrEntityManager.createNamedQuery(
				"PatientBillSummary.findByAccountIdIsBillPaidClientDatabaseId", PatientBillSummary.class);
		query.setParameter("accountId", accountId);
		query.setParameter("isBillPaid", isBillPaid);
		query.setParameter("clientDatabaseId", clientDbID);
		patientBillSummaries = query.getResultList();

		return patientBillSummaries;
	}

	public List<PatientBillSummary> getPatientBillSummaryListByIsBillPaidIsViewed(Integer accountId,
			boolean isBillPaid, boolean isViewed) throws BusinessException, SystemException {
		List<PatientBillSummary> patientBillSummaries = null;
		Integer clientDbID = Integer.parseInt(messageSource.getMessage(VersaWorkConstant.CLIENT_DATABASE_ID, null,
				Locale.getDefault()));
		TypedQuery<PatientBillSummary> query = (TypedQuery<PatientBillSummary>) ehrEntityManager.createNamedQuery(
				"PatientBillSummary.findByAccountIdIsBillPaidIsViewedClientDatabaseId", PatientBillSummary.class);
		query.setParameter("accountId", accountId);
		query.setParameter("isBillPaid", isBillPaid);
		query.setParameter("isViewed", isViewed);
		query.setParameter("clientDatabaseId", clientDbID);

		patientBillSummaries = query.getResultList();

		return patientBillSummaries;
	}

	public PatientBillSummary getPatientBillSummaryByBillId(String billId) throws BusinessException, SystemException {
		PatientBillSummary patientBillSummaries = null;
		Integer clientDbID = Integer.parseInt(messageSource.getMessage(VersaWorkConstant.CLIENT_DATABASE_ID, null,
				Locale.getDefault()));
		try {
			TypedQuery<PatientBillSummary> query = (TypedQuery<PatientBillSummary>) ehrEntityManager.createNamedQuery(
					"PatientBillSummary.findByBillIdClientDatabaseId", PatientBillSummary.class);
			query.setParameter("billId", billId);
			query.setParameter("clientDatabaseId", clientDbID);
			patientBillSummaries = query.getSingleResult();
		} catch (NoResultException exception) {
			LOGGER.debug("No records found for Bill Id :" + billId);
			throw new BusinessException(VersaWorkConstant.NO_RESULT_FOUND);
		}
		return patientBillSummaries;
	}

	public List<PatientBillDetail> getPatientBillDetailList(String billId) throws BusinessException, SystemException {
		List<PatientBillDetail> patientBillDetails = null;
		Integer clientDbID = Integer.parseInt(messageSource.getMessage(VersaWorkConstant.CLIENT_DATABASE_ID, null,
				Locale.getDefault()));
		TypedQuery<PatientBillDetail> query = (TypedQuery<PatientBillDetail>) ehrEntityManager.createNamedQuery(
				"PatientBillDetail.findByBillIdClientDatabaseId", PatientBillDetail.class);
		query.setParameter("billId", billId);
		query.setParameter("clientDatabaseId", clientDbID);
		patientBillDetails = query.getResultList();

		return patientBillDetails;
	}

}
