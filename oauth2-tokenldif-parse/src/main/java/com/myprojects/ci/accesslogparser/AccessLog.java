package com.myprojects.ci.accesslogparser;

import java.util.Set;

public class AccessLog {
    private String  accesslogFileName = null;

    private String  sourceIP = null;
    private String  sourceIP2 = null;
    private String  accesstime = null;
    private String  endpoint = null;
    private int statuscode;
    private int responsesize;
    private int resopnsetime;
    private String reqtrackingID = null;
    private String rsptrackingID = null;
    private String  useragent = null;

    public String getAccesslogFileName() {
        return accesslogFileName;
    }
    public void setAccesslogFileName(String accesslogFileName) {
        this.accesslogFileName = accesslogFileName;
    }
    public String getSourceIP() {
        return sourceIP;
    }
    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }
    public String getSourceIP2() {
        return sourceIP2;
    }
    public void setSourceIP2(String sourceIP2) {
        this.sourceIP2 = sourceIP2;
    }
    public String getAccesstime() {
        return accesstime;
    }
    public void setAccesstime(String accesstime) {
        this.accesstime = accesstime;
    }
    public String getEndpoint() {
        return endpoint;
    }
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    public int getStatuscode() {
        return statuscode;
    }
    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }
    public int getResponsesize() {
        return responsesize;
    }
    public void setResponsesize(int responsesize) {
        this.responsesize = responsesize;
    }
    public int getResopnsetime() {
        return resopnsetime;
    }
    public void setResopnsetime(int resopnsetime) {
        this.resopnsetime = resopnsetime;
    }
    public String getReqtrackingID() {
        return reqtrackingID;
    }
    public void setReqtrackingID(String reqtrackingID) {
        this.reqtrackingID = reqtrackingID;
    }
    public String getRsptrackingID() {
        return rsptrackingID;
    }
    public void setRsptrackingID(String rsptrackingID) {
        this.rsptrackingID = rsptrackingID;
    }
    public String getUseragent() {
        return useragent;
    }
    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }
}
