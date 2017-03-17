package com.versawork.http.dataobject;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CacheInputs implements Serializable {
	private final static long serialVersionUID = 1L;
	protected String module;
	protected Integer clientDatabaseId;
	protected String unitNumber;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Integer getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(Integer clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
}
