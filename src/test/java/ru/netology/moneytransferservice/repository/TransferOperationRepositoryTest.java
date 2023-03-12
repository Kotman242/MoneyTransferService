package ru.netology.moneytransferservice.repository;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.transferObjact.TransferOperation;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder
@ExtendWith(MockitoExtension.class)
public class TransferOperationRepositoryTest {
    private static TransferOperation transferOperationFirst = new TransferOperation(
            new Amount(500, "Rub"),
            "8888888888888888",
            "5555555555555555");
    private static TransferOperation transferOperationSecond = new TransferOperation(
            new Amount(500, "Rub"),
            "5555555555555555",
            "8888888888888888");

    @InjectMocks
    private TransferOperationRepository transferOperationRepository;

    @Test
    void addAndIsExistTest() {
        transferOperationRepository.add(transferOperationFirst);
        assertTrue(transferOperationRepository.isExist(transferOperationFirst.getId()));
        assertFalse(transferOperationRepository.isExist(transferOperationSecond.getId()));
        transferOperationRepository.add(transferOperationSecond);
        assertTrue(transferOperationRepository.isExist(transferOperationSecond.getId()));
    }

    @Test
    void getTest() {
        assertEquals(transferOperationFirst, transferOperationRepository.get(0));
        assertNotEquals(transferOperationFirst, transferOperationRepository.get(1));
    }
}
