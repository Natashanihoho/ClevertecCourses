package ru.clevertec.gordievich.api.servlet.handling;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

public interface ServiceConsumer {

    void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

    RequestType requestType();

}
