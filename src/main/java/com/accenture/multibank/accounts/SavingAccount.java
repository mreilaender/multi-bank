package com.accenture.multibank.accounts;

import com.accenture.multibank.exceptions.UnbalancedSavingAccountException;

public class SavingAccount {

	int balance;

	public void verifyBookingCondition(int amount) throws UnbalancedSavingAccountException {
		if(amount < 0 && Math.abs(amount) > this.balance)
		{
			throw new UnbalancedSavingAccountException("Your balance is too low");
		}
		
	}

}
