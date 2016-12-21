package com.accenture.multibank.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.accenture.multibank.accounts.AccountModifiable;

@Service
public class HashMapAccountDAO implements AbstractDAO<Integer, AccountModifiable> {

	private Map<Integer, AccountModifiable> map = new HashMap<>();

	@Override
	public AccountModifiable save(AccountModifiable account) {
		map.put(account.getAccountNumber(), account);
		return account;
	}

	@Override
	public AccountModifiable find(Integer accountNumber) {
		return map.get(accountNumber);
	}

	@Override
	public void update(AccountModifiable account) {
		if (map.containsKey(account.getAccountNumber()))
			map.put(account.getAccountNumber(), account);
	}

	@Override
	public AccountModifiable delete(Integer accountNumber) {
		return map.remove(accountNumber);
	}
}
