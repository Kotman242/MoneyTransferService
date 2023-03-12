package ru.netology.moneytransferservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.moneytransferservice.repository.CardsRepository;
import ru.netology.moneytransferservice.repository.TransferOperationRepository;
import ru.netology.moneytransferservice.utils.LoggerOperations;
import ru.netology.moneytransferservice.validation.ExceptionChecker;
import ru.netology.moneytransferservice.validation.Transfer;

@Configuration
public class WebConfig {
    /**
     * запись в файл операции перевода и результат.
     * резализует dependency injection, с возможностью расширения
     */
    @Bean
    public LoggerOperations getLoggerOperation() {
        return new LoggerOperations();
    }

    /**
     * проверка валидности данных, при необходимости выбрасывает исключние
     *
     * @param cardsRepository
     * @param transferOperationRepository
     * @return
     */
    @Bean
    public ExceptionChecker getExceptionChecker(CardsRepository cardsRepository, TransferOperationRepository transferOperationRepository) {
        return new ExceptionChecker(cardsRepository, transferOperationRepository);
    }

    /**
     * Осуществляет перевод средств между счетами
     *
     * @return
     */
    @Bean
    public Transfer getTransfer() {
        return new Transfer();
    }
}
