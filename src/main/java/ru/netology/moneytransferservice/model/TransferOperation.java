package ru.netology.moneytransferservice.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

@Getter
public final class TransferOperation {

    private static final AtomicLong idCounter = new AtomicLong(0);
    private final Amount amount;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm:ss");
    private final LocalDateTime localDateTime;
    private final long id;
    private final String numberCardTo;
    private final String numberCardFrom;
    private final double transferFee;
    public TransferOperation(Amount amount, LocalDateTime localDateTime, String numberCardTo, String numberCardFrom) {
        this.amount = amount;
        this.localDateTime = localDateTime;
        this.numberCardTo = numberCardTo;
        this.numberCardFrom = numberCardFrom;
        id=idCounter.getAndIncrement();
        transferFee=amount.value()*0.01;
    }

    public String getLocalDateTime() {
        return localDateTime.format(formatter);
    }

}
