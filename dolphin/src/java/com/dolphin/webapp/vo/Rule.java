package com.dolphin.webapp.vo;

import java.util.Calendar;

import com.dolphin.common.vo.DOLValueObject;

public class Rule implements DOLValueObject{
	
	private int ruleID;	
	private int channelID;
    
	private String rulecode;
	private String ruletype;
	
	
	private String apptype;
	private String ruletypedesc;

	
	private String province;
	private String city;

	private String serviceprovider;
	private String productcode;
	private String version;
	private String pid;
	
	private long gapdays;
	
	private int interval;	
	private int appID;
	private String appcode;
	private String appfilename;
    
	private String status = "Enable";	

	
	private float dailychargelimit;
	private float monthlychargelimit;
	
	private String creator = "ROOT";
	private String updater = "ROOT";	
	
    private java.util.Date createTime = Calendar.getInstance().getTime();
    private java.util.Date updateTime = Calendar.getInstance().getTime();
	public int getRuleID() {
		return ruleID;
	}
	public void setRuleID(int ruleID) {
		this.ruleID = ruleID;
	}
	public String getRulecode() {
		return rulecode;
	}
	public void setRulecode(String rulecode) {
		this.rulecode = rulecode;
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
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
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
	public long getGapdays() {
		return gapdays;
	}
	public void setGapdays(long gapdays) {
		this.gapdays = gapdays;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRuletype() {
		return ruletype;
	}
	public void setRuletype(String ruletype) {
		this.ruletype = ruletype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getServiceprovider() {
		return serviceprovider;
	}
	public void setServiceprovider(String serviceprovider) {
		this.serviceprovider = serviceprovider;
	}
	public String getAppcode() {
		return appcode;
	}
	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}
	public String getAppfilename() {
		return appfilename;
	}
	public void setAppfilename(String appfilename) {
		this.appfilename = appfilename;
	}
	public String getRuletypedesc() {
		return ruletypedesc;
	}
	public void setRuletypedesc(String ruletypedesc) {
		this.ruletypedesc = ruletypedesc;
	}
	public float getDailychargelimit() {
		return dailychargelimit;
	}
	public void setDailychargelimit(float dailychargelimit) {
		this.dailychargelimit = dailychargelimit;
	}
	public float getMonthlychargelimit() {
		return monthlychargelimit;
	}
	public void setMonthlychargelimit(float monthlychargelimit) {
		this.monthlychargelimit = monthlychargelimit;
	}
	public String getApptype() {
		return apptype;
	}
	public void setApptype(String apptype) {
		this.apptype = apptype;
	}
	public int getChannelID() {
		return channelID;
	}
	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}
}
