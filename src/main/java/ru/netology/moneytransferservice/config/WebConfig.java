package ru.netology.moneytransferservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.moneytransferservice.utils.LoggerOperations;

@Configuration
public class WebConfig {

    @Bean
    public LoggerOperations getLoggerOperation(){
        return new LoggerOperations();
    }
}
