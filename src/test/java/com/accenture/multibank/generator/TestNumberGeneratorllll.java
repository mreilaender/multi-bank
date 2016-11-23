package com.accenture.multibank.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestNumberGeneratorllll {

	AccountNumberGenerator generator;

	@Before
	public void setup() {


	}

	@Test
	public void callGeneratorAndGetAcoountNumberSimple2() {
		generator = new AccountNumberGeneratorImplMock();
		int accNum = generator.generateAccountNumber();
		accNum = generator.generateAccountNumber();
		Assert.assertEquals(2, accNum);
	}

	@Test
	public void callGeneratorAndGetAcoountNumberSimple1() {
		generator = new AccountNumberGeneratorImplMock();
		int accNum = generator.generateAccountNumber();

		Assert.assertEquals(1, accNum);
	}
}
