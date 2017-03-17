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
public class FacilityProviderPK implements Serializable {
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
	@Column(name = "provider_id")
	private String providerId;

	public FacilityProviderPK() {
	}

	public FacilityProviderPK(int clientId, int clientDatabaseId, String providerId) {
		this.clientId = clientId;
		this.clientDatabaseId = clientDatabaseId;
		this.providerId = providerId;
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

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) clientId;
		hash += (int) clientDatabaseId;
		hash += (providerId != null ? providerId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof FacilityProviderPK)) {
			return false;
		}
		FacilityProviderPK other = (FacilityProviderPK) object;
		if (this.clientId != other.clientId) {
			return false;
		}
		if (this.clientDatabaseId != other.clientDatabaseId) {
			return false;
		}
		if ((this.providerId == null && other.providerId != null)
				|| (this.providerId != null && !this.providerId.equals(other.providerId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.FacilityProviderPK[ clientId=" + clientId + ", clientDatabaseId="
				+ clientDatabaseId + ", providerId=" + providerId + " ]";
	}

}
