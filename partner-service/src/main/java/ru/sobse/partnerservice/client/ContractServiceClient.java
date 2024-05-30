package ru.sobse.partnerservice.client;

import ru.sobse.partnerservice.dto.ContractResponseDto;

import java.util.List;

public interface ContractServiceClient {
    List<ContractResponseDto> getContracts(Long contractId);
}
