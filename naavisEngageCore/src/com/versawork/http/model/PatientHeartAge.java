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
@Table(name = "patient_heart_age")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatientHeartAge.findAll", query = "SELECT p FROM PatientHeartAge p"),
    @NamedQuery(name = "PatientHeartAge.findByClientId", query = "SELECT p FROM PatientHeartAge p WHERE p.clientId = :clientId"),
    @NamedQuery(name = "PatientHeartAge.findByClientDatabaseId", query = "SELECT p FROM PatientHeartAge p WHERE p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientHeartAge.findByClientIdClientDatabaseIdAccountId", query = "SELECT p FROM PatientHeartAge p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientHeartAge.findByClientIdClientDatabaseIdAccountIdHeartAgeDate", query = "SELECT p FROM PatientHeartAge p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId and p.heartAgeDate  BETWEEN :startDate AND :endDate order by p.heartAgeDate desc"),
    //@NamedQuery(name = "PatientHeartAge.findByAccountId", query = "SELECT p FROM PatientHeartAge p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId order by p.heartAgeId desc"),
    @NamedQuery(name = "PatientHeartAge.findByAccountId", query = "SELECT p FROM PatientHeartAge p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId and p.heartAgeId=(select max(p1.heartAgeId) from PatientHeartAge p1 where p1.accountId = :accountId and  p1.clientId = :clientId and p1.clientDatabaseId = :clientDatabaseId) "),
    @NamedQuery(name = "PatientHeartAge.findByHeartAgeId", query = "SELECT p FROM PatientHeartAge p WHERE p.heartAgeId = :heartAgeId"),
    @NamedQuery(name = "PatientHeartAge.findBySourceId", query = "SELECT p FROM PatientHeartAge p WHERE p.sourceId = :sourceId"),
    @NamedQuery(name = "PatientHeartAge.findBySourceName", query = "SELECT p FROM PatientHeartAge p WHERE p.sourceName = :sourceName"),
    @NamedQuery(name = "PatientHeartAge.findByHeartAge", query = "SELECT p FROM PatientHeartAge p WHERE p.heartAge = :heartAge"),
    @NamedQuery(name = "PatientHeartAge.findByHeartAgeDate", query = "SELECT p FROM PatientHeartAge p WHERE p.heartAgeDate = :heartAgeDate"),
    @NamedQuery(name = "PatientHeartAge.findByDateAdded", query = "SELECT p FROM PatientHeartAge p WHERE p.dateAdded = :dateAdded")})
public class PatientHeartAge implements Serializable {
    private static final long serialVersionUID = 1L;
    //@EmbeddedId
    //protected PatientHeartAgePK patientHeartAgePK;
    
    @Id
   	@Basic(optional = false)
   	@GeneratedValue(strategy = GenerationType.AUTO)	    
    @Column(name = "heart_age_id")
    private int heartAgeId;
    
    @Basic(optional = false)
    @Column(name = "account_id")
    private int accountId;
    
    @Basic(optional = false)
    @Column(name = "client_id")
    private int clientId;
    @Basic(optional = false)
    @Column(name = "client_database_id")
    private int clientDatabaseId;
    @Column(name = "source_id")
    private String sourceId;
    @Column(name = "source_name")
    private String sourceName;
    @Basic(optional = false)
    @Column(name = "heart_age")
    private int heartAge;
    @Basic(optional = false)
    @Column(name = "heart_age_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date heartAgeDate;
    @Basic(optional = false)
    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    public PatientHeartAge() {
    }

    /*public PatientHeartAge(PatientHeartAgePK patientHeartAgePK) {
        this.patientHeartAgePK = patientHeartAgePK;
    }

    public PatientHeartAge(PatientHeartAgePK patientHeartAgePK, int clientId, int clientDatabaseId, int heartAge, Date heartAgeDate, Date dateAdded) {
        this.patientHeartAgePK = patientHeartAgePK;
        this.clientId = clientId;
        this.clientDatabaseId = clientDatabaseId;
        this.heartAge = heartAge;
        this.heartAgeDate = heartAgeDate;
        this.dateAdded = dateAdded;
    }

    public PatientHeartAge(int accountId, int heartAgeId) {
        this.patientHeartAgePK = new PatientHeartAgePK(accountId, heartAgeId);
    }

    public PatientHeartAgePK getPatientHeartAgePK() {
        return patientHeartAgePK;
    }

    public void setPatientHeartAgePK(PatientHeartAgePK patientHeartAgePK) {
        this.patientHeartAgePK = patientHeartAgePK;
    }*/

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

    public int getHeartAge() {
        return heartAge;
    }

    public void setHeartAge(int heartAge) {
        this.heartAge = heartAge;
    }

    public Date getHeartAgeDate() {
        return heartAgeDate;
    }

    public void setHeartAgeDate(Date heartAgeDate) {
        this.heartAgeDate = heartAgeDate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

	public int getHeartAgeId() {
		return heartAgeId;
	}

	public void setHeartAgeId(int heartAgeId) {
		this.heartAgeId = heartAgeId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + heartAgeId;
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
		PatientHeartAge other = (PatientHeartAge) obj;
		if (heartAgeId != other.heartAgeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PatientHeartAge [heartAgeId=" + heartAgeId + "]";
	}

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (patientHeartAgePK != null ? patientHeartAgePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientHeartAge)) {
            return false;
        }
        PatientHeartAge other = (PatientHeartAge) object;
        if ((this.patientHeartAgePK == null && other.patientHeartAgePK != null) || (this.patientHeartAgePK != null && !this.patientHeartAgePK.equals(other.patientHeartAgePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientHeartAge[ patientHeartAgePK=" + patientHeartAgePK + " ]";
    }*/
    
}
