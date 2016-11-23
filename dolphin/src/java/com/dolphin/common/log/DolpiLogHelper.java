package com.dolphin.common.log;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.net.SyslogAppender;
import org.apache.commons.logging.Log;

import java.util.Calendar;


/**
 * Created by IntelliJ IDEA.
 * User: VSingh
 * Date: May 17, 2006
 * Time: 10:56:26 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * A Log Helper class that holds the newly define Levels
 */
public class DolpiLogHelper {
    /** String names of the new levels */
    private static final String PERF = "PERF";
    private static final String QPERF = "QPERF";
    private static final String VERBOSE = "VERBOSE";


    /** The new Level objects */
    public static final Level PERF_LEVEL = new WbxLevel(Priority.DEBUG_INT + 10, PERF, SyslogAppender.LOG_LOCAL0);
    public static final Level QPERF_LEVEL = new WbxLevel(Priority.DEBUG_INT + 20, QPERF, SyslogAppender.LOG_LOCAL0);
    public static final Level VERBOSE_LEVEL = new WbxLevel(Priority.DEBUG_INT + 30, VERBOSE, SyslogAppender.LOG_LOCAL0);


    /**
     * Returns the Level object (PERF_LEVEL or QPERF_LEVEL) based on the
     * string name passed to this method. If the passed name is not that of
     * PERF or QPERF levels then the default level is returned
     *
     * @param name The name of the Level
     * @param defaultLevel The default Level to return if desired Level is not found
     * @return
     */
    public static Level toLevel(String name, Level defaultLevel){
        if(name.equalsIgnoreCase(PERF)) {
            return PERF_LEVEL;
        } else if(name.equalsIgnoreCase(QPERF)) {
            return QPERF_LEVEL;
        } else if (name.equalsIgnoreCase(VERBOSE)){
            return VERBOSE_LEVEL;
        } else
            return defaultLevel;
    }

    /** The extended Level class */
    private static class WbxLevel extends Level {
        private WbxLevel(int level, String name, int sysLogLevel) {
            super(level, name, sysLogLevel);
        }
    }

    /**
     * Utility method to check id the QPERF level is enabled. This method
     * checks if the instance of the logger is WbxLog, if so checks for
     * for the QPERF level. If the instance is not  WbxLog it checks for
     * the debug level and returns
     *
     * @param logger
     * @return
     */
    public static boolean isQueryPerfEanabled(Log logger){
        if(logger instanceof DolpiLog){
            return ((DolpiLog)logger).isQueryPerfEnabled();
        }
        if(logger.isWarnEnabled()){
            logger.warn("The Logger was NOT set to com.webex.infraclient.log.WbxLogger!");
        }
        return logger.isDebugEnabled();
    }

    /**
     * Utility method to log QPERF mesages. This method checks
     * the logger for the instance of WbxLog, if so it logs the
     * message as QPERF else it logs it as DEBUG with the word
     * "QPERF" in the message.
     *
     * @param logger
     * @param message
     */
    public static void queryPerf(Log logger, String message){
        if(logger instanceof DolpiLog){
            if(((DolpiLog)logger).isQueryPerfEnabled()){
                ((DolpiLog)logger).queryPerf(message);
            }
        } else {
            if(logger.isDebugEnabled()){
                logger.debug(QPERF + " " + message);
            }
        }
    }

    /**
     * Utility method to check id the VERBOSE level is enabled. This method
     * checks if the instance of the logger is WbxLog, if so checks for
     * for the VERBOSE level. If the instance is not  WbxLog it checks for
     * the debug level and returns
     *
     * @param logger
     * @return
     */
    public static boolean isVerbosePerfEanabled(Log logger){
        if(logger instanceof DolpiLog){
            return ((DolpiLog)logger).isVerboseEnabled();
        }
        if(logger.isWarnEnabled()){
            logger.warn("The Logger was NOT set to com.webex.infraclient.log.WbxLogger!");
        }
        return logger.isDebugEnabled();
    }

    /**
     * Utility method to log VERBOSE mesages. This method checks
     * the logger for the instance of WbxLog, if so it logs the
     * message as QPERF else it logs it as DEBUG with the word
     * "QPERF" in the message.
     *
     * @param logger
     * @param message
     */
    public static void verbosePerf(Log logger, String message){
        if(logger instanceof DolpiLog){
            if(((DolpiLog)logger).isVerboseEnabled()){
                ((DolpiLog)logger).verbose(message);
            }
        } else {
            if(logger.isDebugEnabled()){
                logger.debug(VERBOSE + " " + message);
            }
        }
    }

    /**
     * Utility method to check id the PERF level is enabled. This method
     * checks if the instance of the logger is WbxLog, if so checks for
     * for the PERF level. If the instance is not  WbxLog it checks for
     * the debug level and returns
     *
     * @param logger
     * @return
     */
    public static boolean isPerfEanabled(Log logger){
        if(logger instanceof DolpiLog){
            return ((DolpiLog)logger).isPerfEnabled();
        }
        if(logger.isWarnEnabled()){
            logger.warn("The Logger was NOT set to com.webex.infraclient.log.WbxLogger!");
        }
        return logger.isDebugEnabled();
    }


    /**
     * Utility method to log PERF mesages. This method checks
     * the logger for the instance of WbxLog, if so it logs the
     * message as PERF else it logs it as DEBUG  with the word
     * "PERF" in the message.
     *
     * @param logger
     * @param message
     */
    public static void perf(Log logger, String message){
        if(logger instanceof DolpiLog){
            if(((DolpiLog)logger).isPerfEnabled()){
                ((DolpiLog)logger).perf(message);
            }
        } else {
            if(logger.isDebugEnabled()){
                logger.debug(PERF + " " + message);
            }
        }
    }
}
