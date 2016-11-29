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
    public boolean transfer(Integer accNrFrom, Integer accNrTo, int amount) {
        AccountModifiable from = accountDAO.find(accNrFrom),
                to = accountDAO.find(accNrTo);
        from.book(-amount);
        to.book(amount);
        return true;
    }

    @Override
    public Integer createAccount(AccountType type, int balance) {
        AccountModifiable newAccount = accountFactory.createAccount(accountNumberGenerator, type, balance);
        accountDAO.save(newAccount);
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
