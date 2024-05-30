package ru.sobse.partnerservice.handler;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import ru.sobse.partnerservice.dto.ContractResponseDto;
import ru.sobse.partnerservice.exception.NonRetryableException;
import ru.sobse.partnerservice.exception.RetryableException;
import ru.sobse.partnerservice.mapper.ContractResponseMapper;
import ru.sobse.partnerservice.repository.ContractResponseRepository;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContractPutInfoEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ContractResponseRepository contractResponseRepository;
    private final ContractResponseMapper contractResponseMapper;

    @KafkaListener(topics = "contract-put-info-events-topic")
    public void handle(ConsumerRecord<String, List<ContractResponseDto>> record) {
        try {
            List<ContractResponseDto> contracts = record.value();
            contractResponseRepository.saveAll(contracts.stream()
                    .map(c -> contractResponseMapper.toEntity(c,
                            UUID.fromString(record.key())))
                    .toList());
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
