package com.accenture.multibank.dao;

import java.util.HashMap;
import java.util.Map;

import com.accenture.multibank.accounts.AccountReadable;

public class AccountDaoImpl implements AbstractDAO<Integer, AccountReadable> {

	private Map<Integer, AccountReadable> map = new HashMap<>();

	@Override
	public void save(AccountReadable account) {
		map.put(account.getAccountNumber(), account);
	}

	@Override
	public void find(Integer accountNumber) {
		map.get(accountNumber);
	}

	@Override
	public void update(AccountReadable account) {
		map.put(account.getAccountNumber(), account);
	}

	@Override
	public void delete(Integer accountNumber) {
		map.remove(accountNumber);
	}
}
