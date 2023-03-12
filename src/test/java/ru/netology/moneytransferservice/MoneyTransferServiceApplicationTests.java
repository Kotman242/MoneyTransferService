package ru.netology.moneytransferservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.moneytransferservice.controller.MoneyTransferController;
import ru.netology.moneytransferservice.repository.CardsRepository;
import ru.netology.moneytransferservice.repository.TransferOperationRepository;
import ru.netology.moneytransferservice.service.MoneyTransferService;



@SpringBootTest
@AutoConfigureMockMvc
class MoneyTransferServiceApplicationTests {
    @Autowired
    TransferOperationRepository transferOperationRepository;
    @Autowired
    CardsRepository cardsRepository;
    @Autowired
    MoneyTransferService moneyTransferService;
    @Autowired
    MoneyTransferController moneyTransferController;
    @Test
    void contextLoads() {
        assertNotNull(transferOperationRepository);
        assertNotNull(cardsRepository);
        assertNotNull(moneyTransferService);
        assertNotNull(moneyTransferController);
    }

}
