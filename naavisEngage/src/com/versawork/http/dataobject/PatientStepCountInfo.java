package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PatientStepCountInfo implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer accountId;
    private Integer stepCountId;
    private Integer clientId;
    private Integer clientDatabaseId;
    private String sourceId;
    private String sourceName;
    private Integer stepCount;
    private Date stepCountDate;
    private Date dateAdded;
    private String stepCntDate;
    
    
    private Integer value;
    private String date;
    
    
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
    
	
	public String getStepCntDate() {
		return stepCntDate;
	}
	public void setStepCntDate(String stepCntDate) {
		this.stepCntDate = stepCntDate;
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
	 * @return the stepCountId
	 */
	public Integer getStepCountId() {
		return stepCountId;
	}
	/**
	 * @param stepCountId the stepCountId to set
	 */
	public void setStepCountId(Integer stepCountId) {
		this.stepCountId = stepCountId;
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
	 * @return the sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}
	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
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
	 * @return the stepCount
	 */
	public Integer getStepCount() {
		return stepCount;
	}
	/**
	 * @param stepCount the stepCount to set
	 */
	public void setStepCount(Integer stepCount) {
		this.stepCount = stepCount;
	}
	/**
	 * @return the stepCountDate
	 */
	public Date getStepCountDate() {
		return stepCountDate;
	}
	/**
	 * @param stepCountDate the stepCountDate to set
	 */
	public void setStepCountDate(Date stepCountDate) {
		this.stepCountDate = stepCountDate;
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


}
