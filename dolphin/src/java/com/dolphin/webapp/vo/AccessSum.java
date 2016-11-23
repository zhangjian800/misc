package com.dolphin.webapp.vo;

import java.util.Calendar;
import java.util.Date;

import com.dolphin.common.vo.DOLValueObject;

public class AccessSum implements DOLValueObject{
	private String imsi;
	private String yearmonth;
	private int maxhits;
	private float maxcharge;
	private int dataversion = 0;
	private String chargstatus = "Normal";
	private String hitsstatus = "Normal";
	  
	private boolean isFirst;
	private Date updatetime = Calendar.getInstance().getTime();
	
	private float correctfailmount;
	private int times = 0;
	private String chargeids;
	
	private String flag4specialrule;
	
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getYearmonth() {
		return yearmonth;
	}
	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}
	public int getMaxhits() {
		return maxhits;
	}
	public void setMaxhits(int maxhits) {
		this.maxhits = maxhits;
	}
	public float getMaxcharge() {
		return maxcharge;
	}
	public void setMaxcharge(float maxcharge) {
		this.maxcharge = maxcharge;
	}
	public int getDataversion() {
		return dataversion;
	}
	public void setDataversion(int dataversion) {
		this.dataversion = dataversion;
	}
	public String getChargstatus() {
		return chargstatus;
	}
	public void setChargstatus(String chargstatus) {
		this.chargstatus = chargstatus;
	}
	public String getHitsstatus() {
		return hitsstatus;
	}
	public void setHitsstatus(String hitsstatus) {
		this.hitsstatus = hitsstatus;
	}
	public boolean isFirst() {
		return isFirst;
	}
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public float getCorrectfailmount() {
		return correctfailmount;
	}
	public void setCorrectfailmount(float correctfailmount) {
		this.correctfailmount = correctfailmount;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getChargeids() {
		return chargeids;
	}
	public void setChargeids(String chargeids) {
		this.chargeids = chargeids;
	}
	public String getFlag4specialrule() {
		return flag4specialrule;
	}
	public void setFlag4specialrule(String flag4specialrule) {
		this.flag4specialrule = flag4specialrule;
	}
	
}