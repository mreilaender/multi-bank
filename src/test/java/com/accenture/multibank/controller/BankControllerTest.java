package com.accenture.multibank.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.accounts.SavingAccount;
import com.accenture.multibank.bank.Bank;
import com.accenture.multibank.entities.Transaction;

//@SpringBootTest(classes = { Main.class })
//@RunWith(SpringJUnit4ClassRunner.class)
public class BankControllerTest {

	// @MockBean
	// AbstractDAO<Integer, AccountModifiable> accountDAO;

	// @MockBean
	// Bank bank;

	BankController bankController;
	Bank bank;

	@Before
	public void test() {
		bank = mock(Bank.class);
		bankController = new BankController(bank);
	}




	@Test
	public void testcreateAccount() throws Exception {
		String actuell = bankController.createAccount(AccountType.SAVING);
		Assert.assertNotNull(actuell);
	}

	@Test
	public void testbookWithdraw() throws Exception {
		int accountNumber = 1234;
		int outBalance = 100;
		int amount = 100;

		String externeAccNr = "Y" + accountNumber;
		Transaction transaction = new Transaction(externeAccNr, null, amount);
		AccountReadable account = new SavingAccount(accountNumber, outBalance);

		when(bank.withdraw(accountNumber, amount)).thenReturn(account);

		bankController.book(transaction);

		verify(bank).withdraw(accountNumber, amount);
	}


}
