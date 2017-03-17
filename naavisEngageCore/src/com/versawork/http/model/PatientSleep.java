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
@Table(name = "patient_sleep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatientSleep.findAll", query = "SELECT p FROM PatientSleep p"),
    @NamedQuery(name = "PatientSleep.findByClientId", query = "SELECT p FROM PatientSleep p WHERE p.clientId = :clientId"),
    @NamedQuery(name = "PatientSleep.findByClientDatabaseId", query = "SELECT p FROM PatientSleep p WHERE p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientSleep.findByClientIdClientDatabaseIdAccountId", query = "SELECT p FROM PatientSleep p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientSleep.findByClientIdClientDatabaseIdAccountIdSleepDate", query = "SELECT p FROM PatientSleep p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId  and p.sleepDate  BETWEEN :startDate AND :endDate order by p.sleepDate desc"),
    //@NamedQuery(name = "PatientSleep.findByAccountId", query = "SELECT p FROM PatientSleep p WHERE p.accountId = :accountId  and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId order by p.sleepId desc "),
    @NamedQuery(name = "PatientSleep.findByAccountId", query = "SELECT p FROM PatientSleep p WHERE p.accountId = :accountId  and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId and p.sleepId=(select max(p1.sleepId) from PatientSleep p1 where p1.accountId = :accountId and  p1.clientId = :clientId and p1.clientDatabaseId = :clientDatabaseId) "),
    @NamedQuery(name = "PatientSleep.findBySleepId", query = "SELECT p FROM PatientSleep p WHERE p.sleepId = :sleepId"),
    @NamedQuery(name = "PatientSleep.findBySourceId", query = "SELECT p FROM PatientSleep p WHERE p.sourceId = :sourceId"),
    @NamedQuery(name = "PatientSleep.findBySourceName", query = "SELECT p FROM PatientSleep p WHERE p.sourceName = :sourceName"),
    @NamedQuery(name = "PatientSleep.findBySleep", query = "SELECT p FROM PatientSleep p WHERE p.sleep = :sleep"),
    @NamedQuery(name = "PatientSleep.findBySleepDate", query = "SELECT p FROM PatientSleep p WHERE p.sleepDate = :sleepDate"),
    @NamedQuery(name = "PatientSleep.findByDateAdded", query = "SELECT p FROM PatientSleep p WHERE p.dateAdded = :dateAdded")})
public class PatientSleep implements Serializable {
    private static final long serialVersionUID = 1L;
    //@EmbeddedId
    //protected PatientSleepPK patientSleepPK;
    
   
    @Id
   	@Basic(optional = false)
   	@GeneratedValue(strategy = GenerationType.AUTO)	
    @Column(name = "sleep_id")
    private int sleepId;
    
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
    @Column(name = "sleep")
    private int sleep;
    @Basic(optional = false)
    @Column(name = "sleep_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sleepDate;
    @Basic(optional = false)
    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    public PatientSleep() {
    }

    /*public PatientSleep(PatientSleepPK patientSleepPK) {
        this.patientSleepPK = patientSleepPK;
    }

    public PatientSleep(PatientSleepPK patientSleepPK, int clientId, int clientDatabaseId, int sleep, Date sleepDate, Date dateAdded) {
        this.patientSleepPK = patientSleepPK;
        this.clientId = clientId;
        this.clientDatabaseId = clientDatabaseId;
        this.sleep = sleep;
        this.sleepDate = sleepDate;
        this.dateAdded = dateAdded;
    }

    public PatientSleep(int accountId, int sleepId) {
        this.patientSleepPK = new PatientSleepPK(accountId, sleepId);
    }

    public PatientSleepPK getPatientSleepPK() {
        return patientSleepPK;
    }

    public void setPatientSleepPK(PatientSleepPK patientSleepPK) {
        this.patientSleepPK = patientSleepPK;
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

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public Date getSleepDate() {
        return sleepDate;
    }

    public void setSleepDate(Date sleepDate) {
        this.sleepDate = sleepDate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

	public int getSleepId() {
		return sleepId;
	}

	public void setSleepId(int sleepId) {
		this.sleepId = sleepId;
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
		result = prime * result + sleepId;
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
		PatientSleep other = (PatientSleep) obj;
		if (sleepId != other.sleepId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PatientSleep [sleepId=" + sleepId + "]";
	}

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (patientSleepPK != null ? patientSleepPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientSleep)) {
            return false;
        }
        PatientSleep other = (PatientSleep) object;
        if ((this.patientSleepPK == null && other.patientSleepPK != null) || (this.patientSleepPK != null && !this.patientSleepPK.equals(other.patientSleepPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientSleep[ patientSleepPK=" + patientSleepPK + " ]";
    }*/
    
}
