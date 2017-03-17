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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "facility_provider")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "FacilityProvider.findAll", query = "SELECT f FROM FacilityProvider f"),
		@NamedQuery(name = "FacilityProvider.findByClientId", query = "SELECT f FROM FacilityProvider f WHERE f.facilityProviderPK.clientId = :clientId order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByClientDatabaseId", query = "SELECT f FROM FacilityProvider f WHERE f.facilityProviderPK.clientDatabaseId = :clientDatabaseId order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByClientDatabaseIdandClientId", query = "SELECT f FROM FacilityProvider f WHERE f.facilityProviderPK.clientDatabaseId = :clientDatabaseId and f.facilityProviderPK.clientId = :clientId order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findBySourceId", query = "SELECT f FROM FacilityProvider f WHERE f.sourceId = :sourceId order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findBySourceName", query = "SELECT f FROM FacilityProvider f WHERE f.sourceName = :sourceName order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByProviverAndClientDatabaseId", query = "SELECT f FROM FacilityProvider f WHERE f.facilityProviderPK.providerId = :providerId and f.facilityProviderPK.clientDatabaseId = :clientDatabaseId order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByProviverAndClientDatabaseIdAndClientId", query = "SELECT f FROM FacilityProvider f WHERE f.facilityProviderPK.providerId = :providerId and f.facilityProviderPK.clientDatabaseId = :clientDatabaseId and f.facilityProviderPK.clientId = :clientId order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByProviderId", query = "SELECT f FROM FacilityProvider f WHERE f.facilityProviderPK.providerId = :providerId order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByFirstName", query = "SELECT f FROM FacilityProvider f WHERE f.firstName = :firstName order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByLastName", query = "SELECT f FROM FacilityProvider f WHERE f.lastName = :lastName order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findBySuffix", query = "SELECT f FROM FacilityProvider f WHERE f.suffix = :suffix order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findBySpecialty", query = "SELECT f FROM FacilityProvider f WHERE f.specialty = :specialty order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByAddress1", query = "SELECT f FROM FacilityProvider f WHERE f.address1 = :address1 order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByAddress2", query = "SELECT f FROM FacilityProvider f WHERE f.address2 = :address2 order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByCity", query = "SELECT f FROM FacilityProvider f WHERE f.city = :city order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByState", query = "SELECT f FROM FacilityProvider f WHERE f.state = :state"),
		@NamedQuery(name = "FacilityProvider.findByPostalCode", query = "SELECT f FROM FacilityProvider f WHERE f.postalCode = :postalCode order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByContactPhoneNumber", query = "SELECT f FROM FacilityProvider f WHERE f.contactPhoneNumber = :contactPhoneNumber order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByContactPhoneExtension", query = "SELECT f FROM FacilityProvider f WHERE f.contactPhoneExtension = :contactPhoneExtension order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByFaxNumber", query = "SELECT f FROM FacilityProvider f WHERE f.faxNumber = :faxNumber order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByContactEmail", query = "SELECT f FROM FacilityProvider f WHERE f.contactEmail = :contactEmail order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByDateAdded", query = "SELECT f FROM FacilityProvider f WHERE f.dateAdded = :dateAdded order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByDateModified", query = "SELECT f FROM FacilityProvider f WHERE f.dateModified = :dateModified order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByConsultantPhysician", query = "SELECT f FROM FacilityProvider f WHERE f.consultantPhysician = :consultantPhysician order by f.lastName,f.firstName"),
		@NamedQuery(name = "FacilityProvider.findByResidentPhysician", query = "SELECT f FROM FacilityProvider f WHERE f.residentPhysician = :residentPhysician order by f.lastName,f.firstName") })
public class FacilityProvider implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected FacilityProviderPK facilityProviderPK;
	@Column(name = "source_id")
	private String sourceId;
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "first_name")
	private String firstName;
	@Basic(optional = false)
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "suffix")
	private String suffix;
	@Basic(optional = false)
	@Column(name = "specialty")
	private String specialty;
	@Basic(optional = false)
	@Column(name = "address_1")
	private String address1;
	@Column(name = "address_2")
	private String address2;
	@Basic(optional = false)
	@Column(name = "city")
	private String city;
	@Basic(optional = false)
	@Column(name = "state")
	private String state;
	@Basic(optional = false)
	@Column(name = "postal_code")
	private String postalCode;
	@Basic(optional = false)
	@Column(name = "contact_phone_number")
	private String contactPhoneNumber;
	@Column(name = "contact_phone_extension")
	private String contactPhoneExtension;
	@Column(name = "fax_number")
	private String faxNumber;
	@Column(name = "contact_email")
	private String contactEmail;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "date_modified")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;
	@Column(name = "consultant_physician")
	private Boolean consultantPhysician;
	@Column(name = "resident_physician")
	private Boolean residentPhysician;

	public Boolean getConsultantPhysician() {
		return consultantPhysician;
	}

	public void setConsultantPhysician(Boolean consultantPhysician) {
		this.consultantPhysician = consultantPhysician;
	}

	public Boolean getResidentPhysician() {
		return residentPhysician;
	}

	public void setResidentPhysician(Boolean residentPhysician) {
		this.residentPhysician = residentPhysician;
	}

	public FacilityProvider() {
	}

	public FacilityProvider(FacilityProviderPK facilityProviderPK) {
		this.facilityProviderPK = facilityProviderPK;
	}

	public FacilityProvider(FacilityProviderPK facilityProviderPK, String sourceName, String firstName,
			String lastName, String specialty, String address1, String city, String state, String postalCode,
			String contactPhoneNumber, Date dateAdded) {
		this.facilityProviderPK = facilityProviderPK;
		this.sourceName = sourceName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialty = specialty;
		this.address1 = address1;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.contactPhoneNumber = contactPhoneNumber;
		this.dateAdded = dateAdded;
	}

	public FacilityProvider(int clientId, int clientDatabaseId, String providerId) {
		this.facilityProviderPK = new FacilityProviderPK(clientId, clientDatabaseId, providerId);
	}

	public FacilityProviderPK getFacilityProviderPK() {
		return facilityProviderPK;
	}

	public void setFacilityProviderPK(FacilityProviderPK facilityProviderPK) {
		this.facilityProviderPK = facilityProviderPK;
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

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
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

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public String getContactPhoneExtension() {
		return contactPhoneExtension;
	}

	public void setContactPhoneExtension(String contactPhoneExtension) {
		this.contactPhoneExtension = contactPhoneExtension;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (facilityProviderPK != null ? facilityProviderPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof FacilityProvider)) {
			return false;
		}
		FacilityProvider other = (FacilityProvider) object;
		if ((this.facilityProviderPK == null && other.facilityProviderPK != null)
				|| (this.facilityProviderPK != null && !this.facilityProviderPK.equals(other.facilityProviderPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.FacilityProvider[ facilityProviderPK=" + facilityProviderPK + " ]";
	}

}
