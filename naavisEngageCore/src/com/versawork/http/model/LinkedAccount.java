package com.versawork.http.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "linked_account")
@NamedQuery(name = "LinkedAccount.secondaryAccountId", query = "SELECT l FROM LinkedAccount l where secondaryAccountId =: secondaryAccountId")
// })
public class LinkedAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	public LinkedAccount() {
	}

	public LinkedAccount(Account primaryAccountId, Integer secondaryAccountId) {
		this.primaryAccountId = primaryAccountId;
		this.secondaryAccountId = secondaryAccountId;
	}

	@Id
	@Column(name = "primary_account_id")
	private Account primaryAccountId;

	@Column(name = "secondary_account_id")
	private Integer secondaryAccountId;

	public Account getPrimaryAccountId() {
		return primaryAccountId;
	}

	public void setPrimaryAccountId(Account primaryAccountId) {
		this.primaryAccountId = primaryAccountId;
	}

	public Integer getSecondaryAccountId() {
		return secondaryAccountId;
	}

	public void setSecondaryAccountId(Integer secondaryAccountId) {
		this.secondaryAccountId = secondaryAccountId;
	}
}
