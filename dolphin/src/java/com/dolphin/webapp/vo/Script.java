package com.dolphin.webapp.vo;

import java.util.Calendar;

import com.dolphin.common.vo.DOLValueObject;

public class Script implements DOLValueObject{

	private long scriptID;
	private String scriptCode;
	private String scriptDesc;
	private String scriptcontent;

	private String creator;
	private String updater;
	
    private java.util.Date createTime = Calendar.getInstance().getTime();
    private java.util.Date updateTime = Calendar.getInstance().getTime();
	public long getScriptID() {
		return scriptID;
	}
	public void setScriptID(long scriptID) {
		this.scriptID = scriptID;
	}
	public String getScriptCode() {
		return scriptCode;
	}
	public void setScriptCode(String scriptCode) {
		this.scriptCode = scriptCode;
	}
	public String getScriptDesc() {
		return scriptDesc;
	}
	public void setScriptDesc(String scriptDesc) {
		this.scriptDesc = scriptDesc;
	}
	public String getScriptcontent() {
		return scriptcontent;
	}
	public void setScriptcontent(String scriptcontent) {
		this.scriptcontent = scriptcontent;
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
    
}
