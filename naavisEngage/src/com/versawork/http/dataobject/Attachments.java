package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Attachments implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String typeOfFile;
	protected String fileName;

	public String getTypeOfFile() {
		return typeOfFile;
	}

	public void setTypeOfFile(String typeOfFile) {
		this.typeOfFile = typeOfFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
