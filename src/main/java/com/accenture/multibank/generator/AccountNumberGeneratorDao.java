package com.accenture.multibank.generator;

import com.accenture.multibank.entities.AccountNumbers;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface AccountNumberGeneratorDao extends CrudRepository<AccountNumbers, Integer> {

}
