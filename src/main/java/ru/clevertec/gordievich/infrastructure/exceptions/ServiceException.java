package ru.clevertec.gordievich.infrastructure.exceptions;

public class ServiceException extends RuntimeException {

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
