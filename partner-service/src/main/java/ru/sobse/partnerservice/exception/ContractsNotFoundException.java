package ru.sobse.partnerservice.exception;

public class ContractsNotFoundException extends RuntimeException {
    public ContractsNotFoundException(String message) {
        super(message);
    }
}
