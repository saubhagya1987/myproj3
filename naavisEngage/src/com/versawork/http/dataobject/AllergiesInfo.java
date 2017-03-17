package com.versawork.http.dataobject;

import java.io.Serializable;

import com.versawork.http.constant.VersaWorkConstant;

/**
 * @author Dheeraj
 * 
 */

public class AllergiesInfo implements Serializable {

	private final static long serialVersionUID = 1L;

	protected String allergiesId;
	protected String allergen;
	protected String reaction;
	protected String status;
	protected String allergenCode;
	protected String reactionCode;
	protected String statusCode;
	protected String dateAdded;
	protected String sourceName;

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		if (sourceName != null) {
			this.sourceName = sourceName;
		} else {
			this.sourceName = VersaWorkConstant.NOT_APPLICABLE;
		}

	}

	public String getAllergenCode() {
		return allergenCode;
	}

	public void setAllergenCode(String allergenCode) {
		if (allergenCode != null) {
			this.allergenCode = allergenCode;
		} else {
			this.allergenCode = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getReactionCode() {
		return reactionCode;
	}

	public void setReactionCode(String reactionCode) {
		if (reactionCode != null) {
			this.reactionCode = reactionCode;
		} else {
			this.reactionCode = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		if (statusCode != null) {
			this.statusCode = statusCode;
		} else {
			this.statusCode = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getAllergiesId() {
		return allergiesId;
	}

	public void setAllergiesId(String allergiesId) {
		this.allergiesId = allergiesId;
	}

	public String getAllergen() {
		return allergen;
	}

	public void setAllergen(String allergen) {
		this.allergen = allergen;
	}

	public String getReaction() {
		return reaction;
	}

	public void setReaction(String reaction) {
		if (reaction != null) {
			this.reaction = reaction;
		} else {
			this.reaction = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		if (status != null) {
			this.status = status;
		} else {
			this.status = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		if (dateAdded != null) {
			this.dateAdded = dateAdded;
		} else {
			this.dateAdded = VersaWorkConstant.NOT_APPLICABLE;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allergen == null) ? 0 : allergen.hashCode());
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
		AllergiesInfo other = (AllergiesInfo) obj;
		if (allergen == null) {
			if (other.allergen != null)
				return false;
		} else if (!allergen.equals(other.allergen))
			return false;
		return true;
	}
}
