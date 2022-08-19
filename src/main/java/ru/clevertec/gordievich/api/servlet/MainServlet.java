package ru.clevertec.gordievich.api.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.gordievich.api.servlet.handling.DispatcherServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@RequiredArgsConstructor
public class MainServlet extends HttpServlet {

    private final DispatcherServlet dispatcherServlet;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        router(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        router(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        router(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        router(req, resp);
    }

    private void router(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/json");
        dispatcherServlet.accept(req, resp);
    }


}
