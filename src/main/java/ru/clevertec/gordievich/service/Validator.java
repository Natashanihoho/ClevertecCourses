package ru.clevertec.gordievich.service;

import java.io.*;

public class Validator {

    private static final String ID_REGEX = "^[1-9][0-9]?$|^100$";
    private static final String NAME_REGEX = "^[A-Z][a-z]{2,29}$";
    private static final String PRICE_REGEX = "^[1-9][0-9]?\\.[0-9][0-9]$|^100.00$";
    private static final String COUNT_REGEX = "^[1-9]$|^1[0-9]$|^20$";

    private final String invalidDataFile = "src/main/resources/invalidData.txt";
    private final String validDataFile = "src/main/resources/validData.txt";
    private final String sourceFile;

    private StringBuilder validDataBuilder = new StringBuilder();
    private StringBuilder invalidDataBuilder = new StringBuilder();

    public Validator(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String checkData() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            while (reader.ready()) {
                String line = reader.readLine();
                System.out.println(line);
                System.out.println(isCorrectPosition(line));
                if(isCorrectPosition(line)) {
                    validDataBuilder.append(line + "\n");
                } else {
                    invalidDataBuilder.append(line + "\n");
                }
            }
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(validDataFile))) {
            writer.write(validDataBuilder.toString());
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(invalidDataFile))) {
            writer.write(invalidDataBuilder.toString());
        }

        return validDataFile;
    }

    public boolean isCorrectPosition(String position) {
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

