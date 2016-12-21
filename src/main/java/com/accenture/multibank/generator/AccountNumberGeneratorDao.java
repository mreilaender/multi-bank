package com.accenture.multibank.generator;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface AccountNumberGeneratorDao extends CrudRepository<Account_Numbers, Integer> {

}
