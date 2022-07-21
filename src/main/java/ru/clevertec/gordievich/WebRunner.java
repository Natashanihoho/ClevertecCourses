package ru.clevertec.gordievich;

import ru.clevertec.gordievich.service.StoreFactory;
import ru.clevertec.gordievich.shop.Store;
import ru.clevertec.gordievich.web.ConstantHttp;
import ru.clevertec.gordievich.web.Controller;
import ru.clevertec.gordievich.web.Handler;
import ru.clevertec.gordievich.web.Server;

import java.util.HashMap;
import java.util.Map;

public class WebRunner {

    public static void main(String[] args) {

        Store store = StoreFactory.defaultStore();
        Map<String, Handler> handlers = new HashMap<>();
        handlers.put(ConstantHttp.UrlPath.CHECK_PATH, new Handler(new Controller()));
        Server server = new Server(handlers);
        server.start();

    }
}
