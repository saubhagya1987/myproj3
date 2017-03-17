package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TestResultInfo implements Serializable{
	private static final long serialVersionUID = 1L;	
	protected String name;
	protected String code;
	protected String date;
	protected String sourceName;
	protected String type;
	protected int ResultTypeID;
	
	protected String normalRange;
	protected String testName;
	protected String labResult;
	protected String labCode;
	
	protected String orderingProvider;
	protected String examTechnologist;
	protected String reportNumber;
	protected String examMessage;
	protected String examName;
	
	protected Boolean rangeOutOfBound;
	
	protected Integer accountId;
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Boolean getRangeOutOfBound() {
		return rangeOutOfBound;
	}
	public void setRangeOutOfBound(Boolean rangeOutOfBound) {
		this.rangeOutOfBound = rangeOutOfBound;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
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
	public String getReportNumber() {
		return reportNumber;
	}
	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}
	public String getExamMessage() {
		return examMessage;
	}
	public void setExamMessage(String examMessage) {
		this.examMessage = examMessage;
	}
	public String getNormalRange() {
		return normalRange;
	}
	public void setNormalRange(String normalRange) {
		this.normalRange = normalRange;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getLabResult() {
		return labResult;
	}
	public void setLabResult(String labResult) {
		this.labResult = labResult;
	}
	public int getResultTypeID() {
		return ResultTypeID;
	}
	public void setResultTypeID(int resultTypeID) {
		ResultTypeID = resultTypeID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
	
}
