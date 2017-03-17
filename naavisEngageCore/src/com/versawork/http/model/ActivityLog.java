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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "activity_log")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "ActivityLog.findAll", query = "SELECT a FROM ActivityLog a"),
		@NamedQuery(name = "ActivityLog.findByActivityId", query = "SELECT a FROM ActivityLog a WHERE a.activityId = :activityId"),
		@NamedQuery(name = "ActivityLog.findByActivity", query = "SELECT a FROM ActivityLog a WHERE a.activity = :activity"),
		@NamedQuery(name = "ActivityLog.findByRequest", query = "SELECT a FROM ActivityLog a WHERE a.request = :request"),
		@NamedQuery(name = "ActivityLog.findByAccountIdActivityList", query = "SELECT a FROM ActivityLog a WHERE a.auditAccountId = :auditAccountId and convert(varchar(10),a.dateAdded,101) between :fromDate and :todaysDate and activity IN ('View patient details','Transmit EHR','Download MU data') ORDER BY a.dateAdded DESC"),
		@NamedQuery(name = "ActivityLog.findByResponse", query = "SELECT a FROM ActivityLog a WHERE a.response = :response"),
		@NamedQuery(name = "ActivityLog.findByAuditAccountId", query = "SELECT a FROM ActivityLog a WHERE a.auditAccountId = :auditAccountId"),
		@NamedQuery(name = "ActivityLog.findByDeleteFlag", query = "SELECT a FROM ActivityLog a WHERE a.deleteFlag = :deleteFlag"),
		@NamedQuery(name = "ActivityLog.findByAccountIdActivityNameFrmTo", query = "SELECT a FROM ActivityLog a WHERE a.auditAccountId = :auditAccountId AND a.activity = :activity and convert(varchar(10),a.dateAdded,101) between :fromDate and :todaysDate ORDER BY a.dateAdded DESC"),
		@NamedQuery(name = "ActivityLog.findByDateAdded", query = "SELECT a FROM ActivityLog a WHERE a.dateAdded = :dateAdded") })
public class ActivityLog implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "activity_id")
	private Integer activityId;
	@Basic(optional = false)
	@Column(name = "activity")
	private String activity;
	@Column(name = "request")
	private String request;
	@Column(name = "response")
	private String response;
	@Column(name = "audit_account_id")
	private Integer auditAccountId;
	@Basic(optional = false)
	@Column(name = "delete_flag")
	private boolean deleteFlag;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;

	public ActivityLog() {
	}

	public ActivityLog(Integer activityId) {
		this.activityId = activityId;
	}

	public ActivityLog(Integer activityId, String activity, boolean deleteFlag, Date dateAdded) {
		this.activityId = activityId;
		this.activity = activity;
		this.deleteFlag = deleteFlag;
		this.dateAdded = dateAdded;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Integer getAuditAccountId() {
		return auditAccountId;
	}

	public void setAuditAccountId(Integer auditAccountId) {
		this.auditAccountId = auditAccountId;
	}

	public boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
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
		hash += (activityId != null ? activityId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ActivityLog)) {
			return false;
		}
		ActivityLog other = (ActivityLog) object;
		if ((this.activityId == null && other.activityId != null)
				|| (this.activityId != null && !this.activityId.equals(other.activityId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.ActivityLog[ activityId=" + activityId + " ]";
	}

}
