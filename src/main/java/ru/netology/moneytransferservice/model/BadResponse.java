package ru.netology.moneytransferservice.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@JsonAutoDetect
@Data
public class BadResponse {
    private final String message;
    private final int id;

    public BadResponse(String message) {
        this.message = message;
        this.id = new Random().nextInt(Integer.MAX_VALUE);
    }
}
