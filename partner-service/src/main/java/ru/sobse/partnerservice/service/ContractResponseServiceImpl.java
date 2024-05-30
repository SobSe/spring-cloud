package ru.sobse.partnerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sobse.partnerservice.dto.ContractResponseDto;
import ru.sobse.partnerservice.mapper.ContractResponseMapper;
import ru.sobse.partnerservice.repository.ContractResponseRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractResponseServiceImpl implements ContractResponseService {

    private final ContractResponseRepository repository;
    private final ContractResponseMapper mapper;

    @Override
    public List<ContractResponseDto> getCompleteContracts(UUID uuid) {
        return repository.findAllByUuid(uuid).stream()
                .map((mapper::toDto))
                .toList();
    }
}
