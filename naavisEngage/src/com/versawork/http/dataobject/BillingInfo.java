package com.versawork.http.dataobject;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BillingInfo {
	private String invoiceNumber;
	private String billingResponse;
	private String billingStatus;
	private String amountPaid;
	private String datTime;

	/**
	 * @return the datTime
	 */
	public String getDatTime() {
		return datTime;
	}

	/**
	 * @param datTime
	 *            the datTime to set
	 */
	public void setDatTime(String datTime) {
		this.datTime = datTime;
	}

	/**
	 * @return the amountPaid
	 */
	public String getAmountPaid() {
		return amountPaid;
	}

	/**
	 * @param amountPaid
	 *            the amountPaid to set
	 */
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * @return the billingResponse
	 */
	public String getBillingResponse() {
		return billingResponse;
	}

	/**
	 * @param billingResponse
	 *            the billingResponse to set
	 */
	public void setBillingResponse(String billingResponse) {
		this.billingResponse = billingResponse;
	}

	/**
	 * @return the billingStatus
	 */
	public String getBillingStatus() {
		return billingStatus;
	}

	/**
	 * @param billingStatus
	 *            the billingStatus to set
	 */
	public void setBillingStatus(String billingStatus) {
		this.billingStatus = billingStatus;
	}

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

}
