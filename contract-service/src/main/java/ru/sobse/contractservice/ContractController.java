package ru.sobse.contractservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sobse.contractservice.dto.ContractResponseDto;
import ru.sobse.contractservice.exception.ContractsNotFoundException;
import ru.sobse.contractservice.service.ContractService;

import java.util.List;

@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @GetMapping("/status/check")
    public String status() {
        return "contract-ws status OK";
    }

    @GetMapping("/{partnerId}")
    public List<ContractResponseDto> getContracts(@PathVariable Long partnerId) {
        return contractService.getContracts(partnerId);
    }

    @ExceptionHandler(ContractsNotFoundException.class)
    public ResponseEntity<String> contractsNotFound(ContractsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
