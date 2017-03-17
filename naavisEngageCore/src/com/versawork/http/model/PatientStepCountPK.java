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
public class PatientStepCountPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "account_id")
    private int accountId;
    @Basic(optional = false)
    @Column(name = "step_count_id")
    private int stepCountId;

    public PatientStepCountPK() {
    }

    public PatientStepCountPK(int accountId, int stepCountId) {
        this.accountId = accountId;
        this.stepCountId = stepCountId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getStepCountId() {
        return stepCountId;
    }

    public void setStepCountId(int stepCountId) {
        this.stepCountId = stepCountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) accountId;
        hash += (int) stepCountId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientStepCountPK)) {
            return false;
        }
        PatientStepCountPK other = (PatientStepCountPK) object;
        if (this.accountId != other.accountId) {
            return false;
        }
        if (this.stepCountId != other.stepCountId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientStepCountPK[ accountId=" + accountId + ", stepCountId=" + stepCountId + " ]";
    }
    
}
