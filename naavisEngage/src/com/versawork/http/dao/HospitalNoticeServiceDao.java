package com.versawork.http.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.AccountToViewedFacilityNotice;
import com.versawork.http.model.ClientNaavisDatabases;
import com.versawork.http.model.ClientNaavisDatabasesPK;
import com.versawork.http.model.FacilityNotice;

/**
 * @author Dheeraj
 * 
 */

@Repository
public class HospitalNoticeServiceDao implements Serializable {

	/**
     * 
     */

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "versawork_DS", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	@PersistenceContext(unitName = "versawork_ehr", type = PersistenceContextType.TRANSACTION)
	private EntityManager ehrEntityManager;

	public List<FacilityNotice> getHospitalNotice(Integer clientDatabaseId) throws BusinessException, SystemException {
		List<FacilityNotice> hospitalNotices = null;
		try {
			TypedQuery<FacilityNotice> query = (TypedQuery<FacilityNotice>) entityManager.createNamedQuery(
					"FacilityNotice.findByClientDatabaseIdDateLimit", FacilityNotice.class);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			hospitalNotices = query.getResultList();
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return hospitalNotices;
	}

	public List<FacilityNotice> getHospitalNoticeCount(int clientDatabaseId, Date todaysDate, Date endDate)
			throws BusinessException, SystemException {
		List<FacilityNotice> hospitalNotices = new ArrayList<FacilityNotice>();
		try {
			TypedQuery<FacilityNotice> query = (TypedQuery<FacilityNotice>) entityManager.createNamedQuery(
					"FacilityNotice.findByClientDatabaseIdDateLimit", FacilityNotice.class);
			query.setParameter("clientDatabaseId", clientDatabaseId);
			query.setParameter("beginDate", todaysDate, TemporalType.DATE);
			hospitalNotices = query.getResultList();
		} catch (NoResultException exp) {
			return hospitalNotices;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		if (hospitalNotices != null && hospitalNotices.size() != 0) {
			return hospitalNotices;
		}
		return hospitalNotices;
	}

	public AccountToViewedFacilityNotice getViewedNoticeList(String accountId) throws SystemException {
		AccountToViewedFacilityNotice hospitalNotices = new AccountToViewedFacilityNotice();
		try {
			TypedQuery<AccountToViewedFacilityNotice> query = (TypedQuery<AccountToViewedFacilityNotice>) entityManager
					.createNamedQuery("AccountToViewedFacilityNotice.findByAccountId",
							AccountToViewedFacilityNotice.class);
			query.setParameter("accountId", accountId);
			hospitalNotices = query.getSingleResult();
		} catch (NoResultException exp) {
			return hospitalNotices;
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return hospitalNotices;
	}

	public List<ClientNaavisDatabases> getDatabasesList(int clientId) throws BusinessException, SystemException {
		List<ClientNaavisDatabases> clientNaavisDatabases = null;
		try {
			TypedQuery<ClientNaavisDatabases> query = (TypedQuery<ClientNaavisDatabases>) ehrEntityManager
					.createNamedQuery("ClientNaavisDatabases.findByClientId", ClientNaavisDatabases.class);
			query.setParameter("clientId", clientId);
			clientNaavisDatabases = query.getResultList();
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
		return clientNaavisDatabases;
	}

	public void updateViewedList(AccountToViewedFacilityNotice aTVFN) throws SystemException {
		try {
			entityManager.merge(aTVFN);
		} catch (Exception exception) {
			throw new SystemException(exception.getMessage());
		}

	}

	public void saveViewedList(AccountToViewedFacilityNotice aTVFN) throws SystemException {
		try {
			entityManager.persist(aTVFN);
		} catch (Exception exception) {
			throw new SystemException(exception);
		}

	}
}
