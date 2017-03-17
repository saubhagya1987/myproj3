package com.versawork.asyn.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.versawork.asyn.constant.VersaworkConstants;

/**
 * @author Dheeraj
 *
 */

/* @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) */
@JsonSerialize()
public class PatientProblemInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String problemId;
	protected String problemName;
	protected String problemCode;
	protected String dateAdded;
	protected String status;
	protected String statusCode;
	protected String sourceName;

	public String getSourceName() {

		return sourceName;
	}

	public void setSourceName(String sourceName) {

		this.sourceName = sourceName;
	}

	public String getStatusCode() {

		return statusCode;
	}

	public void setStatusCode(String statusCode) {

		if (statusCode != null) {
			this.statusCode = statusCode;
		} else {
			this.statusCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getProblemId() {

		return problemId;
	}

	public void setProblemId(String problemId) {

		this.problemId = problemId;
	}

	public String getProblemName() {

		return problemName;
	}

	public void setProblemName(String problemName) {

		if (problemName != null) {
			this.problemName = problemName;
		} else {
			this.problemName = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getProblemCode() {

		return problemCode;
	}

	public void setProblemCode(String problemCode) {

		if (problemCode != null) {
			this.problemCode = problemCode;
		} else {
			this.problemCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getDateAdded() {

		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {

		this.dateAdded = dateAdded;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		if (status != null) {
			this.status = status;
		} else {
			this.status = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((problemName == null) ? 0 : problemName.hashCode());
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
		PatientProblemInfo other = (PatientProblemInfo) obj;
		if (problemName == null) {
			if (other.problemName != null)
				return false;
		} else if (!problemName.equals(other.problemName))
			return false;
		return true;
	}
}
