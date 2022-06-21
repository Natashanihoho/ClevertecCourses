package ru.clevertec.gordievich.proxy;

import ru.clevertec.gordievich.exceptions.InvalidDataFormat;
import ru.clevertec.gordievich.exceptions.NotEnoughProductsException;
import ru.clevertec.gordievich.exceptions.UnknownIdException;
import ru.clevertec.gordievich.service.Interpreter;
import ru.clevertec.gordievich.service.InterpreterImpl;

import java.lang.reflect.Proxy;

public class InterpreterProxy implements Interpreter {

    private static Interpreter interpreterProxy;

    public InterpreterProxy() {
    }

    static {
        interpreterProxy = new InterpreterImpl();
        var classLoader = interpreterProxy.getClass().getClassLoader();
        var interfaces = interpreterProxy.getClass().getInterfaces();
        interpreterProxy = (Interpreter) Proxy.newProxyInstance(classLoader, interfaces, new InterpreterInvocationHandler(interpreterProxy));
    }

    @Override
    public String interpret(String[] args) throws UnknownIdException, NotEnoughProductsException, InvalidDataFormat {
        return interpreterProxy.interpret(args);
    }
}
