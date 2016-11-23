package com.myprojects.ci.accesslogparser;

public interface AccessLogParser {
    public void parseAccessLog(String fileName) throws Exception;
}
