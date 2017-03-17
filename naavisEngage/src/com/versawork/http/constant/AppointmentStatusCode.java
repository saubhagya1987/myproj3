package com.versawork.http.constant;

public enum AppointmentStatusCode {

	BOOKED(1), ATTENDED(2), PENDING(3), NO_SHOW(4), CANCELLED(5), RESCHEDULED(6);

	private Integer code;

	private AppointmentStatusCode(int code) {
		this.code = code;
	}

	public AppointmentStatusCode getAppointmentStatus(int findCode) {
		AppointmentStatusCode result = null;
		for (AppointmentStatusCode statusCode : AppointmentStatusCode.values()) {
			if (statusCode.code == findCode) {
				result = statusCode;
				break;
			}
		}

		return result;
	}

	public Integer getCode() {
		return this.code;

	}

}
