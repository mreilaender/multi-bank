package com.accenture.multibank.entities;

/**
 * @author manuel
 * @version 12/20/16
 */
public class Transaction {

	private String fromAccount;
	private String toAccount;
	private int amount; // zweite Bank ist dies BigDecimal
    private Status status;

    public Transaction() {
    }

	public Transaction(String from, String to, int amount) {
		this.fromAccount = from;
		this.toAccount = to;
        this.amount = amount;
        this.status = Status.IN_PROCESS;
    }

	public String getFrom() {
		return fromAccount;
    }

	public String getTo() {
		return toAccount;
    }

    public int getAmount() {
        return amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

