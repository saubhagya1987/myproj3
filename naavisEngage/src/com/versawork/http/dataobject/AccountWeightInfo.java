package com.versawork.http.dataobject;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AccountWeightInfo {
	private Integer weightId;
	private Integer accountId;
	private String date;
	private String weight;
	private String weightUnits;
	private String dateAdded;
	
	private String value;
   
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	public Integer getWeightId() {
		return weightId;
	}
	public void setWeightId(Integer weightId) {
		this.weightId = weightId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getWeightUnits() {
		return weightUnits;
	}
	public void setWeightUnits(String weightUnits) {
		this.weightUnits = weightUnits;
	}
	
}
