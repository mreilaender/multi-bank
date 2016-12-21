package com.accenture.multibank.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.bank.Bank;
import com.accenture.multibank.entities.Status;
import com.accenture.multibank.entities.Transaction;
/**
 * @author manuel
 * @version 12/20/16
 */
@RequestMapping("/atm")
@RestController
public class BankController {
    private final Bank bank;

    @Autowired
    public BankController(@Qualifier("RaiffeisenBank") Bank bank) {
        this.bank = bank;
    }

    @RequestMapping(value = "/{type}", method = POST)
	public String createAccount(@PathVariable AccountType type) {
		// TODO: int + Prefix = String
		String newAccountNr = new String();
		newAccountNr = "bank.createAccount(type, 0).getAccountNumber()";
		return newAccountNr;
    }

    @RequestMapping(method = PUT)
    public Transaction book(Transaction transaction) {
		String fromWithoutPrefix = transaction.getFrom().substring(1);
		String toWithoutPrefix = transaction.getFrom().substring(1);

		if (transaction.getTo() == null)
			bank.withdraw(Integer.parseInt(fromWithoutPrefix), Math.abs(transaction.getAmount()));
		else if (transaction.getFrom() == null)
			bank.deposit(Integer.parseInt(fromWithoutPrefix), Math.abs(transaction.getAmount()));
        else
			bank.transfer(Integer.parseInt(fromWithoutPrefix), Integer.parseInt(toWithoutPrefix),
					transaction.getAmount());
        transaction.setStatus(Status.FINISHED);
        return transaction;
    }
}
