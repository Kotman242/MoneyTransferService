package ru.netology.moneytransferservice.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.validation.constraints.NotBlank;
import lombok.experimental.Accessors;

import java.util.Objects;

@JsonAutoDetect
@Accessors(chain = true)
public record Amount(@NotBlank int value, @NotBlank String currency) {
    public Amount(@NotBlank int value,
                  @NotBlank String currency) {
        this.value = value / 100;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Amount) obj;
        return this.value == that.value &&
                Objects.equals(this.currency, that.currency);
    }

    @Override
    public String toString() {
        return "Amount[" +
                "value=" + value + ", " +
                "currency=" + currency + ']';
    }


}
