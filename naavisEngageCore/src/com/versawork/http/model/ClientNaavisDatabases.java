/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "client_naavis_databases")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "ClientNaavisDatabases.findAll", query = "SELECT c FROM ClientNaavisDatabases c"),
		@NamedQuery(name = "ClientNaavisDatabases.findByClientId", query = "SELECT c FROM ClientNaavisDatabases c WHERE c.clientNaavisDatabasesPK.clientId = :clientId"),
		@NamedQuery(name = "ClientNaavisDatabases.findByClientDatabaseId", query = "SELECT c FROM ClientNaavisDatabases c WHERE c.clientNaavisDatabasesPK.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "ClientNaavisDatabases.findRestFacilities", query = "SELECT c FROM ClientNaavisDatabases c WHERE c.clientNaavisDatabasesPK.clientDatabaseId != :clientDatabaseId and c.facilityName LIKE :facilityName"),
		@NamedQuery(name = "ClientNaavisDatabases.findRestFacilitiesORCity", query = "SELECT c FROM ClientNaavisDatabases c WHERE c.facilityName LIKE :facilityName OR   c.city LIKE :city"),
		@NamedQuery(name = "ClientNaavisDatabases.findByAddress1", query = "SELECT c FROM ClientNaavisDatabases c WHERE c.address1 = :address1"),
		@NamedQuery(name = "ClientNaavisDatabases.findByAddress2", query = "SELECT c FROM ClientNaavisDatabases c WHERE c.address2 = :address2"),
		@NamedQuery(name = "ClientNaavisDatabases.findByCity", query = "SELECT c FROM ClientNaavisDatabases c WHERE c.city = :city"),
		@NamedQuery(name = "ClientNaavisDatabases.findByState", query = "SELECT c FROM ClientNaavisDatabases c WHERE c.state = :state"),
		@NamedQuery(name = "ClientNaavisDatabases.findByPostalCode", query = "SELECT c FROM ClientNaavisDatabases c WHERE c.postalCode = :postalCode"),
		@NamedQuery(name = "ClientNaavisDatabases.findByCountry", query = "SELECT c FROM ClientNaavisDatabases c WHERE c.country = :country"),
		@NamedQuery(name = "ClientNaavisDatabases.findByType", query = "SELECT c FROM ClientNaavisDatabases c WHERE c.type = :type") 
		})
public class ClientNaavisDatabases implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected ClientNaavisDatabasesPK clientNaavisDatabasesPK;
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
	@Column(name = "type")
	private String type;
	@Column(name = "facility_name")
	private String facilityName;
	@Column(name = "facillity_phone_number")
	private String facillityPhoneNumber;

	/*
	 * @Column(name = "latitude") private double latitude;
	 * 
	 * @Column(name = "longitude") private double longitude;
	 * 
	 * public double getLatitude() { return latitude; }
	 * 
	 * public void setLatitude(double latitude) { this.latitude = latitude; }
	 * 
	 * public double getLongitude() { return longitude; }
	 * 
	 * public void setLongitude(double longitude) { this.longitude = longitude;
	 * }
	 */

	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientNaavisDatabases")
	 * private List<PatientVerification> patientVerificationList;
	 */

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public ClientNaavisDatabases() {
	}

	public ClientNaavisDatabases(ClientNaavisDatabasesPK clientNaavisDatabasesPK) {
		this.clientNaavisDatabasesPK = clientNaavisDatabasesPK;
	}

	public ClientNaavisDatabases(int clientId, int clientDatabaseId) {
		this.clientNaavisDatabasesPK = new ClientNaavisDatabasesPK(clientId, clientDatabaseId);
	}

	public ClientNaavisDatabasesPK getClientNaavisDatabasesPK() {
		return clientNaavisDatabasesPK;
	}

	public void setClientNaavisDatabasesPK(ClientNaavisDatabasesPK clientNaavisDatabasesPK) {
		this.clientNaavisDatabasesPK = clientNaavisDatabasesPK;
	}

	public String getFacillityPhoneNumber() {
		return facillityPhoneNumber;
	}

	public void setFacillityPhoneNumber(String facillityPhoneNumber) {
		this.facillityPhoneNumber = facillityPhoneNumber;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*
	 * public List<PatientVerification> getPatientVerificationList() { return
	 * patientVerificationList; }
	 * 
	 * public void setPatientVerificationList(List<PatientVerification>
	 * patientVerificationList) { this.patientVerificationList =
	 * patientVerificationList; }
	 */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (clientNaavisDatabasesPK != null ? clientNaavisDatabasesPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ClientNaavisDatabases)) {
			return false;
		}
		ClientNaavisDatabases other = (ClientNaavisDatabases) object;
		if ((this.clientNaavisDatabasesPK == null && other.clientNaavisDatabasesPK != null)
				|| (this.clientNaavisDatabasesPK != null && !this.clientNaavisDatabasesPK
						.equals(other.clientNaavisDatabasesPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.ClientNaavisDatabases[ clientNaavisDatabasesPK=" + clientNaavisDatabasesPK
				+ " ]";
	}

}
