package ru.netology.moneytransferservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.moneytransferservice.repository.CardsRepository;
import ru.netology.moneytransferservice.repository.TransferOperationRepository;
import ru.netology.moneytransferservice.utils.ExceptionChecker;
import ru.netology.moneytransferservice.utils.LoggerOperations;

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

    @Bean
    public ExceptionChecker getExceptionChecker(CardsRepository cardsRepository, TransferOperationRepository transferOperationRepository) {
        return new ExceptionChecker(cardsRepository, transferOperationRepository);
    }

}
