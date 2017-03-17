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
public class PatientHeartAgePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "account_id")
    private int accountId;
    @Basic(optional = false)
    @Column(name = "heart_age_id")
    private int heartAgeId;

    public PatientHeartAgePK() {
    }

    public PatientHeartAgePK(int accountId, int heartAgeId) {
        this.accountId = accountId;
        this.heartAgeId = heartAgeId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getHeartAgeId() {
        return heartAgeId;
    }

    public void setHeartAgeId(int heartAgeId) {
        this.heartAgeId = heartAgeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) accountId;
        hash += (int) heartAgeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientHeartAgePK)) {
            return false;
        }
        PatientHeartAgePK other = (PatientHeartAgePK) object;
        if (this.accountId != other.accountId) {
            return false;
        }
        if (this.heartAgeId != other.heartAgeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientHeartAgePK[ accountId=" + accountId + ", heartAgeId=" + heartAgeId + " ]";
    }
    
}
