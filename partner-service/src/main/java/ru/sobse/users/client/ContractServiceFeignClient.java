package ru.sobse.users.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.sobse.users.dto.ContractResponseDto;

import java.util.List;

@FeignClient(name = "contract-ws")
public interface ContractServiceFeignClient {
    @GetMapping("/contract/{partnerId}")
    List<ContractResponseDto> getContracts(@PathVariable("partnerId") Long partnerId);
}
