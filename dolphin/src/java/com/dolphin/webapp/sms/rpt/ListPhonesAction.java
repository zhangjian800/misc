package com.dolphin.webapp.sms.rpt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.common.log.DolpiLogger;
import com.dolphin.webapp.biz.AccessMgr;
import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.biz.PublicMgr;
import com.dolphin.webapp.biz.RuleMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;

public class ListPhonesAction extends GenericAction {

	DolpiLogger logger = new DolpiLogger(ListPhonesAction.class.getName());

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
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}

}
