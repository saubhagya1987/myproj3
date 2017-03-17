/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "facility_notice")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "FacilityNotice.findAll", query = "SELECT f FROM FacilityNotice f"),
		@NamedQuery(name = "FacilityNotice.findByFacilityNoticeId", query = "SELECT f FROM FacilityNotice f WHERE f.facilityNoticePK.facilityNoticeId = :facilityNoticeId"),
		@NamedQuery(name = "FacilityNotice.findByAccRoleIdAndClientDbId", query = "SELECT h FROM FacilityNotice h WHERE h.accountRoleId = :accountRoleId and h.facilityNoticePK.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "FacilityNotice.findByClientId", query = "SELECT f FROM FacilityNotice f WHERE f.facilityNoticePK.clientId = :clientId"),
		@NamedQuery(name = "FacilityNotice.findByClientDatabaseId", query = "SELECT f FROM FacilityNotice f WHERE f.facilityNoticePK.clientDatabaseId = :clientDatabaseId"),
		//@NamedQuery(name = "FacilityNotice.findByClientDatabaseIdDateLimit", query = "SELECT f FROM FacilityNotice f WHERE f.facilityNoticePK.clientDatabaseId = :clientDatabaseId and convert(varchar(10),f.beginDate,101) <= :beginDate and convert(varchar(10),f.endDate,101) >= :beginDate"),
		@NamedQuery(name = "FacilityNotice.findByClientDatabaseIdDateLimit", query = "SELECT f FROM FacilityNotice f WHERE f.facilityNoticePK.clientDatabaseId = :clientDatabaseId and f.beginDate < CURRENT_TIMESTAMP and f.endDate > CURRENT_TIMESTAMP"),
		@NamedQuery(name = "FacilityNotice.findByBeginDate", query = "SELECT f FROM FacilityNotice f WHERE f.beginDate = :beginDate"),
		@NamedQuery(name = "FacilityNotice.findByEndDate", query = "SELECT f FROM FacilityNotice f WHERE f.endDate = :endDate"),
		@NamedQuery(name = "FacilityNotice.findByNoticeMessage", query = "SELECT f FROM FacilityNotice f WHERE f.noticeMessage = :noticeMessage"),
		@NamedQuery(name = "FacilityNotice.findByAccountRoleId", query = "SELECT f FROM FacilityNotice f WHERE f.accountRoleId = :accountRoleId"),
		@NamedQuery(name = "FacilityNotice.findByDateAdded", query = "SELECT f FROM FacilityNotice f WHERE f.dateAdded = :dateAdded") })
public class FacilityNotice implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected FacilityNoticePK facilityNoticePK;
	@Column(name = "begin_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate;
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@Column(name = "notice_message")
	private String noticeMessage;
	@Column(name = "account_role_id")
	private Integer accountRoleId;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "notice_header")
	private String noticeHeader;

	public String getNoticeHeader() {
		return noticeHeader;
	}

	public void setNoticeHeader(String noticeHeader) {
		this.noticeHeader = noticeHeader;
	}

	public FacilityNotice() {
	}

	public FacilityNotice(FacilityNoticePK facilityNoticePK) {
		this.facilityNoticePK = facilityNoticePK;
	}

	public FacilityNotice(FacilityNoticePK facilityNoticePK, Date dateAdded) {
		this.facilityNoticePK = facilityNoticePK;
		this.dateAdded = dateAdded;
	}

	public FacilityNotice(int facilityNoticeId, int clientId, int clientDatabaseId) {
		this.facilityNoticePK = new FacilityNoticePK(facilityNoticeId, clientId, clientDatabaseId);
	}

	public FacilityNoticePK getFacilityNoticePK() {
		return facilityNoticePK;
	}

	public void setFacilityNoticePK(FacilityNoticePK facilityNoticePK) {
		this.facilityNoticePK = facilityNoticePK;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getNoticeMessage() {
		return noticeMessage;
	}

	public void setNoticeMessage(String noticeMessage) {
		this.noticeMessage = noticeMessage;
	}

	public Integer getAccountRoleId() {
		return accountRoleId;
	}

	public void setAccountRoleId(Integer accountRoleId) {
		this.accountRoleId = accountRoleId;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (facilityNoticePK != null ? facilityNoticePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof FacilityNotice)) {
			return false;
		}
		FacilityNotice other = (FacilityNotice) object;
		if ((this.facilityNoticePK == null && other.facilityNoticePK != null)
				|| (this.facilityNoticePK != null && !this.facilityNoticePK.equals(other.facilityNoticePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.FacilityNotice[ facilityNoticePK=" + facilityNoticePK + " ]";
	}

}
