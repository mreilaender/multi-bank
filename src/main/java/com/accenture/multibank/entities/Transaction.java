package com.accenture.multibank.entities;

import javax.persistence.Entity;

/**
 * @author manuel
 * @version 12/20/16
 */
@Entity
public class Transaction {

    private Integer from;
    private Integer to;
    private int amount;
    private Status status;

    public Transaction() {
    }

    public Transaction(Integer from, Integer to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.status = Status.IN_PROCESS;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
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

