package ru.netology.moneytransferservice.service;

import lombok.extern.slf4j.Slf4j;
import ru.netology.moneytransferservice.exception.NotEnoughMoneyException;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.Card;
import ru.netology.moneytransferservice.model.transferObjact.TransferOperation;

@Slf4j
public class Transfer {

    public static TransferOperation transferMoney(Card from, Card to, Amount amount) {
        log.debug("TransferOperation.transferMoney  start");
        if (from.getBalance() < amount.value()) throw new NotEnoughMoneyException("На счету недостаточно средств ");
        to.setBalance(to.getBalance() + amount.value());
        from.setBalance(from.getBalance() - amount.value());
        log.debug("TransferOperation.transferMoney  successfully");
        return new TransferOperation(amount, to.getNUMBER(), from.getNUMBER());
    }
}
