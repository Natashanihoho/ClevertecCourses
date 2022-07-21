package ru.clevertec.gordievich.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    final String SOURCE_FILE_PATH = "src/test/resources/stockProducts.txt";
    Validator validator = new Validator(SOURCE_FILE_PATH);

    @Test
    void getCorrectPositions() throws IOException {
        List<String> expected = Arrays.asList("1;Lollipop;1.07;20;false", "2;Popcorn;2.10;20;true");
        List<String> actual = validator.getCorrectPositions();

        assertEquals(actual, expected);
    }

    @Test
    void isWrittenInvalidDataFile() throws IOException {
        List<String> expected = Arrays.asList("110;Lollipop;1.07;20;false", "five;Biscuit;1.4811;20;true");
        validator.getCorrectPositions();
        List<String> actual = Files.readAllLines(Path.of(PropertiesUtil.get("INVALID_DATA_FILE_PATH")));

        assertAll(
                () -> assertTrue(Files.exists(Path.of(PropertiesUtil.get("INVALID_DATA_FILE_PATH")))),
                () -> assertEquals(actual, expected)
        );
    }
}
