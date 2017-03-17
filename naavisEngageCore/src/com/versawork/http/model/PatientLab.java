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

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "patient_lab")
@NamedQueries({
		@NamedQuery(name = "PatientLab.findAll", query = "SELECT p FROM PatientLab p"),
		@NamedQuery(name = "PatientLab.findByAccountId", query = "SELECT p FROM PatientLab p WHERE p.patientLabPK.accountId = :accountId order by labGroupDate desc"),
		@NamedQuery(name = "PatientLab.findByPatientVisitId", query = "SELECT p FROM PatientLab p WHERE p.patientLabPK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientLab.findBySourceId", query = "SELECT p FROM PatientLab p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientLab.findBySourceName", query = "SELECT p FROM PatientLab p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientLab.findByTestId", query = "SELECT p FROM PatientLab p WHERE p.patientLabPK.testId = :testId"),
		@NamedQuery(name = "PatientLab.findByTestName", query = "SELECT p FROM PatientLab p WHERE p.testName = :testName"),
		@NamedQuery(name = "PatientLab.findByLabId", query = "SELECT p FROM PatientLab p WHERE p.patientLabPK.labId = :labId"),
		@NamedQuery(name = "PatientLab.findByLabCode", query = "SELECT p FROM PatientLab p WHERE p.labCode = :labCode"),
		@NamedQuery(name = "PatientLab.findByLabResult", query = "SELECT p FROM PatientLab p WHERE p.labResult = :labResult"),
		@NamedQuery(name = "PatientLab.findByLabUnit", query = "SELECT p FROM PatientLab p WHERE p.labUnit = :labUnit"),
		@NamedQuery(name = "PatientLab.findByResultDate", query = "SELECT p FROM PatientLab p WHERE p.resultDate = :resultDate"),
		@NamedQuery(name = "PatientLab.findByOrganizerName", query = "SELECT p FROM PatientLab p WHERE p.organizerName = :organizerName"),
		@NamedQuery(name = "PatientLab.findByOrganizerCode", query = "SELECT p FROM PatientLab p WHERE p.organizerCode = :organizerCode"),
		@NamedQuery(name = "PatientLab.findByInterpretationCode", query = "SELECT p FROM PatientLab p WHERE p.interpretationCode = :interpretationCode"),
		@NamedQuery(name = "PatientLab.findByDateAdded", query = "SELECT p FROM PatientLab p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientLab.findByNormalRange", query = "SELECT p FROM PatientLab p WHERE p.normalRangeMin >= :normalRangeMin AND p.normalRangeMax <= :normalRangeMax"),
		@NamedQuery(name = "PatientLab.findByAbnormalFlag", query = "SELECT p FROM PatientLab p WHERE p.abnormalFlag = :abnormalFlag"),
		@NamedQuery(name = "PatientLab.findByLabGroupId", query = "SELECT p FROM PatientLab p WHERE p.patientLabPK.labGroupId = :labGroupId"),
		@NamedQuery(name = "PatientLab.findByLabGroupCode", query = "SELECT p FROM PatientLab p WHERE p.labGroupCode = :labGroupCode"),
		@NamedQuery(name = "PatientLab.findByLabGroupName", query = "SELECT p FROM PatientLab p WHERE p.labGroupName = :labGroupName"),
		@NamedQuery(name = "PatientLab.findByAccountIdTestId", query = "SELECT p FROM PatientLab p WHERE p.patientLabPK.accountId = :accountId and p.labCode = :labCode order by p.dateAdded desc"),
		@NamedQuery(name = "PatientLab.findByLabGroupDate", query = "SELECT p FROM PatientLab p WHERE p.labGroupDate = :labGroupDate"),
		@NamedQuery(name = "PatientLab.findByAbsoluteRange", query = "SELECT p FROM PatientLab p WHERE p.absoluteRangeMin >= :absoluteRangeMin AND p.absoluteRangeMax <= :absoluteRangeMax"),
		@NamedQuery(name = "PatientLab.findByEtlInfoAccount", query = "SELECT plab FROM PatientLab plab, EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND plab.patientLabPK.accountId = info.etlInfoPK.accountId ORDER BY  plab.patientLabPK.accountId,  plab.patientLabPK.patientVisitId"),
		@NamedQuery(name = "PatientLab.findByDistinctAccountId", query = "SELECT distinct p.labGroupName,p.labGroupCode,p.sourceName,p.labGroupDate FROM PatientLab p WHERE p.patientLabPK.accountId = :accountId order by p.labGroupDate desc"),
		@NamedQuery(name = "PatientLab.findByAccountIdAndLabGroupCode", query = "SELECT p FROM PatientLab p WHERE p.patientLabPK.accountId = :accountId AND p.labGroupCode=:labGroupCode order by p.labGroupDate desc")})
