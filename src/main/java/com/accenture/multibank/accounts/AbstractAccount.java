package com.accenture.multibank.accounts;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class AbstractAccount implements AccountModifiable {

	private BigDecimal balance;
	private int accountNumber;

	public AbstractAccount(int accountNumber, BigDecimal balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	abstract public boolean verifyBookingCondition(BigDecimal amount);

	public int getAccountNumber() {

		return accountNumber;
	}

	public BigDecimal getBalance() {

		return balance;
	}

	public AccountReadable book(BigDecimal amount) {

		if (verifyBookingCondition(amount)) {

			balance = balance.add(amount);

		}

		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, balance);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		else if (o instanceof AbstractAccount || o == null)
			return false;

		AbstractAccount abstractAccount2 = (AbstractAccount)o;
		return this.accountNumber == abstractAccount2.getAccountNumber();
	}
}
