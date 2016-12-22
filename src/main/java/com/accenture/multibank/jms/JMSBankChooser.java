package com.accenture.multibank.jms;

import com.accenture.multibank.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Service;

/**
 * @author manuel
 * @version 12/22/16
 */
@Service(value = JMSBankChooser.QUALIFIER)
public class JMSBankChooser implements AbstractBankChooser<Transaction> {
    public final static String QUALIFIER = "jmsBankChooser";
    @Autowired
    private JmsTemplate jmsTemplate;

    private final String QUEUE_NAME = "bank_s";

    @Override
    public Transaction sendToBank(Transaction messageObject) {
        jmsTemplate.convertAndSend(QUEUE_NAME, messageObject);
        return messageObject;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
            converter.setTargetType(MessageType.TEXT);
        return converter;
    }
}
