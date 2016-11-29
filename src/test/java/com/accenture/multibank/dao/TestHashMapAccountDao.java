package com.accenture.multibank.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.accenture.multibank.accounts.AccountModifiable;
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

	AbstractDAO<Integer, AccountModifiable> accDao;
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
		AccountModifiable actual = new SavingAccount(accNr, 100);
		accDao.save(actual);

		AccountReadable expected = accDao.find(actual.getAccountNumber());
		assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public void safeNullAccount() {
		AccountModifiable actual = null;
		accDao.save(actual);
	}

	@Test()
	public void findAccountThatDoesNotExist() {
		AccountModifiable actual = accDao.find(0);
		assertEquals(null, actual);
	}

	@Test
	public void deleteAccountThatDoesNotExist() {
		AccountModifiable accountReadable = accDao.delete(0);
		assertEquals(null, accountReadable);
	}

	@Test
	public void updateAccountThatDoesNotExist() {
		AccountModifiable tmp = new SavingAccount(generator.generateAccountNumber(), 0);
		accDao.update(tmp);

		AccountModifiable actual = accDao.find(accNr);
		assertEquals(null, actual);
	}

}
