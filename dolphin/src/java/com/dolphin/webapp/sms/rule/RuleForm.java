package com.dolphin.webapp.sms.rule;

import com.dolphin.webapp.common.web.struts.GenericForm;

public class RuleForm extends GenericForm {
	
	private int ruleID;
	
	private String ruletype;
	
	private String rulecode;
	private String province;
	private String city;
	private String serviceprovider;
	private String productcode;
	private String version;
	private String pid;
	
	private String gapdays;
	
	private int interval;	
	private int appID;
	
	
	private float dailychargelimit = 0;
	private float monthlychargelimit = 0;
	
	private String status;
	private String apptype;
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
	public String getGapdays() {
		return gapdays;
	}
	public void setGapdays(String gapdays) {
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
	public String getRuletype() {
		return ruletype;
	}
	public void setRuletype(String ruletype) {
		this.ruletype = ruletype;
	}
	public int getRuleID() {
		return ruleID;
	}
	public void setRuleID(int ruleID) {
		this.ruleID = ruleID;
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
	
}