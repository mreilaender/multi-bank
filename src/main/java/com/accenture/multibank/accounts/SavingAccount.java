package com.accenture.multibank.accounts;

import java.math.BigDecimal;

import com.accenture.multibank.exceptions.UnbalancedSavingAccountException;

public class SavingAccount extends AbstractAccount {

	public SavingAccount(int accountNumber, BigDecimal balance) {
		super(accountNumber, balance);
	}


	public boolean verifyBookingCondition(BigDecimal amount) throws UnbalancedSavingAccountException {
		if (amount.compareTo(new BigDecimal(0)) < 0 && amount.abs().compareTo(this.getBalance()) >= 0)
		{
			throw new UnbalancedSavingAccountException("Your balance is too low");
		}
		
		else {
			return true;
		}
	}

}
