package ru.netology.moneytransferservice.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidCardParametersException extends RuntimeException {
    public InvalidCardParametersException(String message) {
        super(message);
    }
}
