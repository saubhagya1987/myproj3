package com.versawork.http.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sohaib
 */
/**
 * @author Sohaib
 * 
 */
@Entity
@Table(name = "account_to_viewed_facility_notice")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "AccountToViewedFacilityNotice.findAll", query = "SELECT a FROM AccountToViewedFacilityNotice a"),
		@NamedQuery(name = "AccountToViewedFacilityNotice.findByAccountId", query = "SELECT a FROM AccountToViewedFacilityNotice a WHERE a.accountId = :accountId"),
		@NamedQuery(name = "AccountToViewedFacilityNotice.findByViewedList", query = "SELECT a FROM AccountToViewedFacilityNotice a WHERE a.viewedList = :viewedList") })
public class AccountToViewedFacilityNotice implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "account_id")
	private String accountId;
	@Basic(optional = false)
	@Column(name = "viewedList")
	private String viewedList;

	public AccountToViewedFacilityNotice() {
	}

	public AccountToViewedFacilityNotice(String accountId) {
		this.accountId = accountId;
	}

	public AccountToViewedFacilityNotice(String accountId, String viewedList) {
		this.accountId = accountId;
		this.viewedList = viewedList;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getViewedList() {
		return viewedList;
	}

	public void setViewedList(String viewedList) {
		this.viewedList = viewedList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (accountId != null ? accountId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AccountToViewedFacilityNotice)) {
			return false;
		}
		AccountToViewedFacilityNotice other = (AccountToViewedFacilityNotice) object;
		if ((this.accountId == null && other.accountId != null)
				|| (this.accountId != null && !this.accountId.equals(other.accountId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.AccountToViewedFacilityNotice[ accountId=" + accountId + " ]";
	}

}