public class PatientLab implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PatientLabPK patientLabPK;
	@Column(name = "source_id")
	private String sourceId;
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "test_name")
	private String testName;
	@Basic(optional = true)
	@Column(name = "lab_code")
	private String labCode;
	@Basic(optional = false)
	@Column(name = "lab_result")
	private String labResult;
	@Basic(optional = false)
	@Column(name = "lab_unit")
	private String labUnit;
	@Basic(optional = false)
	@Column(name = "result_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date resultDate;
	@Column(name = "organizer_name")
	private String organizerName;
	@Column(name = "organizer_code")
	private String organizerCode;
	@Column(name = "interpretation_code")
	private String interpretationCode;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "normal_range")
	private String normalRange;
	@Column(name = "abnormal_flag")
	private String abnormalFlag;
	@Column(name = "lab_group_code")
	private String labGroupCode;
	@Basic(optional = false)
	@Column(name = "lab_group_name")
	private String labGroupName;
	@Basic(optional = false)
	@Column(name = "lab_group_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date labGroupDate;
	// @Column(name = "absolute_range")
	// private String absoluteRange;

	@Column(name = "absolute_range_min")
	private Float absoluteRangeMin;
	@Column(name = "absolute_range_max")
	private Float absoluteRangeMax;
	@Column(name = "normal_range_min")
	private Float normalRangeMin;
	@Column(name = "normal_range_max")
	private Float normalRangeMax;
	@JoinColumns({
			@JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false),
			@JoinColumn(name = "patient_visit_id", referencedColumnName = "patient_visit_id", insertable = false, updatable = false) })
	@ManyToOne(optional = false)
	private PatientVisit patientVisit;

	public PatientLab() {
	}

	public PatientLab(PatientLabPK patientLabPK) {
		this.patientLabPK = patientLabPK;
	}

	public PatientLab(PatientLabPK patientLabPK, String sourceName, String testName, String labCode, String labResult,
			String labUnit, Date resultDate, Date dateAdded, String labGroupName, Date labGroupDate) {
		this.patientLabPK = patientLabPK;
		this.sourceName = sourceName;
		this.testName = testName;
		this.labCode = labCode;
		this.labResult = labResult;
		this.labUnit = labUnit;
		this.resultDate = resultDate;
		this.dateAdded = dateAdded;
		this.labGroupName = labGroupName;
		this.labGroupDate = labGroupDate;
	}

	public PatientLab(int accountId, int patientVisitId, String testId, String labId, String labGroupId) {
		this.patientLabPK = new PatientLabPK(accountId, patientVisitId, testId, labId, labGroupId);
	}

	public PatientLabPK getPatientLabPK() {
		return patientLabPK;
	}

	public void setPatientLabPK(PatientLabPK patientLabPK) {
		this.patientLabPK = patientLabPK;
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

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getLabCode() {
		return labCode;
	}

	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}

	public String getLabResult() {
		return labResult;
	}

	public void setLabResult(String labResult) {
		this.labResult = labResult;
	}

	public String getLabUnit() {
		return labUnit;
	}

	public void setLabUnit(String labUnit) {
		this.labUnit = labUnit;
	}

	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	public String getOrganizerCode() {
		return organizerCode;
	}

	public void setOrganizerCode(String organizerCode) {
		this.organizerCode = organizerCode;
	}

	public String getInterpretationCode() {
		return interpretationCode;
	}

	public void setInterpretationCode(String interpretationCode) {
		this.interpretationCode = interpretationCode;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public String getNormalRange() {
		return normalRange;
	}

	public void setNormalRange(String normalRange) {
		this.normalRange = normalRange;
	}	 

	public String getAbnormalFlag() {
		return abnormalFlag;
	}

	public void setAbnormalFlag(String abnormalFlag) {
		this.abnormalFlag = abnormalFlag;
	}

	public String getLabGroupCode() {
		return labGroupCode;
	}

	public void setLabGroupCode(String labGroupCode) {
		this.labGroupCode = labGroupCode;
	}

	public String getLabGroupName() {
		return labGroupName;
	}

	public void setLabGroupName(String labGroupName) {
		this.labGroupName = labGroupName;
	}

	public Date getLabGroupDate() {
		return labGroupDate;
	}

	public void setLabGroupDate(Date labGroupDate) {
		this.labGroupDate = labGroupDate;
	}

	public PatientVisit getPatientVisit() {
		return patientVisit;
	}

	public void setPatientVisit(PatientVisit patientVisit) {
		this.patientVisit = patientVisit;
	}

	/*
	 * public String getAbsoluteRange() { return absoluteRange; }
	 * 
	 * public void setAbsoluteRange(String absoluteRange) { this.absoluteRange =
	 * absoluteRange; }
	 */

	public Float getAbsoluteRangeMin() {
		return absoluteRangeMin;
	}

	public void setAbsoluteRangeMin(Float absoluteRangeMin) {
		this.absoluteRangeMin = absoluteRangeMin;
	}

	public Float getAbsoluteRangeMax() {
		return absoluteRangeMax;
	}

	public void setAbsoluteRangeMax(Float absoluteRangeMax) {
		this.absoluteRangeMax = absoluteRangeMax;
	}

	public Float getNormalRangeMin() {
		return normalRangeMin;
	}

	public void setNormalRangeMin(Float normalRangeMin) {
		this.normalRangeMin = normalRangeMin;
	}

	public Float getNormalRangeMax() {
		return normalRangeMax;
	}

	public void setNormalRangeMax(Float normalRangeMax) {
		this.normalRangeMax = normalRangeMax;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientLabPK != null ? patientLabPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientLab)) {
			return false;
		}
		PatientLab other = (PatientLab) object;
		if ((this.patientLabPK == null && other.patientLabPK != null)
				|| (this.patientLabPK != null && !this.patientLabPK.equals(other.patientLabPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientLab[ patientLabPK=" + patientLabPK + " ]";
	}

}
