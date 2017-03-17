/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.versawork.http.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Sohaib
 */
@Entity
@Table(name = "frequency")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Frequency.findAll", query = "SELECT f FROM Frequency f"),
		@NamedQuery(name = "Frequency.findByFrequencyId", query = "SELECT f FROM Frequency f WHERE f.frequencyId = :frequencyId"),
		@NamedQuery(name = "Frequency.findByFrequency", query = "SELECT f FROM Frequency f WHERE f.frequency = :frequency"),
		@NamedQuery(name = "Frequency.findByLangCode", query = "SELECT f FROM Frequency f WHERE f.langCode = :langCode"),
		@NamedQuery(name = "Frequency.findByLangCodeType", query = "SELECT f FROM Frequency f WHERE f.langCode = :langCode and f.type = :type"),
		@NamedQuery(name = "Frequency.findByInterval", query = "SELECT f FROM Frequency f WHERE f.interval = :interval") })
public class Frequency implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "frequency_id")
	private Integer frequencyId;
	@Basic(optional = false)
	@Column(name = "frequency")
	private String frequency;
	@Basic(optional = false)
	@Column(name = "lang_code")
	private String langCode;
	@Basic(optional = false)
	@Column(name = "type")
	private Integer type;
	@Column(name = "interval")
	private Integer interval;
	@JoinColumn(name = "date_part_id", referencedColumnName = "date_part_id")
	@ManyToOne
	private DatePart datePartId;

	public Frequency() {
	}

	public Frequency(Integer frequencyId) {
		this.frequencyId = frequencyId;
	}

	public Frequency(Integer frequencyId, String frequency) {
		this.frequencyId = frequencyId;
		this.frequency = frequency;
	}

	public Integer getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(Integer frequencyId) {
		this.frequencyId = frequencyId;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public DatePart getDatePartId() {
		return datePartId;
	}

	public void setDatePartId(DatePart datePartId) {
		this.datePartId = datePartId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (frequencyId != null ? frequencyId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Frequency)) {
			return false;
		}
		Frequency other = (Frequency) object;
		if ((this.frequencyId == null && other.frequencyId != null)
				|| (this.frequencyId != null && !this.frequencyId.equals(other.frequencyId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.versawork.http.model.Frequency[ frequencyId=" + frequencyId + " ]";
	}

}
