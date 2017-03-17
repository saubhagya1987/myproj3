package com.versawork.asyn.dataobject;

import java.util.Date;

import com.versawork.asyn.constant.VersaworkConstants;
import com.versawork.asyn.utils.DateUtils;

public class PatientImagingInfo {

	private int accountId;
	private String dateAdded;
	private String examDate;
	private String examDates;
	private String examId;
	private String examMessage;
	private String reportNumber;
	private String examTechnologist;
	private String examName;
	private String orderingProvider;
	private int patientVisitId;
	private String sourceId;
	private String sourceName;

	public void setAccountId(int accountId) {

		this.accountId = accountId;
	}

	public void setDateAdded(String dateAdded) {

		this.dateAdded = dateAdded;

	}

	public void setExamDate(String examDate) {

		if (examDate != null) {
			this.examDate = examDate;
		} else {
			this.examDate = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}

	}

	public void setExamDates(Date examDates) {

		if (examDates != null)
			this.examDates = DateUtils.getFormatDate(examDates, "MM/dd/yyyy hh:mm a");
		// new SimpleDateFormat("MM/dd/yyyy hh:mm a",
		// Locale.ENGLISH).format(examDates);
		else
			this.examDates = VersaworkConstants.NOT_APPLICABLE.getMessage();
	}

	public void setExamId(String examId) {

		this.examId = examId;
	}

	public void setExamMessage(String examMessage) {

		if (examMessage != null) {
			this.examMessage = examMessage;
		} else {
			this.examMessage = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public void setReportNumber(String reportNumber) {

		if (reportNumber != null) {
			this.reportNumber = reportNumber;
		} else {
			this.reportNumber = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}

	}

	public void setExamTechnologist(String examTechnologist) {

		if (examTechnologist != null) {
			this.examTechnologist = examTechnologist;
		} else {
			this.examTechnologist = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public void setExamName(String examName) {

		if (examName != null) {
			this.examName = examName;
		} else {
			this.examName = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}

	}

	public void setOrderingProvider(String orderingProvider) {

		if (orderingProvider != null) {
			this.orderingProvider = orderingProvider;
		} else {
			this.orderingProvider = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public void setPatientVisitId(int patientVisitId) {

		this.patientVisitId = patientVisitId;
	}

	public void setSourceId(String sourceId) {

		if (sourceId != null) {
			this.sourceId = sourceId;
		} else {
			this.sourceId = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public void setSourceName(String sourceName) {

		this.sourceName = sourceName;
	}

	public int getAccountId() {

		return accountId;
	}

	public String getDateAdded() {

		return dateAdded;
	}

	public String getExamDate() {

		return examDate;
	}

	public String getExamDates() {

		return examDates;
	}

	public String getExamId() {

		return examId;
	}

	public String getExamMessage() {

		return examMessage;
	}

	public String getReportNumber() {

		return reportNumber;
	}

	public String getExamTechnologist() {

		return examTechnologist;
	}

	public String getExamName() {

		return examName;
	}

	public String getOrderingProvider() {

		return orderingProvider;
	}

	public int getPatientVisitId() {

		return patientVisitId;
	}

	public String getSourceId() {

		return sourceId;
	}

	public String getSourceName() {

		return sourceName;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateAdded == null) ? 0 : dateAdded.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientImagingInfo other = (PatientImagingInfo) obj;
		if (dateAdded == null) {
			if (other.dateAdded != null)
				return false;
		} else if (!dateAdded.equals(other.dateAdded))
			return false;
		return true;
	}

}
