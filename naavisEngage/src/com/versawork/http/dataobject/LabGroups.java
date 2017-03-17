package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sohaib
 * 
 */
public class LabGroups implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String groupName;
	public String groupId;
	public String groupDate;
	public String sourceName;
	public List<NsPatientLabResult> labResultList;

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupDate() {
		return groupDate;
	}

	public void setGroupDate(String groupDate) {
		this.groupDate = groupDate;
	}

	public List<NsPatientLabResult> getLabResultList() {
		return labResultList;
	}

	public void setLabResultList(List<NsPatientLabResult> labResultList) {
		this.labResultList = labResultList;
	}

}
