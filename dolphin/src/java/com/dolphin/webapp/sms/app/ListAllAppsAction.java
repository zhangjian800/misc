package com.dolphin.webapp.sms.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.App;

public class ListAllAppsAction extends GenericAction {

	private AppMgr appMgr = null;
	
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<App> listResult = appMgr.listAllApp();
		request.setAttribute("AppList", listResult);
		return mapping.findForward("list");	
	}


	public void setAppMgr(AppMgr appMgr) {
		this.appMgr = appMgr;
	}
	
}