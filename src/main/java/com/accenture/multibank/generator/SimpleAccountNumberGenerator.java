package com.accenture.multibank.generator;

/**
 * @author manuel
 * @version 11/23/16
 */
public class SimpleAccountNumberGenerator implements AccountNumberGenerator {
	private Integer accountNumber = 0;

    /**
     * Constructor
     * @param start This is where the generator starts to count
     */
    public SimpleAccountNumberGenerator(Integer start) {
        this.accountNumber = start;
    }

    /**
     * Increase the accountNumber by 1 and return it
     * @return accountNumber increased by 1
     */
    public Integer generateAccountNumber() {
        return ++accountNumber;
    }
}
