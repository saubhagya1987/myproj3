package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Rows implements Serializable {

	private final static long serialVersionUID = 1L;

	protected List<Elements> elements;
	protected String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Elements> getElements() {
		return elements;
	}

	public void setElements(List<Elements> elements) {
		this.elements = elements;
	}
}
