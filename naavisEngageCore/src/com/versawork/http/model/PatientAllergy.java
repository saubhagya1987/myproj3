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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "patient_allergy")
@NamedQueries({
		@NamedQuery(name = "PatientAllergy.findAll", query = "SELECT p FROM PatientAllergy p"),
		@NamedQuery(name = "PatientAllergy.findByAccountId", query = "SELECT p FROM PatientAllergy p WHERE p.patientAllergyPK.accountId = :accountId ORDER BY p.allergenName Desc"),
		@NamedQuery(name = "PatientAllergy.findBySourceId", query = "SELECT p FROM PatientAllergy p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientAllergy.findBySourceName", query = "SELECT p FROM PatientAllergy p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientAllergy.findByAllergyId", query = "SELECT p FROM PatientAllergy p WHERE p.patientAllergyPK.allergyId = :allergyId"),
		@NamedQuery(name = "PatientAllergy.findByAllergenName", query = "SELECT p FROM PatientAllergy p WHERE p.allergenName = :allergenName"),
		@NamedQuery(name = "PatientAllergy.findByAllergenCode", query = "SELECT p FROM PatientAllergy p WHERE p.allergenCode = :allergenCode"),
		@NamedQuery(name = "PatientAllergy.findByReactionCode", query = "SELECT p FROM PatientAllergy p WHERE p.reactionCode = :reactionCode"),
		@NamedQuery(name = "PatientAllergy.findByStatus", query = "SELECT p FROM PatientAllergy p WHERE p.status = :status"),
		@NamedQuery(name = "PatientAllergy.findByStatusCode", query = "SELECT p FROM PatientAllergy p WHERE p.statusCode = :statusCode"),
		@NamedQuery(name = "PatientAllergy.findByDateAdded", query = "SELECT p FROM PatientAllergy p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientAllergy.findByEtlInfoAccount", query = "SELECT pall FROM PatientAllergy pall, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND pall.patientAllergyPK.accountId = info.etlInfoPK.accountId ORDER BY  pall.patientAllergyPK.accountId") })
public class PatientAllergy implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientAllergyPK patientAllergyPK;
	@Column(name = "source_id")
	private String sourceId;
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "allergen_name")
	private String allergenName;
	@Basic(optional = true)
	@Column(name = "allergen_code")
	private String allergenCode;
	@Column(name = "reaction_code")
	private String reactionCode;
	@Column(name = "status")
	private String status;
	@Column(name = "status_code")
	private String statusCode;
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;

	@Column(name = "reaction")
	private String reaction;

	/*
	 * @JoinColumn(name = "account_id", referencedColumnName = "account_id",
	 * insertable = false, updatable = false)
	 * 
	 * @ManyToOne(optional = false) private Account account;
	 */

	public String getReaction() {
		return reaction;
	}

	public void setReaction(String reaction) {
		this.reaction = reaction;
	}

	public PatientAllergy() {
	}

	public PatientAllergy(PatientAllergyPK patientAllergyPK) {
		this.patientAllergyPK = patientAllergyPK;
	}

	public PatientAllergy(PatientAllergyPK patientAllergyPK, String allergenName, String allergenCode) {
		this.patientAllergyPK = patientAllergyPK;
		this.allergenName = allergenName;
		this.allergenCode = allergenCode;
	}

	public PatientAllergy(int accountId, String allergyId, String reaction) {
		this.patientAllergyPK = new PatientAllergyPK(accountId, allergyId);
	}

	public PatientAllergyPK getPatientAllergyPK() {
		return patientAllergyPK;
	}

	public void setPatientAllergyPK(PatientAllergyPK patientAllergyPK) {
		this.patientAllergyPK = patientAllergyPK;
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

	public String getAllergenName() {
		return allergenName;
	}

	public void setAllergenName(String allergenName) {
		this.allergenName = allergenName;
	}

	public String getAllergenCode() {
		return allergenCode;
	}

	public void setAllergenCode(String allergenCode) {
		this.allergenCode = allergenCode;
	}

	public String getReactionCode() {
		return reactionCode;
	}

	public void setReactionCode(String reactionCode) {
		this.reactionCode = reactionCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	/*
	 * public Account getAccount() { return account; }
	 * 
	 * public void setAccount(Account account) { this.account = account; }
	 */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientAllergyPK != null ? patientAllergyPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientAllergy)) {
			return false;
		}
		PatientAllergy other = (PatientAllergy) object;
		if ((this.patientAllergyPK == null && other.patientAllergyPK != null)
				|| (this.patientAllergyPK != null && !this.patientAllergyPK.equals(other.patientAllergyPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientAllergy[ patientAllergyPK=" + patientAllergyPK + " ]";
	}

}
