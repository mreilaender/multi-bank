package com.accenture.multibank.bank;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;

/**
 * @author manuel
 * @version 11/29/16
 */
@Service
public interface Bank {
    // TODO: Generic bank because the account number may not be represented by an integer
	AccountReadable withdraw(Integer accountNumber, BigDecimal amount);

	AccountReadable deposit(Integer accountNumber, BigDecimal amount);

	AccountReadable transfer(Integer fromAccountNumber, Integer toAccountNumber, BigDecimal amount);

	AccountReadable createAccount(AccountType type, BigDecimal balance);
}
