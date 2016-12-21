package com.accenture.multibank.aspect;

public class BankSelector {

	public boolean isLocal(char prefixTo, char prefixFrom) {
		boolean result = false;

		// abfragen ob Kontos zur eigenen Bank 'Y' gehören
		// 1.Abfrage für Deposit und Withdraw
		if ((prefixFrom == 'Y' || prefixTo == 'Y') && (prefixFrom == '-' || prefixTo == '-')) {
			result = true;

		}
		// 2. Abfrage für tranfer
		if (prefixFrom == 'Y' && prefixTo == 'Y') {
			result = true;
		}

		return result;

	}

}
