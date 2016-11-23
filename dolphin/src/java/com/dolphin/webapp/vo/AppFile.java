package com.dolphin.webapp.vo;

import com.dolphin.common.vo.DOLValueObject;

public class AppFile implements DOLValueObject{
	
	private int appID;
	private String appfilename;
	private byte[] appstream;
	
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public String getAppfilename() {
		return appfilename;
	}
	public void setAppfilename(String appfilename) {
		this.appfilename = appfilename;
	}
	public byte[] getAppstream() {
		return appstream;
	}
	public void setAppstream(byte[] appstream) {
		this.appstream = appstream;
	}
	
}
