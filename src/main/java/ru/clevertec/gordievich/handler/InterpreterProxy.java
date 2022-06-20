package ru.clevertec.gordievich.handler;

import ru.clevertec.gordievich.service.Interpreter;
import ru.clevertec.gordievich.service.InterpreterImpl;

import java.lang.reflect.Proxy;

public final class InterpreterProxy {

    private InterpreterProxy() {
    }

    public static Interpreter getProxyInstance() {
        Interpreter interpreter = new InterpreterImpl();
        var classLoader = interpreter.getClass().getClassLoader();
        var interfaces = interpreter.getClass().getInterfaces();
        return (Interpreter) Proxy.newProxyInstance(classLoader, interfaces, new InterpreterInvocationHandler(interpreter));
    }
}
