package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PatientOverallStressInfo implements Serializable{
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer accountId;
	private Integer overallStressId;
    private Integer clientId;
    private Integer clientDatabaseId;
    private String sourceId;
    private String sourceName;
    private Integer overallStress;
    private Date overallStressDate;
    private Date dateAdded;
    private String stressDate;
    
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
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getOverallStressId() {
		return overallStressId;
	}
	public void setOverallStressId(Integer overallStressId) {
		this.overallStressId = overallStressId;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getClientDatabaseId() {
		return clientDatabaseId;
	}
	public void setClientDatabaseId(Integer clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public Integer getOverallStress() {
		return overallStress;
	}
	public void setOverallStress(Integer overallStress) {
		this.overallStress = overallStress;
	}
	public Date getOverallStressDate() {
		return overallStressDate;
	}
	public void setOverallStressDate(Date overallStressDate) {
		this.overallStressDate = overallStressDate;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	public String getStressDate() {
		return stressDate;
	}
	public void setStressDate(String stressDate) {
		this.stressDate = stressDate;
	}
    
	
    
    

}
