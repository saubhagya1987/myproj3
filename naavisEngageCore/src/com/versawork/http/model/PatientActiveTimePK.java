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
public class PatientActiveTimePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "account_id")
    private int accountId;
    @Basic(optional = false)
    @Column(name = "active_time_id")
    private int activeTimeId;

    public PatientActiveTimePK() {
    }

    public PatientActiveTimePK(int accountId, int activeTimeId) {
        this.accountId = accountId;
        this.activeTimeId = activeTimeId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getActiveTimeId() {
        return activeTimeId;
    }

    public void setActiveTimeId(int activeTimeId) {
        this.activeTimeId = activeTimeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) accountId;
        hash += (int) activeTimeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientActiveTimePK)) {
            return false;
        }
        PatientActiveTimePK other = (PatientActiveTimePK) object;
        if (this.accountId != other.accountId) {
            return false;
        }
        if (this.activeTimeId != other.activeTimeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientActiveTimePK[ accountId=" + accountId + ", activeTimeId=" + activeTimeId + " ]";
    }
    
}
