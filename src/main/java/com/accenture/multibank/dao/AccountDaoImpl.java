package com.accenture.multibank.dao;

import java.util.HashMap;
import java.util.Map;

public class AccountDaoImpl implements AccountDAO {

	Map<Integer, AbstractAccount> map = new HashMap<>();


	public void save(AbstractAccount account) {
		map.put(account.getAccountNumber(), account);
	}

	public AbstractAccount find(int accountNumber) {
		return map.get(accountNumber);

	}

}
