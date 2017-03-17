package com.versawork.http.dataobject;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BillDetailData {
	private String billDescription;
	private String quantity;
	private String detailAmount;
	private Integer clientId;
	private String sourceUpdated;
	private String dateAdded;

	/**
	 * @return the billDescription
	 */
	public String getBillDescription() {
		return billDescription;
	}

	/**
	 * @param billDescription
	 *            the billDescription to set
	 */
	public void setBillDescription(String billDescription) {
		this.billDescription = billDescription;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the detailAmount
	 */
	public String getDetailAmount() {
		return detailAmount;
	}

	/**
	 * @param detailAmount
	 *            the detailAmount to set
	 */
	public void setDetailAmount(String detailAmount) {
		this.detailAmount = detailAmount;
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
