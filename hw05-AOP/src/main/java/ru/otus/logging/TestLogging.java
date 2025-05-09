package ru.otus.logging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.otus.annotations.Log;

public class TestLogging implements TestLoggingInterface {
    private static final Logger logger = LoggerFactory.getLogger(TestLogging.class);

    @Override
    @Log
    public void calculation(int param1) {
        logger.info("calc for 1 parameter: {}", param1);
    }

    @Override
//    @Log
    public void calculation(int param1, int param2) {
        logger.info("calc for 2 params: {}, {}", param1, param2);
    }

    @Override
    @Log
    public void calculation(int param1, int param2, String param3) {
        logger.info("calc for 3 params: {}, {}, {}", param1, param2, param3);
    }
}
