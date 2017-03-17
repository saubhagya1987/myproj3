package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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

@Entity
@Table(name = "feed_info")
@XmlRootElement
@NamedQueries({ 
	@NamedQuery(name = "FeedInfo.findByAccountId", query = "SELECT f FROM FeedInfo f WHERE f.accountId = :accountId AND f.clientId=:clientId AND f.clientDatabaseId=:clientDatabaseId AND f.clinicalDateTime <= current_date() ORDER BY f.feedMessageId DESC"),
	@NamedQuery(name = "FeedInfo.findByAccountIdAndFeatureIdAndFeedMessageId", query = "SELECT f FROM FeedInfo f WHERE f.accountId = :accountId AND f.clientId=:clientId AND f.clientDatabaseId=:clientDatabaseId AND f.featureId=:featureId AND f.feedMessageId=:feedMessageId"),
	@NamedQuery(name = "FeedInfo.findByAccountIdAndClinicalDateTime", query = "SELECT f FROM FeedInfo f WHERE f.accountId = :accountId AND f.clientId=:clientId AND f.clientDatabaseId=:clientDatabaseId AND f.clinicalDateTime <= current_date() "),
	@NamedQuery(name = "FeedInfo.findByAccountIdClientIdClientDbId", query = "SELECT f FROM FeedInfo f WHERE f.accountId = :accountId AND f.clientId=:clientId AND f.clientDatabaseId=:clientDatabaseId  AND f.clinicalDateTime <= current_date() "),
	@NamedQuery(name = "FeedInfo.findByAccountIdAndMedicationName", query = "SELECT f FROM FeedInfo f WHERE f.accountId = :accountId and f.feedMsg=:medicationName AND f.clientId=:clientId AND f.clientDatabaseId=:clientDatabaseId")})

