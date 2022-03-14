package ru.clevertec.gordievich.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Validator {

    private final String ID_REGEX = "^[1-9]\\d?$|^100$";
    private final String NAME_REGEX = "^[A-Z][a-z]{2,29}$";
    private final String PRICE_REGEX = "^[1-9]\\d?\\.\\d\\d$|^100.00$";
    private final String COUNT_REGEX = "^[1-9]$|^1\\d$|^20$";

    private final String invalidDataFilePath = "src/main/resources/invalidData.txt";
    private final String sourceFilePath;

    private StringBuilder invalidDataBuilder = new StringBuilder();
    private List<String> correctPositionsList = new ArrayList<>();

    public Validator(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public List<String> getCorrectPositions() throws IOException {

        try(Stream<String> lines = Files.lines(Path.of(sourceFilePath))) {
            lines.forEach(line -> {
                if(isCorrectPosition(line)) {
                    correctPositionsList.add(line);
                } else {
                    invalidDataBuilder.append(line + "\n");
                }
            });
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(invalidDataFilePath))) {
           writer.write(invalidDataBuilder.toString());
        }

        return correctPositionsList;
    }

    private boolean isCorrectPosition(String position) {
        String[] split = position.split(";");
        String id = split[0];
        String name = split[1];
        String price = split[2];
        String count = split[3];

        return id.matches(ID_REGEX)
                && name.matches(NAME_REGEX)
                && price.matches(PRICE_REGEX)
                && count.matches(COUNT_REGEX);
    }

}

