package com.accenture.multibank.accounts;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.accenture.multibank.exceptions.UnbalancedCreditAccountException;
import com.accenture.multibank.generator.AccountNumberGenerator;

/**
 * @author manuel
 * @version 11/29/16
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCreditAccount {

    @Mock
    private AccountNumberGenerator accountNumberGenerator;

    private int accNr;

    @Before
    public void setUp() throws Exception {
        when(accountNumberGenerator.generateAccountNumber()).thenReturn(++accNr);
    }

    @Test(expected = UnbalancedCreditAccountException.class)
    public void amountToBeBookedGreaterThanCreditLine() throws Exception {
        int amountToBeBooked = -200, creditLine = 100, balance = 0;
        CreditAccount creditAccount = new CreditAccount(accountNumberGenerator.generateAccountNumber(), balance);
            creditAccount.setCreditLine(creditLine);
        creditAccount.verifyBookingCondition(amountToBeBooked);
    }

    @Test
    public void amountDoBeBookedLessThanCreditLine() throws Exception {
        int amountToBeBooked = 200, creditLine = 300, balance = 0;
        CreditAccount creditAccount = new CreditAccount(accountNumberGenerator.generateAccountNumber(), balance);
        creditAccount.setCreditLine(creditLine);
		AccountReadable actual = creditAccount.book(amountToBeBooked);

		Assert.assertEquals(creditAccount, actual);
		Assert.assertEquals(200, actual.getBalance());
    }
}
