package ru.otus.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RunnerUtils {

    public static Method getMethodByAnnotation(final Method[] methods, Class<? extends Annotation> annotation) {
        var filteredMethods = getFilteredMethodsByAnnotation(methods, annotation);
        return Arrays.stream(filteredMethods).findFirst().orElse(null);
    }

    public static Method[] getFilteredMethodsByAnnotation(
            Method[] methods,
            Class<? extends Annotation> annotation) {
        return Arrays.stream(methods).filter(m -> m.isAnnotationPresent(annotation)).toArray(Method[]::new);
    }

    public static void printResults(int executed, int passed, int failed) {
        System.out.println("****** Tests execution is done ******");
        System.out.printf("Results:\n - Executed: %d\n - Passed: %d\n - Failed: %d\n", executed, passed, failed);
    }
}