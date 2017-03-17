package com.versawork.http.dataobject;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FeedListInfo {
	private Integer clientId;
	private Integer clientDatabseId;
	private Integer featureId;
	private Integer accountId;
	private Integer feedMessageId;
	private String recordDate;
	private Boolean recordTime;
	private String feedHeader;
	private String feedMessage;
	private String featureName;
	private String sourceName;
	private Boolean isNew;
	private String dateAdded;
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getClientDatabseId() {
		return clientDatabseId;
	}
	public void setClientDatabseId(Integer clientDatabseId) {
		this.clientDatabseId = clientDatabseId;
	}
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public Boolean getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Boolean recordTime) {
		this.recordTime = recordTime;
	}
	public String getFeedHeader() {
		return feedHeader;
	}
	public void setFeedHeader(String feedHeader) {
		this.feedHeader = feedHeader;
	}
	public String getFeedMessage() {
		return feedMessage;
	}
	public void setFeedMessage(String feedMessage) {
		this.feedMessage = feedMessage;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public Boolean getIsNew() {
		return isNew;
	}
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	public Integer getFeedMessageId() {
		return feedMessageId;
	}
	public void setFeedMessageId(Integer feedMessageId) {
		this.feedMessageId = feedMessageId;
	}
	
	
	/*private Integer feedInfoId;
	private Integer accountId;
	private String feedHeader;
	private String feedMsg;
	private Integer featureId;
	private String featureName;
	private Boolean isNew;
	private String sourceName;
	private String dateAdded;
	private String recordDate;
	
	
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
	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}*/
	
}
