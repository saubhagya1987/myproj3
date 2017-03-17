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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sunil
 */
@Entity
@Table(name = "patient_distance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatientDistance.findAll", query = "SELECT p FROM PatientDistance p"),
    @NamedQuery(name = "PatientDistance.findByClientId", query = "SELECT p FROM PatientDistance p WHERE p.clientId = :clientId"),
    @NamedQuery(name = "PatientDistance.findByClientDatabaseId", query = "SELECT p FROM PatientDistance p WHERE p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientDistance.findByClientIdClientDatabaseIdAccountId", query = "SELECT p FROM PatientDistance p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientDistance.findByClientIdClientDatabaseIdAccountIdInstanceDate", query = "SELECT p FROM PatientDistance p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId and p.distanceDate  BETWEEN :startDate AND :endDate order by p.distanceDate desc"),
    //@NamedQuery(name = "PatientDistance.findByAccountId", query = "SELECT p FROM PatientDistance p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId order by p.patientDistanceId desc "),
    @NamedQuery(name = "PatientDistance.findByAccountId", query = "SELECT p FROM PatientDistance p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId and p.patientDistanceId=(select max(p1.patientDistanceId) from PatientDistance p1 where p1.accountId = :accountId and  p1.clientId = :clientId and p1.clientDatabaseId = :clientDatabaseId) "),
    @NamedQuery(name = "PatientDistance.findBySourceId", query = "SELECT p FROM PatientDistance p WHERE p.sourceId = :sourceId"),
    @NamedQuery(name = "PatientDistance.findBySourceName", query = "SELECT p FROM PatientDistance p WHERE p.sourceName = :sourceName"),
    @NamedQuery(name = "PatientDistance.findByDistance", query = "SELECT p FROM PatientDistance p WHERE p.distance = :distance"),
    @NamedQuery(name = "PatientDistance.findByDistanceDate", query = "SELECT p FROM PatientDistance p WHERE p.distanceDate = :distanceDate"),
    @NamedQuery(name = "PatientDistance.findByDateAdded", query = "SELECT p FROM PatientDistance p WHERE p.dateAdded = :dateAdded")})
public class PatientDistance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
   	@Basic(optional = false)
   	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "distance_id")
    private int patientDistanceId;
    
    @Basic(optional = false)
    @Column(name = "client_id")
    private int clientId;
    @Basic(optional = false)
    @Column(name = "client_database_id")
    private int clientDatabaseId;
    //@Id
    @Basic(optional = false)
    @Column(name = "account_id")
    private Integer accountId;
    @Column(name = "source_id")
    private String sourceId;
    @Column(name = "source_name")
    private String sourceName;
    @Basic(optional = false)
    @Column(name = "distance")
    private double distance;
    @Basic(optional = false)
    @Column(name = "distance_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date distanceDate;
    @Basic(optional = false)
    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    public PatientDistance() {
    }

    public PatientDistance(Integer accountId) {
        this.accountId = accountId;
    }

    public PatientDistance(int patientDistanceId,Integer accountId, int clientId, int clientDatabaseId, double distance, Date distanceDate, Date dateAdded) {
        this.patientDistanceId=patientDistanceId;
    	this.accountId = accountId;
        this.clientId = clientId;
        this.clientDatabaseId = clientDatabaseId;
        this.distance = distance;
        this.distanceDate = distanceDate;
        this.dateAdded = dateAdded;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + patientDistanceId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientDistance other = (PatientDistance) obj;
		if (patientDistanceId != other.patientDistanceId)
			return false;
		return true;
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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Date getDistanceDate() {
        return distanceDate;
    }

    public void setDistanceDate(Date distanceDate) {
        this.distanceDate = distanceDate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

	public int getPatientDistanceId() {
		return patientDistanceId;
	}

	public void setPatientDistanceId(int patientDistanceId) {
		this.patientDistanceId = patientDistanceId;
	}

	@Override
	public String toString() {
		return "PatientDistance [patientDistanceId=" + patientDistanceId + "]";
	}

   
    
}
