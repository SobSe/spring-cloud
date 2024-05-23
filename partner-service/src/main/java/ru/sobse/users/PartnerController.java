package ru.sobse.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sobse.users.client.ContractServiceClient;
import ru.sobse.users.client.ContractServiceFeignClient;
import ru.sobse.users.dto.ContractResponseDto;
import ru.sobse.users.exception.ContractsNotFoundException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerController {
    private final ContractServiceClient contractServiceClient;
    private final ContractServiceFeignClient contractServiceFeignClient;

    @GetMapping("/status/check")
    public String status() {

        return "partner-ws status OK";
    }

    @GetMapping("/{partnerId}")
    public List<ContractResponseDto> getContracts(@PathVariable Long partnerId) {
        return contractServiceFeignClient.getContracts(partnerId);
    }

    @ExceptionHandler(ContractsNotFoundException.class)
    ResponseEntity<String> handleContractsNotFoundException(ContractsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
