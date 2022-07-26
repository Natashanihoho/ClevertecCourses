package ru.clevertec.gordievich.infrastructure.exceptions;

public class NotEnoughProductsException extends RuntimeException {

    public NotEnoughProductsException(String message) {
        super(message);
    }

}
