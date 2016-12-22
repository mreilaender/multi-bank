package com.accenture.multibank.controller;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.multibank.Main;
import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.SavingAccount;
import com.accenture.multibank.bank.Bank;
import com.accenture.multibank.entities.Transaction;
import com.accenture.multibank.jms.AbstractBankChooser;

@SpringBootTest(classes = { Main.class })
@RunWith(SpringJUnit4ClassRunner.class)

public class BankControllerWithAspectTest {

	@Autowired
	BankController bankController;

	@MockBean
	Bank bank;


	@MockBean
	AbstractBankChooser<Transaction> bankChooser;

	@Ignore
	@Test(expected = Exception.class)
	public void testgehtInAspect() throws Exception {
		bankController.book(null);
	}

	@Test
	public void testAspectEntscheidetRichtigBei_from_VonFremderBank() throws Exception {
		int accountNumber = 1234;
		int outBalance = 100;
		BigDecimal amount = new BigDecimal(100.0);

		String externeAccNr = "S" + accountNumber;
		Transaction transaction = new Transaction(externeAccNr, null, amount);
		// AccountReadable account = new SavingAccount(accountNumber,
		// outBalance);

		bankChooser.sendToBank(transaction);

		bankController.book(transaction);

		verify(bankChooser).sendToBank(transaction);
	}

	@Ignore
	@Test
	public void testAspectEntscheidetRichtigBei_to_VonFremderBank() throws Exception {
		int accountNumber = 1234;
		BigDecimal outBalance = new BigDecimal(100.0);
		BigDecimal amount = new BigDecimal(100.0);

		String externeAccNr = "S" + accountNumber;
		Transaction transaction = new Transaction(null, externeAccNr, amount);
		AccountReadable account = new SavingAccount(accountNumber, outBalance);

		bankChooser.sendToBank(transaction);

		bankController.book(transaction);

		verify(bankChooser).sendToBank(transaction);
		;
	}

	@Ignore
	@Test
	public void testAspectEntscheidetRichtigBei_toAndFrom_VonFremderBank() throws Exception {
		int accountNumber1 = 1234;
		int accountNumber2 = 5678;
		BigDecimal outBalance = new BigDecimal(100.0);
		BigDecimal amount = new BigDecimal(100.0);

		String externeAccNr1 = "S" + accountNumber1;
		String externeAccNr2 = "S" + accountNumber2;
		Transaction transaction = new Transaction(externeAccNr1, externeAccNr2, amount);
		AccountReadable account = new SavingAccount(accountNumber1, outBalance);

		bankChooser.sendToBank(transaction);

		bankController.book(transaction);

		verify(bankChooser).sendToBank(transaction);
	}

}
