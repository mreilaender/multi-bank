package com.accenture.multibank.accounts;

public interface AccountModifiable extends AccountReadable {

	AccountReadable book(int amount);

}
