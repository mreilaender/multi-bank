package com.accenture.multibank;

import org.junit.Test;

import com.accenture.multibank.accounts.CreditAccount;
import com.accenture.multibank.accounts.SavingAccount;
import com.accenture.multibank.exceptions.UnbalancedCreditAccountException;
import com.accenture.multibank.exceptions.UnbalancedSavingAccountException;

public class AccountTest {

	@Test
	public void testBookingVerificationShouldThrowNoError() {

		SavingAccount account = new SavingAccount(1, 100);
		int amount = 0;

		account.verifyBookingCondition(amount);

	}

	@Test(expected = UnbalancedSavingAccountException.class)
	public void testBookingVerificationShouldThrowError() {
		SavingAccount account = new SavingAccount(1, 0);
		int amount = -120;

		account.verifyBookingCondition(amount);

	}

	@Test
	public void testCreditBookingVerificationShouldThrowNoError() {
		CreditAccount account = new CreditAccount(1, 0);
		int amount = 0;

		account.verifyBookingCondition(amount);

	}

	@Test(expected = UnbalancedCreditAccountException.class)
	public void testCreditBookingVerificationShouldThrowError() {
		CreditAccount account = new CreditAccount(2, 10);
		int amount = -120;

		account.verifyBookingCondition(amount);

	}


}
