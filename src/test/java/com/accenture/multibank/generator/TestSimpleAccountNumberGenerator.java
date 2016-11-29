package com.accenture.multibank.generator;

import org.junit.Assert;
import org.junit.Test;

public class TestSimpleAccountNumberGenerator {

	private AccountNumberGenerator generator;

	@Test
	public void generateNumber10Times() {
		int startAccNr = 0, timesGenerated = 10, accNum = startAccNr;
		generator = new SimpleAccountNumberGenerator(startAccNr);
		for (int i = timesGenerated; i > 0;--i)
			accNum = generator.generateAccountNumber();

		Assert.assertEquals(startAccNr+timesGenerated, accNum);
	}

	@Test
	public void generateNumber10TimesWithNegativeStartValue() {
		int startAccNr = -10, timesGenerated = 10, accNum = startAccNr;
		generator = new SimpleAccountNumberGenerator(startAccNr);
		for (int i = timesGenerated; i > 0;--i)
			accNum = generator.generateAccountNumber();

		Assert.assertEquals(startAccNr+timesGenerated, accNum);
	}

    @SuppressWarnings("null")
    @Test(expected = NullPointerException.class)
    public void generateNumberWithNullStartValue() {
		generator = new SimpleAccountNumberGenerator(null);
		generator.generateAccountNumber();
	}
}
