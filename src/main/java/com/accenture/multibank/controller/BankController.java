package com.accenture.multibank.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.aspect.BankSelector;
import com.accenture.multibank.bank.Bank;
import com.accenture.multibank.bank.RaiffeisenBank;
import com.accenture.multibank.entities.Status;
import com.accenture.multibank.entities.Transaction;
import com.accenture.multibank.jms.AbstractBankChooser;
import com.accenture.multibank.jms.JMSBankChooser;

/**
 * @author manuel
 * @version 12/20/16
 */
@RequestMapping("/atm")
@RestController
public class BankController {
	private final Bank bank;
	private final AbstractBankChooser<Transaction> bankChooser;
	private BankSelector bankSelector;

	@Autowired
	public BankController(@Qualifier(RaiffeisenBank.QUALIFIER) Bank bank,
			@Qualifier(JMSBankChooser.QUALIFIER) AbstractBankChooser<Transaction> bankChooser) {
		this.bank = bank;
		this.bankChooser = bankChooser;
		bankSelector = new BankSelector();
	}

	@RequestMapping(value = "/{type}", method = GET)
	public String createAccount(@PathVariable AccountType type) {
		// TODO: int + Prefix = String
		String newAccountNr = new String();
		newAccountNr = "bank.createAccount(type, 0).getAccountNumber()";
		return newAccountNr;
	}

	@RequestMapping(method = PUT)
	public Transaction book(Transaction transaction) {

		char prefixFrom, prefixTo;

		// Prefix von aktuellen Konten abspeichern und bei null Sonderzeichen
		// '-'
		if (transaction.getFromAccountNumber() != null) {
			prefixFrom = transaction.getFromAccountNumber().charAt(0);
		} else {
			prefixFrom = '-';
		}
		if (transaction.getToAccountNumber() != null) {
			prefixTo = transaction.getToAccountNumber().charAt(0);
		} else {
			prefixTo = '-';
		}

		if (bankSelector.isLocal(prefixFrom, prefixTo)) {

		BigDecimal absAmount = transaction.getAmount().abs();

		if (transaction.getToAccountNumber() == null) {
			int accountNumber = changeIntoInternalAccountNumber(transaction.getFromAccountNumber());
			bank.withdraw(accountNumber, absAmount);
		}

		else if (transaction.getFromAccountNumber() == null) {
			int accountNumber = changeIntoInternalAccountNumber(transaction.getToAccountNumber());
			AccountReadable account = bank.deposit(accountNumber, absAmount);

		} else {
			int accountNumberFrom = changeIntoInternalAccountNumber(transaction.getFromAccountNumber());
			int accountNumberTo = changeIntoInternalAccountNumber(transaction.getToAccountNumber());
			bank.transfer(accountNumberFrom, accountNumberTo, absAmount);
		}
		transaction.setStatus(Status.FINISHED);
			return transaction;
		} else {
			return bankChooser.sendToBank(transaction);
		}
	}

	public int changeIntoInternalAccountNumber(String externalAccountNumber) {
		String WithoutPrefix = externalAccountNumber.substring(1);
		int WithoutPrefixInt = Integer.parseInt(WithoutPrefix);
		return WithoutPrefixInt;
	}

	@RequestMapping(method = GET)
	public Transaction test(Transaction transaction) {
		return bankChooser.sendToBank(transaction);
	}
}
