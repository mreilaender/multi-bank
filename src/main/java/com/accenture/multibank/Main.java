package com.accenture.multibank;

import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.bank.Bank;
import com.accenture.multibank.bank.RaiffeisenBank;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author manuel
 * @version 11/29/16
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml", "daos.xml");
        Bank bank = (Bank) context.getBean(RaiffeisenBank.class);
        Integer from = bank.createAccount(AccountType.SAVING, 1000),
                to = bank.createAccount(AccountType.SAVING, 2000);

        bank.transfer(from, to, 1001);
    }
}
