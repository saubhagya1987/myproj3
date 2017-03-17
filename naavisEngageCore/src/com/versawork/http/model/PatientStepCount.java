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
@Table(name = "patient_step_count")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatientStepCount.findAll", query = "SELECT p FROM PatientStepCount p"),
    @NamedQuery(name = "PatientStepCount.findByClientId", query = "SELECT p FROM PatientStepCount p WHERE p.clientId = :clientId"),
    @NamedQuery(name = "PatientStepCount.findByClientDatabaseId", query = "SELECT p FROM PatientStepCount p WHERE p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientStepCount.findByClientIdClientDatabaseIdAccountId", query = "SELECT p FROM PatientStepCount p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientStepCount.findByClientIdClientDatabaseIdAccountIdStepCountDate", query = "SELECT p FROM PatientStepCount p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId  and p.stepCountDate  BETWEEN :startDate and :endDate order by p.stepCountDate desc"),
    //@NamedQuery(name = "PatientStepCount.findByAccountId", query = "SELECT p FROM PatientStepCount p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId order by p.stepCountId desc"),
    @NamedQuery(name = "PatientStepCount.findByAccountId", query = "SELECT p FROM PatientStepCount p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId and p.stepCountId=(select max(p1.stepCountId) from PatientStepCount p1 where p1.accountId = :accountId and  p1.clientId = :clientId and p1.clientDatabaseId = :clientDatabaseId) "),
    @NamedQuery(name = "PatientStepCount.findByStepCountId", query = "SELECT p FROM PatientStepCount p WHERE p.stepCountId = :stepCountId"),
    @NamedQuery(name = "PatientStepCount.findBySourceId", query = "SELECT p FROM PatientStepCount p WHERE p.sourceId = :sourceId"),
    @NamedQuery(name = "PatientStepCount.findBySourceName", query = "SELECT p FROM PatientStepCount p WHERE p.sourceName = :sourceName"),
    @NamedQuery(name = "PatientStepCount.findByStepCount", query = "SELECT p FROM PatientStepCount p WHERE p.stepCount = :stepCount"),
    @NamedQuery(name = "PatientStepCount.findByStepCountDate", query = "SELECT p FROM PatientStepCount p WHERE p.stepCountDate = :stepCountDate"),
    @NamedQuery(name = "PatientStepCount.findByDateAdded", query = "SELECT p FROM PatientStepCount p WHERE p.dateAdded = :dateAdded")})
public class PatientStepCount implements Serializable {
    private static final long serialVersionUID = 1L;
    //@EmbeddedId
    //protected PatientStepCountPK patientStepCountPK;
    
   
    @Id
   	@Basic(optional = false)
   	@GeneratedValue(strategy = GenerationType.AUTO)	
    @Column(name = "step_count_id")
    private int stepCountId;
    
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
    @Column(name = "step_count")
    private int stepCount;
    @Basic(optional = false)
    @Column(name = "step_count_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stepCountDate;
    @Basic(optional = false)
    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    public PatientStepCount() {
    }

    /*public PatientStepCount(PatientStepCountPK patientStepCountPK) {
        this.patientStepCountPK = patientStepCountPK;
    }

    public PatientStepCount(PatientStepCountPK patientStepCountPK, int clientId, int clientDatabaseId, int stepCount, Date stepCountDate, Date dateAdded) {
        this.patientStepCountPK = patientStepCountPK;
        this.clientId = clientId;
        this.clientDatabaseId = clientDatabaseId;
        this.stepCount = stepCount;
        this.stepCountDate = stepCountDate;
        this.dateAdded = dateAdded;
    }

    public PatientStepCount(int accountId, int stepCountId) {
        this.patientStepCountPK = new PatientStepCountPK(accountId, stepCountId);
    }

    public PatientStepCountPK getPatientStepCountPK() {
        return patientStepCountPK;
    }

    public void setPatientStepCountPK(PatientStepCountPK patientStepCountPK) {
        this.patientStepCountPK = patientStepCountPK;
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

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public Date getStepCountDate() {
        return stepCountDate;
    }

    public void setStepCountDate(Date stepCountDate) {
        this.stepCountDate = stepCountDate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

	public int getStepCountId() {
		return stepCountId;
	}

	public void setStepCountId(int stepCountId) {
		this.stepCountId = stepCountId;
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
		result = prime * result + stepCountId;
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
		PatientStepCount other = (PatientStepCount) obj;
		if (stepCountId != other.stepCountId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PatientStepCount [stepCountId=" + stepCountId + "]";
	}

   /* @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientStepCountPK != null ? patientStepCountPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientStepCount)) {
            return false;
        }
        PatientStepCount other = (PatientStepCount) object;
        if ((this.patientStepCountPK == null && other.patientStepCountPK != null) || (this.patientStepCountPK != null && !this.patientStepCountPK.equals(other.patientStepCountPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientStepCount[ patientStepCountPK=" + patientStepCountPK + " ]";
    }*/
    
}
