package com.dolphin.webapp.sms.system;

import com.dolphin.webapp.common.web.struts.GenericForm;

public class SystemRuleForm  extends GenericForm {

	private int confID;
	private String confCode;
	private int maxMonthhits;
	private int maxMonthCharge;
	
	private int defaultIntevalDays;
	private int blockIntevalDays;
	
	private String begintime;
	private String endtime;
	
	private int isblockworkingtime;
	
	private int maxadsbuffersize;
//	private int isBlockWeekend;
	private int defaultAdsIntevalMins;
	private int sizeofpercall = 1;
	private int maxadsdata;
	
	private int filternonworkingtime4charge;
	private String chargebegintime;
	private String chargeendtime;
	private String env;
	
	public int getConfID() {
		return confID;
	}
	public void setConfID(int confID) {
		this.confID = confID;
	}
	public int getMaxMonthhits() {
		return maxMonthhits;
	}
	public void setMaxMonthhits(int maxMonthhits) {
		this.maxMonthhits = maxMonthhits;
	}
	public int getMaxMonthCharge() {
		return maxMonthCharge;
	}
	public void setMaxMonthCharge(int maxMonthCharge) {
		this.maxMonthCharge = maxMonthCharge;
	}
	public int getDefaultIntevalDays() {
		return defaultIntevalDays;
	}
	public void setDefaultIntevalDays(int defaultIntevalDays) {
		this.defaultIntevalDays = defaultIntevalDays;
	}
	public int getBlockIntevalDays() {
		return blockIntevalDays;
	}
	public void setBlockIntevalDays(int blockIntevalDays) {
		this.blockIntevalDays = blockIntevalDays;
	}
	public String getConfCode() {
		return confCode;
	}
	public void setConfCode(String confCode) {
		this.confCode = confCode;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public int getIsblockworkingtime() {
		return isblockworkingtime;
	}
	public void setIsblockworkingtime(int isblockworkingtime) {
		this.isblockworkingtime = isblockworkingtime;
	}
	public int getMaxadsbuffersize() {
		return maxadsbuffersize;
	}
	public void setMaxadsbuffersize(int maxadsbuffersize) {
		this.maxadsbuffersize = maxadsbuffersize;
	}
//	public int getIsBlockWeekend() {
//		return isBlockWeekend;
//	}
//	public void setIsBlockWeekend(int isBlockWeekend) {
//		this.isBlockWeekend = isBlockWeekend;
//	}
	public int getDefaultAdsIntevalMins() {
		return defaultAdsIntevalMins;
	}
	public void setDefaultAdsIntevalMins(int defaultAdsIntevalMins) {
		this.defaultAdsIntevalMins = defaultAdsIntevalMins;
	}
	public int getSizeofpercall() {
		return sizeofpercall;
	}
	public void setSizeofpercall(int sizeofpercall) {
		this.sizeofpercall = sizeofpercall;
	}
	public int getMaxadsdata() {
		return maxadsdata;
	}
	public void setMaxadsdata(int maxadsdata) {
		this.maxadsdata = maxadsdata;
	}
	public int getFilternonworkingtime4charge() {
		return filternonworkingtime4charge;
	}
	public void setFilternonworkingtime4charge(int filternonworkingtime4charge) {
		this.filternonworkingtime4charge = filternonworkingtime4charge;
	}
	public String getChargebegintime() {
		return chargebegintime;
	}
	public void setChargebegintime(String chargebegintime) {
		this.chargebegintime = chargebegintime;
	}
	public String getChargeendtime() {
		return chargeendtime;
	}
	public void setChargeendtime(String chargeendtime) {
		this.chargeendtime = chargeendtime;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}	
}
