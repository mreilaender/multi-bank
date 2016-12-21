package com.accenture.multibank.aspect;

import com.accenture.multibank.entities.Transaction;

public interface RemoteBankFacade {

	public void booking(Transaction transaction);

}
