package com.accenture.multibank.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.generator.AccountNumberGenerator;
import org.junit.Before;
import org.junit.Test;

import com.accenture.multibank.accounts.SavingAccount;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestHashMapAccountDao {

	AbstractDAO<Integer, AccountReadable> accDao;

	@Mock
	AccountNumberGenerator generator;

	@Before
	public void setup() {
		accDao = new HashMapAccountDAO();
	}

	@Test
	public void testSafeAndFindAccount() {
		int accNr = 1;
		when(generator.generateAccountNumber()).thenReturn(++accNr);
		AccountReadable actual = new SavingAccount(accNr, 100);
		accDao.save(actual);

		AccountReadable expected = accDao.find(actual.getAccountNumber());

		assertEquals(expected.getAccountNumber(), actual.getAccountNumber());
	}


}
