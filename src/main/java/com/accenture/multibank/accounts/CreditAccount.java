package com.accenture.multibank.accounts;

import com.accenture.multibank.exceptions.UnbalancedCreditAccountException;

public class CreditAccount extends AbstractAccount {

	private int creditLine;

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

	public int getCreditLine() {
		return creditLine;
	}

	public void setCreditLine(int creditLine) {
		this.creditLine = creditLine;
	}

}
