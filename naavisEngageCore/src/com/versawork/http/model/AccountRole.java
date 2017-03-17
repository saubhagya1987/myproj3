/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "account_role")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "AccountRole.findAll", query = "SELECT a FROM AccountRole a"),
		@NamedQuery(name = "AccountRole.findByAccountRoleId", query = "SELECT a FROM AccountRole a WHERE a.accountRoleId = :accountRoleId"),
		@NamedQuery(name = "AccountRole.findByRoleName", query = "SELECT a FROM AccountRole a WHERE a.roleName = :roleName") })
public class AccountRole implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "account_role_id")
	private Integer accountRoleId;
	@Basic(optional = false)
	@Column(name = "role_name")
	private String roleName;
	@OneToMany(mappedBy = "accountRoleId")
	private List<Account> accountList;

	public AccountRole() {
	}

	public AccountRole(Integer accountRoleId) {
		this.accountRoleId = accountRoleId;
	}

	public AccountRole(Integer accountRoleId, String roleName) {
		this.accountRoleId = accountRoleId;
		this.roleName = roleName;
	}

	public Integer getAccountRoleId() {
		return accountRoleId;
	}

	public void setAccountRoleId(Integer accountRoleId) {
		this.accountRoleId = accountRoleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (accountRoleId != null ? accountRoleId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AccountRole)) {
			return false;
		}
		AccountRole other = (AccountRole) object;
		if ((this.accountRoleId == null && other.accountRoleId != null)
				|| (this.accountRoleId != null && !this.accountRoleId.equals(other.accountRoleId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.AccountRole[ accountRoleId=" + accountRoleId + " ]";
	}

}
