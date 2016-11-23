package com.accenture.multibank.dao;

import java.util.HashMap;
import java.util.Map;

import com.accenture.multibank.accounts.AbstractAccount;

public class AccountDaoImpl implements AbstractDAO<Integer, AbstractAccount> {

	private Map<Integer, AbstractAccount> map = new HashMap<>();

	@Override
	public void save(AbstractAccount account) {
		map.put(account.getAccountNumber(), account);
	}

	@Override
	public void find(Integer accountNumber) {
		map.get(accountNumber);
	}

	@Override
	public void update(AbstractAccount account) {
		map.put(account.getAccountNumber(), account);
	}

	@Override
	public void delete(Integer accountNumber) {
		map.remove(accountNumber);
	}
}
