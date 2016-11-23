package com.dolphin.webapp.vo;

import com.dolphin.common.vo.DOLValueObject;

public class SyncObject implements DOLValueObject{
	
	String type = null;
	
	public SyncObject(String type){
		this.type = type;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
