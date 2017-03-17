package com.versawork.http.dataobject;

import java.io.Serializable;

/**
 * @author Sohaib
 * 
 */
public class NotificationsCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer scheduledAppointmentCount;
	protected Integer confirmedAppointmentCount;
	protected Integer hospitalNotificationCount;
	protected Integer warningCount;
	protected Integer outstandingBillCount;

	/**
	 * @return the outstandingBillCount
	 */
	public Integer getOutstandingBillCount() {
		return outstandingBillCount;
	}

	/**
	 * @param outstandingBillCount
	 *            the outstandingBillCount to set
	 */
	public void setOutstandingBillCount(Integer outstandingBillCount) {
		this.outstandingBillCount = outstandingBillCount;
	}

	public Integer getScheduledAppointmentCount() {
		if (scheduledAppointmentCount == null) {
			return 0;
		}
		return scheduledAppointmentCount;
	}

	public void setScheduledAppointmentCount(Integer scheduledAppointmentCount) {
		this.scheduledAppointmentCount = scheduledAppointmentCount;
	}

	public Integer getConfirmedAppointmentCount() {
		if (confirmedAppointmentCount == null) {
			return 0;
		}
		return confirmedAppointmentCount;
	}

	public void setConfirmedAppointmentCount(Integer confirmedAppointmentCount) {
		this.confirmedAppointmentCount = confirmedAppointmentCount;
	}

	public Integer getHospitalNotificationCount() {
		if (hospitalNotificationCount == null) {
			return 0;
		}
		return hospitalNotificationCount;
	}

	public void setHospitalNotificationCount(Integer hospitalNotificationCount) {
		this.hospitalNotificationCount = hospitalNotificationCount;
	}

	public Integer getWarningCount() {
		if (warningCount == null) {
			return 0;
		}
		return warningCount;
	}

	public void setWarningCount(Integer warningCount) {
		this.warningCount = warningCount;
	}

}
