package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SystemAdministratorInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String email;
	protected String certificateDesc;

	protected String serverTimeZone;

	protected String NTPServerTime;
	protected String NaavisSystemTime;

	public String getNTPServerTime() {
		return NTPServerTime;
	}

	public void setNTPServerTime(String nTPServerTime) {
		NTPServerTime = nTPServerTime;
	}

	public String getNaavisSystemTime() {
		return NaavisSystemTime;
	}

	public void setNaavisSystemTime(String naavisSystemTime) {
		NaavisSystemTime = naavisSystemTime;
	}

	public String getServerTimeZone() {
		return serverTimeZone;
	}

	public void setServerTimeZone(String serverTimeZone) {
		this.serverTimeZone = serverTimeZone;
	}

	public String getCertificateDesc() {
		return certificateDesc;
	}

	public void setCertificateDesc(String certificateDesc) {
		this.certificateDesc = certificateDesc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
