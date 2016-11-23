package com.accenture.multibank.dao;

import com.accenture.multibank.accounts.AccountReadable;

/**
 * @author manuel
 * @version 11/23/16
 */
public interface AccountDAO {
    void save(AccountReadable account);
    void find(int accountNumber);
}
