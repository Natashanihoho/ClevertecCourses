package ru.clevertec.gordievich.infrastructure.exceptions;

public class DaoException extends RuntimeException {

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
