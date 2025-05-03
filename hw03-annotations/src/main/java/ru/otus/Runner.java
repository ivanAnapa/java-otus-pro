package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.utils.RunnerUtils;

import java.lang.reflect.Method;
import java.util.Objects;

public class Runner {

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
            execute(getInstance(clazz), before, test, after);
        }

        RunnerUtils.printResults(countOfExecuted, countOfPassed, countOfFailed);
    }

    private <T> T getInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> void execute(T obj, Method before, Method test, Method after) {
        try {
            if (Objects.nonNull(before)) invokeSimple(before, obj);
            invokeWithCounter(test, obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(after)) invokeSimple(after, obj);
            countOfExecuted++;
        }
    }

    private <T> void invokeSimple(Method method, T obj) {
        try {
            method.invoke(obj);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }
    }

    private <T> void invokeWithCounter(Method method, T obj) {
        try {
            method.invoke(obj);
            countOfPassed++;
        } catch (Exception e) {
            countOfFailed++;
            System.out.println(e.getCause().getMessage());
        }
    }
}