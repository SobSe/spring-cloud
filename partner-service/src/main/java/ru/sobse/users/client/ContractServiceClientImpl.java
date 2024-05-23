package ru.sobse.users.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import ru.sobse.users.dto.ContractResponseDto;

import java.util.List;

@RequiredArgsConstructor
public class ContractServiceClientImpl implements ContractServiceClient {
    private final RestClient restClient;

    @Override
    public List<ContractResponseDto> getContracts(Long contractId) {
        return restClient
                .get()
                .uri("/contract/" + contractId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
