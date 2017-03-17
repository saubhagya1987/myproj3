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
public class FacilityNoticePK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "facility_notice_id")
	private int facilityNoticeId;
	@Basic(optional = false)
	@Column(name = "client_id")
	private int clientId;
	@Basic(optional = false)
	@Column(name = "client_database_id")
	private int clientDatabaseId;

	public FacilityNoticePK() {
	}

	public FacilityNoticePK(int facilityNoticeId, int clientId, int clientDatabaseId) {
		this.facilityNoticeId = facilityNoticeId;
		this.clientId = clientId;
		this.clientDatabaseId = clientDatabaseId;
	}

	public int getFacilityNoticeId() {
		return facilityNoticeId;
	}

	public void setFacilityNoticeId(int facilityNoticeId) {
		this.facilityNoticeId = facilityNoticeId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(int clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) facilityNoticeId;
		hash += (int) clientId;
		hash += (int) clientDatabaseId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof FacilityNoticePK)) {
			return false;
		}
		FacilityNoticePK other = (FacilityNoticePK) object;
		if (this.facilityNoticeId != other.facilityNoticeId) {
			return false;
		}
		if (this.clientId != other.clientId) {
			return false;
		}
		if (this.clientDatabaseId != other.clientDatabaseId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.FacilityNoticePK[ facilityNoticeId=" + facilityNoticeId + ", clientId="
				+ clientId + ", clientDatabaseId=" + clientDatabaseId + " ]";
	}

}
