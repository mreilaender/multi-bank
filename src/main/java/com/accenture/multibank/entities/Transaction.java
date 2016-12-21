package com.accenture.multibank.entities;

/**
 * @author manuel
 * @version 12/20/16
 */
public class Transaction {

	private String from;
	private String to;
    private int amount;
    private Status status;

    public Transaction() {
    }

	public Transaction(String from, String to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.status = Status.IN_PROCESS;
    }

	public String getFrom() {
        return from;
    }

	public String getTo() {
        return to;
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

