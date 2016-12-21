package com.accenture.multibank.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_numbers")
public class AccountNumbers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ACCOUNT_NUMBER;

	/**
	 * @return the ACCOUNT_NUMBER
	 */
	public int getAccount_number() {
		return ACCOUNT_NUMBER;
	}

	/**
	 * @param ACCOUNT_NUMBER the ACCOUNT_NUMBER to set
	 */
	public void setAccount_number(int account_number) {
		this.ACCOUNT_NUMBER = account_number;
	}

}
