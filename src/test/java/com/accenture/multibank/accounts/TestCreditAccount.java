package com.accenture.multibank.accounts;

import com.accenture.multibank.generator.AccountNumberGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

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

    @Test
    public void amountToBeBookedGreaterThanCreditLine() throws Exception {
        int amountToBeBooked = 200, creditLine = 100, balance = 0;
        CreditAccount creditAccount = new CreditAccount(accountNumberGenerator.generateAccountNumber(), balance);
        creditAccount.setCreditLine(creditLine);
        boolean actual = creditAccount.verifyBookingCondition(amountToBeBooked),
                expected = false;
        Assert.assertEquals(expected, actual);
    }
}
