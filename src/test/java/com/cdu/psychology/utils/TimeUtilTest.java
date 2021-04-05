package com.cdu.psychology.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimeUtilTest {
    Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public void getTime(){
        logger.info(TimeUtil.getTimestamp());
    }
}
