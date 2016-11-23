package com.accenture.multibank.factory;

import com.accenture.multibank.accounts.AccountModifiable;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.generator.AccountNumberGenerator;

/**
 * @author manuel
 * @version 11/23/16
 */
public interface AccountFactory {
    AccountModifiable createAccount(AccountNumberGenerator generator, AccountType accountType);
}
