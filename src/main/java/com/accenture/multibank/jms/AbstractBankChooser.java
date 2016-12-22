package com.accenture.multibank.jms;

/**
 * @author manuel
 * @version 12/22/16
 */
public interface AbstractBankChooser<T> {
    T sendToBank(T messageObject);
}
