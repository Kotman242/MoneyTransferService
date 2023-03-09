package ru.netology.moneytransferservice.exception;

public class CompletedOperationExceptions extends RuntimeException {
    public CompletedOperationExceptions(String message) {
        super(message);
    }
}
