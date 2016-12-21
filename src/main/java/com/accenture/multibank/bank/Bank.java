package com.accenture.multibank.bank;

import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;
import org.springframework.stereotype.Service;

/**
 * @author manuel
 * @version 11/29/16
 */
@Service
public interface Bank {
    // TODO: Generic bank because the account number may not be represented by an integer
    AccountReadable withdraw(Integer accountNumber, int amount);
    AccountReadable deposit(Integer accountNumber, int amount);
    AccountReadable transfer(Integer fromAccountNumber, Integer toAccountNumber, int amount);
    AccountReadable createAccount(AccountType type, int balance);
}
