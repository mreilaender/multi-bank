package com.accenture.multibank.accounts;

import java.util.Objects;

public abstract class AbstractAccount implements AccountModifiable {

	private int balance;
	private int accountNumber;

	public AbstractAccount(int acountNumber, int balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	abstract public boolean verifyBookingCondition(int amount);

	public int getAccountNumber() {

		return accountNumber;
	}

	public int getBalance() {

		return balance;
	}

	public boolean book(int amount) {

		if (verifyBookingCondition(amount)) {

		balance = balance + amount;

			return true;
		}

		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, balance);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		else if (o instanceof AbstractAccount || o == null)
			return false;

		AbstractAccount abstractAccount2 = (AbstractAccount)o;
		return this.accountNumber == abstractAccount2.getAccountNumber();
	}
}
