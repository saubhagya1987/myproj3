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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "care_member_role")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "CareMemberRole.findAll", query = "SELECT c FROM CareMemberRole c"),
		@NamedQuery(name = "CareMemberRole.findByCareMemberRoleId", query = "SELECT c FROM CareMemberRole c WHERE c.careMemberRoleId = :careMemberRoleId"),
		@NamedQuery(name = "CareMemberRole.findByCareMemberRole", query = "SELECT c FROM CareMemberRole c WHERE c.careMemberRole = :careMemberRole") })
public class CareMemberRole implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "care_member_role_id")
	private Integer careMemberRoleId;
	@Basic(optional = false)
	@Column(name = "care_member_role")
	private String careMemberRole;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "careMemberRole")
	private List<PatientCareTeam> patientCareTeamList;

	public CareMemberRole() {
	}

	public CareMemberRole(Integer careMemberRoleId) {
		this.careMemberRoleId = careMemberRoleId;
	}

	public CareMemberRole(Integer careMemberRoleId, String careMemberRole) {
		this.careMemberRoleId = careMemberRoleId;
		this.careMemberRole = careMemberRole;
	}

	public Integer getCareMemberRoleId() {
		return careMemberRoleId;
	}

	public void setCareMemberRoleId(Integer careMemberRoleId) {
		this.careMemberRoleId = careMemberRoleId;
	}

	public String getCareMemberRole() {
		return careMemberRole;
	}

	public void setCareMemberRole(String careMemberRole) {
		this.careMemberRole = careMemberRole;
	}

	@XmlTransient
	public List<PatientCareTeam> getPatientCareTeamList() {
		return patientCareTeamList;
	}

	public void setPatientCareTeamList(List<PatientCareTeam> patientCareTeamList) {
		this.patientCareTeamList = patientCareTeamList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (careMemberRoleId != null ? careMemberRoleId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CareMemberRole)) {
			return false;
		}
		CareMemberRole other = (CareMemberRole) object;
		if ((this.careMemberRoleId == null && other.careMemberRoleId != null)
				|| (this.careMemberRoleId != null && !this.careMemberRoleId.equals(other.careMemberRoleId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.CareMemberRole[ careMemberRoleId=" + careMemberRoleId + " ]";
	}

}
