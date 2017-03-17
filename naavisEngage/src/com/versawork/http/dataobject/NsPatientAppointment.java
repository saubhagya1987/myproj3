package com.versawork.http.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsPatientAppointment implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String appointmentDate;
	protected String appointmentTime;
	protected String contactPhone;
	protected String contactEmail;
	protected String providerId;
	protected String comment;
	protected String requestSentTime;
	protected String requestSentDate;
	protected String patientName;
	protected String providerName;
	protected String providerEmail;
	protected String confirmationDate;
	protected String confirmedBy;
	protected String providerAddress;
	protected String providerAddress2;
	protected String providerAddress3;
	protected Integer appointmentId;
	protected Integer patientAppointmentRequestId;
	protected String sourceName;
	protected Boolean isAppointment;
	protected Integer accountId;
	

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Boolean getIsAppointment() {
		return isAppointment;
	}

	public void setIsAppointment(Boolean isAppointment) {
		this.isAppointment = isAppointment;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getRequestSentTime() {
		return requestSentTime;
	}

	public void setRequestSentTime(String requestSentTime) {
		this.requestSentTime = requestSentTime;
	}

	protected boolean isviewed;

	public boolean isIsviewed() {
		return isviewed;
	}

	public void setIsviewed(boolean isviewed) {
		this.isviewed = isviewed;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Integer getPatientAppointmentRequestId() {
		return patientAppointmentRequestId;
	}

	public void setPatientAppointmentRequestId(Integer patientAppointmentRequestId) {
		this.patientAppointmentRequestId = patientAppointmentRequestId;
	}

	public String getProviderAddress3() {
		return providerAddress3;
	}

	public void setProviderAddress3(String providerAddress3) {
		this.providerAddress3 = providerAddress3;
	}

	public String getProviderAddress2() {
		return providerAddress2;
	}

	public void setProviderAddress2(String providerAddress2) {
		this.providerAddress2 = providerAddress2;
	}

	public String getProviderAddress() {
		return providerAddress;
	}

	public void setProviderAddress(String providerAddress) {
		this.providerAddress = providerAddress;
	}

	public String getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(String confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public String getConfirmedBy() {
		return confirmedBy;
	}

	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderEmail() {
		return providerEmail;
	}

	public void setProviderEmail(String providerEmail) {
		this.providerEmail = providerEmail;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRequestSentDate() {
		return requestSentDate;
	}

	public void setRequestSentDate(String requestSentDate) {
		this.requestSentDate = requestSentDate;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

}
