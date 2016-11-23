package com.dolphin.common.log;

import org.apache.commons.logging.impl.Log4JLogger;

/**
 * WbxLogger that handles 2 new level (apart from the standard Log4J level).
 * The new level hierarchy is
 *      DEBUG - highest Log4J level
 *          PERF  - New Level added to dump performance numbers
 *          QPERF - New Level add to dump SQL Queries performance numbers
 *          VERBOSE - New Level add to dump verbose messages
 *      INFO  - Standard Log4J level
 *      WARN  - Standard Log4J level
 *      ERROR - Standard Log4J level
 *      FATAL - Standard Log4J level
 *
 * Basically this class forwards all the calls the Log4JLogger from commons logging,
 * it only handles the levels PERF and QPERF by making direct calls into Log4J
 */
public class DolpiLogger implements DolpiLog {
    /** Log4J logger from commons logging */
    private Log4JLogger logger;

    /**
     * Default constructor
     */
    public DolpiLogger() {
        this.logger = new Log4JLogger();
    }

    /**
     * Constructor
     *
     * @param name Name of the Logger
     */
    public DolpiLogger(java.lang.String name){
        this.logger = new Log4JLogger(name);
    }

    /**
     * Constructor
     *
     * @param logger An instance of Log4J logger
     */
    public DolpiLogger(org.apache.log4j.Logger logger){
        this.logger = new Log4JLogger(logger);
    }

    /**
     * Checks if the "PERF" level is enabled.
     *
     * @return
     */
    public boolean isPerfEnabled() {
        return logger.getLogger().getEffectiveLevel().toInt() <= DolpiLogHelper.PERF_LEVEL.toInt();
    }

    /**
     * Logs "PERF" level messages
     *
     * @param message
     */
    public void perf(Object message) {
        logger.getLogger().log(DolpiLogHelper.PERF_LEVEL, message);
    }


    /**
     * Logs "PERF" level messages
     *
     * @param o
     * @param throwable
     */
    public void perf(Object o, Throwable throwable) {
        logger.getLogger().log(DolpiLogHelper.PERF_LEVEL, o, throwable);
    }

    /**
     * Checks if the "QPERF" level is enabled.
     *
     * @return
     */
    public boolean isQueryPerfEnabled(){
        return logger.getLogger().getEffectiveLevel().toInt() <= DolpiLogHelper.QPERF_LEVEL.toInt();
    }
    /**
     * Logs "QPERF" level messages
     *
     * @param message
     */
    public void queryPerf(java.lang.Object message){
        logger.getLogger().log(DolpiLogHelper.QPERF_LEVEL, message);
    }

    /**
     * Logs "QPERF" level messages
     *
     * @param o
     * @param throwable
     */
    public void queryPerf(Object o, Throwable throwable) {
        logger.getLogger().log(DolpiLogHelper.QPERF_LEVEL, o, throwable);
    }

    /**
     * Checks if the "VERBOSE" level is enabled.
     *
     * @return
     */
    public boolean isVerboseEnabled(){
        return logger.getLogger().getEffectiveLevel().toInt() <= DolpiLogHelper.VERBOSE_LEVEL.toInt();
    }
    /**
     * Logs "VERBOSE" level messages
     *
     * @param message
     */
    public void verbose(java.lang.Object message){
        logger.getLogger().log(DolpiLogHelper.VERBOSE_LEVEL, message);
    }

    /**
     * Logs "VERBOSE" level messages
     *
     * @param o
     * @param throwable
     */
    public void verbose(Object o, Throwable throwable) {
        logger.getLogger().log(DolpiLogHelper.VERBOSE_LEVEL, o, throwable);
    }

    /**
     * Checks if the "DEBUG" level is enabled.
     *
     * @return
     */
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * Checks if the "ERROR" level is enabled.
     *
     * @return
     */
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    /**
     * Checks if the "FATAL" level is enabled.
     *
     * @return
     */
    public boolean isFatalEnabled() {
        return logger.isFatalEnabled();
    }

    /**
     * Checks if the "INFO" level is enabled.
     *
     * @return
     */
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * Checks if the "TRACE" level is enabled.
     *
     * @return
     */
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /**
     * Checks if the "WARN" level is enabled.
     *
     * @return
     */
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    /**
     * Logs "TRACE" level messages
     *
     * @param o
     */
    public void trace(Object o) {
        logger.trace(o);
    }

    /**
     * Logs "TRACE" level messages
     *
     * @param o
     * @param throwable
     */
    public void trace(Object o, Throwable throwable) {
        logger.trace(o, throwable);
    }

    /**
    * Logs "DEBUG" level messages
    *
    * @param o
    */
    public void debug(Object o) {
        logger.debug(o);
    }

   /**
    * Logs "DEBUG" level messages
    *
    * @param o
    * @param throwable
    */
    public void debug(Object o, Throwable throwable) {
        logger.debug(o, throwable);
    }

    /**
     * Logs "INFO" level messages
     *
     * @param o
     */
    public void info(Object o) {
        logger.info(o);
    }

    /**
     * Logs "INFO" level messages
     *
     * @param o
     * @param throwable
     */
    public void info(Object o, Throwable throwable) {
        logger.info(o, throwable);
    }

    /**
     * Logs "WARN" level messages
     *
     * @param o
     */
    public void warn(Object o) {
        logger.warn(o);
    }

    /**
     * Logs "WARN" level messages
     *
     * @param o
     * @param throwable
     */
    public void warn(Object o, Throwable throwable) {
        logger.warn(o, throwable);
    }

    /**
     * Logs "ERROR" level messages
     *
     * @param o
     */
    public void error(Object o) {
        logger.error(o);
    }

    /**
     * Logs "ERROR" level messages
     *
     * @param o
     * @param throwable
     */
    public void error(Object o, Throwable throwable) {
        logger.error(o, throwable);
    }

    /**
     * Logs "FATAL" level messages
     *
     * @param o
     */
    public void fatal(Object o) {
        logger.fatal(o);
    }

    /**
     * Logs "FATAL" level messages
     *
     * @param o
     * @param throwable
     */
    public void fatal(Object o, Throwable throwable) {
        logger.fatal(o, throwable);
    }


}
