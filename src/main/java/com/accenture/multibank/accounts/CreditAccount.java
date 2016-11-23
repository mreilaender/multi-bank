package com.accenture.multibank.accounts;

import com.accenture.multibank.exceptions.UnbalancedCreditAccountException;

public class CreditAccount {

	int creditLine;
	int balance;

	public void verifyBookingCondition(int amount) {
		if (amount < 0 && Math.abs(amount) > (this.balance + this.creditLine)) {
			throw new UnbalancedCreditAccountException("Your balance or credit line is too low");
		}

	}

}
