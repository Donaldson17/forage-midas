package com.jpmc.midascore.service;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IncentiveService {
    private static final Logger logger = LoggerFactory.getLogger(IncentiveService.class);
    private final RestTemplate restTemplate;
    private static final String INCENTIVE_API_URL = "http://localhost:9090/incentive";

    public IncentiveService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Incentive getIncentive(Transaction transaction) {
        try {
            Incentive incentive = restTemplate.postForObject(INCENTIVE_API_URL, transaction, Incentive.class);
            logger.info("Received incentive: {} for transaction: {}", incentive, transaction);
            return incentive;
        } catch (Exception e) {
            logger.error("Failed to get incentive for transaction: {}", transaction, e);
            return new Incentive(0.0f);
        }
    }
}