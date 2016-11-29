package com.accenture.multibank.bank;

import com.accenture.multibank.accounts.AccountModifiable;
import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.dao.AbstractDAO;
import com.accenture.multibank.factory.AccountFactory;
import com.accenture.multibank.generator.AccountNumberGenerator;

/**
 * @author manuel
 * @version 11/29/16
 */
public class RaiffeisenBank implements Bank {
    private AbstractDAO<Integer, AccountModifiable> accountDAO;
    private AccountNumberGenerator accountNumberGenerator;
    private AccountFactory accountFactory;

    @Override
    public boolean withdraw(AccountReadable account, int amount) {
        AccountModifiable accountModifiable = accountDAO.find(account.getAccountNumber());
        if (accountModifiable == null)
            throw new NullPointerException("Account not found");
        accountModifiable.book(-amount);
        return true;
    }

    @Override
    public boolean deposit(AccountReadable account, int amount) {
        return false;
    }

    @Override
    public boolean transfer(AccountReadable from, AccountReadable to, int amount) {
        return false;
    }

    @Override
    public void createAccount(AccountType type, int balance) {
        accountFactory.createAccount(accountNumberGenerator, type, balance);
    }

    public void setAccountDAO(AbstractDAO<Integer, AccountModifiable> accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void setAccountNumberGenerator(AccountNumberGenerator accountNumberGenerator) {
        this.accountNumberGenerator = accountNumberGenerator;
    }

    public void setAccountFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }
}
