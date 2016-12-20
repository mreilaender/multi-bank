package com.accenture.multibank.controller;

import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.bank.Bank;
import com.accenture.multibank.entities.Status;
import com.accenture.multibank.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public BankController(Bank bank) {
        this.bank = bank;
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.POST)
    public Integer createAccount(@RequestParam AccountType type) {
        return bank.createAccount(type, 0);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Transaction book(Transaction transaction) {
        if (transaction.getTo() == null)
            if (transaction.getAmount() > 0)
                bank.deposit(transaction.getFrom(), transaction.getAmount());
            else
                bank.withdraw(transaction.getFrom(), transaction.getAmount());
        else
            bank.transfer(transaction.getFrom(), transaction.getTo(), transaction.getAmount());
        transaction.setStatus(Status.FINISHED);
        return transaction;
    }
}
