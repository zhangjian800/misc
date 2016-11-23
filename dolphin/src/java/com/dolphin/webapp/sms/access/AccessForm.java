package com.dolphin.webapp.sms.access;

import com.dolphin.webapp.common.web.struts.GenericForm;


public class AccessForm extends GenericForm{
	
	private int accessID;
	private int simType;
	private String imsi;
	private String productType;
	private String version;
	private String pid;
	private String smsc;
	private String province;
	private String city;
	private String lbs;
	private String platformType;
	private int maxlimit;
	
	private java.util.Date publishDate;
	
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
	public java.util.Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(java.util.Date publishDate) {
		this.publishDate = publishDate;
	}
	public int getSimType() {
		return simType;
	}
	public void setSimType(int simType) {
		this.simType = simType;
	}
	public int getMaxlimit() {
		return maxlimit;
	}
	public void setMaxlimit(int maxlimit) {
		this.maxlimit = maxlimit;
	}
}
