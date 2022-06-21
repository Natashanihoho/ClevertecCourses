package ru.clevertec.gordievich;

import ru.clevertec.gordievich.exceptions.InvalidDataFormat;
import ru.clevertec.gordievich.exceptions.NotEnoughProductsException;
import ru.clevertec.gordievich.exceptions.UnknownIdException;
import ru.clevertec.gordievich.service.Interpreter;
import ru.clevertec.gordievich.service.InterpreterImpl;
import ru.clevertec.gordievich.service.ReceiptService;
import ru.clevertec.gordievich.service.StoreFactory;
import ru.clevertec.gordievich.shop.Store;
import ru.clevertec.gordievich.util.PropertiesUtil;
import ru.clevertec.gordievich.util.Validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileRunner {

    private static String[] fileArgs;

    public static void main(String[] args) throws IOException, UnknownIdException, NotEnoughProductsException, InvalidDataFormat {

        Validator validator = new Validator(PropertiesUtil.get("STOCK_PRODUCTS_FILE_PATH"));
        Store store = StoreFactory.fileStore(validator.getCorrectPositions());

        try(BufferedReader reader = new BufferedReader(new FileReader(PropertiesUtil.get("ORDER_FILE_PATH")))) {
            String argsLine = reader.readLine();
            fileArgs = argsLine.split(" ");
        }
        Interpreter interpreter = new InterpreterImpl();
        String receipt = interpreter.interpret(fileArgs);

// for proxy clas:
//        Interpreter interpreterProxy = new InterpreterProxy();
//        String receipt = interpreterProxy.interpret(fileArgs);

        ReceiptService receiptService = rec -> {
            try(FileWriter fileWriter = new FileWriter(PropertiesUtil.get("RECEIPT_FILE_PATH"))) {
                fileWriter.write(rec);
            }
        };
        receiptService.writeReceipt(receipt);
    }
}


