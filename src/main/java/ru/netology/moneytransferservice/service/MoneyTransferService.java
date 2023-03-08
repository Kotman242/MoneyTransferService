package ru.netology.moneytransferservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.moneytransferservice.exeptions.CardNotFoundExeption;
import ru.netology.moneytransferservice.exeptions.InvalidCardParametersExeption;
import ru.netology.moneytransferservice.model.MoneyTransferRequestTO;
import ru.netology.moneytransferservice.model.TransferOperation;
import ru.netology.moneytransferservice.repository.CardsRepository;
import ru.netology.moneytransferservice.utils.LoggerOperations;
import ru.netology.moneytransferservice.utils.Transfer;

@Slf4j
@RequiredArgsConstructor
@Service
public class MoneyTransferService {
    private final CardsRepository cardsRepository;
    private final LoggerOperations loggerOperations;

    public long transferMoney(MoneyTransferRequestTO moneyTransferRequestTO){
        System.out.println(moneyTransferRequestTO.getCard());
        if (!cardsRepository.isExistCard(moneyTransferRequestTO.getCard())) throw new CardNotFoundExeption();
        System.out.println(moneyTransferRequestTO.getCard());
        if (!cardsRepository.isValideCard(moneyTransferRequestTO.getCard())) throw new InvalidCardParametersExeption();
        final TransferOperation transferOperation = Transfer.transferMoney(cardsRepository.getCardByNumber(moneyTransferRequestTO.NUMBER()),
                cardsRepository.getCardByNumber(moneyTransferRequestTO.CARD_TO_NUMBER()),
                moneyTransferRequestTO.amount());
        loggerOperations.writeOperation(transferOperation);
        return transferOperation.getId();
    }


}
