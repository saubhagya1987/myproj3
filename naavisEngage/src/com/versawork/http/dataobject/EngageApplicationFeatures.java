package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Sohaib
 * 
 */
public class EngageApplicationFeatures implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer clientId;
	private Integer featureId;
	private Integer moduleId;
	private String moduleName;
	private String featureName;

	private Map<Integer, String> featureListing;
	private Map<Integer, String> moduleListing;
	private Map<Map<Integer, String>, Map<Integer, String>> featureMap;

	public Map<Integer, String> getFeatureListing() {
		return featureListing;
	}

	public void setFeatureListing(Map<Integer, String> featureListing) {
		this.featureListing = featureListing;
	}

	public Map<Integer, String> getModuleListing() {
		return moduleListing;
	}

	public void setModuleListing(Map<Integer, String> moduleListing) {
		this.moduleListing = moduleListing;
	}

	public Map<Map<Integer, String>, Map<Integer, String>> getFeatureMap() {
		return featureMap;
	}

	public void setFeatureMap(Map<Map<Integer, String>, Map<Integer, String>> featureMap) {
		this.featureMap = featureMap;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
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

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

}
