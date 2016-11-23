package com.dolphin.webapp.vo;

import com.dolphin.common.vo.DOLValueObject;

public class AccessMain implements DOLValueObject{
	
	private String imsi;
	private String productType;
	private String version;
	private String smsc;
	private String phoneno;
	
	private String province;
	private String city;
	
    private java.util.Date publishDate = null;
    private java.util.Date firstAccessTime = null;
	private String status = "Enable";
	private String serviceprovider = null;
	
	private int dataversion = 0;
	private String flag4specialrule = "0";

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getSmsc() {
		return smsc;
	}

	public void setSmsc(String smsc) {
		this.smsc = smsc;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
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

	public java.util.Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(java.util.Date publishDate) {
		this.publishDate = publishDate;
	}

	public java.util.Date getFirstAccessTime() {
		return firstAccessTime;
	}

	public void setFirstAccessTime(java.util.Date firstAccessTime) {
		this.firstAccessTime = firstAccessTime;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDataversion() {
		return dataversion;
	}
	public void setDataversion(int dataversion) {
		this.dataversion = dataversion;
	}

	public String getServiceprovider() {
		return serviceprovider;
	}

	public void setServiceprovider(String serviceprovider) {
		this.serviceprovider = serviceprovider;
	}

	public String getFlag4specialrule() {
		return flag4specialrule;
	}

	public void setFlag4specialrule(String flag4specialrule) {
		this.flag4specialrule = flag4specialrule;
	}
	
}