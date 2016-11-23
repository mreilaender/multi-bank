package com.accenture.multibank.accounts;

public abstract class AbstractAccount implements AccountModifiable {

	private int balance;
	private int accountNumber;

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
