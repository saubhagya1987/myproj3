package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Sohaib
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PatientImagingObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int accountId;
	private int patientVisitId;
	private String examId;
	private String sourceId;
	private String sourceName;
	private String examName;
	private String orderingProvider;
	private String examTechnologist;
	private String examDate;
	private String examMessage;
	private String dateAdded;
	private Date examDates;
	private Date dateAddeds;
	private String reportNumber;

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getExamDates() {
		return examDates;
	}

	public void setExamDates(Date examDates) {
		this.examDates = examDates;
	}

	public Date getDateAddeds() {
		return dateAddeds;
	}

	public void setDateAddeds(Date dateAddeds) {
		this.dateAddeds = dateAddeds;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getPatientVisitId() {
		return patientVisitId;
	}

	public void setPatientVisitId(int patientVisitId) {
		this.patientVisitId = patientVisitId;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getOrderingProvider() {
		return orderingProvider;
	}

	public void setOrderingProvider(String orderingProvider) {
		this.orderingProvider = orderingProvider;
	}

	public String getExamTechnologist() {
		return examTechnologist;
	}

	public void setExamTechnologist(String examTechnologist) {
		this.examTechnologist = examTechnologist;
	}

	public String getExamMessage() {
		return examMessage;
	}

	public void setExamMessage(String examMessage) {
		this.examMessage = examMessage;
	}

}
