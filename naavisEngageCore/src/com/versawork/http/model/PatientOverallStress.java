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
@Table(name = "patient_overall_stress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatientOverallStress.findAll", query = "SELECT p FROM PatientOverallStress p"),
    @NamedQuery(name = "PatientOverallStress.findByClientId", query = "SELECT p FROM PatientOverallStress p WHERE p.clientId = :clientId"),
    @NamedQuery(name = "PatientOverallStress.findByClientDatabaseId", query = "SELECT p FROM PatientOverallStress p WHERE p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientOverallStress.findByClientIdClientDatabaseIdAccountId", query = "SELECT p FROM PatientOverallStress p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientOverallStress.findByClientIdClientDatabaseIdAccountIdBetweenDates", query = "SELECT p FROM PatientOverallStress p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId and p.overallStressDate  BETWEEN :startDate AND :endDate order by p.overallStressDate desc"),
 
    //@NamedQuery(name = "PatientOverallStress.findByAccountId", query = "SELECT p FROM PatientOverallStress p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId order by p.overallStressId desc"),
    @NamedQuery(name = "PatientOverallStress.findByAccountId", query = "SELECT p FROM PatientOverallStress p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId  and p.overallStressId=(select max(p1.overallStressId) from PatientOverallStress p1 where p1.accountId = :accountId and  p1.clientId = :clientId and p1.clientDatabaseId = :clientDatabaseId)"),
    @NamedQuery(name = "PatientOverallStress.findByOverallStressId", query = "SELECT p FROM PatientOverallStress p WHERE p.overallStressId = :overallStressId"),
    @NamedQuery(name = "PatientOverallStress.findBySourceId", query = "SELECT p FROM PatientOverallStress p WHERE p.sourceId = :sourceId"),
    @NamedQuery(name = "PatientOverallStress.findBySourceName", query = "SELECT p FROM PatientOverallStress p WHERE p.sourceName = :sourceName"),
    @NamedQuery(name = "PatientOverallStress.findByOverallStress", query = "SELECT p FROM PatientOverallStress p WHERE p.overallStress = :overallStress"),
    @NamedQuery(name = "PatientOverallStress.findByOverallStressDate", query = "SELECT p FROM PatientOverallStress p WHERE p.overallStressDate = :overallStressDate"),
    @NamedQuery(name = "PatientOverallStress.findByDateAdded", query = "SELECT p FROM PatientOverallStress p WHERE p.dateAdded = :dateAdded")})
public class PatientOverallStress implements Serializable {
    private static final long serialVersionUID = 1L;
    //@EmbeddedId
    //protected PatientOverallStressPK patientOverallStressPK;
    @Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)	
    @Column(name = "overall_stress_id")
    private int overallStressId;
    
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
    @Column(name = "overall_stress")
    private int overallStress;
    @Basic(optional = false)
    @Column(name = "overall_stress_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date overallStressDate;
    @Basic(optional = false)
    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    public PatientOverallStress() {
    }

   /* public PatientOverallStress(PatientOverallStressPK patientOverallStressPK) {
        this.patientOverallStressPK = patientOverallStressPK;
    }

    public PatientOverallStress(PatientOverallStressPK patientOverallStressPK, int clientId, int clientDatabaseId, int overallStress, Date overallStressDate, Date dateAdded) {
        this.patientOverallStressPK = patientOverallStressPK;
        this.clientId = clientId;
        this.clientDatabaseId = clientDatabaseId;
        this.overallStress = overallStress;
        this.overallStressDate = overallStressDate;
        this.dateAdded = dateAdded;
    }

    public PatientOverallStress(int accountId, int overallStressId) {
        this.patientOverallStressPK = new PatientOverallStressPK(accountId, overallStressId);
    }

    public PatientOverallStressPK getPatientOverallStressPK() {
        return patientOverallStressPK;
    }

    public void setPatientOverallStressPK(PatientOverallStressPK patientOverallStressPK) {
        this.patientOverallStressPK = patientOverallStressPK;
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

    public int getOverallStress() {
        return overallStress;
    }

    public void setOverallStress(int overallStress) {
        this.overallStress = overallStress;
    }

    public Date getOverallStressDate() {
        return overallStressDate;
    }

    public void setOverallStressDate(Date overallStressDate) {
        this.overallStressDate = overallStressDate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

	public int getOverallStressId() {
		return overallStressId;
	}

	public void setOverallStressId(int overallStressId) {
		this.overallStressId = overallStressId;
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
		result = prime * result + overallStressId;
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
		PatientOverallStress other = (PatientOverallStress) obj;
		if (overallStressId != other.overallStressId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PatientOverallStress [overallStressId=" + overallStressId + "]";
	}

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (patientOverallStressPK != null ? patientOverallStressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientOverallStress)) {
            return false;
        }
        PatientOverallStress other = (PatientOverallStress) object;
        if ((this.patientOverallStressPK == null && other.patientOverallStressPK != null) || (this.patientOverallStressPK != null && !this.patientOverallStressPK.equals(other.patientOverallStressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientOverallStress[ patientOverallStressPK=" + patientOverallStressPK + " ]";
    }*/
    
}
