package com.versawork.http.dataobject;

import java.io.Serializable;

public class NotificationCounter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer patientAppointmentRequestId;
	protected Integer appointmentId;
	protected Integer moduleId;
	protected Integer accountId;

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getPatientAppointmentRequestId() {
		return patientAppointmentRequestId;
	}

	public void setPatientAppointmentRequestId(Integer patientAppointmentRequestId) {
		this.patientAppointmentRequestId = patientAppointmentRequestId;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

}
