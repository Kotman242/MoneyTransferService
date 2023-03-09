package ru.netology.moneytransferservice.model.transferResponses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@JsonAutoDetect
@Setter
@Getter
public class BadTransferResponseTO {
    @JsonProperty
    private final String message;
    private final int id;

    public BadTransferResponseTO(String message) {
        this.message = message;
        this.id = new Random().nextInt(Integer.MAX_VALUE);
    }
}
