package ru.netology.moneytransferservice.validation;

import lombok.extern.slf4j.Slf4j;
import ru.netology.moneytransferservice.exception.NotEnoughMoneyException;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.Card;
import ru.netology.moneytransferservice.model.transferObjact.TransferOperation;

@Slf4j
public class Transfer {

    private final double FEE = 0.01; //1%

    public TransferOperation transferMoney(Card from, Card to, Amount amount) {
        log.debug("TransferOperation.transferMoney  start");
        final int sumToTransfer = amount.value();
        if (from.getBalance() < (sumToTransfer + (sumToTransfer * FEE)))
            throw new NotEnoughMoneyException("На счету недостаточно средств ");
        to.setBalance(to.getBalance() + amount.value());
        from.setBalance(from.getBalance() - (sumToTransfer + (sumToTransfer * FEE)));
        log.debug("TransferOperation.transferMoney  successfully");
        return new TransferOperation(amount, to.getNUMBER(), from.getNUMBER());
    }
}
