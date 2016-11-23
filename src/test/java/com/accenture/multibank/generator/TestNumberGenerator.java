package com.accenture.multibank.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class TestNumberGenerator {

	private AccountNumberGenerator generator;

	@Before
	public void setup() {

	}

	@Test
	public void testSimpleAccountNumberGeneratorCount1() {
		int startAccNr = 0, timesGenerated = 2, accNum = startAccNr;
		generator = new SimpleAccountNumberGenerator(startAccNr);
		for (int i = timesGenerated; i > 0;--i)
			accNum = generator.generateAccountNumber();

		Assert.assertEquals(startAccNr+timesGenerated, accNum);
	}

}
