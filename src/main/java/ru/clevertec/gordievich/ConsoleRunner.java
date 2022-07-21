package ru.clevertec.gordievich;

import ru.clevertec.gordievich.service.Interpreter;
import ru.clevertec.gordievich.service.ReceiptService;
import ru.clevertec.gordievich.service.StoreFactory;
import ru.clevertec.gordievich.shop.Store;
import ru.clevertec.gordievich.exceptions.InvalidDataFormat;
import ru.clevertec.gordievich.exceptions.NotEnoughProductsException;
import ru.clevertec.gordievich.exceptions.UnknownIdException;
import ru.clevertec.gordievich.service.InterpreterImpl;

import java.io.IOException;
import java.util.Optional;

public class ConsoleRunner {

    public static void main(String[] args) throws UnknownIdException, NotEnoughProductsException, IOException, InvalidDataFormat {
        Store store = StoreFactory.defaultStore();
        Interpreter interpreter = new InterpreterImpl();
        String receipt = interpreter.interpret(args);

        ReceiptService receiptService = rec -> System.out.println(rec);
        receiptService.writeReceipt(receipt);


    }

}
