package com.accenture.multibank.entities;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * @author manuel
 * @version 11/30/16
 */
@Transactional
public interface AccountDAO extends CrudRepository<Account, Integer> {
}
