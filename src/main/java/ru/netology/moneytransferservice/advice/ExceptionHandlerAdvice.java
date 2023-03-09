package ru.netology.moneytransferservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.netology.moneytransferservice.exception.InvalidCardParametersException;
import ru.netology.moneytransferservice.exception.NotEnoughMoneyException;
import ru.netology.moneytransferservice.exception.NotFoundException;
import ru.netology.moneytransferservice.model.transferResponses.BadTransferResponseTO;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({InvalidCardParametersException.class, NotFoundException.class})
    public ResponseEntity<BadTransferResponseTO> invalidCardParametersExceptionHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BadTransferResponseTO(e.getMessage()));
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<BadTransferResponseTO> notEnoughMoneyHandler(NotEnoughMoneyException e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BadTransferResponseTO(e.getMessage()));
    }
}
