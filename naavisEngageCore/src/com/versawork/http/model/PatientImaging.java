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
@Table(name = "patient_imaging")
@NamedQueries({
		@NamedQuery(name = "PatientImaging.findAll", query = "SELECT p FROM PatientImaging p"),
		@NamedQuery(name = "PatientImaging.findByAccountId", query = "SELECT p FROM PatientImaging p WHERE p.patientImagingPK.accountId = :accountId order by p.examDate,p.sourceId"),
		@NamedQuery(name = "PatientImaging.findByAccountIdAndExamId", query = "SELECT p FROM PatientImaging p WHERE p.patientImagingPK.accountId = :accountId AND p.patientImagingPK.patientVisitId=:patientVisitId order by p.examDate,p.sourceId"),
		@NamedQuery(name = "PatientImaging.findByPatientVisitId", query = "SELECT p FROM PatientImaging p WHERE p.patientImagingPK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientImaging.findByExamId", query = "SELECT p FROM PatientImaging p WHERE p.patientImagingPK.examId = :examId"),
		@NamedQuery(name = "PatientImaging.findBySourceId", query = "SELECT p FROM PatientImaging p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientImaging.findBySourceName", query = "SELECT p FROM PatientImaging p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientImaging.findByExamName", query = "SELECT p FROM PatientImaging p WHERE p.examName = :examName"),
		@NamedQuery(name = "PatientImaging.findByOrderingProvider", query = "SELECT p FROM PatientImaging p WHERE p.orderingProvider = :orderingProvider"),
		@NamedQuery(name = "PatientImaging.findByExamTechnologist", query = "SELECT p FROM PatientImaging p WHERE p.examTechnologist = :examTechnologist"),
		@NamedQuery(name = "PatientImaging.findByExamDate", query = "SELECT p FROM PatientImaging p WHERE p.examDate = :examDate"),
		@NamedQuery(name = "PatientImaging.findByExamMessage", query = "SELECT p FROM PatientImaging p WHERE p.examMessage = :examMessage"),
		@NamedQuery(name = "PatientImaging.findByDateAdded", query = "SELECT p FROM PatientImaging p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientImaging.findByEtlInfoAccount", query = "SELECT p FROM PatientImaging p, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND p.patientImagingPK.accountId = info.etlInfoPK.accountId ORDER BY  p.patientImagingPK.accountId, p.patientImagingPK.patientVisitId"),
		@NamedQuery(name = "PatientImaging.findByAccountIdAndExamIdInfo", query = "SELECT p FROM PatientImaging p WHERE p.patientImagingPK.accountId = :accountId AND p.patientImagingPK.examId = :examId order by p.examDate")})
public class PatientImaging implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientImagingPK patientImagingPK;
	@Column(name = "source_id")
	private String sourceId;
	@Column(name = "source_name")
	private String sourceName;
	@Column(name = "exam_name")
	private String examName;
	@Column(name = "ordering_provider")
	private String orderingProvider;
	@Column(name = "exam_technologist")
	private String examTechnologist;
	@Column(name = "exam_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date examDate;
	@Column(name = "exam_message")
	private String examMessage;

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

	public PatientImaging() {
	}

	public PatientImaging(PatientImagingPK patientImagingPK) {
		this.patientImagingPK = patientImagingPK;
	}

	public PatientImaging(PatientImagingPK patientImagingPK, Date dateAdded) {
		this.patientImagingPK = patientImagingPK;
		this.dateAdded = dateAdded;
	}

	public PatientImaging(int accountId, int patientVisitId, String examId) {
		this.patientImagingPK = new PatientImagingPK(accountId, patientVisitId, examId);
	}

	public PatientImagingPK getPatientImagingPK() {
		return patientImagingPK;
	}

	public void setPatientImagingPK(PatientImagingPK patientImagingPK) {
		this.patientImagingPK = patientImagingPK;
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

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getOrderingProvider() {
		return orderingProvider;
	}

	public void setOrderingProvider(String orderingProvider) {
		this.orderingProvider = orderingProvider;
	}

	public String getExamTechnologist() {
		return examTechnologist;
	}

	public void setExamTechnologist(String examTechnologist) {
		this.examTechnologist = examTechnologist;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamMessage() {
		return examMessage;
	}

	public void setExamMessage(String examMessage) {
		this.examMessage = examMessage;
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
		hash += (patientImagingPK != null ? patientImagingPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientImaging)) {
			return false;
		}
		PatientImaging other = (PatientImaging) object;
		if ((this.patientImagingPK == null && other.patientImagingPK != null)
				|| (this.patientImagingPK != null && !this.patientImagingPK.equals(other.patientImagingPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientImaging[ patientImagingPK=" + patientImagingPK + " ]";
	}

}
