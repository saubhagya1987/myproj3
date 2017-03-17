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
@Table(name = "patient_problem")
@NamedQueries({
		@NamedQuery(name = "PatientProblem.findAll", query = "SELECT p FROM PatientProblem p"),
		@NamedQuery(name = "PatientProblem.findByAccountId", query = "SELECT p FROM PatientProblem p WHERE p.patientProblemPK.accountId = :accountId order by p.problemName desc"),
		@NamedQuery(name = "PatientProblem.findBySourceId", query = "SELECT p FROM PatientProblem p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientProblem.findBySourceName", query = "SELECT p FROM PatientProblem p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientProblem.findByProblemName", query = "SELECT p FROM PatientProblem p WHERE p.problemName = :problemName"),
		@NamedQuery(name = "PatientProblem.findByProblemCode", query = "SELECT p FROM PatientProblem p WHERE p.problemCode = :problemCode"),
		@NamedQuery(name = "PatientProblem.findByStatus", query = "SELECT p FROM PatientProblem p WHERE p.status = :status"),
		@NamedQuery(name = "PatientProblem.findByStatusCode", query = "SELECT p FROM PatientProblem p WHERE p.statusCode = :statusCode"),
		@NamedQuery(name = "PatientProblem.findByDateAdded", query = "SELECT p FROM PatientProblem p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientProblem.findByEtlInfoAccount", query = "SELECT p FROM PatientProblem p, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND p.patientProblemPK.accountId = info.etlInfoPK.accountId ORDER BY  p.patientProblemPK.accountId") })
public class PatientProblem implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientProblemPK patientProblemPK;
	@Column(name = "source_id")
	private String sourceId;
	@Column(name = "source_name")
	private String sourceName;
	@Column(name = "problem_name")
	private String problemName;
	@Basic(optional = true)
	@Column(name = "problem_code")
	private String problemCode;
	@Column(name = "status")
	private String status;
	@Column(name = "status_code")
	private String statusCode;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;

	/*
	 * @JoinColumn(name = "account_id", referencedColumnName = "account_id",
	 * insertable = false, updatable = false)
	 * 
	 * @ManyToOne(optional = false) private Account account;
	 */

	public PatientProblem() {
	}

	public PatientProblem(PatientProblemPK patientProblemPK) {
		this.patientProblemPK = patientProblemPK;
	}

	public PatientProblem(PatientProblemPK patientProblemPK, String problemCode, Date dateAdded) {
		this.patientProblemPK = patientProblemPK;
		this.problemCode = problemCode;
		this.dateAdded = dateAdded;
	}

	public PatientProblem(int accountId, String problemId) {
		this.patientProblemPK = new PatientProblemPK(accountId, problemId);
	}

	public PatientProblemPK getPatientProblemPK() {
		return patientProblemPK;
	}

	public void setPatientProblemPK(PatientProblemPK patientProblemPK) {
		this.patientProblemPK = patientProblemPK;
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

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
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
		hash += (patientProblemPK != null ? patientProblemPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientProblem)) {
			return false;
		}
		PatientProblem other = (PatientProblem) object;
		if ((this.patientProblemPK == null && other.patientProblemPK != null)
				|| (this.patientProblemPK != null && !this.patientProblemPK.equals(other.patientProblemPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientProblem[ patientProblemPK=" + patientProblemPK + " ]";
	}

}
