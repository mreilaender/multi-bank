package com.accenture.multibank.bank;

import com.accenture.multibank.accounts.AccountModifiable;
import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.dao.AbstractDAO;
import com.accenture.multibank.exceptions.AccountNotFoundException;
import com.accenture.multibank.factory.AccountFactory;
import com.accenture.multibank.generator.AccountNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author manuel
 * @version 11/29/16
 */
@Service(value = RaiffeisenBank.QUALIFIER)
public class RaiffeisenBank implements Bank {
    public final static String QUALIFIER = "RaiffeisenBank";
    private final AbstractDAO<java.lang.Integer, AccountModifiable> accountDAO;
    private final AccountNumberGenerator accountNumberGenerator;
    private final AccountFactory accountFactory;

    @Autowired
    public RaiffeisenBank(AccountNumberGenerator accountNumberGenerator, AccountFactory accountFactory, AbstractDAO<Integer, AccountModifiable> accountDAO) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.accountFactory = accountFactory;
        this.accountDAO = accountDAO;
    }

    @Override
    public AccountReadable withdraw(Integer accNr, int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount can't be negative in withdraw");
        return book(accNr, -amount);
    }

    private AccountReadable book(Integer accNr, int amount) {
        AccountModifiable account = accountDAO.find(accNr);
        if (account == null)
            throw new AccountNotFoundException("Account could not be found");
        return account.book(amount);
    }

    @Override
    public AccountReadable deposit(Integer accNr, int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount can't be negative in deposit");
        return book(accNr, amount);
    }

    @Override
    public AccountReadable transfer(Integer accNrFrom, Integer accNrTo, int amount) {
        AccountModifiable from = accountDAO.find(accNrFrom),
                to = accountDAO.find(accNrTo);
        to.book(amount);
        return from.book(-amount);
    }

    @Override
    public AccountReadable createAccount(AccountType type, int balance) {
        AccountModifiable newAccount = accountFactory.createAccount(accountNumberGenerator, type, balance);
        return accountDAO.save(newAccount);
    }
}
