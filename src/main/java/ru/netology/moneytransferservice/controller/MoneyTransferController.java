package ru.netology.moneytransferservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.moneytransferservice.model.GoodResponse;
import ru.netology.moneytransferservice.model.MoneyTransferRequestTO;
import ru.netology.moneytransferservice.service.MoneyTransferService;

@RestController
@RequiredArgsConstructor
public class MoneyTransferController {

    private final MoneyTransferService moneyTransferService;
    @PostMapping("${application.endpoint.transfer}")
    public ResponseEntity<?> moneyTransfer(@RequestBody final MoneyTransferRequestTO moneyTransferRequestTO){
        final Long id = moneyTransferService.transferMoney(moneyTransferRequestTO);

        return new ResponseEntity<>(new GoodResponse(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public String fetmap(){
        return "WORK";
    }

    @GetMapping("${application.endpoint.transfer}")
    public String fetmap2(){
        return "WORK TO";
    }
}
