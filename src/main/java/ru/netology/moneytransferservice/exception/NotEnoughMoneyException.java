package ru.netology.moneytransferservice.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
