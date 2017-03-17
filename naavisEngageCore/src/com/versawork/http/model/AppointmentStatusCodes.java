/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "appointment_status_codes")
@NamedQueries({
		@NamedQuery(name = "AppointmentStatusCodes.findAll", query = "SELECT a FROM AppointmentStatusCodes a"),
		@NamedQuery(name = "AppointmentStatusCodes.findByStatusCode", query = "SELECT a FROM AppointmentStatusCodes a WHERE a.statusCode = :statusCode"),
		@NamedQuery(name = "AppointmentStatusCodes.findByStatusDescription", query = "SELECT a FROM AppointmentStatusCodes a WHERE a.statusDescription = :statusDescription") })
public class AppointmentStatusCodes implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "status_code")
	private Integer statusCode;
	@Basic(optional = false)
	@Column(name = "status_description")
	private String statusDescription;

	/*
	 * @OneToMany(mappedBy = "statusCode") private
	 * List<PatientUpcomingAppointment> patientUpcomingAppointmentList;
	 */
	public AppointmentStatusCodes() {
	}

	public AppointmentStatusCodes(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public AppointmentStatusCodes(Integer statusCode, String statusDescription) {
		this.statusCode = statusCode;
		this.statusDescription = statusDescription;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	/*
	 * public List<PatientUpcomingAppointment>
	 * getPatientUpcomingAppointmentList() { return
	 * patientUpcomingAppointmentList; }
	 * 
	 * public void
	 * setPatientUpcomingAppointmentList(List<PatientUpcomingAppointment>
	 * patientUpcomingAppointmentList) { this.patientUpcomingAppointmentList =
	 * patientUpcomingAppointmentList; }
	 */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (statusCode != null ? statusCode.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AppointmentStatusCodes)) {
			return false;
		}
		AppointmentStatusCodes other = (AppointmentStatusCodes) object;
		if ((this.statusCode == null && other.statusCode != null)
				|| (this.statusCode != null && !this.statusCode.equals(other.statusCode))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.AppointmentStatusCodes[ statusCode=" + statusCode + " ]";
	}

}
