package com.accenture.multibank.bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.multibank.Main;
import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;

/**
 * @author manuel
 * @version 11/30/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Main.class })
@TestPropertySource(locations = "classpath:application.test.properties")
public class RaiffeisenBankTest {
    @Autowired
    @Qualifier(value = "RaiffeisenBank")
    private Bank raiffeisenBank;
	private AccountReadable accNr1, accNr2;
	private int balance;

    @Before
    public void setUp() throws Exception {
        balance = 1000;
        accNr1 = raiffeisenBank.createAccount(AccountType.SAVING, balance);
        accNr2 = raiffeisenBank.createAccount(AccountType.SAVING, balance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawWithNegativeValue() throws Exception {
		raiffeisenBank.withdraw(accNr1.getAccountNumber(), -100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositWithNegativeValue() throws Exception {
		raiffeisenBank.withdraw(accNr1.getAccountNumber(), -100);
    }

    @Test
	public void withdrawlWithPositiveValueShouldReturnAccount() {
		raiffeisenBank.withdraw(accNr1.getAccountNumber(), 100);
		Assert.assertEquals(900, accNr1.getBalance());

	}

	@Test
	public void withdrawlMaxAmountShouldReturnZeroBalance() {
		raiffeisenBank.withdraw(accNr1.getAccountNumber(), 1000);
		Assert.assertEquals(0, accNr1.getBalance());

    }

	@Test
	public void depositWithPositiveValueShouldReturnAccount() {
		raiffeisenBank.deposit(accNr1.getAccountNumber(), 100);
		Assert.assertEquals(1100, accNr1.getBalance());

	}
}
