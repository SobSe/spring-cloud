package ru.sobse.partnerservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sobse.partnerservice.client.ContractServiceClient;
import ru.sobse.partnerservice.client.ContractServiceFeignClient;
import ru.sobse.partnerservice.client.ContractServiceKafka;
import ru.sobse.partnerservice.dto.ContractResponseDto;
import ru.sobse.partnerservice.exception.ContractsNotFoundException;
import ru.sobse.partnerservice.service.ContractResponseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerController {
    private final ContractServiceClient contractServiceClient;
    private final ContractServiceFeignClient contractServiceFeignClient;
    private final ContractServiceKafka contractServiceKafka;
    private final ContractResponseService contractResponseService;

    @GetMapping("/status/check")
    public String status() {

        return "partner-ws status OK";
    }

    @GetMapping("/{partnerId}")
    public List<ContractResponseDto> getContracts(@PathVariable Long partnerId) {
        return contractServiceFeignClient.getContracts(partnerId);
    }

    @GetMapping("/kafka/{partnerID}")
    public ResponseEntity<String> getContractsKafka(@PathVariable Long partnerID) {
        String eventId;
        try {
            eventId = contractServiceKafka.getContracts(partnerID);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok().body(eventId);
    }

    @GetMapping("/kafka/complete/{uuid}")
    public List<ContractResponseDto> getContractsKafka(@PathVariable String uuid) {
        return contractResponseService.getCompleteContracts(UUID.fromString(uuid));
    }

    @ExceptionHandler(ContractsNotFoundException.class)
    ResponseEntity<String> handleContractsNotFoundException(ContractsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
