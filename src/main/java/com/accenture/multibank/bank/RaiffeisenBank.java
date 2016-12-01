package com.accenture.multibank.bank;

import com.accenture.multibank.accounts.AccountModifiable;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.dao.AbstractDAO;
import com.accenture.multibank.entities.AccountDAO;
import com.accenture.multibank.exceptions.AccountNotFoundException;
import com.accenture.multibank.factory.AccountFactory;
import com.accenture.multibank.generator.AccountNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author manuel
 * @version 11/29/16
 */
public class RaiffeisenBank implements Bank {
    @Autowired
    private AccountDAO accountDAO2;
    private AbstractDAO<java.lang.Integer, AccountModifiable> accountDAO;
    private AccountNumberGenerator accountNumberGenerator;
    private AccountFactory accountFactory;

    @Override
    public boolean withdraw(Integer accNr, int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount can't be negative in withdraw");
        return book(accNr, -amount);
    }

    private boolean book(Integer accNr, int amount) {
        AccountModifiable account = accountDAO.find(accNr);
        if (account == null)
            throw new AccountNotFoundException("Account could not be found");
        return account.book(amount);
    }

    @Override
    public boolean deposit(Integer accNr, int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount can't be negative in deposit");
        return book(accNr, amount);
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
        if (accountDAO2 != null)
            System.out.println("not null!!!!");
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

    public void setAccountDAO2(AccountDAO accountDAO2) {
        this.accountDAO2 = accountDAO2;
    }
}
