package com.dolphin.webapp.vo;

import java.util.Calendar;

import com.dolphin.common.vo.DOLValueObject;

public class AccessResponse implements DOLValueObject{
	
	private String ismi;
	
	private int scriptID;
	private int ruleID;
	private int appID;
    private java.util.Date responseTime = Calendar.getInstance().getTime();
    private String responseStatus;
    
	private int inteval = 14 * 24 * 60; //2 weeks 
	private String scriptContent = "";
	private byte [] appStream = null;
	
	public String getIsmi() {
		return ismi;
	}

	public void setIsmi(String ismi) {
		this.ismi = ismi;
	}

	public int getScriptID() {
		return scriptID;
	}

	public void setScriptID(int scriptID) {
		this.scriptID = scriptID;
	}

	public int getAppID() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}

	public java.util.Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(java.util.Date responseTime) {
		this.responseTime = responseTime;
	}

	public String getScriptContent() {
		return scriptContent;
	}

	public void setScriptContent(String scriptContent) {
		this.scriptContent = scriptContent;
	}

	public byte [] getAppStream() {
		return appStream;
	}
	public void setAppStream(byte [] appStream) {
		this.appStream = appStream;
	}
	public int getInteval() {
		return inteval;
	}

	public void setInteval(int inteval) {
		this.inteval = inteval;
	}

	public int getRuleID() {
		return ruleID;
	}

	public void setRuleID(int ruleID) {
		this.ruleID = ruleID;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

}
