package com.accenture.multibank.accounts;

import java.math.BigDecimal;

public interface AccountModifiable extends AccountReadable {

	AccountReadable book(BigDecimal amount);


}
