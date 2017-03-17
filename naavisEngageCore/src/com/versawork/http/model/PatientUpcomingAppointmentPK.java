/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Sohaib
 */
@Embeddable
public class PatientUpcomingAppointmentPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "appointment_id")
	private int appointmentId;

	/*
	 * @Basic(optional = false)
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO)
	 * 
	 * @Column(name = "id") private int id;
	 */

	public PatientUpcomingAppointmentPK() {
	}

	public PatientUpcomingAppointmentPK(int accountId, int appointmentId/*
																		 * , int
																		 * id
																		 */) {
		this.accountId = accountId;
		this.appointmentId = appointmentId;
		/* this.id = id; */
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	/*
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; }
	 */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountId;
		hash += (int) appointmentId;
		/* hash += (int) id; */
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientUpcomingAppointmentPK)) {
			return false;
		}
		PatientUpcomingAppointmentPK other = (PatientUpcomingAppointmentPK) object;
		if (this.accountId != other.accountId) {
			return false;
		}
		if (this.appointmentId != other.appointmentId) {
			return false;
		}
		/*
		 * if (this.id != other.id) { return false; }
		 */
		return true;
	}

	@Override
	public String toString() {
		return "com.traceOn.http.model.PatientUpcomingAppointmentPK[ accountId=" + accountId + ", appointmentId="
				+ appointmentId /* +", id=" + id */+ " ]";
	}

}
