package com.dolphin.common.log;

import org.apache.commons.logging.Log;

/**
 * An extended Log infterface that adds to new levels to the logging system
 *          PERF  - New Level added to dump performance numbers
 *          QPERF - New Level add to dump SQL Queries performance numbers
 */
public interface DolpiLog extends Log{
    /**
     * Checks if the "PERF" level is enabled.
     *
     * @return
     */
    public boolean isPerfEnabled();
    /**
     * Logs "PERF" level messages
     *
     * @param message
     */
    public void perf(java.lang.Object message);

    /**
     * Logs "PERF" level messages
     *
     * @param o
     * @param throwable
     */
    public void perf(Object o, Throwable throwable);

    /**
     * Checks if the "QUERY_PERF" level is enabled.
     *
     * @return
     */
    public boolean isQueryPerfEnabled();

    /**
     * Logs "QUERY_PERF" level messages
     *
     * @param message
     */
    public void queryPerf(java.lang.Object message);

    /**
     * Logs "PERF" level messages
     *
     * @param o
     * @param throwable
     */
    public void queryPerf(Object o, Throwable throwable);

    /**
     * Checks if the "VERBOSE" level is enabled.
     *
     * @return
     */
    public boolean isVerboseEnabled();

    /**
     * Logs "VERBOSE" level messages
     *
     * @param message
     */
    public void verbose(java.lang.Object message);

    /**
     * Logs "VERBOSE" level messages
     *
     * @param o
     * @param throwable
     */
    public void verbose(Object o, Throwable throwable);

}
