package ru.netology.moneytransferservice.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.netology.moneytransferservice.exception.CompletedOperationExceptions;
import ru.netology.moneytransferservice.exception.InvalidCardParametersException;
import ru.netology.moneytransferservice.exception.NotFoundException;
import ru.netology.moneytransferservice.model.confirmingObjact.ConfirmingOperationTO;
import ru.netology.moneytransferservice.model.transferObjact.MoneyTransferRequestTO;
import ru.netology.moneytransferservice.repository.CardsRepository;
import ru.netology.moneytransferservice.repository.TransferOperationRepository;

@Slf4j
@RequiredArgsConstructor
public class ExceptionChecker {

    private final CardsRepository cardsRepository;
    private final TransferOperationRepository transferOperationRepository;

    private String msg;

    public void checkCompletedOperationExceptions(ConfirmingOperationTO confirmingOperationTO) {
        long id = confirmingOperationTO.operationId();
        if (transferOperationRepository.get(id).isCompleted()) {
            log.debug(String.format("Транзакция №%d уже исполнена", id));
            throw new CompletedOperationExceptions("Транзакция с данным ID уже исполнена");
        }
    }

    public void checkInvalidCardParametersException(MoneyTransferRequestTO moneyTransferRequestTO) {
        if (!cardsRepository.isValideCard(moneyTransferRequestTO.getCard())) {
            msg = "Неверные данные карты ";
            log.debug(msg);
            throw new InvalidCardParametersException(msg);
        }
    }

    public void checkOperation(ConfirmingOperationTO confirmingOperationTO) {
        long id = confirmingOperationTO.operationId();
        if (!transferOperationRepository.isExist(id)) {
            msg = String.format("Транзакция №%d уже исполнена", id);
            log.debug(msg);
            throw new NotFoundException(msg);
        }
    }

    public void checkCard(MoneyTransferRequestTO moneyTransferRequestTO) {
        if (!cardsRepository.isExist(moneyTransferRequestTO.getCard().getNUMBER())) {
            msg = "Карта с таким номером не найдена";
            log.debug(msg);
            throw new NotFoundException(msg);
        }
    }

}
