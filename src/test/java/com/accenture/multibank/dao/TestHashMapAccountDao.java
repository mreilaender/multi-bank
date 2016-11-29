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
	int accNr;

	@Mock
	AccountNumberGenerator generator;

	@Before
	public void setup() {
		accDao = new HashMapAccountDAO();
		accNr = 1;
		when(generator.generateAccountNumber()).thenReturn(++accNr);
	}

	@Test
	public void safeAndFindAccount() {
		AccountReadable actual = new SavingAccount(accNr, 100);
		accDao.save(actual);

		AccountReadable expected = accDao.find(actual.getAccountNumber());
		assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public void safeNullAccount() {
		AccountReadable actual = null;
		accDao.save(actual);
	}

	@Test()
	public void findAccountThatDoesNotExist() {
		AccountReadable actual = accDao.find(0);
		assertEquals(null, actual);
	}

	@Test
	public void deleteAccountThatDoesNotExist() {
		AccountReadable accountReadable = accDao.delete(0);
		assertEquals(null, accountReadable);
	}

	@Test
	public void updateAccountThatDoesNotExist() {
		AccountReadable tmp = new SavingAccount(generator.generateAccountNumber(), 0);
		accDao.update(tmp);

		AccountReadable actual = accDao.find(accNr);
		assertEquals(null, actual);
	}

}
