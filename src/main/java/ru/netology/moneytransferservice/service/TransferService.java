package ru.netology.moneytransferservice.service;

import ru.netology.moneytransferservice.model.confirmingObjact.ConfirmingOperationTO;
import ru.netology.moneytransferservice.model.transferObjact.MoneyTransferRequestTO;

public interface TransferService {

    long transferĐˇonfirmation(ConfirmingOperationTO confirmingOperationTO);

    long moneyTransferRequest(MoneyTransferRequestTO moneyTransferRequestTO);
}
