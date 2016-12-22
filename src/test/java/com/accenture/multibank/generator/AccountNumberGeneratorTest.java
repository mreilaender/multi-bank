package com.accenture.multibank.generator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application.test.properties")
public class AccountNumberGeneratorTest {

	@Autowired
	private AccountNumberGeneratorDao generator;

	@Test
	public void createANumber() {
		int accountNumber = generator.save(new Account_Numbers()).getAccount_number();
		System.out.println(accountNumber);

	}

	@Test
	public void createMultipleNumbers() {
		Set<Integer> intSet = new HashSet<>();
		for (int i = 0; i < 1000; i++) {
			intSet.add(generator.save(new Account_Numbers()).getAccount_number());

		}
		Assert.assertEquals(1000, intSet.size());
	}

}
