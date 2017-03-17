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
@Table(name = "patient_calorie_count")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatientCalorieCount.findAll", query = "SELECT p FROM PatientCalorieCount p"),
    @NamedQuery(name = "PatientCalorieCount.findByClientId", query = "SELECT p FROM PatientCalorieCount p WHERE p.clientId = :clientId"),
    @NamedQuery(name = "PatientCalorieCount.findByClientDatabaseId", query = "SELECT p FROM PatientCalorieCount p WHERE p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientCalorieCount.findByClientIdClientDatabaseIdAccountId", query = "SELECT p FROM PatientCalorieCount p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId"),
    @NamedQuery(name = "PatientCalorieCount.findByClientIdClientDatabaseIdAccountIdCalorieCountDate", query = "SELECT p FROM PatientCalorieCount p WHERE  p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId  and p.calorieCountDate  BETWEEN :startDate AND :endDate order by p.calorieCountDate desc"),
    //@NamedQuery(name = "PatientCalorieCount.findByAccountId", query = "SELECT p FROM PatientCalorieCount p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId order by p.calorieCountId desc"),
    @NamedQuery(name = "PatientCalorieCount.findByAccountId", query = "SELECT p FROM PatientCalorieCount p WHERE p.accountId = :accountId and  p.clientId = :clientId and p.clientDatabaseId = :clientDatabaseId and p.calorieCountId=(select max(p1.calorieCountId) from PatientCalorieCount p1 where p1.accountId = :accountId and  p1.clientId = :clientId and p1.clientDatabaseId = :clientDatabaseId) "),
    @NamedQuery(name = "PatientCalorieCount.findByCalorieCountId", query = "SELECT p FROM PatientCalorieCount p WHERE p.calorieCountId = :calorieCountId"),
    @NamedQuery(name = "PatientCalorieCount.findBySourceId", query = "SELECT p FROM PatientCalorieCount p WHERE p.sourceId = :sourceId"),
    @NamedQuery(name = "PatientCalorieCount.findBySourceName", query = "SELECT p FROM PatientCalorieCount p WHERE p.sourceName = :sourceName"),
    @NamedQuery(name = "PatientCalorieCount.findByCalorieCount", query = "SELECT p FROM PatientCalorieCount p WHERE p.calorieCount = :calorieCount"),
    @NamedQuery(name = "PatientCalorieCount.findByCalorieCountDate", query = "SELECT p FROM PatientCalorieCount p WHERE p.calorieCountDate = :calorieCountDate"),
    @NamedQuery(name = "PatientCalorieCount.findByDateAdded", query = "SELECT p FROM PatientCalorieCount p WHERE p.dateAdded = :dateAdded")})
public class PatientCalorieCount implements Serializable {
    private static final long serialVersionUID = 1L;
   // @EmbeddedId
    //protected PatientCalorieCountPK patientCalorieCountPK;
    
   
    @Id
   	@Basic(optional = false)
   	@GeneratedValue(strategy = GenerationType.AUTO)	
    @Column(name = "calorie_count_id")
    private int calorieCountId;
    
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
    @Column(name = "calorie_count")
    private int calorieCount;
    @Basic(optional = false)
    @Column(name = "calorie_count_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calorieCountDate;
    @Basic(optional = false)
    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    public PatientCalorieCount() {
    }

    /*public PatientCalorieCount(PatientCalorieCountPK patientCalorieCountPK) {
        this.patientCalorieCountPK = patientCalorieCountPK;
    }

    public PatientCalorieCount(PatientCalorieCountPK patientCalorieCountPK, int clientId, int clientDatabaseId, int calorieCount, Date calorieCountDate, Date dateAdded) {
        this.patientCalorieCountPK = patientCalorieCountPK;
        this.clientId = clientId;
        this.clientDatabaseId = clientDatabaseId;
        this.calorieCount = calorieCount;
        this.calorieCountDate = calorieCountDate;
        this.dateAdded = dateAdded;
    }

    public PatientCalorieCount(int accountId, int calorieCountId) {
        this.patientCalorieCountPK = new PatientCalorieCountPK(accountId, calorieCountId);
    }

    public PatientCalorieCountPK getPatientCalorieCountPK() {
        return patientCalorieCountPK;
    }

    public void setPatientCalorieCountPK(PatientCalorieCountPK patientCalorieCountPK) {
        this.patientCalorieCountPK = patientCalorieCountPK;
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

    public int getCalorieCount() {
        return calorieCount;
    }

    public void setCalorieCount(int calorieCount) {
        this.calorieCount = calorieCount;
    }

    public Date getCalorieCountDate() {
        return calorieCountDate;
    }

    public void setCalorieCountDate(Date calorieCountDate) {
        this.calorieCountDate = calorieCountDate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

	public int getCalorieCountId() {
		return calorieCountId;
	}

	public void setCalorieCountId(int calorieCountId) {
		this.calorieCountId = calorieCountId;
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
		result = prime * result + calorieCountId;
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
		PatientCalorieCount other = (PatientCalorieCount) obj;
		if (calorieCountId != other.calorieCountId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PatientCalorieCount [calorieCountId=" + calorieCountId + "]";
	}

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (patientCalorieCountPK != null ? patientCalorieCountPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientCalorieCount)) {
            return false;
        }
        PatientCalorieCount other = (PatientCalorieCount) object;
        if ((this.patientCalorieCountPK == null && other.patientCalorieCountPK != null) || (this.patientCalorieCountPK != null && !this.patientCalorieCountPK.equals(other.patientCalorieCountPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientCalorieCount[ patientCalorieCountPK=" + patientCalorieCountPK + " ]";
    }*/
    
}
