/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "patient_visit")
@NamedQueries({
		@NamedQuery(name = "PatientVisit.findAll", query = "SELECT p FROM PatientVisit p"),

		@NamedQuery(name = "PatientVisit.findByClientId", query = "SELECT p FROM PatientVisit p WHERE p.clientId = :clientId"),
		@NamedQuery(name = "PatientVisit.findByClientDatabaseId", query = "SELECT p FROM PatientVisit p WHERE p.clientDatabaseId = :clientDatabaseId"),

		@NamedQuery(name = "PatientVisit.findByPatientVisitId", query = "SELECT p FROM PatientVisit p WHERE p.patientVisitPK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientVisit.findByVisitIdAccountId", query = "SELECT p FROM PatientVisit p WHERE p.patientVisitPK.accountId = :accountId and p.patientVisitPK.patientVisitId = :patientVisitId"),
		@NamedQuery(name = "PatientVisit.findByAccountId", query = "SELECT p FROM PatientVisit p WHERE p.patientVisitPK.accountId = :accountId order by visitDate desc"),
		@NamedQuery(name = "PatientVisit.findByVisitIdentifier", query = "SELECT p FROM PatientVisit p WHERE p.visitIdentifier = :visitIdentifier"),
		@NamedQuery(name = "PatientVisit.findBySourceId", query = "SELECT p FROM PatientVisit p WHERE p.sourceId = :sourceId"),
		@NamedQuery(name = "PatientVisit.findBySourceName", query = "SELECT p FROM PatientVisit p WHERE p.sourceName = :sourceName"),
		@NamedQuery(name = "PatientVisit.findByVisitTypeId", query = "SELECT p FROM PatientVisit p WHERE p.visitTypeId = :visitTypeId"),
		@NamedQuery(name = "PatientVisit.findByVisitDate", query = "SELECT p FROM PatientVisit p WHERE p.visitDate = :visitDate"),
		@NamedQuery(name = "PatientVisit.findByEncounterStartDate", query = "SELECT p FROM PatientVisit p WHERE p.encounterStartDate = :encounterStartDate"),
		@NamedQuery(name = "PatientVisit.findByEncounterEndDate", query = "SELECT p FROM PatientVisit p WHERE p.encounterEndDate = :encounterEndDate"),
		@NamedQuery(name = "PatientVisit.findByFirstName", query = "SELECT p FROM PatientVisit p WHERE p.firstName = :firstName"),
		@NamedQuery(name = "PatientVisit.findByLastName", query = "SELECT p FROM PatientVisit p WHERE p.lastName = :lastName"),
		@NamedQuery(name = "PatientVisit.findBySex", query = "SELECT p FROM PatientVisit p WHERE p.sex = :sex"),
		@NamedQuery(name = "PatientVisit.findByBirthDate", query = "SELECT p FROM PatientVisit p WHERE p.birthDate = :birthDate"),
		@NamedQuery(name = "PatientVisit.findByRace", query = "SELECT p FROM PatientVisit p WHERE p.race = :race"),
		@NamedQuery(name = "PatientVisit.findByRaceCode", query = "SELECT p FROM PatientVisit p WHERE p.raceCode = :raceCode"),
		@NamedQuery(name = "PatientVisit.findByEthnicity", query = "SELECT p FROM PatientVisit p WHERE p.ethnicity = :ethnicity"),
		@NamedQuery(name = "PatientVisit.findByEthnicityCode", query = "SELECT p FROM PatientVisit p WHERE p.ethnicityCode = :ethnicityCode"),
		@NamedQuery(name = "PatientVisit.findByPreferredLanguage", query = "SELECT p FROM PatientVisit p WHERE p.preferredLanguage = :preferredLanguage"),
		@NamedQuery(name = "PatientVisit.findBySmokingStatus", query = "SELECT p FROM PatientVisit p WHERE p.smokingStatus = :smokingStatus"),
		@NamedQuery(name = "PatientVisit.findBySmokingStatusCode", query = "SELECT p FROM PatientVisit p WHERE p.smokingStatusCode = :smokingStatusCode"),

		@NamedQuery(name = "PatientVisit.findByProviderName", query = "SELECT p FROM PatientVisit p WHERE p.providerName = :providerName"),
		@NamedQuery(name = "PatientVisit.findByEtlColumn1", query = "SELECT p FROM PatientVisit p WHERE p.etlColumn1 = :etlColumn1"),
		@NamedQuery(name = "PatientVisit.findByEtlColumn2", query = "SELECT p FROM PatientVisit p WHERE p.etlColumn2 = :etlColumn2"),
		@NamedQuery(name = "PatientVisit.findBySourceUpdated", query = "SELECT p FROM PatientVisit p WHERE p.sourceUpdated = :sourceUpdated"),

		@NamedQuery(name = "PatientVisit.findByDateAdded", query = "SELECT p FROM PatientVisit p WHERE p.dateAdded = :dateAdded"),
		@NamedQuery(name = "PatientVisit.findByEtlInfoAccount", query = "SELECT p  FROM PatientVisit p , EtlInfo info WHERE info.etlInfoPK.transactionId =:transactionId AND info.etlInfoPK.clientDatabaseId =:clientDatabaseId AND p.patientVisitPK.accountId = info.etlInfoPK.accountId ORDER BY  p.patientVisitPK.accountId,  p.patientVisitPK.patientVisitId"),
		@NamedQuery(name = "PatientVisit.findByAccountIdAndAppointmentDate", query = "SELECT p FROM PatientVisit p WHERE p.patientVisitPK.accountId = :accountId  ORDER BY p.visitDate DESC"),
		@NamedQuery(name = "PatientVisit.findByProviderNameAndPastDate", query = "SELECT p FROM PatientVisit p WHERE  p.providerName LIKE :providerName  and p.patientVisitPK.accountId = :accountId and convert(varchar(10),p.visitDate,101)  < :todaysDate ORDER BY p.visitDate DESC")})
