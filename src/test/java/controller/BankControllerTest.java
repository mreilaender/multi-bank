package controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.bank.Bank;
import com.accenture.multibank.bank.RaiffeisenBank;
import com.accenture.multibank.controller.BankController;
import com.accenture.multibank.dao.HashMapAccountDAO;
import com.accenture.multibank.entities.Transaction;
import com.accenture.multibank.factory.SimpleAccountFactory;
import com.accenture.multibank.generator.SimpleAccountNumberGenerator;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BankControllerTest {


	Bank bank;
	@Autowired
	BankController bankController;

	@Before
	public void testBefore() {
		bank = new RaiffeisenBank(new SimpleAccountNumberGenerator(), new SimpleAccountFactory(),
				new HashMapAccountDAO());
		bankController = new BankController(bank);

	}

	@Test
	public void testcreateAccount() throws Exception {
		String actuell = bankController.createAccount(AccountType.SAVING);
		Assert.assertNotNull(actuell);
	}

	@Test
	public void testbook() throws Exception {
		int Accnr1 = bank.createAccount(AccountType.SAVING, 200);
		String extreneAccNr = "Y" + Accnr1;
		Transaction transaction = new Transaction(extreneAccNr, null, 100);

		bankController.book(transaction);
	}


}
