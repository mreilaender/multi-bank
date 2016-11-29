package com.accenture.multibank.bank;

import com.accenture.multibank.accounts.AccountModifiable;
import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.dao.AbstractDAO;
import com.accenture.multibank.exceptions.AccountNotFoundException;
import com.accenture.multibank.factory.AccountFactory;
import com.accenture.multibank.generator.AccountNumberGenerator;

/**
 * @author manuel
 * @version 11/29/16
 */
public class RaiffeisenBank implements Bank {
    private AbstractDAO<java.lang.Integer, AccountModifiable> accountDAO;
    private AccountNumberGenerator accountNumberGenerator;
    private AccountFactory accountFactory;

    @Override
    public boolean withdraw(Integer accNr, int amount) {
        AccountModifiable accountModifiable = accountDAO.find(accNr);
        if (accountModifiable == null)
            throw new AccountNotFoundException("Account could not be found");
        accountModifiable.book(-amount);
        return true;
    }

    @Override
    public boolean deposit(Integer account, int amount) {
        return false;
    }

    @Override
    public boolean transfer(Integer from, Integer to, int amount) {
        return false;
    }

    @Override
    public Integer createAccount(AccountType type, int balance) {
        AccountReadable newAccount = accountFactory.createAccount(accountNumberGenerator, type, balance);
        return newAccount.getAccountNumber();
    }

    public void setAccountDAO(AbstractDAO<java.lang.Integer, AccountModifiable> accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void setAccountNumberGenerator(AccountNumberGenerator accountNumberGenerator) {
        this.accountNumberGenerator = accountNumberGenerator;
    }

    public void setAccountFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }
}
