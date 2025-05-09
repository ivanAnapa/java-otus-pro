package ru.otus.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class LoggingInvocationHandler implements InvocationHandler {
    public static final Logger logger = LoggerFactory.getLogger(LoggingInvocationHandler.class);
    private final Object loggingClass;

    LoggingInvocationHandler(Object loggingClass) {
        this.loggingClass = loggingClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("executed method: {}, param: {}", method.getName(), args);
        return method.invoke(loggingClass, args);
    }
}