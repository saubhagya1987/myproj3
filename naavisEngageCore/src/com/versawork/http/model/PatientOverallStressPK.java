/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Sunil
 */
@Embeddable
public class PatientOverallStressPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "account_id")
    private int accountId;
    @Basic(optional = false)
    @Column(name = "overall_stress_id")
    private int overallStressId;

    public PatientOverallStressPK() {
    }

    public PatientOverallStressPK(int accountId, int overallStressId) {
        this.accountId = accountId;
        this.overallStressId = overallStressId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getOverallStressId() {
        return overallStressId;
    }

    public void setOverallStressId(int overallStressId) {
        this.overallStressId = overallStressId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) accountId;
        hash += (int) overallStressId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientOverallStressPK)) {
            return false;
        }
        PatientOverallStressPK other = (PatientOverallStressPK) object;
        if (this.accountId != other.accountId) {
            return false;
        }
        if (this.overallStressId != other.overallStressId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientOverallStressPK[ accountId=" + accountId + ", overallStressId=" + overallStressId + " ]";
    }
    
}
