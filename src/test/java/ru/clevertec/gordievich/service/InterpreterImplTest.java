package ru.clevertec.gordievich.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.clevertec.gordievich.exceptions.InvalidDataFormat;
import ru.clevertec.gordievich.exceptions.NotEnoughProductsException;
import ru.clevertec.gordievich.exceptions.UnknownIdException;
import ru.clevertec.gordievich.shop.Store;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterImplTest {

    private Store store = StoreFactory.defaultStore();
    private Interpreter interpreter = new InterpreterImpl();

    @ParameterizedTest
    @ValueSource(strings = {"1-2 dfsf", "2.8 9-----", "products"})
    void throwExceptionIfInvalidDataFormat(String argsStr) {
        assertThrows(InvalidDataFormat.class, () -> interpreter.interpret(argsStr.split(" ")));
    }

    @Test
    void checkAllPositionsInReceipt() throws InvalidDataFormat, UnknownIdException, NotEnoughProductsException {
        String receipt = interpreter.interpret("6-8 10-2 18-4".split(" "));
        assertAll(
                () -> assertTrue(receipt.contains("Cake")),
                () -> assertTrue(receipt.contains("Marshmallow")),
                () -> assertTrue(receipt.contains("Lemonade"))
        );
    }
}
