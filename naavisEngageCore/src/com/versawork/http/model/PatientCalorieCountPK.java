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
public class PatientCalorieCountPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "account_id")
    private int accountId;
    @Basic(optional = false)
    @Column(name = "calorie_count_id")
    private int calorieCountId;

    public PatientCalorieCountPK() {
    }

    public PatientCalorieCountPK(int accountId, int calorieCountId) {
        this.accountId = accountId;
        this.calorieCountId = calorieCountId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCalorieCountId() {
        return calorieCountId;
    }

    public void setCalorieCountId(int calorieCountId) {
        this.calorieCountId = calorieCountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) accountId;
        hash += (int) calorieCountId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientCalorieCountPK)) {
            return false;
        }
        PatientCalorieCountPK other = (PatientCalorieCountPK) object;
        if (this.accountId != other.accountId) {
            return false;
        }
        if (this.calorieCountId != other.calorieCountId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.engage.workflow.rule.entity.PatientCalorieCountPK[ accountId=" + accountId + ", calorieCountId=" + calorieCountId + " ]";
    }
    
}
