package com.accenture.multibank.exceptions;

/**
 * @author manuel
 * @version 11/29/16
 */
public class OperationNotSupportedException extends RuntimeException {
    public OperationNotSupportedException(String s) {
        super(s);
    }

    public OperationNotSupportedException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
