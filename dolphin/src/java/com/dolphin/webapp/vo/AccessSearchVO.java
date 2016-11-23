package com.dolphin.webapp.vo;

import com.dolphin.common.vo.DOLValueObject;

public class AccessSearchVO implements DOLValueObject{
	
	private int ruleID = 0;
	private String currdate;
	private String imsi;
	private String version;
	private String productcode;
	private String province;
	private String city;
	private String yearmonth;
	private String respstatus;
	private String beginAccessTime;
	private String endAccessTime;
	
	private int startNum = 0;  
	private int endNum; //Only show 200 records
	private int maxlimit;
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
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
	public String getYearmonth() {
		return yearmonth;
	}
	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}
	public String getBeginAccessTime() {
		return beginAccessTime;
	}
	public void setBeginAccessTime(String beginAccessTime) {
		this.beginAccessTime = beginAccessTime;
	}
	public String getEndAccessTime() {
		return endAccessTime;
	}
	public void setEndAccessTime(String endAccessTime) {
		this.endAccessTime = endAccessTime;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getRespstatus() {
		return respstatus;
	}
	public void setRespstatus(String respstatus) {
		this.respstatus = respstatus;
	}
	public int getMaxlimit() {
		return maxlimit;
	}
	public void setMaxlimit(int maxlimit) {
		this.maxlimit = maxlimit;
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
