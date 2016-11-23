package com.accenture.multibank.dao;

import com.accenture.multibank.accounts.AbstractAccount;

/**
 * @author manuel
 * @version 11/23/16
 */
public interface AccountDAO {
	void save(AbstractAccount account);

	AbstractAccount find(int accountNumber);
}
