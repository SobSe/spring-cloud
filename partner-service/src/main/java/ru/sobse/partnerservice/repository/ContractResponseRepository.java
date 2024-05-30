package ru.sobse.partnerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sobse.partnerservice.entity.ContractResponse;

import java.util.List;
import java.util.UUID;

public interface ContractResponseRepository extends JpaRepository<ContractResponse, Long> {

    List<ContractResponse> findAllByUuid(UUID uuid);

}
