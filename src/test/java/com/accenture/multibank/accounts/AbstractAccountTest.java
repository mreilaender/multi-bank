package com.accenture.multibank.accounts;

import org.junit.Assert;
import org.junit.Test;

public class AbstractAccountTest {

	@Test
	public void testBookWithSavingAccount() {
		int amount = 100;

		AbstractAccount account = new SavingAccount(1, 50);
		AccountReadable result = account.book(amount);

		Assert.assertEquals(account, result);
		Assert.assertEquals(result.getBalance(), 150);

	}

	@Test
	public void testBookWithCreditAccount() {
		int amount = 100;

		AbstractAccount account = new CreditAccount(1, 50);
		AccountReadable result = account.book(amount);

		Assert.assertEquals(account, result);
		Assert.assertEquals(result.getBalance(), 150);
}

}
