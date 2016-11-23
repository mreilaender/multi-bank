package com.accenture.multibank.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestHashMapAccountDao {

	Map map = new HashMap();
	AbstractAccount acc = new AbstractAccount();

	// zu testen: AccountImpl
	AccountDaoImpl accDao = new AccountDaoImpl();


	@Test
	public void testSafeAndFindAccount() {
		
		accDao.save(acc);
		
		accDao.find(acc.getAccountNumber());
		
		assertEquals(2, acc.getAccountNumber());
		assertEquals(100, acc.getBalance());
		

	}


}
