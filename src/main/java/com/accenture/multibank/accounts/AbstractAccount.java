package com.accenture.multibank.accounts;

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

}
