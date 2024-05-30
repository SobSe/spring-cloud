package ru.sobse.partnerservice.service;

import ru.sobse.partnerservice.dto.ContractResponseDto;

import java.util.List;
import java.util.UUID;

public interface ContractResponseService {

    List<ContractResponseDto> getCompleteContracts(UUID uuid);

}
