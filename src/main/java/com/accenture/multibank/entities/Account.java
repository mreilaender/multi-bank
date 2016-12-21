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
    private int ID;

    private String ACCOUNT_NUMBER;

    @NotNull
    private int BALANCE;

    public Account() {
    }

    public Account(String ACCOUNT_NUMBER, int BALANCE) {
        this.ACCOUNT_NUMBER = ACCOUNT_NUMBER;
        this.BALANCE = BALANCE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getACCOUNT_NUMBER() {
        return ACCOUNT_NUMBER;
    }

    public void setACCOUNT_NUMBER(String ACCOUNT_NUMBER) {
        this.ACCOUNT_NUMBER = ACCOUNT_NUMBER;
    }

    public int getBALANCE() {
        return BALANCE;
    }

    public void setBALANCE(int BALANCE) {
        this.BALANCE = BALANCE;
    }
}
