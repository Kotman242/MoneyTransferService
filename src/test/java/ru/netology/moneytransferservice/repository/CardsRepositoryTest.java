package ru.netology.moneytransferservice.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.el.parser.AstNotEqual;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.moneytransferservice.model.Card;
@ExtendWith(MockitoExtension.class)
public class CardsRepositoryTest {
    @InjectMocks
    private CardsRepository cardsRepository;

    private final Card cardTrue = new Card("8888888888888888", "11/88", "888");
    private final Card cardFalse = new Card("8888888788888888", "11/88", "888");
    @Test
    void isValideCardTest(){
        assertTrue(cardsRepository.isValideCard(cardTrue));
        assertFalse(cardsRepository.isValideCard(cardFalse));
    }

    @Test
    void getTest(){
        assertEquals(cardTrue,cardsRepository.get(cardTrue.getNUMBER()));
        assertNotEquals(cardTrue,cardsRepository.get(cardFalse.getNUMBER()));
    }

    @Test
    void isExistTest(){
        assertTrue(cardsRepository.isExist(cardTrue.getNUMBER()));
        assertFalse(cardsRepository.isExist(cardFalse.getNUMBER()));
    }
}
