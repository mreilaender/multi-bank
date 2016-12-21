package com.accenture.multibank.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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
@EnableAspectJAutoProxy
@RestController
public class BankController {
    private final Bank bank;

    @Autowired
    public BankController(@Qualifier("RaiffeisenBank") Bank bank) {
        this.bank = bank;
    }

    @RequestMapping(value = "/{type}", method = POST)
    public Integer createAccount(@PathVariable AccountType type) {
        return bank.createAccount(type, 0);
    }

    @RequestMapping(method = PUT)
    public Transaction book(Transaction transaction) {
		String fromWithoutPrefix = transaction.getFrom().substring(1);
		String toWithoutPrefix = transaction.getFrom().substring(1);

		if (transaction.getTo() == null)
            if (transaction.getAmount() > 0)
				bank.deposit(Integer.parseInt(fromWithoutPrefix), transaction.getAmount());
            else
				bank.withdraw(Integer.parseInt(fromWithoutPrefix), transaction.getAmount());
        else
			bank.transfer(Integer.parseInt(fromWithoutPrefix), Integer.parseInt(toWithoutPrefix),
					transaction.getAmount());
        transaction.setStatus(Status.FINISHED);
        return transaction;
    }
}
