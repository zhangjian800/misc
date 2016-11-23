package com.dolphin.webapp.vo;

import java.util.Calendar;

import com.dolphin.common.vo.DOLValueObject;

public class AdsDetailContent implements DOLValueObject{
	
	private long adsID;
	private String detailID;
	private int seq;
	private String targetphonenum;
	private String adscontent;
	private String status = "New";
	

	private String adsprovider;
	private String yearmonth;
    private java.util.Date generatetime = Calendar.getInstance().getTime();
    
    
	public long getAdsID() {
		return adsID;
	}
	public void setAdsID(long adsID) {
		this.adsID = adsID;
	}
	public String getTargetphonenum() {
		return targetphonenum;
	}
	public void setTargetphonenum(String targetphonenum) {
		this.targetphonenum = targetphonenum;
	}
	public String getAdscontent() {
		return adscontent;
	}
	public void setAdscontent(String adscontent) {
		this.adscontent = adscontent;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDetailID() {
		return detailID;
	}
	public void setDetailID(String detailID) {
		this.detailID = detailID;
	}
	
	public String toScript() throws Exception{
		return "num="+ getTargetphonenum() + "con=" + new String(getAdscontent().getBytes(), "UTF-8");
	}
	public String getAdsprovider() {
		return adsprovider;
	}
	public void setAdsprovider(String adsprovider) {
		this.adsprovider = adsprovider;
	}
	public String getYearmonth() {
		return yearmonth;
	}
	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}
	public java.util.Date getGeneratetime() {
		return generatetime;
	}
	public void setGeneratetime(java.util.Date generatetime) {
		this.generatetime = generatetime;
	}
}