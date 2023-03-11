package ru.netology.moneytransferservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.moneytransferservice.model.confirmingObjact.ConfirmingOperationTO;
import ru.netology.moneytransferservice.model.transferObjact.MoneyTransferRequestTO;
import ru.netology.moneytransferservice.model.transferObjact.TransferOperation;
import ru.netology.moneytransferservice.repository.CardsRepository;
import ru.netology.moneytransferservice.repository.TransferOperationRepository;
import ru.netology.moneytransferservice.utils.ExceptionChecker;
import ru.netology.moneytransferservice.utils.LoggerOperations;

@Slf4j
@RequiredArgsConstructor
@Service
public class MoneyTransferService implements TransferService {
    private final CardsRepository cardsRepository;
    private final TransferOperationRepository transferOperationRepository;
    private final LoggerOperations loggerOperations;
    private final ExceptionChecker exceptionChecker;

    @Override
    public long moneyTransferRequest(MoneyTransferRequestTO moneyTransferRequestTO) {
        log.debug("MoneyTransferService.moneyTransferRequest  start");

        exceptionChecker.checkCard(moneyTransferRequestTO);
        exceptionChecker.checkInvalidCardParametersException(moneyTransferRequestTO);

        final TransferOperation transferOperation = createAndAddtransferOperation(moneyTransferRequestTO);
        loggerOperations.writeOperation(transferOperation, "Создана");
        log.debug("MoneyTransferService.moneyTransferRequest  finish");
        return transferOperation.getId();
    }

    @Override
    public long transferСonfirmation(ConfirmingOperationTO confirmingOperationTO) {
        log.debug("MoneyTransferService.transferСonfirmation  start");

        exceptionChecker.checkOperation(confirmingOperationTO);
        exceptionChecker.checkCompletedOperationExceptions(confirmingOperationTO);

        final TransferOperation transferOperation = transferAndConfirmOperaton(confirmingOperationTO);
        loggerOperations.writeOperation(transferOperation, "Исполнен");
        log.debug("MoneyTransferService.transferСonfirmation  finish");
        return transferOperation.getId();
    }

    private TransferOperation createAndAddtransferOperation(MoneyTransferRequestTO moneyTransferRequestTO) {
        TransferOperation transferOperation = new TransferOperation(moneyTransferRequestTO.amount(),
                moneyTransferRequestTO.CARD_TO_NUMBER(),
                moneyTransferRequestTO.CORD_FROM_NUMBER()
        );
        transferOperationRepository.add(transferOperation);
        return transferOperation;
    }

    private TransferOperation transferAndConfirmOperaton(ConfirmingOperationTO confirmingOperationTO) {
        TransferOperation transferOperation = transferOperationRepository.get(confirmingOperationTO.operationId());
        ;
        Transfer.transferMoney(cardsRepository.get(transferOperation.getNumberCardFrom()),
                cardsRepository.get(transferOperation.getNumberCardTo()),
                transferOperation.getAmount());
        transferOperation.setCompleted(true);
        return transferOperation;
    }
}
