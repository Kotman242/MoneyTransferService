package ru.netology.moneytransferservice.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.validation.constraints.NotBlank;
import lombok.experimental.Accessors;

@JsonAutoDetect
@Accessors(chain = true)
public record Amount(@NotBlank int value,
                     @NotBlank String currency) {
}
