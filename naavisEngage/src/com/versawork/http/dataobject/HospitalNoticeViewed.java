package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HospitalNoticeViewed implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Integer clientId;
	protected Integer clientDatabaseId;
	protected Integer hospitalNoticeId;

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

	public Integer getHospitalNoticeId() {
		return hospitalNoticeId;
	}

	public void setHospitalNoticeId(Integer hospitalNoticeId) {
		this.hospitalNoticeId = hospitalNoticeId;
	}

}
