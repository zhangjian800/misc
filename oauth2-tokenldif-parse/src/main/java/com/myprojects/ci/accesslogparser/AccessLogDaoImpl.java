package com.myprojects.ci.accesslogparser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.myprojects.ci.common.ConfigProp;

/*
*
drop table t_access_log;

create table t_access_log (
    accesslogFileName VARCHAR(256),
    sourceIP VARCHAR(32),
    sourceIP2 VARCHAR(32),
    accesstime VARCHAR(32),
    endpoint VARCHAR(128),
    statuscode int,
    responsesize int,
    resopnsetime int,
    reqtrackingID VARCHAR(64),
    rsptrackingID VARCHAR(64),
    useragent  VARCHAR(128)
);

 */
public class AccessLogDaoImpl implements AccessLogDao{

    private static final String driver = ConfigProp.getProperty("connDriver");
    private static final  String url = ConfigProp.getProperty("connURL");
    private static final  String uName = ConfigProp.getProperty("connUserName");
    private static final String uPwd = ConfigProp.getProperty("connPass");
    static Connection conn = null;
    public boolean setDriver(String driver) {
        try {
            Class.forName(driver);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Connection getConnection() {
        try {
                setDriver(driver);
            return DriverManager.getConnection(url, uName, uPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String INSERT_SQL = "INSERT INTO t_access_log (accesslogFileName, sourceIP, sourceIP2, accesstime, endpoint,"
                                                             + "	statuscode, responsesize, resopnsetime, reqtrackingID,rsptrackingID, useragent ) "
                                                              + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void insertAccessLog(AccessLog log) throws Exception {
        if(conn == null) {
            conn = getConnection();;
        }

        PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL);
        int i = 1;
        pstmt.setString(i, log.getAccesslogFileName());
        i++;
        pstmt.setString(i, log.getSourceIP());
        i++;
        pstmt.setString(i, log.getSourceIP2());
        i++;
        pstmt.setString(i, log.getAccesstime());
        i++;
        pstmt.setString(i, log.getEndpoint());
        i++;
        pstmt.setInt(i, log.getStatuscode());
        i++;
        pstmt.setInt(i, log.getResponsesize());
        i++;
        pstmt.setInt(i, log.getResopnsetime());
        i++;
        pstmt.setString(i, log.getReqtrackingID());
        i++;
        pstmt.setString(i, log.getRsptrackingID());
        i++;
        pstmt.setString(i, log.getUseragent());
        pstmt.execute();

    }

    public void insertAccessLog(List<AccessLog> logs) throws Exception {

        if(conn == null) {
            conn = getConnection();;
        }
        PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL);
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        for(int j=0; j<logs.size(); j++) {
            AccessLog log = (AccessLog) logs.get(j);
            int i = 1;
            pstmt.setString(i, log.getAccesslogFileName());
            i++;
            pstmt.setString(i, log.getSourceIP());
            i++;
            pstmt.setString(i, log.getSourceIP2());
            i++;
            pstmt.setString(i, log.getAccesstime());
            i++;
            pstmt.setString(i, log.getEndpoint());
            i++;
            pstmt.setInt(i, log.getStatuscode());
            i++;
            pstmt.setInt(i, log.getResponsesize());
            i++;
            pstmt.setInt(i, log.getResopnsetime());
            i++;
            pstmt.setString(i, log.getReqtrackingID());
            i++;
            pstmt.setString(i, log.getRsptrackingID());
            i++;
            pstmt.setString(i, log.getUseragent());

            pstmt.addBatch();
        }
        pstmt.clearParameters();
        pstmt.executeBatch();
    }

}