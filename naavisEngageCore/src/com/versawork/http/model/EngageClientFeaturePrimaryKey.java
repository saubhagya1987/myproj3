package com.versawork.http.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class EngageClientFeaturePrimaryKey implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "client_database_id")
	private int clientDatabaseId;


	@Basic(optional = false)
	@Column(name = "version")
	private String version; 
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(int clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clientDatabaseId;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EngageClientFeaturePrimaryKey other = (EngageClientFeaturePrimaryKey) obj;
		if (clientDatabaseId != other.clientDatabaseId)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
}
