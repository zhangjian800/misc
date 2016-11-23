package com.dolphin.webapp.vo;

import java.util.Calendar;

import com.dolphin.common.vo.DOLValueObject;

public class Access implements DOLValueObject{
	
	private int accessID;
	private String imsi;
	private String productType;
	private String version;
	private String pid;
	private String smsc;
	private String lbs;
	private String platformType;
	private int simType;
	
    private java.util.Date accessTime = Calendar.getInstance().getTime();
    private java.util.Date publishDate = null;
    private String accesstimeym = null;
    private String currdate = null;
    
    private long gapdays;
    private int interval = 120;
    
	private String province;
	private String city;

	private String requestStr;
	
	private String responseScriptContent;
	private String responseStatus;
	private int responesAppID;
	
	private String adsdetailID;
	private int ruleID;
	
	public int getAccessID() {
		return accessID;
	}
	public void setAccessID(int accessID) {
		this.accessID = accessID;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getSmsc() {
		return smsc;
	}
	public void setSmsc(String smsc) {
		this.smsc = smsc;
	}
	public String getLbs() {
		return lbs;
	}
	public void setLbs(String lbs) {
		this.lbs = lbs;
	}
	public String getPlatformType() {
		return platformType;
	}
	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}
	public java.util.Date getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(java.util.Date accessTime) {
		this.accessTime = accessTime;
	}
	public long getGapdays() {
		return gapdays;
	}
	public void setGapdays(long gapdays) {
		this.gapdays = gapdays;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public java.util.Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(java.util.Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getResponseScriptContent() {
		return responseScriptContent;
	}
	public void setResponseScriptContent(String responseScriptContent) {
		this.responseScriptContent = responseScriptContent;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public int getResponesAppID() {
		return responesAppID;
	}
	public void setResponesAppID(int responesAppID) {
		this.responesAppID = responesAppID;
	}
	public String getAccesstimeym() {
		return accesstimeym;
	}
	public void setAccesstimeym(String accesstimeym) {
		this.accesstimeym = accesstimeym;
	}
	public String getRequestStr() {
		return requestStr;
	}
	public void setRequestStr(String requestStr) {
		this.requestStr = requestStr;
	}
	public int getSimType() {
		return simType;
	}
	public void setSimType(int simType) {
		this.simType = simType;
	}
	public String getAdsdetailID() {
		return adsdetailID;
	}
	public void setAdsdetailID(String adsdetailID) {
		this.adsdetailID = adsdetailID;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getRuleID() {
		return ruleID;
	}
	public void setRuleID(int ruleID) {
		this.ruleID = ruleID;
	}
	public String getCurrdate() {
		return currdate;
	}
	public void setCurrdate(String currdate) {
		this.currdate = currdate;
	}
}
