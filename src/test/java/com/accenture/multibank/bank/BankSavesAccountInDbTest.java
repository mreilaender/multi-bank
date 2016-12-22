package com.accenture.multibank.bank;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.multibank.Main;
import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Main.class })
public class BankSavesAccountInDbTest {

	@Autowired
	@Qualifier(value = "RaiffeisenBank")
	private Bank raiffeisenBank;
	private AccountReadable accNr1, accNr2;
	private int balance;

	@Before
	public void setUp() throws Exception {
		balance = 1000;
		accNr1 = raiffeisenBank.createAccount(AccountType.SAVING, balance);
		accNr2 = raiffeisenBank.createAccount(AccountType.CREDIT, balance);
	}

	@Test
	public void testDepositWithAccountFromDatabase() {
		int amount = 100;
		raiffeisenBank.deposit(accNr1.getAccountNumber(), amount);

	}

}
