package com.versawork.http.cacheDataObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sohaib
 * 
 */
public class CPatientImaging implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sourceId;
	private String sourceName;
	private String examName;
	private String orderingProvider;
	private String examTechnologist;
	private Date examDate;
	private String examMessage;
	private Date dateAdded;
	private int accountId;
	private int patientVisitId;
	private String examId;

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

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamMessage() {
		return examMessage;
	}

	public void setExamMessage(String examMessage) {
		this.examMessage = examMessage;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
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

}
