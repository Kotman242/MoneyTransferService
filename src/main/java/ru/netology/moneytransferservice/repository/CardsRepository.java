package ru.netology.moneytransferservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.netology.moneytransferservice.model.Card;

import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class CardsRepository {
    private static final ConcurrentHashMap<String, Card> repository = new ConcurrentHashMap<>();

    static {
        repository.put("3333333333333333",new Card("3333333333333333","1133","333").setBalance(3000));
        repository.put("5555555555555555",new Card("5555555555555555","1155","555").setBalance(5000));
        repository.put("8888888888888888",new Card("8888888888888888","1188","888").setBalance(8000));

    }

    public boolean isExistCard(Card card){
        return repository.containsKey(card.getNUMBER());
    }

    public boolean isValideCard(Card card){
        return repository.get(card.getNUMBER()).equals(card);
    }

    public Card getCardByNumber(String number){
        return repository.get(number);
    }
}
