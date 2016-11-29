package com.accenture.multibank.bank;

import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;

/**
 * @author manuel
 * @version 11/29/16
 */
public interface Bank {
    boolean withdraw(AccountReadable account, int amount);
    boolean deposit(AccountReadable account, int amount);
    boolean transfer(AccountReadable from, AccountReadable to, int amount);
    void createAccount(AccountType type, int balance);
}
