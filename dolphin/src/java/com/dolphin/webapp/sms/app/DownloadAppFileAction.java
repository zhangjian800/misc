package com.dolphin.webapp.sms.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;

public class DownloadAppFileAction  extends GenericAction {

	private AppMgr appMgr = null;
	
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("create");
	}
	
	public void setAppMgr(AppMgr appMgr) {
		this.appMgr = appMgr;
	}	
}