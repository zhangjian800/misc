package com.dolphin.webapp.vo;

public class ConfirmChargeRptSearchVO extends AccessSearchVO{
	private String mobile;
	private String beginUpdateTime;
	private String endUpdateTime;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBeginUpdateTime() {
		return beginUpdateTime;
	}
	public void setBeginUpdateTime(String beginUpdateTime) {
		this.beginUpdateTime = beginUpdateTime;
	}
	public String getEndUpdateTime() {
		return endUpdateTime;
	}
	public void setEndUpdateTime(String endUpdateTime) {
		this.endUpdateTime = endUpdateTime;
	}
	
}
