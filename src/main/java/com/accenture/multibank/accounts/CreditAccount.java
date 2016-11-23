package com.accenture.multibank.accounts;

import com.accenture.multibank.exceptions.UnbalancedCreditAccountException;

public class CreditAccount extends AbstractAccount {

	public CreditAccount(int acountNumber, int balance) {
		super(acountNumber, balance);
	}

	int creditLine;

	public boolean verifyBookingCondition(int amount) {
		if (amount < 0 && Math.abs(amount) > (this.getBalance() + this.creditLine)) {
			throw new UnbalancedCreditAccountException("Your balance or credit line is too low");
		}

		else {
			return true;
		}

	}

}
