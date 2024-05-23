package ru.sobse.contractservice.service;

import ru.sobse.contractservice.dto.ContractResponseDto;

import java.util.List;

public interface ContractService {
    List<ContractResponseDto> getContracts(Long partnerId);
}
