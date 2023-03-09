package ru.netology.moneytransferservice.model.transferObjact;

import lombok.Getter;
import lombok.Setter;
import ru.netology.moneytransferservice.model.Amount;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
public final class TransferOperation {

    private static final AtomicLong idCounter = new AtomicLong(0);
    private final Amount amount;
    private final long id;
    private final String numberCardTo;
    private final String numberCardFrom;
    private final double transferFee;
    private final String verificationCode;
    private boolean completed;

    public TransferOperation(Amount amount, String numberCardTo, String numberCardFrom) {
        this.amount = amount;
        this.numberCardTo = numberCardTo;
        this.numberCardFrom = numberCardFrom;
        id = idCounter.getAndIncrement();
        transferFee = amount.value() * 0.01;
        verificationCode = "0000";
        completed = false;
    }


    public boolean checkingVerificationCode(String verificationCode) {
        return this.verificationCode.equals(verificationCode);
    }

    public boolean isCompleted(long id) {
        return completed;
    }
}
