package com.versawork.http.cacheDataObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sohaib
 * 
 *         Key = FacilityN_clientId_clientDatabaseID_facilityNoticeId
 */
public class CFacilityNotice implements Serializable {

	private static final long serialVersionUID = 1L;
	private int facilityNoticeId;
	private int clientId;
	private int clientDatabaseId;
	private Date beginDate;
	private Date endDate;
	private String noticeMessage;
	private Integer accountRoleId;
	private Date dateAdded;

	public int getFacilityNoticeId() {
		return facilityNoticeId;
	}

	public void setFacilityNoticeId(int facilityNoticeId) {
		this.facilityNoticeId = facilityNoticeId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(int clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
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

}
