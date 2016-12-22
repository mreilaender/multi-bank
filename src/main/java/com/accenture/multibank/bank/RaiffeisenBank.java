package com.accenture.multibank.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.multibank.accounts.AccountModifiable;
import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.accounts.CreditAccount;
import com.accenture.multibank.accounts.SavingAccount;
import com.accenture.multibank.entities.Account;
import com.accenture.multibank.entities.AccountDAO;
import com.accenture.multibank.entities.Credit_Account;
import com.accenture.multibank.entities.Saving_Account;
import com.accenture.multibank.exceptions.AccountNotFoundException;
import com.accenture.multibank.factory.AccountFactory;
import com.accenture.multibank.generator.AccountNumberGenerator;

/**
 * @author manuel
 * @version 11/29/16
 */
@Service(value = "RaiffeisenBank")
public class RaiffeisenBank implements Bank {
	private final AccountDAO accountDAO;
    private final AccountNumberGenerator accountNumberGenerator;
    private final AccountFactory accountFactory;

    @Autowired
	public RaiffeisenBank(AccountNumberGenerator accountNumberGenerator, AccountFactory accountFactory,
			AccountDAO accountDAO) {
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
		Account dbAccount = accountDAO.findOne(accNr);
		if (dbAccount == null)
            throw new AccountNotFoundException("Account could not be found");
		AccountModifiable account = selectAccountType(dbAccount);
		;
		account = (AccountModifiable) account.book(amount);
		dbAccount.setBALANCE(account.getBalance());
		accountDAO.save(dbAccount);
		return account;
    }


    @Override
    public AccountReadable deposit(Integer accNr, int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount can't be negative in deposit");
        return book(accNr, amount);
    }

    @Override
    public AccountReadable transfer(Integer accNrFrom, Integer accNrTo, int amount) {
		Account dbFrom = accountDAO.findOne(accNrFrom), dbTo = accountDAO.findOne(accNrTo);
		if (dbFrom == null || dbTo == null)
			throw new AccountNotFoundException("Account could not be found");
		AccountModifiable from = selectAccountType(dbFrom), to = selectAccountType(dbTo);
		to = (AccountModifiable) to.book(amount);
		from = (AccountModifiable) from.book(-amount);

		dbFrom.setBALANCE(from.getBalance());
		dbTo.setBALANCE(to.getBalance());
		accountDAO.save(dbFrom);
		accountDAO.save(dbTo);

		return from;
    }

    @Override
    public AccountReadable createAccount(AccountType type, int balance) {
        AccountModifiable newAccount = accountFactory.createAccount(accountNumberGenerator, type, balance);
		Account DbAccount;
		if (type == AccountType.SAVING) {
			DbAccount = new Account(newAccount.getAccountNumber(), balance, new Saving_Account());
		} else {
			DbAccount = new Account(newAccount.getAccountNumber(), balance, new Credit_Account());
		}
		accountDAO.save(DbAccount);
		return newAccount;
    }

	private AccountModifiable selectAccountType(Account DbAccount) {
		AccountModifiable account;
		if (DbAccount.getSAVING() != null) {
			account = new SavingAccount(DbAccount.getACCOUNT_NUMBER(), DbAccount.getBALANCE());
		} else {
			account = new CreditAccount(DbAccount.getACCOUNT_NUMBER(), DbAccount.getBALANCE());
			((CreditAccount) account).setCreditLine(DbAccount.getCREDIT().getCREDITLINE());
		}
		return account;
	}
}
