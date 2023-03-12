package ru.netology.moneytransferservice.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.netology.moneytransferservice.model.confirmingObjact.ConfirmingOperationTO;
import ru.netology.moneytransferservice.model.transferObjact.MoneyTransferRequestTO;
import ru.netology.moneytransferservice.model.transferResponses.GoodTransferResponseTO;
import ru.netology.moneytransferservice.service.TransferService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class MoneyTransferControllerTest {
    @Mock
    private TransferService transferService;
    @InjectMocks
    private MoneyTransferController moneyTransferController;
    private ObjectMapper objectMapper = new ObjectMapper();
    private GoodTransferResponseTO expectedGoodTransferResponseTO = new GoodTransferResponseTO("0");


    @Test
    private void moneyTransferRequestTest_RenurnValidResponseEntity() throws JsonProcessingException {

        MoneyTransferRequestTO moneyTransferRequestTO = Mockito.mock(MoneyTransferRequestTO.class);

        Mockito.doReturn(0L).when(transferService).moneyTransferRequest(moneyTransferRequestTO);

        var expected = ResponseEntity.ok().body(expectedGoodTransferResponseTO);

        var result = moneyTransferController.moneyTransfer(moneyTransferRequestTO);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(objectMapper.writeValueAsString(expected),
                objectMapper.writeValueAsString(result));
    }

    @Test
    void confirmOperation_RenurnValidResponseEntity() throws JsonProcessingException {

        ConfirmingOperationTO confirmingOperationTO = Mockito.mock(ConfirmingOperationTO.class);

        Mockito.doReturn(0L).when(transferService).transferСonfirmation(confirmingOperationTO);

        var expected = ResponseEntity.ok().body(expectedGoodTransferResponseTO);
        var result = moneyTransferController.confirmOperation(confirmingOperationTO);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(objectMapper.writeValueAsString(expected),
                objectMapper.writeValueAsString(result));
    }
}
