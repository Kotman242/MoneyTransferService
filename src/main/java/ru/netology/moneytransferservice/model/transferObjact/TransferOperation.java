package ru.netology.moneytransferservice.model.transferObjact;

import lombok.Getter;
import lombok.Setter;
import ru.netology.moneytransferservice.model.Amount;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferOperation that = (TransferOperation) o;
        return id == that.id && Objects.equals(amount, that.amount) && Objects.equals(numberCardTo, that.numberCardTo) && Objects.equals(numberCardFrom, that.numberCardFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, id, numberCardTo, numberCardFrom);
    }
}
