package ru.netology.moneytransferservice;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.Getter;

@JsonAutoDetect
@Getter
@Data
public class RequestObjact {
    private final String cardFromNumber;
    private final String cardFromValidTill;
    private final String cardFromCVV;
    private final Amount amount;
    private final String cardToNumber;

    public RequestObjact(String cardFromNumber) {
        cardFromValidTill = "11/55";
        cardFromCVV = "555";
        amount = new Amount();
        this.cardFromNumber = cardFromNumber;
        cardToNumber = "8888888888888888";
    }

    @JsonAutoDetect
    private static class Amount {
        private final int value;
        private final String currency;

        public Amount() {
            value = 1000;
            currency = "RUB";
        }
    }
}

