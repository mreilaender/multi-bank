package com.accenture.multibank.generator;

import com.accenture.multibank.entities.AccountNumbers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountNumberGeneratorTest {

	@Autowired
	private AccountNumberGeneratorDao accountNumberGeneratorDao;

	@Test
	public void createANumber() {
		AccountNumbers accountNumbers = new AccountNumbers();
		accountNumbers = accountNumberGeneratorDao.save(accountNumbers);
		int actual = accountNumbers.getAccount_number(),
				expected = 1;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void createMultipleNumbers() {
		AccountNumbers actual = new AccountNumbers();
		int expected = 1000;
		for(int i = 0; i < expected; ++i) {
			actual = accountNumberGeneratorDao.save(new AccountNumbers());
		}
		Assert.assertEquals(expected, actual.getAccount_number());
	}
}