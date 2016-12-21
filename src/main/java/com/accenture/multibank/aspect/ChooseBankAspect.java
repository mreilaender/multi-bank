package com.accenture.multibank.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.accenture.multibank.entities.Transaction;

/**
 * @author manuel
 * @version 12/20/16
 */
@Aspect
@Component
public class ChooseBankAspect {

	public ChooseBankAspect() {
		System.out.println("construckto Aspect");
	}

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
		// 1.Bafrage für Deposit und Withdraw
		if ((prefixFrom == 'Y' || prefixTo == 'Y') && (prefixFrom == '-' || prefixTo == '-')) {
			result = joinPoint.proceed();
			System.out.println("Test");
		}
		// 2. Abfrage für tranfer
		if (prefixFrom == 'Y' && prefixTo == 'Y') {
			result = joinPoint.proceed();
		}
		
		// abfragen ob Kontos zur anderen Bank 'S' gehören
		// 1.Bafrage für Deposit und Withdraw
		if ((prefixFrom == 'S' || prefixTo == 'S') && (prefixFrom == '-' || prefixTo == '-')) {
			result = joinPoint.proceed();
			// Schnittstelle
		}
		// 2. Abfrage für tranfer
		if (prefixFrom == 'S' && prefixTo == 'S') {
			result = joinPoint.proceed();
			// Schnittstelle
		}

		if ((prefixFrom == 'Y' && prefixTo == 'S') || (prefixFrom == 'S' && prefixTo == 'Y')) {

			result = joinPoint.proceed();
			// schnittstelle nach der eigenen Transaction
			// moegliche Aenderung der Transaction um bei eigener Bank nur
			// deposit/withdraw durch zu führen
		}

		return result;
    }
}
