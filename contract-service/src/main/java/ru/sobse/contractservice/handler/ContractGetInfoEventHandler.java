package ru.sobse.contractservice.handler;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import ru.sobse.contractservice.dto.ContractResponseDto;
import ru.sobse.contractservice.exception.NonRetryableException;
import ru.sobse.contractservice.exception.RetryableException;
import ru.sobse.contractservice.service.ContractService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContractGetInfoEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ContractService contractService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "contract-get-info-events-topic")
    public void handle(ConsumerRecord<String, String> record) {

        try {
            Long partnerIdL = Long.parseLong(record.value());
            List<ContractResponseDto> contractResponseDto = contractService.getContracts(partnerIdL);
            SendResult<String, Object> result = kafkaTemplate
                    .send("contract-put-info-events-topic", record.key(), contractResponseDto).get();

            LOGGER.info("Received response {}", result);

        } catch (ResourceAccessException e) {
            LOGGER.error(e.getMessage());
            throw new RetryableException(e);
        } catch (HttpServerErrorException e) {
            LOGGER.error(e.getMessage());
            throw new NonRetryableException(e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new NonRetryableException(e);
        }
    }
}
