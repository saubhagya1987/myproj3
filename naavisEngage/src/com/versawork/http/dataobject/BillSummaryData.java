package com.versawork.http.dataobject;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BillSummaryData {

	private String sourceName;
	private String invoiceNumber; // Bill Id
	private String accountNumber; // visitIdentifier
	private String sourceId;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postalCode;
	private String insuranceProvider;
	private String encounterStartDate;
	private String encounterEndDate;
	private String billDate;
	private String totalDue;
	private Integer clientId;
	private String sourceUpdated;
	private String dateAdded;
	private String visitIdentifier;
	private List<BillDetailData> billDetailDataList;

	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber
	 *            the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the visitIdentifier
	 */
	public String getVisitIdentifier() {
		return visitIdentifier;
	}

	/**
	 * @param visitIdentifier
	 *            the visitIdentifier to set
	 */
	public void setVisitIdentifier(String visitIdentifier) {
		this.visitIdentifier = visitIdentifier;
	}

	/**
	 * @return the billDetailDataList
	 */
	public List<BillDetailData> getBillDetailDataList() {
		return billDetailDataList;
	}

	/**
	 * @param billDetailDataList
	 *            the billDetailDataList to set
	 */
	public void setBillDetailDataList(List<BillDetailData> billDetailDataList) {
		this.billDetailDataList = billDetailDataList;
	}

	/**
	 * @return the sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * @param sourceName
	 *            the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	/**
	 * @return the sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId
	 *            the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the insuranceProvider
	 */
	public String getInsuranceProvider() {
		return insuranceProvider;
	}

	/**
	 * @param insuranceProvider
	 *            the insuranceProvider to set
	 */
	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}

	/**
	 * @return the encounterStartDate
	 */
	public String getEncounterStartDate() {
		return encounterStartDate;
	}

	/**
	 * @param encounterStartDate
	 *            the encounterStartDate to set
	 */
	public void setEncounterStartDate(String encounterStartDate) {
		this.encounterStartDate = encounterStartDate;
	}

	/**
	 * @return the encounterEndDate
	 */
	public String getEncounterEndDate() {
		return encounterEndDate;
	}

	/**
	 * @param encounterEndDate
	 *            the encounterEndDate to set
	 */
	public void setEncounterEndDate(String encounterEndDate) {
		this.encounterEndDate = encounterEndDate;
	}

	/**
	 * @return the billDate
	 */
	public String getBillDate() {
		return billDate;
	}

	/**
	 * @param billDate
	 *            the billDate to set
	 */
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	/**
	 * @return the totalDue
	 */
	public String getTotalDue() {
		return totalDue;
	}

	/**
	 * @param totalDue
	 *            the totalDue to set
	 */
	public void setTotalDue(String totalDue) {
		this.totalDue = totalDue;
	}

	/**
	 * @return the clientId
	 */
	public Integer getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the sourceUpdated
	 */
	public String getSourceUpdated() {
		return sourceUpdated;
	}

	/**
	 * @param sourceUpdated
	 *            the sourceUpdated to set
	 */
	public void setSourceUpdated(String sourceUpdated) {
		this.sourceUpdated = sourceUpdated;
	}

	/**
	 * @return the dateAdded
	 */
	public String getDateAdded() {
		return dateAdded;
	}

	/**
	 * @param dateAdded
	 *            the dateAdded to set
	 */
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

}
