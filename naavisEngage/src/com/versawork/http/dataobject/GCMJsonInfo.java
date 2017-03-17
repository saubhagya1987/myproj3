package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GCMJsonInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String message;
	protected Integer aID;
	protected String aDate;
	protected String aTime;
	protected String byDoctor;
	protected Integer sID;

	public Integer getsID() {
		return sID;
	}

	public void setsID(Integer sID) {
		this.sID = sID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getaID() {
		return aID;
	}

	public void setaID(Integer aID) {
		this.aID = aID;
	}

	public String getaDate() {
		return aDate;
	}

	public void setaDate(String aDate) {
		this.aDate = aDate;
	}

	public String getaTime() {
		return aTime;
	}

	public void setaTime(String aTime) {
		this.aTime = aTime;
	}

	public String getByDoctor() {
		return byDoctor;
	}

	public void setByDoctor(String byDoctor) {
		this.byDoctor = byDoctor;
	}
}
