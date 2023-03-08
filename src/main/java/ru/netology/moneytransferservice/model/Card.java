package ru.netology.moneytransferservice.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;


@Getter
@Setter
@Accessors(chain = true)
public class Card {
    private final String NUMBER;
    private final String VALID_TILL;
    private final String CVC;
    private long balance;

    public Card(String NUMBER, String VALID_TILL, String CVC) {
        this.NUMBER = NUMBER;
        this.VALID_TILL = VALID_TILL;
        this.CVC = CVC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(NUMBER, card.NUMBER) && Objects.equals(VALID_TILL, card.VALID_TILL) && Objects.equals(CVC, card.CVC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NUMBER, VALID_TILL, CVC);
    }

    @Override
    public String toString() {
        return "Card{" +
                "NUMBER='" + NUMBER + '\'' +
                ", VALID_TILL='" + VALID_TILL + '\'' +
                ", CVC='" + CVC + '\'' +
                ", balance=" + balance +
                '}';
    }
}


