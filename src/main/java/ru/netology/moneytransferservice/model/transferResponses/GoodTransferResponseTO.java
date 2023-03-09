package ru.netology.moneytransferservice.model.transferResponses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonAutoDetect
@Getter
@Setter
@AllArgsConstructor
public class GoodTransferResponseTO {
    private final String operationId;

    public GoodTransferResponseTO(long id) {
        this.operationId = String.valueOf(id);
    }
}
