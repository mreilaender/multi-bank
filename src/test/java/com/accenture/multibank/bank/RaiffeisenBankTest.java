package com.accenture.multibank.bank;

import com.accenture.multibank.accounts.AccountType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author manuel
 * @version 11/30/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RaiffeisenBankTest {
    @Autowired
    @Qualifier(value = "RaiffeisenBank")
    private Bank raiffeisenBank;
    private int accNr1, accNr2, balance;

    @Before
    public void setUp() throws Exception {
        balance = 1000;
        accNr1 = raiffeisenBank.createAccount(AccountType.SAVING, balance);
        accNr2 = raiffeisenBank.createAccount(AccountType.SAVING, balance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawWithNegativeValue() throws Exception {
        raiffeisenBank.withdraw(accNr1, -100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositWithNegativeValue() throws Exception {
        raiffeisenBank.withdraw(accNr1, -100);
    }

    @Test
    public void name() throws Exception {

    }
}
