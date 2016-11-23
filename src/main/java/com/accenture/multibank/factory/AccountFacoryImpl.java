package com.accenture.multibank.factory;

import com.accenture.multibank.accounts.AbstractAccount;
import com.accenture.multibank.accounts.AccountModifiable;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.accounts.CreditAccount;
import com.accenture.multibank.accounts.SavingAccount;
import com.accenture.multibank.generator.AccountNumberGenerator;

public class AccountFacoryImpl implements AccountFactory {

	public AccountModifiable createAccount(AccountNumberGenerator generator, AccountType accountType, int balance) {

		int accNum = generator.generateAccountNumber();

		AbstractAccount acc;
		
		switch (accountType) {
		case SAVING:
			acc = new SavingAccount(accNum, balance);
			break;
		case CREDIT:
			acc = new CreditAccount(accNum, balance);
			break;
			
		}
		

		return acc;
	}

}