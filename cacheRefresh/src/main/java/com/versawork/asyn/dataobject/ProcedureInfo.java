package com.versawork.asyn.dataobject;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.versawork.asyn.constant.VersaworkConstants;

/**
 * @author Dheeraj
 *
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProcedureInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String procedureId;
	protected String procedureName;
	protected String procedureCode;
	protected String dateAdded;

	public String getProcedureId() {

		return procedureId;
	}

	public void setProcedureId(String procedureId) {

		this.procedureId = procedureId;
	}

	public String getProcedureName() {

		return procedureName;
	}

	public void setProcedureName(String procedureName) {

		this.procedureName = procedureName;
	}

	public String getProcedureCode() {

		return procedureCode;
	}

	public void setProcedureCode(String procedureCode) {

		if (procedureCode != null) {
			this.procedureCode = procedureCode;
		} else {
			this.procedureCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getDateAdded() {

		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {

		this.dateAdded = dateAdded;
	}

}