public class PatientVisit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected PatientVisitPK patientVisitPK;

	/*
	 * @Id
	 * 
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "patient_visit_id") private Integer patientVisitId;
	 */
	@Basic(optional = false)
	@Column(name = "visit_identifier")
	private String visitIdentifier;
	@Column(name = "source_id")
	private String sourceId;
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "visit_type_id")
	private short visitTypeId;
	@Column(name = "visit_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date visitDate;
	@Column(name = "encounter_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date encounterStartDate;
	@Column(name = "encounter_end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date encounterEndDate;
	@Basic(optional = false)
	@Column(name = "first_name")
	private String firstName;
	@Basic(optional = false)
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "sex")
	private String sex;
	@Column(name = "birth_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;
	@Column(name = "race")
	private String race;
	@Column(name = "provider_name")
	private String providerName;
	@Column(name = "race_code")
	private String raceCode;
	@Column(name = "ethnicity")
	private String ethnicity;
	@Column(name = "ethnicity_code")
	private String ethnicityCode;
	@Column(name = "preferred_language")
	private String preferredLanguage;
	@Column(name = "smoking_status")
	private String smokingStatus;
	@Column(name = "smoking_status_code")
	private String smokingStatusCode;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;

	@Basic(optional = false)
	@Column(name = "client_id")
	private int clientId;
	@Basic(optional = false)
	@Column(name = "client_database_id")
	private int clientDatabaseId;
	@Basic(optional = false)
	@Column(name = "etl_column_1")
	private String etlColumn1;
	@Basic(optional = false)
	@Column(name = "etl_column_2")
	private String etlColumn2;
	@Basic(optional = false)
	@Column(name = "source_updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sourceUpdated;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientVisit")
	private List<PatientCareTeam> patientCareTeamList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientVisit")
	private List<PatientImmunization> patientImmunizationList;
	/*
	 * @Column(name="patient_visit_id") private PatientFeedback
	 * patientFeedbackList;
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientVisit")
	private List<PatientPrescription> patientMedicationList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientVisit")
	private List<PatientVitalSign> patientVitalSignList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientVisit")
	private List<PatientLab> patientLabList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientVisit")
	private List<PatientFunctionalStatus> patientFunctionalStatusList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientVisit")
	private List<PatientDiagnosis> patientDiagnosisList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientVisit")
	private List<PatientCarePlan> patientCarePlanList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientVisit")
	private List<PatientProcedure> patientProcedureList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientVisit")
	private List<PatientVisitInpatient> patientVisitInpatientList;

	/*
	 * @Basic(optional=false)
	 * 
	 * @Column(name="account_id") private Integer accountId;
	 */

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public PatientVisit() {
	}

	public PatientVisit(PatientVisitPK patientVisitPK) {
		this.patientVisitPK = patientVisitPK;
	}

	public PatientVisit(PatientVisitPK patientVisitPK, int clientId, int clientDatabaseId, String visitIdentifier,
			String sourceName, short visitTypeId, String firstName, String lastName, Date dateAdded, String etlColumn1,
			String etlColumn2, Date sourceUpdated) {
		this.patientVisitPK = patientVisitPK;
		this.clientId = clientId;
		this.clientDatabaseId = clientDatabaseId;
		this.visitIdentifier = visitIdentifier;
		this.sourceName = sourceName;
		this.visitTypeId = visitTypeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateAdded = dateAdded;
		this.etlColumn1 = etlColumn1;
		this.etlColumn2 = etlColumn2;
		this.sourceUpdated = sourceUpdated;
	}

	public PatientVisit(int accountId, int patientVisitId) {
		this.patientVisitPK = new PatientVisitPK(accountId, patientVisitId);
	}

	public PatientVisitPK getPatientVisitPK() {
		return patientVisitPK;
	}

	public void setPatientVisitPK(PatientVisitPK patientVisitPK) {
		this.patientVisitPK = patientVisitPK;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(int clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	public String getVisitIdentifier() {
		return visitIdentifier;
	}

	public void setVisitIdentifier(String visitIdentifier) {
		this.visitIdentifier = visitIdentifier;
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

	public short getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(short visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public Date getEncounterStartDate() {
		return encounterStartDate;
	}

	public void setEncounterStartDate(Date encounterStartDate) {
		this.encounterStartDate = encounterStartDate;
	}

	public Date getEncounterEndDate() {
		return encounterEndDate;
	}

	public void setEncounterEndDate(Date encounterEndDate) {
		this.encounterEndDate = encounterEndDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getEthnicityCode() {
		return ethnicityCode;
	}

	public void setEthnicityCode(String ethnicityCode) {
		this.ethnicityCode = ethnicityCode;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getSmokingStatus() {
		return smokingStatus;
	}

	public void setSmokingStatus(String smokingStatus) {
		this.smokingStatus = smokingStatus;
	}

	public String getSmokingStatusCode() {
		return smokingStatusCode;
	}

	public void setSmokingStatusCode(String smokingStatusCode) {
		this.smokingStatusCode = smokingStatusCode;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getEtlColumn1() {
		return etlColumn1;
	}

	public void setEtlColumn1(String etlColumn1) {
		this.etlColumn1 = etlColumn1;
	}

	public String getEtlColumn2() {
		return etlColumn2;
	}

	public void setEtlColumn2(String etlColumn2) {
		this.etlColumn2 = etlColumn2;
	}

	public Date getSourceUpdated() {
		return sourceUpdated;
	}

	public void setSourceUpdated(Date sourceUpdated) {
		this.sourceUpdated = sourceUpdated;
	}

	public List<PatientCareTeam> getPatientCareTeamList() {
		return patientCareTeamList;
	}

	public void setPatientCareTeamList(List<PatientCareTeam> patientCareTeamList) {
		this.patientCareTeamList = patientCareTeamList;
	}

	public List<PatientImmunization> getPatientImmunizationList() {
		return patientImmunizationList;
	}

	public void setPatientImmunizationList(List<PatientImmunization> patientImmunizationList) {
		this.patientImmunizationList = patientImmunizationList;
	}

	/*
	 * public List<PatientFeedback> getPatientFeedbackList() { return
	 * patientFeedbackList; }
	 * 
	 * public void setPatientFeedbackList(List<PatientFeedback>
	 * patientFeedbackList) { this.patientFeedbackList = patientFeedbackList; }
	 */

	public List<PatientPrescription> getPatientMedicationList() {
		return patientMedicationList;
	}

	public void setPatientMedicationList(List<PatientPrescription> patientMedicationList) {
		this.patientMedicationList = patientMedicationList;
	}

	public List<PatientVitalSign> getPatientVitalSignList() {
		return patientVitalSignList;
	}

	public void setPatientVitalSignList(List<PatientVitalSign> patientVitalSignList) {
		this.patientVitalSignList = patientVitalSignList;
	}

	public List<PatientLab> getPatientLabList() {
		return patientLabList;
	}

	public void setPatientLabList(List<PatientLab> patientLabList) {
		this.patientLabList = patientLabList;
	}

	public List<PatientFunctionalStatus> getPatientFunctionalStatusList() {
		return patientFunctionalStatusList;
	}

	public void setPatientFunctionalStatusList(List<PatientFunctionalStatus> patientFunctionalStatusList) {
		this.patientFunctionalStatusList = patientFunctionalStatusList;
	}

	public List<PatientDiagnosis> getPatientDiagnosisList() {
		return patientDiagnosisList;
	}

	public void setPatientDiagnosisList(List<PatientDiagnosis> patientDiagnosisList) {
		this.patientDiagnosisList = patientDiagnosisList;
	}

	public List<PatientCarePlan> getPatientCarePlanList() {
		return patientCarePlanList;
	}

	public void setPatientCarePlanList(List<PatientCarePlan> patientCarePlanList) {
		this.patientCarePlanList = patientCarePlanList;
	}

	public List<PatientProcedure> getPatientProcedureList() {
		return patientProcedureList;
	}

	public void setPatientProcedureList(List<PatientProcedure> patientProcedureList) {
		this.patientProcedureList = patientProcedureList;
	}

	public List<PatientVisitInpatient> getPatientVisitInpatientList() {
		return patientVisitInpatientList;
	}

	public void setPatientVisitInpatientList(List<PatientVisitInpatient> patientVisitInpatientList) {
		this.patientVisitInpatientList = patientVisitInpatientList;
	}

	/*
	 * public Integer getAccountId() { return accountId; }
	 * 
	 * public void setAccountId(Integer accountId) { this.accountId = accountId;
	 * }
	 */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (patientVisitPK != null ? patientVisitPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PatientVisit)) {
			return false;
		}
		PatientVisit other = (PatientVisit) object;
		if ((this.patientVisitPK == null && other.patientVisitPK != null)
				|| (this.patientVisitPK != null && !this.patientVisitPK.equals(other.patientVisitPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.PatientVisit[ patientVisitId=" + patientVisitPK + " ]";
	}

}
