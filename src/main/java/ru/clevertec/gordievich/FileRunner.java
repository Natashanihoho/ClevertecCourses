package ru.clevertec.gordievich;

import ru.clevertec.gordievich.service.Interpreter;
import ru.clevertec.gordievich.service.ReceiptService;
import ru.clevertec.gordievich.service.StoreFactory;
import ru.clevertec.gordievich.shop.Store;
import ru.clevertec.gordievich.exceptions.InvalidDataFormat;
import ru.clevertec.gordievich.exceptions.NotEnoughProductsException;
import ru.clevertec.gordievich.exceptions.UnknownIdException;
import ru.clevertec.gordievich.service.InterpreterImpl;

import java.io.*;

public class FileRunner {

    private static final String stockProductsFile = "src/main/resources/stockProducts.txt";
    private static final String receiptFile = "src/main/resources/receipt.txt";
    private static final String orderFile = "src/main/resources/args.txt";

    private static String[] fileArgs;

    public static void main(String[] args) throws IOException, UnknownIdException, NotEnoughProductsException, InvalidDataFormat {
        Store store = StoreFactory.fileStore(stockProductsFile);

        try(BufferedReader reader = new BufferedReader(new FileReader(orderFile))) {
            String argsLine = reader.readLine();
            fileArgs = argsLine.split(" ");
        }
        Interpreter interpreter = new InterpreterImpl();
        String receipt = interpreter.interpret(fileArgs);

        ReceiptService receiptService = rec -> {
            try(FileWriter fileWriter = new FileWriter(receiptFile)) {
                fileWriter.write(rec);
            }
        };
        receiptService.writeReceipt(receipt);

    }
}
