package com.accenture.multibank.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.multibank.entities.Transaction;
import com.accenture.multibank.jms.AbstractBankChooser;

/**
 * @author manuel
 * @version 12/20/16
 */
@Aspect
@Component
public class ChooseBankAspect {

	BankSelector bankSelector = new BankSelector();
	@Autowired
	AbstractBankChooser<Transaction> bankChooser;

	@Around("execution(* com.accenture.multibank.controller.BankController.book(..))")
	public Object determineBank(ProceedingJoinPoint joinPoint) throws Throwable {
		// ausgabeobject aufsetzten
		Object result = null;

		// argumente von der Bookfunktion rausholen und in eine Transaction
		// umwandeln
		Object t = joinPoint.getArgs()[0];
		Transaction trans = ((Transaction) t);

		// Prefix chars für die beiden Account aufsetzten
		char prefixFrom , prefixTo;

		// Prefix von aktuellen Konten abspeichern und bei null Sonderzeichen
		// '-'
		if (trans.getFromAccountNumber() != null) {prefixFrom = trans.getFromAccountNumber().charAt(0);}
		else {prefixFrom = '-';}
		if (trans.getToAccountNumber() != null) {prefixTo = trans.getToAccountNumber().charAt(0);}
		else {prefixTo = '-';}
		
		// abfragen ob Kontos zur eigenen Bank 'Y' gehören
		if (bankSelector.isLocal(prefixFrom, prefixTo))
			result = joinPoint.proceed();
		else { // schnittstelle
			bankChooser.sendToBank(trans);
		}
		
		return result;
    }
}
