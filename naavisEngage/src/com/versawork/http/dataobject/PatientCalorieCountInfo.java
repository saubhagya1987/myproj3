package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PatientCalorieCountInfo implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer accountId;
    private Integer calorieCountId;
	private Integer clientId;
    private Integer clientDatabaseId;
    private String sourceId;
    private String sourceName;
    private Integer calorieCount;
    private Date calorieCountDate;
    private Date dateAdded;
    private String calorieCntDate;
    
    
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
    
    
	public String getCalorieCntDate() {
		return calorieCntDate;
	}
	public void setCalorieCntDate(String calorieCntDate) {
		this.calorieCntDate = calorieCntDate;
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
	 * @return the calorieCountId
	 */
	public Integer getCalorieCountId() {
		return calorieCountId;
	}
	/**
	 * @param calorieCountId the calorieCountId to set
	 */
	public void setCalorieCountId(Integer calorieCountId) {
		this.calorieCountId = calorieCountId;
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
	 * @return the calorieCount
	 */
	public Integer getCalorieCount() {
		return calorieCount;
	}
	/**
	 * @param calorieCount the calorieCount to set
	 */
	public void setCalorieCount(Integer calorieCount) {
		this.calorieCount = calorieCount;
	}
	/**
	 * @return the calorieCountDate
	 */
	public Date getCalorieCountDate() {
		return calorieCountDate;
	}
	/**
	 * @param calorieCountDate the calorieCountDate to set
	 */
	public void setCalorieCountDate(Date calorieCountDate) {
		this.calorieCountDate = calorieCountDate;
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
