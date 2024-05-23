package ru.sobse.users.client;

import ru.sobse.users.dto.ContractResponseDto;

import java.util.List;

public interface ContractServiceClient {
    List<ContractResponseDto> getContracts(Long contractId);
}
