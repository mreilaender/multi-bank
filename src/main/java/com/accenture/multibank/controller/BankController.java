package com.accenture.multibank.controller;

import com.accenture.multibank.accounts.AccountReadable;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.bank.Bank;
import com.accenture.multibank.bank.RaiffeisenBank;
import com.accenture.multibank.entities.Status;
import com.accenture.multibank.entities.Transaction;
import com.accenture.multibank.jms.AbstractBankChooser;
import com.accenture.multibank.jms.JMSBankChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author manuel
 * @version 12/20/16
 */
@RequestMapping("/atm")
@RestController
public class BankController {
	private final Bank bank;
	private final AbstractBankChooser<Transaction> bankChooser;

	@Autowired
	public BankController(@Qualifier(RaiffeisenBank.QUALIFIER) Bank bank,
			@Qualifier(JMSBankChooser.QUALIFIER) AbstractBankChooser<Transaction> bankChooser) {
		this.bank = bank;
		this.bankChooser = bankChooser;
	}

	@RequestMapping(value = "/{type}", method = POST)
	public ResponseEntity<AccountReadable> createAccount(@PathVariable AccountType type) {
		// TODO: int + Prefix = String
		AccountReadable accountReadable = bank.createAccount(type, new BigDecimal(0));
		return new ResponseEntity<>(accountReadable, HttpStatus.OK);
	}

	@RequestMapping(method = PUT)
	public ResponseEntity<Transaction> book(Transaction transaction) {

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
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
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
