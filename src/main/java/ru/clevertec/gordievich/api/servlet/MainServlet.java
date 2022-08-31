package ru.clevertec.gordievich.api.servlet;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import ru.clevertec.gordievich.api.servlet.handling.DispatcherServlet;


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
        resp.setContentType(APPLICATION_JSON_VALUE);
        dispatcherServlet.accept(req, resp);
    }


}
