package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsPin implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String oldPin;
	protected String newPin;

	public String getOldPin() {
		return oldPin;
	}

	public void setOldPin(String oldPassword) {
		this.oldPin = oldPassword;
	}

	public String getNewPin() {
		return newPin;
	}

	public void setNewPin(String newPassword) {
		this.newPin = newPassword;
	}
}
