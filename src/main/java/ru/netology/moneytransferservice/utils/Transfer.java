package ru.netology.moneytransferservice.utils;

import lombok.RequiredArgsConstructor;
import ru.netology.moneytransferservice.exeptions.NotEnoughMoneyExeption;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.Card;
import ru.netology.moneytransferservice.model.TransferOperation;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class Transfer {

    public static TransferOperation transferMoney(Card from, Card to, Amount amount){
        System.out.println("балик   "+from.getBalance());
        System.out.println("Запрос   "+amount.value());
        if(from.getBalance() < amount.value()) throw new NotEnoughMoneyExeption();
        to.setBalance(to.getBalance()+amount.value());
        from.setBalance(from.getBalance()-amount.value());
        System.out.println(to.getBalance());
        System.out.println(from.getBalance());
        return new TransferOperation(amount, LocalDateTime.now(),to.getNUMBER(), from.getNUMBER());
    }
}
