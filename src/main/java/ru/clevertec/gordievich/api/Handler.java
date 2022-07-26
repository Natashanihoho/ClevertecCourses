package ru.clevertec.gordievich.api;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public record Handler(Controller controller) {

    public void handle(final HttpExchange httpExchange) {
        try {
            controller.execute(httpExchange);
        } catch (final Exception e) {
            handleException(httpExchange);
        } finally {
            httpExchange.close();
        }
    }

    private void handleException(final HttpExchange httpExchange) {
        try {
            httpExchange.sendResponseHeaders(ConstantHttp.HttpResponseStatus.STATUS_NOT_FOUND, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
