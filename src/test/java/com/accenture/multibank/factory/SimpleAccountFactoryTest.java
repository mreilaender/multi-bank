package com.accenture.multibank.factory;

import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.generator.AccountNumberGenerator;
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
public class SimpleAccountFactoryTest {

    private AccountFactory accountFactory;

    @Mock
    private AccountNumberGenerator accountNumberGenerator;

    private int accNr;

    @Before
    public void setUp() throws Exception {
        accountFactory = new SimpleAccountFactory();
        accNr = 0;
        when(accountNumberGenerator.generateAccountNumber()).thenReturn(++accNr);
    }

    @Test(expected = NullPointerException.class)
    public void createAccountWithNullGenerator() throws Exception {
        accountFactory.createAccount(null, AccountType.CREDIT, 0);
    }

    @Test(expected = NullPointerException.class)
    public void createAccountWithNullAccountType() throws Exception {
        accountFactory.createAccount(accountNumberGenerator, null, 0);
    }

}
