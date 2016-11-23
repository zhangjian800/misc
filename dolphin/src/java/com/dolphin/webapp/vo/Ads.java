package com.dolphin.webapp.vo;

import java.util.Calendar;

import com.dolphin.common.vo.DOLValueObject;

public class Ads implements DOLValueObject{
	
	private long adsID;
	private String instanceID;
	private String adsprovider;
	private int size;
	private int seq = 1;
	
	private String adscontent;
	private String adsproviderurl;
	private String yearmonth;
    private java.util.Date generatetime = Calendar.getInstance().getTime();
	private String status = "New";
	
	public long getAdsID() {
		return adsID;
	}
	public void setAdsID(long adsID) {
		this.adsID = adsID;
	}
	public String getAdsprovider() {
		return adsprovider;
	}
	public void setAdsprovider(String adsprovider) {
		this.adsprovider = adsprovider;
	}
	public String getAdscontent() {
		return adscontent;
	}
	public void setAdscontent(String adscontent) {
		this.adscontent = adscontent;
	}
	public java.util.Date getGeneratetime() {
		return generatetime;
	}
	public void setGeneratetime(java.util.Date generatetime) {
		this.generatetime = generatetime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getInstanceID() {
		return instanceID;
	}
	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}
	public String getAdsproviderurl() {
		return adsproviderurl;
	}
	public void setAdsproviderurl(String adsproviderurl) {
		this.adsproviderurl = adsproviderurl;
	}
	public String getYearmonth() {
		return yearmonth;
	}
	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}
	
}