public class FeedInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "feed_message_id")
	private Integer feedMessageId;
	
	@Basic(optional = false)
	@Column(name = "client_id")
	private Integer clientId;
	
	@Basic(optional = false)
	@Column(name = "client_database_id")
	private Integer clientDatabaseId;
	
	@Basic(optional = false)
	@Column(name = "feature_id")
	private Integer featureId;
		
	@Basic(optional = false)
	@Column(name = "account_id")
	private Integer accountId;

	@Basic(optional = false)
	@Column(name = "clinical_date_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date clinicalDateTime;
		
	@Basic(optional = false)
	@Column(name = "feed_header")
	private String feedHeader;
	
	@Basic(optional = false)
	@Column(name = "feed_msg")
	private String feedMsg;
	
	@Basic(optional = false)
	@Column(name = "feature_name")
	private String featureName;
	
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	
	@Basic(optional = false)
	@Column(name = "is_new")
	private Boolean isNew;
	
	@Basic(optional = false)
	@Column(name = "display_time")
	private Boolean displayTime;
	
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	
	public FeedInfo(){
		
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedInfo other = (FeedInfo) obj;
			
		if (feedMessageId == null) {
			if (other.feedMessageId != null)
				return false;
		}
		return true;
	}


	/**
	 * @return the feedMessageId
	 */
	public Integer getFeedMessageId() {
		return feedMessageId;
	}


	/**
	 * @param feedMessageId the feedMessageId to set
	 */
	public void setFeedMessageId(Integer feedMessageId) {
		this.feedMessageId = feedMessageId;
	}


	/**
	 * @return the clientId
	 */
	public Integer getClientId() {
		return clientId;
	}


	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}


	/**
	 * @return the clientDatabaseId
	 */
	public Integer getClientDatabaseId() {
		return clientDatabaseId;
	}


	/**
	 * @param clientDatabaseId the clientDatabaseId to set
	 */
	public void setClientDatabaseId(Integer clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}


	/**
	 * @return the featureId
	 */
	public Integer getFeatureId() {
		return featureId;
	}


	/**
	 * @param featureId the featureId to set
	 */
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}


	/**
	 * @return the accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}


	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}


	/**
	 * @return the clinicalDateTime
	 */
	public Date getClinicalDateTime() {
		return clinicalDateTime;
	}


	/**
	 * @param clinicalDateTime the clinicalDateTime to set
	 */
	public void setClinicalDateTime(Date clinicalDateTime) {
		this.clinicalDateTime = clinicalDateTime;
	}


	/**
	 * @return the feedHeader
	 */
	public String getFeedHeader() {
		return feedHeader;
	}


	/**
	 * @param feedHeader the feedHeader to set
	 */
	public void setFeedHeader(String feedHeader) {
		this.feedHeader = feedHeader;
	}


	/**
	 * @return the feedMsg
	 */
	public String getFeedMsg() {
		return feedMsg;
	}


	/**
	 * @param feedMsg the feedMsg to set
	 */
	public void setFeedMsg(String feedMsg) {
		this.feedMsg = feedMsg;
	}


	/**
	 * @return the featureName
	 */
	public String getFeatureName() {
		return featureName;
	}


	/**
	 * @param featureName the featureName to set
	 */
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}


	/**
	 * @return the sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}


	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}


	/**
	 * @return the isNew
	 */
	public Boolean getIsNew() {
		return isNew;
	}


	/**
	 * @param isNew the isNew to set
	 */
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}


	/**
	 * @return the displayTime
	 */
	public Boolean getDisplayTime() {
		return displayTime;
	}


	/**
	 * @param displayTime the displayTime to set
	 */
	public void setDisplayTime(Boolean displayTime) {
		this.displayTime = displayTime;
	}


	/**
	 * @return the dateAdded
	 */
	public Date getDateAdded() {
		return dateAdded;
	}


	/**
	 * @param dateAdded the dateAdded to set
	 */
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FeedInfo [feedMessageId=" + feedMessageId + "]";
	}

	
	/*@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feed_info_id")
	private Integer feedInfoId;
	@Basic(optional = false)
	@Column(name = "account_id")
	private Integer accountId;
	@Basic(optional = false)
	@Column(name = "feed_header")
	private String feedHeader;
	@Column(name = "feed_msg")
	private String feedMsg;
	@Basic(optional = false)
	@Column(name = "feature_id")
	private Integer featureId;
	@Column(name = "feature_name")
	private String featureName;
	@Basic(optional = false)
	@Column(name = "is_new")
	private Boolean isNew;
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Basic(optional = false)
	@Column(name = "record_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date recordDate;
	
	public FeedInfo() {
	}
	
	public FeedInfo(Integer feedInfoId, Integer accountId, String feedHeader,
			String feedMsg, Integer featureId, String featureName, Boolean isNew) {
		super();
		this.feedInfoId = feedInfoId;
		this.accountId = accountId;
		this.feedHeader = feedHeader;
		this.feedMsg = feedMsg;
		this.featureId = featureId;
		this.featureName = featureName;
		this.isNew = isNew;
	}
	public Integer getFeedInfoId() {
		return feedInfoId;
	}
	public void setFeedInfoId(Integer feedInfoId) {
		this.feedInfoId = feedInfoId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getFeedHeader() {
		return feedHeader;
	}
	public void setFeedHeader(String feedHeader) {
		this.feedHeader = feedHeader;
	}
	public String getFeedMsg() {
		return feedMsg;
	}
	public void setFeedMsg(String feedMsg) {
		this.feedMsg = feedMsg;
	}
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public Boolean getIsNew() {
		return isNew;
	}
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((feedInfoId == null) ? 0 : feedInfoId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedInfo other = (FeedInfo) obj;
		if (feedInfoId == null) {
			if (other.feedInfoId != null)
				return false;
		} else if (!feedInfoId.equals(other.feedInfoId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FeedInfo [feedInfoId=" + feedInfoId + "]";
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
}
