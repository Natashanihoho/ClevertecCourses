package ru.clevertec.gordievich.proxy;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.clevertec.gordievich.service.Interpreter;
import ru.clevertec.gordievich.service.InterpreterImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InterpreterInvocationHandler implements InvocationHandler {

    private static final String EMPTY_STRING = "";
    private static final String INTERPRET = "interpret";

    private static final Logger logger = LoggerFactory.getLogger(InterpreterImpl.class);

    private final Interpreter interpreter;

    public InterpreterInvocationHandler(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Gson parser = new Gson();

        Object invoke = method.invoke(interpreter, args);

        if (method.getName().equals(INTERPRET)) {
            String arguments = EMPTY_STRING;
            if (args != null) {
                arguments = parser.toJson(args);
            }
            String result = EMPTY_STRING;
            if (invoke != null) {
                //result = parser.toJson(invoke);
                result = (String) invoke;
            }

            logger.info("{} args={}", method.getName(), arguments);
            logger.info("{} result={}", method.getName(), result);
        }

        return invoke;
    }
}
