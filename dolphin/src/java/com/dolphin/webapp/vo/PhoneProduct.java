package com.dolphin.webapp.vo;

import com.dolphin.common.vo.DOLValueObject;

public class PhoneProduct implements DOLValueObject {

	private String productcode;
	private String productdesc;
	private String version;
	//Enable
	//Disalbe...
	private String status = "Enable";
	
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getProductdesc() {
		return productdesc;
	}
	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
