package com.accenture.multibank;

import org.junit.Assert;
import org.junit.Test;

import com.accenture.multibank.accounts.AbstractAccount;
import com.accenture.multibank.accounts.CreditAccount;
import com.accenture.multibank.accounts.SavingAccount;

public class AbstractAccountTest {

	@Test
	public void testBookWithSavingAccount() {
		int amount = 100;

		AbstractAccount account = new SavingAccount();
		boolean result = account.book(amount);

		Assert.assertEquals(true, result);

	}

	@Test
	public void testBookWithCreditAccount() {
		int amount = 100;

		AbstractAccount account = new CreditAccount();
		boolean result = account.book(amount);

		Assert.assertEquals(true, result);

}

}
