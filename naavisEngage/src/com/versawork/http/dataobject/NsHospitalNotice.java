package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsHospitalNotice implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String beginDate;
	protected String endDate;
	protected String noticeMessage;
	protected String email;
	protected String providerName;
	protected String hospitalName;
	protected Integer hospitalNoticeId;
	protected Integer clientId;
	protected Integer clientDatabaseId;
	protected Integer noticesCount;
	protected Boolean billPayMessage;

	/**
	 * @return the billPayMessage
	 */
	public Boolean getisBillPayMessage() {
		return billPayMessage;
	}

	/**
	 * @param billPayMessage
	 *            the billPayMessage to set
	 */
	public void setBillPayMessage(Boolean billPayMessage) {
		this.billPayMessage = billPayMessage;
	}

	public Integer getNoticesCount() {
		return noticesCount;
	}

	public void setNoticesCount(Integer noticesCount) {
		this.noticesCount = noticesCount;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(Integer clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	public Integer getHospitalNoticeId() {
		return hospitalNoticeId;
	}

	public void setHospitalNoticeId(Integer hospitalNoticeId) {
		this.hospitalNoticeId = hospitalNoticeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getNoticeMessage() {
		return noticeMessage;
	}

	public void setNoticeMessage(String noticeMessage) {
		this.noticeMessage = noticeMessage;
	}

}
