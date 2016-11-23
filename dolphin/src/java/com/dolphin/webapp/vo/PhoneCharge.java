package com.dolphin.webapp.vo;

import com.dolphin.common.vo.DOLValueObject;

public class PhoneCharge implements DOLValueObject{
	
	private long chargeID;
	
	private String mobile;
	private String linkid;
	private String receivetimaccessID;
	private String channelID;
	private String ruleIDe String receivetime;
	private String content;
	
	private String imsi;
	private String producttype;
	private String version;
	private String province;
	
	private String private String city;
	private String result;
	private String yearmonth;
	private String currdate;
	private float failmountutil.Date updatetime = java.util.Calendar.getInstance().getTime();
	
	public long getChargeID() {
		return chargeID;
	}
	public void setChargeID(long chargeID) {
		this.chargeID = chargeID;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public String getReceivetime() {
		return receivetime;
	}
	public void setReceivetime(String receivetime) {
		this.receivetime = receivetime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getProducttype() {
		return producttype;
	}
	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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
	public java.util.Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

}
	public String getAccessID() {
		return accessID;
	}
	public void setAccessID(String accessID) {
		this.accessID = accessID;
	}
	public String getChannelID() {
		return channelID;
	}
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}
	public String getRuleID() {
		return ruleID;
	}
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getYearmonth() {
		return yearmonth;
	}
	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}
	public String getCurrdate() {
		return currdate;
	}
	public void setCurrdate(String currdate) {
		this.currdate = currdate;
	}
	public float getFailmount() {
		return failmount;
	}
	public void setFailmount(float failmount) {
		this.failmount = failmount;
	}
	
}
