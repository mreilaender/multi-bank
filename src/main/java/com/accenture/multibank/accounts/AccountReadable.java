package com.accenture.multibank.accounts;

import java.math.BigDecimal;

/**
 * @author manuel
 * @version 11/29/16
 */
public interface AccountReadable {
        int getAccountNumber();

	BigDecimal getBalance();
}
