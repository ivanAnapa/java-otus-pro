package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.utils.RunnerUtils;

import java.lang.reflect.Method;
import java.util.Objects;

public class Runner {
    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    private int countOfExecuted;
    private int countOfPassed;
    private int countOfFailed;

    public void run(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        Method[] methods = Objects.requireNonNull(clazz).getMethods();

        Method before = RunnerUtils.getMethodByAnnotation(methods, Before.class);
        Method[] tests = RunnerUtils.getFilteredMethodsByAnnotation(methods, Test.class);
        Method after = RunnerUtils.getMethodByAnnotation(methods, After.class);

        for (var test : tests) {
            execute(createInstance(clazz), before, test, after);
        }

        RunnerUtils.printResults(countOfExecuted, countOfPassed, countOfFailed);
    }

    private <T> T createInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            logger.error("Instance creation is failed", e);
        }
        return null;
    }

    private <T> void execute(T obj, Method before, Method test, Method after) {
        try {
            if (Objects.nonNull(before)) {
                invokeSimple(before, obj);
                invokeWithCounter(test, obj);
            }
        } catch (Exception e) {
            logger.error("Execution failed", e);
        } finally {
            if (Objects.nonNull(after)) invokeSimple(after, obj);
            countOfExecuted++;
        }
    }

    private <T> void invokeSimple(Method method, T obj) {
        try {
            method.invoke(obj);
        } catch (Exception e) {
            logger.error("Simple invocation failed", e);
            System.out.println(e.getCause().getMessage());
        }
    }

    private <T> void invokeWithCounter(Method method, T obj) {
        try {
            method.invoke(obj);
            countOfPassed++;
        } catch (Exception e) {
            countOfFailed++;
            logger.error("Invocation with counter failed", e);
            System.out.println(e.getCause().getMessage());
        }
    }
}