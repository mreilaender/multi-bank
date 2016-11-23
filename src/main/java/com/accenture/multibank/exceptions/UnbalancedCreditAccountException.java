package com.accenture.multibank.exceptions;

public class UnbalancedCreditAccountException extends RuntimeException {

	public UnbalancedCreditAccountException(String errorMsg) {
		super(errorMsg);
	}

}
