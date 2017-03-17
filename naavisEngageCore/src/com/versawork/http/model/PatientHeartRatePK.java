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
public class PatientHeartRatePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "account_id")
    private int accountId;
    @Basic(optional = false)
    @Column(name = "heart_rate_id")
    private int heartRateId;

    public PatientHeartRatePK() {
    }

    public PatientHeartRatePK(int accountId, int heartRateId) {
        this.accountId = accountId;
        this.heartRateId = heartRateId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getHeartRateId() {
        return heartRateId;
    }

    public void setHeartRateId(int heartRateId) {
        this.heartRateId = heartRateId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) accountId;
        hash += (int) heartRateId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientHeartRatePK)) {
            return false;
        }
        PatientHeartRatePK other = (PatientHeartRatePK) object;
        if (this.accountId != other.accountId) {
            return false;
        }
        if (this.heartRateId != other.heartRateId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientHeartRatePK[ accountId=" + accountId + ", heartRateId=" + heartRateId + " ]";
    }
    
}
