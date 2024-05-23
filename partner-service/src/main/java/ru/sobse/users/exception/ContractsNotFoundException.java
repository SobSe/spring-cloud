package ru.sobse.users.exception;

public class ContractsNotFoundException extends RuntimeException {
    public ContractsNotFoundException(String message) {
        super(message);
    }
}
