/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "engage_client_feature")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "EngageClientToFeature.findAll", query = "SELECT e FROM EngageClientToFeature e"),
		@NamedQuery(name = "EngageClientToFeature.findById", query = "SELECT e FROM EngageClientToFeature e WHERE e.id = :id"),
		@NamedQuery(name = "EngageClientToFeature.findByClientDatabaseIdAndVersion", query = "SELECT e FROM EngageClientToFeature e WHERE e.engageClientFeaturePrimaryKey.clientDatabaseId = :clientDatabaseId and e.engageClientFeaturePrimaryKey.version = :version ORDER BY e.featureOrder ASC") })
public class EngageClientToFeature {
	/*@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "client_id")
	private int clientId;*/
	
	@EmbeddedId
	private EngageClientFeaturePrimaryKey engageClientFeaturePrimaryKey;
	
	@JoinColumn(name = "feature_id", referencedColumnName = "feature_id")
	@ManyToOne(optional = false)
	private EngageAppFeatures featureId;
	@Column(name = "feature_order")
	private int featureOrder;

	public int getFeatureOrder() {
		return featureOrder;
	}

	public void setFeatureOrder(int featureOrder) {
		this.featureOrder = featureOrder;
	}

	public EngageClientToFeature() {
	}

	/*public EngageClientToFeature(Integer id) {
		this.id = id;
	}

	public EngageClientToFeature(Integer id, int clientId) {
		this.id = id;
		this.clientId = clientId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}*/

	public EngageAppFeatures getFeatureId() {
		return featureId;
	}

	public void setFeatureId(EngageAppFeatures featureId) {
		this.featureId = featureId;
	}

	

	public EngageClientFeaturePrimaryKey getEngageClientFeaturePrimaryKey() {
		return engageClientFeaturePrimaryKey;
	}

	public void setEngageClientFeaturePrimaryKey(EngageClientFeaturePrimaryKey engageClientFeaturePrimaryKey) {
		this.engageClientFeaturePrimaryKey = engageClientFeaturePrimaryKey;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.EngageClientToFeature[ id=" + engageClientFeaturePrimaryKey.getId() + " ]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((engageClientFeaturePrimaryKey == null) ? 0 : engageClientFeaturePrimaryKey.hashCode());
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
		EngageClientToFeature other = (EngageClientToFeature) obj;
		if (engageClientFeaturePrimaryKey == null) {
			if (other.engageClientFeaturePrimaryKey != null)
				return false;
		} else if (!engageClientFeaturePrimaryKey.equals(other.engageClientFeaturePrimaryKey))
			return false;
		return true;
	}

}
