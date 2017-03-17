package com.versawork.http.dataobject;

import java.io.Serializable;

public class WarningMessages implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String warningMessage;
	protected String warningDate;
	protected String warningDay;
	protected Boolean isWarningPresent;

	public Boolean getIsWarningPresent() {
		return isWarningPresent;
	}

	public void setIsWarningPresent(Boolean isWarningPresent) {
		this.isWarningPresent = isWarningPresent;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public String getWarningDate() {
		return warningDate;
	}

	public void setWarningDate(String warningDate) {
		this.warningDate = warningDate;
	}

	public String getWarningDay() {
		return warningDay;
	}

	public void setWarningDay(String warningDay) {
		this.warningDay = warningDay;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getWarningMessage();
	}

}
