package ru.netology.moneytransferservice.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.moneytransferservice.exception.CompletedOperationExceptions;
import ru.netology.moneytransferservice.exception.InvalidCardParametersException;
import ru.netology.moneytransferservice.exception.NotFoundException;
import ru.netology.moneytransferservice.model.Card;
import ru.netology.moneytransferservice.model.confirmingObjact.ConfirmingOperationTO;
import ru.netology.moneytransferservice.model.transferObjact.MoneyTransferRequestTO;
import ru.netology.moneytransferservice.model.transferObjact.TransferOperation;
import ru.netology.moneytransferservice.repository.CardsRepository;
import ru.netology.moneytransferservice.repository.TransferOperationRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExceptionCheckerTest {
    @Mock
    private CardsRepository cardsRepository;
    @Mock
    private TransferOperationRepository transferOperationRepository;
    @Mock
    private ConfirmingOperationTO confirmingOperationTO;
    @Mock
    private MoneyTransferRequestTO moneyTransferRequestTO;
    @Mock
    private TransferOperation transferOperation;
    @InjectMocks
    private ExceptionChecker exceptionChecker;

    @Test
    void checkCompletedOperationExceptionsTest() {
        doReturn(1L).when(confirmingOperationTO).operationId();
        doReturn(transferOperation).when(transferOperationRepository).get(1);
        doReturn(true, false).when(transferOperation).isCompleted();

        assertThrows(CompletedOperationExceptions.class, () -> {
            exceptionChecker.checkCompletedOperationExceptions(confirmingOperationTO);
        });
        assertDoesNotThrow(() -> {
            exceptionChecker.checkCompletedOperationExceptions(confirmingOperationTO);
        });
    }

    @Test
    void checkInvalidCardParametersExceptionTest() {
        Card card = mock(Card.class);

        doReturn(card).when(moneyTransferRequestTO).getCard();
        doReturn(false, true).
                when(cardsRepository).isValideCard(card);

        assertThrows(InvalidCardParametersException.class, () -> {
            exceptionChecker.checkInvalidCardParametersException(moneyTransferRequestTO);
        });
        assertDoesNotThrow(() -> {
            exceptionChecker.checkInvalidCardParametersException(moneyTransferRequestTO);
        });
    }

    @Test
    void checkOperationTest() {
        doReturn(1L).when(confirmingOperationTO).operationId();
        doReturn(false, true).
                when(transferOperationRepository).isExist(1L);

        assertThrows(NotFoundException.class, () -> {
            exceptionChecker.checkOperation(confirmingOperationTO);
        });
        assertDoesNotThrow(() -> {
            exceptionChecker.checkOperation(confirmingOperationTO);
        });
    }

    @Test
    void checkCardTest() {
        Card card = mock(Card.class);

        doReturn(card).when(moneyTransferRequestTO).getCard();
        doReturn("5555555555555555").when(card).getNUMBER();
        doReturn(false, true).when(cardsRepository).isExist(anyString());

        assertThrows(NotFoundException.class, () -> {
            exceptionChecker.checkCard(moneyTransferRequestTO);
        });
        assertDoesNotThrow(() -> {
            exceptionChecker.checkCard(moneyTransferRequestTO);
        });
    }
}
