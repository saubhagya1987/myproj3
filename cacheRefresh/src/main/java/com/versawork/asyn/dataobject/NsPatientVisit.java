package com.versawork.asyn.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.versawork.asyn.constant.VersaworkConstants;

/**
 * @author Sohaib
 *
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsPatientVisit implements Serializable {

	private static final long serialVersionUID = 1L;
	private int patientVisitId;
	private int clientId;
	private int clientDatabaseId;
	private String visitIdentifier;
	private int accountId;
	private String sourceId;
	private String sourceName;
	private short visitTypeId;
	private String visitDate;
	private Date admitDate;
	private Date dischargeDate;
	private String firstName;
	private String lastName;
	private String sex;
	private Date birthDate;
	private String race;
	private String raceCode;
	private String ethnicity;
	private String ethnicityCode;
	private String preferredLanguage;
	private String smokingStatus;
	private String smokingStatusCode;
	private Date dateAdded;
	private Integer clientNaavisDatabases;

	private String attendingPhysicianName;
	private String location;
	private String visitType;
	private String unitNumber;

	public String getUnitNumber() {

		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {

		this.unitNumber = unitNumber;
	}

	public String getAttendingPhysicianName() {

		return attendingPhysicianName;
	}

	public void setAttendingPhysicianName(String attendingPhysicianName) {

		this.attendingPhysicianName = attendingPhysicianName;
	}

	public String getLocation() {

		return location;
	}

	public void setLocation(String location) {

		this.location = location;
	}

	public String getVisitType() {

		return visitType;
	}

	public void setVisitType(String visitType) {

		this.visitType = visitType;
	}

	public int getPatientVisitId() {

		return patientVisitId;
	}

	public void setPatientVisitId(int patientVisitId) {

		this.patientVisitId = patientVisitId;
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

	public int getAccountId() {

		return accountId;
	}

	public void setAccountId(int accountId) {

		this.accountId = accountId;
	}

	public String getSourceId() {

		return sourceId;
	}

	public void setSourceId(String sourceId) {

		if (sourceId != null) {
			this.sourceId = sourceId;
		} else {
			this.sourceId = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
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

	public String getVisitDate() {

		return visitDate;
	}

	public void setVisitDate(String visitDate) {

		if (visitDate != null) {
			this.visitDate = visitDate;
		} else {
			this.visitDate = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public Date getAdmitDate() {

		return admitDate;
	}

	public void setAdmitDate(Date admitDate) {

		this.admitDate = admitDate;
	}

	public Date getDischargeDate() {

		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {

		this.dischargeDate = dischargeDate;
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

		if (sex != null) {
			this.sex = sex;
		} else {
			this.sex = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
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

		if (race != null) {
			this.race = race;
		} else {
			this.sex = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getRaceCode() {

		return raceCode;
	}

	public void setRaceCode(String raceCode) {

		if (raceCode != null) {
			this.raceCode = raceCode;
		} else {
			this.raceCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getEthnicity() {

		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {

		if (ethnicity != null) {
			this.ethnicity = ethnicity;
		} else {
			this.ethnicity = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getEthnicityCode() {

		return ethnicityCode;
	}

	public void setEthnicityCode(String ethnicityCode) {

		if (ethnicityCode != null) {
			this.ethnicityCode = ethnicityCode;
		} else {
			this.ethnicityCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getPreferredLanguage() {

		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {

		if (preferredLanguage != null) {
			this.preferredLanguage = preferredLanguage;
		} else {
			this.preferredLanguage = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getSmokingStatus() {

		return smokingStatus;
	}

	public void setSmokingStatus(String smokingStatus) {

		if (smokingStatus != null) {
			this.smokingStatus = smokingStatus;
		} else {
			this.smokingStatus = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public String getSmokingStatusCode() {

		return smokingStatusCode;
	}

	public void setSmokingStatusCode(String smokingStatusCode) {

		if (smokingStatusCode != null) {
			this.smokingStatusCode = smokingStatusCode;
		} else {
			this.smokingStatusCode = VersaworkConstants.NOT_APPLICABLE.getMessage();
		}
	}

	public Date getDateAdded() {

		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {

		this.dateAdded = dateAdded;
	}

	public Integer getClientNaavisDatabases() {

		return clientNaavisDatabases;
	}

	public void setClientNaavisDatabases(Integer clientNaavisDatabases) {

		this.clientNaavisDatabases = clientNaavisDatabases;
	}

}
