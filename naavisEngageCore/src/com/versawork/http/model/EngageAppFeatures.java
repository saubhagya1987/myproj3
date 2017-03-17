/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "engage_app_features")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "EngageAppFeatures.findAll", query = "SELECT e FROM EngageAppFeatures e"),
		@NamedQuery(name = "EngageAppFeatures.findByFeatureId", query = "SELECT e FROM EngageAppFeatures e WHERE e.featureId = :featureId"),
		@NamedQuery(name = "EngageAppFeatures.findByFeatureName", query = "SELECT e FROM EngageAppFeatures e WHERE e.featureName = :featureName") })
public class EngageAppFeatures implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "feature_id")
	private Integer featureId;
	@Basic(optional = false)
	@Column(name = "feature_name")
	private String featureName;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "featureId")
	private List<EngageClientToFeature> engageClientToFeatureList;
	@JoinColumn(name = "module_id", referencedColumnName = "module_id")
	@ManyToOne(optional = false)
	private EngageAppModules moduleId;

	public EngageAppFeatures() {
	}

	public EngageAppFeatures(Integer featureId) {
		this.featureId = featureId;
	}

	public EngageAppFeatures(Integer featureId, String featureName) {
		this.featureId = featureId;
		this.featureName = featureName;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	@XmlTransient
	public List<EngageClientToFeature> getEngageClientToFeatureList() {
		return engageClientToFeatureList;
	}

	public void setEngageClientToFeatureList(List<EngageClientToFeature> engageClientToFeatureList) {
		this.engageClientToFeatureList = engageClientToFeatureList;
	}

	public EngageAppModules getModuleId() {
		return moduleId;
	}

	public void setModuleId(EngageAppModules moduleId) {
		this.moduleId = moduleId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (featureId != null ? featureId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EngageAppFeatures)) {
			return false;
		}
		EngageAppFeatures other = (EngageAppFeatures) object;
		if ((this.featureId == null && other.featureId != null)
				|| (this.featureId != null && !this.featureId.equals(other.featureId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.EngageAppFeatures[ featureId=" + featureId + " ]";
	}

}
