package org.satya.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {

    public static Logger getLogger(Class className) {
        return LogManager.getLogger(className);
    }
}
