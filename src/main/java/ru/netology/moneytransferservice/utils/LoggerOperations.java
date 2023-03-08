package ru.netology.moneytransferservice.utils;


import lombok.extern.slf4j.Slf4j;
import ru.netology.moneytransferservice.model.TransferOperation;

import java.io.*;

@Slf4j
public class LoggerOperations {

    private final String PATH_TO_SAVE_LOGS = "Logs";
    private final String NAME_LOG_FILE = "/Log.txt";

    private final String TEXT_FOR_LOG_MSG = "%s операция перевода, карта отправителя %s, карта получателя %s. Сумма %d %s, комиссия %.2f. %s \n";

    public void writeOperation(TransferOperation operation, String... result) {
        final File path = new File(PATH_TO_SAVE_LOGS);
        if(!path.exists()) path.mkdirs();
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(PATH_TO_SAVE_LOGS+NAME_LOG_FILE, true))) {
            printWriter.print(String.format(TEXT_FOR_LOG_MSG,operation.getLocalDateTime(),
                    operation.getNumberCardFrom(),
                    operation.getNumberCardTo(),
                    operation.getAmount().value(),
                    operation.getAmount().currency(),
                    operation.getTransferFee(),
                    result.length>0 ? result[0] :""));
        } catch (IOException e) {
            log.error("Ошибка при записи операции в "+this.getClass().getSimpleName());
        }
    }
}
