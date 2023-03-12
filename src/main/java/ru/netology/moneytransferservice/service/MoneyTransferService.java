package ru.netology.moneytransferservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.Card;
import ru.netology.moneytransferservice.model.confirmingObjact.ConfirmingOperationTO;
import ru.netology.moneytransferservice.model.transferObjact.MoneyTransferRequestTO;
import ru.netology.moneytransferservice.model.transferObjact.TransferOperation;
import ru.netology.moneytransferservice.repository.CardsRepository;
import ru.netology.moneytransferservice.repository.TransferOperationRepository;
import ru.netology.moneytransferservice.utils.LoggerOperations;
import ru.netology.moneytransferservice.validation.ExceptionChecker;
import ru.netology.moneytransferservice.validation.Transfer;

@Slf4j
@RequiredArgsConstructor
@Service
public class MoneyTransferService implements TransferService {
    private final CardsRepository cardsRepository;
    private final TransferOperationRepository transferOperationRepository;
    private final LoggerOperations loggerOperations;
    private final ExceptionChecker exceptionChecker;
    private final Transfer transfer;

    @Override
    public long moneyTransferRequest(MoneyTransferRequestTO moneyTransferRequestTO) {
        log.debug("MoneyTransferService.moneyTransferRequest  start");

        exceptionChecker.checkCard(moneyTransferRequestTO);
        exceptionChecker.checkInvalidCardParametersException(moneyTransferRequestTO);

        final TransferOperation transferOperation = createAndAddTransferOperation(moneyTransferRequestTO);
        loggerOperations.writeOperation(transferOperation, "Создана");
        log.debug("MoneyTransferService.moneyTransferRequest  finish");
        return transferOperation.getId();
    }

    @Override
    public long transferСonfirmation(ConfirmingOperationTO confirmingOperationTO) {
        log.debug("MoneyTransferService.transferСonfirmation  start");

        exceptionChecker.checkOperation(confirmingOperationTO);
        exceptionChecker.checkCompletedOperationExceptions(confirmingOperationTO);

        final TransferOperation transferOperation = transferAndConfirmOperation(confirmingOperationTO, transfer);
        loggerOperations.writeOperation(transferOperation, "Исполнен");
        log.debug("MoneyTransferService.transferСonfirmation  finish");
        return transferOperation.getId();
    }

    //ToDo make the method private after the tests
    public TransferOperation createAndAddTransferOperation(MoneyTransferRequestTO moneyTransferRequestTO) {
        TransferOperation transferOperation = new TransferOperation(moneyTransferRequestTO.amount(),
                moneyTransferRequestTO.CARD_TO_NUMBER(),
                moneyTransferRequestTO.CORD_FROM_NUMBER()
        );
        transferOperationRepository.add(transferOperation);
        return transferOperation;
    }

    //ToDo make the method private after the tests
    public TransferOperation transferAndConfirmOperation(ConfirmingOperationTO confirmingOperationTO, Transfer transfer) {
        TransferOperation transferOperation = transferOperationRepository.get(confirmingOperationTO.operationId());

        Card from = cardsRepository.get(transferOperation.getNumberCardFrom());
        Card to = cardsRepository.get(transferOperation.getNumberCardTo());
        Amount amount = transferOperation.getAmount();

        transfer.transferMoney(from, to, amount);
        transferOperation.setCompleted(true);
        return transferOperation;
    }
}
