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
@Table(name = "facility_service")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "FacilityService.findAll", query = "SELECT f FROM FacilityService f"),
		@NamedQuery(name = "FacilityService.findByClientId", query = "SELECT f FROM FacilityService f WHERE f.facilityServicePK.clientId = :clientId"),
		@NamedQuery(name = "FacilityService.findByClientDatabaseId", query = "SELECT f FROM FacilityService f WHERE f.facilityServicePK.clientDatabaseId = :clientDatabaseId"),
		@NamedQuery(name = "FacilityService.findBySourceId", query = "SELECT f FROM FacilityService f WHERE f.sourceId = :sourceId"),
		@NamedQuery(name = "FacilityService.findBySourceName", query = "SELECT f FROM FacilityService f WHERE f.sourceName = :sourceName"),
		@NamedQuery(name = "FacilityService.findByServiceGroup", query = "SELECT f FROM FacilityService f WHERE f.serviceGroup = :serviceGroup"),
		@NamedQuery(name = "FacilityService.findByServiceId", query = "SELECT f FROM FacilityService f WHERE f.facilityServicePK.serviceId = :serviceId"),
		@NamedQuery(name = "FacilityService.findByService", query = "SELECT f FROM FacilityService f WHERE f.service = :service"),
		@NamedQuery(name = "FacilityService.findByContactPhoneNumber", query = "SELECT f FROM FacilityService f WHERE f.contactPhoneNumber = :contactPhoneNumber"),
		@NamedQuery(name = "FacilityService.findByContactPhoneExtension", query = "SELECT f FROM FacilityService f WHERE f.contactPhoneExtension = :contactPhoneExtension"),
		@NamedQuery(name = "FacilityService.findByContactEmail", query = "SELECT f FROM FacilityService f WHERE f.contactEmail = :contactEmail"),
		@NamedQuery(name = "FacilityService.findByDateAdded", query = "SELECT f FROM FacilityService f WHERE f.dateAdded = :dateAdded"),
		@NamedQuery(name = "FacilityService.findByDateModified", query = "SELECT f FROM FacilityService f WHERE f.dateModified = :dateModified") })
public class FacilityService implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected FacilityServicePK facilityServicePK;
	@Column(name = "source_id")
	private String sourceId;
	@Basic(optional = false)
	@Column(name = "source_name")
	private String sourceName;
	@Basic(optional = false)
	@Column(name = "service_group")
	private String serviceGroup;
	@Basic(optional = false)
	@Column(name = "service")
	private String service;
	@Basic(optional = false)
	@Column(name = "contact_phone_number")
	private String contactPhoneNumber;
	@Column(name = "contact_phone_extension")
	private String contactPhoneExtension;
	@Column(name = "contact_email")
	private String contactEmail;
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Column(name = "date_modified")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;

	public FacilityService() {
	}

	public FacilityService(FacilityServicePK facilityServicePK) {
		this.facilityServicePK = facilityServicePK;
	}

	public FacilityService(FacilityServicePK facilityServicePK, String sourceName, String serviceGroup, String service,
			String contactPhoneNumber, Date dateAdded) {
		this.facilityServicePK = facilityServicePK;
		this.sourceName = sourceName;
		this.serviceGroup = serviceGroup;
		this.service = service;
		this.contactPhoneNumber = contactPhoneNumber;
		this.dateAdded = dateAdded;
	}

	public FacilityService(int clientId, int clientDatabaseId, String serviceId) {
		this.facilityServicePK = new FacilityServicePK(clientId, clientDatabaseId, serviceId);
	}

	public FacilityServicePK getFacilityServicePK() {
		return facilityServicePK;
	}

	public void setFacilityServicePK(FacilityServicePK facilityServicePK) {
		this.facilityServicePK = facilityServicePK;
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

	public String getServiceGroup() {
		return serviceGroup;
	}

	public void setServiceGroup(String serviceGroup) {
		this.serviceGroup = serviceGroup;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
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
		hash += (facilityServicePK != null ? facilityServicePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof FacilityService)) {
			return false;
		}
		FacilityService other = (FacilityService) object;
		if ((this.facilityServicePK == null && other.facilityServicePK != null)
				|| (this.facilityServicePK != null && !this.facilityServicePK.equals(other.facilityServicePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.FacilityService[ facilityServicePK=" + facilityServicePK + " ]";
	}

}
