package com.accenture.multibank.accounts;

public interface AccountModifiable extends AccountReadable {

	public boolean book(int amount);

}
