package ru.netology.moneytransferservice.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonAutoDetect
@Getter
@Setter
@AllArgsConstructor
public class GoodResponse {
    private final String operationId;

    public GoodResponse(long id) {
        this.operationId = String.valueOf(id);
    }
}
