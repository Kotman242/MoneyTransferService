package ru.netology.moneytransferservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.moneytransferservice.exception.CompletedOperationExceptions;
import ru.netology.moneytransferservice.exception.InvalidCardParametersException;
import ru.netology.moneytransferservice.exception.NotFoundException;
import ru.netology.moneytransferservice.model.confirmingObjact.ConfirmingOperationTO;
import ru.netology.moneytransferservice.model.transferObjact.MoneyTransferRequestTO;
import ru.netology.moneytransferservice.model.transferObjact.TransferOperation;
import ru.netology.moneytransferservice.repository.CardsRepository;
import ru.netology.moneytransferservice.repository.TransferOperationRepository;
import ru.netology.moneytransferservice.utils.LoggerOperations;
import ru.netology.moneytransferservice.utils.Transfer;

@Slf4j
@RequiredArgsConstructor
@Service
public class MoneyTransferService {
    private final CardsRepository cardsRepository;

    private final TransferOperationRepository transferOperationRepository;
    private final LoggerOperations loggerOperations;

    public long moneyTransferRequest(MoneyTransferRequestTO moneyTransferRequestTO) {
        log.debug("MoneyTransferService.moneyTransferRequest  start");
        if (!cardsRepository.isExist(moneyTransferRequestTO.getCard().getNUMBER()))
            throw new NotFoundException("Карта с таким номером не найдена");
        if (!cardsRepository.isValideCard(moneyTransferRequestTO.getCard()))
            throw new InvalidCardParametersException("Неверные данные карты ");
        final TransferOperation transferOperation = new TransferOperation(moneyTransferRequestTO.amount(),
                moneyTransferRequestTO.CARD_TO_NUMBER(),
                moneyTransferRequestTO.CORD_FROM_NUMBER()
        );
        transferOperationRepository.add(transferOperation);
        loggerOperations.writeOperation(transferOperation, "Создана");
        log.debug("MoneyTransferService.moneyTransferRequest  finish");
        return transferOperation.getId();
    }


    public long transferСonfirmation(ConfirmingOperationTO confirmingOperationTO) {
        log.debug("MoneyTransferService.transferСonfirmation  start");
        final TransferOperation transferOperation = transferOperationRepository.get(confirmingOperationTO.operationId());
        if (!transferOperationRepository.isExist(transferOperation.getId()))
            throw new NotFoundException("Транзакция с данным ID не найдена");
        if (transferOperationRepository.get(transferOperation.getId()).isCompleted())
            throw new CompletedOperationExceptions("Транзакция с данным ID уже исполнена");
        Transfer.transferMoney(cardsRepository.get(transferOperation.getNumberCardFrom()),
                cardsRepository.get(transferOperation.getNumberCardTo()),
                transferOperation.getAmount());
        transferOperation.setCompleted(true);
        loggerOperations.writeOperation(transferOperation, "Исполнен");
        log.debug("MoneyTransferService.transferСonfirmation  finish");
        return transferOperation.getId();
    }
}
