package com.dolphin.webapp.vo;

import java.util.Calendar;

import com.dolphin.common.vo.DOLValueObject;

public class RuleAccessDailyCharge implements DOLValueObject{
	
	private int ruleID;
	private String currdate;ruledescte String currdate;
	private int hitssum;
	private float chargesum;
	private String status = "Normal";
	private int dataversion = 1;
	private boolean isFirst;

    private java.util.Date updatetime = Calendar.getInstance().getTime();

	public int getR    
	public String getRuledesc() {
		return ruledesc;
	}

	public void setRuledesc(String ruledesc) {
		this.ruledesc = ruledesc;
	}tRuleID() {
		return ruleID;
	}

	public void setRuleID(int ruleID) {
		this.ruleID = ruleID;
	}

	public String getCurrdate() {
		return currdate;
	}

	public void setCurrdate(String currdate) {
		this.currdate = currdate;
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
