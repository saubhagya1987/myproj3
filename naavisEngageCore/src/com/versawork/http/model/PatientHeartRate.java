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
@Table(name = "patient_heart_rate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatientHeartRate.findAll", query = "SELECT p FROM PatientHeartRate p"),
    @NamedQuery(name = "PatientHeartRate.findByClientId", query = "SELECT p FROM PatientHeartRate p WHERE p.clientId = :clientId"),
    @NamedQuery(name = "PatientHeartRate.findByClientDatabaseId", query = "SELECT p FROM PatientHeartRate p WHERE p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientHeartRate.findByClientIdClientDatabaseIdAccountId", query = "SELECT p FROM PatientHeartRate p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientHeartRate.findByClientIdClientDatabaseIdAccountIdHeartRateDate", query = "SELECT p FROM PatientHeartRate p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId  and p.heartRateDate  BETWEEN :startDate AND :endDate order by p.heartRateDate desc"),
    //@NamedQuery(name = "PatientHeartRate.findByAccountId", query = "SELECT p FROM PatientHeartRate p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId order by p.heartRateId desc"),
    @NamedQuery(name = "PatientHeartRate.findByAccountId", query = "SELECT p FROM PatientHeartRate p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId and p.heartRateId=(select max(p1.heartRateId) from PatientHeartRate p1 where p1.accountId = :accountId and  p1.clientId = :clientId and p1.clientDatabaseId = :clientDatabaseId) "),
    @NamedQuery(name = "PatientHeartRate.findByHeartRateId", query = "SELECT p FROM PatientHeartRate p WHERE p.heartRateId = :heartRateId"),
    @NamedQuery(name = "PatientHeartRate.findBySourceId", query = "SELECT p FROM PatientHeartRate p WHERE p.sourceId = :sourceId"),
    @NamedQuery(name = "PatientHeartRate.findBySourceName", query = "SELECT p FROM PatientHeartRate p WHERE p.sourceName = :sourceName"),
    @NamedQuery(name = "PatientHeartRate.findByHeartRate", query = "SELECT p FROM PatientHeartRate p WHERE p.heartRate = :heartRate"),
    @NamedQuery(name = "PatientHeartRate.findByHeartRateDate", query = "SELECT p FROM PatientHeartRate p WHERE p.heartRateDate = :heartRateDate"),
    @NamedQuery(name = "PatientHeartRate.findByDateAdded", query = "SELECT p FROM PatientHeartRate p WHERE p.dateAdded = :dateAdded")})
public class PatientHeartRate implements Serializable {
    private static final long serialVersionUID = 1L;
    //@EmbeddedId
    //protected PatientHeartRatePK patientHeartRatePK;
    
    @Id
   	@Basic(optional = false)
   	@GeneratedValue(strategy = GenerationType.AUTO)	    
    @Column(name = "heart_rate_id")
    private int heartRateId;
    
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
    @Column(name = "heart_rate")
    private int heartRate;
    @Basic(optional = false)
    @Column(name = "heart_rate_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date heartRateDate;
    @Basic(optional = false)
    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    public PatientHeartRate() {
    }

    /*public PatientHeartRate(PatientHeartRatePK patientHeartRatePK) {
        this.patientHeartRatePK = patientHeartRatePK;
    }

    public PatientHeartRate(PatientHeartRatePK patientHeartRatePK, int clientId, int clientDatabaseId, int heartRate, Date heartRateDate, Date dateAdded) {
        this.patientHeartRatePK = patientHeartRatePK;
        this.clientId = clientId;
        this.clientDatabaseId = clientDatabaseId;
        this.heartRate = heartRate;
        this.heartRateDate = heartRateDate;
        this.dateAdded = dateAdded;
    }

    public PatientHeartRate(int accountId, int heartRateId) {
        this.patientHeartRatePK = new PatientHeartRatePK(accountId, heartRateId);
    }

    public PatientHeartRatePK getPatientHeartRatePK() {
        return patientHeartRatePK;
    }

    public void setPatientHeartRatePK(PatientHeartRatePK patientHeartRatePK) {
        this.patientHeartRatePK = patientHeartRatePK;
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

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public Date getHeartRateDate() {
        return heartRateDate;
    }

    public void setHeartRateDate(Date heartRateDate) {
        this.heartRateDate = heartRateDate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

	public int getHeartRateId() {
		return heartRateId;
	}

	public void setHeartRateId(int heartRateId) {
		this.heartRateId = heartRateId;
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
		result = prime * result + heartRateId;
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
		PatientHeartRate other = (PatientHeartRate) obj;
		if (heartRateId != other.heartRateId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PatientHeartRate [heartRateId=" + heartRateId + "]";
	}

   /* @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientHeartRatePK != null ? patientHeartRatePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientHeartRate)) {
            return false;
        }
        PatientHeartRate other = (PatientHeartRate) object;
        if ((this.patientHeartRatePK == null && other.patientHeartRatePK != null) || (this.patientHeartRatePK != null && !this.patientHeartRatePK.equals(other.patientHeartRatePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientHeartRate[ patientHeartRatePK=" + patientHeartRatePK + " ]";
    }*/
    
}
