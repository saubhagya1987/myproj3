package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PatientHeartRateInfo implements Serializable{
	private static final long serialVersionUID = 1L;	 
	private Integer accountId;	 
	
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
	 * @return the heartRateId
	 */
	public Integer getHeartRateId() {
		return heartRateId;
	}
	/**
	 * @param heartRateId the heartRateId to set
	 */
	public void setHeartRateId(Integer heartRateId) {
		this.heartRateId = heartRateId;
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
	 * @return the heartRate
	 */
	public Integer getHeartRate() {
		return heartRate;
	}
	/**
	 * @param heartRate the heartRate to set
	 */
	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
	}
	/**
	 * @return the heartRateDate
	 */
	public Date getHeartRateDate() {
		return heartRateDate;
	}
	/**
	 * @param heartRateDate the heartRateDate to set
	 */
	public void setHeartRateDate(Date heartRateDate) {
		this.heartRateDate = heartRateDate;
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
	public String getHeartRtDate() {
		return heartRtDate;
	}
	public void setHeartRtDate(String heartRtDate) {
		this.heartRtDate = heartRtDate;
	}
	private Integer heartRateId;
    private Integer clientId;
    private Integer clientDatabaseId;
    private String sourceId;
    private String sourceName;
    private Integer heartRate;
    private Date heartRateDate;
    private Date dateAdded;
    private String heartRtDate;
}
