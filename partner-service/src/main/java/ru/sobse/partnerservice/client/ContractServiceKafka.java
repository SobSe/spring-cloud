package ru.sobse.partnerservice.client;

import java.util.concurrent.ExecutionException;

public interface ContractServiceKafka {

    String getContracts(Long partnerId) throws ExecutionException, InterruptedException;
}
