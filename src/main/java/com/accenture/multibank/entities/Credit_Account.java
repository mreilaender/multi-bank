package com.accenture.multibank.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "credit_account")
public class Credit_Account implements Serializable {

	private Account account;

	private float CREDITLINE;

	@Column(name = "creditline")
	public float getCREDITLINE() {
		return CREDITLINE;
	}

	public void setCREDITLINE(float CREDITLINE) {
		this.CREDITLINE = CREDITLINE;
	}

	@Id
	@OneToOne
	@JoinColumn(name = "id_account_fk")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
