package ru.clevertec.gordievich.shop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.clevertec.gordievich.exceptions.NotEnoughProductsException;
import ru.clevertec.gordievich.exceptions.UnknownIdException;
import ru.clevertec.gordievich.service.StoreFactory;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    private Store store = StoreFactory.defaultStore();

    @ParameterizedTest
    @ValueSource(ints = {-5, 0, 89})
    void throwExceptionIfProductIdIsUnknown(int id) {
        assertThrows(UnknownIdException.class, () -> store.getProduct(id, 1));
    }

    @Test
    void throwExceptionIfNotEnoughProduct() {
        assertThrows(NotEnoughProductsException.class, () -> store.getProduct(1, 100));
    }

    @Test
    void getCashiersNotNull() {
        assertNotNull(store.getCashiers());
    }

    @Test
    void notEmptyFieldsInCashiersList() {
        assertFalse(store.getCashiers().isEmpty());
    }

}
