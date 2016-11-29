package com.accenture.multibank.exceptions;

/**
 * @author manuel
 * @version 11/29/16
 */
public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String s) {
        super(s);
    }
}
