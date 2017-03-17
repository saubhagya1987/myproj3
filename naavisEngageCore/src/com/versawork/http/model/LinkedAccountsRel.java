/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Preet
 */
@Entity
@Table(name = "linkedaccounts_rel")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "LinkedAccountsRel.findAll", query = "SELECT l FROM LinkedAccountsRel l"),
		@NamedQuery(name = "LinkedAccountsRel.findById", query = "SELECT l FROM LinkedAccountsRel l WHERE l.id = :id"),
		@NamedQuery(name = "LinkedAccountsRel.findByAccountId", query = "SELECT l FROM LinkedAccountsRel l WHERE l.accountId = :accountId"),
		@NamedQuery(name = "LinkedAccountsRel.findByLinkedAccountId", query = "SELECT l FROM LinkedAccountsRel l WHERE l.linkedAccountId = :linkedAccountId"),
		@NamedQuery(name = "LinkedAccountsRel.findByAccountLinkedAccountId", query = "SELECT l FROM LinkedAccountsRel l WHERE l.accountId = :accountId AND l.linkedAccountId = :linkedAccountId"),
		@NamedQuery(name = "LinkedAccountsRel.findByLinkedFacilityName", query = "SELECT l FROM LinkedAccountsRel l WHERE l.linkedFacilityName = :linkedFacilityName"),
		@NamedQuery(name = "LinkedAccountsRel.findByLinkedAccountClientdbId", query = "SELECT l FROM LinkedAccountsRel l WHERE l.linkedAccountClientdbId = :linkedAccountClientdbId"),
		@NamedQuery(name = "LinkedAccountsRel.findByAccountLinkedClientdbId", query = "SELECT l FROM LinkedAccountsRel l WHERE l.linkedAccountClientdbId = :linkedAccountClientdbId AND l.accountId = :accountId"),
		@NamedQuery(name = "LinkedAccountsRel.findByDateAdded", query = "SELECT l FROM LinkedAccountsRel l WHERE l.dateAdded = :dateAdded") })
public class LinkedAccountsRel implements Serializable {
	@Basic(optional = true)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	@Basic(optional = false)
	@Column(name = "client_database_id")
	private int clientDatabaseId;
	@Basic(optional = false)
	@Column(name = "linked_account_clientdb_id")
	private int linkedAccountClientdbId;
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "account_id")
	private int accountId;
	@Basic(optional = false)
	@Column(name = "linked_account_id")
	private int linkedAccountId;
	@Column(name = "linked_facility_name")
	private String linkedFacilityName;
	@Column(name = "linked_facility_address")
	private String linkedFacilityAddress;

	public String getLinkedFacilityAddress() {
		return linkedFacilityAddress;
	}

	public void setLinkedFacilityAddress(String linkedFacilityAddress) {
		this.linkedFacilityAddress = linkedFacilityAddress;
	}

	public String getLinkedFacilityName() {
		return linkedFacilityName;
	}

	public void setLinkedFacilityName(String linkedFacilityName) {
		this.linkedFacilityName = linkedFacilityName;
	}

	public LinkedAccountsRel() {
	}

	public LinkedAccountsRel(Integer id) {
		this.id = id;
	}

	public LinkedAccountsRel(Integer id, int accountId, int linkedAccountId, Date dateAdded) {
		this.id = id;
		this.accountId = accountId;
		this.linkedAccountId = linkedAccountId;
		this.dateAdded = dateAdded;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getLinkedAccountId() {
		return linkedAccountId;
	}

	public void setLinkedAccountId(int linkedAccountId) {
		this.linkedAccountId = linkedAccountId;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof LinkedAccountsRel)) {
			return false;
		}
		LinkedAccountsRel other = (LinkedAccountsRel) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "javaapplication1.LinkedaccountsRel[ id=" + id + " ]";
	}

	public int getClientDatabaseId() {
		return clientDatabaseId;
	}

	public void setClientDatabaseId(int clientDatabaseId) {
		this.clientDatabaseId = clientDatabaseId;
	}

	public int getLinkedAccountClientdbId() {
		return linkedAccountClientdbId;
	}

	public void setLinkedAccountClientdbId(int linkedAccountClientdbId) {
		this.linkedAccountClientdbId = linkedAccountClientdbId;
	}

}
