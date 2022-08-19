package ru.clevertec.gordievich.api.servlet.handling;

import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServiceConsumer {

    void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

    RequestType requestType();

}
