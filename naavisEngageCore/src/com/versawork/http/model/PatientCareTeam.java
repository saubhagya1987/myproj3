/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "patient_care_team")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "PatientCareTeam.findAll", query = "SELECT p FROM PatientCareTeam p"),
		@NamedQuery(name = "PatientCareTeam.findByAccountId", query = "SELECT p FROM PatientCareTeam p WHERE p.patientCareTeamPK.accountId = :accountId"),
		@NamedQuery(name = "PatientCareTeam.findByPatientVisitId", query = "SELECT p FROM PatientCareTeam p WHERE p.patientCareTeamPK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientCareTeam.findBySourceId", query = "SELECT p FROM PatientCareTeam p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientCareTeam.findBySourceName", query = "SELECT p FROM PatientCareTeam p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientCareTeam.findByCareMemberId", query = "SELECT p FROM PatientCareTeam p WHERE p.patientCareTeamPK.careMemberId = :careMemberId"),
		@NamedQuery(name = "PatientCareTeam.findByCareMemberName", query = "SELECT p FROM PatientCareTeam p WHERE p.careMemberName = :careMemberName"),
		@NamedQuery(name = "PatientCareTeam.findByPhoneNumber", query = "SELECT p FROM PatientCareTeam p WHERE p.phoneNumber = :phoneNumber"),
		@NamedQuery(name = "PatientCareTeam.findByAddress1", query = "SELECT p FROM PatientCareTeam p WHERE p.address1 = :address1"),
		@NamedQuery(name = "PatientCareTeam.findByAddress2", query = "SELECT p FROM PatientCareTeam p WHERE p.address2 = :address2"),
		@NamedQuery(name = "PatientCareTeam.findByCity", query = "SELECT p FROM PatientCareTeam p WHERE p.city = :city"),
		@NamedQuery(name = "PatientCareTeam.findByState", query = "SELECT p FROM PatientCareTeam p WHERE p.state = :state"),
		@NamedQuery(name = "PatientCareTeam.findByPostalCode", query = "SELECT p FROM PatientCareTeam p WHERE p.postalCode = :postalCode"),
		@NamedQuery(name = "PatientCareTeam.findByCountry", query = "SELECT p FROM PatientCareTeam p WHERE p.country = :country"),
		@NamedQuery(name = "PatientCareTeam.findByDateAdded", query = "SELECT p FROM PatientCareTeam p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientCareTeam.findByCareMemberRoleId", query = "SELECT p FROM PatientCareTeam p WHERE p.patientCareTeamPK.careMemberRoleId = :careMemberRoleId"),
		@NamedQuery(name = "PatientCareTeam.findByEtlInfoAccount", query = "SELECT p FROM PatientCareTeam p, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND p.patientCareTeamPK.accountId = info.etlInfoPK.accountId ORDER BY  p.patientCareTeamPK.accountId, p.patientCareTeamPK.patientVisitId") })
public class PatientCareTeam implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientCareTeamPK patientCareTeamPK;
	@Column(name = "source_id")
	private String sourceId;
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "care_member_name")
	private String careMemberName;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "address_1")
	private String address1;
	@Column(name = "address_2")
	private String address2;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "postal_code")
	private String postalCode;
	@Column(name = "country")
	private String country;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "care_member_speciality")
	private String careMemberSpeciality;
	@Column(name = "care_member_email")
	private String careMemberEmail;
	@JoinColumns({
			@JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false),
			@JoinColumn(name = "patient_visit_id", referencedColumnName = "patient_visit_id", insertable = false, updatable = false) })
	@ManyToOne(optional = false)
	private PatientVisit patientVisit;
	@JoinColumn(name = "care_member_role_id", referencedColumnName = "care_member_role_id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private CareMemberRole careMemberRole;

	public PatientCareTeam() {
	}

	public String getCareMemberSpeciality() {
		return careMemberSpeciality;
	}

	public void setCareMemberSpeciality(String careMemberSpeciality) {
		this.careMemberSpeciality = careMemberSpeciality;
	}

	public String getCareMemberEmail() {
		return careMemberEmail;
	}

	public void setCareMemberEmail(String careMemberEmail) {
		this.careMemberEmail = careMemberEmail;
	}

	public PatientCareTeam(PatientCareTeamPK patientCareTeamPK) {
		this.patientCareTeamPK = patientCareTeamPK;
	}

	public PatientCareTeam(PatientCareTeamPK patientCareTeamPK, String sourceName, String careMemberName, Date dateAdded) {
		this.patientCareTeamPK = patientCareTeamPK;
		this.sourceName = sourceName;
		this.careMemberName = careMemberName;
		this.dateAdded = dateAdded;
	}

	public PatientCareTeam(int accountId, int patientVisitId, String careMemberId, int careMemberRoleId) {
		this.patientCareTeamPK = new PatientCareTeamPK(accountId, patientVisitId, careMemberId, careMemberRoleId);
	}

	public PatientCareTeamPK getPatientCareTeamPK() {
		return patientCareTeamPK;
	}

	public void setPatientCareTeamPK(PatientCareTeamPK patientCareTeamPK) {
		this.patientCareTeamPK = patientCareTeamPK;
	}

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

	public String getCareMemberName() {
		return careMemberName;
	}

	public void setCareMemberName(String careMemberName) {
		this.careMemberName = careMemberName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public PatientVisit getPatientVisit() {
		return patientVisit;
	}

	public void setPatientVisit(PatientVisit patientVisit) {
		this.patientVisit = patientVisit;
	}

	public CareMemberRole getCareMemberRole() {
		return careMemberRole;
	}

	public void setCareMemberRole(CareMemberRole careMemberRole) {
		this.careMemberRole = careMemberRole;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientCareTeamPK != null ? patientCareTeamPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientCareTeam)) {
			return false;
		}
		PatientCareTeam other = (PatientCareTeam) object;
		if ((this.patientCareTeamPK == null && other.patientCareTeamPK != null)
				|| (this.patientCareTeamPK != null && !this.patientCareTeamPK.equals(other.patientCareTeamPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientCareTeam[ patientCareTeamPK=" + patientCareTeamPK + " ]";
	}

}
