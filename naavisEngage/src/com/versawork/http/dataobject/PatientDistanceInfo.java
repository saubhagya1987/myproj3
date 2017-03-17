package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PatientDistanceInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer clientId;
    private Integer clientDatabaseId;
    private Integer accountId;
    private String sourceId;
    private String sourceName;
    private Double distance;
    private Date distanceDate;
    private Date dateAdded;
    private String distanceCoveredDate;
    
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
    
    
	
	public String getDistanceCoveredDate() {
		return distanceCoveredDate;
	}
	public void setDistanceCoveredDate(String distanceCoveredDate) {
		this.distanceCoveredDate = distanceCoveredDate;
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
	 * @return the distance
	 */
	public Double getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	/**
	 * @return the distanceDate
	 */
	public Date getDistanceDate() {
		return distanceDate;
	}
	/**
	 * @param distanceDate the distanceDate to set
	 */
	public void setDistanceDate(Date distanceDate) {
		this.distanceDate = distanceDate;
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
