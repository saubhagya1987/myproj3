package com.versawork.http.dataobject;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.versawork.http.model.FacilityService;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Facility {

	private String facilityName;
	private String facilityStreet;
	private String facilityAddess;
	private Integer clientDbId;
	private List<NsServicesList> nsServicesLists;
	private String facilityPhone;
	private Boolean isLinked;
	private String facilityUrl;
	private Boolean isParent;

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getFacilityUrl() {
		return facilityUrl;
	}

	public void setFacilityUrl(String facilityUrl) {
		this.facilityUrl = facilityUrl;
	}

	public Boolean getIsLinked() {
		return isLinked;
	}

	public void setIsLinked(Boolean isLinked) {
		this.isLinked = isLinked;
	}

	public String getFacilityPhone() {
		return facilityPhone;
	}

	public void setFacilityPhone(String facilityPhone) {
		this.facilityPhone = facilityPhone;
	}

	public List<NsServicesList> getNsServicesLists() {
		return nsServicesLists;
	}

	public void setNsServicesLists(List<NsServicesList> nsServicesLists) {
		this.nsServicesLists = nsServicesLists;
	}

	public String getFacilityStreet() {
		return facilityStreet;
	}

	public void setFacilityStreet(String facilityStreet) {
		this.facilityStreet = facilityStreet;
	}

	public Integer getClientDbId() {
		return clientDbId;
	}

	public void setClientDbId(Integer clientDbId) {
		this.clientDbId = clientDbId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getFacilityAddess() {
		return facilityAddess;
	}

	public void setFacilityAddess(String facilityAddess) {
		this.facilityAddess = facilityAddess;
	}

}
