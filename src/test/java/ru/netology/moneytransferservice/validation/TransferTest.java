package ru.netology.moneytransferservice.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.moneytransferservice.exception.NotEnoughMoneyException;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TransferTest {

    @InjectMocks
    private Transfer transfer;

    @Test
    void transferMoneyTest() {
        Card cardTo = Mockito.mock(Card.class);
        Card cardFROM = Mockito.mock(Card.class);
        Amount amount = Mockito.mock(Amount.class);
        Mockito.when(cardFROM.getBalance()).thenReturn(500.0);
        Mockito.when(amount.value()).thenReturn(1000);
        assertThrows(NotEnoughMoneyException.class, () -> {
            transfer.transferMoney(cardFROM, cardTo, amount);
        });
    }

    @Test
    void transferMoney_SuccessfulMoneyTransferTEST() {
        Card cardTo = new Card("1111111111111111", "11/55", "123").setBalance(1000);
        Card cardFROM = new Card("2222222222222222", "11/55", "123").setBalance(1000);
        Amount amount = Mockito.mock(Amount.class);
        Mockito.when(amount.value()).thenReturn(500);
        transfer.transferMoney(cardFROM, cardTo, amount);
        assertEquals(495, cardFROM.getBalance());
        assertEquals(1500, cardTo.getBalance());
    }
}
