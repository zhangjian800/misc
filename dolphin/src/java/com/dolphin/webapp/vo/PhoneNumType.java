package com.dolphin.webapp.vo;

import com.dolphin.common.vo.DOLValueObject;

public class PhoneNumType implements DOLValueObject {
	
	private String phonePrefix;
	private String citycode;
	private String simType;
	
	private String provincecode;
	
	public String getPhonePrefix() {
		return phonePrefix;
	}
	public void setPhonePrefix(String phonePrefix) {
		this.phonePrefix = phonePrefix;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getSimType() {
		return simType;
	}
	public void setSimType(String simType) {
		this.simType = simType;
	}
	public String getProvincecode() {
		return provincecode;
	}
	public void setProvincecode(String provincecode) {
		this.provincecode = provincecode;
	}
	
}
