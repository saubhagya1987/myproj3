/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "response")
@NamedQueries({
		@NamedQuery(name = "Response.findAll", query = "SELECT r FROM Response r"),
		@NamedQuery(name = "Response.findByResponseId", query = "SELECT r FROM Response r WHERE r.responseId = :responseId"),
		@NamedQuery(name = "Response.findByResponse", query = "SELECT r FROM Response r WHERE r.response = :response") })
public class Response implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "response_id")
	private Integer responseId;
	@Column(name = "response")
	private String response;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "responseId")
	private List<AccountMedicationEngage> accountMedicationEngageList;

	public Response() {
	}

	public Response(Integer responseId) {
		this.responseId = responseId;
	}

	public Integer getResponseId() {
		return responseId;
	}

	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public List<AccountMedicationEngage> getAccountMedicationEngageList() {
		return accountMedicationEngageList;
	}

	public void setAccountMedicationEngageList(List<AccountMedicationEngage> accountMedicationEngageList) {
		this.accountMedicationEngageList = accountMedicationEngageList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (responseId != null ? responseId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Response)) {
			return false;
		}
		Response other = (Response) object;
		if ((this.responseId == null && other.responseId != null)
				|| (this.responseId != null && !this.responseId.equals(other.responseId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.Response[ responseId=" + responseId + " ]";
	}

}
