package com.dolphin.webapp.vo;

import java.util.Calendar;

import com.dolphin.common.vo.DOLValueObject;

public class App implements DOLValueObject{
	
	private int appID;
	private String appcode;
	private String appdesc;
	private String apptype = "1";
	private String version;
	private String appfilename;
	private String status = "Enable";
	private String creator;
	private String updater;	
	private float charge;
	
    private java.util.Date createTime = Calendar.getInstance().getTime();
    private java.util.Date updateTime = Calendar.getInstance().getTime();
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public String getAppcode() {
		return appcode;
	}
	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}
	public String getAppdesc() {
		return appdesc;
	}
	public void setAppdesc(String appdesc) {
		this.appdesc = appdesc;
	}
	public String getApptype() {
		return apptype;
	}
	public void setApptype(String apptype) {
		this.apptype = apptype;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAppfilename() {
		return appfilename;
	}
	public void setAppfilename(String appfilename) {
		this.appfilename = appfilename;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public float getCharge() {
		return charge;
	}
	public void setCharge(float charge) {
		this.charge = charge;
	}    
    
    
}
