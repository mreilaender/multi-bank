package com.accenture.multibank.dao;

<<<<<<< HEAD
=======
import com.accenture.multibank.accounts.AccountReadable;

>>>>>>> branch 'DAO' of https://github.com/mreilaender/multi-bank.git
/**
 * @author manuel
 * @version 11/23/16
 */
public interface AccountDAO {
	void save(AbstractAccount account);

	AbstractAccount find(int accountNumber);
}
