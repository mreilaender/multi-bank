package com.accenture.multibank.accounts;

import com.accenture.multibank.exceptions.UnbalancedSavingAccountException;

public class SavingAccount extends AbstractAccount {


	public boolean verifyBookingCondition(int amount) throws UnbalancedSavingAccountException {
		if (amount < 0 && Math.abs(amount) > this.getBalance())
		{
			throw new UnbalancedSavingAccountException("Your balance is too low");
		}
		
		else {
			return true;
		}
	}

}
