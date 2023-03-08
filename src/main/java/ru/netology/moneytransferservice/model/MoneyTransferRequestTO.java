package ru.netology.moneytransferservice.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.experimental.Accessors;

@JsonAutoDetect
@Accessors(chain = true)
public record MoneyTransferRequestTO(@JsonProperty("cardFromNumber") @NotBlank String NUMBER,
                                     @JsonProperty("cardFromValidTill") @NotBlank String VALID_TILL,
                                     @JsonProperty("cardFromCVV") @NotBlank String CVC, @NotBlank Amount amount,
                                     @JsonProperty("cardToNumber") @NotBlank String CARD_TO_NUMBER) {
    public Card getCard() {
        return new Card(NUMBER, VALID_TILL, CVC);
    }

}
