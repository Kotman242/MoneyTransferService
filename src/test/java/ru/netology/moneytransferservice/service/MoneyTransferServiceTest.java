package ru.netology.moneytransferservice.service;

import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.confirmingObjact.ConfirmingOperationTO;
import ru.netology.moneytransferservice.model.transferObjact.MoneyTransferRequestTO;
import ru.netology.moneytransferservice.model.transferObjact.TransferOperation;
import ru.netology.moneytransferservice.repository.CardsRepository;
import ru.netology.moneytransferservice.repository.TransferOperationRepository;
import ru.netology.moneytransferservice.validation.ExceptionChecker;
import ru.netology.moneytransferservice.utils.LoggerOperations;
import ru.netology.moneytransferservice.validation.Transfer;


@Slf4j
@ExtendWith(MockitoExtension.class)
@FixMethodOrder
public class MoneyTransferServiceTest {

    @Mock
    private ExceptionChecker exceptionChecker;
    @Mock
    private TransferOperationRepository transferOperationRepository;
    @Mock
    private LoggerOperations loggerOperations;
    @Mock
    private CardsRepository cardsRepository;
    @Mock
    private Transfer transfer;
    @InjectMocks
    private MoneyTransferService moneyTransferService;

    private final static MoneyTransferRequestTO moneyTransferRequestTO = new MoneyTransferRequestTO("5555555555555555",
            "11/55","555",new Amount(10, "Rub"),"8888888888888888");
    private final static TransferOperation transferOperation = new TransferOperation(new Amount(100,"Rub"),"8888888888888888","5555555555555555");

    @Test
    void moneyTransferRequestTest(){
        Mockito.ignoreStubs(exceptionChecker);
        Mockito.ignoreStubs(loggerOperations);

        long result = moneyTransferService.moneyTransferRequest(moneyTransferRequestTO);

        assertEquals(1,result);
    }

    @Test
    void transferСonfirmationTest(){
        transferOperationRepository.add(transferOperation);
        Mockito.doReturn(transferOperation).when(transferOperationRepository).get(0);

        Mockito.ignoreStubs(exceptionChecker);
        Mockito.ignoreStubs(loggerOperations);

        ConfirmingOperationTO confirmingOperationTO = Mockito.mock(ConfirmingOperationTO.class);
        Mockito.doReturn(0L).when(confirmingOperationTO).operationId();


        long result = moneyTransferService.transferСonfirmation(confirmingOperationTO);
        assertEquals(0,result);
    }

    @Test
    void createAndAddTransferOperation_TestReturnValidData(){
        Mockito.ignoreStubs(exceptionChecker);
        Mockito.ignoreStubs(loggerOperations);

        TransferOperation result = moneyTransferService
                .createAndAddTransferOperation(moneyTransferRequestTO);

        assertNotNull(result);
        assertEquals(2,result.getId());
    }


    @Test
    void transferAndConfirmOperationTest(){
        ConfirmingOperationTO confirmingOperationTO = Mockito.mock(ConfirmingOperationTO.class);

        Mockito.doReturn(0L).
                when(confirmingOperationTO).operationId();
        Mockito.doReturn(transferOperation).
                when(transferOperationRepository).get(0);

        moneyTransferService.transferAndConfirmOperation(confirmingOperationTO,transfer);
        assertTrue(transferOperation.isCompleted());
    }
}
