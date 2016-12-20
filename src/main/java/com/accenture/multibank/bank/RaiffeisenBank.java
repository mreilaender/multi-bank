package com.accenture.multibank.bank;

import com.accenture.multibank.accounts.AccountModifiable;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.dao.AbstractDAO;
import com.accenture.multibank.entities.Account;
import com.accenture.multibank.entities.AccountDAO;
import com.accenture.multibank.exceptions.AccountNotFoundException;
import com.accenture.multibank.factory.AccountFactory;
import com.accenture.multibank.generator.AccountNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * @author manuel
 * @version 11/29/16
 */
@Service
public class RaiffeisenBank implements Bank {
    private final CrudRepository<Account, Integer> test;
    private final AccountDAO accountDAO2;
    private final AbstractDAO<java.lang.Integer, AccountModifiable> accountDAO;
    private final AccountNumberGenerator accountNumberGenerator;
    private final AccountFactory accountFactory;

    @Autowired
    public RaiffeisenBank(AccountDAO accountDAO2, AccountNumberGenerator accountNumberGenerator, AccountFactory accountFactory, AbstractDAO<Integer, AccountModifiable> accountDAO, CrudRepository<Account, Integer> test) {
        this.accountDAO2 = accountDAO2;
        this.accountNumberGenerator = accountNumberGenerator;
        this.accountFactory = accountFactory;
        this.accountDAO = accountDAO;
        this.test = test;
    }

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
        accountDAO2.equals(null);
        return newAccount.getAccountNumber();
    }
}
