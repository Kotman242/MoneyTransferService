package ru.netology.moneytransferservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.moneytransferservice.model.confirmingObjact.ConfirmingOperationTO;
import ru.netology.moneytransferservice.model.transferObjact.MoneyTransferRequestTO;
import ru.netology.moneytransferservice.model.transferResponses.GoodTransferResponseTO;
import ru.netology.moneytransferservice.service.MoneyTransferService;

@Slf4j
@CrossOrigin(origins = "https://serp-ya.github.io")
@RestController
@RequiredArgsConstructor
public class MoneyTransferController {

    private final MoneyTransferService moneyTransferService;

    @PostMapping("${application.endpoint.transfer}")
    public ResponseEntity<GoodTransferResponseTO> moneyTransfer(@RequestBody final MoneyTransferRequestTO moneyTransferRequestTO) {
        log.debug("Request to /transfer");
        final long id = moneyTransferService.moneyTransferRequest(moneyTransferRequestTO);
        return ResponseEntity.ok(new GoodTransferResponseTO(id));
    }

    @PostMapping("${application.endpoint.confirmOperation}")
    public ResponseEntity<?> confirmOperation(@RequestBody ConfirmingOperationTO confirmingOperationTO) {
        log.debug("Request to /confirmOperation");
        final long id = moneyTransferService.transferСonfirmation(confirmingOperationTO);
        return ResponseEntity.ok(new GoodTransferResponseTO(id));
    }
}

