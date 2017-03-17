package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PatientHeartAgeInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	private Integer accountId;
	private Integer heartAgeId;
	private Integer clientId;
	private Integer clientDatabaseId;
	private String sourceId;
	private String sourceName;
	private Integer heartAge;
	private Date heartAgeDate;
	private Date dateAdded;
	private String heartDate;
	
	
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
	 * @return the heartAgeId
	 */
	public Integer getHeartAgeId() {
		return heartAgeId;
	}
	/**
	 * @param heartAgeId the heartAgeId to set
	 */
	public void setHeartAgeId(Integer heartAgeId) {
		this.heartAgeId = heartAgeId;
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
	 * @return the heartAge
	 */
	public Integer getHeartAge() {
		return heartAge;
	}
	/**
	 * @param heartAge the heartAge to set
	 */
	public void setHeartAge(Integer heartAge) {
		this.heartAge = heartAge;
	}
	/**
	 * @return the heartAgeDate
	 */
	public Date getHeartAgeDate() {
		return heartAgeDate;
	}
	/**
	 * @param heartAgeDate the heartAgeDate to set
	 */
	public void setHeartAgeDate(Date heartAgeDate) {
		this.heartAgeDate = heartAgeDate;
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
	public String getHeartDate() {
		return heartDate;
	}
	public void setHeartDate(String heartDate) {
		this.heartDate = heartDate;
	}

}
