package com.accenture.multibank.accounts;

import java.math.BigDecimal;

import com.accenture.multibank.exceptions.UnbalancedCreditAccountException;

public class CreditAccount extends AbstractAccount {

	private float creditLine;

	public CreditAccount(int accountNumber, BigDecimal balance) {
		super(accountNumber, balance);
	}

	public boolean verifyBookingCondition(BigDecimal amount) {
		if (amount.compareTo(new BigDecimal(0)) < 0
				&& amount.abs().compareTo((this.getBalance().add(new BigDecimal(this.creditLine)))) >= 0) {
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
