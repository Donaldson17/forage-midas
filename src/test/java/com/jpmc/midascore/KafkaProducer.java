package com.jpmc.midascore;

import com.jpmc.midascore.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    public void send(String transactionLine) {
        kafkaProducerService.send(transactionLine);
    }
}