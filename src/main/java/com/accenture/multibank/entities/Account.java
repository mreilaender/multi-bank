package com.accenture.multibank.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
	private Saving_Account SAVING;
	private Credit_Account CREDIT;

    @NotNull
    private int BALANCE;

    public Account() {
    }

    public Account(String ACCOUNT_NUMBER, int BALANCE) {
        this.ACCOUNT_NUMBER = ACCOUNT_NUMBER;
        this.BALANCE = BALANCE;
    }

	public Account(String ACCOUNT_NUMBER, int BALANCE, Saving_Account SAVING) {
		this.ACCOUNT_NUMBER = ACCOUNT_NUMBER;
		this.BALANCE = BALANCE;
		this.SAVING = SAVING;
	}

	public Account(String ACCOUNT_NUMBER, int BALANCE, Credit_Account CREDIT) {
		this.ACCOUNT_NUMBER = ACCOUNT_NUMBER;
		this.BALANCE = BALANCE;
		this.CREDIT = CREDIT;
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

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
	public Saving_Account getSAVING() {
		return SAVING;
	}

	public void setSAVING(Saving_Account sAVING) {
		SAVING = sAVING;
	}

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
	public Credit_Account getCREDIT() {
		return CREDIT;
	}

	public void setCREDIT(Credit_Account cREDIT) {
		CREDIT = cREDIT;
	}

}
