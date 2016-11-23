package com.accenture.multibank.generator;

public class AccountNumberGeneratorImplMock implements AccountNumberGenerator {

	int accountNumber = 0;

	public int generateAccountNumber() {
		return ++accountNumber;

	}

}
