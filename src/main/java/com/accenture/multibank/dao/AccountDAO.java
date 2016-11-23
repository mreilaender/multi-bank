package com.accenture.multibank.dao;

/**
 * @author manuel
 * @version 11/23/16
 */
public interface AccountDAO {
	void save(AbstractAccount account);

	AbstractAccount find(int accountNumber);
}
