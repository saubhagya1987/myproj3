package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EngageModule implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer moduleId;
	private String moduleName;
	private List<EngageFeatures> featuresList = new ArrayList<EngageFeatures>();
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
	public List<EngageFeatures> getFeaturesList() {
		return featuresList;
	}
	public void setFeaturesList(List<EngageFeatures> featuresList) {
		this.featuresList = featuresList;
	}		
}
