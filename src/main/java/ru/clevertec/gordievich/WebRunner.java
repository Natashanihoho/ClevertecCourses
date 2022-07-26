package ru.clevertec.gordievich;

import ru.clevertec.gordievich.api.ConstantHttp;
import ru.clevertec.gordievich.api.Controller;
import ru.clevertec.gordievich.api.Handler;
import ru.clevertec.gordievich.api.Server;

import java.util.HashMap;
import java.util.Map;

public class WebRunner {

    public static void main(String[] args) {
        Map<String, Handler> handlers = new HashMap<>();
        handlers.put(ConstantHttp.UrlPath.CHECK_PATH, new Handler(new Controller()));
        Server server = new Server(handlers);
        server.start();
    }
}
