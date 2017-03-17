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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "patient_upcoming_appointment")
@NamedQueries({
		@NamedQuery(name = "PatientUpcomingAppointment.findAll", query = "SELECT p FROM PatientUpcomingAppointment p"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByAccountId", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.patientUpcomingAppointmentPK.accountId = :accountId"),
		@NamedQuery(name = "PatientUpcomingAppointment.findBySourceId", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientUpcomingAppointment.findBySourceName", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByAppointmentId", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.patientUpcomingAppointmentPK.appointmentId = :appointmentId and p.patientUpcomingAppointmentPK.accountId = :accountId"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByProviderName", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.providerName = :providerName"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByTypeDescription", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.typeDescription = :typeDescription"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByLocationName", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.locationName = :locationName"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByAppointmentDate", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.appointmentDate = :appointmentDate"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByStatus", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.status = :status"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByAccountIdAndDate", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.clientDatabaseId = :clientDatabaseId and p.patientUpcomingAppointmentPK.accountId = :accountId and convert(varchar(10),p.appointmentDate,101)  between :fromDate and :toDate AND p.statusCode = :statusCode ORDER BY p.appointmentDate DESC"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByAccountIdAndFlag", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.clientDatabaseId = :clientDatabaseId and p.patientUpcomingAppointmentPK.accountId = :accountId and p.isRead = :isRead ORDER BY p.appointmentDate DESC"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByStatusUnprocessed", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.clientDatabaseId = :clientDatabaseId and p.status is null order by p.appointmentDate desc"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByDateAdded", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByFromDateFuture", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.patientUpcomingAppointmentPK.accountId = :accountId and convert(varchar(10),p.appointmentDate,101)  >= :fromDate and p.statusCode = :statusCode ORDER BY p.appointmentDate DESC"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByProviderId", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.providerId = :providerId"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByAppointmentTime", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.appointmentTime = :appointmentTime"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByAccountIdAndFromDate", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.clientDatabaseId = :clientDatabaseId and p.patientUpcomingAppointmentPK.accountId = :accountId and convert(varchar(10),p.appointmentDate,101)  >= :todaysDate ORDER BY p.appointmentDate ASC"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByAccountIdAndFromPastDate", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.clientDatabaseId = :clientDatabaseId and p.patientUpcomingAppointmentPK.accountId = :accountId and convert(varchar(10),p.appointmentDate,101)  < :todaysDate ORDER BY p.appointmentDate DESC"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByAccountIdAndAppointmentDate", query = "SELECT p FROM PatientUpcomingAppointment p WHERE p.clientDatabaseId = :clientDatabaseId and p.patientUpcomingAppointmentPK.accountId = :accountId and convert(varchar(10),p.appointmentDate,101)  >= :todaysDate ORDER BY p.appointmentDate DESC"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByProviderNameAndId", query = "SELECT p FROM PatientUpcomingAppointment p WHERE  p.providerName LIKE :providerName and p.clientDatabaseId = :clientDatabaseId and p.patientUpcomingAppointmentPK.accountId = :accountId and convert(varchar(10),p.appointmentDate,101)  >= :todaysDate ORDER BY p.appointmentDate DESC"),
		@NamedQuery(name = "PatientUpcomingAppointment.findByProviderNameAndPastDate", query = "SELECT p FROM PatientUpcomingAppointment p WHERE  p.providerName LIKE :providerName and p.clientDatabaseId = :clientDatabaseId and p.patientUpcomingAppointmentPK.accountId = :accountId and convert(varchar(10),p.appointmentDate,101)  < :todaysDate ORDER BY p.appointmentDate DESC")})
public class PatientUpcomingAppointment implements Serializable {

	@Basic(optional = false)
	@Column(name = "client_database_id")
	private int clientDatabaseId;
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientUpcomingAppointmentPK patientUpcomingAppointmentPK;
	@Column(name = "source_id")
	private String sourceId;
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	@Column(name = "provider_name")
	private String providerName;
	@Column(name = "type_description")
	private String typeDescription;
	@Column(name = "location_name")
	private String locationName;
	@Column(name = "appointment_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date appointmentDate;
	@Basic(optional = false)
	@Column(name = "is_read")
	private Boolean isRead;
	@Column(name = "status")
	@Temporal(TemporalType.TIMESTAMP)
	private Date status;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Basic(optional = false)
	@Column(name = "provider_id")
	private String providerId;
	@Basic(optional = false)
	@Column(name = "appointment_time")
	@Temporal(TemporalType.TIME)
	private Date appointmentTime;
	@Column(name = "status_code")
	private Integer statusCode;

	/*
	 * @JoinColumns({
	 * 
	 * @JoinColumn(name = "account_id", referencedColumnName = "account_id",
	 * insertable = false, updatable = false),
	 * 
	 * @JoinColumn(name = "appointment_id", referencedColumnName =
	 * "appointment_id", insertable = false, updatable = false)})
	 */
	/*
	 * @JoinColumn(name = "account_id", referencedColumnName = "account_id",
	 * insertable = false, updatable = false)
	 */
	/* @ManyToOne(optional = false) */
	/* private Account account; */
	/*
	 * @Column(name = "account_id") private Integer account;
	 * 
	 * public Integer getAccount() { return account; }
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	/*
	 * public void setAccount(Integer account) { this.account = account; }
	 */

	public PatientUpcomingAppointment() {
	}

	public PatientUpcomingAppointment(PatientUpcomingAppointmentPK patientUpcomingAppointmentPK) {
		this.patientUpcomingAppointmentPK = patientUpcomingAppointmentPK;
	}

	public PatientUpcomingAppointment(PatientUpcomingAppointmentPK patientUpcomingAppointmentPK, String sourceName,
			Date dateAdded, String providerId, Date appointmentTime, int clientDatabaseId, boolean isRead) {
		this.patientUpcomingAppointmentPK = patientUpcomingAppointmentPK;
		this.sourceName = sourceName;
		this.dateAdded = dateAdded;
		this.providerId = providerId;
		this.appointmentTime = appointmentTime;
		this.clientDatabaseId = clientDatabaseId;
		this.isRead = isRead;
	}

	public PatientUpcomingAppointment(int accountId, int appointmentId/* , int id */) {
		this.patientUpcomingAppointmentPK = new PatientUpcomingAppointmentPK(accountId, appointmentId/*
																									 * ,
																									 * id
																									 */);
	}

	public PatientUpcomingAppointmentPK getPatientUpcomingAppointmentPK() {
		return patientUpcomingAppointmentPK;
	}

	public void setPatientUpcomingAppointmentPK(PatientUpcomingAppointmentPK patientUpcomingAppointmentPK) {
		this.patientUpcomingAppointmentPK = patientUpcomingAppointmentPK;
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

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Date getStatus() {
		return status;
	}

	public void setStatus(Date status) {
		this.status = status;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public int getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(int clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	public boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientUpcomingAppointmentPK != null ? patientUpcomingAppointmentPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientUpcomingAppointment)) {
			return false;
		}
		PatientUpcomingAppointment other = (PatientUpcomingAppointment) object;
		if ((this.patientUpcomingAppointmentPK == null && other.patientUpcomingAppointmentPK != null)
				|| (this.patientUpcomingAppointmentPK != null && !this.patientUpcomingAppointmentPK
						.equals(other.patientUpcomingAppointmentPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.traceOn.http.model.PatientUpcomingAppointment[ patientUpcomingAppointmentPK="
				+ patientUpcomingAppointmentPK + " ]";
	}

}
