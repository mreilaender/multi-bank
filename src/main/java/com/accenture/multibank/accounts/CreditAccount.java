package com.accenture.multibank.accounts;

import com.accenture.multibank.exceptions.UnbalancedCreditAccountException;

public class CreditAccount extends AbstractAccount {

	private float creditLine;

	public CreditAccount(int accountNumber, int balance) {
		super(accountNumber, balance);
	}

	public boolean verifyBookingCondition(int amount) {
		if (amount < 0 && Math.abs(amount) > (this.getBalance() + this.creditLine)) {
			throw new UnbalancedCreditAccountException("Your balance or credit line is too low");
		}

		else {
			return true;
		}

	}

	public float getCreditLine() {
		return creditLine;
	}

	public void setCreditLine(float creditLine) {
		this.creditLine = creditLine;
	}

}
