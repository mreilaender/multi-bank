package com.accenture.multibank;

import org.junit.Test;

import com.accenture.multibank.accounts.SavingAccount;
import com.accenture.multibank.exceptions.UnbalancedSavingAccountException;

public class AccountTest {

	@Test
	public void testBookingVerificationShouldThrowNoError() {

		SavingAccount account = new SavingAccount();
		int amount = 0;

		account.verifyBookingCondition(amount);

	}

	@Test(expected = UnbalancedSavingAccountException.class)
	public void testBookingVerificationShouldThrowError() {
		SavingAccount account = new SavingAccount();
		int amount = -120;

		account.verifyBookingCondition(amount);

	}


}
