package com.accenture.multibank.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author manuel
 * @version 11/30/16
 */
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int accountNumber;

    @NotNull
    private int balance;

    public Account() {
    }

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
