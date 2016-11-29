package com.accenture.multibank.dao;

import java.util.HashMap;
import java.util.Map;

import com.accenture.multibank.accounts.AccountReadable;

public class HashMapAccountDAO implements AbstractDAO<Integer, AccountReadable> {

	private Map<Integer, AccountReadable> map = new HashMap<>();

	@Override
	public void save(AccountReadable account) {
		map.put(account.getAccountNumber(), account);
	}

	@Override
	public AccountReadable find(Integer accountNumber) {
		return map.get(accountNumber);
	}

	@Override
	public void update(AccountReadable account) {
		if (map.containsKey(account.getAccountNumber()))
			map.put(account.getAccountNumber(), account);
	}

	@Override
	public AccountReadable delete(Integer accountNumber) {
		return map.remove(accountNumber);
	}
}