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
@Table(name = "patient_active_time")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatientActiveTime.findAll", query = "SELECT p FROM PatientActiveTime p"),
    @NamedQuery(name = "PatientActiveTime.findByClientId", query = "SELECT p FROM PatientActiveTime p WHERE p.clientId = :clientId"),
    @NamedQuery(name = "PatientActiveTime.findByClientDatabaseId", query = "SELECT p FROM PatientActiveTime p WHERE p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientActiveTime.findByClientIdClientDatabaseIdAccountId", query = "SELECT p FROM PatientActiveTime p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientActiveTime.findByClientIdClientDatabaseIdAccountIdActiveTimeDate", query = "SELECT p FROM PatientActiveTime p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId and p.activeTimeDate  BETWEEN :startDate AND :endDate order by p.activeTimeDate desc"),
    //@NamedQuery(name = "PatientActiveTime.findByAccountId", query = "SELECT p FROM PatientActiveTime p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId order by p.activeTimeId desc "),
    @NamedQuery(name = "PatientActiveTime.findByAccountId", query = "SELECT p FROM PatientActiveTime p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId and p.activeTimeId=(select max(p1.activeTimeId) from PatientActiveTime p1 where p1.accountId = :accountId and  p1.clientId = :clientId and p1.clientDatabaseId = :clientDatabaseId)"),
    @NamedQuery(name = "PatientActiveTime.findByActiveTimeId", query = "SELECT p FROM PatientActiveTime p WHERE p.activeTimeId = :activeTimeId"),
    @NamedQuery(name = "PatientActiveTime.findBySourceId", query = "SELECT p FROM PatientActiveTime p WHERE p.sourceId = :sourceId"),
    @NamedQuery(name = "PatientActiveTime.findBySourceName", query = "SELECT p FROM PatientActiveTime p WHERE p.sourceName = :sourceName"),
    @NamedQuery(name = "PatientActiveTime.findByActiveTime", query = "SELECT p FROM PatientActiveTime p WHERE p.activeTime = :activeTime"),
    @NamedQuery(name = "PatientActiveTime.findByActiveTimeDate", query = "SELECT p FROM PatientActiveTime p WHERE p.activeTimeDate = :activeTimeDate"),
    @NamedQuery(name = "PatientActiveTime.findByDateAdded", query = "SELECT p FROM PatientActiveTime p WHERE p.dateAdded = :dateAdded")})
public class PatientActiveTime implements Serializable {
    private static final long serialVersionUID = 1L;
    //@EmbeddedId
    //protected PatientActiveTimePK patientActiveTimePK;
    
    
    @Id
   	@Basic(optional = false)
   	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "active_time_id")
    private int activeTimeId;
    
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
    @Column(name = "active_time")
    private double activeTime;
    @Basic(optional = false)
    @Column(name = "active_time_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeTimeDate;
    @Basic(optional = false)
    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    public PatientActiveTime() {
    }

    /*public PatientActiveTime(PatientActiveTimePK patientActiveTimePK) {
        this.patientActiveTimePK = patientActiveTimePK;
    }

    public PatientActiveTime(PatientActiveTimePK patientActiveTimePK, int clientId, int clientDatabaseId, int activeTime, Date activeTimeDate, Date dateAdded) {
        this.patientActiveTimePK = patientActiveTimePK;
        this.clientId = clientId;
        this.clientDatabaseId = clientDatabaseId;
        this.activeTime = activeTime;
        this.activeTimeDate = activeTimeDate;
        this.dateAdded = dateAdded;
    }

    public PatientActiveTime(int accountId, int activeTimeId) {
        this.patientActiveTimePK = new PatientActiveTimePK(accountId, activeTimeId);
    }

    public PatientActiveTimePK getPatientActiveTimePK() {
        return patientActiveTimePK;
    }

    public void setPatientActiveTimePK(PatientActiveTimePK patientActiveTimePK) {
        this.patientActiveTimePK = patientActiveTimePK;
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

    public double getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(double activeTime) {
        this.activeTime = activeTime;
    }

    public Date getActiveTimeDate() {
        return activeTimeDate;
    }

    public void setActiveTimeDate(Date activeTimeDate) {
        this.activeTimeDate = activeTimeDate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

	public int getActiveTimeId() {
		return activeTimeId;
	}

	public void setActiveTimeId(int activeTimeId) {
		this.activeTimeId = activeTimeId;
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
		result = prime * result + activeTimeId;
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
		PatientActiveTime other = (PatientActiveTime) obj;
		if (activeTimeId != other.activeTimeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PatientActiveTime [activeTimeId=" + activeTimeId + "]";
	}

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (patientActiveTimePK != null ? patientActiveTimePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientActiveTime)) {
            return false;
        }
        PatientActiveTime other = (PatientActiveTime) object;
        if ((this.patientActiveTimePK == null && other.patientActiveTimePK != null) || (this.patientActiveTimePK != null && !this.patientActiveTimePK.equals(other.patientActiveTimePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientActiveTime[ patientActiveTimePK=" + patientActiveTimePK + " ]";
    }*/
    
}
