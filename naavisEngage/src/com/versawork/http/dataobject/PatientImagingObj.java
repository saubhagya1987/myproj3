package com.versawork.http.dataobject;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Sohaib
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PatientImagingObj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sourceId;
	private String sourceName;
	private String examDate;
	private List<PatientImagingObject> patientImagingObject;

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public List<PatientImagingObject> getPatientImagingObject() {
		return patientImagingObject;
	}

	public void setPatientImagingObject(List<PatientImagingObject> patientImagingObject) {
		this.patientImagingObject = patientImagingObject;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

}
