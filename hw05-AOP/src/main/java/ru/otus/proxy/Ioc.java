package ru.otus.proxy;

import ru.otus.logging.TestLoggingInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Ioc {
    private Ioc() {
    }

    public static TestLoggingInterface createTestLoggingClass(Object loggingClass) {
        InvocationHandler handler = new LoggingInvocationHandler(loggingClass);
        return (TestLoggingInterface) Proxy.newProxyInstance(
                Ioc.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }
}