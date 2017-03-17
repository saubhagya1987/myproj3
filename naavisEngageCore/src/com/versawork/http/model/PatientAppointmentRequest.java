/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "patient_appointment_request")
@NamedQueries({
		@NamedQuery(name = "PatientAppointmentRequest.findAll", query = "SELECT p FROM PatientAppointmentRequest p"),
		@NamedQuery(name = "PatientAppointmentRequest.findByPatientAppointmentRequestId", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.patientAppointmentRequestId = :patientAppointmentRequestId"),
		@NamedQuery(name = "PatientAppointmentRequest.findByAppointmentDate", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.appointmentDate = :appointmentDate"),
		@NamedQuery(name = "PatientAppointmentRequest.findByAppointmentTime", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.appointmentTime = :appointmentTime"),
		@NamedQuery(name = "PatientAppointmentRequest.findByContactPhone", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.contactPhone = :contactPhone"),
		@NamedQuery(name = "PatientAppointmentRequest.findByContactEmail", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.contactEmail = :contactEmail"),
		@NamedQuery(name = "PatientAppointmentRequest.findByProviderId", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.providerId = :providerId"),
		@NamedQuery(name = "PatientAppointmentRequest.findByProviderName", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.providerName = :providerName"),
		@NamedQuery(name = "PatientAppointmentRequest.findByComment", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.comment = :comment"),
		@NamedQuery(name = "PatientAppointmentRequest.findByFromToDateSchdle", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.accountId = :accountId and convert(varchar(10),p.appointmentDate,101)  between :fromDate and :toDate ORDER BY p.appointmentDate DESC"),
		@NamedQuery(name = "PatientAppointmentRequest.findByFromDateFuture", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.accountId = :accountId and convert(varchar(10),p.appointmentDate,101)  >= :fromDate ORDER BY p.appointmentDate DESC"),
		@NamedQuery(name = "PatientAppointmentRequest.findByAccountFromFlag", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.accountId = :accountId and p.isRead = :isRead ORDER BY p.appointmentDate DESC"),
		@NamedQuery(name = "PatientAppointmentRequest.findByRequestSentDate", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.requestSentDate = :requestSentDate"),
		@NamedQuery(name = "PatientAppointmentRequest.findByDateAdded", query = "SELECT p FROM PatientAppointmentRequest p WHERE p.dateAdded = :dateAdded") })
public class PatientAppointmentRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patient_appointment_request_id")
	private Integer patientAppointmentRequestId;
	@Column(name = "appointment_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date appointmentDate;
	@Basic(optional = false)
	@Column(name = "appointment_time")
	@Temporal(TemporalType.TIME)
	private Date appointmentTime;
	@Basic(optional = false)
	@Column(name = "contact_phone")
	private String contactPhone;
	@Basic(optional = false)
	@Column(name = "contact_email")
	private String contactEmail;
	@Column(name = "provider_id")
	private String providerId;
	@Column(name = "provider_name")
	private String providerName;
	@Column(name = "comment")
	private String comment;
	@Column(name = "request_sent_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestSentDate;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Basic(optional = false)
	@Column(name = "is_read")
	private Boolean isRead;

	@Basic(optional = false)
	@Column(name = "account_id")
	private Integer accountId;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public PatientAppointmentRequest() {
	}

	public PatientAppointmentRequest(Integer patientAppointmentRequestId) {
		this.patientAppointmentRequestId = patientAppointmentRequestId;
	}

	public PatientAppointmentRequest(Integer patientAppointmentRequestId, Date appointmentTime, String contactPhone,
			String contactEmail, Date dateAdded) {
		this.patientAppointmentRequestId = patientAppointmentRequestId;
		this.appointmentTime = appointmentTime;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.dateAdded = dateAdded;
	}

	public Integer getPatientAppointmentRequestId() {
		return patientAppointmentRequestId;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public void setPatientAppointmentRequestId(Integer patientAppointmentRequestId) {
		this.patientAppointmentRequestId = patientAppointmentRequestId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
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

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getRequestSentDate() {
		return requestSentDate;
	}

	public void setRequestSentDate(Date requestSentDate) {
		this.requestSentDate = requestSentDate;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientAppointmentRequestId != null ? patientAppointmentRequestId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientAppointmentRequest)) {
			return false;
		}
		PatientAppointmentRequest other = (PatientAppointmentRequest) object;
		if ((this.patientAppointmentRequestId == null && other.patientAppointmentRequestId != null)
				|| (this.patientAppointmentRequestId != null && !this.patientAppointmentRequestId
						.equals(other.patientAppointmentRequestId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientAppointmentRequest[ patientAppointmentRequestId="
				+ patientAppointmentRequestId + " ]";
	}

}
