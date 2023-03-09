package ru.netology.moneytransferservice.model.transferObjact;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.experimental.Accessors;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.Card;

@JsonAutoDetect
@Accessors(chain = true)
public record MoneyTransferRequestTO(@JsonProperty("cardFromNumber") @NotBlank String CORD_FROM_NUMBER,
                                     @JsonProperty("cardFromValidTill") @NotBlank String VALID_TILL,
                                     @JsonProperty("cardFromCVV") @NotBlank String CVC, @NotBlank Amount amount,
                                     @JsonProperty("cardToNumber") @NotBlank String CARD_TO_NUMBER) {
    public Card getCard() {
        return new Card(CORD_FROM_NUMBER, VALID_TILL, CVC);
    }

}
