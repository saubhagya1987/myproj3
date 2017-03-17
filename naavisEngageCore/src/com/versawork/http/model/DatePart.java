/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "date_part")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "DatePart.findAll", query = "SELECT d FROM DatePart d"),
		@NamedQuery(name = "DatePart.findByDatePartId", query = "SELECT d FROM DatePart d WHERE d.datePartId = :datePartId"),
		@NamedQuery(name = "DatePart.findByDatePartDescription", query = "SELECT d FROM DatePart d WHERE d.datePartDescription = :datePartDescription"),
		@NamedQuery(name = "DatePart.findByDatePart", query = "SELECT d FROM DatePart d WHERE d.datePart = :datePart") })
public class DatePart implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "date_part_id")
	private Integer datePartId;
	@Column(name = "date_part_description")
	private String datePartDescription;
	@Basic(optional = false)
	@Column(name = "date_part")
	private String datePart;
	@OneToMany(mappedBy = "datePartId")
	private List<Frequency> frequencyList;
	@OneToMany(mappedBy = "datePartId")
	private List<AccountMedicationManagementSchedule> accountMedicationManagementScheduleList;
	@OneToMany(mappedBy = "datePartId")
	private List<AccountBloodPressureManagementSchedule> accountBloodPressureManagementScheduleList;

	public DatePart() {
	}

	public DatePart(Integer datePartId) {
		this.datePartId = datePartId;
	}

	public DatePart(Integer datePartId, String datePart) {
		this.datePartId = datePartId;
		this.datePart = datePart;
	}

	public Integer getDatePartId() {
		return datePartId;
	}

	public void setDatePartId(Integer datePartId) {
		this.datePartId = datePartId;
	}

	public String getDatePartDescription() {
		return datePartDescription;
	}

	public void setDatePartDescription(String datePartDescription) {
		this.datePartDescription = datePartDescription;
	}

	public String getDatePart() {
		return datePart;
	}

	public void setDatePart(String datePart) {
		this.datePart = datePart;
	}

	@XmlTransient
	public List<Frequency> getFrequencyList() {
		return frequencyList;
	}

	public void setFrequencyList(List<Frequency> frequencyList) {
		this.frequencyList = frequencyList;
	}

	@XmlTransient
	public List<AccountMedicationManagementSchedule> getAccountMedicationManagementScheduleList() {
		return accountMedicationManagementScheduleList;
	}

	public void setAccountMedicationManagementScheduleList(
			List<AccountMedicationManagementSchedule> accountMedicationManagementScheduleList) {
		this.accountMedicationManagementScheduleList = accountMedicationManagementScheduleList;
	}

	@XmlTransient
	public List<AccountBloodPressureManagementSchedule> getAccountBloodPressureManagementScheduleList() {
		return accountBloodPressureManagementScheduleList;
	}

	public void setAccountBloodPressureManagementScheduleList(
			List<AccountBloodPressureManagementSchedule> accountBloodPressureManagementScheduleList) {
		this.accountBloodPressureManagementScheduleList = accountBloodPressureManagementScheduleList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (datePartId != null ? datePartId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DatePart)) {
			return false;
		}
		DatePart other = (DatePart) object;
		if ((this.datePartId == null && other.datePartId != null)
				|| (this.datePartId != null && !this.datePartId.equals(other.datePartId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.DatePart[ datePartId=" + datePartId + " ]";
	}

}
