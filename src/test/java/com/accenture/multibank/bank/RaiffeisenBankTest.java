package com.accenture.multibank.bank;

import com.accenture.multibank.accounts.AccountType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author manuel
 * @version 11/30/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml", "/daos.xml", "/factories.xml", "/generators.xml"})
public class RaiffeisenBankTest {
    private Bank raiffeisen;
    private int accNr1, accNr2, balance;

    @Before
    public void setUp() throws Exception {
        balance = 1000;
        accNr1 = raiffeisen.createAccount(AccountType.SAVING, balance);
        accNr2 = raiffeisen.createAccount(AccountType.SAVING, balance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawWithNegativeValue() throws Exception {
        raiffeisen.withdraw(accNr1, -100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositWithNegativeValue() throws Exception {
        raiffeisen.withdraw(accNr1, -100);
    }

    @Test
    public void name() throws Exception {

    }

    public void setRaiffeisen(Bank raiffeisen) {
        this.raiffeisen = raiffeisen;
    }
}
