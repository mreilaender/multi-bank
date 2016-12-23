package com.accenture.multibank.entities;

import java.math.BigDecimal;

/**
 * @author manuel
 * @version 12/20/16
 */
public class Transaction {

	private String fromAccountNumber;
	private String toAccountNumber;
	private BigDecimal amount;
    private Status status;

    public Transaction() {
    }

	public Transaction(String from, String to, BigDecimal amount) {
		this.fromAccountNumber = from;
		this.toAccountNumber = to;
        this.amount = amount;
        this.status = Status.IN_PROCESS;
    }

	public String getFromAccountNumber() {
		return fromAccountNumber;
    }

	public String getToAccountNumber() {
		return toAccountNumber;
    }

	public BigDecimal getAmount() {
        return amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

