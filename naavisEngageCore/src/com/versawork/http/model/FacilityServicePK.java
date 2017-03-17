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
public class FacilityServicePK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "client_id")
	private int clientId;
	@Basic(optional = false)
	@Column(name = "client_database_id")
	private int clientDatabaseId;
	@Basic(optional = false)
	@Column(name = "service_id")
	private String serviceId;

	public FacilityServicePK() {
	}

	public FacilityServicePK(int clientId, int clientDatabaseId, String serviceId) {
		this.clientId = clientId;
		this.clientDatabaseId = clientDatabaseId;
		this.serviceId = serviceId;
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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) clientId;
		hash += (int) clientDatabaseId;
		hash += (serviceId != null ? serviceId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof FacilityServicePK)) {
			return false;
		}
		FacilityServicePK other = (FacilityServicePK) object;
		if (this.clientId != other.clientId) {
			return false;
		}
		if (this.clientDatabaseId != other.clientDatabaseId) {
			return false;
		}
		if ((this.serviceId == null && other.serviceId != null)
				|| (this.serviceId != null && !this.serviceId.equals(other.serviceId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.FacilityServicePK[ clientId=" + clientId + ", clientDatabaseId="
				+ clientDatabaseId + ", serviceId=" + serviceId + " ]";
	}

}
