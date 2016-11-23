package com.myprojects.ci.accesslogparser;

import java.util.List;


public interface AccessLogDao {
    public void insertAccessLog(AccessLog log) throws Exception;
    public void insertAccessLog(List<AccessLog> logs) throws Exception;

}
