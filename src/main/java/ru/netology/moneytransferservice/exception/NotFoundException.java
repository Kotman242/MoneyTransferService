package ru.netology.moneytransferservice.exception;

//@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
