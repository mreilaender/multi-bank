package com.accenture.multibank.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "saving_account")
public class Saving_Account implements Serializable {
	private Account account;

	@Id
	@OneToOne
	@JoinColumn(name = "id_acc_fk")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
