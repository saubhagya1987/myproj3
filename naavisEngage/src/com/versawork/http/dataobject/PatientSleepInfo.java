package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PatientSleepInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer clientId;
	private Integer clientDatabaseId;
	private String sourceId;
	private String sourceName;
	private Integer sleep;
	private Date sleepDate;
	private Date dateAdded;
	private String slpDate;
	
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
	
	
	public String getSlpDate() {
		return slpDate;
	}
	public void setSlpDate(String slpDate) {
		this.slpDate = slpDate;
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
	 * @return the sleep
	 */
	public Integer getSleep() {
		return sleep;
	}
	/**
	 * @param sleep the sleep to set
	 */
	public void setSleep(Integer sleep) {
		this.sleep = sleep;
	}
	/**
	 * @return the sleepDate
	 */
	public Date getSleepDate() {
		return sleepDate;
	}
	/**
	 * @param sleepDate the sleepDate to set
	 */
	public void setSleepDate(Date sleepDate) {
		this.sleepDate = sleepDate;
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
