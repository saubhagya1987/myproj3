package com.versawork.asyn.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseData implements Serializable {
	private final static long serialVersionUID = 1L;

	protected int result;

	protected String description;

	public int getResult() {

		return result;
	}

	public void setResult(int result) {

		this.result = result;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

}
