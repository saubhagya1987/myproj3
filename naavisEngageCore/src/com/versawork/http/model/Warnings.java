package com.versawork.http.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author User20
 */
@Entity
@Table(name = "warning")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Warnings.findAll", query = "SELECT w FROM Warnings w"),
		@NamedQuery(name = "Warnings.findByWarningId", query = "SELECT w FROM Warnings w WHERE w.warningId = :warningId"),
		@NamedQuery(name = "Warnings.findByWarningMessage", query = "SELECT w FROM Warnings w WHERE w.warningMessage = :warningMessage"),
		@NamedQuery(name = "Warnings.findByWarningDate", query = "SELECT w FROM Warnings w WHERE w.warningDate = :warningDate"),
		@NamedQuery(name = "Warnings.findByWarningDay", query = "SELECT w FROM Warnings w WHERE w.warningDay = :warningDay"),
		@NamedQuery(name = "Warnings.findByIsWarningPresent", query = "SELECT w FROM Warnings w WHERE w.isWarningPresent = :isWarningPresent"),
		@NamedQuery(name = "Warnings.findByAccountId", query = "SELECT w FROM Warnings w WHERE w.accountId = :accountId"),
		@NamedQuery(name = "Warnings.findByIsViewed", query = "SELECT w FROM Warnings w WHERE w.isViewed = :isViewed"),
		@NamedQuery(name = "Warnings.findByWarningType", query = "SELECT w FROM Warnings w WHERE w.warningType = :warningType"),
		@NamedQuery(name = "Warnings.findByAccountIdAndWarningType", query = "SELECT w FROM Warnings w WHERE w.accountId = :accountId and w.warningType = :warningType and w.isActive = :isActive"),
		@NamedQuery(name = "Warnings.findByAccountIdAndIsViewedAndWarningType", query = "SELECT w FROM Warnings w WHERE w.accountId = :accountId and w.isViewed = :isViewed and  w.warningType = :warningType and w.isActive = :isActive") })
public class Warnings implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "warning_id")
	private Long warningId;
	@Basic(optional = false)
	@Column(name = "warning_message")
	private String warningMessage;
	@Column(name = "warning_date")
	private String warningDate;
	@Column(name = "warning_day")
	private String warningDay;
	@Column(name = "is_warning_present")
	private Boolean isWarningPresent;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "is_viewed")
	private boolean isViewed;

	@Column(name = "warning_type")
	private String warningType;

	@Basic(optional = false)
	@Column(name = "is_active")
	private boolean isActive;

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Warnings() {
	}

	public Warnings(Long warningId) {
		this.warningId = warningId;
	}

	public Warnings(Long warningId, String warningMessage, int accountId, boolean isViewed) {
		this.warningId = warningId;
		this.warningMessage = warningMessage;
		this.accountId = accountId;
		this.isViewed = isViewed;
	}

	public Long getWarningId() {
		return warningId;
	}

	public void setWarningId(Long warningId) {
		this.warningId = warningId;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public String getWarningDate() {
		return warningDate;
	}

	public void setWarningDate(String warningDate) {
		this.warningDate = warningDate;
	}

	public String getWarningDay() {
		return warningDay;
	}

	public void setWarningDay(String warningDay) {
		this.warningDay = warningDay;
	}

	public Boolean getIsWarningPresent() {
		return isWarningPresent;
	}

	public void setIsWarningPresent(Boolean isWarningPresent) {
		this.isWarningPresent = isWarningPresent;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public boolean getIsViewed() {
		return isViewed;
	}

	public void setIsViewed(boolean isViewed) {
		this.isViewed = isViewed;
	}

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (warningId != null ? warningId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Warnings)) {
			return false;
		}
		Warnings other = (Warnings) object;
		if ((this.warningId == null && other.warningId != null)
				|| (this.warningId != null && !this.warningId.equals(other.warningId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "javaapplication2.Warning[ warningId=" + this.warningMessage + " ]";
	}

}
