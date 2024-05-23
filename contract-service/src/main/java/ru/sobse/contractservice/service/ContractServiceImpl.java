package ru.sobse.contractservice.service;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sobse.contractservice.dto.ContractResponseDto;
import ru.sobse.contractservice.exception.ContractsNotFoundException;
import ru.sobse.contractservice.repository.ContractRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository repository;

    @Override
    public List<ContractResponseDto> getContracts(Long partnerId) {
        List<ContractResponseDto> contracts = repository.findByPartnerId(partnerId)
                .stream()
                .map(c -> new ContractResponseDto(c.getId(), c.getName()))
                .toList();
        if (contracts.isEmpty()) {
            throw new ContractsNotFoundException("Contract not found");
        }
        return contracts;
    }
}
