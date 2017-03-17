package com.imagingserver.http.imaging.dataObjects;

import java.io.Serializable;

public class ResponseData implements Serializable {
	private final static long serialVersionUID = 1L;

	private int result;
	private String description;

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