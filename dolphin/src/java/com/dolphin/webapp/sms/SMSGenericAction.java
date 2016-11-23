package com.dolphin.webapp.sms;

import com.dolphin.webapp.biz.AccessMgr;
import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.biz.PublicMgr;
import com.dolphin.webapp.biz.RuleMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;

public abstract class SMSGenericAction extends GenericAction{
	protected AccessMgr accessMgr;
	protected PublicMgr pubMgr;
	protected RuleMgr ruleMgr;
	protected AppMgr appMgr;
	public void setAccessMgr(AccessMgr accessMgr) {
		this.accessMgr = accessMgr;
	}
	public void setPubMgr(PublicMgr pubMgr) {
		this.pubMgr = pubMgr;
	}
	
	public void setRuleMgr(RuleMgr ruleMgr) {
		this.ruleMgr = ruleMgr;
	}
	public void setAppMgr(AppMgr appMgr) {
		this.appMgr = appMgr;
	}
}
