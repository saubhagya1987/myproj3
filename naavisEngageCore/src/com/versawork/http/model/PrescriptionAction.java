/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "prescription_action")
@NamedQueries({
		@NamedQuery(name = "PrescriptionAction.findAll", query = "SELECT p FROM PrescriptionAction p"),
		@NamedQuery(name = "PrescriptionAction.findByPrescriptionActionId", query = "SELECT p FROM PrescriptionAction p WHERE p.prescriptionActionId = :prescriptionActionId"),
		@NamedQuery(name = "PrescriptionAction.findByPrescriptionAction", query = "SELECT p FROM PrescriptionAction p WHERE p.prescriptionAction = :prescriptionAction") })
public class PrescriptionAction implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "prescription_action_id")
	private Integer prescriptionActionId;
	@Basic(optional = false)
	@Column(name = "prescription_action")
	private String prescriptionAction;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "prescriptionActionId")
	private List<PatientPrescription> patientPrescriptionList;

	public PrescriptionAction() {
	}

	public PrescriptionAction(Integer prescriptionActionId) {
		this.prescriptionActionId = prescriptionActionId;
	}

	public PrescriptionAction(Integer prescriptionActionId, String prescriptionAction) {
		this.prescriptionActionId = prescriptionActionId;
		this.prescriptionAction = prescriptionAction;
	}

	public Integer getPrescriptionActionId() {
		return prescriptionActionId;
	}

	public void setPrescriptionActionId(Integer prescriptionActionId) {
		this.prescriptionActionId = prescriptionActionId;
	}

	public String getPrescriptionAction() {
		return prescriptionAction;
	}

	public void setPrescriptionAction(String prescriptionAction) {
		this.prescriptionAction = prescriptionAction;
	}

	public List<PatientPrescription> getPatientPrescriptionList() {
		return patientPrescriptionList;
	}

	public void setPatientPrescriptionList(List<PatientPrescription> patientPrescriptionList) {
		this.patientPrescriptionList = patientPrescriptionList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (prescriptionActionId != null ? prescriptionActionId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PrescriptionAction)) {
			return false;
		}
		PrescriptionAction other = (PrescriptionAction) object;
		if ((this.prescriptionActionId == null && other.prescriptionActionId != null)
				|| (this.prescriptionActionId != null && !this.prescriptionActionId.equals(other.prescriptionActionId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PrescriptionAction[ prescriptionActionId=" + prescriptionActionId + " ]";
	}

}
