package ru.clevertec.gordievich.service;


import java.io.IOException;

public interface ReceiptService {

    void writeReceipt(String receipt) throws IOException;

}
