package com.accenture.multibank.generator;

/**
 * @author manuel
 * @version 11/23/16
 */
public class SimpleAccountNumberGenerator implements AccountNumberGenerator {
    private int accountNumber;

    public SimpleAccountNumberGenerator(int start) {
        this.accountNumber = start;
    }

    /**
     * Increases the accountNumber by 1 and returns it
     * @return accountNumber increased by 1
     */
    public int generateAccountNumber() {
        return ++accountNumber;
    }
}
