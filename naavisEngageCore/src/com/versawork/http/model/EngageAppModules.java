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
@Table(name = "engage_app_modules")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "EngageAppModules.findAll", query = "SELECT e FROM EngageAppModules e"),
		@NamedQuery(name = "EngageAppModules.findByModuleId", query = "SELECT e FROM EngageAppModules e WHERE e.moduleId = :moduleId"),
		@NamedQuery(name = "EngageAppModules.findByModuleName", query = "SELECT e FROM EngageAppModules e WHERE e.moduleName = :moduleName") })
public class EngageAppModules implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "module_id")
	private Integer moduleId;
	@Basic(optional = false)
	@Column(name = "module_name")
	private String moduleName;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "moduleId")
	private List<EngageAppFeatures> engageAppFeaturesList;

	public EngageAppModules() {
	}

	public EngageAppModules(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public EngageAppModules(Integer moduleId, String moduleName) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@XmlTransient
	public List<EngageAppFeatures> getEngageAppFeaturesList() {
		return engageAppFeaturesList;
	}

	public void setEngageAppFeaturesList(List<EngageAppFeatures> engageAppFeaturesList) {
		this.engageAppFeaturesList = engageAppFeaturesList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (moduleId != null ? moduleId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EngageAppModules)) {
			return false;
		}
		EngageAppModules other = (EngageAppModules) object;
		if ((this.moduleId == null && other.moduleId != null)
				|| (this.moduleId != null && !this.moduleId.equals(other.moduleId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.EngageAppModules[ moduleId=" + moduleId + " ]";
	}

}
