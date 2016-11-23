package com.accenture.multibank;

public interface AccountModifiable extends AccountReadable {

	public boolean book(int amount);

}
