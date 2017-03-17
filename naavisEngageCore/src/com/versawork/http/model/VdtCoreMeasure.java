/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 
 * @author Sohaib
 */
@Entity
/*
 * @Table(name = "vdt_core_measure")
 * 
 * @XmlRootElement
 * 
 * @NamedQueries({
 * 
 * @NamedQuery(name = "VdtCoreMeasure.findAll", query =
 * "SELECT v FROM VdtCoreMeasure v"),
 * 
 * @NamedQuery(name = "VdtCoreMeasure.findById", query =
 * "SELECT v FROM VdtCoreMeasure v WHERE v.id = :id"),
 * 
 * @NamedQuery(name = "VdtCoreMeasure.findByPatientId", query =
 * "SELECT v FROM VdtCoreMeasure v WHERE v.patientId = :patientId"),
 * 
 * @NamedQuery(name = "VdtCoreMeasure.findByLastName", query =
 * "SELECT v FROM VdtCoreMeasure v WHERE v.lastName = :lastName"),
 * 
 * @NamedQuery(name = "VdtCoreMeasure.findByFirstName", query =
 * "SELECT v FROM VdtCoreMeasure v WHERE v.firstName = :firstName"),
 * 
 * @NamedQuery(name = "VdtCoreMeasure.findByDateOfBirth", query =
 * "SELECT v FROM VdtCoreMeasure v WHERE v.dateOfBirth = :dateOfBirth"),
 * 
 * @NamedQuery(name = "VdtCoreMeasure.findByAge", query =
 * "SELECT v FROM VdtCoreMeasure v WHERE v.age = :age"),
 * 
 * @NamedQuery(name = "VdtCoreMeasure.findBySex", query =
 * "SELECT v FROM VdtCoreMeasure v WHERE v.sex = :sex"),
 * 
 * @NamedQuery(name = "VdtCoreMeasure.findByEncounterDate", query =
 * "SELECT v FROM VdtCoreMeasure v WHERE v.encounterDate = :encounterDate"),
 * 
 * @NamedQuery(name = "VdtCoreMeasure.findByPopulateDenominator", query =
 * "SELECT v FROM VdtCoreMeasure v WHERE v.populateDenominator = :populateDenominator"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasure.findByPopulateNumerator", query =
 * "SELECT v FROM VdtCoreMeasure v WHERE v.populateNumerator = :populateNumerator"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasure.findByNumeratorRecorded", query =
 * "SELECT v FROM VdtCoreMeasure v WHERE v.numeratorRecorded = :numeratorRecorded"
 * )})
 */
public class VdtCoreMeasure implements Serializable {
	private static final long serialVersionUID = 1L;
	// @Id //Annotated all columns with ID since no primary key is present,
	// hence combining all columns to make composite key
	// @Basic(optional = false)
	// @Column(name = "ID")
	// private Integer id;
	@Id
	@Basic(optional = false)
	@Column(name = "patient_id")
	private Integer patientId;
	/*
	 * @Id
	 * 
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "scenario_id") private Integerscenario_id;
	 * 
	 * @Id
	 */
	@Basic(optional = false)
	@Column(name = "last_name")
	private String lastName;
	@Id
	@Basic(optional = false)
	@Column(name = "first_name")
	private String firstName;
	@Id
	@Basic(optional = false)
	@Column(name = "date_of_birth")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;

	/*
	 * @Id
	 * 
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "age") private Integerage;
	 * 
	 * @Id
	 * 
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "sex") private char sex;
	 * 
	 * @Id
	 * 
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "encounter_date")
	 * 
	 * @Temporal(TemporalType.TIMESTAMP) private Date encounterDate;
	 * 
	 * @Id
	 * 
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "event_date")
	 * 
	 * @Temporal(TemporalType.TIMESTAMP) private Date eventDate;
	 * 
	 * @Id
	 * 
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "populate_denominator") private
	 * IntegerpopulateDenominator;
	 * 
	 * @Id
	 * 
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "populate_numerator") private IntegerpopulateNumerator;
	 * 
	 * @Id
	 * 
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "numerator_recorded") private IntegernumeratorRecorded;
	 */
	/*
	 * public Date getEventDate() { return eventDate; }
	 * 
	 * public void setEventDate(Date eventDate) { this.eventDate = eventDate; }
	 */

	public VdtCoreMeasure() {
	}

	public VdtCoreMeasure(Integer patientId) {
		this.patientId = patientId;
	}

	public VdtCoreMeasure(/* Integer id, Integerscenario_id, */int patientId, String lastName, String firstName,
			Date dateOfBirth/*
							 * , Integerage, char sex, Date encounterDate,
							 * IntegerpopulateDenominator,
							 * IntegerpopulateNumerator,
							 * IntegernumeratorRecorded,Date eventDate
							 */) {
		// this.id = id;
		this.patientId = patientId;
		// this.scenario_id = scenario_id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		// this.age = age;
		// this.sex = sex;
		// this.encounterDate = encounterDate;
		/*
		 * this.populateDenominator = populateDenominator;
		 * this.populateNumerator = populateNumerator; this.numeratorRecorded =
		 * numeratorRecorded; this.eventDate = eventDate;
		 */
	}

	/*
	 * public Integer getId() { return id; }
	 * 
	 * public void setId(Integer id) { this.id = id; }
	 */

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/*
	 * public IntegergetAge() { return age; }
	 * 
	 * public void setAge(Integer age) { this.age = age; }
	 * 
	 * public char getSex() { return sex; }
	 * 
	 * public void setSex(char sex) { this.sex = sex; }
	 * 
	 * public Date getEncounterDate() { return encounterDate; }
	 * 
	 * public void setEncounterDate(Date encounterDate) { this.encounterDate =
	 * encounterDate; }
	 * 
	 * public IntegergetPopulateDenominator() { return populateDenominator; }
	 * 
	 * public void setPopulateDenominator(Integer populateDenominator) {
	 * this.populateDenominator = populateDenominator; }
	 * 
	 * public IntegergetPopulateNumerator() { return populateNumerator; }
	 * 
	 * public void setPopulateNumerator(Integer populateNumerator) {
	 * this.populateNumerator = populateNumerator; }
	 * 
	 * public IntegergetNumeratorRecorded() { return numeratorRecorded; }
	 * 
	 * public void setNumeratorRecorded(Integer numeratorRecorded) {
	 * this.numeratorRecorded = numeratorRecorded; }
	 * 
	 * @Override public IntegerhashCode() { Integerhash = 0; hash += (id != null
	 * ? id.hashCode() : 0); return hash; }
	 * 
	 * 
	 * public IntegergetScenario_id() { return scenario_id; }
	 * 
	 * public void setScenario_id(Integer scenario_id) { this.scenario_id =
	 * scenario_id; }
	 */
	/*
	 * @Override public boolean equals(Object object) { // TODO: Warning - this
	 * method won't work in the case the id fields are not set if (!(object
	 * instanceof VdtCoreMeasure)) { return false; } VdtCoreMeasure other =
	 * (VdtCoreMeasure) object; if ((this.id == null && other.id != null) ||
	 * (this.id != null && !this.id.equals(other.id))) { return false; } return
	 * true; }
	 */
	/*
	 * @Override public String toString() { return
	 * "javaapplication2.VdtCoreMeasure[ id=" + id + " ]"; }
	 */

}
