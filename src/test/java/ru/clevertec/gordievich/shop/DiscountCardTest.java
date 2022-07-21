package ru.clevertec.gordievich.shop;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiscountCardTest {

    @ParameterizedTest
    @ValueSource(strings = {"card125", "", "card", "   ", "12Card", "card 5"})
    void getEmptyDiscountCardIfCardDoesNotExist(String cardName) {
        Optional<DiscountCard> actual = DiscountCard.getDiscountByCard(cardName);
        assertTrue(actual.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"card1", "CARD4", "Card8", "card7"})
    void getDiscountCardIfCardIsAvailable(String cardName) {
        Optional<DiscountCard> expected = Optional.of(DiscountCard.valueOf(cardName.toUpperCase()));
        Optional<DiscountCard> actual = DiscountCard.getDiscountByCard(cardName);
        assertEquals(expected, actual);
    }
}
