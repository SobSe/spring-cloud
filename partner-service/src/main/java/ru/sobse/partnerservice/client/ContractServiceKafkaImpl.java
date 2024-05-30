package ru.sobse.partnerservice.client;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ContractServiceKafkaImpl implements ContractServiceKafka {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ContractServiceKafkaImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String getContracts(Long partnerId) throws ExecutionException, InterruptedException {

        String eventId = UUID.randomUUID().toString();
        SendResult<String, Object> result = kafkaTemplate
                .send("contract-get-info-events-topic", eventId, partnerId.toString()).get();

        return eventId;
    }
}
