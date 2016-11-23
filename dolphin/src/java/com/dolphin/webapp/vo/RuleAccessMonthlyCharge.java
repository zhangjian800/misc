package com.dolphin.webapp.vo;

import java.util.Calendar;

import com.dolphin.common.vo.DOLValueObject;

public class RuleAccessMonthlyCharge implements DOLValueObject{
	
	private int ruleID;
	private String yearmonth;
	private int hitssum;
	private float chargesum;
	private String status = "Normal";
	private int dataversion = 1;
	private boolean isFirst;
    private java.util.Date updatetime = Calendar.getInstance().getTime();

	public int getRuleID() {
		return ruleID;
	}

	public void setRuleID(int ruleID) {
		this.ruleID = ruleID;
	}

	public String getYearmonth() {
		return yearmonth;
	}

	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}

	public int getHitssum() {
		return hitssum;
	}

	public void setHitssum(int hitssum) {
		this.hitssum = hitssum;
	}

	public float getChargesum() {
		return chargesum;
	}

	public void setChargesum(float chargesum) {
		this.chargesum = chargesum;
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

	public java.util.Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

}
