package ru.otus;

import ru.otus.logging.TestLogging;
import ru.otus.logging.TestLoggingInterface;
import ru.otus.proxy.Ioc;

public class Demo {
    public static void main(String[] args) {

        TestLoggingInterface testLogging = Ioc.createTestLoggingClass(new TestLogging());
        testLogging.calculation(1);
        testLogging.calculation(1, 2);
        testLogging.calculation(1, 2, "Text example");
    }

    /* Вывод в консоль:
> Task :hw05-AOP:ru.otus.Demo.main()
16:01:30.681 [main] INFO ru.otus.proxy.LoggingInvocationHandler -- executed method: calculation, param: [1]
16:01:30.686 [main] INFO ru.otus.logging.TestLogging -- calc for 1 parameter: 1
16:01:30.686 [main] INFO ru.otus.proxy.LoggingInvocationHandler -- executed method: calculation, param: [1, 2]
16:01:30.690 [main] INFO ru.otus.logging.TestLogging -- calc for 2 params: 1, 2
16:01:30.691 [main] INFO ru.otus.proxy.LoggingInvocationHandler -- executed method: calculation, param: [1, 2, Text example]
16:01:30.692 [main] INFO ru.otus.logging.TestLogging -- calc for 3 params: 1, 2, Text example
     */
}