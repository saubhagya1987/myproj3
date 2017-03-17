package com.versawork.http.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
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



@Entity
@Table(name = "weight")
@XmlRootElement
@NamedQueries({ 
	@NamedQuery(name = "AccountWeight.findByAccountId", query = "SELECT a FROM AccountWeight a WHERE a.accountId = :accountId ORDER BY a.dateAdded DESC"),
	@NamedQuery(name = "AccountWeight.findWeightListByAccountId", query = "SELECT a FROM AccountWeight a WHERE a.accountId = :accountId ORDER BY a.dateAdded DESC"),
	//@NamedQuery(name = "AccountWeight.findByMaxWeightId", query = "SELECT a FROM AccountWeight a WHERE a.accountId = :accountId ORDER BY a.weightId DESC"),
	@NamedQuery(name = "AccountWeight.findByMaxWeightId", query = "SELECT a FROM AccountWeight a WHERE a.accountId = :accountId and a.weightId=(select max(p1.weightId) from AccountWeight p1 where p1.accountId = :accountId ) "),
	@NamedQuery(name = "AccountWeight.findWeightListByAccountIdDate", query = "SELECT a FROM AccountWeight a WHERE a.accountId = :accountId and a.date  BETWEEN :startDate AND :endDate ORDER BY a.dateAdded DESC"),	
	@NamedQuery(name = "AccountWeight.findWeightByAccountIdAndWeightId", query = "SELECT a FROM AccountWeight a WHERE a.accountId = :accountId and a.weightId=:weightId ORDER BY a.dateAdded DESC")
    })

public class AccountWeight implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weight_id")
	private Integer weightId;
	@Basic(optional = false)
	@Column(name = "account_id")
	private Integer accountId;
	@Basic(optional = false)
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Column(name = "weight")
	private String weight;
	@Column(name = "weight_units")
	private String weightUnits;	
	@Basic(optional = false)
	@Column(name = "date_added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	

	public AccountWeight()
	{
		
	}

	public Integer getWeightId() {
		return weightId;
	}

	public void setWeightId(Integer weightId) {
		this.weightId = weightId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWeightUnits() {
		return weightUnits;
	}

	public void setWeightUnits(String weightUnits) {
		this.weightUnits = weightUnits;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((weightId == null) ? 0 : weightId.hashCode());
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
		AccountWeight other = (AccountWeight) obj;
		if (weightId == null) {
			if (other.weightId != null)
				return false;
		} else if (!weightId.equals(other.weightId))
			return false;
		return true;
	}

	public AccountWeight(Integer weightId, Integer accountId, Date date,
			String weight, String weightUnits) {
		super();
		this.weightId = weightId;
		this.accountId = accountId;
		this.date = date;
		this.weight = weight;
		this.weightUnits = weightUnits;
	}

	@Override
	public String toString() {
		return "AccountWeight [weightId=" + weightId + ", accountId="
				+ accountId + ", date=" + date + ", weight=" + weight
				+ ", weightUnits=" + weightUnits + "]";
	}

}
