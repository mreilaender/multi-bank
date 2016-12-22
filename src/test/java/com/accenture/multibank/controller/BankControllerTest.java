package com.accenture.multibank.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.multibank.Main;
import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.accounts.SavingAccount;
import com.accenture.multibank.bank.Bank;
import com.accenture.multibank.entities.Transaction;
import com.accenture.multibank.jms.AbstractBankChooser;

@SpringBootTest(classes = { Main.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class BankControllerTest {

	// @MockBean
	// AbstractDAO<Integer, AccountModifiable> accountDAO;

	@MockBean
	Bank bank;

	@Autowired
	BankController bankController;

	@MockBean
	AbstractBankChooser<Transaction> bankChooser;

	@Test
	public void testcreateAccount() throws Exception {
		String actuell = bankController.createAccount(AccountType.SAVING);
		Assert.assertNotNull(actuell);
	}

	@Test
	public void testbookWithdraw() throws Exception {
		int accountNumber = 1234;
		int outBalance = 100;
		BigDecimal amount = new BigDecimal(100.0);

		String externeAccNr = "Y" + accountNumber;
		Transaction transaction = new Transaction(externeAccNr, null, amount);
		AccountReadable account = new SavingAccount(accountNumber, outBalance);

		when(bank.withdraw(accountNumber, amount)).thenReturn(account);

		bankController.book(transaction);

		verify(bank).withdraw(accountNumber, amount);
	}


}
