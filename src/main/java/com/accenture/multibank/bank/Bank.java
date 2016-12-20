package com.accenture.multibank.bank;

import com.accenture.multibank.accounts.AccountType;
import org.springframework.stereotype.Service;

/**
 * @author manuel
 * @version 11/29/16
 */
@Service
public interface Bank {
    // TODO: Generic bank because the account number may not be represented by an integer
    boolean withdraw(Integer accountNumber, int amount);
    boolean deposit(Integer accountNumber, int amount);
    boolean transfer(Integer fromAccountNumber, Integer toAccountNumber, int amount);
    Integer createAccount(AccountType type, int balance);
}
