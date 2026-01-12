package com.jpmc.midascore.service;

import com.jpmc.midascore.component.DatabaseConduit;
import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final DatabaseConduit databaseConduit;

    public KafkaConsumerService(DatabaseConduit databaseConduit) {
        this.databaseConduit = databaseConduit;
    }

    @KafkaListener(topics = "${general.kafka-topic}")
    public void consume(Transaction transaction) {
        logger.info("Received transaction: {}", transaction);
        boolean processed = databaseConduit.processTransaction(transaction);
        if (processed) {
            logger.info("Transaction processed successfully");
        } else {
            logger.info("Transaction discarded - validation failed");
        }
    }
}