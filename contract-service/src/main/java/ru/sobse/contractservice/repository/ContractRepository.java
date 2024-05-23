package ru.sobse.contractservice.repository;


import org.springframework.data.repository.CrudRepository;
import ru.sobse.contractservice.entity.Contract;

import java.util.List;

public interface ContractRepository extends CrudRepository<Contract, Long> {
    List<Contract> findByPartnerId(Long ownerId);
}
