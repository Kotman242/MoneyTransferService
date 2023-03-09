package ru.netology.moneytransferservice.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.moneytransferservice.exception.NotEnoughMoneyException;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.Card;

public class TransferTest {

    @Test
    void transferMoneyTest() {
        Card cardTo = Mockito.mock(Card.class);
        Card cardFROM = Mockito.mock(Card.class);
        Amount amount = Mockito.mock(Amount.class);
        Mockito.when(cardFROM.getBalance()).thenReturn(500L);
        Mockito.when(amount.value()).thenReturn(1000);
        Assertions.assertThrows(NotEnoughMoneyException.class, () -> {
            Transfer.transferMoney(cardFROM, cardTo, amount);
        });
    }
}
