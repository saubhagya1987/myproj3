package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PatientActiveTimeInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer clientId;
	private Integer clientDatabaseId;
	private String sourceId;
	private String sourceName;
	private Double activeTime;
	private Date activeTimeDate;
	private Date dateAdded;
	private String activeDate;
	
	
	private double value;
    private String date;
    
    
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public String getActiveDate() {
		return activeDate;
	}
	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
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
	 * @return the activeTime
	 */
	public Double getActiveTime() {
		return activeTime;
	}
	/**
	 * @param activeTime the activeTime to set
	 */
	public void setActiveTime(Double activeTime) {
		this.activeTime = activeTime;
	}
	/**
	 * @return the activeTimeDate
	 */
	public Date getActiveTimeDate() {
		return activeTimeDate;
	}
	/**
	 * @param activeTimeDate the activeTimeDate to set
	 */
	public void setActiveTimeDate(Date activeTimeDate) {
		this.activeTimeDate = activeTimeDate;
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
